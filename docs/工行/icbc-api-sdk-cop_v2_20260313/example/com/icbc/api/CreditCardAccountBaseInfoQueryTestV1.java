package com.icbc.api;

import com.icbc.api.request.CreditCardAccountBaseInfoQueryRequestV1;
import com.icbc.api.request.CreditCardAccountBaseInfoQueryRequestV1.CreditCardAccountBaseInfoQueryRequestV1Biz;
import com.icbc.api.request.CreditCardAccountBaseInfoQueryRequestV1.CreditCardAccountBaseInfoQueryRequestV1CardComm;
import com.icbc.api.request.CreditCardAccountBaseInfoQueryRequestV1.CreditCardAccountBaseInfoQueryRequestV1ChannelComm;
import com.icbc.api.request.CreditCardAccountBaseInfoQueryRequestV1.CreditCardAccountBaseInfoQueryRequestV1TrxComm;
import com.icbc.api.response.CreditCardAccountBaseInfoQueryResponseV1;

/**
 * 信用卡账户信息查询
 * 
 * @author kfzx-linsj
 *
 */
public class CreditCardAccountBaseInfoQueryTestV1 {
	public static void main(String[] args) {
		// 示例总体说明：请使用F-APIP最新的行外网关SDK进行开发，签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值

		// 1、密钥对认证方式，公钥在F-APIP平台登记，此处为私钥
		String MY_PRIVATE_KEY = "xxxxxxxxxxxx";
		// 2、appid
		String APP_ID = "xxxxxx";
		// 3、网关公钥
		String APIGW_PUBLIC_KEY = "xxxxxxxxxxxx";

		// 4、创建与F-APIP外网网关的连接
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8",
				"json", APIGW_PUBLIC_KEY, (String) null, (String) null, (String) null, (String) null);
		// 5、设置请求对象
		CreditCardAccountBaseInfoQueryRequestV1 request = new CreditCardAccountBaseInfoQueryRequestV1();
		// 6、设置请求路径；联测时，请联系我行告知并替换成测试环境地址
		request.setServiceUrl("http://[需替换]工行API网关地址/api/creditcard/accountinfo/query/V1");
		// 7、设置业务参数
		CreditCardAccountBaseInfoQueryRequestV1Biz bizContent = new CreditCardAccountBaseInfoQueryRequestV1Biz();
		CreditCardAccountBaseInfoQueryRequestV1ChannelComm channelComm = bizContent.getChannelComm();
		CreditCardAccountBaseInfoQueryRequestV1TrxComm trxComm = bizContent.getTrxComm();
		CreditCardAccountBaseInfoQueryRequestV1CardComm cardComm = bizContent.getCardComm();

		// 8、组装请求参数
		channelComm.setChannelType("");
		channelComm.setChannelNo("");
		channelComm.setChannelEventType("");
		channelComm.setChannelApp("");
		channelComm.setChannelAccessEventNo("");
		channelComm.setChannelAccessType("");
		channelComm.setChannelMac("");
		channelComm.setChannelIp("");

		trxComm.setTrxType("");
		trxComm.setTrxCode("");
		trxComm.setTrxZoneNo("");
		trxComm.setTrxTellerNo("");
		trxComm.setTrxDate("");
		trxComm.setTrxTime("");
		trxComm.setTrxReserveFlag("");
		trxComm.setTrxServface("");
		trxComm.setTrxOperationFlag("");
		trxComm.setAppType("");

		cardComm.setCardNo("");

		bizContent.setChannelComm(channelComm);
		bizContent.setTrxComm(trxComm);
		bizContent.setCardComm(cardComm);
		bizContent.setCurrType("");

		request.setBizContent(bizContent);

		CreditCardAccountBaseInfoQueryResponseV1 response = null;
		try {
			System.out.println("request:" + request.toString());
			response = client.execute(request);
			System.out.println("response:" + response.toString());
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}
		System.out.println("returnCode:" + response.getReturnCode());
	}
}
