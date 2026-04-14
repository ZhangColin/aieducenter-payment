package com.icbc.api;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.ThirdpartyLoanAttestRequestV1;
import com.icbc.api.request.ThirdpartyLoanAttestRequestV1.ThirdpartyLoanAttestRequestV1Biz;

public class ThirdpartyLoanAttestTestV1 {

	//1、appid
	protected static final String APP_ID = "10000000000000004945";

	//2、密钥对认证方式，公钥在API平台登记，此处为私钥
	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----CdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIh0pL84ShSJqeoz01ef2b9PoDZKgEz396kXWa69tjz2jRHvhoD3beQRqTPnDYCn1FqT/vV4tElxXNfEPZcmDgGKUxMu8F+t3vyOKS6RKCWJYTddeljLYnPTLL7rPWEhHLPiTNFfkf5qIh3lca9SV1bnARNIc25DweZAcZCkot/3AgMBAAECgYBiLhsDgsq6cDqTSmq710XiwrXxmjUqLBhDz4s1kUZ4IqVpzD+ot16YBKULtSkf8qF18po1oH2Hgktc69Os1zKnkqBXq9PUGF6XRFqCSfus5EWXLKrg9puB02FqbBl6Vja+R0GrYl3f9zl6O7Fooj2p8y04Y7CV4boXcj8DVa+icQJBAOSkuFIp1ywpdr65KQMnxYXr2VissU78nDPFjSqTjxzNks0po/QXTB4HD1PXaxQHtbhiqjUjYui2eZ2lft5IPv0CQQCYyD0nZQoQUJlkoNYBMdFcYhjlJBjbhcX+yMQI5D7AO/OFsS+tmRCKAqluPWruuFJheLPtAtXNViSc2vVEx58DAkBWAVAEOBDkX8R3AZdXxIIcWhcrr+oSWBs5rjnnphHTN9Dcs6kkjA7pzKYLV6DDrBLDBPoZevIdr2YX0sTiQDLRAkBPm/8pmN9+ktDq+1entbK25bK9K2r8cB80XJ6xV2VPO63v2tzSIzDYCP0KIVyn2Umr2n4+oLyRSzK2vO0SksXLAkEAsaSosxPz80d7QVT8Dp2qcT7Zb2omxxJuL9JJSL9cqroIuTKhET7SNCk5DZ8dQRm/t0YM1qf1+Ghg23dXuFt/ZQ==";
	//3、AES密钥                                                                                             -----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB
	protected static final String MY_AES_KEY = "TKhSiK9Blwr6+aCq+O0MFg==";
	
	public void attest(HttpServletRequest request1, HttpServletResponse response) throws Exception {
//	public static void main(String[] args) throws IcbcApiException{

		//签名类型为RSA2时，需传入appid，私钥和AES密钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
		UiIcbcClient client = new UiIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8, IcbcConstants.ENCRYPT_TYPE_AES, MY_AES_KEY);
        ThirdpartyLoanAttestRequestV1 request = new ThirdpartyLoanAttestRequestV1();
		//4、根据测试环境和生产环境替换相应ip和端口
		request.setServiceUrl("http://ip:port/ui/thirdparty/loan/attest/V1");
		
		//5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
		ThirdpartyLoanAttestRequestV1Biz bizContent = new ThirdpartyLoanAttestRequestV1Biz();
		bizContent.setCisNo("hO7da6iW6BLvCYswj2k1PQ==");
		bizContent.setCustName("张三");
		bizContent.setCardId("140581199003270019");
		bizContent.setMobileNo("13136936939");
		bizContent.setLoanSum("50000");
		bizContent.setLoanTime("13");
		bizContent.setRepaymentSum("4000");
		bizContent.setLoanRate("4.9");
		bizContent.setFirstRepayTime("20180601");
		bizContent.setFirstRepaySum("1200");
		bizContent.setLoanUse("开店");
		bizContent.setReceiptNo("6222222222222222222");
		bizContent.setRepaymentNo("6222222222222222222");
		bizContent.setLoanCompanyName("工商银行");
		bizContent.setThirdLoanId("123456");
		bizContent.setOutTradeNo("020010202");
		bizContent.setNotifyUrl("ip:port");
		bizContent.setNotifyFlag("1");
		String str = JSON.toJSONString(bizContent);
		System.out.println(str);
		request.setBizContent(bizContent);
        
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
	    String msgId = dateFormat.format(new Date());
		
		System.out.println(client.buildPostForm(request,msgId));
		response.setHeader("Content-Type", "text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		try {
			out.write("<html>");
			out.write("<head>");
			out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8 \">");
			out.write("</head>");
			out.write("<body>");
			out.write(client.buildPostForm(request,msgId));
			out.write("</body>");
			out.write("</html>");
		} catch (IcbcApiException e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
	}
}
