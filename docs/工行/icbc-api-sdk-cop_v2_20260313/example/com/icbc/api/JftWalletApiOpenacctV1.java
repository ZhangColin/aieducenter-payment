package com.icbc.api;

import org.junit.Test;

import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.JftWalletApiOpenAcctRequestV1;
import com.icbc.api.request.JftWalletApiOpenAcctRequestV1.JftWalletApiOpenAcctRequestV1Biz;
import com.icbc.api.response.JftWalletApiOpenAcctResponseV1;


/**
 * 开户
 *
 * @author kfzx-wangpeng02
 *
 */
public class JftWalletApiOpenacctV1 {

	// 公钥（根据环境变化）
String MY_PRIVATE_KEY2 = "-----请使用正确的APP私钥-----EvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCcRR0FmtNV+Z4NVSfdNLLpOrbCIWKUS2OPYpXaHtbKT1cVgYf5Kg/s5mukcfU5cdGqO74NfegzbRyj+/YXTlADRYOSDTl/Pg+4pzUwmddfjJTwXbi+YY0L0YYPh1r503ih6zgQ4tkWiEP9qerKG4DMJhz1brNM+tPDxnQhEtqDp3uDUothZ1kLF3NwP25MhM0lF3h9dlEm2do5IwbgQbCYv4CISyaXkajHSVxLFwh0z06QximHtYlwWJCzKv1njUJSte+5E0SkSluShsaJI/yduvAs3ITrTW0XanIsKthSPJH7VIt8epiuXZxwdE4jwXkGaxTxW0MpR+zw+12EqJm9AgMBAAECggEAFfQjsqDgpchuquLWud3ocJmeCUHVbo30y7UG5mDEviFNU6kh6CA/ooFwh+a2k75ovSitd4nDsE3Y4zTHitimpv8CD+eptzWJ0eAzucMzHapLVkVkaeKchReHZPEYF9bcsKqx8rVb2C2Gl8FiSch5XLTSBqzppZvrWiqsL+VNK4eIgVSNuQFPjCNvfHxqflo+z1VtLmnMWqqdOi6Cvz7/rpClltwM6UZZIeSg2cDN9DYIJjLxzkgAChus+YyRJutLZgqKb1HTxOMbzRLa1tobzNIcsrbs5e7Ky72qRnjovFd6U5fzZ4guxIlzMq2GrEqMNxs7T/YCLZHF6r3DlQZRYQKBgQDbsWWkYSKrueI0kr1xTfRcBQNXBZ/GYjq7HWU38Aj4eZH728w8iVeuRoAvD3cmi99BSAuH89hrzigOS/ixJTit2zQLQTuLW+CCilspY/dg1iSwwFxM7qUQp4Q7NaxtSm4fsYH1+Gn9L35/eMW8MdhNjzP59x2ISKROQ9s7Tb35VQKBgQC2GHcCCip4K2TNbF9JzEzX9atnLfX+87I5+T4oa+CNQjsOElrw6hKmqhxSEFeDrbauKDHUrUFJuqpuhC6UkPNIGustLpHDmz9tFo4Z8R/3ibIQ6Tdoic3ZhER5FESM3LPuBLEbYhlGMqb0XmaoI2zeHzpf7aoU+ipZWCJ88qh+yQKBgE1C7gQJRTAvhyYGFSmryKwz9yC7kZ0rHox/ogEyIXWBC54PhpwjkPpxQNNMFRMxTaqfs650S0BemCevAB8MgApkdMEgh9hSz3oZPkYoKe+cX/rpK4zCwLpCr3ZDQgiNjH0I6DOg0WWBNnON/bxZq0BbhGP8TB6JrX74zjjk9lKhAoGBAKZ5iVtxBdClA1wWhqsGPv5018XVq/7wtnDv35a+wSUDds/vkfqMVI7QGGIiFJAK4Wco0h24Y2bPY/7zk045kmDKU1UULSjiJsNJipJG8k2xMfzp6cEFsw74ME1tbRpGr6yb13rMXYTiy+W+5CU1ddNAQ3Lt6b+R0eOqgqUuh0KZAoGBAKekWvDphNf8IRILhnkUfSK9HF08ztRSUN9jVoUCLLd4fWjqIRvLuW+QPtNMY/xAR38MR58TqMX0jx+N8spk0cMYSJFzUmjh/5BQjmtxxobUcZxU6kxmldWfUG/bHWJDJPj8FWrDhst0fctfCeDGCtXcs3LGjn42Inq6iNEl8Z8K";
// 公钥（根据环境变化）
String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

	protected static final String APP_ID = "10000000000000008062";// 根据实际情况确定appid

	// 发送

	public void test_cop1() {
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA",
				MY_PRIVATE_KEY2, "UTF-8", "json", APIGW_PUBLIC_KEY, "AES",
				"i5N/TQHG9hq00OpdNzpUUw==", "", "");

		JftWalletApiOpenAcctRequestV1 request = new JftWalletApiOpenAcctRequestV1();

		request.setServiceUrl("http://ip:port/api/jft/wallet/api/openacct/V1");//根据实际情况确定url和端口
		JftWalletApiOpenAcctRequestV1Biz bizContent = new JftWalletApiOpenAcctRequestV1Biz();

		// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
				bizContent.setAppId("10000000000000056018");//商户appid
				bizContent.setCorpSerno("100000000000000080622018091200000001");//每次交易改流水号，必须由20位APPID+交易单号组成，最大长度不得超过36位。
				bizContent.setBindMedium("6212264000000505871");// 绑定的I类卡号
				bizContent.setCertNo("433023197208285180");// 身份证
				bizContent.setCertType(0);// 证件类型
				bizContent.setCorpDate("2017-03-15");
				bizContent.setCorpMediumId("100024577653");// 合作方介质号，如微信号
				bizContent.setCustName("窑故");
				bizContent.setMobileNo("13528604603");
				bizContent.setNotifyAddr("https://apipcs4.dccnet.com.cn/api/jft/api/wallet/openAcct/V1");//合作方可接收http请求的回调地址
				bizContent.setOutServiceCode("openaccount");//固定不改
				bizContent.setTrxAccDate("2017-03-15");
				bizContent.setTrxAccTime("13:30:01");
				bizContent.setUserProFlag("01");

				//风险特征值
				bizContent.setCustIp("82.32.02.33");
				bizContent.setCustMac("1121212");
				bizContent.setDeviceId("82.39.02.33");
				bizContent.setBrowserLang("");
				bizContent.setBrowserVersion("");
				bizContent.setChannelType("1");//1-app,CustIp、CustMac和DeviceId必输；2-非app
				bizContent.setCvn2("");
				bizContent.setExpired("");
				bizContent.setFactoryInfo("");
				bizContent.setMemoryCapacity("");
				bizContent.setScreenResolution("");
				bizContent.setSystemVersion("");
				bizContent.setCpuId("");

		// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		System.out.println(bizContent);
		request.setBizContent(bizContent);

		JftWalletApiOpenAcctResponseV1 response;
		try {
			response = (JftWalletApiOpenAcctResponseV1) client.execute(request,
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
		new JftWalletApiOpenacctV1().test_cop1();
	}
}
