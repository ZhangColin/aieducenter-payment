package com.aieducenter.payment.infrastructure.query.projection;

import java.time.LocalDateTime;

/**
 * 退款趋势分桶读模型（issue #17）。
 *
 * <p>jOOQ {@code fetchInto} 目标。{@code refundedCount}/{@code refundedAmount} 经
 * {@code SUM(CASE WHEN status=5 THEN … END)} 得到（SUCCESS 即退款成功）。</p>
 *
 * @param bucketStart      桶起点
 * @param orderCount       桶内退款单数
 * @param totalAmount      桶内退款金额合计（SUM(refund_amount)）
 * @param refundedCount    桶内退款成功单数（status=SUCCESS）
 * @param refundedAmount   桶内成功退款金额合计（SUM(refund_amount)，status=SUCCESS）
 */
public record RefundTrendBucket(
        LocalDateTime bucketStart, Long orderCount, Long totalAmount, Long refundedCount, Long refundedAmount) {
}
