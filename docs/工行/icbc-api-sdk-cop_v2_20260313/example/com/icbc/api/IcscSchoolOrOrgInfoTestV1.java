package com.icbc.api;


import com.icbc.api.request.IcscSchoolOrOrgInfoRequestV1;
import com.icbc.api.response.IcscSchoolOrOrgInfoResponseV1;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class IcscSchoolOrOrgInfoTestV1 {

    protected static final String MY_PRIVATE_KEY = "此处为私钥串";
    protected static final String APIGW_PUBLIC_KEY = "此处为网关公钥串";
    protected static final String APP_ID = "此处为APP_ID";

    public static void main(String[] args) {

        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8);
        IcscSchoolOrOrgInfoRequestV1 request = new IcscSchoolOrOrgInfoRequestV1();
        request.setServiceUrl("https://ip:port/api/icsc/schoolOrOrgInfo/V1");
        IcscSchoolOrOrgInfoRequestV1.IcscSchoolOrOrgInfoRequestV1Biz bizContent = new IcscSchoolOrOrgInfoRequestV1.IcscSchoolOrOrgInfoRequestV1Biz();

        bizContent.setCorpSerno(APP_ID + get16UUID());
        //appId
        bizContent.setAppId(APP_ID);
        bizContent.setCorpNo("123123123123123");
        bizContent.setProvince("530000");
        bizContent.setCity("530100");
        bizContent.setCounty("530102");
        bizContent.setAccountOrgNo("0000000001");
        bizContent.setSchoolOrOrgId("123123123");
        bizContent.setSchoolOrOrgName("学校A");
        bizContent.setStatus("0");

        request.setBizContent(bizContent);
        IcscSchoolOrOrgInfoResponseV1 response;
        try {
            //msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一
            response = client.execute(request, String.valueOf(System.currentTimeMillis()));
            if (response.isSuccess()) {
                //业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
                System.out.println("ReturnCode: " + response.getReturnCode());
                System.out.println("ReturnMsg: " + response.getReturnMsg());
            } else {
                //失败
                System.out.println("ReturnCode:" + response.getReturnCode());
                System.out.println("ReturnMsg:" + response.getReturnMsg());
            }
        } catch (IcbcApiException e) {
            System.out.println(e.getErrCode());
        }
    }

    public static String get16UUID() {
        SimpleDateFormat sdf = new SimpleDateFormat("MMdd");
        String dayTime = sdf.format(new Date());
        int hashCode = UUID.randomUUID().toString().hashCode();
        if (hashCode < 0) {
            hashCode = -hashCode;
        }
        return "01" + dayTime + String.format("%010d", hashCode);
    }
}
