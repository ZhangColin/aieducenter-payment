package com.icbc.api.test;

import java.util.Random;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.request.MybankPayCpayMemberqueryRequestV1;
import com.icbc.api.request.MybankPayCpayMemberqueryRequestV1.MybankPayCpayMemberqueryRequestV1Biz;
import com.icbc.api.response.MybankPayCpayMemberqueryResponseV1;

public class MybankPayCpayMemberqueryTestV1 {
    protected static final String MY_PRIVATE_KEY = "xxxx";

    protected static final String APIGW_PUBLIC_KEY = "xxxx";

    protected static final String APP_ID = "123"; 
		
    static Logger log = Logger.getLogger(MybankPayCpayMemberqueryTestV1.class.getName());

    public static void main(String[] args) {

   			DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        
        // request
        MybankPayCpayMemberqueryRequestV1 req = new MybankPayCpayMemberqueryRequestV1();
        
        // 
        req.setServiceUrl("http://IP:PORT/api/mybank/pay/cpay/memberquery/V1");

        MybankPayCpayMemberqueryRequestV1Biz biz = new MybankPayCpayMemberqueryRequestV1Biz();
        
        biz.setAgreeCode("AGREECODE20190215");
        biz.setMemberNo("001");       
        
        req.setBizContent(biz);
        MybankPayCpayMemberqueryResponseV1 response = new MybankPayCpayMemberqueryResponseV1();

        Random rand = new Random();
        String msgId = rand.nextInt(99999) + "msg";
        log.fine(msgId);

        try {
            response = client.execute(req, msgId);
            log.fine("response:" + response.toString());
            if (response.isSuccess()) {
                // 
                log.fine("success");//
            } else {
                //
                log.fine("error");
            }
        } catch (IcbcApiException e) {
            e.printStackTrace();
        } finally {
        }
    }
}
