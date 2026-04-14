package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.BcssMemberCardApplyRequestV2;
import com.icbc.api.response.BcssMemberCardApplyResponseV2;

import java.math.BigDecimal;


public class BcssMemberApplyCardTestV2 {

    protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCbFZ4tsIgTBbroQsMZJunv+ZRwoQxNL/8ZyQqWk7qn1g1Tb635KsUOEQNrFT+b0gWL91W3+rGF7ShSCNaS0Vb5wTva8a0xK0E70sLIhujxdIw9021EbS9BwCm7202LzXyd2jVrwf8rIfptcoaHcP344ZMM78r52rzZVCxWw5z0tZYsUuOMB9PLsm1J8RkDpbcAm2olbMtPpZhsAuDHKPH6EVaRrxC0rReZy2eh+Q6jdba3zxCRc8QO+rq1nJO6q330cscZ00+Y2e1ywF5pAXf81+Kg+QggGwn0pkAhn9KApqYNZ2Jwe7dPxRW1Skz6w8/x3AtfZNt4ezqOp3gQcaExAgMBAAECggEANJEBSq9ZkEkS74MhqjbceLD6NasBBnDMYSsZ4aw1SoptfeiO6bQrkvcFV5ieNOzdYHH3piLdZW3biuLgCGfYuVNcPHxKni3xMJvh1iKUdrNwjcxKbzUrHXhLLRfKkyaVpNO/48Sf/zjHL63wF5yfGWsscugcvs/7zxaO6OHpI7CYloyJ+Y3AC2QnzZLOVfjtcR19qq2jtU/rHwwGBBzthYeisicvwsQZA/Oj5jZylSAh83dKc5HW21AVmc3whe95Vz4k/IMyz0o/OU9UuGYiiH0VaHXKPiSNicEtAgP431t89PE9ErNusLvimeNeg0b/VdloC+0glrmqwhDLWWUpgQKBgQDnsl8rO81yKHin+AlAvqzC0NXQjj9LJXxnVeraTKxfStN9RFqxtHv8B6RiIpyANFDnd9mTtIR6N3tb2HJ8Km3S6BDOkZqF2UIwpzkrpZdFQN7p6atakNxm6Pb0tS2IVDyGjQctT9xO4FTsOgtTj9waVbNNB9a4vSxizUVNtx7JWQKBgQCrWgRPEp9ck6plKiPRcCaepyzwx/q+SuTk2ova3nu5cmc35Uo1KxBP6d4Y/42vylDGFMZU10LtODLFD/f7E6/KLb1m1bV3IwjyKFCqRefe/eVP9cBKSlIGPjLqfacDuMyJYaZd+Fngp43+lzos8lgKdwT5MU3e1RipYoVYHwFDmQKBgGkA8JqCVsBm0Q+mnGLoRxlfVZdX8B1ZVsDqMi5O00u4eJJr2QJyPkJhIEGNWAnOK+BK86M6C1PsMw7T0EavX+hWXc+QM0x3wsST9JfwStcK6DtwN8Uqo4hMCiequIDxVCDSZy9E4x4oErSgNaPgLasNrd26MLi2mxgH2WG4HM65AoGAHjYh2ls7M9RpT6rtY8j1VjW9i7qGsDR+RQdvbyiZAep03nsT6WntV1mxqhCsx5jRQwt4qI7HoxGsieg13dPrw6bq5Q27EAViV2faSRtINZ3oZ3+55p9R9P3UdlmvL83Oak6ISbs3BZAlTgUV4cTc1wODIfiadTZ3Qa44OoBE9ckCgYEAmOZzycNjDPS59lYIs+yK4GLmmruAwfUBf1sTfN6YWz8K41j8BQSTMvMjm20F5V1HN8SgLWdy61KdaSn6LQZKNp2BXS2AHTL1D/GXP4FrCo7ZZpnSBDJjQ18wctYdjS+4fMZdSPZ0A+0x4lPKjKEr5JDrkUgYnwp58w+ekYPJs40=";

    // APIGW_PUBLIC_KEY
    protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
    //protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的APP私钥-----BIjANBgkqhkiG9w0BAQEFAAOCAQ8A-----请使用正确的APP私钥-----BCgKCAQEAmxWeLbCIEwW66ELDGSbp7/mUcKEMTS//GckKlpO6p9YNU2+t+SrFDhEDaxU/m9IFi/dVt/qxhe0oUgjWktFW+cE72vGtMStBO9LCyIbo8XSMPdNtRG0vQcApu9tNi818ndo1a8H/KyH6bXKGh3D9+OGTDO/K+dq82VQsVsOc9LWWLFLjjAfTy7JtSfEZA6W3AJtqJWzLT6WYbALgxyjx+hFWka8QtK0XmctnofkOo3W2t88QkXPEDvq6tZyTuqt99HLHGdNPmNntcsBeaQF3/NfioPkIIBsJ9KZAIZ/SgKamDWdicHu3T8UVtUpM+sPP8dwLX2TbeHs6jqd4EHGhMQIDAQAB\n";

    protected static final String APP_ID = "10000000000004097413";


    /**
     * APIGW（行外网关） 2020年11月 api地址
     */
    protected static final String API_GW_OUT = "http://ip:port/api";


    public static void main(String[] args) {
        try {
            testApply();
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }


    public static void testApply() throws IcbcApiException {
        BcssMemberCardApplyRequestV2.BcssMemberCardApplyRequestBizV2 bizV2 = new BcssMemberCardApplyRequestV2.BcssMemberCardApplyRequestBizV2();
        bizV2.setCorpId("2000000067");
        bizV2.setApplyNo("1");
        bizV2.setTrxSerno("123192495242");
        bizV2.setTrxDate("2021-07-08");
        bizV2.setTrxTime("11:11:11");
        bizV2.setCertType("0");
        bizV2.setCertCode("441451199901025245");
        bizV2.setName("大王1");
        bizV2.setCustTypeNo("5");
        bizV2.setCellPhone("13322226666");
        bizV2.setProdNo("00000073");
        bizV2.setOpenUserFlag("1");
        BcssMemberCardApplyRequestV2.CorpUserVo corpUserVo = new BcssMemberCardApplyRequestV2.CorpUserVo();
        corpUserVo.setUserNo("202100070803");
        corpUserVo.setDeptNo("100001");
        corpUserVo.setOrgNo("00001");
        corpUserVo.setRoleType("200000006700");
        bizV2.setUser(corpUserVo);

        BcssMemberCardApplyRequestV2 requestV2 = new BcssMemberCardApplyRequestV2();
        requestV2.setServiceUrl(API_GW_OUT + "/bcss/member/card/apply/V2");
        requestV2.setBizContent(bizV2);
        DefaultIcbcClient client = getIcbcClient();
        BcssMemberCardApplyResponseV2 response = client.execute(requestV2);
        System.out.println(JSONObject.toJSONString(response));
    }

    private static DefaultIcbcClient getIcbcClient() {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        return client;
    }

    private static UiIcbcClient getUiClinet() throws IcbcApiException {
        UiIcbcClient client = new UiIcbcClient(APP_ID, MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8);
        return client;
    }

}
