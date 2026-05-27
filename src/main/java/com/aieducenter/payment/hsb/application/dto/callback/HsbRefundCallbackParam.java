package com.aieducenter.payment.hsb.application.dto.callback;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

@Data
public class HsbRefundCallbackParam {
    @JSONField(name = "Ittparty_Tms")
    private String ittpartyTms;

    @JSONField(name = "Ittparty_Jrnl_No")
    private String ittpartyJrnlNo;

    @JSONField(name = "Py_Trn_No")
    private String pyTrnNo;

    @JSONField(name = "Cust_Rfnd_Trcno")
    private String custRfndTrcno;

    @JSONField(name = "Super_Refund_No")
    private String superRefundNo;

    @JSONField(name = "Rfnd_Amt")
    private String rfndAmt;

    @JSONField(name = "Refund_Rsp_St")
    private String refundRspSt;

    @JSONField(name = "Refund_Rsp_Inf")
    private String refundRspInf;

    @JSONField(name = "Refund_Funds_Source")
    private String refundFundsSource;

    @JSONField(name = "Sign_Inf")
    private String signInf;

    public boolean isRefundSuccess() {
        return "00".equals(refundRspSt);
    }
}
