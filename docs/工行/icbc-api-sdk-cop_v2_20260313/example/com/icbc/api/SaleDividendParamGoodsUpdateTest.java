package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.SaleDividendParamGoodsUpdateRequestBizV1;
import com.icbc.api.request.SaleDividendParamGoodsUpdateRequestV1;
import com.icbc.api.response.SaleDividendParamGoodsUpdateResponseV1;

public class SaleDividendParamGoodsUpdateTest {
    //1、网关公钥
    protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
    //2、appid
    protected static final String APP_ID = "10000000000004097413";

    //3、密钥对认证方式，公钥在API平台登记，此处为私钥
    protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCiUXe9cukoQJy0H9qNqertPv+s/Cin7QmEyDWJGb9sslanJ3vt1t4410SynOdo6LeF57KBfQeW6+4gQQCJIicP+c4e7Q7sJPLe7d1pWNp8fkxB4+BGAcxPiyFm5lFGUf2GOKScz0QcVBVb11PVpW0Ksw8Y3QQ/wQASEqKHe/HsCfJCvw54cK8M1PYAhUNtwbVBCZdYH3t9GMs01WpGS00fp2iPRjp85jvpSNgNzr44sjmFufklkx+/zRVTj69XbB52OEgiPk0O40LfF1FDR7+Z5MgdCW2oZu452v5Pxgk8FOniseZnRcSu9PTRdCS8tKFKt/1aXxTYQlfjh16lWmLzAgMBAAECggEAZ1q8hdMfh1Ps8/y8ayvgvQQ6ROmi8ZpLT1M0U4Ijk5DTAEhx7vW8NjFMOvcRmolkIk1wKmd0Z7L+w2bgcnFesht8FdEYiWDVjQGk1CmRpWe+Is54xdrrP1L2B9Fruv0GE2sJ1hdb5TXdC8fGR+m3zvJJEKnaxMWdx+Q5vU88sfB3oKAI71dVJdssNcpvaJeyOp7gnyyT9c40aywC1HIKuC1za6q5ETZD4rmXUSRGk235dHdFAXDdm4y9uaVKYKo0mQ8BU3aKgYZwsMCO0jdOFzKxFs26wUPgRm8BRyOOfD/+KuK5HExDbWFQX5GMkGv8P0mDglH+gmCRzRCFhmyToQKBgQDTO4icVFSaRc+nt3RQOZGG+kP4bsUY+GWgB9RjqLHGXuk/t5pbk5NavPBLFD5vRLiU22CMpEmB567Y+h52Mw8amcHMJQWXZLG+AjLFfVqVRkDPXk7LG55GehWIdKx2I1SKB0UxcgiP777WeNBjwVg3Xo8GuePaRRTxc0HcroMgiQKBgQDEuBPTD3u3vnFGkwi/KcoO1AKK78ykcHEx6l3UyXg1O+bJK+ZK+vJWbOLKCPCQLb+HuCXb6AgqWasEypik/Wq2Usp3Nqe5ECI3wURMcj+pnj7h+elPbmaprGoqeNqlvCn8iAY+2/SvADwCZCk7NZaEbcM7upf1YsGAnIAjp/wwmwKBgQCw/yqqa9N0bdjhnUx+SK7TnnNEAXVwRfvJy6XBWEKWiUMDXBKgd5k6qCTllHH4hGnyeZx+XM8nZf5R28uYmZInZP1J1R/JStvQ7kCi0TYM7RAzcr+tW1gVkAjpDXLRjysweYUWOIpKKccMKbHcVM9ZngdUyIDPjaFN7JkclLJVmQKBgAJO6qSiXOBGEswyM3sSwXU1FaaNs8gGLb3ZMyqpBokRX97ibfxZP3i2JF8chb4bW1D7Xy5aZaYhkTkoK2xcMOFczVVixcsNHmmrEeMXwBl8at558TvmXrwE8uX2rxIcyB1Xd0NLj25yQUIB7N3t/gslwpGz2c6eTwj0TSid8v6bAoGAfuglsl881QXZfPOk1SElJNZr3dtrigjTUL2SmKVEOJv8mCSyI0MQwr/wPGJHd0K+OJXv4dIcU2Zp86HX3cwyW+NCz7hdE6e8h9z49oRqcJAp4zu5lFgK7G5GTqUOkx+6a+23Eu5Z22HU7XO7KmVQcADPhGHher+yJS/7xlTFkNU=";

    public static void main(String[] args) throws Exception {

        //签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2,
                MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

        SaleDividendParamGoodsUpdateRequestV1 request = new SaleDividendParamGoodsUpdateRequestV1();

        //4、根据测试环境和生产环境替换相应ip和端口
        request.setServiceUrl("http://ip:port/api/sale/dividend/param/goods/update/V1");
        //5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
        SaleDividendParamGoodsUpdateRequestBizV1 bizContent = new SaleDividendParamGoodsUpdateRequestBizV1();
        request.setBizContent(bizContent);
        bizContent.setAppId(APP_ID);
        bizContent.setCorpId("2000000050");
        bizContent.setGoodsNo("100861112");
        bizContent.setGoodsName("中国移动好耶2");
        bizContent.setMerno("00000000000000000000");
        bizContent.setSummary("");
        bizContent.setRebatesrate(55);
        bizContent.setOperation(1);

        SaleDividendParamGoodsUpdateResponseV1 response;
        String msgId = String.valueOf(System.currentTimeMillis());
        try {
            response = client.execute(request, msgId);//msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一
            if(response.getReturnCode()==0){
                // 6、业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
                System.out.println("ReturnCode:"+response.getReturnCode());
                System.out.println("response:" + JSON.toJSONString(response));
            } else {
                // 失败
                System.out.println("response:" + JSON.toJSONString(response));
                System.out.println("ReturnCode:"+response.getReturnCode());
                System.out.println("ReturnMsg:"+response.getReturnMsg());
            }

        }catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }

}
