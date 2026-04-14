package com.icbc.api;

import com.icbc.api.request.BcssMemberRechargeRefundRequestV1;
import com.icbc.api.request.BcssMemberRechargeRefundRequestV1.BcssMemberRechargeRefundRequestBizV1;

public class BcssMemberRechargeRefundTestV1 {
	   // MY_PRIVATE_KEY
    protected static final String MY_PRIVATE_KEY = "XXXXXXXXXXXXXXXXXX";
    // APIGW_PUBLIC_KEY
    protected static final String APIGW_PUBLIC_KEY = "XXXXXXXXXXXXXXXXXX";
    // APP_ID
    protected static final String APP_ID = "1*************************6";
    
    protected static final String RSA = "RSA";
    protected static final String RSA2 = "RSA2";

    public static void main(String[] args) throws Exception {
    	BcssMemberRechargeRefundTestV1 a = new BcssMemberRechargeRefundTestV1();
        a.test_cop();
    }

    public void test_cop() throws IcbcApiException {
        BcssMemberRechargeRefundRequestV1 request = new BcssMemberRechargeRefundRequestV1();
        request.setServiceUrl("http://***.***.***.***:****/api/bcss/member/recharge/refund/V1 ");
        BcssMemberRechargeRefundRequestBizV1 bizContent = new BcssMemberRechargeRefundRequestBizV1();         
        bizContent.setCorpId("0***********3");
 
        bizContent.setQueryType("1");
        bizContent.setQrCode("22222222222222222");
        bizContent.setReason("34343");
     
        request.setBizContent(bizContent);
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,RSA2,MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        client.execute(request);
    }
}
