package com.icbc.api;

import java.math.BigDecimal;
import java.util.Map;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.internal.util.internal.util.fastjson.JSONArray;
import com.icbc.api.request.MybankMerMerinFreezedtlqueryRequestV1;
import com.icbc.api.request.MybankMerMerinFreezedtlqueryRequestV1.MybankMerMerinFreezedtlqueryRequestV1Biz;
import com.icbc.api.response.MybankMerMerinFreezedtlqueryResponseV1;

public class MybankMerMerinFreezedtlqueryTestV1 {
	// 1、网关公钥
	protected static final String APIGW_PUBLIC_KEY = "MIGfMA0GCSqGSIb3****";

	// 2、appid
	protected static final String APP_ID = "10000*****095635";

	// 3、密钥对认证方式，公钥在API平台登记，此处为私钥
	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvQIBADAN****";
	
	private Logger logger = Logger.getLogger(MybankMerMerinFreezedtlqueryTestV1.class.getName());

	public static void main(String[] args) throws Exception {

		// 签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA, MY_PRIVATE_KEY,APIGW_PUBLIC_KEY);

		MybankMerMerinFreezedtlqueryRequestV1 request = new MybankMerMerinFreezedtlqueryRequestV1();

		// 4、根据测试环境和生产环境替换相应ip和端口
		request.setServiceUrl("http://****:***/api/mybank/mer/merin/freezedtlquery/V1");
		// 5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
		MybankMerMerinFreezedtlqueryRequestV1Biz bizContent = new MybankMerMerinFreezedtlqueryRequestV1Biz();
		bizContent.setQueryType("****");
		bizContent.setPageNum("****");
		bizContent.setPageSize("****");
		bizContent.setInitFlag("****");
		bizContent.setOutAgreeNo("****");
		bizContent.setIcbcOrderNo("****");
		bizContent.setIcbcOrderSubNo("****");
		bizContent.setStartDate("****");
		bizContent.setEndDate("****");
		request.setBizContent(bizContent);
		MybankMerMerinFreezedtlqueryResponseV1 response;
		try {
			response = client.execute(request, System.currentTimeMillis()+"");// msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一

			logger.info("response:" + response.getReturnCode());

			if (response.isSuccess()) {
				// 6、业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
				logger.info("ReturnCode:" + response.getReturnCode());
				logger.info("endFlag:" + response.getEndFlag());
				logger.info("resultList:" + JSON.toJSONString(response.getResultList()));
			} else {
				// 失败
				logger.info("ReturnCode:" + response.getReturnCode());
				logger.info("ReturnMsg:" + response.getReturnMsg());
			}
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}
	}
}
