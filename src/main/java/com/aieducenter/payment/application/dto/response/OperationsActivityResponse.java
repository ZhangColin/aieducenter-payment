package com.aieducenter.payment.application.dto.response;

import java.util.List;

/**
 * 操作活跃度响应（GET /api/v1/stats/operations/activity，issue #18）。
 *
 * <p>数据源 OperationLog（单一）：各操作员的操作类型/笔数分布 + 通知重发次数及来源业务系统。
 * {@code operatorId}/{@code businessSystem} 为 null 的系统动作单列一组（保留 null）。</p>
 *
 * @param byOperator  各操作员操作类型/笔数分布
 * @param notifyResend 通知重发汇总（总数 + 按来源业务系统）
 */
public record OperationsActivityResponse(List<OperatorActivity> byOperator, NotifyResendActivity notifyResend) {

    /**
     * @param operatorId   操作者 id（可空）
     * @param operatorName 操作者名（可空）
     * @param totalCount   该操作员全部操作笔数
     * @param operations   该操作员各操作类型明细
     */
    public record OperatorActivity(Long operatorId, String operatorName, long totalCount, List<OperationCount> operations) {
    }

    /**
     * @param operation     OperationType 的 Integer code
     * @param operationName 操作类型显示名（OperationType.getName()）
     * @param count         该操作类型笔数
     */
    public record OperationCount(Integer operation, String operationName, long count) {
    }

    /**
     * @param totalCount      通知重发总次数
     * @param byBusinessSystem 按来源业务系统（operator_system）归组
     */
    public record NotifyResendActivity(long totalCount, List<SystemResendCount> byBusinessSystem) {
    }

    /**
     * @param businessSystem 来源业务系统（可空）
     * @param count          该来源系统的通知重发次数
     */
    public record SystemResendCount(String businessSystem, long count) {
    }
}
