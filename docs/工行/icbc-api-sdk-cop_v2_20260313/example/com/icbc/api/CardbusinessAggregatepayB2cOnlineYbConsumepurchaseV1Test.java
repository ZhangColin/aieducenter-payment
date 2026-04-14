package com.icbc.api;

import static org.junit.Assert.*;

import java.util.logging.Logger;

import org.junit.Test;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.CardbusinessAggregatepayB2cOnlineYbConsumepurchaseRequestV1;
import com.icbc.api.request.CardbusinessAggregatepayB2cOnlineYbConsumepurchaseRequestV1.CardbusinessAggregatepayB2cOnlineYbConsumepurchaseRequestV1Biz;
import com.icbc.api.response.CardbusinessAggregatepayB2cOnlineYbConsumepurchaseResponseV1;

public class CardbusinessAggregatepayB2cOnlineYbConsumepurchaseV1Test {
	// 1、网关公钥
	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----";
	// 2、密钥对认证方式，公钥在API平台登记，此处为私钥
	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----";
	// 3、appid
	protected static final String APP_ID = "xxxxxxxxx";
	
	private Logger logger = Logger.getLogger(CardbusinessAggregatepayB2cOnlineYbConsumepurchaseV1Test.class.getName());
	

	public void test_cop() throws IcbcApiException {
		
		//4、签名类型为RSA时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA，其他参数使用缺省值
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,IcbcConstants.SIGN_TYPE_RSA,MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
		CardbusinessAggregatepayB2cOnlineYbConsumepurchaseRequestV1 request = new CardbusinessAggregatepayB2cOnlineYbConsumepurchaseRequestV1();
		//5、根据测试环境和生产环境替换相应ip和端口
		request.setServiceUrl("http://ip:port/api/cardbusiness/aggregatepay/b2c/online/ybconsumepurchase/V1");
		CardbusinessAggregatepayB2cOnlineYbConsumepurchaseRequestV1Biz bizContent = new CardbusinessAggregatepayB2cOnlineYbConsumepurchaseRequestV1Biz();
		request.setBizContent(bizContent);
		//6、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
		bizContent.setMer_id("020001021189");
		bizContent.setMer_prtcl_no("0200010211890201");
		bizContent.setOut_trade_no("MEDTESTcash202512010001");
		bizContent.setPay_mode("9");
		bizContent.setAccess_type("7");
		bizContent.setOrig_date_time("2025-12-01T09:26:35");
		bizContent.setDevice_info("HUAWEI MATE 99");
		bizContent.setBody("WEIXINMED");
		bizContent.setFee_type("001");
		bizContent.setSpbill_create_ip("2409:8a00:18cd:*:*:981b:2989:d7b1");
		bizContent.setMix_pay_type("CASH_ONLY");
		bizContent.setOrder_type("DIAG_PAY");
		bizContent.setPayer_name("张三");
		bizContent.setPayer_id_digest("09eb26e839ff3a2e3980352ae45ef09e");
		bizContent.setPayer_card_type("ID_CARD");
		bizContent.setPay_for_relatives("false");
		bizContent.setSerial_no("WXTEST20250409092701");
		bizContent.setCity_id("440108");
		bizContent.setMed_inst_name("北大医院");
		bizContent.setMed_inst_no("1217752501201407033233368318");
		bizContent.setTotal_fee("1000");
		CardbusinessAggregatepayB2cOnlineYbConsumepurchaseResponseV1 response;
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
