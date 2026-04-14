package com.icbc.api;

import com.icbc.api.request.QuerySendCbInfoQryApplyMsgRequestV1;
import com.icbc.api.request.QuerySendCbInfoQryApplyMsgRequestV1.QuerySendCbInfoQryApplyMsgRequestBizV1;
import com.icbc.api.response.CbspPaySinglepayResponseV1;

import java.util.logging.Logger;

public class QuerySendCbInfoQryApplyMsgRequestV1Test {
protected static Logger log = Logger.getLogger(QuerySendCbInfoQryApplyMsgRequestV1Test.class.getName());
	
	protected static final String APIGW_PUBLIC_KEY = "xxxx";
	protected static final String APP_ID = "123";
	protected static final String MY_PRIVATE_KEY = "xxxx";
	
	public static void main(String[] args) {
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
		QuerySendCbInfoQryApplyMsgRequestV1 request = new QuerySendCbInfoQryApplyMsgRequestV1();
        request.setServiceUrl("http://IP:PORT/cbps/nonpay/querysendinfoqryapplymsg/query/V1");
		QuerySendCbInfoQryApplyMsgRequestBizV1 bizContent = new QuerySendCbInfoQryApplyMsgRequestBizV1();
        QuerySendCbInfoQryApplyMsgRequestV1.QuerySendCbInfoQryApplyMsgRequestBizV1Inprivate bizContentPri =
                new QuerySendCbInfoQryApplyMsgRequestV1.QuerySendCbInfoQryApplyMsgRequestBizV1Inprivate();

		bizContentPri.setOserialn("");
        
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
