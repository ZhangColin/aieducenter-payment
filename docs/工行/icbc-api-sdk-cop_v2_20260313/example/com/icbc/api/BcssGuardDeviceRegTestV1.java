package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.BcssGuardDeviceRegRequestV1;
import com.icbc.api.response.BcssGuardDeviceRegResponseV1;
import com.icbc.hsm.utils.encoders.Hex;

import javax.crypto.Cipher;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.RSAPublicKeySpec;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

public class BcssGuardDeviceRegTestV1 {
    private static final Logger LOGGER = Logger.getLogger(BcssGuardDeviceRegTestV1.class.getName());
    //     MY_PRIVATE_KEY
    protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDDfGldwFbxb8AOvZxLcblZLJZk5N3U8xvgOHXo3ZoXzRl4LWGNl/E4JMGerCiOXICwDm9TyD+vNkS0lha0Aw5/g3YvVnpOfCFGTqWR1ovI/gB3fL+1QlxhUGGtU1NrsS1zuFRfDz06J3XZc3qpIfHSRvFQs2rytw5SuemgRCb3p6ncY+sya1qc8iEujbD6kPi5reb1vL5lriQpI1h9e8RiMIA2jSXltlMQ8EGHQ2nMIcvnECMdi54LG8DB79HhYwIdmOAo8LmL61IB95Rc1dRunI6IRlMjucn/V0YlMhMgu+S76bBzETlNFBYJ4kSeVVQ9xlWJsTtNlITq9kF1MYb5AgMBAAECggEABXDQ/er8gHu7vK1KTE89+V1kUiqMggDCJdtsbK6+OytaKh4ZOPehnw5m1SO1fUDV1BCSyKIqI5Y/E0eaGih7ntDp2SdvAAA0bOCDeyudeFqZaLnwG/tc1tiF320SRU0JFs9qibrC2Xt/rC2qnEp1kISo8rYLo/W0UFkcR3meHAZiH4LRQ74dRH4D8ADxG4BZJfXM49+aoUuXtl6mLcUTLIm8E7qvY8Bj0zwDFgHimr93nqoLaq84trhcO+tdm6xfadBTyUtROQgchhni4fiabJWAyjfmFR0zitY5AOyxEXuICM6cXyuXH7OsuYNFgyS6WyxntvonYOxEO6ZV5KGQgQKBgQDj6WIxBL+woZv4TXfSy69X5MBZZCR76pbT/4cxwik4C45NWTkQZroFnpeNe1+ZUBCBo9yKFqJE3Lc3GDqP4ppyae8ncEt8573BFtgm9/W2+8ktPHpTP69sBPR3cU+qJmGywvhmGbsKw3OAc1hh8QUfB5wWiHGpk9d2n6Oesar8QwKBgQDbk/9fbuFqvR8U8l2SVNo9I6wHuEMi6n+Xvyxa4in+o2sYRexZFXj+V2wG2cnnac/VwLjrc3QZeFjlIeeR2XPq51k3XvJbeDOF8PW+0UxpEkZGY0d3U9w9aUvP8fZXRO9QzVfcoBQs8+azLP7LUg0wu/PyLeX5tvs1mq59S3YaEwKBgFLmKx8lWQxIXFEe7HyyNd9Eh2p8cIDaPW5zPZutwgxut54O3CDJHNS6cOm3fHhplPdywN9BiFh/5+yUVYjMpd0EB0dTI0IVkQHQ62KRQsECKJTZsKzjN02VJ87iWlXLjDHXlT6eEVjC6xpH2lgVsQUuYWoYyy8hZXfybTNl8Im9AoGBAKIno3nImrOGwsNnUO7Vtu1bwJla3EmWv29DAPCFANpJuLcOPEJ1dwrtaJuOjmpVW+TRFD7c3ipJE+zyot60pcQd4WbguT4PgI0zq2eljTYzBq7IdmiLB6RadGA2oeNExy721iHaHcwsunwriGaCS7dntfltJ97H4LBvcNiat7b9AoGBALFY/ztZ/JtQLcDJU63r3GT62UdV9h8P94/tlyn/y9YsVwb0cK87DwSuGCDh1li6jcF+NWOEHbme6iUwXKo/lOFYHHwC8olA2jze2pTduD494C6y1vlBl3d5Ud35D6NEastVtieFKkh5mz1llylWZUbwsqPow46IDBP3JY/poyF2";

    // APIGW_PUBLIC_KEY
    protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

    // APP_ID
    protected static final String APP_ID = "";  //请联系工行获取

    //API的签名类型：
    protected static final String SIGN_TYPE="RSA";

    //URL---//根据测试环境和生产环境替换相应ip和端口
    protected static final String URL_DEVICE="http://ip:port/api/bcss/guard/deviceReg/V1";


    //设备注册时--3des密钥的明文
    protected static final String DESKEY_STRING = "28"; //一定要24个字节

    protected static final String RSA_MODULUS = "28289839";

    protected static final String RSA_PUBLICEXPONENT = "65537";


    public  void hrm() throws Exception {

        //RSA加密
//        DES3Demo
        RSADemo rsaDemo=new RSADemo();
        String rsaencrpy = rsaDemo.getRSAEncrpt(DESKEY_STRING, RSA_MODULUS, RSA_PUBLICEXPONENT);


        BcssGuardDeviceRegRequestV1.BcssGuardDeviceRegRequestBizV1 hrb = new BcssGuardDeviceRegRequestV1.BcssGuardDeviceRegRequestBizV1();
        hrb.setCientTransNo("1111");
        hrb.setClientId("Guard");
        hrb.setCorpId("0000000067");
        hrb.setEncryptDes(rsaencrpy); //设置加密后的内容
        hrb.setManufacture("KAIERRUI01");
        String timeStmp = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
        hrb.setTimeStamp(timeStmp);


        BcssGuardDeviceRegRequestV1 request = new BcssGuardDeviceRegRequestV1();
        request.setServiceUrl(URL_DEVICE); //2020年2月 api地址

        request.setBizContent(hrb);

        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,SIGN_TYPE, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        BcssGuardDeviceRegResponseV1 response = null;
        try {
            response = client.execute(request);
        } catch (IcbcApiException ex) {
            // TODO Auto-generated catch block

            ex.printStackTrace();
        }
        LOGGER.info(JSONObject.toJSONString(response));
//        System.out.println(JSONObject.toJSONString(response));
    }




    class RSADemo {

        public  String getRSAEncrpt(String desKeystr, String RSAmodulus, String RSApublicExponent) throws Exception {
            // rsa
            KeyFactory keyfactory = KeyFactory.getInstance("RSA");//生产rsa专用工厂
            //产生秘钥对的模数和对数
            BigInteger modulus = new BigInteger(RSAmodulus);
            BigInteger publicExponent = new BigInteger(RSApublicExponent);

            //产生秘钥材料
            RSAPublicKeySpec rps = new RSAPublicKeySpec(modulus, publicExponent);
            PublicKey pk = keyfactory.generatePublic(rps);//根据给定的秘钥材料生产公钥对象
            //获取公钥的值
//            String my= Hex.toHexString(pk.getEncoded()).toUpperCase();


            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, pk);
            cipher.update(desKeystr.getBytes()); //继续多部分加密的操作，以处理其他数据部分
            byte[] encryptData = cipher.doFinal();

//		String outstring=GuardServerHandler.bytesToHexString(encryptData);  //Hex.toHexString结果一致（忽略字符大小写）
            String encode = Hex.toHexString(encryptData);
//            System.out.println(encode);

            return encode;
        }


    }
}
