package com.aieducenter.payment.application.dto.command;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * 通知重发命令。
 *
 * <p>携带操作者身份（来自 admin-bff 请求体），系统身份取自签名上下文（{@code RequestContext.getCallerAppName()}）。
 * 与 {@code AuditRefundCommand} 同构。</p>
 */
public record ResendNotificationCommand(
    @NotNull(message = "操作人ID不能为空")
    Long operatorId,

    @NotNull(message = "操作人姓名不能为空")
    @Size(max = 64, message = "操作人姓名长度不能超过64")
    String operatorName,

    @Size(max = 512, message = "备注长度不能超过512")
    String remark
) {
}
