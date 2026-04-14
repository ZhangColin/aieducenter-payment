package com.icbc.api;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcConstants;
import com.icbc.api.UiIcbcClient;
import com.icbc.api.request.JftPayrollWagingpayRequestV1;
import java.util.logging.Logger;
public class JftPayrollWagingpayTestV1 {
    public static void main(String[] args) throws IcbcApiException {
        String privateKey = "-----请使用正确的APP私钥-----EvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCUUdAvkW1u9heeqCKrGosn71reMIMc5wxaB7Y2uUfQYcEHfEpM+f18tRmJW4d6LrY5rWtG8QyFcuyH3ZYYl5PB4Ovs6TYoA6a7yIDgkjLKhUtGXIfsCmyNeV83EJyPVc2WHnCn5NU7khBPtqRTS3bccj7XQo9uP7Hi4dChG8T5MxMmVci873uoe2b6WxiCLUvbylqzp6zRHQJZsaylJXRAUP49CRF6/jhe6aBQCWd8nRTCUiWnLBwgY0kjjtDB0BzvTD+8SlOXdvX1Zmhuo1LbkfAU3IX7gB5Jk655LFjy/wZH1n8CJRMZtTrbsPSiboEx4DUYrr5BeqtB1XCY9vxhAgMBAAECggEAAib5xWbv1pddmmDIOAFnmYnAoM7FaEr2A+xuuXaQ0zZH7crg7SMHiaK7HyIj4iMBY+7fSqU+0ETtp78QaYTxkPOpmMN9lLEQ31K2hAu7DvNGN1wqYMN59ceYGR11mCLfm9kPO3B3o6in8fAtjEPhEqvZu5fsquXjt+4c7+mOPyt36HPjc4t5n7DOrhMsSmsoC3LhSY4tffu+5wrHzNxSGQUjjnX0Bo+GleHuhy2p+zhEzxnVrFjpb+g3e8Fx2ByOUGdG4x8I/eicPpFVHpmLkKY3a/YLKmNmmVGsYXgDO3S9aJLxC3EFpelRde4DPV6xVT1kUXQXx/HJivaObPNLUQKBgQDH8M1zLuhVjiJDjCtPm9rMxdi/6jLR6cp3IVt1jEPQsbKxEYLFdVwqBdOjJP7meSqPUfWBdQUoaFIrRgqMj6n/RC7ZJ02gspyTRessckmfBU8dmscRWxbea+fzZKr9TsMHj/199sswoDlGETQ642G4xe6mBaEXfnHh0+pPHJ2XfQKBgQC958qUsqhZNFf2PPbjHwBExxdcwYZW9Pyz7MeWN85EtdtAoDdLE3uFwjM4XEBMjXmDL5U7bo/+iQ0+LPJ5E59n+dQaSz/3SI+CZun0qyUqf18Ra2wZyw9X5jYs6Mpjl1Ia5g3eNzYO6Ks7ylwboOs2zs99nmIdMg6T7XztZUY1tQKBgQCb5jJ8Vp4IX+xtmPUIfPEtbDNm6Vk4/j/FhjgHPxh9uKEpmgrKpoj3p1VvR2COGvpJiFGS4MjlyfoQDewZz8dw6BxTbGcGH18hIoqOPIsVOJGI9u+zo8AALElPKjZ9jxVZZzf0KSQkRTqDXNTJ+hRve75qq8cBR7HKsW9owlpLDQKBgHz8WiMltpEHV0Ez/JWcqLObiO++WulOUiclFnLyhapJOmU9TUb3KpvtJYLZjGQV1LyXi5NTAChZEUMzl95CIO7wyT0I+6+HrRP5Ic+q+EoRDIPNfJc5Qv7qAAvspo+HXz1e3g+7rLOBRHmUhKyWa1hmIr+dhxuJ/3gynL8toVjlAoGBALbCYyEaBSJNICxSf9DcX5Vh1EgbHYleyjqpi5mqjl+XP+ww1xEaHMK045xQJjzEnBMEQsaFCttodp52DDVwZC0rEk5UO+GViwxXXI1o2Z7hMShe9hvYHjhhWE0nzhzMp4dmL2BvGPdARtYledMg3rF36B+tvybO58Tqoqw+JY4g";
        //  String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
       //  UiIcbcClient client=new UiIcbcClient("10000000000004097477",privateKey,IcbcConstants.CHARSET_UTF8);
      UiIcbcClient client = new UiIcbcClient("youapiid",IcbcConstants.SIGN_TYPE_RSA2, privateKey, IcbcConstants.CHARSET_UTF8);
      Logger log = Logger.getLogger(JftPayrollWagingpayTestV1.class.getName());
      JftPayrollWagingpayRequestV1 requestV1=new JftPayrollWagingpayRequestV1();
        requestV1.setServiceUrl("http://ip:port/ui/jft/payroll/wagingpay/V1");

        JftPayrollWagingpayRequestV1.JftPayrollWagingpayRequestV1Biz bizContent=new  JftPayrollWagingpayRequestV1.JftPayrollWagingpayRequestV1Biz();
        bizContent.setAppId("youapiid");
        bizContent.setAppName("dd");
        bizContent.setPlatId("02000002040095");
        bizContent.setCorpId("201003081416290");
        bizContent.setTotalAmt("123");
        bizContent.setTotalCount("111");
        bizContent.setAppSerialno("123");
        bizContent.setLocalFilepath("1222");
        bizContent.setFileType("1");
        bizContent.setAppRemark("111");
        requestV1.setBizContent(bizContent);

        log.info("response=>" + client.buildPostForm(requestV1));
        
    // System.out.println(client.buildPostForm(requestV1));
        
        
        
        
        // UiIcbcClient clien1t = new UiIcbcClient("10000000000004097477",IcbcConstants.SIGN_TYPE_RSA2, privateKey, IcbcConstants.CHARSET_UTF8);
 
      //   System.out.println(clien1t);
  			 
  	 
    }
}
