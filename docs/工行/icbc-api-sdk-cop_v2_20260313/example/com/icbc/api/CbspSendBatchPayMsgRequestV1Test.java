package com.icbc.api;

import com.icbc.api.request.CbspSendBatchPayMsgRequestV1;
import com.icbc.api.request.CbspSendBatchPayMsgRequestV1.CbspSendBatchPayMsgRequestBizV1;
import com.icbc.api.response.CbspSendBatchPayMsgResponseV1;

import java.util.logging.Logger;

public class CbspSendBatchPayMsgRequestV1Test {
protected static Logger log = Logger.getLogger(CbspSendBatchPayMsgRequestV1Test.class.getName());
	
	protected static final String APIGW_PUBLIC_KEY = "xxxx";
	protected static final String APP_ID = "123";
	protected static final String MY_PRIVATE_KEY = "xxxx";
	
	public static void main(String[] args) {
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
		CbspSendBatchPayMsgRequestV1 request = new CbspSendBatchPayMsgRequestV1();
        request.setServiceUrl("http://IP:PORT/cbsp/pay/batchpay/request/v1");
        CbspSendBatchPayMsgRequestBizV1 bizContent = new CbspSendBatchPayMsgRequestBizV1();
        CbspSendBatchPayMsgRequestV1.CbspSendBatchPayMsgRequestBizV1InPrivate bizContentPri =
                new CbspSendBatchPayMsgRequestV1.CbspSendBatchPayMsgRequestBizV1InPrivate();

		bizContentPri.setSubnode("");
		bizContentPri.setSubdate("");
		bizContentPri.setBatno("");
		bizContentPri.setCorpno("");
		bizContentPri.setTranscode("");
		bizContentPri.setProductcode("");
		bizContentPri.setFeeitem("");
		bizContentPri.setBankno("");
		bizContentPri.setAccbankno("");
		bizContentPri.setAccno("");
		bizContentPri.setAccname("");
		bizContentPri.setCurrency("");
		bizContentPri.setTotalnum("");
		bizContentPri.setTotalmoney("");
		bizContentPri.setAlternameflag("");
		bizContentPri.setAltersubcode("");
		bizContentPri.setNameflag("");
		bizContentPri.setOrderid("");
		bizContentPri.setProjectcode("");
		bizContentPri.setBatchremark("");
		bizContentPri.setProjectnode("");
		bizContentPri.setDetails("");

        
        bizContent.setInPrivate(bizContentPri);
        
        request.setBizContent(bizContent);
        CbspSendBatchPayMsgResponseV1 response;
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
