package com.aieducenter.payment.application.dto.callback;

import com.alibaba.fastjson2.annotation.JSONField;

/**
 * 退款结果通知请求
 */
public record RefundNotifyRequest(
    @JSONField(name = "refundOrderNo")
    String refundOrderNo,

    @JSONField(name = "businessOrderNo")
    String businessOrderNo,

    @JSONField(name = "paymentOrderNo")
    String paymentOrderNo,

    @JSONField(name = "status")
    Integer status,

    @JSONField(name = "statusName")
    String statusName,

    @JSONField(name = "refundAmount")
    Long refundAmount,

    @JSONField(name = "bankRefundNo")
    String bankRefundNo,

    @JSONField(name = "refundTime")
    String refundTime,

    @JSONField(name = "attach")
    String attach
) {}
