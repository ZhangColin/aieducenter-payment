package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.ThirdinterSendUserMsgRequestV2;
import com.icbc.api.response.ThirdinterSendUserMsgResponseV2;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.logging.Logger;

public class ThirdinterSendUserMsgTestV2 {
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

        ThirdinterSendUserMsgRequestV2 request = new ThirdinterSendUserMsgRequestV2();
        // 4、根据测试环境和生产环境替换相应ip和端口
        String host = "[需替换]工行API网关地址";
        String apiUrl = host+"/api/thirdinter/sendUserMsg/V2";
        request.setServiceUrl(apiUrl);
        // 5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
        ThirdinterSendUserMsgRequestV2.ThirdinterSendUserMsgRequestV2Biz bizContent = new ThirdinterSendUserMsgRequestV2.ThirdinterSendUserMsgRequestV2Biz();
        String cisno = "020012300423239";
        String third_id="SF00200000402";
        bizContent.setCisno(cisno);// 客编
        bizContent.setThird_id(third_id);// 三方编号
        request.setBizContent(bizContent);
        ThirdinterSendUserMsgResponseV2 response;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
            String msg_id = dateFormat.format(new Date());
            response = (ThirdinterSendUserMsgResponseV2) client.execute(request, msg_id);
            if (response.isSuccess()) {
                // 业务成功处理
                ThirdinterSendUserMsgResponseV2.ThirdinterSendUserMsgResponseV2BizContent responseV1BizContent = new ThirdinterSendUserMsgResponseV2.ThirdinterSendUserMsgResponseV2BizContent();
                String return_code = responseV1BizContent.getReturn_code();
                String return_msg = responseV1BizContent.getReturn_msg();
                String trans_map = responseV1BizContent.getTrans_map();
                String is_employee = responseV1BizContent.getIs_employee();
                String is_credit_card_user = responseV1BizContent.getIs_credit_card_user();
                String is_debit_card_user = responseV1BizContent.getIs_debit_card_user();
                String card_jointly_no = responseV1BizContent.getCard_jointly_no();
                String card_production_no = responseV1BizContent.getCard_production_no();
                String credit_card_bin = responseV1BizContent.getCredit_card_bin();
                String credit_card_level = responseV1BizContent.getCredit_card_level();
                System.out.println("ReturnCode:" + response.getReturnCode()+return_code+" "+return_msg+" "+trans_map+" "+is_employee+" "+is_credit_card_user
                        +" "+is_debit_card_user+" "+card_jointly_no+" "+card_production_no+" "+credit_card_bin+" "+credit_card_level);
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
