package com.icbc.api;

import java.util.logging.Logger;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.MybankLoanHousefundsupHfsprovecreateRequestV1;
import com.icbc.api.request.MybankLoanHousefundsupHfsprovecreateRequestV1.MybankLoanHousefundsupHfsprovecreate;
import com.icbc.api.request.MybankLoanHousefundsupHfsprovecreateRequestV1.MybankLoanHousefundsupHfsprovecreateRequestV1Biz;
import com.icbc.api.response.MybankLoanHousefundsupHfsprovecreateResponseV1;

public class MybankLoanHousefundsupHfsprovecreateV1Test {
    static Logger log = Logger.getLogger(MybankLoanHousefundsupHfsprovecreateV1Test.class.getName());
    //估值表
    protected static String APP_ID = "1000"+"000000"+"00002"+"33018";

    //网关公钥
    protected static String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
    //应用私钥（与内管平台注册app时的“签名公钥”配对）
    protected static String PRI_KEY = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCoCbjsHAnWpl+DsfqSTN8B2ZWC0KSRH8qENz6XAuiuOj3RiSDg2dwwKEGUHsUjf9a/us+xfpsrZtz6ehGh5t+jf8VfGSwjfR4fAtoEN88woBViNckV4l4tjF8cqysdsxtyheCny5COUvBQgeGAzH1HqIuI/2WOpJhizA4tNCJVpn3HqZrkoip4Ja6UmRwYkcQgzfwv1L9ojFtOU5kuEPUK+i07xBP+HhGEVT7o9qWk77YHVAbHJLbcvvfYqg5mPQy4za+ZXuNfy8usGEqaUFjbG0/XfTa2lx9Hb8SwD6Al6QceiYf4HKHTfdXXqdcxGRol1LsaP93pbqI43P40YSOPAgMBAAECggEBAIp6OKKbGKCDywLEkTIvlzf3D3cWaggOot4CiNn507aA2KDs2ONzpjkpc6GVGfD/UQH/+VjUdRt3UXQ5VH6PvI0IpqaQIAe+/CuiNfhz5ZK02eptWKFoQyRWIauGg6meCGiAW3rsDty7DL/LyQJTgoqjIxprHk5G5i3E64nlBMZ/cAdjMyrbASq9kHAQd0L2GOeMloEsy7xnwoOoVeKo+MsQx49bm5y+bx0hMiACa+NulQJqNRvg/hQViVULJgnbLqWrOwyyg+sPHwXnJW3WY+r5PlplKyZ53Y3s5BUPXnouMMT+B73eMszZnBOG8DCbfrfgnr99O1E2D5SuyoXvkkECgYEA1FFL+WGyJ9UINinIT4+vlpA7DKKFhiAItMsRYL4ierJcmsbYWgLQ5VQP6YaqsdwxLgGw3T2jS7+qQvVy6/lupewxTx2b8CkzH9KLaxYlw5sQOTfhcOFeGAj1aRzyo4HFdcxi3if+F9lUyJCoIuYNO2OPYJ0/IujSHg+7EtnhxW0CgYEAypw7mrHt8Yu5a+ovhu9H5lyPJyddMW2VcLAHb8lkFyCjP2kc0bfU3wD7lQIvYTt6uky0Jx6Klfo3CWZSkvyYIp1QuPOtVSKPXvYmn8laUPy11lTQHUw7GEVxlq7keAbJE2kPgm7mwUnZEtPKXGViLhJ5ulmuIHJT7tr8iJ/hu2sCgYBij2a8WrzQPtV0B0tkm6gANe4jfGKNk9tWyhnEFh99Y8UXWI1go4QDoVVNq+UbccMmv0QyQAXJCS1prfVuU7korw9o0a7s+sMv15Y+3OYk+pu6ZBV0SDpc3/PEmtqMSpa2i22uAVS0D4CLJpPWt4KW+i25xOVlOD0qFVVbd+eVtQKBgGkcxI9s39UAeCHUTPPkD5kjS+E0OtT51R2xNSp5gMO+2vJ6xcjHZliLWfnkOFBpWzJruMrSvXMxbHwj6ZL4GLVciBRr1vDkDR1m9oLPI1wAvxWCyrVoZwAght11vnHGk2rOho1Um8KLXWK3HbTxpA3i5+QVwpByv7ibQ4hd6e4hAoGAPNx4/jkjMvOyKPKTEZP4hR8g2lSlCCA+7lW3QpplhiYa9+0OyH6vUNOKA5kJbaJ80W8H8WS2GFHzhCTpWi/UwUC5lrf2PcYWPuIs9HNGrDptaizjypPaMx5b5uZ/7ctt+tu+IQm7R8z/BKDq5S9nZTvaJuzpAHijMPqJr09BosE=";
    public static void main(String[] args) throws Exception {
        //  String URI = "investment/insurance/prodapply/applycheck/V1"; // api地址
       // DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2",PRI_KEY, APIGW_PUBLIC_KEY);
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", PRI_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, null, (String)null, (String)null, (String)null);               
        
        try {

            // 请求实体
        	MybankLoanHousefundsupHfsprovecreateRequestV1 request = new  MybankLoanHousefundsupHfsprovecreateRequestV1();
            // bizContent实体
        	MybankLoanHousefundsupHfsprovecreateRequestV1Biz bizContent  = new  MybankLoanHousefundsupHfsprovecreateRequestV1Biz();

        	MybankLoanHousefundsupHfsprovecreate param = new MybankLoanHousefundsupHfsprovecreate();
        	
        	param.setCooperCode("666666");
        	param.setOrderNo("11111111");
        	param.setBankNo("325555");
        	param.setCooperPK("123456");
        	param.setCooperOperPk("2344353634");
        	param.setQueryType("1");
        	param.setSubAmtType("01");
        	param.setAccNo("23453536");
        	param.setAccName("测试");
        	param.setAccType("6");
        	param.setAccBank("53453464363634");
        	param.setAccbankName("测试12");
        	param.setSubAmt("10.00");
        	
            bizContent.setParam(param);                                                        
            //request.setServiceUrl(BASE_URL  + URI);           
            String url = "http"+"://8"+"3.28"+".222."+"45:80"+"81/api/mybank/loa"+"n/housefundsup/hfsprovecreate/V1"; 
            
            request.setServiceUrl(url);
            request.setBizContent(bizContent);
            MybankLoanHousefundsupHfsprovecreateResponseV1 response = client.execute(request);
            log.info("response:" + JSON.toJSONString(response));
            boolean flag=response.isSuccess();
            if (flag) {
                // 业务成功处理
                log.info("success");//
            } else {
                // 失败
                log.info("error");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

