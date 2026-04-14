package com.aieducenter.payment.domain.port;

import com.aieducenter.payment.domain.aggregate.PaymentOrder;
import com.aieducenter.payment.domain.aggregate.RefundOrder;
import com.aieducenter.payment.domain.port.response.CreatePaymentResponse;
import com.aieducenter.payment.domain.port.response.CreateRefundResponse;
import com.aieducenter.payment.domain.port.response.QueryPaymentResponse;
import com.aieducenter.payment.domain.port.response.QueryRefundResponse;
import com.cartisan.core.stereotype.Port;
import com.cartisan.core.stereotype.PortType;

/**
 * 支付网关端口接口
 *
 * <p>南向接口 - 定义领域层与银行网关交互的抽象</p>
 * <p>由基础设施层提供具体实现（如 IcbcPaymentGatewayAdapter）</p>
 *
 * @see com.aieducenter.payment.infrastructure.icbc.IcbcPaymentGatewayAdapter
 */
@Port(PortType.CLIENT)
public interface PaymentGatewayPort {

    /**
     * 创建支付
     *
     * @param paymentOrder 支付订单聚合根
     * @return 创建支付响应
     */
    CreatePaymentResponse createPayment(PaymentOrder paymentOrder);

    /**
     * 查询支付
     *
     * @param paymentOrderNo 支付订单号
     * @param bankOrderNo 银行订单号
     * @return 查询支付响应
     */
    QueryPaymentResponse queryPayment(String paymentOrderNo, String bankOrderNo);

    /**
     * 创建退款
     *
     * @param refundOrder 退款订单聚合根
     * @param originalBankOrderNo 原支付订单的银行订单号
     * @return 创建退款响应
     */
    CreateRefundResponse createRefund(RefundOrder refundOrder, String originalBankOrderNo);

    /**
     * 查询退款
     *
     * @param refundOrderNo 退款订单号 (对应 outtrx_serial_no)
     * @param paymentOrderNo 原支付订单号 (对应 out_trade_no)
     * @param bankOrderNo 原工行订单号 (对应 order_id)
     * @param bankRefundNo 银行退款流水号 (对应 intrx_serial_no，可为空)
     * @return 查询退款响应
     */
    QueryRefundResponse queryRefund(String refundOrderNo, String paymentOrderNo, String bankOrderNo, String bankRefundNo);
}
