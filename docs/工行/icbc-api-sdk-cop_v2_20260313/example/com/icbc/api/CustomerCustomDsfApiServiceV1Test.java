package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.CustomerCustomDsfApiServiceRequestV1;
import com.icbc.api.response.CustomerCustomDsfApiServiceResponseV1;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.logging.Logger;

public class CustomerCustomDsfApiServiceV1Test {

    // 1、网关公钥
    protected static final String APIGW_PUBLIC_KEY = "[需替换]工行API网关公钥，请向工行联系获取";

    // 2、appid
    protected static final String APIGW_APPID = "";//例如 10000000000000002697

    // 3、密钥对认证方式，公钥在API平台登记，此处为私钥
    protected static final String MY_PRIVATE_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";

    public static void main(String[] args) {
        // 签名类型为RSA2时，需传入APIGW-APPID，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
        DefaultIcbcClient client = new DefaultIcbcClient(APIGW_APPID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY,
                APIGW_PUBLIC_KEY);

        CustomerCustomDsfApiServiceRequestV1 request = new CustomerCustomDsfApiServiceRequestV1();
        // 4、根据测试环境和生产环境替换相应ip和端口
        String host = "[需替换]工行API网关地址";
        String apiUrl= host + "/api/customer/customDsfApiService/V1";
        request.setServiceUrl(apiUrl);
        // 5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
        CustomerCustomDsfApiServiceRequestV1.CustomerCustomDsfApiServiceRequestV1Biz bizContent = new CustomerCustomDsfApiServiceRequestV1.CustomerCustomDsfApiServiceRequestV1Biz();
        String customId = "CG20230320111544442";
        String phone = "15804227780";
        bizContent.setCustomId(customId);
        bizContent.setPhone(phone);
        request.setBizContent(bizContent);
        System.out.println("request:"+ JSONObject.toJSONString(request));

        //创建返回
        CustomerCustomDsfApiServiceResponseV1 responseV1 = new CustomerCustomDsfApiServiceResponseV1();
        JSONObject resmapss = new JSONObject();

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
            String msg_id = dateFormat.format(new Date());
            responseV1 = (CustomerCustomDsfApiServiceResponseV1)client.execute(request,msg_id);
            System.out.println("responseV1"+JSONObject.toJSONString(responseV1));
            if (responseV1.isSuccess()) {
                // 业务成功处理
                System.out.println("ReturnCode:" + responseV1.getReturnCode());
                CustomerCustomDsfApiServiceResponseV1.CustomerCustomDsfApiServiceResponseV1BizContent response_biz_content = new CustomerCustomDsfApiServiceResponseV1.CustomerCustomDsfApiServiceResponseV1BizContent();
                Map dataMap = responseV1.getData();
                String customGroupIds = (String) dataMap.get("customGroupIds");
                resmapss.put("customGroupIds", customGroupIds);
                String resMsg = responseV1.getReturnMsg();
                response_biz_content = (CustomerCustomDsfApiServiceResponseV1.CustomerCustomDsfApiServiceResponseV1BizContent) JSONObject.parse(resMsg);
                resmapss.put("result",response_biz_content.getResult());
                resmapss.put("data",response_biz_content.getData());
                resmapss.put("return_code",response_biz_content.getReturn_code());
                resmapss.put("return_msg",response_biz_content.getReturn_msg());
                System.out.println("resmapss:" + JSONObject.toJSONString(resmapss));
            }else{
                // 失败
                resmapss.put("returnMsg",responseV1.getReturnMsg());
                resmapss.put("returnCode",responseV1.getReturnCode());
                System.out.println("ReturnCode:" + responseV1.getReturnCode()+";ReturnMsg:" + responseV1.getReturnMsg());
                resmapss.put("customApiTest--returnMsg", responseV1.getReturnCode() + "****" + responseV1.getReturnMsg());
            }
        } catch(Exception e) {
            e.printStackTrace();
            resmapss.put("customApiTest--returnMsg", e.getMessage());
        }
    }
}
