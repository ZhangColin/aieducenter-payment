package com.aieducenter.payment.infrastructure.query;

import com.aieducenter.payment.domain.enums.StatsGranularity;
import com.aieducenter.payment.infrastructure.query.projection.AuditOperationCount;
import com.aieducenter.payment.infrastructure.query.projection.AuditorAuditCount;
import com.aieducenter.payment.infrastructure.query.projection.GatewayInterfaceRollup;
import com.aieducenter.payment.infrastructure.query.projection.GatewayReturnCodeCount;
import com.aieducenter.payment.infrastructure.query.projection.PaymentStatusCount;
import com.aieducenter.payment.infrastructure.query.projection.PaymentTrendBucket;
import com.aieducenter.payment.infrastructure.query.projection.RefundStatusCount;
import com.aieducenter.payment.infrastructure.query.projection.RefundTrendBucket;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 统计读侧端口（issue #17）。
 *
 * <p>CQRS 读侧：jOOQ 实现见 {@code JooqStatsQueryRepository}；本接口是
 * {@code StatsAppService} 的 Mockito 缝——测试 mock 本接口返回投影 record，
 * 断言 AppService 装配逻辑。编码规范 §1.2 允许「应用层依赖基础设施层接口」。</p>
 *
 * <p>所有 {@code from}/{@code to} 形参：为 null 时表示不限制时间（全局），
 * 用于 status-distribution 快照；窗口查询（overview/gateway/audit）传具体区间。</p>
 */
public interface StatsQueryRepository {

    /** 支付单按状态聚合。{@code from}/{@code to} 为 null 时全局。 */
    List<PaymentStatusCount> countPaymentByStatus(LocalDateTime from, LocalDateTime to);

    /** 退款单按状态聚合。{@code from}/{@code to} 为 null 时全局。 */
    List<RefundStatusCount> countRefundByStatus(LocalDateTime from, LocalDateTime to);

    /** 支付趋势分桶（仅有数据的桶，缺失桶由 AppService 补零）。 */
    List<PaymentTrendBucket> paymentTrend(LocalDateTime from, LocalDateTime to, StatsGranularity granularity);

    /** 退款趋势分桶。 */
    List<RefundTrendBucket> refundTrend(LocalDateTime from, LocalDateTime to, StatsGranularity granularity);

    /** 网关各接口汇总（数据源 PaymentLog）。 */
    List<GatewayInterfaceRollup> gatewayInterfaceRollup(LocalDateTime from, LocalDateTime to);

    /** 网关 returnCode 分布（数据源 PaymentLog），AppService 按 bankInterface 归组。 */
    List<GatewayReturnCodeCount> gatewayReturnCodeCounts(LocalDateTime from, LocalDateTime to);

    /** 审核操作总览（数据源 OperationLog，仅 AUDIT_APPROVE/AUDIT_REJECT）。 */
    List<AuditOperationCount> auditOperationCounts(LocalDateTime from, LocalDateTime to);

    /** 按审核人聚合（数据源 OperationLog）。 */
    List<AuditorAuditCount> auditorAuditCounts(LocalDateTime from, LocalDateTime to);

    /**
     * 平均审核时长（分钟，数据源 RefundOrder：{@code audited_at - created_at}，
     * {@code audit_type=MANUAL}）。无数据返回 {@code null}，AppService 视作 0。
     */
    BigDecimal avgAuditDuration(LocalDateTime from, LocalDateTime to);
}
