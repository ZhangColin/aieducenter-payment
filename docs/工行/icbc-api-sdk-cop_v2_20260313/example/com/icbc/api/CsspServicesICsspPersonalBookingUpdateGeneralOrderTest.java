package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.CsspServicesICsspPersonalBookingUpdateGeneralOrderRequestV1;
import com.icbc.api.response.CsspServicesICsspPersonalBookingUpdateGeneralOrderResponseV1;


public class CsspServicesICsspPersonalBookingUpdateGeneralOrderTest {

    protected static final String MY_PRIVATE_KEY = "****";

    protected static final String APP_ID = "1234";

    protected static final String APIGW_PUBLIC_KEY = "****";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        CsspServicesICsspPersonalBookingUpdateGeneralOrderRequestV1 request = new CsspServicesICsspPersonalBookingUpdateGeneralOrderRequestV1();
        request.setServiceUrl("https://ip:port/api/services/ICsspPersonalBooking/updateGeneralOrder/V1");
        CsspServicesICsspPersonalBookingUpdateGeneralOrderRequestV1.CsspServicesICsspPersonalBookingUpdateGeneralOrderResponseV1Biz bizContent = new CsspServicesICsspPersonalBookingUpdateGeneralOrderRequestV1.CsspServicesICsspPersonalBookingUpdateGeneralOrderResponseV1Biz();
        CsspServicesICsspPersonalBookingUpdateGeneralOrderResponseV1.ChanCommV10Bean chanCommV10Bean = new BiomServicesIBookSceneInfoGetBookInfoRequestV1.ChanCommV10Bean();
        CsspServicesICsspPersonalBookingUpdateGeneralOrderResponseV1.InfoCommV10Bean infoCommV10Bean = new BiomServicesIBookSceneInfoGetBookInfoRequestV1.InfoCommV10Bean();
        CsspServicesICsspPersonalBookingUpdateGeneralOrderResponseV1.TranCommV10Bean tranCommV10Bean = new BiomServicesIBookSceneInfoGetBookInfoRequestV1.TranCommV10Bean();
        CsspServicesICsspPersonalBookingUpdateGeneralOrderResponseV1.InPrivateBean inPrivateBean = new BiomServicesIBookSceneInfoGetBookInfoRequestV1.InPrivateBean();

        chanCommV10Bean.setOapp("F-CSSP");
        infoCommV10Bean.setZoneNo("200");
        tranCommV10Bean.setProgram("test");
        inPrivateBean.setOperationType("1");

        bizContent.setChanCommV10Bean(chanCommV10Bean);
        bizContent.setInfoCommV10Bean(infoCommV10Bean);
        bizContent.setTranCommV10Bean(tranCommV10Bean);
        bizContent.setInPrivateBean(inPrivateBean);
        request.setBizContent(bizContent);

        try {
            CsspServicesICsspPersonalBookingUpdateGeneralOrderResponseV1 response = client.execute(request);
            System.out.println(JSON.toJSONString(response));
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }
}
