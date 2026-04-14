package com.icbc.api;

import static org.junit.Assert.*;

import java.util.logging.Logger;

import org.junit.Test;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.CardbusinessAggregatepayB2cOnlineYbOrderqryRequestV1;
import com.icbc.api.request.CardbusinessAggregatepayB2cOnlineYbOrderqryRequestV1.CardbusinessAggregatepayB2cOnlineYbOrderqryRequestV1Biz;
import com.icbc.api.response.CardbusinessAggregatepayB2cOnlineYbOrderqryResponseV1;

public class CardbusinessAggregatepayB2cOnlineYbOrderqryV1Test {
	// 1、网关公钥
	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----";
	// 2、密钥对认证方式，公钥在API平台登记，此处为私钥
	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----";
	// 3、appid
	protected static final String APP_ID = "xxxxxxxxx";
	
	private Logger logger = Logger.getLogger(CardbusinessAggregatepayB2cOnlineYbOrderqryV1Test.class.getName());
	

	public void test_cop() throws IcbcApiException {
		
		//4、签名类型为RSA时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA，其他参数使用缺省值
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,IcbcConstants.SIGN_TYPE_RSA,MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
		CardbusinessAggregatepayB2cOnlineYbOrderqryRequestV1 request = new CardbusinessAggregatepayB2cOnlineYbOrderqryRequestV1();
		//5、根据测试环境和生产环境替换相应ip和端口
		request.setServiceUrl("http://ip:port/api/cardbusiness/aggregatepay/b2c/online/yborderqry/V1");
		CardbusinessAggregatepayB2cOnlineYbOrderqryRequestV1Biz bizContent = new CardbusinessAggregatepayB2cOnlineYbOrderqryRequestV1Biz();
		request.setBizContent(bizContent);
		//6、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
		bizContent.setMerId("02000104****");
		bizContent.setOutTradeNo("2022040****5169952975171534816");
		bizContent.setOrderId("020001041****00522411150268295");
		bizContent.setMerPrtclNo("02000104****0201");
		CardbusinessAggregatepayB2cOnlineYbOrderqryResponseV1 response;
		try {
			boolean testFlag = true;
			response = client.execute(request, System.currentTimeMillis()+"");//msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一
			if (response.getReturnCode() == 0) {
				// 6、业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
				logger.info("ReturnCode:"+response.getReturnCode());
				logger.info("response:" + JSON.toJSONString(response));
			} else {
				// 失败
				testFlag = false;
				logger.info("ReturnCode:"+response.getReturnCode());
				logger.info("response:" + JSON.toJSONString(response));
				logger.info("ReturnMsg:"+response.getReturnMsg());
			}
			assertTrue(testFlag);
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}
	}
}
