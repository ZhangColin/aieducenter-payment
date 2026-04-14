package com.icbc.api;
/**
 * 
 */

import com.icbc.api.request.EiopTaskinfoApproveQueryRequestV1;
import com.icbc.api.response.EiopTaskinfoApproveQueryResponseV1;

/**
 * 
 * @author kfzx-zhaoc
 *
 */
public class EiopTaskinfoApproveQueryTestV1 {

    private  static final Logger logger = LoggerFactory.getLogger(EiopTaskinfoApproveQueryTestV1.class);

    // MY_PRIVATE_KEY
    protected static final String MY_PRIVATE_KEY = "XXXXXXXXXXXXXXXXXX";
    // APIGW_PUBLIC_KEY
    protected static final String APIGW_PUBLIC_KEY = "XXXXXXXXXXXXXXXXXX";
    // APP_ID
    protected static final String APP_ID = "1*************************6";

    protected static final String RSA = "RSA";
    protected static final String RSA2 = "RSA2";

    public static void main(String[] args) throws Exception {
        EiopTaskinfoApproveQueryTestV1 a = new EiopTaskinfoApproveQueryTestV1();
        a.test_cop();
    }

    public  void test_cop() throws IcbcApiException {
        EiopTaskinfoApproveQueryRequestV1 request = new EiopTaskinfoApproveQueryRequestV1();
        EiopTaskinfoApproveQueryRequestV1.EiopTaskinfoApproveQueryRequestBizV1 biz = new EiopTaskinfoApproveQueryRequestV1.EiopTaskinfoApproveQueryRequestBizV1();
        biz.setSerialno("15164784514");
        request.setBizContent(biz);
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,RSA2,MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        EiopTaskinfoApproveQueryResponseV1 res = client.execute(request);
        logger.info(res);
    }
    
}
