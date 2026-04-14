package com.icbc.api;

import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcConstants;
import com.icbc.api.UiIcbcClient;
import com.icbc.api.request.JftUiUserInfoAccessAuthSubmitRequestV1;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class JftUiUserInfoAccessAuthTestServletV1 extends HttpServlet {

    // 此处替换工行API网关域名，请向工行联系获取
    private static final String APIGW_URL = "[需替换]此处替换工行API网关域名，请向工行联系获取";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // 此处替换合作方 APPID
            final String APP_ID = "[需替换]APP的编号";
            // 此处替换合作方 APPID 对应私钥
            final String MY_PRIVATE_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";

            // 通过工行 SDK 调用《融e聚信息接收授权签约页面接口V1》页面接口
            UiIcbcClient client = new UiIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8);
            JftUiUserInfoAccessAuthSubmitRequestV1 request = new JftUiUserInfoAccessAuthSubmitRequestV1();
            // 指定《融e聚信息接收授权签约页面接口V1》URL
            request.setServiceUrl(APIGW_URL+"/ui/jft/ui/user/infoAccessAuth/submit/V1");
            /**
             * 组装调用页面接口上送报文。接口各请求参数说明详见接口文档
             */
            JftUiUserInfoAccessAuthSubmitRequestV1.JftUiUserInfoAccessAuthSubmitRequestV1Biz bizContent = new JftUiUserInfoAccessAuthSubmitRequestV1.JftUiUserInfoAccessAuthSubmitRequestV1Biz();
            bizContent.setAppId(APP_ID);
            bizContent.setJumpUrl("");
            bizContent.setOutUserId("123456789");
            JftUiUserInfoAccessAuthSubmitRequestV1.JftUiUserInfoAccessAuthSubmitRequestV1Biz.PreInfoAccessAuthUserInfoDTO authUserInfoDTO =
                    new JftUiUserInfoAccessAuthSubmitRequestV1.JftUiUserInfoAccessAuthSubmitRequestV1Biz.PreInfoAccessAuthUserInfoDTO();
            authUserInfoDTO.setCustName("张三");
            authUserInfoDTO.setCertType("00");
            authUserInfoDTO.setCertNo("123456789012345678");
            bizContent.setPreFillItems(authUserInfoDTO);
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
//            e.printStackTrace();
        }
    }
}
