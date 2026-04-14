package com.aieducenter.payment.domain.port.response;

/**
 * 创建支付响应
 *
 * <p>支付网关返回的创建支付结果</p>
 */
public record CreatePaymentResponse(
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
     * 二维码URL
     * <p>用户扫码支付的二维码地址</p>
     */
    String qrCodeUrl,

    /**
     * 银行订单号
     */
    String bankOrderNo,

    /**
     * 执行耗时（毫秒）
     */
    Long executionTime,

    /**
     * 请求参数（JSON格式，用于日志记录）
     */
    String requestParams,

    /**
     * 响应体（JSON格式，用于日志记录）
     */
    String responseBody
) {
}
