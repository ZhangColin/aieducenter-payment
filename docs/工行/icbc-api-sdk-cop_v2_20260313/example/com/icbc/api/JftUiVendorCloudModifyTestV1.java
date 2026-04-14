package com.icbc.api.m4;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icbc.api.IcbcApiException;
import com.icbc.api.UiIcbcClient;

import com.icbc.api.request.JftUiVendorCloudModifyRequestV1;


public class JftUiVendorCloudModifyTestV1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	    throws ServletException, IOException{
	    doPost(req, resp);
	  }

	  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
	    PrintWriter out = null;

	    try {
	      String appId = req.getParameter("appId");
	      String outVendorId = req.getParameter("outVendorId");
	      String outUserId = req.getParameter("outUserId");
	     

	      String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCF7eCVTURFK5+gpeaQios8lw6uTcltPalYM6/VNfNDAirGnpFP5RQNI7duf5nr+3QLe1FNMbaR6Cl1BeWcaFzYqba4UTjNrttGf9E2LBnVa7sR0sVWX/1o71nIou21+Wh044zqcSl1T6FEBpSP70fz5ahytU41hVy6lyJymDhyWzZepr4Yqnrs69GB6V/v2DDuX8t4tpZAK1cxsOSrBJHOYxUoTomLb9dX8dFI/4AIShjKv4hmwryviHJRlZSiyV8j72FgOUs7BmMz+KCaKGVGo1G+YrppnX5IruNvaXDB59bcu3lsjPODUkfMKsMr+Vrg4ipDEboCCtKuaPrZ2xMNAgMBAAECggEAY3qQcXWl+xbvB52Sx9QQnh25yuB+eETvz1L9DQp4uVIXwdYwKz8FiMj5y/q9stnglVKwzfzaFkBy4rhRl76xEEHnNbsmzShPOWthU7KoMa1Gk3OSHplIGjSG5Q+YL62k2AXZOp55Y+iU6XlVyh+6uz/fwexHvltjyDjQXnwQmWdvUHM+cy7yTxlfz8FGecZOyRY2jRUJZ7w0WfUx0p1R0vWRRpjRddMDyu7sjtkTQ54KfmqE5HQWKrQVDio2hSw7EbaylAo6HSZO8i2ZSW3neKenvV2YCUVk6AbhfJj/SfTzqzEePduOv5bUq/vaTRCWAImcHnohLhzQQS5zn3jDpQKBgQDTncyUAAOL275viu9Xbk2IBsv/tyFaNji/G8BdE0jB++7x7J3EtLRJIBOJE16vvWUJfD7CXvezqArZWLADt7lholdPuUKlEOJtXvqlYvg7zKDce+wA7HAK0a2sMSvU51nC0q4LLaI+NlGxZZcPYcoj6PYLXw/BHaqGAHm5Y+RMhwKBgQCiBNz3zYP4RnfBTazWBRm3P7jybcteFMh5inmOyw9mfpphIUpFlw9lRbt7J2+sauzlIKZ2d9MOoZIurAZ/zbgIf2msTEoyczzO6yUpyeKinl6HjizMKggLEponU7nk2nQsuVbf7wYoHPwwjT+a5k9R+PMrYVO0B74Z8PIQpHd8ywKBgQCD//qMlzWfGANCMLf7IaLbJuI5MFJto0TicL0dUdogprX10lLXUKDkvE1zQ9HcbZeIwyeitvpr5nZd/SROLVxFuq4b3MPlkxKqhoRyhbmyE4KEBgjAyrtZBFIBBusAcw9ap3BJKL37cIak0WnY/nbIz5gqn7GEFK05yTIkolrWIQKBgH2U42m4BKi2hjBw8pzZSzt8yvAuJkoANfvvV5VU09UQIL1Uvmr+UdKfoFgvEyJDpudazWxaKF/Y4KQIduktHY6Io/IHGCPOqr2ACkg/0clWk9LiYtrFYYKjopUFErvOj+nypuHgNZd2EAvaEAkSpzzUiDeBsw2CStl0p0w7DtrNAoGAIsqskDGGewcgBWQmXvSqa7o0do3coiH3ToMAQm9vlGgHAAw5xlgfVQpGwe8GR4ilysh+5EuiuLnINDt0tXpv9K7dAQgjKAmszFrOjmyBMnSDebfysynABFj3c8GglO+l9J+ljFMqgqPVzsLAgIItciyq80Xmv8TjpbwFM2v34lI=";
	      String SIGN_TYPE = "RSA2";
		  String AES_Key = "[需替换]应用方加密串，与工行API开放平台一致";
		  UiIcbcClient client = new UiIcbcClient(APP_ID, SIGN_TYPE, MY_PRIVATE_KEY,
					IcbcConstants.CHARSET_UTF8, IcbcConstants.ENCRYPT_TYPE_AES, AES_Key);

	      JftUiVendorCloudModifyRequestV1 request = new JftUiVendorCloudModifyRequestV1();

	      request.setServiceUrl("https://ip:port/ui/jft/ui/vendor/cloud/modify/V1");


	      JftUiVendorCloudModifyRequestV1.JftUiVendorCloudModifyRequestV1Biz bizContent = new JftUiVendorCloudModifyRequestV1.JftUiVendorCloudModifyRequestV1Biz();
		  //必输
	      bizContent.setAppId(appId);
	      bizContent.setOutUserId(outUserId);
	      bizContent.setOutVendorId(outVendorId);
	      request.setBizContent(bizContent);

	      resp.setHeader("Content-Type", "text/html;charset=UTF-8");
	      out = resp.getWriter();

	      out.write("<html>");
	      out.write("<head>");
	      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">");

	      out.write("</head>");
	      out.write("<body>");
	      out.write(client.buildPostForm(request));
	      out.write("</body>");
	      out.write("</html>");
	    }
	    catch (IcbcApiException e) {
	      e.printStackTrace();
	      System.out.println(e.getMessage());
	    } finally {
	      if (out != null) {
	        out.flush();
	        out.close();
	      }
	    }
	  }
}
