package com.aieducenter.payment.hsb.application;

import com.aieducenter.payment.hsb.application.dto.command.ConfirmHsbSettlementCommand;
import com.aieducenter.payment.hsb.domain.aggregate.HsbPaymentLog;
import com.aieducenter.payment.hsb.domain.aggregate.HsbPaymentOrder;
import com.aieducenter.payment.hsb.domain.aggregate.HsbSettlementConfirm;
import com.aieducenter.payment.hsb.domain.aggregate.HsbSubOrder;
import com.aieducenter.payment.hsb.domain.enums.HsbPaymentStatus;
import com.aieducenter.payment.hsb.domain.port.HsbPaymentGatewayPort;
import com.aieducenter.payment.hsb.domain.port.response.ConfirmSettlementResponse;
import com.aieducenter.payment.hsb.domain.repository.HsbPaymentLogRepository;
import com.aieducenter.payment.hsb.domain.repository.HsbPaymentOrderRepository;
import com.aieducenter.payment.hsb.domain.repository.HsbSettlementConfirmRepository;
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
public class HsbSettlementAppService {

    private static final Logger log = LoggerFactory.getLogger(HsbSettlementAppService.class);

    private final HsbPaymentOrderRepository paymentOrderRepository;
    private final HsbSettlementConfirmRepository settlementConfirmRepository;
    private final HsbPaymentLogRepository paymentLogRepository;
    private final HsbPaymentGatewayPort gatewayPort;

    @Transactional
    public void confirmSettlement(ConfirmHsbSettlementCommand command) {
        HsbPaymentOrder order = paymentOrderRepository.findByPaymentOrderNo(command.paymentOrderNo())
            .orElseThrow(() -> new IllegalArgumentException("惠市宝支付订单不存在"));

        if (order.getStatus() != HsbPaymentStatus.PAID) {
            throw new IllegalStateException("支付订单未支付，无法确认分账");
        }

        if (order.getPrimOrderNo() == null) {
            throw new IllegalStateException("支付订单缺少建行主订单编号");
        }

        Map<String, HsbSubOrder> subOrderMap = order.getSubOrders().stream()
            .collect(Collectors.toMap(HsbSubOrder::getBusinessSubOrderNo, Function.identity()));

        List<String> subOrderIds = new ArrayList<>();
        for (String bizSubNo : command.businessSubOrderNos()) {
            HsbSubOrder subOrder = subOrderMap.get(bizSubNo);
            if (subOrder == null) {
                throw new IllegalArgumentException("子订单不存在: " + bizSubNo);
            }
            if (subOrder.getSubOrderId() == null) {
                throw new IllegalStateException("子订单缺少建行子订单编号: " + bizSubNo);
            }
            if (Boolean.TRUE.equals(subOrder.getConfirmed())) {
                throw new IllegalStateException("子订单已确认分账: " + bizSubNo);
            }
            subOrderIds.add(subOrder.getSubOrderId());
        }

        String subOrderIdParam = String.join(",", subOrderIds);

        ConfirmSettlementResponse response = gatewayPort.confirmSettlement(
            order.getMktId(), order.getPrimOrderNo(), subOrderIdParam);

        try {
            paymentLogRepository.save(new HsbPaymentLog(
                order.getPaymentOrderNo(), null, "SETTLEMENT_CONFIRM", "mergeNoticeArrival",
                null, response.requestParams(), response.responseParams(),
                200, response.returnCode(), response.returnMsg(),
                response.executionTime(), response.success(),
                response.success() ? null : "建行确认分账调用失败"
            ));
        } catch (Exception e) {
            log.warn("Failed to save HSB settlement log: {}", e.getMessage());
        }

        HsbSettlementConfirm confirm = new HsbSettlementConfirm(
            order.getId(),
            order.getPaymentOrderNo(),
            order.getBusinessMainOrderNo(),
            command.businessSubOrderNos(),
            subOrderIds
        );

        if (response.success()) {
            confirm.markAsConfirmed();
            for (String bizSubNo : command.businessSubOrderNos()) {
                HsbSubOrder subOrder = subOrderMap.get(bizSubNo);
                subOrder.markAsConfirmed();
            }
        } else {
            confirm.markAsFailed();
            throw new RuntimeException("建行确认分账失败: " + response.returnMsg());
        }

        settlementConfirmRepository.save(confirm);
    }
}
