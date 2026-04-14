package com.icbc.api;

/*import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;*/

import com.icbc.api.request.JftApiVendorEnterpriseInfoRegisterRequestV1;
import com.icbc.api.request.JftApiVendorEnterpriseInfoRegisterRequestV1.JftApiVendorEnterpriseInfoRegisterRequestV1Biz;
import com.icbc.api.response.JftApiVendorEnterpriseInfoRegisterResponseV1;

/**
 * 聚富通企业基本信息注册接口
 *
 * 请商户配置SDK中的lib文件夹中的jar包后，根据备注1-6提示进行数据替换
 */
public class JftApiVendorEnterpriseInfoRegisterTestV1 {

	//1、网关公钥
	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
	//2、appid
	protected static final String APP_ID = "10000000000004103938";
	//3、密钥对认证方式，公钥在API平台登记，此处为私钥
	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCF7eCVTURFK5+gpeaQios8lw6uTcltPalYM6/VNfNDAirGnpFP5RQNI7duf5nr+3QLe1FNMbaR6Cl1BeWcaFzYqba4UTjNrttGf9E2LBnVa7sR0sVWX/1o71nIou21+Wh044zqcSl1T6FEBpSP70fz5ahytU41hVy6lyJymDhyWzZepr4Yqnrs69GB6V/v2DDuX8t4tpZAK1cxsOSrBJHOYxUoTomLb9dX8dFI/4AIShjKv4hmwryviHJRlZSiyV8j72FgOUs7BmMz+KCaKGVGo1G+YrppnX5IruNvaXDB59bcu3lsjPODUkfMKsMr+Vrg4ipDEboCCtKuaPrZ2xMNAgMBAAECggEAY3qQcXWl+xbvB52Sx9QQnh25yuB+eETvz1L9DQp4uVIXwdYwKz8FiMj5y/q9stnglVKwzfzaFkBy4rhRl76xEEHnNbsmzShPOWthU7KoMa1Gk3OSHplIGjSG5Q+YL62k2AXZOp55Y+iU6XlVyh+6uz/fwexHvltjyDjQXnwQmWdvUHM+cy7yTxlfz8FGecZOyRY2jRUJZ7w0WfUx0p1R0vWRRpjRddMDyu7sjtkTQ54KfmqE5HQWKrQVDio2hSw7EbaylAo6HSZO8i2ZSW3neKenvV2YCUVk6AbhfJj/SfTzqzEePduOv5bUq/vaTRCWAImcHnohLhzQQS5zn3jDpQKBgQDTncyUAAOL275viu9Xbk2IBsv/tyFaNji/G8BdE0jB++7x7J3EtLRJIBOJE16vvWUJfD7CXvezqArZWLADt7lholdPuUKlEOJtXvqlYvg7zKDce+wA7HAK0a2sMSvU51nC0q4LLaI+NlGxZZcPYcoj6PYLXw/BHaqGAHm5Y+RMhwKBgQCiBNz3zYP4RnfBTazWBRm3P7jybcteFMh5inmOyw9mfpphIUpFlw9lRbt7J2+sauzlIKZ2d9MOoZIurAZ/zbgIf2msTEoyczzO6yUpyeKinl6HjizMKggLEponU7nk2nQsuVbf7wYoHPwwjT+a5k9R+PMrYVO0B74Z8PIQpHd8ywKBgQCD//qMlzWfGANCMLf7IaLbJuI5MFJto0TicL0dUdogprX10lLXUKDkvE1zQ9HcbZeIwyeitvpr5nZd/SROLVxFuq4b3MPlkxKqhoRyhbmyE4KEBgjAyrtZBFIBBusAcw9ap3BJKL37cIak0WnY/nbIz5gqn7GEFK05yTIkolrWIQKBgH2U42m4BKi2hjBw8pzZSzt8yvAuJkoANfvvV5VU09UQIL1Uvmr+UdKfoFgvEyJDpudazWxaKF/Y4KQIduktHY6Io/IHGCPOqr2ACkg/0clWk9LiYtrFYYKjopUFErvOj+nypuHgNZd2EAvaEAkSpzzUiDeBsw2CStl0p0w7DtrNAoGAIsqskDGGewcgBWQmXvSqa7o0do3coiH3ToMAQm9vlGgHAAw5xlgfVQpGwe8GR4ilysh+5EuiuLnINDt0tXpv9K7dAQgjKAmszFrOjmyBMnSDebfysynABFj3c8GglO+l9J+ljFMqgqPVzsLAgIItciyq80Xmv8TjpbwFM2v34lI=";

