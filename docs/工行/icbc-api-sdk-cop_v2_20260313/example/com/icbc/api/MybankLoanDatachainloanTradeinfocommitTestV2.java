package com.icbc.api;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.request.MybankLoanDatachainloanTradeinfocommitRequestV2;
import com.icbc.api.request.MybankLoanDatachainloanTradeinfocommitRequestV2Biz;
import com.icbc.api.response.MybankLoanDatachainloanTradeinfocommitResponseV2;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class MybankLoanDatachainloanTradeinfocommitTestV2 {

	private static Log log = LogFactory.getLog(MybankLoanDatachainloanTradeinfocommitTestV2.class);

	public static void main(String[] args) throws ParseException {
		
		try {
			String apigwPublicKey =  "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
			String privateKey = "-----请使用正确的APP私钥-----EvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCMkkwRnaKS34Tw7EopW5IYTXqDNUoAOktjiHcDk/IBS0FEikwkFfLunl7L0428PsCBFiVt04OuQ0poJ5m2uzqQtr8tPEHfy3iv+4xBjbV9iIXd2EBjsZv/sS7A1yPnVpdMOGF3e2R5YNKJ60Y41bz2NWLVDj5W/9AVCQMXsbQo3vkO1nPCcztNTfyKxHrY30lFsHMBCodHd4Tsxjawit7vASo6iVczy8MGO8KDpIMMd5fgyepKIMdFR+M7RIMnsozjWSdPQLr+YDHZjIWWuTrYtjabkCDUAlyIte7ynRZvhe8V+wGcTxnmhBJEym8tOWIPEdkzeecIQsRlPSmVXm0tAgMBAAECggEAH/JHk2FNxy9BGTe6CvgilZ9WDmeX78FPzMkAszF2OFEP/jGn6DOMyCn5xTzI41Unjq6nxvPg40Orr0wApQSb5LODcxZUqjWlsejrESLRcjwKoEY7K+z7VPrBc1XxdOu66rmNfQfv553Xezya8uOwVAwB8AWSqVbtxToNF99s4DSllRjlv9x1bhiEGeB205Io8KiL+D0jATrAoTus6XgnbNKaY/1F2Hj5gCWAN+pcKrVVwTjb+tyNvXv+gtA4JBmyDTt7kFpHknYnAitv0Jujf6e7WVU9uMNXimzY8yGCEdrYDkLE/jYvZVYWyJaVvb58K50w7wJqdUPPZBLhFJX9SQKBgQDSzkfKqOnGvPlUcW98pBibpyK+CEa7Ps9mK5Bv63XIvwlTd+iHEmIwdnxiPnzTXORmU+pjGz1ixFptwPDHW8YWnO1PN+WSddG7u1WRHUB78YNhAraDnaETodPS8IqWUTr7IUsVaNZnjkJo7gknQ5KFdDmuMFqoQunGKQ8dPCldBwKBgQCqtVM+asyAT5njFPTdaR06lRMTdWTlrBXrmmTekqsfqI8+12WwTW8tId0jvlsTzjZ5CxecBRynMWKumOjOmEsY5KnGxLffVWjLS/8GawsDzibJENjxXYz4xltL5QQ2s5uITgRURoexAyZUji4t4lwyrmpcX4CS04pYnLz3Ao+LKwKBgQC69uMrdeZpMj98SmNTy4Jbwr+IhXSeK5XsiuS+rrm3Qt+1nNqpuX9Gdj/ZLoytNW0I6JIwNf+asoV1ZDLozZeBhMrggLXm0QIkG4FHhAcmJkycZLiVQN+UtulU46w2p3LFOoEKSwIltEFoKP4LSd4Q6MUjnZU6OKZ37wCNsEcLTwKBgQCSzpfFYQF4A3z7TmV7GE/btqKOJHJeFk1Prhq7UvU5p7NxPrqkESPgc3QDrkwTT0+Hb2OheL/4hT8xmiz0pwJGaTgcyca7mW8jDrtGoL37SsoONp/riyGWPLf9H/ostO+4HCga9gDx2RyYsSHDshzKNTchM7yWCLi8liboYtphuwKBgAZxjUvAfAHn7dMAXylVDbgdb7m9vABU1ydQCvNdPHznRsh+/eqvVTrkBnYzKvFhM14MBFxF0YRjwENwrcXZaz5mar6xXXjaG4w8xPw2OGjPxAAU5s0465f+1ykbsITK0ZALs2woxZu+CQuXDncAIKjhU24+eBtXaCpsyqQTtY63";
			String appId = "xxx";
			String url = "http://ip:port/api/mybank/loan/datachainloan/tradeinfocommit/V2";
			
			DefaultIcbcClient client = new DefaultIcbcClient(appId, privateKey,apigwPublicKey);
			MybankLoanDatachainloanTradeinfocommitRequestV2 request = new MybankLoanDatachainloanTradeinfocommitRequestV2();
			request.setServiceUrl(url);
			MybankLoanDatachainloanTradeinfocommitRequestV2Biz bizContent = new MybankLoanDatachainloanTradeinfocommitRequestV2Biz();

			bizContent.setAppNo("F-APIP");
			bizContent.setSerialNo("20191010111");
			bizContent.setLanguage("zh_CN");
			bizContent.setTransNo("IAcceptDtchnTradeInfoCocoaService");
			bizContent.setApplyChannel("02");
			bizContent.setBorrowerCerId("xxx");
			bizContent.setBorrowerCerType("3");
			bizContent.setBorrowerName("12");
			bizContent.setCustCerId("12345");
			bizContent.setCustCerType("3");
			bizContent.setCustName("111");
			bizContent.setEvalType("1");
			bizContent.setLoanWay("0");
			bizContent.setOperFlag("1");
			bizContent.setRelation("01");

			MybankLoanDatachainloanTradeinfocommitRequestV2Biz.TradeInfo tradeInfo= new MybankLoanDatachainloanTradeinfocommitRequestV2Biz.TradeInfo();
			tradeInfo.setTradeNo("123456");
			tradeInfo.setMainTradeNo("111123456");
			tradeInfo.setUpDownTradePartnerType("01");
			tradeInfo.setTradeMoney("100");
			tradeInfo.setAccnoName("aaa");
			tradeInfo.setPayMoney("1.00");
			tradeInfo.setPaidMoney("2.00");
			tradeInfo.setCustType("01");
			tradeInfo.setCustBankCode("11");
			tradeInfo.setProjectBeginDate("20190101");
			tradeInfo.setProjectEndDate("20990101");
			tradeInfo.setUnderWriting("01");
			tradeInfo.setProjectStatus("01");

			MybankLoanDatachainloanTradeinfocommitRequestV2Biz.ServeInfo serveInfo = new MybankLoanDatachainloanTradeinfocommitRequestV2Biz.ServeInfo();
			serveInfo.setProductName("aaa");
			serveInfo.setServeCount("1");
			serveInfo.setServeName("bbb");

			List<MybankLoanDatachainloanTradeinfocommitRequestV2Biz.ServeInfo> serveInfoList = new ArrayList<MybankLoanDatachainloanTradeinfocommitRequestV2Biz.ServeInfo>();
			serveInfoList.add(serveInfo);
			tradeInfo.setServeInfoList(serveInfoList);

			MybankLoanDatachainloanTradeinfocommitRequestV2Biz.OrderInfo orderInfo = new MybankLoanDatachainloanTradeinfocommitRequestV2Biz.OrderInfo();
			orderInfo.setOrderAmt("1.00");
			orderInfo.setOrderNo("111");
			orderInfo.setOrderStatus("01");
			orderInfo.setOrderType("01");

			List<MybankLoanDatachainloanTradeinfocommitRequestV2Biz.OrderInfo> orderInfoList = new ArrayList<MybankLoanDatachainloanTradeinfocommitRequestV2Biz.OrderInfo>();
			orderInfoList.add(orderInfo);
			tradeInfo.setOrderInfoList(orderInfoList);

			MybankLoanDatachainloanTradeinfocommitRequestV2Biz.PlanInfo planInfo = new MybankLoanDatachainloanTradeinfocommitRequestV2Biz.PlanInfo();
			planInfo.setPlanDate("20191010");
			planInfo.setPlanMoney("2.00");

			List<MybankLoanDatachainloanTradeinfocommitRequestV2Biz.PlanInfo> planInfoList = new ArrayList<MybankLoanDatachainloanTradeinfocommitRequestV2Biz.PlanInfo>();
			planInfoList.add(planInfo);

			List<MybankLoanDatachainloanTradeinfocommitRequestV2Biz.TradeInfo> tradeInfoList = new ArrayList<MybankLoanDatachainloanTradeinfocommitRequestV2Biz.TradeInfo>();
			tradeInfoList.add(tradeInfo);
			bizContent.setTradeInfoList(tradeInfoList);
			request.setBizContent(bizContent);

			MybankLoanDatachainloanTradeinfocommitResponseV2 response;
			response = client.execute(request);
			if (response.isSuccess()) {
				String a = response.getReturnContent();
				log.info(a);
				
			} else {
				String a = response.getReturnContent();
				log.error(a);
			}
			
		}  catch (IcbcApiException e1) {
			e1.printStackTrace();
		}
		 
	}
}
