package com.icbc.api;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcConstants;
import com.icbc.api.PassRealNameRegisterRequestV1;
import com.icbc.api.PassRealNameRegisterRequestV1.PassRealNameRegisterRequestV1Biz;
import com.icbc.api.PassRealNameRegisterResponseV1;
import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;

public class PassRealNameRegisterTest {

	//1、网关公钥
	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

	//2、appid 行外通行证APP 
	protected static final String APP_ID = "10000000000004094587";
	
	//3、密钥对认证方式，公钥在API平台登记，此处为私钥 行外访问
	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCHYPFhPlrzfxbZVP+yMJYPxlCroVoUPXVaVMvwmNPyZexlSVY1yuzpbOZSs6/ADB1G0karlzzWyWuKN+ZoRnVQGpJ8x0i/XfScTHuWSx3N5FlHX12F6EjLIhybvWfnokQJl/zIVUfmg9tyUgr6fhA5dL8X/jjx0ScmdsCa0nmMFwFdydcCppzSdD1vvERRfgl1yAiN093i6UadEoVAmDMYa+uN1XabzPVoFGKRKGVRx588sFBpyv4Jw1WBKeEDVQA9ga9wv3Iq9B4pOFw6B9kv71vs/gWoSceIGWV2uA0rxXBl2qAqIPb8aKAeVIJQpm6grsahT+ohZWUPM2nMWfI7AgMBAAECggEAK/OYSkbxRk6Df4mt/g7yGSr8c8xNr3aIjAwtre4gGXlntzaJC6vXIK2fVUhLDeGg+6Y3FWxjbyQziss9PatqWNhXqLUWdDKvwLLACPpYMckqBxb6CQhx4akweouSmUxAJJE4m128myVB8KqcL9kPsWeqxsJdgHyz5jP1sg4VMhqlvku6D0n5pS8aQZmbQGYaAPA+nALRHL8/0sXlj0mzmq8NAqlkh/el0JAU/gjWA8MF9k+RrZHZh3W3KbIBiKHm4WouYhDCdIJOXrC4rxtQJkumULAe2tw1GJEBxads47AlrTP2dk/7lPVTx9FCSBsnnwuIqgzljyC6+r8pQpWKQQKBgQDrN8a5P5zo2Ex5POPQNYNYr9rwJr9MD61kVr3U+0qbECU7hUcfUhop2glxnPZ7Wj+uswXgaQA7B+3i0ElPF3xealE0ElsS+rxp5J0wOpUdnqpPz+O3eN6MCPFGmYb8MPQOtJvTyWeWe74ha0Pb+qvwdezv5dJ/fkS6JZm9p3/qIQKBgQCTVvnPuT5d7IzphlXsMsZ0rIk/xH1pun4EAvTNqYAkAVysYRS4tG4bvnvgdPkcgVSXYNj9Eu36QNpvB0BT/4NPBLxID/F2T2glQiaLHG7Ifonl1ih/Eg+DxjcSbWDHHYd0OqcImwZVXKTa69etmUFBRZYPBaKNIctr1hIuvWeo2wKBgQDiwiamOBlAsCUpS/YjpzM0sHPUyMXqwcJa1PS0+nz1+B6svtPN8gyfBD/Oc497Y4m5FYU4zoEWZ5KB8mJ6CR4wtzYJiRibt3UkcOzIrfFLWMa/op7ZJYEGZyiS8zcasObXtm3zQlrGzAyDkzEcRRC1BDqjG2BO4J/JBK6uE2hRgQKBgBd2Tilp0iiatoWpQp1uKHQD1Yc1/tKLQ+3Xmqv1RdZ18Q/yFZ6j/LSmTIudlN2ZKqNAZM1H6mVjVmU/vlqh4YL/uguvnj+8vVTRh9m3GV0L6WGUDLbPZXESa0qf6D1M0nkLrfWM2iFVs1q5G4slUGLYdeDGgPAAZagyWore1lbpAoGAV+eSrk+iO3sQuCddnqHMKLjeSnha2wiaMjMi54lIGD7VCzUK2OOnh+8RLBIM1jGlp1u10+m3ZVRo0FGDUy2rBZttbJay4G+NcyblIW4XrIFaFSbCoKmd8UIUgeyoYXaNzR+RpoSvtvMOvV4uSvLndTYXhUdKXoGZcdrfM6udqVY=";
	
	public static void main(String[] args) throws Exception {
		
		//签名类型为SHA256WithRSA时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
		PassRealNameRegisterRequestV1 request = new PassRealNameRegisterRequestV1();
		
		//4、根据测试环境和生产环境替换相应ip和端口
		//行外流程ip:port
		request.setServiceUrl("http://ip:port/api/cert/epass/realnameregist/V1");

		//5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值			
		PassRealNameRegisterRequestV1Biz bizContent = new PassRealNameRegisterRequestV1Biz();
		
		//chanCommV10
		PassRealNameRegisterRequestV1.ChanCommV10RequestV1Message chanCommV10 = new PassRealNameRegisterRequestV1.ChanCommV10RequestV1Message();
		chanCommV10.setChantype("");
		bizContent.setChanCommV10(chanCommV10);
		
		//infoCommV10
		PassRealNameRegisterRequestV1.InfoCommV10RequestV1Message infoCommV10 = new PassRealNameRegisterRequestV1.InfoCommV10RequestV1Message();
		infoCommV10.setTrxcode("PassRealNameRegisterAPI");
		infoCommV10.setZoneno("0101");
		infoCommV10.setBrno("0001");
		bizContent.setInfoCommV10(infoCommV10);
		
		//inqCommV10
		PassRealNameRegisterRequestV1.InqCommV10RequestV1Message inqCommV10 = new PassRealNameRegisterRequestV1.InqCommV10RequestV1Message();
		inqCommV10.setInqtype("");
		bizContent.setInqCommV10(inqCommV10);
		
		//inputV10
		PassRealNameRegisterRequestV1.InputV10RequestV1Message inputV10 = new PassRealNameRegisterRequestV1.InputV10RequestV1Message();
		inputV10.setFcode("PassRealNameRegister");
		inputV10.setServer_ip("ip:port");
		inputV10.setDt("2019-04-23");
		inputV10.setTm("172500");
		inputV10.setChannelidentifier("ip:port.20190521092544.329");
		inputV10.setChannelcode("303");
		inputV10.setCardnum("6222020200123093581");
		inputV10.setMobile("13800138000");
		inputV10.setCustname("云二");
		inputV10.setCerttype("0");
		inputV10.setCertnum("110102198006230000");
		bizContent.setInputV10(inputV10);
		
		request.setBizContent(bizContent);
		//建立返回项
		PassRealNameRegisterResponseV1 response = new PassRealNameRegisterResponseV1();
		try {
			response = client.execute(request);	
			System.out.println(JSONObject.toJSONString(response));
			String retcode = response.getAppstatv10().getTransok();
			System.out.println("transok:"+retcode);
			String return_code = response.getAppstatv10().getReturn_code();
			System.out.println("return_code:" + return_code);
			System.out.println("return_msg:" + response.getAppstatv10().getReturn_msg());
		} catch (IcbcApiException e) {
			e.printStackTrace();
		} 	
	}
}
