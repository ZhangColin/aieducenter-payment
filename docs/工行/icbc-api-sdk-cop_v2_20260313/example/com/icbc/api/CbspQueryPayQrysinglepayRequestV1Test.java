package com.icbc.api;

import com.icbc.api.request.CbspQueryPayQrysinglepayRequestV1;
import com.icbc.api.request.CbspQueryPayQrysinglepayRequestV1.CbspQueryPayQrysinglepayRequestBizV1;
import com.icbc.api.response.CbspQueryPayQrysinglepayResponseV1;

import java.util.logging.Logger;

public class CbspQueryPayQrysinglepayRequestV1Test {
protected static Logger log = Logger.getLogger(CbspQueryPayQrysinglepayRequestV1Test.class.getName());
	
	protected static final String APIGW_PUBLIC_KEY = "xxxx";
	protected static final String APP_ID = "123";
	protected static final String MY_PRIVATE_KEY = "xxxx";
	
	public static void main(String[] args) {
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
		CbspQueryPayQrysinglepayRequestV1 request = new CbspQueryPayQrysinglepayRequestV1();
        request.setServiceUrl("http://IP:PORT/cbsp/query/pay/qrysinglepay/request/v1");
        CbspQueryPayQrysinglepayRequestBizV1 bizContent = new CbspQueryPayQrysinglepayRequestBizV1();
        CbspQueryPayQrysinglepayRequestV1.CbspQueryPayQrysinglepayRequestBizV1Srvprivate bizContentPri =
                new CbspQueryPayQrysinglepayRequestV1.CbspQueryPayQrysinglepayRequestBizV1Srvprivate();

		bizContentPri.setOserialn("");
        
        bizContent.setSrvprivate(bizContentPri);
        
        request.setBizContent(bizContent);
        CbspQueryPayQrysinglepayResponseV1 response;
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
