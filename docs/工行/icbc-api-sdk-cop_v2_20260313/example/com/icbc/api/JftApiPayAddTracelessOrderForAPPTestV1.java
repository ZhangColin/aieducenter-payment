package com.icbc.api;

import com.alibaba.fastjson.JSONObject;
import com.icbc.api.request.JftApiPayAddTracelessOrderForAPPRequestV1;
import com.icbc.api.request.JftApiPayAddTracelessOrderForAPPRequestV1.JftApiPayAddTracelessOrderForAPPV1Biz;
import com.icbc.api.request.JftApiPayAddTracelessOrderForAPPRequestV1.SubOrderInfo;
import com.icbc.api.response.JftApiPayAddTracelessOrderForAPPResponseV1;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class JftApiPayAddTracelessOrderForAppTestV1 {
    protected static final String MY_PRIVATE_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";

    protected static final String APIGW_PUBLIC_KEY = "[需替换]工行API网关公钥，请向工行联系获取";

    protected static final String APP_ID = "[需替换]APP的编号";

    protected static final String AES_Key = "[需替换]应用方加密串，与工行API开放平台一致";


    public void test_cop() {

        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,"RSA2", MY_PRIVATE_KEY,"UTF-8",
                "json",APIGW_PUBLIC_KEY,"AES",AES_Key, "", "");

        JftApiPayAddTracelessOrderForAPPRequestV1 request = new JftApiPayAddTracelessOrderForAPPRequestV1();
        request.setServiceUrl("[需替换]工行API网关地址/api/jft/api/pay/app/add/traceless/order/V1");
        JftApiPayAddTracelessOrderForAPPV1Biz bizContent = new JftApiPayAddTracelessOrderForAPPV1Biz();

        bizContent.setAppId(APP_ID);//平台商户标识

        bizContent.setOutUserId("sh2");//用户标识
        bizContent.setNotifyUrl("http://www.xx.com");//商户通知URL:商户接收支付成功通知消息URL
        bizContent.setGoodsId("3212");//商品编号
        bizContent.setGoodsName("xx商品");//商品名称
        bizContent.setOutOrderId("2018082923908");//平台对接方订单ID
        bizContent.setPayType("02");//多订单支付
        bizContent.setMac("a3-b4-c5");//手机Mac
        bizContent.setTrxIp("××.××.××.××");//交易IP
        bizContent.setTrxIpCity("北京");//交易IP所在地
        bizContent.setTrxChannel("02");//交易渠道
        bizContent.setPayAmount("1.03");//支付金额(元)
        bizContent.setPayMode("01");//支付模式：微信：01  支付宝：12  云闪付：26
        bizContent.setVarNote("备注");//商户备注
        bizContent.setCustomerMobile("15512345678");//客户上送手机号
        List<SubOrderInfo> suborders = new ArrayList<>();
        SubOrderInfo suborder = new SubOrderInfo();
        suborder.setGoodsId("3212");//商品编号
        suborder.setGoodsName("xx商品");//商品名称
        suborder.setSubMerRateWx("600");//子商户服务费率（微信/十万分比）
        suborder.setSubMerRateZfb("600");//子商户服务费率（支付宝/十万分比）
        suborder.setSubMerRateOwn("600");//子商户服务费率（e支付本行卡/十万分比）
        suborder.setSubMerRateOther("600");//子商户服务费率（e支付它行卡/十万分比）
        suborder.setOutVendorId("10000000000000045006");//子商户标识号
        suborder.setPayAmount("1.03");//支付金额(元)
        suborder.setVarNote("备注");//商户备注
        suborder.setOutOrderId("2018082923909");//平台对接方订单ID
        suborder.setJDA("1.00");		//优惠金额
        suborder.setJD("1");			// 是否优惠
        suborder.setIW("1"); //是否即时解冻
        suborder.setCOW("0.01");//定额佣金金额(e支付本行卡)
        suborder.setCOT("0.01");//定额佣金金额(e支付它行卡)
        suborder.setCW("0.01");//定额佣金金额(微信支付)
        suborder.setCZ("0.01");//定额佣金金额(支付宝)

        suborders.add(suborder);
        bizContent.setSuborders(suborders);
        bizContent.setOutBusinessCode("10000000000000000001");//外部业务编码

        request.setBizContent(bizContent);

        JftApiPayAddTracelessOrderForAPPResponseV1 response;
        try {
            response = (JftApiPayAddTracelessOrderForAPPResponseV1) client.execute(request, System.currentTimeMillis()+"");

            System.out.println(JSONObject.toJSONString(response));
            if (response.getReturnCode() == 10100000) {
                // 业务成功处理
                System.out.println(response.getReturnCode());
            } else {
                // 失败
            }
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new JftApiPayAddTracelessOrderForAppTestV1().test_cop();
    }
}
