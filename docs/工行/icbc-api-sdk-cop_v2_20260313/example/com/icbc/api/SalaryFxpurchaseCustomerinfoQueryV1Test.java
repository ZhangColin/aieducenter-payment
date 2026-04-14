package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.SalaryFxpurchaseCustomerinfoQueryRequestV1;
import com.icbc.api.response.SalaryFxpurchaseCustomerinfoQueryResponseV1;

public class SalaryFxpurchaseCustomerinfoQueryV1Test {
	public static void main(String[] args) {
		// 示例总体说明：请使用F-APIP最新的行外网关SDK进行开发，签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值 IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值

		//1、密钥对认证方式，公钥在F-APIP平台登记，此处为私钥
		String MY_PRIVATE_KEY = "";
		//2、appid
		String APP_ID = "";
		//3、网关公钥
		String APIGW_PUBLIC_KEY = "";

		//4、创建与F-APIP外网网关的连接
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String)null, (String)null, (String)null, (String)null);
		//5、设置请求对象
		SalaryFxpurchaseCustomerinfoQueryRequestV1 request = new SalaryFxpurchaseCustomerinfoQueryRequestV1();

		//6、设置请求路径；联测时，请联系我行告知并替换成测试环境地址。
		request.setServiceUrl("http://ip:port/api/salary/fxpurchase/customerinfo/query/V1");
		//7、设置业务参数
		//7.1 渠道要素信息
		SalaryFxpurchaseCustomerinfoQueryRequestV1.RequestBiz bizContent = new SalaryFxpurchaseCustomerinfoQueryRequestV1.RequestBiz();
		SalaryFxpurchaseCustomerinfoQueryRequestV1.RequestBiz.ChanCommV10 chanCommV10 = new SalaryFxpurchaseCustomerinfoQueryRequestV1.RequestBiz.ChanCommV10();
		chanCommV10.setChantype("0");
		chanCommV10.setOapp("F-DIMO");
		chanCommV10.setSerialno("050380809365410241137527027");
		bizContent.setChanCommV10(chanCommV10);
		//7.2 交易基础信息
		SalaryFxpurchaseCustomerinfoQueryRequestV1.RequestBiz.InfoCommV10 infoCommV10 = new SalaryFxpurchaseCustomerinfoQueryRequestV1.RequestBiz.InfoCommV10();
		infoCommV10.setTrxtype("1");
		infoCommV10.setTrxcode("5249");
		infoCommV10.setZoneno("00101");
		infoCommV10.setBrno("00001");
		infoCommV10.setTellerno("12");
		infoCommV10.setWorkdate("2028-12-31");
		infoCommV10.setWorktime("10:23:28");
		infoCommV10.setRevtranf("1");
		bizContent.setInfoCommV10(infoCommV10);
		//7.3 私有通讯区
		SalaryFxpurchaseCustomerinfoQueryRequestV1.RequestBiz.SearchCustomerArea searchCustomerArea = new SalaryFxpurchaseCustomerinfoQueryRequestV1.RequestBiz.SearchCustomerArea();
		searchCustomerArea.setOperFlag("0");
		searchCustomerArea.setCino("020079104889989");
		searchCustomerArea.setAffiliatedInstitution("");
		searchCustomerArea.setCustomerName("");
		searchCustomerArea.setCertType("");
		searchCustomerArea.setCertNo("");
		searchCustomerArea.setInitFlag("1");
		searchCustomerArea.setRowReq("5");

		request.setBizContent(bizContent);

		SalaryFxpurchaseCustomerinfoQueryResponseV1 response;
		try {
			System.out.println("request:"+ JSON.toJSONString(request));
			response = client.execute(request);
			System.out.println("response:" + JSON.toJSONString(response));
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}
	}
}
