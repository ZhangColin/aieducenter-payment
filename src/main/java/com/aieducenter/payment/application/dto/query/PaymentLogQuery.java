package com.aieducenter.payment.application.dto.query;

import com.cartisan.data.jpa.specification.Condition;
import com.cartisan.data.jpa.specification.ConditionType;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 支付网关日志多条件查询条件。
 *
 * <p>字段配合 {@link Condition} 注解，由 {@code ConditionSpecifications.fromAnnotation(query)}
 * 生成 JPA Specification。null 与空字符串自动跳过；集合仅在非 null 时参与 IN。
 * 沿用 T2（支付订单列表）确立的本仓列表查询筛选约定。</p>
 *
 * <p>筛选维度（银行无关，CONTEXT.md 不变式 5——均为网关交互日志的通用列，无 ICBC 硬编码）：
 * 支付订单号 / 退款订单号 / 日志类型（logType，多值 IN）/ 银行接口（bankInterface）/
 * 是否成功（success）/ 业务返回码（returnCode）/ 创建时间区间。</p>
 */
public record PaymentLogQuery(
    @Condition(type = ConditionType.EQUAL) String paymentOrderNo,
    @Condition(type = ConditionType.EQUAL) String refundOrderNo,
    @Condition(propName = "logType", type = ConditionType.IN) List<String> logTypes,
    @Condition(type = ConditionType.EQUAL) String bankInterface,
    @Condition(type = ConditionType.EQUAL) Boolean success,
    @Condition(type = ConditionType.EQUAL) String returnCode,
    @Condition(propName = "createdAt", type = ConditionType.GREATER_EQUAL) LocalDateTime createdAtFrom,
    @Condition(propName = "createdAt", type = ConditionType.LESS_EQUAL) LocalDateTime createdAtTo
) {
}
