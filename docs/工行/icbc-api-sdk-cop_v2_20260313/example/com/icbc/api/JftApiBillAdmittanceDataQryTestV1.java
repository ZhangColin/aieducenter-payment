package com.icbc.api;

import com.icbc.api.request.JftApiBillAdmittanceDataQryRequestV1;
import com.icbc.api.response.JftApiBillAdmittanceDataQryResponseV1;

public class JftApiBillAdmittanceDataQryTestV1 {
    protected static final String MY_PRIVATE_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";
    protected static final String APIGW_PUBLIC_KEY = "[需替换]工行API网关公钥，请向工行联系获取";
    protected static final String AES_KEY = "[需替换]此处替换合作方 AES 加密秘钥";
    protected static final String HOST = "[需替换]工行API网关地址";
    protected static final String APP_ID = "[需替换]此处替换合作方 APPID";
    protected static final String API_URL = HOST + "/api/jft/api/bill/admittance/data/qry/V1";


    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8, IcbcConstants.FORMAT_JSON, APIGW_PUBLIC_KEY, IcbcConstants.ENCRYPT_TYPE_AES, AES_KEY, "", "");
        JftApiBillAdmittanceDataQryRequestV1 request = new JftApiBillAdmittanceDataQryRequestV1();
        request.setServiceUrl(API_URL);

        //请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
        JftApiBillAdmittanceDataQryRequestV1.JftApiBillAdmittanceDataQryRequestV1Biz bizContent = new JftApiBillAdmittanceDataQryRequestV1.JftApiBillAdmittanceDataQryRequestV1Biz();
        bizContent.setAppId("1111");//合作方编号
        bizContent.setSerialNo("11");//请求序列号
        bizContent.setCustName("11");//客户名称
        bizContent.setSocialCode("11");//统一社会信用代码
        bizContent.setOutVendorId("11");
        request.setBizContent(bizContent);

        JftApiBillAdmittanceDataQryResponseV1 response;
        try {
            response = (JftApiBillAdmittanceDataQryResponseV1) client.execute(request, System.currentTimeMillis() + "");
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
