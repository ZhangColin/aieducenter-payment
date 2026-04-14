package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.internal.util.internal.util.fastjson.JSONArray;
import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.CardbusinessAggregatepayB2cOnlineSersuborderrefundRequestV1;
import com.icbc.api.response.CardbusinessAggregatepayB2cOnlineSersuborderrefundResponseV1;
import com.icbc.api.request.CardbusinessAggregatepayB2cOnlineSersuborderrefundRequestV1.CardbusinessAggregatepayB2cOnlineSersuborderrefundRequestV1Biz;
import org.junit.Test;

import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;

public class CardbusinessAggregatepayB2cOnlineSersuborderrefundV1Test {
	// 1、网关公钥
	protected static final String APIGW_PUBLIC_KEY = "xxx";
	// 2、密钥对认证方式，公钥在API平台登记，此处为私钥
	protected static final String MY_PRIVATE_KEY = "xxx";
	// 3、appid
	protected static final String APP_ID = "xxx";

	private Logger logger = Logger.getLogger(CardbusinessAggregatepayB2cOnlineSersuborderrefundV1Test.class.getName());

	
	public void test_cop() throws IcbcApiException {

		// 4、签名类型为RSA时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA，其他参数使用缺省值
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA, MY_PRIVATE_KEY,
				APIGW_PUBLIC_KEY);
		CardbusinessAggregatepayB2cOnlineSersuborderrefundRequestV1 request = new CardbusinessAggregatepayB2cOnlineSersuborderrefundRequestV1();
		// 5、根据测试环境和生产环境替换相应ip和端口
		request.setServiceUrl("http://IP:port/api/cardbusiness/aggregatepay/b2c/online/sersuborderrefund/V1");
		CardbusinessAggregatepayB2cOnlineSersuborderrefundRequestV1Biz bizContent = new CardbusinessAggregatepayB2cOnlineSersuborderrefundRequestV1Biz();
		request.setBizContent(bizContent);
		// 6、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
		bizContent.setMerId("xxx");
		bizContent.setMerPrtclNo("xxx");
		bizContent.setIcbcAppid("xxx");
		bizContent.setOperFlag("xxx");
		bizContent.setSubOrderId("xxx");
		bizContent.setBusiType("xxx");
		bizContent.setSeqNo("xxx");
		bizContent.setOriTrxDate("xxx");
		bizContent.setOriMerId("xxx");
		bizContent.setSubMerId("xxxx");
		bizContent.setSubMerPrtclNo("xxx");
		bizContent.setClassifyAmt("xxx");
		bizContent.setRetSubOrderId("xxx");
		bizContent.setOuttrxSerialNo("xxx");
		bizContent.setMerSpInfor("xxx");
		bizContent.setMerAcct("xxx");
		bizContent.setTermId("xxx");
		bizContent.setRetTotalAmt("xxx");
		bizContent.setRefundSource("xxx");
		
		CardbusinessAggregatepayB2cOnlineSersuborderrefundResponseV1 response;
		try {
			boolean testFlag = true;
			response = client.execute(request, System.currentTimeMillis() + "");// msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一
			if (response.getReturnCode() == 0) {
				// 6、业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
				logger.info("ReturnCode:" + response.getReturnCode()); 
				logger.info("response:" + JSON.toJSONString(response));
			} else {
				// 失败
				testFlag = false;
				logger.info("response:" + JSON.toJSONString(response));
				logger.info("ReturnCode:" + response.getReturnCode());
				logger.info("ReturnMsg:" + response.getReturnMsg());
			}
			assertEquals(testFlag);
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}
	}
}
