package com.icbc.api;


import com.icbc.api.request.UpdateLocalProtocalNegotiationRequestV1;
import com.icbc.api.response.UpdateLocalProtocalNegotiationResponseV1;

import java.util.logging.Logger;

public class UpdateLocalProtocalNegotiationRequestV1Test {

    protected static Logger log = Logger.getLogger(UpdateLocalProtocalNegotiationRequestV1Test.class.getName());

    protected static final String APIGW_PUBLIC_KEY = "xxxx";
    protected static final String APP_ID = "123";
    protected static final String MY_PRIVATE_KEY = "xxxx";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        UpdateLocalProtocalNegotiationRequestV1 request = new UpdateLocalProtocalNegotiationRequestV1();
        request.setServiceUrl("http://IP:PORT/cnaps/param/cpba/modagreemt/request/V1");

        UpdateLocalProtocalNegotiationRequestV1.UpdateLocalProtocalNegotiationRequestBizV1 bizContent = new UpdateLocalProtocalNegotiationRequestV1.UpdateLocalProtocalNegotiationRequestBizV1();
        UpdateLocalProtocalNegotiationRequestV1.UpdateLocalProtocalNegotiationRequestBizV1InPrivate bizContentPri =
                new UpdateLocalProtocalNegotiationRequestV1.UpdateLocalProtocalNegotiationRequestBizV1InPrivate();

        bizContentPri.setZoneno("");
        bizContentPri.setAgmtno("");
        bizContentPri.setSndaccno("");
        bizContentPri.setBusitype("");
        bizContentPri.setClosedate("");
        bizContentPri.setRequestid("");
        bizContentPri.setCycdnlimit("");
        bizContentPri.setCycdamtlimit("");
        bizContentPri.setTimeunit("");
        bizContentPri.setTimestep("");
        bizContentPri.setModtlno("");
        bizContentPri.setModydate("");
        bizContentPri.setAuthtelno("");

        bizContent.setInPrivate(bizContentPri);

        request.setBizContent(bizContent);
        UpdateLocalProtocalNegotiationResponseV1 response;
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
