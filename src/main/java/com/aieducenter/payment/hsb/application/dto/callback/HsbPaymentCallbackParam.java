package com.aieducenter.payment.hsb.application.dto.callback;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

@Data
public class HsbPaymentCallbackParam {
    @JSONField(name = "Main_Ordr_No")
    private String mainOrdrNo;

    @JSONField(name = "Py_Trn_No")
    private String pyTrnNo;

    @JSONField(name = "Ordr_Amt")
    private String ordrAmt;

    @JSONField(name = "Txnamt")
    private String txnamt;

    @JSONField(name = "Pay_Time")
    private String payTime;

    @JSONField(name = "Ordr_Stcd")
    private String ordrStcd;

    @JSONField(name = "Sign_Inf")
    private String signInf;

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
