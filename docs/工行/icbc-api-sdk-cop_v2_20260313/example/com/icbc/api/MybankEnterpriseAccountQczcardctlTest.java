package com.icbc.api;

import com.icbc.api.request.MybankEnterpriseAccountQczcardctlRequestV1;
import com.icbc.api.request.MybankEnterpriseAccountQczcardctlRequestV1.MybankEnterpriseAccountQczcardctlRequestBizV1;
import com.icbc.api.response.MybankEnterpriseAccountQczcardctlResponseV1;

public class MybankEnterpriseAccountQczcardctlTest {

    protected static final String BASE_URL = "[需替换]工行API地址"; // https://xxxx/api/
    protected static final String APP_ID = "[需替换]工行APP编号";
    protected static final String PRI_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";
    protected static final String APIGW_PUBLIC_KEY = "[需替换]工行API网关公钥，请向工行联系获取";

    public static void main(String[] args) throws Exception {
        String serviceUri = BASE_URL + "mybank/enterprise/account/qczcardctl/V1";
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, CA_PRIVATE_STR, APIGW_PUBLIC_KEY, CA_PUBLIC_STR, CA_PASSWORD);
        MybankEnterpriseAccountQczcardctlRequestBizV1 bizContent =
                new MybankEnterpriseAccountQczcardctlRequestBizV1();
        MybankEnterpriseAccountQczcardctlRequestV1 request =
                new MybankEnterpriseAccountQczcardctlRequestV1();
        // 请对照接口文档用bizContent.setXxx()方法对业务上送数据进行赋值
        bizContent.setTransCode("QCZCARDCTL");
        bizContent.setTranDate("20190101");
        bizContent.setTranTime("103231001");
        bizContent.setLanguage("zh_CN");
        bizContent.setfSeqNo("QCZCARDCTL1780455267");
        bizContent.setCardNo("9558832405000672231");
        request.setServiceUrl(serviceUri);
        request.setBizContent(bizContent);
        String validate = ExceptionUtils.validate(bizContent);
        if (null == validate) {
            MybankEnterpriseAccountQczcardctlResponseV1 response = client.execute(request);
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
