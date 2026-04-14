package com.icbc.api;

import com.icbc.api.request.ThirdpartyGoodsInfobackRequestV1;
import com.icbc.api.response.ThirdpartyGoodsInfobackResponseV1;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ThirdpartyGoodsInfobackTestV1 {
    // 1、网关公钥
    protected static final String APIGW_PUBLIC_KEY = "[需替换]工行API网关公钥，请向工行联系获取";

    // 2、appid
    protected static final String APIGW_APPID = "";//例如 10000000000000002697

    // 3、密钥对认证方式，公钥在API平台登记，此处为私钥
    protected static final String MY_PRIVATE_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";

    public static void main(String[] args) throws Exception {

        // 签名类型为RSA2时，需传入APIGW-APPID，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
        DefaultIcbcClient client = new DefaultIcbcClient(APIGW_APPID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY,
                APIGW_PUBLIC_KEY);

        ThirdpartyGoodsInfobackRequestV1 request = new ThirdpartyGoodsInfobackRequestV1();
        // 4、根据测试环境和生产环境替换相应ip和端口
        String host = "[需替换]工行API网关地址";
        String apiUrl = host+"/api/thirdinter/sendUserMsg/V2";
        request.setServiceUrl(apiUrl);
        // 5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
        ThirdpartyGoodsInfobackRequestV1.ThirdpartyGoodsInfobackRequestV1Biz bizContent = new ThirdpartyGoodsInfobackRequestV1.ThirdpartyGoodsInfobackRequestV1Biz();
        bizContent.setMer_id("123");//线下协议编号
        bizContent.setOut_sys_onlyid("123");// 商户系统唯一标识
        bizContent.setOrder_id("123");//行内系统订单号
        bizContent.setInfo_source("1");//数据来源，1-爱购8.8
        request.setBizContent(bizContent);
        ThirdpartyGoodsInfobackResponseV1 response;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
            String msg_id = dateFormat.format(new Date());
            response = (ThirdpartyGoodsInfobackResponseV1) client.execute(request, msg_id);
            if (response.isSuccess()) {
                // 业务成功处理
                ThirdpartyGoodsInfobackResponseV1.ThirdpartyGoodsInfobackResponseV1BizContent responseV1BizContent = new ThirdpartyGoodsInfobackResponseV1.ThirdpartyGoodsInfobackResponseV1BizContent();
                String return_code = responseV1BizContent.getReturn_code();
                String return_msg = responseV1BizContent.getReturn_msg();
                String msg_id1 = responseV1BizContent.getMsg_id();
                String mer_id = responseV1BizContent.getMer_id();
                String out_sys_onlyid = responseV1BizContent.getOut_sys_onlyid();
                String order_id = responseV1BizContent.getOrder_id();
                String info_source = responseV1BizContent.getInfo_source();
                System.out.println("ReturnCode:" + response.getReturnCode()+return_code+" "+return_msg+" "+msg_id1
                        +" "+mer_id+" "+out_sys_onlyid+" "+order_id+" "+info_source);
                System.out.println("response:" + response);
            } else {
                // 失败
                System.out.println("ReturnCode:" + response.getReturnCode());
                System.out.println("ReturnMsg:" + response.getReturnMsg());
            }
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }
}
