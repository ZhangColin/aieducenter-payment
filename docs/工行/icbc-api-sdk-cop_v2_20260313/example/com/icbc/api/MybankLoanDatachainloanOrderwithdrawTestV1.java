package com.icbc.api;

import java.util.ArrayList;
import java.util.List;

import com.icbc.api.request.MybankLoanDatachainloanOrderwithdrawRequestV1;
import com.icbc.api.request.MybankLoanDatachainloanOrderwithdrawRequestV1.MybankLoanDatachainloanOrderwithdrawRequestV1Biz;
import com.icbc.api.request.MybankLoanDatachainloanOrderwithdrawRequestV1.MybankLoanDatachainloanOrderwithdrawRequestV1Biz.AODPayInfoDTO;
import com.icbc.api.request.MybankLoanDatachainloanOrderwithdrawRequestV1.MybankLoanDatachainloanOrderwithdrawRequestV1Biz.AODServeInfoDTO;
import com.icbc.api.response.MybankLoanDatachainloanOrderwithdrawResponseV1;

public class MybankLoanDatachainloanOrderwithdrawTestV1 {
	 protected static String APP_ID = "xxxx";
	    protected static String APIGW_PUBLIC_KEY =
	            "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16"
	            +"bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCL"
	            +"T7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP"
	            +"3CiNgg0W6O3AGqwIDAQAB";
	    protected static String PRI_KEY =
	    		"-----请使用正确的APP私钥-----EvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCMkkwRnaKS34Tw7EopW5IYTXqDNUoAOktjiHcDk/IBS0FEikwkFfLunl7L0428PsCBFiVt04OuQ0poJ5m2uzqQtr8tPEHfy3iv+4xBjbV9iIXd2EBjsZv/sS7A1yPnVpdMOGF3e2R5YNKJ60Y41bz2NWLVDj5W/9AVCQMXsbQo3vkO1nPCcztNTfyKxHrY30lFsHMBCodHd4Tsxjawit7vASo6iVczy8MGO8KDpIMMd5fgyepKIMdFR+M7RIMnsozjWSdPQLr+YDHZjIWWuTrYtjabkCDUAlyIte7ynRZvhe8V+wGcTxnmhBJEym8tOWIPEdkzeecIQsRlPSmVXm0tAgMBAAECggEAH/JHk2FNxy9BGTe6CvgilZ9WDmeX78FPzMkAszF2OFEP/jGn6DOMyCn5xTzI41Unjq6nxvPg40Orr0wApQSb5LODcxZUqjWlsejrESLRcjwKoEY7K+z7VPrBc1XxdOu66rmNfQfv553Xezya8uOwVAwB8AWSqVbtxToNF99s4DSllRjlv9x1bhiEGeB205Io8KiL+D0jATrAoTus6XgnbNKaY/1F2Hj5gCWAN+pcKrVVwTjb+tyNvXv+gtA4JBmyDTt7kFpHknYnAitv0Jujf6e7WVU9uMNXimzY8yGCEdrYDkLE/jYvZVYWyJaVvb58K50w7wJqdUPPZBLhFJX9SQKBgQDSzkfKqOnGvPlUcW98pBibpyK+CEa7Ps9mK5Bv63XIvwlTd+iHEmIwdnxiPnzTXORmU+pjGz1ixFptwPDHW8YWnO1PN+WSddG7u1WRHUB78YNhAraDnaETodPS8IqWUTr7IUsVaNZnjkJo7gknQ5KFdDmuMFqoQunGKQ8dPCldBwKBgQCqtVM+asyAT5njFPTdaR06lRMTdWTlrBXrmmTekqsfqI8+12WwTW8tId0jvlsTzjZ5CxecBRynMWKumOjOmEsY5KnGxLffVWjLS/8GawsDzibJENjxXYz4xltL5QQ2s5uITgRURoexAyZUji4t4lwyrmpcX4CS04pYnLz3Ao+LKwKBgQC69uMrdeZpMj98SmNTy4Jbwr+IhXSeK5XsiuS+rrm3Qt+1nNqpuX9Gdj/ZLoytNW0I6JIwNf+asoV1ZDLozZeBhMrggLXm0QIkG4FHhAcmJkycZLiVQN+UtulU46w2p3LFOoEKSwIltEFoKP4LSd4Q6MUjnZU6OKZ37wCNsEcLTwKBgQCSzpfFYQF4A3z7TmV7GE/btqKOJHJeFk1Prhq7UvU5p7NxPrqkESPgc3QDrkwTT0+Hb2OheL/4hT8xmiz0pwJGaTgcyca7mW8jDrtGoL37SsoONp/riyGWPLf9H/ostO+4HCga9gDx2RyYsSHDshzKNTchM7yWCLi8liboYtphuwKBgAZxjUvAfAHn7dMAXylVDbgdb7m9vABU1ydQCvNdPHznRsh+/eqvVTrkBnYzKvFhM14MBFxF0YRjwENwrcXZaz5mar6xXXjaG4w8xPw2OGjPxAAU5s0465f+1ykbsITK0ZALs2woxZu+CQuXDncAIKjhU24+eBtXaCpsyqQTtY63";
	    protected static String BASE_URL = "http://ip:port/api";
	
