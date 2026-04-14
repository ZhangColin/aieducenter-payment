package com.icbc.apip;
import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.ChannelmgmtInfoBranchQueryRequestV1;
import com.icbc.api.request.ChannelmgmtInfoBranchQueryRequestV1.ChannelmgmtInfoBranchQueryRequestV1V1Biz;
import com.icbc.api.response.ChannelmgmtInfoBranchQueryResponseV1;

/**
 *
 * @ClassName ChannelmgmtInfoBranchQueryTest
 *  @Description TODO
 * @author: kfzx-xuew
 * @create: 2021-09-27 17:02
 **/
public class ChannelmgmtInfoBranchQueryTest {
    public static void main(String[] args) {

        Configure configure = new Configure();
        configure.setAppId("*****");
        configure.setUserPrivateLoc("config/yourname.pri");
        configure.setSysPubkeyLoc("config/API_GATEWAY.pub");
        configure.setSysBaseUri("http://*****/api");
        configure.setAlgo(SignatureAlgo.RSA1024);
        configure.setSysAuthMaxTimeStep(1000000);
        WebApiContext context = new WebApiContext();
        context.setConfigure(configure);
        context.init();
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("zone_num", "1001");
        jsonObj.put("branch_num", "1");
        // 获取调用对象；设置服务对应的uri、调用方式、调用参数；
        DefaultInvoker invoker = context.getDefaultInvoker();
        invoker.setUri("/channelmgmt/info/branch/query/V1");
        invoker.setMethod(Invoker.HttpMethodType.POST);
        invoker.addParameter("biz_content", jsonObj.toJSONString());
        try {
            Map<String,Object> resp = invoker.syncInvoke();
            System.out.println(resp);
            Map<String, Object> appStatV10 = (Map<String, Object>) resp.get("appStatV10");
            String return_code = appStatV10.get("return_code").toString();
            if("00000".equals(return_code)){
                String JS = resp.get("branchList").toString();
                System.out.println(JS);
            }
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvokerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ConfigException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            try {
                Invokers.shutdown();
            } catch (InvokerException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (ConfigException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
