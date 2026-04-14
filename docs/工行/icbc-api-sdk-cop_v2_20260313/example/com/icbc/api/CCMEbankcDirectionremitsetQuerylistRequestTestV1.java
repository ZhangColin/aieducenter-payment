package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.CCMEbankcDirectionremitsetQuerylistRequestV1;
import com.icbc.api.response.CCMEbankcDirectionremitsetQuerylistResponseV1;
import com.icbc.api.utils.IcbcEncrypt;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.*;


public class CCMEbankcDirectionremitsetQuerylistRequestTestV1 {
	
	private static final Log logger = LogFactory.getLog(CCMEbankcDirectionremitsetQuerylistRequestTestV1.class);

    protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----";

    // APIGW_PUBLIC_KEY
    protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----";

    // APP_ID
    protected static final String APP_ID = "10000000000004097413";
    protected static final String AESKEY = "AESKEY";

    protected static final String RSA = "RSA";
    protected static final String RSA2 = "RSA2";

    public static void main(String[] args) throws Exception {
        CCMEbankcDirectionremitsetQuerylistRequestTestV1 a = new CCMEbankcDirectionremitsetQuerylistRequestTestV1();
    	String s = "";
        try {
            //System.out.println(IcbcEncrypt.decryptContent(s, "AES", AESKEY, "UTF-8"));
            logger.info(IcbcEncrypt.decryptContent(s, "AES", AESKEY, "UTF-8"));
        } catch (Exception e) {
            //e.printStackTrace();
            logger.info("error :" + e.getErrMsg());
        }
//        a.test_cop();
    }

    public void test_cop() throws IcbcApiException {
        CCMEbankcDirectionremitsetQuerylistRequestV1 request = new CCMEbankcDirectionremitsetQuerylistRequestV1();
        request.setServiceUrl("http://ip:port/ccmebankc/directionremitset/querylist/V1");
        CCMEbankcDirectionremitsetQuerylistRequestV1.CCMEbankcDirectionremitsetQuerylistRequestV1Biz bizContent = new CCMEbankcDirectionremitsetQuerylistRequestV1.CCMEbankcDirectionremitsetQuerylistRequestV1Biz();
        //{"chnsName":"api测试","mobile":"12824342323","iD_TYPE":"1","custCode":"12124","sex":"2","orgId":"9999999999","deptId":"0000000016","cordata5":1,
        // "cordata3":"19","staffCustType":"123456","staffCustTypeDate":"2022-10-28","cordata1":"15327422","staffType":"xy"}
        bizContent.setApp_id("10000000000000000001");
        bizContent.setMsg_id("Msg_id");
        bizContent.setFormat("json");
        bizContent.setCharset("GBK");
        bizContent.setEncrypt_type("AES");
        bizContent.setSign_type("RSA");
        bizContent.setSign("Sign");
        bizContent.setTimestamp("2022-12-22 10:44:38");
        bizContent.setCa("Ca");
        bizContent.setBiz_content("20221222123456125345");
        bizContent.setLogon_id("122123.0200");
        bizContent.setPay_account("6200000000");
        bizContent.setPage("1");
        bizContent.setSize("10");
        bizContent.setGroup_id("360290005000265");
        //System.out.println(JSON.toJSONString(bizContent));
        logger.info(JSON.toJSONString(bizContent));
        request.setBizContent(bizContent);
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,RSA2,MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        CCMEbankcDirectionremitsetQuerylistResponseV1 result =  client.execute(request);
        logger.info(JSON.toJSONString(result));
        //System.out.println(JSON.toJSONString(result));

    }

    public String encryptAesContentByKey1(String aesKey, String content) {
        String result = null;
        try {
            result = IcbcEncrypt.encryptContent(content, "AES", aesKey, "UTF-8");
        } catch (Exception e) {
            //e.printStackTrace();
            logger.info("error :" + e.getErrMsg());
            return null;
        }
        return result;
    }
}
