package com.aieducenter.payment.hsb.domain.enums;

import com.cartisan.core.domain.BaseEnum;
import com.cartisan.data.jpa.converter.BaseEnumConverter;
import jakarta.persistence.Converter;

public enum HsbRefundStatus implements BaseEnum<HsbRefundStatus> {
    PENDING(1, "待退款"),
    REFUNDING(2, "退款中"),
    SUCCESS(3, "退款成功"),
    FAILED(4, "退款失败");

    private final Integer code;
    private final String name;

    HsbRefundStatus(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public Integer getCode() { return code; }

    @Override
    public String getName() { return name; }

    public boolean isTerminal() {
        return this == SUCCESS || this == FAILED;
    }

    @Converter(autoApply = true)
    public static class JpaConverter extends BaseEnumConverter<HsbRefundStatus> {
        public JpaConverter() { super(HsbRefundStatus.class); }
    }
}
