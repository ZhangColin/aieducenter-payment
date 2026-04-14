package com.aieducenter.payment.application.dto.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

/**
 * 创建支付命令
 *
 * <p>用于接收业务系统发送的支付请求</p>
 */
public record CreatePaymentCommand(
    /**
     * 业务订单号
     */
    @NotBlank(message = "业务订单号不能为空")
    @Size(max = 64, message = "业务订单号长度不能超过64")
    String businessOrderNo,

    /**
     * 支付金额（分）
     */
    @NotNull(message = "金额不能为空")
    @Positive(message = "金额必须大于0")
    Long amount,

    /**
     * 支付标题
     * <p>简短的支付描述，展示给用户</p>
     */
    @NotBlank(message = "支付标题不能为空")
    @Size(max = 255, message = "支付标题长度不能超过255")
    String subject,

    /**
     * 支付描述
     * <p>详细的订单信息，可以是 JSON 格式的订单明细</p>
     */
    @Size(max = 1000, message = "支付描述长度不能超过1000")
    String body,

    /**
     * 业务名称
     * <p>业务系统声明的业务分类，如"课程购买"、"会员充值"</p>
     */
    @Size(max = 128, message = "业务名称长度不能超过128")
    String businessName,

    /**
     * 异步通知地址
     * <p>支付结果回调地址</p>
     */
    @NotBlank(message = "异步通知地址不能为空")
    @Size(max = 512, message = "异步通知地址长度不能超过512")
    String notifyUrl,

    /**
     * 过期时长（秒）
     * <p>可选，不传则使用服务端默认配置</p>
     */
    Long expiredSeconds,

    /**
     * 附加数据
     * <p>自定义数据，原样返回</p>
     */
    @Size(max = 1000, message = "附加数据长度不能超过1000")
    String attach
) {
}
