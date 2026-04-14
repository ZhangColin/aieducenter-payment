package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.CardbusinessEtcparkOrdercreateRequestV1;
import com.icbc.api.response.CardbusinessEtcparkOrdercreateResponseV1;

/**
 * @Description: 车辆入场
 * @date:2021/3/16
 */
public class CardbusinessEtcparkOrdercreateTestV1 {
    //1、网关公钥
    protected static final String APIGW_PUBLIC_KEY = "网关公钥";
    //2、appid
    protected static final String APP_ID = "appid";
    //3、密钥对认证方式，公钥在API平台登记，此处为私钥
    protected static final String MY_PRIVATE_KEY = "合作方私钥";

    public static void main(String[] args) {
        //签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

        CardbusinessEtcparkOrdercreateRequestV1 request = new CardbusinessEtcparkOrdercreateRequestV1();
        //4、根据测试环境和生产环境替换相应ip和端口
        request.setServiceUrl("http://ip:port/api/cardbusiness/etcpark/ordercreate/V1");
        //5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
        CardbusinessEtcparkOrdercreateRequestV1.CardbusinessEtcparkOrdercreateRequestV1Biz bizContent = new CardbusinessEtcparkOrdercreateRequestV1.CardbusinessEtcparkOrdercreateRequestV1Biz();
        bizContent.setDepotId("xxx");
        bizContent.setEntranceTime("2021-03-16 13:13:13");
        bizContent.setActualPay("0.01");
        bizContent.setEntranceLineNo("1");
        bizContent.setPlateNum("粤A00001");
        bizContent.setCopayId("1234");
        bizContent.setCarType("2");
        bizContent.setIsBookCar("1");
        bizContent.setBookId("xxx");
        request.setBizContent(bizContent);
        //msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一
        String msgId = String.valueOf(System.currentTimeMillis());
        try {
            CardbusinessEtcparkOrdercreateResponseV1 response = client.execute(request, msgId);
            System.out.println(JSON.toJSON(response));
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }
}
