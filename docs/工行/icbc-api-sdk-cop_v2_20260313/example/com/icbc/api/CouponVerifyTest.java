package com.icbc.api;

import javax.swing.JFrame;

import com.icbc.api.request.CouponVerifyRequestV1;
import com.icbc.api.request.CouponVerifyRequestV1.CouponVerifyRequestV1Biz;
import com.icbc.api.response.CouponVerifyResponseV1;

/**
 * 团购券验券接口
 * 
 * 请商户配置SDK中的lib文件夹中的jar包后，根据备注1-6提示进行数据替换
 */
public class CouponVerifyTest extends JFrame {
	
	//1、网关公钥
	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

	//2、appid
	protected static final String APP_ID = "10000000000000002156";

	//3、密钥对认证方式，公钥在API平台登记，此处为私钥
	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----CdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALvA7jcDdNDIvIy4w0aRiWGuYOMoyavmogTAEJ12O7hCBUUQ6s2xUdrmcspNbxgn+5wEeVy2uuJnjWFTvsxIRq4H0fK8QOCWQfMWLhQgHZfLFn9sXyXeMYzkhCWmcbKB2R7/aYY9nsocirOoKUn4vj06h2NsZRsSxmV2N7he5LUnAgMBAAECgYEAgDgddoxxtjOgMCntkb/KJl9D413ze+WhN8VCk4Yu+oJ7vtLyx04VLHy8X9PJzqFffwi/KMXRktRNBO6AGloxiXFZHnGZZXtCRUwaJ0ULz2VJ3nkBIkawFnHTsz4I0S+PuMrDBXExLayrtIQrXNhTEo1fnmXY80GTMfaUNmlktIECQQD9KpPk0Pm0IvfnZK4smH09sLtgicuFzCs3BA9GJvZWXem00UC8up3HxuOSKMynQc2/3yY3hConDM0oul2yVtjxAkEAvdrrYB7vqlGt4cES22j7bgW38wi+oK6c2zlzsTGj++LxO7OFG4rhaaVmJ2/Wu87gjDGEoNnvAeGdMFQelT6vlwJAQ81zboTJW4VB/6bnPupYIH2EsYY2C89yg3mC3bbxoo/kV5jzTZhtSWPq7uoeZW0qPYj2I0gyQeMbzxyam3jHAQJAAvpgHs3dbIqDc7sXlKF88Cjezc0hUFvs0fOAwRLKQ7U3m3whZmEzT/jsNUon0CJam1hbgDpq6VikopFvOWAWWQJAR1m9XnG8cEDxfKTKf8ruB+0MYnbuFQBWTkF/mp69Pf89wUodzx609VOxHsZqoi5bPLL66kZvoAPcPxiht47MOQ==";
	
	public static void main(String[] args) throws Exception {

		//签名类型为SHA256WithRSA时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
		
		CouponVerifyRequestV1 request = new CouponVerifyRequestV1();
		
		//4、根据测试环境和生产环境替换相应ip和端口
		request.setServiceUrl("https://ip:port/api/coupon/verify/V1");

		//5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
		CouponVerifyRequestV1Biz bizContent = new CouponVerifyRequestV1Biz();
		bizContent.setCouponNo("9766992392614293");
		bizContent.setMerId("020002040095");
		bizContent.setOperId("11111111");
 
		bizContent.setOutTradeNo("123456789");
    bizContent.setAttachData("9999999");
		request.setBizContent(bizContent);

		CouponVerifyResponseV1 response;
		try {
			response = client.execute(request, "msgId");
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


