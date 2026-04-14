package com.icbc.ndf.jft.pay.controller;

import com.icbc.api.IcbcConstants;
import com.icbc.api.UiIcbcClient;
import com.icbc.api.request.JftApiPayB2bpayB2bconfirmreceiptRequestV1;
import com.icbc.api.request.JftUiPayB2bpayB2bconfirmreceiptRequestV1;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Controller
@RequestMapping("/b2bLoanPay")
@RefreshScope
public class B2bLoanPayController {

    @RequestMapping("/ui/uiController")
    public void uiTestContorller(JftApiPayB2bpayB2bconfirmreceiptRequestV1 jftApiPayB2bpayB2bconfirmreceiptRequestV1, HttpServletResponse resp) {
        PrintWriter out = null;
        long startTime = System.currentTimeMillis();
        double executeTime = 0;
        try {
            String appId = jftApiPayB2bpayB2bconfirmreceiptRequestV1.getAppid();
            String myPrivateKey = jftApiPayB2bpayB2bconfirmreceiptRequestV1.getPrivateKey();
            String bizContent = jftApiPayB2bpayB2bconfirmreceiptRequestV1.getBizContent();
            String serviceUrl = jftApiPayB2bpayB2bconfirmreceiptRequestV1.getServiceUrl();

//            String appId = "10000000000000212516";
//            String myPrivateKey = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCF7eCVTURFK5+gpeaQios8lw6uTcltPalYM6/VNfNDAirGnpFP5RQNI7duf5nr+3QLe1FNMbaR6Cl1BeWcaFzYqba4UTjNrttGf9E2LBnVa7sR0sVWX/1o71nIou21+Wh044zqcSl1T6FEBpSP70fz5ahytU41hVy6lyJymDhyWzZepr4Yqnrs69GB6V/v2DDuX8t4tpZAK1cxsOSrBJHOYxUoTomLb9dX8dFI/4AIShjKv4hmwryviHJRlZSiyV8j72FgOUs7BmMz+KCaKGVGo1G+YrppnX5IruNvaXDB59bcu3lsjPODUkfMKsMr+Vrg4ipDEboCCtKuaPrZ2xMNAgMBAAECggEAY3qQcXWl+xbvB52Sx9QQnh25yuB+eETvz1L9DQp4uVIXwdYwKz8FiMj5y/q9stnglVKwzfzaFkBy4rhRl76xEEHnNbsmzShPOWthU7KoMa1Gk3OSHplIGjSG5Q+YL62k2AXZOp55Y+iU6XlVyh+6uz/fwexHvltjyDjQXnwQmWdvUHM+cy7yTxlfz8FGecZOyRY2jRUJZ7w0WfUx0p1R0vWRRpjRddMDyu7sjtkTQ54KfmqE5HQWKrQVDio2hSw7EbaylAo6HSZO8i2ZSW3neKenvV2YCUVk6AbhfJj/SfTzqzEePduOv5bUq/vaTRCWAImcHnohLhzQQS5zn3jDpQKBgQDTncyUAAOL275viu9Xbk2IBsv/tyFaNji/G8BdE0jB++7x7J3EtLRJIBOJE16vvWUJfD7CXvezqArZWLADt7lholdPuUKlEOJtXvqlYvg7zKDce+wA7HAK0a2sMSvU51nC0q4LLaI+NlGxZZcPYcoj6PYLXw/BHaqGAHm5Y+RMhwKBgQCiBNz3zYP4RnfBTazWBRm3P7jybcteFMh5inmOyw9mfpphIUpFlw9lRbt7J2+sauzlIKZ2d9MOoZIurAZ/zbgIf2msTEoyczzO6yUpyeKinl6HjizMKggLEponU7nk2nQsuVbf7wYoHPwwjT+a5k9R+PMrYVO0B74Z8PIQpHd8ywKBgQCD//qMlzWfGANCMLf7IaLbJuI5MFJto0TicL0dUdogprX10lLXUKDkvE1zQ9HcbZeIwyeitvpr5nZd/SROLVxFuq4b3MPlkxKqhoRyhbmyE4KEBgjAyrtZBFIBBusAcw9ap3BJKL37cIak0WnY/nbIz5gqn7GEFK05yTIkolrWIQKBgH2U42m4BKi2hjBw8pzZSzt8yvAuJkoANfvvV5VU09UQIL1Uvmr+UdKfoFgvEyJDpudazWxaKF/Y4KQIduktHY6Io/IHGCPOqr2ACkg/0clWk9LiYtrFYYKjopUFErvOj+nypuHgNZd2EAvaEAkSpzzUiDeBsw2CStl0p0w7DtrNAoGAIsqskDGGewcgBWQmXvSqa7o0do3coiH3ToMAQm9vlGgHAAw5xlgfVQpGwe8GR4ilysh+5EuiuLnINDt0tXpv9K7dAQgjKAmszFrOjmyBMnSDebfysynABFj3c8GglO+l9J+ljFMqgqPVzsLAgIItciyq80Xmv8TjpbwFM2v34lI=";
//            String bizContent = "{\"outOrderId\":\"zhi002\",\"appId\":\"10000000000000212516\",\"outVendorId\":\"zsh201909101759\"}";
//            String serviceUrl = "http://paycf1.dccnet.com.cn/ui/jft/ui/pay/b2bpay/loanpayconfirm/V1";

            //添加测试代码
            String url = serviceUrl.trim();
            UiIcbcClient client = new UiIcbcClient(appId, "RSA2", myPrivateKey, IcbcConstants.CHARSET_UTF8);
            JftUiPayB2bpayB2bconfirmreceiptRequestV1 request = new JftUiPayB2bpayB2bconfirmreceiptRequestV1();
            request.setServiceUrl(url);

            JftUiPayB2bpayB2bconfirmreceiptRequestV1.UiRequestV1Biz requestBiz = new JftUiPayB2bpayB2bconfirmreceiptRequestV1.UiRequestV1Biz();
            requestBiz.setBizContentMyself(bizContent);
            request.setBizContent(requestBiz);
            resp.setHeader("Content-Type", "text/html;charset=" + IcbcConstants.CHARSET_UTF8);
            out = resp.getWriter();
            out.write("<html>");
            out.write("<head>");
            out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=" + IcbcConstants.CHARSET_UTF8 + "\">");
            out.write("</head>");
            out.write("<body>");
            out.write(client.buildPostForm(request));
            out.write("</body>");
            out.write("</html>");
            long endTime = System.currentTimeMillis();
            executeTime = ((double) (endTime - startTime)) / 1000;
        } catch (Exception e) {
            out.write(e.getMessage());
            long endTime = System.currentTimeMillis();
            executeTime = ((double) (endTime - startTime)) / 1000;
        } finally {
            if (out != null) {
                out.flush();
                out.close();
            }
        }
    }

}
