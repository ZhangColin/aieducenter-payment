package com.aieducenter.payment.hsb.domain.port.response;

import com.aieducenter.payment.hsb.domain.enums.HsbRefundStatus;

public record QueryHsbRefundResponse(
    boolean success,
    String returnCode,
    String returnMsg,
    HsbRefundStatus refundStatus,
    String superRefundNo,
    long executionTime,
    String responseParams
) {}
