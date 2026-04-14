package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.RmbexchangeTransferRequestV2;
import com.icbc.api.response.RmbexchangeTransferResponseV2;

public class RmbexchangeTransferV2Test {
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
		RmbexchangeTransferRequestV2 request = new RmbexchangeTransferRequestV2();

		//6、设置请求路径；联测时，请联系我行告知并替换成测试环境地址。
		request.setServiceUrl("http://ip:port/api/rmbexchange/transfer/V2");
		//7、设置业务参数
		//7.1 渠道要素信息
		RmbexchangeTransferRequestV2.RequestBiz bizContent = new RmbexchangeTransferRequestV2.RequestBiz();
		RmbexchangeTransferRequestV2.RequestBiz.ChanCommV10 chanCommV10 = new RmbexchangeTransferRequestV2.RequestBiz.ChanCommV10();
		chanCommV10.setDisrecflag("0");
		chanCommV10.setLaunbankzoneno("0");
		chanCommV10.setCino("");
		chanCommV10.setOserialn("");
		chanCommV10.setMac("");
		chanCommV10.setPreflag("0");
		chanCommV10.setChanlno("0");
		chanCommV10.setSevlevel("0");
		chanCommV10.setTrxnocheckflag("");
		chanCommV10.setComrolflag("0");
		chanCommV10.setTrxsqnb("");
		chanCommV10.setFingerprinflag("0");
		chanCommV10.setDistransinfo("");
		chanCommV10.setIp("");
		chanCommV10.setPaperlessflag("0");
		chanCommV10.setChantype("999");
		chanCommV10.setProdid("");
		chanCommV10.setMserialn("");
		chanCommV10.setTermid("");
		chanCommV10.setField1("0");
		chanCommV10.setTermtype("0");
		chanCommV10.setSerialno("19994");
		chanCommV10.setCobprodid("");
		bizContent.setChanCommV10(chanCommV10);
		//7.2 交易基础信息
		RmbexchangeTransferRequestV2.RequestBiz.InfoCommV10 infoCommV10 = new RmbexchangeTransferRequestV2.RequestBiz.InfoCommV10();
		infoCommV10.setTrxtype("1");
		infoCommV10.setTrxcode("11111");
		infoCommV10.setZoneno("200");
		infoCommV10.setBrno("145");
		infoCommV10.setTellerno("10");
		infoCommV10.setWorkdate("2024-10-17");
		infoCommV10.setWorktime("11:00:30");
		infoCommV10.setRevtranf("0");
		bizContent.setInfoCommV10(infoCommV10);
		//7.3 FMTA应用通用通讯区
		RmbexchangeTransferRequestV2.RequestBiz.FmtaCommV10 fmtaCommV10 = new RmbexchangeTransferRequestV2.RequestBiz.FmtaCommV10();
		fmtaCommV10.setCkUsrFlag("0");
		fmtaCommV10.setCustKind("2");
		fmtaCommV10.setServFace("0");
		fmtaCommV10.setpTrxSerno("0");
		fmtaCommV10.setWdflag("0");
		bizContent.setFmtaCommV10(fmtaCommV10);
		//7.4 认证公共通讯区
		RmbexchangeTransferRequestV2.RequestBiz.AuthCommV10 authCommV10 = new RmbexchangeTransferRequestV2.RequestBiz.AuthCommV10();
		authCommV10.setTellerCheckFlag("10");
		authCommV10.setAuthZoneNo("0");
		authCommV10.setAuthFlag("0");
		authCommV10.setAuthPassword("");
		authCommV10.setAuthBrNo("0");
		bizContent.setAuthCommV10(authCommV10);
		//7.5 服务私有通讯区
		RmbexchangeTransferRequestV2.RequestBiz.PrivateArea privateArea = new RmbexchangeTransferRequestV2.RequestBiz.PrivateArea();
		privateArea.setCvv1("");
		privateArea.setScustNum("JA0035");
		privateArea.setCvv2("");
		privateArea.setSpcRate("");
		privateArea.setSvrCode1("");
		privateArea.setSvrCode2("");
		privateArea.setAccno("");
		privateArea.setTuFlag("0");
		privateArea.setMlReadf1("1");
		privateArea.setMlReadf2("1");
		privateArea.setPreTelno("");
		privateArea.setPreTrxsq("");
		privateArea.setAllwTime("");
		privateArea.setHzFlag2("0");
		privateArea.setJfCurr("0");
		privateArea.setNewCino("");
		privateArea.setCjFlag("0");
		privateArea.setAbstrac1("");
		privateArea.setHzFlag1("0");
		privateArea.setAccPin1("");
		privateArea.setValueDay("");
		privateArea.setFloatPoint("");
		privateArea.setAccPin2("");
		privateArea.setMdCardNo1("6222020200700112705");
		privateArea.setTimeStmp("");
		privateArea.setYxFlag("0");
		privateArea.setPhyBrno2("");
		privateArea.setExcRate("726250000000");
		privateArea.setCashNote("个售");
		privateArea.setExFlag("1");
		privateArea.setSafePara("");
		privateArea.setGhhkFlag("");
		privateArea.setYhType("0");
		privateArea.setAcSerno2("");
		privateArea.setSealf1("");
		privateArea.setAcSerno1("");
		privateArea.setSealf2("");
		privateArea.setScustKind("1");
		privateArea.setCrosf1("");
		privateArea.setYhCurr("0");
		privateArea.setCrosf2("");
		privateArea.setName("");
		privateArea.setCashExf1("1");
		privateArea.setAuthCino("");
		privateArea.setVouhType2("");
		privateArea.setVouhType1("16");
		privateArea.setCatrFlag("1");
		privateArea.setBusiRefno("");
		privateArea.setAccName2("小曦");
		privateArea.setAccName1("小曦");
		privateArea.setAccno1("");
		privateArea.setCurrType("001");
		privateArea.setFloatMod("");
		privateArea.setAccFlag("0");
		privateArea.setVouhNo1("");
		privateArea.setVouhNo2("");
		privateArea.setAccode2("");
		privateArea.setAccode1("");
		privateArea.setYhRate("0");
		privateArea.setAmount1("100000");
		privateArea.setMdCardNo("6222020200700112705");
		privateArea.setSummary("");
		privateArea.setYhAmt("0");
		privateArea.setCurrType1("014");
		privateArea.setAmount("726250");
		privateArea.setPhyBrno1("");
		privateArea.setFmlName("");
		privateArea.setStatCode("2063310");
		privateArea.setSettMode("999");
		privateArea.setJfAmt("0");
		privateArea.setCvvFlag("");
		privateArea.setActBrno1("");
		privateArea.setJshType("");
		privateArea.setExpDate2("");
		privateArea.setActBrno2("");
		privateArea.setExpDate1("");
		privateArea.setYxDiscount("0");
		privateArea.setUpdTranf("0");
		privateArea.setOprotsen("");
		privateArea.setCashExf("1");
		privateArea.setAccPin("aeb06fa815da76e6");
		privateArea.setErrDate("");
		privateArea.setCatrFlag1("1");
		privateArea.setYxUplimit("0");
		privateArea.setRvFlag("");
		privateArea.setAcFlag1("");
		privateArea.setCatrFlag2("1");
		privateArea.setAcFlag2("");


