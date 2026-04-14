package com.icbc.api;

import org.junit.Test;

import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.JftWalletApiCheckSMSRequestV1;
import com.icbc.api.request.JftWalletApiCheckSMSRequestV1.JftWalletApiCheckSMSRequestV1Biz;
import com.icbc.api.response.JftWalletApiCheckSMSResponseV1;
/**
 * 开户
 *
 * @author kfzx-wangpeng02
 *
 */
public class JftWalletApiChecksmsV1 {

	// 公钥（根据环境变化）
String MY_PRIVATE_KEY2 = "-----请使用正确的APP私钥-----EvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCcRR0FmtNV+Z4NVSfdNLLpOrbCIWKUS2OPYpXaHtbKT1cVgYf5Kg/s5mukcfU5cdGqO74NfegzbRyj+/YXTlADRYOSDTl/Pg+4pzUwmddfjJTwXbi+YY0L0YYPh1r503ih6zgQ4tkWiEP9qerKG4DMJhz1brNM+tPDxnQhEtqDp3uDUothZ1kLF3NwP25MhM0lF3h9dlEm2do5IwbgQbCYv4CISyaXkajHSVxLFwh0z06QximHtYlwWJCzKv1njUJSte+5E0SkSluShsaJI/yduvAs3ITrTW0XanIsKthSPJH7VIt8epiuXZxwdE4jwXkGaxTxW0MpR+zw+12EqJm9AgMBAAECggEAFfQjsqDgpchuquLWud3ocJmeCUHVbo30y7UG5mDEviFNU6kh6CA/ooFwh+a2k75ovSitd4nDsE3Y4zTHitimpv8CD+eptzWJ0eAzucMzHapLVkVkaeKchReHZPEYF9bcsKqx8rVb2C2Gl8FiSch5XLTSBqzppZvrWiqsL+VNK4eIgVSNuQFPjCNvfHxqflo+z1VtLmnMWqqdOi6Cvz7/rpClltwM6UZZIeSg2cDN9DYIJjLxzkgAChus+YyRJutLZgqKb1HTxOMbzRLa1tobzNIcsrbs5e7Ky72qRnjovFd6U5fzZ4guxIlzMq2GrEqMNxs7T/YCLZHF6r3DlQZRYQKBgQDbsWWkYSKrueI0kr1xTfRcBQNXBZ/GYjq7HWU38Aj4eZH728w8iVeuRoAvD3cmi99BSAuH89hrzigOS/ixJTit2zQLQTuLW+CCilspY/dg1iSwwFxM7qUQp4Q7NaxtSm4fsYH1+Gn9L35/eMW8MdhNjzP59x2ISKROQ9s7Tb35VQKBgQC2GHcCCip4K2TNbF9JzEzX9atnLfX+87I5+T4oa+CNQjsOElrw6hKmqhxSEFeDrbauKDHUrUFJuqpuhC6UkPNIGustLpHDmz9tFo4Z8R/3ibIQ6Tdoic3ZhER5FESM3LPuBLEbYhlGMqb0XmaoI2zeHzpf7aoU+ipZWCJ88qh+yQKBgE1C7gQJRTAvhyYGFSmryKwz9yC7kZ0rHox/ogEyIXWBC54PhpwjkPpxQNNMFRMxTaqfs650S0BemCevAB8MgApkdMEgh9hSz3oZPkYoKe+cX/rpK4zCwLpCr3ZDQgiNjH0I6DOg0WWBNnON/bxZq0BbhGP8TB6JrX74zjjk9lKhAoGBAKZ5iVtxBdClA1wWhqsGPv5018XVq/7wtnDv35a+wSUDds/vkfqMVI7QGGIiFJAK4Wco0h24Y2bPY/7zk045kmDKU1UULSjiJsNJipJG8k2xMfzp6cEFsw74ME1tbRpGr6yb13rMXYTiy+W+5CU1ddNAQ3Lt6b+R0eOqgqUuh0KZAoGBAKekWvDphNf8IRILhnkUfSK9HF08ztRSUN9jVoUCLLd4fWjqIRvLuW+QPtNMY/xAR38MR58TqMX0jx+N8spk0cMYSJFzUmjh/5BQjmtxxobUcZxU6kxmldWfUG/bHWJDJPj8FWrDhst0fctfCeDGCtXcs3LGjn42Inq6iNEl8Z8K";
// 公钥（根据环境变化）
String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";


	protected static final String APP_ID = "10000000000000008062";//根据实际情况确定appid


	// 校验
	
	public void test_cop2() {
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA",
				MY_PRIVATE_KEY2, "UTF-8", "json", APIGW_PUBLIC_KEY, "AES",
				"i5N/TQHG9hq00OpdNzpUUw==", "", "");
		JftWalletApiCheckSMSRequestV1 request = new JftWalletApiCheckSMSRequestV1();

		request.setServiceUrl("http://ip:port/api/jft/wallet/api/checksms/V1");//根据实际情况确定url和端口

		JftWalletApiCheckSMSRequestV1Biz bizContent = new JftWalletApiCheckSMSRequestV1Biz();

		// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//		bizContent.setCorpNo("10000000000000007541");
		bizContent.setCorpSerno("100000000000000080622018091200000002");//交易改流水号，必须由20位APPID+交易单号组成，最大长度不得超过36位。
		bizContent.setTrxAccDate("2017-03-15");
		bizContent.setTrxAccTime("13:30:01");
		bizContent.setCorpDate("2017-03-15");
		bizContent.setCorpSernoOriginal("100000000000000080622018091200000001");//短信发送接口上送的合作方交易单号
		bizContent.setOutServiceCode("scodeverify");//外部服务代码  调个金用  固定不变
		bizContent.setSmsSendNo("233977");//验证码编号
		bizContent.setSmsScode("2343");//验证码
	//	bizContent.setCertNo("433023197208285180");//身份证
		//bizContent.setCertType(0);//证件类型
		bizContent.setAppId("10000000000000056018");

		// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		System.out.println(bizContent);
		request.setBizContent(bizContent);
		JftWalletApiCheckSMSResponseV1 response;
		try {
			response = response = client.execute(request,
					System.currentTimeMillis() + "");
			System.out.println("~~~~~~~~~");
			System.out.println(JSONObject.toJSONString(response));
			System.out.println("~~~~~~~~~");
			if (response.isSuccess()) {
				// 业务成功处理
				System.out.println(response.getReturnCode());
			} else {
				// 失败
			}
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}
	}



	public static void main(String[] args) {
		new JftWalletApiChecksmsV1().test_cop2();
	}
}
