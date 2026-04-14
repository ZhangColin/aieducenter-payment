package com.icbc.api;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.ThirdpartyLoanAttestRequestV3;
import com.icbc.api.request.ThirdpartyLoanAttestRequestV3.ThirdpartyLoanAttestRequestV3Biz;

public class ThirdpartyLoanAttestTestV3 {
	
	//1、appid
	protected static final String APP_ID = "10000000000000004945";

	//2、密钥对认证方式，公钥在API平台登记，此处为私钥
	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----CdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIh0pL84ShSJqeoz01ef2b9PoDZKgEz396kXWa69tjz2jRHvhoD3beQRqTPnDYCn1FqT/vV4tElxXNfEPZcmDgGKUxMu8F+t3vyOKS6RKCWJYTddeljLYnPTLL7rPWEhHLPiTNFfkf5qIh3lca9SV1bnARNIc25DweZAcZCkot/3AgMBAAECgYBiLhsDgsq6cDqTSmq710XiwrXxmjUqLBhDz4s1kUZ4IqVpzD+ot16YBKULtSkf8qF18po1oH2Hgktc69Os1zKnkqBXq9PUGF6XRFqCSfus5EWXLKrg9puB02FqbBl6Vja+R0GrYl3f9zl6O7Fooj2p8y04Y7CV4boXcj8DVa+icQJBAOSkuFIp1ywpdr65KQMnxYXr2VissU78nDPFjSqTjxzNks0po/QXTB4HD1PXaxQHtbhiqjUjYui2eZ2lft5IPv0CQQCYyD0nZQoQUJlkoNYBMdFcYhjlJBjbhcX+yMQI5D7AO/OFsS+tmRCKAqluPWruuFJheLPtAtXNViSc2vVEx58DAkBWAVAEOBDkX8R3AZdXxIIcWhcrr+oSWBs5rjnnphHTN9Dcs6kkjA7pzKYLV6DDrBLDBPoZevIdr2YX0sTiQDLRAkBPm/8pmN9+ktDq+1entbK25bK9K2r8cB80XJ6xV2VPO63v2tzSIzDYCP0KIVyn2Umr2n4+oLyRSzK2vO0SksXLAkEAsaSosxPz80d7QVT8Dp2qcT7Zb2omxxJuL9JJSL9cqroIuTKhET7SNCk5DZ8dQRm/t0YM1qf1+Ghg23dXuFt/ZQ==";
	
	protected static final String MY_AES_KEY = "TKhSiK9Blwr6+aCq+O0MFg==";

	public void attest(HttpServletRequest request1, HttpServletResponse response) throws Exception {

		//3、签名类型为RSA2时，需传入appid，私钥和AES密钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
		UiIcbcClient client = new UiIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8, IcbcConstants.ENCRYPT_TYPE_AES, MY_AES_KEY);
	    ThirdpartyLoanAttestRequestV3 request = new ThirdpartyLoanAttestRequestV3();
		//4、根据测试环境和生产环境替换相应ip和端口
		request.setServiceUrl("http://ip:port/ui/thirdparty/loan/attest/V3");
			
		//5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
		ThirdpartyLoanAttestRequestV3Biz bizContent = new ThirdpartyLoanAttestRequestV3Biz();
		bizContent.setCisNo("hO7da6iW6BLvCYswj2k1PQ==");//客编
		bizContent.setAreaCode("1243");//地区号
		bizContent.setCustName("张三");//姓名
		bizContent.setCardId("140581199003270019");//身份证号
		bizContent.setMobileNo("13136936939");//手机号
		bizContent.setLoanSum("50000");//贷款金额，元
		bizContent.setLoanTime("13");//贷款期限，月
		bizContent.setRepaymentSum("4000");//应还款总额，元
		bizContent.setRateType("1");//利率类型，0-年利率，1-日利率
		bizContent.setLoanRate("4.9");//贷款利率，%
		bizContent.setFirstRepayTime("20180601");//首期还款时间
		bizContent.setFirstRepaySum("1200");//手机还款金额，元
		bizContent.setLoanUse("开店");//贷款用途
		bizContent.setReceiptNo("6222222222222222222");//收款卡号
		bizContent.setRepaymentNo("6222222222222222222");//还款卡号
		bizContent.setLoanCompanyName("工商银行");//贷款公司名称
		bizContent.setThirdLoanId("123456");//第三方管理ID
		bizContent.setOutTradeNo("020010202");//外部订单号
		bizContent.setNotifyUrl("ip:port");//商户接收验签成功通知消息URL,notify_flag为1时必输入
		bizContent.setNotifyFlag("1");//商户是否开启通知接口 0-否；1-是；非1按0处
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
