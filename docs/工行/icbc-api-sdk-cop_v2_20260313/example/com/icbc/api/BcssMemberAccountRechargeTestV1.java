package com.icbc.api;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.BcssMemRechargeRequestV1;
import com.icbc.api.response.BcssMemRechargeResponseV1;
import com.icbc.api.request.BcssMemAccount;
import com.icbc.api.utils.IcbcEncrypt;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class BcssMemberAccountRechargeTestV1 {

	private static final Log logger = LogFactory.getLog(BcssMemberTradeInfoRechargeTestV1.class);
	  // MY_PRIVATE_KEY
    protected static final String MY_PRIVATE_KEY = "XXXXXXXXXXXXXXXXXX";
    // APIGW_PUBLIC_KEY
    protected static final String APIGW_PUBLIC_KEY = "XXXXXXXXXXXXXXXXXX";
    // APP_ID
    protected static final String APP_ID = "1*************************6";
    protected static final String AESKEY = "**********";
    protected static final String RSA = "RSA";
    protected static final String RSA2 = "RSA2";

    protected static final String URL = "http://******/api/bcss/member/account/recharge/V1";
    
    // 10000000000004096876
    public static void main(String[] args) throws IcbcApiException {

        BcssMemRechargeRequestV1 request = new BcssMemRechargeRequestV1();
        request.setServiceUrl(URL);
        BcssMemRechargeRequestV1.BcssMemRechargeRequesBizV1 bizContent = new BcssMemRechargeRequestV1.BcssMemRechargeRequesBizV1();

        //[{  \"amount\": \"1\",\"currency\": \"5\",\"trxDiscp\": \"1\", \"tradeType\": \"1\", \"userInfo\": \"{ \"memCardNo\": \"234243324\" }\"  }]/

        List<BcssMemAccount> accountList = new ArrayList<BcssMemAccount>();
        BcssMemAccount bcssMemAccountObj = new BcssMemAccount();
        bcssMemAccountObj.setAmount("4");
        bcssMemAccountObj.setCurrency("001");
        bcssMemAccountObj.setTradeType("1");
        bcssMemAccountObj.setTrxDiscp("111");

        JSONObject json = new JSONObject();
        json.put("memCardNo", "0000230000043888");

        bcssMemAccountObj.setUserInfo(encryptAesContentByKey1(AESKEY, json.toString()));

        accountList.add(bcssMemAccountObj);

        bizContent.setCorpId("2000000067");//2000000067

        bizContent.setMemCardNo("0000230000043888");

        bizContent.setRechargeAmt("4");
        bizContent.setClientTransNo("02020051711102601936");
        bizContent.setAccountList(JSONObject.toJSONString(accountList));
        request.setBizContent(bizContent);

        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        BcssMemRechargeResponseV1 response = client.execute(request);
        logger.info(JSONObject.toJSONString(response));

    }

    public String getTimeStamp() {
        Date day = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(day);
    }

    public String getDateTimeString() {
        Date day = new Date();
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
