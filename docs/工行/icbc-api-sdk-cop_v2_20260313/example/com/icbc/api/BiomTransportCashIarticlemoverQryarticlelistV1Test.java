package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.BiomTransportCashIarticlemoverQryarticlelistRequestV1;
import com.icbc.api.response.BiomTransportCashIarticlemoverQryarticlelistResponseV1;

import java.util.ArrayList;

public class BiomTransportCashIarticlemoverQryarticlelistV1Test {

    protected static final String MY_PRIVATE_KEY = "****";

    protected static final String APP_ID = "1234";

    protected static final String APIGW_PUBLIC_KEY = "****";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String) null, (String) null, (String) null, (String) null);

        BiomTransportCashIarticlemoverQryarticlelistRequestV1 request = new BiomTransportCashIarticlemoverQryarticlelistRequestV1();

        BiomTransportCashIarticlemoverQryarticlelistRequestV1.BiomTransportCashIarticlemoverQryarticlelistRequestV1Biz bizContent = new BiomTransportCashIarticlemoverQryarticlelistRequestV1.BiomTransportCashIarticlemoverQryarticlelistRequestV1Biz();

        BiomTransportCashIarticlemoverQryarticlelistRequestV1.PrivateReqBean privateReqBean = new BiomTransportCashIarticlemoverQryarticlelistRequestV1.PrivateReqBean();

        BiomTransportCashIarticlemoverQryarticlelistRequestV1.PublicReqBean publicReqBean = new BiomTransportCashIarticlemoverQryarticlelistRequestV1.PublicReqBean();

        BiomTransportCashIarticlemoverQryarticlelistRequestV1.listMap listMapPara = new BiomTransportCashIarticlemoverQryarticlelistRequestV1.listMap();

        publicReqBean.setChancode("702");
        publicReqBean.setOapp("F-BIOM");
        publicReqBean.setZoneno("200");
        publicReqBean.setBrno("2");
        publicReqBean.setOtellerno("002022");
        

        privateReqBean.setAddr(null);
        privateReqBean.setStruId("11");
        privateReqBean.setAddrType(null);
        privateReqBean.setPageNum(1);
        privateReqBean.setArticle(null);
        privateReqBean.setPageSize(10);
        privateReqBean.setArticleProp(null);
        privateReqBean.setStoreList(new ArrayList<BiomTransportCashIarticlemoverQryarticlelistRequestV1.listMap>(){
            {
                add(listMapPara);
            }
        });
        privateReqBean.setLocalFlag(null);
        privateReqBean.setStruGrade("5");
        privateReqBean.setUseStat(null);
        privateReqBean.setAllocBranchId(null);
        privateReqBean.setZoneno("200");

        listMapPara.setStoreId("11");

        bizContent.setPrivateReqBean(privateReqBean);
        bizContent.setPublicReqBean(publicReqBean);
        request.setBizContent(bizContent);

        try {
            BiomTransportCashIarticlemoverQryarticlelistResponseV1 response = client.execute(request);
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
