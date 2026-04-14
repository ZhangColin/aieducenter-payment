package com.icbc.api;

import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcConstants;
import com.icbc.api.UiIcbcClient;
import com.icbc.api.request.JftUiUserEntrustH5RequestV1;
import com.icbc.api.request.JftUiUserEntrustH5RequestV1.JftUiUserEntrustH5RequestV1Biz;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

class JftUiUserGdEntrustTestV1 extends HttpServlet {

    protected static final String APIGW_PUBLIC_KEY = "[需替换]工行API网关公钥，请向工行联系获取";
    // 2、appid
    protected static final String APP_ID = "[需替换]APP的编号,应用在API开放平台注册时生成";
    // 3、密钥对认证方式，公钥在API平台登记，此处为私钥
    protected static final String MY_PRIVATE_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";
    // 4、工行api网关地址
    public static final String host = "[需替换]工行API网关地址";
    // 5、接口地址
    public static final String apiUrl= host+"+/ui/jft/ui/user/entrust/h5/V1";
    // 6、应用私钥
    public static final String encryptKey = "[需替换]应用方加密串，与工行API开放平台一致";
    
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
        try {
            UiIcbcClient client = new UiIcbcClient(APP_ID, "RSA2",MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8);
            JftUiUserEntrustH5RequestV1 request = new JftUiUserEntrustH5RequestV1();
            request.setServiceUrl(apiUrl);
            JftUiUserEntrustH5RequestV1.JftUiUserEntrustH5RequestV1Biz bizContent = new JftUiUserEntrustH5RequestV1.JftUiUserEntrustH5RequestV1Biz();
            bizContent.setAppId(APP_ID);
            bizContent.setTrxTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
            bizContent.setCallBackUrl("http://this.is.a.test.url/callback");
            bizContent.setMobile("131****1231");
            bizContent.setOutUserId("110223****234343");
            request.setBizContent(bizContent);
            resp.setHeader("Content-Type", "text/html;charset="
                    + IcbcConstants.CHARSET_UTF8);
            PrintWriter out = resp.getWriter();
            System.out.println(client.buildPostForm(request));
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
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


}
