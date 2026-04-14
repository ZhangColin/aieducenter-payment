package com.icbc.api;


import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.request.BcssMemOnlineOrderQueryRequestV1;
import com.icbc.api.response.BcssMemOnlineOrderQueryResponseV1;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @Auther kfzx-wangsj04
 * @Description
 * @Date 2020/4/28
 */

public class BcssMemOnlineOrderQueryTestV1 {
    protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCoATMP53WDxwM/3OwzCwsSsD7zWKhfDIpzS9Mh7+c6uKfg4Z8exgiQk7SALOu17khd+B+3NLcr0EDm9r6IRPxYqMyF/J+XA4flnKNMQwZVpG5FBViwLNgNRq8fD8Op7Rvom2lTZoFR9FVnprYDMXkq6ctXs5ZILG9LbXKPyuKHPPOAXevpjC8e6GLCDt2htJjK3MpiGh+A4ujsezZsT3buLZhdDlvFMoKVcly7qn/dkCZNG8c5MzPnPo4+hLpvx3gISAfNxFfdqxAnLEMWEtpxnTg4R7bdWMeyxMy7zWWjgQRJ5Dh/GSv2CWoI4ihFqB48QEyz6Eugb7L0jP8NyhQ5AgMBAAECggEBAImrsfQub5X01yzWFJgfTIub8XnoBNEerbWdRP5iYNE10Bvvxzyoeww+fYMrTFxhTtvyH8s5X4B/vy+Ess1n+oGMkUyanwxY0FvP5+Dua5pyjAnzP6HenY0Dhy2k9Wmh62TJNZ4qOX63AeVWL4CeYfRQcVkEyPmLsAfXQDGkx8yZSYPllLxK7x4E7byshoOgh2BfYv+xI0L205LEntW5OdJ4b1hJeVv93sEPThZYmALH9p1IVz6tk3mrjGo3NmeRSNMy3p7YKsGYJdFe0AFZk441IOt0pc4+ds9ia/VYpXoC6qiY99Dz/ExLwp6NxnpVKPtri9NasTPA1Mu+AmAfDoECgYEA2dbNzY9yli8eU9aU8EAS2Pvhul76B4Qfj1fwcHZGV/v/9SOFkDYrx9OXfyrWH1m/QmBi2J9VOMFQzu5UtfyVDGoJHrDSZlotfNmS6+etIe3MVHOwlSirFLm1kKvoZtBYOs6bNF+7e/KqQCqd+ficVz732Hcm/4VIm3Pa9L71qf0CgYEAxW+GnrSOxReov6Ett+pZqOxZvE9zq+SwofPqrNGnkuWVPdvLcnzNK7fVruZVn82H7naMEFEorVs+++HYiGccEkjh0IICLmgiSHmfb4K0yg7aUC7OzX1wUNykoaeckEwLl26H5hAhyma2iTHRcmjUPKvOHuWCZBmxoi2CfrRqGe0CgYEA03QvUdps4FvclIU3Df8kTHw0ITUQfztBZ2N1gbVNkxqK2uogPFxZIx/Bby+lqcj0Xvow+RWxI+aDCltO31jPVb2YHLKHidL/VLHebB/3b3flYBTs090mNHgQRt6YOTUEcBEdlzlWRHrJyH+V2Y/6MrZs3zLKKD64vC053VmAHgECgYB89XxHoFGoTrQgLRwphiqfLpARDlxQlBpzNlT2cm9UGvidQSp/2JtmqyCUZPYWgVgiRmeZwDQ1nEW92BBbLJ9otzYs6MwGoSPpqQiO5DQWmtXz39xF1LmshCmyosoFzgucGXnr05P/bYjkRmKgvdZuW/LGPU6MmnKCCbnJBdkNdQKBgQCDeF0GUWOjjkqB88gforfgTvqOl7Q2mGvCiDH8x44IHzToDPfbnjJSjMQZy/+PCnYVKskukPvzD2ck8iVoJgVD42C39k0VYuPqNt5/zb5/eel+XsypXH5BnLX15edPHNpV9IterkPuAHAnAiKurm9fOfER5EITkXYEYAPkeCyoJg==";
    protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

    protected static final String APP_ID = "xxxxxxxxxxxxxx";
    protected static final String RSA = "RSA";
    protected static final String RSA2 = "RSA2";

    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        BcssMemOnlineOrderQueryTestV1 a = new BcssMemOnlineOrderQueryTestV1();
        a.test_orderQuery();

    }

    private void test_orderQuery() throws IcbcApiException {
        BcssMemOnlineOrderQueryRequestV1 requestV1 = new BcssMemOnlineOrderQueryRequestV1();
        requestV1.setServiceUrl("http://xxxxxxxx:xxxx/api/memonline/order/query/V1");
        BcssMemOnlineOrderQueryRequestV1.BcssMemOnlineOrderQueryRequestV1Biz biz = new BcssMemOnlineOrderQueryRequestV1.BcssMemOnlineOrderQueryRequestV1Biz();
        //JSONObject searchInfo = new JSONObject();
        //searchInfo.put("dt","4");
        //searchInfo.put("orderNo","xxxxxxxxxxxx");
        //searchInfo.put("startDate","2020-01-01");
        //searchInfo.put("endDate","2020-01-01");
        //searchInfo.put("dt","1");
        //searchInfo.put("custSort", "0");
        //searchInfo.put("custCode", "441622198009190796");
        //searchInfo.put("custName", "xxxx");
        biz.setCorpId("xxxxxxxxxxx");
        biz.setPagenum(2);
        biz.setorderSrc("3");
        biz.setClientTransNo( new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));
        biz.setMemCardType("001");
        biz.setMerNo(null);
        biz.setOnlineFlag("001");
//        biz.setSearchInfo(searchInfo.toJSONString());
        biz.setTimeStamp("2020-04-28 15:33:00");
        requestV1.setBizContent(biz);
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, RSA2, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        client.execute(requestV1);

    }

}
