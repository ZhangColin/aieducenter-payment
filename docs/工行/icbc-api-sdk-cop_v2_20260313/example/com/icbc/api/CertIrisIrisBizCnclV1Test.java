package com.icbc.api;

import java.util.Date;
import java.util.logging.Logger;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.request.CertIrisIrisBizCnclRequestV1;
import com.icbc.api.request.CertIrisIrisBizCnclRequestV1.CertIrisIrisBizCnclRequestV1Biz;
import com.icbc.api.response.CertIrisIrisBizCnclResponseV1;


public class CertIrisIrisBizCnclV1Test {

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
		CertIrisIrisBizCnclRequestV1 request = new CertIrisIrisBizCnclRequestV1();

//		测试环境霄1�7要替换ip和端口，生成环境已有默认值，此语句可删除
		request.setServiceUrl(API_SERVICE_URL +"/cert/iris/irisbizcncl/V1");
//		请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋倄1�7
		CertIrisIrisBizCnclRequestV1Biz bizContent = new CertIrisIrisBizCnclRequestV1Biz ();
//		
		
		bizContent.setChannel("TEST");
		bizContent.setTrCode(getTrxCode());
		bizContent.setAppName("F-TEST");
		bizContent.setAppInfo("F-TEST");
		bizContent.setAppVersion("1.0");
		bizContent.setTrxZone("00101");
		/*上�1�7�分库id+用户id+虹膜序号*/
		bizContent.setBranchId("BASTEST_20190614");
		bizContent.setUserId("2222");
		bizContent.setIrisIndex("L");
		request.setBizContent(bizContent);

		CertIrisIrisBizCnclResponseV1 response = new CertIrisIrisBizCnclResponseV1();
		Logger log = Logger.getLogger(CertIrisIrisBizCnclV1Test.class.getName());
		try {
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
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}
	}
	
	public static String getTrxCode() {
		String timeStr = "" + new Date().getTime();
		String retStr = "";
		retStr = timeStr + timeStr + timeStr;
		retStr = retStr.substring(retStr.length() - 36);
		Logger log = Logger.getLogger(CertIrisIrisBizCnclV1Test.class.getName());
		log.info("trcode:"+retStr);
		return retStr;
	}
}