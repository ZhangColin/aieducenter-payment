package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.BondAgreementCheckPrecheckRequestV1;
import com.icbc.api.response.BondAgreementCheckPrecheckResponseV1;

public class BondAgreementCheckPrecheckRequsetV1Test {
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
		BondAgreementCheckPrecheckRequestV1 request = new BondAgreementCheckPrecheckRequestV1();

		//6、设置请求路径；联测时，请联系我行告知并替换成测试环境地址。
		request.setServiceUrl("https://ip:port/api/bond/agreement/check/preCheck/V1");
		//7、设置业务参数
		//7.1 渠道要素信息
		BondAgreementCheckPrecheckRequestV1.RequestBiz bizContent = new BondAgreementCheckPrecheckRequestV1.RequestBiz();
		BondAgreementCheckPrecheckRequestV1.RequestBiz.ChanCommV10 chanCommV10 = new BondAgreementCheckPrecheckRequestV1.RequestBiz.ChanCommV10();
		chanCommV10.setChanlno("03032200");
		chanCommV10.setMserialn("");
		chanCommV10.setTermid("53");
		chanCommV10.setOapp("F-WAPB");
		chanCommV10.setIp("ip:port");
		chanCommV10.setSevlevel("0");
		chanCommV10.setCino("020079104130803");
		chanCommV10.setChantype("303");
		chanCommV10.setIftrxsernb("50624053");
		chanCommV10.setMac("0");
		chanCommV10.setPreflag("1");
		chanCommV10.setSerialno("050380818059402291809526054");
		bizContent.setChanCommV10(chanCommV10);
		//7.2 交易基础信息
		BondAgreementCheckPrecheckRequestV1.RequestBiz.InfoCommV10 infoCommV10 = new BondAgreementCheckPrecheckRequestV1.RequestBiz.InfoCommV10();
		infoCommV10.setTrxcode("41150");
		infoCommV10.setWorkdate("2028-02-28");
		infoCommV10.setServiceface("53");
		infoCommV10.setApptype("11");
		infoCommV10.setBrnoweb("00620");
		infoCommV10.setZoneno("0200");
		infoCommV10.setTellerno("59");
		infoCommV10.setRevtranf("0");
		infoCommV10.setZoneweb("0200");
		infoCommV10.setTrxtype("3");
		infoCommV10.setRegflag("0");
		infoCommV10.setWorktime("15:55:44");
		infoCommV10.setBrno("0045");
		bizContent.setInfoCommV10(infoCommV10);
		//7.3 私有通信区
		BondAgreementCheckPrecheckRequestV1.RequestBiz.AppLogComm appLogComm = new BondAgreementCheckPrecheckRequestV1.RequestBiz.AppLogComm();
		appLogComm.setCkpinflag("0");
		appLogComm.setCvvcheckflag("0");
		appLogComm.setReadflag("1");
		appLogComm.setWorkdateflag("0");
		appLogComm.setCardflag("2");
		appLogComm.setCkusrflag("0");
		appLogComm.setCurrtype("001");
		appLogComm.setGdflag("1");
		bizContent.setAppLogComm(appLogComm);

		BondAgreementCheckPrecheckRequestV1.RequestBiz.FmtaBemsCommInput fmtaBemsCommInput = new BondAgreementCheckPrecheckRequestV1.RequestBiz.FmtaBemsCommInput();
		fmtaBemsCommInput.setMediaType("6");
		fmtaBemsCommInput.setOperFlag("1");
		bizContent.setFmtaBemsCommInput(fmtaBemsCommInput);
		bizContent.setDiscountFlag("0");
		bizContent.setDiscountAmount("0");
		bizContent.setBondAmount("1");
		bizContent.setBankCode("080001");
		bizContent.setQueryType("2");
		bizContent.setDiscountCheckFlag("1");

		request.setBizContent(bizContent);

		BondAgreementCheckPrecheckResponseV1 response;
		try {
			System.out.println("request:"+ JSON.toJSONString(request));
			response = client.execute(request);
			System.out.println("response:" + JSON.toJSONString(response));
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}
	}
}
