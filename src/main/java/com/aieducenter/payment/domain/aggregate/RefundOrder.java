package com.aieducenter.payment.domain.aggregate;

import cn.hutool.core.util.StrUtil;
import com.aieducenter.payment.domain.enums.AuditType;
import com.aieducenter.payment.domain.enums.RefundStatus;
import com.aieducenter.payment.domain.error.PaymentMessage;
import com.cartisan.core.domain.AggregateRoot;
import com.cartisan.core.stereotype.Aggregate;
import com.cartisan.core.util.Assertions;
import com.cartisan.data.jpa.domain.AuditableSoftDeletable;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Table(name = "pay_refund_orders")
@Aggregate
public class RefundOrder extends AuditableSoftDeletable implements AggregateRoot<RefundOrder, Long> {
    private static final String REFUND_ORDER_NO_PREFIX = "REF";

    @Getter
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Getter
    @Column(name = "business_order_no", nullable = false, length = 64)
    private String businessOrderNo;

    @Getter
    @Column(name = "refund_order_no", nullable = false, unique = true, length = 64)
    private String refundOrderNo;

    @Getter
    @Column(name = "payment_order_no", nullable = false, length = 64)
    private String paymentOrderNo;

    @Getter
    @Column(name = "business_system_name", nullable = false, length = 128)
    private String businessSystemName;

    @Getter
    @Column(name = "business_name", length = 128)
    private String businessName;

    @Getter
    @Column(name = "status", nullable = false)
    private RefundStatus status;

    @Getter
    @Column(name = "refund_amount", nullable = false)
    private Long refundAmount;

    @Getter
    @Column(name = "refundable_amount", nullable = false)
    private Long refundableAmount;

    @Getter
    @Column(name = "reason", length = 512)
    private String reason;

    // Audit information
    @Getter
    @Column(name = "audit_type")
    private AuditType auditType;

    @Getter
    @Column(name = "auditor_id")
    private Long auditorId;

    @Getter
    @Column(name = "auditor_name", length = 64)
    private String auditorName;

    @Getter
    @Column(name = "audit_agreed")
    private Boolean auditAgreed;

    @Getter
    @Column(name = "audited_at")
    private LocalDateTime auditedAt;

    @Getter
    @Column(name = "audit_remark", columnDefinition = "TEXT")
    private String auditRemark;

    // Timestamps
    @Getter
    @Column(name = "approved_at")
    private LocalDateTime approvedAt;

    @Getter
    @Column(name = "refunded_at")
    private LocalDateTime refundedAt;

    @Getter
    @Column(name = "failed_at")
    private LocalDateTime failedAt;

    // Bank information
    @Getter
    @Column(name = "bank_refund_no", length = 64)
    private String bankRefundNo;

    @Getter
    @Column(name = "attach", columnDefinition = "TEXT")
    private String attach;

    @Getter
    @Column(name = "notify_url", length = 512)
    private String notifyUrl;

    @PrePersist
    void prePersist() {
        if (id == null) {
            this.id = com.cartisan.data.jpa.id.TsidGenerator.newInstance().generate();
            this.refundOrderNo = generateRefundOrderNo();
            this.status = RefundStatus.PENDING;
        }
    }

    /**
     * JPA 要求的无参构造函数
     */
    protected RefundOrder() {
        // JPA only
    }

    /**
     * 创建退款订单
     */
    public RefundOrder(
            String businessOrderNo,
            String paymentOrderNo,
            String businessSystemName,
            String businessName,
            Long refundAmount,
            Long refundableAmount,
            String reason,
            String attach,
            String notifyUrl
    ) {
        Assertions.require(StrUtil.isNotBlank(businessOrderNo),
            PaymentMessage.AMOUNT_INVALID);
        Assertions.require(refundAmount != null && refundAmount > 0,
            PaymentMessage.AMOUNT_INVALID);
        Assertions.require(refundableAmount != null && refundAmount <= refundableAmount,
            PaymentMessage.REFUND_AMOUNT_EXCEEDS);

        this.businessOrderNo = businessOrderNo;
        this.paymentOrderNo = paymentOrderNo;
        this.businessSystemName = businessSystemName;
        this.businessName = businessName;
        this.refundAmount = refundAmount;
        this.refundableAmount = refundableAmount;
        this.reason = reason;
        this.attach = attach;
        this.notifyUrl = notifyUrl;

        // 初始状态（不依赖 @PrePersist）
        this.status = RefundStatus.PENDING;
    }

    /**
     * 审核退款
     */
    public void audit(Long auditorId, String auditorName, Boolean agreed, String remark) {
        Assertions.require(this.status == RefundStatus.PENDING,
            PaymentMessage.REFUND_ORDER_NOT_PENDING);

        this.auditType = AuditType.MANUAL;
        this.auditorId = auditorId;
        this.auditorName = auditorName;
        this.auditAgreed = agreed;
        this.auditRemark = remark;
        this.auditedAt = LocalDateTime.now();

        if (agreed) {
            // 审核通过
            this.status = RefundStatus.APPROVED;
            this.approvedAt = LocalDateTime.now();
        } else {
            // 审核拒绝
            this.status = RefundStatus.REJECTED;
        }
    }

    /**
     * 免审自动通过（创建退款时 needAudit=false 触发）。
     *
     * <p>置 {@code auditType=AUTO}，不记录审核者（{@code auditorId/Name} 保持空），
     * 直接进入 APPROVED。判断「是否免审」应看 {@link #getAuditType()}，而非审核者哨兵值。</p>
     */
    public void autoAudit() {
        Assertions.require(this.status == RefundStatus.PENDING,
            PaymentMessage.REFUND_ORDER_NOT_PENDING);

        this.auditType = AuditType.AUTO;
        this.auditAgreed = true;
        this.auditedAt = LocalDateTime.now();
        this.status = RefundStatus.APPROVED;
        this.approvedAt = LocalDateTime.now();
    }

    /**
     * 开始执行退款
     */
    public void startRefund(String bankRefundNo) {
        Assertions.require(this.status == RefundStatus.APPROVED,
            PaymentMessage.REFUND_ORDER_NOT_APPROVED);
        this.status = RefundStatus.REFUNDING;
        this.bankRefundNo = bankRefundNo;
    }

    /**
     * 退款成功
     */
    public void refundSuccess(String bankRefundNo) {
        Assertions.require(this.status == RefundStatus.REFUNDING,
            PaymentMessage.REFUND_ORDER_NOT_REFUNDING);

        this.status = RefundStatus.SUCCESS;
        this.bankRefundNo = bankRefundNo;
        this.refundedAt = LocalDateTime.now();
    }

    /**
     * 退款失败
     */
    public void refundFailed(String errorMessage) {
        Assertions.require(this.status == RefundStatus.REFUNDING,
            PaymentMessage.REFUND_ORDER_NOT_REFUNDING);

        this.status = RefundStatus.FAILED;
        this.failedAt = LocalDateTime.now();
    }

    /**
     * 检查是否已批准
     */
    public boolean isApproved() {
        return this.status == RefundStatus.APPROVED;
    }

    private String generateRefundOrderNo() {
        String timestamp = LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        String random = cn.hutool.core.util.RandomUtil.randomString("0123456789", 6);
        return REFUND_ORDER_NO_PREFIX + timestamp + random;
    }
}
