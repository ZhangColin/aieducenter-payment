package com.icbc.api;

import com.icbc.api.request.GyJrAcctQueryInterestRateRequestV1;
import com.icbc.api.request.GyJrAcctQueryInterestRateRequestV1.GyJrAcctQueryInterestRateRequestBizV1;
import com.icbc.api.response.GyJrAcctQueryInterestRateResponseV1;

public class GyJrAcctQueryInterestRateTest {

    protected static final String BASE_URL = "[需替换]工行API地址"; // https://xxxx/api/
    protected static final String APP_ID = "[需替换]工行APP编号";
    protected static final String PRI_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";
    protected static final String APIGW_PUBLIC_KEY = "[需替换]工行API网关公钥，请向工行联系获取";

    public static void main(String[] args) throws Exception {
        String serviceUri = BASE_URL + "mybank/enterprise/gyjr/applydiscus/V1";
        IcbcClient client = new DefaultIcbcClient(APP_ID, PRI_KEY, APIGW_PUBLIC_KEY);
        GyJrAcctQueryInterestRateRequestBizV1 bizContent =
                new GyJrAcctQueryInterestRateRequestBizV1();
        GyJrAcctQueryInterestRateRequestV1 request =
                new GyJrAcctQueryInterestRateRequestV1();
        // 请对照接口文档用bizContent.setXxx()方法对业务上送数据进行赋值
        bizContent.setProtocolCode("3333");
        bizContent.setAppId("2222");
        bizContent.setAccNo("1111");
        bizContent.setMgNo("1111");
        bizContent.setCurrType("001");
        bizContent.setBeginDate("2025-08-13");
        bizContent.setInitFlag("1");
        bizContent.setRowReq("");
        bizContent.setStatusFlag("1");
        bizContent.setOperFlag("1");
        bizContent.setVouhNo("");
        bizContent.setEndDate("2025-08-13");
        request.setServiceUrl(serviceUri);
        request.setBizContent(bizContent);
        GyJrAcctQueryInterestRateResponseV1 response = client.execute(request);
        if (response.isSuccess()) { // 业务成功处理
            System.out.println("success");
        } else { // 失败
            System.out.println("error");
        }
    }
}
