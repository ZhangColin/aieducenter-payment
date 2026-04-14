package com.icbc.api;

import com.icbc.api.request.UiIcscPensionAccountRequestV1;
import com.icbc.api.utils.NdfUiIcbcClient;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class UiIcscPensionAccountTestV1 {

    protected static final String MY_PRIVATE_KEY = "此处为私钥串";
    protected static final String APP_ID = "此处为APP_ID";

    public static void main(String[] args) {
        UiIcscPensionAccountRequestV1 request = new UiIcscPensionAccountRequestV1();
        request.setServiceUrl("https://{网关域名}/ui/icsc/pensionAccount/V1");
        UiIcscPensionAccountRequestV1.UiIcscPensionAccountRequestV1Biz bizContent = new UiIcscPensionAccountRequestV1.UiIcscPensionAccountRequestV1Biz();
        //封装参数
        bizContent.setCorpSerno(APP_ID + get16UUID());
        bizContent.setCorpNo("合作方机构编号");
        bizContent.setAppId(APP_ID);
        bizContent.setJumpUrl("UI页面返回地址");
        bizContent.setNotifyAddr("合作方接收开户结果回调通知地址");
        String secretKey = "随机生成16位SM4秘钥";//随机生成16位SM4秘钥
        String initSecreKey = "使用工行公钥加密SM4秘钥";
        bizContent.setSecretKey(initSecreKey);//使用工行公钥加密 SM4秘钥
        bizContent.setCertNo("sm4加密身份证卡号");//sm4加密身份证卡号
        bizContent.setCustName("sm4加密客户名称");//sm4加密客户名称
        bizContent.setPreUploadId("身份信息预上传编号（调用身份图像信息预上传接口返回）");
        bizContent.setAreaCode("开户地区号");
        bizContent.setOccupation("职业");
        bizContent.setBindMedium("sm4加密一类银行卡号");//sm4加密一类银行卡号
        bizContent.setCardType("银行卡开户行类型");
        bizContent.setMobileNo("客户手机号");
        //请求时间戳
        bizContent.setRequestTime(String.valueOf(System.currentTimeMillis()));
        NdfUiIcbcClient clientGet = new NdfUiIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8);
        UiIcbcClient clientPost = new UiIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8);
        try {
            //GET
            request.setBizContent(bizContent);
            String url = clientGet.buildGetUrl(request);
            System.out.println("GetUrl:" + url);
            //POST
            String s = clientPost.buildPostForm(request);
            System.out.println("POST:" + s);
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
