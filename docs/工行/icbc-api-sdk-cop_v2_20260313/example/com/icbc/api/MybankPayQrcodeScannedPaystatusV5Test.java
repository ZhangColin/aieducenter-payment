package com.icbc.api;
import com.icbc.api.request.MybankPayQrcodeScannedPaystatusRequestV5;
import com.icbc.api.request.MybankPayQrcodeScannedPaystatusRequestV5.MybankPayQrcodeScannedPaystatusRequestV5Biz;
import com.icbc.api.response.MybankPayQrcodeScannedPaystatusResponseV5;

/**
 * API聚合支付查询接口
 *
 * 请商户配置SDK中的lib文件夹中的jar包后，根据备注1-6提示进行数据替换
 */
public class MybankPayQrcodeScannedPaystatusV5Test {

    //1、网关公钥
    protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----";
    //2、appid
    protected static final String APP_ID = "1000*****7354649";

    //3、密钥对认证方式，公钥在API平台登记，此处为私钥
    protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----";
    public static void main(String[] args) throws Exception {

        //签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

        MybankPayQrcodeScannedPaystatusRequestV5 request = new MybankPayQrcodeScannedPaystatusRequestV5();

        //4、根据测试环境和生产环境替换相应ip和端口
        request.setServiceUrl("http://*****/api/mybank/pay/qrcode/scanned/paystatus/V2");
        //5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
        MybankPayQrcodeScannedPaystatusRequestV5Biz bizContent = new MybankPayQrcodeScannedPaystatusRequestV5Biz();
        bizContent.setOutTradeNo("111111******66666111"); //外部订单号
        bizContent.setOrderId("");
        bizContent.setMerId("0200*****460001"); //商户编号
        bizContent.setTradeDate("20230515"); //交易日期  格式:YYYYMMDD

        bizContent.setQueryOptions("");//商户通过上送该参数来定制同步需要额外返回的信息字段，数组格式

        request.setBizContent(bizContent);
        MybankPayQrcodeScannedPaystatusResponseV5 response;
        try {
//			System.out.println(client.execute(request, System.currentTimeMillis()+"").getCardNo());
            response = client.execute(request, System.currentTimeMillis()+"");//msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一
			/*System.out.println(response.getSaesReturnCode());
			System.out.println(response.getPayStatus());
			System.out.println(response.getReturnMsg());*/
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