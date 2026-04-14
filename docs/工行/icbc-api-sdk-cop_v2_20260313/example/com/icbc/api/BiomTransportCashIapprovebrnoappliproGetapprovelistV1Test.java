package example.com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.BiomTransportCashIapprovebrnoappliproGetapprovelistRequestV1;
import com.icbc.api.response.BiomTransportCashIapprovebrnoappliproGetapprovelistResponseV1;

public class BiomTransportCashIapprovebrnoappliproGetapprovelistV1Test {


    protected static final String MY_PRIVATE_KEY = "****";

    protected static final String APP_ID = "1234";

    protected static final String APIGW_PUBLIC_KEY = "****";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String) null, (String) null, (String) null, (String) null);

        BiomTransportCashIapprovebrnoappliproGetapprovelistRequestV1 request = new BiomTransportCashIapprovebrnoappliproGetapprovelistRequestV1();

        BiomTransportCashIapprovebrnoappliproGetapprovelistRequestV1.BiomTransportCashIapprovebrnoappliproGetapprovelistRequestV1Biz bizContent = new BiomTransportCashIapprovebrnoappliproGetapprovelistRequestV1.BiomTransportCashIapprovebrnoappliproGetapprovelistRequestV1Biz();

        BiomTransportCashIapprovebrnoappliproGetapprovelistRequestV1.PrivateReqBean privateReqBean = new BiomTransportCashIapprovebrnoappliproGetapprovelistRequestV1.PrivateReqBean();

        BiomTransportCashIapprovebrnoappliproGetapprovelistRequestV1.PublicReqBean publicReqBean = new BiomTransportCashIapprovebrnoappliproGetapprovelistRequestV1.PublicReqBean();


        publicReqBean.setZoneno("200");

        privateReqBean.setAllocDate("2023-10-10");
        privateReqBean.setApproveType("1");
        privateReqBean.setBeginNum(1);
        privateReqBean.setCentBrno("2");
        privateReqBean.setCentZoneNo("2");
        privateReqBean.setCurrOption("2");
        privateReqBean.setFetchNum(5);
        privateReqBean.setReqEndDate("2023-10-10");
        privateReqBean.setReqId("2222222222");
        privateReqBean.setReqStartDate("2023-10-10");
        privateReqBean.setReqType("1");
        privateReqBean.setStateOption("1");
        privateReqBean.setZoneno("1");


        bizContent.setPrivateReqBean(privateReqBean);
        bizContent.setPublicReqBean(publicReqBean);
        request.setBizContent(bizContent);

        try {
            BiomTransportCashIapprovebrnoappliproGetapprovelistResponseV1 response = client.execute(request);
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
