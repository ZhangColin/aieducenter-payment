package com.icbc;

import com.alibaba.fastjson.JSON;
import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.BcssMemberDividendChangelistQueryRequestV1;
import com.icbc.api.response.BcssMemberDividendChangelistQueryResponseV1;

/**
 * BCSS红利变动查询接口
 */
public class BcssMemberDividendChangelistQueryTestV1 {
    protected static final String MY_PRIVATE_KEY = ApipParamters.MY_PRIVATE_KEY;

    protected static final String APIGW_PUBLIC_KEY = ApipParamters.APIGW_PUBLIC_KEY;

    protected static final String APP_ID = ApipParamters.APP_ID;
    /**
     * APIGW（行外网关）
     */
    protected static final String API_GW_OUT = ApipParamters.API_GW_OUT;

    public final static String URL = new String("/bcss/member/dividend/changelist/query/V1");
    private final static String SIGN_TYPE = ApipParamters.RSA2;

    public static void main(String[] args) throws IcbcApiException {

        BcssMemberDividendChangelistQueryRequestV1 request = new BcssMemberDividendChangelistQueryRequestV1();
        request.setServiceUrl(API_GW_OUT + URL);
        BcssMemberDividendChangelistQueryRequestV1.BcssMemberDividendChangelistQueryRequestBizV1 bizContent = new BcssMemberDividendChangelistQueryRequestV1.BcssMemberDividendChangelistQueryRequestBizV1();
        bizContent.setIcbcAppid(APP_ID);
        bizContent.setCorpId("2000000138");
        bizContent.setCustId("00000000000000000181");
        bizContent.setQueryType("1");
        bizContent.setBeginDate("2022-01-22 17:00:00");
        bizContent.setEndDate("2022-03-22 17:00:00");
        bizContent.setPageNum("1");
        bizContent.setPageSize("10");
        request.setBizContent(bizContent);
        System.out.println("请求示例：" + JSONObject.toJSONString(request));
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, SIGN_TYPE, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

        BcssMemberDividendChangelistQueryResponseV1 response = null;
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
