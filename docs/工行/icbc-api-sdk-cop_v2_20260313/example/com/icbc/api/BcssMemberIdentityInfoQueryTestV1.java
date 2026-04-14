package com.icbc.api;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.BcssMemberIdentityInfoQueryRequestV1;
import com.icbc.api.request.BcssMemberIdentityInfoQueryRequestV1.BcssMemberIdentityInfoQueryRequestBizV1;
import com.icbc.api.response.BcssMemberIdentityInfoQueryResponseV1;
 
import com.icbc.api.utils.IcbcEncrypt;

public class BcssMemberIdentityInfoQueryTestV1 {
	
	private static final Log logger = LogFactory.getLog(BcssMemberIdentityInfoQueryTestV1.class);
 

    // MY_PRIVATE_KEY
    protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCbFZ4tsIgTBbroQsMZJunv+ZRwoQxNL/8ZyQqWk7qn1g1Tb635KsUOEQNrFT+b0gWL91W3+rGF7ShSCNaS0Vb5wTva8a0xK0E70sLIhujxdIw9021EbS9BwCm7202LzXyd2jVrwf8rIfptcoaHcP344ZMM78r52rzZVCxWw5z0tZYsUuOMB9PLsm1J8RkDpbcAm2olbMtPpZhsAuDHKPH6EVaRrxC0rReZy2eh+Q6jdba3zxCRc8QO+rq1nJO6q330cscZ00+Y2e1ywF5pAXf81+Kg+QggGwn0pkAhn9KApqYNZ2Jwe7dPxRW1Skz6w8/x3AtfZNt4ezqOp3gQcaExAgMBAAECggEANJEBSq9ZkEkS74MhqjbceLD6NasBBnDMYSsZ4aw1SoptfeiO6bQrkvcFV5ieNOzdYHH3piLdZW3biuLgCGfYuVNcPHxKni3xMJvh1iKUdrNwjcxKbzUrHXhLLRfKkyaVpNO/48Sf/zjHL63wF5yfGWsscugcvs/7zxaO6OHpI7CYloyJ+Y3AC2QnzZLOVfjtcR19qq2jtU/rHwwGBBzthYeisicvwsQZA/Oj5jZylSAh83dKc5HW21AVmc3whe95Vz4k/IMyz0o/OU9UuGYiiH0VaHXKPiSNicEtAgP431t89PE9ErNusLvimeNeg0b/VdloC+0glrmqwhDLWWUpgQKBgQDnsl8rO81yKHin+AlAvqzC0NXQjj9LJXxnVeraTKxfStN9RFqxtHv8B6RiIpyANFDnd9mTtIR6N3tb2HJ8Km3S6BDOkZqF2UIwpzkrpZdFQN7p6atakNxm6Pb0tS2IVDyGjQctT9xO4FTsOgtTj9waVbNNB9a4vSxizUVNtx7JWQKBgQCrWgRPEp9ck6plKiPRcCaepyzwx/q+SuTk2ova3nu5cmc35Uo1KxBP6d4Y/42vylDGFMZU10LtODLFD/f7E6/KLb1m1bV3IwjyKFCqRefe/eVP9cBKSlIGPjLqfacDuMyJYaZd+Fngp43+lzos8lgKdwT5MU3e1RipYoVYHwFDmQKBgGkA8JqCVsBm0Q+mnGLoRxlfVZdX8B1ZVsDqMi5O00u4eJJr2QJyPkJhIEGNWAnOK+BK86M6C1PsMw7T0EavX+hWXc+QM0x3wsST9JfwStcK6DtwN8Uqo4hMCiequIDxVCDSZy9E4x4oErSgNaPgLasNrd26MLi2mxgH2WG4HM65AoGAHjYh2ls7M9RpT6rtY8j1VjW9i7qGsDR+RQdvbyiZAep03nsT6WntV1mxqhCsx5jRQwt4qI7HoxGsieg13dPrw6bq5Q27EAViV2faSRtINZ3oZ3+55p9R9P3UdlmvL83Oak6ISbs3BZAlTgUV4cTc1wODIfiadTZ3Qa44OoBE9ckCgYEAmOZzycNjDPS59lYIs+yK4GLmmruAwfUBf1sTfN6YWz8K41j8BQSTMvMjm20F5V1HN8SgLWdy61KdaSn6LQZKNp2BXS2AHTL1D/GXP4FrCo7ZZpnSBDJjQ18wctYdjS+4fMZdSPZ0A+0x4lPKjKEr5JDrkUgYnwp58w+ekYPJs40=";
    // APIGW_PUBLIC_KEY
    //protected static final String APIGW_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCMpjaWjngB4E3ATh+G1DVAmQnIpiPEFAEDqRfNGAVvvH35yDetqewKi0l7OEceTMN1C6NPym3zStvSoQayjYV+eIcZERkx31KhtFu9clZKgRTyPjdKMIth/wBtPKjL/5+PYalLdomM4ONthrPgnkN4x4R0+D4+EBpXo8gNiAFsNwIDAQAB";
    protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
    
