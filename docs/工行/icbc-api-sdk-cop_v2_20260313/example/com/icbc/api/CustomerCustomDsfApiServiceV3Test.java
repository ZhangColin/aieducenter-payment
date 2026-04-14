package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.CustomerCustomDsfApiServiceRequestV3;
import com.icbc.api.response.CustomerCustomDsfApiServiceResponseV3;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomerCustomDsfApiServiceV3Test {

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

        CustomerCustomDsfApiServiceRequestV3 request = new CustomerCustomDsfApiServiceRequestV3();
        // 4、根据测试环境和生产环境替换相应ip和端口
        String host = "[需替换]工行API网关地址";
        String apiUrl= host + "/api/customer/customDsfApiService/V3";
        request.setServiceUrl(apiUrl);
        // 5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
        CustomerCustomDsfApiServiceRequestV3.CustomerCustomDsfApiServiceRequestV3Biz bizContent = new CustomerCustomDsfApiServiceRequestV3.CustomerCustomDsfApiServiceRequestV3Biz();
        String customId = "CG20230320111544442";
        String phone = "15804227780";
        String customType = "1";
        bizContent.setCustomId(customId);
        bizContent.setPhone(phone);
        bizContent.setCustomType(customType);
        request.setBizContent(bizContent);
        System.out.println("request:"+ JSONObject.toJSONString(request));

        //创建返回
        CustomerCustomDsfApiServiceResponseV3 responseV3 = new CustomerCustomDsfApiServiceResponseV3();

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
            String msg_id = dateFormat.format(new Date());
            responseV3 = (CustomerCustomDsfApiServiceResponseV3) client.execute(request,msg_id);
            System.out.println("responseV3"+JSONObject.toJSONString(responseV3));
                // 业务成功处理
            System.out.println("Res:" + responseV3.getRes());
            System.out.println("Ret_code:" + responseV3.getRet_code());
            System.out.println("Return_code:" + responseV3.getReturnCode());
            System.out.println("Return_msg:" + responseV3.getReturnMsg());

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
