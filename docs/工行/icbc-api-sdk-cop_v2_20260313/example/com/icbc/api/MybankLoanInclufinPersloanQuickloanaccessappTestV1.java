package com.icbc.api;

import com.icbc.api.request.MybankLoanInclufinPersloanQuickloanaccessappRequestV1;
import com.icbc.api.request.MybankLoanInclufinPersloanQuickloanaccessappRequestV1.MybankLoanInclufinPersloanQuickloanaccessappV1Biz;
import com.icbc.api.response.MybankLoanInclufinPersloanQuickloanaccessappResponseV1;

/**
 * @ClassName
 * @Descirption 经营快贷申请开通
 * @Author kfzx-shenkz
 */
public class MybankLoanInclufinPersloanQuickloanaccessappTestV1 {
    //appid,privateKey,apigwPublicKey  根据实际的应用场景进行修改
	protected static final String appId = "123";

	protected static final String privateKey = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCWRm7yznTipYpZ3EuEO/PDIkmIVlgdnzc6ZML7WiwHGboLl3jBOJ4MtYABxEychxArgYmOmNZ76ubztuJ2EIC2H7cxVWIwDxwF+sNf8+WgLin+tx4qnepr+roLuui7mL84aMddcLcESmVjx9kYV1tG/6m1o0lK9r5OYQVcK3FY3N/N+gFoYsXYVGnNO7ZIo3P7uDWfm0sJ6Blfh2sBVcjMhCBXYOJE/HZiEHQU3DiQM2YqcS7UrzsTGxm/q2LUjUJDjeJDeHTgxFdoHE/l6Mrcy1TeJ2ZMD7MrkqaUbrtfRiAXpRWonadnFUNWJeG7V4TqDth9Z14YOhLsm2zKBXIJAgMBAAECggEAL/nelAAbyvwktlmhdl8ZjLPWnANM8HATVr4yjcKVCdNJ1UePksh7lP0hF+9Osi5gmDMr8m2TFE7DWw2JMT5FiV+IweRLi6JXRQVw+KJKLiBu4dYeeGagH8cVPId4o+O5MM4q0AcbNvB1fCyif6XsCze2O7ocvSUXTrik2G86Ka8joM8/EPclIAzQNIOMog6DQRck33QGfODRxQFTi54mqSwRJmne0BSiO/rhOOEC+McymEwprO832OK6nJyPaar2K/L8ofeXER9grCGxFeHmFJ8F/vkWtgX4vNgwAhwuUW949B3Kzz+vOZjJ2SAK3sC1cflK4b4daV/8wEqWPBAf8QKBgQDmJRUUaWQ5AKnbLi5PCOGUMml8fw738lvaWyOFNHLnsbEMQamdtGB7Ej0ER6Hww0VII+NP68a28YizFVyoy1KttGUZFeGyIOjJpRq2uFAG3PvSJByfoYxx2A4GswfX2Qekk9OiuOTqpFekq65+bE+9mZpLO1XqMtxzc+6detxvdQKBgQCnKFCJfuZlgOG5H3P4aferTTY6e+xDOVNq1YP1sdmQl6DZM9bLp+ZhhLGElInZRN8AiEnXs7XXDHu8L6Vp7bHaNDAIxybkgqBl/ZvrhvOdDD2MOK1D5LU3Cd/dgZjNwAvfUtfcdzjSxMWH1fbeM/VvdMkDnC51+FA5RCUD/JRZxQKBgQDQY9d4ig20B8PFsUVBT1ti41rc3ffhz1QYndnF7b31ydH0tSguonuSNOuHCo26AjajoctcPLPMR1xuO/OQ67pLi0LXuhaJQn9GxvcvaCkebc6fcw9wA8m189qPemCps9zVyv0hJKYzSOgLNWFlHBIALiul325mB/PRnrH7appbWQKBgDsgqYxZVQomkrG3DICciz0O7NW3LBWDW5x4tlrcdEcPO5hZG+uiEn4AQALTwAcYs4EutGAWc4YEJmikufBEYhr7Rtp0B6/g/PfWw6aGb/nncIX/LlrLtcYoOgXSZ8iv3AM8/JpBmdoEPJMV/mMPIgww1UHvpweXtFjtX+KPH7K5AoGANT4MoJalNsHnBqq+D+qdRXnYxHZCXF5Z1obzTfemRola7WE6RYrhZipKDlFqQ2TPNj75zXeEVrD4TWRJg4wmlp85rjMRnWba+ZKx1/1eTKyDLtzlhVke7oRt5io6nza5G3QjzQjJTEJRapXuG4FCmz/jVaKeOAnBTSBFqANEXcg=";
	protected static final String apigwPublicKey = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
    public static void main(String[] args) throws IcbcApiException {
        DefaultIcbcClient client = new DefaultIcbcClient(appId, "RSA2", privateKey, "UTF-8", "json", apigwPublicKey, null, null, null, null);
        MybankLoanInclufinPersloanQuickloanaccessappRequestV1 request = new MybankLoanInclufinPersloanQuickloanaccessappRequestV1();
		request.setServiceUrl("http://ip:port/api/mybank/loan/inclufin/persloan/quickloanaccessapp/V1");
		MybankLoanInclufinPersloanQuickloanaccessappV1Biz param = new MybankLoanInclufinPersloanQuickloanaccessappV1Biz();
		param.setSerialNo("123123123");
		param.setAppNo("F-APIP");
		param.setAreaCode("");
		param.setEmployeeCode("");
		param.setLanguage("zh_CN");
		param.setTransNo("IQuickOperLoanApplyCocoaService");
		param.setVer("1.0");
		param.setTurnPageFlag("");
		param.setBeginRow("");
		param.setRowCount("");
		param.setAppid("123");
		param.setType("01");
		param.setCustID("123");
		param.setModelID("ZH1107");
		param.setcName("万润能源贸易");
		param.setCreditCode("123");
		param.setName("谢凤雨");
		param.setIdType("0");
		param.setIdNo("123");
		param.setCardNo("123");
		param.setPhoneNo("123");
		param.setIdValid("");
		param.setAddress("");
		param.setRegion("");
		param.setIndustry("");
		param.setOccupation("");
		param.setCreditLimit("");
		param.setCooperUnit("123");
		param.setBranchNo("0020000072");
		param.setRemark1("");
		param.setRemark2("");
		param.setMonitorFlag("00001");
		param.setOtherInfo(
				"{\"totalCntlist\":[{\"powerAccountNo\":\"1234567890\",\"pqPassMonth\":\"120\",\"tradeCode\":\"6510\",\"powerOffCode\":\"02\",\"pqAvgPass12Month\":\"12\",\"pqPass3Month\":\"A3\",\"pqPass12Month\":\"A5\",\"pqPass6Month\":\"A4\",\"pqPass24Month\":\"B1\",\"cap\":\"A1\",\"rcvedAmt2019\":\"A1\",\"rcvedAmtPass12Month\":\"A3\",\"rcvedAmtPass24Month\":\"A3\",\"rcvblAmtPass12Month\":\"A4\",\"rcvblAmtPass24Month\":\"A5\",\"penaltyPass24Month\":\"A6\",\"penaltyNumPass12Month\":\"1\",\"penaltyNumPass24Month\":\"1\"}]}");
		// System.out.println("-------经营快贷申请开通 入参--------" + param);
        request.setBizContent(param);
        MybankLoanInclufinPersloanQuickloanaccessappResponseV1 response = new MybankLoanInclufinPersloanQuickloanaccessappResponseV1();
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
