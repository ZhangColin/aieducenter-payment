package com.icbc.api.test;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.BcssOrderCenterOrderUpdateRequestV1;
import com.icbc.api.request.BcssOrderCenterOrderUpdateRequestV1;
import com.icbc.api.response.BcssOrderCenterOrderCreateResponseV1;
import com.icbc.api.response.BcssOrderCenterOrderUpdateResponseV1;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author jieyd
 * @Date 2021/11/23 15:17
 */
public class BcssOrderCenterOrderUpdateTestV1 {
    String puv = "-----请使用正确的APP私钥-----BIjANBgkqhkiG9w0BAQEFAAOCAQ8A-----请使用正确的APP私钥-----BCgKCAQEAnOH2yFG0MFXoHgcWtJY0B/okNM6yV6gS9wjxlCBzYAf1ppV6SZlvzt3Gj7TWj98V7jGbrBjHkIv3McNWNH4G6/pNVXGPyvsnR1sBL1KzOaC30qjrsDiTkchkAJjfx6eozCGhdLStcxYTBGE/jX9k3jpH0KRbxw9cHBg6T2aGKQwkjuJqo98vExSXyiR1dTEujzuEJu2p1xPlrfSuwAhDzwRIrppqP4jmr0KSwGGtqAfhR8NRn4bn3bcRdmBSgk78aTOE+rGHs1eqj54c4Wfgnp4NBdXAjELytROOZJpvZkmVfjhPUxFEPQg4JX+XIwUXpOjhrAwoqHFbKrNGO3qAowIDAQAB";
    String prv = "-----请使用正确的APP私钥-----EvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCc4fbIUbQwVegeBxa0ljQH+iQ0zrJXqBL3CPGUIHNgB/WmlXpJmW/O3caPtNaP3xXuMZusGMeQi/cxw1Y0fgbr+k1VcY/K+ydHWwEvUrM5oLfSqOuwOJORyGQAmN/Hp6jMIaF0tK1zFhMEYT+Nf2TeOkfQpFvHD1wcGDpPZoYpDCSO4mqj3y8TFJfKJHV1MS6PO4Qm7anXE+Wt9K7ACEPPBEiummo/iOavQpLAYa2oB+FHw1GfhufdtxF2YFKCTvxpM4T6sYezV6qPnhzhZ +Ceng0F1cCMQvK1E45kmm9mSZV+OE9TEUQ9CDglf5cjBRek6OGsDCiocVsqs0Y7eoCjAgMBAAECggEAH635mYX2LvSKBNZFnX3fT+URPqCmntDP05a4wcKzyaaR0mvI3+feDhX2rLpFwZO45/qYhu4SDnxVFHVviPYSC8DlsOVXthN1YRFdceLvUinENQPrZBBorD2dBAna1oRir6Xy5pA9nknM2UuR+87H3aBDNDycU9wkK4Aw9iur18nMTMSJQ4YDqDERJe3+BKMORSClt7sWzXP0e8djHG4AuDLD+he0cXp3Grv+KHYyxWDiqKKccZgwgFUX9g932SCglR3aLufm44uH3msdnBcfaQ9+rYwybgDtNmdnKcDzckiLnU3mvxkWJ8LHGSUtfKrte4GiyxpP14vVdqwk+FAqYQKBgQDkkO6xZFrmlfS+HdkmWUfhMmtRAiAwWmawo2YFRQpdT55bwcIIdmeFRK+G4XFedntPyonoDYFF3sabu4dTCKR+bunGIDZpCn04z8ueIi59CKLTC3AR3lRgwWLRkmrDT9QBY9kGDbv+q12SWZBrRIJKj9j98IDPRZ20EG61Yya/uwKBgQCvtnCltZtvQBldCfmZufh9N2JPEcCep+pV+FZuSn5watNyzvvtFD7PnakK7vzIJCGEa/vC292WsFUo32tHp+2LXmegWjpSKhBZLupxTH/ZlNeFtaGcYEIm4DfiSZ1Ugyxh43ukybBR0RjkeRjxQm9CuYrzduPZfEo2bsU/8GhwOQKBgQCw/6dISf22uQN5QmYJs3PdaYz/g5TnvOnu+eokRI29Ks3oIoiewwpxgnonCmgfjcL6TE81G1gzgkvtB3DJFVVE76ErI4DfIRdNqmT1BWVYwBpPMnlL7Q8MilV7E1aH/0ae+PJ0JKijk5L3pVIYL0pycSjJpbgqSq/QZDVHVAwKxwKBgEvkTo8j+zh3xPysXEQLUMo0a5+fGUTtXYc4NYx5XFcNrLo/sAvb3K0reWGhi0ieYmOhiCHHeNOm4YE/zkQqwayaX8OgdOQcCueaHeRFmErPwK6dnmm+IZRM75YrGWrxJPA6oqKwAhm0aD32F9HMUeXN4bf6N7sttZgBC+zr1w/xAoGBALDFuYZAT4YA6tVcfm1r7sHaAxQXZrAgI6zXZA5m+URAg97PDpkLeqy+WMKVDDO7CoD72QDs4MR+QkxZ2uIeSDHV/M/b9Vyn0Jkb5Mh3Mj3QPAOmwVODYQfCYti0uM2QtcqNHozhCeeZa6IubXFPazQuQNJng7aegwHUdbCguC+U";
    protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCbFZ4tsIgTBbroQsMZJunv+ZRwoQxNL/8ZyQqWk7qn1g1Tb635KsUOEQNrFT+b0gWL91W3+rGF7ShSCNaS0Vb5wTva8a0xK0E70sLIhujxdIw9021EbS9BwCm7202LzXyd2jVrwf8rIfptcoaHcP344ZMM78r52rzZVCxWw5z0tZYsUuOMB9PLsm1J8RkDpbcAm2olbMtPpZhsAuDHKPH6EVaRrxC0rReZy2eh+Q6jdba3zxCRc8QO+rq1nJO6q330cscZ00+Y2e1ywF5pAXf81+Kg+QggGwn0pkAhn9KApqYNZ2Jwe7dPxRW1Skz6w8/x3AtfZNt4ezqOp3gQcaExAgMBAAECggEANJEBSq9ZkEkS74MhqjbceLD6NasBBnDMYSsZ4aw1SoptfeiO6bQrkvcFV5ieNOzdYHH3piLdZW3biuLgCGfYuVNcPHxKni3xMJvh1iKUdrNwjcxKbzUrHXhLLRfKkyaVpNO/48Sf/zjHL63wF5yfGWsscugcvs/7zxaO6OHpI7CYloyJ+Y3AC2QnzZLOVfjtcR19qq2jtU/rHwwGBBzthYeisicvwsQZA/Oj5jZylSAh83dKc5HW21AVmc3whe95Vz4k/IMyz0o/OU9UuGYiiH0VaHXKPiSNicEtAgP431t89PE9ErNusLvimeNeg0b/VdloC+0glrmqwhDLWWUpgQKBgQDnsl8rO81yKHin+AlAvqzC0NXQjj9LJXxnVeraTKxfStN9RFqxtHv8B6RiIpyANFDnd9mTtIR6N3tb2HJ8Km3S6BDOkZqF2UIwpzkrpZdFQN7p6atakNxm6Pb0tS2IVDyGjQctT9xO4FTsOgtTj9waVbNNB9a4vSxizUVNtx7JWQKBgQCrWgRPEp9ck6plKiPRcCaepyzwx/q+SuTk2ova3nu5cmc35Uo1KxBP6d4Y/42vylDGFMZU10LtODLFD/f7E6/KLb1m1bV3IwjyKFCqRefe/eVP9cBKSlIGPjLqfacDuMyJYaZd+Fngp43+lzos8lgKdwT5MU3e1RipYoVYHwFDmQKBgGkA8JqCVsBm0Q+mnGLoRxlfVZdX8B1ZVsDqMi5O00u4eJJr2QJyPkJhIEGNWAnOK+BK86M6C1PsMw7T0EavX+hWXc+QM0x3wsST9JfwStcK6DtwN8Uqo4hMCiequIDxVCDSZy9E4x4oErSgNaPgLasNrd26MLi2mxgH2WG4HM65AoGAHjYh2ls7M9RpT6rtY8j1VjW9i7qGsDR+RQdvbyiZAep03nsT6WntV1mxqhCsx5jRQwt4qI7HoxGsieg13dPrw6bq5Q27EAViV2faSRtINZ3oZ3+55p9R9P3UdlmvL83Oak6ISbs3BZAlTgUV4cTc1wODIfiadTZ3Qa44OoBE9ckCgYEAmOZzycNjDPS59lYIs+yK4GLmmruAwfUBf1sTfN6YWz8K41j8BQSTMvMjm20F5V1HN8SgLWdy61KdaSn6LQZKNp2BXS2AHTL1D/GXP4FrCo7ZZpnSBDJjQ18wctYdjS+4fMZdSPZ0A+0x4lPKjKEr5JDrkUgYnwp58w+ekYPJs40=";
    protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

