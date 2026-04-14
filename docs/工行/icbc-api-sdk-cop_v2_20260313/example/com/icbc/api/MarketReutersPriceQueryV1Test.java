import java.util.logging.Logger;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcConstants;
import com.icbc.api.request.MarketReutersPriceQueryRequestV1;
import com.icbc.api.request.MarketReutersPriceQueryRequestV1.MarketReutersPriceQueryRequestV1Biz;
import com.icbc.api.response.MarketReutersPriceQueryResponseV1;


public class MarketReutersPriceQueryV1Test {

    //	调用方自己的AppId
    private static final String APP_ID = new String("10000000000004098019");
    //	调用方私钥
    protected static final String MY_PRIVATE_KEY = new String("7d087a12cc3c90cc4da6fe10d0c833bfaac915c581b26df9a0cfd9e42408ddad");
    //	API网关公钥 囯密
   // protected static final String APIGW_PUBLIC_KEY = new String("-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB");
      protected static final String APIGW_PUBLIC_KEY = "04677AF1CFC1E107D3A2EA3EA36B3E3CDAC2E8AB8C40453B638C7CBA1832928253FAD8AA556254287054BF5B0FD4AE5BB603901CFE20B22F138130EBCB5507E3BB";
    //	API请求地址 互联网访问 应使用行外集群
    private static final String API_SERVICE_URL = new  String("http://ip:port/api");

    public static void main(String[] args) {

        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID
               , IcbcConstants.SIGN_TYPE_SM2
                ,MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        MarketReutersPriceQueryRequestV1 request = new MarketReutersPriceQueryRequestV1();


        request.setServiceUrl(API_SERVICE_URL +"/market/reuters/price/query/V1");

        MarketReutersPriceQueryRequestV1Biz bizContent = new MarketReutersPriceQueryRequestV1Biz();

        String msgId="BF-CPLEASE(ICBCLE)"+ System.currentTimeMillis();

        bizContent.setAppName("BF-CPLEASE(ICBCLE)");
        bizContent.setRicCode("SHICNYOND=&&SHICNYSWD=");
        bizContent.setSeq(msgId);

        request.setBizContent(bizContent);

        Logger log = Logger.getLogger(MarketReutersPriceQueryV1Test.class.getName());
        MarketReutersPriceQueryResponseV1 response = new MarketReutersPriceQueryResponseV1();
        try {
            response = client.execute(request, msgId);
            log.info("Msg_id:" + response.getMsgId());
            if (response.isSuccess()) {

                log.info("ReturnCode:" + response.getReturnCode());
                log.info("ReturnMsg:" + response.getReturnMsg());
                log.info("response:" + response.getData().toString());
            } else {

                log.info("ReturnCode:" + response.getReturnCode());
                log.info("ReturnMsg:" + response.getReturnMsg());
                log.info("response:" + response);
            }
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }
}
