package com.aieducenter.payment.hsb.domain.aggregate;

import com.aieducenter.payment.hsb.domain.enums.HsbSettlementStatus;
import com.cartisan.core.domain.AggregateRoot;
import com.cartisan.core.stereotype.Aggregate;
import com.cartisan.data.jpa.domain.AuditableSoftDeletable;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "hsb_settlement_confirms")
@Aggregate
public class HsbSettlementConfirm extends AuditableSoftDeletable implements AggregateRoot<HsbSettlementConfirm, Long> {

    @Getter
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Getter
    @Column(name = "payment_order_id", nullable = false)
    private Long paymentOrderId;

    @Getter
    @Column(name = "payment_order_no", nullable = false, length = 64)
    private String paymentOrderNo;

    @Getter
    @Column(name = "business_main_order_no", nullable = false, length = 64)
    private String businessMainOrderNo;

    @Getter
    @Column(name = "status", nullable = false)
    private HsbSettlementStatus status;

    @Getter
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "business_sub_order_nos", columnDefinition = "json")
    private String businessSubOrderNos;

    @Getter
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "sub_order_ids", columnDefinition = "json")
    private String subOrderIds;

    @Getter
    @Column(name = "confirmed_at")
    private LocalDateTime confirmedAt;

    @PrePersist
    void prePersist() {
        if (id == null) {
            this.id = com.cartisan.data.jpa.id.TsidGenerator.newInstance().generate();
        }
    }

    protected HsbSettlementConfirm() {}

    public HsbSettlementConfirm(
            Long paymentOrderId,
            String paymentOrderNo,
            String businessMainOrderNo,
            List<String> businessSubOrderNos,
            List<String> subOrderIds
    ) {
        this.paymentOrderId = paymentOrderId;
        this.paymentOrderNo = paymentOrderNo;
        this.businessMainOrderNo = businessMainOrderNo;
        this.businessSubOrderNos = com.alibaba.fastjson2.JSON.toJSONString(businessSubOrderNos);
        this.subOrderIds = com.alibaba.fastjson2.JSON.toJSONString(subOrderIds);
        this.status = HsbSettlementStatus.PENDING;
    }

    public void markAsConfirmed() {
        this.status = HsbSettlementStatus.CONFIRMED;
        this.confirmedAt = LocalDateTime.now();
    }

    public void markAsFailed() {
        this.status = HsbSettlementStatus.FAILED;
    }
}
