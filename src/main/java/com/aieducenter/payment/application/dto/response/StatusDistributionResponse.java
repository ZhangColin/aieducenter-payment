package com.aieducenter.payment.application.dto.response;

import java.util.List;

/**
 * 订单状态在途分布响应（GET /api/v1/stats/orders/status-distribution，issue #17）。
 *
 * <p>全局快照（无时间窗）：所有状态枚举值都出现（补零），退款 PENDING 积压单列。
 * 枚举以 Integer code + String name 暴露（沿用 OperationLogResponse 约定）。</p>
 *
 * @param paymentStatuses 支付各状态分布（PaymentStatus 5 个全列）
 * @param refundStatuses  退款各状态分布（RefundStatus 6 个全列）
 * @param refundBacklog   退款待审核积压（PENDING）
 */
public record StatusDistributionResponse(
        List<PaymentStatusBucket> paymentStatuses,
        List<RefundStatusBucket> refundStatuses,
        Backlog refundBacklog
) {
    public record PaymentStatusBucket(Integer status, String statusName, long count, long amount) {
    }

    public record RefundStatusBucket(Integer status, String statusName, long count, long amount) {
    }

    public record Backlog(long pendingCount, long pendingAmount) {
    }
}
