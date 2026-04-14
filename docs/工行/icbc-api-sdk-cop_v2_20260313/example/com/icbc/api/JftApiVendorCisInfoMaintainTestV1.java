package com.icbc.api.test;

import java.util.ArrayList;
import java.util.List;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.request.JftApiVendorCisInfoMaintainRequestV1;
import com.icbc.api.request.JftApiVendorCisInfoMaintainRequestV1.BeneficiaryInfo;
import com.icbc.api.request.JftApiVendorCisInfoMaintainRequestV1.JftApiVendorCisInfoMaintainRequestV1Biz;
import com.icbc.api.response.JftApiVendorCisInfoMaintainResponseV1;

/**
 * 聚富通子商户客编要素维护接口
 * 
 * 请商户配置SDK中的lib文件夹中的jar包后，根据备注1-6提示进行数据替换
 */
public class JftApiVendorCisInfoMaintainTestV1 {

	// 1、网关公钥（API网关公钥）
	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5"
			+ "BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lT"
			+ "W3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
	// 2、appid
	protected static final String APP_ID = "10000000000000117015";
	// 3、密钥对认证方式，公钥在API平台登记，此处为私钥
	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCF7eCVTURFK5+gpeaQios8lw6uTcltPalYM6/VNfNDAirGnpFP5RQNI7duf5nr+3QLe1FNMbaR6Cl1BeWcaFzYqba4UTjNrttGf9E2LBnVa7sR0sVWX/1o71nIou21+Wh044zqcSl1T6FEBpSP70fz5ahytU41hVy6lyJymDhyWzZepr4Yqnrs69GB6V/v2DDuX8t4tpZAK1cxsOSrBJHOYxUoTomLb9dX8dFI/4AIShjKv4hmwryviHJRlZSiyV8j72FgOUs7BmMz+KCaKGVGo1G+YrppnX5IruNvaXDB59bcu3lsjPODUkfMKsMr+Vrg4ipDEboCCtKuaPrZ2xMNAgMBAAECggEAY3qQcXWl+xbvB52Sx9QQnh25yuB+eETvz1L9DQp4uVIXwdYwKz8FiMj5y/q9stnglVKwzfzaFkBy4rhRl76xEEHnNbsmzShPOWthU7KoMa1Gk3OSHplIGjSG5Q+YL62k2AXZOp55Y+iU6XlVyh+6uz/fwexHvltjyDjQXnwQmWdvUHM+cy7yTxlfz8FGecZOyRY2jRUJZ7w0WfUx0p1R0vWRRpjRddMDyu7sjtkTQ54KfmqE5HQWKrQVDio2hSw7EbaylAo6HSZO8i2ZSW3neKenvV2YCUVk6AbhfJj/SfTzqzEePduOv5bUq/vaTRCWAImcHnohLhzQQS5zn3jDpQKBgQDTncyUAAOL275viu9Xbk2IBsv/tyFaNji/G8BdE0jB++7x7J3EtLRJIBOJE16vvWUJfD7CXvezqArZWLADt7lholdPuUKlEOJtXvqlYvg7zKDce+wA7HAK0a2sMSvU51nC0q4LLaI+NlGxZZcPYcoj6PYLXw/BHaqGAHm5Y+RMhwKBgQCiBNz3zYP4RnfBTazWBRm3P7jybcteFMh5inmOyw9mfpphIUpFlw9lRbt7J2+sauzlIKZ2d9MOoZIurAZ/zbgIf2msTEoyczzO6yUpyeKinl6HjizMKggLEponU7nk2nQsuVbf7wYoHPwwjT+a5k9R+PMrYVO0B74Z8PIQpHd8ywKBgQCD//qMlzWfGANCMLf7IaLbJuI5MFJto0TicL0dUdogprX10lLXUKDkvE1zQ9HcbZeIwyeitvpr5nZd/SROLVxFuq4b3MPlkxKqhoRyhbmyE4KEBgjAyrtZBFIBBusAcw9ap3BJKL37cIak0WnY/nbIz5gqn7GEFK05yTIkolrWIQKBgH2U42m4BKi2hjBw8pzZSzt8yvAuJkoANfvvV5VU09UQIL1Uvmr+UdKfoFgvEyJDpudazWxaKF/Y4KQIduktHY6Io/IHGCPOqr2ACkg/0clWk9LiYtrFYYKjopUFErvOj+nypuHgNZd2EAvaEAkSpzzUiDeBsw2CStl0p0w7DtrNAoGAIsqskDGGewcgBWQmXvSqa7o0do3coiH3ToMAQm9vlGgHAAw5xlgfVQpGwe8GR4ilysh+5EuiuLnINDt0tXpv9K7dAQgjKAmszFrOjmyBMnSDebfysynABFj3c8GglO+l9J+ljFMqgqPVzsLAgIItciyq80Xmv8TjpbwFM2v34lI=";

