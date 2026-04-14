package com.icbc.api;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.CidpriskCommonDataRequestSupplyV1;
import com.icbc.api.response.CidpriskCommonDataResponseSupplyV1;


public class CidpriskCommonDataSupplyV1Test {

    protected static final String APIGW_PUBLIC_KEY = "xxxx";
    protected static final String APP_ID = "123";
    protected static final String MY_PRIVATE_KEY = "xxxx";
    private static final String SIGN_TYPE = "RSA2";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, SIGN_TYPE, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        CidpriskCommonDataRequestSupplyV1 request = new CidpriskCommonDataRequestSupplyV1();
        request.setServiceUrl("https://api.icbc.com.cn/cidprisk/common/data/supply/v1");
        CidpriskCommonDataRequestSupplyV1.CidpriskCommonDataRequestSupplyV1Biz biz = new CidpriskCommonDataRequestSupplyV1.CidpriskCommonDataRequestSupplyV1Biz();
        biz.setAppId("GCMSINCF");
        biz.setAppSerNo("GCMSINCF2025010112000093984753");
        biz.setBizType("经营快递");
        biz.setBizScene("助商贷");
        biz.setDataSource("GDFH");
        biz.setDataProduct("美宜佳");
        biz.setOrgId("001920129");
        biz.setName("张三");
        biz.setIdType("0");
        biz.setIdNumber("30012319990101293X");
        biz.setServiceAlias("CIDP_RISK_COMMON_DATA_SUPPLY_V1.0");

        JSONObject msgInfo = new JSONObject();
        msgInfo.put("reservedVar1", "字符型参数1");
        msgInfo.put("reservedVar2", "字符型参数2");
        msgInfo.put("reservedVar3", "字符型参数3");
        msgInfo.put("reservedNum1", 100);
        msgInfo.put("reservedNum2", 200);
        msgInfo.put("reservedNum3", 99829);
        msgInfo.toJSONString();

        biz.setMsgContent(msgInfo.toJSONString());
        biz.setTranInfo("123");
        biz.setOriginMsg("{\"info1\":\"12\",\"info2\":\"test\"}");
        request.setBizContent(biz);
        try {
            CidpriskCommonDataResponseSupplyV1 response = client.execute(request, "msgId");
            if (response.isSuccess()) {
                //成功-处理业务逻辑
            } else {
                //失败-处理业务逻辑
            }
        } catch (IcbcApiException e) {
            // 异常-处理逻辑
        } catch (Exception e) {
            // 其他异常-处理逻辑
        }
    }
}
