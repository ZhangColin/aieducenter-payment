package com.icbc.api;

import org.junit.Test;

import com.icbc.api.request.MybankAccountBankconfirmationrequestBcrrequestcancelRequestV1;
import com.icbc.api.request.MybankAccountBankconfirmationrequestBcrrequestcancelRequestV1.MybankAccountBankconfirmationrequestBcrrequestcancelRequestV1Biz;
import com.icbc.api.response.MybankAccountBankconfirmationrequestBcrrequestcancelResponseV1;

/**
 * 合作方会员产品协议录入
 * 
 * 请商户配置SDK中的lib文件夹中的jar包后，根据备注1-6提示进行数据替换
 */
public class MybankAccountBankconfirmationrequestBcrrequestcancelV1Test {

	// 1、网关公钥
	protected static final String APIGW_PUBLIC_KEY = "test";

	// 2、appid
	protected static final String APP_ID = "test";

	// 3、密钥对认证方式，公钥在API平台登记，此处为私钥
	protected static final String MY_PRIVATE_KEY = "test";


	public void test() throws Exception {

		// 签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,
				IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
		MybankAccountBankconfirmationrequestBcrrequestcancelRequestV1 request = new MybankAccountBankconfirmationrequestBcrrequestcancelRequestV1();

		// 4、根据测试环境和生产环境替换相应ip和端口
		// request.setServiceUrl("http://ip:port/api/mybank/account/enterprise/applycurrentaccount/V2");
		request.setServiceUrl("http://ip:port/api/mybank/account/bankconfirmationrequest/bcrrequestcancel/V1");
		// 5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值，非必输字段可不上送
		MybankAccountBankconfirmationrequestBcrrequestcancelRequestV1Biz bizContent = new MybankAccountBankconfirmationrequestBcrrequestcancelRequestV1Biz();

		bizContent.setConfirmationNO("test");// 申请单编号
		request.setBizContent(bizContent);
		// System.out.println(client.buildPostForm(request));
		MybankAccountBankconfirmationrequestBcrrequestcancelResponseV1 response = new MybankAccountBankconfirmationrequestBcrrequestcancelResponseV1();
		try {
			response = client.execute(request,
					String.valueOf(System.currentTimeMillis()));// msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一
			System.out.println("ReturnCode:" + response.getReturnCode());
			System.out.println("ReturnMsg:" + response.getReturnMsg());
			if (response.isSuccess()) {
				// 6、业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
				System.out.println("ReturnCode:" + response.getReturnCode());
			} else {
				System.out.println("returnCode:" + response.getReturnCode());
				System.out.println("returnMsg:" + response.getReturnMsg());
			}
		} catch (IcbcApiException e) {
			e.printStackTrace();
		} finally {
		}
	}
}