	public static void main(String[] args) throws Exception {

		// 签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, "UTF-8", "json",
				APIGW_PUBLIC_KEY, "AES", "5xGJdh7qb+B95SUoxDlatg==", "", "");

		JftApiVendorCisInfoMaintainRequestV1 request = new JftApiVendorCisInfoMaintainRequestV1();

		// 4、根据测试环境和生产环境替换相应ip和端口
		request.setServiceUrl("https://ip:port/api/jft/api/vendor/cis/info/maintain/V1");
		
		// 5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
		JftApiVendorCisInfoMaintainRequestV1Biz bizContent = new JftApiVendorCisInfoMaintainRequestV1Biz();
		bizContent.setAppId(APP_ID);
		bizContent.setOutUpperVendorId(APP_ID);
		bizContent.setOutVendorId("Test07320010");
		bizContent.setOutVendorManagerId("Test07320010");
		bizContent.setRegistType("1");
		bizContent.setBizType("03");
		bizContent.setBusSwitch("1");
		bizContent.setVendorName("北京中指实证数据信息技术有限公司重庆分公司");
		bizContent.setVendorShortName("北京中指信息");
		bizContent.setVendorPhone("13888888868");
		bizContent.setVendorEmail("zzszxx@zzxx.com");
		bizContent.setProvince("110000");
		bizContent.setCity("110100");
		bizContent.setCounty("110102");
		bizContent.setAddress("东城xx街道xx号");
		bizContent.setPostcode("100000");
		bizContent.setOperatorName("小东东");
		bizContent.setOperatorMobile("13777777999");
		bizContent.setOperatorEmail("zzszxx@zzxx.com");
		bizContent.setOperatorIdNo("110101198506230318");
		bizContent.setVendorType("01");
		bizContent.setCertType("103");
		bizContent.setCertNo("915001035789578375");
		bizContent.setCertPic("mer8014c8412df44a4e83c4056f09968249");
		bizContent.setCertValiditylStr("2099-09-19");
		bizContent.setCorporateIdValidityStr("2099-09-09");
		bizContent.setCorporateName("汪勇");
		bizContent.setCorporateMobile("16888888888");
		bizContent.setCorporateIdType("2");
		bizContent.setCorporateIdNo("1231000012231");
		bizContent.setCorporateIdPic1("mer8014c8412df44a4e83c4056f09968249");
		bizContent.setCorporateIdPic2("mer8014c8412df44a4e83c4056f09968249");
		bizContent.setOtherCertPic1("mer8014c8412df44a4e83c4056f09968249");
		bizContent.setOtherCertPic2("mer8014c8412df44a4e83c4056f09968249");
		bizContent.setOtherCertPic3("mer8014c8412df44a4e83c4056f09968249");
		bizContent.setAccountBizType("1");
		bizContent.setAccountName("北京中指实证数据信息技术有限公司重庆分公司");
		bizContent.setAccountBankProvince("北京");
		bizContent.setAccountBankCity("北京");
		bizContent.setAccountBankNm("中国农业银行");
		bizContent.setAccountBankName("中国农业银行股份有限公司北京东城支行");
		bizContent.setAccountBankCode("100120101");
		bizContent.setAccountNo("6000012121212121212");
		bizContent.setAccountMobile("18612312312");
		bizContent.setSerBankZoneno("00402");
		bizContent.setSerBankBrno("00202");
		bizContent.setIntoWechat("0");
		bizContent.setIntoAlipay("0");

