package com.aieducenter.payment.application.dto.response;

import java.time.LocalDateTime;

/**
 * 支付网关日志响应（列表视图）。
 *
 * <p>网关交互日志用于集成调试、银行侧问题排查、网关健康度统计（CONTEXT.md · PaymentLog）。
 * 本列表 DTO 仅暴露诊断摘要字段；完整的请求/响应原文（requestUrl / requestParams /
 * responseParams）体量大，不进列表，留待未来详情接口。</p>
 *
 * <p>字段均为基础类型（PaymentLog 的 logType / bankInterface / returnCode 在领域里以 String 记，
 * 非 {@code BaseEnum}），故无需枚举 code/name 拆分，沿用 RefundOrderResponse 的「仅暴露 DTO」约定。</p>
 */
public record PaymentLogResponse(
    Long id,
    String paymentOrderNo,
    String refundOrderNo,
    String logType,
    String bankCode,
    String bankInterface,
    Integer httpStatus,
    String returnCode,
    String returnMsg,
    Long executionTime,
    Boolean success,
    String errorMessage,
    LocalDateTime createdAt
) {
}
