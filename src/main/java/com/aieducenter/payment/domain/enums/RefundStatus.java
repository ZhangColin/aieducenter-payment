package com.aieducenter.payment.domain.enums;

import com.cartisan.core.domain.BaseEnum;
import com.cartisan.data.jpa.converter.BaseEnumConverter;
import jakarta.persistence.Converter;

public enum RefundStatus implements BaseEnum<RefundStatus> {
    PENDING(1, "待审核"),
    REJECTED(2, "已拒绝"),
    APPROVED(3, "已批准"),
    REFUNDING(4, "退款中"),
    SUCCESS(5, "退款成功"),
    FAILED(6, "退款失败");

    private final Integer code;
    private final String name;

    RefundStatus(Integer code, String name) {
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
        return this == REJECTED || this == SUCCESS || this == FAILED;
    }

    @Converter(autoApply = true)
    public static class JpaConverter extends BaseEnumConverter<RefundStatus> {
        public JpaConverter() {
            super(RefundStatus.class);
        }
    }
}
