package com.icbc.api;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcConstants;
import com.icbc.api.response.JftApiUserQueryUserInfoH5FaceResponseV1;
import com.icbc.api.request.JftApiUserQueryUserInfoH5FaceRequestV1;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JftApiUserQueryUserInfoH5FaceTestServletV1 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // FIXME：此处替换工行API网关公钥，请向工行联系获取
        final String APIGW_PUBLIC_KEY = "[需替换]此处替换工行API网关公钥，请向工行联系获取";
        
        // FIXME：此处替换工行API网关域名，请向工行联系获取
        final String APIGW_URL = "[需替换]此处替换工行API网关域名，请向工行联系获取";

        // FIXME: 此处替换合作方 APPID
        final String APP_ID = "[需替换]此处替换合作方 APPID";

        // FIXME：此处替换合作方 APPID 对应私钥
        final String MY_PRIVATE_KEY = "[需替换]此处替换合作方 APPID 对应私钥";

        // FIXME：此处替换合作方 AES 加密秘钥
        final String AES_Key = "[需替换]此处替换合作方 AES 加密秘钥";

        // 声明工行开放平台 API 数据接口请求 Client 实体类，传入配置项
        DefaultIcbcClient client = new DefaultIcbcClient(
                APP_ID,
                IcbcConstants.SIGN_TYPE_RSA2,
                MY_PRIVATE_KEY,
                IcbcConstants.CHARSET_UTF8,
                IcbcConstants.FORMAT_JSON,
                APIGW_PUBLIC_KEY,
                IcbcConstants.ENCRYPT_TYPE_AES,
                AES_Key,
                "", "");

        // 组装调用《查询实人认证比对结果接口V1》上送报文
        JftApiUserQueryUserInfoH5FaceRequestV1 request = new JftApiUserQueryUserInfoH5FaceRequestV1();
        request.setServiceUrl(APIGW_URL+"/api/jft/api/user/faceH5/query/V1");

        JftApiUserQueryUserInfoH5FaceRequestV1.JftApiUserQueryUserInfoH5RequestV1Biz bizContent = new JftApiUserQueryUserInfoH5FaceRequestV1.JftApiUserQueryUserInfoH5RequestV1Biz();
        bizContent.setAppId("11000000000000027551");
        bizContent.setTransNo("2300000000027551");
        request.setBizContent(bizContent);
        JftApiUserQueryUserInfoH5FaceResponseV1 response;
        try {
            response = client.execute(request, System.currentTimeMillis() + "");//msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一
            if (response.isSuccess()) {
                //业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
                System.out.println("ReturnCode:" + response.getReturnCode());
                System.out.println("response:" + response);
            } else {
                //失败
                System.out.println("ReturnCode:" + response.getReturnCode());
                System.out.println("ReturnMsg:" + response.getReturnMsg());
            }
        } catch (IcbcApiException e) {
            // log.error("");
        }
    }
}
