package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.BiomServiceIstorageoperUpdstrorageRequestV1;
import com.icbc.api.response.BiomServiceIstorageoperUpdstrorageResponseV1;

import java.util.List;

public class BiomServiceIstorageoperUpdstrorageV1Test {


    protected static final String MY_PRIVATE_KEY = "****";

    protected static final String APP_ID = "1234";

    protected static final String APIGW_PUBLIC_KEY = "****";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY,
         "UTF-8", "json", APIGW_PUBLIC_KEY, (String) null, (String) null, (String) null, (String) null);

        BiomServiceIstorageoperUpdstrorageRequestV1 request =
        new BiomServiceIstorageoperUpdstrorageRequestV1();

        BiomServiceIstorageoperUpdstrorageRequestV1.BiomServiceAemsdisposeStrorageoperRequestV1Biz bizContent =
        new BiomServiceIstorageoperUpdstrorageRequestV1.BiomServiceAemsdisposeStrorageoperRequestV1Biz();

        BiomServiceIstorageoperUpdstrorageRequestV1.PrivateReqBean privateReqBean = new BiomServiceIstorageoperUpdstrorageRequestV1.PrivateReqBean();

        BiomServiceIstorageoperUpdstrorageRequestV1.PublicReqBean publicReqBean = new BiomServiceIstorageoperUpdstrorageRequestV1.PublicReqBean();

        BiomServiceIstorageoperUpdstrorageRequestV1.item item = new BiomServiceIstorageoperUpdstrorageRequestV1.item();

        publicReqBean.setBranchFlag("sstsaemshttp");
        publicReqBean.setZoneno("0200");
        publicReqBean.setBrno("0002");
        publicReqBean.setTellno("10907");


        item.setAcelenum("1009");//
        item.setGoodsno("002000004");
        item.setAcelebno("740643433");
        item.setAceleeno("740643433");
        item.setCardnolength("");
        item.setInvcode("");
        item.setStatus("2");
        item.setCtlnoflg("1");
        item.setAcelebtp("7");

        List<BiomServiceIstorageoperUpdstrorageRequestV1.item> items = null;
        items.add(item);

        privateReqBean.setOperflag("33");
        privateReqBean.setFuhjyh("");
        privateReqBean.setZhuangjjytyrzh("020000316");
        privateReqBean.setFuhjytyrzh("020016998");
        privateReqBean.setShouqjyh("");
        privateReqBean.setHouqdm("");
        privateReqBean.setJuyjb("");
        privateReqBean.setKah("");
        privateReqBean.setGangwbh("");
        privateReqBean.setShouqmm("");
        privateReqBean.setShouqje("");
        privateReqBean.setDevid("000020069472128");
        privateReqBean.setWaiwdjylsh("1");
        privateReqBean.setDaicljls("1");
        privateReqBean.setFanjybz("0");
        privateReqBean.setNotes("");
        privateReqBean.setItems(items);


        bizContent.setPrivateReqBean(privateReqBean);
        bizContent.setPublicReqBean(publicReqBean);
        request.setBizContent(bizContent);

        try {
            BiomServiceIstorageoperUpdstrorageResponseV1 response = client.execute(request);
            if (response.getReturnCode() == 0) {
                System.out.println("ReturnCode:" + response.getReturnCode());
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
