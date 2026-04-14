package example.com.icbc.api;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcConstants;
import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.BiomServiceIcashbundlemngproAddcashbundleRequestV1;
import com.icbc.api.response.BiomServiceIcashbundlemngproAddcashbundleResponseV1;

public class BiomServiceIcashbundlemngproAddcashbundleV1Test {

    protected static final String MY_PRIVATE_KEY = "****";

    protected static final String APP_ID = "1234";

    protected static final String APIGW_PUBLIC_KEY = "****";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String) null, (String) null, (String) null, (String) null);

        BiomServiceIcashbundlemngproAddcashbundleRequestV1 request = new BiomServiceIcashbundlemngproAddcashbundleRequestV1();

        BiomServiceIcashbundlemngproAddcashbundleRequestV1.BiomServiceIcashbundlemngproAddcashbundleRequestV1Biz bizContent = new BiomServiceIcashbundlemngproAddcashbundleRequestV1.BiomServiceIcashbundlemngproAddcashbundleRequestV1Biz();

        BiomServiceIcashbundlemngproAddcashbundleRequestV1.PrivateReqBean privateReqBean = new BiomServiceIcashbundlemngproAddcashbundleRequestV1.PrivateReqBean();

        BiomServiceIcashbundlemngproAddcashbundleRequestV1.PublicReqBean publicReqBean = new BiomServiceIcashbundlemngproAddcashbundleRequestV1.PublicReqBean();

        publicReqBean.setChancode("2");
        publicReqBean.setOapp("1");
        publicReqBean.setOtellerno("200");

        privateReqBean.setPiece("1");
        privateReqBean.setQrcode("1234567890125");
        privateReqBean.setType("1");
        privateReqBean.setCurrency("001");
        privateReqBean.setDenomination("50");
        privateReqBean.setConditionType("1");
        privateReqBean.setZoneno("200");
        privateReqBean.setBrno("2");
        privateReqBean.setMacno("111111111112");
        privateReqBean.setGeneDatetime("2025-01-01 10:00:00");

        bizContent.setPrivateReqBean(privateReqBean);
        bizContent.setPublicReqBean(publicReqBean);
        request.setBizContent(bizContent);

        try {
            BiomServiceIcashbundlemngproAddcashbundleResponseV1 response = client.execute(request);
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