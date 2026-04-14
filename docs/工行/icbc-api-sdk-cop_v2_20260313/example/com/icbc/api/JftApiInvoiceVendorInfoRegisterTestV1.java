package com.icbc.api.test;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.request.JftApiInvoiceVendorInfoRegisterRequestV1;
import com.icbc.api.response.JftApiInvoiceVendorInfoRegisterResponseV1;

/**
 * 聚富通开票企业商户注册接口
 * 
 * 请商户配置SDK中的lib文件夹中的jar包后，根据备注1-6提示进行数据替换
 */
public class JftApiInvoiceVendorInfoRegisterTestV1 {

	// 1、网关公钥（不是APP的公钥）
	protected static final String APIGW_PUBLIC_KEY = "";
	// 2、appid
	protected static final String APP_ID = "";
	// 3、密钥对认证方式，公钥在API平台登记，此处为私钥
	protected static final String MY_PRIVATE_KEY = "";

	public static void main(String[] args) throws Exception {

		// 签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, "UTF-8", "json",
				APIGW_PUBLIC_KEY, "AES", "", "", "");

		JftApiInvoiceVendorInfoRegisterRequestV1 request = new JftApiInvoiceVendorInfoRegisterRequestV1();

		// 4、根据测试环境和生产环境替换相应ip和端口
		request.setServiceUrl("https://ip:port/api/jft/api/vendor/info/register/V1");

		// 5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
		JftApiInvoiceVendorInfoRegisterRequestV1.JftApiInvoiceVendorInfoRegisterRequestV1Biz bizContent =
				new JftApiInvoiceVendorInfoRegisterRequestV1.JftApiInvoiceVendorInfoRegisterRequestV1Biz();
		bizContent.setAppId("10000000000000045006");
		bizContent.setOutVendorId("999900001");
		bizContent.setOutUserId("777700001");
		bizContent.setVendorName("XX公司");
		bizContent.setVendorShortName("公司简称");
		bizContent.setVendorPhone("13888888880");
		bizContent.setVendorEmail("aa@wqeq.com");
		bizContent.setProvince("130000");
		bizContent.setCity("130100");
		bizContent.setCounty("130101");
		bizContent.setAddress("xx路xx号xx楼xxx");
		bizContent.setPostcode("012340");
		bizContent.setOperatorName("张三");
		bizContent.setOperatorMobile("139999999");
		bizContent.setOperatorEmail("12@qq.com");
		bizContent.setVendorType("01");
		bizContent.setCertType("02");
		bizContent.setCertNo("66EW6455221");
		bizContent.setCertPic("merca308c0cb903494ab8e7f45b43fcce46");
		bizContent.setOtherCertPic1("merca308c0cb903494ab8e7f45b43fcce46");
		bizContent.setOtherCertPic2("merca308c0cb903494ab8e7f45b43fcce46");
		bizContent.setOtherCertPic3("merca308c0cb903494ab8e7f45b43fcce46");
		// ....其他参数参考md文档
		request.setBizContent(bizContent);

		JftApiInvoiceVendorInfoRegisterResponseV1 response;
		try {
			response = (JftApiInvoiceVendorInfoRegisterResponseV1) client.execute(request, System.currentTimeMillis() + "");
			if (response.isSuccess()) {
				// 6、业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
				System.out.println("ReturnCode:" + response.getReturnCode());
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
}
