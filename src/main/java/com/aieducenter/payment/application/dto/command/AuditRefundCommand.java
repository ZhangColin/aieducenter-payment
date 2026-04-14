package com.aieducenter.payment.application.dto.command;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * 审核退款命令
 */
public record AuditRefundCommand(
    @NotNull(message = "审核人ID不能为空")
    Long auditorId,

    @NotNull(message = "审核人姓名不能为空")
    @Size(max = 64, message = "审核人姓名长度不能超过64")
    String auditorName,

    @NotNull(message = "审核结果不能为空")
    Boolean agreed,

    @Size(max = 512, message = "审核备注长度不能超过512")
    String remark
) {
}
