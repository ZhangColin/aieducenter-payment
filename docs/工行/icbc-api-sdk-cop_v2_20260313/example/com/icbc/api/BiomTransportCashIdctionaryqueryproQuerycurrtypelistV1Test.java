package example.com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.BiomTransportCashIdctionaryqueryproQuerycurrtypelistRequestV1;
import com.icbc.api.response.BiomTransportCashIdctionaryqueryproQuerycurrtypelistResponseV1;

public class BiomTransportCashIdctionaryqueryproQuerycurrtypelistV1Test {

    protected static final String MY_PRIVATE_KEY = "****";

    protected static final String APP_ID = "1234";

    protected static final String APIGW_PUBLIC_KEY = "****";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String) null, (String) null, (String) null, (String) null);

        BiomTransportCashIdctionaryqueryproQuerycurrtypelistRequestV1 request = new BiomTransportCashIdctionaryqueryproQuerycurrtypelistRequestV1();

        BiomTransportCashIdctionaryqueryproQuerycurrtypelistRequestV1.BiomTransportCashIdctionaryqueryproQuerycurrtypelistRequestV1Biz bizContent = new BiomTransportCashIdctionaryqueryproQuerycurrtypelistRequestV1.BiomTransportCashIdctionaryqueryproQuerycurrtypelistRequestV1Biz();

        BiomTransportCashIdctionaryqueryproQuerycurrtypelistRequestV1.PrivateReqBean privateReqBean = new BiomTransportCashIdctionaryqueryproQuerycurrtypelistRequestV1.PrivateReqBean();

        BiomTransportCashIdctionaryqueryproQuerycurrtypelistRequestV1.PublicReqBean publicReqBean = new BiomTransportCashIdctionaryqueryproQuerycurrtypelistRequestV1.PublicReqBean();

        publicReqBean.setChancode("702");
        publicReqBean.setOapp("F-BIOM");
        publicReqBean.setOtellerno("0020001242");
        publicReqBean.setZoneno("200");

        privateReqBean.setBrno("998");
        privateReqBean.setZoneno("200");

        bizContent.setPrivateReqBean(privateReqBean);
        bizContent.setPublicReqBean(publicReqBean);
        request.setBizContent(bizContent);

        try {
            BiomTransportCashIdctionaryqueryproQuerycurrtypelistResponseV1 response = client.execute(request);
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
