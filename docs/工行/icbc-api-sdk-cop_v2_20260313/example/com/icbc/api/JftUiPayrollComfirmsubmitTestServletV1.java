package com.icbc.api.request;

import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcConstants;
import com.icbc.api.UiIcbcClient;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class JftUiPayrollComfirmsubmitTestServletV1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {

        try {
            //商户向api平台申请的平台商户编号
            String APP_ID = "10000000000000045510";
            //商户申请平台编号后获取的私钥
            String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----CdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJ42tqQomxbLvuZTVKO4bBLwGp8SzRMiJ7jX9JNlS4sqmZzs9Z4lw/SSxf4dF9JJUErpNReUjzLp0As2FiuV8BALh6GYmiHGTWx7+v3HuWH9LiZNI00Ia+1HfpkJ8Sao8Ny5h1Pu49LayVleeLy496+phRE81dqlqaSHH8cKWnK1AgMBAAECgYBxod5f3QI2xzNe/e7GgAivOWAFbF16JofdDM4Opyww0fHucYqfgYRSPrCRqJeZYyqWAxUs0HhhGulfhAM8Xr5BxY5//x4HDqE2rOF7mrc/xkWNL+S9X5U0T8cdA3fnF/rYAUnJjKmeliuD2gcf5xYJENcnb8p/GQbiwIjoHDqe6QJBANzcevl9ke4cx1fa3ZcxVS3A1eHVtp573M/lo+8pRJlrf96ghFB54CzumsC0F7YfojDgCAN2egTqCGqNOuPJWWcCQQC3YqT5xlMlGk6Agav5Y6E5blx/AKJB8DeZSbjXLvytpXOASzoNR/A40uQXanXVqNU68l96FSq415AmTHi9OdWDAkA0el7t8Rw/i68B/Qsx5ZLrsCoh4vnlZmDtNQ9iwFeAbL6RU2qdBJhzlK5Io4IO0C1ll5XP3NLZYBJn3u7jOPB5AkEAiM/qHoHsM8j9effD0kmW1V7VWNajNqg9AnoykS73yaCem78DrzbVK7+B9UoyYNUVR2Xc/xpdhgsj+r6gcSN9ewJANSmxYF34601mteAnZwhyGKCJomiuSMNTr52i2uTspdZi0zqaerI1EGD3dAENxRebyPmPFxhwCcCbzF2gNUr1fw==";

            UiIcbcClient client = new UiIcbcClient(APP_ID, MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8);

            JftUiPayrollComfirmsubmitRequest request = new JftUiPayrollComfirmsubmitRequest();

            request.setServiceUrl("http://ip:port/ui/jft/ui/govn/echarge/search/bill/V1");
            JftUiPayrollComfirmsubmitRequest.JftUiPayrollComfirmsubmitV1Biz bizContent = new JftUiPayrollComfirmsubmitRequest.JftUiPayrollComfirmsubmitV1Biz();


            bizContent.setAppId("10000000000000365010");
            bizContent.setAppName("盖亚");
            bizContent.setAppRemark("202112243");
            bizContent.setAppSerialno("1");
            bizContent.setFileMdcode("5015");
            bizContent.setFileType("5");
            bizContent.setLocalFilepath("123456789");
            bizContent.setOprType("D:/2022/1月版/代发工资确认提交文件/202112243_20220109t17_0_20211224.xls");
            bizContent.setOutVendorId("1");
            bizContent.setTotalAmt("备注");
            bizContent.setTotalCount("10");


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
