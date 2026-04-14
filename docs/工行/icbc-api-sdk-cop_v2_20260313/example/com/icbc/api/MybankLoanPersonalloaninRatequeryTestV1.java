package com.icbc.api;

import com.icbc.api.request.MybankLoanPersonalloaninRatequeryRequestV1;
import com.icbc.api.request.MybankLoanPersonalloaninRatequeryRequestV1.MybankLoanPersonalloaninRatequeryV1Biz;
import com.icbc.api.response.MybankLoanPersonalloaninRatequeryResponseV1;

public class MybankLoanPersonalloaninRatequeryTestV1 {
    //appid,privateKey,apigwPublicKey  需替换
	protected static final String appId = "10000000000004099031";
	protected static final String privateKey = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";
	protected static final String apigwPublicKey = "[需替换]工行API网关公钥，请向工行联系获取";
	public static void main(String[] args) throws IcbcApiException {
        DefaultIcbcClient client = new DefaultIcbcClient(appId, "RSA2", privateKey, "UTF-8", "json", apigwPublicKey, null, null, null, null);
        MybankLoanPersonalloaninRatequeryRequestV1 request = new MybankLoanPersonalloaninRatequeryRequestV1();
		String host = "[需替换]工行API网关地址";
		String apiUrl= host+"/api/mybank/loan/personalloanin/ratequery/V1";
        request.setServiceUrl(apiUrl);
		MybankLoanPersonalloaninRatequeryV1Biz bizContent = new MybankLoanPersonalloaninRatequeryV1Biz();
		
        bizContent.setSerialNo("12334455");
        bizContent.setAppNo("F-EBNKP");
        bizContent.setAreaCode("0020000072");
        bizContent.setEmployeeCode("000393868");
        bizContent.setLanguage("zh_CN");
        bizContent.setTransNo("IQueryExecuteRateInfCocoaService");
        bizContent.setVer("1.0");
        bizContent.setTurnPageFlag("0");
        bizContent.setBeginRow("1");
        bizContent.setRowCount("200");
        bizContent.setQueryType("01");
        bizContent.setBaseRateCode("26000");
        bizContent.setCurrType("001");
        bizContent.setRateMode("26000");
        bizContent.setProductCode("001");
        bizContent.setLoanTerm("100");
        bizContent.setBusiAreaCode("0020000072");
        bizContent.setRateFloatType("001");
        bizContent.setRateFloatValue("10");

        request.setBizContent(bizContent);
        MybankLoanPersonalloaninRatequeryResponseV1 response = new MybankLoanPersonalloaninRatequeryResponseV1();
        try {
            response = client.execute(request);
        } catch (Exception e) {
			// System.out.println(e);
        }
        if (response.isSuccess()) {
			// System.out.println("success!");
        } else {
			// System.out.println("fail" + response.getReturnMsg());
        }
    }


}
