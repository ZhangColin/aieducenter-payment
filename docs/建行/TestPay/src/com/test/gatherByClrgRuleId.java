package com.test;

import com.test.HttpUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ccb.mktpay.sign.RSASignUtil;


/**
 * 分账规则推送
 */
public class gatherByClrgRuleId {
    public static void main(String[] args) {
        //推送地址，市场方自己的推送地址
        String url = "http://120.42.34.82:8020/payreback/separate";
        //RSA算法采用该私钥
        String private_RSA = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDS9se1wbq51SXw49bZgzIdj/ShiV618b/vNDztFhK/iW1nD1zMxo72YD9yHRX8jVWnrByDwL18107HK2nYbHu8t3Bcxd8k3/tpZgosbDOoXExDuJG02cwSx4jbCK6x5WWvucGO+tjoMaB1pPEurK6R8AVykcEzB6eo0bGSjx6ZUDh3pUESMXTKkXoQO/fJofq1BibWUq5KpAdeNL+yjFUUZg05PH2K7XRFp8nwe9tDQ3HrvUx1HhyNP/IFxU/XSR/D7uZsYuYOehiBX76mQzYD8PocnS0ehjpcyho7R/jmGuD4O6BGdWxCthrNrrRd6g030/TvfRictiuEJAMSQqf/AgMBAAECggEAHa61OMCSSjVQSk10XFRWR8yKafQPDGCAVeKus9kIOETYzMhfkTxavxWZt6+Z+VfVdmsD9BG5V4hfwCw+j0HsQwg4WgVJOUH+eLzvr4Jl3klmPZ0Jez2ttfK3McJN+h/Bp/Dl5/0paboZzpOvj5aiVUxFJ/KUEV8BWwJuDqXuczmRsG/JwXCDnLsrIEMmyBXDgvGSEiu/L6mjfIMNpwBPGkTiiJGRlBSWIAWdQr/jNw/po0zb+jlCVGWoPcivWGAafXJQX66aAk4JMiNCuLjdkH+xen/xSWU/QEQ9nWwLRJh6l9shvPWY3bfqQKPYkDGyyLdIUzxpIQBpkn+SZp5TSQKBgQD7KOtBC0bcVPpOVu333BC/pxMzqA2HPMyflQuUr7Di6HXZulxb2qEuXfafRMgVo7puLu/TDozZa83L7W1nEf5nbjClVEgVMbhT1uNgkIRuJ8CJu50wnDWUBsmMB4+Dn6kUG7YD/Psp4M1xzhWQSXvyxFXzAf6+DzrISK0FymJeVQKBgQDXB462cxKLbot8la1rQIjw2lX6p/WMBASuqOnPFlkDjIZuQbYfXchWxJRXVLCUikPzBLjWvJHbjrhmBTeIyxTyib+JThqaMzyMf0Y77/GiUJdUBx7PFHFyhDaj+jSmOxi7ceSZF37RNn539D2S6E7Qusj3OYPlaQrSV1WmLxBZAwKBgQDh9lSBdnXQMRvpc0gxoOnoo5Yg+WcCbu7h/CQpJ1ALNX0h4ArMEQzGPH9vl2A0J9PI4a2eww5xZg4HFJtDCetKftaBSCx59PuTYle7PwoGWPlecU7gtwl1Hg4iT4MMto5VqwC84dPOP5RWeUTpRVOgfIefVAIuWGFYZBpWhViu6QKBgQDLU3gdCX6VnbgD3DyZV/KlXK9ETyGefgY3ab18djNBadWL2FLwIevYMBXc5lX6fyt1Vhe55aE+LRwsS+6RSQbLuHkGynXZLW2ppIezEVY5F1+gswLs6PXFRUOtll/Gd8cRJ8bzBAaEqbS4lJjMmyI7uQNi0l3nxYXYE4EHnSUmJQKBgHqcrvr0P2fl01Yma4CxU/CVppAWpN2xPFrIaOgIhMRwAIB/VJxMIcA35hjf5IDMFeevGFtqUCMHtxvFeDWsQlFw4WVR3aaATnQypNC3lukH4kgko7v6IsVj0NoaVOJd2kpNmivlcQGNGX1fg+wfkuF2vksKWfsJVZjM8GUoqMiu";
        //平台公钥
        String plat_Pk = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA0vbHtcG6udUl8OPW2YMyHY/0oYletfG/7zQ87RYSv4ltZw9czMaO9mA/ch0V/I1Vp6wcg8C9fNdOxytp2Gx7vLdwXMXfJN/7aWYKLGwzqFxMQ7iRtNnMEseI2wiuseVlr7nBjvrY6DGgdaTxLqyukfAFcpHBMwenqNGxko8emVA4d6VBEjF0ypF6EDv3yaH6tQYm1lKuSqQHXjS/soxVFGYNOTx9iu10RafJ8HvbQ0Nx671MdR4cjT/yBcVP10kfw+7mbGLmDnoYgV++pkM2A/D6HJ0tHoY6XMoaO0f45hrg+DugRnVsQrYaza60XeoNN9P0730YnLYrhCQDEkKn/wIDAQAB";
        //维护类型
        String Mnt_Type = "00000";
        //市场编号
        String Mkt_Id = "41060860898016";
        //市场名称，非必输
        String Mkt_Nm = "";
        //清算规则编号
        String Clrg_Rule_Id = "";
        //规则名称，非必输
        String Rule_Nm = "";
        //规则描述
        String Rule_Dsc = "";
        //分账周期
        String Sub_Acc_Cyc = "1";
        //清算后延天数,非必输
        String Clrg_Dlay_Dys = "5";
        //清算模式
        String Clrg_Mode = "2";
        //清算方式代码,非必输
        String Clrg_Mtdcd = "1";
        //生效日期
        String Efdt = "20200325";
        //失效日期
        String Expdt = "20200326";


        JSONObject json = new JSONObject(true);
        //维护类型
        json.put("Mnt_Type", Mnt_Type);
        //市场编号
        json.put("Mkt_Id", Mkt_Id);
        //市场名称,非必输
        //json.put("Mkt_Nm", Mkt_Nm);
        //清算规则编号
        json.put("Clrg_Rule_Id", Clrg_Rule_Id);
        //规则名称，非必输
        //json.put("Rule_Nm", Rule_Nm);
        //规则描述，非必输
        // json.put("Rule_Dsc", Rule_Dsc);
        //清算后延天数，当分账周期为1-日，2-月时必输；
        json.put("Clrg_Dlay_Dys", Clrg_Dlay_Dys);
        //清算模式
        json.put("Clrg_Mode", Clrg_Mode);
        //清算方式代码，非必输
        //json.put("Clrg_Mtdcd", Clrg_Mtdcd);
        //生效日期
        json.put("Efdt", Efdt);
        //失效日期
        json.put("Expdt", Expdt);

        JSONArray orderListJsonArray1 = new JSONArray();
        //子订单
        JSONObject order1 = new JSONObject();
        //顺序号
        order1.put("Seq_No", 1);
        //清算方式代码
        order1.put("Clrg_Mtdcd", "5");
        //清算比例
        order1.put("Clrg_Pctg", 0.9);
        //金额
        // order1.put("Amt", 10);
        orderListJsonArray1.add(order1);

        JSONObject order2 = new JSONObject();
        //顺序号
        order2.put("Seq_No", 2);
        //清算方式代码
        order2.put("Clrg_Mtdcd", "5");
        //清算比例
        order2.put("Clrg_Pctg", 0.1);
        //金额
        // order1.put("Amt", 10);
        orderListJsonArray1.add(order2);
        json.put("Memblist", orderListJsonArray1);

        System.out.println(json.toString());
        String jsonString = SplicingUtil.createSign(json.toString());
        System.out.println("加签字符串：" + jsonString);
        String signInf = RSASignUtil.sign(private_RSA, jsonString);
        json.put("Sign_Inf", signInf);

        System.out.println("传入的json串：" + json);
        try {
            String result = HttpUtil.doJsonPost(url, json.toString());;
            System.out.println("result=" + result);
            //开始验签
            JSONObject jsonObject = JSONObject.parseObject(result);
            //验签原串
            jsonString = SplicingUtil.createSign(result);
            System.out.println("输出的原串为" + jsonString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}