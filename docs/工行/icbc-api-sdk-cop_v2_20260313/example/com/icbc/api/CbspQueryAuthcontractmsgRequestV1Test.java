package com.icbc.api;

import com.icbc.api.request.CbspQueryAuthcontractmsgRequestV1;
import com.icbc.api.request.CbspQueryAuthcontractmsgRequestV1.CbspQueryAuthcontractmsgRequestBizV1;
import com.icbc.api.response.CbspQueryAuthcontractmsgResponseV1;

import java.util.logging.Logger;

public class CbspQueryAuthcontractmsgRequestV1Test {
protected static Logger log = Logger.getLogger(CbspQueryAuthcontractmsgRequestV1Test.class.getName());
	
	protected static final String APIGW_PUBLIC_KEY = "xxxx";
	protected static final String APP_ID = "123";
	protected static final String MY_PRIVATE_KEY = "xxxx";
	
	public static void main(String[] args) {
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
		CbspQueryAuthcontractmsgRequestV1 request = new CbspQueryAuthcontractmsgRequestV1();
        request.setServiceUrl("http://IP:PORT/cbsp/query/authcontractmsg/request/v1");
        CbspQueryAuthcontractmsgRequestBizV1 bizContent = new CbspQueryAuthcontractmsgRequestBizV1();
        CbspQueryAuthcontractmsgRequestV1.CbspQueryAuthcontractmsgRequestBizV1InPrivate bizContentPri =
                new CbspQueryAuthcontractmsgRequestV1.CbspQueryAuthcontractmsgRequestBizV1InPrivate();

		bizContentPri.setCorpno("");
		bizContentPri.setAcctname("");
		bizContentPri.setAcctno("");
		bizContentPri.setBankno("");
		bizContentPri.setMemo("");
		bizContentPri.setContractno("");
		bizContentPri.setEffectdateend("");
		bizContentPri.setEffectdatestart("");
		bizContentPri.setInputdatestart("");
		bizContentPri.setInputdateend("");
		bizContentPri.setQuerytype("");
		bizContentPri.setSerialno("");
		bizContentPri.setSubnode("");
        
        bizContent.setInPrivate(bizContentPri);
        
        request.setBizContent(bizContent);
        CbspQueryAuthcontractmsgResponseV1 response;
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
