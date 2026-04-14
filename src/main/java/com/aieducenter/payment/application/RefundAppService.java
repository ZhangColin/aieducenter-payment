package com.aieducenter.payment.application;

import com.aieducenter.payment.application.dto.callback.RefundNotifyRequest;
import com.aieducenter.payment.application.dto.command.AuditRefundCommand;
import com.aieducenter.payment.application.dto.command.CreateRefundCommand;
import com.aieducenter.payment.application.dto.response.RefundOrderResponse;
import com.aieducenter.payment.domain.aggregate.PaymentLog;
import com.aieducenter.payment.domain.aggregate.PaymentOrder;
import com.aieducenter.payment.domain.aggregate.RefundOrder;
import com.aieducenter.payment.domain.enums.PaymentStatus;
import com.aieducenter.payment.domain.enums.RefundStatus;
import com.aieducenter.payment.domain.error.PaymentMessage;
import com.aieducenter.payment.domain.port.PaymentGatewayPort;
import com.aieducenter.payment.domain.port.response.CreateRefundResponse;
import com.aieducenter.payment.domain.port.response.QueryRefundResponse;
import com.aieducenter.payment.domain.repository.PaymentLogRepository;
import com.aieducenter.payment.domain.repository.PaymentOrderRepository;
import com.aieducenter.payment.domain.repository.RefundOrderRepository;
import com.aieducenter.payment.infrastructure.BusinessSystemNotifier;
import com.cartisan.core.exception.ApplicationException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * 退款应用服务
 *
 * <p>负责退款相关的业务编排</p>
 */
@Service
@RequiredArgsConstructor
public class RefundAppService {

    private static final Logger log = LoggerFactory.getLogger(RefundAppService.class);

    private final RefundOrderRepository refundOrderRepository;
    private final PaymentOrderRepository paymentOrderRepository;
    private final PaymentGatewayPort paymentGatewayPort;
    private final PaymentLogRepository paymentLogRepository;
    private final BusinessSystemNotifier businessSystemNotifier;
    private final TransactionTemplate transactionTemplate;

    /**
     * 创建退款订单
     */
    @Transactional
    public RefundOrderResponse createRefund(CreateRefundCommand command, String businessSystemName) {
        // 1. 查找原支付订单
        PaymentOrder paymentOrder = paymentOrderRepository.findByPaymentOrderNo(command.paymentOrderNo())
            .orElseThrow(() -> new ApplicationException(PaymentMessage.PAYMENT_ORDER_NOT_FOUND));

        // 2. 校验原订单支付成功
        if (paymentOrder.getStatus() != PaymentStatus.PAID) {
            throw new ApplicationException(PaymentMessage.ORIGINAL_PAYMENT_NOT_SUCCESS);
        }

        // 3. 创建退款订单
        RefundOrder refundOrder = new RefundOrder(
            command.businessOrderNo(),
            command.paymentOrderNo(),
            businessSystemName,
            paymentOrder.getBusinessName(),
            command.refundAmount(),
            paymentOrder.getAmount(), // 可退款金额 = 支付金额
            command.reason(),
            command.attach(),
            command.notifyUrl()
        );

        RefundOrder saved = refundOrderRepository.save(refundOrder);

        // 4. 免审：自动审核通过并发起退款
        if (!command.isNeedAudit()) {
            saved.audit(null, "SYSTEM", true, "免审自动通过");
            saved = refundOrderRepository.save(saved);
            executeRefundAfterApproval(saved);
        }

        return toResponse(saved);
    }

    /**
     * 审核退款
     */
    public RefundOrderResponse auditRefund(String refundOrderNo, AuditRefundCommand command) {
        // 事务1：审核状态更新
        RefundOrder saved = transactionTemplate.execute(status -> {
            RefundOrder refundOrder = refundOrderRepository.findByRefundOrderNo(refundOrderNo)
                .orElseThrow(() -> new ApplicationException(PaymentMessage.REFUND_ORDER_NOT_FOUND));

            refundOrder.audit(
                command.auditorId(),
                command.auditorName(),
                command.agreed(),
                command.remark()
            );

            return refundOrderRepository.save(refundOrder);
        });

        // 事务外：审核通过后发起退款
        if (command.agreed()) {
            executeRefundAfterApproval(saved);
        }

        return toResponse(saved);
    }

