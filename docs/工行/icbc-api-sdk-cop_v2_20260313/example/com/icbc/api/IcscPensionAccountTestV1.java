package com.icbc.api;

import com.icbc.api.request.UiIcscPensionAccountRequestV1;

/**
 * @author dccb-wbwangqb
 * @date 2021/10/28 - 16:01
 */
public class IcscPensionAccountTestV1 {

    protected static final String MY_PRIVATE_KEY = "[私钥]";
    protected static final String APP_ID = "[APP_ID]";

    public static void main(String[] args) {
        try {
            UiIcbcClient client = new UiIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8);
            //NdfUiIcbcClient client = new NdfUiIcbcClient(APP_ID, ApipConstants.RSA2, MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8);
            UiIcscPensionAccountRequestV1 request = new UiIcscPensionAccountRequestV1();
            request.setServiceUrl("https://apipcs4.dccnet.com.cn/ui/icsc/pensionAccount/V1");
            UiIcscPensionAccountRequestV1.UiIcscPensionAccountRequestV1Biz bizContent = new UiIcscPensionAccountRequestV1.UiIcscPensionAccountRequestV1Biz();
            //平台商户标识
            bizContent.setCorpSerno(APP_ID + "[16位随机序列号]");
            bizContent.setCorpNo("[合作方机构编号]");
            bizContent.setAppId(APP_ID);
            bizContent.setJumpUrl("[返回地址]");
            //测试地址
            bizContent.setNotifyAddr("[合作方接收回调通知地址]");
            bizContent.setSecretKey("[SM2公钥加密的SM4秘钥串]");
            bizContent.setCertNo("[SM4加密的客户身份证件编号]");
            bizContent.setCustName("[SM4加密的客户名称]");
            //非必送字段
            bizContent.setPreUploadId("[接入预上传接口返回的图片编号]");
            bizContent.setAreaCode("[开户归属地区城市代码]");
            //1-公务员;2-事业单位员工;3-公司员工;4-军人警察;5-工人;6-农民;7-管理人员;8-技术人员;9-私营业主; 10-文体明星;11-自由职业者;12-学生;13-无职业
            bizContent.setOccupation("[职业]");
            bizContent.setBindMedium("[SM4加密的客户银行卡编号]");
            //银行卡开户行类型 | 1:中国工商银行; 2:中国农业银行; 3:中国建设银行; 4:中国银行; 5:交通银行; 6:邮政储蓄银行
            bizContent.setCardType("[银行卡开户行类型]");
            bizContent.setMobileNo("[客户手机号]");
            bizContent.setAddress("[客户详细地址]");
            bizContent.setRequestTime(String.valueOf(System.currentTimeMillis()));
            request.setBizContent(bizContent);

            //String url = client.buildGetUrl(request);
            //System.out.println(url);
            System.out.println(client.buildPostForm(request));
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }

    }
}