	public static void main(String[] args) {
		String URI = "mybank/loan/datachainloan/orderwithdraw/V1";
     DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, PRI_KEY, APIGW_PUBLIC_KEY);
     try {

    	 MybankLoanDatachainloanOrderwithdrawRequestV1Biz bizContent =
                 new MybankLoanDatachainloanOrderwithdrawRequestV1Biz();
     	
     	
     	bizContent.setSerialNo("xxxx");
     	bizContent.setAppNo("F-GCMS");
     	bizContent.setLanguage("zh_CN");
     	bizContent.setTransNo("IAcceptOrderDrawCocoaService");
     	bizContent.setAppid("xxxx");
     	
     	bizContent.setBorrowerName("X国X辐X份X限X司");
     	bizContent.setBorrowerCerType("3");
     	bizContent.setBorrowerCerId("11068751-3");
     	bizContent.setCustName("委耽懂整壶捧泳酰佣禾支死嗣");
     	bizContent.setCustCerType("3");
     	bizContent.setCustCerId("74382908-6");
     	bizContent.setRelation("01");
     	bizContent.setEvalType("2");
     	bizContent.setDrawInstruct("xyw20191012001");
     	bizContent.setTradeNo("xyw201910112052002");
     	bizContent.setOrderNo("xyw201910112052002");
     	bizContent.setOrderType("01");
     	bizContent.setOrderAmt("500.02");
     	bizContent.setOrderStatus("01");
     	bizContent.setOrderDetail("订单详情");
     	bizContent.setCpFeeRate("7");
     	bizContent.setCpFee("50.99");
     	
     	List<AODServeInfoDTO> serveInfoList = new ArrayList<AODServeInfoDTO>();
     	AODServeInfoDTO serveInfoDTO  = new AODServeInfoDTO();
     	
     	serveInfoDTO.setProductName("产品名称");
     	serveInfoDTO.setServeCount("2");
     	serveInfoDTO.setServeMoney("11.22");
     	serveInfoDTO.setServeName("服务名称");
     	serveInfoDTO.setServeNo("11111111");
     	serveInfoDTO.setServeStatus("01");
     	serveInfoList.add(serveInfoDTO);
     	bizContent.setServeInfoList(serveInfoList);
     	
     	List<AODPayInfoDTO> payInfoList = new ArrayList<AODPayInfoDTO>();
     	AODPayInfoDTO payInfoDTO  = new AODPayInfoDTO();
     	
     	payInfoDTO.setAccnoName("受托支付对象户名");
     	payInfoDTO.setBankName("工商银行");
     	payInfoDTO.setCustBankCode("123456788911");
     	payInfoDTO.setCustType("01");
     	payInfoDTO.setPaccNo("3333333");
     	payInfoDTO.setPaidMoney("123.44");
     	payInfoDTO.setPayMoney("66.99");
     	payInfoList.add(payInfoDTO);
     	bizContent.setPayInfoList(payInfoList);
     	
     	MybankLoanDatachainloanOrderwithdrawRequestV1 request =
                 new MybankLoanDatachainloanOrderwithdrawRequestV1();
         request.setServiceUrl(BASE_URL + "/" + URI);
         request.setBizContent(bizContent);
         MybankLoanDatachainloanOrderwithdrawResponseV1 response = client.execute(request);
         if (response.isSuccess()) {
             //
         } else {
             //
         }

     } catch (Exception e) {
         e.printStackTrace();
     }
	}
}
