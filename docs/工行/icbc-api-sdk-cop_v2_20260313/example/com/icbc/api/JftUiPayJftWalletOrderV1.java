package com.icbc.api;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icbc.api.request.JftWalletApiVaorderfinRequestUiV1;
import com.icbc.api.request.JftWalletApiVaorderfinRequestUiV1.JftWalletApiVaorderfinRequestUiV1Biz;

public class JftUiPayJftWalletOrderV1 extends HttpServlet{

	String  APIGW_PUBLIC_KEY = "-----请使用正确的APP私钥-----BIjANBgkqhkiG9w0BAQEFAAOCAQ8A-----请使用正确的APP私钥-----BCgKCAQEAq3tp1oq/vEFlmeJ6T+tHq7iF1MCH7IRjrTA7vUqEl+HMgBeqMcDnxa7jPg4nKy+KnGQXX91IlZdQdKQWWXlEvHLtaaeIs2V9nGuQCZnnZuG6B1dEpX36GZa8xy66jmD5iXn0DU/QdyXM3oQu7cnw09qggKFaU9bWVRLV1NEbZu5Hl5mCS09rc5y5jMRw4sHVaht1k/WK5olK1ZMR4nexh+efeKm7nYuTdJRWCU+D0uhpdMBlpOKJrxZE9i2xieL0IeXdDOXyThl5hvS9dESHqIAS11swW5VBZ61iJ3ECXJ8Gfs+lQ34mciJcQmJZALgVQQT52q0d60CjbyUKMvQHcQIDAQAB";
	String  MY_PRIVATE_KEY2 = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCF7eCVTURFK5+gpeaQios8lw6uTcltPalYM6/VNfNDAirGnpFP5RQNI7duf5nr+3QLe1FNMbaR6Cl1BeWcaFzYqba4UTjNrttGf9E2LBnVa7sR0sVWX/1o71nIou21+Wh044zqcSl1T6FEBpSP70fz5ahytU41hVy6lyJymDhyWzZepr4Yqnrs69GB6V/v2DDuX8t4tpZAK1cxsOSrBJHOYxUoTomLb9dX8dFI/4AIShjKv4hmwryviHJRlZSiyV8j72FgOUs7BmMz+KCaKGVGo1G+YrppnX5IruNvaXDB59bcu3lsjPODUkfMKsMr+Vrg4ipDEboCCtKuaPrZ2xMNAgMBAAECggEAY3qQcXWl+xbvB52Sx9QQnh25yuB+eETvz1L9DQp4uVIXwdYwKz8FiMj5y/q9stnglVKwzfzaFkBy4rhRl76xEEHnNbsmzShPOWthU7KoMa1Gk3OSHplIGjSG5Q+YL62k2AXZOp55Y+iU6XlVyh+6uz/fwexHvltjyDjQXnwQmWdvUHM+cy7yTxlfz8FGecZOyRY2jRUJZ7w0WfUx0p1R0vWRRpjRddMDyu7sjtkTQ54KfmqE5HQWKrQVDio2hSw7EbaylAo6HSZO8i2ZSW3neKenvV2YCUVk6AbhfJj/SfTzqzEePduOv5bUq/vaTRCWAImcHnohLhzQQS5zn3jDpQKBgQDTncyUAAOL275viu9Xbk2IBsv/tyFaNji/G8BdE0jB++7x7J3EtLRJIBOJE16vvWUJfD7CXvezqArZWLADt7lholdPuUKlEOJtXvqlYvg7zKDce+wA7HAK0a2sMSvU51nC0q4LLaI+NlGxZZcPYcoj6PYLXw/BHaqGAHm5Y+RMhwKBgQCiBNz3zYP4RnfBTazWBRm3P7jybcteFMh5inmOyw9mfpphIUpFlw9lRbt7J2+sauzlIKZ2d9MOoZIurAZ/zbgIf2msTEoyczzO6yUpyeKinl6HjizMKggLEponU7nk2nQsuVbf7wYoHPwwjT+a5k9R+PMrYVO0B74Z8PIQpHd8ywKBgQCD//qMlzWfGANCMLf7IaLbJuI5MFJto0TicL0dUdogprX10lLXUKDkvE1zQ9HcbZeIwyeitvpr5nZd/SROLVxFuq4b3MPlkxKqhoRyhbmyE4KEBgjAyrtZBFIBBusAcw9ap3BJKL37cIak0WnY/nbIz5gqn7GEFK05yTIkolrWIQKBgH2U42m4BKi2hjBw8pzZSzt8yvAuJkoANfvvV5VU09UQIL1Uvmr+UdKfoFgvEyJDpudazWxaKF/Y4KQIduktHY6Io/IHGCPOqr2ACkg/0clWk9LiYtrFYYKjopUFErvOj+nypuHgNZd2EAvaEAkSpzzUiDeBsw2CStl0p0w7DtrNAoGAIsqskDGGewcgBWQmXvSqa7o0do3coiH3ToMAQm9vlGgHAAw5xlgfVQpGwe8GR4ilysh+5EuiuLnINDt0tXpv9K7dAQgjKAmszFrOjmyBMnSDebfysynABFj3c8GglO+l9J+ljFMqgqPVzsLAgIItciyq80Xmv8TjpbwFM2v34lI=";

