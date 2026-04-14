package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.CardbusinessFovaPeripheryCardlessWithdrawByJetcoRequestV1;
import com.icbc.api.request.CardbusinessFovaPeripheryCardlessWithdrawByJetcoRequestV1.CardbusinessFovaPeripheryCardlessWithdrawByJetcoRequestV1Biz;
import com.icbc.api.response.CardbusinessFovaPeripheryCardlessWithdrawByJetcoResponseV1;
import org.junit.Assert;
import org.junit.Test;

import java.util.logging.Logger;

/**
 * @ClassName CardbusinessFovaPeripheryCardlessWithdrawByJetcoV1Test
 * @Description TODO
 * @Author kfzx-caijj01
 * @Date 2023/1/29 15:20
 */
public class CardbusinessFovaPeripheryCardlessWithdrawByJetcoV1Test {

    // 1、网关公钥
    protected static final String APIGW_PUBLIC_KEY = "xxx";

    // 2、appid
    protected static final String APP_ID = "xxx";

    // 3、密钥对认证方式，公钥在API平台登记，此处为私钥
    protected static final String MY_PRIVATE_KEY = "xxx";

    private Logger logger = Logger.getLogger(CardbusinessFovaPeripheryCardlessWithdrawByJetcoV1Test.class.getName());

    
    public void test_cop() throws IcbcApiException {

        // 签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA, MY_PRIVATE_KEY,APIGW_PUBLIC_KEY);

        CardbusinessFovaPeripheryCardlessWithdrawByJetcoRequestV1 request = new CardbusinessFovaPeripheryCardlessWithdrawByJetcoRequestV1();

        // 4、根据测试环境和生产环境替换相应ip和端口
        request.setServiceUrl("http://IP:port/api/cardbusiness/fova/periphery/cardlesswithdrawbyjetco/V1");
        // 5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
        CardbusinessFovaPeripheryCardlessWithdrawByJetcoRequestV1Biz bizContent = new CardbusinessFovaPeripheryCardlessWithdrawByJetcoRequestV1Biz();
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
        bizContent.setCksafef("xxx");
		bizContent.setCkusrf("xxx");
		bizContent.setCkchnlf("xxx");
		bizContent.setCkpinf("xxx");
		bizContent.setCkidf("xxx");
		bizContent.setCkaccnf("xxx");
		bizContent.setCkunacf("xxx");
		bizContent.setCkcrosf("xxx");
		bizContent.setCkdatef("xxx");
		bizContent.setWrtjnlf("xxx");
		bizContent.setFlag2("xxx");
		bizContent.setFlag8("xxx");
		bizContent.setQrcode("xxx");
        bizContent.setAtmno("xxx");
        bizContent.setMediumid("xxx");
        bizContent.setMedtype("xxx");
        bizContent.setCurrtype("xxx");
        bizContent.setAmount("xxx");
        bizContent.setStardate("xxx");
        bizContent.setEnddate("xxx");
        bizContent.setEndtime("xxx");
        bizContent.setPostdate("xxx");
        bizContent.setSsn("xxx");
        bizContent.setOrigbank("xxx");
        bizContent.setCardbank("xxx");
        bizContent.setBlgcurrtype("xxx");
        bizContent.setBlgamount("xxx");
        bizContent.setBlgee("xxx");
        bizContent.setTermid("xxx");
        bizContent.setTrxspot("xxx");
        bizContent.setMsgtype("xxx");
        bizContent.setProccode("xxx");
        bizContent.setCorrcode("xxx");
        bizContent.setCwdtype("xxx");
        bizContent.setAction("xxx");
        bizContent.setOrtrxserno("xxx");
        bizContent.setOrqrcode("xxx");
        bizContent.setAcqctrcode("xxx");
        bizContent.setBemailaddr("xxx");
        bizContent.setIbackup("xxx");

        request.setBizContent(bizContent);
        CardbusinessFovaPeripheryCardlessWithdrawByJetcoResponseV1 response;
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
