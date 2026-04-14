package com.icbc.api;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.icbc.apip.config.SdkUtil;
import com.icbc.apip.invoker.DefaultInvoker;
import com.icbc.apip.invoker.Invoker;
import com.icbc.apip.invoker.Invokers;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class WecomPushMessageV1 {


    public static void main(String[] args) throws Exception {
        //在resources文件夹下config文件夹中读取配置文件（包括公私钥和properties）   注意配置文件名为sdk-config.properties
        //注意相对路径的位置（当前工作目录）
        System.setProperty(SdkUtil.SDK_CONFIG_FOLDER_PATH, "wecom-online-parent\\wecom-online-service\\src\\main\\resources\\config");
// 调用参数准备
        String a = "{\n" +
                "\"appName\": \"F-RAS\",\n" +
                "\"msgList\": [\n" +
                "\n" +
                "{\n" +
                "\"msgId\": \"00G21040614555965503\",\n" +
                "\"prodType\": \"\",\n" +
                "\"notifyTime\": \"20210406145559\",\n" +
                "\"innerMgrId\":\"\",\n" +
                "\"innerCustId\":\"xiemiancst\",\n" +
                "\"msgType\": \"2\",\n" +
                "\"msgContent\": {\n" +
                "\"title\":\"基金投资方案\",\n" +
                "\"text\": \"您的基金投资方案已生成，快来查看吧！\",\n" +
                "\"url\":\"http://ip:port/icbc/RASIapM/iap-mobile/login?zone=1202&key=pRPx5mXL8hAU50UNoFaVOkQng0%2BAesbciS5OtGkDdkxJ7up5ZBfQ6FeqKwB1VNRV5HZ5VVb790oTf6LKUovtNA%3D%3D&tag=27936\",\n" +
                "\"bymText\":\"\"\n" +
                "},\n" +
                "\"remark\": \"\",\n" +
                "\"reserved\": \"\"\n" +
                "}\n" +
                "\n" +
                "]\n" +
                "}";
        //注意map泛型要求 Map<String,String>
        Map<String, String> map = new HashMap();
        //key必须是biz_content
        map.put("biz_content", a);
// 获取调用对象；设置服务对应的uri、调用方式、调用参数；
        DefaultInvoker invoker = Invokers.getDefaultInvoker();
        invoker.setUri("/wecom/push/message/V1");
        invoker.setMethod(Invoker.HttpMethodType.POST);
        invoker.setParameters(map);
        Map<String, Object> resp = invoker.syncInvoke();
        //释放资源
        Invokers.shutdown();
        if (resp.containsKey(DefaultInvoker.ERROR_CODE)) {
// 交易失败
            System.out.println("交易失败");
            System.out.println(resp.toString());
        } else {
            System.out.println("交易成功");
            System.out.println(resp.toString());
        }

    }
}


