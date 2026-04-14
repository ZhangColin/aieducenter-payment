package com.icbc.apip;


import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcConstants;
import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;

public class CustomerServiceCCISCSMTokenandSessionInfoTestV1 {
    public static void main(String[] args) {
        httpsTest();
    }

    public static void httpsTest() {
        /**
         * 网关公钥，测试环境
         */
        String publicKey = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
        /**
         * 测试环境上新申请APPid
         */
        String appId = "10000000000000323533";
        /**
         *  申请APP时，使用公私钥工具生成的公私钥，公钥配置在API平台上APP信息里，私钥写在代码里，由API平台做公私钥匹配校验
         */
        String privateKey = "-----请使用正确的APP私钥-----EvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCIO+/Qc2hPccFiKcMEzjpzlDGXQeC4X/nVtPIcC6XzCYzRr7Q1VsE9yk8zj+J5mTOUDBjJmL2XEDYIqz6WwYsM0PokpG8OL9NVnDPVoRTiQeseHFz5+4j+xgaGDB+6RnPXt44sZgnPYBiIX+rfIq1aZxW99OVVnzpN62/5snLD89j2v7/J+dG0+2TDT9XdYv+XlpCkl0ZeIiLUEvx9lqm2ZgQP38sQyk60IFDBMb8UdaIYNVNAd/aq2t2Zydh1UcMP3mJbhWOUkrpB3+Kky3xoLV2ixBUMgU8EpOz191jL6bAw/yEOgCo8MrAnjtpOdlGZht/RYBTZwid7mp+CgJTpAgMBAAECggEATmuaDzbvm5OSywvZG8IOuYtbxIMCdX+h09e16lj2LOGZcvaeWHnZANWdI8Q2jOKYYydXeWx37iZBxFm2zJcakMUWyNLyrorvLv+cguOXnuptCkcvaiLV6DpbdPwzo0l803bw2zPdf8cEODRmgg13FmvygwceoFvo9wOI0IZdM9gm9JZJiSCDOkbj6XGa2Gb053cszUC1Exw8XJXXF1+AnPO7UAV3Lv1dUaqqzCG2Zo/K/3rtKgmyHGwmahYYZO0xsmBrwFw60uzFy5ic9H1kOmN8UuRM9GCLk7Hr0to8GnNnvbLyXgd5akiNACeNSs5e4+Gn9N2CTSmJa5f0rmVtAQKBgQDe3OWygQsIwwKkfSVqfAg+fFG17S0r3lZvAtOP6cYBo/9t/75/dKEUANwgVyqVUNkW6nOztX/vviTOnjTYuNRDDYocklvjrSdW2AA35d3vBx8CzN5TOzyGdOJGFKsAFSAgruMg2E8M11C5pmj57C20mxjaN6Yv2HMI2JNQ/3QPIQKBgQCcfZVPM2hKsarObWRLfHxwDWCpT9SLtCPzVAurfQ7IMYhG0X9K/wWYG9FOfam+fykJREJSOVW2fyuqvTO9TTsHBLzGfxR7dwUNIm2t+2UrjenEVYs0121LvvXGEeDib+CjZ2ilzzWXLSgbe653xqumRL+2VkjCD0hNePrw2Y40yQKBgEILuM7Dy19XW4sfw1zhURgjg5ukpERh6b+QWN5Ha3TFA0e4CN6A09O3xYSYEHibsxpFdPCHE1kbQpEw9XxcmhsdAhchkLXSdNwZu0bVioXx2FDeIe0Mmw1FymD2cw7dM947tfff4t1tWfEKzgVy5sClTiItZ8S6Iq02ccIJUn3BAoGADxpbr6IhfZv6BSQvms1v4QyZeJ24wKbpFVHWMn3GUHHfWh5b76v/KTf+Elo0aKszFa7dy7u21v8dAsRx2uI6QSom4e1f166b8TGSbkkl8XcnyYgMAFSOOtI6PBx7nL75aivPwLjnzihQh55/LxYgeNKHwhw/t2LsMnXrmhUVEWkCgYBvoaoPXADVf96mXTzgwUW6nRq6us1vkm4ZW6btxBTwj/x/zouhwF8h7wzXFkmK1z1ewLFCpB/BAdbVrXAiann/fxF9Uui2lhToBYMSOnZS8z24y+AYyTtpquaaq8n+LtXOQC/kUyT4cWDyfHJaNzkDuxWqkTRNn4jGYXmsv0euyw==";

        // CISNO
        final String CISNO = "123456";
        // schannel
        final String CHANNEL = "C00018";
        // UNIONID
        final String UNIONID = "123456";
        // ENTRANCE 入口
        final String ENTRANCE = "1";
        // OPENID
        final String OPENID = "123456";


        //签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
        DefaultIcbcClient client = new DefaultIcbcClient(appId, IcbcConstants.SIGN_TYPE_RSA2, privateKey, publicKey);
        CustomerServiceCCISCSMTokenandSessionInfoRequestV1 request = new CustomerServiceCCISCSMTokenandSessionInfoRequestV1();

        //根据测试环境和生产环境替换相应ip和端口
        request.setServiceUrl("http://ip:port/api/CustomerService/CCIS/CSMTokenandSessionInfo/V1");
        //请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
        CustomerServiceCCISCSMTokenandSessionInfoRequestV1.CustomerServiceCCISCSMTokenandSessionInfoRequestV1Biz bizContent = new CustomerServiceCCISCSMTokenandSessionInfoRequestV1.CustomerServiceCCISCSMTokenandSessionInfoRequestV1Biz();
        //客编
        bizContent.setCisno(CISNO);
        //来源渠道
        bizContent.setChannel(CHANNEL);
        // unionid
        bizContent.setUnionId(UNIONID);
        //入口
        bizContent.setEntrance(ENTRANCE);
        bizContent.setOpenid(OPENID);
        request.setBizContent(bizContent);
        System.out.println("上送报文");
        System.out.println(JSONObject.toJSONString(bizContent));
        CustomerServiceCCISCSMTokenandSessionInfoResponseV1 response;
        try {
            //msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一，可用uuid
            response = client.execute(request, "11");
            if (response.isSuccess()) {
                // 业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
                System.out.println("TranErrorCode:" + response.getReturnCode());
                System.out.println("response:" + response);
            } else {
                // 失败
                System.out.println("TranErrorCode:" + response.getReturnCode());
                System.out.println("TranErrorMsg:" + response.getReturnMsg());
            }
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }


    }
}
