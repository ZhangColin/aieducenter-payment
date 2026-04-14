package com.icbc.api;

import com.icbc.api.request.JftUiUserEntrustopenacctSubmitRequestV1;
import com.icbc.api.request.JftUiUserEntrustopenacctSubmitRequestV1.JftUiUserEntrustopenacctSubmitRequestV1Biz;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class JftUiUserEntrustopenacctSubmitTestServletV1 extends HttpServlet {
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

            // 通过工行 SDK 调用《聚富通委托代扣签约同步开户页面接口V1》页面接口
            UiIcbcClient client = new UiIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8);
            JftUiUserEntrustopenacctSubmitRequestV1 request = new JftUiUserEntrustopenacctSubmitRequestV1();
            // 指定《聚富通委托代扣签约同步开户页面接口V1》URL
            request.setServiceUrl("https://ip:port/ui/jft/ui/user/entrustopenacct/submit/V1");

            /**
             * 组装调用页面接口上送报文。接口各请求参数说明详见接口文档
             */
            JftUiUserEntrustopenacctSubmitRequestV1Biz bizContent = new JftUiUserEntrustopenacctSubmitRequestV1Biz();
            bizContent.setAppId(APP_ID);
            bizContent.setCorpNo("[需替换]合作方在 CAMS 系统的编号");
            bizContent.setOutUserId("M201801112326");
            bizContent.setCorpSerno("LSHNO00001");
            bizContent.setOutVendorId("SH000001");
            bizContent.setTrxChannel("01");
            bizContent.setProjectId("PJ140014021000004309");
            bizContent.setBusiCode("888810222");
            bizContent.setProtocolEndDate("2018-02-01");
            bizContent.setProtocolLimitAmount("20000000");
            bizContent.setTemplateParams("{\"prtlEndDate\":\"2018-11-22\",\"loanTotalAmt\":\"200000\",\"trxLmtAmt\":\"200000\",\"dayLmtAmt\":\"200000\"}");
            bizContent.setShareTargetUrl("https://www.jft.com/landscape?topic=xxx");
            bizContent.setWxShareParamsFetchUrl("https://www.jft.com/getWxShareParamsFetchUrl?token=xxx");
            bizContent.setCallbackUrl("https://www.jft.com/notify");
            bizContent.setJumpUrl("https://www.jft.com/jumpback?userId=xxx");
            bizContent.setFailJumpUrl("https://www.jft.com/jumpback?userId=xxx");
            bizContent.setAccountOrgNo(null);
            bizContent.setCamsPublicKey("ABCDEFGH1234567");

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
            e.printStackTrace();
        }
    }
}
