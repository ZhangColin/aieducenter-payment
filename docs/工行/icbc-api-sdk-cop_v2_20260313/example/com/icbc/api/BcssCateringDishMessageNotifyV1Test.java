package com.icbc.api;

import com.icbc.api.request.BcssCateringDishMessageNotifyRequestV1;
import com.icbc.api.request.BcssCateringDishMessageNotifyRequestV1.BcssCateringDishMessageNotifyRequestBizV1;

public class BcssCateringDishMessageNotifyV1Test {
    // 网关公钥
    protected static final String APIGW_PUBLIC_KEY = "XXXXXXX";
    protected static final String APP_ID = "XXXX";
    // 密钥对认证方式，公钥在API平台登记，此处为私钥
    protected static final String MY_PRIVATE_KEY = "XXXXXXX";
    // 加密类型和秘钥
    protected static final String encryptType = "xxxx";
    protected static final String encryptKey = "xxxx";

    public static void main(String[] args) throws IcbcApiException {

        BcssCateringDishMessageNotifyRequestV1 request = new BcssCateringDishMessageNotifyRequestV1();
        // 根据测试环境和生产环境替换相应ip和端口
        request.setServiceUrl("http://***.***.***.***:****/api/bcss/dish/approvalInfo/insert/V1");
        BcssCateringDishMessageNotifyRequestBizV1 bizContent = new BcssCateringDishMessageNotifyRequestBizV1();
        bizContent.setCorpId("corpId1");
        bizContent.setMemmerNo("memmerNo1");
        bizContent.setTypeId("typeId1");
        bizContent.setDishId("dishId1");
        bizContent.setApprovalNo("approvalNo1");
        bizContent.setApprovalResult(0);
        bizContent.setRemarks("remarks1");
        bizContent.setApprovalDate("2022-04-01 00:00:00");
        request.setBizContent(bizContent);
        // 签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA, MY_PRIVATE_KEY,
                APIGW_PUBLIC_KEY);
        client.execute(request);
    }
}
