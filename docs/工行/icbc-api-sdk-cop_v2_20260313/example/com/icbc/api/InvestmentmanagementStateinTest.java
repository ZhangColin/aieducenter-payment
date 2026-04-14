package com.icbc.api;

import com.icbc.api.request.InvestmentmanagementStateinRequestV2;
import com.icbc.api.request.InvestmentmanagementStateinRequestV2.InvestmentmanagementStateinRequestV2Biz;
import com.icbc.api.request.InvestmentmanagementStateinRequestV2.InvestmentmanagementStateinRequestV2BizStatus;
import com.icbc.api.response.InvestmentmanagementStateinResponseV2;

/**
 * 交易平台状态更新
 */
public class InvestmentmanagementStateinTest {

	public static void main(String[] args) throws Exception {

		// 示例总体说明：请使用F-APIP最新的行外网关SDK进行开发，签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值

		//1、appid
		String APP_ID = "11000000000000004856";
		//2、密钥对认证方式，公钥在F-APIP平台登记，此处为私钥
		String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCa5KEY9GBJTKQVq9v3tfeTx6xDIeGm8juinGpuKqo7OiFv6kD9WCetaqLnj5OOjz5BskomLM/+wPyhLvSgQ9RjwVGQS3ne1Dbh0ljFig1slv3QTZJft8F9N0BVjQQ26mCBBsEbqjunXpzxIbWuHDQjxzSS3/CdbR3gdJTlZ3YIBGwcYrqnYRLBb5KQXB3Obi2UmTEboYvd5IakGm3hwtCpeLdDWXYJzXyDUf335HI3JFaSWKwOzaM6w8q0UrJY0KtiZ0EaPGQNa1ldJ5H4WOXCLb8NM+NJcgumdD9vlv9qEl0k34j9akStq0lSgMiSxVyVtp72kErK/CrEFot9nax5AgMBAAECggEBAJbh34vPgXvljHEP2FaUi3cJKmS6vHVDwZPFimeGaA0Js0ixiF1BYmUrW0T2N2bHYn3cjx5gyLwAghcBBoqXiG0kA6/r1euaDVws6wyCTlFZRCWFavCSr4iqKaEsnM5hwV/ulG2+hh2jrH23uKLCFohDpJLuUhUIFfJvnhtZjUCAhfaVcatBrSVRKu9YTJLV7bbvkZFhWPlPFm+fTGAgfBJ2pnyFZeqzP8MOGwfK/FPFQKxy76JexIzRTt7XgalEf3e73HSDAsVu8Et4rqWVYAfOKZ51WbgBLYCPGnMYrWKdGtfLu1TdYVceGZz2jt0bCMRZZEdDB29To8j6vsO68AECgYEA53Gq/kzA83mYf51VBhJ/3gl9UgGGb8jlm2nHGp7MSMOcgxZq8NKj28UdhViR8cFJQYhQ/YKi0BN81uo97nDq68LI411jtBvyh1DElMsAJAK2exYrHjHlo9+uvKmlXgVVLRVLvTwcUUnFjmziibdzgjHnsVGczq8Y071FjBJ6D+kCgYEAq1O8EMlJ68vCu/Id0mVuwfQ26b+xNBl0ZceFAF5fQYCKPC7PEm6f5dGGmqr5r/Gt1M7LpLWvexQyseVjS6qJEN2dxZyj2qPlKhlPotiji5ubgqnKDWlPLJ0UtGyvKIjBTeAzWazuQ0B7q4SrzNQ472/cn8BBD1j9DkhEEgf47hECgYAwUWizYm695drSeZQwilgrkKubHTplx/k3cuPgSNq5Li1UqK92y2LuFL7PAeimIgYbKkxOQKGg8xCTBaAcUXKZ+bZa0TppdZQyb8g6kA7ddbuHAt/Jk/0vMxmVxY0CjfZ0TQfc39nBlrcpvs2klqoEukp2mGU2ORPj0JXeYlUdUQKBgBQ5NiHlIMysqLf2xH5pasGKSlEdR5WWy56YBqMcgLICsk6YV+kdFdLTAMiRFhgmtxY/stomeChXIgTNxwLPho5vGoiRDOZqIkrt4fCfEe8Q1zJ9SFR88dOaVy9VBPgL7IV8yZL0TsuxeOeuKXlsM6xP0AMrdoKRRo3BCgf22POBAoGBAImzSVljqv6ChX7YHbIFIleUT5XqN4RKrYXJsqzFXI3Ttfu8teRjh70m1v55gWmGjgQk/Mke1fXWUHqSOpFvOmr7wdWjcj0jG0+FDe7eO4ixILGuV0GjcybKTY8xmmUvosXT+92qjptffrYE72X+wrXC2v6Ps8XmirUpFbGDPgd3";
		//3、API网关公钥
		String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

		//4、创建与F-APIP外网网关的连接
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String)null, (String)null, (String)null, (String)null);
		//5、设置请求对象
		InvestmentmanagementStateinRequestV2 request = new InvestmentmanagementStateinRequestV2();
		//6、设置请求路径；联测时，请联系我行告知并替换成测试环境地址。
		request.setServiceUrl("http://apip-gateway-gw-out-gn-F.month.sdc.cs.icbc/api/investment/management/statein/V2");
		//7、设置业务参数，按需求自行组装所需业务参数
		InvestmentmanagementStateinRequestV2.InvestmentmanagementStateinRequestV2Biz bizContent = new InvestmentmanagementStateinRequestV2.InvestmentmanagementStateinRequestV2Biz();
		InvestmentmanagementStateinRequestV2.InvestmentmanagementStateinRequestV2BizStatus bizStatus = new InvestmentmanagementStateinRequestV2.InvestmentmanagementStateinRequestV2BizStatus();
		//8、组装请求参数
		bizStatus.setGuidNo("A20240910");
		bizStatus.setFundCode("GS2352");
		bizContent.setService("BondInterbankOrdinaryTransInfoService");
		bizContent.setMethod("receive");
		bizContent.setInsStatus(bizStatus);
		request.setBizContent(bizContent);

		InvestmentmanagementStateinResponseV2 response;
		try {
			System.out.println("request:"+ JSONObject.toJSONString(request));
			response = client.execute(request);
			System.out.println("response:" + JSONObject.toJSONString(response));
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}
	}

	
}


