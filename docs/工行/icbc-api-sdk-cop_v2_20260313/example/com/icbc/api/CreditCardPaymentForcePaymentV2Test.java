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

public class CreditCardPaymentForcePaymentV2Test {
    public static void main(String[] args) {

        //指定config文件夹的位置，建议此步骤放置于容器初始化过程中完成，设置一次即可（例如：ServletContextListener）
        System.setProperty(SdkUtil.SDK_CONFIG_FOLDER_PATH, "config");

        //创建调用实例
        DefaultInvoker invoker = Invokers.getDefaultInvoker();
        //设置请求路径
        invoker.setUri("http://[需替换]工行API网关地址/api/csi/creditcard/payment/forcepayment/V2");
        invoker.setMethod(Invoker.HttpMethodType.POST);

        try {
            Map<String,Object> channel = new HashMap<String,Object>();
            channel.put("chantype","101");
            channel.put("chanlno","04041400");
            channel.put("mac","8C-16-45-13-FC-33");
            channel.put("ip","");
            channel.put("termid","04041400");
            channel.put("oapp","F-CCPS");
            channel.put("sevlevel","0");
            channel.put("serialno","201802271525655195333");
            channel.put("mserialn","201802271525655195333");
            channel.put("oserialn","201802271525655195333");
            channel.put("preflag","0");
            channel.put("prodid","030100006162");
            channel.put("cobprodid","030100006162");
            channel.put("cino","1234567890");
            channel.put("disrecflag","0");
            channel.put("comrolflag","0");
            channel.put("paperlessflag","0");
            channel.put("field1","0");
            channel.put("termtype","0");
            channel.put("launbankzoneno","0");
            channel.put("fingerprinflag","0");
            channel.put("distransinfo","0");
            Map<String,Object> nfo_comm = new HashMap<String,Object>();
            nfo_comm.put("trxtype","1");
            nfo_comm.put("trxcode","200");
            nfo_comm.put("zoneno","200");
            nfo_comm.put("brno","620");
            nfo_comm.put("tellerno","200");
            nfo_comm.put("workdate","2018-06-28");
            nfo_comm.put("worktime","10:02:03");
            Map<String,Object> ran_comm = new HashMap<String,Object>();
            ran_comm.put("trx_big_sqn","1");
            ran_comm.put("trx_small_sqn","1");
            ran_comm.put("trx_operation_type","1");
            ran_comm.put("trx_date_to_account","1");
            ran_comm.put("workdate_chk","1");
            ran_comm.put("teller_chk","1");
            ran_comm.put("channel_access_type","1");

            Map<String,Object> public = new HashMap<String,Object>();
            public.put("trxcode","2886");
            public.put("trx_operation_type","1");
            public.put("workdate","2027-12-20");


            
            Map<String,Object> inputMap = new HashMap<String,Object>();
            inputMap.put("channel",channel);
            inputMap.put("nfo_comm",nfo_comm);
            inputMap.put("ran_comm",ran_comm);
            inputMap.put("public",public);


            inputMap.put("counter_account_isICBC","0");

            
            
            String req_json = JSON.toJSONString(inputMap);
            
            invoker.setMethod(HttpMethodType.POST);
            
            
            invoker.setMethod(HttpMethodType.POST);
            invoker.addParameter("biz_content", req_json);
            System.out.println(invoker.toString());
            
            Map<String, Object> resp = invoker.syncInvoke();
            
            
            System.out.println(resp);
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            //关闭连接池、线程池，释放资源。在容器销毁前调用一次即可。注意每次调用后释放。
            Invokers.shutdown();
        } catch (InvokerException e) {
            e.printStackTrace();
        } catch (ConfigException e) {
            e.printStackTrace();
        }
    }
}