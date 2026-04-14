package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.SalaryFxpurchaseLedgerinfoMaintainRequestV1;
import com.icbc.api.response.SalaryFxpurchaseLedgerinfoMaintainResponseV1;

import java.util.ArrayList;
import java.util.List;

public class SalaryFxpurchaseLedgerinfoMaintainV1Test {
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
		SalaryFxpurchaseLedgerinfoMaintainRequestV1 request = new SalaryFxpurchaseLedgerinfoMaintainRequestV1();

		//6、设置请求路径；联测时，请联系我行告知并替换成测试环境地址。
		request.setServiceUrl("http://ip:port/api/salary/fxpurchase/ledgerinfo/maintain/V1");
		//7、设置业务参数
		//7.1 渠道要素信息
		SalaryFxpurchaseLedgerinfoMaintainRequestV1.RequestBiz bizContent = new SalaryFxpurchaseLedgerinfoMaintainRequestV1.RequestBiz();
		SalaryFxpurchaseLedgerinfoMaintainRequestV1.RequestBiz.ChanCommV10 chanCommV10 = new SalaryFxpurchaseLedgerinfoMaintainRequestV1.RequestBiz.ChanCommV10();
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
		SalaryFxpurchaseLedgerinfoMaintainRequestV1.RequestBiz.InfoCommV10 infoCommV10 = new SalaryFxpurchaseLedgerinfoMaintainRequestV1.RequestBiz.InfoCommV10();
		infoCommV10.setTrxtype("1");
		infoCommV10.setTrxcode("11111");
		infoCommV10.setZoneno("200");
		infoCommV10.setBrno("145");
		infoCommV10.setTellerno("10");
		infoCommV10.setWorkdate("2024-10-17");
		infoCommV10.setWorktime("11:00:30");
		infoCommV10.setRevtranf("0");
		bizContent.setInfoCommV10(infoCommV10);
		//7.4 认证公共通讯区
		SalaryFxpurchaseLedgerinfoMaintainRequestV1.RequestBiz.AuthCommV10 authCommV10 = new SalaryFxpurchaseLedgerinfoMaintainRequestV1.RequestBiz.AuthCommV10();
		authCommV10.setTellerCheckFlag("10");
		authCommV10.setAuthZoneNo("0");
		authCommV10.setAuthFlag("0");
		authCommV10.setTellerPassword("");
		authCommV10.setAuthPassword("");
		authCommV10.setAuthBrNo("0");
		bizContent.setAuthCommV10(authCommV10);
		//7.5 客户信息私有通讯区
		SalaryFxpurchaseLedgerinfoMaintainRequestV1.RequestBiz.CustomerPrivateArea customerPrivateArea = new SalaryFxpurchaseLedgerinfoMaintainRequestV1.RequestBiz.CustomerPrivateArea();
		customerPrivateArea.setModCreationChannel("2");
		customerPrivateArea.setTellerBranch("0010100500");
		customerPrivateArea.setOperFlag("1");
		customerPrivateArea.setCino("12345");
		customerPrivateArea.setReportingNotes("外管局报送备注");
		customerPrivateArea.setPhoneNo("13545885719");
		customerPrivateArea.setTotalTaxPaidAmount("665");
		customerPrivateArea.setAffiliatedInstitution("0010100500");
		customerPrivateArea.setCreationChannel("2");
		customerPrivateArea.setAccumulatedUsedAmount("1000");
		customerPrivateArea.setTellerNo("10");
		customerPrivateArea.setModTellerBranch("0010100500");
		customerPrivateArea.setModTellerNo("11");
		customerPrivateArea.setIsProxyCustomer("1");
		customerPrivateArea.setObcyAccumulatedUsedAmount("10000");
		customerPrivateArea.setCreatingInstitution("0010100500");
		customerPrivateArea.setRfepAmount("1000");
		customerPrivateArea.setRetailModelCumPostTaxAmount("100");
		customerPrivateArea.setBankModelCumPostTaxAmount("100");
		bizContent.setCustomerPrivateArea(customerPrivateArea);

