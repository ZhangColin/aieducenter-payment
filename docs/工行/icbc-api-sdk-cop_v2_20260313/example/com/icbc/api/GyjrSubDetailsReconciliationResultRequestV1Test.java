package com.icbc.api.request;


import com.alibaba.fastjson.JSONObject;
import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcConstants;
import com.icbc.api.response.GyjrSubDetailsReconciliationResultResponseV1;

/**
 * @description TODO
 * @date 2022/11/4 15:57
 * @author kfzx-wangj07
 * @version 1.0
 */
public class GyjrSubDetailsReconciliationResultRequestV1Test {


    public static void main(String[] args) {
        GyjrSubDetailsReconciliationResultRequestV1.SubDetailsReconciliationResultRequestV1Biz requestV1Biz = new GyjrSubDetailsReconciliationResultRequestV1.SubDetailsReconciliationResultRequestV1Biz();
        GyjrSubDetailsReconciliationResultRequestV1 requestV1 = new GyjrSubDetailsReconciliationResultRequestV1();
        requestV1Biz.setTrans_info(transInfo());
        requestV1.setBizContent(requestV1Biz);
        requestV1.setServiceUrl("http://ip:port/api/gyjr/b2b/bankEnterpriseRecon/subDetailsReconciliationResult/V1");
        DefaultIcbcClient client = client();
        try {
            GyjrSubDetailsReconciliationResultResponseV1 response = (GyjrSubDetailsReconciliationResultResponseV1) client.execute(requestV1);
            System.out.println("响应：" + JSONObject.toJSONString(response));
            if ("0".equals(response.getReturn_code())) {
                System.out.println("success");// 成功
            } else {
                System.out.println("error");// 失败
            }
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }

    }

    private static DefaultIcbcClient client () {
        String app_Id = "10000000000004098168";
        String priKey = "-----请使用正确的APP私钥-----EvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCDkBboKWjOqO++2CXIE2RmE5Lym5gZvsgeqn6AGdzVT6zOnr+79PLhjfbUntXWS8e4XooVvLYdMuyasH5Wq8+Gb4pY4pCU85rMFqqIj8gYvSGU2P+EsAbngTqs/ck9fBYUcpZe+aDwvvE4snYgpJVM4sDJL6nRjFn6h3WFUnLNTOiknxAtxNKksP5RmKFkOMEjxh/SxltkOI9Qndpnj3CW2u+SDqRpoPFkQm0obK7HGFumMRbWTZZeFnmAVR/16SRcBQRXE4AKZ3KynCHPIHMNRIFHw4+sqKqHgiH6NpCo+k4dXnJy1OWTAgC6x2ghQ77mJ4k+B2TWbvivLpzS4stPAgMBAAECggEAIfbV4xOlqgx08Eju4AsZlTyiaB2BE8xr73bGd521pCAQlbrn9scS2OYEffmPbOZdZhyJ+3ifb0RRTtIXm5p5VdR/29iJyNpm8R884Up1NuXEwJ+Qv5mN/sAe/L/77vKU5Kx8Y4KAaQZyUdfIpT64CIRVjtrONXnNVN8ZmNSjLJq4xkQmxWtG7gqZpMo2RIJzsZm/9sgOcl8FA6DGJV+R/SjlPblze7d8c0ZonV3+Gzp9vV32ON9GbaHmhrFdfiltEW+Ch9Catfiu3dYGBTWbF25ntlnNY7d47MlcsF6gJNF2Jm9V3l2uq6vcA9rjtflzGlMu0Hd7KULBpNfcNSDbKQKBgQDeXtZ8oo0hBz5L5XA3P1IOd/Etoi9alc0AxzXjOT2UrFnFAH3SH3eOGJbhJUG4C15uiEHmLItVCLdvWHkTPWxZl6Vfqi3B2fpainnQTBCOEfR30LE0DbLW8gpnE2eNXGV8lZPllh8u0Y/E4tQdOEntNRqPysKxk5bOUnWipZ7EOwKBgQCXdZkrrTNr7q2LvfFSdA4kIx4FFI1M/8kHnqNT2wdCFYYEagiE3Lw+/CjFpkE6tpKd2SN5P6c98g/jL0Kej0HHKwNUfeI1n7klEhroC8KtROMEO8fuzoDyEZjvbNGEUFMa+jvvzsfyMx3mFfkzQzL3qJKzCUl0/HTkmX7vLUPH/QKBgBAfsE25O6J5c/rs2AYpgTmrWMGGUQiwc/zqdO3U6LIvDRNLxdPGlFSpU8dffDRsHpzFJIIcGL5Bs4tPTvgmLAUTCdq17DtyIsUN5WdFHK4wfxlw+Ghd1engAwEWVxvY50LxVZgAGqK6b0JHcDnuKu4YO6TRfuyBVx3+vKjeeXShAoGBAJSdTYhv0M8NSbiezhYrzlxjIOe3PNba/TS+0mBLwJl1aSUXrfhAZ24eGxulgMfNsZu1Hnuwm7fKtxAipWnUA6UcbY4QX1Biw1a+pywdrg46U88poYqPkjL0VW5JBmSgNIt6EinCaqsawTGtSfKk83PTMu7+I9foiAJvIwApGCM5AoGBAKfIvZ9nrdmxm1E5SkSylWYJh3X0ZqgX505MX+FcdGTLUWwpuw/AW6fLd8zzrYgExPZkH9lNSXpYxKqUDnZvhVUakwQ6DCV5bEA9jxARtpUHMAi35caAbX49IUrs2BzsfOXCajnyoCZN2O7D0JaFR6IuvfePLr68aYM3Himhqq+0";
        String pubKey = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
        return new DefaultIcbcClient(app_Id, IcbcConstants.SIGN_TYPE_RSA2, priKey, pubKey);
    }

    private static GyjrSubDetailsReconciliationResultRequestV1.TransInfo transInfo () {
        GyjrSubDetailsReconciliationResultRequestV1.TransInfo transInfo = new GyjrSubDetailsReconciliationResultRequestV1.TransInfo();
        transInfo.setProtocol_code("GYJR20220415100404714699148");
        transInfo.setApp_id("1000000000000409816");
        transInfo.setD_flag("1");
        transInfo.setDt_type("1");
        transInfo.setAcc_no("0200062014212394556");
        transInfo.setCurr_type("001");
        transInfo.setBill_id("020000002022033139001178");
        transInfo.setContact_name("1");
        transInfo.setContact_phone("1");
        transInfo.setSubmit_id("1");
        transInfo.setDc_hk_res("1");
        transInfo.setAmount("1");
        transInfo.setRec_flag("1");
        transInfo.setRefer_flag("1");
        return transInfo;
    }
}
