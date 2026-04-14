package com.icbc.api;

import com.icbc.api.request.MybankLoanInclusivefinanceProgressqryRequestV1;
import com.icbc.api.request.MybankLoanInclusivefinanceProgressqryRequestV1.MybankLoanInclusivefinanceProgressqryV1Biz;
import com.icbc.api.response.MybankLoanInclusivefinanceProgressqryResponseV1;


public class MybankLoanInclusivefinanceProgressqryTestV1 {
    //appid,privateKey,apigwPublicKey  根据实际的应用场景进行修攄1�7
	protected static final String appId = "xxxxxx";
	protected static final String privateKey = "xxxxxx";
	protected static final String apigwPublicKey = "xxxxxx";
    public static void main(String[] args) throws IcbcApiException {
        DefaultIcbcClient client = new DefaultIcbcClient(appId, "RSA2", privateKey, "UTF-8", "json", apigwPublicKey, null, null, null, null);
        MybankLoanInclusivefinanceProgressqryRequestV1 request = new MybankLoanInclusivefinanceProgressqryRequestV1();
		request.setServiceUrl("http://ip:port/api/mybank/loan/inclusivefinance/progressqry/V1");
		MybankLoanInclusivefinanceProgressqryV1Biz bizContent = new MybankLoanInclusivefinanceProgressqryV1Biz();
		bizContent.setSerialNo("12334455");
		bizContent.setAppNo("F-EBNKP");
		bizContent.setAreaCode("0020000072");
		bizContent.setEmployeeCode("000393868");
		bizContent.setLanguage("zh_CN");
		bizContent.setTransNo("IncfBillInfoCocoaService");
		bizContent.setVer("1.0");
		bizContent.setTurnPageFlag("0");
		bizContent.setBeginRow("1");
		bizContent.setRowCount("200");
		bizContent.setEbankLoanApplyNo("123414213");
		bizContent.setCnltype("302");
		bizContent.setTransDate("20170101");
		bizContent.setCino("020006202540160");
		bizContent.setCustomerName("�ﳬ");
		bizContent.setCertType("0");
		bizContent.setCertCode("221637198802036675");
		bizContent.setTerminalType("");
		bizContent.setTerminalIp("");
		bizContent.setTerminalMac("");

        request.setBizContent(bizContent);
        MybankLoanInclusivefinanceProgressqryResponseV1 response = new MybankLoanInclusivefinanceProgressqryResponseV1();
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
