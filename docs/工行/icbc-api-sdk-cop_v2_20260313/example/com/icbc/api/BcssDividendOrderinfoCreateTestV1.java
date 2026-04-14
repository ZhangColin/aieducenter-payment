package com.icbc;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.BcssDividendOrderinfoCreateRequestV1;
import com.icbc.api.response.BcssDividendOrderinfoCreateResponseV1;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @Author kfzx-dengry
 * @Date 2022/2/18
 */
public class BcssDividendOrderinfoCreateTestV1 {
    protected static final String MY_PRIVATE_KEY = ApipParamters.MY_PRIVATE_KEY;

    protected static final String APIGW_PUBLIC_KEY = ApipParamters.APIGW_PUBLIC_KEY;

    protected static final String APP_ID = ApipParamters.APP_ID;
    /**
     * APIGW（行外网关）
     */
    protected static final String API_GW_OUT = ApipParamters.API_GW_OUT;

    public final static String URL = new String("/api/bcss/dividend/orderinfo/create/V1");
    private final static String SIGN_TYPE = ApipParamters.RSA2;

    public static void main(String[] args) throws IcbcApiException {

        BcssDividendOrderinfoCreateRequestV1 request = new BcssDividendOrderinfoCreateRequestV1();
        request.setServiceUrl(API_GW_OUT + URL);
        BcssDividendOrderinfoCreateRequestV1.BcssDividendOrderinfoCreateRequestBizV1 bizContent = new BcssDividendOrderinfoCreateRequestV1.BcssDividendOrderinfoCreateRequestBizV1();
        bizContent.setIcbcAppid(APP_ID);
        bizContent.setCorpId("2000000138");
        bizContent.setStartIndustry("25");
        bizContent.setDataSrc("2");
        bizContent.setOutTradeNo("mTraderNo20220316012");
        bizContent.setTotalAmt("1000");
//        bizContent.setCustId("00000000000000000181");
        bizContent.setCustId("00000000000000000043");
        bizContent.setTrxSerialNo("TrxSerialNo20220316012");
        bizContent.setOrderName("测试订单202203116012");
        BcssDividendOrderinfoCreateRequestV1.SubOrderInfo subOrderInfo = new BcssDividendOrderinfoCreateRequestV1.SubOrderInfo();
        bizContent.setSubOrderInfo(Arrays.asList(subOrderInfo));
        subOrderInfo.setMerchantNo("2001");
        subOrderInfo.setSubOutTradeNo("STradeNo20220316012");
        subOrderInfo.setSubTotalAmt("1000");
        subOrderInfo.setExtendsInfo("测试子订单");
        BcssDividendOrderinfoCreateRequestV1.OrderDetails orderDetails = new BcssDividendOrderinfoCreateRequestV1.OrderDetails();
        subOrderInfo.setOrderDetails(Arrays.asList(orderDetails));
        orderDetails.setGoodsId("200101");
        orderDetails.setGoodsNum("2");
        orderDetails.setGoodsAmt("500");
        orderDetails.setGoodsTotalAmt("1000");
        orderDetails.setIndustryExtendsInfo("测试新增");
        request.setBizContent(bizContent);
        System.out.println("请求示例：" + JSONObject.toJSONString(request));
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, SIGN_TYPE, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        BcssDividendOrderinfoCreateResponseV1 response = client.execute(request);
        System.out.println("返回示例：" + JSONObject.toJSONString(response));
    }

    public static String getTimeStamp() {
        Date day = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(day);
    }

}
