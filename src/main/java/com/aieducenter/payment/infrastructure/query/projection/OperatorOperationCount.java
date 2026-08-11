package com.aieducenter.payment.infrastructure.query.projection;

/**
 * 按操作员×操作类型聚合的读模型（issue #18，数据源 OperationLog）。
 *
 * <p>jOOQ {@code fetchInto} 目标。按 {@code operatorId, operatorName, operation} 分组；
 * AppService 透视成「每个操作员的各操作类型笔数」。{@code operatorId/Name} 对系统发起的动作可空。</p>
 *
 * @param operatorId   操作者 id（OperationLog.operatorId，可空）
 * @param operatorName 操作者名（OperationLog.operatorName，可空）
 * @param operation    OperationType 的 Integer code
 * @param opCount      该操作笔数
 */
public record OperatorOperationCount(Long operatorId, String operatorName, Integer operation, Long opCount) {
}
