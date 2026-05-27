package com.aieducenter.payment.hsb.domain.aggregate;

import com.cartisan.data.jpa.domain.AuditableSoftDeletable;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "hsb_refund_sub_orders")
public class HsbRefundSubOrder extends AuditableSoftDeletable {

    @Getter
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Getter
    @Column(name = "refund_order_id", nullable = false)
    private Long refundOrderId;

    @Getter
    @Column(name = "refund_order_no", length = 64)
    private String refundOrderNo;

    @Getter
    @Column(name = "business_main_order_no", nullable = false, length = 64)
    private String businessMainOrderNo;

    @Getter
    @Column(name = "business_sub_order_no", nullable = false, length = 64)
    private String businessSubOrderNo;

    @Getter
    @Column(name = "sub_order_id", length = 64)
    private String subOrderId;

    @Getter
    @Column(name = "refund_amount", nullable = false)
    private Long refundAmount;

    @PrePersist
    void prePersist() {
        if (id == null) {
            this.id = com.cartisan.data.jpa.id.TsidGenerator.newInstance().generate();
        }
    }

    protected HsbRefundSubOrder() {}

    public HsbRefundSubOrder(
            String businessMainOrderNo,
            String businessSubOrderNo,
            String subOrderId,
            Long refundAmount
    ) {
        this.businessMainOrderNo = businessMainOrderNo;
        this.businessSubOrderNo = businessSubOrderNo;
        this.subOrderId = subOrderId;
        this.refundAmount = refundAmount;
    }

    void setRefundOrderInfo(Long refundOrderId, String refundOrderNo) {
        this.refundOrderId = refundOrderId;
        this.refundOrderNo = refundOrderNo;
    }
}
