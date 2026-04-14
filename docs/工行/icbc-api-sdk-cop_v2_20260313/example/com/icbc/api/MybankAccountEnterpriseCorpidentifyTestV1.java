package com.icbc.api;

import com.icbc.api.request.MybankAccountEnterpriseCorpidentifyRequestV1;
import com.icbc.api.request.MybankAccountEnterpriseCorpidentifyRequestV1.MybankAccountEnterpriseCorpidentifyRequestV1Biz;
import com.icbc.api.request.MybankAccountEnterpriseCorpidentifyRequestV1.ChanCommV11;
import com.icbc.api.request.MybankAccountEnterpriseCorpidentifyRequestV1.InfoCommV11;
import com.icbc.api.request.MybankAccountEnterpriseCorpidentifyRequestV1.PrivateMap;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MybankAccountEtradeMemberTestV1 {
		// 备注：1-6点根据实际情况进行修改替换
		// 1.公钥
    protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCQJkp7Sqh/k5zk8zLufDzJ4kL/DyRN7Vn4oLoqlaAnq21Zq7Dhlrfko4+wKqK7enf8Fwt5xNkfXJbktoTTg0C5v5V21yjrBdoV1masnqq5frkwU2QTTB5lZXxeYPze2+qv7Q+vjV8IISr2N2H2YrdUZZYjboWCG0AAaf17C1T1SjdpfUn2YTtqL2G7UhfDGHqyvmYUzeqSaF0kxrXe9EDWZPwvP7fxKqgvQAkoBOYUkGtRZjQFOjoWieFaNCD8C61noNvv1nJfdn3YsCcQEHhlO0ra3qkF1+t515RFsl+q7U4pZH/Lj5bdp5MWI88qLASrmGIsJeMP/Ynu8Dk16tHbAgMBAAECggEARRtGW+NH8pmW/yAky7aeAf9KNTNSKVXf61VKyv2eoUYLD5FDJHN4R9GuIjvedljZSu7VXpRjryhZYt6pwSlMj4BsrGOsRzgv/IYkBR+wI2vAZo2kDahRbjUAMdL3h/QQiDTzbyssYx5blVypx1A+KeTl3KiwstZTkZ8+mY2uNkWcmgPinPDaYrEldpaj087shm3b+yvkPwaftxY8ewP2St7WbjruZMhm45RFwTPrMW3YPHJCjfUQK3fwXDmZII6BeJoGimM6VH4V8K7+L/rKo3TulOXrVq00tJd3mVErKTpOMjgE7XkWOdjX3lEKZc6S64gcVZ5bV6vOTJvR+9xz8QKBgQDPm0ACkW/zUlQXuClAhUdXK3Zw6vQ2ZMuQ9U1rDaVGQlonF724hTnXr/AyVPEEwO+kBLTDn0AP9Ldk9yUQhpDSPK72fQgu7kGOeQRy+zgRCUuZXn+vbSgodS6xCxOXdpPx+yyr/kP4ZiMALYwL5r7PQ+O6wE6WihMGPQ6fkHZlIwKBgQCxwE1sSAvPAqrp3m2EL5e5yBpZry8sGaSY55nrjMXKHaCWyLTHW6eVXi5ZMh4q9OydNHkxn/gcOjdb1ajvaNybT76+DME0YQNw4xTHwDJT1auC1QZMj9/MhTVS6hzFCjRCUqdlVht1gfqwS/+WCRJBY811F/oRBLzL7lqwolz36QKBgAdLnlk4wSVczEW1HAX/aP1gMp5PM1vB+QFUDW9ooFkytbZP+EPGsryomxk5sOqI2SL7Y1rIetjUsakjojLNd8NbjI5u4F2906HtEMH1oAfBOt3d20DP5ixdydFA4WReL16+g9hbbAoLlMlJ0HUOm7/u087ZOSnDGl4KwTvMOMzfAoGBAKTTIDgPWrvbymbQ9ZbElxoT/rM5KjHnmWScJnNw9xuje302ocPXLr/oNGxLeAP3V82ykcs9flGWfizvJBbUkvSF7ubGZwXOC+XK/EL56vEoSkEojg3CwBEjWL4JDyJ7gtgZUtY6EX5WSALghiE3SjFOfX9d+2l6jiNAY+I0vKa5AoGAGZkr7ByB16nzgtyOm43SZPUDOAQSAFW3E8uTHUZ6AJ83XQLhizN8nx7SENx15iRYtD5qAcV9hHBgGoKq/dlK5cMeuixboFBDM+H1O77zuSlbsYxzrdhePnK5q6zVhbAudPRd3/XqYqdTB31FWFIZNX31yM1ZdqI2OajMf3lXcMA=";
		// 2.私钥
    protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
    // 3.APP的ID
    protected static final String APP_ID = "10000000000004094684";
		// 4.调用方式
    protected static final String SIGN_TYPE = "RSA2"; 
		// 5.API的访问url
    protected static String URL = "http://ip:port/api/mybank/account/enterprise/corpidentify/V1";

    public static void main(String[] args) throws Exception {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, SIGN_TYPE, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSSZ");
        try {
        		// 6.通讯区赋值(根据md文档的字段要求赋值，必输的需要送值)
            ChanCommV11 chancomm = new ChanCommV11();
            chancomm.setChantype("101");
            chancomm.setChanlno("0");
            chancomm.setMac("5445");
            chancomm.setIp("ip:port");
            chancomm.setTermid(1);
            chancomm.setOapp("F-NCMS");
            chancomm.setSerialno("010140001070000777227693329");
            chancomm.setMserialn("010140001070000777227693329");
            
            InfoCommV11 infocomm = new InfoCommV11();
            infocomm.setTrxtype("1");
            infocomm.setTrxcode("41286");
            infocomm.setZoneno("200");
            infocomm.setBrno("620");
            infocomm.setWorkdate("2022-09-20");
            infocomm.setWorktime("15:42:00");
            infocomm.setRevtranf("0");
            infocomm.setServface("1");
            infocomm.setTerminalId("1");
            infocomm.setOperflag("4");
            infocomm.setCheckWorkDateFlag("1");
            
            PrivateMap privateMap = new PrivateMap();
            privateMap.setOperFlag("");
            privateMap.setApplno("");
            privateMap.setTrCode("");
            privateMap.setRecTime("");
            privateMap.setCustomerAgreement("");
            
            MybankAccountEnterpriseCorpidentifyRequestV1Biz biz = new MybankAccountEnterpriseCorpidentifyRequestV1Biz();
            biz.setChanCommV11(chancomm);
            biz.setInfoCommV11(infocomm);
            biz.setPrivateMap(privateMap);
						
						// 7.调用服务返回结果
            MybankAccountEnterpriseCorpidentifyRequestV1 requestV1 = new MybankAccountEnterpriseCorpidentifyRequestV1();
            requestV1.setBizContent(biz);
            requestV1.setServiceUrl(URL);
            //client.execute(requestV1);
            MybankAccountEnterpriseCorpidentifyResponseV1 responseV1 = client.execute(requestV1);
            if(responseV1.isSuccess()){
                System.out.println("success");
            }else {
                System.out.println("error");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
