package com.icbc.api;


import java.util.logging.Logger;

import com.icbc.api.request.MybankCustomerCustdemandSatisfactionupdateRequestV1;
import com.icbc.api.response.MybankCustomerCustdemandSatisfactionupdateResponseV1;

public class MyBankCustomerCustdemandSatisfactionupdateTest {
	static Logger log = Logger.getLogger(MyBankCustomerCustdemandSatisfactionupdateTest.class.getName());
    //证书公钥
    protected static final  String MY_PUB_KEY =  "";
    //证书私钥
    protected static final String MY_PRIVATE_KEY="";
    //网关公钥
    protected static final String APIGW_PUBLIC_KEY = "";

    protected static final String APP_ID = "";

    public static void testApi(){
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        MybankCustomerCustdemandSatisfactionupdateRequestV1 request = new MybankCustomerCustdemandSatisfactionupdateRequestV1();
        request.setServiceUrl("http://ip:port/api/corporatecust/custdemand/satisfaction/update/V1");
        MybankCustomerCustdemandSatisfactionupdateRequestV1.MybankCustomerCustdemandSatisfactionupdateRequestV1Biz bizContent =
                new MybankCustomerCustdemandSatisfactionupdateRequestV1.MybankCustomerCustdemandSatisfactionupdateRequestV1Biz();

        bizContent.setAddvice("");
        bizContent.setApprstar(2);
        bizContent.setDeamndSeq("");

        request.setBizContent(bizContent);

        MybankCustomerCustdemandSatisfactionupdateResponseV1 response;
        try {
            response = client.execute(request);
            if (response.isSuccess()) {
            	log.info("success");
            } else {
            	log.info("fail"+response.getReturnMsg());
            }
        } catch (IcbcApiException e) {
        	log.info(e.getMessage());
        }
    }

    public static void main(String[] args) {
        testApi();
    }
}
