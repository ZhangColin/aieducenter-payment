package com.aieducenter.payment.domain.error;

import com.cartisan.core.exception.CodeMessage;

public enum PaymentMessage implements CodeMessage {
    // ========== 格式校验错误 (400) ==========
    PAYMENT_ORDER_NO_INVALID(400, "PAY_001", "支付订单号格式不正确"),
    REFUND_ORDER_NO_INVALID(400, "PAY_002", "退款订单号格式不正确"),
    AMOUNT_INVALID(400, "PAY_003", "金额格式不正确"),

    // ========== 业务规则错误 (400) ==========
    PAYMENT_ORDER_NOT_PENDING(400, "PAY_010", "支付订单不是待支付状态"),
    REFUND_ORDER_NOT_PENDING(400, "PAY_011", "退款订单不是待审核状态"),
    REFUND_ORDER_NOT_APPROVED(400, "PAY_0111", "退款订单未批准"),
    REFUND_ORDER_NOT_REFUNDING(400, "PAY_0112", "退款订单不是退款中状态"),
    REFUND_AMOUNT_EXCEEDS(400, "PAY_012", "退款金额超过可退款金额"),
    ORIGINAL_PAYMENT_NOT_SUCCESS(400, "PAY_013", "原支付订单未成功，无法退款"),

    // ========== 唯一性错误 (409) ==========
    PAYMENT_ORDER_NO_EXISTS(409, "PAY_020", "支付订单号已存在"),
    REFUND_ORDER_NO_EXISTS(409, "PAY_021", "退款订单号已存在"),

    // ========== 资源不存在 (404) ==========
    PAYMENT_ORDER_NOT_FOUND(404, "PAY_030", "支付订单不存在"),
    REFUND_ORDER_NOT_FOUND(404, "PAY_031", "退款订单不存在"),

    // ========== 业务限制 (403) ==========
    PAYMENT_ORDER_EXPIRED(403, "PAY_040", "支付订单已过期"),
    PAYMENT_ORDER_FAILED(403, "PAY_041", "支付订单已失败"),
    REFUND_ALREADY_REFUNDED(403, "PAY_042", "已退款，不能重复退款");

    private final int httpStatus;
    private final String code;
    private final String message;

    PaymentMessage(int httpStatus, String code, String message) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }

    @Override
    public int httpStatus() {
        return httpStatus;
    }

    @Override
    public String code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }
}
