package example.com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.BiomTransportCashItaskcashmoverConfirmcashoutRequestV1;
import com.icbc.api.response.BiomTransportCashItaskcashmoverConfirmcashoutResponseV1;

public class BiomTransportCashItaskcashmoverConfirmcashoutV1Test {

    protected static final String MY_PRIVATE_KEY = "****";

    protected static final String APP_ID = "1234";

    protected static final String APIGW_PUBLIC_KEY = "****";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String) null, (String) null, (String) null, (String) null);

        BiomTransportCashItaskcashmoverConfirmcashoutRequestV1 request = new BiomTransportCashItaskcashmoverConfirmcashoutRequestV1();

        BiomTransportCashItaskcashmoverConfirmcashoutRequestV1.BiomTransportCashItaskcashmoverConfirmcashoutRequestV1Biz bizContent = new BiomTransportCashItaskcashmoverConfirmcashoutRequestV1.BiomTransportCashItaskcashmoverConfirmcashoutRequestV1Biz();

        BiomTransportCashItaskcashmoverConfirmcashoutRequestV1.PrivateReqBean privateReqBean = new BiomTransportCashItaskcashmoverConfirmcashoutRequestV1.PrivateReqBean();

        BiomTransportCashItaskcashmoverConfirmcashoutRequestV1.PublicReqBean publicReqBean = new BiomTransportCashItaskcashmoverConfirmcashoutRequestV1.PublicReqBean();

        publicReqBean.setChancode("702");
        publicReqBean.setOapp("F-BIOM");
        publicReqBean.setOtellerno("0020001242");
        publicReqBean.setZoneno("200");


        privateReqBean.setBranchId("0000778");
        privateReqBean.setBrno("2");
        privateReqBean.setHostOpertype(3);
        privateReqBean.setInTelNo("00000000032");
        privateReqBean.setOtellerno("00000000032");
        privateReqBean.setOutTelNo("00000000032");
        privateReqBean.setStatus(1);
        privateReqBean.setTaskId("22222222222222222222");
        privateReqBean.setTaskType("3003");
        privateReqBean.setTellerName("leo");
        privateReqBean.setZoneno("200");




        bizContent.setPrivateReqBean(privateReqBean);
        bizContent.setPublicReqBean(publicReqBean);
        request.setBizContent(bizContent);

        try {
            BiomTransportCashItaskcashmoverConfirmcashoutResponseV1 response = client.execute(request);
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
