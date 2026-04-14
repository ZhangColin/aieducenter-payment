package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.CardbusinessFovaPeripheryB2CpreProcessRequestV1;
import com.icbc.api.request.CardbusinessFovaPeripheryB2CpreProcessRequestV1.CardbusinessFovaPeripheryB2CpreProcessRequestV1Biz;
import com.icbc.api.response.CardbusinessFovaPeripheryB2CpreProcessResponseV1;
import org.junit.Assert;
import org.junit.Test;

import java.util.logging.Logger;

/**
 * @ClassName CardbusinessFovaPeripheryB2CpreProcessV1Test
 * @Description TODO
 * @Author kfzx-caijj01
 * @Date 2023/1/29 13:51
 */
public class CardbusinessFovaPeripheryB2CpreProcessV1Test {

    // 1、网关公钥
    protected static final String APIGW_PUBLIC_KEY = "xxx";

    // 2、appid
    protected static final String APP_ID = "xxx";

    // 3、密钥对认证方式，公钥在API平台登记，此处为私钥
    protected static final String MY_PRIVATE_KEY = "xxx";

    private Logger logger = Logger.getLogger(CardbusinessFovaPeripheryB2CpreProcessV1Test.class.getName());


    public void test_cop() throws IcbcApiException {

        // 签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA, MY_PRIVATE_KEY,APIGW_PUBLIC_KEY);

        CardbusinessFovaPeripheryB2CpreProcessRequestV1 request = new CardbusinessFovaPeripheryB2CpreProcessRequestV1();

        // 4、根据测试环境和生产环境替换相应ip和端口
        request.setServiceUrl("http://IP:port/api/cardbusiness/fova/periphery/B2cPreProcess/V1");
        // 5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
        CardbusinessFovaPeripheryB2CpreProcessRequestV1Biz bizContent = new CardbusinessFovaPeripheryB2CpreProcessRequestV1Biz();
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
        bizContent.setApptype("xxx");
        bizContent.setMdcardno("xxx");
        bizContent.setMerno("xxx");
        bizContent.setTrxtype("xxx");
        bizContent.setMertrxcurr("xxx");
        bizContent.setMertrxamt("xxx");
        bizContent.setCardlogo("xxx");
        bizContent.setPayway("xxx");
        bizContent.setSerialno("xxx");
        bizContent.setSerhead("xxx");
        bizContent.setNodeseq("xxx");
        bizContent.setChantype("xxx");
        bizContent.setCledate("xxx");
        bizContent.setTrxsqnb("xxx");
        bizContent.setShopcode("xxx");
        bizContent.setShacct("xxx");
        bizContent.setShmedseno("xxx");
        bizContent.setShcurrtype("xxx");
        bizContent.setScashexf("xxx");
        bizContent.setSsubserno("xxx");
        bizContent.setCustname("xxx");
        bizContent.setShzoneno("xxx");
        bizContent.setShbrno("xxx");
        bizContent.setMcertid("xxx");
        bizContent.setMerurl("xxx");
        bizContent.setTradtype("xxx");
        bizContent.setProtseno("xxx");
        bizContent.setOcardno("xxx");
        bizContent.setOmedseno("xxx");
        bizContent.setOcurrtype("xxx");
        bizContent.setOcashexf("xxx");
        bizContent.setOsubserno("xxx");
        bizContent.setPcardtyp("xxx");
        bizContent.setPaccname("xxx");
        bizContent.setOzoneno("xxx");
        bizContent.setPopbrno("xxx");
        bizContent.setCertype("xxx");
        bizContent.setCertnum("xxx");
        bizContent.setCphone("xxx");
        bizContent.setIp("xxx");
        bizContent.setMac("xxx");
        bizContent.setChswtich("xxx");
        bizContent.setJiswtich("xxx");
        bizContent.setMemberno("xxx");
        bizContent.setOrderno("xxx");
        bizContent.setOrdersum("xxx");
        bizContent.setOrdercurrtypy("xxx");
        bizContent.setGoodsno("xxx");
        bizContent.setGoodname("xxx");
        bizContent.setOrdvalue("xxx");
        bizContent.setCarrsum("xxx");
        bizContent.setCarrcurrtypy("xxx");
        bizContent.setNotytype("xxx");
        bizContent.setResutype("xxx");
        bizContent.setAuthno("xxx");
        bizContent.setIntename("xxx");
        bizContent.setIntevers("xxx");
        bizContent.setSdate("xxx");
        bizContent.setStime("xxx");
        bizContent.setTrtimesp("xxx");
        bizContent.setTdate("xxx");
        bizContent.setTtime("xxx");
        bizContent.setBrstamp("xxx");
        bizContent.setLcldate("xxx");
        bizContent.setHdate("xxx");
        bizContent.setRjswitch("xxx");
        bizContent.setRjsum("xxx");
        bizContent.setRjamt("xxx");
        bizContent.setRjcurrtype("xxx");
        bizContent.setTrxcode("xxx");
        bizContent.setStatus("xxx");
        bizContent.setShpnamec("xxx");
        bizContent.setGroupid("xxx");
        bizContent.setShopstat("xxx");
        bizContent.setSpecacct("xxx");
        bizContent.setSpzoneno("xxx");
        bizContent.setSpbrno("xxx");
        bizContent.setPacctype("xxx");
        bizContent.setShoplevl("xxx");
        bizContent.setPayzonenoflg("xxx");
        bizContent.setRate("xxx");
        bizContent.setSynflag("xxx");
        bizContent.setFeeamt("xxx");
        bizContent.setBirthdate("xxx");
        bizContent.setRjfeeamt("xxx");
        bizContent.setMonthxun("xxx");
        bizContent.setAddress("xxx");
        bizContent.setGrouname("xxx");
        bizContent.setFaildesc("xxx");
        bizContent.setDescrch1("xxx");
        bizContent.setDescrch2("xxx");
        bizContent.setFcardflg("xxx");
        bizContent.setTrxserno("xxx");
        bizContent.setProamt("xxx");
        bizContent.setSfeeamount("xxx");
        bizContent.setSfeerate("xxx");
        bizContent.setBak_char("xxx");
        bizContent.setAuthorno("xxx");
        bizContent.setBacknum1("xxx");
        bizContent.setDapcode("xxx");
        bizContent.setPaycurr("xxx");
        bizContent.setPayamount("xxx");
        bizContent.setSrfeeamt("xxx");
        bizContent.setSerialno2("xxx");
        bizContent.setBacknum2("xxx");
        bizContent.setBacknum3("xxx");
        bizContent.setBackchar1("xxx");
        bizContent.setBackchar2("xxx");
        bizContent.setBackchar3("xxx");
        bizContent.setUpopid("xxx");
        bizContent.setEci("xxx");
        bizContent.setUpoporderno("xxx");
        bizContent.setUpoppayflg("xxx");
        bizContent.setUpopcrdtyp("xxx");
        bizContent.setDisccurr("xxx");
        bizContent.setDiscamount("xxx");
        bizContent.setBacknum4("xxx");
        bizContent.setBacknum5("xxx");
        bizContent.setBackchar4("xxx");
        bizContent.setBackchar5("xxx");
        bizContent.setDkprotseno("xxx");
        bizContent.setAppid("xxx");
        bizContent.setBk("xxx");

        request.setBizContent(bizContent);
        CardbusinessFovaPeripheryB2CpreProcessResponseV1 response;
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
