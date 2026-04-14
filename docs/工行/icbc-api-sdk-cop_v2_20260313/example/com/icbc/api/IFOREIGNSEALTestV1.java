package com.icbc.api;


import com.icbc.api.request.IFOREIGNSEALRequestV1;
import com.icbc.api.response.IFOREIGNSEALResponseV1;
import java.util.logging.Logger;

import org.junit.Assert;
import org.junit.Test;

public class IFOREIGNSEALTestV1 {
    private Logger log = Logger.getLogger(IFOREIGNSEALTestV1.class.getName());

    //密钥对认证方式，公钥在API平台登记，此处为私钥
    protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCqXe8dCRg49WMAr+io5iGCrW2Fby1Bnjf7Jjt+VDzylTnDsHicRvql+pFYw1ycQUvw4YEDz3kMuO7ImWsTFszzVFGwsYxSuqsaud/qhnToRBjFe0OSsrt+EdtK0Sr++Zo0w56nKSg9kQVy4EctUh4bvHntp8sW9vEs818DN9KqINDuiVpqIbxd80Sl2aYpEgf6Yk0QE2GlgE/ed1Z/rPNV74whfz6x+Hwc97RgWzNyHUjrcHzEztzcI319oD84XqzkA3K8cEvVBQ7P5ziYzOO1SFPzMVtbfqzitYC6P3UiBS90mLwatZ+iOMATDFhjCKvUNMfUlNJhmG5ss4riU5JfAgMBAAECggEAMgNHWae806O9v4rkawi716ff2ecMSxIObw0HNAu4pKIIBhl9Hqt7k1QebQdU3Y4RpBIfFKZIa+KyPnNDSqRL4DblkFBe2D5yUnM0d52nHjQ3bWnFKYCMp/LIBIuo6pcNnOW8Ymgjpwc2qUWLv5M2wX+v74NtjD1MCZ0cTE6L6mMiTQtkc4qYnlyTIdtJu7KhhhZbq9sScWQW1wGdfm8dygX58SC811AJ9t/9GekyYUR6aTFAsgYQHxUuNzF5+uElidjZkOVnBhLeUljjNOv9CU+Nu6zOk8BTDNaXcK3N+d+MtsMvk+EO88nN3Oe10lKABXEOtUCkLUlrWMxbEqPFkQKBgQDr0dI2R9jaL6mASCaMv/X3I+NBgZM/gqcKDZrKEzBKAGGJWKT4OiWpkF+lqhB94qQx1xnF0+Haz+QykXkdQ3Y7c5LJn83mT9y8v8V5NHmGM1Avw7f7xX5JYXQ5EipqDBypbDA8xZUPlfs1Xqe1zVO4Mbg36SGzT+3jtKcTDSBoxwKBgQC48jgZaPDHxLM2g9tDXZTe6pch/Px2ptBGSyrBnymdJM21aDMs034Eh2DnNP2RllqwhNJHSMOR+u6E+RBnNhSgt3NenPzGKFIjE1ljIOZmhn+3/SR5ziRiiAhdea4LNanX0pJ80Rf0rBDPh1tgptCBJeYKDd5TFZVnLMaLkO1hqQKBgQCxGmR4VZ8bxb3T1Nv2ytfr4qzy7mBqyzGuECeW6bBTc1PNNaC3t9u/K+lZXo6CLGtJ7xSxCP7POWBVRgGio56w6YxtFjIpbQMHUjL5Yxp9Wh43sRKE6H5+l80Ua3slJ7GC3/ieO3lGOkpue4cSt4DPja+fddvUcgvj/jGX0jzr0wKBgCQrct89T8Gx7V6fAPW2ypOiC3ahNHq7+wWUU8NULl5GQVjWDRQaNI4ML95n1aNwZUIaZ8l7KIxXJ+inkRauU/BukEiTqTfT0OR9N0EUwRqR+U5xWn3b2+/NLN2jG2+jyhikXPO+LNI0CIGdtjg7x5FKaSfUOIPFN9fawOWwrUwpAoGAC9ojPybKU2MmcaBK44KuhfywAk7w6ZStsA3MEvBGiKLFpPiXd1ftZ/p6LxcAT7DQMBeMEU0SeBSTLZ74ug6X7pTJUnYUv02wAteUUTmslkVLfvr3nzee4Uf9R7u2uH7/cTklnpXeNpTEodcKDTh13TMY/A4lVEXk4SeFJeM8YYY=";

