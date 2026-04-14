package com.icbc.api;

import com.icbc.api.request.JftUiVendorOpenAccountRequestV1;
import com.icbc.api.request.JftUiVendorOpenAccountRequestV1.JftUiVendorOpenAccountRequestV1Biz;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
  * @Author dccb-wblit
  * @Description 往来户预约开户UI接口V1-测试类
  * @Date 2021/1/18 16:36
  * @Version 1.0.0
  */
public class JftUiVendorOpenAccountTestV1 extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		try {
			String APP_ID = "10000000000000004473";
			String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----CdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJ42tqQomxbLvuZTVKO4bBLwGp8SzRMiJ7jX9JNlS4sqmZzs9Z4lw/SSxf4dF9JJUErpNReUjzLp0As2FiuV8BALh6GYmiHGTWx7+v3HuWH9LiZNI00Ia+1HfpkJ8Sao8Ny5h1Pu49LayVleeLy496+phRE81dqlqaSHH8cKWnK1AgMBAAECgYBxod5f3QI2xzNe/e7GgAivOWAFbF16JofdDM4Opyww0fHucYqfgYRSPrCRqJeZYyqWAxUs0HhhGulfhAM8Xr5BxY5//x4HDqE2rOF7mrc/xkWNL+S9X5U0T8cdA3fnF/rYAUnJjKmeliuD2gcf5xYJENcnb8p/GQbiwIjoHDqe6QJBANzcevl9ke4cx1fa3ZcxVS3A1eHVtp573M/lo+8pRJlrf96ghFB54CzumsC0F7YfojDgCAN2egTqCGqNOuPJWWcCQQC3YqT5xlMlGk6Agav5Y6E5blx/AKJB8DeZSbjXLvytpXOASzoNR/A40uQXanXVqNU68l96FSq415AmTHi9OdWDAkA0el7t8Rw/i68B/Qsx5ZLrsCoh4vnlZmDtNQ9iwFeAbL6RU2qdBJhzlK5Io4IO0C1ll5XP3NLZYBJn3u7jOPB5AkEAiM/qHoHsM8j9effD0kmW1V7VWNajNqg9AnoykS73yaCem78DrzbVK7+B9UoyYNUVR2Xc/xpdhgsj+r6gcSN9ewJANSmxYF34601mteAnZwhyGKCJomiuSMNTr52i2uTspdZi0zqaerI1EGD3dAENxRebyPmPFxhwCcCbzF2gNUr1fw==";

			UiIcbcClient client = new UiIcbcClient(APP_ID, MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8);

			JftUiVendorOpenAccountRequestV1 request = new JftUiVendorOpenAccountRequestV1();
			request.setServiceUrl("https://ip:port/ui/jft/ui/vendor/open/account/V1");

			JftUiVendorOpenAccountRequestV1Biz bizContent = new JftUiVendorOpenAccountRequestV1Biz();
			bizContent.setAppId(APP_ID);
			bizContent.setApi_name("PRSIGN");
			bizContent.setApi_version("001.001.002.001");
			bizContent.setFile_code("0200EG0000001");
			bizContent.setFile_name("平台名称");
			bizContent.setFile_serialno("6633074382999");
			bizContent.setTrade_time("20180310162230");
			bizContent.setEnt_name("三丫医疗器械销售责任有限公司");
			bizContent.setEnt_orgcode("429006NA000000022X");
			bizContent.setEnt_type("股份有限公司");
			bizContent.setFr_name("王二丫");
			bizContent.setNeed_addinfo("1");
			request.setBizContent(bizContent);

			resp.setHeader("Content-Type", "text/html;charset="
					+ IcbcConstants.CHARSET_UTF8);
			PrintWriter out = resp.getWriter();

			out.write("<html>");
			out.write("<head>");
			out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset="
					+ IcbcConstants.CHARSET_UTF8 + "\">");
			out.write("</head>");
			out.write("<body>");
			out.write(client.buildPostForm(request));
			out.write("</body>");
			out.write("</html>");

		} catch (IcbcApiException e) {
			e.printStackTrace();
		}
	}
	
}

