package com.icbc.api;

import java.util.Calendar;
import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.PassVerifyInfoModifyRequestV1;
import com.icbc.api.request.PassVerifyInfoModifyRequestV1.PassVerifyInfoModifyRequestV1Biz;
import com.icbc.api.response.PassVerifyInfoModifyResponseV1;



public class PassVerifyInfoModifyTest {

	//1、网关公钥千万不要改 这个是不变的API公钥
	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

	//2、mims
	protected static final String APP_ID = "10000000000001214554";
	//3、密钥对认证方式，公钥在API平台登记，此处为私钥 行外访问
	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDCKa1UknsiKQXzsnf3IWDJ7URfZVQqj0Mh4cQsLEnNcyFShxKX4ncJF5ngRrzHu9960F731KRBQnU/dB7uYp+1l9rmLBthr0Qj+W12EQLc5iDm/1iEXvV3HErEGAntKxw8FaK1gtxSjxGC8pd7iRGp0ZaXxghxgxqk4MDUkf9PWCsHShKZa+HZwCs7tO/q1GWo6UiVrRMqQ0veZdBAcq1MI1qjoYkocFfoioYGo6MXIrvudmPJR6D6yboP95HsUFeoddrSNFm7jlpNFpquAXDNb38IoQkYlbUkbn6UZNg1NJDyjXfnWhAamw0l2vUaQhiZ52vOtIbXCqZarv7YG9bTAgMBAAECggEAQSL+NRsXF/SvPHwo5s1ZzSQ+BcrWm5iIKvu0diU9dSEkkq0hvbaJvJEgXC8e8drDNE5nLbk2iXYkI29vWQXZEnCpTQKLBRBpSbBJ2owGa9kAPL55EZ9Rte3V30c5PUlzJW0UstP92eyJMWb1DsqDFHdtj+ktt/cFnz6PMkDwZ8tUobhZh2ekSPYaIomRmlDfPgN4E/xcYeda9M4AsaYOXxeTeiaVQQHKqKiMTE0gsb9pq4iL6qTtrA8kQIsQ8NwIU4nRb1bNl2NExs+TxTQSbNruD1Gnf83QdKMBff8cOTcP6IrFc17au3aQ3AopT/hPI+iOqH5eOqM0V7GPQvc6MQKBgQDnkz4+5lyQO505ss0IayP3UiBlkEIONP/m1qQYaTcc5ZW2PIiu1tM4IobNTKdVcmm05hzAwdeN8QdsLAFR7+8BP/Gv1rHfy1ZuFbqPpSTO+1Mo5iSyOfRe3b/QLe6ror81PXA93QnJw173Kzs/v49NrpyMMGbQj0m8bTQZ56Oz5QKBgQDWpENmWA/PvG3Yxc5nHaqXSp7OHYP6tV2DZTjFGG3XdfhivCpxHEXqiG2fSV+o37V4pDwFf4uMzBWHw4HLgJ5uJK2P08038HD5rO8qxDZFLA4BBA1dWZK7klmQUwhk4KqUmoMKOrOYTyl/pp0hi/S65Q4QKSHIhqjqi8RDIZ+kVwKBgEqQYKSELB3M9C0V42rohKYn7YKGVp6YdiVvy1qI1lQZoIETMLeLOGuEwn7sRZz/wAWdaniucaekik1MRgiKBx0R59JEgPWcVXIQ0Qdbl2SGDBrJL9urSYIaSJ7Ri138gzCzXaatu28Z10QiyWsXVR8gMAv7sb9Jw+ZGC1aj/VP5AoGBAKWsCl3/kMy+W2cbXvJWgqreLO9Y9trXjINz6Lqw3W+WHg9Pv86ojQq3HS36e3ykl7Szx6RSy0r055VwAUorT54ib1j5mYgY7Yq5wa0X3+/KTlDYaNxp3AktsDXm8S6z/tZO4OBPaPoh+B0QZG5VUaPEUvOEXBAkaFMJaIaMeGVnAoGBAIclLqmP9yrwTOcOX3s5OtaoLFhiPWYrU7OrIJrMFEZnd1lUslbIaAAGChwwlO1ugopi7SAL6JBhQusaZ3BEoFpR0qoXyqpquV7pis/4PRq8w/VQJsdyT0rhaGiFRynAK5ccVWli157csu3/xJSYk4CYjuFLCCxYLzAq0zIZmArL";

