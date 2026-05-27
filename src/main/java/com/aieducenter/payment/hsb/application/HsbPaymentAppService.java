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

import java.util.List;

@Service
@RequiredArgsConstructor
public class HsbPaymentAppService {

    private static final Logger log = LoggerFactory.getLogger(HsbPaymentAppService.class);

    private final HsbPaymentOrderRepository paymentOrderRepository;
    private final HsbPaymentLogRepository paymentLogRepository;
    private final HsbPaymentGatewayPort gatewayPort;
    private final HsbConfig hsbConfig;

    @Transactional
    public HsbPaymentOrderResponse createPayment(CreateHsbPaymentCommand command, String businessSystemName) {
        List<HsbSubOrder> subOrders = command.subOrders().stream()
            .map(sub -> new HsbSubOrder(
                command.businessMainOrderNo(),
                sub.businessSubOrderNo(),
                sub.mktMrchId(),
                sub.orderAmount(),
                sub.txnAmount()
            ))
            .toList();

        HsbPaymentOrder order = new HsbPaymentOrder(
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
            subOrders
        );

        HsbPaymentOrder saved = paymentOrderRepository.save(order);

        CreateHsbPaymentResponse gatewayResponse = gatewayPort.createPayment(order, subOrders);

        saveLog(order.getPaymentOrderNo(), null, "PAYMENT_REQUEST", "gatherPlaceorder",
            gatewayResponse.requestParams(), gatewayResponse.responseParams(),
            gatewayResponse.success(), gatewayResponse.returnCode(), gatewayResponse.returnMsg(),
            gatewayResponse.executionTime(), gatewayResponse.success() ? null : "建行网关调用失败");

        if (!gatewayResponse.success()) {
            throw new RuntimeException("建行网关调用失败: " + gatewayResponse.returnMsg());
        }

        if (gatewayResponse.payUrl() != null || gatewayResponse.primOrderNo() != null) {
            saved.setPaymentResult(gatewayResponse.payUrl(), gatewayResponse.payQrCode(), gatewayResponse.primOrderNo());
            paymentOrderRepository.save(saved);
        }

        return HsbPaymentOrderMapper.convert(saved);
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
