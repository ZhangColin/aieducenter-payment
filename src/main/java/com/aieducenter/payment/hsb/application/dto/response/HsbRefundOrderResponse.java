package com.aieducenter.payment.hsb.application.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record HsbRefundOrderResponse(
    String refundOrderNo,
    String paymentOrderNo,
    String businessMainOrderNo,
    String businessSystemName,
    String businessName,
    String refundType,
    String status,
    Long refundAmount,
    String reason,
    String superRefundNo,
    LocalDateTime refundedAt,
    LocalDateTime failedAt,
    String notifyUrl,
    String attach,
    List<HsbRefundSubOrderResponse> subOrders
) {
    @Builder
    public record HsbRefundSubOrderResponse(
        String businessSubOrderNo,
        String subOrderId,
        Long refundAmount
    ) {}
}
