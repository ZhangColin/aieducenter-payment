package com.aieducenter.payment.infrastructure.icbc;

import com.aieducenter.payment.domain.aggregate.PaymentOrder;
import com.aieducenter.payment.domain.aggregate.RefundOrder;
import com.aieducenter.payment.domain.enums.PaymentChannel;
import com.aieducenter.payment.domain.enums.PaymentStatus;
import com.aieducenter.payment.domain.enums.RefundStatus;
import com.aieducenter.payment.domain.port.PaymentGatewayPort;
import com.aieducenter.payment.domain.port.response.*;
import com.cartisan.core.stereotype.Adapter;
import com.cartisan.core.stereotype.PortType;
import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.request.CardbusinessQrcodeConsumptionRequestV1;
import com.icbc.api.request.CardbusinessQrcodeConsumptionRequestV1.CardbusinessQrcodeConsumptionRequestV1Biz;
import com.icbc.api.response.CardbusinessQrcodeConsumptionResponseV1;
import com.icbc.api.request.CardbusinessAggregatepayB2cOnlineOrderqryRequestV1;
import com.icbc.api.request.CardbusinessAggregatepayB2cOnlineOrderqryRequestV1.CardbusinessAggregatepayB2cOnlineOrderqryRequestV1Biz;
import com.icbc.api.response.CardbusinessAggregatepayB2cOnlineOrderqryResponseV1;
import com.icbc.api.request.CardbusinessAggregatepayB2cOnlineMerrefundRequestV1;
import com.icbc.api.request.CardbusinessAggregatepayB2cOnlineMerrefundRequestV1.CardbusinessAggregatepayB2cOnlineMerrefundRequestV1Biz;
import com.icbc.api.response.CardbusinessAggregatepayB2cOnlineMerrefundResponseV1;
import com.icbc.api.request.CardbusinessAggregatepayB2cOnlineRefundqryRequestV1;
import com.icbc.api.request.CardbusinessAggregatepayB2cOnlineRefundqryRequestV1.CardbusinessAggregatepayB2cOnlineRefundqryRequestV1Biz;
import com.icbc.api.response.CardbusinessAggregatepayB2cOnlineRefundqryResponseV1;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 工行支付网关适配器
 *
 * <p>实现 PaymentGatewayPort 接口，调用工行 SDK 进行支付、退款等操作</p>
 */
@Component("icbcPaymentGateway")
@Adapter(PortType.CLIENT)
@RequiredArgsConstructor
public class IcbcPaymentGatewayAdapter implements PaymentGatewayPort {
    private static final Logger log = LoggerFactory.getLogger(IcbcPaymentGatewayAdapter.class);

    private final IcbcConfig icbcConfig;
    private final IcbcClientFactory clientFactory;

