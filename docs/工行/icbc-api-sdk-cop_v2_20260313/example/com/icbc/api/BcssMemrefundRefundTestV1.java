/**
 * 
 */
package com.icbc.api;

import java.math.BigDecimal;

import com.icbc.api.request.BcssMemrefundRefundRequestV1;
import com.icbc.api.request.BcssMemrefundRefundRequestV1.BcssMemrefundRefundRequestBizV1;
import com.icbc.api.response.BcssMemrefundRefundResponseV1;

/**
 * 
 * @author kfzx-lizm
 *
 */
public class BcssMemrefundRefundTestV1 {
    // MY_PRIVATE_KEY
    protected static final String MY_PRIVATE_KEY = "XXXXXXXXXXXXXXXXXX";
    // APIGW_PUBLIC_KEY
    protected static final String APIGW_PUBLIC_KEY = "XXXXXXXXXXXXXXXXXX";
    // APP_ID
    protected static final String APP_ID = "1*************************6";
    
    protected static final String RSA = "RSA";
    protected static final String RSA2 = "RSA2";

    public static void main(String[] args) throws Exception {
        BcssMemrefundRefundTestV1 a = new BcssMemrefundRefundTestV1();
        a.testCop();
    }

    public void testCop() throws IcbcApiException {
        BcssMemrefundRefundRequestV1 request = new BcssMemrefundRefundRequestV1();
        request.setServiceUrl("http://***.***.***.***:****/api/bcss/memRefund/refund/V1");
        BcssMemrefundRefundRequestBizV1 bizContent = new BcssMemrefundRefundRequestBizV1();
        bizContent.setManufacturerId("V***********1");
        bizContent.setCorpId("0***********3");
        bizContent.setImeiNo("0*************2");
        bizContent.setTimeStamp("2019-03-05 20:25:33");
        bizContent.setClientTransNo("0*******************9");
        bizContent.setClientType("3");
        bizContent.setOrderNo("ME*****************************5");
        bizContent.setOtherData("");
        bizContent.setMerNo("");
        bizContent.setRefundMethod("1");
        bizContent.setRecheckteller("");
        bizContent.setRefundType("2");
        bizContent.setRefundAmt(new BigDecimal(100));
        bizContent.setMsgFlag("0");
        bizContent.setOptionType("");
        request.setBizContent(bizContent);
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,RSA2,MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

        BcssMemrefundRefundResponseV1 refundRefundResponseV1=client.execute(request);
        System.out.println(refundRefundResponseV1);
    }
    
}
