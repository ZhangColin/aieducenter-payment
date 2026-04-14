package com.icbc.api;

import java.util.Date;
import java.util.logging.Logger;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.CertVoiceprintUserinfosqueryRequestV1;
import com.icbc.api.request.CertVoiceprintUserinfosqueryRequestV1.CertVoiceprintUserinfosqueryRequestV1Biz;
import com.icbc.api.response.CertVoiceprintUserinfosqueryResponseV1;

public class CertVoiceprintUserinfosqueryTest {
	//	调用方自己的AppId
	private static final String APP_ID = new String("1000000s00000040a97529");
	//	调用方私钥
	protected static final String MY_PRIVATE_KEY = new String("-----请使用正确的APP私钥-----EvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDSVzg5SO6pIdKUH6nuVd6Oze029R7g+nCBBtm0VlyPpHEXAPbPe2JQgIIqwXtoRm6AkpixSzkp8x74A9H9FfdiKN+iTXjvdNEx+sl6BmnSm3yx6GxAeCtFPtokkM2eiO2zLMQu5J38cCN3z1TIhaVLdvBRe5juC6qrWSm9i2fvojoKCImpDZzSbZNwva8C6eK8xBqs7bvYHf2mAM+9p92rAODz98RzPZU6SkIz2zvOHL4BtHwgCAFHP8yA7palLBUdcdpJXORL1aPgO1NwE5beEqM4FOCqNdMbGP9Wl4GIcH/vlbcaKLTb3OfGTyr1r0RgOgNA3xXOAquwAFsPaAB7AgMBAAECggEAS3O4Gmx8GDWmyTW2imVJKabbAgzZ39BWjSI89ADKwUg2vQ6+el0DPmdqyhWPxC8Hsp1joq2eOtsYb9VG/Wdkz1RkLF9oE1WvOSOCJICqcz8NcvAx7Mpuky9hpx4+dDEgO1bCpSOjPIupXXAFNM0kJzbqLACPMk0DEuYwH+sjBIVhgEd07MwGAqYIkgrVtotHWtdaha8ogIZ/d6oiPGcpQ4H90khF4XCx/pqWkLs6SkWeypAVCPmDSiZPi49dudefoIx0y6ZbIbGnLXVYWrkAb8Al92VY/U+EI+2+TJXkOs60UdqffyLb2IB2xU1D1GMSv28fG+CxAEj9mLr9g3cm8QKBgQDwcxBGVZ7MARAIks59Jq5+MjDoOMO71UDScT99/4dyUbieodMwwiKHMI99oa01KpanS5T3cn+O5vJC4nguVHI6sxrEthuik4djDMsPwlijNxYQ0D4z6QsTLbThwNkil/zS0aXfyJwXg0kJYQpgyIG2LcBLeWuzamowjDZmfEos8wKBgQDf8ases7I2a4FKRRi4CTHSb7Euwwdm+W8FqFF8W66E9BV3lAMZ2llAKKvIOfSrxZN4nc7w1MtcC6ypA8HTHePnMp4zEtWCDeNf7MtbTA8XwOWp4JlfufEYmGDhANxtgHGYT+BimOHV7HPy5F60iDQyUUAeIQeMJnvw6YYiHAAgWQKBgQC7mytxlVBnH9Ugu1q9LzcqY4LhTZj6rzL6fz3o0z0dJ0IDE4QyIObL0EnE3ibEusv9D+3U+9Mm7m7LXOQkUeMmhaQ3I1eAi9cwo0S0sRBzmIFw8On3mrb3acwd8Ff6s9ehrc3cQyv29IpB9SMhzI/YUu4cdUphHIBqDJxNbmB5nQKBgQCeSeBtJBC8XIKXb32IX59ioeX4Kowp4fbyZcWrA9a4TRp7WhhTBr39FoJNVYG2hEG3iLvECHTxS6MwF+LBiagUO/gMG6zkb1Ke8+3NS1aDsKnkyEdAnVpnyxT3FTe5AofIY/UzW+KM5cQTFA5Ixtk2E64hXbFLA8ypPTa9F1OTiQKBgQDNewh/9tIXYybote82xxfelIrDPqNC4uBOJnCv6jgLPZ+TzVjCJ+jtlspBTHrlpYc2BJ0/JYJcqzlREbirbjEeOuaAtfG/gHb3iN+4kNj0I+W68g+uWoO2xMhiur0yoB7FGQrXUBQji7BHkzi3riq/cBztD4zUwIYyepPLZ7V6uA==");
	//	API网关公钥
	protected static final String APIGW_PUBLIC_KEY = new String("-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB");
	//	API请求地址
	private static final String API_SERVICE_URL = new String("http://ip:port/api");
	
	public static void main(String[] args) {
//		签名类型为SHA256WithRSA时，传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,IcbcConstants.SIGN_TYPE_RSA2,MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
		CertVoiceprintUserinfosqueryRequestV1 request = new CertVoiceprintUserinfosqueryRequestV1();
		
//		测试环境替换ip和端口，生成环境已有默认值，此语句可删除
		request.setServiceUrl(API_SERVICE_URL + "/cert/voiceprint/userinfosquery/V1");
		Logger log = Logger.getLogger(CertVoiceprintUserinfosqueryTest.class.getName());
//		请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
		CertVoiceprintUserinfosqueryRequestV1Biz bizContent =  new CertVoiceprintUserinfosqueryRequestV1Biz();
		bizContent.setChannel("REAF01");
		bizContent.setAppName("F-REAF");
		bizContent.setAppVersion("1.0");
		bizContent.setAppInfo("F-REAF");
		bizContent.setTrCode("asdfasdfasfdadsfas");
		bizContent.setTrxZone("1001");
		bizContent.setProgramName("Userinfosquery");
		bizContent.setUserId("202011032");
		bizContent.setVprType("1");
		request.setBizContent(bizContent);
		CertVoiceprintUserinfosqueryResponseV1 response;
		try {
//		    response = client.execute(request, "msgId");
			response = client.execute(request, "" + new Date().getTime());
			log.info("Msg_id:" + response.getMsgId());
		    if (response.isSuccess()) {
		        // 业务成功处理
		    	log.info("ReturnCode:" + response.getReturnCode());
				log.info("response:" + response);
		    } else {
		        // 失败
		    	log.info("ReturnCode:" + response.getReturnCode());
				log.info("ReturnMsg:" + response.getReturnMsg());
		    }		
//		    log.info("response.toJSONString: "+JSON.toJSONString(response));
//		    log.info("response.result: " + response.getResult());
//		    log.info("response.exMsg: " + response.getExMsg());
//		    log.info("response.exCode: " + response.getExCode());
		} catch (IcbcApiException e) {
		    e.printStackTrace();
		}
	}
	
}
