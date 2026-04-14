package com.icbc.api;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.icbc.api.request.MybankLoanInclufinChainloanCustinfouploadRequestV1;
import com.icbc.api.request.MybankLoanInclufinChainloanCustinfouploadRequestV1Biz;
import com.icbc.api.request.MybankLoanInclufinChainloanCustinfouploadRequestV1Biz.TransDtl;
import com.icbc.api.response.MybankLoanInclufinChainloanCustinfouploadResponseV1;

public class MybankLoanInclufinChainloanCustinfouploadTestV1 {

	// APP_ID,APIGW_PUBLIC_KEY,PRI_KEY,URL 根据实际的应用场景进行修改
	protected static final String APP_ID = "xxxxxxx";
	protected static final String APIGW_PUBLIC_KEY = "xxxxxxx";
	protected static final String PRI_KEY = "xxxxxxx";
	protected static final String URL = "http://xxxxxxx/api/mybank/loan/inclufin/chainloan/custinfoupload/V1";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MybankLoanInclufinChainloanCustinfouploadRequestV1 request = new MybankLoanInclufinChainloanCustinfouploadRequestV1();
		MybankLoanInclufinChainloanCustinfouploadResponseV1 response = new MybankLoanInclufinChainloanCustinfouploadResponseV1();
		MybankLoanInclufinChainloanCustinfouploadRequestV1Biz bizContent = new MybankLoanInclufinChainloanCustinfouploadRequestV1Biz();

		// 公共参数
		bizContent.setSerialNo("201708231441XX020083999");
		bizContent.setAppNo("F-APIP");
		bizContent.setAreaCode("0120XX0000");
		bizContent.setEmployeeCode("0200XX999");
		bizContent.setLanguage("zh_CN");
		bizContent.setTransNo("ISCCustomerCocoaService");
		bizContent.setVer("1.0");
		// 私有参数
		bizContent.setApplyNo("123");
		bizContent.setScNo("9807f");
		bizContent.setProductCode("01");
		bizContent.setCustNo("ksj");
		bizContent.setCustName("dasccs");
		bizContent.setVoucherType("3");
		bizContent.setVoucherNo("alipl");
		bizContent.setFcustCertType("0");
		bizContent.setFcustCertCode("5465432136");
		bizContent.setFcustName("dasdasdasd");
		bizContent.setJcustName("ddddd");
		bizContent.setJcustNotes("908908@dlip");
		bizContent.setJcustPhone("123123123");
		bizContent.setApplyTime("20111111");
		bizContent.setCopoTime("20200101");
		bizContent.setCopoYearLimit(3);
		bizContent.setCustLevel("1");
		bizContent.setCustIsRelation("1");
		bizContent.setCustRelation("10");
		bizContent.setIsSign("1");
		bizContent.setSignTime("20200101");
		bizContent.setTransCycle("1");
		bizContent.setTransCycleNum(364);
		bizContent.setRetransAmount(new BigDecimal(54));
		bizContent.setRetransNum(new BigDecimal(14));
		bizContent.setTransUnit("2");
		bizContent.setTransDsec("ddddddd");
		bizContent.setRemark1("ssssss");
		bizContent.setRemark2("1111111");
		bizContent.setRemark3("33333333");
		bizContent.setTransJRate(new BigDecimal(13));
		bizContent.setTransFRate(new BigDecimal(13));
		bizContent.setTransnumJRate(new BigDecimal(54));
		bizContent.setTransnumFRate(new BigDecimal(13));
		bizContent.setPayPeriod(12);
		bizContent.setLimitNum(new BigDecimal(13000.00));
		bizContent.setOprationType("1");
		List<TransDtl> transDtlList = new ArrayList();
		TransDtl t = new TransDtl();
		t.setSeq(1);
		t.setTransTime("2021W1");
		t.setTransAmount(new BigDecimal(13));
		t.setTransNum(new BigDecimal(1));
		t.setTransUnit("2");
		t.setTransAmountRate(new BigDecimal(12));
		t.setTransNumRate(new BigDecimal(10));
		transDtlList.add(t);
		bizContent.setTransDtlList(transDtlList);

		List<CounterpartyDtl> counterpartyDtlList = new ArrayList();
		counterpartyDtlList c = new counterpartyDtlList();
		c.setSeq(1);
		c.setIsBankFlag("1");
		c.setCounterpartyName("sjjdj");
		c.setCounterpartyCino("11112222");
		c.setCounCertType("0");
		c.setCounCertCode("123456");
		c.setAccountCode("1111");
		c.setAccountCodeName("jxjx");
		c.setAccountBankName("xksj");
		c.setAccountBankNo("111111111");
		counterpartyDtlList.add(c);
		bizContent.setCounterpartyDtlList(counterpartyDtlList);

		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", PRI_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, null, null, null, null);

		request.setServiceUrl(URL);
		request.setBizContent(bizContent);

		try {
			response = client.execute(request);
		} catch (Exception e) {
			// log
		}
		if (response.isSuccess()) {
			// succ
		} else {
			// fail
		}

	}

}
