package com.icbc.api;

import com.icbc.api.request.CbspQuerySendBatchAcpMsgRequestV1;
import com.icbc.api.response.CbspQuerySendBatchAcpMsgResponseV1;

import java.util.logging.Logger;

public class CbspQuerySendBatchAcpMsgRequestV1Test {
protected static Logger log = Logger.getLogger(CbspQuerySendBatchAcpMsgRequestV1Test.class.getName());
	
	protected static final String APIGW_PUBLIC_KEY = "xxxx";
	protected static final String APP_ID = "123";
	protected static final String MY_PRIVATE_KEY = "xxxx";
	
	public static void main(String[] args) {
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
		CbspQuerySendBatchAcpMsgRequestV1 request = new CbspQuerySendBatchAcpMsgRequestV1();
        request.setServiceUrl("http://IP:PORT/cbsp/query/receive/qrybathleacp/request/v1");
        CbspQuerySendBatchAcpMsgRequestV1.CbspQuerySendBatchAcpMsgRequestBizV1 bizContent = new CbspQuerySendBatchAcpMsgRequestV1.CbspQuerySendBatchAcpMsgRequestBizV1();
        CbspQuerySendBatchAcpMsgRequestV1.CbspQuerySendBatchAcpMsgRequestBizV1InPrivate bizContentPri =
                new CbspQuerySendBatchAcpMsgRequestV1.CbspQuerySendBatchAcpMsgRequestBizV1InPrivate();

		bizContentPri.setLstno("");
		bizContentPri.setOserialn("");
        
        bizContent.setInPrivate(bizContentPri);
        
        request.setBizContent(bizContent);
        CbspQuerySendBatchAcpMsgResponseV1 response;
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
