package com.icbc.api.request;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcConstants;
import com.icbc.api.response.JftApiPayFeewithholdWithholdingResponseV1;

public class JftApiPayFeewithholdWithholdingTestV1 {

    public void test() throws IcbcApiException {

        String APP_ID = "[需替换]app_id";
        String MY_PRIVATE_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";
        String icbcPulicKey = "[需替换]工行API网关公钥，请向工行联系获取";
        String AES_Key = "[需替换]应用方加密串，与工行API开放平台一致";
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY,IcbcConstants.CHARSET_UTF8,IcbcConstants.FORMAT_JSON,
                icbcPulicKey,IcbcConstants.ENCRYPT_TYPE_AES,AES_Key,null,null);

        JftApiPayFeewithholdWithholdingRequestV1 request = new JftApiPayFeewithholdWithholdingRequestV1();
        request.setServiceUrl("http://[需替换]工行API网关地址/api/jft/api/feewithhold/withholding/V1");

        JftApiPayFeewithholdWithholdingRequestV1.JftApiPayFeewithholdWithholdingRequestV1Biz bizContent = new JftApiPayFeewithholdWithholdingRequestV1.JftApiPayFeewithholdWithholdingRequestV1Biz();

        bizContent.setAppId("[需替换]app_id");
        bizContent.setOutVendorId("[需替换]20201128531215026");
        bizContent.setChanSerialNo("[需替换]合作方交易单号");
        bizContent.setTrxPersonNo("[需替换]客户编号,签订协议时上送的客户编号，是缴费协议的唯一标识");
        bizContent.setTrxCorpcode("[需替换]缴费项目编码,签订协议时签约的缴费项目编码，一个用户可在缴费项目中签订多个代扣协议");
        bizContent.setTrxAccDate("[需替换]合作方交易时间,交易发起时间，格式格式: hh:mm:ss，用于记录交易时间|");
        bizContent.setTrxAccTime("[需替换]合作方交易时间,交易发起时间，格式格式: hh:mm:ss，用于记录交易时间|");
        bizContent.setTrxIdType("[需替换]证件类型.");
        bizContent.setTrxIdNo("[需替换]证件号");
        bizContent.setTrxMobileNo("[需替换]手机号");
        bizContent.setTrxName("[需替换]姓名");
        bizContent.setTrxAccount("[需替换]交易卡号.");
        bizContent.setTrxCcy("[需替换]币种,示例:001.");
        bizContent.setTrxCashExFlag("[需替换]钞汇标志, 0 (0-钞，1-汇，2-假汇，3-境外汇款，9-不分钞汇) ");
        bizContent.setTrxAmount("[需替换]交易额,以“分为单位”，100表示1元.");
        bizContent.setTrxSummary("[需替换]摘要.");
        bizContent.setTrxSummary("[需替换]注释.");

        request.setBizContent(bizContent);
        JftApiPayFeewithholdWithholdingResponseV1 responseV1 = client.execute(request);
        System.out.println(responseV1);

    }
}
