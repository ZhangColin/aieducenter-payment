package example.com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.BiomTransportCashIbrnopayGetcurrtypeRequestV1;
import com.icbc.api.response.BiomTransportCashIbrnopayGetcurrtypeResponseV1;

public class BiomTransportCashIbrnopayGetcurrtypeV1Test {

    protected static final String MY_PRIVATE_KEY = "****";

    protected static final String APP_ID = "1234";

    protected static final String APIGW_PUBLIC_KEY = "****";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String) null, (String) null, (String) null, (String) null);

        BiomTransportCashIbrnopayGetcurrtypeRequestV1 request = new BiomTransportCashIbrnopayGetcurrtypeRequestV1();

        BiomTransportCashIbrnopayGetcurrtypeRequestV1.BiomTransportCashIbrnopayGetcurrtypeRequestV1Biz bizContent = new BiomTransportCashIbrnopayGetcurrtypeRequestV1.BiomTransportCashIbrnopayGetcurrtypeRequestV1Biz();

        BiomTransportCashIbrnopayGetcurrtypeRequestV1.PrivateReqBean privateReqBean = new BiomTransportCashIbrnopayGetcurrtypeRequestV1.PrivateReqBean();

        BiomTransportCashIbrnopayGetcurrtypeRequestV1.PublicReqBean publicReqBean = new BiomTransportCashIbrnopayGetcurrtypeRequestV1.PublicReqBean();


        publicReqBean.setZoneno("200");

        privateReqBean.setALLOC_DATE("2011-01-01");
        privateReqBean.setBeginNum(1);
        privateReqBean.setBrno("2");
        privateReqBean.setCurrOption("001");
        privateReqBean.setFetchNum(2);
        privateReqBean.setREQ_ID("1222222");
        privateReqBean.setReqType("1");
        privateReqBean.setSTATUS("2");
        privateReqBean.setZoneNo("200");

        bizContent.setPrivateReqBean(privateReqBean);
        bizContent.setPublicReqBean(publicReqBean);
        request.setBizContent(bizContent);

        try {
            BiomTransportCashIbrnopayGetcurrtypeResponseV1 response = client.execute(request);
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
