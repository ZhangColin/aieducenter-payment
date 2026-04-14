package com.icbc.api;

import com.icbc.api.request.UiIcscJftWalletRequestV1;
import com.icbc.api.utils.NdfUiIcbcClient;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class UiIcscJftWalletTestV1 {


    protected static final String MY_PRIVATE_KEY = "此处为私钥串";
    protected static final String APIGW_PUBLIC_KEY = "此处为网关公钥串";
    protected static final String APP_ID = "此处为APP_ID";

    public static void main(String[] args) {

        UiIcscJftWalletRequestV1 requestV1 = new UiIcscJftWalletRequestV1();
        UiIcscJftWalletRequestV1.UiIcscJftWalletRequestV1Biz bizContentV1 = new UiIcscJftWalletRequestV1.UiIcscJftWalletRequestV1Biz();
        requestV1.setServiceUrl("https://{网关域名}/ui/icsc/jftWallet/V1");
        /* 交易单号：appId+16为序列号，要求唯一，不可空；*/
        bizContentV1.setCorpSerno(APP_ID + get16UUID());
        /* 合作方介质号：用户编号，要求同一个合作方机构编号下唯一，不可空；*/
        bizContentV1.setUserId("12312312312");
        /* 合作方机构编号（二类户）：申请的合作方机构编号，不可空；*/
        bizContentV1.setCorpNo1("12312323123");
        /* appId1（二类户）：在apip平台申请的编号，不可空；*/
        bizContentV1.setAppId1("12122134123");
        /* 合作方机构编号（三类户）：申请的合作方机构编号，不可空；*/
        bizContentV1.setCorpNo2("42123412341234234");
        /* appId2（三类户）：在apip平台申请的编号，不可空；*/
        bizContentV1.setAppId2("34523452345234");
        /* 返回链接 必送 */
        bizContentV1.setJumpUrl("www.xxx.com");
        /* 开户成功跳转链接，可空；*/
        bizContentV1.setSuccessAddr("www.xxx.com");
        /* 回调通知地址：用于接收开户结果回调的地址，可空；*/
        bizContentV1.setNotifyAddr("www.xxx.com");
        requestV1.setBizContent(bizContentV1);

        NdfUiIcbcClient clientGet = new NdfUiIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8);
        UiIcbcClient clientPost = new UiIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8);
        try {
            //GET
            String url = clientGet.buildGetUrl(requestV1);
            System.out.println("GetUrl:" + url);
            //POST
            String postForm = clientPost.buildPostForm(requestV1);
            System.out.println("PostUrl:" + postForm);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public static String get16UUID() {
        SimpleDateFormat sdf = new SimpleDateFormat("MMdd");
        String dayTime = sdf.format(new Date());
        int hashCode = UUID.randomUUID().toString().hashCode();
        if (hashCode < 0) {
            hashCode = -hashCode;
        }
        return "01" + dayTime + String.format("%010d", hashCode);
    }
}
