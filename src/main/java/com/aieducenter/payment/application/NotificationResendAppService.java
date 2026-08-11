package com.aieducenter.payment.application;

import com.aieducenter.payment.application.dto.callback.RefundNotifyRequest;
import com.aieducenter.payment.application.dto.command.ResendNotificationCommand;
import com.aieducenter.payment.application.mapper.PaymentOrderMapper;
import com.aieducenter.payment.domain.aggregate.PaymentOrder;
import com.aieducenter.payment.domain.aggregate.RefundOrder;
import com.aieducenter.payment.domain.enums.OperationLogTargetType;
import com.aieducenter.payment.domain.enums.OperationType;
import com.aieducenter.payment.domain.error.PaymentMessage;
import com.aieducenter.payment.domain.repository.PaymentOrderRepository;
import com.aieducenter.payment.domain.repository.RefundOrderRepository;
import com.aieducenter.payment.infrastructure.BusinessSystemNotifier;
import com.cartisan.core.context.RequestContext;
import com.cartisan.core.exception.ApplicationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 通知重发应用服务。
 *
 * <p>运营补发业务系统漏收的支付/退款结果通知：仅当订单处于<b>终态</b>时，把当前结果重 POST 到其
 * {@code notifyUrl}（复用 {@link BusinessSystemNotifier}），<b>不改订单状态、不重置时间线</b>（ADR-0001）。
 * 每次重发落 {@code OperationLog(NOTIFY_RESEND)}，含操作者（请求体）与系统身份（签名上下文）。</p>
 */
@Service
@RequiredArgsConstructor
public class NotificationResendAppService {

    private final PaymentOrderRepository paymentOrderRepository;
    private final RefundOrderRepository refundOrderRepository;
    private final BusinessSystemNotifier businessSystemNotifier;
    private final OperationLogAppService operationLogAppService;

    /**
     * 重发支付结果通知。
     *
     * @param paymentOrderNo 支付订单号
     * @param command        操作者身份（来自请求体）
     */
    public void resendPaymentNotification(String paymentOrderNo, ResendNotificationCommand command) {
        PaymentOrder order = paymentOrderRepository.findByPaymentOrderNo(paymentOrderNo)
            .orElseThrow(() -> new ApplicationException(PaymentMessage.PAYMENT_ORDER_NOT_FOUND));

        if (!order.getStatus().isTerminal()) {
            throw new ApplicationException(PaymentMessage.PAYMENT_ORDER_NOT_TERMINAL);
        }

        businessSystemNotifier.notify(order.getNotifyUrl(), PaymentOrderMapper.convert(order));

        operationLogAppService.record(
            OperationLogTargetType.PAYMENT,
            paymentOrderNo,
            OperationType.NOTIFY_RESEND,
            command.operatorId(),
            command.operatorName(),
            RequestContext.getCallerAppName(),
            "SUCCESS",
            command.remark()
        );
    }

    /**
     * 重发退款结果通知。
     *
     * @param refundOrderNo 退款订单号
     * @param command       操作者身份（来自请求体）
     */
    public void resendRefundNotification(String refundOrderNo, ResendNotificationCommand command) {
        RefundOrder order = refundOrderRepository.findByRefundOrderNo(refundOrderNo)
            .orElseThrow(() -> new ApplicationException(PaymentMessage.REFUND_ORDER_NOT_FOUND));

        if (!order.getStatus().isTerminal()) {
            throw new ApplicationException(PaymentMessage.REFUND_ORDER_NOT_TERMINAL);
        }

        businessSystemNotifier.notify(order.getNotifyUrl(), RefundNotifyRequest.from(order));

        operationLogAppService.record(
            OperationLogTargetType.REFUND,
            refundOrderNo,
            OperationType.NOTIFY_RESEND,
            command.operatorId(),
            command.operatorName(),
            RequestContext.getCallerAppName(),
            "SUCCESS",
            command.remark()
        );
    }
}
