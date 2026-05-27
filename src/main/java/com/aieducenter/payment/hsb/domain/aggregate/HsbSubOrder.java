package com.aieducenter.payment.hsb.domain.aggregate;

import com.cartisan.data.jpa.domain.AuditableSoftDeletable;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Table(name = "hsb_payment_sub_orders")
public class HsbSubOrder extends AuditableSoftDeletable {

    @Getter
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Getter
    @Column(name = "payment_order_id", nullable = false)
    private Long paymentOrderId;

    @Getter
    @Column(name = "payment_order_no", length = 64)
    private String paymentOrderNo;

    @Getter
    @Column(name = "business_main_order_no", nullable = false, length = 64)
    private String businessMainOrderNo;

    @Getter
    @Column(name = "business_sub_order_no", nullable = false, length = 64)
    private String businessSubOrderNo;

    @Getter
    @Column(name = "mkt_mrch_id", nullable = false, length = 32)
    private String mktMrchId;

    @Getter
    @Column(name = "order_amount", nullable = false)
    private Long orderAmount;

    @Getter
    @Column(name = "txn_amount", nullable = false)
    private Long txnAmount;

    @Getter
    @Column(name = "sub_order_id", length = 64)
    private String subOrderId;

    @Getter
    @Column(name = "confirmed")
    private Boolean confirmed = false;

    @Getter
    @Column(name = "confirmed_at")
    private LocalDateTime confirmedAt;

    @PrePersist
    void prePersist() {
        if (id == null) {
            this.id = com.cartisan.data.jpa.id.TsidGenerator.newInstance().generate();
        }
    }

    protected HsbSubOrder() {}

    public HsbSubOrder(
            String businessMainOrderNo,
            String businessSubOrderNo,
            String mktMrchId,
            Long orderAmount,
            Long txnAmount
    ) {
        this.businessMainOrderNo = businessMainOrderNo;
        this.businessSubOrderNo = businessSubOrderNo;
        this.mktMrchId = mktMrchId;
        this.orderAmount = orderAmount;
        this.txnAmount = txnAmount;
        this.confirmed = false;
    }

    void setPaymentOrderInfo(Long paymentOrderId, String paymentOrderNo) {
        this.paymentOrderId = paymentOrderId;
        this.paymentOrderNo = paymentOrderNo;
    }

    public void setSubOrderId(String subOrderId) {
        this.subOrderId = subOrderId;
    }

    public void markAsConfirmed() {
        this.confirmed = true;
        this.confirmedAt = LocalDateTime.now();
    }
}
