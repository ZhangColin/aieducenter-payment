package com.aieducenter.payment.application.dto.query;

import com.aieducenter.payment.domain.enums.OperationLogTargetType;
import com.aieducenter.payment.domain.enums.OperationType;
import com.cartisan.data.jpa.specification.Condition;
import com.cartisan.data.jpa.specification.ConditionType;

import java.time.LocalDateTime;

/**
 * 操作日志查询条件。
 *
 * <p>字段配合 {@link Condition} 注解，由 {@code ConditionSpecifications.fromAnnotation(query)}
 * 生成 JPA Specification。null 与空字符串自动跳过。</p>
 */
public record OperationLogQuery(
    @Condition(type = ConditionType.EQUAL) OperationLogTargetType targetType,
    @Condition(type = ConditionType.EQUAL) String targetNo,
    @Condition(type = ConditionType.EQUAL) OperationType operation,
    @Condition(type = ConditionType.EQUAL) Long operatorId,
    @Condition(type = ConditionType.EQUAL) String operatorSystem,
    @Condition(type = ConditionType.EQUAL) String result,
    @Condition(propName = "createdAt", type = ConditionType.GREATER_EQUAL) LocalDateTime createdAtStart,
    @Condition(propName = "createdAt", type = ConditionType.LESS_EQUAL) LocalDateTime createdAtEnd
) {
}
