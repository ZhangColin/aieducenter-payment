package com.aieducenter.payment.infrastructure.query.projection;

/**
 * 按业务系统聚合的支付读模型（issue #18，数据源 PaymentOrder）。
 *
 * <p>jOOQ {@code fetchInto} 目标。按 {@code businessSystemName} 分组；
 * {@code paidCount/Amount} 经 {@code SUM(CASE WHEN status=PAID THEN …)} 聚合到每组。</p>
 *
 * @param businessSystemName 业务系统名（business_system_name）
 * @param orderCount         该业务系统支付单数
 * @param totalAmount        该业务系统支付单金额合计（SUM(amount)）
 * @param paidCount          已支付（PAID）笔数
 * @param paidAmount         已支付金额合计（SUM(actual_amount)）
 */
public record BusinessSystemPaymentRollup(
        String businessSystemName, Long orderCount, Long totalAmount, Long paidCount, Long paidAmount) {
}
