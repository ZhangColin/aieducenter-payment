package com.icbc.api;

import com.icbc.api.request.BcssCateringDinerStatsQueryRequestV1;

/**
 * 实时查询菜单分组下就餐人数统计测试类
 * @author kfzx-huangqy01
 */
public class BcssCateringDinerStatsQueryV1Test {
    /** 网关公钥 */
    protected static final String APIGW_PUBLIC_KEY = "XXXXXXX";
    protected static final String APP_ID = "XXXX";
    /** 密钥对认证方式，公钥在API平台登记，此处为私钥 */
    protected static final String MY_PRIVATE_KEY = "XXXXXXX";
    /** 加密类型和秘钥 */
    protected static final String encryptType = "xxxx";
    protected static final String encryptKey = "xxxx";

    public static void main(String[] args) throws IcbcApiException {

        BcssCateringDinerStatsQueryRequestV1 request = new BcssCateringDinerStatsQueryRequestV1();
        // 根据测试环境和生产环境替换相应ip和端口
        request.setServiceUrl("http://***.***.***.***:****/api/bcss/query/dinerStats/V1");
        BcssCateringDinerStatsQueryRequestV1.BcssCateringDinerStatsQueryRequestBizV1 bizContent
                = new BcssCateringDinerStatsQueryRequestV1.BcssCateringDinerStatsQueryRequestBizV1();
        bizContent.setCorpId("2000000001");
        bizContent.setMemmerNo("20000000010000000001");
        bizContent.setBeginTime("090000");
        bizContent.setEndTime("140000");
        bizContent.setDeDuplicateFlag("1");
        bizContent.setClientTransNo("12345678901234567890");
        request.setBizContent(bizContent);
        // 签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA, MY_PRIVATE_KEY,  APIGW_PUBLIC_KEY);
        client.execute(request);
    }
}
