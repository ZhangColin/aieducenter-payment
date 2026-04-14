package example.com.icbc.api;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcConstants;
import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.BiomOperationCassIcasstaskadminproInstaskRequestV1;
import com.icbc.api.response.BiomOperationCassIcasstaskadminproInstaskResponseV1;

public class BiomOperationCassIcasstaskadminproInstaskV1Test {

    protected static final String MY_PRIVATE_KEY = "****";

    protected static final String APP_ID = "1234";

    protected static final String APIGW_PUBLIC_KEY = "****";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String) null, (String) null, (String) null, (String) null);

        BiomOperationCassIcasstaskadminproInstaskRequestV1 request = new BiomOperationCassIcasstaskadminproInstaskRequestV1();

        BiomOperationCassIcasstaskadminproInstaskRequestV1.BiomOperationCassIcasstaskadminproInstaskRequestV1Biz bizContent = new BiomOperationCassIcasstaskadminproInstaskRequestV1.BiomOperationCassIcasstaskadminproInstaskRequestV1Biz();

        BiomOperationCassIcasstaskadminproInstaskRequestV1.PrivateReqBean privateReqBean = new BiomOperationCassIcasstaskadminproInstaskRequestV1.PrivateReqBean();

        BiomOperationCassIcasstaskadminproInstaskRequestV1.PublicReqBean publicReqBean = new BiomOperationCassIcasstaskadminproInstaskRequestV1.PublicReqBean();

        publicReqBean.setChancode("702");
        publicReqBean.setOapp("F-BIOM");
        publicReqBean.setOtellerno("1242");
        publicReqBean.setZoneno("200");

        privateReqBean.setChannel("1");
        privateReqBean.setOperUser("11");
        privateReqBean.setIsUpd("1");
        privateReqBean.setOperName("0002902");
        privateReqBean.setTaskId("11111111");
        privateReqBean.setLocSeq("220202");
        privateReqBean.setServeDate("2012-09-07");
        privateReqBean.setServerDate("2012-09-07");
        privateReqBean.setTaskType("05001");
        privateReqBean.setZoneno("200");

        bizContent.setPrivateReqBean(privateReqBean);
        bizContent.setPublicReqBean(publicReqBean);
        request.setBizContent(bizContent);

        try {
            BiomOperationCassIcasstaskadminproInstaskResponseV1 response = client.execute(request);
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
