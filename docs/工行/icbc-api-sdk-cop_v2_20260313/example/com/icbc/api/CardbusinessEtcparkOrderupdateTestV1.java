package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.CardbusinessEtcparkOrderqryRequestV1;
import com.icbc.api.request.CardbusinessEtcparkOrderupdateRequestV1;
import com.icbc.api.response.CardbusinessEtcparkOrderqryResponseV1;
import com.icbc.api.response.CardbusinessEtcparkOrderupdateResponseV1;

/**
 * @author: Create by kfzx-liyh07
 * @Description: 订单金额更新接口
 * @date:2021/4/25
 */

public class CardbusinessEtcparkOrderupdateTestV1 {
    //1、网关公钥
    protected static final String APIGW_PUBLIC_KEY = "网关公钥";
    //2、appid
    protected static final String APP_ID = "appid";

    //3、密钥对认证方式，公钥在API平台登记，此处为私钥
    protected static final String MY_PRIVATE_KEY = "合作方私钥";

    public static void main(String[] args) throws Exception {



        //签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

        CardbusinessEtcparkOrderupdateRequestV1 request = new CardbusinessEtcparkOrderupdateRequestV1();

        //4、根据测试环境和生产环境替换相应ip和端口
        request.setServiceUrl("http://ip:port/api/cardbusiness/etcpark/orderupdate/V1");
        //5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
        CardbusinessEtcparkOrderupdateRequestV1.CardbusinessEtcparkOrderupdateRequestV1Biz bizContent = new CardbusinessEtcparkOrderupdateRequestV1.CardbusinessEtcparkOrderupdateRequestV1Biz();
        bizContent.setOrderId("17929363");
        bizContent.setDepotId("xxx");
        bizContent.setActualPay("0.01");
        bizContent.setPlateNum("粤A00001");
        request.setBizContent(bizContent);
        CardbusinessEtcparkOrderupdateResponseV1 response;
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
