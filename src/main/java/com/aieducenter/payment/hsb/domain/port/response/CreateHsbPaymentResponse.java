package com.aieducenter.payment.hsb.domain.port.response;

import java.util.Map;

public record CreateHsbPaymentResponse(
    boolean success,
    String returnCode,
    String returnMsg,
    String cshdkUrl,
    String payUrl,
    String payQrCode,
    String primOrderNo,
    long executionTime,
    String requestParams,
    String responseParams,
    Map<String, String> subOrderIds
) {}
