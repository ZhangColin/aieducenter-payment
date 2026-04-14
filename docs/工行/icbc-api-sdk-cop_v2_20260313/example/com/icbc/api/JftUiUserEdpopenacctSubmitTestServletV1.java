package com.icbc.api;

import com.icbc.api.request.JftUiUserEdpopenacctSubmitRequestV1;
import com.icbc.api.request.JftUiUserEdpopenacctSubmitRequestV1.JftUiUserEdpopenacctSubmitRequestV1Biz;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class JftUiUserEdpopenacctSubmitTestServletV1 extends HttpServlet {
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

            // 通过工行 SDK 调用《聚富通网络货运收方入驻页面接口V1》页面接口
            UiIcbcClient client = new UiIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8);
            JftUiUserEdpopenacctSubmitRequestV1 request = new JftUiUserEdpopenacctSubmitRequestV1();
            // 指定《聚富通网络货运收方入驻页面服务V1》URL
            request.setServiceUrl("https://ip:port/ui/jft/ui/user/edpopenacct/submit/V1");

            /**
             * 组装调用页面接口上送报文。接口各请求参数说明详见接口文档
             */
            JftUiUserEdpopenacctSubmitRequestV1Biz bizContent = new JftUiUserEdpopenacctSubmitRequestV1Biz();
            bizContent.setAppId(APP_ID);
            bizContent.setCorpNo("[需替换]合作方在 CAMS 系统的编号");
            bizContent.setAppIdSub("SH000001");
            bizContent.setOutUserId("M201801112326");
            bizContent.setCorpSerno("LSHNO00001");
            bizContent.setCallbackUrl("http://www.jft.com/notify");
            bizContent.setJumpUrl("http://www.jft.com/jumpback?userId=xxx");
            bizContent.setFailJumpUrl("http://www.jft.com/jumpback?userId=xxx");
            bizContent.setAccountOrgNo(null);
            bizContent.setCamsPublicKey("ABCDEFGH1234567");
            bizContent.setSkipImgUpload("1");
            bizContent.setOpenWallet("1");
            Map<String, Object> preFillItems = new HashMap<>();
            preFillItems.put("custName", "张三");
            preFillItems.put("certType", "0");
            preFillItems.put("certNo", "123456789012345678");
            preFillItems.put("validityPeriod", "9999-12-30");
            preFillItems.put("signDate", "2023-08-10");
            preFillItems.put("address", "XXX省XXX市XXX路");
            preFillItems.put("bindMedium", "622202xx");
            preFillItems.put("mobile", "1321231234");
            preFillItems.put("occupation", "9");
            preFillItems.put("receiverRole", "01");
            bizContent.setPreFillItems(preFillItems);
            bizContent.setAllowModifyPreFillItems("0");
            bizContent.setExistingWalletUser("0");
            bizContent.setWalletMediumId(null);
            bizContent.setValidityPeriod(null);
            bizContent.setSignDate(null);

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
