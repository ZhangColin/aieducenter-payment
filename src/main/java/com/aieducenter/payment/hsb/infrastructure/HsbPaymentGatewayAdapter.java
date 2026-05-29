package com.aieducenter.payment.hsb.infrastructure;

import com.aieducenter.payment.hsb.domain.aggregate.HsbPaymentOrder;
import com.aieducenter.payment.hsb.domain.aggregate.HsbRefundOrder;
import com.aieducenter.payment.hsb.domain.aggregate.HsbRefundSubOrder;
import com.aieducenter.payment.hsb.domain.aggregate.HsbSubOrder;
import com.aieducenter.payment.hsb.domain.enums.HsbPaymentStatus;
import com.aieducenter.payment.hsb.domain.enums.HsbRefundStatus;
import com.aieducenter.payment.hsb.domain.port.HsbPaymentGatewayPort;
import com.aieducenter.payment.hsb.domain.port.response.*;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.cartisan.core.stereotype.Adapter;
import com.cartisan.core.stereotype.PortType;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
@Adapter(PortType.CLIENT)
@RequiredArgsConstructor
public class HsbPaymentGatewayAdapter implements HsbPaymentGatewayPort {

    private static final Logger log = LoggerFactory.getLogger(HsbPaymentGatewayAdapter.class);
    private static final DateTimeFormatter TIMESTAMP_FORMAT = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");

    private final HsbConfig hsbConfig;
    private final HsbHttpClient hsbHttpClient;

    @Override
    public CreateHsbPaymentResponse createPayment(HsbPaymentOrder order, List<HsbSubOrder> subOrders) {
        JSONObject json = new JSONObject();
        json.put("Ittparty_Stm_Id", hsbConfig.getInitiatorSystemId());
        json.put("Py_Chnl_Cd", hsbConfig.getInitiatorChannelCode());
        json.put("Ittparty_Tms", LocalDateTime.now().format(TIMESTAMP_FORMAT));
        json.put("Ittparty_Jrnl_No", order.getPaymentOrderNo());
        json.put("Mkt_Id", hsbConfig.getMktId());
        json.put("Main_Ordr_No", order.getBusinessMainOrderNo());
        json.put("Pymd_Cd", order.getPaymentMethod());
        json.put("Py_Ordr_Tpcd", order.getOrderType());
        json.put("Ccy", order.getCurrency());
        json.put("Ordr_Tamt", fenToYuan(order.getTotalAmount()));
        json.put("Txn_Tamt", fenToYuan(order.getTxnTotalAmount()));
        // TODO 建行配置暂不支持 Hdcg_Brs_Id，待确认后启用
        // if (cn.hutool.core.util.StrUtil.isNotBlank(hsbConfig.getPlatformMerchantId())) {
        //     json.put("Hdcg_Brs_Id", hsbConfig.getPlatformMerchantId());
        // } else if (cn.hutool.core.util.StrUtil.isNotBlank(order.getFeeBearerId())) {
        //     json.put("Hdcg_Brs_Id", order.getFeeBearerId());
        // }
        json.put("Vno", hsbConfig.getVersion().getPlaceOrder());
        json.put("Clrg_Dt", order.resolveClrgDt());
        if (cn.hutool.core.util.StrUtil.isNotBlank(order.getPageReturnUrl())) {
            json.put("Pgfc_Ret_Url_Adr", order.getPageReturnUrl());
        }

        JSONArray orderList = new JSONArray();
        for (HsbSubOrder sub : subOrders) {
            JSONObject subJson = new JSONObject();
            subJson.put("Mkt_Mrch_Id", sub.getMktMrchId());
            subJson.put("Cmdty_Ordr_No", sub.getBusinessSubOrderNo());
            subJson.put("Ordr_Amt", fenToYuan(sub.getOrderAmount()));
            subJson.put("Txnamt", fenToYuan(sub.getTxnAmount()));
            orderList.add(subJson);
        }
        json.put("Orderlist", orderList);

        String requestParams = json.toJSONString();
        String signStr = HsbSplicingUtil.createSign(requestParams);
        String signInf = HsbSignUtil.sign(hsbConfig.getPrivateKey(), signStr);
        json.put("Sign_Inf", signInf);

        long startTime = System.currentTimeMillis();
        String responseBody;
        try {
            responseBody = hsbHttpClient.postJson(hsbConfig.getPlaceOrderUrl(), json.toJSONString());
        } catch (Exception e) {
            long executionTime = System.currentTimeMillis() - startTime;
            log.error("HSB createPayment failed", e);
            return new CreateHsbPaymentResponse(false, "SYSTEM_ERROR", e.getMessage(),
                null, null, null, null, executionTime, requestParams, null, null);
        }
        long executionTime = System.currentTimeMillis() - startTime;

        JSONObject response = JSONObject.parseObject(responseBody);
        String returnCode = response.getString("Svc_Rsp_St");
        String returnMsg = response.getString("Svc_Rsp_Cd");

        boolean success = "00".equals(returnCode);
        String cshdkUrl = success ? response.getString("Cshdk_Url") : null;
        String payUrl = success ? response.getString("Pay_Url") : null;
        String payQrCode = success ? response.getString("Pay_Qr_Code") : null;
        String primOrderNo = success ? response.getString("Prim_Ordr_No") : null;

        java.util.Map<String, String> subOrderIdMap = null;
        if (success) {
            JSONArray responseOrderList = response.getJSONArray("Orderlist");
            if (responseOrderList != null) {
                subOrderIdMap = new java.util.LinkedHashMap<>();
                for (int i = 0; i < responseOrderList.size(); i++) {
                    JSONObject subJson = responseOrderList.getJSONObject(i);
                    String cmdtyOrdrNo = subJson.getString("Cmdty_Ordr_No");
                    String subOrdrId = subJson.getString("Sub_Ordr_Id");
                    if (cmdtyOrdrNo != null && subOrdrId != null) {
                        subOrderIdMap.put(cmdtyOrdrNo, subOrdrId);
                    }
                }
            }
        }

        return new CreateHsbPaymentResponse(success, returnCode, returnMsg,
            cshdkUrl, payUrl, payQrCode, primOrderNo, executionTime, requestParams, responseBody, subOrderIdMap);
    }

