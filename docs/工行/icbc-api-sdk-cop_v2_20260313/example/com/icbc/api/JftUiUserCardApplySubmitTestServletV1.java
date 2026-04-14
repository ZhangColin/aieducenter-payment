package com.icbc.api;

import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcConstants;
import com.icbc.api.UiIcbcClient;
import com.icbc.api.request.JftUiUserCardApplySubmitRequestV1;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class JftUiUserCardApplySubmitTestServletV1 extends HttpServlet {
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

            // 通过工行 SDK 调用《聚富通线上申卡页面接口V1》页面接口
            UiIcbcClient client = new UiIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8);
            JftUiUserCardApplySubmitRequestV1 request = new JftUiUserCardApplySubmitRequestV1();
            // 指定《聚富通线上申卡页面接口V1》URL
            request.setServiceUrl("https://ip:port/ui/jft/ui/user/cardApply/submit/V1");

            /**
             * 组装调用页面接口上送报文。接口各请求参数说明详见接口文档
             */
            JftUiUserCardApplySubmitRequestV1.JftUiUserCardApplySubmitRequestV1Biz bizContent = new JftUiUserCardApplySubmitRequestV1.JftUiUserCardApplySubmitRequestV1Biz();
            bizContent.setAppId(APP_ID);
            bizContent.setOutUserId("M201801112326");
            bizContent.setCustName("张三");
            bizContent.setCertNo("110xxxxxxxxxxxxxxxx");
            bizContent.setMobile("10000000000");
            bizContent.setCardApplyType("2");
            Map<String, Object> JftCreditCardApply = new HashMap<>();
            JftCreditCardApply.put("sixMonthTurnover", "10000");
            JftCreditCardApply.put("commissionRate", "15");
            JftCreditCardApply.put("idCard", "xxx");
            JftCreditCardApply.put("idCardBack", "xxx");
            JftCreditCardApply.put("driverLicense1", "xxx");
            JftCreditCardApply.put("driverLicense2", "xxx");
            JftCreditCardApply.put("otherLicense1", "xxx");
            JftCreditCardApply.put("otherLicense2", "xxx");
            JftCreditCardApply.put("jumpUrl", "xxx");
            JftCreditCardApply.put("APPLY_BAK_1", "xxx");
            // ...
            bizContent.setJftCreditCardApplyDTO(JftCreditCardApply);
            request.setBizContent(bizContent);


            // 调用工行 SDK，生成自动提交表单，将用户跳转到聚富通线上申卡页面
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
