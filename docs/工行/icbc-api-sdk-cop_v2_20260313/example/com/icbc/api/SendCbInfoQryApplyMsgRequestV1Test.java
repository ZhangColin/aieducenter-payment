package com.icbc.api;

import com.icbc.api.request.SendCbInfoQryApplyMsgRequestV1;
import com.icbc.api.request.SendCbInfoQryApplyMsgRequestV1.SendCbInfoQryApplyMsgRequestBizV1;
import com.icbc.api.response.CbspPaySinglepayResponseV1;

import java.util.logging.Logger;

public class SendCbInfoQryApplyMsgRequestV1Test {
protected static Logger log = Logger.getLogger(SendCbInfoQryApplyMsgRequestV1Test.class.getName());
	
	protected static final String APIGW_PUBLIC_KEY = "xxxx";
	protected static final String APP_ID = "123";
	protected static final String MY_PRIVATE_KEY = "xxxx";
	
	public static void main(String[] args) {
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
		SendCbInfoQryApplyMsgRequestV1 request = new SendCbInfoQryApplyMsgRequestV1();
        request.setServiceUrl("http://IP:PORT/cbps/nonpay/querysendinfoqryapplymsg/query/V1");
		SendCbInfoQryApplyMsgRequestBizV1 bizContent = new SendCbInfoQryApplyMsgRequestBizV1();
        SendCbInfoQryApplyMsgRequestV1.SendCbInfoQryApplyMsgRequestBizV1Inprivate bizContentPri =
                new SendCbInfoQryApplyMsgRequestV1.SendCbInfoQryApplyMsgRequestBizV1Inprivate();

		bizContentPri.setQrytype("");
		bizContentPri.setIdtype("");
		bizContentPri.setQryid("");
		bizContentPri.setAccbkcode("");
		bizContentPri.setAccname("");
		bizContentPri.setOthrname("");
		bizContentPri.setDebtcurr("");
		bizContentPri.setCrbtcurr("");
		bizContentPri.setCountrycode("");
		bizContentPri.setMsgmark("");

        
        bizContent.setInprivate(bizContentPri);
        
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
