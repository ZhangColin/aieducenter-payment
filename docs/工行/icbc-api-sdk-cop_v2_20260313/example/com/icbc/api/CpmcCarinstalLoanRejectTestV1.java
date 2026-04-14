package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.CpmcCarinstalLoanRejectRequestV1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CpmcCarinstalLoanRejectTestV1 {

    private static final Logger logger = LoggerFactory.getLogger(CpmcCarinstalLoanRejectTestV1.class);

    public static void main(String[] args) {
        // 示例总体说明：请使用F-APIP最新的行外网关SDK进行开发，签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值

        //1、密钥对认证方式，公钥在F-APIP平台登记，此处为私钥
        String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----";
        //2、appid
        String APP_ID = "-----请使用正确的APPID-----10000000000004096790";
        //3、网关公钥
        String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----";

        //4、创建与F-APIP外网网关的连接
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY,
                "AES", "-----请使用正确的AES秘钥-----", "", "");
        //5、设置请求对象
        CpmcCarinstalLoanRejectRequestV1 request = new CpmcCarinstalLoanRejectRequestV1();
        //6、设置请求路径；联测时，请联系我行告知并替换成测试环境地址。
        request.setServiceUrl("https://ip:port/api/cpmc/carinstal/loan/reject/V1");
        //7、设置业务参数
        CpmcCarinstalLoanRejectRequestV1.CpmcCarinstalLoanRejectRequestV1Biz bizContent = new CpmcCarinstalLoanRejectRequestV1.CpmcCarinstalLoanRejectRequestV1Biz();

        //8、组装请求参数，字段待定，待获取sdp那边最新接口文档
        bizContent.setBankCode("ICBC");
        bizContent.setFinanceNo("HD-JRRJ-20231013-5M4OB");
        bizContent.setFinanceBillNo("vx0010290061288837758327992320");
        bizContent.setCustomerId(1111L);
        bizContent.setReason("驳回测试3！");
        bizContent.setType(1);

        request.setBizContent(bizContent);

        IcbcResponse response;
        try {
            logger.info("request: {}", JSON.toJSONString(request));
            response = client.execute(request);
            logger.info("response: {}", JSON.toJSONString(response));
        } catch (IcbcApiException e) {
            logger.error("调用失败！", e);
        }
    }
}
