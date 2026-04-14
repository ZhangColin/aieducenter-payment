package com.icbc.api;

import com.icbc.api.request.MlpOcrRecoRequestV1;
import com.icbc.api.request.MlpOcrRecoRequestV1.MlpOcrRecoBiz;
import com.icbc.api.response.MlpOcrRecoResponseV1;

import java.util.UUID;
import javax.xml.bind.DatatypeConverter;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;

public class MlpOcrRecoTestV1 {
    // 合作方rsa私钥，由sdk－bin目录下的keygen_rsa.bat生成，yourname.pri和yourname.pub，其中youname.pub提供给总行做登记，yourname.pri合作方自己保留
    protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCIgdsr3VNMpXe56BQrYP2HFxt8cFrPXvfc+lodgFxkr4GPMcPvf1hsynjZu40cDbTGPsaE53cKjTDJwCZ8SvK/jk6HMpaqLHi7sbhIZLJLEw5Lz65x3VYCe2TARYqT2mw9TAvwqoHXuLgtvSsz45cLp6Pk1OxrdrEVDWytIeYNY20ggb+dQKVjlfoYPgZzdsnjAGQMKeXWKmbBCDUN53Mw9/ZK70rKzQL7KJOPMCvsvtSSh0wc1Cf+wa5h737pbuT/fSJShNmnWOFa4evzrzp6zS+kLKWndCVqAt4gzys2xahsrp5A7Pvserrzh3ERFrVRaW5b25bde9N1l7DJ1ni7AgMBAAECggEACnw/A42KmnaTTbpf4j0e/ENwHwzaTNd0noq0ZVO4xRseDou6Cyly91hOFBtt6Vu8KQYlZYwJ/st9leGmkyGGp+QccBIYtJWZ6IDsMNNbRpz+GiCnu6NOuf4UbRhqXpUGf2bZ+/B87L8JlqnTKbcvoPuNgURBrLd/Pqi7UkkqTJjbrlDWeHHGRvKnPzP2Qab0M5U/4QKAXfJEv10e2Avb/kDLNTrR50b2LqrcQLz8JVytFAMqJptgZzu7EZweJtw8diBSb/BDR+Gq2AYngoCFCCXYp7CrGBUo4fQJGTbqPYANADh7Y8M+nONGeIjWtyBxhYi+kIx2GXunbKH0t9njEQKBgQDNccKLYgph+79KKdZ/PZHkGtBeaCkHy7oDsGwqeEZpYOp/fOnwPNgWKOzyKEm/PcZ2vbTdyMuoYmHusIGkyvtGpxFfvS86C2sbzXLbRAs1Y5xHc/OhQPq5DOqc9K5UciYNoB1YDMaVHCTN6DVEi9UTRl/C1yXV2Nzu2VGBNpxLdwKBgQCqGU+XBtHXJrKsLOTCOJQmCMziO6GZKVN3bN2h+AwIliP4Ey5i48Eu2UyrseXe/G4fWO3IQAVLTTzy/NYdgHqRtxJUa38pAoEcOUUvXl/MGZZfAeDLz+7Y7n20wFETkY0Rep3n6FhSQxGCQpVw+NcCdrvBoARxfCP7rwUgIBAF3QKBgCMJmL0+vjiRv8D9O6i9rmjxrcG8D+tVcmb4qqnfk1qbfcK6YgdnyQ6WcjqmZlVoSrWrivIcMaeJB9y2W5XoTExzZ8IlOK20jtLSChmdFDBJybhh7RX+SARRUJ3uGOEsCpNeUELZAvEhOKGpTZRgKLm90c0f9kLvVlah14Wq86LnAoGBAJAO/6kYzX859vh6zQLwiAfuTBcOCb59kYMDYHFcclSTU8M1+4MlAOd8gcKn19cSUvGZj0iZb+Jmd8gS+gPiW+ws8AkvQXKp/E668i078EYbJY8xOAFlCRQQZ8Y9Wm0ISEEVZ8aQsLx6sMlxOr+DABXGGR3TN/AlAxSVFfIvNWLBAoGALeFcZN7IHCoC/d7yoeTUdUz3mn8w2I/ZOXMAPv1JJtioCgGOGibwohqpXru2W1Zg9Q9uz5w7Q7W4Yq08e/RJk38qDR8cKnLI1KLItCFb2Clm0gC7HKnbdy3PwlWrLzxB6L/1sAEcf5HCbd54xM2DniVgW8QfVKgU/zWiP6CQrZY=";

    // 网关公钥
    protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

    // appid，由分行自己在cams系统中生成和维护
    protected static final String APP_ID = "10000000000000203539";

    public static void main(String[] args) {
        String msg_id= UUID.randomUUID().toString();
        System.out.println("进行身份证图片ocr识别");
        try{
            // 签名类型为SHA256WithRSA时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
            DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
            MlpOcrRecoRequestV1 morr_request = new MlpOcrRecoRequestV1();

            // 根据测试环境和生产环境替换相应ip和端口
            // 行外流程
            morr_request.setServiceUrl("https://apipcs3.dccnet.com.cn/api/mlp/ocr/reco/V1");//https://apipcs3.dccnet.com.cn/api/mlp/ocr/reco/V1

            // 参数设置
            String fs = "D:\\Test\\2.jpg";
            InputStream in = new FileInputStream(fs);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024*4];
            int n = 0;
            while((n = in.read(buffer)) != -1){
                out.write(buffer, 0 , n);
            }
            String base64str = DatatypeConverter.printBase64Binary(out.toByteArray());
            String asyncFlag = "0";//默认送0
            String caseId = "2";//2-身份证正面  3-身份证反面 17-银行卡 101-自动识别正背面(图像为身份证复印件，正反面打印在同一张纸上时，可以送101)
            String eapId = "";
            String appId = "f-old";
            String suffix = "jpg";

            // 请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
            MlpOcrRecoBiz bizContent = new MlpOcrRecoBiz();
            bizContent.setAsyncFlag(asyncFlag);
            bizContent.setCaseId(caseId);
            bizContent.setEapId(eapId);
            bizContent.setBase64(base64str);
            bizContent.setSuffix(suffix);
            morr_request.setBizContent(bizContent);
            // 建立返回项
            MlpOcrRecoResponseV1 morr_response = client.execute(morr_request, msg_id);
            if(morr_response.isSuccess()){
                System.out.println("OCR识别成功");
                System.out.println("return_code="+morr_response.getReturnCode());
                System.out.println("return_msg="+morr_response.getReturnMsg());
                System.out.println("content="+morr_response.getContent());
            }else{
                System.out.println("OCR识别失败");
                System.out.println("return_code="+morr_response.getReturnCode());
                System.out.println("return_msg="+morr_response.getReturnMsg());
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
