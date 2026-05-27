package com.aieducenter.payment.hsb.domain.aggregate;

import com.cartisan.core.domain.AggregateRoot;
import com.cartisan.core.stereotype.Aggregate;
import com.cartisan.data.jpa.domain.AuditableSoftDeletable;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "hsb_payment_logs")
@Aggregate
public class HsbPaymentLog extends AuditableSoftDeletable implements AggregateRoot<HsbPaymentLog, Long> {

    @Getter
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Getter
    @Column(name = "payment_order_no", length = 64)
    private String paymentOrderNo;

    @Getter
    @Column(name = "refund_order_no", length = 64)
    private String refundOrderNo;

    @Getter
    @Column(name = "log_type", length = 32)
    private String logType;

    @Getter
    @Column(name = "bank_interface", length = 64)
    private String bankInterface;

    @Getter
    @Column(name = "request_url", length = 512)
    private String requestUrl;

    @Getter
    @Column(name = "request_params", columnDefinition = "TEXT")
    private String requestParams;

    @Getter
    @Column(name = "response_params", columnDefinition = "TEXT")
    private String responseParams;

    @Getter
    @Column(name = "http_status")
    private Integer httpStatus;

    @Getter
    @Column(name = "return_code", length = 16)
    private String returnCode;

    @Getter
    @Column(name = "return_msg", columnDefinition = "TEXT")
    private String returnMsg;

    @Getter
    @Column(name = "execution_time")
    private Long executionTime;

    @Getter
    @Column(name = "success")
    private Boolean success;

    @Getter
    @Column(name = "error_message", columnDefinition = "TEXT")
    private String errorMessage;

    @PrePersist
    void prePersist() {
        if (id == null) {
            this.id = com.cartisan.data.jpa.id.TsidGenerator.newInstance().generate();
        }
    }

    protected HsbPaymentLog() {}

    public HsbPaymentLog(
            String paymentOrderNo, String refundOrderNo, String logType,
            String bankInterface, String requestUrl, String requestParams,
            String responseParams, Integer httpStatus, String returnCode,
            String returnMsg, Long executionTime, Boolean success, String errorMessage
    ) {
        this.paymentOrderNo = paymentOrderNo;
        this.refundOrderNo = refundOrderNo;
        this.logType = logType;
        this.bankInterface = bankInterface;
        this.requestUrl = requestUrl;
        this.requestParams = requestParams;
        this.responseParams = responseParams;
        this.httpStatus = httpStatus;
        this.returnCode = returnCode;
        this.returnMsg = returnMsg;
        this.executionTime = executionTime;
        this.success = success;
        this.errorMessage = errorMessage;
    }
}