		bizContent.setPrimaryIndustry("设计、制作广告；经济贸易咨询");
		bizContent.setVatNo("333333-1");
		bizContent.setLocalVatNo(null);
		bizContent.setCoNo("22EDD021201");
		bizContent.setBusinessLicense("915001035789578375");
		bizContent.setLicenseEndDate("2022-06-20");
		bizContent.setAddressType("2");
		bizContent.setCountry("156");
		bizContent.setPartnerName("张三");
		bizContent.setPartnerType("2");
		bizContent.setPartnerNo("110101197011020101");
		bizContent.setPartnerEndDate("2022-02-23");
		bizContent.setCorpEndDate("2032-11-22");
		bizContent.setAuthName("李四");
		bizContent.setAuthType("2");
		bizContent.setAuthNo("11010119701102221");
		bizContent.setAuthEndDate("2120-05-03");
		bizContent.setAuthAreaCde("010");
		bizContent.setAuthNoBody("86222222");
		bizContent.setAuthExtn("");
		bizContent.setIndustryCode("A0410");
		bizContent.setOrgScale("02");
		bizContent.setNoOfEmp("7");
		bizContent.setOwnerTotal("11");
		bizContent.setGrossRevn("9");
		bizContent.setGrossRevnCrncy("001");
		bizContent.setEmployerType("2");
		
		setBeneficiaryInfos("5", bizContent);
		request.setBizContent(bizContent);
		
