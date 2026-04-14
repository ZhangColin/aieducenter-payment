package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.BiomServicesIBookSceneInfoGetBookInfoRequestV1;
import com.icbc.api.request.BiomServicesIBookSceneInfoGetBookInfoRequestV1;
import com.icbc.api.response.BiomServicesIBookSceneInfoGetBookInfoResponseV1;
import com.icbc.api.response.BiomServicesICsrcToFhProfhGetPckgResponseV1;

public class BiomServicesIBookSceneInfoGetBookInfoTest {

    protected static final String MY_PRIVATE_KEY = "****";

    protected static final String APP_ID = "1234";

    protected static final String APIGW_PUBLIC_KEY = "****";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        BiomServicesIBookSceneInfoGetBookInfoRequestV1 request = new BiomServicesIBookSceneInfoGetBookInfoRequestV1();
        request.setServiceUrl("https://ip:port/api/services/IBookSceneInfo/getBookInfo/V1");
        BiomServicesIBookSceneInfoGetBookInfoRequestV1.BiomServicesIBookSceneInfoGetBookInfoResponseV1Biz bizContent = new BiomServicesIBookSceneInfoGetBookInfoRequestV1.BiomServicesIBookSceneInfoGetBookInfoResponseV1Biz();
        BiomServicesIBookSceneInfoGetBookInfoRequestV1.ApprootReqBean approotReqBean = new BiomServicesIBookSceneInfoGetBookInfoRequestV1.ApprootReqBean();
        BiomServicesIBookSceneInfoGetBookInfoRequestV1.PublicReqBean publicReqBean = new BiomServicesIBookSceneInfoGetBookInfoRequestV1.PublicReqBean();
        BiomServicesIBookSceneInfoGetBookInfoRequestV1.PrivateReqBean privateReqBean = new BiomServicesIBookSceneInfoGetBookInfoRequestV1.PrivateReqBean();

        publicReqBean.setOapp("F-BIOM");
        privateReqBean.setZoneno("200");
        privateReqBean.setBrno("998");

        approotReqBean.setPublicReqBean(publicReqBean);
        approotReqBean.setPrivateReqBean(privateReqBean);
        bizContent.setApprootReqBean(approotReqBean);
        request.setBizContent(bizContent);

        try {
            BiomServicesIBookSceneInfoGetBookInfoResponseV1 response = client.execute(request);
            System.out.println(JSON.toJSONString(response));
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }

    }
}
