package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.CardbusinessEtcparkOrdersyncRequestV1;
import com.icbc.api.response.CardbusinessEtcparkOrdersyncResponseV1;

/**
 * @Description: 出场通知接口
 * @date:2021/4/26
 */
public class CardbusinessEtcparkOrdersyncTestV1 {
    //1、网关公钥
    protected static final String APIGW_PUBLIC_KEY = "网关公钥";
    //2、appid
    protected static final String APP_ID = "appid";
    //3、密钥对认证方式，公钥在API平台登记，此处为私钥
    protected static final String MY_PRIVATE_KEY = "合作方私钥";

    public static void main(String[] args) {
        //签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

        CardbusinessEtcparkOrdersyncRequestV1 request = new CardbusinessEtcparkOrdersyncRequestV1();
        //4、根据测试环境和生产环境替换相应ip和端口
        request.setServiceUrl("http://ip:port/api/cardbusiness/etcpark/ordersync/V1");
        //5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
        CardbusinessEtcparkOrdersyncRequestV1.CardbusinessEtcparkOrdersyncRequestV1Biz bizContent = new CardbusinessEtcparkOrdersyncRequestV1.CardbusinessEtcparkOrdersyncRequestV1Biz();
        bizContent.setOrderId("17929365");
        bizContent.setCopayId("654610");
        bizContent.setPlateNum("粤A00001");
        bizContent.setActualPay("0.01");
        bizContent.setEntranceTime("2021-03-16 13:13:15");
        bizContent.setExportTime("2021-03-17 13:13:15");
        bizContent.setDepotId("xx");
        bizContent.setPlateColor("0");
        bizContent.setExportLineNo("1");
        bizContent.setIsBookCar("1");
        bizContent.setBookId("xxx");
        request.setBizContent(bizContent);
        //msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一
        String msgId = String.valueOf(System.currentTimeMillis());
        try {
            CardbusinessEtcparkOrdersyncResponseV1 response = client.execute(request, msgId);
            System.out.println(JSON.toJSON(response));
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }
}
