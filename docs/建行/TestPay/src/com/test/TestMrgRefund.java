package com.test;
import com.test.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.ccb.govpay.sign.SignUtil;
import com.ccb.mktpay.sign.RSASignUtil;
//查询退款结果
public class TestMrgRefund {
    public static void main(String[] args) {
    	//访问地址
    	 String testMrgRefundUrl = "http://marketpaykone.dev.jh:8028/online/direct/refundOrder";
        //平台公钥
        String plat_Pk = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA0vbHtcG6udUl8OPW2YMyHY/0oYletfG/7zQ87RYSv4ltZw9czMaO9mA/ch0V/I1Vp6wcg8C9fNdOxytp2Gx7vLdwXMXfJN/7aWYKLGwzqFxMQ7iRtNnMEseI2wiuseVlr7nBjvrY6DGgdaTxLqyukfAFcpHBMwenqNGxko8emVA4d6VBEjF0ypF6EDv3yaH6tQYm1lKuSqQHXjS/soxVFGYNOTx9iu10RafJ8HvbQ0Nx671MdR4cjT/yBcVP10kfw+7mbGLmDnoYgV++pkM2A/D6HJ0tHoY6XMoaO0f45hrg+DugRnVsQrYaza60XeoNN9P0730YnLYrhCQDEkKn/wIDAQAB";

        //RSA算法采用该私钥
        String private_RSA = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDS9se1wbq51SXw49bZgzIdj/ShiV618b/vNDztFhK/iW1nD1zMxo72YD9yHRX8jVWnrByDwL18107HK2nYbHu8t3Bcxd8k3/tpZgosbDOoXExDuJG02cwSx4jbCK6x5WWvucGO+tjoMaB1pPEurK6R8AVykcEzB6eo0bGSjx6ZUDh3pUESMXTKkXoQO/fJofq1BibWUq5KpAdeNL+yjFUUZg05PH2K7XRFp8nwe9tDQ3HrvUx1HhyNP/IFxU/XSR/D7uZsYuYOehiBX76mQzYD8PocnS0ehjpcyho7R/jmGuD4O6BGdWxCthrNrrRd6g030/TvfRictiuEJAMSQqf/AgMBAAECggEAHa61OMCSSjVQSk10XFRWR8yKafQPDGCAVeKus9kIOETYzMhfkTxavxWZt6+Z+VfVdmsD9BG5V4hfwCw+j0HsQwg4WgVJOUH+eLzvr4Jl3klmPZ0Jez2ttfK3McJN+h/Bp/Dl5/0paboZzpOvj5aiVUxFJ/KUEV8BWwJuDqXuczmRsG/JwXCDnLsrIEMmyBXDgvGSEiu/L6mjfIMNpwBPGkTiiJGRlBSWIAWdQr/jNw/po0zb+jlCVGWoPcivWGAafXJQX66aAk4JMiNCuLjdkH+xen/xSWU/QEQ9nWwLRJh6l9shvPWY3bfqQKPYkDGyyLdIUzxpIQBpkn+SZp5TSQKBgQD7KOtBC0bcVPpOVu333BC/pxMzqA2HPMyflQuUr7Di6HXZulxb2qEuXfafRMgVo7puLu/TDozZa83L7W1nEf5nbjClVEgVMbhT1uNgkIRuJ8CJu50wnDWUBsmMB4+Dn6kUG7YD/Psp4M1xzhWQSXvyxFXzAf6+DzrISK0FymJeVQKBgQDXB462cxKLbot8la1rQIjw2lX6p/WMBASuqOnPFlkDjIZuQbYfXchWxJRXVLCUikPzBLjWvJHbjrhmBTeIyxTyib+JThqaMzyMf0Y77/GiUJdUBx7PFHFyhDaj+jSmOxi7ceSZF37RNn539D2S6E7Qusj3OYPlaQrSV1WmLxBZAwKBgQDh9lSBdnXQMRvpc0gxoOnoo5Yg+WcCbu7h/CQpJ1ALNX0h4ArMEQzGPH9vl2A0J9PI4a2eww5xZg4HFJtDCetKftaBSCx59PuTYle7PwoGWPlecU7gtwl1Hg4iT4MMto5VqwC84dPOP5RWeUTpRVOgfIefVAIuWGFYZBpWhViu6QKBgQDLU3gdCX6VnbgD3DyZV/KlXK9ETyGefgY3ab18djNBadWL2FLwIevYMBXc5lX6fyt1Vhe55aE+LRwsS+6RSQbLuHkGynXZLW2ppIezEVY5F1+gswLs6PXFRUOtll/Gd8cRJ8bzBAaEqbS4lJjMmyI7uQNi0l3nxYXYE4EHnSUmJQKBgHqcrvr0P2fl01Yma4CxU/CVppAWpN2xPFrIaOgIhMRwAIB/VJxMIcA35hjf5IDMFeevGFtqUCMHtxvFeDWsQlFw4WVR3aaATnQypNC3lukH4kgko7v6IsVj0NoaVOJd2kpNmivlcQGNGX1fg+wfkuF2vksKWfsJVZjM8GUoqMiu";

       
        String Ittparty_Tms = "20180628101113019";
        String Ittparty_Jrnl_No = "223456722912340049222899H";

        //String Mkt_Id = "41060860802001";
        //String Cmdty_Ordr_No="201811131711";
        String Refund_Rsp_St = "00";
        String Py_Trn_No = "10555100000715401111929484212H";
        //用来标识采用哪种加密算法,1表示采用SM2加密算法，2表示采用RSA加密算法。如果不送该字段则默认为SM2加密算法
        String Vno = "3";
        JSONObject json = new JSONObject();
        json.put("Ittparty_Tms", Ittparty_Tms);
        json.put("Ittparty_Jrnl_No", Ittparty_Jrnl_No);//不许重复
        json.put("Refund_Rsp_St", Refund_Rsp_St);
        json.put("Py_Trn_No", Py_Trn_No);
        //如果默认使用SM2时，可以不拼接该字段。
        json.put("Vno", Vno);

        String jsonString = SplicingUtil.createSign(json.toString());
        System.out.println("加签前的字符串为:" + jsonString);
        String signInf;
        if (Integer.parseInt(Vno)>1) {
            signInf = RSASignUtil.sign(private_RSA, jsonString);
        } else {
            signInf = SignUtil.sign(jsonString, plat_Pk);
        }
        json.put("Sign_Inf", signInf);
        System.out.println("退款结果请求json："+json);

        try {
            String result = HttpUtil.doJsonPost(testMrgRefundUrl, json.toString());
            System.out.println(result);
            //开始验签
            JSONObject jsonObject = JSONObject.parseObject(result);
            //验签原串
            jsonString = SplicingUtil.createSign(result);
            signInf = jsonObject.getString("Sign_Inf");
            System.out.println("验签结果:" + RSASignUtil.verifySign(plat_Pk, jsonString, signInf));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
