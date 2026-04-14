package com.icbc.api;


import com.icbc.api.request.MybankOpayWalletBindRequestV1;
import com.icbc.api.request.MybankOpayWalletBindRequestV1.MybankOpayWalletBindRequestV1Biz;
import com.icbc.api.response.MybankOpayWalletBindResponseV1;

public class MybankOpayWalletBindTestV1 {
	
	// 调用方自己的AppId
		//private static final String APP_ID = new String("10000000000004095545"); //10月
		//private static final String APP_ID = new String("10000000000004095745"); //9月
		//private static final String APP_ID = new String("10000000000004095775"); //11月
//		private static final String APP_ID = new String("test2002 "); //20年2月10000000000004096999
	
		private static final String APP_ID = "10000xxxxxxx9"; //20年2月
		// 调用方私钥
		private static final String MY_PRIVATE_KEY =
				"-----请使用正确的APP私钥-----EvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDSWGaqBWWaWFYMOyqQ7wRHKEjdUHH6RXNs/eopPTHvo5xIk1QjXyHCwSqBKb6U9pytkelKe4HV/37npNz03rCGvxUKZC47Vu9dMZ5/By+jcIP2otj+KO91QcOke96juISVSOJ7dNuBOpduGMOJ4Lw/6kH56m1aQa339CAoEwA/vouxslUYAFrwbwCBJ0E8UPzkMCj2esZakDvM7A+sphsqTC641mR4aI0aU+OkIIBYtPAuUK75JKiHjDZ9t/xkzyMIrqJatAWeef92WwRgFXBHb5FskjEZGPcsR3CNTbzBppGxKksHu2PA6yhD6o2joyRAZvz1sXJI33Z1IC1a1wmvAgMBAAECggEAe6EXnkCTyha3ZHL1/r9s3UHRKbrZcsaVI+9NTLW5/uZDuehV4IBOgIA2MjpN2JHl3qJO4scataN4fD/K2mV0jY4LmNLu1VOwVi8OwAyCNGayOSjXrvJyY5GmoqtnXQTbcD6pVvSE+zxrunr1mrkrC8nswy3E3fKShJTIvaUFVXDgSlylUxSjwTqRCm4YGDOfRHG3txjgxu21+32Crx5OeHT2jxo++pBaEfYN05o2GTMPvZtb+9ufIgemhwKNT4EacZ1DyWjLN1GPMLQAmmQPfKp9bDLWRZ/embYMjlQxw5CjfNhcyUdtnuXOEVQNfYJpBcW2ChkqjvLz66cji3jbyQKBgQD720I1CjJ/0aSRYy5kxkNj0Z+gnk+DlDeEqZ51QEgXbHoRr0MgsgSKfXa6w2GRxhersR6NEc7jaTZ4N0tVkFZXGS7ScynTwcHBFAfcTD90XXHz2LM9uYVnRRRYarBbVNKtGZ2VoCYALvi3x+IqmSByIZ4p+kdXFLX8QGpP13aszQKBgQDVzk9tunZvQxbm5jQ70tqlYyKPGs1HkxUISq8556bkOjZeimdriie2fJAsSiVG2YiScxzyjjvZsnGt/OKXOYCvlCGKpd27UnaGIiEQJiXlr0rxJbsBbvDxzyZYaCd98DfFDZyHWYhG4+CAew/HTMkqzGNkoBbmVWAs5sPQZzAQawKBgQDTOogjneLOqtAgE5PUdygL7sK3Xi8IBuYtQGkio4ATDSqv7FMbd9IxKJrMdpM9RGuT1t/tprLirdo+Jvnq0DxpnjhTzryU2hsbSSZykAxkFv88aVGjAhatjxVKNmikoNZ00VgGJqPYYYy97kl0OqsoraNOR5dPpJIg9xFy1x20PQKBgQCJeRVTTZB3a+De6Gfvi10w+HQxJZ+WhPkCEttmVQ/XMaLh2QY0fGmaia89+xLK4nZRDBkx0Mik6nrzz2V2rO+UdZ+3gXz3xEcXafxuYMj/TV3XflYmzsm4AWT7dUTnUjzZxC3ATY41XrJsijQUBshWb4L9IX537a9gT6TabG7YLwKBgQDXxTjd9+AKvr/CCrRmFObkfJB/uECIWDVath/Ea1eHPBH6XI92c4eMkpoFBhuJ790PBnIsXTWr0n9pqj1NWG7Fng9rWO5HV0NsfoaNqmC4LntKZe4gQn82rP7JmepgbzeMzuxgFdEsT5Gbvttpi/2oNvDsEzyXo9vf+3J1n9CAnw==";
		// API网关公钥
		private static final String APIGW_PUBLIC_KEY =
				"-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
		// API请求地址
		//private static final String API_SERVICE_URL = new String("http://ip:port/api");
		//private static final String API_SERVICE_URL = new String("http://ip:port/api");
		//private static final String API_SERVICE_URL = new String("http://ip:port/api");
//		private static final String API_SERVICE_URL = new String("http://ip:port/api");
//		private static final String API_SERVICE_URL = new String("http://ip:port/api");http://ip:port/api
		private static final String API_SERVICE_URL ="http://ip:port/api";
		
		// AES加密密钥
		private static final String AES_KEY = "oYu6vN8oDimqEM8jztvcog==";

		public static void main(String[] args) {

			// 签名类型为SHA256WithRSA时，传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
			DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,IcbcConstants.SIGN_TYPE_RSA, MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8,IcbcConstants.FORMAT_JSON,APIGW_PUBLIC_KEY,IcbcConstants.ENCRYPT_TYPE_AES, AES_KEY, null, null);
			
			MybankOpayWalletBindRequestV1 request = new MybankOpayWalletBindRequestV1();
			
			//访问工行API平台URL
			request.setServiceUrl(API_SERVICE_URL + "/mybank/opay/wallet/bind/V1");
			

			MybankOpayWalletBindRequestV1Biz bizContent = new MybankOpayWalletBindRequestV1Biz();

			//APPID
			bizContent.setBind_wallet_session_id("11111111");
			bizContent.setPalm_global_user_id("1111111");
			request.setBizContent(bizContent);

			MybankOpayWalletBindResponseV1 response;

			try {
				response = client.execute(request);
				
				
			} catch (IcbcApiException e) {
				bizContent.setBind_wallet_session_id("11111111");
			}

		}
}
