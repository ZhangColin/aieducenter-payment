package com.aieducenter.payment.domain.port.response;

/**
 * 创建退款响应
 *
 * <p>支付网关返回的创建退款结果</p>
 */
public record CreateRefundResponse(
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
     * 银行退款流水号
     */
    String bankRefundNo,

    /**
     * 执行耗时（毫秒）
     */
    Long executionTime
) {
}
