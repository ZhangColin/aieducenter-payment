package com.icbc.api;

import com.icbc.api.request.EpaymentSubclassRequestV1;
import com.icbc.api.request.EpaymentSubclassRequestV1.EpaymentSubclassRequestV1Biz;

public class EpaymentSubclassV1Test {
	
	private static final String APP_ID = "";
	private static final String MY_PRIVATE_KEY = "";
	public static void main(String[] args) throws IcbcApiException {

		// TODO Auto-generated method stub
		UiIcbcClient client = new UiIcbcClient(APP_ID, MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8);

        EpaymentSubclassRequestV1 request = new EpaymentSubclassRequestV1();
        request.setServiceUrl("https://ip:port/ui/epayment/subclass/V1");

        EpaymentSubclassRequestV1Biz bizContent = new EpaymentSubclassRequestV1Biz();
        bizContent.setComputeId("21");
        bizContent.setAreaCode("0200");
        bizContent.setUserId("0123456789");   
        bizContent.setApiTip("%E6%B8%A9%E9%A6%A8%E6%8F%90%E7%A4%BA%EF%BC%9A%0D%0A%E8%BF%99%E9%87%8C%E6%98%AF%E5%B7%A5%E5%95%86%E9%93%B6%E8%A1%8C%E5%B7%A5%E9%93%B6e%E7%BC%B4%E8%B4%B9%E7%B3%BB%E7%BB%9F%EF%BC%8C%E8%AF%B7%E6%8C%89%E6%8F%90%E7%A4%BA%E7%BC%B4%E8%B4%B9%EF%BC%8C%E8%B0%A2%E8%B0%A2%EF%BC%81");
        bizContent.setProvinceCode("1200");
        bizContent.setCityCode("1202");
        bizContent.setPaymentSubType("1401");     
        request.setBizContent(bizContent);

        client.buildPostForm(request);
	}

}
