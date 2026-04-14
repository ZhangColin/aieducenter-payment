package com.icbc.api.test;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.request.JftApiInvoiceVendorInfoQueryRequestV1;
import com.icbc.api.response.JftApiInvoiceVendorInfoQueryResponseV1;

/**
 * 聚富通开票企业信息查询
 * 
 * 请商户配置SDK中的lib文件夹中的jar包后，根据备注1-6提示进行数据替换
 */
public class JftApiInvoiceVendorInfoQueryTestV1 {

	//1、网关公钥
	protected static final String APIGW_PUBLIC_KEY = "";
	//2、appid
	protected static final String APP_ID = "";
	//3、密钥对认证方式，公钥在API平台登记，此处为私钥
	protected static final String MY_PRIVATE_KEY = "";
	
	public static void main(String[] args) throws Exception {

		// 签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, "AES", "", "", "");

		JftApiInvoiceVendorInfoQueryRequestV1 request = new JftApiInvoiceVendorInfoQueryRequestV1();

		// 4、根据测试环境和生产环境替换相应ip和端口
		request.setServiceUrl("http://ip:port/api/jft/api/query/invoice/vendor/info/V1");

		// 5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
		JftApiInvoiceVendorInfoQueryRequestV1.JftApiInvoiceVendorInfoQueryRequestV1Biz bizContent =
				new JftApiInvoiceVendorInfoQueryRequestV1.JftApiInvoiceVendorInfoQueryRequestV1Biz();
		bizContent.setAppId(APP_ID);
		bizContent.setOutVendorId("999221245");
		request.setBizContent(bizContent);

		JftApiInvoiceVendorInfoQueryResponseV1 response;
		try {
			response = (JftApiInvoiceVendorInfoQueryResponseV1) client.execute(request, System.currentTimeMillis() + "");
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

