package com.icbc.api;
/**
 * 
 */

import com.icbc.api.request.EiopTaskinfoApproveOperRequestV1;
import com.icbc.api.request.EiopTaskinfoApproveQueryRequestV1;
import com.icbc.api.response.EiopTaskinfoApproveOperResponseV1;
import com.icbc.api.response.EiopTaskinfoApproveQueryResponseV1;

/**
 * 
 * @author kfzx-zhaoc
 *
 */
public class EiopTaskinfoApproveOperTestV1 {

    private  static final Logger logger = LoggerFactory.getLogger(EiopTaskinfoApproveOperTestV1.class);

    // MY_PRIVATE_KEY
    protected static final String MY_PRIVATE_KEY = "XXXXXXXXXXXXXXXXXX";
    // APIGW_PUBLIC_KEY
    protected static final String APIGW_PUBLIC_KEY = "XXXXXXXXXXXXXXXXXX";
    // APP_ID
    protected static final String APP_ID = "1*************************6";
    
    protected static final String RSA = "RSA";
    protected static final String RSA2 = "RSA2";

    public static void main(String[] args) throws Exception {
        EiopTaskinfoApproveOperTestV1 a = new EiopTaskinfoApproveOperTestV1();
        a.test_cop();
    }

    public  void test_cop() throws IcbcApiException {
        EiopTaskinfoApproveOperRequestV1 request = new EiopTaskinfoApproveOperRequestV1();
        EiopTaskinfoApproveOperRequestV1.EiopTaskinfoApproveOperRequestBizV1 bizContent = new EiopTaskinfoApproveOperRequestV1.EiopTaskinfoApproveOperRequestBizV1();
        request.setServiceUrl("http://***.***.***.***:****/api/eiop/taskinfo/approve/oper/V1");
        bizContent.setHandleFlag("1");
        bizContent.setFuncId("001");
        bizContent.setAreaCode("01204");
        bizContent.setOperTeller("1");
        bizContent.setTaskOper("1");
        bizContent.setBankgrade("E1546118");
        request.setBizContent(bizContent);
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,RSA2,MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        EiopTaskinfoApproveOperResponseV1 res = client.execute(request);
        logger.info(res);
    }
    
}
