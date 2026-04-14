package com.aieducenter.payment.application.dto.response;

import java.time.LocalDateTime;

/**
 * 支付订单响应
 *
 * <p>返回给业务系统的支付订单详情</p>
 */
public record PaymentOrderResponse(
    /**
     * 订单 ID
     */
    Long id,

    /**
     * 业务订单号
     */
    String businessOrderNo,

    /**
     * 支付订单号
     */
    String paymentOrderNo,

    /**
     * 业务系统名称
     */
    String businessSystemName,

    /**
     * 业务名称
     */
    String businessName,

    /**
     * 支付状态码
     */
    Integer status,

    /**
     * 支付状态名称
     */
    String statusName,

    /**
     * 支付金额（分）
     */
    Long amount,

    /**
     * 支付标题
     */
    String subject,

    /**
     * 支付描述
     */
    String body,

    /**
     * 支付渠道名称
     */
    String paymentChannelName,

    /**
     * 二维码 URL
     * <p>用户扫码支付的地址</p>
     */
    String qrCodeUrl,

    /**
     * 客户端 IP
     */
    String clientIp,

    /**
     * 创建时间
     */
    LocalDateTime createdAt,

    /**
     * 过期时间
     */
    LocalDateTime expiredAt,

    /**
     * 支付完成时间
     */
    LocalDateTime paidAt,

    /**
     * 银行订单号
     */
    String bankOrderNo,

    /**
     * 第三方订单号
     */
    String thirdPartyOrderNo
) {
}
