package com.aieducenter.payment.hsb.domain.port.response;

public record ConfirmSettlementResponse(
    boolean success,
    String returnCode,
    String returnMsg,
    long executionTime,
    String requestParams,
    String responseParams
) {}
