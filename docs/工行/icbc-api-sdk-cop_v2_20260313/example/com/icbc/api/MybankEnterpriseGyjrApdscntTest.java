package com.icbc.api;

import com.icbc.api.request.MybankEnterpriseGyjrApdscntRequestV1;
import com.icbc.api.request.MybankEnterpriseGyjrApdscntRequestV1.MybankEnterpriseGyjrApdscntRequestBizV1;
import com.icbc.api.response.MybankEnterpriseGyjrApdscntResponseV1;

public class MybankEnterpriseGyjrApdscntTest {

    protected static final String BASE_URL = "[需替换]工行API地址"; // https://xxxx/api/
    protected static final String APP_ID = "[需替换]工行APP编号";
    protected static final String PRI_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";
    protected static final String APIGW_PUBLIC_KEY = "[需替换]工行API网关公钥，请向工行联系获取";

    public static void main(String[] args) throws Exception {
        String serviceUri = BASE_URL + "mybank/enterprise/gyjr/apdscnt/V1";
        IcbcClient client = new DefaultIcbcClient(APP_ID, PRI_KEY, APIGW_PUBLIC_KEY);
        MybankEnterpriseGyjrApdscntRequestBizV1 bizContent =
                new MybankEnterpriseGyjrApdscntRequestBizV1();
        MybankEnterpriseGyjrApdscntRequestV1 request =
                new MybankEnterpriseGyjrApdscntRequestV1();
        // 请对照接口文档用bizContent.setXxx()方法对业务上送数据进行赋值
        bizContent.setTransCode("APDSCNT");
        bizContent.setTranDate("20190101");
        bizContent.setTranTime("103231001");
        bizContent.setLanguage("zh_CN");
        bizContent.setfSeqNo("APDSCNT2061351819");
        bizContent.setOperType("");
        request.setServiceUrl(serviceUri);
        request.setBizContent(bizContent);
        String validate = ExceptionUtils.validate(bizContent);
        if (null == validate) {
            MybankEnterpriseGyjrApdscntResponseV1 response = client.execute(request);
            if (response.isSuccess()) { // 业务成功处理
                System.out.println("success");
            } else { // 失败
                System.out.println("error");
            }
        } else { // 校验不通过
            System.err.println(validate);
        }
    }
}
