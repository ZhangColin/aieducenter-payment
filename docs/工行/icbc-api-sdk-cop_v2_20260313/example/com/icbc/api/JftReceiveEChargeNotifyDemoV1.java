package me.demo;

import cas.utils.ConnectionUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcConstants;
import com.icbc.api.request.JftGovNotifyBean;
import com.icbc.api.utils.HttpRequestParamsUtils;
import com.icbc.api.utils.IcbcSignature;
import com.icbc.api.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

public class JftReceiveEChargeNotifyDemoV1 extends HttpServlet {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 验签
        // 签名由 `合作方通知地址路径+”?”+请求参数key字符串升序排序` 拼接成的字符串、通过网关私钥加密生成。合作方通过网关公钥解密后和实际入参比对是否一致
        // POST请求结果与各参数含义说明详见文档《上行网关通知接口》

        // 调用 APIP 工具类方法，解析、收集请求参数=路径参数+POST表单参数
        Map<String, String> params = HttpRequestParamsUtils.parseParams(req);
        System.out.println("通知报文入参: " + params);
        // 验签
        boolean verifyResult = verify(params, req);
        if (!verifyResult) {
            // TODO: 验签失败，合作方自行实现后续逻辑
            System.out.println("验签失败！");
            throw new IllegalArgumentException("验签失败！");
        }

        // 2. TODO：具体缴费通知处理逻辑
        String biz_content = params.get("biz_content");
        String decode = URLDecoder.decode(biz_content, "utf-8");
        String merOrderId = "";
        JftGovNotifyBean jftGovNotifyBean = objectMapper.readValue(decode, JftGovNotifyBean.class);
        if (jftGovNotifyBean != null) {
            if ("01".equals(jftGovNotifyBean.getNotifyType()) && jftGovNotifyBean.getChargeNotifyContent() != null) {
                merOrderId = jftGovNotifyBean.getChargeNotifyContent().getMerOrderId();
            } else if ("02".equals(jftGovNotifyBean.getNotifyType()) && jftGovNotifyBean.getRefundNotifyContent() != null) {
                merOrderId = jftGovNotifyBean.getRefundNotifyContent().getMerOrderId();
            }
        }
        System.out.println("插入gov_payment_inform表 decode is : " + decode + "and merOrderId is :" + merOrderId);
        boolean result = ConnectionUtil.savePaymentInform(decode, merOrderId);
        System.out.println("插入gov_payment_inform表: " + result);

        // 3. 响应通知接口（业务响应报文+签名）
        // 业务响应报文
        Map<String, Object> bizMap = new HashMap<>();
        bizMap.put("return_code", 0);           // 0-接收通知结果成功
        bizMap.put("return_msg", "success");    // 接收通知结果成功信息
        UUID uuid = UUID.randomUUID();
        String msgId = uuid.toString().replace("-", "");
        bizMap.put("msg_id", msgId);            // msg_id 要求10分钟内唯一，这里采取 UUID 作为 msg_id

        // 合作方签名
        Map<String, Object> responseMap = new LinkedHashMap<>();
        responseMap.put("response_biz_content", bizMap);
        responseMap.put("sign_type", IcbcConstants.SIGN_TYPE_RSA2);
        try {
            // 以响应报文json字符串中，在第一个 `{` 后，在 `,"sign"` 之前的字符串作为待签名字符串
            // 详见文档《上行网关通知接口》
            String strToSign = "\"response_biz_content\":" + objectMapper.writeValueAsString(bizMap)
                    + "," + "\"sign_type\":\"" + responseMap.get("sign_type") + "\"";
            System.out.println("Out strToSign: " + strToSign);

            // 调用工行 apip sdk 签名方法将响应报文签名
            // FIXME: 替换为合作方 RSA 秘钥
            String appId = params.get("app_id");
            final String BIZ_RSA_PRIVATE_KEY = ConnectionUtil.selectAppByAppId(appId).getPrivateKey();
            System.out.println("私钥: " + BIZ_RSA_PRIVATE_KEY);
            responseMap.put("sign", IcbcSignature.sign(strToSign,
                    IcbcConstants.SIGN_TYPE_RSA2,
                    BIZ_RSA_PRIVATE_KEY,
                    IcbcConstants.CHARSET_UTF8,
                    null));
        } catch (IcbcApiException e) {
            System.out.println("签名失败: " + e);
        }

        // 响应报文为 json 格式
        System.out.println("响应报文: " + objectMapper.writeValueAsString(responseMap));
        resp.setContentType("application/json; charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println(objectMapper.writeValueAsString(responseMap));
        out.close();
    }

    private boolean verify(Map<String, String> params, HttpServletRequest request) {
        // 取出 APIP 入参中的 sign（APIP 签名和 charSet）
        String sign = params.get(IcbcConstants.SIGN);
        String charset = params.get(IcbcConstants.CHARSET);
        // 去掉入参中的网关签名字段，其余入参用于验签
        params.remove(IcbcConstants.SIGN);
        // 合作方回调地址，然后调用工行 apip sdk 方法拼接好签名字符串
        params.put("biz_content", request.getParameter("biz_content"));
        String servletPath = "/govn/sandbox" + request.getRequestURI();
        String strToSign = WebUtils.buildOrderedSignStr(servletPath, params);

        System.out.printf("验签准备数据：params:%s, servletPath:%s, strToSign:%s\n", params, servletPath, strToSign);

        // 调用工行 apip sdk 方法验签
        boolean verifyResult = false;
        try {
            // FIXME：替换为实际 APIP 网关公钥
            final String APIP_GATEWAY_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
            verifyResult = IcbcSignature.verify(strToSign, IcbcConstants.SIGN_TYPE_RSA,
                    APIP_GATEWAY_PUBLIC_KEY,
                    charset, sign);
        } catch (IcbcApiException e) {
            System.out.println("验签异常:" + e);
        }

        System.out.printf("验签结束：verifyResult:%s\n", verifyResult);

        return verifyResult;
    }
}
