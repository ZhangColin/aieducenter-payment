package com.aieducenter.payment.hsb.application;

import com.aieducenter.payment.hsb.application.dto.command.CreateHsbRefundCommand;
import com.aieducenter.payment.hsb.application.dto.response.HsbRefundOrderResponse;
import com.aieducenter.payment.hsb.application.mapper.HsbPaymentOrderMapper;
import com.aieducenter.payment.hsb.domain.aggregate.HsbPaymentLog;
import com.aieducenter.payment.hsb.domain.aggregate.HsbPaymentOrder;
import com.aieducenter.payment.hsb.domain.aggregate.HsbRefundOrder;
import com.aieducenter.payment.hsb.domain.aggregate.HsbRefundSubOrder;
import com.aieducenter.payment.hsb.domain.aggregate.HsbSubOrder;
import com.aieducenter.payment.hsb.domain.enums.HsbPaymentStatus;
import com.aieducenter.payment.hsb.domain.enums.HsbRefundStatus;
import com.aieducenter.payment.hsb.domain.port.HsbPaymentGatewayPort;
import com.aieducenter.payment.hsb.domain.port.response.CreateHsbRefundResponse;
import com.aieducenter.payment.hsb.domain.port.response.QueryHsbRefundResponse;
import com.aieducenter.payment.hsb.domain.repository.HsbPaymentLogRepository;
import com.aieducenter.payment.hsb.domain.repository.HsbPaymentOrderRepository;
import com.aieducenter.payment.hsb.domain.repository.HsbRefundOrderRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HsbRefundAppService {

    private static final Logger log = LoggerFactory.getLogger(HsbRefundAppService.class);

    private final HsbPaymentOrderRepository paymentOrderRepository;
    private final HsbRefundOrderRepository refundOrderRepository;
    private final HsbPaymentLogRepository paymentLogRepository;
    private final HsbPaymentGatewayPort gatewayPort;

    @Transactional
    public HsbRefundOrderResponse createRefund(CreateHsbRefundCommand command, String businessSystemName) {
        HsbPaymentOrder paymentOrder = paymentOrderRepository.findByPaymentOrderNo(command.paymentOrderNo())
            .orElseThrow(() -> new IllegalArgumentException("惠市宝支付订单不存在"));

        if (paymentOrder.getStatus() != HsbPaymentStatus.PAID) {
            throw new IllegalStateException("原支付订单未支付，无法退款");
        }

        Map<String, HsbSubOrder> subOrderMap = paymentOrder.getSubOrders().stream()
            .collect(Collectors.toMap(HsbSubOrder::getBusinessSubOrderNo, Function.identity()));

        List<HsbRefundSubOrder> refundSubOrders = new ArrayList<>();
        if (command.subOrders() != null) {
            for (var sub : command.subOrders()) {
                HsbSubOrder originalSub = subOrderMap.get(sub.businessSubOrderNo());
                String subOrderId = originalSub != null ? originalSub.getSubOrderId() : null;
                refundSubOrders.add(new HsbRefundSubOrder(
                    paymentOrder.getBusinessMainOrderNo(),
                    sub.businessSubOrderNo(),
                    subOrderId,
                    sub.refundAmount()
                ));
            }
        }

        HsbRefundOrder refundOrder = new HsbRefundOrder(
            paymentOrder.getId(),
            paymentOrder.getPaymentOrderNo(),
            command.businessMainOrderNo(),
            businessSystemName,
            null,
            command.refundType(),
            command.refundAmount(),
            command.reason(),
            command.notifyUrl(),
            command.attach(),
            refundSubOrders
        );

        HsbRefundOrder saved = refundOrderRepository.save(refundOrder);

        CreateHsbRefundResponse response = gatewayPort.createRefund(
            saved, paymentOrder.getPyTrnNo(), refundSubOrders);

        try {
            paymentLogRepository.save(new HsbPaymentLog(
                paymentOrder.getPaymentOrderNo(), saved.getRefundOrderNo(),
                "REFUND_REQUEST", "refundOrder",
                null, response.requestParams(), response.responseParams(),
                200, response.returnCode(), response.returnMsg(),
                response.executionTime(), response.success(),
                response.success() ? null : "建行退款调用失败"
            ));
        } catch (Exception e) {
            log.warn("Failed to save HSB refund log: {}", e.getMessage());
        }

        if (response.success()) {
            if (response.refundStatus() == HsbRefundStatus.SUCCESS) {
                saved.markAsSuccess(response.superRefundNo());
            } else if (response.refundStatus() == HsbRefundStatus.REFUNDING) {
                saved.markAsRefunding();
            }
        } else {
            saved.markAsFailed();
        }
        refundOrderRepository.save(saved);

        return HsbPaymentOrderMapper.convert(saved);
    }

    @Transactional(readOnly = true)
    public HsbRefundOrderResponse getRefund(String refundOrderNo) {
        HsbRefundOrder order = refundOrderRepository.findByRefundOrderNo(refundOrderNo)
            .orElseThrow(() -> new IllegalArgumentException("惠市宝退款订单不存在"));
        return HsbPaymentOrderMapper.convert(order);
    }

    @Transactional
    public HsbRefundOrderResponse queryRefundStatus(String refundOrderNo) {
        HsbRefundOrder order = refundOrderRepository.findByRefundOrderNo(refundOrderNo)
            .orElseThrow(() -> new IllegalArgumentException("惠市宝退款订单不存在"));

        if (order.getStatus().isTerminal()) {
            return HsbPaymentOrderMapper.convert(order);
        }

        HsbPaymentOrder paymentOrder = paymentOrderRepository.findById(order.getPaymentOrderId())
            .orElseThrow(() -> new IllegalArgumentException("关联的支付订单不存在"));

        QueryHsbRefundResponse response = gatewayPort.queryRefund(
            paymentOrder.getMktId(), order.getRefundOrderNo(), null);

        try {
            paymentLogRepository.save(new HsbPaymentLog(
                order.getPaymentOrderNo(), order.getRefundOrderNo(),
                "REFUND_QUERY", "enquireRefundOrder",
                null, null, response.responseParams(),
                200, response.returnCode(), response.returnMsg(),
                response.executionTime(), response.success(), null
            ));
        } catch (Exception e) {
            log.warn("Failed to save HSB refund query log: {}", e.getMessage());
        }

        if (response.success() && response.refundStatus() != null) {
            if (response.refundStatus() == HsbRefundStatus.SUCCESS) {
                order.markAsSuccess(response.superRefundNo());
            } else if (response.refundStatus() == HsbRefundStatus.FAILED) {
                order.markAsFailed();
            } else {
                order.markAsRefunding();
            }
            refundOrderRepository.save(order);
        }

        return HsbPaymentOrderMapper.convert(order);
    }
}
