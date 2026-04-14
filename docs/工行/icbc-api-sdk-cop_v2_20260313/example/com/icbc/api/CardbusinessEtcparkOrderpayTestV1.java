package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.CardbusinessEtcparkOrderpayRequestV1;
import com.icbc.api.response.CardbusinessEtcparkOrderpayResponseV1;


/**
 * @Description: 无感支付 ,聚合下单
 * @date:2021/2/25
 */

public class CardbusinessEtcparkOrderpayTestV1 {
    //1、网关公钥
    protected static final String APIGW_PUBLIC_KEY = "网关公钥";
    //2、appid
    protected static final String APP_ID = "appid";

    //3、密钥对认证方式，公钥在API平台登记，此处为私钥
    protected static final String MY_PRIVATE_KEY = "合作方私钥";
    public static void main(String[] args) throws Exception {




        // ncpay();

        // aggrementPay();
        //无感支付失败生成二维码
        ncpayQrcode();
        //月租车下单
        rentCar_aggrementPay();

    }
    /**
     * 月租车下单
     */
    private static void rentCar_aggrementPay(){
        //签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

        CardbusinessEtcparkOrderpayRequestV1 request = new CardbusinessEtcparkOrderpayRequestV1();

        //4、根据测试环境和生产环境替换相应ip和端口 http://ip:port/api
        request.setServiceUrl("http://ip:port/api/cardbusiness/etcpark/orderpay/V1");
        //5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
        CardbusinessEtcparkOrderpayRequestV1.CardbusinessEtcparkOrderpayRequestV1Biz bizContent = new
                CardbusinessEtcparkOrderpayRequestV1.CardbusinessEtcparkOrderpayRequestV1Biz();
        //plateNum	车牌号	字符	32	是
        bizContent.setPlateNum("蒙Y88888");
        //entranceTime
        bizContent.setEntranceTime("2021-07-01 01:02:12");
        //exportTime	车辆离场时间	字符	20	是	YYYY-MM-DD hh:mm:ss
        bizContent.setExportTime("2021-07-30 15:45:06");
        //depotId	停车场编号	字符	20	否	E停为停车场生成的车场编号。
        bizContent.setDepotId("znpark0000000118");
        //plateColor	车牌颜色	字符	3	否	0-蓝色， 1-黄色，2-黑色， 3-白色，4- 渐变绿色 5- 黄绿双拼色 黄绿双拼色6- 蓝白渐变色 蓝白渐变色9- 未确定11-绿色12-红色（若不上送默认为0-蓝色）
        bizContent.setPlateColor("");
        //orderId	入场流水号	字符	16	是	E停车入场生成订单流水号
        bizContent.setOrderId("");
        //actualPay	停车费用	字符	17	否	订单金额，单位元。最多保留两位小数
        bizContent.setActualPay("0.01");

        //copayId	合作方支付流水号	字符	50	否	合作方上送支付流水号
        bizContent.setCopayId("1234");
        //qrcode	支付码	字符			扫码枪扫描客户二维码时上送
        //opType	操作类型	字符		否	“” 或者null或者”0” 支付
        bizContent.setOpType("1");//“1” 埋名下单 “2” 非埋名下单
        bizContent.setOrderType("1");//月租车



        //aggregateNouiPayinfo	主扫埋名聚合支付参数	字符			Json格式字符串。
        CardbusinessEtcparkOrderpayRequestV1.AggregateNouiPayInfo aggregateNouiPayInfo = new CardbusinessEtcparkOrderpayRequestV1.AggregateNouiPayInfo();
        aggregateNouiPayInfo.setPayMode("9");
        aggregateNouiPayInfo.setAccessType("7");
        aggregateNouiPayInfo.setDeciveInfo("11");
        aggregateNouiPayInfo.setUnionId("2088821086177677");
        aggregateNouiPayInfo.setShopAppId("wx8c30df0b481cca17");
        aggregateNouiPayInfo.setOpenId("ofZaLuKq04owvDD-MXlTB4H024bk");
        aggregateNouiPayInfo.setExpireTime("120");
        bizContent.setAggregateNouiPayInfo(aggregateNouiPayInfo);

        request.setBizContent(bizContent);
        CardbusinessEtcparkOrderpayResponseV1 response;
        String msgId = String.valueOf(System.currentTimeMillis());
        try {
            response = client.execute(request, msgId);//msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一
            System.out.println("response:"+JSON.toJSONString(response));

        }catch (IcbcApiException e) {
            e.printStackTrace();
        }

    }
    /**
     * 无感失败生成二维码
     */
    private static void ncpayQrcode() {
        //签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

        CardbusinessEtcparkOrderpayRequestV1 request = new CardbusinessEtcparkOrderpayRequestV1();

        //4、根据测试环境和生产环境替换相应ip和端口
        request.setServiceUrl("http://ip:port/api/cardbusiness/etcpark/orderpay/V1");
        //5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
        CardbusinessEtcparkOrderpayRequestV1.CardbusinessEtcparkOrderpayRequestV1Biz bizContent = new
                CardbusinessEtcparkOrderpayRequestV1.CardbusinessEtcparkOrderpayRequestV1Biz();
        //plateNum	车牌号	字符	32	是
        bizContent.setPlateNum("蒙Y88888");
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
        //qrcode	支付码	字符			扫码枪扫描客户二维码时上送
        //opType	操作类型	字符		否	“” 或者null或者”0” 支付
        bizContent.setOpType("3");


        request.setBizContent(bizContent);
        CardbusinessEtcparkOrderpayResponseV1 response;
        String msgId = String.valueOf(System.currentTimeMillis());
        try {
            response = client.execute(request, msgId);//msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一
            //status=0表示无感支付成功
            System.out.println("response:"+JSON.toJSONString(response));

        }catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }

