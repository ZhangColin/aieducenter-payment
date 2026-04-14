package com.icbc.api;

import java.util.HashMap;
import java.util.Map;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.request.MobileVerifyRequestV1;
import com.icbc.api.response.MobileVerifyResponseV1;

public class MobileVerifyTest {


	private static final String APIGW_PUBLIC_KEY = null;
	private static final String MY_PRIVATE_KEY = null;
	protected static String APP_ID = "10000000000000002225";
	public static void main(String[] args) {
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

		MobileVerifyRequestV1 request = new MobileVerifyRequestV1();		
		request.setServiceUrl("https://ip:port/api/mobile/verify/V1");
		MobileVerifyRequestV1.MobileVerifyRequestV1Biz bizContent = new MobileVerifyRequestV1.MobileVerifyRequestV1Biz();
		request.setBizContent(bizContent);
		bizContent.setMobile("15158114810");
		bizContent.setAcctName("XUZZ");
		bizContent.setAcctNo("6222020200000072500");
		bizContent.setIdType("1");
		bizContent.setIdNo("331082198911178108");
		bizContent.setTrxType("01");
		bizContent.setProjectId("PJ140014011000002153");
		bizContent.setTextSerno("00");//只能送00或者01，不送默认00通用模板
		bizContent.setPrtlLmtAmt("20000000");//单位分，不要带小数，非必输
		bizContent.setSmsNote("自定义备注内容");//短信会显示：XXXXXXXX，备注：自定义备注内容
		Map<String,String> templateParams = new HashMap<String,String>();
    templateParams.put("prtl_enddate","2018-11-22");
    bizContent.setTemplateParams(templateParams);
		MobileVerifyResponseV1 response;
		try {
			response = client.execute(request, "msgId");
			if (response.isSuccess()) {
				// 业务成功处理
				System.out.println(response.getReturnCode());

			} else {
				// 失败
			}		
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}
	}
}
