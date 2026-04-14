package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.apip.config.Configure;
import com.icbc.apip.context.WebApiContext;
import com.icbc.apip.invoker.DefaultInvoker;
import com.icbc.apip.invoker.Invoker;
import com.icbc.apip.token.SignatureAlgo;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * 工银e服务消息状态更新测试案例
 * @author kfzx-huangk1
 * @date 2022/7/5
 */
public class MsgServiceUpdatemsgreadflagV1Test {

    private static final Logger LOG = Logger.getLogger(MsgServiceUpdatemsgreadflagV1Test.class.getName());

    public static void main(String[] args) throws Exception {

        //1.调用参数准备
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("oapp", "F-IES");
        paramMap.put("user_id", "020000201");
        paramMap.put("id", "1");
        paramMap.put("read_flag", "1");
        paramMap.put("msg_source", "001");
        String json = JSON.toJSONString(new Object[]{paramMap});
        //2.通过代码配置
        Configure configure = new Configure();
        configure.setAppId("10000000000004098136");
        configure.setUserPrivateLoc("C:/moniqi/mykey.pri");
        configure.setSysPubkeyLoc("C:/moniqi/API_GATEWAY.pub");
        configure.setSysBaseUri("http://ip:port/api");
        configure.setAlgo(SignatureAlgo.RSA2048);
        configure.setSysAuthMaxTimeStep(1000000);
        WebApiContext context = new WebApiContext();
        context.setConfigure(configure);
        context.init();
        //3.获取调用对象
        DefaultInvoker invoker = context.getDefaultInvoker();
        invoker.setUri("/msg/service/updatemsgreadflag/V1");
        invoker.setSendEncoding("utf-8");
        //4.添加参数
        invoker.addParameter("biz_content", json);
        invoker.setMethod(Invoker.HttpMethodType.POST);
        //5.同步调用，获取map对象
        Map<String, Object> resp = invoker.syncInvoke();
        //6.释放资源
        context.shutdown();
        if ("0".equals(String.valueOf(resp.get("return_code")))) {
            // 业务成功处理
            LOG.info("成功");
        } else {
            // 失败
            LOG.info("失败");
        }
    }
}
