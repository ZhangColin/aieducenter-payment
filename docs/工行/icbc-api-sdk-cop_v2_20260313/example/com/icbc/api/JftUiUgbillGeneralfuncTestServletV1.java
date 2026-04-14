package com.icbc.api;


import com.icbc.api.request.JftUiUgbillGeneralfuncRequestV1;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class JftUiUgbillGeneralfuncTestServletV1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String MY_PRIVATE_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";
        String APP_ID = "[需替换]APPID";
        String HOST = "[需替换]工行API网关地址";
        String API_URL = HOST + "/ui/jft/ui/ugbill/generalfunc/V1";

        try {
            UiIcbcClient client = new UiIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY,
                    IcbcConstants.CHARSET_UTF8);

            JftUiUgbillGeneralfuncRequestV1.JftUiUgbillGeneralfuncRequestV1Biz bizContent =
                    new JftUiUgbillGeneralfuncRequestV1.JftUiUgbillGeneralfuncRequestV1Biz();
            bizContent.setAppId(APP_ID);
            bizContent.setOutVendorId("201802021008999991");
            bizContent.setUkey("3421431");

            JftUiUgbillGeneralfuncRequestV1 request = new JftUiUgbillGeneralfuncRequestV1();
            request.setServiceUrl(API_URL);
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
        }
    }
}
