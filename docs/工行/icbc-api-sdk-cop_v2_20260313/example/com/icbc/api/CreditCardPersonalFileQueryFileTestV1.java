package com.icbc.api;

import com.icbc.api.request.CreditCardPersonalFileQueryFileRequestV1;
import com.icbc.api.request.CreditCardPersonalFileQueryFileRequestV1.CreditCardPersonalFileQueryFileRequestV1Biz;
import com.icbc.api.request.CreditCardPersonalFileQueryFileRequestV1.CreditCardPersonalFileQueryFileRequestV1ChannelComm;
import com.icbc.api.request.CreditCardPersonalFileQueryFileRequestV1.CreditCardPersonalFileQueryFileRequestV1InputInfo;
import com.icbc.api.request.CreditCardPersonalFileQueryFileRequestV1.CreditCardPersonalFileQueryFileRequestV1TrxComm;
import com.icbc.api.response.CreditCardPersonalFileQueryFileResponseV1;

/**
 * 信用卡个人档案补充查询
 * 
 * @author kfzx-liangd01
 *
 */
public class CreditCardPersonalFileQueryFileTestV1 {
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
		CreditCardPersonalFileQueryFileRequestV1 request = new CreditCardPersonalFileQueryFileRequestV1();
		// 6、设置请求路径；联测时，请联系我行告知并替换成测试环境地址
		request.setServiceUrl("http://[需替换]工行API网关地址/api/credicardt/customer/personfile/queryFile/V1");
		// 7、设置业务参数
		CreditCardPersonalFileQueryFileRequestV1Biz bizContent = new CreditCardPersonalFileQueryFileRequestV1Biz();
		CreditCardPersonalFileQueryFileRequestV1ChannelComm channelComm = bizContent.getChannelComm();
		CreditCardPersonalFileQueryFileRequestV1TrxComm trxComm = bizContent.getTrxComm();
		CreditCardPersonalFileQueryFileRequestV1InputInfo inputInfo = bizContent.getInputInfo();

		// 8、组装请求参数
		channelComm.setChannelType("1");
		channelComm.setChannelNo("011900212345678");
		channelComm.setChannelEventType("3");
		channelComm.setChannelAccessEventNo("1234567890");
		channelComm.setChannelAccessType("1");
		channelComm.setChannelMac("111");
		channelComm.setChannelIp("ip:port");

		trxComm.setTrxType("1");
		trxComm.setTrxCode("1234");
		trxComm.setTrxZoneNo("119");
		trxComm.setTrxBranchNo("1862");
		trxComm.setTrxTellerNo("99006");
		trxComm.setTrxDate("2023-03-23");
		trxComm.setTrxTime("12:12:16");
		trxComm.setTrxReserveFlag("0");
		trxComm.setTrxServface("1");
		trxComm.setTrxOperationFlag("1");
		trxComm.setTrxBigSqn("123");
		trxComm.setTrxSmallSqn("0");
		trxComm.setAppType("0");

		inputInfo.setZoneNo("119");
		inputInfo.setCino("011900020002283");

		bizContent.setChannelComm(channelComm);
		bizContent.setTrxComm(trxComm);
		bizContent.setinputInfo(inputInfo);

		request.setBizContent(bizContent);

		CreditCardPersonalFileQueryFileResponseV1 response = null;
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
