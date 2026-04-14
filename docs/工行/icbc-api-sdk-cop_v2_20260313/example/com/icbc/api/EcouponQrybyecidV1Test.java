package com.icbc.api;

import com.icbc.api.request.EcouponInfoRequestV1;
import com.icbc.api.response.EcouponInfoResponseV1;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EcouponQrybyecidV1Test {
	// 1、网关公钥
	protected static final String APIGW_PUBLIC_KEY = "请使用正确的网关公钥";

	// 2、appid
	protected static final String APP_ID = "11000000000000006290";

	// 3、密钥对认证方式，公钥在API平台登记，此处为私钥
	protected static final String MY_PRIVATE_KEY = "请使用正确的APP私钥";

	public static void main(String[] args) throws Exception {

		// 签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,
				IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

		EcouponInfoRequestV1 request = new EcouponInfoRequestV1();
		// 4、根据测试环境和生产环境替换相应ip和端口
		request.setServiceUrl("https://ip:port/api/ecoupon/ecoupon/qrybyecid/V1");

		// 5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
		EcouponInfoRequestV1.EcouponInfoRequestV1Biz bizContent = new EcouponInfoRequestV1.EcouponInfoRequestV1Biz();
		EcouponInfoRequestV1.PrivateParams privateParams = new EcouponInfoRequestV1.PrivateParams();
		privateParams.setEcActId("OFST20251111Jw5iws17");
		bizContent.setPrivateParams(privateParams);
		request.setBizContent(bizContent);

		EcouponInfoResponseV1 response;
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
			e.printStackTrace();
		}
	}
}
