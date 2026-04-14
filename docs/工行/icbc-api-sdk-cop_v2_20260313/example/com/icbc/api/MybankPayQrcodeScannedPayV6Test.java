package com.icbc.api;
import com.icbc.api.internal.util.internal.util.fastjson.JSONArray;
import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.MybankPayQrcodeScannedPayRequestV6;
import com.icbc.api.request.MybankPayQrcodeScannedPayRequestV6.MybankPayQrcodeScannedPayRequestV6Biz;
import com.icbc.api.response.MybankPayQrcodeScannedPayResponseV6;


/**
 * API聚合支付被扫接口
 *
 * 请商户配置SDK中的lib文件夹中的jar包后，根据备注1-6提示进行数据替换
 */
public class MybankPayQrcodeScannedPayV6Test {

    //1、网关公钥
    protected static final String APIGW_PUBLIC_KEY = "请使用正确的公钥";
    //2、appid
    protected static final String APP_ID = "100000000****7354649";

    //3、密钥对认证方式，公钥在API平台登记，此处为私钥
    protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----";
    public static void main(String[] args) throws Exception {

        //签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

        MybankPayQrcodeScannedPayRequestV6 request = new MybankPayQrcodeScannedPayRequestV6();

        //4、根据测试环境和生产环境替换相应ip和端口
        request.setServiceUrl("http://****/api/mybank/pay/qrcode/scanned/pay/V6");
        //5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
        MybankPayQrcodeScannedPayRequestV6Biz bizContent = new MybankPayQrcodeScannedPayRequestV6Biz();
        bizContent.setQrCode("134688****540238"); //付款码
        bizContent.setOutTradeNo("1111116*****666666555"); //外部订单号
        bizContent.setMerId("020*****460001"); //商户编号
        bizContent.setTradeDate("20230515"); //交易日期  格式:YYYYMMDD
        bizContent.setTradeTime("091001"); //交易时间hhmmss
        bizContent.setAttach("1231"); //商户附加信息
        bizContent.setOrderAmt("1"); //交易金额
        bizContent.setSubAppId("wxfb72f1a7d061d631"); //子商户应用号
        bizContent.setGoodsTag("DP");  //优惠标志

        bizContent.setSnCode("");//国补优惠SN码
        bizContent.setBarCode("");//国补优惠商品条码
        bizContent.setQueryOptions("");//返回查询选项，商户通过上送该参数来定制同步需要额外返回的信息字段，数组

        JSONArray goodsDetailArray = new JSONArray();
        JSONObject gd1 = new JSONObject();
        gd1.put("goods_id", "123456");
        gd1.put("goods_name", "FOOD");
        gd1.put("quantity", 1);
        gd1.put("price", 100);
        goodsDetailArray.add(gd1);

        bizContent.setGoodsDetail(goodsDetailArray);

        request.setBizContent(bizContent);
        MybankPayQrcodeScannedPayResponseV6 response;
        try {
            response = client.execute(request, System.currentTimeMillis()+"");//msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一
            if (response.getReturnCode() == 0) {
                // 6、业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
                //System.out.println("response:" + JSON.toJSONString(response));
                //System.out.println("ReturnCode:"+response.getReturnCode());
            } else {
                // 失败
                //System.out.println("response:" + JSON.toJSONString(response));
                //System.out.println("ReturnCode:"+response.getReturnCode());
                //System.out.println("ReturnMsg:"+response.getReturnMsg());
            }
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }


}
