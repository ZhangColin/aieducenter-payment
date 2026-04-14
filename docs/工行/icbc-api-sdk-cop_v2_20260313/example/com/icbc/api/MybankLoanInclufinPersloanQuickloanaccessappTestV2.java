package com.icbc.api;

import com.icbc.api.request.MybankLoanInclufinPersloanQuickloanaccessappRequestV2;
import com.icbc.api.request.MybankLoanInclufinPersloanQuickloanaccessappRequestV2.MybankLoanInclufinPersloanQuickloanaccessappV2Biz;
import com.icbc.api.response.MybankLoanInclufinPersloanQuickloanaccessappResponseV2;

/**
 * @ClassName
 * @Descirption 经营快贷申请开通
 * @Author kfzx-chenz
 */
public class MybankLoanInclufinPersloanQuickloanaccessappTestV2 {
    //appid,privateKey,apigwPublicKey  根据实际的应用场景进行修改
	protected static final String appId = "10000000000004099031";
	protected static final String privateKey = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCWRm7yznTipYpZ3EuEO/PDIkmIVlgdnzc6ZML7WiwHGboLl3jBOJ4MtYABxEychxArgYmOmNZ76ubztuJ2EIC2H7cxVWIwDxwF+sNf8+WgLin+tx4qnepr+roLuui7mL84aMddcLcESmVjx9kYV1tG/6m1o0lK9r5OYQVcK3FY3N/N+gFoYsXYVGnNO7ZIo3P7uDWfm0sJ6Blfh2sBVcjMhCBXYOJE/HZiEHQU3DiQM2YqcS7UrzsTGxm/q2LUjUJDjeJDeHTgxFdoHE/l6Mrcy1TeJ2ZMD7MrkqaUbrtfRiAXpRWonadnFUNWJeG7V4TqDth9Z14YOhLsm2zKBXIJAgMBAAECggEAL/nelAAbyvwktlmhdl8ZjLPWnANM8HATVr4yjcKVCdNJ1UePksh7lP0hF+9Osi5gmDMr8m2TFE7DWw2JMT5FiV+IweRLi6JXRQVw+KJKLiBu4dYeeGagH8cVPId4o+O5MM4q0AcbNvB1fCyif6XsCze2O7ocvSUXTrik2G86Ka8joM8/EPclIAzQNIOMog6DQRck33QGfODRxQFTi54mqSwRJmne0BSiO/rhOOEC+McymEwprO832OK6nJyPaar2K/L8ofeXER9grCGxFeHmFJ8F/vkWtgX4vNgwAhwuUW949B3Kzz+vOZjJ2SAK3sC1cflK4b4daV/8wEqWPBAf8QKBgQDmJRUUaWQ5AKnbLi5PCOGUMml8fw738lvaWyOFNHLnsbEMQamdtGB7Ej0ER6Hww0VII+NP68a28YizFVyoy1KttGUZFeGyIOjJpRq2uFAG3PvSJByfoYxx2A4GswfX2Qekk9OiuOTqpFekq65+bE+9mZpLO1XqMtxzc+6detxvdQKBgQCnKFCJfuZlgOG5H3P4aferTTY6e+xDOVNq1YP1sdmQl6DZM9bLp+ZhhLGElInZRN8AiEnXs7XXDHu8L6Vp7bHaNDAIxybkgqBl/ZvrhvOdDD2MOK1D5LU3Cd/dgZjNwAvfUtfcdzjSxMWH1fbeM/VvdMkDnC51+FA5RCUD/JRZxQKBgQDQY9d4ig20B8PFsUVBT1ti41rc3ffhz1QYndnF7b31ydH0tSguonuSNOuHCo26AjajoctcPLPMR1xuO/OQ67pLi0LXuhaJQn9GxvcvaCkebc6fcw9wA8m189qPemCps9zVyv0hJKYzSOgLNWFlHBIALiul325mB/PRnrH7appbWQKBgDsgqYxZVQomkrG3DICciz0O7NW3LBWDW5x4tlrcdEcPO5hZG+uiEn4AQALTwAcYs4EutGAWc4YEJmikufBEYhr7Rtp0B6/g/PfWw6aGb/nncIX/LlrLtcYoOgXSZ8iv3AM8/JpBmdoEPJMV/mMPIgww1UHvpweXtFjtX+KPH7K5AoGANT4MoJalNsHnBqq+D+qdRXnYxHZCXF5Z1obzTfemRola7WE6RYrhZipKDlFqQ2TPNj75zXeEVrD4TWRJg4wmlp85rjMRnWba+ZKx1/1eTKyDLtzlhVke7oRt5io6nza5G3QjzQjJTEJRapXuG4FCmz/jVaKeOAnBTSBFqANEXcg=";
	protected static final String apigwPublicKey = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
    public static void main(String[] args) throws IcbcApiException {
        DefaultIcbcClient client = new DefaultIcbcClient(appId, "RSA2", privateKey, "UTF-8", "json", apigwPublicKey, null, null, null, null);
        MybankLoanInclufinPersloanQuickloanaccessappRequestV2 request = new MybankLoanInclufinPersloanQuickloanaccessappRequestV2();
		request.setServiceUrl("http://ip:port/api/mybank/loan/inclufin/persloan/quickloanaccessapp/V2");
		MybankLoanInclufinPersloanQuickloanaccessappV2Biz bizContent = new MybankLoanInclufinPersloanQuickloanaccessappV2Biz();
		bizContent.setSerialNo("123123123");
		bizContent.setAppNo("F-APIP");
		bizContent.setAreaCode("");
		bizContent.setEmployeeCode("");
		bizContent.setLanguage("zh_CN");
		bizContent.setTransNo("IQuickOperLoanApplyCocoaService");
		bizContent.setVer("1.0");
		bizContent.setAppid("10000000000004099031");
		bizContent.setType("01");
		bizContent.setCustID("M12345678901234567");
		bizContent.setModelID("ZH1110");
		bizContent.setcName("网网一测试公司");
		bizContent.setCreditCode("M12345678901234567");
		bizContent.setName("网网一");
		bizContent.setIdType("0");
		bizContent.setIdNo("110106199006060208");
		bizContent.setCardNo("6222021001064513481");
		bizContent.setPhoneNo("15212345511");
		bizContent.setIdValid("");
		bizContent.setAddress("");
		bizContent.setRegion("");
		bizContent.setIndustry("");
		bizContent.setOccupation("");
		bizContent.setCreditLimit("");
		bizContent.setCooperUnit("NW0101124");
		bizContent.setBranchNo("0020000072");
		bizContent.setRemark1("");
		bizContent.setRemark2("");
		bizContent.setMonitorFlag("00001");
		bizContent.setOtherInfo(
				"{\"totalCntlist\":[{\"powerAccountNo\":\"1234567890\",\"pqPassMonth\":\"120\",\"tradeCode\":\"6510\",\"powerOffCode\":\"02\",\"pqAvgPass12Month\":\"12\",\"pqPass3Month\":\"A3\",\"pqPass12Month\":\"A5\",\"pqPass6Month\":\"A4\",\"pqPass24Month\":\"B1\",\"cap\":\"A1\",\"rcvedAmt2019\":\"A1\",\"rcvedAmtPass12Month\":\"A3\",\"rcvedAmtPass24Month\":\"A3\",\"rcvblAmtPass12Month\":\"A4\",\"rcvblAmtPass24Month\":\"A5\",\"penaltyPass24Month\":\"A6\",\"penaltyNumPass12Month\":\"1\",\"penaltyNumPass24Month\":\"1\"}]}");
		// System.out.println("-------经营快贷申请开通 入参--------" + bizContent);
        request.setBizContent(bizContent);
        MybankLoanInclufinPersloanQuickloanaccessappResponseV2 response = new MybankLoanInclufinPersloanQuickloanaccessappResponseV2();
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
