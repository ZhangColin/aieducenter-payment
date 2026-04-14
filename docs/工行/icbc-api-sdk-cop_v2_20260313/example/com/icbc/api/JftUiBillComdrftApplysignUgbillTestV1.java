package com.icbc.api;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class JftUiBillComdrftApplysignUgbillTestV1 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doPost(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String MY_PRIVATE_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";
        String APP_ID = "10000000000000005452";
        String host = "[需替换]工行API网关地址";
        String apiUrl = host + "/ui/jft/ui/bill/ugbill/comdrftapply/sign/V1";
        try {
            UiIcbcClient client = new UiIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY,
                    IcbcConstants.CHARSET_UTF8);
            //请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值

            JftUiBillComdrftApplysignUgbillUIRequestV1 request = new JftUiBillComdrftApplysignUgbillUIRequestV1();
            JftUiBillComdrftApplysignUgbillUIRequestV1.JftUiBillComdrftApplysignUgbillUIRequestBiz bizContent = new JftUiBillComdrftApplysignUgbillUIRequestV1.JftUiBillComdrftApplysignUgbillUIRequestBiz();

            request.setServiceUrl(apiUrl);
            bizContent.setAppId(APP_ID);

            bizContent.setOutVendorId("F202301121017");
            bizContent.setSignOper("SU00");

            List<JftUiBillComdrftApplysignUgbillUIRequestV1.SignInfo> signInfoList = new ArrayList<>();
            JftUiBillComdrftApplysignUgbillUIRequestV1.SignInfo signInfo = new JftUiBillComdrftApplysignUgbillUIRequestV1.signInfo();
            signInfo.setPackNo("000000000123211123877770012100");
            signInfo.setRangeBgn("000000000001");
            signInfo.setRangeEnd("000000123436");
            signInfo.setSignAcctId("1231313131313132131");
            signInfo.setSignType("NES_AC_02");
            signInfo.setBillType("1");
            signInfo.setSgnUpCode("CP02");
            signInfo.setOtherInf("拒付原因是XXXX");
            signInfo.setAppAcctId("123123");
            signInfo.setAppDisttp("DT01");
            signInfo.setAppBranch("231313131");
            signInfo.setBusiSeq("12312312312321312312312312312312");

            signInfoList.add(signInfo);
            bizContent.setSignList(signInfoList);
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
            e.printStackTrace();
        }
    }

}
