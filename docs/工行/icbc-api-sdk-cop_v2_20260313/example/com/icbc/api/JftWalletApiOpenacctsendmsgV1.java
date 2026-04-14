package com.icbc.api;

import org.junit.Test;

import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.JftWalletApiOpenAcctSendMsgRequestV1;
import com.icbc.api.request.JftWalletApiOpenAcctSendMsgRequestV1.JftWalletApiOpenAcctSendMsgRequestV1Biz;
import com.icbc.api.response.JftWalletApiOpenAcctSendMsgReponseV1;


/**
 * 开户
 *
 * @author kfzx-wangpeng02
 *
 */
public class JftWalletApiOpenacctsendmsgV1 {

	// 公钥（根据环境变化）
String MY_PRIVATE_KEY2 = "-----请使用正确的APP私钥-----EvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCcRR0FmtNV+Z4NVSfdNLLpOrbCIWKUS2OPYpXaHtbKT1cVgYf5Kg/s5mukcfU5cdGqO74NfegzbRyj+/YXTlADRYOSDTl/Pg+4pzUwmddfjJTwXbi+YY0L0YYPh1r503ih6zgQ4tkWiEP9qerKG4DMJhz1brNM+tPDxnQhEtqDp3uDUothZ1kLF3NwP25MhM0lF3h9dlEm2do5IwbgQbCYv4CISyaXkajHSVxLFwh0z06QximHtYlwWJCzKv1njUJSte+5E0SkSluShsaJI/yduvAs3ITrTW0XanIsKthSPJH7VIt8epiuXZxwdE4jwXkGaxTxW0MpR+zw+12EqJm9AgMBAAECggEAFfQjsqDgpchuquLWud3ocJmeCUHVbo30y7UG5mDEviFNU6kh6CA/ooFwh+a2k75ovSitd4nDsE3Y4zTHitimpv8CD+eptzWJ0eAzucMzHapLVkVkaeKchReHZPEYF9bcsKqx8rVb2C2Gl8FiSch5XLTSBqzppZvrWiqsL+VNK4eIgVSNuQFPjCNvfHxqflo+z1VtLmnMWqqdOi6Cvz7/rpClltwM6UZZIeSg2cDN9DYIJjLxzkgAChus+YyRJutLZgqKb1HTxOMbzRLa1tobzNIcsrbs5e7Ky72qRnjovFd6U5fzZ4guxIlzMq2GrEqMNxs7T/YCLZHF6r3DlQZRYQKBgQDbsWWkYSKrueI0kr1xTfRcBQNXBZ/GYjq7HWU38Aj4eZH728w8iVeuRoAvD3cmi99BSAuH89hrzigOS/ixJTit2zQLQTuLW+CCilspY/dg1iSwwFxM7qUQp4Q7NaxtSm4fsYH1+Gn9L35/eMW8MdhNjzP59x2ISKROQ9s7Tb35VQKBgQC2GHcCCip4K2TNbF9JzEzX9atnLfX+87I5+T4oa+CNQjsOElrw6hKmqhxSEFeDrbauKDHUrUFJuqpuhC6UkPNIGustLpHDmz9tFo4Z8R/3ibIQ6Tdoic3ZhER5FESM3LPuBLEbYhlGMqb0XmaoI2zeHzpf7aoU+ipZWCJ88qh+yQKBgE1C7gQJRTAvhyYGFSmryKwz9yC7kZ0rHox/ogEyIXWBC54PhpwjkPpxQNNMFRMxTaqfs650S0BemCevAB8MgApkdMEgh9hSz3oZPkYoKe+cX/rpK4zCwLpCr3ZDQgiNjH0I6DOg0WWBNnON/bxZq0BbhGP8TB6JrX74zjjk9lKhAoGBAKZ5iVtxBdClA1wWhqsGPv5018XVq/7wtnDv35a+wSUDds/vkfqMVI7QGGIiFJAK4Wco0h24Y2bPY/7zk045kmDKU1UULSjiJsNJipJG8k2xMfzp6cEFsw74ME1tbRpGr6yb13rMXYTiy+W+5CU1ddNAQ3Lt6b+R0eOqgqUuh0KZAoGBAKekWvDphNf8IRILhnkUfSK9HF08ztRSUN9jVoUCLLd4fWjqIRvLuW+QPtNMY/xAR38MR58TqMX0jx+N8spk0cMYSJFzUmjh/5BQjmtxxobUcZxU6kxmldWfUG/bHWJDJPj8FWrDhst0fctfCeDGCtXcs3LGjn42Inq6iNEl8Z8K";
//私钥（根据环境变化）
String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";


	protected static final String APP_ID = "10000000000000008062";// (根据实际情况确定appid)



	// 重新发送

	public void test_cop() {
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA",
				MY_PRIVATE_KEY2, "UTF-8", "json", APIGW_PUBLIC_KEY, "AES",
				"i5N/TQHG9hq00OpdNzpUUw==", "", "");
		JftWalletApiOpenAcctSendMsgRequestV1 request = new JftWalletApiOpenAcctSendMsgRequestV1();

		request.setServiceUrl("http://ip:port/api/jft/wallet/api/openacctsendmsg/V1");//根据实际情况确定url和端口

		JftWalletApiOpenAcctSendMsgRequestV1Biz biz = new JftWalletApiOpenAcctSendMsgRequestV1Biz();

		// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
				biz.setAppId("10000000000000012090");
				biz.setCorpDate("2017-03-15");
				biz.setCorpSerno("321654987");
				biz.setCorpSernoOriginal("AasD123467524");
				biz.setOutServiceCode("scode");
				biz.setTrxAccDate("2018-10-09");
				biz.setTrxAccTime("15:47:00");
				biz.setType("5");
				// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
				System.out.println(biz);
				request.setBizContent(biz);

				JftWalletApiOpenAcctSendMsgReponseV1 response;

				try {

					response = (JftWalletApiOpenAcctSendMsgReponseV1) client.execute(request, System.currentTimeMillis() + "");
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
				new JftWalletApiOpenacctsendmsgV1().test_cop();
			}
		}
