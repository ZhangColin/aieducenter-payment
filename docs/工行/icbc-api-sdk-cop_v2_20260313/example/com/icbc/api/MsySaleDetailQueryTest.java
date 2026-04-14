package com.icbc.apip;
import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.MsySaleDetailQueryRequestV1;
import com.icbc.api.request.MsySaleDetailQueryRequestV1.MsySaleDetailQueryRequestV1V1Biz;
import com.icbc.api.response.MsySaleDetailQueryResponseV1;

/**
 *
 * @ClassName MsySaleDetailQueryTest
 *  @Description TODO
 * @author: kfzx-zhanyic
 * @create: 2022-12-08 17:02
 **/
public class MsySaleDetailQueryTest {
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
        jsonObj.put("ssicId", "0011122333");
        jsonObj.put("tradeDateBeg", "2001-11-11");
        jsonObj.put("tradeDateEnd", "2001-12-12");
        jsonObj.put("saleType", "1");
        jsonObj.put("cmos_code", "1");
        jsonObj.put("qrcodeType", "1");
        jsonObj.put("struId", "1");
        jsonObj.put("pageNum", "1");
        jsonObj.put("pageSize", "1");
        jsonObj.put("prd_list", new ArrayList());
        // ��ȡ���ö������÷����Ӧ��uri�����÷�ʽ�����ò�����
        DefaultInvoker invoker = context.getDefaultInvoker();
        invoker.setUri("/msysale/info/detail/query/V1");
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
