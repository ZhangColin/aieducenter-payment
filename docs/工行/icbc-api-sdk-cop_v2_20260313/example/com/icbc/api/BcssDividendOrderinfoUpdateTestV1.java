package com.icbc;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.BcssDividendOrderinfoUpdateRequestV1;
import com.icbc.api.response.BcssDividendOrderinfoUpdateResponseV1;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author kfzx-dengry
 * @Date 2022/2/18
 */
public class BcssDividendOrderinfoUpdateTestV1 {
    protected static final String MY_PRIVATE_KEY = ApipParamters.MY_PRIVATE_KEY;

    protected static final String APIGW_PUBLIC_KEY = ApipParamters.APIGW_PUBLIC_KEY;

    protected static final String APP_ID = ApipParamters.APP_ID;
    /**
     * APIGW（行外网关）
     */
    protected static final String API_GW_OUT = ApipParamters.API_GW_OUT;

    public final static String URL = new String("/api/bcss/dividend/orderinfo/update/V1");
    private final static String SIGN_TYPE = ApipParamters.RSA2;

    public static void main(String[] args) throws IcbcApiException {

        BcssDividendOrderinfoUpdateRequestV1 request = new BcssDividendOrderinfoUpdateRequestV1();
        request.setServiceUrl(API_GW_OUT + URL);
        BcssDividendOrderinfoUpdateRequestV1.BcssDividendOrderinfoUpdateRequestBizV1 bizContent = new BcssDividendOrderinfoUpdateRequestV1.BcssDividendOrderinfoUpdateRequestBizV1();
        bizContent.setIcbcAppid(APP_ID);
        bizContent.setCorpId("2000000138");
        bizContent.setStartIndustry("25");
        bizContent.setOutTradeNo("mTraderNo20220316010");

//        退货下单时上送的退款外部订单号，退款更新是必输
        bizContent.setRefundOutTradeNo("");

        bizContent.setMorderNo("MO202203160000115127");

//        退款下单时返回的红利退款编号
        bizContent.setMrefundNo("");

//        操作类型：0-支付 1-退货
        bizContent.setOperation("0");

//        处理结果：0-成功 1-失败 2-取消
        bizContent.setDealResult("0");

//        是否使用红利抵扣：0-否 1-是
        bizContent.setUseDividendFlag("1");

//        支付方式，支付成功时上送 ：22-一体化聚合微信支付
        bizContent.setPayType("22");

//        支付序列号，支付成功时上送,值为支付时上送给收单的外部订单号
        bizContent.setPaySerialNumber("");

//        支付完成时间，格式为 YYYY-MM-DD HH:MM:SS，支付成功时上送（非必填）
        bizContent.setPayEndDate(getTimeStamp());

//        第三方流水号，例如微信支付时微信的支付序列号，支付成功时上送（非必填）
        bizContent.setOrgLseqno("");

//        支付交易流水号，例如调用工行支付接口返回的工行订单号，支付成功时上送
        bizContent.setTrxSeqno("STradeNo20220316010");

        bizContent.setEcAccno("");
        bizContent.setCardno("");
        request.setBizContent(bizContent);
        System.out.println("请求示例：" + JSONObject.toJSONString(request));
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, SIGN_TYPE, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        BcssDividendOrderinfoUpdateResponseV1 response = client.execute(request);
        System.out.println("返回示例：" + JSONObject.toJSONString(response));
    }

    public static String getTimeStamp() {
        Date day = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(day);
    }

}
