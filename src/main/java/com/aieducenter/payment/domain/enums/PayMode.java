package com.aieducenter.payment.domain.enums;

import com.cartisan.core.domain.BaseEnum;
import com.cartisan.data.jpa.converter.BaseEnumConverter;
import jakarta.persistence.Converter;

public enum PayMode implements BaseEnum<PayMode> {
    WECHAT(9, "微信"),
    ALIPAY(10, "支付宝"),
    UNIONPAY(13, "云闪付");

    private final Integer code;
    private final String name;

    PayMode(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getName() {
        return name;
    }

    @Converter(autoApply = true)
    public static class JpaConverter extends BaseEnumConverter<PayMode> {
        public JpaConverter() {
            super(PayMode.class);
        }
    }
}
