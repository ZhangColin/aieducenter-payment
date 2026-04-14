/**
 *
 */
package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.BcssConsumeOrderCreateRequestTestV2;
import com.icbc.api.request.BcssConsumeOrderModUpdateRequestTestV1;
import com.icbc.api.request.BcssOrderCenterCreateRequestTestV2;
import com.icbc.api.request.BcssOrderCenterModUpdateRequestTestV1;
import com.icbc.api.request.BcssOrderCenterModUpdateRequestTestV1.BcssOrderCenterUpdateRequestBizV1;
import com.icbc.api.response.BcssConsumeOrderModUpdateResponseV1;
import com.icbc.api.response.BcssOrderCenterModUpdateResponseV1;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * 合作方会员信息查询
 *
 * @author kfzx-lizm
 *
 */
public class BcssConsumeOrderUpdateTestV1 {
    // MY_PRIVATE_KEY
    protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCGWcbLxe2GfHfltp5qimW22EMclBH2302iVDYc7oRqvzlq3LJgNNdL09z6KyiWTcGPw83S2/f2teGUKoidVs3uLpWn4Q7wqmZmXH/QWnN1BGabRVeZj4rzP5vPTnI1fMDaReJsbmJWdwhqboS72lRSFJSBmeCKDL+ITVv8b+U4TaYlgJuYytYn88cHIISUSB/OgrzP1WtY10agc2yST669JcB7n38qLD3Dfy74mYucERiJNQxtjMzbPgpdTvYFuCJCcQGYX4Pqcp0h4dcXUHVmxCducnj9QeL/xi4gOpuKUzoi7f451d7OTS2Ktx9h8m3qAoi2t6+la+4UKA138XHbAgMBAAECggEAN+D7OCHYiwkV7SspdHKfn48PSsGjPyTda1jPMPVbifKC9RlZ733K1jUGQd7VgL5SdXMhFkR7O9emPTP7okVKXXhusmZ/CkY1VPnAB263esIb0xTiPHJhfo7ERVMBWZHLvH04kZTPvdrAW1QgTOmgDIuWwNi5dXGb8BsM2U/I7MN0jVhf+17ih9EoEJyauDcDov8QJ+qGDBd5ds1LEFLXlr9Pq7bxvcpv9cKosFrDQR24cNnJcE+Ec4kW77GvQ50pBC3l7Rd3OFcBSiH2FELn4WlKZcYluRPsDJKYaFclrEk2GBHdlijHowux2lkhUrM2auAeb/A7BmQ6Wsd/NHDlcQKBgQDDdqJx5QBN899zQuRLJWffCStUIMaB533BoX0ik46Xvh6XqfaaicwzFLsS6H7DyZeYkt2ehmtBgoN1vaQmOt8RDzCYDA42OMwcP9Hh97TiaD4SDQ9UqUbCQGn/MSMAYYN9yCT9LdZnhRS4iObIyKaB2WVs8U6BZdG8Mv7KYMSj7wKBgQCv9c3nGTa5zdM5jAdfuZSTZjcgyk6DWUrkMCFN8XmoNxHEp+X2mGjHRFVDnIQo1wejeflXFHvxKXSDhfwA78DjDi7ntRoryqYrOD96S9VjbnZYQiUuEK7gDXSq89RvrlW7x3Ktr4EvwpsLB8JKlDhSCiDcu7ZaWKEBmLb2+v+01QKBgQCXQED5s0PmDlWVomh4Acz72OOPaQFS2mgEosrrK5WGtFaF875g3UqHKH6OZhxZsrA9H0Cg/9EVU0IYpRPVhW7Bi6iFr6Z7rL29CmGF4yE0pYeKflKjp42amS+sikBUdiDC/1IoHAUx+9gJ59319tZp2mjSwXOHS9vdLvTcc78WPwKBgF3lcUbM8aMc7oZa7uRqB5rQhMJ+sn7s+0XhWSRao2zcViL25YQ9uEv9bvJxZyTT+kOGJ0QMrkb2tDIrIVrulaOUQftrbNfJf/q4PUdpiClTHNvkECsQxADTrVo8WzRtd1qJB+0fJnTyCAJO1aMujQ9aUEb59Ekp2mEbuSb6sdExAoGAbB1QYibFTKwwhzOV2o5KcgP+rmK4bdClWMsA3UY0MBFjNEUF2meijuM+AiteUU/1LuDo/hE502XiTNjqQUElob7IEZTSa3jyTW+eag8XgS5tUeCw5v1vILsQTNf7HGKSDs2VOt/Weqhveds9a6OzT1yPW31cg7wOWMCuHyDANJc=";
    // APIGW_PUBLIC_KEY
    protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
    // APP_ID
    protected static final String APP_ID = "XXXXXXXXXXXXXXXXXXXXX";

