package com.test;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ccb.mktpay.sign.RSASignUtil;
import com.test.HttpUtil;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Map;

/**
 * 聚合下单--合并订单
 */
public class gatherByMergePlaceorder {
    public static void main(String[] args) {
        //访问地址
        String url = "http://marketpayktwo.dev.jh:8028/online/direct/gatherPlaceorder";
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
        //发起方流水号
        String Ittparty_Jrnl_No = System.currentTimeMillis() + "";
        //市场编号
        String Mkt_Id = "41060860898016"; ///////
        //主订单编号
        String Main_Ordr_No = System.currentTimeMillis() + "";
        //支付方式代码：01 PC端   02 线下支付（无收银台） 03 移动端H5页面 (app)
        //05 微信小程序（无收银台）06 对私网银（无收银台） 07 聚合二维码（无收银台）
        String Pymd_Cd = "03";
        //订单类型:02 消费券购买订单 03 在途订单 04 普通订单  05 线下订单
        String Py_Ordr_Tpcd = "04";
        //币种
        String Ccy = "156";
        //页面返回URL地址
        String Pgfc_Ret_Url_Adr = "http://test.zmyou.com/rwy-pay";
        //订单总金额
        String Ordr_Tamt = "6";
        //交易总金额
        String Txn_Tamt = "2";
        //手续费承担方，是否指定手续费承当方，若是指定的话则有，若没有，不用进行填写
        String Hdcg_Brs_Id = "41060860898016000000";
        //ordrlist
        //子订单1
        //商家编号
        String Mkt_Mrch_Id1 = "41060860898016000000";
        //商品订单号 不允许重复
        String Cmdty_Ordr_No1 = Ittparty_Jrnl_No + "01";
        //订单金额 订单商品总金额，即应付金额(商品金额)
        String Ordr_Amt1 = "3";
        //交易金额 消费者实付金额(结算金额)
        String Txnamt1 = "1";
        //附加项总金额
        String Apd_Tamnt1 = "1";
        //子订单2
        //商家编号
        String Mkt_Mrch_Id2 = "41060860898016008602";
        //商品订单号 不允许重复
        String Cmdty_Ordr_No2 = Ittparty_Jrnl_No + "02";
        //订单金额 订单商品总金额，即应付金额(商品金额)
        String Ordr_Amt2 = "3";
        //交易金额 消费者实付金额(结算金额)
        String Txnamt2 = "1";
        //附加项总金额
        String Apd_Tamnt2 = "1";
        //消费券列表 Cpnlist （若无消费券消费时，此域可不填）
        //消费券订单编号
        String Cnsmp_Note_Ordr_Id1 = "C4106086089801631311";
        //消费券金额
        String Amt3 = "1";
        //消费券列表 Cpnlist （若无消费券消费时，此域可不填）
        //消费券订单编号
        String Cnsmp_Note_Ordr_Id2 = "C4106086089801631140";
        //消费券金额
        String Amt33 = "1";
        //Prjlist 附加项列表
        //项目编号
        String Prj_Id = "P410608608980168604";
        //项目名称
        String Prj_Nm = "减项";
        //项目类型 1-加项；2-减项; 3积分
        String Pjcy_Tp = "2";
        //金额
        String Amt = "1";
        //版本号
        String Vno = "4";

        JSONObject json = new JSONObject(true);
        //发起方渠道编号
        json.put("Ittparty_Stm_Id", Ittparty_Stm_Id);
        //发起方渠道代码
        json.put("Py_Chnl_Cd", Py_Chnl_Cd);
        //发起方时间戳
        json.put("Ittparty_Tms", Ittparty_Tms);
        //发起方流水号 不许重复
        json.put("Ittparty_Jrnl_No", Ittparty_Jrnl_No);
        //市场编号
        json.put("Mkt_Id", Mkt_Id);
        //主订单编号
        json.put("Main_Ordr_No", Main_Ordr_No);
        //支付方式代码
        json.put("Pymd_Cd", Pymd_Cd);
        //订单类型
        json.put("Py_Ordr_Tpcd", Py_Ordr_Tpcd);
        //币种
        json.put("Ccy", Ccy);
        //页面返回URL地址
        json.put("Pgfc_Ret_Url_Adr", Pgfc_Ret_Url_Adr);
        //订单总金额
        json.put("Ordr_Tamt", Ordr_Tamt);
        //交易总金额
        json.put("Txn_Tamt", Txn_Tamt);
        //手续费承担方,若指定手续费承担方则添加
        //json.put("Hdcg_Brs_Id", Hdcg_Brs_Id);
        //版本号
        json.put("Vno", Vno);

        JSONArray orderListJsonArray1 = new JSONArray();
        //子订单
        JSONObject order1 = new JSONObject();
        //商家编号
        order1.put("Mkt_Mrch_Id", Mkt_Mrch_Id1);
        //商品订单号
        order1.put("Cmdty_Ordr_No", Cmdty_Ordr_No1);
        //订单金额
        order1.put("Ordr_Amt", Ordr_Amt1);
        //交易金额
        order1.put("Txnamt", Txnamt1);
        //附加项总金额
        order1.put("Apd_Tamnt", Apd_Tamnt1);

        //消费券列表，若无消费券，可以不用写
        JSONArray Cpnlist1 = new JSONArray();
        JSONObject orderByCpnlist1 = new JSONObject();
        orderByCpnlist1.put("Cnsmp_Note_Ordr_Id", Cnsmp_Note_Ordr_Id1);
        orderByCpnlist1.put("Amt", Amt3);
        Cpnlist1.add(orderByCpnlist1);
        order1.put("Cpnlist", Cpnlist1);

        //附加项列表,若无附加项，可以不用写
        JSONArray Prjlist1 = new JSONArray();
        JSONObject orderByPrjlist1 = new JSONObject();
        orderByPrjlist1.put("Prj_Id", Prj_Id);
        orderByPrjlist1.put("Prj_Nm", Prj_Nm);
        orderByPrjlist1.put("Pjcy_Tp", Pjcy_Tp);
        orderByPrjlist1.put("Amt", Amt);
        Prjlist1.add(orderByPrjlist1);
        order1.put("Prjlist", Prjlist1);
        orderListJsonArray1.add(order1);


        //子订单编号2
        JSONObject order2 = new JSONObject();
        //商家编号
        order2.put("Mkt_Mrch_Id", Mkt_Mrch_Id2);
        //商品订单号
        order2.put("Cmdty_Ordr_No", Cmdty_Ordr_No2);
        //订单金额
        order2.put("Ordr_Amt", Ordr_Amt2);
        //交易金额
        order2.put("Txnamt", Txnamt2);
        //附加项总金额
        order2.put("Apd_Tamnt", Apd_Tamnt2);


        //消费券列表2，若无消费券，可以不用写
        JSONArray Cpnlist2 = new JSONArray();
        JSONObject orderByCpnlist2 = new JSONObject();
        orderByCpnlist2.put("Cnsmp_Note_Ordr_Id", Cnsmp_Note_Ordr_Id2);
        orderByCpnlist2.put("Amt", Amt33);
        Cpnlist2.add(orderByCpnlist2);
        order2.put("Cpnlist", Cpnlist2);

        //附加项列表2，若无附加项，可以不用写
        JSONArray Prjlist2 = new JSONArray();
        JSONObject orderByPrjlist2 = new JSONObject();
        orderByPrjlist2.put("Prj_Id", Prj_Id);
        orderByPrjlist2.put("Prj_Nm", Prj_Nm);
        orderByPrjlist2.put("Pjcy_Tp", Pjcy_Tp);
        orderByPrjlist2.put("Amt", Amt);
        Prjlist2.add(orderByPrjlist2);
        order2.put("Prjlist", Prjlist2);
        orderListJsonArray1.add(order2);
        json.put("Orderlist", orderListJsonArray1);

        System.out.println(json.toString());
        String jsonString = SplicingUtil.createSign(json.toString());
        System.out.println("加签字符串：" + jsonString);
        String signInf = RSASignUtil.sign(private_RSA, jsonString);
        json.put("Sign_Inf", signInf);

        System.out.println("传入的json串：" + json);
        try {
            String result = HttpUtil.doJsonPost(url, json.toString());
            System.out.println("result=" + result);
            //开始验签
            JSONObject jsonObject = JSONObject.parseObject(result);
            //验签原串
            jsonString = SplicingUtil.createSign(result);
            System.out.println("输出的原串为" + jsonString);
            signInf = jsonObject.getString("Sign_Inf");
            System.out.println("验签结果:" + RSASignUtil.verifySign(plat_Pk, jsonString, signInf));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}