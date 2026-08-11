package com.aieducenter.payment.application.dto.response;

import java.util.List;

/**
 * 异常监控响应（GET /api/v1/stats/anomalies，issue #18）。
 *
 * <p>三类异常：「长时」滞留单（阈值 {@code payment.stats.anomaly.long-*-hours} 可配）+
 * 「近期」查询/回调失败（窗口 {@code payment.stats.anomaly.failure-window-hours} 可配）。
 * 「当前时间」由 SQL 的 {@code NOW()} 计算，Java 侧不取时钟，保证缝测试确定性。</p>
 *
 * @param longPendingPayments 长时 PENDING 支付单（超 long-pending-payment-hours 仍未终态）
 * @param longRefundingRefunds 长时 REFUNDING 退款单（超 long-refunding-refund-hours 仍未终态）
 * @param recentFailures       近期查询/回调失败计数（窗口内 success=FALSE 的网关日志）
 */
public record AnomaliesResponse(
        StuckOrders longPendingPayments,
        StuckOrders longRefundingRefunds,
        RecentFailures recentFailures
) {

    /**
     * @param count  滞留笔数
     * @param amount 滞留金额合计
     */
    public record StuckOrders(long count, long amount) {
    }

    /**
     * @param totalCount 失败总次数（各类型求和）
     * @param byType     按日志类型明细
     */
    public record RecentFailures(long totalCount, List<FailureCount> byType) {
    }

    /**
     * @param logType      日志类型（PAYMENT_QUERY / REFUND_QUERY / PAYMENT_CALLBACK）
     * @param failureCount 该类型失败次数
     */
    public record FailureCount(String logType, long failureCount) {
    }
}