    @Override
    public QueryHsbPaymentResponse queryPayment(String mktId, String mainOrderNo, String pyTrnNo) {
        JSONObject json = new JSONObject();
        json.put("Ittparty_Stm_Id", hsbConfig.getInitiatorSystemId());
        json.put("Py_Chnl_Cd", hsbConfig.getInitiatorChannelCode());
        json.put("Ittparty_Tms", LocalDateTime.now().format(TIMESTAMP_FORMAT));
        json.put("Ittparty_Jrnl_No", String.valueOf(System.currentTimeMillis()));
        json.put("Mkt_Id", mktId);
        json.put("Main_Ordr_No", mainOrderNo);
        if (pyTrnNo != null) {
            json.put("Py_Trn_No", pyTrnNo);
        }
        json.put("Vno", hsbConfig.getVersion().getQueryOrder());

        String requestParams = json.toJSONString();
        String signStr = HsbSplicingUtil.createSign(requestParams);
        String signInf = HsbSignUtil.sign(hsbConfig.getPrivateKey(), signStr);
        json.put("Sign_Inf", signInf);

        long startTime = System.currentTimeMillis();
        String responseBody;
        try {
            responseBody = hsbHttpClient.postJson(hsbConfig.getQueryOrderUrl(), json.toJSONString());
        } catch (Exception e) {
            long executionTime = System.currentTimeMillis() - startTime;
            return new QueryHsbPaymentResponse(false, "SYSTEM_ERROR", e.getMessage(),
                null, null, null, executionTime, null);
        }
        long executionTime = System.currentTimeMillis() - startTime;

        JSONObject response = JSONObject.parseObject(responseBody);
        String returnCode = response.getString("Svc_Rsp_St");
        boolean success = "00".equals(returnCode);

        HsbPaymentStatus paymentStatus = null;
        String pyTrnNoResult = null;
        Long actualAmount = null;

        if (success) {
            String ordrStcd = response.getString("Ordr_Stcd");
            paymentStatus = mapPaymentStatus(ordrStcd);
            pyTrnNoResult = response.getString("Py_Trn_No");
            String ordrAmt = response.getString("Ordr_Amt");
            if (ordrAmt != null) {
                actualAmount = yuanToFen(ordrAmt);
            }
        }

        return new QueryHsbPaymentResponse(success, returnCode, response.getString("Svc_Rsp_Cd"),
            paymentStatus, pyTrnNoResult, actualAmount, executionTime, responseBody);
    }

