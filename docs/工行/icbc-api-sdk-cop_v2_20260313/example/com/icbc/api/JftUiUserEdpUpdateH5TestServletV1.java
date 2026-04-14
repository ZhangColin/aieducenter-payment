package com.icbc.api;

import com.icbc.api.request.JftUiEdpUpdateH5RequestV1;
import com.icbc.api.request.JftUiEdpUpdateH5RequestV1.JftUiEdpUpdateH5RequestV1Biz;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class JftUiUserEdpUpdateH5TestServletV1 extends HttpServlet {
    private static final long serialVersionUID = 1L;

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
            // FIXME：此处替换合作方 APPID 对应私钥
            final String URL = "[需替换]生产请求域名";

            // 通过工行 SDK 调用《聚富通网络货运收方入驻页面接口V1》页面接口
            UiIcbcClient client = new UiIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8);
            JftUiEdpUpdateH5RequestV1 request = new JftUiEdpUpdateH5RequestV1();
            // 指定《聚富通网络货运收方入驻页面服务V1》URL
            request.setServiceUrl(URL+"/ui/jft/ui/user/edpreceive/updateH5/V1");

            /**
             * 组装调用页面接口上送报文。接口各请求参数说明详见接口文档
             */
            JftUiEdpUpdateH5RequestV1Biz bizContent = new JftUiEdpUpdateH5RequestV1Biz();
            bizContent.setAppId(APP_ID);
            bizContent.setAppIdSub("SH000001");
            bizContent.setOutUserId("M201801112326");
            bizContent.setBusinessType("0001");
            bizContent.setCallbackUrl("");
            bizContent.setJumpUrl("");
            bizContent.setFailJumpUrl("");
            request.setBizContent(bizContent);


            // 调用工行 SDK，生成自动提交表单，将用户跳转到收方入驻页面
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
//            log.error("");
        }
    }
}