    /**
     * 查询退款订单（根据状态自动同步工行）
     */
    public RefundOrderResponse queryRefund(String refundOrderNo) {
        RefundOrder refundOrder = refundOrderRepository.findByRefundOrderNo(refundOrderNo)
            .orElseThrow(() -> new ApplicationException(PaymentMessage.REFUND_ORDER_NOT_FOUND));

        if (refundOrder.getStatus() == RefundStatus.APPROVED) {
            try {
                PaymentOrder paymentOrder = paymentOrderRepository
                    .findByPaymentOrderNo(refundOrder.getPaymentOrderNo())
                    .orElseThrow(() -> new ApplicationException(PaymentMessage.PAYMENT_ORDER_NOT_FOUND));
                executeRefundAndQuery(refundOrder, paymentOrder);
            } catch (Exception e) {
                log.error("Failed to execute refund in query: refundOrderNo={}", refundOrderNo, e);
            }
        } else if (refundOrder.getStatus() == RefundStatus.REFUNDING) {
            try {
                PaymentOrder paymentOrder = paymentOrderRepository
                    .findByPaymentOrderNo(refundOrder.getPaymentOrderNo())
                    .orElseThrow(() -> new ApplicationException(PaymentMessage.PAYMENT_ORDER_NOT_FOUND));
                queryAndUpdateRefundStatus(refundOrder, paymentOrder);
            } catch (Exception e) {
                log.error("Failed to query refund status: refundOrderNo={}", refundOrderNo, e);
            }
        }

        return toResponse(refundOrder);
    }

    /**
     * 处理已批准的退款订单（发起退款 + 查询 + 回调）
     * 供定时任务调用
     */
    public void processApprovedRefund(RefundOrder refundOrder, PaymentOrder paymentOrder) {
        executeRefundAndQuery(refundOrder, paymentOrder);
    }

    /**
     * 处理退款中的订单（查询 + 回调）
     * 供定时任务调用
     */
    public void processRefundingOrder(RefundOrder refundOrder, PaymentOrder paymentOrder) {
        queryAndUpdateRefundStatus(refundOrder, paymentOrder);
        notifyIfTerminal(refundOrder);
    }

    // ==================== Private Methods ====================

    private void executeRefundAfterApproval(RefundOrder refundOrder) {
        try {
            PaymentOrder paymentOrder = paymentOrderRepository
                .findByPaymentOrderNo(refundOrder.getPaymentOrderNo())
                .orElseThrow(() -> new ApplicationException(PaymentMessage.PAYMENT_ORDER_NOT_FOUND));

            executeRefundAndQuery(refundOrder, paymentOrder);
        } catch (Exception e) {
            log.error("Failed to execute refund after approval: refundOrderNo={}, error={}",
                refundOrder.getRefundOrderNo(), e.getMessage());
        }
    }

    private void executeRefundAndQuery(RefundOrder refundOrder, PaymentOrder paymentOrder) {
        // 1. 调工行退货
        CreateRefundResponse refundResponse = paymentGatewayPort.createRefund(
            refundOrder, paymentOrder.getBankOrderNo()
        );

        // 2. 记录退款请求日志
        saveRefundRequestLog(refundOrder, refundResponse);

        if (!refundResponse.success()) {
            log.warn("Refund request failed: refundOrderNo={}, returnCode={}, returnMsg={}",
                refundOrder.getRefundOrderNo(), refundResponse.returnCode(), refundResponse.returnMsg());
            return; // 保持 APPROVED，等定时任务重试
        }

        // 3. 更新为 REFUNDING（独立事务）
        transactionTemplate.executeWithoutResult(status -> {
            refundOrder.startRefund(refundResponse.bankRefundNo());
            refundOrderRepository.save(refundOrder);
        });

        // 4. 等 2 秒
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }

        // 5. 查询退款状态
        queryAndUpdateRefundStatus(refundOrder, paymentOrder);