    @Override
    public CreateHsbRefundResponse createRefund(HsbRefundOrder order, String pyTrnNo, List<HsbRefundSubOrder> subOrders) {
        JSONObject json = new JSONObject();
        json.put("Ittparty_Stm_Id", hsbConfig.getInitiatorSystemId());
        json.put("Py_Chnl_Cd", hsbConfig.getInitiatorChannelCode());
        json.put("Ittparty_Tms", LocalDateTime.now().format(TIMESTAMP_FORMAT));
        json.put("Ittparty_Jrnl_No", order.getRefundOrderNo());
        json.put("Mkt_Id", hsbConfig.getMktId());
        json.put("Py_Trn_No", pyTrnNo);
        json.put("Rfnd_Type", "01".equals(order.getRefundType()) || "ASYNC".equals(order.getRefundType()) ? "01" : "00");
        json.put("Cust_Rfnd_Trcno", order.getRefundOrderNo());
        json.put("Rfnd_Amt", fenToYuan(order.getRefundAmount()));
        json.put("Vno", hsbConfig.getVersion().getRefundOrder());

        if (subOrders != null && !subOrders.isEmpty()) {
            JSONArray subList = new JSONArray();
            for (HsbRefundSubOrder sub : subOrders) {
                JSONObject subJson = new JSONObject();
                subJson.put("Sub_Ordr_Id", sub.getSubOrderId());
                subJson.put("Rfnd_Amt", fenToYuan(sub.getRefundAmount()));
                subList.add(subJson);
            }
            json.put("Sub_Ordr_List", subList);
        }

        String requestParams = json.toJSONString();
        String signStr = HsbSplicingUtil.createSign(requestParams);
        String signInf = HsbSignUtil.sign(hsbConfig.getPrivateKey(), signStr);
        json.put("Sign_Inf", signInf);

        long startTime = System.currentTimeMillis();
        String responseBody;
        try {
            responseBody = hsbHttpClient.postJson(hsbConfig.getRefundOrderUrl(), json.toJSONString());
        } catch (Exception e) {
            long executionTime = System.currentTimeMillis() - startTime;
            return new CreateHsbRefundResponse(false, "SYSTEM_ERROR", e.getMessage(),
                null, null, executionTime, requestParams, null);
        }
        long executionTime = System.currentTimeMillis() - startTime;

        JSONObject response = JSONObject.parseObject(responseBody);
        String returnCode = response.getString("Svc_Rsp_St");
        boolean success = "00".equals(returnCode);

        HsbRefundStatus refundStatus = null;
        String superRefundNo = null;
        if (success) {
            String rspSt = response.getString("Refund_Rsp_St");
            refundStatus = mapRefundStatus(rspSt);
            superRefundNo = response.getString("Super_Refund_No");
        }

        return new CreateHsbRefundResponse(success, returnCode, response.getString("Svc_Rsp_Cd"),
            refundStatus, superRefundNo, executionTime, requestParams, responseBody);
    }

