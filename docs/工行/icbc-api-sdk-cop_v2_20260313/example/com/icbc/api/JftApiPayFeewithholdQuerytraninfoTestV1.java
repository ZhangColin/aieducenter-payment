package com.icbc.api.request;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcConstants;
import com.icbc.api.response.JftApiPayFeewithholdQuerytraninfoResponseV1;

public class JftApiPayFeewithholdQuerytraninfoTestV1 {

    public void test() throws IcbcApiException {

        String APP_ID = "[需替换]app_id";
        String MY_PRIVATE_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";
        String icbcPulicKey = "[需替换]工行API网关公钥，请向工行联系获取";
        String AES_Key = "[需替换]应用方加密串，与工行API开放平台一致";
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY,IcbcConstants.CHARSET_UTF8,IcbcConstants.FORMAT_JSON,
                icbcPulicKey,IcbcConstants.ENCRYPT_TYPE_AES,AES_Key,null,null);

        JftApiPayFeewithholdQuerytraninfoRequestV1 request = new JftApiPayFeewithholdQuerytraninfoRequestV1();
        request.setServiceUrl("https:[需替换]工行API网关地址/api/jft/api/feewithhold/querytraninfo/V1");

        JftApiPayFeewithholdQuerytraninfoRequestV1.JftApiPayFeewithholdQuerytraninfoRequestV1Biz bizContent = new JftApiPayFeewithholdQuerytraninfoRequestV1.JftApiPayFeewithholdQuerytraninfoRequestV1Biz();

        bizContent.setAppId("[需替换]app_id");
        bizContent.setOutVendorId("[需替换]20201128531215026");
        bizContent.setChanSerialNo("[需替换]合作方交易单号");
        bizContent.setTrxCorpcode("[需替换]缴费项目编码,签订协议时签约的缴费项目编码，一个用户可在缴费项目中签订多个代扣协议");
        bizContent.setOrigChanSerialNo("[需替换]原合作方交易单号");
        bizContent.setTrxType("[需替换]交易类型1扣款2扣款撤销");
        request.setBizContent(bizContent);
        JftApiPayFeewithholdQuerytraninfoResponseV1 responseV1 = client.execute(request);
        System.out.println(responseV1);

    }
}
