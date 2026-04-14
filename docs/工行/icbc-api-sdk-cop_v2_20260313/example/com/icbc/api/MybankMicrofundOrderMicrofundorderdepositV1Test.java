package com.icbc.api;

import java.util.Random;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.request.MybankMicrofundOrderMicrofundorderdepositRequestV1;
import com.icbc.api.request.MybankMicrofundOrderMicrofundorderdepositRequestV1.MybankMicrofundOrderMicrofundorderdepositRequestV1Biz;
import com.icbc.api.response.MybankMicrofundOrderMicrofundorderdepositResponseV1;

public class MybankMicrofundOrderMicrofundorderdepositV1Test {
	/**
	 * @param args
	 */
	// 1、网关公钥
	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的APP私钥-----DljCCAn6gAwIBAgIKbaHKEE0tAAI7+jANBgkqhkiG9w0BAQsFADA3MRkwFwYDVQQKExBjb3JiYW5rNDMuY29tLmNuMRowGAYDVQQDExFjb3JiYW5rMTI4IHNkYyBDTjAeFw0xODEwMzEwNTM3MzFaFw0yOTAxMzEwNTM3MzFaMEkxHTAbBgNVBAMMFDAyMDBFSDAwMTMwMzUuZS4wMjAwMQ0wCwYDVQQLDAQwMjAwMRkwFwYDVQQKDBBjb3JiYW5rNDMuY29tLmNu-----请使用正确的APP私钥-----BIjANBgkqhkiG9w0BAQEFAAOCAQ8A-----请使用正确的APP私钥-----BCgKCAQEAnqvltq3gG1eVgTJ547CNI0jfzgC6GphVQyE7YzhHwJLnufTBAx/Zezg9M+R1UJWqg3R+WYPu4VXpkaIz0N/0obG5FkMFqyViz8ZSwUHcntLK5R65vBbbJ/UVcAe3GYpIBONB7ApeLuWjh5SZ0Bq8rXZ/DVjg/JMbBnqG50rbjETRHsaLODn447yJMK4H3wEq9DaRHH8xEkd01qv3lnRb0PpU78EWyMwoyHgvo7GLuR0sAjOeRAqNeYrenm2rG+UGyYsT9/K4Lw6H2fAfdtFUMaAQ+q3p+qQpPfMJXRTXLgTfQTdlqweVIppYFMNp1IkwbWtqbXliN4rCknj1VxjNaQIDAQABo4GRMIGOMB8GA1UdIwQYMBaAFJ6AEaO7V853/fEnZ/7k4X8i8lHzMEwGA1UdHwRFMEMwQaA/oD2kOzA5MQ0wCwYDVQQDDARjcmwzMQ0wCwYDVQQLDARjY3JsMRkwFwYDVQQKDBBjb3JiYW5rNDMuY29tLmNuMB0GA1UdDgQWBBQEmtl3SpIDUN49NC3V+kprWYraTTANBgkqhkiG9w0BAQsFAAOCAQEAQKBKbgxSwRoZbqgOza+CZFcP3Sp16x2Yek1C5nlcX5REHCinbNVeMKmqnTEl8DK/XX0f/nuj+5WPsSMeQ1ltSjfMm1NAJNwwTqdq1zU5jK4YCA0dZhj90pooVgcXu152erCcP2lHncAk3xrVxgMWIL82sYQu3pE/TmEc6K6ofuPzcCdNxoAJpn82qYuCxNQUxu3fKdZsEpwt0sgJBhSiVkzxUMK9GcEXz177f6ktivJfw61VKZUq3BQXv9VvuprLEeCC9xJLX7quph/Nl9Q/4/4NU2dRFqXdWgpW98exaKQv6g2+a9f7PqW+cAKVEOZvvkdDzxkntv7yrccFEDJi6w==";
  // 2、appid
	protected static final String APP_ID = "10000000000000003917"; 
  // 3、密钥对认证方式，公钥在API平台登记，此处为私钥
	protected static final String MY_PRIVATE_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        		
		// 设置请求对象request
		MybankMicrofundOrderMicrofundorderdepositRequestV1 req = new MybankMicrofundOrderMicrofundorderdepositRequestV1();
		
		// 设置请求路径
		req.setServiceUrl("http://ip:port/api/mybank/microfund/order/microfundorderdeposit/V1");

		MybankMicrofundOrderMicrofundorderdepositRequestV1Biz biz = new MybankMicrofundOrderMicrofundorderdepositRequestV1Biz();

