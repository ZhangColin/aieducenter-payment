package com.aieducenter.payment.hsb.domain.port;

import com.aieducenter.payment.hsb.domain.aggregate.HsbPaymentOrder;
import com.aieducenter.payment.hsb.domain.aggregate.HsbRefundOrder;
import com.aieducenter.payment.hsb.domain.aggregate.HsbRefundSubOrder;
import com.aieducenter.payment.hsb.domain.aggregate.HsbSubOrder;
import com.aieducenter.payment.hsb.domain.port.response.*;
import com.cartisan.core.stereotype.Port;
import com.cartisan.core.stereotype.PortType;

import java.util.List;

@Port(PortType.CLIENT)
public interface HsbPaymentGatewayPort {
    CreateHsbPaymentResponse createPayment(HsbPaymentOrder order, List<HsbSubOrder> subOrders);
    QueryHsbPaymentResponse queryPayment(String mktId, String mainOrderNo, String pyTrnNo);
    CreateHsbRefundResponse createRefund(HsbRefundOrder order, String pyTrnNo, List<HsbRefundSubOrder> subOrders);
    QueryHsbRefundResponse queryRefund(String mktId, String custRfndTrcno, String rfndTrcno);
    ConfirmSettlementResponse confirmSettlement(String mktId, String primOrderNo, String subOrderId);
}
