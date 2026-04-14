package com.icbc;


import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcConstants;
import com.icbc.api.UiIcbcClient;
import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.BcssMemberAccountRechargeRequestV2;
import com.icbc.api.response.BcssMemberAccountRechargeResponseV2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author kfzx-ouhz
 */
public class BcssMemberAccountRechargeTestV2 {
    protected static final String MY_PRIVATE_KEY = ApipParamters.MY_PRIVATE_KEY;

    protected static final String APIGW_PUBLIC_KEY = ApipParamters.APIGW_PUBLIC_KEY;

    protected static final String APP_ID = ApipParamters.APP_ID;
    /**
     * APIGW（行外网关）
     */
    protected static final String API_GW_OUT = ApipParamters.API_GW_OUT;

    public final static String URL = new String("/api/bcss/member/account/recharge/V2");
    private final static String SIGN_TYPE = ApipParamters.RSA2;

    public static void main(String[] args) {

        try {
            test_recharge();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 充值测试类
     */
    public static void test_recharge() throws Exception {
        BcssMemberAccountRechargeRequestV2 request = new BcssMemberAccountRechargeRequestV2();
        request.setServiceUrl(API_GW_OUT + URL);
        BcssMemberAccountRechargeRequestV2.BcssAccountRechargeRequestBizV2 bizContent = new BcssMemberAccountRechargeRequestV2.BcssAccountRechargeRequestBizV2();
        //设置参数
        List<BcssMemberAccountRechargeRequestV2.AccountRechargeDtlReqDto> dataList = new ArrayList<>();
        BcssMemberAccountRechargeRequestV2.AccountRechargeDtlReqDto rechargeDtlReq = new BcssMemberAccountRechargeRequestV2.AccountRechargeDtlReqDto();
        rechargeDtlReq.setAmount("1");
        rechargeDtlReq.setTrxDiscp("给odi充值");
        rechargeDtlReq.setTradeType("1");
        rechargeDtlReq.setCorgOrderNo("1");
        HashMap<String, String> userInfoMap = new HashMap<>();
        userInfoMap.put("dt", "1");
        userInfoMap.put("clientTransNo", "1");
        userInfoMap.put("cardNo", "1");
        userInfoMap.put("protocolType", "000001");
        rechargeDtlReq.setUserInfo(JSONObject.toJSONString(userInfoMap));
        dataList.add(rechargeDtlReq);
        bizContent.setCorpId("2000000067");
        bizContent.setTimeStamp("2021-07-08 17:06:45");
        bizContent.setMerNo("00000000000000000000");
        bizContent.setImeiNo("1");
        bizContent.setManufacturerId("1");
        bizContent.setClientType("3");
        bizContent.setClientTransNo("1");
        bizContent.setApplyNo("3");
        bizContent.setDataList(dataList);
        request.setBizContent(bizContent);
        DefaultIcbcClient client = getIcbcClient();
        System.out.println("请求示例：" + JSONObject.toJSONString(request));
        BcssMemberAccountRechargeResponseV2 response = client.execute(request);
        System.out.println("返回示例：" + JSONObject.toJSONString(response));
    }

    /**
     * api
     *
     * @return
     */
    private static DefaultIcbcClient getIcbcClient() {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, SIGN_TYPE, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        return client;
    }

    /**
     * UI
     *
     * @return
     */
    private static UiIcbcClient getUiClinet() {
        UiIcbcClient client = new UiIcbcClient(APP_ID, SIGN_TYPE, MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8);
        return client;
    }
}

