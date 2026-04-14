package example.com.icbc.api;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcConstants;
import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.BiomTransportCashCenterinoutstoreInstorenumqryRequestV1;
import com.icbc.api.response.BiomTransportCashCenterinoutstoreInstorenumqryResponseV1;

import java.util.ArrayList;

public class BiomTransportCashCenterinoutstoreInstorenumqryV1Test {

    protected static final String MY_PRIVATE_KEY = "****";

    protected static final String APP_ID = "1234";

    protected static final String APIGW_PUBLIC_KEY = "****";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String) null, (String) null, (String) null, (String) null);

        BiomTransportCashCenterinoutstoreInstorenumqryRequestV1 request = new BiomTransportCashCenterinoutstoreInstorenumqryRequestV1();

        BiomTransportCashCenterinoutstoreInstorenumqryRequestV1.BiomTransportCashCenterinoutstoreInstorenumqryRequestV1Biz bizContent = new BiomTransportCashCenterinoutstoreInstorenumqryRequestV1.BiomTransportCashCenterinoutstoreInstorenumqryRequestV1Biz();

        BiomTransportCashCenterinoutstoreInstorenumqryRequestV1.PrivateReqBean privateReqBean = new BiomTransportCashCenterinoutstoreInstorenumqryRequestV1.PrivateReqBean();

        BiomTransportCashCenterinoutstoreInstorenumqryRequestV1.PublicReqBean publicReqBean = new BiomTransportCashCenterinoutstoreInstorenumqryRequestV1.PublicReqBean();

        publicReqBean.setChancode("702");
        publicReqBean.setOapp("F-BIOM");
        publicReqBean.setOtellerno("1242");
        publicReqBean.setZoneno("200");

        privateReqBean.setBranchId("0200001242");
        privateReqBean.setZoneno("200");
        privateReqBean.setEndDate("2020-09-09");
        privateReqBean.setProperties(new ArrayList<>());
        privateReqBean.setInType("1");
        privateReqBean.setRouteNo("73829");
        privateReqBean.setStartDate("2020-09-09");

        bizContent.setPrivateReqBean(privateReqBean);
        bizContent.setPublicReqBean(publicReqBean);
        request.setBizContent(bizContent);

        try {
            BiomTransportCashCenterinoutstoreInstorenumqryResponseV1 response = client.execute(request);
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
