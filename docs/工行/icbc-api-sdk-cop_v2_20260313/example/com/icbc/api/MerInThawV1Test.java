package com.icbc.api;

import java.sql.Timestamp;

import com.icbc.api.request.MerInThawRequestV1;
import com.icbc.api.request.MerInThawRequestV1.MerInThawRequestV1Biz;
import com.icbc.api.response.MerInThawResponseV1;

public class MerInThawV1Test {
	// 1、网关公钥
	protected static final String APIGW_PUBLIC_KEY = "*****+60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

	// 2、appid
	protected static final String APP_ID = "1000000*****4095646";

	// 3、密钥对认证方式，公钥在API平台登记，此处为私钥
	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvQIBA******";
	
	private Logger logger = Logger.getLogger(MerInThawV1Test.class.getName());

	public static void main(String[] args) throws Exception {

		// 签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA, MY_PRIVATE_KEY,APIGW_PUBLIC_KEY);

		MerInThawRequestV1 request = new MerInThawRequestV1();

		// 4、根据测试环境和生产环境替换相应ip和端口
		request.setServiceUrl("http://****:***/api/mybank/mer/merin/thaw/V1");
		// 5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
		MerInThawRequestV1Biz bizContent = new MerInThawRequestV1Biz();
		bizContent.setIcbcOrderNo("****");
		bizContent.setIcbcOrderSubNo("****");
		bizContent.setOutAgreeNo("****");
		request.setBizContent(bizContent);
		MerInThawResponseV1 response;
		try {
			response = client.execute(request, System.currentTimeMillis()+"");// msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一

			logger.info("response:" + response.getReturnCode());

			if (response.isSuccess()) {
				// 6、业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
				logger.info("ReturnCode:" + response.getReturnCode());
				logger.info("response:" + response);
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
