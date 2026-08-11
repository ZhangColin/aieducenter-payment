package com.aieducenter.payment.application.mapper;

import com.aieducenter.payment.application.dto.response.OrderLifecycleResponse;
import com.aieducenter.payment.domain.aggregate.OperationLog;
import com.aieducenter.payment.domain.aggregate.PaymentLog;
import org.springframework.stereotype.Component;

/**
 * 订单生命周期事件映射器（手写，非 MapStruct）。
 *
 * <p>生命周期是<b>两源 → 一目标</b>的合并读模型，不适用 {@code DomainMapper<单源, 单目标>} 的 MapStruct
 * {@code convert} 模式。故以普通 {@code @Component} 类提供两个来源各自的映射法，由
 * {@link com.aieducenter.payment.application.OrderLifecycleAppService} 注入并按来源分别调用。</p>
 *
 * <p>银行无关（CONTEXT.md 不变式 5）：网关事件只读 PaymentLog 的通用列（bankCode/bankInterface/success/returnMsg/errorMessage），
 * 无 ICBC 硬编码。</p>
 */
@Component
public class OrderLifecycleMapper {

    /** 网关交互日志（PaymentLog）→ 统一事件，来源 GATEWAY。 */
    public OrderLifecycleResponse fromPaymentLog(PaymentLog log) {
        boolean success = Boolean.TRUE.equals(log.getSuccess());
        return new OrderLifecycleResponse(
            log.getId(),
            "GATEWAY",
            log.getCreatedAt(),
            log.getLogType(),
            log.getLogType(),
            success ? "SUCCESS" : "FAILED",
            log.getBankInterface(),
            log.getBankCode(),
            success ? log.getReturnMsg() : log.getErrorMessage()
        );
    }

    /** 行为者操作日志（OperationLog）→ 统一事件，来源 OPERATION。 */
    public OrderLifecycleResponse fromOperationLog(OperationLog log) {
        return new OrderLifecycleResponse(
            log.getId(),
            "OPERATION",
            log.getCreatedAt(),
            log.getOperation().name(),
            log.getOperation().getName(),
            log.getResult(),
            log.getOperatorName(),
            log.getOperatorSystem(),
            log.getRemark()
        );
    }
}
