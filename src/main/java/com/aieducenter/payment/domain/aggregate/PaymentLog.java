package com.aieducenter.payment.domain.aggregate;

import com.cartisan.core.domain.AggregateRoot;
import com.cartisan.core.stereotype.Aggregate;
import com.cartisan.data.jpa.domain.AuditableSoftDeletable;
import jakarta.persistence.*;
import lombok.Getter;

/**
 * 支付日志聚合根
 *
 * <p>记录所有银行接口调用的详细信息，用于问题排查和审计追溯</p>
 */
@Entity
@Table(name = "pay_payment_logs")
@Aggregate
public class PaymentLog extends AuditableSoftDeletable implements AggregateRoot<PaymentLog, Long> {

    @Getter
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    /**
     * 关联的支付订单号
     */
    @Getter
    @Column(name = "payment_order_no", length = 64)
    private String paymentOrderNo;

    /**
     * 关联的退款订单号
     */
    @Getter
    @Column(name = "refund_order_no", length = 64)
    private String refundOrderNo;

    /**
     * 日志类型
     * <p>如: PAYMENT_REQUEST, PAYMENT_QUERY, REFUND_REQUEST, REFUND_QUERY</p>
     */
    @Getter
    @Column(name = "log_type", nullable = false, length = 32)
    private String logType;

    /**
     * 银行编码
     * <p>如: ICBC, ABC, CCB</p>
     */
    @Getter
    @Column(name = "bank_code", nullable = false, length = 16)
    private String bankCode;

    /**
     * 银行接口
     * <p>如: qrcode/consumption, aggregatepay/b2c/online/orderqry</p>
     */
    @Getter
    @Column(name = "bank_interface", nullable = false, length = 64)
    private String bankInterface;

    /**
     * 请求 URL
     */
    @Getter
    @Column(name = "request_url", length = 512)
    private String requestUrl;

    /**
     * 请求参数（JSON 格式）
     */
    @Getter
    @Column(name = "request_params", columnDefinition = "TEXT")
    private String requestParams;

    /**
     * 响应参数（JSON 格式）
     */
    @Getter
    @Column(name = "response_params", columnDefinition = "TEXT")
    private String responseParams;

    /**
     * HTTP 状态码
     */
    @Getter
    @Column(name = "http_status")
    private Integer httpStatus;

    /**
     * 业务返回码
     */
    @Getter
    @Column(name = "return_code", length = 16)
    private String returnCode;

    /**
     * 业务返回消息
     */
    @Getter
    @Column(name = "return_msg", columnDefinition = "TEXT")
    private String returnMsg;

    /**
     * 执行耗时（毫秒）
     */
    @Getter
    @Column(name = "execution_time")
    private Long executionTime;

    /**
     * 是否成功
     */
    @Getter
    @Column(name = "success", nullable = false)
    private Boolean success;

    /**
     * 错误信息
     */
    @Getter
    @Column(name = "error_message", columnDefinition = "TEXT")
    private String errorMessage;

    @PrePersist
    void prePersist() {
        if (id == null) {
            this.id = com.cartisan.data.jpa.id.TsidGenerator.newInstance().generate();
        }
    }

    /**
     * JPA 要求的无参构造函数
     */
    protected PaymentLog() {
        // JPA only
    }

    /**
     * 创建支付日志
     */
    public PaymentLog(
            String paymentOrderNo,
            String refundOrderNo,
            String logType,
            String bankCode,
            String bankInterface,
            String requestUrl,
            String requestParams,
            String responseParams,
            Integer httpStatus,
            String returnCode,
            String returnMsg,
            Long executionTime,
            Boolean success,
            String errorMessage
    ) {
        this.paymentOrderNo = paymentOrderNo;
        this.refundOrderNo = refundOrderNo;
        this.logType = logType;
        this.bankCode = bankCode;
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
