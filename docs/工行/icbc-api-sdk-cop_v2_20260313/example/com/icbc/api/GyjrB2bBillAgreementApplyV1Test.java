package com.icbc.api;

import com.icbc.api.request.GyjrB2bBillAgreementApplyRequestV1;

public class GyjrB2bBillAgreementApplyV1Test {
    protected static final String APP_ID = "10000000000004097933";
    // 应用私钥(变量值需替换)
    protected static String MY_PRIVATE_KEY = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
    // API网关公钥(变量值需替换)
    protected static String APIGW_PUBLIC_KEY = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";

    public void test_request() {
        try {
            UiIcbcClient client = new UiIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY,"UTF-8");
            GyjrB2bBillAgreementApplyRequestV1 request = new GyjrB2bBillAgreementApplyRequestV1();
            // 根据实际环境替换相应的域名
            request.setServiceUrl("https://ip:port/ui/gyjr/b2b/bill/discountAgreementApply/V1");
            GyjrB2bBillAgreementApplyRequestV1. GyjrB2bBillAgreementApplyRequestV1Biz bizContent = new GyjrB2bBillAgreementApplyRequestV1. GyjrB2bBillAgreementApplyRequestV1Biz();


            //请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
            bizContent.setProtocolCode("124123jiomr123");
            bizContent.setPlatVendorId("2183o71u3");
            bizContent.setTradeName("discountAgreementApply");
            bizContent.setTradeVersion("V1");
            bizContent.setCallBackUrl("http://ip:port/");
            bizContent.setPartyAAcctsvcr("31525481515");
            bizContent.setPlatBatSerialNo("31525");
            bizContent.setPartyABranch("1213213312");
            bizContent.setPartyABranchName("12132");
            bizContent.setReg_no("12132");
            bizContent.setReg_type("12132");
            bizContent.setName("12132");
            request.setBizContent(bizContent);
            String s = client.buildPostForm(request);
            System.out.println(s);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
