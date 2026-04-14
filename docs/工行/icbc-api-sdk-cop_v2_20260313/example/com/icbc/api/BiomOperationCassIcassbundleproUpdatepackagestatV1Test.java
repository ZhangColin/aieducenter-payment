package example.com.icbc.api;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcConstants;
import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.BiomOperationCassIcassbundleproUpdatepackagestatRequestV1;
import com.icbc.api.response.BiomOperationCassIcassbundleproUpdatepackagestatResponseV1;

import java.util.ArrayList;

public class BiomOperationCassIcassbundleproUpdatepackagestatV1Test {

    protected static final String MY_PRIVATE_KEY = "****";

    protected static final String APP_ID = "1234";

    protected static final String APIGW_PUBLIC_KEY = "****";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String) null, (String) null, (String) null, (String) null);

        BiomOperationCassIcassbundleproUpdatepackagestatRequestV1 request = new BiomOperationCassIcassbundleproUpdatepackagestatRequestV1();

        BiomOperationCassIcassbundleproUpdatepackagestatRequestV1.BiomOperationCassIcassbundleproUpdatepackagestatRequestV1Biz bizContent = new BiomOperationCassIcassbundleproUpdatepackagestatRequestV1.BiomOperationCassIcassbundleproUpdatepackagestatRequestV1Biz();

        BiomOperationCassIcassbundleproUpdatepackagestatRequestV1.PrivateReqBean privateReqBean = new BiomOperationCassIcassbundleproUpdatepackagestatRequestV1.PrivateReqBean();

        BiomOperationCassIcassbundleproUpdatepackagestatRequestV1.PublicReqBean publicReqBean = new BiomOperationCassIcassbundleproUpdatepackagestatRequestV1.PublicReqBean();

        publicReqBean.setChancode("702");
        publicReqBean.setOapp("F-BIOM");
        publicReqBean.setOtellerno("1242");
        publicReqBean.setZoneno("200");

        privateReqBean.setBrno("998");
        privateReqBean.setTaskId("255042027040210089");
        privateReqBean.setLocSeq("25504");
        privateReqBean.setEscortPern1("45042000000000000000");
        privateReqBean.setUserName("%E4%BA%8E%E5%A7%90");
        privateReqBean.setStruId("0020001242");
        privateReqBean.setEscortPern2("370784000000198617");
        privateReqBean.setEscortPernName1("%E5%A4%A7%E5%A4%A7");
        privateReqBean.setEscortPernName2("%E6%88%91%E5%B0%B1%E7%9C%8B%E9%AB%98%E6%96%AF");
        privateReqBean.setIsPhone("1");
        privateReqBean.setPackIds(new ArrayList<>());
        privateReqBean.setRouteName("%E4%B8%8A%E9%97%A8%E6%94%B6%E6%AC%BE%E7%BA%BF%E8%B7%AF");
        privateReqBean.setRouteNo("2");
        privateReqBean.setUserNo("13010000000000020087");

        bizContent.setPrivateReqBean(privateReqBean);
        bizContent.setPublicReqBean(publicReqBean);
        request.setBizContent(bizContent);

        try {
            BiomOperationCassIcassbundleproUpdatepackagestatResponseV1 response = client.execute(request);
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
