package com.icbc.api;

import com.icbc.api.internal.util.fastjson.serializer.SerializerFeature;
import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.BusinessopFinancialecocloudTenementinfoqryRequestV1;
import com.icbc.api.request.BusinessopFinancialecocloudTenementinfoqryRequestV1.BusinessopFinancialecocloudTenementinfoqryRequestBizV1;
import com.icbc.api.response.BusinessopFinancialecocloudTenementinfoqryResponseV1;

public class BusinessopFinancialecocloudTenementinfoqryTest {

	protected static final String APIGW_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBi";

	protected static final String APP_ID = "10000000000000000500";

	protected static final String PRI_KEY = "-----请使用正确的APP私钥-----EvgIBADANBgk";
	
	protected static final String ENCRYPT_KEY = "mZZP";

	protected static final String BASE_URL = "http://ip:port/api";

	public static void main(String[] args) {

		String uri = "businessop/financialecocloud/tenementinfoqry/V1";
		
		// 无需加密传输时
//		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, PRI_KEY, APIGW_PUBLIC_KEY);
		
		// 需使用AES加密传输时
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, PRI_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, IcbcConstants.ENCRYPT_TYPE_AES, ENCRYPT_KEY, "", "");
		
		try {

			BusinessopFinancialecocloudTenementinfoqryRequestBizV1 bizContent = new BusinessopFinancialecocloudTenementinfoqryRequestBizV1();
			BusinessopFinancialecocloudTenementinfoqryRequestV1 request = new BusinessopFinancialecocloudTenementinfoqryRequestV1();
			bizContent.setPageNum("1");
			bizContent.setLastUpdateTime("20210720100000");
			bizContent.setOrderVersion("free");

			request.setServiceUrl(BASE_URL + "/" + uri);
			request.setBizContent(bizContent);
			request.setNeedEncrypt(true); // 需使用AES加密传输时为true
			
			BusinessopFinancialecocloudTenementinfoqryResponseV1 response = client.execute(request);
			if (response.isSuccess()) {
				System.out.println("success");
				System.out.println(response.toString());
				System.out.println(JSONObject.toJSONString(response, SerializerFeature.DisableCircularReferenceDetect));
			} else {
				System.out.println("error");
				System.out.println(response.toString());
				System.out.println(JSONObject.toJSONString(response, SerializerFeature.DisableCircularReferenceDetect));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
