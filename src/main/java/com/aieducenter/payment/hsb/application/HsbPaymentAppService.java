package com.aieducenter.payment.hsb.application;

import com.aieducenter.payment.hsb.application.dto.command.CreateHsbPaymentCommand;
import com.aieducenter.payment.hsb.application.dto.response.HsbPaymentOrderResponse;
import com.aieducenter.payment.hsb.application.mapper.HsbPaymentOrderMapper;
import com.aieducenter.payment.hsb.domain.aggregate.HsbPaymentLog;
import com.aieducenter.payment.hsb.domain.aggregate.HsbPaymentOrder;
import com.aieducenter.payment.hsb.domain.aggregate.HsbSubOrder;
import com.aieducenter.payment.hsb.domain.enums.HsbPaymentStatus;
import com.aieducenter.payment.hsb.domain.port.HsbPaymentGatewayPort;
import com.aieducenter.payment.hsb.domain.port.response.CreateHsbPaymentResponse;
import com.aieducenter.payment.hsb.domain.port.response.QueryHsbPaymentResponse;
import com.aieducenter.payment.hsb.domain.repository.HsbPaymentLogRepository;
import com.aieducenter.payment.hsb.domain.repository.HsbPaymentOrderRepository;
import com.aieducenter.payment.hsb.infrastructure.HsbConfig;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HsbPaymentAppService {

    private static final Logger log = LoggerFactory.getLogger(HsbPaymentAppService.class);

    private final HsbPaymentOrderRepository paymentOrderRepository;
    private final HsbPaymentLogRepository paymentLogRepository;
    private final HsbPaymentGatewayPort gatewayPort;
    private final HsbConfig hsbConfig;
    private final TransactionTemplate transactionTemplate;

    public HsbPaymentOrderResponse createPayment(CreateHsbPaymentCommand command, String businessSystemName) {
        // 事务1：创建并保存订单（PENDING 状态）
        HsbPaymentOrder order = transactionTemplate.execute(status -> {
            List<HsbSubOrder> subOrders = command.subOrders().stream()
                .map(sub -> new HsbSubOrder(
                    command.businessMainOrderNo(),
                    sub.businessSubOrderNo(),
                    sub.mktMrchId(),
                    sub.orderAmount(),
                    sub.txnAmount()
                ))
                .toList();

            HsbPaymentOrder paymentOrder = new HsbPaymentOrder(
                command.businessMainOrderNo(),
                businessSystemName,
                command.businessName(),
                hsbConfig.getMktId(),
                command.paymentMethod(),
                command.orderType(),
                command.currency(),
                command.totalAmount(),
                command.txnTotalAmount(),
                command.feeBearerId(),
                command.expiredSeconds(),
                command.notifyUrl(),
                command.attach(),
                command.confirmReceiptDate(),
                null,
                subOrders
            );

            return paymentOrderRepository.save(paymentOrder);
        });

        // 调用建行网关（无事务，失败不影响订单记录）
        CreateHsbPaymentResponse gatewayResponse = gatewayPort.createPayment(order, order.getSubOrders());

        saveLog(order.getPaymentOrderNo(), null, "PAYMENT_REQUEST", "gatherPlaceorder",
            gatewayResponse.requestParams(), gatewayResponse.responseParams(),
            gatewayResponse.success(), gatewayResponse.returnCode(), gatewayResponse.returnMsg(),
            gatewayResponse.executionTime(), gatewayResponse.success() ? null : "建行网关调用失败");

        if (!gatewayResponse.success()) {
            throw new RuntimeException("建行网关调用失败: " + gatewayResponse.returnMsg());
        }

        // 事务2：更新支付结果和子订单ID
        if (gatewayResponse.payUrl() != null || gatewayResponse.primOrderNo() != null
            || (gatewayResponse.subOrderIds() != null && !gatewayResponse.subOrderIds().isEmpty())) {
            transactionTemplate.executeWithoutResult(status -> {
                HsbPaymentOrder toUpdate = paymentOrderRepository.findByPaymentOrderNo(order.getPaymentOrderNo())
                    .orElseThrow();
                toUpdate.setPaymentResult(null, gatewayResponse.payUrl(), gatewayResponse.payQrCode(), gatewayResponse.primOrderNo());
                if (gatewayResponse.subOrderIds() != null && !gatewayResponse.subOrderIds().isEmpty()) {
                    toUpdate.updateSubOrderIds(gatewayResponse.subOrderIds());
                }
                paymentOrderRepository.save(toUpdate);
                order.setPaymentResult(null, gatewayResponse.payUrl(), gatewayResponse.payQrCode(), gatewayResponse.primOrderNo());
                if (gatewayResponse.subOrderIds() != null && !gatewayResponse.subOrderIds().isEmpty()) {
                    order.updateSubOrderIds(gatewayResponse.subOrderIds());
                }
            });
        }

        return HsbPaymentOrderMapper.convert(order);
    }

    @Transactional(readOnly = true)
    public HsbPaymentOrderResponse getPayment(String paymentOrderNo) {
        HsbPaymentOrder order = paymentOrderRepository.findByPaymentOrderNo(paymentOrderNo)
            .orElseThrow(() -> new IllegalArgumentException("惠市宝支付订单不存在"));

        if (order.getStatus() == HsbPaymentStatus.PENDING && order.getExpiredAt() != null
            && java.time.LocalDateTime.now().isAfter(order.getExpiredAt())) {
            order.markAsExpired();
            paymentOrderRepository.save(order);
        }

        return HsbPaymentOrderMapper.convert(order);
    }

    @Transactional
    public HsbPaymentOrderResponse queryPaymentStatus(String paymentOrderNo) {
        HsbPaymentOrder order = paymentOrderRepository.findByPaymentOrderNo(paymentOrderNo)
            .orElseThrow(() -> new IllegalArgumentException("惠市宝支付订单不存在"));

        if (order.getStatus() != HsbPaymentStatus.PENDING) {
            return HsbPaymentOrderMapper.convert(order);
        }

        QueryHsbPaymentResponse queryResponse = gatewayPort.queryPayment(
            order.getMktId(), order.getBusinessMainOrderNo(), order.getPyTrnNo());

        saveLog(paymentOrderNo, null, "PAYMENT_QUERY", "gatherEnquireOrder",
            null, queryResponse.responseParams(),
            queryResponse.success(), queryResponse.returnCode(), queryResponse.returnMsg(),
            queryResponse.executionTime(), null);

        if (queryResponse.success() && queryResponse.paymentStatus() != null) {
            if (queryResponse.paymentStatus() == HsbPaymentStatus.PAID) {
                order.markAsPaid(queryResponse.pyTrnNo(), queryResponse.actualAmount());
                paymentOrderRepository.save(order);
            } else if (queryResponse.paymentStatus() == HsbPaymentStatus.FAILED) {
                order.markAsFailed();
                paymentOrderRepository.save(order);
            } else if (queryResponse.paymentStatus() == HsbPaymentStatus.EXPIRED) {
                order.markAsExpired();
                paymentOrderRepository.save(order);
            }
        }

        return HsbPaymentOrderMapper.convert(order);
    }

    private void saveLog(String paymentOrderNo, String refundOrderNo, String logType,
                         String bankInterface, String requestParams, String responseParams,
                         Boolean success, String returnCode, String returnMsg,
                         Long executionTime, String errorMessage) {
        try {
            paymentLogRepository.save(new HsbPaymentLog(
                paymentOrderNo, refundOrderNo, logType, bankInterface,
                null, requestParams, responseParams, 200,
                returnCode, returnMsg, executionTime, success, errorMessage
            ));
        } catch (Exception e) {
            log.warn("Failed to save HSB payment log: {}", e.getMessage());
        }
    }
}
