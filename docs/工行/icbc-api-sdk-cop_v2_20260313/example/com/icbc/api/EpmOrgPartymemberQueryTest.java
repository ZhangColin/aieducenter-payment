package com.icbc.api;


import javax.servlet.http.HttpServlet;
import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.request.PartyerQueryRequestV1;
import com.icbc.api.response.PartyerQueryResponseV1;

public class EpmOrgPartymemberQueryTest extends HttpServlet{

	private static final long serialVersionUID = -5532204010005647452L;

	public static void main(String[] args) {
		String appId ="10000000000000469001";
		String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCG8kbF/xbf2YnIW\r\n" + 
				"a/xXI38NFuDMaHjqVgSw0iq3BvPTd5fLL5XfWlub8NE9qxhWqVqKEh0RQYp5xoqD2\r\n" + 
				"ryh4weSAtvzHls5PwCiLlLTfCBzD4RLr/PnYoyOkxk8gZ5ggJBc7LIGQUKCcGxdES\r\n" + 
				"yvwR7jKPGxNqVDwOQdTIOLPDbdmu0frLDlh3r9FceneMk3BqFFu9vCKj4Rq9sCTc/\r\n" + 
				"le09hUH2KN9oAXiJGC6Beyw7V+NvjArjKEl18jEc6V4llw+3WqNxasbZoniN0lXsq\r\n" + 
				"0gKmeP4HFDbnpzWF338CpAkSeUVtCWUHVtrE0B83r3hUTlb8Y42y5bWWuy7mEt+pX\r\n" + 
				"r9AgMBAAECggEAHwt7tq8xrvvX08y2SluVmpDdALiZWsuppF9ix1Pt3eo3H0Qm4e/\r\n" + 
				"LIon5rVLmY1s8NMjhqkX63gb5oPV9ECCY3pqKbwTmZmPjih20KxvaGbJIESHlJLlE\r\n" + 
				"gruguZRoYunC5RXd6mS/IQkPk2iBKXICcP3w65h8BNC4bDJZ+qLiIkqr9cZlYM7F3\r\n" + 
				"/4DgpYphQtdQcJMgTKzlZchQ/aqE9xtBl0oQLjyVR1TzOoxkZ0jDVoIvf7S2P+g4c\r\n" + 
				"+JsLzGJOOVcFmgU61oyNvtQ6jaElumOXdWlrNT7y87S5oqRPZSTJEGpWj/DLnvBOV\r\n" + 
				"x6z2TszkFzyNVx8w5IgQYobGH074zQQKBgQDg8SP+acRDVGVk3W7zwSSD2fNVC/la\r\n" + 
				"iQP4/EGSAPc7R7CL/JsZvmzw76Q373V73hbsTwHgdy6O24X6WKSKhKrXhikQ+2d3B\r\n" + 
				"plQ+v7+ZUU/BxxIrWplsU/AGfxAFI1M6+ZrrHnXeOVAUcJSIMeWRi8YPO3+AwRspB\r\n" + 
				"6MRD+iY93ujQKBgQCZlCDV8yi59h5/3tf222QCo5uTZmJfsqPfL5TgRf9YLRzyusK\r\n" + 
				"chSGHaJYIZvNBPfne3OaWe3yXjNCjonRsF+LBoBpIHptfVs02/3m2L6LhPuRzVp2z\r\n" + 
				"xPi3kckhkDRh0bmXODshGS6RVH+yHy0x8y1AGyPgbZww3XhjbJh18NKaMQKBgQDaz\r\n" + 
				"vbaVfwKGqB+2z9AcVKZx3sB8aHfRYTNwGPwKGxWB8heg5xEEI5cWF//4TkBAup9d+\r\n" + 
				"U1efmaEURkX7G+5IpjGCf+Uu80QwEEWwOMZupTAxGqSLZEsZQII77SfqtsddQwTRh\r\n" + 
				"AV2N5i45g4W4EacUJ6MsA2eKF46erfgAAcPDDSQKBgG86mkF6N6/fch14Xv+Zy6gh\r\n" + 
				"qGasC2l17zuQsYlxCMtq+QFPg+1qemsgszDopxtEcLkZUiQA0a7NPLqWyS0Ua5WCi\r\n" + 
				"CZdjHgTXNONWjA59gSe0UQ7okH10ZDaFEqrr1R9WaYNAr4pB9IX2dg+vjPkBIzeWz\r\n" + 
				"CLEklNzYhsuUsKHO2xAoGBAKbZIVPODSI71XHFddsu09KbjCijG116cs8MylW07sZ\r\n" + 
				"8mVDx/FZE+wrOj/wgk85n1RfDSvZd3HL8i+hVtSB3lP8YsnOmySXTWB5PV7sJ/U6M\r\n" + 
				"9Yw9B2Pt6VAPjsksa5rI/SW963zRM7xiL/ztwJd8mfA5Pf2GqvgZ6T2OHXyQbC9D";

		String APIGW_PUBLIC_KEY ="-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16"+
		"bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCL"+
		"T7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP"+
		"3CiNgg0W6O3AGqwIDAQAB";	
		
		String url = "http://ip:port/api/epm/org/partymember/query/V1";
		DefaultIcbcClient client = new DefaultIcbcClient(appId, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
		
		PartyerQueryRequestV1 request = new PartyerQueryRequestV1();
		PartyerQueryRequestV1.PartyerQueryRequestV1Biz bizContent = new PartyerQueryRequestV1.PartyerQueryRequestV1Biz();

		bizContent.setBeginNum("1");
		bizContent.setFetchNum("5");
		bizContent.setOrgId("100102100");
		bizContent.setProtocolNo("PT20181213100000108002"); 
		request.setServiceUrl(url);
		request.setBizContent(bizContent);	
		PartyerQueryResponseV1 resp = new PartyerQueryResponseV1();
		
		try {
			resp = client.execute(request, "msg_id");
			System.out.println("return_msg:"+resp.getReturnMsg());
			System.out.println("return_code:"+resp.getReturnCode());
			System.out.println("msg_id:"+resp.getMsgId());
		} catch (IcbcApiException e) {
			System.out.println("异常 err:"+e.getMessage());
		}

	}

}
