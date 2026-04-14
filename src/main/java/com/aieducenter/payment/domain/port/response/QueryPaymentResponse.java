package com.aieducenter.payment.domain.port.response;

import com.aieducenter.payment.domain.enums.PaymentChannel;
import com.aieducenter.payment.domain.enums.PaymentStatus;

/**
 * 查询支付响应
 *
 * <p>支付网关返回的支付状态查询结果</p>
 */
public record QueryPaymentResponse(
    /**
     * 是否成功
     */
    boolean success,

    /**
     * 返回码
     */
    String returnCode,

    /**
     * 返回消息
     */
    String returnMsg,

    /**
     * 支付状态
     */
    PaymentStatus paymentStatus,

    /**
     * 订单总金额（分）
     */
    Long amount,

    /**
     * 已支付金额（分）
     */
    Long paidAmount,

    /**
     * 支付时间
     */
    String payTime,

    /**
     * 银行订单号
     */
    String bankOrderNo,

    /**
     * 第三方订单号
     * <p>如微信、支付宝的订单号</p>
     */
    String thirdPartyOrderNo,

    /**
     * 支付渠道
     */
    PaymentChannel paymentChannel,

    /**
     * 执行耗时（毫秒）
     */
    Long executionTime
) {
}
