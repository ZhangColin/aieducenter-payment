package com.aieducenter.payment.hsb.domain.port.response;

import com.aieducenter.payment.hsb.domain.enums.HsbPaymentStatus;

public record QueryHsbPaymentResponse(
    boolean success,
    String returnCode,
    String returnMsg,
    HsbPaymentStatus paymentStatus,
    String pyTrnNo,
    Long actualAmount,
    long executionTime,
    String responseParams
) {}