		bizContent.setPrivateArea(privateArea);

		//7.6 外管报送私有信息
		RmbexchangeTransferRequestV2.RequestBiz.PrivateAreaReport privateAreaReport = new RmbexchangeTransferRequestV2.RequestBiz.PrivateAreaReport();
		privateAreaReport.setCapitalNo("");
		privateAreaReport.setRemark("便利化薪酬购汇");
		privateAreaReport.setIndivOrgCode("");
		privateAreaReport.setFcyAcctAmt("100000");
		privateAreaReport.setPassWord("AE3CFA2AD595F51936A7FC634FEFF30D");
		privateAreaReport.setReportType("3");
		privateAreaReport.setMsgNo("00022030010114631190");
		privateAreaReport.setTchkAmt("0");
		privateAreaReport.setSalefxSettleCode("");
		privateAreaReport.setIdTypeCode("09");
		privateAreaReport.setCommonOrgCode("110108000205");
		privateAreaReport.setAgentCorpCode("");
		privateAreaReport.setIdCode("JA0035");
		privateAreaReport.setPurfxCashAmt("0");
		privateAreaReport.setBizTypeCode("12");
		privateAreaReport.setCommonUserCode("NET_BJ80");
		privateAreaReport.setCommonPassWord("AE3CFA2AD595F51936A7FC634FEFF30D");
		privateAreaReport.setAddIdCode("654201194401069325");
		privateAreaReport.setFcyRemitAmt("0");
		privateAreaReport.setChiefUserCode("NET_BJ80");
		privateAreaReport.setTypeCode("331");
		privateAreaReport.setTxCcy("USD");
		privateAreaReport.setPersonName("小曦");
		privateAreaReport.setIndivOrgName("");
		privateAreaReport.setBizTypeCode("03");
		privateAreaReport.setCtyCode("CHN");
		privateAreaReport.setPayOrgCode("");
		privateAreaReport.setAgentCorpName("");
		bizContent.setPrivateAreaReport(privateAreaReport);

		//7.5 购汇申请书私有信息
		RmbexchangeTransferRequestV2.RequestBiz.PrivateAreaApplication privateAreaApplication = new RmbexchangeTransferRequestV2.RequestBiz.PrivateAreaApplication();
		privateAreaApplication.setExchangeUse("");
		privateAreaApplication.setExchangeDate("");
		privateAreaApplication.setNote2("");
		privateAreaApplication.setUseMassage1("");
		privateAreaApplication.setUseMassage2("");
		privateAreaApplication.setUseMassage3("");
		privateAreaApplication.setUseMassage4("");
		privateAreaApplication.setUseMassage5("");
		privateAreaApplication.setUseMassage6("");
		privateAreaApplication.setUseMassage7("");
		privateAreaApplication.setUseMassage8("");
		privateAreaApplication.setUseMassage9("");
		privateAreaApplication.setChTrxcode("2001");
		privateAreaApplication.setAgCustName("");
		privateAreaApplication.setAgCustId("");
		privateAreaApplication.setNote1("");
		bizContent.setPrivateAreaApplication(privateAreaApplication);

		
		request.setBizContent(bizContent);

		RmbexchangeTransferResponseV2 response;
		try {
			System.out.println("request:"+ JSON.toJSONString(request));
			response = client.execute(request);
			System.out.println("response:" + JSON.toJSONString(response));
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}
	}
}
