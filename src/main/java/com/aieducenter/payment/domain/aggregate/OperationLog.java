package com.aieducenter.payment.domain.aggregate;

import cn.hutool.core.util.StrUtil;
import com.aieducenter.payment.domain.enums.OperationLogTargetType;
import com.aieducenter.payment.domain.enums.OperationType;
import com.aieducenter.payment.domain.error.PaymentMessage;
import com.cartisan.core.domain.AggregateRoot;
import com.cartisan.core.stereotype.Aggregate;
import com.cartisan.core.util.Assertions;
import com.cartisan.data.jpa.domain.AuditableSoftDeletable;
import jakarta.persistence.*;
import lombok.Getter;

/**
 * 操作日志聚合根。
 *
 * <p>记录行为者对订单发起的写操作（审核通过/拒绝、通知重发等），用于合规追溯与运营审计。
 * 与 {@link PaymentLog}（网关交互日志）职责分离（见 ADR-0002）。</p>
 *
 * <p><b>追加只写</b>：仅记录，不改订单状态；无状态迁移方法。</p>
 */
@Entity
@Table(name = "pay_operation_logs")
@Aggregate
public class OperationLog extends AuditableSoftDeletable implements AggregateRoot<OperationLog, Long> {

    @Getter
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    /** 操作目标类型（PAYMENT / REFUND） */
    @Getter
    @Column(name = "target_type", nullable = false)
    private OperationLogTargetType targetType;

    /** 操作目标单号（支付订单号 / 退款订单号） */
    @Getter
    @Column(name = "target_no", nullable = false, length = 64)
    private String targetNo;

    /** 操作类型（AUDIT_APPROVE / AUDIT_REJECT / NOTIFY_RESEND） */
    @Getter
    @Column(name = "operation", nullable = false)
    private OperationType operation;

    /** 操作者 ID（系统发起的动作可空） */
    @Getter
    @Column(name = "operator_id")
    private Long operatorId;

    /** 操作者名称（系统发起的动作可空） */
    @Getter
    @Column(name = "operator_name", length = 64)
    private String operatorName;

    /** 来源系统（调用方 appName，取自签名上下文；可空） */
    @Getter
    @Column(name = "operator_system", length = 128)
    private String operatorSystem;

    /** 操作结果（稳定 token，如 SUCCESS / FAILED） */
    @Getter
    @Column(name = "result", nullable = false, length = 32)
    private String result;

    /** 备注（决策依据等，可空） */
    @Getter
    @Column(name = "remark", columnDefinition = "TEXT")
    private String remark;

    @PrePersist
    void prePersist() {
        if (id == null) {
            this.id = com.cartisan.data.jpa.id.TsidGenerator.newInstance().generate();
        }
    }

    /** JPA 要求的无参构造函数 */
    protected OperationLog() {
        // JPA only
    }

    /**
     * 记录一条操作日志。
     *
     * @param targetType     目标类型（必填）
     * @param targetNo       目标单号（必填，非空白）
     * @param operation      操作类型（必填）
     * @param operatorId     操作者 ID（可空，系统发起时为 null）
     * @param operatorName   操作者名称（可空）
     * @param operatorSystem 来源系统（可空）
     * @param result         操作结果（必填，非空白）
     * @param remark         备注（可空）
     */
    public OperationLog(
            OperationLogTargetType targetType,
            String targetNo,
            OperationType operation,
            Long operatorId,
            String operatorName,
            String operatorSystem,
            String result,
            String remark
    ) {
        Assertions.require(targetType != null, PaymentMessage.OPERATION_LOG_TARGET_TYPE_REQUIRED);
        Assertions.require(StrUtil.isNotBlank(targetNo), PaymentMessage.OPERATION_LOG_TARGET_NO_REQUIRED);
        Assertions.require(operation != null, PaymentMessage.OPERATION_LOG_OPERATION_REQUIRED);
        Assertions.require(StrUtil.isNotBlank(result), PaymentMessage.OPERATION_LOG_RESULT_REQUIRED);

        this.targetType = targetType;
        this.targetNo = targetNo;
        this.operation = operation;
        this.operatorId = operatorId;
        this.operatorName = operatorName;
        this.operatorSystem = operatorSystem;
        this.result = result;
        this.remark = remark;
    }
}
