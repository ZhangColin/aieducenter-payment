package com.aieducenter.payment.application.dto.response;

import java.time.LocalDateTime;

/**
 * 退款订单响应
 */
public record RefundOrderResponse(
    Long id,
    String businessOrderNo,
    String refundOrderNo,
    String paymentOrderNo,
    String businessSystemName,
    String businessName,
    Integer status,
    String statusName,
    Long refundAmount,
    Long refundableAmount,
    String reason,
    String auditorName,
    Boolean auditAgreed,
    String auditRemark,
    Integer auditType,
    String auditTypeName,
    LocalDateTime createdAt,
    LocalDateTime approvedAt,
    LocalDateTime refundedAt,
    LocalDateTime failedAt,
    String bankRefundNo,
    String notifyUrl
) {
}
