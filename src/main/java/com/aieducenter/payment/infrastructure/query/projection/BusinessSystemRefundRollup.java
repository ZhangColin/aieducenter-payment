package com.aieducenter.payment.infrastructure.query.projection;

/**
 * 按业务系统聚合的退款读模型（issue #18，数据源 RefundOrder）。
 *
 * <p>jOOQ {@code fetchInto} 目标。按 {@code businessSystemName} 分组；
 * {@code refundedCount/Amount} 标记 {@code status=SUCCESS} 的成功退款。</p>
 *
 * @param businessSystemName 业务系统名（business_system_name）
 * @param orderCount         该业务系统退款单数
 * @param totalAmount        该业务系统退款单金额合计（SUM(refund_amount)）
 * @param refundedCount      退款成功（SUCCESS）笔数
 * @param refundedAmount     退款成功金额合计
 */
public record BusinessSystemRefundRollup(
        String businessSystemName, Long orderCount, Long totalAmount, Long refundedCount, Long refundedAmount) {
}
