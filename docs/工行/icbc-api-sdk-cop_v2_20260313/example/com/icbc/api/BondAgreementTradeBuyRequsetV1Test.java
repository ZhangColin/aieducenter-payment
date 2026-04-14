package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.BondAgreementTradeBuyRequestV1;
import com.icbc.api.response.BondAgreementTradeBuyResponseV1;

public class BondAgreementTradeBuyRequsetV1Test {
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
        BondAgreementTradeBuyRequestV1 request = new BondAgreementTradeBuyRequestV1();

		//6、设置请求路径；联测时，请联系我行告知并替换成测试环境地址。
		request.setServiceUrl("https://ip:port/api/bond/agreement/trade/buy/V1");
		//7、设置业务参数
		//7.1 渠道要素信息
		BondAgreementTradeBuyRequestV1.RequestBiz bizContent = new BondAgreementTradeBuyRequestV1.RequestBiz();
		BondAgreementTradeBuyRequestV1.RequestBiz.ChanCommV10 chanCommV10 = new BondAgreementTradeBuyRequestV1.RequestBiz.ChanCommV10();
		chanCommV10.setChantype("303");
		chanCommV10.setOapp("F-FMTA-BON");
		chanCommV10.setSerialno("030302008001127701216008702");
		chanCommV10.setCino("020075000024901");
		chanCommV10.setIftrxsernb("58767042");
		bizContent.setChanCommV10(chanCommV10);
		//7.2 交易基础信息
		BondAgreementTradeBuyRequestV1.RequestBiz.InfoCommV10 infoCommV10 = new BondAgreementTradeBuyRequestV1.RequestBiz.InfoCommV10();
		infoCommV10.setTrxtype("3");
		infoCommV10.setTrxcode("51100");
		infoCommV10.setZoneno("200");
		infoCommV10.setBrno("45");
		infoCommV10.setTellerno("59");
		infoCommV10.setWorkdate("2028-12-31");
		infoCommV10.setRevtranf("0");
		infoCommV10.setServiceface("53");
		infoCommV10.setZoneweb("200");
		infoCommV10.setBrnoweb("2");
        bizContent.setInfoCommV10(infoCommV10);
        //7.3 共有通信区
        BondAgreementTradeBuyRequestV1.RequestBiz.AppLogComm appLogComm = new BondAgreementTradeBuyRequestV1.RequestBiz.AppLogComm();
        appLogComm.setAccno("02000620010232269");
        appLogComm.setCurrtype("1");
        appLogComm.setCkpinflag("0");
        appLogComm.setGdflag("1");
        appLogComm.setCvvcheckflag("0");
        appLogComm.setCkusrflag("0");
        appLogComm.setWorkdateflag("0");
        appLogComm.setReadflag("1");
        appLogComm.setCardflag("2");
        bizContent.setAppLogComm(appLogComm);
        //7.4 共有通信区
        BondAgreementTradeBuyRequestV1.RequestBiz.FmtaBemsCommInput fmtaBemsCommInput = new BondAgreementTradeBuyRequestV1.RequestBiz.FmtaBemsCommInput();
        fmtaBemsCommInput.setMediaType("3");
        bizContent.setFmtaBemsCommInput(fmtaBemsCommInput);

        bizContent.setBondAmount("200");
        bizContent.setBondCode("080001");
        bizContent.setTradePrice("10106");
		request.setBizContent(bizContent);

        BondAgreementTradeBuyResponseV1 response;
		try {
			System.out.println("request:"+ JSON.toJSONString(request));
			response = client.execute(request);
			System.out.println("response:" + JSON.toJSONString(response));
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}
	}
}
