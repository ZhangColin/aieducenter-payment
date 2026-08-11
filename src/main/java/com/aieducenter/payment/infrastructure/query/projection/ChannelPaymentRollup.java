package com.aieducenter.payment.infrastructure.query.projection;

/**
 * 按渠道聚合的支付读模型（issue #18，数据源 PaymentOrder）。
 *
 * <p>jOOQ {@code fetchInto} 目标。pay_mode 与 access_type 两路聚合**共用此形状**——
 * 均按 Integer 枚举 code 分组（{@code BaseEnum} 经 {@code BaseEnumConverter} 落库为 code）。
 * AppService 把 code 映射回 {@code PayMode}/{@code AccessType} 枚举名并按枚举顺序补零。</p>
 *
 * @param channelCode  渠道枚举 code（pay_mode 或 access_type）
 * @param orderCount   该渠道支付单数
 * @param totalAmount  该渠道支付单金额合计（SUM(amount)）
 * @param paidCount    已支付（PAID）笔数
 * @param paidAmount   已支付金额合计（SUM(actual_amount)）
 */
public record ChannelPaymentRollup(
        Integer channelCode, Long orderCount, Long totalAmount, Long paidCount, Long paidAmount) {
}
