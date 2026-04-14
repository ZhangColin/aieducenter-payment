package example.com.icbc.api;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcConstants;
import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.BiomOperationCassIcasstaskadminproDeltaskpackRequestV1;
import com.icbc.api.response.BiomOperationCassIcasstaskadminproDeltaskpackResponseV1;

public class BiomOperationCassIcasstaskadminproDeltaskpackV1Test {

    protected static final String MY_PRIVATE_KEY = "****";

    protected static final String APP_ID = "1234";

    protected static final String APIGW_PUBLIC_KEY = "****";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String) null, (String) null, (String) null, (String) null);

        BiomOperationCassIcasstaskadminproDeltaskpackRequestV1 request = new BiomOperationCassIcasstaskadminproDeltaskpackRequestV1();

        BiomOperationCassIcasstaskadminproDeltaskpackRequestV1.BiomOperationCassIcasstaskadminproDeltaskpackRequestV1Biz bizContent = new BiomOperationCassIcasstaskadminproDeltaskpackRequestV1.BiomOperationCassIcasstaskadminproDeltaskpackRequestV1Biz();

        BiomOperationCassIcasstaskadminproDeltaskpackRequestV1.PrivateReqBean privateReqBean = new BiomOperationCassIcasstaskadminproDeltaskpackRequestV1.PrivateReqBean();

        BiomOperationCassIcasstaskadminproDeltaskpackRequestV1.PublicReqBean publicReqBean = new BiomOperationCassIcasstaskadminproDeltaskpackRequestV1.PublicReqBean();

        publicReqBean.setChancode("702");
        publicReqBean.setOapp("F-BIOM");
        publicReqBean.setOtellerno("1242");
        publicReqBean.setZoneno("200");

        privateReqBean.setTaskId("11111");
        privateReqBean.setOperUser("11111");
        privateReqBean.setPackId("1233");

        bizContent.setPrivateReqBean(privateReqBean);
        bizContent.setPublicReqBean(publicReqBean);
        request.setBizContent(bizContent);

        try {
            BiomOperationCassIcasstaskadminproDeltaskpackResponseV1 response = client.execute(request);
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
