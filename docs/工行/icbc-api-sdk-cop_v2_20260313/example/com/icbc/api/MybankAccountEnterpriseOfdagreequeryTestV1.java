package com.icbc.api;

import com.icbc.api.request.MybankAccountEnterpriseOfdagreequeryRequestV1;
import com.icbc.api.request.MybankAccountEnterpriseOfdagreequeryRequestV1.MybankAccountEnterpriseOfdagreequeryRequestV1Biz;
import com.icbc.api.request.MybankAccountEnterpriseAccopenoutsiteinfoqueryRequestV1.ChanCommV11;
import com.icbc.api.request.MybankAccountEnterpriseAccopenoutsiteinfoqueryRequestV1.InfoCommV11;
import com.icbc.api.request.MybankAccountEnterpriseAccopenoutsiteinfoqueryRequestV1.PrivateMap;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MybankAccountEnterpriseOfdagreequeryTestV1 {

    protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCB0ZjVnewk6klgZmsblTIDoJijQckYTwWoBnL7RtxdHEJRB1E4lkyE3JMZjPg6l2sK1W4jWRT3+DB8RAI/GQrnPzk7sgXb4QxzSuuzW6JCAglnX3aDUVnUBF1HvmPKmSQlpElR4LzdQ8Z3fYNuRftmJNNsOE3DSMcQaW4iexrOKXlCkJv1FFVGfKrImR4mzWmCQqPk4VDIvULl59vtGF5GRiKv9nE7GL701vkIZAhbyAGrajjecIYXbGCkLHf4th1vAEuWyn3ZdUO8kQq0hzagc0IW9xs2szzo/4yBK/wWXQFwojZSWy/VROVeW/wuGc6kYUrOLupbYhdSzqsjLtznAgMBAAECggEAW+xUcn6epg0X5CDhmuhsP9FKA+AjSwk5kh7u8CrfAzdtZf7ubklO6Y1ew+rkImjxrPwwPutnQ6cEfhmSFaff5psIJdMK/wU+Ua0tdRVfsanLit04CAhkuRJWEuUnuF/0DotkN4wRcAP71sSTelQnjc5pvumndq5j9qURDW1qL6x+QhjiSv3Bgf1StoBupYc5ywxMNkvm9eV/rVXieJYANwXf8hBk72cJpDgQy1tmgPtSwm3AQxMuHLEShCZV04NCGFlUfiag4tgBEhszkd3dIGrgX6g8DyiZenRTHlEYxVCXxjxR4pFQVGdqEh4o96Zp8gwiW0My/wS+kpJoRivEsQKBgQDOP4PGR3bUkvzUC/1tWn8vsXgWKFzXgQUhO0wyZCDz/YlxYI/3/871VYdxOIpdLPE0Cm4A3gpzsx2mv8zT9ypJFgtxwMYYpOGihi9O2HdxhQf1KZ8xn06efgvndLw33PgxwG+CEsKzJqxQh3lAWKsiqLwt+jZNkdPWe551suZDiwKBgQChIlKSjcM2Aj+tfwPssl4YWc9sXoERkg610yKICTvytpe80WaiL/rAhi3Rpfe8ChPwVxgSyyUuDWppljXyZZDfrjvuqIK6qMhvNcMt9/eimu2biOJS30UPjlMmMq/fo5n8DwQAIP6PVykUJwR6VN4V+Zp5edVh6erEiO+86QpHlQKBgAXBhgbxlaJnG8E7OG5l2SjWlhhgNf8Zra/4vK6nVrgYek58uGfebsMwCOCjCFGDYJPoOpuxlq8V75zou2mq+zYg9F7PV3f2mgl0HzvfFK5JXSh9aeDzpjkMIX7rJhmVBCQ3T6VcqqxfRu3UahDeqKAF/4J0+jD+AeB5gLmeLYjzAoGAePyEc3Xt9UeK+II+OCNVc4M7s2riDodUdEGr0+jbEkr3c9bm8TCFXrlg/eFYOeB3SCac9L+DkyiPlegraW3JcaopZfEw92Zz2MzNWF6vmXAqqHYHOaJbAGslE7FLTOhSXmgH4PDlMkuHlJIXUqMWffmEe2F3TqWMmTUYjQeNqGECgYBynOvlVl9z/8STBwCKQXxFVjodHBUvwz4PEuDI4mdhhhTb8kSndzODrOXpYUwe+kedAVOv+08wYS6SPqsg/MvLykXDJqwAYye4ip1CS04F2PHMKj8CYHghOzsGLbfT6pS6itIa3+y5tDPfJtMy2MoOifuzWqcbK5sSuywiXSaLWw==";
    //私钥公钥根据实际进行替换
    protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
    //protected static final String APP_ID = "10000000000004094684";
    protected static final String APP_ID = "10000000000004097541";

    protected static final String SIGN_TYPE = "RSA2";

    protected static String URL = "http://ip:port/api/mybank/account/enterprise/ofdagreequery/V1";

    public static void main(String[] args) throws Exception {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, SIGN_TYPE, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSSZ");
        try {
//            MybankAccountEnterpriseOfdagreequeryRequestV1Public pub = new MybankAccountEnterpriseOfdagreequeryRequestV1Public();
//            pub.setZoneno("00200");
//            pub.setBrno("00998");
//            pub.setChncode("308");
            ChanCommV11 chancomm = new ChanCommV11();
            chancomm.setChantype("101");
            chancomm.setChanlno("0");
            chancomm.setMac("5445");
            chancomm.setIp("ip:port");
            chancomm.setTermid(1);
            chancomm.setOapp("F-NCMS");
            chancomm.setSerialno("010140001070000777227693329");
            chancomm.setMserialn("010140001070000777227693329");
            InqWork inqWork = new InqWork();
            inqWork.setInitFlag("1");
            inqWork.setRowReq("10");
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
            privateMap.setAgaccno("0200041709212092812");
            privateMap.setAccno("2000417092120928");
            privateMap.setCurrtype("1");
            privateMap.setAppid("123469");
            privateMap.setAgreeno("1267");
            privateMap.setLstmodfd("2003-01-08");
            privateMap.setStatus("1");
            MybankAccountEnterpriseOfdagreequeryRequestV1Biz biz = new MybankAccountEnterpriseOfdagreequeryRequestV1Biz();
            biz.setChanCommV11(chancomm);
            biz.setInfoCommV11(infocomm);
            biz.setInqWork(inqWork);
            biz.setPrivateMap(privateMap);

            MybankAccountEnterpriseOfdagreequeryRequestV1 requestV1 = new MybankAccountEnterpriseOfdagreequeryRequestV1();
            requestV1.setBizContent(biz);
            requestV1.setServiceUrl(URL);
            client.execute(requestV1);
//            MybankAccountEtradeMemberMemberaccountopenResponseV1 responseV1 = client.execute(requestV1);
//            if(responseV1.isSuccess()){
//                System.out.println("success");
//            }else {
//                System.out.println("error");
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
