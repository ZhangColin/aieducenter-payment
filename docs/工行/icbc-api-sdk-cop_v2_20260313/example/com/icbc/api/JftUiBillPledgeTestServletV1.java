package com.icbc.api;
//package com.icbc.ndf.jft.pay.api.impl.pay;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class JftUiBillPledgeTestServletV1 extends HttpServlet {

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
        String apiUrl= host+"/ui/jft/ui/bill/ugbill/pledge/page/V1";
        try {


            UiIcbcClient client = new UiIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY,
                    IcbcConstants.CHARSET_UTF8);

            //请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
            JftUiBillPledgeRequestV1.BillList bill = new JftUiBillPledgeRequestV1.BillList();
            bill.setPackNo("500000000220221130110114105134"); //票包号
            bill.setRangeBgn("000000000012"); //起始子区间
            bill.setRangeEnd("000000000101"); //截止子区间
            bill.setPledgeAmt("1"); //质押金额
            bill.setHolderAcctId("0200000309024100566"); //持票人账号
            bill.setBusiSeq("2022020310a"); //业务序列号
            List<JftUiBillPledgeRequestV1.BillList> billList = new ArrayList<>();
            billList.add(bill);

            JftUiBillPledgeRequestV1.JftUiBillPledgeRequestV1Biz bizContent = new JftUiBillPledgeRequestV1.JftUiBillPledgeRequestV1Biz();
            bizContent.setAppId(APP_ID);//合作方编号
            bizContent.setOutVendorId("F202301121017");//子商户编号
            bizContent.setNotifyfUrl("通知地址");//通知地址
            bizContent.setPledgeeRoleCode("RC01");//质权人类型
            bizContent.setPledgeeAcctSvcr("100000");//质权人行号
            bizContent.setPledgeeIsIcbc("1");//是否为工行客户标识
            bizContent.setPledgeeAcctId("0200000309024100562");//质权人账号
            bizContent.setPledgeeAcctNm("出策语知婚平民轮频识");//质权人账号全称
            bizContent.setRemark("备注"); //备注
            bizContent.setBillList(billList);//质押票据列表

            JftUiBillPledgeRequestV1 request = new JftUiBillPledgeRequestV1();
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
