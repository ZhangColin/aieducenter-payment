package com.icbc.api;

import com.icbc.api.BizContent;
import com.icbc.api.IcbcApiException;
import com.icbc.api.UiIcbcClient;
import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.AccountdetailserviceAdsagrsignRequestV1;

public class AccountdetailserviceAdsagrsignuiV1Test {

	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDyFCsJJh5/uYOAfh0I5PC/vcRWg0m73BtQDLPPn9KXJtaDl6fbdh1VNpTi4UeuksEKWzqDlVt6Fz+Etib7w1F4I75bMGKrsDu0EGvyTnDjiJOMS4FlhMkZ9FxOzSZD0XUO66XIpDJIs0KWHeVetG14nRK84yxp7+EQ/sQXzgHxjZlnMqOTePUvezrJeM0v637+MWiKLi3PhmLYdF1XDHyfHEjDzX+VFrl+VH5+iihQTmBsiwAXO3ofnuTL3MSPT76C1A8E27gMVW3rTtPEfdj8fXTZR7GFlWqGlfz/s8Ge7/JMwqqM0RLdNrJ9fyIG8E+T8C769qDTcipXGI6UEfXZAgMBAAECggEAa+JPF6G0X+m2UFJj8C3PmTQiRwf1mVJLnc47aGFqwdAeM8EnnqeyBOLxUXdisctXWp0zo6axgOTRevyOvQ7bxPXxBMS4hJli0imrYe5RYyXf09tmjG60DOwZI0lML1cumvqsBJIgksPI/gvOx/kS/HjL/AAmMAQmwqM3kwBNX2+Eo/az+cf089oshD5+Z/5ouC91LK6XyUd3YNjonHvxnNFR+4yekX5uPW4pTyzm95UXTTrGOrVT3CyiY9aCXABVTE0A0mmbZEifcyiyK/DyI1vvsdfl8utw0e5xTfORN6lrH0SWYB5rpO6Zwwz3uwuBS/HacwzhmavxUIP5ooGLsQKBgQD/PUaWh7X9+kABew9ufPCrSjeUCX3eiQUGgXY+d0rkeTdx3U8HnWU6EjKYx1z6eLZpWDIoDyi/6gRCHbsNQsO7Hw7GBKRdThJ3RtDnchOCbCGBsXwfopQTJZwLfAvU8U5Ny7f/0f98F2gLWKWDyqSenpyPe14af7ju0YtGYm4mfQKBgQDyzNoghIAXQ2c9ElPI5FHZPv8Rg8josxEQ9Tdg44Uz1Z2bnWotM2+NUy8DF8qdel5K1dEUE5OlqOdQ0cwmb11CC5nECCUShTf7s64uMbrGKFFVvNJY2HddWDNDvEmy9WTS8aPCicM+IQFRQjBgT39tnNnoksDUUDi4SaqttwY/jQKBgQC+vrtO6ZLPgEJvGNlad9KgH/HTFxjSwtFXoE6OtjXo8tHFrV9PUIgC0vCvDmZk5OtlZLzvBQ0uByQxiOhuK5P89mPjBjsTY7IXI3whSzZbRJXxx4L8XVKN0JCCYKyy8bbUGseiTfzBbviZaiEfkcReqihUbhzQbtz01PJholOxPQKBgQC7z2Wy3YtPc136SaDxtlrxlt8qpyvThB/hZC0wDw5MKCK/6qXkzPKu+PdWhyK9aZhifr45r2L4M4yqYX5vBJm4qsyIbFxw1QeW9Uf4h5MYELvJfEOhYeADHmSZMp43hcY1vaCUrIeQGWuxdWeCODwzh66vTkVGdb9KHgGeWgLFSQKBgFVt/jSCTCLW4apDLsPHIsxKL1SPNBdMcX4JxsjZgqvp7pk0nR9JGEp6Nbt0FpvMARDV4VpEiw/jDmU2MhVWbY17HxCqBJXreGuLfvI3MGj2+ww8ZJ0nrvUxIJTwlALII9ZgW7LgrwQXONAz11YswJ4nc2D6UaSWPWkiAKz35dMX";

	protected static final String APP_ID = "10000000000004097541";

	protected static final String SIGN_TYPE = "rsa2";

	protected static final String CHARSET = "UTF-8";

	protected static final String BIZ_CONTONT = "{\"app_id\" : \"10000000000004097541\",\"api_name\" : \"ADSSIGN\",\"api_version\" : \"001.001.001.001\",\"corpNo\" : \"100004\",\"coMode\" : \"1\",\"accCompNo\" : \"10011005\",\"account\" : \"0200099809277675078\",\"currType\" : \"1\",\"accFlag\" : \"1\",\"cntioFlag\" : \"1\",\"phone\" : \"10012341234\",\"epType\" : \"1\",\"remark\" : \"1\",\"accList\" : [{\"account\" : \"0200099809277675078\",\"currType\" : \"1\",\"accFlag\" : \"1\",\"cntioFlag\" : \"1\",\"isMainAcc\" : \"1\",\"receiptFlag\" : \"1\",\"statementFlag\" : \"1\"}]}";

	public static void testRequest() {

		try {
			// 构造client对象
			UiIcbcClient client = null;

			if (SIGN_TYPE.equals("rsa")) {
				client = new UiIcbcClient(APP_ID, MY_PRIVATE_KEY, CHARSET);
			}
			if (SIGN_TYPE.equals("rsa2")) {
				client = new UiIcbcClientRsa2(APP_ID, MY_PRIVATE_KEY, CHARSET);
			}

			AccountdetailserviceAdsagrsignRequestV1 request = new AccountdetailserviceAdsagrsignRequestV1();
			// request.setServiceUrl("https://ip:port/ui/enterprise/salary/normalroom/V1");
			request.setServiceUrl("http://ip:port/ui/mybank/account/accountdetailservice/adsagrsignui/V1");
			BizContent bizContent = JSON.parseObject(BIZ_CONTONT, request.getBizContentClass());
			request.setBizContent(bizContent);

			// 生成自提交的表单返回客户浏览器，该表单会自动提交完成调用
			System.out.println(client.buildPostForm(request));
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}
	}

	/**
	 * TODO 方法的描述：。
	 * 
	 * <pre>
	 * 
	 * </pre>
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		testRequest();
	}

	public static class UiIcbcClientRsa2 extends UiIcbcClient {

		public UiIcbcClientRsa2(String appId, String privateKey, String charset) {
			super(appId, privateKey, charset);
			super.signType = "RSA2";
		}

	}

}
