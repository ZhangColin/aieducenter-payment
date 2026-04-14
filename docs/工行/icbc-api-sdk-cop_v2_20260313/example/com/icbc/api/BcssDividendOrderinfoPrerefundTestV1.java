package com.icbc;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.BcssDividendOrderinfoPrerefundRequestV1;
import com.icbc.api.response.BcssDividendOrderinfoPrerefundResponseV1;

import java.util.Arrays;

/**
 * @Author kfzx-dengry
 * @Date 2022/2/18
 */
public class BcssDividendOrderinfoPrerefundTestV1 {
    protected static final String MY_PRIVATE_KEY = ApipParamters.MY_PRIVATE_KEY;

    protected static final String APIGW_PUBLIC_KEY = ApipParamters.APIGW_PUBLIC_KEY;

    protected static final String APP_ID = ApipParamters.APP_ID;
    /**
     * APIGW（行外网关）
     */
    protected static final String API_GW_OUT = ApipParamters.API_GW_OUT;

    public final static String URL = new String("/api/bcss/dividend/orderinfo/prerefund/V1");
    private final static String SIGN_TYPE = ApipParamters.RSA2;

    public static void main(String[] args) throws IcbcApiException {

        BcssDividendOrderinfoPrerefundRequestV1 request = new BcssDividendOrderinfoPrerefundRequestV1();
        request.setServiceUrl(API_GW_OUT + URL);
        BcssDividendOrderinfoPrerefundRequestV1.BcssDividendOrderinfoPrerefundRequestBizV1 bizContent = new BcssDividendOrderinfoPrerefundRequestV1.BcssDividendOrderinfoPrerefundRequestBizV1();
        bizContent.setIcbcAppid("10000000000004097413");
        bizContent.setCorpId("2000000138");
        bizContent.setStartIndustry("25");
        //退款订单编号
        bizContent.setOutTradeNo("mTraderNo20220316011");
        //原订单编号
        bizContent.setOriOutTradeNo("mTraderNo20220316010");
        //每次请求都得变更
        bizContent.setTrxSerialNo("TrxSerialNo202203170012");
        bizContent.setCustId("00000000000000000043");
        bizContent.setDataSrc("10");
        BcssDividendOrderinfoPrerefundRequestV1.SubOrderInfo subOrderInfo = new BcssDividendOrderinfoPrerefundRequestV1.SubOrderInfo();
        subOrderInfo.setMerchantNo("2001");
        //退款子订单编号
        subOrderInfo.setSubOutTradeNo("STradeNo20220316011");

        //原子订单编号
        subOrderInfo.setOriSubOutTradeNo("STradeNo20220316010");

        BcssDividendOrderinfoPrerefundRequestV1.OrderDetail orderDetail = new BcssDividendOrderinfoPrerefundRequestV1.OrderDetail();

        orderDetail.setGoodsId("200101");
        orderDetail.setGoodsNum("2");

        subOrderInfo.setOrderDetails(Arrays.asList(orderDetail));
        bizContent.setSubOrderInfo(Arrays.asList(subOrderInfo));
        request.setBizContent(bizContent);
        System.out.println("请求示例：" + JSONObject.toJSONString(request));
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, SIGN_TYPE, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        BcssDividendOrderinfoPrerefundResponseV1 response = client.execute(request);
        System.out.println("返回示例：" + JSONObject.toJSONString(response));
    }


}