		//7.6 他行私有通讯区
		SalaryFxpurchaseLedgerinfoMaintainRequestV1.RequestBiz.OtherBankPrivateArea otherBankPrivateArea = new SalaryFxpurchaseLedgerinfoMaintainRequestV1.RequestBiz.OtherBankPrivateArea();
		//7.6.1 他行完税收入列表
		List<SalaryFxpurchaseLedgerinfoMaintainRequestV1.RequestBiz.LedgerOtherBankTaxPaymentDetail> otherBankTaxPaymentDetailList = new ArrayList<>();
		SalaryFxpurchaseLedgerinfoMaintainRequestV1.RequestBiz.LedgerOtherBankTaxPaymentDetail ledgerOtherBankTaxPaymentDetail = new SalaryFxpurchaseLedgerinfoMaintainRequestV1.RequestBiz.LedgerOtherBankTaxPaymentDetail();
		ledgerOtherBankTaxPaymentDetail.setPostTaxBeginDate("2023-10");
		ledgerOtherBankTaxPaymentDetail.setPostTaxEndDate("2023-12");
		ledgerOtherBankTaxPaymentDetail.setUnitName("黑色玫瑰");
		ledgerOtherBankTaxPaymentDetail.setContractEapNo("合同批次号a");
		//7.6.2 他行单个完税收入下购汇明细列表
		List<SalaryFxpurchaseLedgerinfoMaintainRequestV1.RequestBiz.LedgerOtherBankForexPurchaseDetail> otherBankForexPurchaseDetailList = new ArrayList<>();
		SalaryFxpurchaseLedgerinfoMaintainRequestV1.RequestBiz.LedgerOtherBankForexPurchaseDetail ledgerOtherBankForexPurchaseDetail = new SalaryFxpurchaseLedgerinfoMaintainRequestV1.RequestBiz.LedgerOtherBankForexPurchaseDetail();
		ledgerOtherBankForexPurchaseDetail.setForexPurchaseBeginDate("2023-05-01");
		ledgerOtherBankForexPurchaseDetail.setForexPurchaseAmount("1000");
		ledgerOtherBankForexPurchaseDetail.setForexPurchaseEndDate("2023-05-01");
		ledgerOtherBankForexPurchaseDetail.setForexPurchaseEapNo("购汇批次号1");
		ledgerOtherBankForexPurchaseDetail.setForexPurchaseBank("购汇银行1");
		otherBankForexPurchaseDetailList.add(ledgerOtherBankForexPurchaseDetail);
		SalaryFxpurchaseLedgerinfoMaintainRequestV1.RequestBiz.LedgerOtherBankForexPurchaseDetail ledgerOtherBankForexPurchaseDetail1 = new SalaryFxpurchaseLedgerinfoMaintainRequestV1.RequestBiz.LedgerOtherBankForexPurchaseDetail();
		ledgerOtherBankForexPurchaseDetail1.setForexPurchaseBeginDate("2023-06-01");
		ledgerOtherBankForexPurchaseDetail1.setForexPurchaseAmount("2000");
		ledgerOtherBankForexPurchaseDetail1.setForexPurchaseEndDate("2023-06-01");
		ledgerOtherBankForexPurchaseDetail1.setForexPurchaseEapNo("购汇批次号2");
		ledgerOtherBankForexPurchaseDetail1.setForexPurchaseBank("购汇银行2");
		otherBankForexPurchaseDetailList.add(ledgerOtherBankForexPurchaseDetail1);
		ledgerOtherBankTaxPaymentDetail.setOtherBankForexPurchaseDetailList(otherBankForexPurchaseDetailList);
		otherBankTaxPaymentDetailList.add(ledgerOtherBankTaxPaymentDetail);
		otherBankPrivateArea.setOtherBankTaxPaymentDetailList(otherBankTaxPaymentDetailList);
		bizContent.setOtherBankPrivateArea(otherBankPrivateArea);

