package com.icbc.api;

import com.icbc.api.request.EbankcCommonSaasukeyauthenRequestV1;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;


public class EbankcCommonSaasukeyauthenTest {

    protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----CdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIyANF5Z7e/J2DLCH7785Xw301UbvCjTGbxcD8TwLHf/CuoKVKdKNCcUncnBwIpmRu5acyMuwkeRTnjB6q5C5OnXayyoNxjDK6SzW9LfvADAnn0/dxxdtdW5TyspPyWC7IfELTa84Utqyr2fY7mj0oxl3XntKbP+gzWYeP6JUlXfAgMBAAECgYAEaGE8NIYKZ8KolP5592UbqISwvtYAUd8U0a6M9xvuB8pH3VsahHFuaG8qzTFFJkBDagMCr4zYwe48S5kZLnAP+hPIBTYGTy98EmrGZvEH3sDWyauDHGGlLCgPOOMegf4vMSvUUR3A4k+FUZwWUOnDhvT/Slftc6LK81MerbgZgQJBAL5nzYsh2TrbyahXRb+zyw3wvskjrhADOyIDVAkiC4PDU/g3QJVd3g3jnKHQQKr6tUrCmPAOWSJ9RkbOmF+wAacCQQC85zgkTJeJlS17uL63guEOO39lGw2xtmUTKI5Kp6LSv3ecxHgHYU94vlWkv0+CS9UGHxTeUymbPuOZoDBErmEJAkEAkb36wuih0j+x170VkmsYng8MugEplvAiLyLFDEiW78BvYatJMAutkIwMtH8nQLiuVA8n6XfSGmgxhrlUQMoEOQJAVchmXU2Oz0K4OePmb612iCEKtB459MbQVYfoPgysSyn5pm4t16QgE8PTXpImCBRps3Lv5ho73a0MbForMTVDCQJAYxstWDQSAB6VAkes79ct9KsDQ4dCz2CJ+yxdmzeOTcq73nK4MuRhRAM3ZARcHyjiIsxEOAlgUySJ/0BEqjdNAw==";
    protected static final String APP_ID = "10000000000000018252";

    /**
     * 签名内容参考方法
     * 1. 使用icbc-ca.jar包中的cn.com.infosec.icbc.ReturnValue.base64enc(byte[])方法，对文件做base64
     * 2. 如果使用其他开源方法，务必使字符串在1行内输出，否则传输时存在问题（return a.replaceAll("\r|\n", "")）
     * @param fileType 签名方式，0-html，1-png图片，2-jpg图片
     * @param fileInfo 签名方式为0时该字段为要签名的文本信息，1、2时该字段为文件名称
     * @return
     */
    public static String GetStr(String fileType,String fileInfo) {
        String encode = null;
        try {
            byte[] ba = null;
            if("0".equals(fileType)){
                ba = fileInfo.getBytes();
            }else{
                File file = new File(fileInfo);
                InputStream is = new FileInputStream(file);
                long contentLength = file.length();
                ba = new byte[(int) contentLength];
                is.read(ba);
                is.close();
            }
            encode = new String(cn.com.infosec.icbc.ReturnValue.base64enc(ba));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encode;// 返回Base64编码过的字节数组字符串
    }
    public static void main(String[] args) {
        // 构造client对象
        UiIcbcClient client = new UiIcbcClient(APP_ID, MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8);

        // 设置请求对象request
        EbankcCommonSaasukeyauthenRequestV1 request = new EbankcCommonSaasukeyauthenRequestV1();
        // 设置请求路径
        request.setServiceUrl("https://ip:port/ui/ebankc/common/saasukeyauthen/V1/");
//        request.setServiceUrl("http://ip:port/ui/ebankc/common/ukeyauthen/V1");
        // 设置通知消息路径
        request.setNotifyUrl("http://ip:port/index.jsp");

        EbankcCommonSaasukeyauthenRequestV1.EbankcCommonSaasukeyauthenRequestV1Biz bizContent = new EbankcCommonSaasukeyauthenRequestV1.EbankcCommonSaasukeyauthenRequestV1Biz();
        bizContent.setApiName("JSON");
        bizContent.setApiVersion("001.001.004.001");
        bizContent.setSerialNo("1000000000000000213620170122012021000001");
        bizContent.setTranTime("20170125134253");
        bizContent.setSignFlag("1");
        bizContent.setSignKind("1");
        bizContent.setSignMode("0");
        bizContent.setCustAcctName("XXX集团有限公司");
        bizContent.setCustAcctNo("6222020200106190966");
        bizContent.setCustType("100");
        bizContent.setCustId("428767198408147542");
        bizContent.setCustName("XXX集团有限公司");
        bizContent.setFileType("0");
//        bizContent.setSignInfo(GetStr("1","C:\\XXX\\XXX.jpg"));
//        bizContent.setSignInfo(GetStr("0","签名内容"));
        bizContent.setSignInfo("INK7oaLW2Mfs6LXJvbmk0vi05z4=");
        bizContent.setAutoTurnFlag("1");
        bizContent.setRequestIp("ip:port");
        bizContent.setRequestMac("28-6E-D4-89-E6-32");
        request.setBizContent(bizContent);

        // 生成自提交的表单返回客户浏览器，该表单会自动提交完成调用
        try {
            System.out.println(client.buildPostForm(request));
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }
}