        // 6. 如果达到终态，回调业务系统
        notifyIfTerminal(refundOrder);
    }

    private void queryAndUpdateRefundStatus(RefundOrder refundOrder, PaymentOrder paymentOrder) {
        QueryRefundResponse queryResponse = paymentGatewayPort.queryRefund(
            refundOrder.getRefundOrderNo(),
            refundOrder.getPaymentOrderNo(),
            paymentOrder.getBankOrderNo(),
            refundOrder.getBankRefundNo()
        );

        saveRefundQueryLog(refundOrder, queryResponse);

        if (!queryResponse.success()) {
            log.warn("Refund query failed: refundOrderNo={}, returnCode={}, returnMsg={}",
                refundOrder.getRefundOrderNo(), queryResponse.returnCode(), queryResponse.returnMsg());
            return;
        }

        // 根据查询结果更新状态（独立事务）
        if (queryResponse.refundStatus() == RefundStatus.SUCCESS) {
            transactionTemplate.executeWithoutResult(status -> {
                refundOrder.refundSuccess(
                    queryResponse.bankRefundNo() != null ? queryResponse.bankRefundNo() : refundOrder.getBankRefundNo()
                );
                refundOrderRepository.save(refundOrder);
            });
        } else if (queryResponse.refundStatus() == RefundStatus.FAILED) {
            transactionTemplate.executeWithoutResult(status -> {
                refundOrder.refundFailed(queryResponse.returnMsg());
                refundOrderRepository.save(refundOrder);
            });
        }
        // REFUNDING 状态不变
    }

    private void saveRefundRequestLog(RefundOrder refundOrder, CreateRefundResponse refundResponse) {
        PaymentLog logEntry = new PaymentLog(
            refundOrder.getPaymentOrderNo(),
            refundOrder.getRefundOrderNo(),
            "REFUND_REQUEST",
            "ICBC",
            "aggregatepay/b2c/online/merrefund",
            null,
            null,
            null,
            200,
            refundResponse.returnCode(),
            refundResponse.returnMsg(),
            refundResponse.executionTime(),
            refundResponse.success(),
            refundResponse.success() ? null : "银行调用失败"
        );
        paymentLogRepository.save(logEntry);
    }

    private void saveRefundQueryLog(RefundOrder refundOrder, QueryRefundResponse queryResponse) {
        PaymentLog logEntry = new PaymentLog(
            refundOrder.getPaymentOrderNo(),
            refundOrder.getRefundOrderNo(),
            "REFUND_QUERY",
            "ICBC",
            "aggregatepay/b2c/online/refundqry",
            null,
            null,
            com.alibaba.fastjson2.JSON.toJSONString(queryResponse),
            200,
            queryResponse.returnCode(),
            queryResponse.returnMsg(),
            queryResponse.executionTime(),
            queryResponse.success(),
            null
        );
        paymentLogRepository.save(logEntry);
    }

    private void notifyIfTerminal(RefundOrder refundOrder) {
        if (refundOrder.getStatus().isTerminal() && refundOrder.getStatus() != RefundStatus.REJECTED) {
            RefundNotifyRequest request = new RefundNotifyRequest(
                refundOrder.getRefundOrderNo(),
                refundOrder.getBusinessOrderNo(),
                refundOrder.getPaymentOrderNo(),
                refundOrder.getStatus().getCode(),
                refundOrder.getStatus().getName(),
                refundOrder.getRefundAmount(),
                refundOrder.getBankRefundNo(),
                refundOrder.getRefundedAt() != null ? refundOrder.getRefundedAt().toString() : null,
                refundOrder.getAttach()
            );
            businessSystemNotifier.notify(refundOrder.getNotifyUrl(), request);
        }
    }

    private RefundOrderResponse toResponse(RefundOrder order) {
        return new RefundOrderResponse(
            order.getId(),
            order.getBusinessOrderNo(),
            order.getRefundOrderNo(),
            order.getPaymentOrderNo(),
            order.getBusinessSystemName(),
            order.getBusinessName(),
            order.getStatus().getCode(),
            order.getStatus().getName(),
            order.getRefundAmount(),
            order.getRefundableAmount(),
            order.getReason(),
            order.getAuditorName(),
            order.getAuditAgreed(),
            order.getAuditRemark(),
            order.getCreatedAt(),
            order.getApprovedAt(),
            order.getRefundedAt(),
            order.getFailedAt(),
            order.getBankRefundNo(),
            order.getNotifyUrl()
        );
    }
}
