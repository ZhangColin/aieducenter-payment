package com.icbc.api;

 
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
 
import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.BcssMemRechargeRequestV1;
import com.icbc.api.request.BcssMemRechargeRequestV1.BcssMemRechargeRequesBizV1;
 
import com.icbc.api.response.BcssMemRechargeResponseV1;
import com.icbc.api.utils.IcbcEncrypt;
import com.icbc.api.request.BcssMemAccount ;

public class BcssMemberTradeInfoRechargeTestV1 {

    	private static final Log logger = LogFactory.getLog(BcssMemberTradeInfoRechargeTestV1.class);

	    // MY_PRIVATE_KEY
	    protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCbFZ4tsIgTBbroQsMZJunv+ZRwoQxNL/8ZyQqWk7qn1g1Tb635KsUOEQNrFT+b0gWL91W3+rGF7ShSCNaS0Vb5wTva8a0xK0E70sLIhujxdIw9021EbS9BwCm7202LzXyd2jVrwf8rIfptcoaHcP344ZMM78r52rzZVCxWw5z0tZYsUuOMB9PLsm1J8RkDpbcAm2olbMtPpZhsAuDHKPH6EVaRrxC0rReZy2eh+Q6jdba3zxCRc8QO+rq1nJO6q330cscZ00+Y2e1ywF5pAXf81+Kg+QggGwn0pkAhn9KApqYNZ2Jwe7dPxRW1Skz6w8/x3AtfZNt4ezqOp3gQcaExAgMBAAECggEANJEBSq9ZkEkS74MhqjbceLD6NasBBnDMYSsZ4aw1SoptfeiO6bQrkvcFV5ieNOzdYHH3piLdZW3biuLgCGfYuVNcPHxKni3xMJvh1iKUdrNwjcxKbzUrHXhLLRfKkyaVpNO/48Sf/zjHL63wF5yfGWsscugcvs/7zxaO6OHpI7CYloyJ+Y3AC2QnzZLOVfjtcR19qq2jtU/rHwwGBBzthYeisicvwsQZA/Oj5jZylSAh83dKc5HW21AVmc3whe95Vz4k/IMyz0o/OU9UuGYiiH0VaHXKPiSNicEtAgP431t89PE9ErNusLvimeNeg0b/VdloC+0glrmqwhDLWWUpgQKBgQDnsl8rO81yKHin+AlAvqzC0NXQjj9LJXxnVeraTKxfStN9RFqxtHv8B6RiIpyANFDnd9mTtIR6N3tb2HJ8Km3S6BDOkZqF2UIwpzkrpZdFQN7p6atakNxm6Pb0tS2IVDyGjQctT9xO4FTsOgtTj9waVbNNB9a4vSxizUVNtx7JWQKBgQCrWgRPEp9ck6plKiPRcCaepyzwx/q+SuTk2ova3nu5cmc35Uo1KxBP6d4Y/42vylDGFMZU10LtODLFD/f7E6/KLb1m1bV3IwjyKFCqRefe/eVP9cBKSlIGPjLqfacDuMyJYaZd+Fngp43+lzos8lgKdwT5MU3e1RipYoVYHwFDmQKBgGkA8JqCVsBm0Q+mnGLoRxlfVZdX8B1ZVsDqMi5O00u4eJJr2QJyPkJhIEGNWAnOK+BK86M6C1PsMw7T0EavX+hWXc+QM0x3wsST9JfwStcK6DtwN8Uqo4hMCiequIDxVCDSZy9E4x4oErSgNaPgLasNrd26MLi2mxgH2WG4HM65AoGAHjYh2ls7M9RpT6rtY8j1VjW9i7qGsDR+RQdvbyiZAep03nsT6WntV1mxqhCsx5jRQwt4qI7HoxGsieg13dPrw6bq5Q27EAViV2faSRtINZ3oZ3+55p9R9P3UdlmvL83Oak6ISbs3BZAlTgUV4cTc1wODIfiadTZ3Qa44OoBE9ckCgYEAmOZzycNjDPS59lYIs+yK4GLmmruAwfUBf1sTfN6YWz8K41j8BQSTMvMjm20F5V1HN8SgLWdy61KdaSn6LQZKNp2BXS2AHTL1D/GXP4FrCo7ZZpnSBDJjQ18wctYdjS+4fMZdSPZ0A+0x4lPKjKEr5JDrkUgYnwp58w+ekYPJs40=";
	    // APIGW_PUBLIC_KEY
	    //protected static final String APIGW_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCMpjaWjngB4E3ATh+G1DVAmQnIpiPEFAEDqRfNGAVvvH35yDetqewKi0l7OEceTMN1C6NPym3zStvSoQayjYV+eIcZERkx31KhtFu9clZKgRTyPjdKMIth/wBtPKjL/5+PYalLdomM4ONthrPgnkN4x4R0+D4+EBpXo8gNiAFsNwIDAQAB";
	    protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
	            // APP_ID
        protected static final String APP_ID = "*******";//10000000000004097413
        protected static final String RSA = "RSA";
        protected static final String RSA2 = "RSA2";
        protected static final String AESKEY="645r6z1azEItiR+ghwF4+g==";
        protected static final String CORPID="2000000067";
        protected static final String URL="http://*******/api/bcss/member/tradeInfo/accRecharge/V1";// ip:port
        

		// 10000000000004096876
		public static void main(String[] args) throws IcbcApiException {
			
			//http://ip:port:8005/api-member/bcssGetExchangeNoteService/query?corpId=2000000067
			//&personId=d9xqFJMpYN1/7QVR6Ae1UqN94QsIlUPCcSiBtgALJE3/4TMHu6u/NDllWuBVyhnr
			//&timeStamp=2020-02-02 12:00:00&trxSrc=1
				
			BcssMemRechargeRequestV1 request = new BcssMemRechargeRequestV1();
			//http://ip:port/api/cardbusiness/aggregatepay/b2c/online/refundqry/V1
			request.setServiceUrl(URL);
			BcssMemRechargeRequesBizV1 bizContent = new BcssMemRechargeRequesBizV1();
            
            //[{  \"amount\": \"1\",\"currency\": \"5\",\"trxDiscp\": \"1\", \"tradeType\": \"1\", \"userInfo\": \"{ \"memCardNo\": \"234243324\" }\"  }]/
            
            List<BcssMemAccount>  accountList = new ArrayList<BcssMemAccount>();
            BcssMemAccount bcssMemAccountObj = new BcssMemAccount();
            bcssMemAccountObj.setAmount("3234.33");
            bcssMemAccountObj.setCurrency("001");
            bcssMemAccountObj.setTradeType("3");
            bcssMemAccountObj.setTrxDiscp("3434sdf");
            
            JSONObject json=new JSONObject();
            json.put("memCardNo", "120*******45");
        
            bcssMemAccountObj.setUserInfo(encryptAesContentByKey1(AESKEY,json.toString()));
            
            accountList.add(bcssMemAccountObj);
            
            bizContent.setCorpId("2000000067");//2000000067
	 
            bizContent.setMemCardNo("34434343"); 
		 
            bizContent.setRechargeAmt("1.222");
            bizContent.setClientTransNo("323232");
            bizContent.setAccountList(accountList);
			request.setBizContent(bizContent);

			DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
			BcssMemRechargeResponseV1 response = client.execute(request);
			logger.info(JSONObject.toJSONString(response));
			 
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
	    
	    
	    public static String encryptAesContentByKey1(String aesKey, String content) {
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
