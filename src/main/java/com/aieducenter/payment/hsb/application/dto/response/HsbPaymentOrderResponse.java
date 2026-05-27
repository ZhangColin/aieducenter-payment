package com.aieducenter.payment.hsb.application.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record HsbPaymentOrderResponse(
    String paymentOrderNo,
    String businessMainOrderNo,
    String businessSystemName,
    String businessName,
    String status,
    String mktId,
    String paymentMethod,
    String orderType,
    String currency,
    Long totalAmount,
    Long txnTotalAmount,
    String feeBearerId,
    String payUrl,
    String payQrCode,
    String primOrderNo,
    String pyTrnNo,
    Long actualAmount,
    LocalDateTime paidAt,
    LocalDateTime failedAt,
    LocalDateTime expiredAt,
    String notifyUrl,
    String attach,
    List<HsbSubOrderResponse> subOrders
) {
    @Builder
    public record HsbSubOrderResponse(
        String businessSubOrderNo,
        String mktMrchId,
        Long orderAmount,
        Long txnAmount,
        String subOrderId,
        Boolean confirmed,
        LocalDateTime confirmedAt
    ) {}
}
