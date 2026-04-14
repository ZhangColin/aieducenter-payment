package com.icbc.api;

import com.icbc.api.request.CreditCardMediumDetailedInfoQueryRequestV1;
import com.icbc.api.request.CreditCardMediumDetailedInfoQueryRequestV1.CreditCardMediumDetailedInfoQueryRequestV1Biz;
import com.icbc.api.request.CreditCardMediumDetailedInfoQueryRequestV1.CreditCardMediumDetailedInfoQueryRequestV1CardComm;
import com.icbc.api.request.CreditCardMediumDetailedInfoQueryRequestV1.CreditCardMediumDetailedInfoQueryRequestV1ChannelComm;
import com.icbc.api.request.CreditCardMediumDetailedInfoQueryRequestV1.CreditCardMediumDetailedInfoQueryRequestV1QueryComm;
import com.icbc.api.request.CreditCardMediumDetailedInfoQueryRequestV1.CreditCardMediumDetailedInfoQueryRequestV1TrxComm;
import com.icbc.api.response.CreditCardMediumDetailedInfoQueryResponseV1;

/**
 * 信用卡详细信息查询
 * 
 * @author kfzx-caixiang
 *
 */
public class CreditCardMediumDetailedInfoQueryTestV1 {
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
		CreditCardMediumDetailedInfoQueryRequestV1 request = new CreditCardMediumDetailedInfoQueryRequestV1();
		// 6、设置请求路径；联测时，请联系我行告知并替换成测试环境地址
		request.setServiceUrl("http://[需替换]工行API网关地址/api/creditcard/medium/detailedinfo/query/V1");
		// 7、设置业务参数
		CreditCardMediumDetailedInfoQueryRequestV1Biz bizContent = new CreditCardMediumDetailedInfoQueryRequestV1Biz();
		CreditCardMediumDetailedInfoQueryRequestV1ChannelComm channelComm = bizContent.getChannelComm();
		CreditCardMediumDetailedInfoQueryRequestV1TrxComm trxComm = bizContent.getTrxComm();
		CreditCardMediumDetailedInfoQueryRequestV1CardComm cardComm = bizContent.getCardComm();
		CreditCardMediumDetailedInfoQueryRequestV1QueryComm queryComm = bizContent.getQueryComm();

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
		trxComm.setOpenZone("");
		trxComm.setAppType("0");

		cardComm.setCardNo("111111111");
		
		queryComm.setInitflag("1");
		queryComm.setPagebeginnum("1");
		queryComm.setPagefetchnum("5");

		bizContent.setChannelComm(channelComm);
		bizContent.setTrxComm(trxComm);
		bizContent.setCardComm(cardComm);
		bizContent.setQueryFlag("2");
		bizContent.setCustSort("");
		bizContent.setCustCode("");
		bizContent.setCino("");
		bizContent.setPwdCheckFlag("");
		bizContent.setSubcardFlag("");
		bizContent.setCardKind("");
		bizContent.setCdstatCheckFlag("");
		bizContent.setCustCheckFlag("");
		bizContent.setiRowCardNo("");

		request.setBizContent(bizContent);

		CreditCardMediumDetailedInfoQueryResponseV1 response = null;
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
