package com.icbc.api;

import java.util.Arrays;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcConstants;
import com.icbc.api.PassUpdatePhotoOpRequestV1;
import com.icbc.api.PassUpdatePhotoOpRequestV1.PassUpdatePhotoOpRequestV1Biz;
import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.PassUpdatePhotoOpResponseV1;

public class PassUpdatePhotoOpTest {

	//1、网关公钥
	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

	//2、appid 行外通行证APP 
	protected static final String APP_ID = "10000000000004094587";
	
	//3、密钥对认证方式，公钥在API平台登记，此处为私钥 行外访问
	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCHYPFhPlrzfxbZVP+yMJYPxlCroVoUPXVaVMvwmNPyZexlSVY1yuzpbOZSs6/ADB1G0karlzzWyWuKN+ZoRnVQGpJ8x0i/XfScTHuWSx3N5FlHX12F6EjLIhybvWfnokQJl/zIVUfmg9tyUgr6fhA5dL8X/jjx0ScmdsCa0nmMFwFdydcCppzSdD1vvERRfgl1yAiN093i6UadEoVAmDMYa+uN1XabzPVoFGKRKGVRx588sFBpyv4Jw1WBKeEDVQA9ga9wv3Iq9B4pOFw6B9kv71vs/gWoSceIGWV2uA0rxXBl2qAqIPb8aKAeVIJQpm6grsahT+ohZWUPM2nMWfI7AgMBAAECggEAK/OYSkbxRk6Df4mt/g7yGSr8c8xNr3aIjAwtre4gGXlntzaJC6vXIK2fVUhLDeGg+6Y3FWxjbyQziss9PatqWNhXqLUWdDKvwLLACPpYMckqBxb6CQhx4akweouSmUxAJJE4m128myVB8KqcL9kPsWeqxsJdgHyz5jP1sg4VMhqlvku6D0n5pS8aQZmbQGYaAPA+nALRHL8/0sXlj0mzmq8NAqlkh/el0JAU/gjWA8MF9k+RrZHZh3W3KbIBiKHm4WouYhDCdIJOXrC4rxtQJkumULAe2tw1GJEBxads47AlrTP2dk/7lPVTx9FCSBsnnwuIqgzljyC6+r8pQpWKQQKBgQDrN8a5P5zo2Ex5POPQNYNYr9rwJr9MD61kVr3U+0qbECU7hUcfUhop2glxnPZ7Wj+uswXgaQA7B+3i0ElPF3xealE0ElsS+rxp5J0wOpUdnqpPz+O3eN6MCPFGmYb8MPQOtJvTyWeWe74ha0Pb+qvwdezv5dJ/fkS6JZm9p3/qIQKBgQCTVvnPuT5d7IzphlXsMsZ0rIk/xH1pun4EAvTNqYAkAVysYRS4tG4bvnvgdPkcgVSXYNj9Eu36QNpvB0BT/4NPBLxID/F2T2glQiaLHG7Ifonl1ih/Eg+DxjcSbWDHHYd0OqcImwZVXKTa69etmUFBRZYPBaKNIctr1hIuvWeo2wKBgQDiwiamOBlAsCUpS/YjpzM0sHPUyMXqwcJa1PS0+nz1+B6svtPN8gyfBD/Oc497Y4m5FYU4zoEWZ5KB8mJ6CR4wtzYJiRibt3UkcOzIrfFLWMa/op7ZJYEGZyiS8zcasObXtm3zQlrGzAyDkzEcRRC1BDqjG2BO4J/JBK6uE2hRgQKBgBd2Tilp0iiatoWpQp1uKHQD1Yc1/tKLQ+3Xmqv1RdZ18Q/yFZ6j/LSmTIudlN2ZKqNAZM1H6mVjVmU/vlqh4YL/uguvnj+8vVTRh9m3GV0L6WGUDLbPZXESa0qf6D1M0nkLrfWM2iFVs1q5G4slUGLYdeDGgPAAZagyWore1lbpAoGAV+eSrk+iO3sQuCddnqHMKLjeSnha2wiaMjMi54lIGD7VCzUK2OOnh+8RLBIM1jGlp1u10+m3ZVRo0FGDUy2rBZttbJay4G+NcyblIW4XrIFaFSbCoKmd8UIUgeyoYXaNzR+RpoSvtvMOvV4uSvLndTYXhUdKXoGZcdrfM6udqVY=";

