package com.aieducenter.payment.application.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 支付交易概览响应（GET /api/v1/stats/payments/overview，issue #17）。
 *
 * <p>金额一律 {@code long}（分）；{@code successRate}/{@code net} 口径见设计规约。
 * {@code trend} 为按 granularity 补零的连续序列。</p>
 *
 * @param payment   支付摘要
 * @param refund    退款摘要
 * @param netAmount 净额 = 支付成功金额 − 退款成功金额
 * @param trend     趋势分桶序列（按日/小时补零）
 */
public record PaymentOverviewResponse(
        Summary payment,
        Summary refund,
        long netAmount,
        List<TrendBucket> trend
) {
    /**
     * @param count         总笔数（窗口内）
     * @param amount        总金额（SUM(amount) / SUM(refund_amount)）
     * @param successCount  成功笔数（PAID / SUCCESS）
     * @param successAmount 成功金额
     * @param successRate   成功率 [0,1] 4 位小数，分母为 0 时 0
     */
    public record Summary(long count, long amount, long successCount, long successAmount, BigDecimal successRate) {
    }

    /**
     * @param bucket         桶起点
     * @param paymentCount   支付笔数
     * @param paymentAmount  支付金额
     * @param paidCount      支付成功笔数
     * @param paidAmount     支付成功金额
     * @param refundCount    退款笔数
     * @param refundAmount   退款金额
     * @param refundedCount  退款成功笔数
     * @param refundedAmount 退款成功金额
     */
    public record TrendBucket(LocalDateTime bucket,
                              long paymentCount, long paymentAmount, long paidCount, long paidAmount,
                              long refundCount, long refundAmount, long refundedCount, long refundedAmount) {
    }
}
