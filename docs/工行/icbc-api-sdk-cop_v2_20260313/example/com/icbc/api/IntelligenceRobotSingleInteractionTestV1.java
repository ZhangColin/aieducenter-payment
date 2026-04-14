package com.icbc.api;


import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.IntelligenceRobotSingleInteractionRequestV1;
import com.icbc.api.response.IntelligenceRobotSingleInteractionResponseV1;

public class IntelligenceRobotSingleInteractionTestV1 {
    public static void main(String[] args) {
        httpsTest();
    }

    public static void httpsTest() {
        /**
         * 网关公钥，功能测试环境
         */
        String publicKey = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
        /**
         * 功能测试环境上新申请APPid
         */
        String appId = "10000000000004097667";
        /**
         *  申请APP时，使用公私钥工具生成的公私钥，公钥配置在API平台上APP信息里，私钥写在代码里，由API平台做公私钥匹配校验
         */
        String privateKey = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCMSMR4sLCAAjMr/o/F5bFA20dssV3rPHs6Ka529k8hvGuh+cB6/TXV0eLNtcKwo7ZEoEBExmjI0k4aA5XN7jUMDIgN+bZ4Vyfrg3cgA5zh+0ymD4QtN3zZMtD2sUtWP248u76VaXcbHGBv7rKoP31IjUuyCjPpQu29JVwuMcJtrfa7ntDt+zXamqquAYi5mMLRzc+/2d61TA0xoyNXeUR6DnEq8v/ama2EuyZlgO60tRln9TonPuLAguw5H8Zvy3RgY/OzW0zSxoGba5bcZmHafuUpTdXEx24yFc9dyZuwpuoOjfGKiIk06B4LgAmLlmWT/BDH/mRRbm90ABduwSWbAgMBAAECggEANbrFLWiXIWA2UzpbvLM90ZZq49GHJEr085D1VnD+W/B71P0AYNrMnMmXsaxVFiyN6fjqxKytg4UxDruiy83ZmXTPQ3MKFUtkacrffG6FzvPeJpg4ysy+z72r1WCQd0cyJKbP1xAe405aDWUCHni6tmgPvvAzc7sDrXc6uCKs0i7G7YSGku8wkztl+4MMl33o5ZsZg2mvvbb9Mij/na6zr6JwRZRlUB/gXemWfb6JQo7RXLMEvEX0b8qnE+xZqCuIfiE4AnI4goxflOJQw58th7FoOKhjnihk3IexT4qDj+Ww5ZGsK0R3te3pT9AvneEZu5jyivF9E3F6awR1GesYiQKBgQC/uMW3hEc+VN1FY7CaImLDNbXnYpx4VQPE4ETifqGcRyS4nFvGUgKY1OaCbnvwnb0hYMNQmbzB3Si0cdzbrOuDox3ivbQnNqXlkhFBc+NonV5ao9kvCdCnwpxK0j84VcwLM6JYEgR1dRqc7iIq+frAeu7IDTHFDkNIQymHVovVtwKBgQC7US4tPBNz+VqJWqob9ago63h9M0JyXaTEHpi5rOm3IGFnnoEVkToDA7S8CnfkY0Z1qWxgqVqsGrCvQvfrW48bsJ6z27HTVA29jBJntJ2Z4Z/IO8syClIYT+n4p++Rvrqszpgn1h0uhFuaCxsStu2jEkXgNA1vbkNV+lEPNWKPPQKBgQCpAwxZoaTkkA+YUi+nUVTbXggD+OyTL+7oqgRP3u49RIJctIo7JKThch3bMebU0VmfiRvlfAHorTxrBk164s+j9jIsZqvgPc61uLS5jJ4W5VP+d57IVtC4c1kwpHPTB9+eqGAfX5LKsPdLDKrWVBT3hefmN6t+Wfp6Q6rigis3tQKBgFXvLlIOR5ZjBQgSkgv1rUnojQRTKd6x208mAkbcbvRWZJlW/D1avNR5NMc3vj0G1mJQcan+x9xA+mHNRVOyU3sOkiBNX81luFAHHMJ69/qKqfUAiX3RgkwpDVvIH34rlBSHSwKrlgOrMSMAiVESnv66+VecSbWfTkD5b4qcoTS5AoGAeKO2VDAU1kmCKlCnn8rqKXmo/MTho7IjjZ27xYX1ClXgwbkk9H7Bm3lSyzZk1ZFQ5mBwgtw0dN+3hdDOU8l02Q1ekYwfGnEex7OmJHO6NifEaxF1F76mDGxuSYsIF3jpZi9iner7Q+qWQMsbT4VQFfWwSTBpgJxBJr4or5nGIYM=";

        // sptranscode
        final String SPTRANSCODE = "V0022";
        // schannel
        final String SCHANNEL = "38";
        // sserialno
        final String SSERIALNO = "JQRGXZ000003";
        // sinquestion
        final String SINQUESTION = "信用卡";


        //签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
        DefaultIcbcClient client = new DefaultIcbcClient(appId, IcbcConstants.SIGN_TYPE_RSA2, privateKey, publicKey);
        IntelligenceRobotSingleInteractionRequestV1 request = new IntelligenceRobotSingleInteractionRequestV1();

        //根据测试环境和生产环境替换相应ip和端口
        request.setServiceUrl("http://ip:port/api/intelligence/robot/single/interaction/V1");
        //请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
        IntelligenceRobotSingleInteractionRequestV1.IntelligenceRobotSingleInteractionRequestV1Biz bizContent = new IntelligenceRobotSingleInteractionRequestV1.IntelligenceRobotSingleInteractionRequestV1Biz();
        //写死V0022
        bizContent.setSpTransCode(SPTRANSCODE);
        //来源渠道
        bizContent.setsChannel(SCHANNEL);
        // 会话标识，每次生成不一样的
        bizContent.setsSerialNo(SSERIALNO);
        //客户问题
        bizContent.setsInQuestion(SINQUESTION);
        request.setBizContent(bizContent);
        System.out.println("上送报文");
        System.out.println(JSONObject.toJSONString(bizContent));
        IntelligenceRobotSingleInteractionResponseV1 response;
        try {
            //msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一，可用uuid
            response = client.execute(request, "11");
            if (response.isSuccess()) {
                // 业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
                System.out.println("ReturnCode:" + response.getReturnCode());
                System.out.println("response:" + response);
            } else {
                // 失败
                System.out.println("ReturnCode:" + response.getReturnCode());
                System.out.println("ReturnMsg:" + response.getReturnMsg());
            }
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }


    }
}