	public static void main(String[] args) throws Exception {
		
		String whitelistid = "1,2,4,5";
		String loginid = "";
		String[] whitelist = whitelistid.split(",");
		boolean flag=Arrays.asList(whitelist).contains(loginid);
		if(flag)
			System.out.println("yes");
		else
			System.out.println("no");
		
		//签名类型为SHA256WithRSA时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
		
		PassUpdatePhotoOpRequestV1 request = new PassUpdatePhotoOpRequestV1();
		
		//4、根据测试环境和生产环境替换相应ip和端口
		//行外流程ip:port
		request.setServiceUrl("http://ip:port/api/cert/epass/mediaprocess/epassphotoupdate/V1");

		//5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值			
		PassUpdatePhotoOpRequestV1Biz bizContent = new PassUpdatePhotoOpRequestV1Biz();
		
		//chanCommV10
		PassUpdatePhotoOpRequestV1.ChanCommV10RequestV1Message chanCommV10 = new PassUpdatePhotoOpRequestV1.ChanCommV10RequestV1Message();
		chanCommV10.setChantype("");
		bizContent.setChanCommV10(chanCommV10);
		
		//infoCommV10
		PassUpdatePhotoOpRequestV1.InfoCommV10RequestV1Message infoCommV10 = new PassUpdatePhotoOpRequestV1.InfoCommV10RequestV1Message();
		infoCommV10.setTrxtype("");
		infoCommV10.setTrxcode("PassUpdatePhotoDSF");
		bizContent.setInfoCommV10(infoCommV10);
		
		//inqCommV10
		PassUpdatePhotoOpRequestV1.InqCommV10RequestV1Message inqCommV10 = new PassUpdatePhotoOpRequestV1.InqCommV10RequestV1Message();
		inqCommV10.setInqtype("1");
		inqCommV10.setRowreq("1");
		inqCommV10.setInitflag("");
		bizContent.setInqCommV10(inqCommV10);
		
		//inputV10
		PassUpdatePhotoOpRequestV1.InputV10RequestV1Message inputV10 = new PassUpdatePhotoOpRequestV1.InputV10RequestV1Message();
		inputV10.setFcode("PassUpdatePhotoOp");
		inputV10.setServer_ip("ip:port");
		inputV10.setDt("2023-06-20");
		inputV10.setTm("083033");
		inputV10.setChannelIdentifier("12746");
		inputV10.setChannelCode("315");
		inputV10.setUserid("A20190725000061070");
		inputV10.setPortrait("_9j_4QC-RXhpZgAASUkqAAgAAAAIAAiSAwABAAAAAAAAABIBAwABAAAAAAAAABABAgAWAAAAbgAAAAEBAwABAAAAewAAADIBAgAUAAAAhAAAAAeSAwABAAAA__8AAAABAwABAAAAewAAAGmHBAABAAAAmAAAAAAAAABNRDU6MDMyOGM5YzU2YzcyOTMzZAAAMjAyMzowMjoyMCAwOTo1MjoxNQACAAECBAABAAAAtgAAAAICBAABAAAAAAAAAAAAAAD_2wBDAAsICAoIBwsKCQoNDAsNERwSEQ8PESIZGhQcKSQrKigkJyctMkA3LTA9MCcnOEw5PUNFSElIKzZPVU5GVEBHSEX_2wBDAQwNDREPESESEiFFLicuRUVFRUVFRUVFRUVFRUVFRUVFRUVFRUVFRUVFRUVFRUVFRUVFRUVFRUVFRUVFRUVFRUX_wgARCAB7AHsDASIAAhEBAxEB_8QAGgAAAgMBAQAAAAAAAAAAAAAAAAECAwQFBv_EABgBAQEBAQEAAAAAAAAAAAAAAAABAgME_9oADAMBAAIQAxAAAAHBXb1vJzy9xLhqUXDmkBoOLxERnt5qr1fB9EyNR3fWxieQgJZRkLEtNStTiCFDsrLHCQpZtn6GS29dbCUKdNRzlZ0o4JLsrWiTHX0Y8nMOhXyumvDX75qzvRLjn0LDnPohzY9RHIOnVZVox0nWlybS2-5ZowAAAUNNUASEJu3JT0UjTJQCgCIyURsBicAgYmICmRCSWLE01eXow9rPy3o9tAjdaFDcWBE3RSjnNXk-v5_zTf2Zvi85o6Pm-k93ZzOl6tAStiwT_8QAJBAAAgIBBAICAwEAAAAAAAAAAAECAxEEEBITICEwMSJAQQX_2gAIAQEAAQUC-js5Gl059JeP0cSUOaupdTUsraNLkVaXifQmPyT9yY484XaT8-pxMMTwZ3_mPgUvT9nBGDJnbEjjM4SOuRwkcZGJbej1viTOtiqFWcUYW-DBxQ6zqZ1sw0ZOK3zgdiQ9RE7TtO0VorTOd3HJ17OZK0csnHJ1nSdR1DqwYwciNgrBPOzsZ7ZGrJGoUDHi4jqJVHFoUmjtFSKC-Tgh1HT-uv0c_O5xiLUVikpfGtrdRGBPU2zPyRp9VKEq7I2L4f7ZNQhdY5z09DsJaaHG6lwno73C2MuSP778l9f6Darr9ywopN51iXTnBpG3X4__xAAUEQEAAAAAAAAAAAAAAAAAAABg_9oACAEDAQE_AQH_xAAeEQACAwEBAAMBAAAAAAAAAAAAAQIREhADEyAhMP_aAAgBAgEBPwFyJS-ikRkWfpTMsyzBg_UaYvMXmYRgwPzH5nxldssvmV_Cy-32-Vxs0RlyuskzRFiff__EACcQAAECBgAFBQEAAAAAAAAAAAABMQIRICEwQRASMlGhEyJAUGGR_9oACAEBAAY_AuE4pLctXMuPRs6vGFeCll8GxlpljephhhhvjtQwymx1Nm6tVuOPhY2bwbNjDGhs7fb3VDrh_pZcd-Dmj9E9RURCcKzx8xFfYrWFlDcucqr7UTHZdizE5UlPsJciWVyaOQz7Vf_EACUQAAICAQMEAgMBAAAAAAAAAAABESFBEDFRIGFxkTCBofDx4f_aAAgBAQABPyF5cjSqTQ_is0NJqSPA1WNQ30TuJOjiRUaJwsoSXCEpoVNBJL5Kqn0XmweC0OQnNEkkitkNRsClHClQMTtrUYD5T60xGQ2Yk8jUksFBt8katSoMQKjI1QEW9neEChcUvY4Fqt-pjNGOREPJ3E4aQLME7eDsoSsISsHaII0RyibBJhD7Q18DJTIkYeiFwQMMqEClz7E_9BPz7ELuX2KeGIII5E4LSlIQuPZ2PyPxJchN39Ev4J_wftAy0t_Q35CZYI91nkS8L2J0MYRBnbkghMIWWU6GJeWJeQxbISt2hk_A5G9iQklt8MjSY2Cnh-x33e_kjojrtpMMT-bwE5-bbF5Y24MmpfhiXXWjbihnHImxXtTmHQ1hv0IthQ2EHYZoT4R9tEMsskjTI3uwTByR1SY9Zpky3Y7G0qxmpEEpsioFraypEPYbqLzpLJMMtYa5FTZjZjyKCmaESMXPYtCUVx3JXIhuicDdemD_2gAMAwEAAgADAAAAEL_oML6Z6m_iswvvRupJHViRYHMqNpugidAINFKiJIEQhBiviNygZcAhDhPScA_B6f_EABoRAAMBAQEBAAAAAAAAAAAAAAABERAgMED_2gAIAQMBAT8Q84QhMm3iP4J4zKil5Y3lgsZ__8QAGhEAAwEBAQEAAAAAAAAAAAAAAAERECExQf_aAAgBAgEBPxCI1sb7j48-Oaw3CJA2KQtARO5SSIzbEI4VYQhkTKDb3uUrLRxlw3pO4-E80Y1zCQh-DPSqPtKIQ3D_xAAlEAEAAgIBAwQDAQEAAAAAAAABABEhMUEQUZFhcYHRIKGx8MH_2gAIAQEAAT8QNe3smQQYtYJEUm7GsPMBJlwrmXAy95djFvH4BzCxwntxHiZAbqsgfOzLl5fqGLr9YZ4snrPMeVUG7wVc2jTT_sZcDPJKkH-w4R34lv0dHshaUoWjvHNdBQb3mUNc-0fRUsl8QsMHZ_sxNUY3jY_yYYVqVlmozLliklu4AQYI5grFswvmUd4OZ4mhTBiATncUyvUoOq7xxVLeGW5_ZKNyuqHxKvf6nGQb7Sdj9j7hV9j7mB_4QJ9iI7_o-4HoPklDhUG9KJCPHrLgq0xhoJ7y7yfMqG93dlBeL1YJr5lBA57YlNRrwVE2D5YOnmYzR5S94e8UprHrODhCuWuBDiB8QXEoK5qXFpXapYW3HEXtDiliSzPaw7R7f8ww0JFPMToC90gjXhiVWvCJNnmDm1wI0Fs4iwyL9YP3l6R3MBOcnef77xdbf77wD4SOC7NR3K8zhEC8hHKHmY7kVw-eCPI_BA7cGtQTdITAGizXPUDtYHnZeGFY_wAfE2oxeo9pu5Hcgo5GBA_x5hh2EbkfMxtvP4XLlxLKlJBcs2K9puRfmXtSZHQX1s6OII3XReDcL5Y21ArfWnROnHQMxWblYanItgOjoS5fSupOeq1ogznOUVALX8yVKxOOlwEyPYgMXLQA-5Zi3VDCH8TPS-6CZhEb1iYv45vHUFgSwW_8lsULCibhp7JpBTlTzrDWr5hnQqNi8-pFeIlu4q94WLhRzKQE1MpbwRDNGdBYLDML1bIIatjmQHBhv6hEG-9sNQYBIUGDMKBDTZLJ2jI7zlM-xEwoCADK5ZMDlgkyxA9qevQaP5w4YyHdbduZjfhifohmqKWW7wKmNNenMFquYUz1ETb4Iw4hHUp2n__Z");
		bizContent.setInputV10(inputV10);
		
		request.setBizContent(bizContent);
		//建立返回项
		PassUpdatePhotoOpResponseV1 response = new PassUpdatePhotoOpResponseV1();
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


