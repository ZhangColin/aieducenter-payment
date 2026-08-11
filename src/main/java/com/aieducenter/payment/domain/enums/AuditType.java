package com.aieducenter.payment.domain.enums;

import com.cartisan.core.domain.BaseEnum;
import com.cartisan.data.jpa.converter.BaseEnumConverter;
import jakarta.persistence.Converter;

/**
 * 退款审核类型。
 *
 * <p>{@link #AUTO} 为免审（创建时系统自动通过，{@code auditorId} 为空）；
 * {@link #MANUAL} 为人工审核（操作者放行/拒绝，{@code auditorId} 非空）。</p>
 *
 * <p>判断「是否人工审核」应看本字段，而非 {@code auditorName} 之类哨兵值（见 CONTEXT.md）。</p>
 */
public enum AuditType implements BaseEnum<AuditType> {
    AUTO(1, "免审"),
    MANUAL(2, "人工审核");

    private final Integer code;
    private final String name;

    AuditType(Integer code, String name) {
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
    public static class JpaConverter extends BaseEnumConverter<AuditType> {
        public JpaConverter() {
            super(AuditType.class);
        }
    }
}
