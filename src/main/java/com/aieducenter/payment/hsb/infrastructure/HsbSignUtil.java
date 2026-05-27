package com.aieducenter.payment.hsb.infrastructure;

import com.ccb.mktpay.sign.RSASignUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HsbSignUtil {

    private static final Logger log = LoggerFactory.getLogger(HsbSignUtil.class);

    public static String sign(String privateKey, String signStr) {
        try {
            return RSASignUtil.sign(privateKey, signStr);
        } catch (Exception e) {
            log.error("HSB sign failed: {}", e.getMessage(), e);
            throw new RuntimeException("建行签名失败: " + e.getMessage(), e);
        }
    }

    public static boolean verifySign(String platformPublicKey, String signStr, String signInf) {
        try {
            return RSASignUtil.verifySign(platformPublicKey, signStr, signInf);
        } catch (Exception e) {
            log.error("HSB verify sign failed: {}", e.getMessage(), e);
            return false;
        }
    }
}
