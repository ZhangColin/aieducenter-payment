package com.icbc.api;

import com.icbc.api.request.MybankLoanInclusivefinanceCreditlimitcheckRequestV2;
import com.icbc.api.response.MybankLoanInclusivefinanceCreditlimitcheckResponseV2;

public class MybankLoanInclusivefinanceCreditlimitcheckTestV2 {
	// appid,privateKey,apigwPublicKey 根据实际的应用场景进行修改
	protected static final String appId = "123";

	protected static final String privateKey = "actualprivateKeyValue";
	protected static final String apigwPublicKey = "actualpublicKeyValue+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

	public static void main(String[] args) throws IcbcApiException {
		DefaultIcbcClient client = new DefaultIcbcClient(appId, privateKey, apigwPublicKey);
		MybankLoanInclusivefinanceCreditlimitcheckRequestV2 request = new MybankLoanInclusivefinanceCreditlimitcheckRequestV2();

		request.setServiceUrl("http://ip:port/api/mybank/loan/inclusivefinance/creditlimitcheck/V2");
		MybankLoanInclusivefinanceCreditlimitcheckRequestV2.MybankLoanInclusivefinanceCreditlimitcheckV2Biz param = new MybankLoanInclusivefinanceCreditlimitcheckRequestV2.MybankLoanInclusivefinanceCreditlimitcheckV2Biz();
		// 公共参数
		param.setSerialNo("123456671234567");
		param.setAppNo("F-APIP");
		param.setAreaCode("");
		param.setEmployeeCode("");
		param.setLanguage("zh_CN");
		param.setTransNo("IQuickLoanHandleQueryCheckCocoaService");
		param.setVer("1.0");
		param.setTurnPageFlag("");
		param.setBeginRow("");
		param.setRowCount("");
		// 私有参数
		param.setCooperUnit("ST2313014");
		param.setCustName("test123");
		param.setCertType("0");
		param.setCertCode("330227199301234567");
		param.setCellphone("13312346666");
		param.setCnltype("405");
		param.setTerminal_type("");
		param.setTerminal_ip("");
		param.setTerminal_mac("");

		request.setBizContent(param);
		MybankLoanInclusivefinanceCreditlimitcheckResponseV2 response = new MybankLoanInclusivefinanceCreditlimitcheckResponseV2();
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
