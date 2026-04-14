package com.icbc.api;

import com.icbc.api.request.BcssCateringDishInventoryQueryRequestV1;
import com.icbc.api.request.BcssCateringDishInventoryQueryRequestV1.BcssCateringDishInventoryQueryRequestBizV1;
import com.icbc.api.request.BcssCateringDishSalesStatsQueryRequestV1;

import java.util.ArrayList;
import java.util.List;

public class BcssCateringDishSalesStatsQueryV1Test {
	// 网关公钥
	protected static final String APIGW_PUBLIC_KEY = "XXXXXXX";
	protected static final String APP_ID = "XXXX";
	// 密钥对认证方式，公钥在API平台登记，此处为私钥
	protected static final String MY_PRIVATE_KEY = "XXXXXXX";
	// 加密类型和秘钥
	protected static final String encryptType = "xxxx";
	protected static final String encryptKey = "xxxx";

	public static void main(String[] args) throws IcbcApiException {

		BcssCateringDishInventoryQueryRequestV1 request = new BcssCateringDishInventoryQueryRequestV1();
		// 根据测试环境和生产环境替换相应ip和端口
		request.setServiceUrl("http://***.***.***.***:****/api/bcss/query/dishSalesStats/V1");
		BcssCateringDishSalesStatsQueryRequestV1.BcssCateringDishSalesStatsQueryRequestBizV1 bizContent
				= new BcssCateringDishSalesStatsQueryRequestV1.BcssCateringDishSalesStatsQueryRequestBizV1();
		bizContent.setCorpId("2000000001");
		bizContent.setMemmerNo("20000000010000000001");
		bizContent.setBeginTime("060000");
		bizContent.setEndTime("080000");
		bizContent.setPeriodId("202204210000804464");
		bizContent.setQueryType("1");
		List<String> dishIdList = new ArrayList(2);
		dishIdList.add("202204220000804477");
		dishIdList.add("202204220000804478");
		bizContent.setDishIdList(dishIdList);
		bizContent.setClientTransNo("012345678901234567890");
		request.setBizContent(bizContent);
		// 签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA, MY_PRIVATE_KEY,
				APIGW_PUBLIC_KEY);
		client.execute(request);
	}
}
