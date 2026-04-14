package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.RmbexchangeDealpriceQueryRequestV2;
import com.icbc.api.response.RmbexchangeDealpriceQueryResponseV2;

public class RmbexchangeDealpriceQueryV2Test {
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
		RmbexchangeDealpriceQueryRequestV2 request = new RmbexchangeDealpriceQueryRequestV2();

		//6、设置请求路径；联测时，请联系我行告知并替换成测试环境地址。
		request.setServiceUrl("http://ip:port/api/rmbexchange/dealprice/query/V2");
		//7、设置业务参数
		//7.1 渠道要素信息
		RmbexchangeDealpriceQueryRequestV2.RequestBiz bizContent = new RmbexchangeDealpriceQueryRequestV2.RequestBiz();
		RmbexchangeDealpriceQueryRequestV2.RequestBiz.ChanCommV10 chanCommV10 = new RmbexchangeDealpriceQueryRequestV2.RequestBiz.ChanCommV10();
		chanCommV10.setMserialn("050380810579411011543368113");
		chanCommV10.setTermid("53");
		chanCommV10.setOapp("F-WAPB");
		chanCommV10.setIp("");
		chanCommV10.setSevlevel("0");
		chanCommV10.setCino("020006202541727");
		chanCommV10.setChantype("303");
		chanCommV10.setMac("0");
		chanCommV10.setPreflag("0");
		chanCommV10.setSerialno("050380810579411011543368113");
		bizContent.setChanCommV10(chanCommV10);
		//7.2 交易基础信息
		RmbexchangeDealpriceQueryRequestV2.RequestBiz.InfoCommV10 infoCommV10 = new RmbexchangeDealpriceQueryRequestV2.RequestBiz.InfoCommV10();
		infoCommV10.setTrxcode("49927");
		infoCommV10.setZoneno("0200");
		infoCommV10.setTellerno("59");
		infoCommV10.setWorkdate("2024-10-17");
		infoCommV10.setRevtranf("0");
		infoCommV10.setTrxtype("1");
		infoCommV10.setWorktime("11:00:30");
		infoCommV10.setBrno("0045");
		bizContent.setInfoCommV10(infoCommV10);
		//7.3 FMTA应用通用通讯区
		RmbexchangeDealpriceQueryRequestV2.RequestBiz.FmtaCommV10 fmtaCommV10 = new RmbexchangeDealpriceQueryRequestV2.RequestBiz.FmtaCommV10();
		fmtaCommV10.setCkUsrFlag("0");
		fmtaCommV10.setServFace("53");
		fmtaCommV10.setCustKind("2");
		fmtaCommV10.setWdflag("1");
		bizContent.setFmtaCommV10(fmtaCommV10);

		//7.4 服务私有通讯区
		RmbexchangeDealpriceQueryRequestV2.RequestBiz.PrivateArea privateArea = new RmbexchangeDealpriceQueryRequestV2.RequestBiz.PrivateArea();
		privateArea.setPdCode("130060000016");
		privateArea.setCheckLimitFlag("");
		privateArea.setCardNo1("6222020200000013504");
		privateArea.setInqFlag("1");
		privateArea.setCardNo2("6222020200000013504");
		privateArea.setAccno1("62220202000000135");
		privateArea.setExFlag("1");
		privateArea.setThFlag("0");
		privateArea.setVipLevel("1");
		privateArea.setLockTimF("0");
		privateArea.setAmount1("1000");
		privateArea.setAccno2("62220202000000135");
		privateArea.setCashExf1("1");
		privateArea.setBusiType("12");
		privateArea.setTradFlag("1");
		privateArea.setCashExf2("1");
		privateArea.setCurrTyp1("001");
		privateArea.setInOutF("0");
		privateArea.setCurrTyp2("038");
		bizContent.setPrivateArea(privateArea);
		request.setBizContent(bizContent);

		RmbexchangeDealpriceQueryResponseV2 response;
		try {
			System.out.println("request:"+ JSON.toJSONString(request));
			response = client.execute(request);
			System.out.println("response:" + JSON.toJSONString(response));
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}
	}
}
