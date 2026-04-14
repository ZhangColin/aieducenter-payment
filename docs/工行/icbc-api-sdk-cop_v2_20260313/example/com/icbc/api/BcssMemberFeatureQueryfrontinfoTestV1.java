package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.BcssMemberFeatureQueryfrontinfoRequestV1;
import com.icbc.api.response.BcssMemberFeatureQueryfrontinfoResponseV1;
import com.icbc.hsm.utils.encoders.Base64;

import java.text.SimpleDateFormat;
import java.util.Date;


public class BcssMemberFeatureQueryfrontinfoTestV1 {

    protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCiUXe9cukoQJy0H9qNqertPv+s/Cin7QmEyDWJGb9sslanJ3vt1t4410SynOdo6LeF57KBfQeW6+4gQQCJIicP+c4e7Q7sJPLe7d1pWNp8fkxB4+BGAcxPiyFm5lFGUf2GOKScz0QcVBVb11PVpW0Ksw8Y3QQ/wQASEqKHe/HsCfJCvw54cK8M1PYAhUNtwbVBCZdYH3t9GMs01WpGS00fp2iPRjp85jvpSNgNzr44sjmFufklkx+/zRVTj69XbB52OEgiPk0O40LfF1FDR7+Z5MgdCW2oZu452v5Pxgk8FOniseZnRcSu9PTRdCS8tKFKt/1aXxTYQlfjh16lWmLzAgMBAAECggEAZ1q8hdMfh1Ps8/y8ayvgvQQ6ROmi8ZpLT1M0U4Ijk5DTAEhx7vW8NjFMOvcRmolkIk1wKmd0Z7L+w2bgcnFesht8FdEYiWDVjQGk1CmRpWe+Is54xdrrP1L2B9Fruv0GE2sJ1hdb5TXdC8fGR+m3zvJJEKnaxMWdx+Q5vU88sfB3oKAI71dVJdssNcpvaJeyOp7gnyyT9c40aywC1HIKuC1za6q5ETZD4rmXUSRGk235dHdFAXDdm4y9uaVKYKo0mQ8BU3aKgYZwsMCO0jdOFzKxFs26wUPgRm8BRyOOfD/+KuK5HExDbWFQX5GMkGv8P0mDglH+gmCRzRCFhmyToQKBgQDTO4icVFSaRc+nt3RQOZGG+kP4bsUY+GWgB9RjqLHGXuk/t5pbk5NavPBLFD5vRLiU22CMpEmB567Y+h52Mw8amcHMJQWXZLG+AjLFfVqVRkDPXk7LG55GehWIdKx2I1SKB0UxcgiP777WeNBjwVg3Xo8GuePaRRTxc0HcroMgiQKBgQDEuBPTD3u3vnFGkwi/KcoO1AKK78ykcHEx6l3UyXg1O+bJK+ZK+vJWbOLKCPCQLb+HuCXb6AgqWasEypik/Wq2Usp3Nqe5ECI3wURMcj+pnj7h+elPbmaprGoqeNqlvCn8iAY+2/SvADwCZCk7NZaEbcM7upf1YsGAnIAjp/wwmwKBgQCw/yqqa9N0bdjhnUx+SK7TnnNEAXVwRfvJy6XBWEKWiUMDXBKgd5k6qCTllHH4hGnyeZx+XM8nZf5R28uYmZInZP1J1R/JStvQ7kCi0TYM7RAzcr+tW1gVkAjpDXLRjysweYUWOIpKKccMKbHcVM9ZngdUyIDPjaFN7JkclLJVmQKBgAJO6qSiXOBGEswyM3sSwXU1FaaNs8gGLb3ZMyqpBokRX97ibfxZP3i2JF8chb4bW1D7Xy5aZaYhkTkoK2xcMOFczVVixcsNHmmrEeMXwBl8at558TvmXrwE8uX2rxIcyB1Xd0NLj25yQUIB7N3t/gslwpGz2c6eTwj0TSid8v6bAoGAfuglsl881QXZfPOk1SElJNZr3dtrigjTUL2SmKVEOJv8mCSyI0MQwr/wPGJHd0K+OJXv4dIcU2Zp86HX3cwyW+NCz7hdE6e8h9z49oRqcJAp4zu5lFgK7G5GTqUOkx+6a+23Eu5Z22HU7XO7KmVQcADPhGHher+yJS/7xlTFkNU=";

    // APIGW_PUBLIC_KEY
    protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
    //protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的APP私钥-----BIjANBgkqhkiG9w0BAQEFAAOCAQ8A-----请使用正确的APP私钥-----BCgKCAQEAmxWeLbCIEwW66ELDGSbp7/mUcKEMTS//GckKlpO6p9YNU2+t+SrFDhEDaxU/m9IFi/dVt/qxhe0oUgjWktFW+cE72vGtMStBO9LCyIbo8XSMPdNtRG0vQcApu9tNi818ndo1a8H/KyH6bXKGh3D9+OGTDO/K+dq82VQsVsOc9LWWLFLjjAfTy7JtSfEZA6W3AJtqJWzLT6WYbALgxyjx+hFWka8QtK0XmctnofkOo3W2t88QkXPEDvq6tZyTuqt99HLHGdNPmNntcsBeaQF3/NfioPkIIBsJ9KZAIZ/SgKamDWdicHu3T8UVtUpM+sPP8dwLX2TbeHs6jqd4EHGhMQIDAQAB\n";

    protected static final String APP_ID = "10000000000004097413";


    /**
     * APIGW（行外网关） 2020年11月 api地址
     */
    protected static final String API_GW_OUT = "http://ip:port/api";


    public static void main(String[] args) {
        String s="Y29yZW1haWwxMjM=";
        byte[] ts=Base64.decode(s);
        String s1=new String(ts);
        System.out.println(s1);

        String ss="coremail123";
        byte[] ss1=Base64.encode(ss.getBytes());
        System.out.println(new String(ss1));

        try {
            testCancel();
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }

    public static void testCancel() throws IcbcApiException {
        BcssMemberFeatureQueryfrontinfoRequestV1.BcssMemberFeatureQueryfrontinfoRequestBizV1 bizV1 = new BcssMemberFeatureQueryfrontinfoRequestV1.BcssMemberFeatureQueryfrontinfoRequestBizV1();
        bizV1.setCorpId("2000000067");
        bizV1.setManufacturerId("rockchip");
        bizV1.setImeiNo("123");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String format = dateFormat.format(new Date());
        bizV1.setClientTransNo(format);
        bizV1.setTimeStamp("2022-02-22 22:22:22");
        bizV1.setOperType("1");
        bizV1.setDetailInfo("");
        bizV1.setChannelInfo("");
        bizV1.setStartNum("");
        bizV1.setEndNum("");

        BcssMemberFeatureQueryfrontinfoRequestV1 requestV1 = new BcssMemberFeatureQueryfrontinfoRequestV1();
        requestV1.setServiceUrl(API_GW_OUT + "/bcss/member/queryfaceinfo/V1");
        requestV1.setBizContent(bizV1);
        System.out.println(JSON.toJSONString(requestV1));
        System.out.println(JSON.toJSONString(bizV1));
        DefaultIcbcClient client = getIcbcClient();
        BcssMemberFeatureQueryfrontinfoResponseV1 response = client.execute(requestV1);
        System.out.println(JSONObject.toJSONString(response));
    }

    private static DefaultIcbcClient getIcbcClient() {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        return client;
    }

    private static UiIcbcClient getUiClinet() throws IcbcApiException {
        UiIcbcClient client = new UiIcbcClient(APP_ID, MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8);
        return client;
    }

}
