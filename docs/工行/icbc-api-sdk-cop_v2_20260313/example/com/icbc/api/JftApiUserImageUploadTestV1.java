package com.icbc.api;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcConstants;
import com.icbc.api.request.JftApiUserImageUploadRequestV1;
import com.icbc.api.response.JftApiUserImageUploadResponseV1;
import sun.misc.BASE64Encoder;

import java.util.Arrays;
import java.util.zip.Deflater;

public class JftApiUserImageUploadTestV1 {

    // FIXME：此处替换工行API网关公钥，请向工行联系获取
    private static final String APIGW_PUBLIC_KEY = "[需替换]此处替换工行API网关公钥，请向工行联系获取";

    // FIXME: 此处替换合作方 APPID
    private static final String APP_ID = "[需替换]此处替换合作方 APPID";

    // FIXME：此处替换合作方 APPID 对应私钥
    private static final String MY_PRIVATE_KEY = "[需替换]此处替换合作方 APPID 对应私钥";

    // FIXME：此处替换合作方 AES 加密秘钥
    private static final String AES_Key = "[需替换]此处替换合作方 AES 加密秘钥";

    public static void main(String[] args) {
        // 声明工行开放平台 API 数据接口请求 Client 实体类，传入配置项
        DefaultIcbcClient client = new DefaultIcbcClient(
                APP_ID,
                IcbcConstants.SIGN_TYPE_RSA2,
                MY_PRIVATE_KEY,
                IcbcConstants.CHARSET_UTF8,
                IcbcConstants.FORMAT_JSON,
                APIGW_PUBLIC_KEY,
                IcbcConstants.ENCRYPT_TYPE_AES,
                AES_Key,
                "", "");

        // 组装调用《聚富通用户图片上传接口V1》上送报文
        JftApiUserImageUploadRequestV1 request = new JftApiUserImageUploadRequestV1();
        request.setServiceUrl("https://ip:port/api/jft/api/user/image/upload/V1");

        JftApiUserImageUploadRequestV1.JftApiUserImageUploadRequestV1Biz bizContent = new JftApiUserImageUploadRequestV1.JftApiUserImageUploadRequestV1Biz();
        bizContent.setAppId(APP_ID);                // 平台商户标识

        // 将照片图片流使用deflate算法压缩后，以BASE64格式上送
        // 请关注最终BASE64编码后，照片大小应不超过1M
        byte[] imageData = new byte[1024];
        byte[] bytes = deflateCompress(imageData);
        BASE64Encoder encoder = new BASE64Encoder();
        String encode = encoder.encode(bytes);
        bizContent.setImageData(encode);
        bizContent.setImageName("123456789.png");

        request.setBizContent(bizContent);
        JftApiUserImageUploadResponseV1 response;
        try {
            response = client.execute(request, System.currentTimeMillis() + "");//msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一
            if (response.isSuccess()) {
                //业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
                System.out.println("ReturnCode:" + response.getReturnCode());
                System.out.println("response:" + response);
            } else {
                //失败
                System.out.println("ReturnCode:" + response.getReturnCode());
                System.out.println("ReturnMsg:" + response.getReturnMsg());
            }
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }

    /**
     * 进行DEFLATE压缩
     *
     * @param input 原始图片流
     * @return DEFLATE压缩图片流
     */
    public static byte[] deflateCompress(byte[] input) {
        System.out.println("[deflateCompress]:input length: " + input.length);
        byte[] output = new byte[1024 * 1024];
        System.out.println("[deflateCompress]:output length: " + output.length);
        Deflater deflater = new Deflater();
        deflater.setInput(input);
        deflater.finish();
        int compressesDateLength = deflater.deflate(output);
        deflater.end();
        return Arrays.copyOf(output, compressesDateLength);
    }
}
