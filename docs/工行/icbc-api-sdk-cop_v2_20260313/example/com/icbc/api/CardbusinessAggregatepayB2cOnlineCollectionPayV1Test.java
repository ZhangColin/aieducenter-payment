package com.icbc.api;

import com.icbc.api.request.CardbusinessAggregatepayB2cOnlineCollectionPayRequestV1;
import com.icbc.api.response.CardbusinessAggregatepayB2cOnlineCollectionPayResponseV1;

/**
 * @Author kfzx-gucy
 * @Date 2022/06/27
 */
public class CardbusinessAggregatepayB2cOnlineCollectionPayV1Test {
    //1、网关公钥
    protected static final String APIGW_PUBLIC_KEY = "********";

    //2、appid
    protected static final String APP_ID = "******";

    //3、密钥对认证方式，公钥在API平台登记，此处为私钥
    protected static final String MY_PRIVATE_KEY = "************";

    public static void main(String[] args) throws Exception {

        //签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

        CardbusinessAggregatepayB2cOnlineCollectionPayRequestV1 request = new CardbusinessAggregatepayB2cOnlineCollectionPayRequestV1();

        //4、根据测试环境和生产环境替换相应ip和端口 http://ip:port/api
        request.setServiceUrl("https://ip:port/api/cardbusiness/aggregatepay/b2c/online/collectionpay/V1");
        //5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
        CardbusinessAggregatepayB2cOnlineCollectionPayRequestV1.CardbusinessAggregatepayB2cOnlineCollectionPayRequestV1Biz bizContent = new CardbusinessAggregatepayB2cOnlineCollectionPayRequestV1.CardbusinessAggregatepayB2cOnlineCollectionPayRequestV1Biz();
        bizContent.setOrigDatetime("20250501T11:24:31");
        bizContent.setMerPrtclNo("0200060304000201");
        bizContent.setMerId("020006030400");
        bizContent.setAttach("附加数据");
        bizContent.setAccNo("1234135325");//商户入账账号
        bizContent.setOutTradeNo("020006030400123");
        bizContent.setOrderAmt("1000");
        bizContent.setTrnscCcy("001");
        bizContent.setMerUrl("http://www.test.com/notifyurl");
        bizContent.setNotifyType("HS");
        bizContent.setResultType("0");
        bizContent.setExpireTime("3000");
        bizContent.setOrderApdInf("3000");
        bizContent.setContractid("1421654w364");
        bizContent.setTxnTime("20220627132332");


        request.setBizContent(bizContent);
        CardbusinessAggregatepayB2cOnlineCollectionPayResponseV1 response;
        try {
            response = client.execute(request, "2323***4345345");//msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一

            if (response.isSuccess()) {
                // 6、业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
                System.out.println("ReturnCode:"+response.getReturnCode());
                System.out.println("response:" + response);

            } else {
                // 失败
                System.out.println("ReturnCode:"+response.getReturnCode());
                System.out.println("ReturnMsg:"+response.getReturnMsg());
            }
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }
}
