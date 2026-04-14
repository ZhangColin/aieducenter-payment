package com.icbc.api;

import java.util.ArrayList;

import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.MybankAccountFarmParamsyncRequestV1;
import com.icbc.api.request.MybankAccountFarmParamsyncRequestV1.MybankAccountFarmParamsyncRequestBizV1;
import com.icbc.api.response.MybankAccountFarmParamsyncResponseV1;

public class MybankAccountFarmParamsyncTest {
/*
	// protected static String APP_ID = "10000000000000002225";
	protected static String APP_ID = "10000000000004096993";
	protected static String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
	protected static String PRI_KEY = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCR8/ZvKPAdZzsyvapySvztQm56s1N59ynKMOWpUbK4c5MNWMl+q3dvsp+UiSAx2TAeGkYfW3W6nO/3Y0hAZki99fmuzpPujEeHhs79HNwGZYQjN71Vck2JeflTq8jpL+9/up0Kz2nbwtZDMKTTEgbfNeO24gV1bmvD2kQ9j66RIyuXSDwQbVbQfl6LiqKoJm3rbtsbwX1Ytc0/Szslyor1VdPZWnHDMm3m78Hqu7X3vL6K7fAW/4FVbeKV7vWjvyiTQfETmuADqMdsqV5YeqUZWE/Dnzg+6JV/3L9nJ8f+/mPlh8k1h1oW035GrADKFKf1M2ujKKeHICnj7qKCdBeNAgMBAAECggEAHkh+U2YtHAy1Tbvox7ojbJ8iCTd4FJBiDV/D5zPaX0crtdM8S5oMOBLZ5ZnmIjGsODK/ZfY2ITg62huxfBs88J0+5zRZoV9d4BLqk74PMQyTNDN2h2omCGZUgzXbg/a8PMZdm0aZ8k0k4+AN8vWEk3+89c9Dzq/QkFyTWCqdz+Mp9NkcOjA255kj5/D1q9Zj0x9VcmKg3oTtrKL/dkspUxVaMKXcpo6J4AszC05tT3N0SNLhcq51I6B9QdbYsYCkP9whPNYIl/y4dN4QnNEivQzA5/ltr5DDQZc7Xke1+SpJN+ylBOBJ8yvNoDnuxx0xEWuNJ5bUzyri/DNjZRKNQQKBgQDKkxn5R4Q3rChI/KVIXHMecTs9fXN5pbnA8UKU6ZeTl+wHffxwRieHZJJp1nDcQ5i8YXCtqEJcJGFn3T0rEjVCJ/RVHclFO+TjYaK9HtyNJLPrvKmAzjCzV1yPfe4qmpgJddzZI7Vii2uFgklixvhkoY74hwSJtvLPTO0p0xrYdQKBgQC4cgfpCMLYlaHQGz+dAEf4IewQpwBGn7XShCpiXGFciGZZEIzvJMzXP7yo8pDMHQbB4kQsTRRG2fcdHGWI5VQGHPqG5O0tUueKGUlSg7j8Y/Pp8ZrBGSrlilAf17I/u9MC7Xe2ZRVGNgPDYAyjMEGmClI0n2+aN6b4CFVBjYmfuQKBgEKO9KDIE7QrF41rnW7aGWTuNVWty2wzvIWdf4/n9EqlRwLrLS9CjahZrhWiRLDKcPusVFZqi2s09OAoe/mT4PXcpNX2lHPwCvN+1/allje10HvrIBJXLP8v/BSVftR2uO+azzZ1GhrHzksulKgk0eZWguA7lI0fFEZycxYj65UlAoGAD9p1RZlkLfuGgf2llRgOF4zK3o+MHYXiuep0PioUkECFE4ixpGh0Vtf6nkbjHTgteYK6O1iQsppPfCgRrheQBkp9WhTZMfkbP6p2u+nof4ET2PrUQ16naj1eL655erLpKypADORZVMSVxDhAPdKLAfuHH1DI5ed8qXsF4PGKb7kCgYEAm/d+daT6YsbHDZlJ/J9Q8rRkKmiqj43NGQSHKg6Z6BEDibm8wRmj3Itu1N6XVChuaH+ekJzvUnZ/q1nyYzGvy6bOHYn3ziF9aH7wuhcRZ4qARmKDnzTBLg2QXBK1+400O3LJ+sAH/yuH/Y4hzRE6YMxBQpdYfnlJcIFVimAsT1w=";
	// protected static String PRI_KEY =
	// "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCe/HF/zUu+R1Y+wxHZvJPTng5+yog067beDjuXSfJdHaq/BBwDviYOtDRirXgrlNnZjRV/QTc+s4iU5KBGOWIJXXDbxypO9L1mX0jV05017F55mfs3N1sAfOkyz2LH0YZeQTvNm08Pnb0Pxy54TDtuJmM3Z43PbTURf9h+DpQI8EDbBdSlxdLw9E5Fc5m30QqLDcf8M0WlwemN8vS1U1z/zuSnS3b8DQ8NwYcGnoQqw1t2JMGouYrjENAUWJkKa7BOQpRYkq2WKmQ94V4rHelDSOriyV2WL3HxcF5xxoqwAySORAU2vSdwUyV0Xv1SJLRPgIT66NkLwT7VgByfPdrRAgMBAAECggEAfv+KrptQmOOlOida2AJyV4WXcKLaYOO1xPW7lmNkbgtnvIRM2JSJFA/RFM4bAz05NDYl6OwwdC63uLBUcyQdcvuoTndvDXkk3mTY6q3KlCuHYD2nVfkrAV3GbmcJMjLl0d7sJZaddgz73GGwdjn280tI0BYxSnOjA6KLTk7Y47W4duC5m5lQcP2NFBvnJogQu86UzJfJd2C6KQd50Yt0sXNy6JA8jzBH2gMEBCV3iG6oPzHSur3502UUtGaGK4CsT659liRG1soMSwNwILNhqDGd/5jQrrKvK9ijpeW43j/ZVBJp7aDQcGjnTaIoveqqW0yrb24Dw2/W0tXHDR4XnQKBgQDt4U7hPl/TSkTSMhwD3StICAOEUtwZLq//QHW51glDpbAHSMz5oYkCRPBQtF269TT5FYWKjeJzbN8n4HEN+Rrc9uZK2LlsVR10UA0Q6aEwAJWgFieP7RG2wQm+Fo6VybuOjo6cDR1dy4UVcW+EE6Pg2UeuhTkFLJS1OnTcZQgDJwKBgQCrGLE7X9V48qIYZycyZdKcN+cTyW+iLtU7U9LXVbN9FyA8sPCgzzQT2qMlyYT9RPV/cxnlzf/E9GM2Z1KKQDjXNmNCUBQHWvktEbqGiZL4w9sDZf2LaY7g1cnw92uVDee+HgxCJ4DNyhmNTBuxUrY+aaUG4/XoAIGyYXDd+MQNRwKBgQCLynjhUxdbAm0ZHQtrleBmbxcp/suLHO2eUdIdCFPfZhHg5f5WwH/UTpDuzF+8i3a63dPhTWoDLLqjI3ksnfQUYB+LYnrs18P8Y0KaasEnhWBoeDx9jvJ29E/2Xikiiy9mIg5aVDaqtPwbUtMWybfnXaiLPYz5pm4pZhXgopqnxwKBgEC+DDqabe3hakLvrZ5dd0+Tu8X6AFsPe7KCapL/I/cerB0rGzLOlTc6EWg4FDG+SWNby53w0GrXyz8H4+zalmehdffe02VhIE7UmY9VOZ8WhaaIjmOVovIjE9Ix3ay+YUXImvzhc9TrN7YG6ae0069UTJUJ58BNiMZWJt5nxs/7AoGAcn4pbsdp0CZK04u6d2PQfocvx/kgRMxY+DEVlzYzyGz00zRyNH0zY5ofFmYNCANj6VKHux/Nvm/GajdQHy5Y4c+eizH+N/eSoG1aoPLy491E3rwMiuoo7kVxJLx6pmkacKyLdylAs4ieSJIKpuiGTYVMjya5oP0EGYWMHgxncgQ=";
	protected static String BASE_URL = "http://ip:port/api";

	public static void main(String[] args) throws Exception {
		String URI = "mybank/account/farm/paramsync/V1";
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", PRI_KEY, APIGW_PUBLIC_KEY);
		// DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, PRI_KEY,
		// APIGW_PUBLIC_KEY);
		try {

			MybankAccountFarmParamsyncRequestBizV1 bizContent = new MybankAccountFarmParamsyncRequestBizV1();
			MybankAccountFarmParamsyncRequestV1 request = new MybankAccountFarmParamsyncRequestV1();
			bizContent.setfSeqNo("1234567890");
			bizContent.setCorpCode("inspur");
			bizContent.setTranDate("20200324");
			bizContent.setTranTime("101522365");
			bizContent.setTotalNum("4");
			ArrayList<MybankAccountFarmParamsyncRequestV1.MybankAccountFarmParamsyncRequestRdV1> rdList = new ArrayList<>();
			MybankAccountFarmParamsyncRequestV1.MybankAccountFarmParamsyncRequestRdV1 rd1 = new MybankAccountFarmParamsyncRequestV1.MybankAccountFarmParamsyncRequestRdV1();
			MybankAccountFarmParamsyncRequestV1.MybankAccountFarmParamsyncRequestRdV1 rd2 = new MybankAccountFarmParamsyncRequestV1.MybankAccountFarmParamsyncRequestRdV1();
			MybankAccountFarmParamsyncRequestV1.MybankAccountFarmParamsyncRequestRdV1 rd3 = new MybankAccountFarmParamsyncRequestV1.MybankAccountFarmParamsyncRequestRdV1();
			MybankAccountFarmParamsyncRequestV1.MybankAccountFarmParamsyncRequestRdV1 rd4 = new MybankAccountFarmParamsyncRequestV1.MybankAccountFarmParamsyncRequestRdV1();

			rd1.setiSeqNo("1");
			rd1.setParamType("LEDGERINFO");
			rd1.setOperType("3");
			ParamSyncLedgerInfoDtoBean ledgerInfo = new ParamSyncLedgerInfoDtoBean();
			ledgerInfo.setDbId("967");
			ledgerInfo.setInstId("370101002");
			ledgerInfo.setLedgerCode("1002");
			ledgerInfo.setLedgerLeafFlag("Y");
			ledgerInfo.setLedgerName("付款科目");
			ledgerInfo.setLedgerStatus("0");
			ledgerInfo.setLedgerType("010");
			ledgerInfo.setLedgerLevel("");
			ledgerInfo.setParentLedgerCode("");
			rd1.setDetail(JSONObject.toJSONString(ledgerInfo));
			//rd1.setDetail(
			//		"{\"ledgerCode\":\"001\",\"ledgerName\":\"付款科目\",\"ledgerType\":\"010\",\"ledgerLevel\":\"2\",\"ledgerLeafFlag\":\"Y\",\"parentLedgerCode\":\"01\",\"ledgerStatus\":\"0\",\"dbId\":\"967\",\"instId\":\"1001\"}");
			rd2.setiSeqNo("2");
			rd2.setParamType("ACCTINFO");
			rd2.setOperType("3");
			ParamSyncAcctInfoDtoBean acctInfo = new ParamSyncAcctInfoDtoBean();
			acctInfo.setInstId("370101002");
			acctInfo.setAccountNum("123456789123456789-123456789");
			acctInfo.setAccountName("山东省济南市历下区姚家街道办事处工业南路路社区居民委员会");
			acctInfo.setBelongBankId("103");
			acctInfo.setAccountBankCode("098765432112");
			acctInfo.setAccountBankName("山东省济南市历下区高新街道支行");
			acctInfo.setAccountType("01");
			acctInfo.setAccountUseType("01");
			acctInfo.setAccountStatus("1");
			rd2.setDetail(JSONObject.toJSONString(acctInfo));
			//rd2.setDetail(
			//		"{\"accountNum\":\"123456789123456789-123456789\",\"accountName\":\"山东省济南市历下区姚家街道办事处奥体西路社区居民委员会\",\"belongBankId\":\"102\",\"accountBankCode\":\"\",\"accountBankName\":\"山东省济南市历下区姚家街道支行\",\"accountType\":\"1\",\"accountUseType\":\"1\",\"accountStatus\":\"0\",\"instId\":\"370101002\"}");
			
			rd3.setiSeqNo("3");
			rd3.setParamType("INSTINFO");
			rd3.setOperType("3");
			ParamSyncInstInfoDtoBean instInfo = new ParamSyncInstInfoDtoBean();
			instInfo.setInstId("370101002");
			instInfo.setInstName("山东省济南市历下区姚家街道");
			instInfo.setInstLevel("1");
			instInfo.setParentInstId("370101");
			instInfo.setInstStatus("1");
			rd3.setDetail(JSONObject.toJSONString(instInfo));
			//rd3.setDetail(
			//		"{\"instId\":\"370101002\",\"instName\":\"山东省济南市\",\"instLevel\":\"2\",\"parentInstId\":\"370101\",\"instStatus\":\"0\"}");

			rd4.setiSeqNo("4");
			rd4.setParamType("USERINFO");
			rd4.setOperType("3");
			ParamSyncUserInfoDtoBean userInfo = new ParamSyncUserInfoDtoBean();
			userInfo.setUserId("1002");
			userInfo.setUserName("用户");
			userInfo.setUserStatus("1");
			userInfo.setMobilePhone("13769303840");
			userInfo.setInstId("370101002");
			rd4.setDetail(JSONObject.toJSONString(userInfo));
			//rd4.setDetail(
			//		"{\"userId\":\"001\",\"userName\":\"用户\",\"userStatus\":\"0\",\"mobilePhone\":\"13333333333\",\"instId\":\"370101002\"}");

			rdList.add(rd2);
			rdList.add(rd1);
			rdList.add(rd3);
			rdList.add(rd4);
			bizContent.setRd(rdList);
			request.setServiceUrl(BASE_URL + "/" + URI);
			request.setBizContent(bizContent);
			System.out.println(JSONObject.toJSONString(bizContent));
			MybankAccountFarmParamsyncResponseV1 response = client.execute(request);
			if (response.isSuccess()) {
				System.out.println("success");
				System.out.println(JSONObject.toJSONString(response));
			} else {
				System.out.println("error");
				System.out.println(JSONObject.toJSONString(response));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static class ParamSyncLedgerInfoDtoBean {
		private String ledgerCode;
		
		private String ledgerName;
		
		private String ledgerType;
	    
		private String ledgerLevel;
	    
		private String ledgerLeafFlag;
		
		private String parentLedgerCode;
	    
		private String ledgerStatus;
		
		private String dbId;
		
		private String instId;
		
		private String corpCode;

		public String getCorpCode() {
			return corpCode;
		}

		public void setCorpCode(String corpCode) {
			this.corpCode = corpCode;
		}
		
		public String getLedgerCode() {
			return ledgerCode;
		}
		public void setLedgerCode(String ledgerCode) {
			this.ledgerCode = ledgerCode;
		}
		public String getLedgerName() {
			return ledgerName;
		}
		public void setLedgerName(String ledgerName) {
			this.ledgerName = ledgerName;
		}
		public String getLedgerType() {
			return ledgerType;
		}
		public void setLedgerType(String ledgerType) {
			this.ledgerType = ledgerType;
		}
		public String getLedgerLevel() {
			return ledgerLevel;
		}
		public void setLedgerLevel(String ledgerLevel) {
			this.ledgerLevel = ledgerLevel;
		}
		public String getLedgerLeafFlag() {
			return ledgerLeafFlag;
		}
		public void setLedgerLeafFlag(String ledgerLeafFlag) {
			this.ledgerLeafFlag = ledgerLeafFlag;
		}
		public String getParentLedgerCode() {
			return parentLedgerCode;
		}
		public void setParentLedgerCode(String parentLedgerCode) {
			this.parentLedgerCode = parentLedgerCode;
		}
		public String getLedgerStatus() {
			return ledgerStatus;
		}
		public void setLedgerStatus(String ledgerStatus) {
			this.ledgerStatus = ledgerStatus;
		}
		public String getDbId() {
			return dbId;
		}
		public void setDbId(String dbId) {
			this.dbId = dbId;
		}
		public String getInstId() {
			return instId;
		}
		public void setInstId(String instId) {
			this.instId = instId;
		}
	}
	
	public static class ParamSyncUserInfoDtoBean {
		private String userId;

		private String userName;

		private String mobilePhone;

		private String userStatus = "0";

		private String instId;

		private String corpCode;

		public String getCorpCode() {
			return corpCode;
		}

		public void setCorpCode(String corpCode) {
			this.corpCode = corpCode;
		}

		public String getUserId() {
			return userId;
		}

		public void setUserId(String userId) {
			this.userId = userId;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public String getMobilePhone() {
			return mobilePhone;
		}

		public void setMobilePhone(String mobilePhone) {
			this.mobilePhone = mobilePhone;
		}

		public String getUserStatus() {
			return userStatus;
		}

		public void setUserStatus(String userStatus) {
			this.userStatus = userStatus;
		}

		public String getInstId() {
			return instId;
		}

		public void setInstId(String instId) {
			this.instId = instId;
		}

	}
	
	public static class ParamSyncInstInfoDtoBean {
		private String instId;

		private String instName;

		private String instLevel;

		private String instStatus = "0";

		private String parentInstId;

		private String corpCode;

		public String getCorpCode() {
			return corpCode;
		}

		public void setCorpCode(String corpCode) {
			this.corpCode = corpCode;
		}

		public String getInstId() {
			return instId;
		}

		public void setInstId(String instId) {
			this.instId = instId;
		}

		public String getInstName() {
			return instName;
		}

		public void setInstName(String instName) {
			this.instName = instName;
		}

		public String getInstLevel() {
			return instLevel;
		}

		public void setInstLevel(String instLevel) {
			this.instLevel = instLevel;
		}

		public String getInstStatus() {
			return instStatus;
		}

		public void setInstStatus(String instStatus) {
			this.instStatus = instStatus;
		}

		public String getParentInstId() {
			return parentInstId;
		}

		public void setParentInstId(String parentInstId) {
			this.parentInstId = parentInstId;
		}

	}
	
	public static class ParamSyncAcctInfoDtoBean {
		private String accountNum;
		
		private String accountName;
		
	    private String belongBankId;
		
		private String accountBankCode;
		
		private String accountBankName;

		private String accountType;

		private String accountUseType;
	    
		private String accountStatus = "0";
		
		private String instId;
		
		private String corpCode;

		public String getCorpCode() {
			return corpCode;
		}

		public void setCorpCode(String corpCode) {
			this.corpCode = corpCode;
		}
		public String getAccountNum() {
			return accountNum;
		}

		public void setAccountNum(String accountNum) {
			this.accountNum = accountNum;
		}

		public String getAccountName() {
			return accountName;
		}

		public void setAccountName(String accountName) {
			this.accountName = accountName;
		}

		public String getBelongBankId() {
			return belongBankId;
		}

		public void setBelongBankId(String belongBankId) {
			this.belongBankId = belongBankId;
		}

		public String getAccountBankCode() {
			return accountBankCode;
		}

		public void setAccountBankCode(String accountBankCode) {
			this.accountBankCode = accountBankCode;
		}

		public String getAccountBankName() {
			return accountBankName;
		}

		public void setAccountBankName(String accountBankName) {
			this.accountBankName = accountBankName;
		}

		public String getAccountType() {
			return accountType;
		}

		public void setAccountType(String accountType) {
			this.accountType = accountType;
		}

		public String getAccountUseType() {
			return accountUseType;
		}

		public void setAccountUseType(String accountUseType) {
			this.accountUseType = accountUseType;
		}

		public String getAccountStatus() {
			return accountStatus;
		}

		public void setAccountStatus(String accountStatus) {
			this.accountStatus = accountStatus;
		}

		public String getInstId() {
			return instId;
		}

		public void setInstId(String instId) {
			this.instId = instId;
		}
		
	}*/
}
