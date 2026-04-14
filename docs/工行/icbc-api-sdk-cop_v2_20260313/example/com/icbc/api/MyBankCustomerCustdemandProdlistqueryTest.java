package com.icbc.api;

import java.util.logging.Logger;

import com.icbc.api.request.CorporatecustCustdemandProdlistQueryRequestV1;
import com.icbc.api.response.CorporatecustCustdemandProdlistQueryResponseV1;

public class MyBankCustomerCustdemandProdlistqueryTest {
	static Logger log = Logger.getLogger(MyBankCustomerCustdemandProdlistqueryTest.class.getName());
    //证书公钥
    protected static final  String MY_PUB_KEY =  "";
    //证书私钥
    protected static final String MY_PRIVATE_KEY="";
    //网关公钥
    protected static final String APIGW_PUBLIC_KEY = "";

    protected static final String APP_ID = "";

    public static void testApi(){
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        CorporatecustCustdemandProdlistQueryRequestV1 request = new CorporatecustCustdemandProdlistQueryRequestV1();
        request.setServiceUrl("http://ip:port/api/corporatecust/custdemand/prodlist/query/V1");
        CorporatecustCustdemandProdlistQueryRequestV1.CorporatecustCustdemandProdlistQueryRequestV1Biz bizContent =
                new CorporatecustCustdemandProdlistQueryRequestV1.CorporatecustCustdemandProdlistQueryRequestV1Biz();

        bizContent.setBegNum(0);
        bizContent.setFetchNum(13);

        request.setBizContent(bizContent);

        CorporatecustCustdemandProdlistQueryResponseV1 response;
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
