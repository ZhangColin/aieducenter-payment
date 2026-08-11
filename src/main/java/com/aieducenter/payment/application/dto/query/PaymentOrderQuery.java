package com.aieducenter.payment.application.dto.query;

import com.aieducenter.payment.domain.enums.AccessType;
import com.aieducenter.payment.domain.enums.PayMode;
import com.aieducenter.payment.domain.enums.PaymentChannel;
import com.aieducenter.payment.domain.enums.PaymentStatus;
import com.cartisan.data.jpa.specification.Condition;
import com.cartisan.data.jpa.specification.ConditionType;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 支付订单多条件查询条件。
 *
 * <p>字段配合 {@link Condition} 注解，由 {@code ConditionSpecifications.fromAnnotation(query)}
 * 生成 JPA Specification。null 与空字符串自动跳过；集合仅在非 null 时参与 IN。
 * 确立本仓列表查询筛选约定，后续列表票（退款 / 网关日志）沿用。</p>
 *
 * <p>筛选维度：订单号 / 业务订单号 / 业务系统 / 多状态 / 支付方式 / 接入类型 / 通道 /
 * 金额区间（amountMin..amountMax）/ 创建时间区间 / 付款时间区间。</p>
 */
public record PaymentOrderQuery(
    @Condition(type = ConditionType.EQUAL) String paymentOrderNo,
    @Condition(type = ConditionType.EQUAL) String businessOrderNo,
    @Condition(type = ConditionType.EQUAL) String businessSystemName,
    @Condition(propName = "status", type = ConditionType.IN) List<PaymentStatus> statuses,
    @Condition(type = ConditionType.EQUAL) PayMode payMode,
    @Condition(type = ConditionType.EQUAL) AccessType accessType,
    @Condition(type = ConditionType.EQUAL) PaymentChannel paymentChannel,
    @Condition(propName = "amount", type = ConditionType.GREATER_EQUAL) Long amountMin,
    @Condition(propName = "amount", type = ConditionType.LESS_EQUAL) Long amountMax,
    @Condition(propName = "createdAt", type = ConditionType.GREATER_EQUAL) LocalDateTime createdAtFrom,
    @Condition(propName = "createdAt", type = ConditionType.LESS_EQUAL) LocalDateTime createdAtTo,
    @Condition(propName = "paidAt", type = ConditionType.GREATER_EQUAL) LocalDateTime paidAtFrom,
    @Condition(propName = "paidAt", type = ConditionType.LESS_EQUAL) LocalDateTime paidAtTo
) {
}
