package com.icbc.api;

import java.util.ArrayList;
import java.util.List;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.BiomServicesICsrcToFhProfhSortEncapRequestV1;
import com.icbc.api.response.BiomServicesICsrcToFhProfhSortEncapResponseV1;

public class BiomServicesICsrcToFhProfhSortEncapV1Test {


    protected static final String MY_PRIVATE_KEY = "****";

    protected static final String APP_ID = "1234";

    protected static final String APIGW_PUBLIC_KEY = "****";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String) null, (String) null, (String) null, (String) null);

        BiomServicesICsrcToFhProfhSortEncapRequestV1 request = new BiomTransportCashICsrcToFhProfhSortEncapRequestV1();

        BiomServicesICsrcToFhProfhSortEncapRequestV1.BiomServicesICsrcToFhProfhSortEncapRequestV1Biz bizContent = new BiomServicesICsrcToFhProfhSortEncapRequestV1.BiomTransportCashICsrcToFhProfhUpdTaskRequestV1Biz();

        BiomServicesICsrcToFhProfhSortEncapRequestV1.PrivateReqBean privateReqBean = new BiomServicesICsrcToFhProfhSortEncapRequestV1.PrivateReqBean();

        BiomServicesICsrcToFhProfhSortEncapRequestV1.PublicReqBean publicReqBean = new BiomServicesICsrcToFhProfhSortEncapRequestV1.PublicReqBean();

        privateReqBean.setZoneno("200");
        privateReqBean.setBrno("998");
        privateReqBean.setApplyno("123");
        privateReqBean.setOperTell("123");
        privateReqBean.setCurrType("001");
        privateReqBean.setOrderid("123");
        privateReqBean.setPckgNum("1");
        privateReqBean.setMacno("1");


        List<BiomServicesICsrcToFhProfhSortEncapRequestV1.allotDet> allotDet = new ArrayList<>();

        BiomServicesICsrcToFhProfhSortEncapRequestV1.allotDet allot = new BiomServicesICsrcToFhProfhSortEncapRequestV1.allotDet();
        allot.setCurrNum("1");
        allot.setCurrVoucher("123");
        allotDet.add(allot);

        List<BiomTransportCashICsrcToFhProfhSortEncapRequestV1.pckgs> pckgs = new ArrayList<>();
        BiomServicesICsrcToFhProfhSortEncapRequestV1.pckgs pckg = new BiomServicesICsrcToFhProfhSortEncapRequestV1.pckgs();
        pckg.setAllotDet(allotDet);
        pckg.setIdentifId("12333");
        pckg.setPckgAmount("1234444");
        pckg.setPckgId("123");
        pckg.setPckgSheets("123");
        pckgs.add(pckg);
        privateReqBean.setPckgs(pckgs);


        publicReqBean.setChancode("702");
        publicReqBean.setOapp("F-BIOM");
        publicReqBean.setOtellerno("888899996");
        publicReqBean.setZoneno("200");

        bizContent.setPrivateReqBean(privateReqBean);
        bizContent.setPublicReqBean(publicReqBean);

        request.setBizContent(bizContent);

        try {
            BiomServicesICsrcToFhProfhSortEncapResponseV1 response = client.execute(request);
            if (response.getReturn_code() == 0) {
                System.out.println("ReturnCode:" + response.getReturn_code());
                System.out.println("response:" + JSON.toJSONString(response));
            } else {
                System.out.println("response:" + JSON.toJSONString(response));
                System.out.println("ReturnCode:" + response.getReturnCode());
                System.out.println("ReturnMsg:" + response.getReturnMsg());
            }
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }
}