	protected final String APP_ID = "10000000000001754554";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		UiIcbcClient client = new UiIcbcClient(APP_ID,MY_PRIVATE_KEY2,IcbcConstants.CHARSET_UTF8);
		JftWalletApiVaorderfinRequestUiV1 request = new JftWalletApiVaorderfinRequestUiV1();
		request.setServiceUrl("http://ip:port/ui/jft/ui/pay/jft/wallet/order/V1");//
		JftWalletApiVaorderfinRequestUiV1Biz jftWalletApiVaorderfinprcRequestV1Biz = new JftWalletApiVaorderfinRequestUiV1Biz();
		// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		jftWalletApiVaorderfinprcRequestV1Biz.setOrderId("201986009998878");//鐠併垹宕焛d
		jftWalletApiVaorderfinprcRequestV1Biz.setOrderName("20182019032809");//鐠併垹宕熼崥宥囆�
		jftWalletApiVaorderfinprcRequestV1Biz.setTrxAccDate("2019-03-02");
		jftWalletApiVaorderfinprcRequestV1Biz.setTrxAccTime("10:00:00");
		jftWalletApiVaorderfinprcRequestV1Biz.setCorpDate("2019-03-02");
		jftWalletApiVaorderfinprcRequestV1Biz.setCorpSerno("1225525452222225");//濞翠焦鎸夐崣锟�
		jftWalletApiVaorderfinprcRequestV1Biz.setTrxChannel("03");//娴溿倖妲楀〒鐘讳壕02-H5   03-PC
		jftWalletApiVaorderfinprcRequestV1Biz.setTranAmt("1.00");//娴溿倖妲楅柌鎴︻杺
		jftWalletApiVaorderfinprcRequestV1Biz.setOrderNote("鐠併垹宕熼梽鍕枅");//鐠併垹宕熼梽鍕枅
		jftWalletApiVaorderfinprcRequestV1Biz.setAppId("10000000000000010851");
		jftWalletApiVaorderfinprcRequestV1Biz.setBaseAcctNo("1201905010003871820");//娴兼艾鎲抽崡鈥冲娇
		jftWalletApiVaorderfinprcRequestV1Biz.setOutUserId("user03271534");//婢舵牠鍎撮悽銊﹀煕id
		jftWalletApiVaorderfinprcRequestV1Biz.setTrxIp("ip:port");//閻€劍鍩沬p
		jftWalletApiVaorderfinprcRequestV1Biz.setNotifyUrl("http://shanghu");//閺�顖欑帛閹存劕濮�  闁氨鐓rl
		jftWalletApiVaorderfinprcRequestV1Biz.setJumpUrl("http://jumpurl");//閺�顖欑帛閹存劕濮� 鐠哄疇娴唘rl
		jftWalletApiVaorderfinprcRequestV1Biz.setVersion("1.0.2");//鐗堟湰鍙�

		// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		request.setBizContent(jftWalletApiVaorderfinprcRequestV1Biz);

		try {
			resp.setHeader("Content-Type", "text/html;charset="
					+ IcbcConstants.CHARSET_UTF8);
			PrintWriter out = resp.getWriter();
			System.out.println(client.buildPostForm(request));
			out.write("<html>");
			out.write("<head>");
			out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset="
			+ IcbcConstants.CHARSET_UTF8 + "\">");
			out.write("</head>");
			out.write("<body>");
			out.write(client.buildPostForm(request));
			out.write("</body>");
			out.write("</html>");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



}
