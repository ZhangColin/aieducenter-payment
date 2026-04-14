package com.icbc.api;

import java.util.HashMap;

import com.icbc.api.request.IQueryElecContractRequestV1;
import com.icbc.api.response.IQueryElecContractResponseV1;

public class IQueryElecContractTest {
    //appid,privateKey,apigwPublicKey  根据实际的应用场景进行修攄1�7
    protected static final String appId="123";
    protected static final String privateKey="xxxxxx";
    protected static final String apigwPublicKey="xxxxxx";

    public static void main(String[] args) throws IcbcApiException{
        DefaultIcbcClient client = new DefaultIcbcClient(appId, privateKey,apigwPublicKey);
        IQueryElecContractRequestV1 request = new IQueryElecContractRequestV1();

        request.setServiceUrl("https://ip:port/api/mybank/loan/inclusivefinance/contracttextqry/V1");
        IQueryElecContractRequestV1.IQueryElecContractBizContent bizContent = new IQueryElecContractRequestV1.IQueryElecContractBizContent();
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
        param.put("transDate","20170101");
        param.put("switchType","3");
        param.put("queryType","2");
        param.put("isSupportSVG","1");
        param.put("modelType","ZH0100");
        //System.out.println("-------合同文本查询入参--------"+param);

        bizContent.setParam(param);
        request.setBizContent(bizContent);
        //System.out.println("-------param--------"+param);
        IQueryElecContractResponseV1 response=new IQueryElecContractResponseV1();
        try{
            response = client.execute(request);
        }catch(Exception e){
        //    System.out.println(e.getMessage());
        }
        if(response.isSuccess()){
         //   System.out.println("success!");
        }else{
         //   System.out.println("fail"+response.getReturnMsg());
        }
    }

}
