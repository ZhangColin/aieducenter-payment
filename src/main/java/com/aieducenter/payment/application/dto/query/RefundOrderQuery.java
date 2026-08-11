package com.aieducenter.payment.application.dto.query;

import com.aieducenter.payment.domain.enums.AuditType;
import com.aieducenter.payment.domain.enums.RefundStatus;
import com.cartisan.data.jpa.specification.Condition;
import com.cartisan.data.jpa.specification.ConditionType;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 退款订单多条件查询条件。
 *
 * <p>字段配合 {@link Condition} 注解，由 {@code ConditionSpecifications.fromAnnotation(query)}
 * 生成 JPA Specification。null 与空字符串自动跳过；集合仅在非 null 时参与 IN。
 * 沿用 T2（支付订单列表）确立的本仓列表查询筛选约定。</p>
 *
 * <p>筛选维度：退款订单号 / 支付订单号 / 业务订单号 / 业务系统 / 多状态 /
 * 审核类型（auditType，T3 落地）/ 审核者 / 退款金额区间（refundAmountMin..refundAmountMax）/
 * 创建时间区间。</p>
 */
public record RefundOrderQuery(
    @Condition(type = ConditionType.EQUAL) String refundOrderNo,
    @Condition(type = ConditionType.EQUAL) String paymentOrderNo,
    @Condition(type = ConditionType.EQUAL) String businessOrderNo,
    @Condition(type = ConditionType.EQUAL) String businessSystemName,
    @Condition(propName = "status", type = ConditionType.IN) List<RefundStatus> statuses,
    @Condition(type = ConditionType.EQUAL) AuditType auditType,
    @Condition(type = ConditionType.EQUAL) Long auditorId,
    @Condition(propName = "refundAmount", type = ConditionType.GREATER_EQUAL) Long refundAmountMin,
    @Condition(propName = "refundAmount", type = ConditionType.LESS_EQUAL) Long refundAmountMax,
    @Condition(propName = "createdAt", type = ConditionType.GREATER_EQUAL) LocalDateTime createdAtFrom,
    @Condition(propName = "createdAt", type = ConditionType.LESS_EQUAL) LocalDateTime createdAtTo
) {
}
