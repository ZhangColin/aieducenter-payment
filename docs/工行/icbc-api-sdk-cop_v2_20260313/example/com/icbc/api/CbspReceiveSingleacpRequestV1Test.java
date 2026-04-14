package com.icbc.api;

import com.icbc.api.request.CbspReceiveSingleacpRequestV1;
import com.icbc.api.request.CbspReceiveSingleacpRequestV1.CbspReceiveSingleacpRequestBizV1;
import com.icbc.api.response.CbspReceiveSingleacpResponseV1;

import java.util.logging.Logger;

public class CbspReceiveSingleacpRequestV1Test {
protected static Logger log = Logger.getLogger(CbspReceiveSingleacpRequestV1Test.class.getName());
	
	protected static final String APIGW_PUBLIC_KEY = "xxxx";
	protected static final String APP_ID = "123";
	protected static final String MY_PRIVATE_KEY = "xxxx";
	
	public static void main(String[] args) {
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
		CbspReceiveSingleacpRequestV1 request = new CbspReceiveSingleacpRequestV1();
        request.setServiceUrl("http://IP:PORT/cbsp/receive/singleacp/request/v1");
        CbspReceiveSingleacpRequestBizV1 bizContent = new CbspReceiveSingleacpRequestBizV1();
        CbspReceiveSingleacpRequestV1.CbspReceiveSingleacpRequestBizV1Srvprivate bizContentPri =
                new CbspReceiveSingleacpRequestV1.CbspReceiveSingleacpRequestBizV1Srvprivate();

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
		bizContentPri.setContractno("");
        
        bizContent.setSrvprivate(bizContentPri);
        
        request.setBizContent(bizContent);
        CbspReceiveSingleacpResponseV1 response;
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
