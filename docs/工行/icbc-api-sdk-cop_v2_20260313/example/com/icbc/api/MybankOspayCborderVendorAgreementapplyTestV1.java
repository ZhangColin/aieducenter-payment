package com.icbc.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.icbc.api.request.MybankOspayCborderVendorAgreementapplyRequestV1;
import com.icbc.api.request.MybankOspayCborderVendorAgreementapplyRequestV1.MybankOspayCborderVendorAgreementapplyRequestV1Biz;
import com.icbc.api.request.MybankOspayCborderVendorAgreementapplyRequestV1.ReceiptInfo;
import com.icbc.api.response.MybankOspayCborderVendorAgreementapplyResponseV1;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;

public class MybankOspayCborderVendorAgreementapplyTestV1 {

    private static final Log logger = LogFactory.getLog(MybankOspayCborderVendorAgreementapplyTestV1.class);

    //应用私钥
    protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCEoohfv2u4DZeQOMVWkB8lmDRM0DrWD5a6B9nY7ID9VYE/Mk+L8kLbGzovDQVrnptKSBOhwaaraM0SME2K5EEtqUjZRxusPQLOaL1YgmrJ6dlMMQFxoB4SG2SyLCG787amDE4ddMAkI1E7otOupy5GGqdtI4YUOx4tF4tdVlTq8vlGY61amuxCYRJpXtjcxvcVI0LofXKlJ6MW55B3R5kAhx3rEIXduJQmWbTBRMi/IJw7dnSRGK6DG5fbkxpsDYOmaH4WQtVyTj8ul5wtCt8km3PAyi2psMzAf/Csab25PEn7RFdz9eLjoY76L9wf4U354e/qFu/eEKsQDbr5i24BAgMBAAECggEASDCGdUakhHqxYYfksGak7TtYS+6R92xopzwar9yZoEjnndPuQ8dJ6CN7mZxGtPqQnu937/SMmILkCuBLdCsj5oxa4Be50hcq7dlIJWJmaRtkb/nERWnWtBhAyFEI01mksd/ErHedFI48am8HM+o+X12mftp04ZIDrJtB+T1B3tYyyCEt+LM8vZtnv93dDvqAN6oLUwRXIx+XEfk88SHAJIVtgd6qDFCXvn/+F1QgOoJ/AipzmrUkkAbdj+9s8AcN3cSX2qv+tDkPxNdbyk3oCXbBxViuSZAknVsktolEGwNxHHU4MMWsiuQYgWsHmjcuqpaOhohFZOzmIxAq3rdq2QKBgQDJGyVuRGiKynqQEMlpmOVn22/2Ua7oGRwikZl5tZvQCgQexBnMXl+AetkAu0u6UQoGZwFdk46L/5ZYTTgaVUh4f66NVlfEtu1reFj3mcU53igiINupJUPdOepmPBU2FiUcgfO20p4M4WPjsfEQQh0VN0x8HR/RXBaDuv8qBO8wJwKBgQCo1sV1X6afLwLDFj5Fj7N1+dL4jejsIMYV7rtB32g6FVFrIGN0iOcqJ4zXoCjqRKwcvIw+f4IEC+3C34w98YHeWOANbxB50IXnJctZP7ww8TKTCMTD292j8TS1pWHJw1UZWb+ilRmssciEh8r7imiJpTi0V4RNvMwDTfMX8lchlwKBgBMALZktfXMfjlTuQRlpCMO3R+zN6uii9rWNbCLDteUCDUGa5ZUZlYxeDwKyr4T7Ayd3s/lTZ7RygZH2OcbDKAW6ipNwBPrhDa2JwV4jeS7ufnw6/oYMr0zHnA3sv1RM41S+MnijjatJ4FYzCcOve2/1uZRAbDErsjTgBhvCO5chAoGAbhckx8Tn05IGvghJ8Hej8ABHFTULNLSmFPUGewxeXMk7vptu3jTKbo09Y/gV1bOTemVjGPlxYIwaq1+yhU1tffr7lSbfWl6igTb+oiJR5qPPc1pbWs6JdfW4hdzhp3rLiW0g7LVF+XovImOc8mmsglwOXmFWbo31hQ4VvX6SW98CgYA9ZeVIX24MpSZShsXrdlVtXi7BOowD2oerr9aVg8ISPAPz7GUn6+JCM7VWeyMUjgsL9+wA/nA7P23W2zlkGl6o9WPS2Bo5F6LwOQwpuupRXRF4Hw5RePk8a3qLdv4Fiu0es7XMTgV1xddpQEVTjtfZQeEFfe1xtKjWDGAEptK6mg==";

    //网关公钥
    protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
    //应用ID，暂定，APP申请分配
    protected static final String APP_ID = "10000000000004098133";


    public static void main(String[] args) {

        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

        MybankOspayCborderVendorAgreementapplyRequestV1 request = new MybankOspayCborderVendorAgreementapplyRequestV1();
        request.setServiceUrl("http://ip:port/mybank/ospay/cborder/vendor/agreementapply/V1");
        MybankOspayCborderVendorAgreementapplyRequestV1Biz bizContent = new MybankOspayCborderVendorAgreementapplyRequestV1Biz();

        bizContent.setParentVendorId("11223344");
        bizContent.setVendorType("1");
        bizContent.setOperateType("1");
        bizContent.setVendorId("202207010002981");
        bizContent.setCnCompanyName("企业名称");
        bizContent.setBusinessLicense("00010562226");
        bizContent.setSettleAccount("0200099809277975364");
        bizContent.setSettleAccountCurrency("RMB");
        bizContent.setEnContactPerson("联系人");
        bizContent.setEnContactPerson("LianXiRen");
        bizContent.setContactIdcartType("0");
        bizContent.setContactIdcartNo("3713247866545563556");
        bizContent.setContactPhone("15866669999");
        bizContent.setEnBankName("en Bank Name");
        bizContent.setCnBankName("出口收汇银行");
        bizContent.setBankBiccode("bankBiccode");
        bizContent.setExpCnCompanyName("出口收汇银行企业名称");
        bizContent.setExpEnCompanyName("exp com Name");
        bizContent.setCnCompanyAddress("出口收汇企业地址");
        bizContent.setEnCompanyAddress("en com address");
        bizContent.setEnContact("en contact");
        bizContent.setCnContact("出口收汇联系人");
        bizContent.setExpContactPhone("出口收汇款联系人");
        bizContent.setTradeType("");
        List<ReceiptInfo> receiptInfos = new ArrayList<>(2);
        ReceiptInfo receiptInfo = new ReceiptInfo();
        receiptInfo.setAccount("11223344");
        receiptInfo.setCurrency("HKD");
        receiptInfos.add(receiptInfo);
        bizContent.setReceiptInfoList(receiptInfos);
        request.setBizContent(bizContent);
        System.out.println(JSON.toJSONString(bizContent));

        MybankOspayCborderVendorAgreementapplyResponseV1 response;
        try {
            logger.info("Request: " + JSONObject.toJSONString(bizContent));
            response = (MybankOspayCborderVendorAgreementapplyResponseV1) client.execute(request, System.currentTimeMillis() + "");
            logger.info("Response: " + JSONObject.toJSONString(response));
            if (response.isSuccess()) {
                System.out.println("execute success");
            } else {
                System.out.println("execute failue");
            }
        } catch (IcbcApiException e) {
            logger.error("execute exception" + e);
            e.printStackTrace();
        }
    }
}
