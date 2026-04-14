package com.icbc.api;


import com.icbc.api.request.QueryLocalProtocalNegotiationRequestV1;
import com.icbc.api.response.QueryLocalProtocalNegotiationResponseV1;

import java.util.logging.Logger;

public class QueryLocalProtocalNegotiationRequestV1Test {

    protected static Logger log = Logger.getLogger(QueryLocalProtocalNegotiationRequestV1Test.class.getName());

    protected static final String APIGW_PUBLIC_KEY = "xxxx";
    protected static final String APP_ID = "123";
    protected static final String MY_PRIVATE_KEY = "xxxx";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        QueryLocalProtocalNegotiationRequestV1 request = new QueryLocalProtocalNegotiationRequestV1();
        request.setServiceUrl("http://IP:PORT/cnaps/param/cpba/qryagreemt/request/V1");

        QueryLocalProtocalNegotiationRequestV1.QueryLocalProtocalNegotiationRequestBizV1 bizContent = new QueryLocalProtocalNegotiationRequestV1.QueryLocalProtocalNegotiationRequestBizV1();
        QueryLocalProtocalNegotiationRequestV1.QueryLocalProtocalNegotiationRequestBizV1InPrivate bizContentPri =
                new QueryLocalProtocalNegotiationRequestV1.QueryLocalProtocalNegotiationRequestBizV1InPrivate();

        bizContentPri.setZoneno("");
        bizContentPri.setAgmtno("");
        bizContentPri.setSndaccno("");
        bizContentPri.setBusitype("");

        bizContent.setInPrivate(bizContentPri);

        request.setBizContent(bizContent);
        QueryLocalProtocalNegotiationResponseV1 response;
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
