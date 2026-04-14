package com.icbc.api;

import static org.junit.Assert.*;

import java.util.logging.Logger;

import org.junit.Test;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.CardbusinessAggregatepayB2cOnlineYbMerrefundRequestV1;
import com.icbc.api.request.CardbusinessAggregatepayB2cOnlineYbMerrefundRequestV1.CardbusinessAggregatepayB2cOnlineYbMerrefundRequestV1Biz;
import com.icbc.api.response.CardbusinessAggregatepayB2cOnlineYbMerrefundResponseV1;

public class CardbusinessAggregatepayB2cOnlineYbMerrefundV1Test {
	// 1、网关公钥
	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----";
	// 2、密钥对认证方式，公钥在API平台登记，此处为私钥
	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----";
	// 3、appid
	protected static final String APP_ID = "xxxxxxxxx";
	
	private Logger logger = Logger.getLogger(CardbusinessAggregatepayB2cOnlineYbMerrefundV1Test.class.getName());
	

	public void test_cop() throws IcbcApiException {
		
		//4、签名类型为RSA时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA，其他参数使用缺省值
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,IcbcConstants.SIGN_TYPE_RSA,MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
		CardbusinessAggregatepayB2cOnlineYbMerrefundRequestV1 request = new CardbusinessAggregatepayB2cOnlineYbMerrefundRequestV1();
		//5、根据测试环境和生产环境替换相应ip和端口
		request.setServiceUrl("http://ip:port/api/cardbusiness/aggregatepay/b2c/online/ybmerrefund/V1");
		CardbusinessAggregatepayB2cOnlineYbMerrefundRequestV1Biz bizContent = new CardbusinessAggregatepayB2cOnlineYbMerrefundRequestV1Biz();
		request.setBizContent(bizContent);
		//6、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
		bizContent.setOut_trade_no("AAPPT1300721101443544");
		bizContent.setOrder_id("020001041240000522511210356112");
		bizContent.setMer_id("020001041240");
		bizContent.setMer_prtcl_no("0200010412400201");
		bizContent.setMed_refund_total_fee("200533809");
		bizContent.setMed_refund_gov_fee("100");
		bizContent.setMed_refund_self_fee("50");
		bizContent.setMed_refund_other_fee("50");
		bizContent.setRefund_time("2025-11-24T13:29:35+08:00");
		bizContent.setOut_refund_no("54353443634376922357855223");
		CardbusinessAggregatepayB2cOnlineYbMerrefundResponseV1 response;
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
