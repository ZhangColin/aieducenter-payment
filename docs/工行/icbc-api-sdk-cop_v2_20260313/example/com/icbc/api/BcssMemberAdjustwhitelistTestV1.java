package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.BcssMemberAdjustwhitelistRequestV1;
import com.icbc.api.response.BcssMemberAdjustwhitelistResponseV1;
import com.icbc.api.utils.IcbcEncrypt;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.*;


public class BcssMemberAdjustwhitelistTestV1 {
	
	private static final Log logger = LogFactory.getLog(BcssMemberAdjustwhitelistTestV1.class);

    protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCiUXe9cukoQJy0H9qNqertPv+s/Cin7QmEyDWJGb9sslanJ3vt1t4410SynOdo6LeF57KBfQeW6+4gQQCJIicP+c4e7Q7sJPLe7d1pWNp8fkxB4+BGAcxPiyFm5lFGUf2GOKScz0QcVBVb11PVpW0Ksw8Y3QQ/wQASEqKHe/HsCfJCvw54cK8M1PYAhUNtwbVBCZdYH3t9GMs01WpGS00fp2iPRjp85jvpSNgNzr44sjmFufklkx+/zRVTj69XbB52OEgiPk0O40LfF1FDR7+Z5MgdCW2oZu452v5Pxgk8FOniseZnRcSu9PTRdCS8tKFKt/1aXxTYQlfjh16lWmLzAgMBAAECggEAZ1q8hdMfh1Ps8/y8ayvgvQQ6ROmi8ZpLT1M0U4Ijk5DTAEhx7vW8NjFMOvcRmolkIk1wKmd0Z7L+w2bgcnFesht8FdEYiWDVjQGk1CmRpWe+Is54xdrrP1L2B9Fruv0GE2sJ1hdb5TXdC8fGR+m3zvJJEKnaxMWdx+Q5vU88sfB3oKAI71dVJdssNcpvaJeyOp7gnyyT9c40aywC1HIKuC1za6q5ETZD4rmXUSRGk235dHdFAXDdm4y9uaVKYKo0mQ8BU3aKgYZwsMCO0jdOFzKxFs26wUPgRm8BRyOOfD/+KuK5HExDbWFQX5GMkGv8P0mDglH+gmCRzRCFhmyToQKBgQDTO4icVFSaRc+nt3RQOZGG+kP4bsUY+GWgB9RjqLHGXuk/t5pbk5NavPBLFD5vRLiU22CMpEmB567Y+h52Mw8amcHMJQWXZLG+AjLFfVqVRkDPXk7LG55GehWIdKx2I1SKB0UxcgiP777WeNBjwVg3Xo8GuePaRRTxc0HcroMgiQKBgQDEuBPTD3u3vnFGkwi/KcoO1AKK78ykcHEx6l3UyXg1O+bJK+ZK+vJWbOLKCPCQLb+HuCXb6AgqWasEypik/Wq2Usp3Nqe5ECI3wURMcj+pnj7h+elPbmaprGoqeNqlvCn8iAY+2/SvADwCZCk7NZaEbcM7upf1YsGAnIAjp/wwmwKBgQCw/yqqa9N0bdjhnUx+SK7TnnNEAXVwRfvJy6XBWEKWiUMDXBKgd5k6qCTllHH4hGnyeZx+XM8nZf5R28uYmZInZP1J1R/JStvQ7kCi0TYM7RAzcr+tW1gVkAjpDXLRjysweYUWOIpKKccMKbHcVM9ZngdUyIDPjaFN7JkclLJVmQKBgAJO6qSiXOBGEswyM3sSwXU1FaaNs8gGLb3ZMyqpBokRX97ibfxZP3i2JF8chb4bW1D7Xy5aZaYhkTkoK2xcMOFczVVixcsNHmmrEeMXwBl8at558TvmXrwE8uX2rxIcyB1Xd0NLj25yQUIB7N3t/gslwpGz2c6eTwj0TSid8v6bAoGAfuglsl881QXZfPOk1SElJNZr3dtrigjTUL2SmKVEOJv8mCSyI0MQwr/wPGJHd0K+OJXv4dIcU2Zp86HX3cwyW+NCz7hdE6e8h9z49oRqcJAp4zu5lFgK7G5GTqUOkx+6a+23Eu5Z22HU7XO7KmVQcADPhGHher+yJS/7xlTFkNU=";

    // APIGW_PUBLIC_KEY
    protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

    // APP_ID
    protected static final String APP_ID = "10000000000004097413";
    protected static final String AESKEY = "645r6z1azEItiR+ghwF4+g==";

    protected static final String RSA = "RSA";
    protected static final String RSA2 = "RSA2";

