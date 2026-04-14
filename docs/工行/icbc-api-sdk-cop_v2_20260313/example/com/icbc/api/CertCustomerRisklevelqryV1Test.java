package com.icbc.api;
import org.junit.Assert;
import org.junit.Test;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcConstants;
import com.icbc.api.request.CertCustomerRisklevelqryRequestV1;
import com.icbc.api.request.CertCustomerRisklevelqryRequestV1.CertCustomerRisklevelqryRequestV1Biz;
import com.icbc.api.response.CertCustomerRisklevelqryResponseV1;

/**
 * 反洗钱客户风险分类等级查询
 * @author kfzx-hanrj01
 *
 */
public class CertCustomerRisklevelqryV1Test {

	
	public void test_cop() {
		String APP_ID = "*****";		//appid
		String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EwAIBADANBgkqhkiG9w0BAQEFAASCBKowggSmAgEAAoIBAQCtpRJQwmhJ01NioEKj3Y9spD2eFwnRUPPA2puWr574VO3waoYPBgFsdK/XMS2WgmV+jMOO6ahvQ+t9fuEuRW2/lbjSphDDUITRtScw30nJlahcFyaBiU/2sAi3qZbcvHcT76PUIhpo9H4vu02X0AhfskyyXzoihFEY2v3rM7umtm/X9gkHWxMoC/4Zyi5IW9PPdbGwdz+X8oJ9ioa2aty3NcqfFaT7t3vBk7Utq+wf8/m9S5Oo+FeKyHPCEW2+4DSFSus+DVJ2TSOqWq7M8NzfGNeKvXLg9WBs7NSxvlmXaDRo0qFjLLsGfgTqsn4+QarrCNczZWQQ7iYlJgbVLjQRAgMBAAECggEBAJGkOlByPnZtQSsfr8UxN/Kvbt8CA8IEW+4NbSJd+r/evBNBxV8kcl09IZ8kKFBs3iPefMeJQzTPityfZs8gfISMV5CWqMio6D3b6xsarRDe7W8QG8PaH6+Qxj8FhvttN5MJFCgA0pXlO1FdkB8c5WkeayXQqTn6MOrgAskbgvC4ALxKFT90wR5Nbv/tUM2LfM8gTch/RlQ2OkMAB5QRrJ5PAJMS8C1cijRTUixsvs4rsBMCwpIxwi32227lHhS6qujqHgIsp9ZC9GbFxE192BeOtt9afX34u3kx2a3efv/gWBpq0ZWSBu2mCyrmg1PEqXv943Ut7SXVDo9vxy5XAIECgYEA5xLebOGnj1lnYR5KvdLg7ryKt/Z40NQBSaC0yCh2vEGrBARUpw64vJKwqL3yTtEw2ybE43s71Q+B+YpeRLvwVyMNRNHn8fWvAKglrDRjHHo1UH9rJKRpPMNG9mlgmACgvFMMfA7k8D6RlGVCG1Ktwij8QQPlzwMTbDHzS+gEgJ0CgYEAwGBLzSJpx0314rRaI/35OdLifrvoYaJjiXIxFsy6Qwjhev+Oykjjg2FFdD0PJNa+D4EqAF9E9yzOFxWbLhtzA8e1U/NORuR+V96nHgCuEMWUNmO6fmi02wmsoshr2rVe/2S1zuOFUko6o+7VjNy2PJXmvCypCiN9tbgKRj41JQUCgYEAxgMFV4iHbe/PBwpOQhxTZEFtHfe30DvcXpu4T01dcjVTtz1+zSlwZSfO+srKuX/JvgKa9dTEkO+ZEZ3g6kWizwjadf7qJA3EyWtN3SOHSv/ub+zAg8Xf6JHuXXovrBHdq4P/Ts/0n2r+lYuv+nn/LEKy54PPinW4Tx7+vi00roECgYEArUXO4JxvdDGXGDPjwOlf0qJMmWP8wfv29XSiHGwYFIrqNNP8M90E92/kk/bvLfoxxFtNjnpA7vUvR4GRfS0CIlR90Xijd50U42ZLVe282qRrg7W941/CDkwfb5tf8eVNBHIcg5VQnzb2rHNRa4fcqKNH6RO8ycH/125F6tDbI1UCgYEAvDGahhmJoxPf8JEjUaFmJhqQQVpCqdKDIXqGZpxxM7MBOUEIDcBjjgayA+MF6Dn1ShzuTBRoEMCNOm4VSUMCwGFd6Yv932DJB9iwBYMUiChA5b6vK/ldeyQ2EWzoa8Ilw+ik/9fT+22fmQ5pG6v8CfL3UaE0GnF/El9lHAbjMVw=";
		String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

		CertCustomerRisklevelqryRequestV1 request = new CertCustomerRisklevelqryRequestV1();
		request.setServiceUrl("https://ip:port/api/cert/customer/risklevelqry/V1");
		CertCustomerRisklevelqryRequestV1Biz bizContent = new CertCustomerRisklevelqryRequestV1Biz();
		bizContent.setSerialNo("*****");		//请求序列号
		bizContent.setAppNo("F-AML");
		bizContent.setAreaCode("0020000067");
		bizContent.setTeller("020083999");
		bizContent.setLanguage("zh_CN");
		bizContent.setVer("1.0");
		bizContent.setQueryType("1");
		bizContent.setCustNo("020090001957324");
		bizContent.setBranchId("");
		bizContent.setCustType("");
		bizContent.setRegType("");
		bizContent.setRegNo("");

		request.setBizContent(bizContent);

		CertCustomerRisklevelqryResponseV1 response;
		try {
			response = client.execute(request, "msgId");
			if (response.isSuccess() ) {
				// 业务成功处理
				Assert.assertEquals(response.getStatus(), 0);
			} else {
				// 失败
			}		
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}
	}
}
