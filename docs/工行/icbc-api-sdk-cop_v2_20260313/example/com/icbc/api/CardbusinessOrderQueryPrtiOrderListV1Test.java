package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.CardbusinessOrderQueryPrtiOrderListRequestV1;
import com.icbc.api.response.CardbusinessOrderQueryPrtiOrderListResponseV1;

public class CardbusinessOrderQueryPrtiOrderListV1Test {

    protected static final String MY_PRIVATE_KEY = "****";

    protected static final String APP_ID = "1234";

    protected static final String APIGW_PUBLIC_KEY = "****";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String) null, (String) null, (String) null, (String) null);

        CardbusinessOrderQueryPrtiOrderListRequestV1 request = new CardbusinessOrderQueryPrtiOrderListRequestV1();

        request.setServiceUrl("https://ip:port/api/cardbusiness/order/queryprtiorderlist/V1");

        CardbusinessOrderQueryPrtiOrderListRequestV1.CardbusinessOrderQueryPrtiOrderListRequestV1Biz bizContent = new CardbusinessOrderQueryPrtiOrderListRequestV1.CardbusinessOrderQueryPrtiOrderListRequestV1Biz();

        bizContent.setPrtiId("020001021189");
        bizContent.setBeginDate("2023-12-01");
        bizContent.setEndDate("2023-12-10");
        bizContent.setPnMethod("1");
        bizContent.setRecordQueryNo("5");

        request.setBizContent(bizContent);

        try {
            CardbusinessOrderQueryPrtiOrderListResponseV1 response = client.execute(request);
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