    protected static final String RSA = "RSA";
    protected static final String RSA2 = "RSA2";

    public static void main(String[] args) throws Exception {
        BcssConsumeOrderUpdateTestV1 a = new BcssConsumeOrderUpdateTestV1();
        a.test_cop();
    }

    public void test_cop() throws IcbcApiException {
        BcssConsumeOrderModUpdateRequestTestV1 request = new BcssConsumeOrderModUpdateRequestTestV1();
        request.setServiceUrl("http://ip:port/api/bcss/consume/mod/updateOrder/V1");
        BcssConsumeOrderModUpdateRequestTestV1.BcssConsumeOrderUpdateRequestBizV1 bizContent = new BcssConsumeOrderModUpdateRequestTestV1.BcssConsumeOrderUpdateRequestBizV1();
        bizContent.setClientTransNo("XXXXXXXXXXXXXXXXXXXXX");
        bizContent.setClientType("X");
        bizContent.setCorpId("XXXXXXXXXX");
        bizContent.setCouponAmt(new BigDecimal(44));
        bizContent.setDataSrc("X");
        bizContent.setCouponNo("X");
        bizContent.setGoodsType("XXX");
        bizContent.setMerNo("XXXXXXXXXXXXXXXXXXXX");
        bizContent.setGoodsType("XXX");
        bizContent.setImeiNo("XXXXXXXXXX");
        bizContent.setManufacturerId("VXXXXXXXXXX");
        bizContent.setIndustry("XXX");
        bizContent.setPayType("X");
        bizContent.setTimeStamp("XXXX-XX-XX XX:XX:XX");
        bizContent.setPayType("X");
        bizContent.setTotalAmt(new BigDecimal(44));
        bizContent.setPayAmt(new BigDecimal(4));
        bizContent.setOrdersrc("X");
        bizContent.setTrademode("X");
        List<BcssConsumeOrderCreateRequestTestV2.BcssConsumeOrderCreateRequestBizV2> goods = new ArrayList<BcssConsumeOrderCreateRequestTestV2.BcssConsumeOrderCreateRequestBizV2>();
        BcssConsumeOrderCreateRequestTestV2.BcssConsumeOrderCreateRequestBizV2 good1 = new BcssConsumeOrderCreateRequestTestV2.BcssConsumeOrderCreateRequestBizV2();
        good1.setGoodsId("XX");
        try {
            good1.setGoodsName(URLEncoder.encode("北XXXX","UTF-8"));
        } catch (UnsupportedEncodingException e) {
            good1.setGoodsName("BXX XXXX XX XX");
        }
        good1.setGoodsNum(new BigDecimal(44));
        good1.setGoodsAmt(new BigDecimal(44));
        good1.setGoodstype("XXX");
        try {
            good1.setGoodsName(URLEncoder.encode("XX","UTF-8"));
        } catch (UnsupportedEncodingException e) {
            good1.setGoodsName("XX XX");
        }
        good1.setGroupNo(null);
        good1.setPeriodid(null);
        good1.setTypeid(null);
        good1.setGoodsback(null);
        good1.setDistributeDate(null);
        goods.add(good1);
        BcssConsumeOrderCreateRequestTestV2.BcssConsumeOrderCreateRequestBizV2 good2 = new BcssConsumeOrderCreateRequestTestV2.BcssConsumeOrderCreateRequestBizV2();
        good2.setGoodsId("X");
        try {
            good2.setGoodsName(URLEncoder.encode("XXXXX","UTF-8"));
        } catch (UnsupportedEncodingException e) {
            good2.setGoodsName("XXX XXX XXX");
        }
        good2.setGoodsNum(new BigDecimal(4));
        good2.setGoodsAmt(new BigDecimal(4));
        good2.setGoodstype("XXX");
        try {
            good2.setGoodsName(URLEncoder.encode("XX","UTF-8"));
        } catch (UnsupportedEncodingException e) {
            good2.setGoodsName("TXXXX");
        }
        good2.setGroupNo(null);
        good2.setPeriodid(null);
        good2.setTypeid(null);
        good2.setGoodsback(null);
        good2.setDistributeDate(null);
        goods.add(good2);
        request.setBizContent(bizContent);
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,RSA2,MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        BcssConsumeOrderModUpdateResponseV1 response = client.execute(request);

        Logger log = Logger
                .getLogger(BcssConsumeOrderUpdateTestV1.class
                        .getName());
        log.info(JSONObject.toJSONString(response));

    }

}
