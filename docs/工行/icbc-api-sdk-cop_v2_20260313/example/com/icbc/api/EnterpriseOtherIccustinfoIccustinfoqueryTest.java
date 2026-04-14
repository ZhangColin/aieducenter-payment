package com.icbc.api;

import java.util.logging.Logger;

import com.icbc.api.request.EnterpriseOtherIccustinfoIccustinfoqueryRequestV1;
import com.icbc.api.response.EnterpriseOtherIccustinfoIccustinfoqueryResponseV1;

public class EnterpriseOtherIccustinfoIccustinfoqueryTest {
	static Logger log = Logger.getLogger(EnterpriseOtherIccustinfoIccustinfoqueryTest.class.getName());
    
    protected static final  String MY_PUB_KEY =  "";
   
    protected static final String MY_PRIVATE_KEY="";
    
    protected static final String APIGW_PUBLIC_KEY = "";

    protected static final String APP_ID = "";
    
    protected static final String SIGN_TYPE ="RSA2";
    
  public static void testApi(){
    	DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, SIGN_TYPE,MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
    	EnterpriseOtherIccustinfoIccustinfoqueryRequestV1 request = 	new EnterpriseOtherIccustinfoIccustinfoqueryRequestV1();
    	request.setServiceUrl("http://ip:port/api/enterprise/other/iccustinfo/iccustinfoquery/V1");
    	
    	EnterpriseOtherIccustinfoIccustinfoqueryRequestV1.EnterpriseOtherIccustinfoIccustinfoqueryRequestV1Biz bizContent = 
    		new EnterpriseOtherIccustinfoIccustinfoqueryRequestV1.EnterpriseOtherIccustinfoIccustinfoqueryRequestV1Biz();
    	
    	bizContent.setCreditoce("96989492MA69QX373T");
    	bizContent.setCustname("");
    	request.setBizContent(bizContent);
    	
    	EnterpriseOtherIccustinfoIccustinfoqueryResponseV1 response;
    	
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
