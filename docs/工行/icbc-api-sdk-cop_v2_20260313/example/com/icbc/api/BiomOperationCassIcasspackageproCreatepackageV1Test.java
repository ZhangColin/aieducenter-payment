package example.com.icbc.api;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcConstants;
import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.BiomOperationCassIcasspackageproCreatepackageRequestV1;
import com.icbc.api.response.BiomOperationCassIcasspackageproCreatepackageResponseV1;

public class BiomOperationCassIcasspackageproCreatepackageV1Test {

    protected static final String MY_PRIVATE_KEY = "****";

    protected static final String APP_ID = "1234";

    protected static final String APIGW_PUBLIC_KEY = "****";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String) null, (String) null, (String) null, (String) null);

        BiomOperationCassIcasspackageproCreatepackageRequestV1 request = new BiomOperationCassIcasspackageproCreatepackageRequestV1();

        BiomOperationCassIcasspackageproCreatepackageRequestV1.BiomOperationCassIcasspackageproCreatepackageRequestV1Biz bizContent = new BiomOperationCassIcasspackageproCreatepackageRequestV1.BiomOperationCassIcasspackageproCreatepackageRequestV1Biz();

        BiomOperationCassIcasspackageproCreatepackageRequestV1.PrivateReqBean privateReqBean = new BiomOperationCassIcasspackageproCreatepackageRequestV1.PrivateReqBean();

        BiomOperationCassIcasspackageproCreatepackageRequestV1.PublicReqBean publicReqBean = new BiomOperationCassIcasspackageproCreatepackageRequestV1.PublicReqBean();

        publicReqBean.setChancode("702");
        publicReqBean.setOapp("F-BIOM");
        publicReqBean.setOtellerno("1242");
        publicReqBean.setZoneno("200");

        privateReqBean.setTaskId("35464712132536");
        privateReqBean.setBrno("2");
        privateReqBean.setLocSeq("34242");
        privateReqBean.setUserName("");
        privateReqBean.setAccount("2222222222-00");
        privateReqBean.setZoneno("200");
        privateReqBean.setUserId("03909032");
        privateReqBean.setPackType("1");
        privateReqBean.setPackSource("");
        privateReqBean.setIsEdit("1");
        privateReqBean.setBranchId("11111111");
        privateReqBean.setPackAmount("1111111.00");
        privateReqBean.setPackId("11111111");

        bizContent.setPrivateReqBean(privateReqBean);
        bizContent.setPublicReqBean(publicReqBean);
        request.setBizContent(bizContent);

        try {
            BiomOperationCassIcasspackageproCreatepackageResponseV1 response = client.execute(request);
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
