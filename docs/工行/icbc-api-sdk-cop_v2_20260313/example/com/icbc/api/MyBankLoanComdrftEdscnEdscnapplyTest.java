package com.icbc.api;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcConstants;
import com.icbc.api.UiIcbcClient;
import org.junit.Test;

import com.icbc.api.request.MyBankLoanComdrftEdscnEdscnApplyRequestV1;
import com.icbc.api.request.MyBankLoanComdrftEdscnEdscnApplyRequestV1.ComdrftEdscnEdscnApplyRequestV1Biz;

public class MyBankLoanComdrftEdscnEdscnApplyTest {

	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC7kn0WqNjJeVh9WaToArJ2Tadz+sgHGdYr9sCvakGJ/pvL281hhHVdIyBCNKfTCOwsWixta/2I06t9IBO2yXd+iInG65fprlhvmFcW70T1QHGMUpF41uMQmgJ90yfJ/zFW+jtGm1Aa4BumEhC7h+7b6aw7UUyfRxl0PoooPQ7cZpOZeEsypyBADnSdBBCJAdqS2+vO1vDGpLEqujOtC59mJyBKveL8ogmOVyhsTsOhkWeaCSTLq2AHfcH8vLCACXBFyu973oRxqiwGqTPENFyNGFOnZR0u55hgzJa8mWf60sFQBeM4pc1FNdrMOzWrr10q1dSvHVHwsUJvGtxGSBNHAgMBAAECggEAWtNGCtDbmENP2/KqbUXREIPtj7VhYj7afBNRnh7/eUh2PDJEsvGmuuClYCmLEh8B7UAEvz9JVxXXthDwOtQ3LmgC/vVZa+fmHrNPiGV3qKH52Y6pri+vZ3yLtE2ouKD/TTSBu2C9WyYlfTbGL2hU+tudcGOfjIirBKS71VwrdADJ8H9epluds54Rq4wr59WGlkYiQfimGMZo8pyDqnMBwlCdCEkzIaBRlBijrqfOFUoM6fMBlmv7SEPv8v43Ga0Wf+YWFRJ/8MXx4qDXhoiTxvMbn8rosPANz2FJj9OzdWGTRObA24TvdpV6e6UlKE7Rpj/14z/9gbgh575vP6vjsQKBgQD1DSp3LoEskFZSu9C60uVmL9cAthd16w6p9FvxjdAwoBvUUd/HL1ATjiI4IGjrODUkSKFVCoQpKPxTEf5m0b9oNCl5WczPy36Eaw2N0n0xTfa7KOmlZSdEZExOqzc7gx+UAPMwF+yYi3lZDlbVVRd4jFaNjOjebpUkwujwbnoNCwKBgQDD8+P+yjmBhxAqczMMfqWK9cTxOg6/C5CX3NQE2l3H0sE69aOQBolCS7JRkyJTgYtbV3j7YsGF/SaMTkoa9RFqXbbnYp33rROskpEo6Ovsy5ZzUFWdBuCfXpZxM26zFPeIMEG3atRBQ/+E8YIUsJbxBIQueI4/YZo9Q6SouWYgNQKBgQDlx+Oy//N2Yx3e1GhkVs9uo5ta+mRDWRdV/GGevpiij++ohuFsOAxBWfzyXkmjjwAdttE5GfRVWC5Kgsvng/SUdJ0TQkZP7wQCjROXkfF8oxuh4jo8sf5BHj+DYP8YmdqpgL6IsVZUsCwoes6nmfO5QPcEIkZWFKFbaDze/ukJgwKBgCC++TLzCHkk4Qa3Cajk4TU/rRHC6gPzIs6+EJBfDZz/E1uPAS5oaTey/rGzLOFXnLo++/DGqCCEFZUM4EVw/uAPmSlBybRCMC9+rtNvWT3RJ+tNWYFMTO1VGT8BoVY8AsG3jsPgDmh5vJGy6lIa0J6mapB5hQwCe4wRacrocLChAoGAFo6PqUHcJP9DR1oPQ52VRdi85SiCVrb/5p8ggL+eskxm6HK3sOpjah9qyCKUhYBIAqxalxoAIPLnLt2WvpZlBbl4Y5i1k2dsRYlMIt4veuSOSi6Mp5tgXefd4tC5trTsDCFP2tNs9eCm2hBjXmd2Vg348g6vdc7Ps/ztNyM5eU8=";	
	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
	
	protected static final String APP_ID = "10000000000000005031";
	
	protected static final String PASSWORD = "12345678";


	public void test_cop() throws IcbcApiException, UnsupportedEncodingException {
		

		// 构造client对象
		UiIcbcClient client = new UiIcbcClient(APP_ID, MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8);

		// 设置请求对象request
		MyBankLoanComdrftEdscnEdscnApplyRequestV1 request = new MyBankLoanComdrftEdscnEdscnApplyRequestV1();
		// 设置请求路径
		request.setServiceUrl("http://ip:port/ui/mybank/loan/comdrft/edscn/edscnapply/V1");

		ComdrftEdscnEdscnApplyRequestV1Biz bizContent = new ComdrftEdscnEdscnApplyRequestV1Biz();

		bizContent.setSerialNo("20201234545555");
		bizContent.setApi_name("edscnQuery");
		bizContent.setChanSerialNo("2020112635489");
		bizContent.setChanAppid("20201126548774");
		bizContent.setGroupId("100015525000000");
		bizContent.setOpVersion("1.0.0");
		bizContent.setAcctIds("12345678901234,12345678901235");
		request.setBizContent(bizContent);
		System.out.println(client.buildPostForm(request));

	
		
	}
}
