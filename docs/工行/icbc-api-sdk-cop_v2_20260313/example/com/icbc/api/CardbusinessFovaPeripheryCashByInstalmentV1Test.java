package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.CardbusinessFovaPeripheryCashByInstalmentRequestV1;
import com.icbc.api.response.CardbusinessFovaPeripheryCashByInstalmentResponseV1;
import org.junit.Assert;
import org.junit.Test;

import java.util.logging.Logger;

public class CardbusinessFovaPeripheryCashByInstalmentV1Test {
    // 1、网关公钥
    protected static final String APIGW_PUBLIC_KEY = "xxx";

    // 2、appid
    protected static final String APP_ID = "xxx";

    // 3、密钥对认证方式，公钥在API平台登记，此处为私钥
    protected static final String MY_PRIVATE_KEY = "xxx";

    private Logger logger = Logger.getLogger(CardbusinessFovaPeripheryCashByInstalmentV1Test.class.getName());

    
    public void test_cop() throws IcbcApiException {

        // 签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

        CardbusinessFovaPeripheryCashByInstalmentRequestV1 request = new CardbusinessFovaPeripheryCashByInstalmentRequestV1();

        // 4、根据测试环境和生产环境替换相应ip和端口
        request.setServiceUrl("http://IP:port/api/cardbusiness/fova/periphery/cashbyinstalment/V1");
        // 5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
        CardbusinessFovaPeripheryCashByInstalmentRequestV1.CardbusinessFovaPeripheryCashByInstalmentRequestV1Biz bizContent = new CardbusinessFovaPeripheryCashByInstalmentRequestV1.CardbusinessFovaPeripheryCashByInstalmentRequestV1Biz();
        bizContent.setZoneno("xx");
        bizContent.setOperflag_comm("xx");
        bizContent.setChnltype("xx");
        bizContent.setBrno("xx");
        bizContent.setTellerno("xx");
        bizContent.setWorkdate("xx");
        bizContent.setWorktime("xx");
        bizContent.setRevtranf("xx");
        bizContent.setServface("xx");
        bizContent.setPretelno("xx");
        bizContent.setPtrxsqnb("xx");
        bizContent.setChantype("xx");
        bizContent.setApptype("xx");
		bizContent.setIp("xx");
        bizContent.setCard_no("xx");
        bizContent.setExp_date("xx");
        bizContent.setMdept_no("xx");
        bizContent.setTrx_curr("xx");
        bizContent.setPamount("xx");
        bizContent.setPlan_num("xx");
        bizContent.setFirst_dat("xx");
        bizContent.setOperflag("xx");
        bizContent.setFeeamtin("xx");
        bizContent.setFeed_crate("xx");
        bizContent.setPlan_no("xx");
        bizContent.setAcc_type("xx");
        bizContent.setBank_no("xx");
        bizContent.setBankbr_no("xx");
        bizContent.setBank_name("xx");
        bizContent.setAcc_no("xx");
        bizContent.setAuth_num("xx");
        bizContent.setAcc_date("xx");
        bizContent.setItrate("xx");
        bizContent.setStat_code("xx");
        bizContent.setFirst_amt("xx");
        bizContent.setEach_amt("xx");



        request.setBizContent(bizContent);
        CardbusinessFovaPeripheryCashByInstalmentResponseV1 response;
        try {
            boolean testFlag = true;
            // msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一
            response = client.execute(request, System.currentTimeMillis() + "");

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
