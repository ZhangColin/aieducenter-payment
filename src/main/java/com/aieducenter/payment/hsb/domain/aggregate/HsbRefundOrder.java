package com.aieducenter.payment.hsb.domain.aggregate;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.aieducenter.payment.hsb.domain.enums.HsbRefundStatus;
import com.aieducenter.payment.hsb.domain.error.HsbMessage;
import com.cartisan.core.domain.AggregateRoot;
import com.cartisan.core.stereotype.Aggregate;
import com.cartisan.core.util.Assertions;
import com.cartisan.data.jpa.domain.AuditableSoftDeletable;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "hsb_refund_orders")
@Aggregate
public class HsbRefundOrder extends AuditableSoftDeletable implements AggregateRoot<HsbRefundOrder, Long> {

    private static final String REFUND_ORDER_NO_PREFIX = "HSBRF";
    private static final DateTimeFormatter TIMESTAMP_FORMAT = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    @Getter
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Getter
    @Column(name = "refund_order_no", nullable = false, unique = true, length = 64)
    private String refundOrderNo;

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
    @Column(name = "business_system_name", nullable = false, length = 128)
    private String businessSystemName;

    @Getter
    @Column(name = "business_name", length = 128)
    private String businessName;

    @Getter
    @Column(name = "refund_type", length = 16)
    private String refundType;

    @Getter
    @Column(name = "status", nullable = false)
    private HsbRefundStatus status;

    @Getter
    @Column(name = "refund_amount", nullable = false)
    private Long refundAmount;

    @Getter
    @Column(name = "reason", length = 512)
    private String reason;

    @Getter
    @Column(name = "notify_url", length = 512)
    private String notifyUrl;

    @Getter
    @Column(name = "attach", columnDefinition = "TEXT")
    private String attach;

    @Getter
    @Column(name = "super_refund_no", length = 64)
    private String superRefundNo;

    @Getter
    @Column(name = "refunded_at")
    private LocalDateTime refundedAt;

    @Getter
    @Column(name = "failed_at")
    private LocalDateTime failedAt;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "refund_order_id")
    private List<HsbRefundSubOrder> subOrders = new ArrayList<>();

    public List<HsbRefundSubOrder> getSubOrders() {
        return List.copyOf(subOrders);
    }

    @PrePersist
    void prePersist() {
        if (id == null) {
            this.id = com.cartisan.data.jpa.id.TsidGenerator.newInstance().generate();
            this.refundOrderNo = generateRefundOrderNo();
            this.status = HsbRefundStatus.PENDING;
        }
    }

    protected HsbRefundOrder() {}

    public HsbRefundOrder(
            Long paymentOrderId,
            String paymentOrderNo,
            String businessMainOrderNo,
            String businessSystemName,
            String businessName,
            String refundType,
            Long refundAmount,
            String reason,
            String notifyUrl,
            String attach,
            List<HsbRefundSubOrder> subOrders
    ) {
        Assertions.require(refundAmount != null && refundAmount > 0, HsbMessage.HSB_REFUND_AMOUNT_EXCEEDS);

        this.paymentOrderId = paymentOrderId;
        this.paymentOrderNo = paymentOrderNo;
        this.businessMainOrderNo = businessMainOrderNo;
        this.businessSystemName = businessSystemName;
        this.businessName = businessName;
        this.refundType = refundType != null ? refundType : "ASYNC";
        this.refundAmount = refundAmount;
        this.reason = reason;
        this.notifyUrl = notifyUrl;
        this.attach = attach;
        this.status = HsbRefundStatus.PENDING;

        if (subOrders != null) {
            for (HsbRefundSubOrder subOrder : subOrders) {
                this.subOrders.add(subOrder);
            }
        }
    }

    public void markAsRefunding() {
        Assertions.require(this.status == HsbRefundStatus.PENDING || this.status == HsbRefundStatus.REFUNDING,
            HsbMessage.HSB_REFUND_ORDER_NOT_PENDING);
        this.status = HsbRefundStatus.REFUNDING;
    }

    public void markAsSuccess(String superRefundNo) {
        Assertions.require(this.status == HsbRefundStatus.PENDING || this.status == HsbRefundStatus.REFUNDING,
            HsbMessage.HSB_REFUND_ORDER_NOT_PENDING);
        this.status = HsbRefundStatus.SUCCESS;
        this.superRefundNo = superRefundNo;
        this.refundedAt = LocalDateTime.now();
    }

    public void markAsFailed() {
        Assertions.require(this.status == HsbRefundStatus.PENDING || this.status == HsbRefundStatus.REFUNDING,
            HsbMessage.HSB_REFUND_ORDER_NOT_PENDING);
        this.status = HsbRefundStatus.FAILED;
        this.failedAt = LocalDateTime.now();
    }

    private String generateRefundOrderNo() {
        String timestamp = LocalDateTime.now().format(TIMESTAMP_FORMAT);
        String random = RandomUtil.randomString("0123456789", 6);
        return REFUND_ORDER_NO_PREFIX + timestamp + random;
    }
}
