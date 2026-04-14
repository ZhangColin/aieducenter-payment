package com.icbc.api;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import com.icbc.api.request.MybankAccountBankconfirmationrequestBcrrequestmakeRequestV1;
import com.icbc.api.request.MybankAccountBankconfirmationrequestBcrrequestmakeRequestV1.ConfirmInfo;
import com.icbc.api.request.MybankAccountBankconfirmationrequestBcrrequestmakeRequestV1.MybankAccountBankconfirmationrequestBcrrequestmakeRequestV1Biz;
import com.icbc.api.response.MybankAccountBankconfirmationrequestBcrrequestmakeResponseV1;

/**
 * 合作方会员产品协议录入
 * 
 * 请商户配置SDK中的lib文件夹中的jar包后，根据备注1-6提示进行数据替换
 */
public class MybankAccountBankconfirmationrequestBcrrequestmakeV1Test {

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
		MybankAccountBankconfirmationrequestBcrrequestmakeRequestV1 request = new MybankAccountBankconfirmationrequestBcrrequestmakeRequestV1();

		// 4、根据测试环境和生产环境替换相应ip和端口
		// request.setServiceUrl("http://ip:port/api/mybank/account/enterprise/applycurrentaccount/V2");
		request.setServiceUrl("http://ip:port/api/mybank/account/bankconfirmationrequest/bcrrequestmake/V1");
		// 5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值，非必输字段可不上送
		MybankAccountBankconfirmationrequestBcrrequestmakeRequestV1Biz bizContent = new MybankAccountBankconfirmationrequestBcrrequestmakeRequestV1Biz();
		bizContent.setConfirmationNO("test");// 申请单编号
		bizContent.setRequestType("1");// 询证类型
		bizContent.setAuthPayAccount("test");// 授权付费账号
		bizContent.setOrganizationName("test");// 被函证单位名称
		bizContent.setSubmitter("111");// 函证单位提交人
		bizContent.setIpAddress("xxx.xxx.xxx.xxx");// IP
		ConfirmInfo conlist = new ConfirmInfo();
		List<ConfirmInfo> creInfoList = new ArrayList<ConfirmInfo>();
		// bizContent.setConList("[[{\"confirmationType\":\"1\",\"conditionNO\":\"0200062009213110737\",\"confirmBeginDate\":\"2019-04-06\",\"confirmEndDate\":\"2019-04-06\"},{\"confirmationType\":\"2\",\"conditionNO\":\"0200062009213110737\",\"confirmBeginDate\":\"2019-04-06\",\"confirmEndDate\":\"2019-04-06\"}]");
		for (int i = 0; i < 1; i++) {
			conlist.setConfirmationType("1");
			conlist.setConditionNO("test");
			conlist.setAccountName("11");
			conlist.setConfirmBeginDate("2019-05-02");
			conlist.setConfirmEndDate("2019-05-02");
			creInfoList.add(conlist);
		}
		bizContent.setList(creInfoList);
		request.setBizContent(bizContent);
		// System.out.println(client.buildPostForm(request));
		MybankAccountBankconfirmationrequestBcrrequestmakeResponseV1 response = new MybankAccountBankconfirmationrequestBcrrequestmakeResponseV1();
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
		}
	}
}