    /**
     * 无感支付
     */
    private static void ncpay(){
        //签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

        CardbusinessEtcparkOrderpayRequestV1 request = new CardbusinessEtcparkOrderpayRequestV1();

        //4、根据测试环境和生产环境替换相应ip和端口
        request.setServiceUrl("http://ip:port/api/cardbusiness/etcpark/orderpay/V1");
        //5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
        CardbusinessEtcparkOrderpayRequestV1.CardbusinessEtcparkOrderpayRequestV1Biz bizContent = new
                CardbusinessEtcparkOrderpayRequestV1.CardbusinessEtcparkOrderpayRequestV1Biz();
        //plateNum	车牌号	字符	32	是
        bizContent.setPlateNum("蒙Y88888");
        //entranceTime
        bizContent.setEntranceTime("2021-02-23 14:02:12");
        //exportTime	车辆离场时间	字符	20	是	YYYY-MM-DD hh:mm:ss
        bizContent.setExportTime("2021-02-23 15:45:06");
        //depotId	停车场编号	字符	20	否	E停为停车场生成的车场编号。
        bizContent.setDepotId("xxx");
        //plateColor	车牌颜色	字符	3	否	0-蓝色， 1-黄色，2-黑色， 3-白色，4- 渐变绿色 5- 黄绿双拼色 黄绿双拼色6- 蓝白渐变色 蓝白渐变色9- 未确定11-绿色12-红色（若不上送默认为0-蓝色）
        bizContent.setPlateColor("");
        //orderId	入场流水号	字符	16	是	E停车入场生成订单流水号
        bizContent.setOrderId("xx");
        //actualPay	停车费用	字符	17	否	订单金额，单位元。最多保留两位小数
        bizContent.setActualPay("0.01");
        //entranceLineNo	入场车道编号	字符	50	否
        bizContent.setEntranceLineNo("1");
        //exportLineNo	出场车道编号	字符	50	否
        //copayId	合作方支付流水号	字符	50	否	合作方上送支付流水号
        bizContent.setCopayId("1234");
        //qrcode	支付码	字符			扫码枪扫描客户二维码时上送
        //opType	操作类型	字符		否	“” 或者null或者”0” 支付



        request.setBizContent(bizContent);
        CardbusinessEtcparkOrderpayResponseV1 response;
        String msgId = String.valueOf(System.currentTimeMillis());
        try {
            response = client.execute(request, msgId);//msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一
            //status=0表示无感支付成功
            System.out.println("response:"+JSON.toJSONString(response));

        }catch (IcbcApiException e) {
            e.printStackTrace();
        }

    }

