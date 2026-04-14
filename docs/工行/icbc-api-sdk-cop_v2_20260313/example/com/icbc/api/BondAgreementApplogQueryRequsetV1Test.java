package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.BondAgreementApplogQueryRequestV1;
import com.icbc.api.response.BondAgreementApplogQueryResponseV1;

public class BondAgreementApplogQueryRequsetV1Test {
	public static void main(String[] args) {
		// 示例总体说明：请使用F-APIP最新的行外网关SDK进行开发，签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值

		//1、密钥对认证方式，公钥在F-APIP平台登记，此处为私钥
		String MY_PRIVATE_KEY = "";
		//2、appid
		String APP_ID = "";
		//3、网关公钥
		String APIGW_PUBLIC_KEY = "";

		//4、创建与F-APIP外网网关的连接
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String)null, (String)null, (String)null, (String)null);
		//5、设置请求对象
        BondAgreementApplogQueryRequestV1 request = new BondAgreementApplogQueryRequestV1();

		//6、设置请求路径；联测时，请联系我行告知并替换成测试环境地址。
		request.setServiceUrl("https://ip:port/api/bond/agreement/applog/query/V1");
		//7、设置业务参数
		//7.1 渠道要素信息
		BondAgreementApplogQueryRequestV1.RequestBiz bizContent = new BondAgreementApplogQueryRequestV1.RequestBiz();
		BondAgreementApplogQueryRequestV1.RequestBiz.ChanCommV10 chanCommV10 = new BondAgreementApplogQueryRequestV1.RequestBiz.ChanCommV10();
		chanCommV10.setChantype("303");
		chanCommV10.setOapp("F-SOCT");
		chanCommV10.setSerialno("150202008264836000051554002");
		chanCommV10.setCino("622202020090233");
		chanCommV10.setIftrxsernb("123456789");
		bizContent.setChanCommV10(chanCommV10);
		//7.2 交易基础信息
		BondAgreementApplogQueryRequestV1.RequestBiz.InfoCommV10 infoCommV10 = new BondAgreementApplogQueryRequestV1.RequestBiz.InfoCommV10();
		infoCommV10.setTrxtype("2");
		infoCommV10.setTrxcode("5147");
		infoCommV10.setZoneno("00200");
		infoCommV10.setBrno("00620");
		infoCommV10.setTellerno("12");
		infoCommV10.setWorkdate("2028-04-10");
		infoCommV10.setWorktime("19:17:30");
		infoCommV10.setServiceface("53");
		bizContent.setInfoCommV10(infoCommV10);

        bizContent.setEventNo("050202008264836000051554439");

		request.setBizContent(bizContent);

        BondAgreementApplogQueryResponseV1 response;
		try {
			System.out.println("request:"+ JSON.toJSONString(request));
			response = client.execute(request);
			System.out.println("response:" + JSON.toJSONString(response));
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}
	}
}
