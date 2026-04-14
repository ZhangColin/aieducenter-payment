package com.icbc.api;


import com.icbc.api.request.AddLocalProtocalNegotiationRequestV1;
import com.icbc.api.response.AddLocalProtocalNegotiationResponseV1;

import java.util.logging.Logger;

public class AddLocalProtocalNegotiationRequestV1Test {

    protected static Logger log = Logger.getLogger(AddLocalProtocalNegotiationRequestV1Test.class.getName());

    protected static final String APIGW_PUBLIC_KEY = "xxxx";
    protected static final String APP_ID = "123";
    protected static final String MY_PRIVATE_KEY = "xxxx";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        AddLocalProtocalNegotiationRequestV1 request = new AddLocalProtocalNegotiationRequestV1();
        request.setServiceUrl("http://IP:PORT/cnaps/param/cpba/addagreemt/request/V1");

        AddLocalProtocalNegotiationRequestV1.AddLocalProtocalNegotiationRequestBizV1 bizContent = new AddLocalProtocalNegotiationRequestV1.AddLocalProtocalNegotiationRequestBizV1();
        AddLocalProtocalNegotiationRequestV1.AddLocalProtocalNegotiationRequestBizV1InPrivate bizContentPri =
                new AddLocalProtocalNegotiationRequestV1.AddLocalProtocalNegotiationRequestBizV1InPrivate();

        bizContentPri.setZoneno("");
        bizContentPri.setBrno("");
        bizContentPri.setWdate("");
        bizContentPri.setAgmtno("");
        bizContentPri.setOpendate("");
        bizContentPri.setClosedate("");
        bizContentPri.setAgmttype("");
        bizContentPri.setRegionid("");
        bizContentPri.setUnisoccd("");
        bizContentPri.setCustomid("");
        bizContentPri.setCustname("");
        bizContentPri.setRequestid("");
        bizContentPri.setAuthmodel("");
        bizContentPri.setSacctype("");
        bizContentPri.setSndaccno("");
        bizContentPri.setSndbnkcode("");
        bizContentPri.setSndrbnkcode("");
        bizContentPri.setSndname("");
        bizContentPri.setRcvaccno("");
        bizContentPri.setRcvname("");
        bizContentPri.setBusitype("");
        bizContentPri.setSidcodetyp("");
        bizContentPri.setSidcode("");
        bizContentPri.setTelno("");
        bizContentPri.setAddr("");
        bizContentPri.setOncedlimit("");
        bizContentPri.setCycdnlimit("");
        bizContentPri.setCycdamtlimit("");
        bizContentPri.setTimeunit("");
        bizContentPri.setTimestep("");
        bizContentPri.setTimenotes("");
        bizContentPri.setMsgmark("");
        bizContentPri.setTyptlno("");
        bizContentPri.setTypdate("");
        bizContentPri.setAuthtelno("");

        bizContent.setInPrivate(bizContentPri);

        request.setBizContent(bizContent);
        AddLocalProtocalNegotiationResponseV1 response;
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
