package com.aieducenter.payment.hsb.domain.enums;

import com.cartisan.core.domain.BaseEnum;
import com.cartisan.data.jpa.converter.BaseEnumConverter;
import jakarta.persistence.Converter;

public enum HsbSettlementStatus implements BaseEnum<HsbSettlementStatus> {
    PENDING(1, "待确认"),
    CONFIRMED(2, "已确认"),
    FAILED(3, "确认失败");

    private final Integer code;
    private final String name;

    HsbSettlementStatus(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public Integer getCode() { return code; }

    @Override
    public String getName() { return name; }

    public boolean isTerminal() {
        return this == CONFIRMED || this == FAILED;
    }

    @Converter(autoApply = true)
    public static class JpaConverter extends BaseEnumConverter<HsbSettlementStatus> {
        public JpaConverter() { super(HsbSettlementStatus.class); }
    }
}
