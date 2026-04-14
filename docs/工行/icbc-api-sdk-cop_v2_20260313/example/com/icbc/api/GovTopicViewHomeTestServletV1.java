package com.icbc.api;

import com.icbc.api.request.GovUiTopicViewHomeRequestV1;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class GovTopicViewHomeTestServletV1 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            // FIXME: 此处替换合作方 APPID
            final String APP_ID = "[需替换]APP的编号";
            // FIXME：此处替换合作方 APPID 对应私钥
            final String MY_PRIVATE_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";

            // 通过工行 SDK 调用《聚富通金融专区首页接口V1》页面接口
            UiIcbcClient client = new UiIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8);
            GovUiTopicViewHomeRequestV1 request = new GovUiTopicViewHomeRequestV1();
            // 指定《聚富通金融专区首页接口V1》URL
            request.setServiceUrl("https://ip:port/ui/gov/ui/topic/view/home/V1");

            /**
             * 组装调用页面接口上送报文。接口各请求参数说明详见接口文档
             */
            GovUiTopicViewHomeRequestV1.GovUiTopicViewHomeRequestV1Biz bizContent = new GovUiTopicViewHomeRequestV1.GovUiTopicViewHomeRequestV1Biz();
            bizContent.setAppId(APP_ID);
            bizContent.setOutUserId("outUserId123");
            bizContent.setTrxChannel("05");

            /**
             * 合作方使用金融专区【新版经营快贷申请】模块时本字段必填
             */
            Map<String, Object> userInfo = new HashMap<>();
            userInfo.put("custName", "张三");
            userInfo.put("certNo", "123456789012345678");
            userInfo.put("mobile", "13212312341");
            bizContent.setUserInfo(userInfo);

            /**
             * 合作方使用金融专区【贷款申请】模块时本字段必填
             */
            Map<String, Object> loanApplyInfo = new HashMap<>();
            loanApplyInfo.put("custName", "张三");
            loanApplyInfo.put("certNo", "123456789012345678");
            bizContent.setLoanApplyInfo(loanApplyInfo);

            /**
             * 合作方使用金融专区【开通钱包】模块时本字段必填
             *
             * 请求参数结构及各字段校验规则详见API接口《聚富通电子钱包账户开立通用页面接口V1》接口文档说明
             */
            Map<String, Object> openacctH5Info = new HashMap<>();
            bizContent.setOpenacctH5Info(openacctH5Info);
            openacctH5Info.put("corpNo", "[需替换]合作方在 CAMS 系统的编号");
            openacctH5Info.put("callbackUrl", "https://www.jft.com/notify");
            openacctH5Info.put("jumpUrl", "https://www.jft.com/jumpback?userId=xxx");
            openacctH5Info.put("failJumpUrl", "https://www.jft.com/jumpback?userId=xxx");
            openacctH5Info.put("camsPublicKey", "ABCDEFGH1234567");
            openacctH5Info.put("openProtocol", "1");
            openacctH5Info.put("copyMediumId", "1");
            Map<String, Object> preFillItems = new HashMap<>();
            preFillItems.put("custName", "张三");
            preFillItems.put("certType", "0");
            preFillItems.put("certNo", "123456789012345678");
            preFillItems.put("validityPeriod", "9999-12-30");
            preFillItems.put("signDate", "2023-08-10");
            preFillItems.put("address", "XXX省XXX市XXX路");
            preFillItems.put("bindMedium", "622202xx");
            preFillItems.put("mobile", "13212312341");
            preFillItems.put("occupation", "9");
            openacctH5Info.put("preFillItems", preFillItems);
            bizContent.setOpenacctH5Info(openacctH5Info);

            request.setBizContent(bizContent);


            // 调用工行 SDK，生成自动提交表单，将用户跳转到金融专区首页
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
