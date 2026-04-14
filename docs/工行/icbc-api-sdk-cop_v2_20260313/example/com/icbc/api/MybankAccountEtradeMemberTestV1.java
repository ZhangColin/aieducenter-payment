package com.icbc.api;

import com.icbc.api.request.MybankAccountEtradeMemberMemberaccountopenRequestV1;
import com.icbc.api.request.MybankAccountEtradeMemberMemberaccountopenRequestV1.MybankAccountEtradeMemberMemberaccountopenRequestV1Biz;
import com.icbc.api.request.MybankAccountEtradeMemberMemberaccountopenRequestV1.MybankAccountEtradeMemberMemberaccountopenRequestV1Business;
import com.icbc.api.request.MybankAccountEtradeMemberMemberaccountopenRequestV1.MybankAccountEtradeMemberMemberaccountopenRequestV1Ewcchancomm;
import com.icbc.api.request.MybankAccountEtradeMemberMemberaccountopenRequestV1.MybankAccountEtradeMemberMemberaccountopenRequestV1Nwcctrlcomm;
import com.icbc.api.request.MybankAccountEtradeMemberMemberaccountopenRequestV1.MybankAccountEtradeMemberMemberaccountopenRequestV1Nwcinfocomm;
import com.icbc.api.request.MybankAccountEtradeMemberMemberaccountopenRequestV1.MybankAccountEtradeMemberMemberaccountopenRequestV1Public;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MybankAccountEtradeMemberTestV1 {

    protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCQJkp7Sqh/k5zk8zLufDzJ4kL/DyRN7Vn4oLoqlaAnq21Zq7Dhlrfko4+wKqK7enf8Fwt5xNkfXJbktoTTg0C5v5V21yjrBdoV1masnqq5frkwU2QTTB5lZXxeYPze2+qv7Q+vjV8IISr2N2H2YrdUZZYjboWCG0AAaf17C1T1SjdpfUn2YTtqL2G7UhfDGHqyvmYUzeqSaF0kxrXe9EDWZPwvP7fxKqgvQAkoBOYUkGtRZjQFOjoWieFaNCD8C61noNvv1nJfdn3YsCcQEHhlO0ra3qkF1+t515RFsl+q7U4pZH/Lj5bdp5MWI88qLASrmGIsJeMP/Ynu8Dk16tHbAgMBAAECggEARRtGW+NH8pmW/yAky7aeAf9KNTNSKVXf61VKyv2eoUYLD5FDJHN4R9GuIjvedljZSu7VXpRjryhZYt6pwSlMj4BsrGOsRzgv/IYkBR+wI2vAZo2kDahRbjUAMdL3h/QQiDTzbyssYx5blVypx1A+KeTl3KiwstZTkZ8+mY2uNkWcmgPinPDaYrEldpaj087shm3b+yvkPwaftxY8ewP2St7WbjruZMhm45RFwTPrMW3YPHJCjfUQK3fwXDmZII6BeJoGimM6VH4V8K7+L/rKo3TulOXrVq00tJd3mVErKTpOMjgE7XkWOdjX3lEKZc6S64gcVZ5bV6vOTJvR+9xz8QKBgQDPm0ACkW/zUlQXuClAhUdXK3Zw6vQ2ZMuQ9U1rDaVGQlonF724hTnXr/AyVPEEwO+kBLTDn0AP9Ldk9yUQhpDSPK72fQgu7kGOeQRy+zgRCUuZXn+vbSgodS6xCxOXdpPx+yyr/kP4ZiMALYwL5r7PQ+O6wE6WihMGPQ6fkHZlIwKBgQCxwE1sSAvPAqrp3m2EL5e5yBpZry8sGaSY55nrjMXKHaCWyLTHW6eVXi5ZMh4q9OydNHkxn/gcOjdb1ajvaNybT76+DME0YQNw4xTHwDJT1auC1QZMj9/MhTVS6hzFCjRCUqdlVht1gfqwS/+WCRJBY811F/oRBLzL7lqwolz36QKBgAdLnlk4wSVczEW1HAX/aP1gMp5PM1vB+QFUDW9ooFkytbZP+EPGsryomxk5sOqI2SL7Y1rIetjUsakjojLNd8NbjI5u4F2906HtEMH1oAfBOt3d20DP5ixdydFA4WReL16+g9hbbAoLlMlJ0HUOm7/u087ZOSnDGl4KwTvMOMzfAoGBAKTTIDgPWrvbymbQ9ZbElxoT/rM5KjHnmWScJnNw9xuje302ocPXLr/oNGxLeAP3V82ykcs9flGWfizvJBbUkvSF7ubGZwXOC+XK/EL56vEoSkEojg3CwBEjWL4JDyJ7gtgZUtY6EX5WSALghiE3SjFOfX9d+2l6jiNAY+I0vKa5AoGAGZkr7ByB16nzgtyOm43SZPUDOAQSAFW3E8uTHUZ6AJ83XQLhizN8nx7SENx15iRYtD5qAcV9hHBgGoKq/dlK5cMeuixboFBDM+H1O77zuSlbsYxzrdhePnK5q6zVhbAudPRd3/XqYqdTB31FWFIZNX31yM1ZdqI2OajMf3lXcMA=";

    protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
    //protected static final String APP_ID = "10000000000004094684";
    protected static final String APP_ID = "";

    protected static final String SIGN_TYPE = "RSA2";

    protected static String URL = "http://ip:port/api/mybank/account/etrade/member/memberaccountopen/V1";

    public static void main(String[] args) throws Exception {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, SIGN_TYPE, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSSZ");
        try {
            MybankAccountEtradeMemberMemberaccountopenRequestV1Public pub = new MybankAccountEtradeMemberMemberaccountopenRequestV1Public();
            pub.setZoneno("00200");
            pub.setBrno("00998");
            pub.setChncode("308");
            MybankAccountEtradeMemberMemberaccountopenRequestV1Ewcchancomm ewcchancomm = new MybankAccountEtradeMemberMemberaccountopenRequestV1Ewcchancomm();
            ewcchancomm.setSevlevel("1");
            ewcchancomm.setSerialno(sdf.format(date));
            ewcchancomm.setPreflag("0");
            MybankAccountEtradeMemberMemberaccountopenRequestV1Nwcctrlcomm ctrlcomm = new MybankAccountEtradeMemberMemberaccountopenRequestV1Nwcctrlcomm();
            ctrlcomm.setCkusrF("0");
            ctrlcomm.setCtrlFlag1("0");
            ctrlcomm.setCtrlFlag6("0");
            MybankAccountEtradeMemberMemberaccountopenRequestV1Nwcinfocomm infocomm = new MybankAccountEtradeMemberMemberaccountopenRequestV1Nwcinfocomm();
            infocomm.setTrxcode("3710");
            infocomm.setWorkdate("2020-06-03");
            infocomm.setWorktime("10:10:10");
            infocomm.setServface("140");
            infocomm.setOperflag("1");
            MybankAccountEtradeMemberMemberaccountopenRequestV1Business business = new MybankAccountEtradeMemberMemberaccountopenRequestV1Business();
            business.setMktno("0005502");
            business.setMemnoNew("ZCCZWUZZ6");
            business.setMemnoOld("02005291");
            business.setMemtype("1");
            business.setOpflag("1");
            business.setBtflag("0");
            business.setDesignateflag("1");
            business.setMemberzoneno("00200");
            business.setMemberbrno("00998");
            MybankAccountEtradeMemberMemberaccountopenRequestV1Biz biz = new MybankAccountEtradeMemberMemberaccountopenRequestV1Biz();
            biz.setPub(pub);
            biz.setEwcchancomm(ewcchancomm);
            biz.setNwcctrlcomm(ctrlcomm);
            biz.setNwcinfocomm(infocomm);
            biz.setBusiness(business);

            MybankAccountEtradeMemberMemberaccountopenRequestV1 requestV1 = new MybankAccountEtradeMemberMemberaccountopenRequestV1();
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
