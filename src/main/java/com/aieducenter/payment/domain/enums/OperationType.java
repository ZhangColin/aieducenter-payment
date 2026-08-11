package com.aieducenter.payment.domain.enums;

import com.cartisan.core.domain.BaseEnum;
import com.cartisan.data.jpa.converter.BaseEnumConverter;
import jakarta.persistence.Converter;

/**
 * 操作日志的操作类型。
 *
 * <p>行为者对订单发起的写操作。</p>
 */
public enum OperationType implements BaseEnum<OperationType> {
    AUDIT_APPROVE(1, "审核通过"),
    AUDIT_REJECT(2, "审核拒绝"),
    NOTIFY_RESEND(3, "通知重发");

    private final Integer code;
    private final String name;

    OperationType(Integer code, String name) {
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
    public static class JpaConverter extends BaseEnumConverter<OperationType> {
        public JpaConverter() {
            super(OperationType.class);
        }
    }
}
