package com.icbc.api;

import java.util.logging.Logger;

import com.icbc.api.request.CbspPaySinglepayRequestV1;
import com.icbc.api.request.CbspPaySinglepayRequestV1.CbspPaySinglepayRequestBizV1;
import com.icbc.api.response.CbspPaySinglepayResponseV1;

public class CbspPaySinglepayRequestV1Test {
protected static Logger log = Logger.getLogger(CbspPaySinglepayRequestV1Test.class.getName());
	
	protected static final String APIGW_PUBLIC_KEY = "xxxx";
	protected static final String APP_ID = "123";
	protected static final String MY_PRIVATE_KEY = "xxxx";
	
	public static void main(String[] args) {
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
		CbspPaySinglepayRequestV1 request = new CbspPaySinglepayRequestV1();
        request.setServiceUrl("http://IP:PORT/cbsp/pay/singlepay/request/v1");
        CbspPaySinglepayRequestBizV1 bizContent = new CbspPaySinglepayRequestBizV1();
        CbspPaySinglepayRequestV1.CbspPaySinglepayRequestBizV1Srvprivate bizContentPri =
                new CbspPaySinglepayRequestV1.CbspPaySinglepayRequestBizV1Srvprivate();

		bizContentPri.setAcpaccbankno("");
		bizContentPri.setAcpbankno("");
		bizContentPri.setCorpno("");
		bizContentPri.setFeeitem("");
		bizContentPri.setMsgmark("");
		bizContentPri.setMsgamt("");
		bizContentPri.setPayaccbankno("");
		bizContentPri.setPaybankno("");
		bizContentPri.setProductcode("");
		bizContentPri.setProductnum("");
		bizContentPri.setProjectcode("");
		bizContentPri.setRaccname("");
		bizContentPri.setRaccno("");
		bizContentPri.setSaccname("");
		bizContentPri.setSaccno("");
        
        bizContent.setSrvprivate(bizContentPri);
        
        request.setBizContent(bizContent);
        CbspPaySinglepayResponseV1 response;
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
