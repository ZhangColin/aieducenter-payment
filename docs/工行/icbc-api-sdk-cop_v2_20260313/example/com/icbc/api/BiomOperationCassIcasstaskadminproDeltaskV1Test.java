package example.com.icbc.api;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcConstants;
import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.BiomOperationCassIcasstaskadminproDeltaskRequestV1;
import com.icbc.api.response.BiomOperationCassIcasstaskadminproDeltaskResponseV1;

public class BiomOperationCassIcasstaskadminproDeltaskV1Test {

    protected static final String MY_PRIVATE_KEY = "****";

    protected static final String APP_ID = "1234";

    protected static final String APIGW_PUBLIC_KEY = "****";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String) null, (String) null, (String) null, (String) null);

        BiomOperationCassIcasstaskadminproDeltaskRequestV1 request = new BiomOperationCassIcasstaskadminproDeltaskRequestV1();

        BiomOperationCassIcasstaskadminproDeltaskRequestV1.BiomOperationCassIcasstaskadminproDeltaskRequestV1Biz bizContent = new BiomOperationCassIcasstaskadminproDeltaskRequestV1.BiomOperationCassIcasstaskadminproDeltaskRequestV1Biz();

        BiomOperationCassIcasstaskadminproDeltaskRequestV1.PrivateReqBean privateReqBean = new BiomOperationCassIcasstaskadminproDeltaskRequestV1.PrivateReqBean();

        BiomOperationCassIcasstaskadminproDeltaskRequestV1.PublicReqBean publicReqBean = new BiomOperationCassIcasstaskadminproDeltaskRequestV1.PublicReqBean();

        publicReqBean.setChancode("702");
        publicReqBean.setOapp("F-BIOM");
        publicReqBean.setOtellerno("1242");
        publicReqBean.setZoneno("200");

        privateReqBean.setTaskId("11111");

        bizContent.setPrivateReqBean(privateReqBean);
        bizContent.setPublicReqBean(publicReqBean);
        request.setBizContent(bizContent);

        try {
            BiomOperationCassIcasstaskadminproDeltaskResponseV1 response = client.execute(request);
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
