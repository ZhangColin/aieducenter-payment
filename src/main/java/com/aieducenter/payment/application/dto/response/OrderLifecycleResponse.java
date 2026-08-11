package com.aieducenter.payment.application.dto.response;

import java.time.LocalDateTime;

/**
 * 订单生命周期统一事件（读模型）。
 *
 * <p>把 {@code PaymentLog}（网关交互，来源 GATEWAY）与 {@code OperationLog}（行为者操作，来源 OPERATION）
 * 按 {@code createdAt} 合并后的统一事件序列项。供客诉排查与运营查看「这单经历了什么」
 * （CONTEXT.md · 订单生命周期；ADR-0002 · 不合表、合视图）。只读、不写库。</p>
 *
 * <p>字段为跨来源的语义抽象：来源标签 + 动作 + 结果 + 执行方 + 补充说明，两类来源各自填值。
 * 银行无关（CONTEXT.md 不变式 5）——网关事件只透出 bankCode/bankInterface 通用列。</p>
 *
 * @param id              源记录主键（PaymentLog.id / OperationLog.id；同时间排序的稳定键）
 * @param source          来源标签：{@code "GATEWAY"}（网关交互）/ {@code "OPERATION"}（行为者操作）
 * @param createdAt       事件时间（合并后的主排序键）
 * @param action          动作稳定 token：GATEWAY→{@code logType}（PAYMENT_REQUEST…）；OPERATION→{@code operation} 枚举名（AUDIT_APPROVE…）
 * @param actionName      动作显示名：GATEWAY→{@code logType} 原值；OPERATION→{@code operation.getName()}（审核通过…）
 * @param outcome         结果 token（SUCCESS/FAILED）：GATEWAY 由 success 派生；OPERATION 取 result
 * @param performer       执行方：GATEWAY→{@code bankInterface}；OPERATION→{@code operatorName}
 * @param performerSystem 执行方系统：GATEWAY→{@code bankCode}；OPERATION→{@code operatorSystem}
 * @param detail          补充说明：GATEWAY→success 时 returnMsg / 失败时 errorMessage；OPERATION→remark
 */
public record OrderLifecycleResponse(
    Long id,
    String source,
    LocalDateTime createdAt,
    String action,
    String actionName,
    String outcome,
    String performer,
    String performerSystem,
    String detail
) {
}
