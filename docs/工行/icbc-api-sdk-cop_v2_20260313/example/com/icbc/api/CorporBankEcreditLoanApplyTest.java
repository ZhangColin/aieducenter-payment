package com.icbc.api;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcConstants;
import com.icbc.api.UiIcbcClient;
import com.icbc.api.request.CorporbankEcreditLoanapplyRequestV1;
import com.icbc.api.request.CorporbankEcreditLoanapplyRequestV1.CorporbankEcreditLoanapplyRequestV1Biz;
import com.icbc.api.request.CorporbankEcreditLoanapplyRequestV1.CorporbankEcreditLoanapplyRequestV1Biz.CorporbankEcreditLoanapplyBillInfo;
import com.icbc.api.request.CorporbankEcreditLoanapplyRequestV1.CorporbankEcreditLoanapplyRequestV1Biz.CorporbankEcreditLoanapplyECredit;
import com.icbc.api.request.CorporbankEcreditLoanapplyRequestV1.CorporbankEcreditLoanapplyRequestV1Biz.CorporbankEcreditLoanapplyRePayInfo;
import com.icbc.api.request.CorporbankEcreditLoanapplyRequestV1.CorporbankEcreditLoanapplyRequestV1Biz.CorporbankEcreditLoanapplyTradeInfo;
import com.icbc.api.request.CorporbankEcreditLoanapplyRequestV1.CorporbankEcreditLoanapplyRequestV1Biz.CorporbankEcreditLoanapplyTradeInfoText;

public class CorporBankEcreditLoanApplyTest {

	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC7kn0WqNjJeVh9WaToArJ2Tadz+sgHGdYr9sCvakGJ/pvL281hhHVdIyBCNKfTCOwsWixta/2I06t9IBO2yXd+iInG65fprlhvmFcW70T1QHGMUpF41uMQmgJ90yfJ/zFW+jtGm1Aa4BumEhC7h+7b6aw7UUyfRxl0PoooPQ7cZpOZeEsypyBADnSdBBCJAdqS2+vO1vDGpLEqujOtC59mJyBKveL8ogmOVyhsTsOhkWeaCSTLq2AHfcH8vLCACXBFyu973oRxqiwGqTPENFyNGFOnZR0u55hgzJa8mWf60sFQBeM4pc1FNdrMOzWrr10q1dSvHVHwsUJvGtxGSBNHAgMBAAECggEAWtNGCtDbmENP2/KqbUXREIPtj7VhYj7afBNRnh7/eUh2PDJEsvGmuuClYCmLEh8B7UAEvz9JVxXXthDwOtQ3LmgC/vVZa+fmHrNPiGV3qKH52Y6pri+vZ3yLtE2ouKD/TTSBu2C9WyYlfTbGL2hU+tudcGOfjIirBKS71VwrdADJ8H9epluds54Rq4wr59WGlkYiQfimGMZo8pyDqnMBwlCdCEkzIaBRlBijrqfOFUoM6fMBlmv7SEPv8v43Ga0Wf+YWFRJ/8MXx4qDXhoiTxvMbn8rosPANz2FJj9OzdWGTRObA24TvdpV6e6UlKE7Rpj/14z/9gbgh575vP6vjsQKBgQD1DSp3LoEskFZSu9C60uVmL9cAthd16w6p9FvxjdAwoBvUUd/HL1ATjiI4IGjrODUkSKFVCoQpKPxTEf5m0b9oNCl5WczPy36Eaw2N0n0xTfa7KOmlZSdEZExOqzc7gx+UAPMwF+yYi3lZDlbVVRd4jFaNjOjebpUkwujwbnoNCwKBgQDD8+P+yjmBhxAqczMMfqWK9cTxOg6/C5CX3NQE2l3H0sE69aOQBolCS7JRkyJTgYtbV3j7YsGF/SaMTkoa9RFqXbbnYp33rROskpEo6Ovsy5ZzUFWdBuCfXpZxM26zFPeIMEG3atRBQ/+E8YIUsJbxBIQueI4/YZo9Q6SouWYgNQKBgQDlx+Oy//N2Yx3e1GhkVs9uo5ta+mRDWRdV/GGevpiij++ohuFsOAxBWfzyXkmjjwAdttE5GfRVWC5Kgsvng/SUdJ0TQkZP7wQCjROXkfF8oxuh4jo8sf5BHj+DYP8YmdqpgL6IsVZUsCwoes6nmfO5QPcEIkZWFKFbaDze/ukJgwKBgCC++TLzCHkk4Qa3Cajk4TU/rRHC6gPzIs6+EJBfDZz/E1uPAS5oaTey/rGzLOFXnLo++/DGqCCEFZUM4EVw/uAPmSlBybRCMC9+rtNvWT3RJ+tNWYFMTO1VGT8BoVY8AsG3jsPgDmh5vJGy6lIa0J6mapB5hQwCe4wRacrocLChAoGAFo6PqUHcJP9DR1oPQ52VRdi85SiCVrb/5p8ggL+eskxm6HK3sOpjah9qyCKUhYBIAqxalxoAIPLnLt2WvpZlBbl4Y5i1k2dsRYlMIt4veuSOSi6Mp5tgXefd4tC5trTsDCFP2tNs9eCm2hBjXmd2Vg348g6vdc7Ps/ztNyM5eU8=";	
	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
	
