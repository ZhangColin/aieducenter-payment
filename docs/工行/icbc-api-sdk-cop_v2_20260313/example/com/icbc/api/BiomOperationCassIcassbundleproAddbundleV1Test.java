package example.com.icbc.api;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcConstants;
import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.BiomOperationCassIcassbundleproAddbundleRequestV1;
import com.icbc.api.response.BiomOperationCassIcassbundleproAddbundleResponseV1;

import java.util.ArrayList;

public class BiomOperationCassIcassbundleproAddbundleV1Test {

    protected static final String MY_PRIVATE_KEY = "****";

    protected static final String APP_ID = "1234";

    protected static final String APIGW_PUBLIC_KEY = "****";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String) null, (String) null, (String) null, (String) null);

        BiomOperationCassIcassbundleproAddbundleRequestV1 request = new BiomOperationCassIcassbundleproAddbundleRequestV1();

        BiomOperationCassIcassbundleproAddbundleRequestV1.BiomOperationCassIcassbundleproAddbundleRequestV1Biz bizContent = new BiomOperationCassIcassbundleproAddbundleRequestV1.BiomOperationCassIcassbundleproAddbundleRequestV1Biz();

        BiomOperationCassIcassbundleproAddbundleRequestV1.PrivateReqBean privateReqBean = new BiomOperationCassIcassbundleproAddbundleRequestV1.PrivateReqBean();

        BiomOperationCassIcassbundleproAddbundleRequestV1.PublicReqBean publicReqBean = new BiomOperationCassIcassbundleproAddbundleRequestV1.PublicReqBean();

        publicReqBean.setChancode("702");
        publicReqBean.setOapp("F-BIOM");
        publicReqBean.setOtellerno("1242");
        publicReqBean.setZoneno("200");

        privateReqBean.setAccount("11111111");
        privateReqBean.setAmount("100000");
        privateReqBean.setLocSeq("25504");
        privateReqBean.setBranchId("11111111");
        privateReqBean.setBundleId("111111");
        privateReqBean.setOperName("\u4F60\u597D");
        privateReqBean.setCashBoxSource("\u4F60\u597D");
        privateReqBean.setIsUpd("0");
        privateReqBean.setPhoneNum("1111111111");

        bizContent.setPrivateReqBean(privateReqBean);
        bizContent.setPublicReqBean(publicReqBean);
        request.setBizContent(bizContent);

        try {
            BiomOperationCassIcassbundleproAddbundleResponseV1 response = client.execute(request);
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
