package example.com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.BiomServicesIuserroleproQryuserdataRequestV1;
import com.icbc.api.response.BiomServicesIuserroleproQryuserdataResponseV1;

public class BiomServicesIuserroleproQryuserdataV1Test {
    protected static final String MY_PRIVATE_KEY = "****";

    protected static final String APP_ID = "1234";

    protected static final String APIGW_PUBLIC_KEY = "****";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String) null, (String) null, (String) null, (String) null);

        BiomServicesIuserroleproQryuserdataRequestV1 request = new BiomServicesIuserroleproQryuserdataRequestV1();

        BiomServicesIuserroleproQryuserdataRequestV1.BiomServicesIuserroleproQryuserdataRequestV1Biz bizContent = new BiomServicesIuserroleproQryuserdataRequestV1.BiomServicesIuserroleproQryuserdataRequestV1Biz();

        BiomServicesIuserroleproQryuserdataRequestV1.PrivateReqBean privateReqBean = new BiomServicesIuserroleproQryuserdataRequestV1.PrivateReqBean();

        BiomServicesIuserroleproQryuserdataRequestV1.PublicReqBean publicReqBean = new BiomServicesIuserroleproQryuserdataRequestV1.PublicReqBean();


        publicReqBean.setZoneno("200");

        privateReqBean.setUserId("00002013");

        bizContent.setPrivateReqBean(privateReqBean);
        bizContent.setPublicReqBean(publicReqBean);
        request.setBizContent(bizContent);

        try {
            BiomServicesIuserroleproQryuserdataResponseV1 response = client.execute(request);
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
