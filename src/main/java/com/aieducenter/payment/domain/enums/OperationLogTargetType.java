package com.aieducenter.payment.domain.enums;

import com.cartisan.core.domain.BaseEnum;
import com.cartisan.data.jpa.converter.BaseEnumConverter;
import jakarta.persistence.Converter;

/**
 * 操作日志目标类型。
 */
public enum OperationLogTargetType implements BaseEnum<OperationLogTargetType> {
    PAYMENT(1, "支付订单"),
    REFUND(2, "退款订单");

    private final Integer code;
    private final String name;

    OperationLogTargetType(Integer code, String name) {
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
    public static class JpaConverter extends BaseEnumConverter<OperationLogTargetType> {
        public JpaConverter() {
            super(OperationLogTargetType.class);
        }
    }
}