	public static void main(String[] args) throws Exception {
		
		//签名类型为SHA256WithRSA时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
		
		PassVerifyInfoModifyRequestV1 request = new PassVerifyInfoModifyRequestV1();
		
		//4、根据测试环境和生产环境替换相应ip和端口						/cert/epass/authentoken
		//行外流程							/epass/PassTransInfoE/cert/epass/epassverifyinfomodify/V1
		request.setServiceUrl("http://ip:port/api/cert/epass/epassverifyinfomodify/V1");
		PassVerifyInfoModifyRequestV1Biz bizContent = new PassVerifyInfoModifyRequestV1Biz();
		//包头
		bizContent.setVersionid("1");
		bizContent.setAppsenderid("F-MIMS");
		bizContent.setAppreceiverid("F-EPASS");
		bizContent.setOrigsenddate("1");
		bizContent.setOrigsendtime("2");
		bizContent.setStructtype("JSON");
		bizContent.setMesgcharset("UTF-8");
		bizContent.setMesgstat("9");
		bizContent.setMsg_id("ip:port.20181011154033.2");
		bizContent.setMesgpriority("1");
		//chanCommV10
		PassVerifyInfoModifyRequestV1.ChanCommV10RequestV1Message chanCommV10 = new PassVerifyInfoModifyRequestV1.ChanCommV10RequestV1Message();
		chanCommV10.setCHANNELIDENTIFIER("ip:port.20181011154033.2");
		chanCommV10.setCHANTYPE("309");
		chanCommV10.setIP("ip:port");
		bizContent.setChanCommV10(chanCommV10);
		//infoCommV10
		PassVerifyInfoModifyRequestV1.InfoCommV10RequestV1Message infoCommV10 = new PassVerifyInfoModifyRequestV1.InfoCommV10RequestV1Message();
		infoCommV10.setTRXCODE("passVerifyInfoModifyAPI");
		infoCommV10.setWORKDATE("2020-10-21");
		infoCommV10.setWORKTIME("191518");
		bizContent.setInfoCommV10(infoCommV10);
		//inputV10
		PassVerifyInfoModifyRequestV1.InputV10RequestV1Message inputV10 = new PassVerifyInfoModifyRequestV1.InputV10RequestV1Message();
		inputV10.setUSERID("201311290001092163");
		inputV10.setLOGIN_IDFLAG("0");
		inputV10.setPASSWORDFLAG("0");
		inputV10.setEMAILFLAG("0");
		inputV10.setPROVINCEFLAG("0");
		inputV10.setCITYFLAG("0");
		inputV10.setMOBILEFLAG("0");
		inputV10.setCIS_CODEFLAG("0");
		inputV10.setNICKFLAG("1");
		inputV10.setLOGIN_ID("");
		inputV10.setPASSWORD_OLD("");
		inputV10.setPASSWORD_NEW("");
		inputV10.setEMAIL("");
		inputV10.setPROVINCE("");
		inputV10.setCITY("");
		inputV10.setMOBILE("");
		inputV10.setCIS_CODE("b280600000011255");
		inputV10.setNICKNAME("昵称0");
		inputV10.setTEMP1("");
		inputV10.setTEMP2("");
		inputV10.setTEMP3("");
		bizContent.setInputV10(inputV10);
		request.setBizContent(bizContent);
		System.out.println(request.getBizContent());
		//建立返回项
		PassVerifyInfoModifyResponseV1 response = new PassVerifyInfoModifyResponseV1();
		try {
			response = client.execute(request);	
			System.out.println(JSONObject.toJSONString(response));
			String retcode = response.getAppstatv10().getTransok();
			System.out.println("transok:"+retcode);
			String return_code = response.getAppstatv10().getReturn_code();
			System.out.println("return_code:" + return_code);
			System.out.println("rresponse:" + response.getAppstatv10().getReturn_msg());
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}
	}

	
}

