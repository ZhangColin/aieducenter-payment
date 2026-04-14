package com.icbc.api;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.icbc.api.request.MybankLoanDigitalcreditQueryloanprogresszjsjRequestV1;
import com.icbc.api.request.MybankLoanDigitalcreditQueryloanprogresszjsjRequestV1.MybankLoanDigitalcreditQueryloanprogresszjsjRequestV1Biz;
import com.icbc.api.response.MybankLoanDigitalcreditQueryloanprogresszjsjResponseV1;

public class MybankLoanDigitalcreditQueryloanprogresszjsjTestV1 {

	public static void main(String[] args) {

		Log log = LogFactory.getLog(MybankLoanDigitalcreditQueryloanprogresszjsjTestV1.class);

		try {
			String apigwPublicKey =  "xxxxx";
			String privateKey = "xxxxx";
			String appId = "xxxxx";
			String url = "http://ip/api/mybank/loan/digitalcredit/queryloanprogresszjsj/V1";

			DefaultIcbcClient client = new DefaultIcbcClient(appId, privateKey,apigwPublicKey);
			MybankLoanDigitalcreditQueryloanprogresszjsjRequestV1 request = new MybankLoanDigitalcreditQueryloanprogresszjsjRequestV1();
			request.setServiceUrl(url);
			MybankLoanDigitalcreditQueryloanprogresszjsjRequestV1Biz bizContent = new MybankLoanDigitalcreditQueryloanprogresszjsjRequestV1Biz();

			bizContent.setSerialNo("201805131234567");
			bizContent.setAppNo("x-xxxx");
			bizContent.setAreaCode("");
			bizContent.setTeller("");
			bizContent.setLanguage("zh_CN");
			bizContent.setVerify("");
			bizContent.setTransNo("ILoanProgressService");
			bizContent.setVER("1.0");
			bizContent.setTurnPage("0");
			bizContent.setBeginRow("");
			bizContent.setEndRow("");
			bizContent.setPlatformNo("12345");
			bizContent.setCustomerNumb("12");
			bizContent.setPlatLoanInsno("001");

			request.setBizContent(bizContent);

			MybankLoanDigitalcreditQueryloanprogresszjsjResponseV1 response;
			response = client.execute(request);
			if (response.isSuccess()) {
				String a = response.getReturnContent();
				log.info(a);

			} else {

			}

		}  catch (IcbcApiException e1) {
			e1.printStackTrace();
		}

	}
}