		//7.7 我行私有通讯区
		SalaryFxpurchaseLedgerinfoMaintainRequestV1.RequestBiz.OurBankPrivateArea ourBankPrivateArea = new SalaryFxpurchaseLedgerinfoMaintainRequestV1.RequestBiz.OurBankPrivateArea();
		//7.7.1 我行劳动合同列表
		List<SalaryFxpurchaseLedgerinfoMaintainRequestV1.RequestBiz.LedgerOurBankLaborContract> ourBankLaborContractList = new ArrayList<>();
		SalaryFxpurchaseLedgerinfoMaintainRequestV1.RequestBiz.LedgerOurBankLaborContract ledgerOurBankLaborContract = new SalaryFxpurchaseLedgerinfoMaintainRequestV1.RequestBiz.LedgerOurBankLaborContract();
		ledgerOurBankLaborContract.setUnitName("艾欧尼亚");
		ledgerOurBankLaborContract.setContractBeginDate("2023-01");
		ledgerOurBankLaborContract.setContractEndDate("2024-04");
		ledgerOurBankLaborContract.setContractEapNo("合同批次号1");
		//ip:port 我行单个劳动合同下完税收入证明列表
		List<SalaryFxpurchaseLedgerinfoMaintainRequestV1.RequestBiz.LedgerOurBankPostTaxAmount> ourBankPostTaxAmountList = new ArrayList<>();
		SalaryFxpurchaseLedgerinfoMaintainRequestV1.RequestBiz.LedgerOurBankPostTaxAmount ledgerOurBankPostTaxAmount = new SalaryFxpurchaseLedgerinfoMaintainRequestV1.RequestBiz.LedgerOurBankPostTaxAmount();
		ledgerOurBankPostTaxAmount.setPostTaxBeginDate("2023-01");
		ledgerOurBankPostTaxAmount.setPostTaxEndDate("2023-10");
		ledgerOurBankPostTaxAmount.setPostTaxEapNo("完税批次号1");
		ledgerOurBankPostTaxAmount.setPostTaxAmount("100");
		ledgerOurBankPostTaxAmount.setCooperationModel("1");
		ourBankPostTaxAmountList.add(ledgerOurBankPostTaxAmount);
		SalaryFxpurchaseLedgerinfoMaintainRequestV1.RequestBiz.LedgerOurBankPostTaxAmount ledgerOurBankPostTaxAmount1 = new SalaryFxpurchaseLedgerinfoMaintainRequestV1.RequestBiz.LedgerOurBankPostTaxAmount();
		ledgerOurBankPostTaxAmount1.setPostTaxBeginDate("2024-02");
		ledgerOurBankPostTaxAmount1.setPostTaxEndDate("2024-04");
		ledgerOurBankPostTaxAmount1.setPostTaxEapNo("完税批次号2");
		ledgerOurBankPostTaxAmount1.setPostTaxAmount("200");
		ledgerOurBankPostTaxAmount1.setCooperationModel("1");
		ourBankPostTaxAmountList.add(ledgerOurBankPostTaxAmount1);
		ledgerOurBankLaborContract.setOurBankPostTaxAmountList(ourBankPostTaxAmountList);
		ourBankLaborContractList.add(ledgerOurBankLaborContract);
		ourBankPrivateArea.setOurBankLaborContractList(ourBankLaborContractList);
		bizContent.setOurBankPrivateArea(ourBankPrivateArea);
		request.setBizContent(bizContent);

		SalaryFxpurchaseLedgerinfoMaintainResponseV1 response;
		try {
			System.out.println("request:"+ JSON.toJSONString(request));
			response = client.execute(request);
			System.out.println("response:" + JSON.toJSONString(response));
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}
	}
}
