package com.icbc.api;

import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcConstants;
import com.icbc.api.UiIcbcClient;
import com.icbc.api.request.GyjrB2bBillApplyconfirmcontractRequestV1;

import java.util.ArrayList;
import java.util.List;

public class GyjrB2bBillApplyconfirmcontractRequestV1Test {
    protected static final String APP_ID = "10000000000004097933";
    // 应用私钥(变量值需替换)
    protected static String MY_PRIVATE_KEY = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
    // API网关公钥(变量值需替换)
    protected static String APIGW_PUBLIC_KEY = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";

    public void test_request() {
        try {
            UiIcbcClient client = new UiIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY,"UTF-8");
            GyjrB2bBillApplyconfirmcontractRequestV1 request = new GyjrB2bBillApplyconfirmcontractRequestV1();
            // 根据实际环境替换相应的域名
            request.setServiceUrl("https://ip:port/ui/gyjr/b2b/bill/applyconfirmcontract/V1");
            GyjrB2bBillApplyconfirmcontractRequestV1.GyjrB2bBillApplyconfirmcontractRequestV1Biz bizContent = new GyjrB2bBillApplyconfirmcontractRequestV1.GyjrB2bBillApplyconfirmcontractRequestV1Biz();


            //请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
            bizContent.setProtocolCode("124123jiomr123");
            bizContent.setPlatVendorId("2183o71u3");
            bizContent.setTradeName("applyconfirmcontract");
            bizContent.setTradeVersion("V1");
            bizContent.setCallBackUrl("http://ip:port/");
            bizContent.setPlatBusiSeq("31525481515");
            bizContent.setPlatBatSerialNo("31525");
            bizContent.setContractNo("1213213312");
            bizContent.setAccountNo("12132");
            request.setBizContent(bizContent);
            String s = client.buildPostForm(request);
            System.out.println(s);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
