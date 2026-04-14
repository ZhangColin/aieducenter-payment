package com.icbc.api;

import com.icbc.api.request.JftApiLoanOrderapplyRequestV1;
import com.icbc.api.response.JftApiLoanOrderapplyResponseV1;

import java.text.ParseException;
import java.util.*;

public class JftApiLoanOrderapplyTestV1 {
    protected static final String MY_PRIVATE_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";
    protected static final String APIGW_PUBLIC_KEY = "[需替换]工行API网关公钥，请向工行联系获取";
    protected static final String AES_KEY = "[需替换]此处替换合作方 AES 加密秘钥";
    protected static final String HOST = "[需替换]工行API网关地址";
    protected static final String APP_ID = "[需替换]此处替换合作方 APPID";
    protected static final String API_URL = HOST + "/api/loan/batchrepayment/V1";


    public static void main(String[] args) throws ParseException {

        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8, IcbcConstants.FORMAT_JSON, APIGW_PUBLIC_KEY, IcbcConstants.ENCRYPT_TYPE_AES, AES_KEY, "", "");
        JftApiLoanOrderapplyRequestV1 request = new JftApiLoanOrderapplyRequestV1();
        request.setServiceUrl(API_URL);

        //请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
        JftApiLoanOrderapplyRequestV1.JftApiLoanOrderapplyRequestV1Biz bizContent = new JftApiLoanOrderapplyRequestV1.JftApiLoanOrderapplyRequestV1Biz();
        bizContent.setAppId("10000000000000001234");
        bizContent.setOperType("1");
        List<Map> orderList = new ArrayList<>();
        Map maps = new HashMap();
        maps.put("outVendorId", "JFT01");
        maps.put("vendorType", "1");
        maps.put("custName", "1234");
        maps.put("certNo", "123");
        maps.put("certType", "1");
        maps.put("orderId", "1111");
        maps.put("accountNo", "1");
        maps.put("amount", "1");
        maps.put("loanTerm", "12");
        bizContent.setOrderList(orderList);
        request.setBizContent(bizContent);

        JftApiLoanOrderapplyResponseV1 response;
        try {
            response = (JftApiLoanOrderapplyResponseV1) client.execute(request, System.currentTimeMillis() + "");
            if (response.isSuccess()) {
                //业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
                System.out.println("returnCode:" + response.getReturnCode());
                System.out.println("response:" + response);
            } else {
                //失败
                System.out.println("returnCode:" + response.getReturnCode());
                System.out.println("returnMsg:" + response.getReturnMsg());
            }
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }

    }
}
