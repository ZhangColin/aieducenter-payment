package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.MybankMessageEinfoOuternonconmsgRequestV1;
import com.icbc.api.response.CustomerCustomDsfApiServiceResponseV1;
import com.icbc.api.response.MybankMessageEinfoOuternonconmsgResponseV1;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class MybankMessageEinfoOuternonconmsgV1Test {

    // 1、网关公钥
    protected static final String APIGW_PUBLIC_KEY = "[需替换]工行API网关公钥，请向工行联系获取";
    // 2、appid
    protected static final String APIGW_APPID = "";//例如 10000000000000002697
    // 3、密钥对认证方式，公钥在API平台登记，此处为私钥
    protected static final String MY_PRIVATE_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";

    public static void main(String[] args) {
        // 签名类型为RSA2时，需传入APIGW-APPID，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
        DefaultIcbcClient client = new DefaultIcbcClient(APIGW_APPID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY,
                APIGW_PUBLIC_KEY);

        MybankMessageEinfoOuternonconmsgRequestV1 request = new MybankMessageEinfoOuternonconmsgRequestV1();
        // 请根据测试环境和生产环境替换相应ip和端口
        request.setServiceUrl("http://ip:port/mybank/message/einfo/outernonconmsg/V1");

        MybankMessageEinfoOuternonconmsgRequestV1.MybankMessageEinfoOuternonconmsgRequestV1Biz bizContent = new MybankMessageEinfoOuternonconmsgRequestV1.MybankMessageEinfoOuternonconmsgRequestV1Biz();
        MybankMessageEinfoOuternonconmsgRequestV1.PublicInfo publicInfo = new MybankMessageEinfoOuternonconmsgRequestV1.PublicInfo();
        MybankMessageEinfoOuternonconmsgRequestV1.PrivateInfo privateInfo = new MybankMessageEinfoOuternonconmsgRequestV1.PrivateInfo();
        MybankMessageEinfoOuternonconmsgRequestV1.Message message = new MybankMessageEinfoOuternonconmsgRequestV1.Message();
        MybankMessageEinfoOuternonconmsgRequestV1.AuxMessage auxMessage = new MybankMessageEinfoOuternonconmsgRequestV1.AuxMessage();

        publicInfo.setVersion("1.0.0");
        publicInfo.setAppid("F-EINFO");
        publicInfo.setEventid("123514645634563");
        publicInfo.setEventtype("api");
        publicInfo.setTimestamp(String.valueOf(new Date().getTime()));

        privateInfo.setBusstype("BFTP001");
        privateInfo.setTempno("NONE");
        privateInfo.setSendchannel("1");
        privateInfo.setPolicyno("PIPGW002");
        privateInfo.setAreacode("00200");

        List<HashMap<String, String>> sendInfo = new ArrayList<>();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("cisno", "001388985690667442143312216064764");
        hashMap.put("phone", "13988791111");
        hashMap.put("cardno", "6212260200024976512");
        hashMap.put("accno", "");
        sendInfo.add(hashMap);
        privateInfo.setSendInfo(sendInfo);

        message.setServiceId("90000002");
        message.setMsgType("accountChange");
        message.setMsgTitle("余额变动");
        message.setMsgSubtype("1");
        privateInfo.setMessage(message);

        List<HashMap<String, String>> buttons = new ArrayList<>();
        HashMap<String, String> button = new HashMap<>();
        button.put("desc", "账户明细");
        button.put("forceLoginFlag", "false");
        button.put("targetMenuID", "checkRegAndGuide");
        button.put("targetParams", "cardNum=6212260200024976512&needBackFlag=1&jumpMenuID=account_detail_new&jumpParam=Y2FyZE51bVRtcD02MjEyMjYwMjAwMDI0OTc2NTEyJm5lZWRCYWNrRmxhZz0x");
        buttons.add(button);
        auxMessage.setButtons(buttons);
        privateInfo.setAuxMessage(auxMessage);

        bizContent.setPrivateInfo(privateInfo);
        bizContent.setPublicInfo(publicInfo);

        //创建返回
        MybankMessageEinfoOuternonconmsgResponseV1 responseV1 = new MybankMessageEinfoOuternonconmsgResponseV1();
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
            String msg_id = dateFormat.format(new Date());
            responseV1 = (MybankMessageEinfoOuternonconmsgResponseV1)client.execute(request,msg_id);
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
        System.out.println("responseV1"+ JSONObject.toJSONString(responseV1));
    }
}
