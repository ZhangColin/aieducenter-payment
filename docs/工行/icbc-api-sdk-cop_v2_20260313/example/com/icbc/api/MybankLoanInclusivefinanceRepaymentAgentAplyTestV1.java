package com.icbc.api;

import com.icbc.api.request.MybankLoanInclusivefinanceRepaymentAgentAplyRequestV1;
import com.icbc.api.response.MybankLoanInclusivefinanceRepaymentAgentAplyResponseV1;

/**
 * @ClassName
 * @Descirption 合作方代还款服务测试类
 * @Author kfzx-chenz
 */
public class MybankLoanInclusivefinanceRepaymentAgentAplyTestV1 {
    //appid,privateKey,apigwPublicKey  根据实际的应用场景进行修改
    protected static final String appId = "123";

    protected static final String privateKey = "actualprivateKeyValue";
    protected static final String apigwPublicKey = "actualpublicKeyValue+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
    public static void main(String[] args) throws IcbcApiException {
        DefaultIcbcClient client = new DefaultIcbcClient(appId, privateKey, apigwPublicKey);
        MybankLoanInclusivefinanceRepaymentAgentAplyRequestV1 request = new MybankLoanInclusivefinanceRepaymentAgentAplyRequestV1();

        request.setServiceUrl("http://ip:port/api/mybank/loan/inclusivefinance/repaymentagentaply/V1");
        MybankLoanInclusivefinanceRepaymentAgentAplyRequestV1.MybankLoanInclusivefinanceRepaymentAgentAplyV1Biz param = new MybankLoanInclusivefinanceRepaymentAgentAplyRequestV1.MybankLoanInclusivefinanceRepaymentAgentAplyV1Biz();
        //公共参数
		param.setSerialNo("12334455");
		param.setAppNo("F-APIP");
		param.setAreaCode("0170700014");
		param.setEmployeeCode("000393868");
		param.setAppid("123");
		param.setLanguage("zh_CN");
		param.setTransNo("IPerLoaReapymentCocoaService");
		param.setVer("2.0");
		param.setTurnPageFlag("0");
		param.setBeginRow("1");
		param.setRowCount("0");
		param.setCooperUnit("DY1707104");
		param.setDealType("1");
		param.setApplyNo("0000000000000020760");
		param.setEbankLoanApplyNo("0000000000000020760");
		param.setLnno("1");
		param.setModelId("ZH1458");
		param.setReturnAccountAreacode("02000260");
		param.setReturnAccountType("4");
		param.setReturnAccountName("燕燕网银数据1");
		param.setReturnAccountNo("0200000209213652642");
		param.setRepayEventNo("");
		param.setReturnAmt("50");

        request.setBizContent(param);
        MybankLoanInclusivefinanceRepaymentAgentAplyResponseV1 response = new MybankLoanInclusivefinanceRepaymentAgentAplyResponseV1();
        try {
            response = client.execute(request);
        } catch (Exception e) {
//            System.out.println(e.getMessage());
        }
        if (response.isSuccess()) {
//            System.out.println("success!");
        } else {
//            System.out.println("fail" + response.getReturnMsg());
        }
//        System.out.println(response);
    }


}
