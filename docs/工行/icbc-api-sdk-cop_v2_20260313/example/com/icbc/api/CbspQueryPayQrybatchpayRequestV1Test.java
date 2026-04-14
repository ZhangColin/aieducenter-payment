package com.icbc.api;

import com.icbc.api.request.CbspQueryPayQrybatchpayRequestV1;
import com.icbc.api.request.CbspQueryPayQrybatchpayRequestV1.CbspQueryPayQrybatchpayRequestBizV1;
import com.icbc.api.response.CbspQueryPayQrybatchpayResponseV1;

import java.util.logging.Logger;

public class CbspQueryPayQrybatchpayRequestV1Test {
protected static Logger log = Logger.getLogger(CbspQueryPayQrybatchpayRequestV1Test.class.getName());
	
	protected static final String APIGW_PUBLIC_KEY = "xxxx";
	protected static final String APP_ID = "123";
	protected static final String MY_PRIVATE_KEY = "xxxx";
	
	public static void main(String[] args) {
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
		CbspQueryPayQrybatchpayRequestV1 request = new CbspQueryPayQrybatchpayRequestV1();
        request.setServiceUrl("http://IP:PORT/cbsp/query/pay/qrybathlepay/request/v1");
        CbspQueryPayQrybatchpayRequestBizV1 bizContent = new CbspQueryPayQrybatchpayRequestBizV1();
        CbspQueryPayQrybatchpayRequestV1.CbspQueryPayQrybatchpayRequestBizV1Srvprivate bizContentPri =
                new CbspQueryPayQrybatchpayRequestV1.CbspQueryPayQrybatchpayRequestBizV1Srvprivate();

		bizContentPri.setOserialn("");
        
        bizContent.setSrvprivate(bizContentPri);
        
        request.setBizContent(bizContent);
        CbspQueryPayQrybatchpayResponseV1 response;
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
