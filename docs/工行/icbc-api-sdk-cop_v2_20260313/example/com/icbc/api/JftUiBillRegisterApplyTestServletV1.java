package com.icbc.api;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class JftUiBillRegisterApplyTestServletV1 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
        String MY_PRIVATE_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";
        String APP_ID = "10000000000000328022";
        String host = "[需替换]工行API网关地址";
        String apiUrl= host+"/ui/jft/ui/bill/register/ugbill/apply/V1";
        try {

            UiIcbcClient client = new UiIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY,
                    IcbcConstants.CHARSET_UTF8);
            //请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
            JftUiBillRegisterRegisterapplyRequestV1.BillPayeeList billPayeeList = new JftUiBillRegisterRegisterapplyRequestV1.BillPayeeList();
            billPayeeList.setPayeeIsIcbc("1");
            billPayeeList.setPayeeAcctsvcr("11234");
            billPayeeList.setPayeeName("11");
            billPayeeList.setPayeeAcctid("0100001101001101111");
            billPayeeList.setPackAmt("1100");
            billPayeeList.setAgrmtNb("000011011111");
            billPayeeList.setDueDate("20220206");
            billPayeeList.setBnedMtMrk("0");
            billPayeeList.setBillFlag("0");
            billPayeeList.setRemark("备注");
            billPayeeList.setIsMsg("1");
            billPayeeList.setSuccPhoneNo("18201164881");
            billPayeeList.setFailPhoneNo("18201164881");
            billPayeeList.setBusiSeq("0230037");
            List<JftUiBillRegisterRegisterapplyRequestV1.BillPayeeList> payeeList = new ArrayList<>();
            payeeList.add(billPayeeList);

            JftUiBillRegisterRegisterapplyRequestV1.JftUiBillRegisterRegisterapplyRequestV1Biz bizContent =
                    new JftUiBillRegisterRegisterapplyRequestV1.JftUiBillRegisterRegisterapplyRequestV1Biz();
            bizContent.setAppId("10000000000000328022");
            bizContent.setOutVendorId("F202301121017");
            bizContent.setDrwrName("泰州市优品汇食品有限公司");
            bizContent.setDrwrAcctid("0100001101001101111");
            bizContent.setIsBooking("1");
            bizContent.setIssueDate("20250202");
            bizContent.setAccptrTp("1");
            bizContent.setAccptrIsIcbc("0");
            bizContent.setAccptrAcctsvcr("11021");
            bizContent.setAccptrName("11");
            bizContent.setAccptrAcctid("11");
            bizContent.setIsAutoAccptnc("0");
            bizContent.setIsAutoAccptncsign("0");
            bizContent.setIsGuarntee("0");
            bizContent.setGuarntrIsIcbc("0");
            bizContent.setGuarntrAcctsvcr("1123");
            bizContent.setGuarntrName("11");
            bizContent.setGuarntrAcctid("11");
            bizContent.setIsAutoIssnc("0");
            bizContent.setPayeeList(payeeList);

            JftUiBillRegisterRegisterapplyRequestV1 request = new JftUiBillRegisterRegisterapplyRequestV1();
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
