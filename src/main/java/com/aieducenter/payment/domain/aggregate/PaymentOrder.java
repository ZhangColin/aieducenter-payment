package com.aieducenter.payment.domain.aggregate;

import cn.hutool.core.util.StrUtil;
import com.aieducenter.payment.domain.enums.PaymentChannel;
import com.aieducenter.payment.domain.enums.PaymentStatus;
import com.aieducenter.payment.domain.error.PaymentMessage;
import com.cartisan.core.domain.AggregateRoot;
import com.cartisan.core.stereotype.Aggregate;
import com.cartisan.core.util.Assertions;
import com.cartisan.data.jpa.domain.AuditableSoftDeletable;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Table(name = "pay_payment_orders")
@Aggregate
public class PaymentOrder extends AuditableSoftDeletable implements AggregateRoot<PaymentOrder, Long> {
    private static final String PAYMENT_ORDER_NO_PREFIX = "PAY";

    @Getter
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Getter
    @Column(name = "business_order_no", nullable = false, length = 64)
    private String businessOrderNo;

    @Getter
    @Column(name = "payment_order_no", nullable = false, unique = true, length = 64)
    private String paymentOrderNo;

    @Getter
    @Column(name = "business_system_name", nullable = false, length = 128)
    private String businessSystemName;

    @Getter
    @Column(name = "business_name", length = 128)
    private String businessName;

    @Getter
    @Column(name = "status", nullable = false)
    private PaymentStatus status;

    @Getter
    @Column(name = "amount", nullable = false)
    private Long amount;

    @Getter
    @Column(name = "subject")
    private String subject;

    @Getter
    @Column(name = "body", columnDefinition = "TEXT")
    private String body;

    @Getter
    @Column(name = "payment_channel")
    private PaymentChannel paymentChannel;

    @Getter
    @Column(name = "client_ip", length = 64)
    private String clientIp;

    @Getter
    @Column(name = "notify_url", length = 512)
    private String notifyUrl;

    @Getter
    @Column(name = "qr_code_url", length = 512)
    private String qrCodeUrl;

    @Getter
    @Column(name = "attach", columnDefinition = "TEXT")
    private String attach;

    @Getter
    @Column(name = "expired_seconds")
    private Long expiredSeconds;

    // Timestamps for status transitions
    @Getter
    @Column(name = "paid_at")
    private LocalDateTime paidAt;

    @Getter
    @Column(name = "failed_at")
    private LocalDateTime failedAt;

    @Getter
    @Column(name = "cancelled_at")
    private LocalDateTime cancelledAt;

    @Getter
    @Column(name = "expired_at")
    private LocalDateTime expiredAt;

    // Bank information
    @Getter
    @Column(name = "bank_order_no", length = 64)
    private String bankOrderNo;

    @Getter
    @Column(name = "third_party_order_no", length = 128)
    private String thirdPartyOrderNo;

    @Getter
    @Column(name = "actual_amount")
    private Long actualAmount;

    @PrePersist
    void prePersist() {
        if (id == null) {
            this.id = com.cartisan.data.jpa.id.TsidGenerator.newInstance().generate();
            this.paymentOrderNo = generatePaymentOrderNo();
            this.status = PaymentStatus.PENDING;
            this.expiredAt = calculateExpiredAt();
        }
    }

    protected PaymentOrder() {}

    /**
     * 创建支付订单
     */
    public PaymentOrder(
            String businessOrderNo,
            String businessSystemName,
            String businessName,
            Long amount,
            String subject,
            String body,
            String notifyUrl,
            String attach,
            Long expiredSeconds
    ) {
        Assertions.require(StrUtil.isNotBlank(businessOrderNo),
            PaymentMessage.AMOUNT_INVALID);
        Assertions.require(amount != null && amount > 0,
            PaymentMessage.AMOUNT_INVALID);

        this.businessOrderNo = businessOrderNo;
        this.businessSystemName = businessSystemName;
        this.businessName = businessName;
        this.amount = amount;
        this.subject = subject;
        this.body = body;
        this.notifyUrl = notifyUrl;
        this.attach = attach;
        this.expiredSeconds = expiredSeconds != null ? expiredSeconds : 3600L;

        // 初始状态（不依赖 @PrePersist）
        this.status = PaymentStatus.PENDING;
        this.expiredAt = calculateExpiredAt();
    }

    /**
     * 设置客户端 IP（由应用层调用）
     */
    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    /**
     * 设置二维码 URL（银行网关返回后由应用层调用）
     */
    public void setQrCodeUrl(String qrCodeUrl) {
        this.qrCodeUrl = qrCodeUrl;
    }

    /**
     * 支付成功
     */
    public void markAsPaid(String bankOrderNo, String thirdPartyOrderNo, PaymentChannel paymentChannel, Long actualAmount) {
        Assertions.require(this.status == PaymentStatus.PENDING,
            PaymentMessage.PAYMENT_ORDER_NOT_PENDING);

        this.status = PaymentStatus.PAID;
        this.paidAt = LocalDateTime.now();
        this.bankOrderNo = bankOrderNo;
        this.thirdPartyOrderNo = thirdPartyOrderNo;
        this.paymentChannel = paymentChannel;
        this.actualAmount = actualAmount;
    }

    /**
     * 支付失败
     */
    public void markAsFailed(String reason) {
        Assertions.require(this.status == PaymentStatus.PENDING,
            PaymentMessage.PAYMENT_ORDER_NOT_PENDING);

        this.status = PaymentStatus.FAILED;
        this.failedAt = LocalDateTime.now();
    }

    /**
     * 取消支付
     */
    public void cancel() {
        Assertions.require(this.status == PaymentStatus.PENDING,
            PaymentMessage.PAYMENT_ORDER_NOT_PENDING);

        this.status = PaymentStatus.CANCELLED;
        this.cancelledAt = LocalDateTime.now();
    }

    /**
     * 标记为过期
     */
    public void markAsExpired() {
        Assertions.require(this.status == PaymentStatus.PENDING,
            PaymentMessage.PAYMENT_ORDER_NOT_PENDING);

        this.status = PaymentStatus.EXPIRED;
    }

    /**
     * 检查是否过期
     */
    public boolean isExpired() {
        return LocalDateTime.now().isAfter(this.expiredAt);
    }

    private String generatePaymentOrderNo() {
        String timestamp = LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        String random = cn.hutool.core.util.RandomUtil.randomString("0123456789", 6);
        return PAYMENT_ORDER_NO_PREFIX + timestamp + random;
    }

    private LocalDateTime calculateExpiredAt() {
        return LocalDateTime.now().plusSeconds(this.expiredSeconds != null ? this.expiredSeconds : 3600L);
    }
}
