package com.aieducenter.payment.hsb.application.dto.callback;

import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class HsbRefundCallbackParam {
    @JsonProperty("Ittparty_Tms")
    @JSONField(name = "Ittparty_Tms")
    private String ittpartyTms;

    @JsonProperty("Ittparty_Jrnl_No")
    @JSONField(name = "Ittparty_Jrnl_No")
    private String ittpartyJrnlNo;

    @JsonProperty("Py_Trn_No")
    @JSONField(name = "Py_Trn_No")
    private String pyTrnNo;

    @JsonProperty("Cust_Rfnd_Trcno")
    @JSONField(name = "Cust_Rfnd_Trcno")
    private String custRfndTrcno;

    @JsonProperty("Super_Refund_No")
    @JSONField(name = "Super_Refund_No")
    private String superRefundNo;

    @JsonProperty("Rfnd_Amt")
    @JSONField(name = "Rfnd_Amt")
    private String rfndAmt;

    @JsonProperty("Refund_Rsp_St")
    @JSONField(name = "Refund_Rsp_St")
    private String refundRspSt;

    @JsonProperty("Refund_Rsp_Inf")
    @JSONField(name = "Refund_Rsp_Inf")
    private String refundRspInf;

    @JsonProperty("Refund_Funds_Source")
    @JSONField(name = "Refund_Funds_Source")
    private String refundFundsSource;

    @JsonProperty("Sign_Inf")
    @JSONField(name = "Sign_Inf")
    private String signInf;

    public boolean isRefundSuccess() {
        return "00".equals(refundRspSt);
    }
}
