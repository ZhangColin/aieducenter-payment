package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.CardbusinessEtcparkEventNotifyRequestV1;
import com.icbc.api.response.CardbusinessEtcparkEventNotifyResponseV1;

/**
 * @Description:epark事件通知
 * @date:2021/7/29
 */

public class CardbusinessEtcparkEventNotifyTestV1 {
    //1、网关公钥
    protected static final String APIGW_PUBLIC_KEY = "网关公钥";
    //2、appid
    protected static final String APP_ID = "appid";

    //3、密钥对认证方式，公钥在API平台登记，此处为私钥
    protected static final String MY_PRIVATE_KEY = "合作方私钥";

    public static void main(String[] args) throws Exception {

        //签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

        CardbusinessEtcparkEventNotifyRequestV1 request = new CardbusinessEtcparkEventNotifyRequestV1();

        //4、根据测试环境和生产环境替换相应ip和端口
        request.setServiceUrl("http://ip:port/api/cardbusiness/etcpark/eventNotify/V1");
        //5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
        CardbusinessEtcparkEventNotifyRequestV1.CardbusinessEtcparkEventNotifyRequestV1Biz bizContent = new CardbusinessEtcparkEventNotifyRequestV1.CardbusinessEtcparkEventNotifyRequestV1Biz();
        bizContent.setEventType("1");
        bizContent.setEventId("epark"+System.currentTimeMillis());
        bizContent.setDepotId("znpark0000000371");
        bizContent.setEventTime("2021-07-29 11:11:11");
        CardbusinessEtcparkEventNotifyRequestV1.CardbusinessEtcparkEventNotifyRequestV1Biz.EventData data = new   CardbusinessEtcparkEventNotifyRequestV1.CardbusinessEtcparkEventNotifyRequestV1Biz.EventData();
        data.setPlateNumber("粤A66666,粤A55555");
        data.setPlateColor("0,0");
        data.setTimeout("");
        bizContent.setData(data);
        request.setBizContent(bizContent);
        CardbusinessEtcparkEventNotifyResponseV1 response;
        String msgId = String.valueOf(System.currentTimeMillis());
        try {
            response = client.execute(request, msgId);//msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一
            System.out.println(JSON.toJSON(response));
            if(response.getReturnCode()==0){
                //成功

            } else if(response.getReturnCode() == 1){
                // 失败

            }else if(response.getReturnCode() == 2){
                //部分成功
            }

        }catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }
}
