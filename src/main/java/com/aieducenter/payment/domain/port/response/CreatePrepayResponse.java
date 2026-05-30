package com.aieducenter.payment.domain.port.response;

/**
 * 创建预支付响应
 *
 * <p>聚合支付网关返回的创建预支付结果</p>
 */
public record CreatePrepayResponse(
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
     * 工行订单号
     */
    String bankOrderNo,

    /**
     * 支付参数包JSON
     * <p>根据支付方式不同，包含 wx_data_package / zfb_data_package / union_data_package</p>
     */
    String dataPackage,

    /**
     * 交易类型
     * <p>JSAPI（公众号/小程序）、APP 等</p>
     */
    String tradeType,

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
