package com.icbc.api;


import com.icbc.api.request.JftUiBillIssuanceApplyRequestV1;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class JftUiBillIssuanceApplyTestServletV1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doPost(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String MY_PRIVATE_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";
        String APP_ID = "[需替换]APPID";
        String HOST = "[需替换]工行API网关地址";
        String API_URL = HOST + "/ui/jft/ui/bill/issuance/apply/ugbill/V1";

        try {
            UiIcbcClient client = new UiIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY,
                    IcbcConstants.CHARSET_UTF8);
            //请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
            JftUiBillIssuanceApplyRequestV1.BillInfo billinfo = new JftUiBillIssuanceApplyRequestV1.BillInfo();
            billinfo.setPackNo("11111");
            billinfo.setRangeBgn("000000000012");
            billinfo.setRangeEnd("000000000101");
            billinfo.setRangeAmt("11");
            billinfo.setDrwrAcctId("111111");
            billinfo.setBusiSeq("111");
            List<JftUiBillIssuanceApplyRequestV1.BillInfo> billInfoList = new ArrayList<>();
            billInfoList.add(billinfo);
            JftUiBillIssuanceApplyRequestV1.JftUiBillIssuanceApplyRequestV1Biz bizContent =
                    new JftUiBillIssuanceApplyRequestV1.JftUiBillIssuanceApplyRequestV1Biz();
            bizContent.setAppId("1111");
            bizContent.setOutVendorId("111");
            bizContent.setIsBooking("1");
            bizContent.setBookingDate("20230203");
            bizContent.setBillList(billInfoList);
            bizContent.setNotifyUrl("xxxx");
            JftUiBillIssuanceApplyRequestV1 request = new JftUiBillIssuanceApplyRequestV1();
            request.setServiceUrl(API_URL);
            request.setBizContent(bizContent);
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
        }
    }
}
