package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.BcssGuardUserAuthRequestV1;
import com.icbc.api.response.BcssGuardUserAuthResponseV1;
import com.icbc.hsm.utils.encoders.Hex;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.security.Key;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

public class BcssGuardUserAuthTestV1 {
    private static final Logger LOGGER = Logger.getLogger(BcssGuardUserAuthTestV1.class.getName());
    //     MY_PRIVATE_KEY
    protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDDfGldwFbxb8AOvZxLcblZLJZk5N3U8xvgOHXo3ZoXzRl4LWGNl/E4JMGerCiOXICwDm9TyD+vNkS0lha0Aw5/g3YvVnpOfCFGTqWR1ovI/gB3fL+1QlxhUGGtU1NrsS1zuFRfDz06J3XZc3qpIfHSRvFQs2rytw5SuemgRCb3p6ncY+sya1qc8iEujbD6kPi5reb1vL5lriQpI1h9e8RiMIA2jSXltlMQ8EGHQ2nMIcvnECMdi54LG8DB79HhYwIdmOAo8LmL61IB95Rc1dRunI6IRlMjucn/V0YlMhMgu+S76bBzETlNFBYJ4kSeVVQ9xlWJsTtNlITq9kF1MYb5AgMBAAECggEABXDQ/er8gHu7vK1KTE89+V1kUiqMggDCJdtsbK6+OytaKh4ZOPehnw5m1SO1fUDV1BCSyKIqI5Y/E0eaGih7ntDp2SdvAAA0bOCDeyudeFqZaLnwG/tc1tiF320SRU0JFs9qibrC2Xt/rC2qnEp1kISo8rYLo/W0UFkcR3meHAZiH4LRQ74dRH4D8ADxG4BZJfXM49+aoUuXtl6mLcUTLIm8E7qvY8Bj0zwDFgHimr93nqoLaq84trhcO+tdm6xfadBTyUtROQgchhni4fiabJWAyjfmFR0zitY5AOyxEXuICM6cXyuXH7OsuYNFgyS6WyxntvonYOxEO6ZV5KGQgQKBgQDj6WIxBL+woZv4TXfSy69X5MBZZCR76pbT/4cxwik4C45NWTkQZroFnpeNe1+ZUBCBo9yKFqJE3Lc3GDqP4ppyae8ncEt8573BFtgm9/W2+8ktPHpTP69sBPR3cU+qJmGywvhmGbsKw3OAc1hh8QUfB5wWiHGpk9d2n6Oesar8QwKBgQDbk/9fbuFqvR8U8l2SVNo9I6wHuEMi6n+Xvyxa4in+o2sYRexZFXj+V2wG2cnnac/VwLjrc3QZeFjlIeeR2XPq51k3XvJbeDOF8PW+0UxpEkZGY0d3U9w9aUvP8fZXRO9QzVfcoBQs8+azLP7LUg0wu/PyLeX5tvs1mq59S3YaEwKBgFLmKx8lWQxIXFEe7HyyNd9Eh2p8cIDaPW5zPZutwgxut54O3CDJHNS6cOm3fHhplPdywN9BiFh/5+yUVYjMpd0EB0dTI0IVkQHQ62KRQsECKJTZsKzjN02VJ87iWlXLjDHXlT6eEVjC6xpH2lgVsQUuYWoYyy8hZXfybTNl8Im9AoGBAKIno3nImrOGwsNnUO7Vtu1bwJla3EmWv29DAPCFANpJuLcOPEJ1dwrtaJuOjmpVW+TRFD7c3ipJE+zyot60pcQd4WbguT4PgI0zq2eljTYzBq7IdmiLB6RadGA2oeNExy721iHaHcwsunwriGaCS7dntfltJ97H4LBvcNiat7b9AoGBALFY/ztZ/JtQLcDJU63r3GT62UdV9h8P94/tlyn/y9YsVwb0cK87DwSuGCDh1li6jcF+NWOEHbme6iUwXKo/lOFYHHwC8olA2jze2pTduD494C6y1vlBl3d5Ud35D6NEastVtieFKkh5mz1llylWZUbwsqPow46IDBP3JY/poyF2";

    // APIGW_PUBLIC_KEY
    protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

    // APP_ID
    protected static final String APP_ID = "";  //2020年2月appid

    //API的签名类型：
    protected static final String SIGN_TYPE="RSA";

    //URL---//根据测试环境和生产环境替换相应ip和端口
    protected static final String URL_AUTH="http://ip:port/api/bcss/guard/userAuth/V1";


    public  void ioth() throws Exception {
//        DES3Demo des3demo = new DES3Demo();
//        String desEncrypDate = null;
//        desEncrypDate = des3demo.getDES3Encrpt("440101197001023456", "440101197001023456", "2000000048");


        BcssGuardUserAuthRequestV1.BcssGuardUserAuthRequestBizV1 bdr = new BcssGuardUserAuthRequestV1.BcssGuardUserAuthRequestBizV1();
        bdr.setCientTransNo("111");
        bdr.setClientId("A0000000001");
        bdr.setCorpId("0000000067");
        bdr.setManufacture("KAIERRUI01");
        bdr.setInOutDirect("0");
        bdr.setProtocolType("004");
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
        bdr.setTimeStamp(timeStamp);
        bdr.setOtherData("");
        bdr.setUpData("88588");

        // 设置请求
        BcssGuardUserAuthRequestV1 request = new BcssGuardUserAuthRequestV1();
        request.setServiceUrl(URL_AUTH);
        request.setBizContent(bdr);

        // 执行发送请求
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,SIGN_TYPE, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        BcssGuardUserAuthResponseV1 response = null;
        try {
            response = client.execute(request);
        } catch (IcbcApiException ex) {
            ex.printStackTrace();
        }
//        System.out.println(JSONObject.toJSONString(response));
        LOGGER.info(JSONObject.toJSONString(response));

    }



    class DES3Demo {

        public    String getDES3Encrpt(String desKeystr,String targetNo,String corpid) throws Exception {
            //3des加密
            Key deskey = null;
            DESedeKeySpec spec = new DESedeKeySpec(desKeystr.getBytes());//将3des的秘钥变成字节数组，然后生成秘钥规范
            SecretKeyFactory keyFactory2 = SecretKeyFactory.getInstance("desede"); //返回指定算法（desede）的秘密秘钥的SecretKeyFactory对象
            deskey = keyFactory2.generateSecret(spec);//利用秘钥工厂，根据提供的秘钥规范（spec），生成secrctkey对象

            Cipher cipher2 = Cipher.getInstance("desede/CBC/PKCS5Padding");
//	    IvParameterSpec ips = new IvParameterSpec("00000323".getBytes()); //企业编号后8位，加密的初始变量

            String corpidEigth=corpid.substring(corpid.length()-8, corpid.length());
            IvParameterSpec ips = new IvParameterSpec(corpidEigth.getBytes()); //企业编号后8位，加密的初始变量

            cipher2.init(Cipher.ENCRYPT_MODE, deskey,ips);
            byte[] encryptData2 = cipher2.doFinal(targetNo.getBytes());  //输入明文，加密
            String encode2 = Hex.toHexString(encryptData2);

//            System.out.println(encode2);
            return encode2;
        }


    }
}
