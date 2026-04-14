package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.CardbusinessFovaPeripheryMerchantProcessRequestV1;
import com.icbc.api.response.CardbusinessFovaPeripheryMerchantProcessResponseV1;
import org.junit.Assert;
import org.junit.Test;

import java.util.logging.Logger;

public class CardbusinessFovaPeripheryMerchantProcessV1Test {
    // 1、网关公钥
    protected static final String APIGW_PUBLIC_KEY = "xxx";

    // 2、appid
    protected static final String APP_ID = "xxx";

    // 3、密钥对认证方式，公钥在API平台登记，此处为私钥
    protected static final String MY_PRIVATE_KEY = "xxx";

    private Logger logger = Logger.getLogger(CardbusinessFovaPeripheryMerchantProcessV1Test.class.getName());


    public void test_cop() throws IcbcApiException {

        // 签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

        CardbusinessFovaPeripheryMerchantProcessRequestV1 request = new CardbusinessFovaPeripheryMerchantProcessRequestV1();

        // 4、根据测试环境和生产环境替换相应ip和端口
        request.setServiceUrl("http://IP:port/api/cardbusiness/fova/periphery/MerchantProcess/V1");
        // 5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
        CardbusinessFovaPeripheryMerchantProcessRequestV1.CardbusinessFovaPeripheryMerchantProcessRequestV1Biz bizContent = new CardbusinessFovaPeripheryMerchantProcessRequestV1.CardbusinessFovaPeripheryMerchantProcessRequestV1Biz();

        bizContent.setMer_no("xxx");
        bizContent.setZoneno("xxx");
        bizContent.setAdmibrno("xxx");
        bizContent.setUnitno("xxx");
        bizContent.setMercname("xxx");
        bizContent.setMerename("xxx");
        bizContent.setConntel("xxx");
        bizContent.setConnor("xxx");
        bizContent.setPostype("xxx");
        bizContent.setSacctype("xxx");
        bizContent.setSetaccno("xxx");
        bizContent.setSacccurr("xxx");
        bizContent.setSacccsef("xxx");
        bizContent.setMacctype("xxx");
        bizContent.setMsaccno("xxx");
        bizContent.setMacccurr("xxx");
        bizContent.setMacccsef("xxx");
        bizContent.setMdkposno("xxx");
        bizContent.setMdkicpos("xxx");
        bizContent.setAllyflag("xxx");
        bizContent.setAllyno("xxx");
        bizContent.setMdkgrpno("xxx");
        bizContent.setAllycdna ("xxx");
        bizContent.setAwardf("xxx");
        bizContent.setAintegrf("xxx");
        bizContent.setSintegrf("xxx");
        bizContent.setSpgrfstd("xxx");
        bizContent.setSactdate("xxx");
        bizContent.setSatpdate("xxx");
        bizContent.setSaeddate("xxx");
        bizContent.setIgcvflag("xxx");
        bizContent.setPresatyp("xxx");
        bizContent.setPresaset("xxx");
        bizContent.setPresprop("xxx");
        bizContent.setEnpwdamt("xxx");
        bizContent.setSigndate("xxx");
        bizContent.setSdbamt("xxx");
        bizContent.setSdbcnt("xxx");
        bizContent.setRcramt("xxx");
        bizContent.setRcrcnt("xxx");
        bizContent.setTipamt("xxx");
        bizContent.setTipcnt("xxx");
        bizContent.setMsaletot("xxx");
        bizContent.setMsalenum("xxx");
        bizContent.setYsaletot("xxx");
        bizContent.setYsalenum("xxx");
        bizContent.setLsttrxd("xxx");
        bizContent.setSetzono("xxx");
        bizContent.setSetdate("xxx");
        bizContent.setSettlno("xxx");
        bizContent.setLstzono("xxx");
        bizContent.setLstmodd("xxx");
        bizContent.setLstmodn("xxx");
        bizContent.setSzoneno("xxx");
        bizContent.setSbrno("xxx");
        bizContent.setSetflag("xxx");
        bizContent.setRecstat("xxx");
        bizContent.setDeptflag("xxx");
        bizContent.setCrdtpacf("xxx");
        bizContent.setTcc("xxx");
        bizContent.setFvipflag("xxx");
        bizContent.setNotes("xxx");
        bizContent.setFixfeeflag("xxx");
        bizContent.setFeecycle("xxx");
        bizContent.setMerfeem("xxx");
        bizContent.setFixfeeamt("xxx");
        bizContent.setRemark("xxx");
        bizContent.setMeraddr("xxx");
        bizContent.setMailaddr("xxx");
        bizContent.setConnemal("xxx");
        bizContent.setConnfax("xxx");
        bizContent.setMersour("xxx");
        bizContent.setDepuno("xxx");
        bizContent.setBnkconr("xxx");
        bizContent.setBnkcontel("xxx");
        bizContent.setMerpstar("xxx");
        bizContent.setMerpend("xxx");
        bizContent.setStbnkcod("xxx");
        bizContent.setStbnkbrn("xxx");
        bizContent.setStbaccnam("xxx");
        bizContent.setStlcycle("xxx");
        bizContent.setStlcyld("xxx");
        bizContent.setSldlyno("xxx");
        bizContent.setSlmaxamt("xxx");
        bizContent.setHndposno("xxx");
        bizContent.setPosdepos("xxx");
        bizContent.setPosdpsd("xxx");
        bizContent.setBillcyl("xxx");
        bizContent.setBillcyln("xxx");
        bizContent.setMerfee("xxx");
        bizContent.setMemo("xxx");
        bizContent.setPpayamt("xxx");
        bizContent.setField1("xxx");
        bizContent.setField2("xxx");
        bizContent.setField3("xxx");
        bizContent.setField4("xxx");
        bizContent.setAddress("xxx");
        bizContent.setDcconoff("xxx");
        bizContent.setDccflrtfg("xxx");
        bizContent.setDccflrtslim1("xxx");
        bizContent.setDccflrtdlim1("xxx");
        bizContent.setDccflrt1("xxx");
        bizContent.setDcccurr2("xxx");
        bizContent.setDccflrtslim2("xxx");
        bizContent.setDccflrtdlim2("xxx");
        bizContent.setDccflrt2("xxx");
        bizContent.setRefundflg("xxx");
        bizContent.setSheetemail("xxx");
        bizContent.setSheetemail2 ("xxx");
        bizContent.setDflag("xxx");
        bizContent.setN3dflag("xxx");
        bizContent.setCupsflg("xxx");
        bizContent.setCupqflg("xxx");
        bizContent.setFsctflg("xxx");
        bizContent.setCsctflg("xxx");
        bizContent.setEpyaflg("xxx");
        bizContent.setEsheetflag("xxx");
        bizContent.setMcss_service("xxx");
        bizContent.setMcss_refund("xxx");
        bizContent.setBakinfo("xxx");
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

        request.setBizContent(bizContent);
        CardbusinessFovaPeripheryMerchantProcessResponseV1 response;
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