		//赋值通讯区
		biz.setAPPID("10000000000000003917");
		biz.setZONENO("200");
		biz.setBRNO("998");
		biz.setTELLERNO("12");	
		biz.setCHNCODE("302");
		biz.setCHANLNO("1");    
		biz.setMAC("");        
		biz.setIP("");         
		biz.setSEVLEVEL("1");   
		biz.setSERIALNO("010102008567094459735880525");   
		biz.setMSERIALN("");   
		biz.setOSERIALN("");   
		biz.setPREFLAG("0");    
		biz.setAUTHZONE("");   
		biz.setAUTHBRNO("");   
		biz.setAUTHFLAG("");   
		biz.setTELLPASS("");   
		biz.setTRITMSP("");    
		biz.setPRODID("");     
		biz.setCOBPRODID("");  
		biz.setCHAN_FLAG1(""); 
		biz.setCHAN_FLAG2(""); 
		biz.setCHAN_FLAG3(""); 
		biz.setCHAN_FIELD1("");
		biz.setCHAN_FIELD2("");
		biz.setCHAN_FIELD3("");
		biz.setCHAN_FIELD4("");
		biz.setCHAN_FIELD5("");
		biz.setCHAN_FIELD6("");
		biz.setCHAN_FIELD7("");
		biz.setCHAN_FIELD8("");
		biz.setCHAN_FIELD9("");
		biz.setTRXCODE("01000");
		biz.setACTBRNO("");
		biz.setAUTHTLNO("");
		biz.setAUTHCODE("");
		biz.setLEVEL("");
		biz.setCARDNO("");
		biz.setAUTHPWD("");
		biz.setDUTYNO("");
		biz.setAUTHAMT("");
		biz.setWORKDATE("2023-04-30");
		biz.setWORKTIME("16:17:48");
		biz.setUNISEQNO("");
		biz.setREVTRANF("");
		biz.setSERVFACE("10");
		biz.setUSEJNLF("");
		biz.setOPERFLAG("");
		biz.setTERMID("10");
		biz.setNOTES1("");
		biz.setOPERTYPE("002");
		biz.setIFTRXSERNB("000005");
		biz.setPTRXSERNB("");
		biz.setPRETELNO("");
		biz.setYZEVENTID("");
		biz.setYZFLAG("");
		biz.setYZSUMMARY("");
		biz.setTRACHN("10");
		biz.setVALUEDAY("2023-04-30");
		biz.setSUMMARY("");
		biz.setGDTLFLAG("");
		biz.setGDTLNOTE("");
		biz.setACCFEE_F("");
		biz.setEBUINESSEQNO("");
		biz.setINDUSTRY_ID("0000002");
		biz.setPROJECT("");
		biz.setMKTNO("0043102");
		biz.setBUYERMEMTYPE("1");
		biz.setBUYERBTFLAG("0");
		biz.setBUYERACCNO("0200099809277402381");
		biz.setBUYERCURR("001");
		biz.setBUYER_TEL("13631263449");
		biz.setSELLERMEMTYPE("1");
		biz.setSELLERBTFLAG("0");
		biz.setSELLERACCNO("0200099809277402133");
		biz.setSELLERCURR("001");
		biz.setSELLER_TEL("18600003102");
		biz.setSELLER_ADDRESS("");
		biz.setORDERNO("");
		biz.setORDER_PRICE("");
		biz.setAMOUNT("2");
		biz.setREFUND_REASON("");
		biz.setREJECT_REASON("");
		biz.setVERIFICATION_CODE("");
		biz.setREAL_TEL("");
		biz.setSMS_NO("");
		biz.setISREADICBCTERM("1");
		biz.setOTACCNO("");
		biz.setOTACCNAME("");
		biz.setOTBNKNO("");
		biz.setBUYER_TEL2("13631263448");
		biz.setSELLER_TEL2("18600003101");
		biz.setMEMACCNOTYPE("");
		biz.setNOTE("");
		biz.setBAKDEC1("");
		biz.setBAKDEC2("");
		biz.setBAKDATE1("");
		biz.setBAKDATE2("");
		biz.setBAKCHAR1("");
		biz.setBAKCHAR2("");
		biz.setBAKCHAR3("");
		biz.setBAKCHAR4("");
		biz.setBAKCHAR5("");
		biz.setBAKCHAR6("");
		biz.setREALNAMEFLAG("");
		biz.setCUSTNO("");
		biz.setEPAYADDFLAG("");
		biz.setCHANTYPE("");
		biz.setAPPTYPE("");
		biz.setCHNSEQNO("");
		biz.setINTRXCODE("");
		biz.setZONENOWEB("");
		biz.setBRNOWEB("");
		biz.setZONENOCRD("");
		biz.setBRNOCRD("");
		biz.setREGF("");
		biz.setINTELNO("");
		biz.setAINTELNO("");
		biz.setCURRTYPE("");
		biz.setMDCARDNO("");
		biz.setCUSTTYPE("");
		biz.setPROTSENO("");
		biz.setUITYPE("");
		biz.setTRANSAMT("");
		biz.setREGTYPE("");
		biz.setPCFNO("");
		biz.setDYDAYQUOTA("");
		biz.setBOUNDFLG("");
		biz.setDEFLIMFLAG("");
		biz.setDEFLIM_SNG("");
		biz.setDEFLIM_DAY("");
		biz.setMQLIMFLAG("");
		biz.setB_PREFLAG("");
		biz.setCHECKFLG("");
		biz.setEPAYFLAG("");
		biz.setE_BAKFLAG1("");
		biz.setE_BAKFLAG2("");
		biz.setE_BAKDEC1("");
		biz.setE_BAKDEC2("");
		biz.setE_BAKCHAR1("");
		biz.setE_BAKCHAR2("");
		biz.setE_BAKDATE("");
		biz.setPROJECT_NO("");
		biz.setRECEIVE_CYCLE("");
		biz.setITEMS_NO("00001");
		biz.setFUNDMANAGE_NO("00001");
		biz.setRETURN_CYCLE("");
		biz.setSUPERVISE_ACCNO("");
		biz.setSUPERVISE_ACCNAME("");
		biz.setAGENCY_ACCNO("");
		biz.setAGENCY_ACCNAME("");
		biz.setAGENCY_TEL("");
		biz.setAGENCY_TEL2("");
		biz.setOTBNKNO_S("");
		biz.setBUYER_NAME("");
		biz.setSELLER_NAME("");
		biz.setINTERMEDIARY_CHARGE_AMT("");
		req.setBizContent(biz);
    //赋值通讯区完毕
        
		IcbcResponse response;

		Random rand = new Random();
		String msgId = rand.nextInt(99999) + "msg";
		System.out.println(msgId);

		try {
			response = client.execute(req, msgId);
			if (response.isSuccess()) {
				// 业务成功处理
				System.out.println("success");//
			} else {
				// 失败
				System.out.println("error");
			}
		} catch (IcbcApiException e) {
			e.printStackTrace();
		} finally {
		}
	}
}
