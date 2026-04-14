package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.MedicalWechatSubmitOrderRequsetV1;

import java.util.ArrayList;
import java.util.List;

import com.icbc.api.response.MedicalWechatSubmitOrderResponseV1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MedicalWechatSubmitOrderTestV1 {

    private final Logger log = LoggerFactory.getLogger(getClass());
        
	//1.工行侧网关公钥
	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----";

	//2.appid
	protected static final String APP_ID = "10000000000000002156";

	//合作方私钥
	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----";
		
	public static void main(String[] args) throws Exception {
        //aes秘钥
		String priKey = "-----请使用正确的aes秘钥-----";
		//初始化客户端
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA", MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, "AES", priKey, (String)null, (String)null, (String)null, (String)null, (String)null);

        //构建请求
        MedicalWechatSubmitOrderRequsetV1 requset = new MedicalWechatSubmitOrderRequsetV1();
        requset.setServiceUrl("http://ip:port/api/mybank/medical/mixorder/submitorder/V1");
        //组装参数
        MedicalWechatSubmitOrderRequsetV1.MedicalWechatSubmitOrderRequsetV1Biz bizContext = new MedicalWechatSubmitOrderRequsetV1.MedicalWechatSubmitOrderRequsetV1Biz();

        bizContext.setIcbcSerialNo("12345678901234567890");
        bizContext.setHospitalId("1234567890");
        bizContext.setMixPayType("CASH_ONLY");
        bizContext.setOrderType("REG_PAY");
        bizContext.setSubOpenid("openid1234567890");
        bizContext.setSubAppid("wx1cc0a67d4d37891f");
        bizContext.setSubMchid("o1F5v6WOqkITYoez9ljYA2PLB100");

        MedicalWechatSubmitOrderRequsetV1.UserInfo payer = new MedicalWechatSubmitOrderRequsetV1.UserInfo();
        payer.setName("张三");
        payer.setIdDigest("123456789012345679");
        payer.setCardType("ID_CARD");
        bizContext.setPayer(payer);

        bizContext.setPayForRelatives(Boolean.TRUE);
        MedicalWechatSubmitOrderRequsetV1.UserInfo relative = new MedicalWechatSubmitOrderRequsetV1.UserInfo();
        relative.setName("John Doe");
        relative.setIdDigest("123456789012345678");
        relative.setCardType("ID_CARD");
        bizContext.setPayer(relative);

        bizContext.setOutTradeNo("outTradeNo123456");
        bizContext.setSerialNo("1217752501201");
        bizContext.setPayOrderId("payOrderId1234567890");
        bizContext.setPayAuthNo("payAuthNo1234567890");
        bizContext.setGeoLocation("geoLocation1234567890");
        bizContext.setCityId("510100");
        bizContext.setMedInstName("北大医院");
        bizContext.setMedInstNo("1217752501201407033233368318");
        bizContext.setMedInsOrderCreateTime("2023-10-05T15:30:45+08:00");
        bizContext.setTotalFee(1000);
        bizContext.setMedInsGovFee(1000);
        bizContext.setMedInsSelfFee(1000);
        bizContext.setMedInsOtherFee(1000);
        bizContext.setMedInsCashFee(1000);
        bizContext.setWechatPayCashFee(1000);

        List<MedicalWechatSubmitOrderRequsetV1.CashAddDetail> cashAddDetail = new ArrayList<>();
        MedicalWechatSubmitOrderRequsetV1.CashAddDetail addDetail = new MedicalWechatSubmitOrderRequsetV1.CashAddDetail();
        addDetail.setCashAddType("FREIGHT");
        addDetail.setCashAddFee(1000);
        cashAddDetail.add(addDetail);
        bizContext.setCashAddDetail(cashAddDetail);

        List<MedicalWechatSubmitOrderRequsetV1.CashReduceDetail> cashReduceDetail = new ArrayList<>();
        MedicalWechatSubmitOrderRequsetV1.CashReduceDetail reduceDetail = new MedicalWechatSubmitOrderRequsetV1.CashReduceDetail();
        reduceDetail.setCashReduceType("PHARMACY_DISCOUNT");
        reduceDetail.setCashReduceFee(1000);
        cashReduceDetail.add(reduceDetail);
        bizContext.setCashReduceDetail(cashReduceDetail);

        bizContext.setCallbackUrl("https://ip:port/notice");
        bizContext.setPrepayId("wx201410272009395522657a690389285100");
        bizContext.setPassthroughRequestContent("wx201410272009395522657a690389285100");
        bizContext.setExtends_field("{}");
        bizContext.setAttach("{}");
        bizContext.setChannelNo("{}");

        requset.setBizContent(bizContext);
        try {
            log.debug("request:"+ JSON.toJSONString(requset));
            MedicalWechatSubmitOrderResponseV1 response1 = client.execute(requset);
            log.debug("response:" + JSON.toJSONString(response1));
        } catch (IcbcApiException e) {
            log.error(e.getMessage());
        }
    }
}