    protected static final String APP_ID = "**";//10000000000004097413
    protected static final String RSA = "RSA";
    protected static final String RSA2 = "RSA2";
    protected static final String AESKEY="645r6z1azEItiR+ghwF4+g==";
    protected static final String CORPID="2000000067";
    protected static final String URL="http://**/api/bcss/member/identityInfo/query/V1"; //ip:port

    
    public static void main(String[] args) throws Exception {
    	BcssMemberIdentityInfoQueryTestV1 a = new BcssMemberIdentityInfoQueryTestV1();
        a.test_cop();
    }

    public void test_cop() throws IcbcApiException {
        BcssMemberIdentityInfoQueryRequestV1 request = new BcssMemberIdentityInfoQueryRequestV1();
        //request.setServiceUrl("http://ip:port/api/bcss/member/identityInfo/query/V1");
        request.setServiceUrl(URL);
        BcssMemberIdentityInfoQueryRequestBizV1 bizContent = new BcssMemberIdentityInfoQueryRequestBizV1();         
        bizContent.setCorpId("2000000067");
        bizContent.setManufacturerId("V1111");
        bizContent.setImeiNo("02");
        bizContent.setClientTransNo("045542");
        bizContent.setQueryType("3");
        String clientTransNo=getDateTimeString();
        JSONObject json=new JSONObject();
        json.put("dt", "10");
        json.put("custcode", "**");//120102199904210745
        json.put("custsort", "0");
        json.put("name", "田丹妮"); 
        json.put("paymedium", "**");// 6222080200022990439
        json.put("clientTransNo", clientTransNo);
        bizContent.setQrCode(encryptAesContentByKey1(AESKEY,json.toString()));
        // QrCode是aes加密信息  格式如下
        //{ \"dt\":\"1\", \"mobile\":\"\", \"validateCode\":\"\",  \"staffno\":\"\", \"name\":\"12121\", \"custcode\":\"23423423\", \"custsort\":\"1\", \"memCardNo\":\"232323232\"  }"
        bizContent.setClientType("3");
        bizContent.setTimeStamp(getTimeStamp());
      
        bizContent.setMerNo("");
        bizContent.setOtherData("");
     
        request.setBizContent(bizContent);
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,RSA2,MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        BcssMemberIdentityInfoQueryResponseV1  result = client.execute(request);
        
        logger.info(JSON.toJSONString(result));
    }
    
    public String getTimeStamp(){
    	Date day=new Date();    
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		return df.format(day);  
    }
    
    public String getDateTimeString(){
    	Date day=new Date();    
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss"); 
		return df.format(day);  
    }
    
    
    public String encryptAesContentByKey1(String aesKey, String content) {
        String result = null;
        try {
            result = IcbcEncrypt.encryptContent(content, "AES", aesKey, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return result;
    }
}
