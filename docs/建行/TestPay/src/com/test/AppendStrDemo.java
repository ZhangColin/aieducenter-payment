package com.test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 字符串拼接例子
 * 1.公共字段：Sign_Inf, Svc_Rsp_St, Svc_Rsp_Cd, Rsp_Inf不参与拼接
 * 2.空字符串不参与拼接：如例子中的Blank, Blank2, Blank3
 * 3.字段采用&符号拼接，按照字母顺序进行拼接
 * 4.① json数组拼接时，数组所在的位置是以外层key为准的，如例子中的Parlist。
 *   因此该数组排在Amt之后，Pymd_Cd之前。外层key不参与拼接
 *   ② 数组中的json对象内部按照字母顺序进行拼接，json对象之间的顺序不变。
 *
 */
public class AppendStrDemo {
    public static void main(String[] args) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Pymd_Cd", "pymdCd");
        jsonObject.put("Amt", "amt");

        jsonObject.put("Sign_Inf", "signInf");
        jsonObject.put("Svc_Rsp_St", "svcRspSt");
        jsonObject.put("Svc_Rsp_Cd", "svcRspCd");
        jsonObject.put("Rsp_Inf", "rspInf");

        jsonObject.put("Blank", null);
        jsonObject.put("Blank2", "");
        jsonObject.put("Blank3", " ");

        JSONArray parListJsonArray = new JSONArray();
        JSONObject par1 = new JSONObject();
        par1.put("Seq_No", "seqNo");
        par1.put("Mkt_Mrch_Id", "mktMrchIdFj");
        parListJsonArray.add(par1);

        JSONObject par2 = new JSONObject();
        par2.put("Seq_No", "seqNo2");
        par2.put("Mkt_Mrch_Id", "mktMrchIdFj2");
        parListJsonArray.add(par2);

        JSONObject par3 = new JSONObject();
        par3.put("Seq_No", "seqNo3");
        par3.put("Mkt_Mrch_Id", "mktMrchIdFj3");
        parListJsonArray.add(par3);
        jsonObject.put("Parlist", parListJsonArray);
        System.out.println("拼接前的json字符串为：" + jsonObject.toString());
        String jsonString = SplicingUtil.createSign(jsonObject.toString());
        System.out.println("拼接后的字符串为：" + jsonString);
    }

}
