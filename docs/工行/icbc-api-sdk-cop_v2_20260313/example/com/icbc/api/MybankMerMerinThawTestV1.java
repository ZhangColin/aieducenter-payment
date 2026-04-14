package com.icbc.api;

import java.sql.Timestamp;
import com.icbc.api.request.MybankMerMerinThawRequestV1;
import com.icbc.api.request.MybankMerMerinThawRequestV1.MybankMerMerinThawRequestV1Biz;
import com.icbc.api.response.MybankMerMerinThawResponseV1;

public class MybankMerMerinThawTestV1 {
	// 1、网关公钥
	protected static final String APIGW_PUBLIC_KEY = "MIGf*******AB";

	// 2、appid
	protected static final String APP_ID = "10000000*******5646";

	// 3、密钥对认证方式，公钥在API平台登记，此处为私钥
	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvQIBADAKU*******";
  
  private Logger logger = Logger.getLogger(MybankMerMerinThawTestV1.class.getName());
	
	public static void main(String[] args) throws Exception {

		// 签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA, MY_PRIVATE_KEY,APIGW_PUBLIC_KEY);

		MybankMerMerinThawRequestV1 request = new MybankMerMerinThawRequestV1();

		// 4、根据测试环境和生产环境替换相应ip和端口
		request.setServiceUrl("http://*******/api/mybank/mer/merin/thaw/V1");
		// 5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
		MybankMerMerinThawRequestV1Biz bizContent = new MybankMerMerinThawRequestV1Biz();
		bizContent.setOutAgreeNo("02*******244");
		bizContent.setIcbcOrderNo("2019*******33");
		bizContent.setIcbcOrderSubNo("123");
		bizContent.setMerinSummary("摘要");
		request.setBizContent(bizContent);
		MybankMerMerinThawResponseV1 response;
		try {
			response = client.execute(request, "233445*******4471");// msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一

			//System.out.println("response:" + response.getReturnCode());
			logger.info("response:" + response.getReturnCode());

			if (response.isSuccess()) {
				// 6、业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
				//System.out.println("ReturnCode:" + response.getReturnCode());
				//System.out.println("response:" + response);
				logger.info("ReturnCode:" + response.getReturnCode());
				logger.info("response:" + response);
			} else {
				// 失败
				//System.out.println("ReturnCode:" + response.getReturnCode());
				//System.out.println("ReturnMsg:" + response.getReturnMsg());
				logger.info("ReturnCode:" + response.getReturnCode());
				logger.info("ReturnMsg:" + response.getReturnMsg());
			}
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}
	}
}
