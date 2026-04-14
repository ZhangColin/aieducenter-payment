package com.icbc.api;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcConstants;
import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.BiomTransportCashItaskcashmoverQrycashinoutconRequestV1;
import com.icbc.api.response.BiomTransportCashItaskcashmoverQrycashinoutconResponseV1;

public class BiomTransportCashItaskcashmoverQrycashinoutconV1Test {

    protected static final String MY_PRIVATE_KEY = "****";

    protected static final String APP_ID = "1234";

    protected static final String APIGW_PUBLIC_KEY = "****";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String) null, (String) null, (String) null, (String) null);

        BiomTransportCashItaskcashmoverQrycashinoutconRequestV1 request = new BiomTransportCashItaskcashmoverQrycashinoutconRequestV1();

        BiomTransportCashItaskcashmoverQrycashinoutconRequestV1.BiomTransportCashItaskcashmoverQrycashinoutconRequestV1Biz bizContent = new BiomTransportCashItaskcashmoverQrycashinoutconRequestV1.BiomTransportCashItaskcashmoverQrycashinoutconRequestV1Biz();

        BiomTransportCashItaskcashmoverQrycashinoutconRequestV1.PrivateReqBean privateReqBean = new BiomTransportCashItaskcashmoverQrycashinoutconRequestV1.PrivateReqBean();

        BiomTransportCashItaskcashmoverQrycashinoutconRequestV1.PublicReqBean publicReqBean = new BiomTransportCashItaskcashmoverQrycashinoutconRequestV1.PublicReqBean();

        publicReqBean.setChancode("702");
        publicReqBean.setOapp("F-BIOM");
        publicReqBean.setOtellerno("0020001242");
        publicReqBean.setZoneno("200");

        privateReqBean.setZoneno("200");
        privateReqBean.setOtellerno("465475424");
        privateReqBean.setBrno("998");
        privateReqBean.setBranchId("0020001242");
        privateReqBean.setTellerName("姓名");
        privateReqBean.setTaskType("03004");
        privateReqBean.setTaskId("00200009982709304896");
        privateReqBean.setStatus(2);
        privateReqBean.setCurrType("012");
        privateReqBean.setStatus(0);


        bizContent.setPrivateReqBean(privateReqBean);
        bizContent.setPublicReqBean(publicReqBean);
        request.setBizContent(bizContent);

        try {
            BiomTransportCashItaskcashmoverQrycashinoutconResponseV1 response = client.execute(request);
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
