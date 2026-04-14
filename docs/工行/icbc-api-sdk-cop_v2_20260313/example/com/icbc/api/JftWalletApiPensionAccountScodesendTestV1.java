package com.icbc.api;

import com.icbc.api.request.JftWalletApiPensionAccountScodesendRequestV1;
import com.icbc.api.request.JftWalletApiPensionAccountScodesendRequestV1.JftWalletApiPensionAccountScodesendRequestV1Biz;
import com.icbc.api.response.JftWalletApiPensionAccountScodesendResponseV1;

/**
 * @author dingpf
 * @date 2022/3/7 10:02
 * @desc
 *      聚富通养老金第三支柱账户短信验证码重发
 */
public class JftWalletApiPensionAccountScodesendTestV1 {

	private static String APIGW_PUBLIC_KEY = "[需替换]工行API网关公钥，请向工行联系获取";
	private static String APP_ID = "[需替换]APP的编号,应用在API开放平台注册时生成";
	private static String MY_PRIVATE_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";
	private static String HOST = "[需替换]工行API网关地址";
	private static String API_URL = HOST + "/api/jft/wallet/api/pension/account/scodesend/V1";
	private static String ENCRYPT_KEY = "[需替换]应用方加密串，与工行API开放平台一致";
	public static void main(String[] args) {
		
		//签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,"RSA2", MY_PRIVATE_KEY,"UTF-8", "json",APIGW_PUBLIC_KEY,"AES",ENCRYPT_KEY, "", "");
		JftWalletApiPensionAccountScodesendRequestV1 request = new JftWalletApiPensionAccountScodesendRequestV1();
		//根据测试环境和生产环境替换相应ip和端口
		request.setServiceUrl(API_URL);

		//请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
		JftWalletApiPensionAccountScodesendRequestV1Biz bizContent = new JftWalletApiPensionAccountScodesendRequestV1Biz();
		bizContent.setAppId(APP_ID);//合作方机构编号
        bizContent.setCorpSerno("030302002186100040283285267");//合作方交易单号
        bizContent.setTrxAccDate("2021-04-26");//交易受理日期
        bizContent.setTrxAccTime("10:38:01");//交易受理时间
        bizContent.setCorpDate("2021-04-26");//合作方工作日期
        bizContent.setCorpSernoOriginal("030302001030333431322320040");//原合作方交易单号
        bizContent.setAppType("1");//申请交易类型，1-开户，2-授权，3-绑卡
		request.setBizContent(bizContent);
		
		JftWalletApiPensionAccountScodesendResponseV1 response;
		try {
			response = (JftWalletApiPensionAccountScodesendResponseV1) client.execute(request, System.currentTimeMillis()+"");
			if (response.isSuccess()) {
				//业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
				System.out.println("returnCode:"+response.getReturnCode());
				System.out.println("response:" + response);
			} else {
				//失败
				System.out.println("returnCode:"+response.getReturnCode());
				System.out.println("returnMsg:"+response.getReturnMsg());
			}	
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}
	}

}

