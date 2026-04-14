package com.icbc.api;

import com.icbc.api.request.CbspContractManagemsgRequestV1;
import com.icbc.api.request.CbspContractManagemsgRequestV1.CbspContractManagemsgRequestBizV1;
import com.icbc.api.response.CbspContractManagemsgResponseV1;

import java.util.logging.Logger;

public class CbspContractManagemsgRequestV1Test {
protected static Logger log = Logger.getLogger(CbspContractManagemsgRequestV1Test.class.getName());
	
	protected static final String APIGW_PUBLIC_KEY = "xxxx";
	protected static final String APP_ID = "123";
	protected static final String MY_PRIVATE_KEY = "xxxx";
	
	public static void main(String[] args) {
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
		CbspContractManagemsgRequestV1 request = new CbspContractManagemsgRequestV1();
        request.setServiceUrl("http://IP:PORT/cbsp/contract/managemsg/request/v1");
        CbspContractManagemsgRequestBizV1 bizContent = new CbspContractManagemsgRequestBizV1();
        CbspContractManagemsgRequestV1.CbspContractManagemsgRequestBizV1Srvprivate bizContentPri =
                new CbspContractManagemsgRequestV1.CbspContractManagemsgRequestBizV1Srvprivate();

		bizContentPri.setCorpno("");
		bizContentPri.setFeeitem("");
		bizContentPri.setProjectcode("");
		bizContentPri.setAcctname("");
		bizContentPri.setAcctno("");
		bizContentPri.setAccttype("");
		bizContentPri.setAddr("");
		bizContentPri.setAuthcode("");
		bizContentPri.setAuthmodel("");
		bizContentPri.setBankno("");
		bizContentPri.setChgtype("");
		bizContentPri.setContracttype("");
		bizContentPri.setCurrency("");
		bizContentPri.setCustno("");
		bizContentPri.setDuedate("");
		bizContentPri.setMemo("");
		bizContentPri.setNumlimit("");
		bizContentPri.setEffectdate("");
		bizContentPri.setMoneylimit("");
		bizContentPri.setOncelimit("");
		bizContentPri.setPayerid("");
		bizContentPri.setSendtype("");
		bizContentPri.setPayeridtype("");
		bizContentPri.setSendtype("");
		bizContentPri.setSigndate("");
		bizContentPri.setPayername("");
		bizContentPri.setProductid("");
        
        bizContent.setSrvprivate(bizContentPri);
        
        request.setBizContent(bizContent);
        CbspContractManagemsgResponseV1 response;
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
