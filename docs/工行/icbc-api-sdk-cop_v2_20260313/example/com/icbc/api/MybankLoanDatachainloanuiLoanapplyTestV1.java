package com.icbc.api;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcConstants;
import com.icbc.api.UiIcbcClient;
import com.icbc.api.request.MybankLoanDatachainloanuiLoanapplyRequestV1;
import com.icbc.api.request.MybankLoanDatachainloanuiLoanapplyRequestV1.MybankLoanDatachainloanuiLoanapplyRequestV1Biz;
import com.icbc.api.request.MybankLoanDatachainloanuiLoanapplyRequestV1.MybankLoanDatachainloanuiLoanapplyRequestV1Biz.MybankLoanDatachainloanuiLoanapplyTradeInfo;

public class MybankLoanDatachainloanuiLoanapplyTestV1 {

	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC7kn0WqNjJeVh9WaToArJ2Tadz+sgHGdYr9sCvakGJ/pvL281hhHVdIyBCNKfTCOwsWixta/2I06t9IBO2yXd+iInG65fprlhvmFcW70T1QHGMUpF41uMQmgJ90yfJ/zFW+jtGm1Aa4BumEhC7h+7b6aw7UUyfRxl0PoooPQ7cZpOZeEsypyBADnSdBBCJAdqS2+vO1vDGpLEqujOtC59mJyBKveL8ogmOVyhsTsOhkWeaCSTLq2AHfcH8vLCACXBFyu973oRxqiwGqTPENFyNGFOnZR0u55hgzJa8mWf60sFQBeM4pc1FNdrMOzWrr10q1dSvHVHwsUJvGtxGSBNHAgMBAAECggEAWtNGCtDbmENP2/KqbUXREIPtj7VhYj7afBNRnh7/eUh2PDJEsvGmuuClYCmLEh8B7UAEvz9JVxXXthDwOtQ3LmgC/vVZa+fmHrNPiGV3qKH52Y6pri+vZ3yLtE2ouKD/TTSBu2C9WyYlfTbGL2hU+tudcGOfjIirBKS71VwrdADJ8H9epluds54Rq4wr59WGlkYiQfimGMZo8pyDqnMBwlCdCEkzIaBRlBijrqfOFUoM6fMBlmv7SEPv8v43Ga0Wf+YWFRJ/8MXx4qDXhoiTxvMbn8rosPANz2FJj9OzdWGTRObA24TvdpV6e6UlKE7Rpj/14z/9gbgh575vP6vjsQKBgQD1DSp3LoEskFZSu9C60uVmL9cAthd16w6p9FvxjdAwoBvUUd/HL1ATjiI4IGjrODUkSKFVCoQpKPxTEf5m0b9oNCl5WczPy36Eaw2N0n0xTfa7KOmlZSdEZExOqzc7gx+UAPMwF+yYi3lZDlbVVRd4jFaNjOjebpUkwujwbnoNCwKBgQDD8+P+yjmBhxAqczMMfqWK9cTxOg6/C5CX3NQE2l3H0sE69aOQBolCS7JRkyJTgYtbV3j7YsGF/SaMTkoa9RFqXbbnYp33rROskpEo6Ovsy5ZzUFWdBuCfXpZxM26zFPeIMEG3atRBQ/+E8YIUsJbxBIQueI4/YZo9Q6SouWYgNQKBgQDlx+Oy//N2Yx3e1GhkVs9uo5ta+mRDWRdV/GGevpiij++ohuFsOAxBWfzyXkmjjwAdttE5GfRVWC5Kgsvng/SUdJ0TQkZP7wQCjROXkfF8oxuh4jo8sf5BHj+DYP8YmdqpgL6IsVZUsCwoes6nmfO5QPcEIkZWFKFbaDze/ukJgwKBgCC++TLzCHkk4Qa3Cajk4TU/rRHC6gPzIs6+EJBfDZz/E1uPAS5oaTey/rGzLOFXnLo++/DGqCCEFZUM4EVw/uAPmSlBybRCMC9+rtNvWT3RJ+tNWYFMTO1VGT8BoVY8AsG3jsPgDmh5vJGy6lIa0J6mapB5hQwCe4wRacrocLChAoGAFo6PqUHcJP9DR1oPQ52VRdi85SiCVrb/5p8ggL+eskxm6HK3sOpjah9qyCKUhYBIAqxalxoAIPLnLt2WvpZlBbl4Y5i1k2dsRYlMIt4veuSOSi6Mp5tgXefd4tC5trTsDCFP2tNs9eCm2hBjXmd2Vg348g6vdc7Ps/ztNyM5eU8=";	
	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
	
	protected static final String APP_ID = "xxxx";
	
	protected static final String PASSWORD = "12345678";


	public void test_cop() throws IcbcApiException, UnsupportedEncodingException {
		

		// 构造client对象
		UiIcbcClient client = new UiIcbcClient(APP_ID, MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8);

		// 设置请求对象request
		MybankLoanDatachainloanuiLoanapplyRequestV1 request = new MybankLoanDatachainloanuiLoanapplyRequestV1();
		
		// 设置请求路径
		request.setServiceUrl("http://ip:port/ui/mybank/loan/datachainloanui/loanapply/V1");

		MybankLoanDatachainloanuiLoanapplyRequestV1Biz bizContent = new MybankLoanDatachainloanuiLoanapplyRequestV1Biz();

		bizContent.setApi_name("ECHAIN");
		bizContent.setApi_version("xxxxx");
		bizContent.setPlatNo("PF001");
		bizContent.setCycFlag("1");
		bizContent.setCusNo("020090000018907");
		bizContent.setCusName("金网络交易平台");
		bizContent.setCusCIS("020090001926823");
		bizContent.setCounterpartType("3");
		bizContent.setCounterpartNo("1188");
		bizContent.setCounterpartName("xxx");
		bizContent.setLoanApplyNo("1288");
		bizContent.setSecretFlag("1");
		bizContent.setRelations("02");
		bizContent.setEvalType("1");
		bizContent.setLoanCur("001");
		bizContent.setPlatChargeRate("0.18");
		bizContent.setPlatCharge("1000");
	 
	
		List<MybankLoanDatachainloanuiLoanapplyTradeInfo> tradeinfolist = new ArrayList<MybankLoanDatachainloanuiLoanapplyTradeInfo>();
	
		MybankLoanDatachainloanuiLoanapplyTradeInfo tradeinfo1=new MybankLoanDatachainloanuiLoanapplyTradeInfo();
		MybankLoanDatachainloanuiLoanapplyTradeInfo tradeinfo2=new MybankLoanDatachainloanuiLoanapplyTradeInfo();
		//贸易编号1
		tradeinfo1.setTradeInfoNo("CAIY190101"); 
		
		//贸易编号2  
		tradeinfo2.setTradeInfoNo("CAIY190102"); 
		
		tradeinfolist.add(tradeinfo1);
		tradeinfolist.add(tradeinfo2);
		  
		bizContent.setiTradeInfoList(tradeinfolist);
	 	
		request.setBizContent(bizContent);

		Assert.assertEquals(client.buildPostForm(request), "test");
		
	}
}