    /**
     * 聚合支付
     */
    private static void aggrementPay(){
        //签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

        CardbusinessEtcparkOrderpayRequestV1 request = new CardbusinessEtcparkOrderpayRequestV1();

        //4、根据测试环境和生产环境替换相应ip和端口
        request.setServiceUrl("http://ip:port/api/cardbusiness/etcpark/orderpay/V1");
        //5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
        CardbusinessEtcparkOrderpayRequestV1.CardbusinessEtcparkOrderpayRequestV1Biz bizContent = new
                CardbusinessEtcparkOrderpayRequestV1.CardbusinessEtcparkOrderpayRequestV1Biz();
        //plateNum	车牌号	字符	32	是
        bizContent.setPlateNum("蒙Y88888");
        //entranceTime
        bizContent.setEntranceTime("2021-02-23 14:02:12");
        //exportTime	车辆离场时间	字符	20	是	YYYY-MM-DD hh:mm:ss
        bizContent.setExportTime("2021-02-23 15:45:06");
        //depotId	停车场编号	字符	20	否	E停为停车场生成的车场编号。
        bizContent.setDepotId("xxxx");
        //plateColor	车牌颜色	字符	3	否	0-蓝色， 1-黄色，2-黑色， 3-白色，4- 渐变绿色 5- 黄绿双拼色 黄绿双拼色6- 蓝白渐变色 蓝白渐变色9- 未确定11-绿色12-红色（若不上送默认为0-蓝色）
        bizContent.setPlateColor("");
        //orderId	入场流水号	字符	16	是	E停车入场生成订单流水号
        bizContent.setOrderId("xx");
        //actualPay	停车费用	字符	17	否	订单金额，单位元。最多保留两位小数
        bizContent.setActualPay("0.01");
        //entranceLineNo	入场车道编号	字符	50	否
        bizContent.setEntranceLineNo("1");
        //exportLineNo	出场车道编号	字符	50	否
        //copayId	合作方支付流水号	字符	50	否	合作方上送支付流水号
        bizContent.setCopayId("1234");
        //qrcode	支付码	字符			扫码枪扫描客户二维码时上送
        //opType	操作类型	字符		否	“” 或者null或者”0” 支付
        bizContent.setOpType("1");//“1” 埋名下单 “2” 非埋名下单



        //aggregateNouiPayinfo	主扫埋名聚合支付参数	字符			Json格式字符串。
        CardbusinessEtcparkOrderpayRequestV1.AggregateNouiPayInfo aggregateNouiPayInfo = new CardbusinessEtcparkOrderpayRequestV1.AggregateNouiPayInfo();
        aggregateNouiPayInfo.setPayMode("10");
        aggregateNouiPayInfo.setAccessType("7");
        aggregateNouiPayInfo.setDeciveInfo("11");
        aggregateNouiPayInfo.setUnionId("xx");
        aggregateNouiPayInfo.setShopAppId("xx");
        aggregateNouiPayInfo.setOpenId("xx");
        aggregateNouiPayInfo.setExpireTime("120");
        bizContent.setAggregateNouiPayInfo(aggregateNouiPayInfo);
        //aggregateUiPayinfo	主扫非埋名聚合支付参数	字符			Json格式字符串。
        CardbusinessEtcparkOrderpayRequestV1.AggregateUiPayInfo aggregateUiPayInfo = new CardbusinessEtcparkOrderpayRequestV1.AggregateUiPayInfo();
//        aggregateUiPayInfo.setExpireTime("120");
//        aggregateUiPayInfo.setOpenId("ofZaLuKq04owvDD-MXlTB4H024bk");
//        aggregateUiPayInfo.setSaledepName("xx停车场");
//        aggregateUiPayInfo.setShopAppId("wx8c30df0b481cca17");
//        aggregateUiPayInfo.setSubject("xx停车场");
        bizContent.setAggregateUiPayInfo(aggregateUiPayInfo);

        CardbusinessEtcparkOrderpayRequestV1.AggregateUiPayInfo aggregateUiPayinfo = new CardbusinessEtcparkOrderpayRequestV1.AggregateUiPayInfo();
        bizContent.setAggregateUiPayInfo(aggregateUiPayinfo);

        request.setBizContent(bizContent);
        CardbusinessEtcparkOrderpayResponseV1 response;
        String msgId = String.valueOf(System.currentTimeMillis());
        try {
            response = client.execute(request, msgId);//msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一
            System.out.println("response:"+JSON.toJSONString(response));

        }catch (IcbcApiException e) {
            e.printStackTrace();
        }

    }
}
