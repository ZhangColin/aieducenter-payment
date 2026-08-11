package com.aieducenter.payment.application.dto.response;

import java.time.LocalDateTime;

/**
 * 操作日志响应。
 *
 * <p>枚举以 Integer code + String name 暴露（沿用 RefundOrderResponse 约定）。</p>
 */
public record OperationLogResponse(
    Long id,
    Integer targetType,
    String targetTypeName,
    String targetNo,
    Integer operation,
    String operationName,
    Long operatorId,
    String operatorName,
    String operatorSystem,
    String result,
    String remark,
    LocalDateTime createdAt
) {
}
