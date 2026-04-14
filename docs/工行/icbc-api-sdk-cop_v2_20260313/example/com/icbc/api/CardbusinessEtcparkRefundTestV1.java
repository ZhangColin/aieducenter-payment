package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.CardbusinessEtcparkRefundRequestV1;
import com.icbc.api.response.CardbusinessEtcparkRefundResponseV1;

/**
 * @Description: 退款
 * @date:2020/12/30
 */

public class CardbusinessEtcparkRefundTestV1 {
    //1、网关公钥
    protected static final String APIGW_PUBLIC_KEY = "网关公钥";
    //2、appid
    protected static final String APP_ID = "appid";

    //3、密钥对认证方式，公钥在API平台登记，此处为私钥
    protected static final String MY_PRIVATE_KEY = "合作方私钥";

    public static void main(String[] args) throws Exception {

        //签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

        CardbusinessEtcparkRefundRequestV1 request = new CardbusinessEtcparkRefundRequestV1();

        //4、根据测试环境和生产环境替换相应ip和端口
        request.setServiceUrl("http://ip:port/api/cardbusiness/etcpark/refund/V1");
        //5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
        CardbusinessEtcparkRefundRequestV1.CardbusinessEtcparkRefundRequestV1Biz bizContent = new CardbusinessEtcparkRefundRequestV1.CardbusinessEtcparkRefundRequestV1Biz();
        bizContent.setOrderId("17928756");
        bizContent.setCopayId("");
        bizContent.setRefundAmt("1");
        bizContent.setRefundNo("12345");
        bizContent.setOrderType("");
        bizContent.setPlateNumber("");
        bizContent.setPlateColor("");
        request.setBizContent(bizContent);
        CardbusinessEtcparkRefundResponseV1 response;
        String msgId = String.valueOf(System.currentTimeMillis());
        try {
            response = client.execute(request, msgId);//msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一
            if(response.getReturnCode()==0){
                // 6、业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
                System.out.println("ReturnCode:"+response.getReturnCode());
                System.out.println("response:" + JSON.toJSONString(response));
            } else {
                // 失败
                System.out.println("response:" + JSON.toJSONString(response));
                System.out.println("ReturnCode:"+response.getReturnCode());
                System.out.println("ReturnMsg:"+response.getReturnMsg());
            }

        }catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }
}
