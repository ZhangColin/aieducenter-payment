package com.aieducenter.payment.infrastructure.query.projection;

/**
 * 退款单按状态聚合的读模型（issue #17）。
 *
 * <p>jOOQ {@code fetchInto} 目标。{@code totalAmount} = {@code SUM(refund_amount)}，
 * SUCCESS 行的 {@code totalAmount} 即「成功退款金额」。</p>
 *
 * @param status      RefundStatus 的 Integer code
 * @param orderCount  该状态下退款单数
 * @param totalAmount 该状态退款金额合计（SUM(refund_amount)）
 */
public record RefundStatusCount(Integer status, Long orderCount, Long totalAmount) {
}
