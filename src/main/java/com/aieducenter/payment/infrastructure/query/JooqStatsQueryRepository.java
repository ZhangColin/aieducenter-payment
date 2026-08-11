package com.aieducenter.payment.infrastructure.query;

import com.aieducenter.payment.domain.enums.AuditType;
import com.aieducenter.payment.domain.enums.OperationType;
import com.aieducenter.payment.domain.enums.PaymentStatus;
import com.aieducenter.payment.domain.enums.RefundStatus;
import com.aieducenter.payment.domain.enums.StatsGranularity;
import com.aieducenter.payment.infrastructure.query.projection.AuditOperationCount;
import com.aieducenter.payment.infrastructure.query.projection.AuditorAuditCount;
import com.aieducenter.payment.infrastructure.query.projection.GatewayInterfaceRollup;
import com.aieducenter.payment.infrastructure.query.projection.GatewayReturnCodeCount;
import com.aieducenter.payment.infrastructure.query.projection.PaymentStatusCount;
import com.aieducenter.payment.infrastructure.query.projection.PaymentTrendBucket;
import com.aieducenter.payment.infrastructure.query.projection.RefundStatusCount;
import com.aieducenter.payment.infrastructure.query.projection.RefundTrendBucket;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.Query;
import org.jooq.ResultQuery;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 统计读侧 jOOQ 实现（issue #17）。
 *
 * <p><b>不单测</b>（沿用 spec「不引入 @DataJpaTest」——查询正确性靠显式可读 SQL + 框架；
 * 装配逻辑由 {@code StatsAppServiceTest} 在 AppService 缝覆盖）。PostgreSQL 方言。</p>
 *
 * <p><b>列别名双引号</b>：Postgres 折叠未加引号标识符为小写，故投影列必须
 * {@code AS "orderCount"} 加双引号以保留驼峰、与投影 record 组件名一一对应（jOOQ fetchInto 按名映射）。</p>
 *
 * <p><b>绑定方式</b>：{@code from}/{@code to}/{@code *Code} 走命名绑定（防注入）；
 * {@link StatsGranularity#sqlLiteral()} 是白名单字面量（"day"/"hour"）内联到 {@code date_trunc}
 * 首参（text literal 不能绑定）。bind 以语句形式调用以保留 {@link ResultQuery} 静态类型（fetchInto 在其上）。</p>
 */
@Repository
@RequiredArgsConstructor
public class JooqStatsQueryRepository implements StatsQueryRepository {

    private final DSLContext dsl;

    @Override
    public List<PaymentStatusCount> countPaymentByStatus(LocalDateTime from, LocalDateTime to) {
        ResultQuery<?> q = dsl.resultQuery("""
                SELECT status AS "status",
                       COUNT(*) AS "orderCount",
                       COALESCE(SUM(amount), 0) AS "totalAmount",
                       COALESCE(SUM(actual_amount), 0) AS "paidAmount"
                  FROM pay_payment_orders
                """ + whereDeleted(from, to) + """
                 GROUP BY status
                """);
        bindWindow(q, from, to);
        return q.fetchInto(PaymentStatusCount.class);
    }

    @Override
    public List<RefundStatusCount> countRefundByStatus(LocalDateTime from, LocalDateTime to) {
        ResultQuery<?> q = dsl.resultQuery("""
                SELECT status AS "status",
                       COUNT(*) AS "orderCount",
                       COALESCE(SUM(refund_amount), 0) AS "totalAmount"
                  FROM pay_refund_orders
                """ + whereDeleted(from, to) + """
                 GROUP BY status
                """);
        bindWindow(q, from, to);
        return q.fetchInto(RefundStatusCount.class);
    }

    @Override
    public List<PaymentTrendBucket> paymentTrend(LocalDateTime from, LocalDateTime to, StatsGranularity granularity) {
        // status = PaymentStatus.PAID.getCode() 标记支付成功（CASE 聚合到每桶）
        int paid = PaymentStatus.PAID.getCode();
        String trunc = "date_trunc('" + granularity.sqlLiteral() + "', created_at)";
        ResultQuery<?> q = dsl.resultQuery("""
                SELECT %s AS "bucketStart",
                       COUNT(*) AS "orderCount",
                       COALESCE(SUM(amount), 0) AS "totalAmount",
                       SUM(CASE WHEN status = %d THEN 1 ELSE 0 END) AS "paidCount",
                       COALESCE(SUM(CASE WHEN status = %d THEN actual_amount ELSE 0 END), 0) AS "paidAmount"
                  FROM pay_payment_orders
                 WHERE deleted = FALSE AND created_at BETWEEN :from AND :to
                 GROUP BY %s
                 ORDER BY 1
                """.formatted(trunc, paid, paid, trunc));
        bindWindow(q, from, to);
        return q.fetchInto(PaymentTrendBucket.class);
    }

    @Override
    public List<RefundTrendBucket> refundTrend(LocalDateTime from, LocalDateTime to, StatsGranularity granularity) {
        // status = RefundStatus.SUCCESS.getCode() 标记退款成功
        int success = RefundStatus.SUCCESS.getCode();
        String trunc = "date_trunc('" + granularity.sqlLiteral() + "', created_at)";
        ResultQuery<?> q = dsl.resultQuery("""
                SELECT %s AS "bucketStart",
                       COUNT(*) AS "orderCount",
                       COALESCE(SUM(refund_amount), 0) AS "totalAmount",
                       SUM(CASE WHEN status = %d THEN 1 ELSE 0 END) AS "refundedCount",
                       COALESCE(SUM(CASE WHEN status = %d THEN refund_amount ELSE 0 END), 0) AS "refundedAmount"
                  FROM pay_refund_orders
                 WHERE deleted = FALSE AND created_at BETWEEN :from AND :to
                 GROUP BY %s
                 ORDER BY 1
                """.formatted(trunc, success, success, trunc));
        bindWindow(q, from, to);
        return q.fetchInto(RefundTrendBucket.class);
    }

    @Override
    public List<GatewayInterfaceRollup> gatewayInterfaceRollup(LocalDateTime from, LocalDateTime to) {
        ResultQuery<?> q = dsl.resultQuery("""
                SELECT bank_code AS "bankCode",
                       bank_interface AS "bankInterface",
                       COUNT(*) AS "totalCount",
                       SUM(CASE WHEN success THEN 1 ELSE 0 END) AS "successCount",
                       COALESCE(AVG(execution_time), 0) AS "avgExecutionTime"
                  FROM pay_payment_logs
                """ + whereDeleted(from, to) + """
                 GROUP BY bank_code, bank_interface
                 ORDER BY bank_interface
                """);
        bindWindow(q, from, to);
        return q.fetchInto(GatewayInterfaceRollup.class);
    }

    @Override
    public List<GatewayReturnCodeCount> gatewayReturnCodeCounts(LocalDateTime from, LocalDateTime to) {
        ResultQuery<?> q = dsl.resultQuery("""
                SELECT bank_interface AS "bankInterface",
                       return_code AS "returnCode",
                       COUNT(*) AS "codeCount"
                  FROM pay_payment_logs
                 WHERE deleted = FALSE AND return_code IS NOT NULL
                """ + windowClause(from, to, "created_at", true) + """
                 GROUP BY bank_interface, return_code
                """);
        bindWindow(q, from, to);
        return q.fetchInto(GatewayReturnCodeCount.class);
    }

    @Override
    public List<AuditOperationCount> auditOperationCounts(LocalDateTime from, LocalDateTime to) {
        ResultQuery<?> q = dsl.resultQuery("""
                SELECT operation AS "operation",
                       COUNT(*) AS "opCount"
                  FROM pay_operation_logs
                 WHERE deleted = FALSE
                   AND operation IN (:approveCode, :rejectCode)
                """ + windowClause(from, to, "created_at", true) + """
                 GROUP BY operation
                """);
        q.bind("approveCode", OperationType.AUDIT_APPROVE.getCode());
        q.bind("rejectCode", OperationType.AUDIT_REJECT.getCode());
        bindWindow(q, from, to);
        return q.fetchInto(AuditOperationCount.class);
    }

    @Override
    public List<AuditorAuditCount> auditorAuditCounts(LocalDateTime from, LocalDateTime to) {
        ResultQuery<?> q = dsl.resultQuery("""
                SELECT operator_id AS "auditorId",
                       operator_name AS "auditorName",
                       operation AS "operation",
                       COUNT(*) AS "opCount"
                  FROM pay_operation_logs
                 WHERE deleted = FALSE
                   AND operation IN (:approveCode, :rejectCode)
                """ + windowClause(from, to, "created_at", true) + """
                 GROUP BY operator_id, operator_name, operation
                 ORDER BY operator_id
                """);
        q.bind("approveCode", OperationType.AUDIT_APPROVE.getCode());
        q.bind("rejectCode", OperationType.AUDIT_REJECT.getCode());
        bindWindow(q, from, to);
        return q.fetchInto(AuditorAuditCount.class);
    }

    @Override
    public BigDecimal avgAuditDuration(LocalDateTime from, LocalDateTime to) {
        // 平均审核时长（分钟）= AVG(audited_at - created_at)，仅人工审核且已审结。
        // audit_type = AuditType.MANUAL.getCode()；时间窗作用在 audited_at。
        ResultQuery<?> q = dsl.resultQuery("""
                SELECT COALESCE(AVG(EXTRACT(EPOCH FROM (audited_at - created_at)) / 60), 0) AS "avgDurationMinutes"
                  FROM pay_refund_orders
                 WHERE deleted = FALSE
                   AND audit_type = :manualCode
                   AND audited_at IS NOT NULL
                """ + windowClause(from, to, "audited_at", true));
        q.bind("manualCode", AuditType.MANUAL.getCode());
        bindWindow(q, from, to);
        return q.fetchOne(0, BigDecimal.class);
    }

    // ==================== SQL 拼接辅助 ====================

    /** {@code WHERE deleted = FALSE [AND created_at BETWEEN :from AND :to]}（独立 WHERE 起头）。 */
    private static String whereDeleted(LocalDateTime from, LocalDateTime to) {
        return windowClause(from, to, "created_at", false);
    }

    /**
     * 时间窗片段。
     *
     * @param withLeadingAnd true：以 {@code AND <col> BETWEEN ...} 接续既有 WHERE 条件；
     *                       false：以 {@code WHERE deleted = FALSE [AND ...]} 起头。
     *                       无窗口（from/to 为 null）时：true→空串，false→仅 {@code WHERE deleted = FALSE}。
     */
    private static String windowClause(LocalDateTime from, LocalDateTime to, String column, boolean withLeadingAnd) {
        if (from == null || to == null) {
            return withLeadingAnd ? "" : " WHERE deleted = FALSE";
        }
        if (withLeadingAnd) {
            return "\n   AND " + column + " BETWEEN :from AND :to";
        }
        return " WHERE deleted = FALSE AND " + column + " BETWEEN :from AND :to";
    }

    private static void bindWindow(Query q, LocalDateTime from, LocalDateTime to) {
        if (from != null && to != null) {
            q.bind("from", from);
            q.bind("to", to);
        }
    }
}
