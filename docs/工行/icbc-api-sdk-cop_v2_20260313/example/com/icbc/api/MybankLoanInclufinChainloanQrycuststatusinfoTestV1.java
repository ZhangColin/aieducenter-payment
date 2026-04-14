package com.icbc.api;

import com.icbc.api.request.MybankLoanInclufinChainloanQrycuststatusinfoRequestV1;
import com.icbc.api.request.MybankLoanInclufinChainloanQrycuststatusinfoRequestV1.MybankLoanInclufinChainloanQrycuststatusinfoV1Biz;
import com.icbc.api.response.MybankLoanInclufinChainloanQrycuststatusinfoResponseV1;

public class MybankLoanInclufinChainloanQrycuststatusinfoTestV1 {

	// APP_ID,APIGW_PUBLIC_KEY,PRI_KEY,URL 根据实际的应用场景进行修改
	protected static final String APP_ID = "xxxxxxx";
	protected static final String APIGW_PUBLIC_KEY = "xxxxxxx";
	protected static final String PRI_KEY = "xxxxxxx";
	protected static final String URL = "http://xxxxxxx/api/mybank/loan/inclufin/chainloan/qrycuststatusinfo/V1";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MybankLoanInclufinChainloanQrycuststatusinfoRequestV1 request = new MybankLoanInclufinChainloanQrycuststatusinfoRequestV1();
		MybankLoanInclufinChainloanQrycuststatusinfoResponseV1 response = new MybankLoanInclufinChainloanQrycuststatusinfoResponseV1();
		MybankLoanInclufinChainloanQrycuststatusinfoV1Biz bizContent = new MybankLoanInclufinChainloanQrycuststatusinfoV1Biz();

		// 公共参数
		bizContent.setSerialNo("201708231441XX020083999");
		bizContent.setAppNo("F-GCMS-NFS");
		bizContent.setAreaCode("0120XX0000");
		bizContent.setTeller("0200XX999");
		bizContent.setLanguage("zh_CN");
		bizContent.setVerify("");
		bizContent.setTransNo("CorporInfoCocoaService");
		bizContent.setVER("1.0");
		// 私有参数
		bizContent.setCoreEntName(APP_ID);
		bizContent.setCoreEntType("01");
		bizContent.setCoreEntId("12345XXXXXXXX6789");
		bizContent.setCustName("王XX");
		bizContent.setCertType("01");
		bizContent.setCertCode("372926XXXXXXXX0537");
		bizContent.setSupplyChainNo("GY-20160XXXX1234");
		bizContent.setInnerCustNo("12345XXXXXXXX6789");

		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", PRI_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY,
				null, null, null, null);

		request.setServiceUrl(URL);
		request.setBizContent(bizContent);

		try {
			response = client.execute(request);
		} catch (Exception e) {
			// System.out.println(e.getMessage());
		}
		if (response.isSuccess()) {
			// System.out.println(e.getMessage());
		} else {
			// System.out.println(e.getMessage());
		}
		// System.out.println(response);
	}

}
