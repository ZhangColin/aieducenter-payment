package com.icbc.api;

import java.util.HashMap;
import java.util.Map;


import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.apip.config.Configure;
import com.icbc.apip.context.WebApiContext;
import com.icbc.apip.invoker.DefaultInvoker;
import com.icbc.apip.invoker.Invoker.HttpMethodType;
import com.icbc.apip.token.SignatureAlgo;

public class IspNewsStreamIdToOaasKeyTest {


    /**
     * API行内调用
     */
    public static void main(String[] args) {
        try {
            //调用参数准备
            Map<String,Object> paramMap = new HashMap<>();
            paramMap.put("streamId", "3602078209000868801");
            Map<String,Object> common = new HashMap<>();
            common.put("senderid", "050202002263503000019600019");
            common.put("serialno", "3503000019600019");
            common.put("areacode", "1234");
            common.put("cis", "131100020257848");
            paramMap.put("common", common);
            String json = JSON.toJSONString(new Object[]{paramMap});
            //通过代码配置
            Configure configure = new Configure();
            configure.setAppId("10000000000004097593");
            configure.setUserPrivateLoc("D:/api/API_Config/mykey.pri");
            configure.setSysPubkeyLoc("D:/api/API_Config/API_GATEWAY.pub");
            configure.setSysBaseUri("http://ip:port/api");
            configure.setAlgo(SignatureAlgo.RSA2048);
            configure.setSysAuthMaxTimeStep(1000000);
            WebApiContext context = new WebApiContext();
            context.setConfigure(configure);
            context.init();
            //获取调用对象
            DefaultInvoker invoker = context.getDefaultInvoker();
            invoker.setUri("/api/isp/news/StreamIdToOaasKey/GetOaasKeyByStreamId/V1");
            invoker.setSendEncoding("utf-8");
            //添加参数
            invoker.addParameter("biz_content", json);
            invoker.setMethod(HttpMethodType.POST);
            //同步调用，获取map对象
            Map<String, Object> resp = invoker.syncInvoke();
            //释放资源
            context.shutdown();
            System.out.println("return_code：" + String.valueOf(resp.get("return_code")));
            System.out.println("return_msg：" + String.valueOf(resp.get("return_msg")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
