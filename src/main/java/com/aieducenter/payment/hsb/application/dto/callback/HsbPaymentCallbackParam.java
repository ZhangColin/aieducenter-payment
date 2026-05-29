package com.aieducenter.payment.hsb.application.dto.callback;

import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class HsbPaymentCallbackParam {
    @JsonProperty("Main_Ordr_No")
    @JSONField(name = "Main_Ordr_No")
    private String mainOrdrNo;

    @JsonProperty("Py_Trn_No")
    @JSONField(name = "Py_Trn_No")
    private String pyTrnNo;

    @JsonProperty("Ordr_Amt")
    @JSONField(name = "Ordr_Amt")
    private String ordrAmt;

    @JsonProperty("Txnamt")
    @JSONField(name = "Txnamt")
    private String txnamt;

    @JsonProperty("Pay_Time")
    @JSONField(name = "Pay_Time")
    private String payTime;

    @JsonProperty("Ordr_Stcd")
    @JSONField(name = "Ordr_Stcd")
    private String ordrStcd;

    @JsonProperty("Sign_Inf")
    @JSONField(name = "Sign_Inf")
    private String signInf;

    @JsonProperty("Prim_Ordr_No")
    @JSONField(name = "Prim_Ordr_No")
    private String primOrdrNo;

    public boolean isPaymentSuccess() {
        return "2".equals(ordrStcd);
    }

    public boolean isPaymentFailed() {
        return "3".equals(ordrStcd);
    }

    public boolean isPaymentExpired() {
        return "4".equals(ordrStcd);
    }
}
