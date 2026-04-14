package com.icbc.api;


import com.icbc.api.request.DeleteLocalProtocalNegotiationRequestV1;
import com.icbc.api.response.DeleteLocalProtocalNegotiationResponseV1;

import java.util.logging.Logger;

public class DeleteLocalProtocalNegotiationRequestV1Test {

    protected static Logger log = Logger.getLogger(DeleteLocalProtocalNegotiationRequestV1Test.class.getName());

    protected static final String APIGW_PUBLIC_KEY = "xxxx";
    protected static final String APP_ID = "123";
    protected static final String MY_PRIVATE_KEY = "xxxx";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        DeleteLocalProtocalNegotiationRequestV1 request = new DeleteLocalProtocalNegotiationRequestV1();
        request.setServiceUrl("http://IP:PORT/cnaps/param/cpba/delagreemt/request/V1");

        DeleteLocalProtocalNegotiationRequestV1.DeleteLocalProtocalNegotiationRequestBizV1 bizContent = new DeleteLocalProtocalNegotiationRequestV1.DeleteLocalProtocalNegotiationRequestBizV1();
        DeleteLocalProtocalNegotiationRequestV1.DeleteLocalProtocalNegotiationRequestBizV1InPrivate bizContentPri =
                new DeleteLocalProtocalNegotiationRequestV1.DeleteLocalProtocalNegotiationRequestBizV1InPrivate();

        bizContentPri.setZoneno("");
        bizContentPri.setAgmtno("");
        bizContentPri.setSndaccno("");
        bizContentPri.setBusitype("");
        bizContentPri.setDeltlno("");
        bizContentPri.setDeldate("");
        bizContentPri.setAuthtelno("");

        bizContent.setInPrivate(bizContentPri);

        request.setBizContent(bizContent);
        DeleteLocalProtocalNegotiationResponseV1 response;
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
