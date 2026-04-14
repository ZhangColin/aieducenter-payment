package com.icbc.api;

import com.icbc.api.request.MybankLoanInclufinPersloanQuickloanaccessqryRequestV2;
import com.icbc.api.response.MybankLoanInclufinPersloanQuickloanaccessqryResponseV2;

/**
 * @ClassName
 * @Descirption 经营快贷开通结果查询
 * @Author kfzx-shenkz
 */
public class MybankLoanInclufinPersloanQuickloanaccessqryTestV2 {
    //appid,privateKey,apigwPublicKey  根据实际的应用场景进行修改
    protected static final String appId = "123";

    protected static final String privateKey = "actualprivateKeyValue";
    protected static final String apigwPublicKey = "actualpublicKeyValue+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
    public static void main(String[] args) throws IcbcApiException {
        DefaultIcbcClient client = new DefaultIcbcClient(appId, privateKey, apigwPublicKey);
        MybankLoanInclufinPersloanQuickloanaccessqryRequestV2 request = new MybankLoanInclufinPersloanQuickloanaccessqryRequestV2();

        request.setServiceUrl("http://ip:port/api/mybank/loan/inclufin/persloan/quickloanaccessqry/V2");
        MybankLoanInclufinPersloanQuickloanaccessqryRequestV2.MybankLoanInclufinPersloanQuickloanaccessqryV2Biz param = new MybankLoanInclufinPersloanQuickloanaccessqryRequestV2.MybankLoanInclufinPersloanQuickloanaccessqryV2Biz();
        //公共参数
		param.setSerialNo("20190624152356888800135");
		param.setAppNo("F-APIP");
		param.setLanguage("zh_CN");
		param.setTransNo("IQuickOperLoanAppQryCocoaService");
		param.setVer("1.0");
		// 私有参数
		param.setAppid("123");
		param.setType("01");
		param.setCustID("E00000011006");
		param.setModelID("ZH1107");
		param.setCooperUnit("DW0101045");

//        System.out.println("-------经营快贷开通结果查询 入参--------" + param);
        request.setBizContent(param);
        MybankLoanInclufinPersloanQuickloanaccessqryResponseV2 response = new MybankLoanInclufinPersloanQuickloanaccessqryResponseV2();
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
