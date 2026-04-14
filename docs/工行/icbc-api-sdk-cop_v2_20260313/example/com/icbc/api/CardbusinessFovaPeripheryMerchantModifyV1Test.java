package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.CardbusinessFovaPeripheryMerchantModifyRequestV1;
import com.icbc.api.request.CardbusinessFovaPeripheryMerchantModifyRequestV1.CardbusinessFovaPeripheryMerchantModifyRequestV1Biz;
import com.icbc.api.response.CardbusinessFovaPeripheryMerchantModifyResponseV1;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * @ClassName CardbusinessFovaPeripheryMerchantModifyV1Test
 * @Description TODO
 * @author: kfzx-zhangxx02
 * @create: 2023-01-05 16:35
 **/
public class CardbusinessFovaPeripheryMerchantModifyV1Test {

    protected static final String APIGW_PUBLIC_KEY = "xxx";

    protected static final String APP_ID = "xxx";

    protected static final String MY_PRIVATE_KEY = "xxx";

    private Logger logger = Logger.getLogger(CardbusinessFovaPeripheryMerchantModifyV1Test.class.getName());

    
    public void test_cop() throws IcbcApiException {

        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

        CardbusinessFovaPeripheryMerchantModifyRequestV1 request = new CardbusinessFovaPeripheryMerchantModifyRequestV1();

        // 4、根据测试环境和生产环境替换相应ip和端口
        request.setServiceUrl("http://IP:port/api/cardbusiness/fova/periphery/MerchantModify/V1");

        CardbusinessFovaPeripheryMerchantModifyRequestV1Biz bizContent = new CardbusinessFovaPeripheryMerchantModifyRequestV1Biz();
        bizContent.setOper_flag("xx");
        bizContent.setZoneno("xx");
        bizContent.setChnl_type("xx");
        bizContent.setBrno("xx");
        bizContent.setTeller_no("xx");
        bizContent.setWork_date("xx");
        bizContent.setWork_time("xx");
        bizContent.setRev_tranf("xx");
        bizContent.setServ_face("xx");
        bizContent.setPretel_no("xx");
        bizContent.setPtrxsqnb("xx");
        bizContent.setChan_type("xx");
        bizContent.setApp_type("xx");
        bizContent.setMdept_no("xx");
        bizContent.setTrxcrdtp("xx");
        bizContent.setDcc_typf("xx");
        bizContent.setRecnum("xx");
        bizContent.setGfavorra("xx");
        bizContent.setAfavorra("xx");
        bizContent.setSign_date("xx");
        bizContent.setTipmode("xx");
        bizContent.setTiprate("xx");
        bizContent.setDtr_amt("xx");
        bizContent.setDtiprate("xx");
        bizContent.setRedfeenm("xx");
        bizContent.setPred_fee("xx");
        bizContent.setPtrx_fee("xx");
        bizContent.setMstot_amt("xx");
        bizContent.setMstot_num("xx");
        bizContent.setYstot_amt("xx");
        bizContent.setYstot_num("xx");
        bizContent.setMcc_code("xx");
        bizContent.setFnorgno("xx");
        bizContent.setLtrx_date("xx");
        bizContent.setSet_date("xx");
        bizContent.setSettl_no("xx");
        bizContent.setLstmodd("xx");
        bizContent.setLstmodn("xx");
        bizContent.setField1("xx");
        bizContent.setField2("xx");
        bizContent.setField3("xx");
        bizContent.setField4("xx");
        bizContent.setMconlmt("xx");
        bizContent.setMexcess("xx");
        bizContent.setFeeflag("xx");
        bizContent.setData(new ArrayList<>());

        request.setBizContent(bizContent);
        CardbusinessFovaPeripheryMerchantModifyResponseV1 response;
        client.setHeaderParam("X-Fova-Region", "00119"); 
        try {
            boolean testFlag = true;
            response = client.execute(request, System.currentTimeMillis() + ""); 

            logger.info("response:" + response.getReturnCode());

            if (0 == response.getReturnCode()) {

                
                logger.info("ReturnCode:" + response.getReturnCode());
                logger.info("response:" + JSON.toJSONString(response));
            } else {
                // ???
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
