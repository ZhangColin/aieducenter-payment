package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.BiomTransportCashCentrestorageSubmitcentreputinstorageRequestV1;
import com.icbc.api.response.BiomTransportCashCentrestorageSubmitcentreputinstorageResponseV1;

import java.util.ArrayList;
import java.util.List;

public class BiomTransportCashCentrestorageSubmitcentreputinstorageV1Test {

    protected static final String MY_PRIVATE_KEY = "****";

    protected static final String APP_ID = "1234";

    protected static final String APIGW_PUBLIC_KEY = "****";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String)null, (String)null, (String)null, (String)null);

        BiomTransportCashCentrestorageSubmitcentreputinstorageRequestV1 request = new BiomTransportCashCentrestorageSubmitcentreputinstorageRequestV1();

        BiomTransportCashCentrestorageSubmitcentreputinstorageRequestV1.BiomTransportCashCentrestorageSubmitcentreputinstorageRequestV1Biz bizContent = new BiomTransportCashCentrestorageSubmitcentreputinstorageRequestV1.BiomTransportCashCentrestorageSubmitcentreputinstorageRequestV1Biz();

        BiomTransportCashCentrestorageSubmitcentreputinstorageRequestV1.PrivateReqBean privateReqBean = new BiomTransportCashCentrestorageSubmitcentreputinstorageRequestV1.PrivateReqBean();

        BiomTransportCashCentrestorageSubmitcentreputinstorageRequestV1.PublicReqBean publicReqBean = new BiomTransportCashCentrestorageSubmitcentreputinstorageRequestV1.PublicReqBean();

        BiomTransportCashCentrestorageSubmitcentreputinstorageRequestV1.CategoryItem categoryItem = new BiomTransportCashCentrestorageSubmitcentreputinstorageRequestV1.CategoryItem();

        BiomTransportCashCentrestorageSubmitcentreputinstorageRequestV1.ListItem listItem = new BiomTransportCashCentrestorageSubmitcentreputinstorageRequestV1.ListItem();

        BiomTransportCashCentrestorageSubmitcentreputinstorageRequestV1.AticlePropAuto aticlePropAuto = new BiomTransportCashCentrestorageSubmitcentreputinstorageRequestV1.AticlePropAuto();
        BiomTransportCashCentrestorageSubmitcentreputinstorageRequestV1.AticlePropAuto aticlePropAuto2 = new BiomTransportCashCentrestorageSubmitcentreputinstorageRequestV1.AticlePropAuto();

        BiomTransportCashCentrestorageSubmitcentreputinstorageRequestV1.CurrItem currItem = new BiomTransportCashCentrestorageSubmitcentreputinstorageRequestV1.CurrItem();

        List<BiomTransportCashCentrestorageSubmitcentreputinstorageRequestV1.CurrItem> currItemList = new ArrayList<>();

        currItem.setAmount("1000");
        currItem.setCashType("1");
        currItem.setQrCode("0005");
        currItem.setTagType("1");
        currItem.setTicketNum("2");
        currItem.setTickets("1");
        currItemList.add(currItem);

        publicReqBean.setChancode("");
        publicReqBean.setOapp("F-BIOM");
        publicReqBean.setOtellerno("0020001242");
        publicReqBean.setZoneno("200");

        List<BiomTransportCashCentrestorageSubmitcentreputinstorageRequestV1.CategoryItem> categoryList = new ArrayList<>();

        List<BiomTransportCashCentrestorageSubmitcentreputinstorageRequestV1.ListItem> list = new ArrayList<>();

        List<BiomTransportCashCentrestorageSubmitcentreputinstorageRequestV1.AticlePropAuto> aticlePropAutoList = new ArrayList<>();

        aticlePropAuto.setQrCode("0008");
        aticlePropAuto2.setQrCode("0009");
        aticlePropAutoList.add(aticlePropAuto);
        aticlePropAutoList.add(aticlePropAuto2);

        listItem.setAceleNam("666");
        listItem.setAceleNo("66");
        listItem.setAticleProp("");
        listItem.setAticlePropManuNum("10");
        listItem.setTotalAticleNum("6");
        listItem.setAceleNum("6");
        listItem.setAticlePropAutoList(aticlePropAutoList);
        listItem.setCurr("001");
        listItem.setCurrList(currItemList);

        list.add(listItem);

        categoryItem.setCategory("1");
        categoryItem.setList(list);

        categoryList.add(categoryItem);

        privateReqBean.setBranchId("0020001242");
        privateReqBean.setUserId("555017484");
        privateReqBean.setUserName("卿某某");
        privateReqBean.setCategoryList(categoryList);

        bizContent.setPrivateReqBean(privateReqBean);
        bizContent.setPublicReqBean(publicReqBean);
        request.setBizContent(bizContent);

        try{
            BiomTransportCashCentrestorageSubmitcentreputinstorageResponseV1 response = client.execute(request);
            if(response.getReturnCode() == 0){
                System.out.println("ReturnCode:" + response.getReturnCode());
                System.out.println("response:" + JSON.toJSONString(response));
            }else{
                System.out.println("response:" + JSON.toJSONString(response));
                System.out.println("ReturnCode:" + response.getReturnCode());
                System.out.println("ReturnMsg:" + response.getReturnMsg());
            }
        }catch(IcbcApiException e){
            e.printStackTrace();
        }
    }
}
