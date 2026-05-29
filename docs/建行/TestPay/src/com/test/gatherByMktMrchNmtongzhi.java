package com.test;

import cn.hutool.core.date.DateUtil;
import com.test.HttpUtil;

import com.alibaba.fastjson.JSONObject;
import com.ccb.mktpay.sign.RSASignUtil;


import java.time.LocalDateTime;


/**
 * 商家信息通知
 */
public class gatherByMktMrchNmtongzhi {
    public static void main(String[] args) {
        //市场方url地址
        String url = "http://120.42.34.82:8020/payreback/business";
        //RSA算法采用该私钥
        String private_RSA = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDS9se1wbq51SXw49bZgzIdj/ShiV618b/vNDztFhK/iW1nD1zMxo72YD9yHRX8jVWnrByDwL18107HK2nYbHu8t3Bcxd8k3/tpZgosbDOoXExDuJG02cwSx4jbCK6x5WWvucGO+tjoMaB1pPEurK6R8AVykcEzB6eo0bGSjx6ZUDh3pUESMXTKkXoQO/fJofq1BibWUq5KpAdeNL+yjFUUZg05PH2K7XRFp8nwe9tDQ3HrvUx1HhyNP/IFxU/XSR/D7uZsYuYOehiBX76mQzYD8PocnS0ehjpcyho7R/jmGuD4O6BGdWxCthrNrrRd6g030/TvfRictiuEJAMSQqf/AgMBAAECggEAHa61OMCSSjVQSk10XFRWR8yKafQPDGCAVeKus9kIOETYzMhfkTxavxWZt6+Z+VfVdmsD9BG5V4hfwCw+j0HsQwg4WgVJOUH+eLzvr4Jl3klmPZ0Jez2ttfK3McJN+h/Bp/Dl5/0paboZzpOvj5aiVUxFJ/KUEV8BWwJuDqXuczmRsG/JwXCDnLsrIEMmyBXDgvGSEiu/L6mjfIMNpwBPGkTiiJGRlBSWIAWdQr/jNw/po0zb+jlCVGWoPcivWGAafXJQX66aAk4JMiNCuLjdkH+xen/xSWU/QEQ9nWwLRJh6l9shvPWY3bfqQKPYkDGyyLdIUzxpIQBpkn+SZp5TSQKBgQD7KOtBC0bcVPpOVu333BC/pxMzqA2HPMyflQuUr7Di6HXZulxb2qEuXfafRMgVo7puLu/TDozZa83L7W1nEf5nbjClVEgVMbhT1uNgkIRuJ8CJu50wnDWUBsmMB4+Dn6kUG7YD/Psp4M1xzhWQSXvyxFXzAf6+DzrISK0FymJeVQKBgQDXB462cxKLbot8la1rQIjw2lX6p/WMBASuqOnPFlkDjIZuQbYfXchWxJRXVLCUikPzBLjWvJHbjrhmBTeIyxTyib+JThqaMzyMf0Y77/GiUJdUBx7PFHFyhDaj+jSmOxi7ceSZF37RNn539D2S6E7Qusj3OYPlaQrSV1WmLxBZAwKBgQDh9lSBdnXQMRvpc0gxoOnoo5Yg+WcCbu7h/CQpJ1ALNX0h4ArMEQzGPH9vl2A0J9PI4a2eww5xZg4HFJtDCetKftaBSCx59PuTYle7PwoGWPlecU7gtwl1Hg4iT4MMto5VqwC84dPOP5RWeUTpRVOgfIefVAIuWGFYZBpWhViu6QKBgQDLU3gdCX6VnbgD3DyZV/KlXK9ETyGefgY3ab18djNBadWL2FLwIevYMBXc5lX6fyt1Vhe55aE+LRwsS+6RSQbLuHkGynXZLW2ppIezEVY5F1+gswLs6PXFRUOtll/Gd8cRJ8bzBAaEqbS4lJjMmyI7uQNi0l3nxYXYE4EHnSUmJQKBgHqcrvr0P2fl01Yma4CxU/CVppAWpN2xPFrIaOgIhMRwAIB/VJxMIcA35hjf5IDMFeevGFtqUCMHtxvFeDWsQlFw4WVR3aaATnQypNC3lukH4kgko7v6IsVj0NoaVOJd2kpNmivlcQGNGX1fg+wfkuF2vksKWfsJVZjM8GUoqMiu";
        //平台公钥
        String plat_Pk = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA0vbHtcG6udUl8OPW2YMyHY/0oYletfG/7zQ87RYSv4ltZw9czMaO9mA/ch0V/I1Vp6wcg8C9fNdOxytp2Gx7vLdwXMXfJN/7aWYKLGwzqFxMQ7iRtNnMEseI2wiuseVlr7nBjvrY6DGgdaTxLqyukfAFcpHBMwenqNGxko8emVA4d6VBEjF0ypF6EDv3yaH6tQYm1lKuSqQHXjS/soxVFGYNOTx9iu10RafJ8HvbQ0Nx671MdR4cjT/yBcVP10kfw+7mbGLmDnoYgV++pkM2A/D6HJ0tHoY6XMoaO0f45hrg+DugRnVsQrYaza60XeoNN9P0730YnLYrhCQDEkKn/wIDAQAB";
        //发起方渠道编号
        String Ittparty_Stm_Id = "00000";
        //发起方渠道代码
        String Py_Chnl_Cd = "0000000000000000000000000";
        String formatter = DateUtil.format(LocalDateTime.now(), "yyyyMMddHHmmssSSS");
        //发起方时间戳
        String Ittparty_Tms = formatter;
        //操作类型
        String Opr_Tp = "01";
        //市场商家编号
        String Mkt_Mrch_Id = "41060860898016000001";
        //市场商家名称
        String Mkt_Mrch_Nm = "商户1";
        //POS编号
        String Pos_No = "123";
        //商家证件类型，非必输
        String Mrch_Crdt_Tp = "";
        //商家证件号码，非必输
        String Mrch_Crdt_No = "";
        //商家柜台代码，非必输
        String Mrch_Cnter_Cd = "";
        //居民身份证，非必输
        String Crdt_Tp = "";
        //联系人名称，非必输
        String Ctcpsn_Nm = "";
        //证件号码
        String Crdt_No = "123";
        //手机号码
        String Mblph_No = "123";//商家编号
        //商家自定义编号,非必输
        String Udf_Id = "";

        JSONObject json = new JSONObject(true);
        //发起方渠道编号
        json.put("Ittparty_Stm_Id", Ittparty_Stm_Id);
        //发起方渠道代码
        json.put("Py_Chnl_Cd", Py_Chnl_Cd);
        //发起方时间戳
        json.put("Ittparty_Tms", Ittparty_Tms);
        //操作类型
        json.put("Opr_Tp", Opr_Tp);
        //市场商家编号
        json.put("Mkt_Mrch_Id", Mkt_Mrch_Id);
        //市场商家名称
        json.put("Mkt_Mrch_Nm", Mkt_Mrch_Nm);
        //POS编号
        json.put("Pos_No", Pos_No);
        //商家证件类型
//        json.put("Mrch_Crdt_Tp", Mrch_Crdt_Tp);
        //商家证件号码
//        json.put("Mrch_Crdt_No", Mrch_Crdt_No);
        //商家柜台代码
//        json.put("Mrch_Cnter_Cd", Mrch_Cnter_Cd);
        //证件类型
//        json.put("Crdt_Tp", Crdt_Tp);
        //联系人名称
//        json.put("Ctcpsn_Nm", Ctcpsn_Nm);
        //证件号码
        json.put("Crdt_No", Crdt_No);
        //手机号码
        json.put("Mblph_No", Mblph_No);
        //商家自定义编号
        //json.put("Udf_Id", Udf_Id);


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