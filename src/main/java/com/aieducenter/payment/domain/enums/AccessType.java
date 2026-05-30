package com.aieducenter.payment.domain.enums;

import com.cartisan.core.domain.BaseEnum;
import com.cartisan.data.jpa.converter.BaseEnumConverter;
import jakarta.persistence.Converter;

public enum AccessType implements BaseEnum<AccessType> {
    H5(4, "H5"),
    APP(5, "APP"),
    WECHAT_OA(7, "微信公众号"),
    ALIPAY_LIFE(8, "支付宝生活号"),
    MINI_PROGRAM(9, "小程序");

    private final Integer code;
    private final String name;

    AccessType(Integer code, String name) {
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
    public static class JpaConverter extends BaseEnumConverter<AccessType> {
        public JpaConverter() {
            super(AccessType.class);
        }
    }
}
