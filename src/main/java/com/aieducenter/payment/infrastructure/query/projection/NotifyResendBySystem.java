package com.aieducenter.payment.infrastructure.query.projection;

/**
 * 按来源业务系统聚合的通知重发读模型（issue #18，数据源 OperationLog）。
 *
 * <p>jOOQ {@code fetchInto} 目标。仅 {@code operation=NOTIFY_RESEND} 的 OperationLog 行，
 * 按 {@code operator_system}（来源业务系统）分组。AppService 求和得 {@code totalCount}。</p>
 *
 * @param operatorSystem 来源业务系统（OperationLog.operatorSystem，可空）
 * @param resendCount    该来源系统的通知重发次数
 */
public record NotifyResendBySystem(String operatorSystem, Long resendCount) {
}
