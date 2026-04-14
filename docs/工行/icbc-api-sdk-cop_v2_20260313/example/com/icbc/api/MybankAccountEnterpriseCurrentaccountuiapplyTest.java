
package com.icbc.api;
import java.util.regex.Matcher;
import java.util.regex.Pattern; 
import com.icbc.api.IcbcConstants;
import com.icbc.api.UiIcbcClient;
import com.icbc.api.request.MybankAccountEnterpriseCurrentaccountuiapplyRequestV1;
import com.icbc.api.request.MybankAccountEnterpriseCurrentaccountuiapplyRequestV1.MybankAccountEnterpriseCurrentaccountuiapplyRequestV1Biz;
public class MybankAccountEnterpriseCurrentaccountuiapplyTest {
	protected static final String MY_PRIVATE_KEY = "Al84Gc7IXK8AfR1qLPdW96blEjRN8pVdJ9crarCngQPIkIvx1abQXlx5PCmgs/7Bqu3vJpdRlK2EnFJ5FMN6p9Ga6r1ILcPK4ldVahY4J02/ZQjw7poXMZ+NwZYdXpVyrqfvMLz/JbDPih/gU9sN39hdJrYAeqpXmmtvl3657dDkz45i7UBwKLZQ/+couNOcXgrGvuqbVGRkK0k1lfmj9U0gbbER+7VS47sukjXxNLx/V+8W1I9Xw8hZberSYx8RAaFDb68ZNcKdMRgoM5g7AgEmMWxkemN46f/0xjZuDMdH00KEfTZDzq6EnyZG++92wU9n8BPxqPxtK0M5h7OXqRrq3+H9JBoYlRVB1SHyIcIo8RduvZdleVhRkFsAuKN4m9lpomFxMNSz4HRuOEY0RkRaInl0XColI10a81cxXG9Nngmu2o6flGCe6VZUDiNSp/HzD+WroaIMsiN3kJl0yg5dPmO6KyTnc4D27WxRkOfCfasiLbaXCfc7rldTmplZnkc0RbNMijG5ToQAp8bZbq5JpYwDl9+c33tYhU89CZrTujFEeTnL4kLBWv1F4n3/TEl83M5zyAxLLzy3MymdQNvEdk+D5wlhbM3dJdLlqusyVnaswIJ0SDXWca+24s0SvUiQcNOwbkeEYbo29FeWYoFNpKfTARx7vE+rqvfx6MwEx9vAlqReD3YdE0vSHFWU1cZ0dULyHKvZ5Hy7/dY2VQCcwKWDBKarMdPafUCDLfXjJn2gq0WHVROXmB5N9WIq9bBsmOJ6TrvjV6qTBJPbdfnIEOtETezC5qCKZKhcFrEw";

	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的APP私钥-----DDzCCAfegAwIBAgIKbaHKEE0tAABStDANBgkqhkiG9w0BAQUFADA3MRkwFwYDVQQKExBjb3JiYW5rNDMuY29tLmNuMRowGAYDVQQDExFjb3JiYW5rMTI4IHNkYyBDTjAeFw0xNjA5MTMwODQ2MDdaFw0yMjAzMDEwODQ2MDdaMEUxGTAXBgNVBAMTEGx1aHV6eTAwMS55LjAyMDAxDTALBgNVBAsTBDAyMDAxGTAXBgNVBAoTEGNvcmJhbms0My5jb20uY24wgZ8wDQYJKoZIhvcNAQEBBQADgY0AMIGJAoGBAIYomiojXul1Ri/9CnknLHZ1txuq1o0vF/DpeiRspzldFpB0RKxTqfcC1gs82nN76vYo50xIgtP0tLJ1/rDqc/t/AMpnCpDPJXKymOq8j9iESfrgEpypbQ/6Hhozq1tFxIi+/TARf3TlZB6OnTMu5EEaYXOKaDfEv90PkYpq2OMzAgMBAAGjgZIwgY8wHwYDVR0jBBgwFoAUnoARo7tXznf98Sdn/uThfyLyUfMwTQYDVR0fBEYwRDBCoECgPqQ8MDoxDjAMBgNVBAMTBWNybDIyMQ0wCwYDVQQLEwRjY3JsMRkwFwYDVQQKExBjb3JiYW5rNDMuY29tLmNuMB0GA1UdDgQWBBSCIdS+x+AlXsCCvB36nJmsvuVt4jANBgkqhkiG9w0BAQUFAAOCAQEACBF+MaxsHRH52Im+amFM8xBrvO57xN8rQ94nyozd25gaGh3CgtgxY1SgIGwk05rmNyeDi0QSByMFZ7aFhgxCxEeR1i0VT/gNzgoyXYmocBsFnVKo+ihHGE7OdXz9DrGCOhk+EAqnFBOrnalNSaZs7bklpnc9AzuLTLqRZxkAiQpPj+UNOot/y6/lb/rJsjC43idECycAGc3M6TmZQH5wNeccxXtuv3DbLTme9mbh+8eEWYtwBgqQCDwHxDPoHcBSzRaCb/N6u3dEBLDFs1I/SIyyUSUwUKneS3iUYIyE1hPZASQSZAOIUvUvG3BRr/mwrpePcTpPhZubSHlCYAG68A==";

