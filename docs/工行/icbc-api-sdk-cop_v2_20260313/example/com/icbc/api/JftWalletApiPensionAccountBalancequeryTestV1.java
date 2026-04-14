package com.icbc.api;

import com.icbc.api.request.JftWalletApiPensionAccountBalancequeryRequestV1;
import com.icbc.api.response.JftWalletApiPensionAccountBalancequeryResponseV1;

/**
 * @author dingpf
 * @date 2022/3/2 9:42
 * @desc 聚富通钱包第三支柱专用账户余额查询
 */
public class JftWalletApiPensionAccountBalancequeryTestV1 {

    private static String APIGW_PUBLIC_KEY = "[需替换]工行API网关公钥，请向工行联系获取";
	private static String APP_ID = "[需替换]APP的编号,应用在API开放平台注册时生成";
	private static String MY_PRIVATE_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";
	private static String HOST = "[需替换]工行API网关地址";
	private static String API_URL = HOST + "api/jft/wallet/api/pension/account/balancequery/V1";
	private static String ENCRYPT_KEY = "[需替换]应用方加密串，与工行API开放平台一致";

    public static void main(String[] args) {
        //签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, "AES", ENCRYPT_KEY, "", "");
        JftWalletApiPensionAccountBalancequeryRequestV1 request = new JftWalletApiPensionAccountBalancequeryRequestV1();
        //根据测试环境和生产环境替换相应ip和端口
        request.setServiceUrl(API_URL);
        //请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
        JftWalletApiPensionAccountBalancequeryRequestV1.JftWalletApiPensionAccountBalancequeryRequestV1Biz bizContent = new JftWalletApiPensionAccountBalancequeryRequestV1.JftWalletApiPensionAccountBalancequeryRequestV1Biz();
        bizContent.setAppId(APP_ID);
        bizContent.setTrxAccDate("2022-03-01");
        bizContent.setTrxAccTime("10:00:00");
        bizContent.setCorpDate("2022-03-01");
        bizContent.setCorpSerno(APP_ID + "20220301001");
        bizContent.setCcy("1");
        bizContent.setMediumId("620014564313165165");
        request.setBizContent(bizContent);

        JftWalletApiPensionAccountBalancequeryResponseV1 response;
        try {
            response = (JftWalletApiPensionAccountBalancequeryResponseV1) client.execute(request, System.currentTimeMillis() + "");
            if (response.isSuccess()) {
                //6、业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
                System.out.println("returnCode:" + response.getReturnCode());
                System.out.println("response:" + response);
            } else {
                //失败
                System.out.println("returnCode:" + response.getReturnCode());
                System.out.println("returnMsg:" + response.getReturnMsg());
            }
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }

}
