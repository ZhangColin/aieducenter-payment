package com.icbc.api;

import com.icbc.api.request.JftApiInvoiceRedOffsetRevokeRequestV1;
import com.icbc.api.request.JftApiInvoiceRedOffsetRevokeRequestV1.JftApiInvoiceRedOffsetRevokeRequestV1Biz;
import com.icbc.api.response.JftApiInvoiceRedOffsetRevokeResponseV1;

public class JftApiInvoiceRedOffsetRevokeTestV1 {

    protected static final String MY_PRIVATE_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";

    protected static final String APIGW_PUBLIC_KEY = "[需替换]工行API网关公钥，请向工行联系获取";

    protected static final String APP_ID = "[需替换]APP的编号";

    protected static final String AES_Key = "[需替换]应用方加密串，与工行API开放平台一致";


    public static void main(String[] args) {

        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, "UTF-8",
                "json", APIGW_PUBLIC_KEY, "AES", AES_Key, "", "");

        JftApiInvoiceRedOffsetRevokeRequestV1 request = new JftApiInvoiceRedOffsetRevokeRequestV1();
        request.setServiceUrl("[需替换]工行API网关地址/api/jft/api/red/invoice/offset/revoke/V1");
        JftApiInvoiceRedOffsetRevokeRequestV1Biz bizContent = new JftApiInvoiceRedOffsetRevokeRequestV1Biz();
        bizContent.setAppId(APP_ID);//平台商户标识
        bizContent.setOutRedOffsetId("");// 红冲流水号
        bizContent.setOutOrderId("");//平台对接方红冲订单号
        request.setBizContent(bizContent);

        JftApiInvoiceRedOffsetRevokeResponseV1 response;
        try {
            response = client.execute(request, System.currentTimeMillis() + "");//msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一
            if (response.getReturnCode() == 0) {
                //6、业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
                System.out.println("ReturnCode:" + response.getReturnCode());
                System.out.println("response:" + response);
            } else {
                //失败
                System.out.println("ReturnCode:" + response.getReturnCode());
                System.out.println("ReturnMsg:" + response.getReturnMsg());
            }
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }
}

