package me.demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcConstants;
import com.icbc.api.UiIcbcClient;
import com.icbc.api.request.JftUiGovnAddEchargeOrderRequestV1;
import com.icbc.api.request.JftUiGovnAddEchargeOrderRequestV1.JftUiGovnAddEchargeOrderRequestV1Biz;

public class JftUiGovnAddEchargeOrderTestServletV1 extends HttpServlet {
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
            final String APP_ID = "100000000000045510";
            // FIXME：此处替换合作方 APPID 对应私钥
            final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----CdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJ42tqQomxbLvuZTVKO4bBLwGp8SzRMiJ7jX9JNlS4sqmZzs9Z4lw/SSxf4dF9JJUErpNReUjzLp0As2FiuV8BALh6GYmiHGTWx7+v3HuWH9LiZNI00Ia+1HfpkJ8Sao8Ny5h1Pu49LayVleeLy496+phRE81dqlqaSHH8cKWnK1AgMBAAECgYBxod5f3QI2xzNe/e7GgAivOWAFbF16JofdDM4Opyww0fHucYqfgYRSPrCRqJeZYyqWAxUs0HhhGulfhAM8Xr5BxY5//x4HDqE2rOF7mrc/xkWNL+S9X5U0T8cdA3fnF/rYAUnJjKmeliuD2gcf5xYJENcnb8p/GQbiwIjoHDqe6QJBANzcevl9ke4cx1fa3ZcxVS3A1eHVtp573M/lo+8pRJlrf96ghFB54CzumsC0F7YfojDgCAN2egTqCGqNOuPJWWcCQQC3YqT5xlMlGk6Agav5Y6E5blx/AKJB8DeZSbjXLvytpXOASzoNR/A40uQXanXVqNU68l96FSq415AmTHi9OdWDAkA0el7t8Rw/i68B/Qsx5ZLrsCoh4vnlZmDtNQ9iwFeAbL6RU2qdBJhzlK5Io4IO0C1ll5XP3NLZYBJn3u7jOPB5AkEAiM/qHoHsM8j9effD0kmW1V7VWNajNqg9AnoykS73yaCem78DrzbVK7+B9UoyYNUVR2Xc/xpdhgsj+r6gcSN9ewJANSmxYF34601mteAnZwhyGKCJomiuSMNTr52i2uTspdZi0zqaerI1EGD3dAENxRebyPmPFxhwCcCbzF2gNUr1fw==";

            // 通过工行 SDK 调用《聚富通E缴费预订单接口V1》页面接口
            UiIcbcClient client = new UiIcbcClient(APP_ID, MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8);
            JftUiGovnAddEchargeOrderRequestV1 request = new JftUiGovnAddEchargeOrderRequestV1();
            // 指定《聚富通E缴费预订单接口V1》URL
            request.setServiceUrl("https://ip:port/ui/jft/ui/govn/echarge/add/order/V1");

            /**
             * 组装调用页面接口上送报文。接口各请求参数说明详见接口文档
             */
            JftUiGovnAddEchargeOrderRequestV1Biz bizContent = new JftUiGovnAddEchargeOrderRequestV1Biz();
            bizContent.setAppId(APP_ID);
            bizContent.setOutUserId("M201801112326");
            bizContent.setOutOrderId("20200202018");
            bizContent.setTrxChannel("03");
            bizContent.setProjectId("PJ140014021000004309");
            bizContent.setNotifyUrl("http://www.jft.com");
            bizContent.setJumpUrl("http://www.goto.com");
            bizContent.setTrxIp("ip:port");
            bizContent.setTrxIpCity("北京");
            bizContent.setMac("a3-b4-c5");
            bizContent.setVarNote("备注");

            /**
             * 接入第三方支付（微信、支付宝）相关参数
             */
            bizContent.setTpAppId("wx8888888888888888");
            bizContent.setTpOpenId("oUSDOusdsdISLSDlskdf");
            bizContent.setUnionId("oUSDOusdsdISLSDlskdf");
            bizContent.setMiniProgramWxPayJumpUrl("http://www.biz.com/miniProgram/wxPayJump");

            /**
             * 接入聚富通优惠相关参数
             */
            bizContent.setPromotionMode("1");
            // 组装聚富通优惠券活动信息
            JftUiGovnAddEchargeOrderRequestV1Biz.PromotionInfoDTO promotionInfoDTO = new JftUiGovnAddEchargeOrderRequestV1Biz.PromotionInfoDTO();
            promotionInfoDTO.setPromotionId("02022102514162001401");
            promotionInfoDTO.setPromotionType("1");
            promotionInfoDTO.setCouponInfoList(new ArrayList<>());
            // 用户有两条优惠券信息：coupon1-满100减20元；coupon2-满200减30元
            JftUiGovnAddEchargeOrderRequestV1Biz.CouponInfoDTO couponInfo1 = new JftUiGovnAddEchargeOrderRequestV1Biz.CouponInfoDTO();
            couponInfo1.setCouponId("coupon1");
            couponInfo1.setDiscountFloorAmount("100.00");
            couponInfo1.setDiscountAmount("20.00");
            promotionInfoDTO.getCouponInfoList().add(couponInfo1);
            JftUiGovnAddEchargeOrderRequestV1Biz.CouponInfoDTO couponInfo2 = new JftUiGovnAddEchargeOrderRequestV1Biz.CouponInfoDTO();
            couponInfo2.setCouponId("coupon2");
            couponInfo2.setDiscountFloorAmount("200.00");
            couponInfo2.setDiscountAmount("30.00");
            promotionInfoDTO.getCouponInfoList().add(couponInfo2);
            bizContent.setPromotionInfo(Collections.singletonList(promotionInfoDTO));

            request.setBizContent(bizContent);


            // 调用工行 SDK，生成自动提交表单，将用户跳转到缴费页面
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
