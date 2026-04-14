package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.VisaCreditQueryRequestV1.VisaCreditQueryRequestV1Biz;
import com.icbc.api.request.VisaCreditQueryRequestV1;
import com.icbc.api.response.VisaCreditQueryResponseV1;
import org.junit.Assert;
import org.junit.Test;

import java.util.logging.Logger;

public class VisaCreditQueryV1Test {
    // 1、网关公钥
    protected static final String APIGW_PUBLIC_KEY = "xxx";

    // 2、appid
    protected static final String APP_ID = "xxx";

    // 3、密钥对认证方式，公钥在API平台登记，此处为私钥
    protected static final String MY_PRIVATE_KEY = "xxx";

    private Logger logger = Logger.getLogger(VisaCreditQueryV1Test.class.getName());


    public void test_cop() throws IcbcApiException {
//		//网关公钥
//		final String APIGW_PUBLIC_KEY = "public_key";
//		//私钥
//		final String MY_PRIVATE_KEY = "primary_key";
//		//appid
//		final String APP_ID = "app_id";
        // 签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
//		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA, MY_PRIVATE_KEY,APIGW_PUBLIC_KEY);
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        VisaCreditQueryRequestV1 request = new VisaCreditQueryRequestV1();
        //根据测试环境和生产环境替换相应ip和端口
        request.setServiceUrl("http://ip:port/api/visa/creditQuery/V1");
        VisaCreditQueryRequestV1Biz bizContent = new VisaCreditQueryRequestV1Biz();
        //请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
        bizContent.setMerId("020******095");
        bizContent.setProdAgrSeriNo("012******88");
        bizContent.setOutTradeNo("ZHL******2039");
        bizContent.setOperflag("0");
        bizContent.setori_out_tradeno("");
        bizContent.setCardNo("1234******123456");
        request.setBizContent(bizContent);
        System.out.println(client.buildPostForm(request));

        VisaCreditQueryRequestResponseV1 response;
        client.setHeaderParam("X‐Fova‐Region", "04000"); //交易地区号
        try {
            response = client.execute(request, System.currentTimeMillis() + "");//msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一
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
