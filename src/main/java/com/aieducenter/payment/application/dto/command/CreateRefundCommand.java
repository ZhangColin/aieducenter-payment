package com.aieducenter.payment.application.dto.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

/**
 * 创建退款命令
 */
public record CreateRefundCommand(
    @NotBlank(message = "业务订单号不能为空")
    @Size(max = 64, message = "业务订单号长度不能超过64")
    String businessOrderNo,

    @NotBlank(message = "支付订单号不能为空")
    @Size(max = 64, message = "支付订单号长度不能超过64")
    String paymentOrderNo,

    @NotNull(message = "退款金额不能为空")
    @Positive(message = "退款金额必须大于0")
    Long refundAmount,

    @Size(max = 512, message = "退款原因长度不能超过512")
    String reason,

    @Size(max = 1000, message = "附加数据长度不能超过1000")
    String attach,

    @Size(max = 512, message = "退款通知地址长度不能超过512")
    String notifyUrl,

    Boolean needAudit
) {
    public boolean isNeedAudit() {
        return needAudit == null || needAudit;
    }
}
