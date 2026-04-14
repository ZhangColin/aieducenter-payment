/**
 * 
 */
package com.icbc.api;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.BcssTradeDetailQueryRequestV1;
import com.icbc.api.request.BcssTradeDetailQueryRequestV1.BcssTradeDetailQueryRequestBizV1;
import com.icbc.api.response.BcssTradeDetailQueryResponseV1;

/**
 * 
 * @author kfzx-lizm
 *
 */
public class BcssTradeDetailQueryTestV1 {
    // MY_PRIVATE_KEY
    protected static final String MY_PRIVATE_KEY = "XXXXXXXXXXXXXXXXXX";
    // APIGW_PUBLIC_KEY
    protected static final String APIGW_PUBLIC_KEY = "XXXXXXXXXXXXXXXXXX";
    // APP_ID
    protected static final String APP_ID = "1*************************6";
    
    protected static final String RSA = "RSA";
    protected static final String RSA2 = "RSA2";

    public static void main(String[] args) throws Exception {
        BcssTradeDetailQueryTestV1 a = new BcssTradeDetailQueryTestV1();
        a.test_cop();
    }

    public void test_cop() throws IcbcApiException {
        BcssTradeDetailQueryRequestV1 request = new BcssTradeDetailQueryRequestV1();
        request.setServiceUrl("http://***.***.***.***:****/api/bcss/tradeDetail/query/V1");
        BcssTradeDetailQueryRequestBizV1 bizContent = new BcssTradeDetailQueryRequestBizV1();
        bizContent.setTimeStamp("2019-03-05 20:25:33");
        bizContent.setClientTransNo("0*************************9");
        bizContent.setClientType("3");
        bizContent.setManufacturerId("V***********1");
        bizContent.setCorpId("0***********3");
        bizContent.setMerNo("");
        bizContent.setImeiNo("0************1");
        bizContent.setStartdate("");
        bizContent.setEnddate("");
        bizContent.setPagenum("1");
        bizContent.setSearchnum("10");
        bizContent.setOtherData("");
        bizContent.setOrderNo("M***************************************5");
        bizContent.setSearchType("");

        request.setBizContent(bizContent);
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,RSA2,MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        client.execute(request);
    }
    
}
