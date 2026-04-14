package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.MedicalWechatRefundsNotifyRequsetV1;
import com.icbc.api.response.MedicalWechatRefundsNotifyResponseV1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MedicalWechatRefundsNotifyTestV1 {

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
        MedicalWechatRefundsNotifyRequsetV1 requset = new MedicalWechatRefundsNotifyRequsetV1();
        requset.setServiceUrl("http://ip:port/api/mybank/medical/mixorder/refundsnotify/V1");
        //组装参数
        MedicalWechatRefundsNotifyRequsetV1.MedicalWechatRefundsNotifyRequsetV1Biz bizContext = new MedicalWechatRefundsNotifyRequsetV1.MedicalWechatRefundsNotifyRequsetV1Biz();

        bizContext.setHospitalId("1234567890");
        bizContext.setMixTradeNo("202204022005169952975171534816");
        bizContext.setSubMchid("o1F5v6WOqkITYoez9ljYA2PLB100");
        bizContext.setMedRefundTotalFee(100000);
        bizContext.setMedRefundGovFee(10000);
        bizContext.setMedRefundSelfFee(0);
        bizContext.setMedRefundSelfFee(0);
        bizContext.setRefundTime("2023-10-05T15:30:45+08:00");

        requset.setBizContent(bizContext);
        try {
            log.debug("request:"+ JSON.toJSONString(requset));
            MedicalWechatRefundsNotifyResponseV1 response1 = client.execute(requset);
            log.debug("response:" + JSON.toJSONString(response1));
        } catch (IcbcApiException e) {
            log.error(e.getMessage());
        }

		
	}

	
}
