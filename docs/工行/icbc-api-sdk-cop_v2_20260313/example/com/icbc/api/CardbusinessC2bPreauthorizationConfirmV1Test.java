package com.icbc.api;

import com.icbc.api.request.CardbusinessC2bPreAuthorizationConfirmRequestV1;
import com.icbc.api.request.CardbusinessNcpayPreAuthorizationConfirmRequestV1;
import com.icbc.api.response.CardbusinessC2bPreAuthorizationConfirmResponseV1;
import com.icbc.api.response.CardbusinessNcpayPreAuthorizationConfirmResponseV1;

public class CardbusinessC2bPreauthorizationConfirmV1Test {
    //1、网关公钥
    protected static final String APIGW_PUBLIC_KEY = "********";

    //2、appid
    protected static final String APP_ID = "******";

    //3、密钥对认证方式，公钥在API平台登记，此处为私钥
    protected static final String MY_PRIVATE_KEY = "************";

    public static void main(String[] args) throws Exception {

        //签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

        CardbusinessC2bPreAuthorizationConfirmRequestV1 request = new CardbusinessC2bPreAuthorizationConfirmRequestV1();

        //4、根据测试环境和生产环境替换相应ip和端口 http://ip:port/api
        request.setServiceUrl("http://ip:port/api/cardbusiness/c2b/preauthorization/confirm/V1");
        //5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
        CardbusinessNcpayPreAuthorizationConfirmRequestV1.CardbusinessNcpayPreAuthorizationConfirmRequestV1Biz bizContent = new CardbusinessNcpayPreAuthorizationConfirmRequestV1.CardbusinessNcpayPreAuthorizationConfirmRequestV1Biz();
        bizContent.setOutTradeNo("20200******005");
        bizContent.setOrigDateTime("20250501T11:24:31");
        bizContent.setMerPrtclNo("02*******0201");
        bizContent.setMerId("0200****35");
        bizContent.setSignNo("2025063******42561");
        bizContent.setCardNo("");
        bizContent.setOOrderId("893*****649");
        bizContent.setOOutTradeNo("202003****05");
        bizContent.setOTrxAmt("1000");
        bizContent.setOAuthorNo("543210");
        bizContent.setTotalAmt("1000");
        bizContent.setExtendInfo("扩展信息");

        request.setBizContent(bizContent);
        CardbusinessC2bPreAuthorizationConfirmResponseV1 response;
        try {
            response = client.execute(request, "2323***4345345");//msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一

            if (response.isSuccess()) {
                // 6、业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
                //System.out.println("ReturnCode:"+response.getReturnCode());
                //System.out.println("response:" + response);

            } else {
                // 失败
                //System.out.println("ReturnCode:"+response.getReturnCode());
                //System.out.println("ReturnMsg:"+response.getReturnMsg());
            }
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }
}
