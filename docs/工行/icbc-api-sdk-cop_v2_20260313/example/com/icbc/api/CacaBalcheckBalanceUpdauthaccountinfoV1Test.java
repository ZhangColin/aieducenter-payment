package com.icbc.api;


import com.icbc.api.request.CacaBalcheckBalanceUpdauthaccountinfoRequestV1;
import com.icbc.api.request.CacaBalcheckBalanceUpdauthaccountinfoResponseV1;

import java.util.ArrayList;
import java.util.List;

public class CacaBalcheckBalanceUpdauthaccountinfoV1Test {
    public static void main(String[] args) throws Exception{

        String MY_PRIVATE_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";
        String APP_ID = "[需替换]工行API APPID，请向工行联系获取";
        String APIGW_PUBLIC_KEY = "[需替换]工行API网关公钥，请向工行联系获取";

        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,"RSA2",
                MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);  //signType具体见申请的APP的签名类型，要一致

        CacaBalcheckBalanceUpdauthaccountinfoRequestV1 request = new CacaBalcheckBalanceUpdauthaccountinfoRequestV1();

        request.setServiceUrl("http://[需替换]地址/api/mybank/cardbusiness/shop/serUserManage/V1");
        //	request.setServiceUrl("https://gw.open.icbc.com.cn/api//cash/account/door/collect/V1");
        CacaBalcheckBalanceUpdauthaccountinfoRequestV1.CacaBalcheckBalanceUpdauthaccountinfoRequestV1Biz bizRes = new CacaBalcheckBalanceUpdauthaccountinfoRequestV1.CacaBalcheckBalanceUpdauthaccountinfoRequestV1Biz();

        CacaBalcheckBalanceUpdauthaccountinfoRequestV1.PrivateMap privateMap = new CacaBalcheckBalanceUpdauthaccountinfoRequestV1.PrivateMap();
        privateMap.setAccno("02000620292136265");
        List<CacaBalcheckBalanceUpdauthaccountinfoRequestV1.PrivateMap> privateList = new ArrayList<>();
        privateList.add(privateMap);
        bizRes.setPrivateMap(privateList);
        request.setBizContent( bizRes);
        CacaBalcheckBalanceUpdauthaccountinfoResponseV1 response = new CacaBalcheckBalanceUpdauthaccountinfoResponseV1();
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
