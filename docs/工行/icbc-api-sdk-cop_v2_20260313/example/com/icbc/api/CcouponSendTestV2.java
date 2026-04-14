package com.icbc.api;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.icbc.api.request.CcouponSendRequestV2;
import com.icbc.api.request.CcouponSendRequestV2.CcouponSendRequestV2Biz;
import com.icbc.api.response.CcouponSendResponseV2;

public class CcouponSendTestV2 {
	private static final Logger logger = LoggerFactory.getLogger(CcouponSendTestV2.class);

	// 1、网关公钥
	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----";

	// 2、appid
	protected static final String APP_ID = "10000000000000002697";

	// 3、密钥对认证方式，公钥在API平台登记，此处为私钥
	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----";

	public static void main(String[] args) throws Exception {

		// 签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,
				IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

		CcouponSendRequestV2 request = new CcouponSendRequestV2();
		// 4、根据测试环境和生产环境替换相应ip和端口
		request.setServiceUrl("https://ip:port/api/eiop/ccoupon/send/V2");

		// 5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
		CcouponSendRequestV2Biz bizContent = new CcouponSendRequestV2Biz();
		bizContent.setSerialno("13579246810");
		bizContent.setCinoHsm("gGNNZ2OWz+SJXuFTSv4hieVWhfZSlr4m");
		bizContent.setMobileNo("13400000020");
		bizContent.setActId("cbc20190720160851595");
		bizContent.setElifeMsgSwitch("1");
		bizContent.setFetchField1("1");
		bizContent.setFetchField2("2");
		bizContent.setFetchField3("备用字段3");
		bizContent.setFetchField4("备用字段4");
		bizContent.setFetchField5("备用字段5");

		CcouponSendResponseV2 response;
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
			String msgId = dateFormat.format(new Date());
			response = client.execute(request, msgId);
			if (response.isSuccess()) {
				// 业务成功处理
				System.out.println("ReturnCode:" + response.getReturnCode());
				System.out.println("response:" + response);
			} else {
				// 失败
				System.out.println("ReturnCode:" + response.getReturnCode());
				System.out.println("ReturnMsg:" + response.getReturnMsg());
			}
		} catch (IcbcApiException e) {
			logger.error("send exception", e);
		}
	}
}
