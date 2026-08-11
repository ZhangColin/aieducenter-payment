package com.aieducenter.payment.infrastructure.query.projection;

import java.time.LocalDateTime;

/**
 * 支付趋势分桶读模型（issue #17）。
 *
 * <p>jOOQ {@code fetchInto} 目标。{@code paidCount}/{@code paidAmount} 经
 * {@code SUM(CASE WHEN status=2 THEN … END)} 得到（PAID 即支付成功）。
 * 仅含有数据的桶；缺失桶由 AppService 补零。</p>
 *
 * @param bucketStart  桶起点（date_trunc('day'|'hour', created_at)）
 * @param orderCount   桶内支付单数
 * @param totalAmount  桶内支付金额合计（SUM(amount)）
 * @param paidCount    桶内支付成功单数（status=PAID）
 * @param paidAmount   桶内实际支付金额合计（SUM(actual_amount)，status=PAID）
 */
public record PaymentTrendBucket(
        LocalDateTime bucketStart, Long orderCount, Long totalAmount, Long paidCount, Long paidAmount) {
}
