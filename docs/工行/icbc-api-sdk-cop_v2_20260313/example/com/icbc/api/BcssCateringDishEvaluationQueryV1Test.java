package com.icbc.api;

import com.icbc.api.request.BcssCateringDishEvaluationQueryRequestV1;
import com.icbc.api.request.BcssCateringDishInventoryQueryRequestV1;
import com.icbc.api.request.BcssCateringDishInventoryQueryRequestV1.BcssCateringDishInventoryQueryRequestBizV1;

public class BcssCateringDishEvaluationQueryV1Test {
	// 网关公钥
	protected static final String APIGW_PUBLIC_KEY = "XXXXXXX";
	protected static final String APP_ID = "XXXX";
	// 密钥对认证方式，公钥在API平台登记，此处为私钥
	protected static final String MY_PRIVATE_KEY = "XXXXXXX";
	// 加密类型和秘钥
	protected static final String encryptType = "xxxx";
	protected static final String encryptKey = "xxxx";

	public static void main(String[] args) throws IcbcApiException {

		BcssCateringDishEvaluationQueryRequestV1 request = new BcssCateringDishEvaluationQueryRequestV1();
		// 根据测试环境和生产环境替换相应ip和端口
		request.setServiceUrl("http://***.***.***.***:****/api/bcss/query/dishEvaluationList/V1");
		BcssCateringDishEvaluationQueryRequestV1.BcssCateringDishEvaluationQueryRequestBizV1 bizContent
				= new BcssCateringDishEvaluationQueryRequestV1.BcssCateringDishEvaluationQueryRequestBizV1();
		bizContent.setCorpId("2000000001");
		bizContent.setMemmerNo("20000000010000000001");
		bizContent.setStartDate("20220424");
		bizContent.setEndDate("20220425");
		bizContent.setEvaluationNum("10");
		bizContent.setClientTransNo("12345678901234567890");
		request.setBizContent(bizContent);
		// 签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA, MY_PRIVATE_KEY,
				APIGW_PUBLIC_KEY);
		client.execute(request);
	}
}
