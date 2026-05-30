package com.aieducenter.payment.application.dto.response;

import java.time.LocalDateTime;

/**
 * 预支付订单响应
 *
 * <p>返回给业务系统的预支付订单详情，包含前端调起支付所需的参数包</p>
 */
public record PrepayOrderResponse(
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
     * 支付参数包JSON
     * <p>前端用于调起微信/支付宝/云闪付支付</p>
     */
    String prepayDataPackage,

    /**
     * 支付方式码
     */
    Integer payMode,

    /**
     * 支付方式名称
     */
    String payModeName,

    /**
     * 接入方式码
     */
    Integer accessType,

    /**
     * 接入方式名称
     */
    String accessTypeName,

    /**
     * 过期时间
     */
    LocalDateTime expiredAt,

    /**
     * 创建时间
     */
    LocalDateTime createdAt
) {
}
