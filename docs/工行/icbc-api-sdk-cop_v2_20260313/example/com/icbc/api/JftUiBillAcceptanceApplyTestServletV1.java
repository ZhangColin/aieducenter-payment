package com.icbc.api;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class JftUiBillAcceptanceApplyTestServletV1 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doPost(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String MY_PRIVATE_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";
        String APP_ID = "10000000000000328022";
        String host = "[需替换]工行API网关地址";
        String apiUrl = host + "/ui/jft/ui/bill/acceptance/apply/ugbill/V1";
        try {
            UiIcbcClient client = new UiIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY,
                    IcbcConstants.CHARSET_UTF8);
            //请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
            JftUiBillAcceptanceApplyacceptanceRequestV1.AcceptBillList acceptBill = new JftUiBillAcceptanceApplyacceptanceRequestV1.AcceptBillList();
            acceptBill.setPackNo("500000000220221130110114105134");
            acceptBill.setRangeBgn("000000000012");
            acceptBill.setRangeEnd("000000000101");
            acceptBill.setRangeAmt("11");
            acceptBill.setDrwrAcctId("0200000309024100566");
            acceptBill.setBusiSeq("7879454b");

            List<JftUiBillAcceptanceApplyacceptanceRequestV1.AcceptBillList> acceptBillList = new ArrayList<>();
            acceptBillList.add(acceptBill);

            JftUiBillAcceptanceApplyacceptanceRequestV1.JftUiBillAcceptanceApplyacceptanceRequestBiz bizContent =
                    new JftUiBillAcceptanceApplyacceptanceRequestV1.JftUiBillAcceptanceApplyacceptanceRequestBiz();
            bizContent.setAppId("10000000000000328022");
            bizContent.setOutVendorId("F202301121017");
            bizContent.setRemark("备注");
            bizContent.setBillList(acceptBillList);
            JftUiBillAcceptanceApplyacceptanceRequestV1 request = new JftUiBillAcceptanceApplyacceptanceRequestV1();
            request.setServiceUrl(apiUrl);
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
