package com.icbc.api;


import com.icbc.api.request.CacaBalcheckBalanceCheckwechatbillRequestV1;
import com.icbc.api.request.CacaBalcheckBalanceCheckwechatbillResponseV1;

import java.util.ArrayList;
import java.util.List;

public class CacaBalcheckBalanceCheckwechatbillV1Test {
    public static void main(String[] args) throws Exception{

        String MY_PRIVATE_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";
        String APP_ID = "[需替换]工行API APPID，请向工行联系获取";
        String APIGW_PUBLIC_KEY = "[需替换]工行API网关公钥，请向工行联系获取";

        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,"RSA2",
                MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);  //signType具体见申请的APP的签名类型，要一致

        CacaBalcheckBalanceCheckwechatbillRequestV1 request = new CacaBalcheckBalanceCheckwechatbillRequestV1();

        request.setServiceUrl("http://[需替换]地址/api/mybank/cardbusiness/shop/serUserManage/V1");
        //	request.setServiceUrl("https://gw.open.icbc.com.cn/api//cash/account/door/collect/V1");
        CacaBalcheckBalanceCheckwechatbillRequestV1.CacaBalcheckBalanceCheckwechatbillRequestBizV1 bizRes = new CacaBalcheckBalanceCheckwechatbillRequestV1.CacaBalcheckBalanceCheckwechatbillRequestBizV1();

        CacaBalcheckBalanceCheckwechatbillRequestV1.PrivateMap privateMap = new CacaBalcheckBalanceCheckwechatbillRequestV1.PrivateMap();
        privateMap.setAccno("0200062014200001124");
        privateMap.setCurrtype("001");
        privateMap.setLoanno("00000000000000004");
        privateMap.setBal_date("2024-02-28");
        privateMap.setBal_id("020000002028061210000002");
        privateMap.setCheck_result("1");
        privateMap.setCert_type("1");
        privateMap.setCert_number("610102198506020001");
        privateMap.setCust_name("张三");

        List<CacaBalcheckBalanceCheckwechatbillRequestV1.PrivateMap> privateList = new ArrayList<>();
        privateList.add(privateMap);
        bizRes.setPrivateMap(privateList);
        request.setBizContent( bizRes);
        CacaBalcheckBalanceCheckwechatbillResponseV1 response = new CacaBalcheckBalanceCheckwechatbillResponseV1();
        try{
            response = client.execute(request, "msgId");
            // 判断调用是否成功，进行后续业务处理
            if (response.isSuccess()) {
                // TODO 业务成功处理
                System.out.println(" success:"+response.getReturnCode()+response.getReturnMsg());
            } else {
                // TODO 失败
                System.out.println(" fail:"+response.getReturnCode()+response.getReturnMsg());
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
