package com.test;


import com.alibaba.fastjson.JSONObject;
import com.ccb.mktpay.sign.RSASignUtil;
import java.text.SimpleDateFormat;
import java.util.*;
import com.test.HttpUtil;

/**
 *退款通知
 */
public class NoticeMktRefundRst {
	//访问地址
    private static final String URL = "http://marketpaykone.dev.jh:8028/online/direct/refundOrderSta";

    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");

    public static void main(String[] args) {
        //RSA算法采用该私钥
        String private_RSA = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDS9se1wbq51SXw49bZgzIdj/ShiV618b/vNDztFhK/iW1nD1zMxo72YD9yHRX8jVWnrByDwL18107HK2nYbHu8t3Bcxd8k3/tpZgosbDOoXExDuJG02cwSx4jbCK6x5WWvucGO+tjoMaB1pPEurK6R8AVykcEzB6eo0bGSjx6ZUDh3pUESMXTKkXoQO/fJofq1BibWUq5KpAdeNL+yjFUUZg05PH2K7XRFp8nwe9tDQ3HrvUx1HhyNP/IFxU/XSR/D7uZsYuYOehiBX76mQzYD8PocnS0ehjpcyho7R/jmGuD4O6BGdWxCthrNrrRd6g030/TvfRictiuEJAMSQqf/AgMBAAECggEAHa61OMCSSjVQSk10XFRWR8yKafQPDGCAVeKus9kIOETYzMhfkTxavxWZt6+Z+VfVdmsD9BG5V4hfwCw+j0HsQwg4WgVJOUH+eLzvr4Jl3klmPZ0Jez2ttfK3McJN+h/Bp/Dl5/0paboZzpOvj5aiVUxFJ/KUEV8BWwJuDqXuczmRsG/JwXCDnLsrIEMmyBXDgvGSEiu/L6mjfIMNpwBPGkTiiJGRlBSWIAWdQr/jNw/po0zb+jlCVGWoPcivWGAafXJQX66aAk4JMiNCuLjdkH+xen/xSWU/QEQ9nWwLRJh6l9shvPWY3bfqQKPYkDGyyLdIUzxpIQBpkn+SZp5TSQKBgQD7KOtBC0bcVPpOVu333BC/pxMzqA2HPMyflQuUr7Di6HXZulxb2qEuXfafRMgVo7puLu/TDozZa83L7W1nEf5nbjClVEgVMbhT1uNgkIRuJ8CJu50wnDWUBsmMB4+Dn6kUG7YD/Psp4M1xzhWQSXvyxFXzAf6+DzrISK0FymJeVQKBgQDXB462cxKLbot8la1rQIjw2lX6p/WMBASuqOnPFlkDjIZuQbYfXchWxJRXVLCUikPzBLjWvJHbjrhmBTeIyxTyib+JThqaMzyMf0Y77/GiUJdUBx7PFHFyhDaj+jSmOxi7ceSZF37RNn539D2S6E7Qusj3OYPlaQrSV1WmLxBZAwKBgQDh9lSBdnXQMRvpc0gxoOnoo5Yg+WcCbu7h/CQpJ1ALNX0h4ArMEQzGPH9vl2A0J9PI4a2eww5xZg4HFJtDCetKftaBSCx59PuTYle7PwoGWPlecU7gtwl1Hg4iT4MMto5VqwC84dPOP5RWeUTpRVOgfIefVAIuWGFYZBpWhViu6QKBgQDLU3gdCX6VnbgD3DyZV/KlXK9ETyGefgY3ab18djNBadWL2FLwIevYMBXc5lX6fyt1Vhe55aE+LRwsS+6RSQbLuHkGynXZLW2ppIezEVY5F1+gswLs6PXFRUOtll/Gd8cRJ8bzBAaEqbS4lJjMmyI7uQNi0l3nxYXYE4EHnSUmJQKBgHqcrvr0P2fl01Yma4CxU/CVppAWpN2xPFrIaOgIhMRwAIB/VJxMIcA35hjf5IDMFeevGFtqUCMHtxvFeDWsQlFw4WVR3aaATnQypNC3lukH4kgko7v6IsVj0NoaVOJd2kpNmivlcQGNGX1fg+wfkuF2vksKWfsJVZjM8GUoqMiu";
        //平台公钥
        String plat_Pk = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA0vbHtcG6udUl8OPW2YMyHY/0oYletfG/7zQ87RYSv4ltZw9czMaO9mA/ch0V/I1Vp6wcg8C9fNdOxytp2Gx7vLdwXMXfJN/7aWYKLGwzqFxMQ7iRtNnMEseI2wiuseVlr7nBjvrY6DGgdaTxLqyukfAFcpHBMwenqNGxko8emVA4d6VBEjF0ypF6EDv3yaH6tQYm1lKuSqQHXjS/soxVFGYNOTx9iu10RafJ8HvbQ0Nx671MdR4cjT/yBcVP10kfw+7mbGLmDnoYgV++pkM2A/D6HJ0tHoY6XMoaO0f45hrg+DugRnVsQrYaza60XeoNN9P0730YnLYrhCQDEkKn/wIDAQAB";

        //市场编号
        //String Mkt_Id = "43086363609829";
        String Mkt_Id="41060860898016";
        //支付流水号
        String Py_Trn_No = "10555100000715401111929484212H";
        String Super_Refund_No = "10555100000715401111929484212H";
        String Refund_Rsp_St = "1";
        //版本号
        String Vno = "3";

        JSONObject json = new JSONObject(true);
        String Ittparty_Jrnl_No = System.currentTimeMillis() + "";
        json.put("Ittparty_Jrnl_No", Ittparty_Jrnl_No);
        String Ittparty_Tms = formatter.format(new Date());
        json.put("Ittparty_Tms", Ittparty_Tms);
        json.put("Py_Trn_No", Py_Trn_No);
        json.put("Super_Refund_No", Super_Refund_No);
        json.put("Refund_Rsp_St", Refund_Rsp_St);
        String oriStr = "";
        if(Integer.parseInt(Vno) > 2){
            oriStr = SplicingUtil.createSign(json.toString());
        }else{
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Ittparty_Tms=").append(Ittparty_Tms)
                    .append("&Ittparty_Jrnl_No=").append(Ittparty_Jrnl_No)
                    .append("&Py_Trn_No=").append(Py_Trn_No)
                    .append("&Refund_Rsp_St=").append(Refund_Rsp_St)
                    .append("&Super_Refund_No=").append(Super_Refund_No);
            oriStr = stringBuilder.toString();
        }
        System.out.println("加签字符串：" + oriStr);
        String signInf = RSASignUtil.sign(private_RSA, oriStr);
        json.put("Sign_Inf", signInf);
//        String jsonStr = JSON.toJSONString(json,new PascalNameFilter());
        String jsonStr = json.toJSONString();
        System.out.println("传入的json串：" + jsonStr);
        try {
            String result = HttpUtil.doJsonPost(URL, jsonStr);
            System.out.println(result);
            //开始验签
            JSONObject jsonObject = JSONObject.parseObject(result);
            //验签原串
            String jsonString = SplicingUtil.createSign(result);
            signInf = jsonObject.getString("Sign_Inf");
            System.out.println("验签结果:" + RSASignUtil.verifySign(plat_Pk, jsonString, signInf));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