	public static void main(String[] args) throws Exception {

		//签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,"RSA2", MY_PRIVATE_KEY,"UTF-8",
				"json",APIGW_PUBLIC_KEY,"AES","5xGJdh7qb+B95SUoxDlatg==", "", "");

		JftApiVendorEnterpriseInfoRegisterRequestV1 request = new JftApiVendorEnterpriseInfoRegisterRequestV1();

		//4、根据测试环境和生产环境替换相应ip和端口
		request.setServiceUrl("http://ip:port/api/jft/api/vendor/enterprise/info/register/V1");

		//5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
		JftApiVendorEnterpriseInfoRegisterRequestV1Biz bizContent = new JftApiVendorEnterpriseInfoRegisterRequestV1Biz();

		bizContent.setAppId(APP_ID);
		bizContent.setOutUpperVendorId("10000000000007354577");
		bizContent.setOutVendorId("01236542");
		bizContent.setOutVendorManagerId("012365231");
		bizContent.setBizType("02");
		bizContent.setVendorName("某某公司");
		bizContent.setVendorShortName("简称");
		bizContent.setVendorPhone("18888888888");
		bizContent.setVendorEmail("xxx@gmail.com");
		bizContent.setProvince("130000");
		bizContent.setCity("130100");
		bizContent.setCounty("130102");
		bizContent.setAddress("某某路某某号某某楼1001");
		bizContent.setPostcode("100001");
		bizContent.setOperatorName("张三");
		bizContent.setOperatorMobile("13800001111");
		bizContent.setOperatorEmail("zhangsan@gmail.com");
		bizContent.setOperatorIdNo("110101198801010101");
		bizContent.setVendorType("01");
		bizContent.setCorporateName("李四");
		bizContent.setCorporateMobile("18800001111");
		bizContent.setCorporateIdType("0");
		bizContent.setCorporateIdNo("110123199901121230");
		bizContent.setCorporateIdPic1("mere73ffaf4499740678fcc6e9dd542698e");
		bizContent.setCorporateIdPic2("mere73ffaf4499740678fcc6e9dd542698e");
		bizContent.setCertType("101");
		bizContent.setCertNo("6E66455221");
		bizContent.setCertPic("mere73ffaf4499740678fcc6e9dd542698e");
		bizContent.setOtherCertPic1("mere73ffaf4499740678fcc6e9dd542698e");
		bizContent.setOtherCertPic2("mere73ffaf4499740678fcc6e9dd542698e");
		bizContent.setOtherCertPic3("mere73ffaf4499740678fcc6e9dd542698e");
		bizContent.setAccountBizType("2");
		bizContent.setAccountName("某某公司");
		bizContent.setAccountBankProvince("河北");
		bizContent.setAccountBankCity("石家庄");
		bizContent.setAccountBankNm("中国工商银行");
		bizContent.setAccountBankName("中国工商银行石家庄长安支行");
		bizContent.setAccountBankCode("102100029679");
		bizContent.setAccountNo("0200919282761721");
		bizContent.setAccountMobile("17700001111");
		bizContent.setCertValidityl("2099-12-31");
		//代发商户可设置代发账户列表，代码如下
		/*Map<String,String> accMap=new HashMap<>();
		accMap.put("accountName","北京市千叶珠宝股份有限公司");
		accMap.put("accountNo","0200062009200003262");
		Map<String,String> accMap1=new HashMap<>();
		accMap1.put("accountName","榔右程慎颗莉瘤指侦柱");
		accMap1.put("accountNo","0200006509200090346");
		Map<String,String> accMap2=new HashMap<>();
		accMap2.put("accountName","聚富通业务待结算资金账户");
		accMap2.put("accountNo","0200095611200920027");
		List<Map<String,String>> accList=new ArrayList();
		accList.add(accMap);
		accList.add(accMap1);
		accList.add(accMap2);
		bizContent.setAcctList(accList);*/

		bizContent.setTransScene("5");
		request.setBizContent(bizContent);

		JftApiVendorEnterpriseInfoRegisterResponseV1 response;
		try {
			response =  (JftApiVendorEnterpriseInfoRegisterResponseV1) client.execute(request, System.currentTimeMillis()+"");
			if (response.isSuccess()) {
				//6、业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
				System.out.println("ReturnCode:"+response.getReturnCode());
				System.out.println("response:" + response);
			} else {
				//失败
				System.out.println("ReturnCode:"+response.getReturnCode());
				System.out.println("ReturnMsg:"+response.getReturnMsg());
			}
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}

	}

}

