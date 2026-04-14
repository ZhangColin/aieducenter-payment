package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.SalaryFxpurchaseContractinfoQueryRequestV1;
import com.icbc.api.response.SalaryFxpurchaseContractinfoQueryResponseV1;

public class SalaryFxpurchaseContractinfoQueryV1Test {
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
		SalaryFxpurchaseContractinfoQueryRequestV1 request = new SalaryFxpurchaseContractinfoQueryRequestV1();

		//6、设置请求路径；联测时，请联系我行告知并替换成测试环境地址。
		request.setServiceUrl("http://ip:port/api/salary/fxpurchase/contractinfo/query/V1");
		//7、设置业务参数
		//7.1 渠道要素信息
		SalaryFxpurchaseContractinfoQueryRequestV1.RequestBiz bizContent = new SalaryFxpurchaseContractinfoQueryRequestV1.RequestBiz();
		SalaryFxpurchaseContractinfoQueryRequestV1.RequestBiz.ChanCommV10 chanCommV10 = new SalaryFxpurchaseContractinfoQueryRequestV1.RequestBiz.ChanCommV10();
		chanCommV10.setOapp("F-DIMO");
		chanCommV10.setChantype("0");
		chanCommV10.setSerialno("123456789012345678901234569");
		bizContent.setChanCommV10(chanCommV10);
		//7.2 交易基础信息
		SalaryFxpurchaseContractinfoQueryRequestV1.RequestBiz.InfoCommV10 infoCommV10 = new SalaryFxpurchaseContractinfoQueryRequestV1.RequestBiz.InfoCommV10();
		infoCommV10.setTrxcode("5249");
		infoCommV10.setZoneno("00101");
		infoCommV10.setTellerno("12");
		infoCommV10.setWorkdate("2028-12-31");
		infoCommV10.setTrxtype("1");
		infoCommV10.setWorktime("10:23:28");
		infoCommV10.setBrno("00001");
		bizContent.setInfoCommV10(infoCommV10);
		//7.3 请求参数
		bizContent.setOperFlag("0");
		bizContent.setCino("020079104889989");
		bizContent.setInitFlag("1");
		bizContent.setRowReq("5");
		bizContent.setpInitFlag("1");
		bizContent.setpRowReq("5");

		request.setBizContent(bizContent);

		SalaryFxpurchaseContractinfoQueryResponseV1 response;
		try {
			System.out.println("request:"+ JSON.toJSONString(request));
			response = client.execute(request);
			System.out.println("response:" + JSON.toJSONString(response));
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}
	}
}
