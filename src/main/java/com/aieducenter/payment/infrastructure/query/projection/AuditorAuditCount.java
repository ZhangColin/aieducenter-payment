package com.aieducenter.payment.infrastructure.query.projection;

/**
 * 按审核人聚合的审核读模型（issue #17，数据源 OperationLog）。
 *
 * <p>jOOQ {@code fetchInto} 目标。按 {@code operatorId, operatorName, operation} 分组；
 * AppService 透视成每人的 approved/rejected 计数。</p>
 *
 * @param auditorId   操作者 id（OperationLog.operatorId，与 RefundOrder.auditorId 同源）
 * @param auditorName 操作者名（OperationLog.operatorName）
 * @param operation   OperationType 的 Integer code
 * @param opCount     该操作笔数
 */
public record AuditorAuditCount(Long auditorId, String auditorName, Integer operation, Long opCount) {
}
