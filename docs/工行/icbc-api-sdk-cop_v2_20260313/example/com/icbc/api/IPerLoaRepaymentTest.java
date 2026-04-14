package com.icbc.api;

import java.util.HashMap;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.request.IPerLoaRepaymentResqusetV1;
import com.icbc.api.response.IPerLoaRepaymentResponseV1;

public class IPerLoaRepaymentTest {
    //appid,privateKey,apigwPublicKey  根据实际的应用场景进行修攄1�7
    protected static final String appId="123";
    protected static final String privateKey="xxxxxx";
    protected static final String apigwPublicKey="xxxxxx";

    public static void main(String[] args) throws IcbcApiException{
        DefaultIcbcClient client = new DefaultIcbcClient(appId, privateKey,apigwPublicKey);
        IPerLoaRepaymentResqusetV1 request = new IPerLoaRepaymentResqusetV1();

        request.setServiceUrl("https://ip:port/api/mybank/loan/inclusivefinance/repaymentapy/V1");
        IPerLoaRepaymentResqusetV1.IPerLoaRepaymentBizContent bizContent = new IPerLoaRepaymentResqusetV1.IPerLoaRepaymentBizContent();
        HashMap<String,Object> param = new HashMap<String,Object>();
        //公共参数
        param.put("serialNo","123");
        param.put("appNo","F-APIP");
        param.put("areaCode","");
        param.put("employeeCode","");
        param.put("language","zh_CN");
        param.put("transNo","IQueryElecContractTextCocoaService");
        param.put("ver","1.0");
        param.put("turnPageFlag","");
        param.put("beginRow","");
        param.put("rowCount","");
        //私有参数
        param.put("cooperUnit","ST2313014");
        param.put("applyNo","123");
        param.put("ebankLoanApplyNo","123");
        param.put("cnltype","303");
        param.put("dealType","1");
        param.put("lnno","1");
        param.put("repayAmt","1");
        //System.out.println("---------------"+param);

        bizContent.setParam(param);
        request.setBizContent(bizContent);
        //System.out.println("-------param--------"+param);
        IPerLoaRepaymentResponseV1 response=new IPerLoaRepaymentResponseV1();
        try{
            response = client.execute(request);
        }catch(Exception e){
         //   System.out.println(e.getMessage());
        }
        if(response.isSuccess()){
        //    System.out.println("success!");
        }else{
        //    System.out.println("fail"+response.getReturnMsg());
        }
    }
}
