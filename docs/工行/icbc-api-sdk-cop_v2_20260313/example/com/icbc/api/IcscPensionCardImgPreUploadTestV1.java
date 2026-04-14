package com.icbc.api;

import com.icbc.api.request.IcscPensionCardImgPreUploadRequestV1;
import com.icbc.api.response.IcscPensionCardImgPreUploadResponseV1;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class IcscPensionCardImgPreUploadTestV1 {

    protected static final String MY_PRIVATE_KEY = "此处为私钥串";
    protected static final String APIGW_PUBLIC_KEY = "此处为网关公钥串";
    protected static final String APP_ID = "此处为APP_ID";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2,  MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8, "json", APIGW_PUBLIC_KEY,"AES","5xGJdh7qb+B95SUoxDlatg==", "", "");
        IcscPensionCardImgPreUploadRequestV1 request = new IcscPensionCardImgPreUploadRequestV1();
        request.setServiceUrl("https://{网关域名}/api/icsc/pension/cardImgPreUpload/V1");
        IcscPensionCardImgPreUploadRequestV1.IcscPensionCardImgPreUploadRequestV1Biz bizContent = new IcscPensionCardImgPreUploadRequestV1.IcscPensionCardImgPreUploadRequestV1Biz();
        //appId
        bizContent.setAppId(APP_ID);
        bizContent.setCorpNo("JFT_HuiCanTongTest");
        bizContent.setCorpSerno(APP_ID + get16UUID());
        bizContent.setBackImgType("02");//01-jpg 02-png 03-jpeg 04-bmp
        bizContent.setFrontImgType("02");
        bizContent.setFrontImg("身份证正面图片（deflate压缩后base64）");//确保整个请求报文大小不超过1M即可，单张图片大小调用方自行调整
        bizContent.setBackImg("身份证背面图片（deflate压缩后base64）");
        //请求时间戳
        bizContent.setRequestTime(String.valueOf(System.currentTimeMillis()));
        IcscPensionCardImgPreUploadResponseV1 response;
        try {
            request.setBizContent(bizContent);
            //msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一
            response = client.execute(request, String.valueOf(System.currentTimeMillis()));
            if (response.isSuccess()) {
                //业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
                System.out.println("ReturnCode: " + response.getReturnCode());
                System.out.println("ReturnMsg: " + response.getReturnMsg());
                System.out.println("PreUploadId: " + response.getPreUploadId());
            } else {
                //失败
                System.out.println("ReturnCode:"+response.getReturnCode());
                System.out.println("ReturnMsg:"+response.getReturnMsg());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String get16UUID(){
        SimpleDateFormat sdf = new SimpleDateFormat("MMdd");
        String dayTime = sdf.format(new Date());
        int hashCode= UUID.randomUUID().toString().hashCode();
        if(hashCode<0){
            hashCode=-hashCode;
        }
        return "01" + dayTime + String.format("%010d", hashCode);
    }
}
