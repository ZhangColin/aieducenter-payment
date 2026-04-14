package com.icbc.api;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.internal.util.internal.util.fastjson.JSONArray;
import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.MallbUserDigitalredpacketSendRequestV1;
import com.icbc.api.request.MallbUserDigitalredpacketSendRequestV1.MallbUserDigitalredpacketSendRequestV1Biz;
import com.icbc.api.response.MallbUserDigitalredpacketSendResponseV1;


public class MallbUserDigitalredpacketSendTestV1 {
    // 1、网关公钥
    protected static final String APIGW_PUBLIC_KEY = "[需替换]工行API网关公钥，请向工行联系获取";
    // 2、appid
    protected static final String APP_ID = "[需替换]APP的编号,应用在API开放平台注册时生成";
    // 3、密钥对认证方式，公钥在API平台登记，此处为私钥
    protected static final String MY_PRIVATE_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";
    // 4、工行api网关地址
    public static final String host = "[需替换]工行API网关地址";
    // 5、接口地址
    public static final String apiUrl= host+ "/api/mallb/user/digitalredpacket/send/V1";;
    // 6、应用私钥
    public static final String encryptKey = "[需替换]应用方加密串，与工行API开放平台一致";

    public static void main(String[] args) throws Exception {
        //签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, "AES", encryptKey, "", "");
        //4、根据测试环境和生产环境替换相应ip和端口
        //5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
        MallbUserDigitalredpacketSendRequestV1 request = new MallbUserDigitalredpacketSendRequestV1();
        request.setServiceUrl(host + apiUrl);
        MallbUserDigitalredpacketSendRequestV1.MallbUserDigitalredpacketSendRequestV1Biz bizContent = new MallbUserDigitalredpacketSendRequestV1.MallbUserDigitalredpacketSendRequestV1Biz();
        JSONArray ja = new JSONArray();
        JSONObject kmsg1 = new JSONObject();
        kmsg1.put("username","张三");
        kmsg1.put("phoneno","133xxxx3333");
        kmsg1.put("amount","50");
        JSONObject kmsg2 = new JSONObject();
        kmsg2.put("username","李四");
        kmsg2.put("phoneno","1444xxxx444");
        kmsg2.put("amount","150");
        ja.add(kmsg1);
        ja.add(kmsg2);
        bizContent.setDetail(ja);
        bizContent.setActcode("act****00001");
        bizContent.setPartnerflag("4");
        bizContent.setPartnerid("1490xxxx0001");
        bizContent.setPartnername("xx外卖");
        bizContent.setSummary("无");
        bizContent.setAppid(APP_ID);
        request.setBizContent(bizContent);
        MallbUserDigitalredpacketSendResponseV1 response;
        try {
            response = (MallbUserDigitalredpacketSendResponseV1) client.execute(request, System.currentTimeMillis() + "");//msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一
            if (response.isSuccess()) {
                //6、业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
                System.out.println("ReturnCode:"+response.getReturnCode());
                System.out.println("response:" + response);
            } else {
                //失败
                System.out.println("ReturnCode:"+response.getReturnCode());
                System.out.println("ReturnMsg:"+response.getReturnMsg());
            }
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }
}
