package com.aieducenter.payment.infrastructure.query.projection;

/**
 * 按日志类型聚合的失败计数读模型（issue #18，anomalies「近期查询/回调失败」）。
 *
 * <p>jOOQ {@code fetchInto} 目标。数据源 PaymentLog（{@code success=FALSE AND log_type IN
 * (PAYMENT_QUERY, REFUND_QUERY, PAYMENT_CALLBACK)}）；按 {@code log_type} 分组。
 * AppService 求和得 {@code totalCount} 并透传明细。</p>
 *
 * @param logType       日志类型（PAYMENT_QUERY / REFUND_QUERY / PAYMENT_CALLBACK）
 * @param failureCount  该类型失败次数
 */
public record FailureCountByType(String logType, Long failureCount) {
}
