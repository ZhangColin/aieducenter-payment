package com.icbc.api.test;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcConstants;
import com.icbc.api.request.JftApiVacctSubAcctBalanceAndThredholdQueryRequestV1;
import com.icbc.api.response.JftApiVacctSubAcctBalanceAndThredholdQueryResponseV1;

/**
 * 子商户登记簿余额查询及子商户登记簿余额阈值接口
 *
 */
public class JftApiVacctSubAcctBalanceAndThredholdQueryTestV1 {
    //1、网关公钥
    protected static final String APIGW_PUBLIC_KEY = "[需替换]工行API网关公钥，请向工行联系获取";
    //2、appid
    protected static final String APP_ID = "[需替换]app_id";
    //3、密钥对认证方式，公钥在API平台登记，此处为私钥
    protected static final String MY_PRIVATE_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";

    protected static final String AES_Key = "[需替换]应用方加密串，与工行API开放平台一致";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY,IcbcConstants.CHARSET_UTF8, IcbcConstants.FORMAT_JSON,APIGW_PUBLIC_KEY,IcbcConstants.ENCRYPT_TYPE_AES,AES_Key, "", "");
        JftApiVacctSubAcctBalanceAndThredholdQueryRequestV1 request= new JftApiVacctSubAcctBalanceAndThredholdQueryRequestV1();
        //4、根据测试环境和生产环境替换相应ip和端口
        request.setServiceUrl("http://[需替换]工行API网关地址/api/jft/api/vacct/subacct/balanceandthredhold/query/V1");
        //5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
        JftApiVacctSubAcctBalanceAndThredholdQueryRequestV1.JftApiVacctSubAcctBalanceAndThredholdQueryRequestV1Biz bizContent = new JftApiVacctSubAcctBalanceAndThredholdQueryRequestV1.JftApiVacctSubAcctBalanceAndThredholdQueryRequestV1Biz();
        //必输参数
        bizContent.setAppId(APP_ID);
        bizContent.setOutVendorId("h201905231005");
        bizContent.setAcctNo("");
        request.setBizContent(bizContent);
        System.out.println("request:" + request.toString());

        JftApiVacctSubAcctBalanceAndThredholdQueryResponseV1 response;
        try {
            response = (JftApiVacctSubAcctBalanceAndThredholdQueryResponseV1) client.execute(request, System.currentTimeMillis()+"");

            if (response.isSuccess()) {
                //6、业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
                System.out.println("ReturnCode:"+response.getReturnCode());
                System.out.println("response:" + response);
            } else {
                //失败
                System.out.println("ReturnCode:"+response.getReturnCode());
                System.out.println("ReturnMsg:"+response.getReturnMsg());
            }
        }catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }
}
