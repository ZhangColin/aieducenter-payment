package com.icbc.api;

import javax.servlet.http.HttpServlet;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.request.EpmPartyfeeBillUpdateRequestV1;
import com.icbc.api.response.EpmPartyfeeBillUpdateResponseV1;

public class EpmPartyfeeBillUpdateApiServiceImplTest extends HttpServlet {

	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1588786734333856129L;
	
	public static void main(String[] args) {

		String appId ="10000000000000469001";
		String MY_PRIVATE_KEY ="-----请使用正确的APP私钥-----EvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCG8kbF/xbf2YnIWa/xXI38NFuDMaHjqVgSw0iq3BvPTd5fLL5XfWlub8NE9qxhWqVqKEh0RQYp5xoqD2ryh4weSAtvzHls5PwCiLlLTfCBzD4RLr/PnYoyOkxk8gZ5ggJBc7LIGQUKCcGxdESyvwR7jKPGxNqVDwOQdTIOLPDbdmu0frLDlh3r9FceneMk3BqFFu9vCKj4Rq9sCTc/le09hUH2KN9oAXiJGC6Beyw7V+NvjArjKEl18jEc6V4llw+3WqNxasbZoniN0lXsq0gKmeP4HFDbnpzWF338CpAkSeUVtCWUHVtrE0B83r3hUTlb8Y42y5bWWuy7mEt+pXr9AgMBAAECggEAHwt7tq8xrvvX08y2SluVmpDdALiZWsuppF9ix1Pt3eo3H0Qm4e/LIon5rVLmY1s8NMjhqkX63gb5oPV9ECCY3pqKbwTmZmPjih20KxvaGbJIESHlJLlEgruguZRoYunC5RXd6mS/IQkPk2iBKXICcP3w65h8BNC4bDJZ+qLiIkqr9cZlYM7F3/4DgpYphQtdQcJMgTKzlZchQ/aqE9xtBl0oQLjyVR1TzOoxkZ0jDVoIvf7S2P+g4c+JsLzGJOOVcFmgU61oyNvtQ6jaElumOXdWlrNT7y87S5oqRPZSTJEGpWj/DLnvBOVx6z2TszkFzyNVx8w5IgQYobGH074zQQKBgQDg8SP+acRDVGVk3W7zwSSD2fNVC/laiQP4/EGSAPc7R7CL/JsZvmzw76Q373V73hbsTwHgdy6O24X6WKSKhKrXhikQ+2d3BplQ+v7+ZUU/BxxIrWplsU/AGfxAFI1M6+ZrrHnXeOVAUcJSIMeWRi8YPO3+AwRspB6MRD+iY93ujQKBgQCZlCDV8yi59h5/3tf222QCo5uTZmJfsqPfL5TgRf9YLRzyusKchSGHaJYIZvNBPfne3OaWe3yXjNCjonRsF+LBoBpIHptfVs02/3m2L6LhPuRzVp2zxPi3kckhkDRh0bmXODshGS6RVH+yHy0x8y1AGyPgbZww3XhjbJh18NKaMQKBgQDazvbaVfwKGqB+2z9AcVKZx3sB8aHfRYTNwGPwKGxWB8heg5xEEI5cWF//4TkBAup9d+U1efmaEURkX7G+5IpjGCf+Uu80QwEEWwOMZupTAxGqSLZEsZQII77SfqtsddQwTRhAV2N5i45g4W4EacUJ6MsA2eKF46erfgAAcPDDSQKBgG86mkF6N6/fch14Xv+Zy6ghqGasC2l17zuQsYlxCMtq+QFPg+1qemsgszDopxtEcLkZUiQA0a7NPLqWyS0Ua5WCiCZdjHgTXNONWjA59gSe0UQ7okH10ZDaFEqrr1R9WaYNAr4pB9IX2dg+vjPkBIzeWzCLEklNzYhsuUsKHO2xAoGBAKbZIVPODSI71XHFddsu09KbjCijG116cs8MylW07sZ8mVDx/FZE+wrOj/wgk85n1RfDSvZd3HL8i+hVtSB3lP8YsnOmySXTWB5PV7sJ/U6M9Yw9B2Pt6VAPjsksa5rI/SW963zRM7xiL/ztwJd8mfA5Pf2GqvgZ6T2OHXyQbC9D";
		String APIGW_PUBLIC_KEY ="-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16"+
		"bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCL"+
		"T7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP"+
		"3CiNgg0W6O3AGqwIDAQAB";

		DefaultIcbcClient client = new DefaultIcbcClient(appId, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

		EpmPartyfeeBillUpdateRequestV1 request = new EpmPartyfeeBillUpdateRequestV1();		
		request.setServiceUrl("http://ip:port/api/epm/partyfee/bill/update/V1");
		EpmPartyfeeBillUpdateRequestV1.EpmPartyfeeBillUpdateRequestV1Biz bizContent = new EpmPartyfeeBillUpdateRequestV1.EpmPartyfeeBillUpdateRequestV1Biz();
		request.setBizContent(bizContent);
		bizContent.setProtocolNo("PT20181213100000108002");
		bizContent.setBillNo("1291218");
		bizContent.setAmount("111");
		bizContent.setStartDate("20190401");
		bizContent.setEndDate("20190630");
		bizContent.setStatus("0");

		EpmPartyfeeBillUpdateResponseV1 response;
		try {
			response = client.execute(request, "msgId");
			// 业务成功处理
			System.out.println(response.getReturnCode());
			System.out.println(response.getReturnMsg());
			System.out.println(response.getMsgId());
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
