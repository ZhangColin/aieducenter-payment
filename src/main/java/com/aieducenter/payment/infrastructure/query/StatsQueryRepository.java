package com.aieducenter.payment.infrastructure.query;

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

    // ==================== 统计二档（issue #18） ====================

    /** 按业务系统聚合的支付（数据源 PaymentOrder）。{@code from}/{@code to} 为 null 时全局。 */
    List<BusinessSystemPaymentRollup> businessSystemPaymentRollup(LocalDateTime from, LocalDateTime to);

    /** 按业务系统聚合的退款（数据源 RefundOrder）。{@code from}/{@code to} 为 null 时全局。 */
    List<BusinessSystemRefundRollup> businessSystemRefundRollup(LocalDateTime from, LocalDateTime to);

    /** 按支付方式（pay_mode）聚合的支付（数据源 PaymentOrder，仅 pay_mode 非空行）。 */
    List<ChannelPaymentRollup> payModeRollup(LocalDateTime from, LocalDateTime to);

    /** 按接入类型（access_type）聚合的支付（数据源 PaymentOrder，仅 access_type 非空行）。 */
    List<ChannelPaymentRollup> accessTypeRollup(LocalDateTime from, LocalDateTime to);

    /**
     * 长时滞留 PENDING 支付单（数据源 PaymentOrder，{@code status=PENDING AND created_at < NOW() - hours}）。
     * 「当前时间」由 SQL 的 {@code NOW()} 计算；{@code pendingHours} 由 AppService 注入。
     * 单行聚合，无数据时返回 {@code {0,0}}（不返回 null）。
     */
    StuckOrderCount longPendingPayments(long pendingHours);

    /**
     * 长时滞留 REFUNDING 退款单（数据源 RefundOrder，{@code status=REFUNDING AND created_at < NOW() - hours}）。
     */
    StuckOrderCount longRefundingRefunds(long refundingHours);

    /**
     * 近期查询/回调失败（数据源 PaymentLog，{@code success=FALSE AND log_type IN
     * (PAYMENT_QUERY, REFUND_QUERY, PAYMENT_CALLBACK) AND created_at > NOW() - windowHours}），
     * 按 {@code log_type} 分组。「当前时间」由 SQL 的 {@code NOW()} 计算。
     */
    List<FailureCountByType> recentFailureCounts(long windowHours);

    /**
     * 按操作员×操作类型聚合（数据源 OperationLog，全操作：AUDIT_APPROVE/AUDIT_REJECT/NOTIFY_RESEND）。
     * AppService 透视成每操作员的各操作类型笔数。
     */
    List<OperatorOperationCount> operatorOperationCounts(LocalDateTime from, LocalDateTime to);

    /**
     * 通知重发按来源业务系统聚合（数据源 OperationLog，仅 {@code operation=NOTIFY_RESEND}，
     * 按 {@code operator_system} 分组）。AppService 求和得总次数。
     */
    List<NotifyResendBySystem> notifyResendBySystem(LocalDateTime from, LocalDateTime to);
}
