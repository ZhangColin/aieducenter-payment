package com.aieducenter.payment.application.dto.response;

import java.math.BigDecimal;
import java.util.List;

/**
 * 按渠道维度统计响应（GET /api/v1/stats/by-channel，issue #18）。
 *
 * <p>{@code byPayMode} / {@code byAccessType} 各自独立聚合，按枚举顺序全列（缺失补零），
 * 仅统计 {@code pay_mode} / {@code access_type} 非空的支付单（预支付前的单不计入渠道分布）。</p>
 *
 * @param byPayMode    按支付方式（PayMode）聚合
 * @param byAccessType 按接入类型（AccessType）聚合
 */
public record ByChannelResponse(List<ChannelBreakdown> byPayMode, List<ChannelBreakdown> byAccessType) {

    /**
     * @param channelCode   渠道枚举 code（PayMode / AccessType 的 code）
     * @param channelName   渠道显示名（枚举 name）
     * @param count         总笔数
     * @param amount        总金额
     * @param successCount  成功笔数（PAID）
     * @param successAmount 成功金额
     * @param successRate   成功率 [0,1] 4 位小数，分母为 0 时 0
     */
    public record ChannelBreakdown(
            Integer channelCode, String channelName,
            long count, long amount, long successCount, long successAmount, BigDecimal successRate) {
    }
}
