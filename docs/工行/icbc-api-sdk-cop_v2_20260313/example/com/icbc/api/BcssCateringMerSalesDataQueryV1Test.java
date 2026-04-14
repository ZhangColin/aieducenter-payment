package com.icbc.api;

import com.icbc.api.request.BcssCateringMerSalesDataQueryRequestV1;

public class BcssCateringMerSalesDataQueryV1Test {
	// 网关公钥
	protected static final String APIGW_PUBLIC_KEY = "XXXXXXX";
	protected static final String APP_ID = "XXXX";
	// 密钥对认证方式，公钥在API平台登记，此处为私钥
	protected static final String MY_PRIVATE_KEY = "XXXXXXX";
	// 加密类型和秘钥
	protected static final String encryptType = "xxxx";
	protected static final String encryptKey = "xxxx";

	public static void main(String[] args) throws IcbcApiException {

		BcssCateringMerSalesDataQueryRequestV1 request = new BcssCateringMerSalesDataQueryRequestV1();
		// 根据测试环境和生产环境替换相应ip和端口
		request.setServiceUrl("http://***.***.***.***:****/api/bcss/query/salesDataQuery/V1");
		BcssCateringMerSalesDataQueryRequestV1.BcssCateringMerQueryRequestBizV1 bizContent
				= new BcssCateringMerSalesDataQueryRequestV1.BcssCateringMerQueryRequestBizV1();
		bizContent.setCorpId("2000000001");
		bizContent.setMemmerNo("20000000010000000001");
		bizContent.setStartDate("2022-08-17");
		bizContent.setEndDate("2022-08-17");
		request.setBizContent(bizContent);
		// 签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA, MY_PRIVATE_KEY,
				APIGW_PUBLIC_KEY);
		client.execute(request);
	}
}
