package com.icbc.api;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.request.MybankLoanDigitalcreditAcceptIssueInfoRequestV2;
import com.icbc.api.request.MybankLoanDigitalcreditAcceptIssueInfoRequestV2Biz;
import com.icbc.api.response.MybankLoanDigitalcreditAcceptIssueInfoResponseV2;

public class MybankLoanDigitalcreditAcceptIssueInfoTestV2 {

	 

	public static void main(String[] args) throws ParseException {
Log log = LogFactory.getLog(MybankLoanDigitalcreditAcceptIssueInfoTestV2.class);
		try {
			String apigwPublicKey = "xxxxx";
			String privateKey = "xxxxx";
			String appId = "XXXXXXXXXXXXXXXXXX";
			String url = "http://ip/api/mybank/loan/digitalcredit/acceptIssueInfo/V2";

			DefaultIcbcClient client = new DefaultIcbcClient(appId, privateKey, apigwPublicKey);
			MybankLoanDigitalcreditAcceptIssueInfoRequestV2 request = new MybankLoanDigitalcreditAcceptIssueInfoRequestV2();
			request.setServiceUrl(url);
			MybankLoanDigitalcreditAcceptIssueInfoRequestV2Biz bizContent = new MybankLoanDigitalcreditAcceptIssueInfoRequestV2Biz();
			HashMap<String, Object> bizParaMap = new HashMap<String, Object>();

			bizParaMap.put("SerialNo", "201805131234567");
			bizParaMap.put("AppNo", "x-xxxx");
			bizParaMap.put("AreaCode", "");
			bizParaMap.put("Teller", "");
			bizParaMap.put("Language", "zh_CN");
			bizParaMap.put("Verify", "");
			bizParaMap.put("TransNo", "IIssueInfoService");
			bizParaMap.put("VER", "1.0");
			bizParaMap.put("PlatformNo", "02");
			bizParaMap.put("CustomerNumb", "1");
			bizParaMap.put("ECreditNumb", "1234");
			bizParaMap.put("ECreditMoney", "12");
			bizParaMap.put("Currency", "001");
			bizParaMap.put("EffectDate", "20180101");
			bizParaMap.put("PayDate", "20190101");
			bizParaMap.put("PayerBankName", "134");
			bizParaMap.put("PayerBankAccount", "134");
			bizParaMap.put("CertificateType", "01");
			bizParaMap.put("modeType", "02");
			bizParaMap.put("isSecret", "1");
			bizParaMap.put("secretFileNo", "20190610001");
			bizParaMap.put("hasCertificate", "1");

			HashMap<String, Object> certificate = new HashMap<String, Object>();
			certificate.put("name", "12345");
			certificate.put("seqNo", "1");
			certificate.put("certificationBody", "aaa");
			certificate.put("certificateNo", "123");
			certificate.put("certificateNo", "123");
			certificate.put("obligee", "1");
			certificate.put("endDate", "20191212");
			certificate.put("certificateImageNO", "20190610002");

			List<Map<String, Object>> certificateList = new ArrayList<Map<String, Object>>();
			certificateList.add(certificate);
			bizParaMap.put("certificateList", certificateList);

			HashMap<String, Object> contract = new HashMap<String, Object>();
			contract.put("contractNum", "201906101532");
			contract.put("contractAmt", "123");
			contract.put("contractCur", "001");
			contract.put("tradeContent", "11");
			contract.put("payDate", "20191213");
			contract.put("contractImageNo", "11");

			List<Map<String, Object>> contractList = new ArrayList<Map<String, Object>>();
			contractList.add(contract);
			bizParaMap.put("contractList", contractList);

			HashMap<String, Object> iBillInfo = new HashMap<String, Object>();
			iBillInfo.put("contractNum", "201906101529");
			iBillInfo.put("billSerialNo", "1");
			iBillInfo.put("billMoney", "123.33");
			iBillInfo.put("billSerialNo", "1");
			iBillInfo.put("billCode", "123");
			iBillInfo.put("billNum", "123");
			iBillInfo.put("billMoney", "1230.00");
			iBillInfo.put("billCurrency", "001");
			iBillInfo.put("billKind", "01");
			iBillInfo.put("billImageNo", "123");

			List<Map<String, Object>> iBillInfoList = new ArrayList<Map<String, Object>>();
			iBillInfoList.add(iBillInfo);
			bizParaMap.put("iBillInfoList", iBillInfoList);

			bizContent.setParam(bizParaMap);
			request.setBizContent(bizContent);

			MybankLoanDigitalcreditAcceptIssueInfoResponseV2 response;
			response = client.execute(request);
			if (response.isSuccess()) {
				String a = response.getReturnContent();
				log.info(a);
			}

		} catch (IcbcApiException e1) {
			e1.printStackTrace();
		}

	}
}
