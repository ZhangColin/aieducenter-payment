package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.CardbusinessEtcparkFeepersueRequestV1;
import com.icbc.api.response.CardbusinessEtcparkFeepersueResponseV1;

/**
 * @author: Create by kfzx-liyh07
 * @Description:
 * @date:2021/4/25
 */

public class CardbusinessEtcparkFeepersueTestV1 {

    //1、网关公钥
    protected static final String APIGW_PUBLIC_KEY = "网关公钥";
    //2、appid
    protected static final String APP_ID = "appid";

    //3、密钥对认证方式，公钥在API平台登记，此处为私钥
    protected static final String MY_PRIVATE_KEY = "合作方私钥";
    public static void main(String[] args) throws Exception {


        //签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

        CardbusinessEtcparkFeepersueRequestV1 request = new CardbusinessEtcparkFeepersueRequestV1();

        //4、根据测试环境和生产环境替换相应ip和端口
        request.setServiceUrl("http://ip:port/api/cardbusiness/etcpark/feePersue/V1");
        //5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
        CardbusinessEtcparkFeepersueRequestV1.CardbusinessEtcparkFeepersueRequestV1Biz bizContent = new CardbusinessEtcparkFeepersueRequestV1.CardbusinessEtcparkFeepersueRequestV1Biz();
        //plateNum	车牌号	字符	32	是
        bizContent.setPlateNumber("蒙Y88888");
        //entranceTime
        bizContent.setEntranceTime("2021-02-23 14:02:12");
        //exportTime	车辆离场时间	字符	20	是	YYYY-MM-DD hh:mm:ss
        bizContent.setExportTime("2021-02-23 15:45:06");
        //depotId	停车场编号	字符	20	否	E停为停车场生成的车场编号。
        bizContent.setDepotId("xx");
        //plateColor	车牌颜色	字符	3	否	0-蓝色， 1-黄色，2-黑色， 3-白色，4- 渐变绿色 5- 黄绿双拼色 黄绿双拼色6- 蓝白渐变色 蓝白渐变色9- 未确定11-绿色12-红色（若不上送默认为0-蓝色）
        bizContent.setPlateColor("");
        //orderId	入场流水号	字符	16	是	E停车入场生成订单流水号
        bizContent.setOrderId("17929297");
        //actualPay	停车费用	字符	17	否	订单金额，单位元。最多保留两位小数
        bizContent.setActualPay("0.01");
        //entranceLineNo	入场车道编号	字符	50	否
        bizContent.setEntranceLineNo("1");
        //exportLineNo	出场车道编号	字符	50	否
        //copayId	合作方支付流水号	字符	50	否	合作方上送支付流水号
        bizContent.setCopayId("1234");
        request.setBizContent(bizContent);
        CardbusinessEtcparkFeepersueResponseV1 response;
        String msgId = String.valueOf(System.currentTimeMillis());
        try {
            response = client.execute(request, msgId);//msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一
            if (response.getReturnCode() == 0) {
                // 6、业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
                System.out.println("ReturnCode:" + response.getReturnCode());
                System.out.println("response:" + JSON.toJSONString(response));
            } else {
                // 失败
                System.out.println("response:" + JSON.toJSONString(response));
                System.out.println("ReturnCode:" + response.getReturnCode());
                System.out.println("ReturnMsg:" + response.getReturnMsg());
            }

        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }
}
