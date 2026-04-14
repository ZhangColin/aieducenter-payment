package com.icbc.api;


import org.junit.Test;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.request.EnterpriseOpenpayPayRequestV1;
import com.icbc.api.request.EnterpriseOpenpayPayRequestV1.EnterpriseOpenpayPayV1Biz;
import com.icbc.api.response.EnterpriseOpenpayPayResponseV1;

public class EnterpriseOpenpayPayTest {

	protected static final String MY_PRIVATE_KEY ="Al84Gc7IXK8AfR1qLPdW96blEjRN8pVdJ9crarCngQPIkIvx1abQXlx5PCmgs/7Bqu3vJpdRlK2EnFJ5FMN6p9Ga6r1ILcPK4ldVahY4J02/ZQjw7poXMZ+NwZYdXpVyrqfvMLz/JbDPih/gU9sN39hdJrYAeqpXmmtvl3657dDkz45i7UBwKLZQ/+couNOcXgrGvuqbVGRkK0k1lfmj9U0gbbER+7VS47sukjXxNLx/V+8W1I9Xw8hZberSYx8RAaFDb68ZNcKdMRgoM5g7AgEmMWxkemN46f/0xjZuDMdH00KEfTZDzq6EnyZG++92wU9n8BPxqPxtK0M5h7OXqRrq3+H9JBoYlRVB1SHyIcIo8RduvZdleVhRkFsAuKN4m9lpomFxMNSz4HRuOEY0RkRaInl0XColI10a81cxXG9Nngmu2o6flGCe6VZUDiNSp/HzD+WroaIMsiN3kJl0yg5dPmO6KyTnc4D27WxRkOfCfasiLbaXCfc7rldTmplZnkc0RbNMijG5ToQAp8bZbq5JpYwDl9+c33tYhU89CZrTujFEeTnL4kLBWv1F4n3/TEl83M5zyAxLLzy3MymdQNvEdk+D5wlhbM3dJdLlqusyVnaswIJ0SDXWca+24s0SvUiQcNOwbkeEYbo29FeWYoFNpKfTARx7vE+rqvfx6MwEx9vAlqReD3YdE0vSHFWU1cZ0dULyHKvZ5Hy7/dY2VQCcwKWDBKarMdPafUCDLfXjJn2gq0WHVROXmB5N9WIq9bBsmOJ6TrvjV6qTBJPbdfnIEOtETezC5qCKZKhcFrEw";

	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

	protected static final String APP_ID = "10000000000000004447";
	
	protected static final String password = "12345678";
	
	protected static final  String MY_PUB_KEY = "-----请使用正确的APP私钥-----DDzCCAfegAwIBAgIKbaHKEE0tAABStDANBgkqhkiG9w0BAQUFADA3MRkwFwYDVQQKExBjb3JiYW5rNDMuY29tLmNuMRowGAYDVQQDExFjb3JiYW5rMTI4IHNkYyBDTjAeFw0xNjA5MTMwODQ2MDdaFw0yMjAzMDEwODQ2MDdaMEUxGTAXBgNVBAMTEGx1aHV6eTAwMS55LjAyMDAxDTALBgNVBAsTBDAyMDAxGTAXBgNVBAoTEGNvcmJhbms0My5jb20uY24wgZ8wDQYJKoZIhvcNAQEBBQADgY0AMIGJAoGBAIYomiojXul1Ri/9CnknLHZ1txuq1o0vF/DpeiRspzldFpB0RKxTqfcC1gs82nN76vYo50xIgtP0tLJ1/rDqc/t/AMpnCpDPJXKymOq8j9iESfrgEpypbQ/6Hhozq1tFxIi+/TARf3TlZB6OnTMu5EEaYXOKaDfEv90PkYpq2OMzAgMBAAGjgZIwgY8wHwYDVR0jBBgwFoAUnoARo7tXznf98Sdn/uThfyLyUfMwTQYDVR0fBEYwRDBCoECgPqQ8MDoxDjAMBgNVBAMTBWNybDIyMQ0wCwYDVQQLEwRjY3JsMRkwFwYDVQQKExBjb3JiYW5rNDMuY29tLmNuMB0GA1UdDgQWBBSCIdS+x+AlXsCCvB36nJmsvuVt4jANBgkqhkiG9w0BAQUFAAOCAQEACBF+MaxsHRH52Im+amFM8xBrvO57xN8rQ94nyozd25gaGh3CgtgxY1SgIGwk05rmNyeDi0QSByMFZ7aFhgxCxEeR1i0VT/gNzgoyXYmocBsFnVKo+ihHGE7OdXz9DrGCOhk+EAqnFBOrnalNSaZs7bklpnc9AzuLTLqRZxkAiQpPj+UNOot/y6/lb/rJsjC43idECycAGc3M6TmZQH5wNeccxXtuv3DbLTme9mbh+8eEWYtwBgqQCDwHxDPoHcBSzRaCb/N6u3dEBLDFs1I/SIyyUSUwUKneS3iUYIyE1hPZASQSZAOIUvUvG3BRr/mwrpePcTpPhZubSHlCYAG68A==";
	

	public void test_cop() { 
	// ����client����
	DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY,MY_PUB_KEY,password);

	// �����������request
	EnterpriseOpenpayPayRequestV1 request = new EnterpriseOpenpayPayRequestV1();
	// ��������·��
	request.setServiceUrl("http://ip:port/api/enterprise/openpay/pay/V1");
	

	EnterpriseOpenpayPayV1Biz bizContent = new EnterpriseOpenpayPayV1Biz();

	bizContent.setTransCode("ZJJKHB"); 
	bizContent.setCis("dongxiao2017045"); 
	bizContent.setBankCode("102"); 
	bizContent.setId("xiaoqiao02030327.y.0200"); 
	bizContent.setTranDate("20201231"); 
	bizContent.setTranTime("105899000"); 
	bizContent.setfSeqno("ZJJKHB20180305104754-18"); 
	bizContent.setFileCode("0200EH0003042"); 
    bizContent.setTradePlatCode("0200TT0001002"); 
	bizContent.setTradePlatName("tmptest"); 
	bizContent.setFileSerialno("new036"); 
	bizContent.setOrgFileSerialno("ZJBL1236"); 
    bizContent.setCurrType("001"); 
    bizContent.setAmt("100"); 
    bizContent.setSystemFlag("1"); 
	bizContent.setRecName("����"); 
	bizContent.setRecAcctNum("0200000309200041013"); 
	bizContent.setRecBnkclsCode(""); 
	bizContent.setUserRem("010-8270XXXX"); 
    bizContent.setSignTime("20180315103022334"); 

	request.setBizContent(bizContent);
	
	EnterpriseOpenpayPayResponseV1 response; 
	 try { 
	  response = (EnterpriseOpenpayPayResponseV1) client.execute(request, "msgId"); 
	  if (response.isSuccess() ) { 
	   // ҵ��ɹ����� 
		  System.out.println("success");//
		 } else { 
		   // ʧ�� 
		  System.out.println("error");
		 }   
	 } catch (IcbcApiException e) { 
	  e.printStackTrace(); 
	 } 
	}
}
