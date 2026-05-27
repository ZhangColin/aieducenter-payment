package com.aieducenter.payment.hsb.application.mapper;

import com.aieducenter.payment.hsb.application.dto.response.HsbPaymentOrderResponse;
import com.aieducenter.payment.hsb.application.dto.response.HsbRefundOrderResponse;
import com.aieducenter.payment.hsb.domain.aggregate.HsbPaymentOrder;
import com.aieducenter.payment.hsb.domain.aggregate.HsbRefundOrder;
import com.aieducenter.payment.hsb.domain.aggregate.HsbRefundSubOrder;
import com.aieducenter.payment.hsb.domain.aggregate.HsbSubOrder;

public class HsbPaymentOrderMapper {

    public static HsbPaymentOrderResponse convert(HsbPaymentOrder order) {
        return HsbPaymentOrderResponse.builder()
            .paymentOrderNo(order.getPaymentOrderNo())
            .businessMainOrderNo(order.getBusinessMainOrderNo())
            .businessSystemName(order.getBusinessSystemName())
            .businessName(order.getBusinessName())
            .status(order.getStatus().getName())
            .mktId(order.getMktId())
            .paymentMethod(order.getPaymentMethod())
            .orderType(order.getOrderType())
            .currency(order.getCurrency())
            .totalAmount(order.getTotalAmount())
            .txnTotalAmount(order.getTxnTotalAmount())
            .feeBearerId(order.getFeeBearerId())
            .payUrl(order.getPayUrl())
            .payQrCode(order.getPayQrCode())
            .primOrderNo(order.getPrimOrderNo())
            .pyTrnNo(order.getPyTrnNo())
            .actualAmount(order.getActualAmount())
            .paidAt(order.getPaidAt())
            .failedAt(order.getFailedAt())
            .expiredAt(order.getExpiredAt())
            .notifyUrl(order.getNotifyUrl())
            .attach(order.getAttach())
            .subOrders(order.getSubOrders().stream()
                .map(HsbPaymentOrderMapper::convertSubOrder)
                .toList())
            .build();
    }

    private static HsbPaymentOrderResponse.HsbSubOrderResponse convertSubOrder(HsbSubOrder sub) {
        return HsbPaymentOrderResponse.HsbSubOrderResponse.builder()
            .businessSubOrderNo(sub.getBusinessSubOrderNo())
            .mktMrchId(sub.getMktMrchId())
            .orderAmount(sub.getOrderAmount())
            .txnAmount(sub.getTxnAmount())
            .subOrderId(sub.getSubOrderId())
            .confirmed(sub.getConfirmed())
            .confirmedAt(sub.getConfirmedAt())
            .build();
    }

    public static HsbRefundOrderResponse convert(HsbRefundOrder order) {
        return HsbRefundOrderResponse.builder()
            .refundOrderNo(order.getRefundOrderNo())
            .paymentOrderNo(order.getPaymentOrderNo())
            .businessMainOrderNo(order.getBusinessMainOrderNo())
            .businessSystemName(order.getBusinessSystemName())
            .businessName(order.getBusinessName())
            .refundType(order.getRefundType())
            .status(order.getStatus().getName())
            .refundAmount(order.getRefundAmount())
            .reason(order.getReason())
            .superRefundNo(order.getSuperRefundNo())
            .refundedAt(order.getRefundedAt())
            .failedAt(order.getFailedAt())
            .notifyUrl(order.getNotifyUrl())
            .attach(order.getAttach())
            .subOrders(order.getSubOrders().stream()
                .map(HsbPaymentOrderMapper::convertRefundSubOrder)
                .toList())
            .build();
    }

    private static HsbRefundOrderResponse.HsbRefundSubOrderResponse convertRefundSubOrder(HsbRefundSubOrder sub) {
        return HsbRefundOrderResponse.HsbRefundSubOrderResponse.builder()
            .businessSubOrderNo(sub.getBusinessSubOrderNo())
            .subOrderId(sub.getSubOrderId())
            .refundAmount(sub.getRefundAmount())
            .build();
    }
}
