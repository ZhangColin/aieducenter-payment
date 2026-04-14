package com.icbc.api;

import com.icbc.api.request.CbspQueryReceiveQrysingleacpRequestV1;
import com.icbc.api.request.CbspQueryReceiveQrysingleacpRequestV1.CbspQueryReceiveQrysingleacpRequestBizV1;
import com.icbc.api.response.CbspQueryReceiveQrysingleacpResponseV1;

import java.util.logging.Logger;

public class CbspQueryReceiveQrysingleacpRequestV1Test {
protected static Logger log = Logger.getLogger(CbspQueryReceiveQrysingleacpRequestV1Test.class.getName());
	
	protected static final String APIGW_PUBLIC_KEY = "xxxx";
	protected static final String APP_ID = "123";
	protected static final String MY_PRIVATE_KEY = "xxxx";
	
	public static void main(String[] args) {
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
		CbspQueryReceiveQrysingleacpRequestV1 request = new CbspQueryReceiveQrysingleacpRequestV1();
        request.setServiceUrl("http://IP:PORT/cbsp/query/receive/qrysingleacp/request/v1");
        CbspQueryReceiveQrysingleacpRequestBizV1 bizContent = new CbspQueryReceiveQrysingleacpRequestBizV1();
        CbspQueryReceiveQrysingleacpRequestV1.CbspQueryReceiveQrysingleacpRequestBizV1Srvprivate bizContentPri =
                new CbspQueryReceiveQrysingleacpRequestV1.CbspQueryReceiveQrysingleacpRequestBizV1Srvprivate();

		bizContentPri.setOserialn("");
        
        bizContent.setSrvprivate(bizContentPri);
        
        request.setBizContent(bizContent);
        CbspQueryReceiveQrysingleacpResponseV1 response;
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