    protected static final String APP_ID = "10000000000004097499";
    protected static final String RSA = "RSA";
    protected static final String RSA2 = "RSA2";

    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        BcssOrderCenterOrderUpdateTestV1 testV1 = new BcssOrderCenterOrderUpdateTestV1();
        testV1.orderUpdate();

    }

    public void orderUpdate() throws IcbcApiException {
        BcssOrderCenterOrderUpdateRequestV1 request = new BcssOrderCenterOrderUpdateRequestV1();
//        request.setServiceUrl("https://ip:port/bcss/order/orderUpdate/V1");
        request.setServiceUrl("http://ip:port/api/bcss/order/orderUpdate/V1");
        BcssOrderCenterOrderUpdateRequestV1.BcssOrderCenterOrderUpdateRequestBizV1 requestBizV1 = new BcssOrderCenterOrderUpdateRequestV1.BcssOrderCenterOrderUpdateRequestBizV1();
        //majorOrderInfo
        BcssOrderCenterOrderUpdateRequestV1.OrderMajorOrderInfoDto majorOrderInfoDto = new BcssOrderCenterOrderUpdateRequestV1.OrderMajorOrderInfoDto();
        majorOrderInfoDto.setCorpId("2000000049");
        majorOrderInfoDto.setMorderNo("MO1000000");
        requestBizV1.setMajorOrderInfo(majorOrderInfoDto);

        //subOrderInfo--子订单集合
        List arrayList = new ArrayList<>();
        BcssOrderCenterOrderUpdateRequestV1.OrderSubOrderInfoDto subOrderInfoDto = new BcssOrderCenterOrderUpdateRequestV1.OrderSubOrderInfoDto();
        subOrderInfoDto.setSorderNo("SO123456");
        subOrderInfoDto.setSubRefundAmt(BigDecimal.TEN);
        subOrderInfoDto.setRefundWay("1");
        requestBizV1.setSubOrderInfo(arrayList);

        request.setBizContent(requestBizV1);
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, RSA2, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        BcssOrderCenterOrderUpdateResponseV1 response = client.execute(request);
        System.out.println(JSONObject.toJSONString(response));
    }
}
