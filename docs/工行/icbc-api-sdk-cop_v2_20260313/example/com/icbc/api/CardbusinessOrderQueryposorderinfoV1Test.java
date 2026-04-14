package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.CardbusinessOrderQueryposorderinfoRequestV1.CardbusinessOrderQueryposorderinfoRequestV1Biz;
import com.icbc.api.request.CardbusinessOrderSuborderqueryRequestV1;
import com.icbc.api.response.CardbusinessOrderQueryposorderinfoResponseV1;
import org.junit.Assert;
import org.junit.Test;

import java.util.logging.Logger;

public class CardbusinessOrderQueryposorderinfoV1Test {
	// 1、网关公钥
	protected static final String APIGW_PUBLIC_KEY = "xxx";

	// 2、appid
	protected static final String APP_ID = "xxx";

	// 3、密钥对认证方式，公钥在API平台登记，此处为私钥
	protected static final String MY_PRIVATE_KEY = "xxx";

	private Logger logger = Logger.getLogger(CardbusinessOrderQueryposorderinfoV1Test.class.getName());

	
	public void test_cop() throws IcbcApiException {

		// 签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA, MY_PRIVATE_KEY,APIGW_PUBLIC_KEY);

		CardbusinessOrderSuborderqueryRequestV1 request = new CardbusinessOrderSuborderqueryRequestV1();

		// 4、根据测试环境和生产环境替换相应ip和端口
		request.setServiceUrl("http://IP:port/api/cardbusiness/order/queryposorderinfo/V1");
		// 5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
		CardbusinessOrderQueryposorderinfoRequestV1Biz bizContent = new CardbusinessOrderQueryposorderinfoRequestV1Biz();
        bizContent.setProjectId("xxx");
        bizContent.setProductId("xxx");
        bizContent.setSendTimestamp("xxx");
        bizContent.setTimeOut("xxx");
        bizContent.setZoneno("xxx");
        bizContent.setBrno("xxx");
        bizContent.setTellerno("xxx");
        bizContent.setServface("xxx");
        bizContent.setBusicheckFlag("xxx");
        bizContent.setRemark("xxx");
        bizContent.setChanType("xxx");
        bizContent.setOapp("xxx");
        bizContent.setChanSerialno("xxx");
        bizContent.setTrxcode("xxx");
        bizContent.setAuthtlno("xxx");
        bizContent.setAuthcode("xxx");
        bizContent.setVagenLevel("xxx");
        bizContent.setCardno("xxx");
        bizContent.setAuthpwd("xxx");
        bizContent.setDutyno("xxx");
        bizContent.setOperflag("xxx");
        bizContent.setTermid("xxx");
        bizContent.setInitFlag("xxx");
        bizContent.setRowReq("xxx");
        bizContent.setAtmtemno("xxx");
        bizContent.setBackup("xxx");
        bizContent.setPosno("xxx");
        bizContent.setTrxserno("xxx");

		request.setBizContent(bizContent);
		CardbusinessOrderQueryposorderinfoResponseV1 response;
		try {
			boolean testFlag = true;
			response = client.execute(request, System.currentTimeMillis()+"");// msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一

			logger.info("response:" + response.getReturnCode());

			if (0 == response.getReturnCode()) {

				// 6、业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
				logger.info("ReturnCode:" + response.getReturnCode());
				logger.info("response:" + JSON.toJSONString(response));
			} else {
				// 失败
				testFlag = false;
				logger.info("ReturnCode:" + response.getReturnCode());
				logger.info("ReturnMsg:" + response.getReturnMsg());
			}
			Assert.assertEquals(true, testFlag);
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}
	}
}
