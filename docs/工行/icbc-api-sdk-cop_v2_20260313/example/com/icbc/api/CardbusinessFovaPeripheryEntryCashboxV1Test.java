package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.CardbusinessFovaPeripheryEntryCashboxRequestV1;
import com.icbc.api.response.CardbusinessFovaPeripheryEntryCashboxResponseV1;
import org.junit.Assert;
import org.junit.Test;

import java.util.logging.Logger;


public class CardbusinessFovaPeripheryEntryCashboxV1Test {
    // 1、网关公钥
    protected static final String APIGW_PUBLIC_KEY = "xxx";

    // 2、appid
    protected static final String APP_ID = "xxx";

    // 3、密钥对认证方式，公钥在API平台登记，此处为私钥
    protected static final String MY_PRIVATE_KEY = "xxx";

    private Logger logger = Logger.getLogger(CardbusinessFovaPeripheryEntryCashboxV1Test.class.getName());

    
    public void test_cop() throws IcbcApiException {

        // 签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA, MY_PRIVATE_KEY,APIGW_PUBLIC_KEY);

        CardbusinessFovaPeripheryEntryCashboxRequestV1 request = new CardbusinessFovaPeripheryEntryCashboxRequestV1();

        // 4、根据测试环境和生产环境替换相应ip和端口
        request.setServiceUrl("http://IP:port/api/cardbusiness/fova/periphery/entryCashbox/V1");
        // 5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
        CardbusinessFovaPeripheryEntryCashboxRequestV1.CardbusinessFovaPeripheryEntryCashboxRequestV1Biz bizContent = new CardbusinessFovaPeripheryEntryCashboxRequestV1.CardbusinessFovaPeripheryEntryCashboxRequestV1Biz();
        bizContent.setZoneno("xxx");
        bizContent.setOperflag("xxx");
        bizContent.setChnltype("xxx");
        bizContent.setBrno("xxx");
        bizContent.setTellerno("xxx");
        bizContent.setWorkdate("xxx");
        bizContent.setWorktime("xxx");
        bizContent.setRevtranf("xxx");
        bizContent.setServface("xxx");
        bizContent.setPretelno("xxx");
        bizContent.setPtrxsqnb("xxx");
        bizContent.setChantype("xxx");
        bizContent.setApptype("xxx");
        bizContent.setIp("xxx");
        bizContent.setAtmno("xxx");
        bizContent.setCurrtype("xxx");
        bizContent.setAmount("xxx");
        bizContent.setBoxtype("xxx");
        bizContent.setStatcode("xxx");
        bizContent.setLocalflag("xxx");
        bizContent.setChkamtflg("xxx");
        bizContent.setAmttelllno("xxx");
        bizContent.setBk("xxx");


        request.setBizContent(bizContent);
        CardbusinessFovaPeripheryEntryCashboxResponseV1 response;
        try {
            boolean testFlag = true;
            response = client.execute(request, System.currentTimeMillis()+"");// msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一

            logger.info("response:" + response.getReturnCode());

            if (0 == response.getReturnCode()) {

                // 6、业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
                logger.info("ReturnCode:" + response.getReturnCode());
                logger.info("response:" + JSON.toJSONString(response));
            } else {
                // 失败
                testFlag = false;
                logger.info("ReturnCode:" + response.getReturnCode());
                logger.info("ReturnMsg:" + response.getReturnMsg());
            }
            Assert.assertEquals(true, testFlag);
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }
}
