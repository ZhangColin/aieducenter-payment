package com.icbc.api;

import com.icbc.api.request.JftUiUserOpenacctH5MywalletRequestV1;
import com.icbc.api.request.JftUiUserOpenacctH5MywalletRequestV1.JftUiUserOpenacctH5MywalletRequestV1Biz;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 聚富通电子钱包账户功能页面接口V1
 */
public class JftUiUserOpenacctH5MywalletTestServletV1 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // FIXME: 此处替换合作方 APPID
            final String APP_ID = "[需替换]APP的编号";
            // FIXME：此处替换合作方 APPID 对应私钥
            final String MY_PRIVATE_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";

            // 通过工行 SDK 调用《聚富通电子钱包账户功能页面接口V1》页面接口
            UiIcbcClient client = new UiIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8);
            JftUiUserOpenacctH5MywalletRequestV1 request = new JftUiUserOpenacctH5MywalletRequestV1();
            // 指定《聚富通电子钱包账户功能页面接口V1》URL
            request.setServiceUrl("https://ip:port/ui/jft/ui/user/openacctH5/mywallet/V1");

            /**
             * 组装调用页面接口上送报文。接口各请求参数说明详见接口文档
             */
            JftUiUserOpenacctH5MywalletRequestV1Biz bizContent = new JftUiUserOpenacctH5MywalletRequestV1Biz();
            bizContent.setAppId(APP_ID);
            bizContent.setCorpNo("[需替换]合作方在 CAMS 系统的编号");
            bizContent.setOutUserId("123456789");
            bizContent.setCamsPublicKey("ABCDEFGH1234567");
            bizContent.setMediumId("6214760200000012345");
            bizContent.setBindMedium("6222024300000088888");
            bizContent.setShowProtocolModel("1");
            request.setBizContent(bizContent);


            // 调用工行 SDK，生成自动提交表单，将用户跳转到【聚富通电子钱包账户功能页面】
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
