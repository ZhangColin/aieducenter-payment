package com.icbc;

import com.alibaba.fastjson.JSON;
import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.BcssMemberDividendAccountbalanceQueryRequestV1;
import com.icbc.api.response.BcssMemberDividendAccountbalanceQueryResponseV1;

import java.util.ArrayList;
import java.util.List;

/**
 * BCSS我的红利余额查询接口
 */
public class BcssMemberDividendAccountbalanceQueryTestV1 {
    protected static final String MY_PRIVATE_KEY = ApipParamters.MY_PRIVATE_KEY;

    protected static final String APIGW_PUBLIC_KEY = ApipParamters.APIGW_PUBLIC_KEY;

    protected static final String APP_ID = ApipParamters.APP_ID;
    /**
     * APIGW（行外网关）
     */
    protected static final String API_GW_OUT = ApipParamters.API_GW_OUT;

    public final static String URL = new String("/bcss/member/dividend/accountbalance/query/V1");
    private final static String SIGN_TYPE = ApipParamters.RSA2;

    public static void main(String[] args) throws IcbcApiException {

        BcssMemberDividendAccountbalanceQueryRequestV1 request = new BcssMemberDividendAccountbalanceQueryRequestV1();
        request.setServiceUrl(API_GW_OUT + URL);
        BcssMemberDividendAccountbalanceQueryRequestV1.BcssMemberDividendAccountbalanceQueryRequestBizV1 bizContent = new BcssMemberDividendAccountbalanceQueryRequestV1.BcssMemberDividendAccountbalanceQueryRequestBizV1();
        bizContent.setIcbcAppid(APP_ID);
        bizContent.setCorpId("2000000138");
        bizContent.setCustId("00000000000000000181");

        request.setBizContent(bizContent);
        System.out.println("请求示例：" + JSONObject.toJSONString(request));
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, SIGN_TYPE, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

        BcssMemberDividendAccountbalanceQueryResponseV1 response = null;
        try {
            response = client.execute(request);
            System.out.println("返回示例：" + JSONObject.toJSONString(response));
            if (response.getReturnCode() == 0) {
                // 业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
                System.out.println("returnCode:" + response.getReturnCode());
                System.out.println("response:" + JSON.toJSONString(response));
            } else {
                System.out.println("response:" + JSON.toJSONString(response));
                System.out.println("returnCode:" + response.getReturnCode());
                System.out.println("returnMsg:" + response.getReturnMsg());
            }
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }

}
