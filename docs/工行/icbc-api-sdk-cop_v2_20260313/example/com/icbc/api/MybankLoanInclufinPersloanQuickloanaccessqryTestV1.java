package com.icbc.api;

import com.icbc.api.request.MybankLoanInclufinPersloanQuickloanaccessqryRequestV1;
import com.icbc.api.response.MybankLoanInclufinPersloanQuickloanaccessqryResponseV1;

/**
 * @ClassName
 * @Descirption 经营快贷开通结果查询
 * @Author kfzx-shenkz
 */
public class MybankLoanInclufinPersloanQuickloanaccessqryTestV1 {
    //appid,privateKey,apigwPublicKey  根据实际的应用场景进行修改
    protected static final String appId = "123";

    protected static final String privateKey = "actualprivateKeyValue";
    protected static final String apigwPublicKey = "actualpublicKeyValue+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
    public static void main(String[] args) throws IcbcApiException {
        DefaultIcbcClient client = new DefaultIcbcClient(appId, privateKey, apigwPublicKey);
        MybankLoanInclufinPersloanQuickloanaccessqryRequestV1 request = new MybankLoanInclufinPersloanQuickloanaccessqryRequestV1();

        request.setServiceUrl("http://ip:port/api/mybank/loan/inclufin/persloan/quickloanaccessqry/V1");
        MybankLoanInclufinPersloanQuickloanaccessqryRequestV1.MybankLoanInclufinPersloanQuickloanaccessqryV1Biz param = new MybankLoanInclufinPersloanQuickloanaccessqryRequestV1.MybankLoanInclufinPersloanQuickloanaccessqryV1Biz();
        //公共参数
		param.setSerialNo("12334455");
		param.setAppNo("F-APIP");
		param.setAreaCode("");
		param.setEmployeeCode("");
		param.setLanguage("zh_CN");
		param.setTransNo("IQuickOperLoanAppQryCocoaService");
		param.setVer("1.0");
		param.setTurnPageFlag("");
		param.setBeginRow("");
		param.setRowCount("");
		// 私有参数
		param.setAppid("12334455");
		param.setType("01");
		param.setCustID("12334455");
		param.setModelID("ZH0483");
		param.setCooperUnit("12334455");

//        System.out.println("-------经营快贷开通结果查询 入参--------" + param);
        request.setBizContent(param);
        MybankLoanInclufinPersloanQuickloanaccessqryResponseV1 response = new MybankLoanInclufinPersloanQuickloanaccessqryResponseV1();
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
