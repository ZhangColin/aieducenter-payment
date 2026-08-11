package com.aieducenter.payment.application.dto.response;

import java.math.BigDecimal;
import java.util.List;

/**
 * 按业务系统维度统计响应（GET /api/v1/stats/by-business-system，issue #18）。
 *
 * <p>payment / refund 双源按 {@code businessSystemName} 并集（保序：payment 顺序优先，
 * refund-only 的追加在后），缺失侧补零。{@code refundRate = refundedCount / paidCount}
 * （退款成功笔数 / 支付成功笔数，分母为 0 时 0.0000）。</p>
 *
 * @param businessSystems 各业务系统明细（按 payment rollup 顺序 + refund-only 追加）
 */
public record ByBusinessSystemResponse(List<BusinessSystemBreakdown> businessSystems) {

    /**
     * @param businessSystemName 业务系统名
     * @param payment            支付摘要
     * @param refund             退款摘要
     * @param refundRate         退款率 = 退款成功笔数 / 支付成功笔数，[0,1] 4 位小数
     */
    public record BusinessSystemBreakdown(
            String businessSystemName, Summary payment, Summary refund, BigDecimal refundRate) {
    }

    /**
     * @param count         总笔数
     * @param amount        总金额
     * @param successCount  成功笔数（PAID / SUCCESS）
     * @param successAmount 成功金额
     * @param successRate   成功率 [0,1] 4 位小数，分母为 0 时 0
     */
    public record Summary(long count, long amount, long successCount, long successAmount, BigDecimal successRate) {
    }
}
