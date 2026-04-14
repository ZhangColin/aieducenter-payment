package com.icbc.api;

import com.icbc.api.request.IcscSmallPayRequestV1;
import com.icbc.api.response.IcscSmallPayResponseV1;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class IcscSmallPayTestV1 {

    protected static final String MY_PRIVATE_KEY = "此处为私钥串";
    protected static final String APIGW_PUBLIC_KEY = "此处为网关公钥串";
    protected static final String APP_ID = "此处为APP_ID";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

        IcscSmallPayRequestV1 request = new IcscSmallPayRequestV1();
        request.setServiceUrl("https://{网关域名}/api/icsc/pay/small/V1");
        IcscSmallPayRequestV1.IcscSmallPayRequestV1Biz bizContent = new IcscSmallPayRequestV1.IcscSmallPayRequestV1Biz();

        bizContent.setCorpSerno(APP_ID + get16UUID());
        //appId 平台商户标识：提交支付请求的平台商户编号
        bizContent.setAppId(APP_ID);
        bizContent.setCorpNo("10000000000004097038");
        //用户编号
        bizContent.setUserId("M20180111222326");
        //学校编号
        bizContent.setSchoolId("1231231");
        //学校名称
        bizContent.setSchoolName("学校1");
        //学生编号
        bizContent.setStudentId("1231231");
        //学生名称
        bizContent.setStudentName("张三");
        //子商户标识：提交支付请求的子商户编号
        bizContent.setOutVendorId("201809121");
        //订单编号
        bizContent.setOrderId("2099876345");
        //支付金额(元)整数长度不能超过8位，小数位不能超过2位
        bizContent.setPayAmount("1.01");
        //商品名称（长度单位：字节）
        bizContent.setGoodsName("XX商品");
        //用户签约的协议号
        bizContent.setExternalAgreementNo("12312321312");

        request.setBizContent(bizContent);

        IcscSmallPayResponseV1 response;
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
