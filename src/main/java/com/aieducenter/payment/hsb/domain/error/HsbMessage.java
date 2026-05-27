package com.aieducenter.payment.hsb.domain.error;

import com.cartisan.core.exception.CodeMessage;

public enum HsbMessage implements CodeMessage {
    // ========== 业务规则错误 (400) ==========
    HSB_PAYMENT_ORDER_NOT_PENDING(400, "HSB_010", "惠市宝支付订单不是待支付状态"),
    HSB_REFUND_ORDER_NOT_PENDING(400, "HSB_011", "惠市宝退款订单不是待退款状态"),
    HSB_REFUND_AMOUNT_EXCEEDS(400, "HSB_012", "退款金额超过可退款金额"),
    HSB_SETTLEMENT_NOT_PAID(400, "HSB_013", "支付订单未支付，无法确认分账"),
    HSB_SUB_ORDER_NOT_FOUND(400, "HSB_014", "子订单不存在"),
    HSB_SUB_ORDER_ALREADY_CONFIRMED(400, "HSB_015", "子订单已确认分账"),

    // ========== 资源不存在 (404) ==========
    HSB_PAYMENT_ORDER_NOT_FOUND(404, "HSB_030", "惠市宝支付订单不存在"),
    HSB_REFUND_ORDER_NOT_FOUND(404, "HSB_031", "惠市宝退款订单不存在"),

    // ========== 签名错误 (400) ==========
    HSB_SIGN_VERIFY_FAILED(400, "HSB_040", "建行回调验签失败"),
    HSB_SIGN_FAILED(500, "HSB_041", "建行签名失败");

    private final int httpStatus;
    private final String code;
    private final String message;

    HsbMessage(int httpStatus, String code, String message) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }

    @Override
    public int httpStatus() { return httpStatus; }

    @Override
    public String code() { return code; }

    @Override
    public String message() { return message; }
}
