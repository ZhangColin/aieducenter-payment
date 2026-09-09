package com.aieducenter.payment.infrastructure.query;

import com.aieducenter.payment.domain.enums.AuditType;
import com.aieducenter.payment.domain.enums.OperationType;
import com.aieducenter.payment.domain.enums.PaymentStatus;
import com.aieducenter.payment.domain.enums.RefundStatus;
import com.aieducenter.payment.domain.enums.StatsGranularity;
import com.aieducenter.payment.infrastructure.query.projection.AuditOperationCount;
import com.aieducenter.payment.infrastructure.query.projection.AuditorAuditCount;
import com.aieducenter.payment.infrastructure.query.projection.BusinessSystemPaymentRollup;
import com.aieducenter.payment.infrastructure.query.projection.BusinessSystemRefundRollup;
import com.aieducenter.payment.infrastructure.query.projection.ChannelPaymentRollup;
import com.aieducenter.payment.infrastructure.query.projection.FailureCountByType;
import com.aieducenter.payment.infrastructure.query.projection.GatewayInterfaceRollup;
import com.aieducenter.payment.infrastructure.query.projection.GatewayReturnCodeCount;
import com.aieducenter.payment.infrastructure.query.projection.NotifyResendBySystem;
import com.aieducenter.payment.infrastructure.query.projection.OperatorOperationCount;
import com.aieducenter.payment.infrastructure.query.projection.PaymentStatusCount;
import com.aieducenter.payment.infrastructure.query.projection.PaymentTrendBucket;
import com.aieducenter.payment.infrastructure.query.projection.RefundStatusCount;
import com.aieducenter.payment.infrastructure.query.projection.RefundTrendBucket;
import com.aieducenter.payment.infrastructure.query.projection.StuckOrderCount;
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
 * <p><b>测试边界</b>：不引入 @DataJpaTest（沿用 spec 约定）；装配逻辑由
 * {@code StatsAppServiceTest} 在 AppService 缝覆盖，命名绑定的可用性由
 * {@code JooqStatsQueryRepositoryTest} 在无 DB 的 parse+bind 缝覆盖。PostgreSQL 方言。</p>
 *
 * <p><b>列别名双引号</b>：Postgres 折叠未加引号标识符为小写，故投影列必须
 * {@code AS "orderCount"} 加双引号以保留驼峰、与投影 record 组件名一一对应（jOOQ fetchInto 按名映射）。</p>
 *
 * <p><b>绑定方式</b>：必须经 {@code dsl.parser().parseResultQuery(...)} 构造——plain
 * {@code resultQuery} 不注册任何命名参数（getParams 恒空），{@code bind(name, ...)} 必抛
 * {@code IllegalArgumentException: No such parameter}；且 parser 会把标识符折叠为小写，
 * 故 SQL 参数名一律 <b>snake_case</b>（如 {@code :pending_code}），bind 字面量须与之逐字一致。
 * {@link StatsGranularity#sqlLiteral()} 是白名单字面量（"day"/"hour"）内联到 {@code date_trunc}
 * 首参（text literal 不能绑定）。bind 以语句形式调用以保留 {@link ResultQuery} 静态类型（fetchInto 在其上）。</p>
 */
@Repository
@RequiredArgsConstructor
public class JooqStatsQueryRepository implements StatsQueryRepository {

    private final DSLContext dsl;

    @Override
    public List<PaymentStatusCount> countPaymentByStatus(LocalDateTime from, LocalDateTime to) {
        ResultQuery<?> q = dsl.parser().parseResultQuery("""
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
        ResultQuery<?> q = dsl.parser().parseResultQuery("""
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
        ResultQuery<?> q = dsl.parser().parseResultQuery("""
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
        ResultQuery<?> q = dsl.parser().parseResultQuery("""
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
        ResultQuery<?> q = dsl.parser().parseResultQuery("""
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
        ResultQuery<?> q = dsl.parser().parseResultQuery("""
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
        ResultQuery<?> q = dsl.parser().parseResultQuery("""
                SELECT operation AS "operation",
                       COUNT(*) AS "opCount"
                  FROM pay_operation_logs
                 WHERE deleted = FALSE
                   AND operation IN (:approve_code, :reject_code)
                """ + windowClause(from, to, "created_at", true) + """
                 GROUP BY operation
                """);
        q.bind("approve_code", OperationType.AUDIT_APPROVE.getCode());
        q.bind("reject_code", OperationType.AUDIT_REJECT.getCode());
        bindWindow(q, from, to);
        return q.fetchInto(AuditOperationCount.class);
    }

    @Override
    public List<AuditorAuditCount> auditorAuditCounts(LocalDateTime from, LocalDateTime to) {
        ResultQuery<?> q = dsl.parser().parseResultQuery("""
                SELECT operator_id AS "auditorId",
                       operator_name AS "auditorName",
                       operation AS "operation",
                       COUNT(*) AS "opCount"
                  FROM pay_operation_logs
                 WHERE deleted = FALSE
                   AND operation IN (:approve_code, :reject_code)
                """ + windowClause(from, to, "created_at", true) + """
                 GROUP BY operator_id, operator_name, operation
                 ORDER BY operator_id
                """);
        q.bind("approve_code", OperationType.AUDIT_APPROVE.getCode());
        q.bind("reject_code", OperationType.AUDIT_REJECT.getCode());
        bindWindow(q, from, to);
        return q.fetchInto(AuditorAuditCount.class);
    }

    @Override
    public BigDecimal avgAuditDuration(LocalDateTime from, LocalDateTime to) {
        // 平均审核时长（分钟）= AVG(audited_at - created_at)，仅人工审核且已审结。
        // audit_type = AuditType.MANUAL.getCode()；时间窗作用在 audited_at。
        ResultQuery<?> q = dsl.parser().parseResultQuery("""
                SELECT COALESCE(AVG(EXTRACT(EPOCH FROM (audited_at - created_at)) / 60), 0) AS "avgDurationMinutes"
                  FROM pay_refund_orders
                 WHERE deleted = FALSE
                   AND audit_type = :manual_code
                   AND audited_at IS NOT NULL
                """ + windowClause(from, to, "audited_at", true));
        q.bind("manual_code", AuditType.MANUAL.getCode());
        bindWindow(q, from, to);
        return q.fetchOne(0, BigDecimal.class);
    }

    // ==================== 统计二档（issue #18） ====================

    @Override
    public List<BusinessSystemPaymentRollup> businessSystemPaymentRollup(LocalDateTime from, LocalDateTime to) {
        // status = PaymentStatus.PAID.getCode() 标记支付成功（CASE 聚合到每组）
        // business_system_name 为 NOT NULL（V1），无需 IS NOT NULL 过滤。
        ResultQuery<?> q = dsl.parser().parseResultQuery("""
                SELECT business_system_name AS "businessSystemName",
                       COUNT(*) AS "orderCount",
                       COALESCE(SUM(amount), 0) AS "totalAmount",
                       SUM(CASE WHEN status = :paid_code THEN 1 ELSE 0 END) AS "paidCount",
                       COALESCE(SUM(CASE WHEN status = :paid_code THEN actual_amount ELSE 0 END), 0) AS "paidAmount"
                  FROM pay_payment_orders
                """ + whereDeleted(from, to) + """
                 GROUP BY business_system_name
                 ORDER BY business_system_name
                """);
        q.bind("paid_code", PaymentStatus.PAID.getCode());
        bindWindow(q, from, to);
        return q.fetchInto(BusinessSystemPaymentRollup.class);
    }

    @Override
    public List<BusinessSystemRefundRollup> businessSystemRefundRollup(LocalDateTime from, LocalDateTime to) {
        // status = RefundStatus.SUCCESS.getCode() 标记退款成功
        ResultQuery<?> q = dsl.parser().parseResultQuery("""
                SELECT business_system_name AS "businessSystemName",
                       COUNT(*) AS "orderCount",
                       COALESCE(SUM(refund_amount), 0) AS "totalAmount",
                       SUM(CASE WHEN status = :success_code THEN 1 ELSE 0 END) AS "refundedCount",
                       COALESCE(SUM(CASE WHEN status = :success_code THEN refund_amount ELSE 0 END), 0) AS "refundedAmount"
                  FROM pay_refund_orders
                """ + whereDeleted(from, to) + """
                 GROUP BY business_system_name
                 ORDER BY business_system_name
                """);
        q.bind("success_code", RefundStatus.SUCCESS.getCode());
        bindWindow(q, from, to);
        return q.fetchInto(BusinessSystemRefundRollup.class);
    }

    @Override
    public List<ChannelPaymentRollup> payModeRollup(LocalDateTime from, LocalDateTime to) {
        return channelRollup(from, to, "pay_mode");
    }

    @Override
    public List<ChannelPaymentRollup> accessTypeRollup(LocalDateTime from, LocalDateTime to) {
        return channelRollup(from, to, "access_type");
    }

    /**
     * 按某渠道列（pay_mode / access_type）聚合支付。
     *
     * <p>列名 {@code column} 是本仓内部白名单字面量（两选一），不来自外部输入，无注入风险。
     * 仅聚合该列非空的行（预支付前未赋值的支付单不计入渠道分布）。</p>
     */
    private List<ChannelPaymentRollup> channelRollup(LocalDateTime from, LocalDateTime to, String column) {
        // status = PaymentStatus.PAID.getCode() 标记支付成功
        ResultQuery<?> q = dsl.parser().parseResultQuery("""
                SELECT %s AS "channelCode",
                       COUNT(*) AS "orderCount",
                       COALESCE(SUM(amount), 0) AS "totalAmount",
                       SUM(CASE WHEN status = :paid_code THEN 1 ELSE 0 END) AS "paidCount",
                       COALESCE(SUM(CASE WHEN status = :paid_code THEN actual_amount ELSE 0 END), 0) AS "paidAmount"
                  FROM pay_payment_orders
                """.formatted(column)
                + whereDeleted(from, to) + """
                   AND %s IS NOT NULL
                 GROUP BY %s
                 ORDER BY %s
                """.formatted(column, column, column));
        q.bind("paid_code", PaymentStatus.PAID.getCode());
        bindWindow(q, from, to);
        return q.fetchInto(ChannelPaymentRollup.class);
    }

    @Override
    public StuckOrderCount longPendingPayments(long pendingHours) {
        // status = PaymentStatus.PENDING.getCode()；cutoff = NOW() - INTERVAL '1 hour' * :hours
        ResultQuery<?> q = dsl.parser().parseResultQuery("""
                SELECT COUNT(*) AS "orderCount",
                       COALESCE(SUM(amount), 0) AS "totalAmount"
                  FROM pay_payment_orders
                 WHERE deleted = FALSE
                   AND status = :pending_code
                   AND created_at < NOW() - INTERVAL '1 hour' * :hours
                """);
        q.bind("pending_code", PaymentStatus.PENDING.getCode());
        q.bind("hours", pendingHours);
        return q.fetchOneInto(StuckOrderCount.class);
    }

    @Override
    public StuckOrderCount longRefundingRefunds(long refundingHours) {
        // status = RefundStatus.REFUNDING.getCode()
        ResultQuery<?> q = dsl.parser().parseResultQuery("""
                SELECT COUNT(*) AS "orderCount",
                       COALESCE(SUM(refund_amount), 0) AS "totalAmount"
                  FROM pay_refund_orders
                 WHERE deleted = FALSE
                   AND status = :refunding_code
                   AND created_at < NOW() - INTERVAL '1 hour' * :hours
                """);
        q.bind("refunding_code", RefundStatus.REFUNDING.getCode());
        q.bind("hours", refundingHours);
        return q.fetchOneInto(StuckOrderCount.class);
    }

    @Override
    public List<FailureCountByType> recentFailureCounts(long windowHours) {
        // 近期查询/回调失败：success=FALSE AND log_type IN (查询/回调三类)
        ResultQuery<?> q = dsl.parser().parseResultQuery("""
                SELECT log_type AS "logType",
                       COUNT(*) AS "failureCount"
                  FROM pay_payment_logs
                 WHERE deleted = FALSE
                   AND success = FALSE
                   AND log_type IN (:q1, :q2, :q3)
                   AND created_at > NOW() - INTERVAL '1 hour' * :hours
                 GROUP BY log_type
                """);
        q.bind("q1", "PAYMENT_QUERY");
        q.bind("q2", "REFUND_QUERY");
        q.bind("q3", "PAYMENT_CALLBACK");
        q.bind("hours", windowHours);
        return q.fetchInto(FailureCountByType.class);
    }

    @Override
    public List<OperatorOperationCount> operatorOperationCounts(LocalDateTime from, LocalDateTime to) {
        // 全操作（AUDIT_APPROVE/AUDIT_REJECT/NOTIFY_RESEND）按操作员×操作类型聚合
        ResultQuery<?> q = dsl.parser().parseResultQuery("""
                SELECT operator_id AS "operatorId",
                       operator_name AS "operatorName",
                       operation AS "operation",
                       COUNT(*) AS "opCount"
                  FROM pay_operation_logs
                 WHERE deleted = FALSE
                """ + windowClause(from, to, "created_at", true) + """
                 GROUP BY operator_id, operator_name, operation
                 ORDER BY operator_id
                """);
        bindWindow(q, from, to);
        return q.fetchInto(OperatorOperationCount.class);
    }

    @Override
    public List<NotifyResendBySystem> notifyResendBySystem(LocalDateTime from, LocalDateTime to) {
        // 仅 NOTIFY_RESEND，按来源业务系统（operator_system）归组
        ResultQuery<?> q = dsl.parser().parseResultQuery("""
                SELECT operator_system AS "operatorSystem",
                       COUNT(*) AS "resendCount"
                  FROM pay_operation_logs
                 WHERE deleted = FALSE
                   AND operation = :notify_code
                """ + windowClause(from, to, "created_at", true) + """
                 GROUP BY operator_system
                 ORDER BY operator_system
                """);
        q.bind("notify_code", OperationType.NOTIFY_RESEND.getCode());
        bindWindow(q, from, to);
        return q.fetchInto(NotifyResendBySystem.class);
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
