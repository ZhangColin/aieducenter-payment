package com.icbc.api;

import com.icbc.api.request.MybankLoanInclusivefinanceRepaymentapyRequestV1;
import com.icbc.api.response.MybankLoanInclusivefinanceRepaymentapyResponseV1;

public class MybankLoanInclusivefinanceRepaymentapyTestV1 {
	// appid,privateKey,apigwPublicKey 根据实际的应用场景进行修攄1�7
	protected static final String appId = "xxxxxx";

	protected static final String privateKey = "xxxxxx";
	protected static final String apigwPublicKey = "xxxxxx";

	public static void main(String[] args) throws IcbcApiException {
			DefaultIcbcClient client = new DefaultIcbcClient(appId, privateKey, apigwPublicKey);
			MybankLoanInclusivefinanceRepaymentapyRequestV1 request = new MybankLoanInclusivefinanceRepaymentapyRequestV1();

			request.setServiceUrl("http://ip:port/api/mybank/loan/inclusivefinance/creditlimitcheck/V1");
			MybankLoanInclusivefinanceRepaymentapyRequestV1.MybankLoanInclusivefinanceRepaymentapyV1Biz param = new MybankLoanInclusivefinanceRepaymentapyRequestV1.MybankLoanInclusivefinanceRepaymentapyV1Biz();
			// 公共参数
			param.setSerialNo("12334455");
			param.setAppNo("F-APIP");
			param.setAreaCode("0020000072");
			param.setLanguage("zh_CN");
			param.setTransNo("IPerLoaReapymentCocoaService");
			// 私有参数
			param.setCooperUnit("DY1707104");
			param.setDealType("1");
			param.setApplyNo("DY170710420210331052");
			param.setEbankLoanApplyNo("0000000000000021858");
			param.setLnno("1");
			param.setRepayAmt("50");
			param.setRepayEventNo("");
			param.setOperFlag("");
			param.setCalDate("");
			param.setCnltype("405");
			param.setTerminalType("");
			param.setTerminalIp("");
			param.setTerminalMac("");

			request.setBizContent(param);
			MybankLoanInclusivefinanceRepaymentapyResponseV1 response = new MybankLoanInclusivefinanceRepaymentapyResponseV1();
			try {
				response = client.execute(request);
			} catch (Exception e) {
				// System.out.println(e.getMessage());
			}
			if (response.isSuccess()) {
				// System.out.println("success!");
			} else {
				// System.out.println("fail" + response.getReturnMsg());
			}
			// System.out.println(response);
	}
}
