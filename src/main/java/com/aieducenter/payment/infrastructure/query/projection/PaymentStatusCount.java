package com.aieducenter.payment.infrastructure.query.projection;

/**
 * 支付单按状态聚合的读模型（issue #17）。
 *
 * <p>jOOQ {@code fetchInto} 目标：SQL 列别名须与本 record 组件名（驼峰）一致。
 * {@code paidAmount} = {@code SUM(actual_amount)}，PAID 行即「成功金额」。</p>
 *
 * @param status      PaymentStatus 的 Integer code
 * @param orderCount  该状态下支付单数
 * @param totalAmount 该状态支付单金额合计（SUM(amount)）
 * @param paidAmount  该状态实际支付金额合计（SUM(actual_amount)）
 */
public record PaymentStatusCount(Integer status, Long orderCount, Long totalAmount, Long paidAmount) {
}
