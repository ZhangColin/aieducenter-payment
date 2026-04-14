package com.icbc.api;

import com.icbc.api.request.GucpInteligntComDataRequestV1;
import com.icbc.api.request.GucpInteligntComDataRequestV1.GucpInteligntComDataRequestBizV1;
import com.icbc.api.response.GucpInteligntComDataResponseV1;

import java.util.logging.Logger;

public class GucpInteligntComDataRequestV1Test {
protected static Logger log = Logger.getLogger(GucpInteligntComDataRequestV1Test.class.getName());
	
	protected static final String APIGW_PUBLIC_KEY = "xxxx";
	protected static final String APP_ID = "123";
	protected static final String MY_PRIVATE_KEY = "xxxx";
	
	public static void main(String[] args) {
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
		GucpInteligntComDataRequestV1 request = new GucpInteligntComDataRequestV1();
        request.setServiceUrl("http://IP:PORT/api/InteligntComData/qryBnkDataDw/query/V1");
		GucpInteligntComDataRequestBizV1 bizContent = new GucpInteligntComDataRequestBizV1();
		GucpInteligntComDataRequestBizV1.GucpInteligntComDataRequestBizV1Srvprivate bizContentPri =
                new GucpInteligntComDataRequestBizV1.GucpInteligntComDataRequestBizV1Srvprivate();

		bizContentPri.setCardNo("10");
        
        bizContent.setSrvprivate(bizContentPri);
        
        request.setBizContent(bizContent);
		GucpInteligntComDataResponseV1 response;
		try {
			response = client.execute(request);
			if (response.isSuccess()) {
	            System.out.println("success");
	        } else {
	            System.out.println("error");
	        }
		} catch (IcbcApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