    //网关公钥
    protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
    //应用ID，APP申请分配appid
    protected static final String APP_ID = "xxxxxxxxx";

    
    public void test_cop() throws IcbcApiException {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        IFOREIGNSEALRequestV1 request = new IFOREIGNSEALRequestV1();
        //根据测试环境和生产环境替换相应ip和端口
        request.setServiceUrl("http://IP:PORT/api/mybank/account/foreignseal/V1");

        IFOREIGNSEALRequestV1.IFOREIGNSEALRequestV1Biz bizContent = new IFOREIGNSEALRequestV1.IFOREIGNSEALRequestV1Biz();
        //其余参数内容见文档
        bizContent.setMETHOD("1");
        bizContent.setAREACODE("");
        bizContent.setBRANCH_NUM("");
        bizContent.setMEDIUM_ID("");
        bizContent.setMEDIUM_TYPE("");
        bizContent.setBUSSID("");
        bizContent.setEXCHANGE_DATE("");
        bizContent.setEXCHANGE_TIME("");
        bizContent.setUPDATE_OPT("");
        bizContent.setCOMMISION_OPT("");
        bizContent.setUPDATE_REASON("");
        bizContent.setUSE_FLAG("");
        bizContent.setVALID_DATE("");
        bizContent.setINVALID_DATE("");
        bizContent.setNOTES("");
        bizContent.setBUILD_DATE("");
        bizContent.setBUILD_TIME("");
        bizContent.setSIGNTYPE("");
        bizContent.setCLASSTYPE("");
        bizContent.setCIN("");
        bizContent.setCNAME("");
        bizContent.setCATTRIBUTE("");
        bizContent.setCREATER("");
        bizContent.setLAPPROVEDATE("");
        bizContent.setLAPPROVETIME("");
        bizContent.setLAPPROVER("");
        bizContent.setLAPPROVERESULT("");
        bizContent.setCHANGETYPE("");
        bizContent.setSEAL_UPDATE_STATE("");
        bizContent.setAPPROVEINFO("");
        bizContent.setRELATION_STATE2("");
        bizContent.setIMGTYPE("");
        bizContent.setQUERYDATE("");
        bizContent.setLAPPROVERESULT2("");
        bizContent.setRELATION_STATE("");
        bizContent.setEXCHANGE_CODE("");
        bizContent.setEXCHANGE_OPT("");
        bizContent.setSCAN_STATE("");
        bizContent.setSTART_TIME("");
        bizContent.setEND_TIME("");
        bizContent.setAREA_CODE_N("");
        bizContent.setBRANCH_NUM_N("");
        bizContent.setMEDIUM_ID_N("");
        bizContent.setMEDIUM_TYPE_N("");
        bizContent.setEXCHANGE_DATE_N("");
        bizContent.setEXCHANGE_TIME_N("");
        bizContent.setPAGE_FLAG("");
        bizContent.setPAGE_NUM("");
        bizContent.setVALIDATE_RES("");
        bizContent.setEXCHANGE_BRANCH("");
        bizContent.setREMARK("");
        bizContent.setRULETYPE("");
        bizContent.setRULEDESCRIP("");
        bizContent.setMONEYUPLIMMIT("");
        bizContent.setCHOP("");
        bizContent.setLUPDATEDATE("");
        bizContent.setLUPDATETIME("");
        bizContent.setLUPDATER("");
        bizContent.setRULENUM("");
        bizContent.setRULENUM_N("");
        bizContent.setBUSSID_N("");
        bizContent.setUPDATE_DATE_N("");
        bizContent.setUPDATE_TIME_N("");
        bizContent.setIMGNUM("");
        bizContent.setACCBRNO("");
        bizContent.setGROUPNAME("");
        bizContent.setCBUSSID("");
        bizContent.setCBUSSID_N("");
        bizContent.setPAGE_N("");
        bizContent.setCTYPE("");
        bizContent.setPAGE("");
        bizContent.setID("");
        bizContent.setNOTES1("");
        bizContent.setNOTES2("");
        bizContent.setIMAGESOURCE("");
        bizContent.setEXCHANGER("");
        bizContent.setSTATUS("");
        bizContent.setRESULT("");
        bizContent.setCSYSFLAG("");
        bizContent.setRULE1("");
        bizContent.setRULE2("");
        bizContent.setRULE3("");
        bizContent.setRULE4("");
        bizContent.setRULE5("");
        bizContent.setRULE6("");
        bizContent.setRULE7("");
        bizContent.setRULE8("");
        bizContent.setRULE9("");
        bizContent.setRULE10("");
        bizContent.setRULE11("");
        bizContent.setRULE12("");
        bizContent.setRULE13("");
        bizContent.setRULE14("");
        bizContent.setRULE15("");
        bizContent.setRULE16("");
        bizContent.setUPDATE_DATE("");
        bizContent.setUPDATE_TIME("");

        request.setBizContent(bizContent);

        IFOREIGNSEALResponseV1 response = client.execute(request, System.currentTimeMillis() + "");//请求通讯唯一编号
        boolean testFlag = true;
        if (response.isSuccess() && response.getReturnCode() == 0) {// 业务成功处理
            Assert.assertEquals(true, testFlag);
        } else {// 失败
            Assert.assertEquals(false, testFlag);
        }
    }
}
