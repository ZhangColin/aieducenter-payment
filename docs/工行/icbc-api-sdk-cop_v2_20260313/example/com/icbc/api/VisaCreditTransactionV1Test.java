package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.VisaCreditTransactionRequestV1.VisaCreditTransactionRequestV1Biz;
import com.icbc.api.request.VisaCreditTransactionRequestV1;
import com.icbc.api.response.VisaCreditTransactionResponseV1;
import org.junit.Assert;
import org.junit.Test;

import java.util.logging.Logger;

public class VisaCreditTransactionV1Test {
	// 1、网关公钥
	protected static final String APIGW_PUBLIC_KEY = "xxx";

	// 2、appid
	protected static final String APP_ID = "xxx";

	// 3、密钥对认证方式，公钥在API平台登记，此处为私钥
	protected static final String MY_PRIVATE_KEY = "xxx";

	private Logger logger = Logger.getLogger(VisaCreditTransactionV1Test.class.getName());


	public void test_cop() throws IcbcApiException {
//		//网关公钥
//		final String APIGW_PUBLIC_KEY = "public_key";
//		//私钥
//		final String MY_PRIVATE_KEY = "primary_key";
//		//appid
//		final String APP_ID = "app_id";
		// 签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
//		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA, MY_PRIVATE_KEY,APIGW_PUBLIC_KEY);
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
		VisaCreditTransactionRequestV1 request = new VisaCreditTransactionRequestV1();
		//根据测试环境和生产环境替换相应ip和端口
		request.setServiceUrl("http://ip:port/api/visa/creditTransaction/V1");
		VisaCreditTransactionRequestV1Biz bizContent = new VisaCreditTransactionRequestV1Biz();
		//请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
		bizContent.setMerId("400******026");
		bizContent.setProdAgrSeriNo("400******201");
		bizContent.setCardNo("414******308");
		bizContent.setOutTradeNo("940******086");
		bizContent.setOrderAmt(1000);
		bizContent.setTradeDate("20260121");
		bizContent.setTradeTime("174711");
		bizContent.setAttach("境外******退税款");
		bizContent.setAcceptorMerid("400******026");
		bizContent.setRecipientName("Law******bao");
		bizContent.setAcceptorName("深圳******公司");
		bizContent.setAcceptorRefNo("201******070");
		bizContent.setPayPurpose("离境退税");
		bizContent.setCustName("Law******bao");
		bizContent.setEci("7");
		bizContent.setAcceptorMcc("9311");
		bizContent.setOperflag("0");
		bizContent.setOriTranIdentifier("");
		bizContent.setOriIcbcOrder("");
		bizContent.setOriOutTradeno("");
		bizContent.setOriTranIdentifier("");
		bizContent.setOriIcbcOrder("");
		bizContent.setOriOutTradeno("");
		bizContent.setCrCountryCode("");
		bizContent.setCrGivenName("");
		bizContent.setCrMiddleName("");
		bizContent.setCrLastName("");
		bizContent.setCrAddress1("");
		bizContent.setCrAddress2("");
		bizContent.setCrStreetName("");
		bizContent.setCrBuildingNum("");
		bizContent.setCrPostcode("");
		bizContent.setCrCityname("");
		bizContent.setCrCountryCodeMinor("");
		bizContent.setCrCountryCodeMajor("");
		bizContent.setAcceptorBusiNane("");
		bizContent.setAcceptorPostCode("");
		bizContent.setAttachInfo("");
		bizContent.setRemark("");
		request.setBizContent(bizContent);
		System.out.println(client.buildPostForm(request));

		VisaCreditTransactionRequestResponseV1 response;
		client.setHeaderParam("X‐Fova‐Region", "04000"); //交易地区号
		try {
			response = client.execute(request, System.currentTimeMillis() + "");//msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一
			if (response.getReturnCode() == 0) {
				// 6、业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
				System.out.println("ReturnCode:" + response.getReturnCode());
				System.out.println("response:" + JSON.toJSONString(response));
			} else {
				// 失败
				System.out.println("response:" + JSON.toJSONString(response));
				System.out.println("ReturnCode:" + response.getReturnCode());
				System.out.println("ReturnMsg:" + response.getReturnMsg());
			}
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}
	}
}