	protected static final String APP_ID = "10000000000000005031";
	
	protected static final String PASSWORD = "12345678";


	public void test_cop() throws IcbcApiException, UnsupportedEncodingException {
		

		// 构造client对象
		UiIcbcClient client = new UiIcbcClient(APP_ID, MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8);

		// 设置请求对象request
		CorporbankEcreditLoanapplyRequestV1 request = new CorporbankEcreditLoanapplyRequestV1();
		// 设置请求路径
		request.setServiceUrl("http://ip:port/ui/corporbank/ecredit/loanapply/V1");

		CorporbankEcreditLoanapplyRequestV1Biz bizContent = new CorporbankEcreditLoanapplyRequestV1Biz();

		bizContent.setApi_name("ECREDIT");
		bizContent.setApi_version("001.001.001.001");
		//bizContent.setPlatformNo("PF001");
		bizContent.setProductType("abc");
		bizContent.setCustomerName("金网络交易平台");
		bizContent.setCustomerNo("020090000018907");
		bizContent.setEnterpriseNo("1234567890");
		bizContent.setEnterpriseName("XXX");
		bizContent.setApplyMoney("1188");
		bizContent.setApplyNo("65");
		bizContent.setSecretFlag("1");
		bizContent.setEcreditType("01");
	 
		bizContent.setConfirmType("1");
		bizContent.setNoPayType("0");
		bizContent.setBeginDate("20210701");
		bizContent.setEndDate("20180601");
		bizContent.setLoanCur("001");
		bizContent.setInterestMode("01");
	 
		bizContent.setRepayMent("01");
		bizContent.setPlatChargeRate("0.18");
		bizContent.setPlatCharge("1000");
	 
		bizContent.setDealTerm("100");
		bizContent.setDealVitality("A");
		bizContent.setDealMoneyRankRate("0.16");
	 
		bizContent.setDisputeRate("0.18");
		bizContent.setEvaluation("A");
		List<CorporbankEcreditLoanapplyTradeInfo> tradeinfolist = new ArrayList<CorporbankEcreditLoanapplyTradeInfo>();
		List<CorporbankEcreditLoanapplyRePayInfo> repayinfolist = new ArrayList<CorporbankEcreditLoanapplyRePayInfo>();
		List<CorporbankEcreditLoanapplyTradeInfoText> tradeinfotextlist = new ArrayList<CorporbankEcreditLoanapplyTradeInfoText>();
		List<CorporbankEcreditLoanapplyBillInfo> billinfolist = new ArrayList<CorporbankEcreditLoanapplyBillInfo>();
		List<CorporbankEcreditLoanapplyECredit> ecreditlist = new ArrayList<CorporbankEcreditLoanapplyECredit>();
		

		CorporbankEcreditLoanapplyTradeInfo tradeinfo1=new CorporbankEcreditLoanapplyTradeInfo();
		CorporbankEcreditLoanapplyTradeInfo tradeinfo2=new CorporbankEcreditLoanapplyTradeInfo();
		//贸易编号1
		tradeinfo1.setTradeInfoNo("105025610"); 
		tradeinfo1.setTradeInfoAmt("1050"); 
		  
		//贸易编号2  
		tradeinfo2.setTradeInfoNo("105025612"); 
		tradeinfo2.setTradeInfoAmt("1100"); 
		
		tradeinfolist.add(tradeinfo1);
		tradeinfolist.add(tradeinfo2);
		  
		bizContent.setiTradeInfoList(tradeinfolist);
	  
		CorporbankEcreditLoanapplyBillInfo billinfo1=new CorporbankEcreditLoanapplyBillInfo();
		CorporbankEcreditLoanapplyBillInfo billinfo2 = new CorporbankEcreditLoanapplyBillInfo();

		// 融资发票1
		billinfo1.setTradeInfoNo("105025610");
		billinfo1.setBillSerialNo("451542051");
		billinfo1.setBillCode("5153418");
		billinfo1.setBillNum("445454");

		billinfo1.setBillMoney("455");
		billinfo1.setBillDate("20180401");
		// 融资发票2
		billinfo2.setTradeInfoNo("105025612");
		billinfo2.setBillSerialNo("451542052");
		billinfo2.setBillCode("5153424");
		billinfo2.setBillNum("445455");

		billinfo2.setBillMoney("460");
		billinfo2.setBillDate("20180402");

		billinfolist.add(billinfo1);
		billinfolist.add(billinfo2);

		bizContent.setiBillInfoList(billinfolist);

		CorporbankEcreditLoanapplyECredit ecredit1 = new CorporbankEcreditLoanapplyECredit();
		CorporbankEcreditLoanapplyECredit ecredit2 = new CorporbankEcreditLoanapplyECredit();

		// 数字信用凭证持有人1
		ecredit1.setTradeInfoNo("105025610");
		ecredit1.setEcreditSerialNo("1545");
		ecredit1.setPrevECreditNo("2113215");
		ecredit1.setEcreditNo("24151512");
		ecredit1.setEcreditMoney("54545");
		ecredit1.setEcreditEntNo("564154201");
		ecredit1.setEcreditEntName("金网络交易平台");
		ecredit1.setEcreditCertiType("SZ");
		ecredit1.setEcreditCertiNo("12541512");
		ecredit1.setEcreditEntTaxID("54524524");
		ecredit1.setPayDate("20180510");
		ecredit1.setTransfTime("20180501083036");
		ecredit1.setEcreditText("SSSSSS");
		ecredit1.setEcreditSign("ASFSA7451SAD");
		ecredit1.setEcreditSignTime("20180502083036");
		ecredit1.setCertiNo("564154201");
		ecredit1.setCertiType("ICBC");

		// 数字信用凭证持有人2
		ecredit2.setTradeInfoNo("105025612");
		ecredit2.setEcreditSerialNo("1546");
		ecredit2.setPrevECreditNo("2113215");
		ecredit2.setEcreditNo("24151515");
		ecredit2.setEcreditMoney("54548");
		ecredit2.setEcreditEntNo("564154209");
		ecredit2.setEcreditEntName("金网络交易平台");
		ecredit2.setEcreditCertiType("SZ");
		ecredit2.setEcreditCertiNo("12541519");
		ecredit2.setEcreditEntTaxID("54524528");
		ecredit2.setPayDate("20180512");
		ecredit2.setTransfTime("20180502083036");
		ecredit2.setEcreditText("SSSSSS");
		ecredit2.setEcreditSign("ASFSA7451SWD");
		ecredit2.setEcreditSignTime("20180503083036");
		ecredit2.setCertiNo("564154208");
		ecredit2.setCertiType("ICBC");

		ecreditlist.add(ecredit1);
		ecreditlist.add(ecredit2);

		bizContent.setiECreditList(ecreditlist);

		CorporbankEcreditLoanapplyTradeInfoText tradeinfotext1 = new CorporbankEcreditLoanapplyTradeInfoText();
		CorporbankEcreditLoanapplyTradeInfoText tradeinfotext2 = new CorporbankEcreditLoanapplyTradeInfoText();

		// 贸易信息影像文件编号1
		tradeinfotext1.setTradeInfoNo("105025610");
		tradeinfotext1.setContractNo("51505115");

		// 贸易信息影像文件编号2
		tradeinfotext2.setTradeInfoNo("105025612");
		tradeinfotext2.setContractNo("51505116");

		tradeinfotextlist.add(tradeinfotext1);
		tradeinfotextlist.add(tradeinfotext2);
		bizContent.setiTradeInfoTextList(tradeinfotextlist);

		CorporbankEcreditLoanapplyRePayInfo repayinfo1 = new CorporbankEcreditLoanapplyRePayInfo();
		CorporbankEcreditLoanapplyRePayInfo repayinfo2 = new CorporbankEcreditLoanapplyRePayInfo();
		// 受托支付信息1
		repayinfo1.setCountpartyName("AAAA");
		repayinfo1.setCountpartyAccount("452121512");
		repayinfo1.setCountpartyType("1");
		repayinfo1.setAmtUse("1");
		repayinfo1.setReceiveAmount("452165");
		repayinfo1.setOpenBank("金网络交易平台");

		// 受托支付信息2
		repayinfo2.setCountpartyName("AAAABN");
		repayinfo2.setCountpartyAccount("452121519");
		repayinfo2.setCountpartyType("1");
		repayinfo2.setAmtUse("2");
		repayinfo2.setReceiveAmount("452160");
		repayinfo2.setOpenBank("金网络交易平台");

		repayinfolist.add(repayinfo1);
		repayinfolist.add(repayinfo2);

		bizContent.setiRePayInfoList(repayinfolist);

		request.setBizContent(bizContent);
		System.out.println(client.buildPostForm(request));

	
		
	}
}
