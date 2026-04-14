package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.CardbusinessOrderQueryOrderExaRequestV1;
import com.icbc.api.response.CardbusinessOrderQueryOrderExaResponseV1;

public class CardbusinessOrderQueryOrderExaV1Test {

    protected static final String MY_PRIVATE_KEY = "****";

    protected static final String APP_ID = "1234";

    protected static final String APIGW_PUBLIC_KEY = "****";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String) null, (String) null, (String) null, (String) null);

        CardbusinessOrderQueryOrderExaRequestV1 request = new CardbusinessOrderQueryOrderExaRequestV1();

        request.setServiceUrl("https://ip:port/api/cardbusiness/order/queryorderexa/V1");

        CardbusinessOrderQueryOrderExaRequestV1.CardbusinessOrderQueryOrderExaRequestV1Biz bizContent = new CardbusinessOrderQueryOrderExaRequestV1.CardbusinessOrderQueryOrderExaRequestV1Biz();

        bizContent.setOrderId("020008990264000522306089019543");
        bizContent.setTrnscSerNo("020008990264000522306089019543");
        bizContent.setSubTrnscSerNo("1");

        request.setBizContent(bizContent);

        try {
            CardbusinessOrderQueryOrderExaResponseV1 response = client.execute(request);
            if (response.getSaesReturnCode().equals("0")) {
                System.out.println("ReturnCode:" + response.getSaesReturnCode());
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
