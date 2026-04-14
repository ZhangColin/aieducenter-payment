package com.icbc.api;


import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcConstants;
import com.icbc.api.UiIcbcClient;
import com.icbc.api.request.GyjrB2bFinanceGroupLoanQueryRequestV2;

/**
 * @ClassName GyjrB2bFinanceGroupLoanQueryRequestV2Test
 * @Description TODO
 * @author: kfzx-zhangy19
 * @create: 2023/8/10 10:51
 **/
public class GyjrB2bFinanceGroupLoanQueryRequestV2Test {
    //签名私钥
    protected static String MY_PRIVATE_KEY = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
    //网关公钥
    protected static final String APIGW_PUBLIC_KEY = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
    //合作方登记在门户或内管中的app编号
    protected static String APP_ID = "11111111";

    public static void main(String[] args) throws IcbcApiException {

        String url = "http://ip:port/ui/gyjr/b2b/finance/group/loan/query/V2";

        UiIcbcClient client = new UiIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2,MY_PRIVATE_KEY,IcbcConstants.CHARSET_UTF8);
        GyjrB2bFinanceGroupLoanQueryRequestV2 request = new GyjrB2bFinanceGroupLoanQueryRequestV2();
        request.setServiceUrl(url);
        GyjrB2bFinanceGroupLoanQueryRequestV2.GyjrB2bFinanceGroupLoanQueryRequestV2Biz bizContent = new GyjrB2bFinanceGroupLoanQueryRequestV2.GyjrB2bFinanceGroupLoanQueryRequestV2Biz();

        bizContent.setOp_version("1.0.0");
        bizContent.setProtocol_code("IFSBBB202107303232");
        bizContent.setCredit_no("102054341AAAAAA");
        bizContent.setSerial_no("201805131234567");

        request.setBizContent(bizContent);

        System.out.println(client.buildPostForm(request));

    }
}
