package com.icbc.api;

import java.util.Date;
import java.util.logging.Logger;

import com.icbc.api.request.CertFingerveinFingerveinbizdealRequestV1;
import com.icbc.api.request.CertFingerveinFingerveinbizdealRequestV1.CertFingerveinFingerveinbizdealRequestV1Biz;
import com.icbc.api.response.CertFingerveinFingerveinbizdealResponseV1;

public class CertFingerveinFingerveinbizdealV1Test {
	
//	调用方自己的AppId
	private static final String APP_ID = new String("xxxxxx");
//	调用方私钄1�7
	protected static final String MY_PRIVATE_KEY = new String("xxxxxx");
//	API网关公钥
	protected static final String APIGW_PUBLIC_KEY = new String("xxxxxx");
//	API请求地址
	private static final String API_SERVICE_URL = new String("http://ip:port/api");
	
	public static void main(String[] args) {
//		签名类型为SHA256WithRSA时，传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省�1�7�1�7
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
		CertFingerveinFingerveinbizdealRequestV1 request = new CertFingerveinFingerveinbizdealRequestV1();

//		测试环境霄1�7要替换ip和端口，生成环境已有默认值，此语句可删除
		request.setServiceUrl(API_SERVICE_URL +"/Cert/fingervein/fingerveinbizdeal/V1");
//		请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋倄1�7
		CertFingerveinFingerveinbizdealRequestV1Biz bizContent = new CertFingerveinFingerveinbizdealRequestV1Biz();
//		上�1�7�图片的Base64形式，此处以固定图片做例孄1�7
		bizContent.setChannel("101"); // 渠道ID：请根据申请沟�1�7�情况上送正确的渠道叄1�7用于统计c00:默认渠道;101：企业手机银衄1�7(IOS);102:企业手机银行(安卓);201:智能网点IE渠道〄1�71：个人手机银衄1�7(IOS);302:个人手机银行(安卓)
		bizContent.setTrCode("Bas2018kj328421313"); // 交易流水号：霄1�7保证应用内唯丄1�7，用于统计以及交易定佄1�7
		bizContent.setAppName("工银E办公"); // 调用方应用名：请根据申请沟�1�7�情况上送正确的应用各1�7
		bizContent.setAppVersion("1.0"); // 客户端版本号
		bizContent.setAppInfo("工银E办公"); // 客户端app信息,如：工银e办公
		bizContent.setTrxZone("1001"); // 交易地区
		bizContent.setProgramName("dealFingerVein"); // 调用程序名称
		bizContent.setUserId("123456789"); // 客户信息ID：用于唯丄1�7确认客户信息的标礄1�7
		bizContent.setVeinIndex("L2"); // 特征标识：左手拇指L1、食指L2，依次L3、L4、L5;右手拇指R1、食指R2，依次R3、R4、R5
		bizContent.setBranchId("F-SHFH");  // 分库编号：应用名*类型*编号。其中应用名：分行上送分行简秄1�7,如上海行刄1�7F-SHFH；�1�7�行应用上�1�7�应用简称，智能网点上�1�7�：F-SSTS; 类型为应甄1�7/分行自行定义，如内部办公:OA筄1�7编号：为应用/分行自行定义，保证每个分组内人脸数量小于1000＄1�7
		
		request.setBizContent(bizContent);

		CertFingerveinFingerveinbizdealResponseV1 response = new CertFingerveinFingerveinbizdealResponseV1();
		
		Logger log = Logger.getLogger(CertFingerveinFingerveinbizdealV1Test.class.getName());
		try {
			response = client.execute(request, "" + new Date().getTime());
			log.info("Msg_id:" + response.getMsgId());
			if (response.isSuccess()) {
				// 业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数捄1�7
				log.info("ReturnCode:" + response.getReturnCode());
				log.info("response:" + response);
			} else {
				// 失败
				log.info("ReturnCode:" + response.getReturnCode());
				log.info("ReturnMsg:" + response.getReturnMsg());
			}
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}
	}
	
	public static String getTrxCode() {
		String timeStr = "" + new Date().getTime();
		String retStr = "";
		retStr = timeStr + timeStr + timeStr;
		retStr = retStr.substring(retStr.length() - 36);
		Logger log = Logger.getLogger(CertFingerveinFingerveinbizdealV1Test.class.getName());
		log.info("trcode:"+retStr);
		return retStr;
	}
	

}