	protected static final String APP_ID = "10000000000000004447";
	
	protected static final String PASSWORD = "12345678";
	

	public void test_cop() throws IcbcApiException {
		// 构造client对象
		String castr = APIGW_PUBLIC_KEY;
		// 去除签名数据及证书数据中的空格 added for Safari
		Pattern p = Pattern.compile("\\s*|\t");
		Matcher m2 = p.matcher(castr);
		castr = m2.replaceAll("");
		UiIcbcClient client = new UiIcbcClient(APP_ID, MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8, castr, PASSWORD);
		
		// 设置请求对象request
		MybankAccountEnterpriseCurrentaccountuiapplyRequestV1 request = new MybankAccountEnterpriseCurrentaccountuiapplyRequestV1();

		MybankAccountEnterpriseCurrentaccountuiapplyRequestV1Biz bizContent = new MybankAccountEnterpriseCurrentaccountuiapplyRequestV1Biz();
		
		// 设置请求路径
		request.setServiceUrl("http://IP:PORT/ui/mybank/account/enterprise/currentaccountuiapply/V1");
		
		bizContent.setApi_name("PRSIGN");
		bizContent.setApi_version("001.001.001.001");//接口版本
		bizContent.setFile_code("0200EG0000001");//在银行端注册一级平台时生成的号码
		bizContent.setFile_name("平台名称");//在银行端注册一级平台时生成的名称
		bizContent.setFile_serialno("6633074382999");//一级平台流水号
		bizContent.setTradeplat_name("交易平台名称");//交易平台名称
		bizContent.setTradeplat_code("773382910333");//交易平台号
		bizContent.setTrade_orgcode("32444324343");//交易平台统一社会信用代码
		bizContent.setTrade_time("20180310162230");//交易平台发起的时间，格式：YYYYMMDDHHMMSS
		bizContent.setEnt_name("三丫医疗器械销售责任有限公司");//企业名称
		bizContent.setEnt_orgcode("429006NA000000022X");//企业统一社会代码
		bizContent.setEnt_type("股份有限公司");//企业类型
		bizContent.setFr_name("王二丫");//法定代表人姓名
		bizContent.setCorp_type("000");//法定代表人证件类型,身份证：000
		bizContent.setIdentity_cardnt("431128198512270068");//法人身份证号
		bizContent.setTel("18026285748");//法定代表联系电话
		bizContent.setRegcap_cur("001");//注册资本币种 人民币 001
		bizContent.setRegcap("3895.12");//注册资本 1234.88 不能带万,元等中文字符，有小数则保留小数点后2位
		bizContent.setOp_from("20150203");//注册日期经营期限自
		bizContent.setOp_to("20220203");//经营期限至
		bizContent.setBus_enddate("20220203");//营业执照到期日期
		bizContent.setOp_scope("医疗器械销售");//经营业务范围
		bizContent.setDom("珠海市软件园路2号");//注册地址
		bizContent.setCis_phone("020-82706349");//单位联系电话
		bizContent.setHolder_name("王大丫");//控股股东姓名
		bizContent.setHolder_corptype("000");//控股股东证件类型
		bizContent.setHolder_custid("431128198512270059");//控股股东证件号码
		bizContent.setEnt_status("营业中");//经营状态
		bizContent.setNeed_addinfo("1");//是否需要返回结果，账号等信息0，不需要，1，需要
		bizContent.setIs_fr("1");//是否是法人自己申请0,是，1，否
		bizContent.setSign_name("王小Y");//代理申请人姓名
		bizContent.setSign_id("431128198512270999");//代理申请人身份证号码
		bizContent.setPhonenumber("18026284788");//申请使用的手机号码
		bizContent.setNotifyurl("https://xxxx/do.jsp");//异步通知的地址
		bizContent.setProvince_name("北京");//上送省的名称
		bizContent.setCity_name("北京");//上送市的名称
		request.setBizContent(bizContent);
		
		// 生成自提交的表单返回客户浏览器，该表单会自动提交完成调用
		System.out.println(client.buildPostForm(request));
	}
		
}
