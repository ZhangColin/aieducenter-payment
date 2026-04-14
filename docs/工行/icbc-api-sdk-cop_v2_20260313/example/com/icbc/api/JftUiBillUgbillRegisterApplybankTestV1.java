package com.icbc.api;

import com.icbc.api.request.JftUiBillUgbillRegisterApplybankRequestV1;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class JftUiBillUgbillRegisterApplybankTestV1 extends HttpServlet {
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
        String API_URL = HOST + "/ui/jft/ui/bill/ugbill/register/applybank/V1";
        try {
            UiIcbcClient client = new UiIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY,
                    IcbcConstants.CHARSET_UTF8);
            JftUiBillUgbillRegisterApplybankRequestV1.JftUiBillUgbillRegisterApplybankRequestV1Biz bizContent =
                    new JftUiBillUgbillRegisterApplybankRequestV1.JftUiBillUgbillRegisterApplybankRequestV1Biz();
            bizContent.setAppId("1111");
            bizContent.setOutVendorId("111");
            bizContent.setBusiSeq("111");
            bizContent.setNotifyUrl("xxxx");
            //请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
            JftUiBillUgbillRegisterApplybankRequestV1 request = new JftUiBillUgbillRegisterApplybankRequestV1();
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
