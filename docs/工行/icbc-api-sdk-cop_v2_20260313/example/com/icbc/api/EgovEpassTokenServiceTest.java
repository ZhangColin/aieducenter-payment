package com.icbc.api;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcResponse;
import com.icbc.api.request.MybankPayEgovEpasstokenvalidateRequestV1;
import com.icbc.api.request.MybankPayEgovEpasstokenvalidateRequestV1.MybankPayEgovEpasstokenvalidateRequestV1Biz;

import org.junit.Assert;

public class EgovEpassTokenServiceTest {
	// 1、网关公钥
	protected static final String APIGW_PUBLIC_KEY = "xxx";
	// 2、密钥对认证方式，公钥在API平台登记，此处为私钥
	protected static final String MY_PRIVATE_KEY = "xxx";
	// 3、appid
	protected static final String APP_ID = "xxxxxxxxx";
	
    public static void main(String[] args) {
        final String URL = "https://ip:port/api/mybank/pay/fiancial/tokenValidate/V1";

        MybankPayEgovEpasstokenvalidateRequestV1 request = new MybankPayEgovEpasstokenvalidateRequestV1();
        request.setServiceUrl(URL);
        MybankPayEgovEpasstokenvalidateRequestV1Biz bizContent = new MybankPayEgovEpasstokenvalidateRequestV1Biz();
        bizContent.setAppType("002");
        bizContent.setToken("913b571e50143d1593a153f65e13ff0b31ec69b1576493ba6f8aa44534547db31e06ae45496f78b06376fc1df5ce5a47");
        request.setBizContent(bizContent);

        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        try {
        	IcbcResponse responseV1 = client.execute(request);
        	Assert.assertNotNull(responseV1);
        } catch (IcbcApiException e) {
			e.printStackTrace();
        }
      }
}
