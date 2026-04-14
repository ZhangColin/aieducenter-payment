package com.icbc.api;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.BcssMemberPayProducttimesQueryRequestV1;
import com.icbc.api.request.BcssMemberPayProducttimesQueryRequestV1.BcssMemberPayProducttimesQueryRequesBizV1;
import com.icbc.api.response.BcssMemberPayProducttimesQueryResponseV1;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



public class BcssMemberPayProductTimesQueryTestV1 {
	
	private static final Log logger = LogFactory.getLog(BcssMemberPayProductTimesQueryTestV1.class);
	// MY_PRIVATE_KEY
	protected static final String MY_PRIVATE_KEY = "********";

	// APIGW_PUBLIC_KEY
	protected static final String APIGW_PUBLIC_KEY = "*8888888";

    // APP_ID
    protected static final String APP_ID = "1*************************6";
    
    protected static final String RSA = "RSA";
    protected static final String RSA2 = "RSA2";

    public static void main(String[] args) throws Exception {
    	BcssMemberPayProductTimesQueryTestV1 a = new BcssMemberPayProductTimesQueryTestV1();
        a.test_cop();
    }

    public void test_cop() throws IcbcApiException {
        BcssMemberPayProducttimesQueryRequestV1 request = new BcssMemberPayProducttimesQueryRequestV1();
        request.setServiceUrl("http://*******8/api/bcss/member/lmttimeCouponQuery/V1 ");
        BcssMemberPayProducttimesQueryRequesBizV1 bizContent = new BcssMemberPayProducttimesQueryRequesBizV1();         
        bizContent.setCorpId("0***********3");
        bizContent.setOperType("1");
        bizContent.setStartNum("1");
        bizContent.setEndNum("10");
        bizContent.setMerNo("2323232");
        bizContent.setClientTransNo("1212121");
        bizContent.setTimeStamp("2019-08-21 10:58:41");
     
        request.setBizContent(bizContent);
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,RSA2,MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        BcssMemberPayProducttimesQueryResponseV1 result = client.execute(request);
        logger.info(JSON.toJSONString(result));
        
    }
    
}
