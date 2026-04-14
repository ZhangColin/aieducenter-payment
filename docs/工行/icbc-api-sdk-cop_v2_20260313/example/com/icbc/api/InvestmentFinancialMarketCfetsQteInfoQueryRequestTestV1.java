package com.icbc.api;

import java.util.ArrayList;
import java.util.List;

import com.icbc.api.request.InvestmentFinancialMarketCfetsQteInfoQueryRequestV1;
import com.icbc.api.request.InvestmentFinancialMarketCfetsQteInfoQueryRequestV1.InvestmentFinancialMarketCfetsQteInfoQueryRequestBizV1;
import com.icbc.api.request.InvestmentFinancialMarketCfetsQteInfoQueryRequestV1.InvestmentFinancialMarketCfetsQteInfoQueryRequestBizV1.InParam;
import com.icbc.api.request.InvestmentFinancialMarketCfetsQteInfoQueryRequestV1.InvestmentFinancialMarketCfetsQteInfoQueryRequestBizV1.InParam.InPrivateInfo;
import com.icbc.api.request.InvestmentFinancialMarketCfetsQteInfoQueryRequestV1.InvestmentFinancialMarketCfetsQteInfoQueryRequestBizV1.InParam.InPublicInfo;
import com.icbc.api.response.InvestmentFinancialMarketCfetsQteInfoQueryResponseV1;

import junit.framework.Assert;

public class InvestmentFinancialMarketCfetsQteInfoQueryRequestTestV1 {
	    //1、网关公钥
			protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

			//2、appid
			protected static final String APP_ID = Const.APP_ID;

			//3、密钥对认证方式，公钥在API平台登记，此处为私钥
			protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----CdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIJBzZNaiobplRUgtJ4OzmUvZRK99ih/fUyDBOoFLtpJJCCRzp8T6V11YNlE7Xg5dt+EG7byQs2NImqg0eWEj/mBdZ7UmlAct7BNw2hyF2h4R5OEfXyjoH3wqGjKJayhaHTvLM1DYy/mDFBb0ShJYI1QMScQJZhsOhMMFhrrZwIZAgMBAAECgYAA2kdrOIOBoJPOQJmOE1C8jtPdjIrI9xSt5Imqsn/9A8+NuwacOfgkGXmZ0n6vc8jYa7f2uZ1AVTUtd4IIO5bpq8s0Tw2BfWALYwr/JdUuNKSjHVQsh/Do+wl8BgOgB4RqsNXWNGtoMC8lHKHmrVcpyJMfDc3cP07NZ1wG2zB0lQJBAM+dNZv2L/Z4RzvQcoVZEthYavZ4pkFoWGYC4jwc5G8um76zoQyrtxWYrtTP0GS+xFFX2dEuiGXxwzmSQJrPdrMCQQCgnUXcQe/if2c6TFt4x9v+6L0pmFClYyiOi9RuBSz1sHmPouuc/YYvuxAOdOzu3yzOkeo7b5KcCKITTWvKI+oDAkA5dl6vIw2VXycAJCp+Q/AWVyqLu0rw0Yud+HBbiPek2jabKqaJlkFfRdol5rrcF3zIstMDtahk5uxM0/DzqDZHAkBGnZ8vfdYIUVeDbDrzWXvCEXXJqewbKwOT2KqnTKM9yj9IBatttJGgvrAKiyH4zCqZD9JaG23sKGeJ8QopL60dAkEAtc4tlKoj3XZzRUXboqz0EhkgkjzDj50zpCD1sJKZ2EZH+A/7tXwPug+RnuSmKpM2uv3msqw3prdS3K4En8+rog==";
			
			@SuppressWarnings("deprecation")
			public static void main(String[] args) throws Exception {

				//签名类型为RSA时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA，其他参数使用缺省值
				DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA, MY_PRIVATE_KEY,
						APIGW_PUBLIC_KEY);
				
				InvestmentFinancialMarketCfetsQteInfoQueryRequestV1 request = new InvestmentFinancialMarketCfetsQteInfoQueryRequestV1();
				
				//4、根据测试环境和生产环境替换相应ip和端口
				request.setServiceUrl("https://ip:port/api/investment/financialmarket/cfetsQteinfoquery/V1");

				//5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
				
				
				InPublicInfo inPublicInfo = new InPublicInfo();
				
				inPublicInfo.setAppName("F-ZZTA");
				inPublicInfo.setReqId("12345");
				inPublicInfo.setServiceType("fmCfetsQteInfoQueryService");
				
				InPrivateInfo inPrivateInfo = new InPrivateInfo(); 
				
				inPrivateInfo.setSecCode("160413");
				inPrivateInfo.setStartTradeDate("20190927");
				inPrivateInfo.setEndTradeDate("20191027");
				inPrivateInfo.setStartSetlDate("");
				inPrivateInfo.setEndSetlDate("");
				inPrivateInfo.setBeginNum("1");
			        inPrivateInfo.setFetchNum("15");
				
				InParam inParam = new InParam();
				inParam.setInPublicInfo(inPublicInfo);
				inParam.setInPrivateInfo(inPrivateInfo);
				
				InvestmentFinancialMarketCfetsQteInfoQueryRequestBizV1 bizContent = new InvestmentFinancialMarketCfetsQteInfoQueryRequestBizV1();
						
				bizContent.setInParam(inParam);

				request.setBizContent(bizContent);

				InvestmentFinancialMarketCfetsQteInfoQueryResponseV1 response;
				try {
					response = client.execute(request, "msgId");//msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一
					if (response.isSuccess()) {
						//6、业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
						Assert.assertEquals(response.getReturnCode(), "0");
						//System.out.println("ReturnCode:"+response.getReturnCode());
						//System.out.println("response:" + response);
					} else {
						//失败
						Assert.assertEquals(response.getReturnCode(), "1");
						//System.out.println("ReturnCode:"+response.getReturnCode());
						//System.out.println("ReturnMsg:"+response.getReturnMsg());
					}		
				} catch (IcbcApiException e) {
					e.printStackTrace();
				}
			}
	}
