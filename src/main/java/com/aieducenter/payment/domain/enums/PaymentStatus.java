package com.aieducenter.payment.domain.enums;

import com.cartisan.core.domain.BaseEnum;
import com.cartisan.data.jpa.converter.BaseEnumConverter;
import jakarta.persistence.Converter;

public enum PaymentStatus implements BaseEnum<PaymentStatus> {
    PENDING(1, "待支付"),
    PAID(2, "已支付"),
    FAILED(3, "支付失败"),
    CANCELLED(4, "已取消"),
    EXPIRED(5, "已过期");

    private final Integer code;
    private final String name;

    PaymentStatus(Integer code, String name) {
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

    public boolean isTerminal() {
        return this == PAID || this == FAILED || this == CANCELLED || this == EXPIRED;
    }

    @Converter(autoApply = true)
    public static class JpaConverter extends BaseEnumConverter<PaymentStatus> {
        public JpaConverter() {
            super(PaymentStatus.class);
        }
    }
}
