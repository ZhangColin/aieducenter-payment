package com.aieducenter.payment.infrastructure.icbc;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 工行支付网关配置
 *
 * <p>从 application.yml 中加载 gateway.icbc.* 前缀的配置</p>
 */
@Data
@Component
@ConfigurationProperties(prefix = "gateway.icbc")
public class IcbcConfig {

    /**
     * 环境配置
     * <p>可选值: local, test, prod</p>
     */
    private String env;

    /**
     * 应用ID
     */
    private String appId;

    /**
     * 商户号
     */
    private String merId;

    /**
     * 协议号
     * <p>协议号 = 商户号 + 0201</p>
     */
    private String merPrtclNo;

    /**
     * 商户私钥
     * <p>用于签名</p>
     */
    private String privateKey;

    /**
     * 工行网关公钥
     * <p>用于验签</p>
     */
    private String publicKey;

    /**
     * 支付接口地址
     */
    private String paymentUrl;

    /**
     * 支付查询接口地址
     */
    private String paymentQueryUrl;

    /**
     * 退款接口地址
     */
    private String refundUrl;

    /**
     * 退款查询接口地址
     */
    private String refundQueryUrl;

    /**
     * 支付回调基础地址
     * <p>用于拼接工行异步通知地址: {notifyBaseUrl}/api/v1/payment/callbacks/icbc</p>
     */
    private String notifyBaseUrl;
}
