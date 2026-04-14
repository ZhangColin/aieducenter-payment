package com.icbc.api;

import com.icbc.api.request.CreditCardPersonalFilemaintainFlieRequestV1;
import com.icbc.api.request.CreditCardPersonalFilemaintainFlieRequestV1.CreditCardPersonalFilemaintainFlieRequestV1Biz;
import com.icbc.api.request.CreditCardPersonalFilemaintainFlieRequestV1.CreditCardPersonalFilemaintainFlieRequestV1ChannelComm;
import com.icbc.api.request.CreditCardPersonalFilemaintainFlieRequestV1.CreditCardPersonalFilemaintainFlieRequestV1InputInfo;
import com.icbc.api.request.CreditCardPersonalFilemaintainFlieRequestV1.CreditCardPersonalFilemaintainFlieRequestV1TrxComm;
import com.icbc.api.response.CreditCardPersonalFilemaintainFlieResponseV1;

/**
 * 信用卡个人档案补充维护
 * 
 * @author kfzx-liangd01
 *
 */
public class CreditCardPersonalFilemaintainFlieTestV1 {
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
		CreditCardPersonalFilemaintainFlieRequestV1 request = new CreditCardPersonalFilemaintainFlieRequestV1();
		// 6、设置请求路径；联测时，请联系我行告知并替换成测试环境地址
		request.setServiceUrl("http://[需替换]工行API网关地址/api/credicardt/customer/personfile/maintainFlie/V1");
		// 7、设置业务参数
		CreditCardPersonalFilemaintainFlieRequestV1Biz bizContent = new CreditCardPersonalFilemaintainFlieRequestV1Biz();
		CreditCardPersonalFilemaintainFlieRequestV1ChannelComm channelComm = bizContent.getChannelComm();
		CreditCardPersonalFilemaintainFlieRequestV1TrxComm trxComm = bizContent.getTrxComm();
		CreditCardPersonalFilemaintainFlieRequestV1InputInfo inputInfo = bizContent.getInputInfo();

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
		inputInfo.setLocalFlag("");
		inputInfo.setMonthlySuply("");
		inputInfo.setHouseStat("0");
		inputInfo.setHouseInDate("");
		inputInfo.setVipFlag("");
		inputInfo.setCorperCode("");
		inputInfo.setAppScore("");
		inputInfo.setGuaScore("");
		inputInfo.setCredit("");
		inputInfo.setPassport("8081");
		inputInfo.setTuName("");
		inputInfo.setDept("");
		inputInfo.setNotes1("");
		inputInfo.setNotes2("");
		inputInfo.setViceRelFlag("");
		inputInfo.setRelFlag("0");
		inputInfo.setZoneNo("");
		inputInfo.setSalaryVerifyFlag("1");
		inputInfo.setSchoolEnName("");
		inputInfo.setPreGradDate("");
		inputInfo.setLclPledValue("");
		inputInfo.setCustKind("001");
		inputInfo.setForPledValue("");
		inputInfo.setChnCustCode("0015819");
		inputInfo.setChnCustName("test15819");
		inputInfo.setCreditReportDate("");
		inputInfo.setSalaryVerifyDate("");
		inputInfo.setPreappPermanentQuota("");
		inputInfo.setPreappTempQuota("");
		inputInfo.setPermanentQuotaAppDate("");
		inputInfo.setPermanentQuotaDisDate("");
		inputInfo.setPreappNotes("");
		inputInfo.setTempQuotaAppDate("");
		inputInfo.setTempQuotaDisDate("");
		inputInfo.setCrcardLoadType("");

		bizContent.setChannelComm(channelComm);
		bizContent.setTrxComm(trxComm);
		bizContent.setInputInfo(inputInfo);

		request.setBizContent(bizContent);

		CreditCardPersonalFilemaintainFlieResponseV1 response = null;
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
