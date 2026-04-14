package com.aieducenter.payment.infrastructure.icbc;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * 工行客户端工厂
 *
 * <p>负责创建 DefaultIcbcClient 实例和生成消息ID</p>
 */
@Component
@RequiredArgsConstructor
public class IcbcClientFactory {

    private final IcbcConfig icbcConfig;

    /**
     * 创建工行客户端
     *
     * @return 配置好的 DefaultIcbcClient 实例
     */
    public DefaultIcbcClient createClient() {
        return new DefaultIcbcClient(
            icbcConfig.getAppId(),
            IcbcConstants.SIGN_TYPE_RSA2,
            icbcConfig.getPrivateKey(),
            icbcConfig.getPublicKey()
        );
    }

    /**
     * 生成消息ID
     *
     * @return UUID 格式的消息ID
     */
    public String generateMsgId() {
        return UUID.randomUUID().toString();
    }
}
