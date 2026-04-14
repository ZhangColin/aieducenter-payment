package com.icbc.api;

import com.icbc.api.request.MybankLoanInclufinChainloanQuerychaintradelistRequestV1;
import com.icbc.api.response.MybankLoanInclufinChainloanQuerychaintradelistResponseV1;

public class MybankLoanInclufinChainloanQuerychaintradelistTestV1 {

	protected static final String appId = "10000000000004097132";

	protected static final String privateKey = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCWRm7yznTipYpZ3EuEO/PDIkmIVlgdnzc6ZML7WiwHGboLl3jBOJ4MtYABxEychxArgYmOmNZ76ubztuJ2EIC2H7cxVWIwDxwF+sNf8+WgLin+tx4qnepr+roLuui7mL84aMddcLcESmVjx9kYV1tG/6m1o0lK9r5OYQVcK3FY3N/N+gFoYsXYVGnNO7ZIo3P7uDWfm0sJ6Blfh2sBVcjMhCBXYOJE/HZiEHQU3DiQM2YqcS7UrzsTGxm/q2LUjUJDjeJDeHTgxFdoHE/l6Mrcy1TeJ2ZMD7MrkqaUbrtfRiAXpRWonadnFUNWJeG7V4TqDth9Z14YOhLsm2zKBXIJAgMBAAECggEAL/nelAAbyvwktlmhdl8ZjLPWnANM8HATVr4yjcKVCdNJ1UePksh7lP0hF+9Osi5gmDMr8m2TFE7DWw2JMT5FiV+IweRLi6JXRQVw+KJKLiBu4dYeeGagH8cVPId4o+O5MM4q0AcbNvB1fCyif6XsCze2O7ocvSUXTrik2G86Ka8joM8/EPclIAzQNIOMog6DQRck33QGfODRxQFTi54mqSwRJmne0BSiO/rhOOEC+McymEwprO832OK6nJyPaar2K/L8ofeXER9grCGxFeHmFJ8F/vkWtgX4vNgwAhwuUW949B3Kzz+vOZjJ2SAK3sC1cflK4b4daV/8wEqWPBAf8QKBgQDmJRUUaWQ5AKnbLi5PCOGUMml8fw738lvaWyOFNHLnsbEMQamdtGB7Ej0ER6Hww0VII+NP68a28YizFVyoy1KttGUZFeGyIOjJpRq2uFAG3PvSJByfoYxx2A4GswfX2Qekk9OiuOTqpFekq65+bE+9mZpLO1XqMtxzc+6detxvdQKBgQCnKFCJfuZlgOG5H3P4aferTTY6e+xDOVNq1YP1sdmQl6DZM9bLp+ZhhLGElInZRN8AiEnXs7XXDHu8L6Vp7bHaNDAIxybkgqBl/ZvrhvOdDD2MOK1D5LU3Cd/dgZjNwAvfUtfcdzjSxMWH1fbeM/VvdMkDnC51+FA5RCUD/JRZxQKBgQDQY9d4ig20B8PFsUVBT1ti41rc3ffhz1QYndnF7b31ydH0tSguonuSNOuHCo26AjajoctcPLPMR1xuO/OQ67pLi0LXuhaJQn9GxvcvaCkebc6fcw9wA8m189qPemCps9zVyv0hJKYzSOgLNWFlHBIALiul325mB/PRnrH7appbWQKBgDsgqYxZVQomkrG3DICciz0O7NW3LBWDW5x4tlrcdEcPO5hZG+uiEn4AQALTwAcYs4EutGAWc4YEJmikufBEYhr7Rtp0B6/g/PfWw6aGb/nncIX/LlrLtcYoOgXSZ8iv3AM8/JpBmdoEPJMV/mMPIgww1UHvpweXtFjtX+KPH7K5AoGANT4MoJalNsHnBqq+D+qdRXnYxHZCXF5Z1obzTfemRola7WE6RYrhZipKDlFqQ2TPNj75zXeEVrD4TWRJg4wmlp85rjMRnWba+ZKx1/1eTKyDLtzlhVke7oRt5io6nza5G3QjzQjJTEJRapXuG4FCmz/jVaKeOAnBTSBFqANEXcg=";
	protected static final String apigwPublicKey = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

	public static void main(String[] args) throws IcbcApiException {
		DefaultIcbcClient client = new DefaultIcbcClient(appId, "RSA2", privateKey, "UTF-8", "json", apigwPublicKey, (String)null, (String)null, (String)null, (String)null);
		MybankLoanInclufinChainloanQuerychaintradelistRequestV1 request = new MybankLoanInclufinChainloanQuerychaintradelistRequestV1();

		request.setServiceUrl("http://ip:port/api/mybank/loan/inclufin/chainloan/querychaintradelist/V1");
		MybankLoanInclufinChainloanQuerychaintradelistRequestV1.MybankLoanInclufinChainloanQuerychaintradelistV1Biz param = new MybankLoanInclufinChainloanQuerychaintradelistRequestV1.MybankLoanInclufinChainloanQuerychaintradelistV1Biz();
		param.setSerialNo("202202170000001");
		param.setAppNo("");
		param.setAreaCode("0020000067");
		param.setTeller("");
		param.setLanguage("zh_CN");
		param.setTransNo("QueryChainTradeListService1");
		param.setVer("1");
		param.setVerify("");
		param.setTurnPage("0");
		param.setBeginRow("1");
		param.setEndRow("3");
		param.setEnpCode("020090001926823");
		param.setiSCore("1");
		param.setQueryScene("01");
		param.setSupplyChainCode("GY-2016000004412");
		param.setProductCode("01");
		param.setPaymentsCode("01");
		param.setOrderBeginDate("19700101");
		param.setOrderEndDate("20561231");
		param.setPayStatus("");
		param.setConfirmStatus("");
		param.setOppEnpCode("");
		param.setiSCore("0");
		param.setTradeInfoNo("");
		param.setPrePayBeginDate("");
		param.setPrePayEndDate("");
		request.setBizContent(param);
		MybankLoanInclufinChainloanQuerychaintradelistResponseV1 response = new MybankLoanInclufinChainloanQuerychaintradelistResponseV1();
		try {
			response = client.execute(request);
		} catch (Exception e) {
			 System.out.println(e.getMessage());
		}
		if (response.isSuccess()) {
			 System.out.println("success!");
		} else {
			 System.out.println("fail" + response.getReturnMsg());
		}
		 System.out.println(response);
	}
}
