package com.icbc.api;

import java.util.logging.Logger;

import com.icbc.api.request.IcCustInfoQueryRequestV1;
import com.icbc.api.request.MybankPaySellcombosetfrombranchSellcombosetfrombranchRequestV1.MybankPaySellcombosetfrombranchSellcombosetfrombranchRequestV1Biz;
import com.icbc.api.response.IcCustInfoQueryResponseV1;

public class MybankPaySellcombosetfrombranchSellcombosetfrombranchTest {
	static Logger log = Logger.getLogger(MybankPaySellcombosetfrombranchSellcombosetfrombranchTest.class.getName());
    
    protected static final  String MY_PUB_KEY =  "";
   
    protected static final String MY_PRIVATE_KEY="";
    
    protected static final String APIGW_PUBLIC_KEY = "";

    protected static final String APP_ID = "";
    
    protected static final String SIGN_TYPE ="RSA2";
    
  public static void testApi(){
    	DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, SIGN_TYPE,MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
    	IcCustInfoQueryRequestV1 request = 	new IcCustInfoQueryRequestV1();
    	request.setServiceUrl("http://ip:port/api/mybank/pay/sellComboSetFromBranch/sellComboSetFromBranch/V1");
    	
    	MybankPaySellcombosetfrombranchSellcombosetfrombranchRequestV1Biz bizContent = 
    		new MybankPaySellcombosetfrombranchSellcombosetfrombranchRequestV1Biz();
    	
    	bizContent.setUserId("020011213");
    	bizContent.setOpeDate("20200810");
    	bizContent.setZoneNo("00200");
    	bizContent.setBrNo("00998");
    	bizContent.setJSTCFWFASEQ("000000000097809");
    	bizContent.setQRYACCNO("wrwtery45645242fdsf");
    	bizContent.setQRYACCCUR("001");
    	bizContent.setQRYACCIDX("");
    	bizContent.setPARTCHARGEFLAG("1");
    	bizContent.setPAYFLAG("1");
    	bizContent.setPAYACC("wrwtery45645242fdsf");
    	bizContent.setPAYCURR("001");
    	bizContent.setPAYACCIDX("");
    	bizContent.setSIGNDATE("20210809");
    	bizContent.setACTDATE("20210809");
    	bizContent.setDeferIssue("1");
    	bizContent.setBRCHSPEFLAG("1");
    	
    	request.setBizContent(bizContent);
    	
    	IcCustInfoQueryResponseV1 response;
    	
    	try {
    		 response = client.execute(request);
             if (response.isSuccess()) {
             	log.info("success");
             	
             } else {
             	log.info("fail"+response.getReturnMsg());
             	
             }
		} catch (IcbcApiException e) {
			// TODO: handle exception
			
			log.info(e.getMessage());
		}
    
    	
    }
    
    public static void main(String[] args) {
        testApi();
    }
}
