package example.com.icbc.api;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcConstants;
import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.BiomItmCurrencyItmmanageserviceAddtransactionlogRequestV1;
import com.icbc.api.response.BiomItmCurrencyItmmanageserviceAddtransactionlogResponseV1;


public class BiomItmCurrencyItmmanageserviceAddtransactionlogV1Test {

    protected static final String MY_PRIVATE_KEY = "****";

    protected static final String APP_ID = "1234";

    protected static final String APIGW_PUBLIC_KEY = "****";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String) null, (String) null, (String) null, (String) null);

        BiomItmCurrencyItmmanageserviceAddtransactionlogRequestV1 request = new BiomItmCurrencyItmmanageserviceAddtransactionlogRequestV1();

        BiomItmCurrencyItmmanageserviceAddtransactionlogRequestV1.BiomItmCurrencyItmmanageserviceAddtransactionlogRequestV1Biz bizContent = new BiomItmCurrencyItmmanageserviceAddtransactionlogRequestV1.BiomItmCurrencyItmmanageserviceAddtransactionlogRequestV1Biz();

        BiomItmCurrencyItmmanageserviceAddtransactionlogRequestV1.PrivateReqBean privateReqBean = new BiomItmCurrencyItmmanageserviceAddtransactionlogRequestV1.PrivateReqBean();

        BiomItmCurrencyItmmanageserviceAddtransactionlogRequestV1.PublicReqBean publicReqBean = new BiomItmCurrencyItmmanageserviceAddtransactionlogRequestV1.PublicReqBean();

        publicReqBean.setDevId("000000012345678");
        publicReqBean.setDevType("0");
        publicReqBean.setMsgType("01");
        publicReqBean.setZoneNo("00200");

        privateReqBean.setBusinessId("101");
        privateReqBean.setCode("456");
        privateReqBean.setBusiType("01");
        privateReqBean.setLogId("000000012345678020251124123422");
        privateReqBean.setLogContent("2025-11-24 12:34:22 01 000000012345678 123 456 6228480034567890 01 �ֽ���ɹ�      ");
        privateReqBean.setLogNums("01");
        privateReqBean.setEndWorkTime("2025-11-24 12:34:22");
        privateReqBean.setMediumId("6228480034567890");
        privateReqBean.setOrderId("123");
        privateReqBean.setStartWorkTime("2025-11-24 12:34:22");
        privateReqBean.setSerialNo("789");
        privateReqBean.setWorkDate("2025-11-23");
        privateReqBean.setWorkTime("10:50:51");

        bizContent.setPrivateReqBean(privateReqBean);
        bizContent.setPublicReqBean(publicReqBean);
        request.setBizContent(bizContent);

        try {
            BiomItmCurrencyItmmanageserviceAddtransactionlogResponseV1 response = client.execute(request);
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
