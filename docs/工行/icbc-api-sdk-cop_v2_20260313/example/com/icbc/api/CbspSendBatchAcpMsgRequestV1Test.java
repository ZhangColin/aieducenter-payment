package com.icbc.api;

import com.icbc.api.request.CbspSendBatchAcpMsgRequestV1;
import com.icbc.api.request.CbspSendBatchAcpMsgRequestV1.CbspSendBatchAcpMsgRequestBizV1;
import com.icbc.api.response.CbspSendBatchAcpMsgResponseV1;

import java.util.logging.Logger;

public class CbspSendBatchAcpMsgRequestV1Test {
protected static Logger log = Logger.getLogger(CbspSendBatchAcpMsgRequestV1Test.class.getName());
	
	protected static final String APIGW_PUBLIC_KEY = "xxxx";
	protected static final String APP_ID = "123";
	protected static final String MY_PRIVATE_KEY = "xxxx";
	
	public static void main(String[] args) {
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
		CbspSendBatchAcpMsgRequestV1 request = new CbspSendBatchAcpMsgRequestV1();
        request.setServiceUrl("http://IP:PORT/cbsp/receive/bathleacp/request/v1");
        CbspSendBatchAcpMsgRequestBizV1 bizContent = new CbspSendBatchAcpMsgRequestBizV1();
        CbspSendBatchAcpMsgRequestV1.CbspSendBatchAcpMsgRequestBizV1InPrivate bizContentPri =
                new CbspSendBatchAcpMsgRequestV1.CbspSendBatchAcpMsgRequestBizV1InPrivate();

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
        CbspSendBatchAcpMsgResponseV1 response;
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
