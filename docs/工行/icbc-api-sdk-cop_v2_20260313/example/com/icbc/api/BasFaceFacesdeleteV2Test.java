package com.icbc.api;

import java.util.Date;
import java.util.logging.Logger;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.request.BasFaceFacesdeleteRequestV2;
import com.icbc.api.request.BasFaceFacesdeleteRequestV2.BasFaceFacesdeleteRequestV2Biz;
import com.icbc.api.response.BasFaceFacesdeleteResponseV2;

public class BasFaceFacesdeleteV2Test {
//	调用方自己的AppId
	private static final String APP_ID = new String("xxxxxx");
//	调用方私钄1�7
	protected static final String MY_PRIVATE_KEY = new String("xxxxxx");
//	API网关公钥
	protected static final String APIGW_PUBLIC_KEY = new String("xxxxxx");
	//	API请求地址
	private static final String API_SERVICE_URL = new  String("http://ip:port/api");
	
	public static void main(String[] args) {
		//		签名类型为SHA256WithRSA时，传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省�1�7�1�7
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
		BasFaceFacesdeleteRequestV2 request = new BasFaceFacesdeleteRequestV2();
		request.setServiceUrl(API_SERVICE_URL +"/bas/face/facesdelete/V2");
//		请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋倄1�7
		BasFaceFacesdeleteRequestV2Biz bizContent = new BasFaceFacesdeleteRequestV2Biz();
		bizContent.setId("testhuangwj1");
		bizContent.setChannel("202");
		bizContent.setTrCode(getTrxCode());
		bizContent.setAppName("F-BAS");
		bizContent.setAppInfo("tesadthuangwj");
		bizContent.setTrxZone("01001");
		request.setBizContent(bizContent);

		Logger log = Logger.getLogger(BasFaceFaceexitbranchV1Test.class.getName());
		BasFaceFacesdeleteResponseV2 response = new BasFaceFacesdeleteResponseV2();
		try {
			response = client.execute(request, "" + new Date().getTime());
			log.info("Msg_id:" + response.getMsgId());
			if (response.isSuccess()) {
				// 6、业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数捄1�7
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
		Logger log = Logger.getLogger(BasFaceFaceexitbranchV1Test.class.getName());
		String timeStr = "" + new Date().getTime();
		String retStr = "";
		retStr = timeStr + timeStr + timeStr;
		retStr = retStr.substring(retStr.length() - 36);
		log.info("trcode:"+retStr);
		return retStr;
	}

}
