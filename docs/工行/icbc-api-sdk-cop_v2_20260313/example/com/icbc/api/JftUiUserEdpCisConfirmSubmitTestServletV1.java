package com.icbc.api;

import com.icbc.api.request.JftUiUserEdpCisConfirmSubmitRequestV1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 融e聚网络货运收方新增客编授权页面接口V1
 */
public class JftUiUserEdpCisConfirmSubmitTestServletV1 extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(JftUiUserEdpCisConfirmSubmitTestServletV1.class);


    private static final String APP_ID = "[需替换]此处替换合作方 APPID";

    private static final String MY_PRIVATE_KEY = "[需替换]此处替换合作方 APPID 对应私钥, 由应用方生成公私钥对，并将公钥上传至工行API开放平台";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // 通过工行 SDK 调用《融e聚网络货运收方新增客编授权页面接口V1》页面接口
            UiIcbcClient client = new UiIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8);
            JftUiUserEdpCisConfirmSubmitRequestV1 request = new JftUiUserEdpCisConfirmSubmitRequestV1();
            // 指定《融e聚网络货运收方新增客编授权页面接口V1》URL
            request.setServiceUrl("[需替换]工行API网关地址/ui/jft/ui/user/edpopenacct/submit/V1");

            /**
             * 组装调用页面接口上送报文。接口各请求参数说明详见接口文档
             */
            JftUiUserEdpCisConfirmSubmitRequestV1.JftUiUserEdpCisConfirmSubmitRequestV1Biz bizContent = new JftUiUserEdpCisConfirmSubmitRequestV1.JftUiUserEdpCisConfirmSubmitRequestV1Biz();
            bizContent.setAppId(APP_ID);
            bizContent.setAppIdSub("SH000001");
            bizContent.setOutUserId("M201801112326");
            bizContent.setCorpSerno("LSHNO00001");
            bizContent.setCallbackUrl("[需替换]合作方地址/notify");
            bizContent.setJumpUrl("[需替换]合作方地址/jumpback?userId=xxx");
            bizContent.setFailJumpUrl("[需替换]合作方地址/jumpback?userId=xxx");
            bizContent.setBusinessType("0001");
            bizContent.setTrxChannel("03");
            bizContent.setCertPreId("MTEwMDAwMDAwMDAwMDAwMjg2MDMxNzM4Mzg5NDE0NzMyMDAwMDAwMDE1NTQ=");
            bizContent.setEntranceType("02");
            request.setBizContent(bizContent);

            // 调用工行 SDK，生成自动提交表单，将用户跳转到授权页面
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
            // 异常
            LOGGER.error("IcbcApiException e=", e);
        }
    }

}
