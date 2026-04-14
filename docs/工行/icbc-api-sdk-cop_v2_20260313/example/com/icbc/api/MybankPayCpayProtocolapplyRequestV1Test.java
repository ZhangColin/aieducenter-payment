package com.icbc.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.internal.util.internal.util.fastjson.JSONArray;
import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.MybankPayCpayProtocolapplyRequestV1;
import com.icbc.api.request.MybankPayCpayProtocolapplyRequestV1.MybankPayCpayProtocolapplyRequestV1Biz;
import com.icbc.api.response.MybankPayCpayProtocolapplyResponseV1;

public class MybankPayCpayProtocolapplyRequestV1Test {
    protected static final String MY_PRIVATE_KEY = "XXXX";

    protected static final String APIGW_PUBLIC_KEY = "XXXXX";

    protected static final String APP_ID = "123";

    public static void main(String[] args) {

        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

        MybankPayCpayProtocolapplyRequestV1 req = new MybankPayCpayProtocolapplyRequestV1();
        req.setServiceUrl("http://IP:PROT/api/mybank/pay/cpay/protocolapply/V1");

        MybankPayCpayProtocolapplyRequestV1.MybankPayCpayProtocolapplyRequestV1Biz bizContent = new MybankPayCpayProtocolapplyRequestV1.MybankPayCpayProtocolapplyRequestV1Biz();

        MybankPayCpayProtocolapplyResponseV1 response = new MybankPayCpayProtocolapplyResponseV1();


        bizContent.put("checkWorkDateFlag", "1");
        bizContent.put("trxRevSideFeeCurr", "001");
        bizContent.put("iForCurrFeePaySideFeeAmount", "");
        bizContent.put("apprTeller", "000038230");
        bizContent.put("bForceRegist", "1");
        bizContent.put("mainSaleDept", "1");
        bizContent.put("guaranteeIsTransactionAuth", "1");
        bizContent.put("trxRevSideFeeRatio", "0");
        bizContent.put("bGurantPay", "1");
        bizContent.put("programName", "CBMS");
        bizContent.put("guaranteeCheckoutMode", "2");
        bizContent.put("iForCurrFeePaySide", "1");
        bizContent.put("iForCurrPaySideFeeObj", "1");
        bizContent.put("verifyDocId", "bbbb123");
        bizContent.put("channelDate", "2022-03-31");
        bizContent.put("trxDate", "2022-03-31");
        bizContent.put("brNo", "620");
        bizContent.put("authTeller", "");
        bizContent.put("tellPass", "");
        bizContent.put("rateShareAccno", "");
        bizContent.put("commissionAccno", "");
        bizContent.put("bCommissionPay", "0");
        bizContent.put("ensurePayInterestSide", "0");
        bizContent.put("startApp", "F-CBMS");
        bizContent.put("guaranteeConfirmSide", "1");
        bizContent.put("bShareInterest", "0");
        bizContent.put("upstreamEventSerialno", "030862007581234493656439996");
        bizContent.put("revAccno", "");
        bizContent.put("trxOfflineFeeRatio", "0");
        bizContent.put("channelTime", "09:36:54");
        bizContent.put("bNewBillPay", "1");
        bizContent.put("trxPlatfSidefeeType", "0");
        bizContent.put("autDutyno", "");
        bizContent.put("trxOfflinefeeType", "1");
        bizContent.put("apprDate", "2022-03-31");
        bizContent.put("iForCurrFeeCurr", "");
        bizContent.put("mBankFeePlatSideFeeAccno", "");
        bizContent.put("operTeller", "49");
        bizContent.put("agrText", "测试协议文本123");
        bizContent.put("extraFeePaySide", "0");
        bizContent.put("agrFirstDay", "2030-03-31");
        bizContent.put("trxPlatSideFeeCycle", "");
        bizContent.put("isAggregatePay", "1");
        bizContent.put("trxOfflineFeeAccno", "0200041729213876383");
        bizContent.put("iForCurrFeePaySideMinAmt", "");
        bizContent.put("extraFeePerMaxAmt", "0");
        bizContent.put("remitPlatSideCycle", "5");
        bizContent.put("fixRate", "0");
        bizContent.put("servface", "39");
        bizContent.put("iForCurrFeePaySideRatio", "");
        bizContent.put("terminalId", "0");
        bizContent.put("mBankFeePlatSideCurr", "001");
        bizContent.put("endAgreementDate", "9999-12-31");
        bizContent.put("targetPayChk", "1");
        bizContent.put("trxCode", "12889");
        bizContent.put("agrFeeAmount", "0");
        bizContent.put("bAsynPay", "1");
        bizContent.put("trxPlatSideFeeAmount", "");
        bizContent.put("harvestGoodDate", "30");
        bizContent.put("cashCurr", "001");
        bizContent.put("cashType", "1");
        bizContent.put("deferTimes", "4");
        bizContent.put("parName", "2204-蒋晞茜新增担保、保证协议");
        bizContent.put("bIForCurrAcct", "1");
        bizContent.put("trxRevSidefeeType", "0");
        bizContent.put("executeRate", "325000");
        bizContent.put("authFlag", "");
        bizContent.put("apprOrganNo", "0010100001");
        bizContent.put("trxPaySidefeeType", "1");
        bizContent.put("autPwd", "");
        bizContent.put("agrFeeRatio", "0");
        bizContent.put("mBankFeePlatSideObj", "");
        bizContent.put("bTransferFee", "0");
        bizContent.put("trxPaySideFeeAmount", "0");
        bizContent.put("mBankFeePlatSideCycle", "");
        bizContent.put("trxOfflineFeeFirstDay", "2030-03-31");
        bizContent.put("busSerialno", "010102432083210465456706489");
        bizContent.put("trxPaySideFeeRatio", "0");
        bizContent.put("bDigiccyPay", "1");
        bizContent.put("agrFeeCycle", "4");
        bizContent.put("autLevel", "");
        bizContent.put("bBillPay", "1");
        bizContent.put("trxPlatSideFeeCurr", "001");
        bizContent.put("notes", "备注");
        bizContent.put("functionCode", "123");
        bizContent.put("signOrganno", "0020000998");
        bizContent.put("accno", "0200062009213703958");
        bizContent.put("mBankFeePSideObj", "0");
        bizContent.put("cino", "020090051225956");
        bizContent.put("channelType", "102");
        bizContent.put("trxType", "1");
        bizContent.put("rateMode", "1");
        bizContent.put("signDate", "2022-03-31");
        bizContent.put("operType", "4");
        bizContent.put("trxOfflinefeePaySide", "3");
        bizContent.put("bEnsurePay", "1");
        bizContent.put("bDirectPay", "1");
        bizContent.put("mBankPayFeePSidePerPayAmount", "");
        bizContent.put("agrFeeType", "1");
        bizContent.put("trxPaySideFeeCurr", "001");
        bizContent.put("signTeller", "020009939");
        bizContent.put("mBankPayFeePSideCurr", "001");
        bizContent.put("appId", "10000000000000004507");
        bizContent.put("interestCycle", "2");
        bizContent.put("bBillHoldPay", "1");
        bizContent.put("agrPerFeeAmount", "300");
        bizContent.put("mBankFeePlatPerPayAmount", "");
        bizContent.put("iForCurrFeePerPayAmount", "300");
        bizContent.put("bForExtraFee", "0");
        bizContent.put("trxPlatSideFeeRatio", "");
        bizContent.put("serviceProviderNo", "1234567890123456789012345678901234");
        bizContent.put("trxRevSideFeeAmount", "0");
        bizContent.put("ipAddress", "ip:port");
        bizContent.put("agrPayCurr", "001");
        bizContent.put("trxPaySidePerFeeAmt", "300");
        bizContent.put("isShowPayeeInfo", "2");
        bizContent.put("trxRevSideFeeCycle", "0");
        bizContent.put("recordNo", "1000000001");
        bizContent.put("authBrNo", "");
        bizContent.put("isHarvestGood", "1");
        bizContent.put("mBankFeeFirstDay", "");
        bizContent.put("macAddress", "00-50-56-a8-36-02");
        bizContent.put("extraFeePerFeeAmount", "0");
        bizContent.put("trxOfflineFeeCycle", "4");
        bizContent.put("bTrustPlatform", "0");
        bizContent.put("opertype", "4");
        bizContent.put("firstEventSerialno", "01025010001004022107295");
        bizContent.put("bRegistMember", "1");
        bizContent.put("mBankFeePaySide", "0");
        bizContent.put("guaranteeAccType", "1");
        bizContent.put("remitPlatSideFeeAccno", "0200041729213876383");
        bizContent.put("autAmt", "");
        bizContent.put("trxOfflineFeeCurr", "001");
        bizContent.put("trxOfflineFeeAmount", "0");
        bizContent.put("bCashPay", "1");
        bizContent.put("trxID", "0");
        bizContent.put("rateCode", "1100000");
        bizContent.put("trxPlatSidePerFeeAmt", "");
        bizContent.put("trxPlatSideFeeAccno", "");
        bizContent.put("remitPlatSideCurr", "1");
        bizContent.put("shareInterestRatio", "200000");
        bizContent.put("bIForCurrFee", "0");
        bizContent.put("extraFeeType", "0");
        bizContent.put("bMutiBankFee", "0");
        bizContent.put("ensurePayConfirmSide", "1");
        bizContent.put("trxRevSidePerFeeAmt", "0");
        bizContent.put("bHoldPay", "1");
        bizContent.put("bTcYdKhTrxFee", "1");
        bizContent.put("extraFeeAmount", "0");
        bizContent.put("autCardNo", "");
        bizContent.put("authZoneNo", "");
        bizContent.put("bCrossAcct", "1");
        bizContent.put("authCode", "");
        bizContent.put("agrPayAccno", "0200041729213876383");
        bizContent.put("trxfeePaySide", "1");
        bizContent.put("remitPaySide", "1");
        bizContent.put("isPayCheckSide", "1");
        bizContent.put("trxFeeFirstDay", "2030-03-31");
        bizContent.put("isEPay", "1");
        bizContent.put("extraFeeCurr", "0");
        bizContent.put("isDefer", "1");
        bizContent.put("ensurePayCheckSide", "1");
        bizContent.put("isOfflineAsynPay", "1");
        bizContent.put("zoneNo", "200");
        bizContent.put("agrTerm", "1");
        bizContent.put("proId", "060410006041");
        bizContent.put("bMutiBankPay", "0");
        bizContent.put("secSaleDept", "2");
        bizContent.put("extraFeeRatio", "0");
        bizContent.put("bTerriAcctFee", "1");
        bizContent.put("beginAgreementDate", "2022-03-31");
        bizContent.put("trxOfflinePerFeeAmt", "20");
        bizContent.put("extraFeePerMinAmt", "0");
        bizContent.put("channelTerminalType", "01000");
        bizContent.put("iForCurrFeePaySideMaxAmt", "");

        Random rand = new Random();
        String msgId = rand.nextInt(99999) + "msg";
        log.fine("msgId:" + msgId);

        request.setBizContent(bizContent);

        try {
            response = client.execute(request, msgId);
            log.fine("JSONObject.toJSONString(response):" + JSONObject.toJSONString(response));
            if (response.isSuccess()) {
                // 业务成功处理
                log.fine("response.getReturnCode():" + response.getReturnCode());
            } else {
                //失败
                log.fine("response.getReturnCode():" + response.getReturnCode());
                log.fine("response.getReturnMsg():" + response.getReturnMsg());
            }
        } catch (IcbcApiException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
