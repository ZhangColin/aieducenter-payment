package com.icbc.api;

import com.alibaba.fastjson.JSON;
import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcConstants;
import com.icbc.api.request.GyjrB2bCorporationDetailQueryRequestV1;
import com.icbc.api.response.GyjrB2bCorporationDetailQueryResponseV1;

public class GyjrB2bCorporationDetailQueryTestV1 {
	private  static final Logger LOGGER = LoggerFactory.getLogger(GyjrB2bCorporationDetailQueryTestV1.class);
	private static final String LOGP_REFIX = "GyjrB2bCorporationDetailQueryTestV1";
	// APPID(变量值需替换)
	protected static final String APP_ID = "11000000000000002792";
	// 应用私钥(变量值需替换)
	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCZYtoGG+qYy+y3SfMXsiF8+NwZd8OXmXl74GyYqJPaCwRQ9jIjmNkGD5lvoakEvGjsD86y1oQsGEBJXNKIb1oN5FF8hHbJqY36FnCRuMs1OBFywuuqaov4Xun1wLvQUeb83rj63PuMefj0DMeNe/L7yPUwtFeFBPObflVhR8U9cpDbtXX2xhzPxnrAQUBjUKaA5IFgozvadTaeJS65x3DH8ZLd4G09Jah22PWdUimDEhqp/elVsFxC5V8s7jgg5ibrXk1xtyaHOQhaXEuOZMLq/cV/RmnBJ+u3qsnENQo/xkD8uc4/N/NXyFbb/BbjKsoVC2ft4J58RHVUp2KEnoPDAgMBAAECggEAJYR2S5Q8d485oUNnQF6Drmu4eko+/y+89T3ZkiV5tJWJQrxVWzuhqcfbAeEK1Gu5RjQ/nKK3xYlRACDCJjkGT+uOuuQzofPlFEy00A/hNG04Z1eiCRbykZe1pjS0O6os06Euob23kY6sBBW0Ibk1n7xxrK4P0it54oiuHZNOfU7MTNaYcBRf/q48fvd8r/h+zQClWpsRJSWZH6dU70FUfbcHtoUB4Lg9X2R24CUnKIJB4q/cRuGAEUbqyu+A5epZcqpRju+W2O+/C8JM65tdFQ0qA51H8Vmy8CRdgewnepK6bvZF1uX2bQoWfS2HAmjp6YhF16ZY4PMV5cBjzob7IQKBgQDokDdYDqGx52JougKuRxOL3bi4sOuWXSU7JL94cpD7jvgDeuVWDZ7qIHI39JM68NGdks7fi4J583mSM6t7wshCO/WJGdMfNE/l/+mPcIdupq1CUqvxmf5/gmVvHiU9r8wpRaG1BJpabr0WxDvZAa94bISZBdoz49qH3rWeSXWGOwKBgQCo1/ue/3o9T/gP5apwInrlVl0oW+ZYzEGrOHzb9Ug4fNrwTNW228820ky0lBSCtwY5PBQhGB3d7AoonLm3I4TIVo46qVHXmNFvVj4WMHL9Ubm0w8mGMlCLcrPvtWX7qZbvOFgwhsvC9Z7B4I7kKXnYNwIrwergJW4Q9vpVYl64GQKBgQCSxdm34FwaHC7Wa3afqMnkPsWDAl1co2KI5hNVH/DyYnOSNrCgU65Ukq+jMt7slumj6fBX0wNAlfHG3IdAb1jVvGOk6EB+9BPs00sDNQC0S8sTDXvo2chtjs0XiM32FYnjDkgVJ1eG9VavFJ+0wESO2uYAcVoZ35aeo2a6BCCA2wKBgQCGG3b6JQWcqWXoiozDoD9o5R39LnFZWnNWYfmaY8pHQ7RKZVzibti5PHQ8gdmAsYq57yQ13Qfr+s1e0I0aD5Yp4cX6hf5BdlnzStbpnPXYiw8rqs7oThJ3kFWnMbedGmiN/XzenlI9ugWtDIWyZ9clixuoUdAS1OgFhYX0R7vgEQKBgHvj1zfRlWVj3i27L/lSCMMoBSd2f5S/n+Zers+OGp6pqrAFVhNOZTyC9FnvIHBdAgtS5zDwy2dNV+VlP+q/tpv7L3L8ZLjq8sCZj2Wa7cNE2m9FTTGMbkirn/BQHjVB2jurFiJIwovlaZWassVvEVpdnHltDrIkmk9z213+6ZE4";

	// API网关公钥(变量值需替换)
	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
	public static void main(String[] args) {
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

		try {
			GyjrB2bCorporationDetailQueryRequestV1 request = new GyjrB2bCorporationDetailQueryRequestV1();

			// 根据实际环境替换相应的域名
			request.setServiceUrl("http://ip:port/api/gyjr/b2b/corporation/detail/query/V1");

			GyjrB2bCorporationDetailQueryRequestV1.GyjrB2bCorporationDetailQueryRequestV1Biz bizContent = new GyjrB2bCorporationDetailQueryRequestV1.GyjrB2bCorporationDetailQueryRequestV1Biz();
			GyjrB2bCorporationDetailQueryRequestV1.GyjrB2bCorporationDetailQueryRequestV1Biz.TransInfo transInfo = new GyjrB2bCorporationDetailQueryRequestV1.GyjrB2bCorporationDetailQueryRequestV1Biz.TransInfo();
			transInfo.setProtocolCode("GYJR20220510113117804911933");// 请修改为实际上送值
			transInfo.setAppId(APP_ID);// 请修改为实际上送值
			transInfo.setTranDate("2022-06-01");// 请修改为实际上送值
			transInfo.setTranTime("12:34:32");// 请修改为实际上送值
			transInfo.setSeqNo("234123");// 请修改为实际上送值
			transInfo.setAccountNo("0200000209024213178");// 请修改为实际上送值
			transInfo.setBeginDate("2022-05-06");// 请修改为实际上送值
			transInfo.setEndDate("2022-06-06");// 请修改为实际上送值
			transInfo.setMinAmount("0");// 请修改为实际上送值
			transInfo.setMaxAmount("1000000");// 请修改为实际上送值
			transInfo.setCurrency("001");// 请修改为实际上送值
			transInfo.setPnSerialNo("");// 请修改为实际上送值
			transInfo.setPnDt("");// 请修改为实际上送值
			bizContent.setTransInfo(transInfo);
			request.setBizContent(bizContent);

			// 建立返回项
			GyjrB2bCorporationDetailQueryResponseV1 response = new GyjrB2bCorporationDetailQueryResponseV1();
			logger.info(JSON.toJSONString(request));
			response = client.execute(request);
			logger.info(JSON.toJSONString(response));
			if (response.isSuccess()) {
				logger.info("success");// 成功
			} else {
				logger.info("error");// 失败
			}
			
			// 从response中获取返回信息
		} catch (Exception e) {
			LOGGER.error(LOGP_REFIX,e);
		}
	}
}
