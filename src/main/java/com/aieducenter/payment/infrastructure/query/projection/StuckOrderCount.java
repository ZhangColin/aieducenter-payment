package com.aieducenter.payment.infrastructure.query.projection;

/**
 * 长时滞留订单计数读模型（issue #18，anomalies）。
 *
 * <p>jOOQ {@code fetchOneInto} 目标。用于「长时 PENDING 支付单」与「长时 REFUNDING 退款单」
 * 两类异常——单行聚合（无 GROUP BY），SQL {@code COALESCE} 保证无数据时返回 {@code {0,0}}（不返回 null）。</p>
 *
 * @param orderCount  滞留笔数
 * @param totalAmount 滞留金额合计
 */
public record StuckOrderCount(Long orderCount, Long totalAmount) {
}
