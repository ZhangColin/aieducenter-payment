package com.icbc.api;

import com.icbc.api.request.JftWalletApiPensionAccountRealtimeimguploadRequestV1;
import com.icbc.api.request.JftWalletApiPensionAccountRealtimeimguploadRequestV1.JftWalletApiPensionAccountRealtimeimguploadRequestV1Biz;
import com.icbc.api.response.JftWalletApiPensionAccountRealtimeimguploadResponseV1;

/**
 * @author dingpf
 * @date 2022/3/7 10:07
 * @desc
 *      聚富通养老金第三支柱账户影像信息上传
 */
public class JftWalletApiPensionAccountRealtimeimguploadTestV1 {

	private static String APIGW_PUBLIC_KEY = "[需替换]工行API网关公钥，请向工行联系获取";
	private static String APP_ID = "[需替换]APP的编号,应用在API开放平台注册时生成";
	private static String MY_PRIVATE_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";
	private static String HOST = "[需替换]工行API网关地址";
	private static String API_URL = HOST + "api/jft/wallet/api/pension/account/realtimeimgupload/V1";
	private static String ENCRYPT_KEY = "[需替换]应用方加密串，与工行API开放平台一致";

	public static void main(String[] args) {
		//签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, "AES", ENCRYPT_KEY, "", "");
		JftWalletApiPensionAccountRealtimeimguploadRequestV1 request = new JftWalletApiPensionAccountRealtimeimguploadRequestV1();
		request.setServiceUrl(API_URL);

		//请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
		JftWalletApiPensionAccountRealtimeimguploadRequestV1Biz bizContent = new JftWalletApiPensionAccountRealtimeimguploadRequestV1Biz();
		bizContent.setAppId(APP_ID);//平台编号
        bizContent.setTrxAccDate("2021-04-14");// 合作方交易日期 yyyy-MM-dd
        bizContent.setTrxAccTime("10:38:01");// 合作方交易时间
        bizContent.setCorpSerno("030302002182100046192701324");// 合作方交易单号
        bizContent.setCertNo("37032319850314020X");// 身份证号码 必须和姓名同时输入
        bizContent.setCustName("王亮");// 用户名 必须和证件号码同时输入
        bizContent.setValidityPeriod("2036-02-17");// 身份证有效期 证件有效期到日期（永久时送9999-12-30） 2017-03-02
        bizContent.setFrontImgType("01");// 身份证正面图片类型 01-jpg 02-png 03-jpeg 04-bmp
        bizContent.setFrontImg("eNqct1VUnEHT7zsEJwSCuwQJTnCXAMGDu7sONjgMF"); // 身份证正面图片 deflate压缩算法之后再Base64, 注意：字符串很长很长，测试案例里只是截取了部分
        bizContent.setBackImgType("01");// 身份证背面图片类型 01-jpg 02-png 03-jpeg 04-bmp
        bizContent.setBackImg("eNqc+mVUXM0TPooODO7uzhAIGtwlJLi7uzM4wd"); // 身份证背面图片 deflate压缩算法之后再Base64 注意：字符串很长很长，测试案例里只是截取了部分
        bizContent.setMediumId(""); // 二类户卡号 卡号和证件号码必须否其一
        bizContent.setClearFlag(0); // 是否补上传标志 0-否1-是
		request.setBizContent(bizContent);
		
		JftWalletApiPensionAccountRealtimeimguploadResponseV1 response;
		try {
			response = (JftWalletApiPensionAccountRealtimeimguploadResponseV1) client.execute(request, System.currentTimeMillis()+"");
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

