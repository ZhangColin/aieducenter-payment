package com.aieducenter.payment.hsb.domain.aggregate;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.aieducenter.payment.hsb.domain.enums.HsbPaymentStatus;
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
@Table(name = "hsb_payment_orders")
@Aggregate
public class HsbPaymentOrder extends AuditableSoftDeletable implements AggregateRoot<HsbPaymentOrder, Long> {

    private static final String PAYMENT_ORDER_NO_PREFIX = "HSB";
    private static final DateTimeFormatter TIMESTAMP_FORMAT = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    @Getter
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Getter
    @Column(name = "payment_order_no", nullable = false, unique = true, length = 64)
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
    @Column(name = "status", nullable = false)
    private HsbPaymentStatus status;

    @Getter
    @Column(name = "mkt_id", nullable = false, length = 32)
    private String mktId;

    @Getter
    @Column(name = "payment_method", nullable = false, length = 8)
    private String paymentMethod;

    @Getter
    @Column(name = "order_type", nullable = false, length = 8)
    private String orderType;

    @Getter
    @Column(name = "currency", length = 8)
    private String currency;

    @Getter
    @Column(name = "total_amount", nullable = false)
    private Long totalAmount;

    @Getter
    @Column(name = "txn_total_amount", nullable = false)
    private Long txnTotalAmount;

    @Getter
    @Column(name = "fee_bearer_id", length = 32)
    private String feeBearerId;

    @Getter
    @Column(name = "expired_seconds")
    private Long expiredSeconds;

    @Getter
    @Column(name = "notify_url", length = 512)
    private String notifyUrl;

    @Getter
    @Column(name = "attach", columnDefinition = "TEXT")
    private String attach;

    @Getter
    @Column(name = "pay_url", length = 512)
    private String payUrl;

    @Getter
    @Column(name = "pay_qr_code", length = 512)
    private String payQrCode;

    @Getter
    @Column(name = "prim_order_no", length = 64)
    private String primOrderNo;

    @Getter
    @Column(name = "py_trn_no", length = 64)
    private String pyTrnNo;

    @Getter
    @Column(name = "actual_amount")
    private Long actualAmount;

    @Getter
    @Column(name = "paid_at")
    private LocalDateTime paidAt;

    @Getter
    @Column(name = "failed_at")
    private LocalDateTime failedAt;

    @Getter
    @Column(name = "expired_at")
    private LocalDateTime expiredAt;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_order_id")
    private List<HsbSubOrder> subOrders = new ArrayList<>();

    public List<HsbSubOrder> getSubOrders() {
        return List.copyOf(subOrders);
    }

    @PrePersist
    void prePersist() {
        if (id == null) {
            this.id = com.cartisan.data.jpa.id.TsidGenerator.newInstance().generate();
            this.paymentOrderNo = generatePaymentOrderNo();
            this.status = HsbPaymentStatus.PENDING;
            this.currency = this.currency != null ? this.currency : "156";
            this.expiredAt = calculateExpiredAt();
        }
    }

    protected HsbPaymentOrder() {}

    public HsbPaymentOrder(
            String businessMainOrderNo,
            String businessSystemName,
            String businessName,
            String mktId,
            String paymentMethod,
            String orderType,
            String currency,
            Long totalAmount,
            Long txnTotalAmount,
            String feeBearerId,
            Long expiredSeconds,
            String notifyUrl,
            String attach,
            List<HsbSubOrder> subOrders
    ) {
        Assertions.require(StrUtil.isNotBlank(businessMainOrderNo), HsbMessage.HSB_PAYMENT_ORDER_NOT_FOUND);
        Assertions.require(totalAmount != null && totalAmount > 0, HsbMessage.HSB_PAYMENT_ORDER_NOT_FOUND);
        Assertions.require(txnTotalAmount != null && txnTotalAmount > 0, HsbMessage.HSB_PAYMENT_ORDER_NOT_FOUND);

        this.businessMainOrderNo = businessMainOrderNo;
        this.businessSystemName = businessSystemName;
        this.businessName = businessName;
        this.mktId = mktId;
        this.paymentMethod = paymentMethod;
        this.orderType = orderType;
        this.currency = currency != null ? currency : "156";
        this.totalAmount = totalAmount;
        this.txnTotalAmount = txnTotalAmount;
        this.feeBearerId = feeBearerId;
        this.expiredSeconds = expiredSeconds != null ? expiredSeconds : 3600L;
        this.notifyUrl = notifyUrl;
        this.attach = attach;
        this.status = HsbPaymentStatus.PENDING;
        this.expiredAt = calculateExpiredAt();

        if (subOrders != null) {
            for (HsbSubOrder subOrder : subOrders) {
                addSubOrder(subOrder);
            }
        }
    }

    private void addSubOrder(HsbSubOrder subOrder) {
        this.subOrders.add(subOrder);
    }

    public void setPaymentResult(String payUrl, String payQrCode, String primOrderNo) {
        this.payUrl = payUrl;
        this.payQrCode = payQrCode;
        this.primOrderNo = primOrderNo;
    }

    public void markAsPaid(String pyTrnNo, Long actualAmount) {
        Assertions.require(this.status == HsbPaymentStatus.PENDING, HsbMessage.HSB_PAYMENT_ORDER_NOT_PENDING);
        this.status = HsbPaymentStatus.PAID;
        this.pyTrnNo = pyTrnNo;
        this.actualAmount = actualAmount;
        this.paidAt = LocalDateTime.now();
    }

    public void markAsFailed() {
        Assertions.require(this.status == HsbPaymentStatus.PENDING, HsbMessage.HSB_PAYMENT_ORDER_NOT_PENDING);
        this.status = HsbPaymentStatus.FAILED;
        this.failedAt = LocalDateTime.now();
    }

    public void markAsExpired() {
        Assertions.require(this.status == HsbPaymentStatus.PENDING, HsbMessage.HSB_PAYMENT_ORDER_NOT_PENDING);
        this.status = HsbPaymentStatus.EXPIRED;
    }

    public void updateSubOrderIds(List<HsbSubOrder> updatedSubOrders) {
        for (HsbSubOrder updated : updatedSubOrders) {
            this.subOrders.stream()
                .filter(so -> so.getBusinessSubOrderNo().equals(updated.getBusinessSubOrderNo()))
                .findFirst()
                .ifPresent(so -> so.setSubOrderId(updated.getSubOrderId()));
        }
    }

    private String generatePaymentOrderNo() {
        String timestamp = LocalDateTime.now().format(TIMESTAMP_FORMAT);
        String random = RandomUtil.randomString("0123456789", 6);
        return PAYMENT_ORDER_NO_PREFIX + timestamp + random;
    }

    private LocalDateTime calculateExpiredAt() {
        return LocalDateTime.now().plusSeconds(this.expiredSeconds != null ? this.expiredSeconds : 3600L);
    }
}
