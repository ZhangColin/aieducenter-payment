package com.icbc.api;

import com.icbc.api.request.MybankLoanInclusivefinanceCreditlimitcheckRequestV1;
import com.icbc.api.response.MybankLoanInclusivefinanceCreditlimitcheckResponseV1;

public class MybankLoanInclusivefinanceCreditlimitcheckTestV1 {
	// appid,privateKey,apigwPublicKey 根据实际的应用场景进行修改
	protected static final String appId = "123";

	protected static final String privateKey = "actualprivateKeyValue";
	protected static final String apigwPublicKey = "actualpublicKeyValue+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

	public static void main(String[] args) throws IcbcApiException {
		DefaultIcbcClient client = new DefaultIcbcClient(appId, privateKey, apigwPublicKey);
		MybankLoanInclusivefinanceCreditlimitcheckRequestV1 request = new MybankLoanInclusivefinanceCreditlimitcheckRequestV1();

		request.setServiceUrl("http://ip:port/api/mybank/loan/inclusivefinance/creditlimitcheck/V1");
		MybankLoanInclusivefinanceCreditlimitcheckRequestV1.MybankLoanInclusivefinanceCreditlimitcheckV1Biz param = new MybankLoanInclusivefinanceCreditlimitcheckRequestV1.MybankLoanInclusivefinanceCreditlimitcheckV1Biz();
		// 公共参数
		param.setSerialNo("12334455");
		param.setAppNo("F-APIP");
		param.setAreaCode("");
		param.setEmployeeCode("");
		param.setLanguage("zh_CN");
		param.setTransNo("/mybank/loan/inclusivefinance/creditlimitcheck/V1");
		param.setVer("1.0");
		param.setTurnPageFlag("");
		param.setBeginRow("");
		param.setRowCount("");
		// 私有参数
		param.setCooperUnit("12334455");
		param.setCustName("12334455");
		param.setCertType("12334455");
		param.setCertCode("12334455");
		param.setCellphone("12334455");
		param.setCnltype("12334455");
		param.setTerminal_type("12334455");
		param.setTerminal_ip("12334455");
		param.setTerminal_mac("12334455");

		request.setBizContent(param);
		MybankLoanInclusivefinanceCreditlimitcheckResponseV1 response = new MybankLoanInclusivefinanceCreditlimitcheckResponseV1();
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