    public static void main(String[] args) throws Exception {
    	BcssMemberAdjustwhitelistTestV1 a = new BcssMemberAdjustwhitelistTestV1();
    	String s = "NwE9vKf/kiY13LCkxkH9wXZ7035RTK0QE1V62ft04WuvNanRlAb+qIDoZbKSk/Oj";
        try {
            System.out.println(IcbcEncrypt.decryptContent(s, "AES", AESKEY, "UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
//        a.test_cop();
    }

    public void test_cop() throws IcbcApiException {
        BcssMemberAdjustwhitelistRequestV1 request = new BcssMemberAdjustwhitelistRequestV1();
        request.setServiceUrl("http://ip:port/api/bcss/member/adjustwhitelist/V1");
        BcssMemberAdjustwhitelistRequestV1.BcssAdjustMemberWhiteListRequestBizV1 bizContent = new BcssMemberAdjustwhitelistRequestV1.BcssAdjustMemberWhiteListRequestBizV1();
        //{"chnsName":"api测试","mobile":"12824342323","iD_TYPE":"1","custCode":"12124","sex":"2","orgId":"9999999999","deptId":"0000000016","cordata5":1,
        // "cordata3":"19","staffCustType":"123456","staffCustTypeDate":"2022-10-28","cordata1":"15327422","staffType":"xy"}
        bizContent.setCorpId("2000000067");
        bizContent.setTimeStamp("2022-12-22 10:44:38");
        bizContent.setClientTransNo("20221222123456125345");
        List<BcssMemberAdjustwhitelistRequestV1.BcssAdjustMemberWhiteListRequestBizV1.User> users = new ArrayList<>();
        bizContent.setUsers(users);

        /*for (int i = 10; i < 20; i++) {
            BcssAdjustMemberWhiteListRequestV1.BcssAdjustMemberWhiteListRequestBizV1.User user = new BcssAdjustMemberWhiteListRequestV1.BcssAdjustMemberWhiteListRequestBizV1.User();
            String userInfo = String.format("{\"custSort\":\"1\",\"custCode\":\"123123%s\", \"name\":\"aa%s\" , \"mobile\":\"156575727%s\",\"operType\":\"2\"}", i, i, i);
            String string = JSON.toJSONString(encryptAesContentByKey1(AESKEY, userInfo));
            user.setUserInfo(string);

            user.setSeqNo(String.valueOf(i));
            user.setName("张三");
            user.setCustType("19");
            user.setMemCardType("3");
            user.setSex("1");
            user.setOrgId("9999999999");
            user.setDeptId("0000000016");
            user.setStaffCustType("3");
            user.setStaffCustTypeDate("2023-12-28");
            user.setStaffNo("3" + i);
            user.setStaffType("xy");
            users.add(user);
        }
        for (int i = 20; i < 30; i++) {
            BcssAdjustMemberWhiteListRequestV1.BcssAdjustMemberWhiteListRequestBizV1.User user = new BcssAdjustMemberWhiteListRequestV1.BcssAdjustMemberWhiteListRequestBizV1.User();
            String userInfo = String.format("{\"custSort\":\"1\",\"custCode\":\"123123%s\", \"name\":\"aa%s\" , \"mobile\":\"156575727%s\",\"operType\":\"3\"}", i, i, i);
            String string = JSON.toJSONString(encryptAesContentByKey1(AESKEY, userInfo));
            user.setUserInfo(string);

            user.setSeqNo(String.valueOf(i));
//            user.setName("张三");
//            user.setCustType("19");
//            user.setMemCardType("3");
//            user.setSex("1");
//            user.setOrgId("9999999999");
//            user.setDeptId("0000000016");
//            user.setStaffCustType("3");
//            user.setStaffCustTypeDate("2025-12-28");
//            user.setStaffNo("3321421" + i);
//            user.setStaffType("xy");
            users.add(user);
        }*/
        BcssMemberAdjustwhitelistRequestV1.BcssAdjustMemberWhiteListRequestBizV1.User user = new BcssMemberAdjustwhitelistRequestV1.BcssAdjustMemberWhiteListRequestBizV1.User();
        String userInfo = "{\"custSort\":\"1\",\"custCode\":\"1231231000\", \"name\":\"牛牛1\" , \"mobile\":\"\",\"operType\":\"3\"}";
        String string = JSON.toJSONString(encryptAesContentByKey1(AESKEY, userInfo));
        user.setUserInfo(string);

        user.setSeqNo(String.valueOf(1));
//        user.setName("张三");
        user.setCustType("测试身份");
        user.setMemCardType("1");
//        user.setSex("");
//        user.setOrgId("9999999999");
//        user.setDeptId("0000000016");
//        user.setStaffCustType("");
//        user.setStaffCustTypeDate("");
//        user.setStaffNo("");
//        user.setStaffType("");
        users.add(user);

        BcssMemberAdjustwhitelistRequestV1.BcssAdjustMemberWhiteListRequestBizV1.User user1 = new BcssMemberAdjustwhitelistRequestV1.BcssAdjustMemberWhiteListRequestBizV1.User();
        String userInfo1 = "{\"custSort\":\"1\",\"custCode\":\"1231231000\", \"name\":\"牛牛2\" , \"mobile\":\"\",\"operType\":\"1\"}";
        String string1 = JSON.toJSONString(encryptAesContentByKey1(AESKEY, userInfo1));
        user1.setUserInfo(string1);

        user1.setSeqNo(String.valueOf(2));
//        user1.setName("张三");
        user1.setCustType("19");
        user1.setMemCardType("3");
//        user1.setSex("");
//        user1.setOrgId("9999999999");
//        user1.setDeptId("0000000016");
//        user1.setStaffCustType("");
//        user1.setStaffCustTypeDate("");
//        user1.setStaffNo("");
//        user1.setStaffType("");
        users.add(user1);

//        logger.info(JSON.toJSONString(bizContent));
        System.out.println(JSON.toJSONString(bizContent));
        request.setBizContent(bizContent);
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,RSA2,MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        BcssMemberAdjustwhitelistResponseV1 result =  client.execute(request);
        logger.info(JSON.toJSONString(result));
        System.out.println(JSON.toJSONString(result));

    }

    public String encryptAesContentByKey1(String aesKey, String content) {
        String result = null;
        try {
            result = IcbcEncrypt.encryptContent(content, "AES", aesKey, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return result;
    }
}
