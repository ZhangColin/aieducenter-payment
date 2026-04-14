/**
 * 
 */
package com.icbc.api.request;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.request.BcssTradeInfoQueryRequestV1.BcssTradeInfoQueryRequestBizV1;

/**
 * 
 * @author kfzx-lizm
 *
 */
public class BcssTradeInfoQueryTestV1 {
    // MY_PRIVATE_KEY
    protected static final String MY_PRIVATE_KEY = "XXXXXXXXXXXXXXXXXX";
    // APIGW_PUBLIC_KEY
    protected static final String APIGW_PUBLIC_KEY = "XXXXXXXXXXXXXXXXXX";
    // APP_ID
    protected static final String APP_ID = "1*************************6";
    
    protected static final String RSA = "RSA";
    protected static final String RSA2 = "RSA2";

    public static void main(String[] args) throws Exception {
        BcssTradeInfoQueryTestV1 a = new BcssTradeInfoQueryTestV1();
        a.test_cop();
    }

    public void test_cop() throws IcbcApiException {
        BcssTradeInfoQueryRequestV1 request = new BcssTradeInfoQueryRequestV1();
        request.setServiceUrl("http://***.***.***.***:****/api/bcss/tradeInfo/query/V1");
        BcssTradeInfoQueryRequestBizV1 bizContent = new BcssTradeInfoQueryRequestBizV1();
        bizContent.setManufacturerId("V***********1");
        bizContent.setCorpId("0***********3");
        bizContent.setImeiNo("0*************2");
        bizContent.setMerNo("");
        bizContent.setTimeStamp("2019-03-05 20:25:33");
        bizContent.setClientTransNo("0*************************9");
        bizContent.setClientType("3");
        bizContent.setSearchType("1");
        bizContent.setOrderNo("M***************************************5");
        bizContent.setStartdate("");
        bizContent.setEnddate("");
        bizContent.setPagenum("1");
        bizContent.setSearchnum("10");
        bizContent.setOtherData("");
        request.setBizContent(bizContent);
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,RSA2,MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        client.execute(request);
    }
    
}