    @Override
    public QueryHsbRefundResponse queryRefund(String mktId, String custRfndTrcno, String rfndTrcno) {
        JSONObject json = new JSONObject();
        json.put("Ittparty_Stm_Id", hsbConfig.getInitiatorSystemId());
        json.put("Py_Chnl_Cd", hsbConfig.getInitiatorChannelCode());
        json.put("Ittparty_Tms", LocalDateTime.now().format(TIMESTAMP_FORMAT));
        json.put("Ittparty_Jrnl_No", String.valueOf(System.currentTimeMillis()));
        json.put("Mkt_Id", mktId);
        json.put("Cust_Rfnd_Trcno", custRfndTrcno);
        if (rfndTrcno != null) {
            json.put("Rfnd_Trcno", rfndTrcno);
        }
        json.put("Vno", hsbConfig.getVersion().getQueryRefund());

        String requestParams = json.toJSONString();
        String signStr = HsbSplicingUtil.createSign(requestParams);
        String signInf = HsbSignUtil.sign(hsbConfig.getPrivateKey(), signStr);
        json.put("Sign_Inf", signInf);

        long startTime = System.currentTimeMillis();
        String responseBody;
        try {
            responseBody = hsbHttpClient.postJson(hsbConfig.getQueryRefundUrl(), json.toJSONString());
        } catch (Exception e) {
            long executionTime = System.currentTimeMillis() - startTime;
            return new QueryHsbRefundResponse(false, "SYSTEM_ERROR", e.getMessage(),
                null, null, executionTime, null);
        }
        long executionTime = System.currentTimeMillis() - startTime;

        JSONObject response = JSONObject.parseObject(responseBody);
        String returnCode = response.getString("Svc_Rsp_St");
        boolean success = "00".equals(returnCode);

        HsbRefundStatus refundStatus = null;
        String superRefundNo = null;
        if (success) {
            String rspSt = response.getString("Refund_Rsp_St");
            refundStatus = mapRefundStatus(rspSt);
            superRefundNo = response.getString("Super_Refund_No");
        }

        return new QueryHsbRefundResponse(success, returnCode, response.getString("Svc_Rsp_Cd"),
            refundStatus, superRefundNo, executionTime, responseBody);
    }

    @Override
    public ConfirmSettlementResponse confirmSettlement(String mktId, String primOrderNo, String subOrderId) {
        JSONObject json = new JSONObject();
        json.put("Ittparty_Stm_Id", hsbConfig.getInitiatorSystemId());
        json.put("Py_Chnl_Cd", hsbConfig.getInitiatorChannelCode());
        json.put("Ittparty_Tms", LocalDateTime.now().format(TIMESTAMP_FORMAT));
        json.put("Ittparty_Jrnl_No", String.valueOf(System.currentTimeMillis()));
        json.put("Mkt_Id", mktId);
        json.put("Prim_Ordr_No", primOrderNo);
        if (subOrderId != null) {
            json.put("Sub_Ordr_Id", subOrderId);
        }
        json.put("Vno", hsbConfig.getVersion().getConfirmSettlement());

        String requestParams = json.toJSONString();
        String signStr = HsbSplicingUtil.createSign(requestParams);
        String signInf = HsbSignUtil.sign(hsbConfig.getPrivateKey(), signStr);
        json.put("Sign_Inf", signInf);

        long startTime = System.currentTimeMillis();
        String responseBody;
        try {
            responseBody = hsbHttpClient.postJson(hsbConfig.getConfirmSettlementUrl(), json.toJSONString());
        } catch (Exception e) {
            long executionTime = System.currentTimeMillis() - startTime;
            return new ConfirmSettlementResponse(false, "SYSTEM_ERROR", e.getMessage(),
                executionTime, requestParams, null);
        }
        long executionTime = System.currentTimeMillis() - startTime;

        JSONObject response = JSONObject.parseObject(responseBody);
        String returnCode = response.getString("Svc_Rsp_St");
        boolean success = "00".equals(returnCode);

        return new ConfirmSettlementResponse(success, returnCode, response.getString("Svc_Rsp_Cd"),
            executionTime, requestParams, responseBody);
    }

    private String fenToYuan(Long fen) {
        if (fen == null) return "0.00";
        return BigDecimal.valueOf(fen).divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP).toPlainString();
    }

    private Long yuanToFen(String yuan) {
        if (yuan == null || yuan.isBlank()) return null;
        return new BigDecimal(yuan).multiply(BigDecimal.valueOf(100)).longValue();
    }

    private HsbPaymentStatus mapPaymentStatus(String ordrStcd) {
        if ("2".equals(ordrStcd)) return HsbPaymentStatus.PAID;
        if ("3".equals(ordrStcd)) return HsbPaymentStatus.FAILED;
        if ("4".equals(ordrStcd)) return HsbPaymentStatus.EXPIRED;
        return HsbPaymentStatus.PENDING;
    }

    private HsbRefundStatus mapRefundStatus(String rspSt) {
        if ("00".equals(rspSt)) return HsbRefundStatus.SUCCESS;
        if ("01".equals(rspSt)) return HsbRefundStatus.FAILED;
        return HsbRefundStatus.REFUNDING;
    }
}
