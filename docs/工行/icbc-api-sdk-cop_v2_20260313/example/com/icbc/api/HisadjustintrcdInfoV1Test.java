  package com.icbc.api;


import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.apip.config.SdkUtil;
import com.icbc.apip.exception.ConfigException;
import com.icbc.apip.exception.InvokerException;
import com.icbc.apip.invoker.DefaultInvoker;
import com.icbc.apip.invoker.Invoker;
import com.icbc.apip.invoker.Invokers;

import java.util.HashMap;
import java.util.Map;

public class HisadjustintrcdInfoV1Test {
    public static void main(String[] args) {

        //指定config文件夹的位置，建议此步骤放置于容器初始化过程中完成，设置一次即可（例如：ServletContextListener）
        System.setProperty(SdkUtil.SDK_CONFIG_FOLDER_PATH, "config");

        //创建调用实例
        DefaultInvoker invoker = Invokers.getDefaultInvoker();
        //设置请求路径
        invoker.setUri("http://[需替换]工行API网关地址/api/hisadjustintrcd/info/V1");
        invoker.setMethod(Invoker.HttpMethodType.POST);

        try {
            Map<String, Object> chanCommV10 = new HashMap<String, Object>();
            chanCommV10.put("chantype", "101");
            chanCommV10.put("chanlno", "04041400");
            chanCommV10.put("mac", "8C-16-45-13-FC-33");
            chanCommV10.put("ip", "");
            chanCommV10.put("termid", "04041400");
            chanCommV10.put("oapp", "F-APIP");
            chanCommV10.put("sevlevel", 0);
            chanCommV10.put("serialno", "201802271525655195333");
            chanCommV10.put("mserialn", "201802271525655195333");
            chanCommV10.put("oserialn", "201802271525655195333");
            chanCommV10.put("preflag", 0);

            chanCommV10.put("prodid", 030100006162);
            chanCommV10.put("serialno", "201802271525655195333");
            chanCommV10.put("mserialn", "201802271525655195333");
            chanCommV10.put("oserialn", "201802271525655195333");
            chanCommV10.put("preflag", 0);
            chanCommV10.put("prodid", "030100006162");
            chanCommV10.put("cobprodid" ,"030100006162");
            chanCommV10.put("cino" ,"1234567890");
            chanCommV10.put("disrecflag" , "0");
            chanCommV10.put("comrolflag" ,"0");
            chanCommV10.put("paperlessflag" , "0");
            chanCommV10.put("field1" ,"0");
            chanCommV10.put("termtype" ,"0");
            chanCommV10.put("launbankzoneno" , "0");
            chanCommV10.put("fingerprinflag" , "0");
            chanCommV10.put("distransinfo" , "0");
            Map<String, Object> infoCommV10 = new HashMap<String, Object>();
            infoCommV10.put("trxtype", "1");
            infoCommV10.put("trxcode", "200");
            infoCommV10.put("zoneno", "200");
            infoCommV10.put("brno", "200");
            infoCommV10.put("tellerno", "200");
            infoCommV10.put("workdate", "2018-06-28");
            infoCommV10.put("worktime", "10:02:03");
            infoCommV10.put("revtranf", "0");
            Map<String, Object> newnewTranComm = new HashMap<String, Object>();
            newTranComm.put("trx_big_sqn", "1");
            newTranComm.put("trx_small_sqn", "1");
            newTranComm.put("trx_operation_type", "1");
            newTranComm.put("trx_date_to_account", "1");
            newTranComm.put("workdate_chk", "1");
            newTranComm.put("teller_chk", "1");
            newTranComm.put("channel_access_type", "1");

            Map<String, Object> inqCommV10 = new HashMap<String, Object>();
            inqCommV10.put("inqtype", "1");
            inqCommV10.put("rowreq", "10");
            inqCommV10.put("initflag", "1");
            inqCommV10.put("stridenum", "1");

            Map<String, Object> inputMap = new HashMap<String, Object>();
            inputMap.put("chanCommV10", chanCommV10);
            inputMap.put("infoCommV10", infoCommV10);
            inputMap.put("new_tran_comm", inqCommV10);
            inputMap.put("inqCommV10", inqCommV10);
            inputMap.put("query_mode", "1");
            inputMap.put("card_no", "4518110021557596");
            inputMap.put("start_date", "1900-01-01");
            inputMap.put("end_date", "2024-04-10");


            String req_json = JSON.toJSONString(inputMap);

            //req_json 业务参数，为json数组格式
            invoker.setMethod(Invoker.HttpMethodType.POST);
            invoker.addParameter("biz_content", req_json);
            System.out.println(invoker.toString());
            //同步调用，并获取请求结果
            Map<String, Object> resp = invoker.syncInvoke();

            //调用获取结果后的后续业务处理
            System.out.println(resp);

        } catch (Exception e) {
            e.printStackTrace();
        }
        sleep5Sec();

        try {
            //关闭连接池、线程池，释放资源。在容器销毁前调用一次即可。注意每次调用后释放。
            Invokers.shutdown();
        } catch (InvokerException e) {
            e.printStackTrace();
        } catch (ConfigException e) {
            e.printStackTrace();
        }
    }

    public static void sleep5Sec() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}