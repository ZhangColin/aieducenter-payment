package com.aieducenter.payment.infrastructure.query.projection;

/**
 * 审核操作总览读模型（issue #17，数据源 OperationLog）。
 *
 * <p>jOOQ {@code fetchInto} 目标。仅统计 {@code AUDIT_APPROVE}/{@code AUDIT_REJECT}
 * 两类操作，按 {@code operation} 分组。</p>
 *
 * @param operation OperationType 的 Integer code（AUDIT_APPROVE / AUDIT_REJECT）
 * @param opCount   该操作笔数
 */
public record AuditOperationCount(Integer operation, Long opCount) {
}
