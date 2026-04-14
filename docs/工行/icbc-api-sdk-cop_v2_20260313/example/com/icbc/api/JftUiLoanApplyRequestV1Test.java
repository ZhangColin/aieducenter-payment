package com.icbc.api;

import com.icbc.api.request.JftUiLoanApplyRequestV1;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class JftUiLoanApplyRequestV1Test extends HttpServlet {
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
        String API_URL = HOST + "/ui/jft/ui/loan/apply/V1";

        try {
            UiIcbcClient client = new UiIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY,IcbcConstants.CHARSET_UTF8);
            //请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
            JftUiLoanApplyRequestV1.JftUiLoanApplyRequestV1Biz bizContent = new JftUiLoanApplyRequestV1.JftUiLoanApplyRequestV1Biz();
            bizContent.setAppId("123");
            bizContent.setOutVendorId("111");
            bizContent.setOutUserId("1");
            bizContent.setExternalSno("20230203");
            bizContent.setApplyAmt("1");
            bizContent.setIsGivingFlag("1");
            JftUiLoanApplyRequestV1 request = new JftUiLoanApplyRequestV1();
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
