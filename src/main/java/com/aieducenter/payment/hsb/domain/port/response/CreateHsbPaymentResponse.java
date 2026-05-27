package com.aieducenter.payment.hsb.domain.port.response;

public record CreateHsbPaymentResponse(
    boolean success,
    String returnCode,
    String returnMsg,
    String payUrl,
    String payQrCode,
    String primOrderNo,
    long executionTime,
    String requestParams,
    String responseParams
) {}
