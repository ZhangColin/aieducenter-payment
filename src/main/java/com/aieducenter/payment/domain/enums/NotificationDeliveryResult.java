package com.aieducenter.payment.domain.enums;

/**
 * 业务系统通知的投递结果。
 *
 * <p>反映一次 {@code BusinessSystemNotifier.notify} 调用对业务系统的真实送达情况，
 * 供调用方（如通知重发）按需留痕，区分“送达成功 / 投递失败 / 未投递”三态。</p>
 *
 * <p>纯内部值枚举：不落库、不与前端交互，故不实现 {@code BaseEnum}（无 Integer code 语义）。</p>
 *
 * <ul>
 *   <li>{@link #DELIVERED} —— HTTP 200，业务系统已收到。</li>
 *   <li>{@link #FAILED} —— HTTP 非 200，或抛异常（连接/读超时、网络中断、URL 不可达等）。</li>
 *   <li>{@link #SKIPPED} —— {@code notifyUrl} 为空，未构造请求即返回。</li>
 * </ul>
 */
public enum NotificationDeliveryResult {
    DELIVERED,
    FAILED,
    SKIPPED
}
