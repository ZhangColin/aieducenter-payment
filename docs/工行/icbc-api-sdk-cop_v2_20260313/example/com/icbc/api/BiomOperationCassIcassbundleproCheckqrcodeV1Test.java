package example.com.icbc.api;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcConstants;
import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.BiomOperationCassIcassbundleproCheckqrcodeRequestV1;
import com.icbc.api.response.BiomOperationCassIcassbundleproCheckqrcodeResponseV1;

import java.util.ArrayList;

public class BiomOperationCassIcassbundleproCheckqrcodeV1Test {

    protected static final String MY_PRIVATE_KEY = "****";

    protected static final String APP_ID = "1234";

    protected static final String APIGW_PUBLIC_KEY = "****";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String) null, (String) null, (String) null, (String) null);

        BiomOperationCassIcassbundleproCheckqrcodeRequestV1 request = new BiomOperationCassIcassbundleproCheckqrcodeRequestV1();

        BiomOperationCassIcassbundleproCheckqrcodeRequestV1.BiomOperationCassIcassbundleproCheckqrcodeRequestV1Biz bizContent = new BiomOperationCassIcassbundleproCheckqrcodeRequestV1.BiomOperationCassIcassbundleproCheckqrcodeRequestV1Biz();

        BiomOperationCassIcassbundleproCheckqrcodeRequestV1.PrivateReqBean privateReqBean = new BiomOperationCassIcassbundleproCheckqrcodeRequestV1.PrivateReqBean();

        BiomOperationCassIcassbundleproCheckqrcodeRequestV1.PublicReqBean publicReqBean = new BiomOperationCassIcassbundleproCheckqrcodeRequestV1.PublicReqBean();

        publicReqBean.setChancode("702");
        publicReqBean.setOapp("F-BIOM");
        publicReqBean.setOtellerno("1242");
        publicReqBean.setZoneno("200");

        privateReqBean.setBundleId("555");

        bizContent.setPrivateReqBean(privateReqBean);
        bizContent.setPublicReqBean(publicReqBean);
        request.setBizContent(bizContent);

        try {
            BiomOperationCassIcassbundleproCheckqrcodeResponseV1 response = client.execute(request);
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
