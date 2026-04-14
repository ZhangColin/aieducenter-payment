package icbc.cfbi.wecom.api;

import com.icbc.apip.config.SdkUtil;
import com.icbc.apip.invoker.DefaultInvoker;
import com.icbc.apip.invoker.Invoker;
import com.icbc.apip.invoker.Invokers;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description 认证客户与客户经理是否企业微信好友
 * @ClassName wecomFriendAuthV1
 * @Version 1.0
 * @Date 2021/6/30 15:46
 * @Author kfzx-xiangf
 **/
public class wecomFriendAuthV1 {
    public static void main(String[] args) throws Exception {
        //在resources文件夹下config文件夹中读取配置文件（包括公私钥和properties）   注意配置文件名为sdk-config.properties
        //注意相对路径的位置（当前工作目录）
        System.setProperty(SdkUtil.SDK_CONFIG_FOLDER_PATH, "internalApi\\api\\src\\main\\resources\\config");
        // 调用参数准备
        String biz_content = "{\n" +
                "\"innerMgrId\": \"320205756\",\n" +
                "\"qywxCorpId\": \"ww1f75467418bb8f3e\",\n" +
                "\"innerCustId\": \"cis02\"\n" +
                "}";
        //注意map泛型要求 Map<String,String>
        Map<String, String> map = new HashMap();
        //key必须是biz_content
        map.put("biz_content", biz_content);
        // 获取调用对象；设置服务对应的uri、调用方式、调用参数；
        DefaultInvoker invoker = Invokers.getDefaultInvoker();
        invoker.setUri("/wecom/friend/auth/V1");
        invoker.setMethod(Invoker.HttpMethodType.POST);
        invoker.setParameters(map);
        Map<String, Object> resp = invoker.syncInvoke();

        if("200".equals(String.valueOf(resp.get("returnCode")))) {
            Map<String, Object> data = (Map<String, Object>) resp.get("data");
            String isWeChatFriend = String.valueOf(data.get("isWeChatFriend"));
        }
        //释放资源
        Invokers.shutdown();
        if (resp.containsKey(DefaultInvoker.ERROR_CODE)) {
            // 失败
            System.out.println("失败");
            System.out.println(resp.toString());
        } else {
            System.out.println("成功");
            System.out.println(resp.toString());
        }

    }
}
