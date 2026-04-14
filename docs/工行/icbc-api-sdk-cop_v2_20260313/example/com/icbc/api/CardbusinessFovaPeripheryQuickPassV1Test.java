package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.CardbusinessFovaPeripheryQuickPassRequestV1;
import com.icbc.api.request.CardbusinessFovaPeripheryQuickPassRequestV1.CardbusinessFovaPeripheryQuickPassRequestV1Biz;
import com.icbc.api.response.CardbusinessFovaPeripheryQuickPassResponseV1;
import org.junit.Assert;
import org.junit.Test;

import java.util.logging.Logger;

/**
 * @ClassName CardbusinessFovaPeripheryQuickPassV1Test
 * @Description TODO
 * @author: kfzx-zhangxx02
 * @create: 2023-02-08 16:35
 **/
public class CardbusinessFovaPeripheryQuickPassV1Test {

    // 1、网关公钥
    protected static final String APIGW_PUBLIC_KEY = "xxx";

    // 2、appid
    protected static final String APP_ID = "xxx";

    // 3、密钥对认证方式，公钥在API平台登记，此处为私钥
    protected static final String MY_PRIVATE_KEY = "xxx";

    private Logger logger = Logger.getLogger(CardbusinessFovaPeripheryQuickPassV1Test.class.getName());


    public void test_cop() throws IcbcApiException {

        // 签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

        CardbusinessFovaPeripheryQuickPassRequestV1 request = new CardbusinessFovaPeripheryQuickPassRequestV1();

        // 4、根据测试环境和生产环境替换相应ip和端口
        request.setServiceUrl("http://IP:port/api/cardbusiness/fova/periphery/quickPass/V1");
        // 5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
        CardbusinessFovaPeripheryQuickPassRequestV1Biz bizContent = new CardbusinessFovaPeripheryQuickPassRequestV1Biz();
        bizContent.setOper_flag("xxx");
        bizContent.setZoneno("xxx");
        bizContent.setChnl_type("xxx");
        bizContent.setChnl_no("xxx");
        bizContent.setBrno("xxx");
        bizContent.setTeller_no("xxx");
        bizContent.setWork_date("xxx");
        bizContent.setWork_time("xxx");
        bizContent.setRev_tranf("xxx");
        bizContent.setPretel_no("xxx");
        bizContent.setPtrxsqnb("xxx");
        bizContent.setServ_face("xxx");
        bizContent.setMedium_id("xxx");
        bizContent.setMer_no("xxx");
        bizContent.setExp_date("xxx");
        bizContent.setCurr_type("xxx");
        bizContent.setAmount("xxx");
        bizContent.setTrx_date("xxx");
        bizContent.setBatch_no("xxx");
        bizContent.setPanentry_mode("xxx");
        bizContent.setTrk2("xxx");
        bizContent.setPansqnum("xxx");
        bizContent.setEmv_data("xxx");
        bizContent.setEmvtec("xxx");
        bizContent.setNotes("xxx");

        request.setBizContent(bizContent);
        CardbusinessFovaPeripheryQuickPassResponseV1 response;
        client.setHeaderParam("X-Fova-Region", "00119"); //���׵����� ������00119������������00110
        try {
            boolean testFlag = true;
            response = client.execute(request, System.currentTimeMillis() + ""); // msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一

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
