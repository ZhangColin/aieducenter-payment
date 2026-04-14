package com.icbc.api;

import java.util.Date;
import java.util.logging.Logger;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.request.CertFingerveinFingerveinupdateRequestV1;
import com.icbc.api.request.CertFingerveinFingerveinupdateRequestV1.CertFingerveinFingerveinupdateRequestV1Biz;
import com.icbc.api.response.CertFingerveinFingerveinupdateResponseV1;


public class CertFingerveinFingerveinupdateV1Test {
	//	调用方自己的AppId
	private static final String APP_ID = new String("xxxxxx");
	//	调用方私钄1�7
	protected static final String MY_PRIVATE_KEY = new String("xxxxxx");
	//	API网关公钥
	protected static final String APIGW_PUBLIC_KEY = new String("xxxxxx");
	//	API请求地址
	private static final String API_SERVICE_URL = new String("http://ip:port/api");
	
	public static void main(String[] args) {
//		签名类型为SHA256WithRSA时，传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省�1ￄ1�7�1�7�1�7
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,  MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
		CertFingerveinFingerveinupdateRequestV1 request = new CertFingerveinFingerveinupdateRequestV1();
		request.setServiceUrl(API_SERVICE_URL + "/cert/fingervein/fingerveinupdate/V1");
		CertFingerveinFingerveinupdateRequestV1Biz bizContent = new CertFingerveinFingerveinupdateRequestV1Biz();
		String base64_1 = getImage1Base64Str();

//		请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋倄1�7
		bizContent.setImg1(base64_1);
		bizContent.setChannel("TEST2");
		bizContent.setTrCode("BAS201803dfgasa3");
		bizContent.setAppName("F-TEST");
		bizContent.setAppVersion("F-TEST");
		bizContent.setAppInfo("工银e办公");
		bizContent.setTrxZone("1001");
		bizContent.setProgramName("指静脉更斄1�7");
		bizContent.setUserId("bas123456");
		request.setBizContent(bizContent);
		bizContent.setVeinIndex("L2");
		bizContent.setCustomerAgreement("1");
		request.setBizContent(bizContent);

//		log.info(client.buildPostForm(request)); 
		
		CertFingerveinFingerveinupdateResponseV1 response = new CertFingerveinFingerveinupdateResponseV1();
		Logger log = Logger.getLogger(CertFingerveinFingerveinupdateV1Test.class.getName());
		try {
			response = client.execute(request, "" + new Date().getTime());
			log.info(response.toString());
			if (response.isSuccess()) {
				// 6、业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数ￄ1�7�1�7
				log.info("ReturnCode:" + response.getReturnCode());
				log.info("ReturnMsg:" + response.getReturnMsg());
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
		Logger log = Logger.getLogger(CertFingerveinFingerveinupdateV1Test.class.getName());
		log.info("trcode:"+retStr);
		return retStr;
	}
	public static String getImage1Base64Str() {
//		因sdk无法带出图片，所以直接提供图片的base64数据。如霄1�7使用本接口，请自行开发图片文件转base64数据相关部分的代砄1�7
	    String img1 ="kSXSKLNAKKJSNKJXNJSA";
	    return img1;
	}

}
