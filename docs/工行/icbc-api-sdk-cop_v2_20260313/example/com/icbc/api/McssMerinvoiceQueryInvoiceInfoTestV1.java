package com.icbc.api;


import com.alibaba.fastjson.JSONObject;
import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.request.McssMerinvoiceQueryInvoiceInfoRequestV1;
import com.icbc.api.response.McssMerinvoiceQueryInvoiceInfoResponseV1;


public  class McssMerinvoiceQueryInvoiceInfoTestV1 {


	protected static final String APIGW_PUBLIC_KEY = "xxxx";
	protected static final String MY_PRIVATE_KEY = "xxxx";
	protected static final String APP_ID = "xxxx";
	
	public static void main(String[] args) throws Exception{
		McssMerinvoiceQueryInvoiceInfoTestV1 a=new McssMerinvoiceQueryInvoiceInfoTestV1();
		a.test_cop();
	}

	public void test_cop() throws IcbcApiException {
		McssMerinvoiceQueryInvoiceInfoRequestV1 request = new McssMerinvoiceQueryInvoiceInfoRequestV1();
		request.setServiceUrl("http://ip:port/api/mcss/merinvoice/queryInvoiceInfo/V1");
		McssMerinvoiceQueryInvoiceInfoRequestV1.McssMerinvoiceQueryInvoiceInfoRequestBizV1 bizContent = new McssMerinvoiceQueryInvoiceInfoRequestV1.McssMerinvoiceQueryInvoiceInfoRequestBizV1();
		//商户APPID
		bizContent.setAppId(APP_ID);
		//商户编号
		bizContent.setPrtiId("020057018890");
		//工行订单号、外部协议编号+商户订单号必输其一 
		bizContent.setOrderId("C251107095511121121CPY");
		//开票企业编号
		bizContent.setOutVendorId("20251112KP002");

		request.setBizContent(bizContent);
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
		McssMerinvoiceQueryInvoiceInfoResponseV1 response = client.execute(request);

	}
}