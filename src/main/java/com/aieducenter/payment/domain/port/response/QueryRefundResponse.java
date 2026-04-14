package com.aieducenter.payment.domain.port.response;

import com.aieducenter.payment.domain.enums.RefundStatus;

/**
 * 查询退款响应
 *
 * <p>支付网关返回的退款状态查询结果</p>
 */
public record QueryRefundResponse(
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
     * 退款状态
     */
    RefundStatus refundStatus,

    /**
     * 退款金额（分）
     */
    Long refundAmount,

    /**
     * 实际退款金额（分）
     */
    Long realRefundAmount,

    /**
     * 退款时间
     */
    String refundTime,

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
