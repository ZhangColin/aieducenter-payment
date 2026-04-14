package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.CardbusinessFovaPeripheryPaymentByJetcoRequestV1;
import com.icbc.api.request.CardbusinessFovaPeripheryPaymentByJetcoRequestV1.CardbusinessFovaPeripheryPaymentByJetcoRequestV1Biz;
import com.icbc.api.response.CardbusinessFovaPeripheryPaymentByJetcoResponseV1;
import org.junit.Assert;
import org.junit.Test;

import java.util.logging.Logger;

/**
 * @ClassName CardbusinessFovaPeripheryPaymentByJetcoV1Test
 * @Description TODO
 * @author: kfzx-liangjl01
 * @create: 2023-03-14 15:26
 **/
public class CardbusinessFovaPeripheryPaymentByJetcoV1Test {

    // 1、网关公钥
    protected static final String APIGW_PUBLIC_KEY = "xxx";

    // 2、appid
    protected static final String APP_ID = "xxx";

    // 3、密钥对认证方式，公钥在API平台登记，此处为私钥
    protected static final String MY_PRIVATE_KEY = "xxx";

    private Logger logger = Logger.getLogger(CardbusinessFovaPeripheryPaymentByJetcoV1Test.class.getName());


    public void test_cop() throws IcbcApiException {
        // 签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA, MY_PRIVATE_KEY,APIGW_PUBLIC_KEY);

        CardbusinessFovaPeripheryPaymentByJetcoRequestV1 request = new CardbusinessFovaPeripheryPaymentByJetcoRequestV1();

        // 4、根据测试环境和生产环境替换相应ip和端口
        request.setServiceUrl("http://IP:port/api/cardbusiness/fova/periphery/PaymentByJetco/V1");
        // 5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
        CardbusinessFovaPeripheryPaymentByJetcoRequestV1Biz bizContent = new CardbusinessFovaPeripheryPaymentByJetcoRequestV1Biz();
        bizContent.setZoneno("xxx");
        bizContent.setOperflag("xxx");
        bizContent.setChnltype("xxx");
        bizContent.setBrno("xxx");
        bizContent.setTrxsqnb("xxx");
        bizContent.setTellerno("xxx");
        bizContent.setWorkdate("xxx");
        bizContent.setWorktime("xxx");
        bizContent.setRevtranf("xxx");
        bizContent.setServface("xxx");
        bizContent.setPretelno("xxx");
        bizContent.setPtrxsqnb("xxx");
        bizContent.setTermid("xxx");
        bizContent.setCksafef("xxx");
        bizContent.setCkusrf("xxx");
        bizContent.setCkchnlf("xxx");
        bizContent.setCkdatef("xxx");
        bizContent.setWrtjnlf("xxx");
        bizContent.setFlag1("xxx");
        bizContent.setFlag8("xxx");
        bizContent.setMediumid("xxx");
        bizContent.setMedseno("xxx");
        bizContent.setCurrtype("xxx");
        bizContent.setCashexf("xxx");
        bizContent.setMerno("xxx");
        bizContent.setPymno("xxx");
        bizContent.setBilltype("xxx");
        bizContent.setPymcur("xxx");
        bizContent.setPymamt("xxx");
        bizContent.setNotes("xxx");
        bizContent.setSysbsno("xxx");
        bizContent.setHxno("xxx");
        bizContent.setCustno("xxx");

        request.setBizContent(bizContent);
        CardbusinessFovaPeripheryPaymentByJetcoResponseV1 response;
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
