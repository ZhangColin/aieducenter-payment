package com.aieducenter.payment.application.dto.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

/**
 * 创建预支付命令
 *
 * <p>用于接收业务系统发送的聚合支付请求</p>
 */
public record CreatePrepayCommand(
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
     */
    @NotBlank(message = "支付标题不能为空")
    @Size(max = 255, message = "支付标题长度不能超过255")
    String subject,

    /**
     * 支付描述
     */
    @Size(max = 1000, message = "支付描述长度不能超过1000")
    String body,

    /**
     * 业务名称
     */
    @Size(max = 128, message = "业务名称长度不能超过128")
    String businessName,

    /**
     * 异步通知地址
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
    String attach,

    /**
     * 支付方式
     * <p>9=微信, 10=支付宝, 13=云闪付</p>
     */
    @NotNull(message = "支付方式不能为空")
    Integer payMode,

    /**
     * 接入方式
     * <p>5=APP, 7=公众号, 8=生活号, 9=小程序</p>
     */
    @NotNull(message = "接入方式不能为空")
    Integer accessType,

    /**
     * 微信用户标识
     * <p>微信支付时必填（payMode=9 且 accessType=7或9）</p>
     */
    @Size(max = 128, message = "openId长度不能超过128")
    String openId,

    /**
     * 支付宝用户标识
     * <p>支付宝生活号时必填（payMode=10 且 accessType=8）</p>
     */
    @Size(max = 128, message = "unionId长度不能超过128")
    String unionId
) {
}
