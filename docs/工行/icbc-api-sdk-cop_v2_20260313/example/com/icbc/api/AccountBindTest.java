package com.icbc.api;

import com.icbc.api.request.AccountBillInfoQueryRequestV2;
import com.icbc.api.request.MybankAccountDigitalwalletAccountbindRequestV1;
import com.icbc.api.response.AccountBillInfoQueryResponseV2;
import com.icbc.api.response.MybankAccountDigitalwalletAccountbindResponseV1;

public class AccountBindTest {
    //1、网关公钥
    protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

    //2、appid
    protected static final String APP_ID = "10000000000004094584";

    //3、密钥对认证方式，公钥在API平台登记，此处为私钥
    protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----CdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALAWAcPiTMRU906PTdy0ozspX7XptZnkEw2C8R64RDB9BiRFXAj0cU4aTA1MyfmGIlceeVdgJf7OnmvpHnYxjQ7sGxMItPtodrGwA2y8j0AEbHc5pNWU8Hn0zoY9smHS5e+KjSbWv+VNbdnrRFTpDeiJ3+s2E/cKI2CDRbo7cAarAgMBAAECgYABiA933q4APyTvf/uTYdbRmuiEMoYr0nn/8hWayMt/CHdXNWs5gLbDkSL8MqDHFM2TqGYxxlpOPwnNsndbW874QIEKmtH/SSHuVUJSPyDW4B6MazA+/e6Hy0TZg2VAYwkB1IwGJox+OyfWzmbqpQGgs3FvuH9q25cDxkWntWbDcQJBAP2RDXlqx7UKsLfM17uu+ol9UvpdGoNEed+5cpScjFcsB0XzdVdCpp7JLlxR+UZNwr9Wf1V6FbD2kDflqZRBuV8CQQCxxpq7CJUaLHfm2kjmVtaQwDDw1ZKRb/Dm+5MZ67bQbvbXFHCRKkGI4qqNRlKwGhqIAUN8Ynp+9WhrEe0lnxo1AkEA0flSDR9tbPADUtDgPN0zPrN3CTgcAmOsAKXSylmwpWciRrzKiI366DZ0m6KOJ7ew8z0viJrmZ3pmBsO537llRQJAZLrRxZRRV6lGrwmUMN+XaCFeGbgJ+lphN5/oc9F5npShTLEKL1awF23HkZD9HUdNLS76HCp4miNXbQOVSbHi2QJAUw7KSaWENXbCl5c7M43ESo9paHHXHT+/5bmzebq2eoBofn+IFsyJB8Lz5L7WciDK7WvrGC2JEbqwpFhWwCOl/w==";
    public static void main(String[] args) throws Exception {

        //签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

        MybankAccountDigitalwalletAccountbindRequestV1 request = new MybankAccountDigitalwalletAccountbindRequestV1();

        //4、根据测试环境和生产环境替换相应ip和端口
        request.setServiceUrl("http://IP:PORT/api/mybank/account/digitalwallet/accountbind/V1");

        //5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
        MybankAccountDigitalwalletAccountbindRequestV1.MybankAccountDigitalwalletAccountbindRequestBizV1 bizContent = new MybankAccountDigitalwalletAccountbindRequestV1.MybankAccountDigitalwalletAccountbindRequestBizV1();
        
        bizContent.setCooperator_no("1234567890");
        bizContent.setAgent_cooperator_no("1122334455");
        bizContent.setSerial_no("002000000201234567891200001");
        bizContent.setRelated_serial_no("");
        bizContent.setOriginal_serial_no("");
        bizContent.setWork_date("2020-12-12");
        bizContent.setWork_time("12:12:12");
        bizContent.setMac("xx:xx:xx:xx:xx:xx");
        bizContent.setIP("xxxx:xxxx:xxxx:xxxx:xxxx:xxxx:xxxx:xxxx");

      //  bizContent.setOperate_type("99");
        bizContent.setWallet_id("0022000575880400");
        bizContent.setAccno("002250159286001122");
        bizContent.setAccno_name("珠海市古春堂凉茶有限公司");
        bizContent.setBank_code("C1010211000012");
        bizContent.setAccno_permit(0);
        bizContent.setAcc_type(1);
        bizContent.setCurr_type("1");
        request.setBizContent(bizContent);
        request.setNeedEncrypt(true);

        MybankAccountDigitalwalletAccountbindResponseV1 response;
        try {
            response = client.execute(request, "msgId");//msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一
            if (response.isSuccess()) {
                //6、业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
                System.out.println("ReturnCode:"+response.getReturnCode());
                System.out.println("response:" + response);
            } else {
                //失败
                System.out.println("ReturnCode:"+response.getReturnCode());
                System.out.println("ReturnMsg:"+response.getReturnMsg());
            }
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }


}