    @Override
    public CreatePaymentResponse createPayment(PaymentOrder paymentOrder) {
        DefaultIcbcClient client = clientFactory.createClient();

        CardbusinessQrcodeConsumptionRequestV1 request = new CardbusinessQrcodeConsumptionRequestV1();
        request.setServiceUrl(icbcConfig.getPaymentUrl());

        CardbusinessQrcodeConsumptionRequestV1Biz bizContent = new CardbusinessQrcodeConsumptionRequestV1Biz();

        // 设置业务参数
        bizContent.setOut_trade_no(paymentOrder.getPaymentOrderNo());
        bizContent.setMer_id(icbcConfig.getMerId());
        bizContent.setMer_prtcl_no(icbcConfig.getMerPrtclNo());
        bizContent.setAccess_type("4"); // H5
        bizContent.setCur_type("001"); // 工行只支持人民币，固定传001
        bizContent.setAmount(paymentOrder.getAmount().toString());
        bizContent.setIcbc_appid(icbcConfig.getAppId());
        bizContent.setMer_url(icbcConfig.getNotifyBaseUrl() + "/api/v1/payment/callbacks/icbc");
        bizContent.setExpire_time(paymentOrder.getExpiredSeconds().toString());
        bizContent.setNotify_type("HS");
        bizContent.setResult_type("0");
        bizContent.setOrder_date(paymentOrder.getCreatedAt().format(
            java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        bizContent.setGoods_name(paymentOrder.getSubject());
        bizContent.setBody(paymentOrder.getBody());
        bizContent.setInstallment_times("1");

        request.setBizContent(bizContent);

        // 记录请求参数（用于日志）
        String requestParams = com.alibaba.fastjson2.JSON.toJSONString(bizContent);

        long startTime = System.currentTimeMillis();
        CardbusinessQrcodeConsumptionResponseV1 response;
        try {
            response = client.execute(request, clientFactory.generateMsgId());
        } catch (Exception e) {
            log.error("ICBC payment request failed", e);
            return new CreatePaymentResponse(
                false,
                "SYSTEM_ERROR",
                e.getMessage(),
                null,
                null,
                System.currentTimeMillis() - startTime,
                requestParams,
                null
            );
        }
        long executionTime = System.currentTimeMillis() - startTime;

        // 解析响应
        boolean success = response.getReturnCode() == 0;
        String responseBody = com.alibaba.fastjson2.JSON.toJSONString(response);

        return new CreatePaymentResponse(
            success,
            String.valueOf(response.getReturnCode()),
            response.getReturnMsg(),
            success ? response.getCodeUrl() : null,
            success ? paymentOrder.getPaymentOrderNo() : null, // 使用支付订单号作为临时银行订单号
            executionTime,
            requestParams,
            responseBody
        );
    }

    @Override
    public QueryPaymentResponse queryPayment(String paymentOrderNo, String bankOrderNo) {
        DefaultIcbcClient client = clientFactory.createClient();

        CardbusinessAggregatepayB2cOnlineOrderqryRequestV1 request =
            new CardbusinessAggregatepayB2cOnlineOrderqryRequestV1();
        request.setServiceUrl(icbcConfig.getPaymentQueryUrl());

        CardbusinessAggregatepayB2cOnlineOrderqryRequestV1Biz bizContent =
            new CardbusinessAggregatepayB2cOnlineOrderqryRequestV1Biz();

        bizContent.setMer_id(icbcConfig.getMerId());
        bizContent.setOut_trade_no(paymentOrderNo);
        bizContent.setOrder_id(bankOrderNo);
        bizContent.setDeal_flag("0");
        bizContent.setIcbc_appid(icbcConfig.getAppId());
        bizContent.setMer_prtcl_no(icbcConfig.getMerPrtclNo());

        request.setBizContent(bizContent);

        long startTime = System.currentTimeMillis();
        CardbusinessAggregatepayB2cOnlineOrderqryResponseV1 response;
        try {
            response = client.execute(request, clientFactory.generateMsgId());
        } catch (Exception e) {
            log.error("ICBC payment query failed", e);
            return new QueryPaymentResponse(
                false,
                "SYSTEM_ERROR",
                e.getMessage(),
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                System.currentTimeMillis() - startTime
            );
        }
        long executionTime = System.currentTimeMillis() - startTime;

        // 解析响应
        boolean success = response.getReturnCode() == 0;
        PaymentStatus status = mapPaymentStatus(response.getPay_status());

        return new QueryPaymentResponse(
            success,
            String.valueOf(response.getReturnCode()),
            response.getReturnMsg(),
            status,
            success ? parseAmount(response.getTotal_amt()) : null,
            success ? parseAmount(response.getPayment_amt()) : null,
            response.getPay_time(),
            response.getOrder_id(),
            response.getThird_trade_no(),
            mapPaymentChannel(response.getPay_type()),
            executionTime
        );
    }

    @Override
    public CreateRefundResponse createRefund(RefundOrder refundOrder, String originalBankOrderNo) {
        DefaultIcbcClient client = clientFactory.createClient();

        CardbusinessAggregatepayB2cOnlineMerrefundRequestV1 request =
            new CardbusinessAggregatepayB2cOnlineMerrefundRequestV1();
        request.setServiceUrl(icbcConfig.getRefundUrl());

        CardbusinessAggregatepayB2cOnlineMerrefundRequestV1Biz bizContent =
            new CardbusinessAggregatepayB2cOnlineMerrefundRequestV1Biz();

        bizContent.setMer_id(icbcConfig.getMerId());
        bizContent.setOuttrx_serial_no(refundOrder.getRefundOrderNo()); // 退货流水号
        bizContent.setOut_trade_no(refundOrder.getPaymentOrderNo()); // 原商户订单号
        bizContent.setOrder_id(originalBankOrderNo); // 原工行订单号
        bizContent.setRet_total_amt(refundOrder.getRefundAmount().toString()); // 退款金额（分）
        bizContent.setTrnsc_ccy("001"); // 人民币
        bizContent.setIcbc_appid(icbcConfig.getAppId());
        bizContent.setMer_prtcl_no(icbcConfig.getMerPrtclNo());

        request.setBizContent(bizContent);

        long startTime = System.currentTimeMillis();
        CardbusinessAggregatepayB2cOnlineMerrefundResponseV1 response;
        try {
            response = client.execute(request, clientFactory.generateMsgId());
        } catch (Exception e) {
            log.error("ICBC refund request failed", e);
            return new CreateRefundResponse(
                false,
                "SYSTEM_ERROR",
                e.getMessage(),
                null,
                System.currentTimeMillis() - startTime
            );
        }
        long executionTime = System.currentTimeMillis() - startTime;

        boolean success = response.getReturnCode() == 0;

        return new CreateRefundResponse(
            success,
            String.valueOf(response.getReturnCode()),
            response.getReturnMsg(),
            success ? response.getIntrx_serial_no() : null, // 工行退款流水号
            executionTime
        );
    }

    @Override
    public QueryRefundResponse queryRefund(String refundOrderNo, String paymentOrderNo, String bankOrderNo, String bankRefundNo) {
        DefaultIcbcClient client = clientFactory.createClient();

        CardbusinessAggregatepayB2cOnlineRefundqryRequestV1 request =
            new CardbusinessAggregatepayB2cOnlineRefundqryRequestV1();
        request.setServiceUrl(icbcConfig.getRefundQueryUrl());

        var bizContent = new CardbusinessAggregatepayB2cOnlineRefundqryRequestV1Biz();

        bizContent.setMer_id(icbcConfig.getMerId());
        bizContent.setOuttrx_serial_no(refundOrderNo);
        if (bankOrderNo != null && !bankOrderNo.isEmpty()) {
            bizContent.setOrder_id(bankOrderNo);
        }
        if (paymentOrderNo != null && !paymentOrderNo.isEmpty()) {
            bizContent.setOut_trade_no(paymentOrderNo);
        }
        bizContent.setMer_prtcl_no(icbcConfig.getMerPrtclNo());

        request.setBizContent(bizContent);

        long startTime = System.currentTimeMillis();
        CardbusinessAggregatepayB2cOnlineRefundqryResponseV1 response;
        try {
            response = client.execute(request, clientFactory.generateMsgId());
        } catch (Exception e) {
            log.error("ICBC refund query failed", e);
            return new QueryRefundResponse(
                false, "SYSTEM_ERROR", e.getMessage(),
                RefundStatus.REFUNDING, null, null, null, null,
                System.currentTimeMillis() - startTime
            );
        }
        long executionTime = System.currentTimeMillis() - startTime;

        boolean success = response.getReturnCode() == 0;
        RefundStatus refundStatus = mapRefundStatus(response.getPay_status());

        return new QueryRefundResponse(
            success,
            String.valueOf(response.getReturnCode()),
            response.getReturnMsg(),
            refundStatus,
            parseAmount(response.getReject_amt()),
            parseAmount(response.getReal_reject_amt()),
            response.getRefund_time(),
            response.getIntrx_serial_no(),
            executionTime
        );
    }

    /**
     * 映射工行退款状态到领域状态
     */
    private RefundStatus mapRefundStatus(String payStatus) {
        if ("0".equals(payStatus)) return RefundStatus.SUCCESS;
        if ("1".equals(payStatus)) return RefundStatus.FAILED;
        return RefundStatus.REFUNDING;
    }

    /**
     * 映射工行支付状态到领域状态
     */
    private PaymentStatus mapPaymentStatus(String payStatus) {
        if ("0".equals(payStatus)) return PaymentStatus.PAID;
        if ("1".equals(payStatus)) return PaymentStatus.FAILED;
        if ("2".equals(payStatus)) return PaymentStatus.PENDING;
        return PaymentStatus.PENDING;
    }

    /**
     * 映射工行支付方式到领域支付渠道
     */
    private PaymentChannel mapPaymentChannel(String payType) {
        // 所有通过工行网关的支付统一为 ICBC 渠道
        return PaymentChannel.ICBC;
    }

    /**
     * 解析金额字符串（分）
     */
    private Long parseAmount(String amountStr) {
        if (amountStr == null || amountStr.isEmpty()) {
            return null;
        }
        try {
            return Long.parseLong(amountStr);
        } catch (NumberFormatException e) {
            log.warn("Failed to parse amount: {}", amountStr);
            return null;
        }
    }
}