		JftApiVendorCisInfoMaintainResponseV1 response;
		try {
			response = client.execute(request, System.currentTimeMillis() + "");
			if (response.isSuccess()) {
				// 6、业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
				System.out.println("ReturnCode:" + response.getReturnCode());
				System.out.println("ReturnCode:" + response.getReturnMsg());
				System.out.println("response:" + response);
			} else {
				// 失败
				System.out.println("ReturnCode:" + response.getReturnCode());
				System.out.println("ReturnMsg:" + response.getReturnMsg());
			}
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 非自然人类型：<br>
	 * 1、公司； <br> 2、合伙企业；<br> 3、信托公司；<br> 4、基金公司；<br> 5、个体工商户；<br> 6、个人独资企业；<br> 7、不具备法人资格的专业服务机构；<br>
	 * 8、经营农林渔牧产业的非公司制农民专业合作组织；<br> 9、受政府控制的企事业单位<br>
	 * 自然人类型：<br>
	 * 1、直接或者间接拥有超过25%公司股权或者表决权的自然人（0-4人）信息（按实际情况填写） <br>
	 * 2、通过人事、财务等其他方式对公司进行控制的自然人信息（按实际情况填写）<br>
	 * 3、公司高级管理人员（总经理、副总经理、财务负责人，上市公司董事会秘书和公司章程规定的其他人员）信息。（必填项） <br>
	 * 
	 * 公司客户请依次填写: 1、2、3类型自然人信息  <br>
	 * 合伙企业、信托公司、 基金公司客户仅填写: 1 类型自然人信息 <br>
	 * 个体工商户等其他类型客户仅填写:2 类型自然人信息　<br>
	 * 
	 * @param nonNaturalCustType
	 *            非自然人类型
	 * @param beneficiaryInfos
	 *            自然人信息集合
	 */
	private static void setBeneficiaryInfos(String nonNaturalCustType, JftApiVendorCisInfoMaintainRequestV1Biz bizContent) {
		List<BeneficiaryInfo> beneficiaryInfos = new ArrayList<>(3);
		BeneficiaryInfo b = new BeneficiaryInfo();
		if ("1".equals(nonNaturalCustType)) {
			setBenecifiaryInfo1(b, "1");
			BeneficiaryInfo b2 = new BeneficiaryInfo();
			setBenecifiaryInfo2(b2, "2");
			BeneficiaryInfo b3 = new BeneficiaryInfo();
			setBenecifiaryInfo3(b3, "3");
			beneficiaryInfos.add(b);
			beneficiaryInfos.add(b2);
			beneficiaryInfos.add(b3);
		} else if ("2".equals(nonNaturalCustType) || "3".equals(nonNaturalCustType) || "4".equals(nonNaturalCustType)) {
			setBenecifiaryInfo1(b, "1");
			beneficiaryInfos.add(b);
		} else {
			setBenecifiaryInfo2(b, "2");
			beneficiaryInfos.add(b);
		}
		bizContent.setNonNaturalCustType(nonNaturalCustType);
		bizContent.setBeneficiaryInfos(beneficiaryInfos);
	}
	
	/**
	 * 自然人类型1: 不设置职务字段
	 */
	private static void setBenecifiaryInfo1(BeneficiaryInfo bnf, String bnfType){
		bnf.setBeneficiaryType(bnfType);
		bnf.setNoBeneficiary("0");
		bnf.setBnfName1("自然人1姓名");
		bnf.setBnfRegNo1("bnfno1");
		bnf.setBnfRegType1("1");
		bnf.setBnfCountry1("156");
		bnf.setBnfProvince1("110000");
		bnf.setBnfCity1("110100");
		bnf.setBnfCounty1("110101");
		bnf.setBnfAddr1("自然人1地址");
		bnf.setBnfLongFlag1("0");
		bnf.setBnfEndDate1("2019-08-12");
		bnf.setBnfRatio1(1000);
		
		bnf.setBnfName2("自然人2姓名");
		bnf.setBnfRegNo2("bnfno2");
		bnf.setBnfRegType2("2");
		bnf.setBnfCountry2("156");
		bnf.setBnfProvince2("120000");
		bnf.setBnfCity2("120100");
		bnf.setBnfCounty2("120101");
		bnf.setBnfAddr2("自然人2地址");
		bnf.setBnfLongFlag2("1");
		bnf.setBnfEndDate2(null);
		bnf.setBnfRatio2(2000);
		
		bnf.setBnfName3("自然人3姓名");
		bnf.setBnfRegNo3("bnfno3");
		bnf.setBnfRegType3("3");
		bnf.setBnfCountry3("156");
		bnf.setBnfProvince3("130000");
		bnf.setBnfCity3("120100");
		bnf.setBnfCounty3("130101");
		bnf.setBnfAddr3("自然人3地址");
		bnf.setBnfLongFlag3("0");
		bnf.setBnfEndDate3("2029-09-12");
		bnf.setBnfRatio3(3000);
		
		bnf.setBnfName4("自然人4姓名");
		bnf.setBnfRegNo4("bnfno4");
		bnf.setBnfRegType4("4");
		bnf.setBnfCountry4("156");
		bnf.setBnfProvince4("140000");
		bnf.setBnfCity4("140100");
		bnf.setBnfCounty4("140101");
		bnf.setBnfAddr4("自然人4地址");
		bnf.setBnfLongFlag4("1");
		bnf.setBnfEndDate4(null);
		bnf.setBnfRatio4(4000);
	}
	
	/**
	 * 自然人类型 2： 不设置 占比、职务字段
	 */
	private static void setBenecifiaryInfo2(BeneficiaryInfo bnf, String bnfType){
		bnf.setBeneficiaryType(bnfType);
		bnf.setNoBeneficiary("0");
		bnf.setBnfName1("自然人1姓名");
		bnf.setBnfRegNo1("bnfno1");
		bnf.setBnfRegType1("1");
		bnf.setBnfCountry1("156");
		bnf.setBnfProvince1("210000");
		bnf.setBnfCity1("210100");
		bnf.setBnfCounty1("210101");
		bnf.setBnfAddr1("自然人1地址");
		bnf.setBnfLongFlag1("0");
		bnf.setBnfEndDate1("2019-08-12");
		
		bnf.setBnfName2("自然人2姓名");
		bnf.setBnfRegNo2("bnfno2");
		bnf.setBnfRegType2("2");
		bnf.setBnfCountry2("156");
		bnf.setBnfProvince2("220000");
		bnf.setBnfCity2("220100");
		bnf.setBnfCounty2("220101");
		bnf.setBnfAddr2("自然人2地址");
		bnf.setBnfLongFlag2("1");
		bnf.setBnfEndDate2(null);
		
		bnf.setBnfName3("自然人3姓名");
		bnf.setBnfRegNo3("bnfno3");
		bnf.setBnfRegType3("3");
		bnf.setBnfCountry3("156");
		bnf.setBnfProvince3("230000");
		bnf.setBnfCity3("230100");
		bnf.setBnfCounty3("230101");
		bnf.setBnfAddr3("自然人3地址");
		bnf.setBnfLongFlag3("0");
		bnf.setBnfEndDate3("2029-09-12");
	}
	
	/**
	 * 自然人类型 3： 不设置占比字段
	 */
	private static void setBenecifiaryInfo3(BeneficiaryInfo bnf, String bnfType){
		bnf.setBeneficiaryType(bnfType);
		bnf.setNoBeneficiary("0");
		bnf.setBnfName1("自然人1姓名");
		bnf.setBnfRegNo1("bnfno1");
		bnf.setBnfRegType1("1");
		bnf.setBnfCountry1("156");
		bnf.setBnfProvince1("310000");
		bnf.setBnfCity1("310100");
		bnf.setBnfCounty1("310101");
		bnf.setBnfAddr1("自然人1地址");
		bnf.setBnfLongFlag1("0");
		bnf.setBnfEndDate1("2019-08-12");
		bnf.setBnfTitle1("1");
		
		bnf.setBnfName2("自然人2姓名");
		bnf.setBnfRegNo2("bnfno2");
		bnf.setBnfRegType2("2");
		bnf.setBnfCountry2("156");
		bnf.setBnfProvince2("320000");
		bnf.setBnfCity2("320100");
		bnf.setBnfCounty2("320101");
		bnf.setBnfAddr2("自然人2地址");
		bnf.setBnfLongFlag2("1");
		bnf.setBnfEndDate2(null);
		bnf.setBnfTitle2("2");
		
		bnf.setBnfName3("自然人3姓名");
		bnf.setBnfRegNo3("bnfno3");
		bnf.setBnfRegType3("3");
		bnf.setBnfCountry3("156");
		bnf.setBnfProvince3("330000");
		bnf.setBnfCity3("330100");
		bnf.setBnfCounty3("330101");
		bnf.setBnfAddr3("自然人3地址");
		bnf.setBnfLongFlag3("0");
		bnf.setBnfEndDate3("2029-09-12");
		bnf.setBnfTitle3("3");
		
		bnf.setBnfName4("自然人4姓名");
		bnf.setBnfRegNo4("bnfno4");
		bnf.setBnfRegType4("4");
		bnf.setBnfCountry4("156");
		bnf.setBnfProvince4("340000");
		bnf.setBnfCity4("340100");
		bnf.setBnfCounty4("340101");
		bnf.setBnfAddr4("自然人4地址");
		bnf.setBnfLongFlag4("1");
		bnf.setBnfEndDate4(null);
		bnf.setBnfTitle4("4");
	}

}
