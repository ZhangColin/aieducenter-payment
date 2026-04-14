package com.icbc.api;
import java.util.Date;
import java.util.logging.Logger;

import com.icbc.api.request.CertIrisIrisQueryRequestV1.CertIrisIrisQueryRequestV1Biz;
import com.icbc.api.request.CertIrisIrisQueryRequestV1;
import com.icbc.api.response.CertIrisIrisQueryResponseV1;
public class CertIrisIrisQueryV1Test {
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
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
		CertIrisIrisQueryRequestV1 request = new CertIrisIrisQueryRequestV1();
//		测试环境霄1�7要替换ip和端口，生成环境已有默认值，此语句可删除
		request.setServiceUrl(API_SERVICE_URL +"/cert/iris/irisbizdeal/V1");
//		请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋倄1�7
		CertIrisIrisQueryRequestV1Biz bizContent = new CertIrisIrisQueryRequestV1Biz ();
		
		bizContent.setChannel("TEST");
		bizContent.setTrCode(getTrxCode());
		bizContent.setAppName("F-TEST");
		bizContent.setAppInfo("F-TEST");
		bizContent.setAppVersion("1.0");
		bizContent.setTrxZone("00101");
		/*上�1�7�用户id*/
		bizContent.setUserId("702816403_1.jpg");
		request.setBizContent(bizContent);
		
		CertIrisIrisQueryResponseV1 response= new CertIrisIrisQueryResponseV1();
		Logger log = Logger.getLogger(CertIrisIrisQueryV1Test.class.getName());
		try{
			response = client.execute(request, "" + new Date().getTime());
			log.info("Msg_id:" + response.getMsgId());
			if (response.isSuccess()) {
				// 业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数捄1�7
				log.info("ReturnCode:" + response.getReturnCode());
				log.info("ReturnMsg:" + response.getReturnMsg());
				log.info("response:" + response);
			} else {
				// 失败
				log.info("ReturnCode:" + response.getReturnCode());
				log.info("ReturnMsg:" + response.getReturnMsg());
				log.info("response:" + response);
			}
		}catch(IcbcApiException e){
			e.printStackTrace();
		}
}

	private static String getTrxCode() {
		// TODO Auto-generated method stub
		String timeStr = ""+ new Date().getTime();
		String retStr = "";
		retStr = timeStr+timeStr+timeStr;
		retStr = retStr.substring(retStr.length() - 36);
		Logger log = Logger.getLogger(CertIrisIrisQueryV1Test.class.getName());
		log.info("trcode:"+retStr);
		return retStr;
	}
}

