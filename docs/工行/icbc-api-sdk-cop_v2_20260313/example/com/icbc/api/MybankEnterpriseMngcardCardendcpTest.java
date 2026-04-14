package com.icbc.api;

import com.icbc.api.request.MybankEnterpriseMngcardCardendcpRequestV1;
import com.icbc.api.request.MybankEnterpriseMngcardCardendcpRequestV1.MybankEnterpriseMngcardCardendcpRequestBizV1;
import com.icbc.api.response.MybankEnterpriseMngcardCardendcpResponseV1;

public class MybankEnterpriseMngcardCardendcpTest {

    protected static final String BASE_URL = "[需替换]工行API地址"; // https://xxxx/api/
    protected static final String APP_ID = "[需替换]工行APP编号";
    protected static final String PRI_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";
    protected static final String APIGW_PUBLIC_KEY = "[需替换]工行API网关公钥，请向工行联系获取";

    public static void main(String[] args) throws Exception {
        String serviceUri = BASE_URL + "mybank/enterprise/mngcard/cardendcp/V1";
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, CA_PRIVATE_STR, APIGW_PUBLIC_KEY, CA_PUBLIC_STR, CA_PASSWORD);
        MybankEnterpriseMngcardCardendcpRequestBizV1 bizContent =
                new MybankEnterpriseMngcardCardendcpRequestBizV1();
        MybankEnterpriseMngcardCardendcpRequestV1 request =
                new MybankEnterpriseMngcardCardendcpRequestV1();
        // 请对照接口文档用bizContent.setXxx()方法对业务上送数据进行赋值
        bizContent.setTransCode("CARDENDCP");
        bizContent.setTranDate("20190101");
        bizContent.setTranTime("103231001");
        bizContent.setLanguage("zh_CN");
        bizContent.setfSeqNo("CARDENDCP0399427928");
        bizContent.setTotalCount("1");
        request.setServiceUrl(serviceUri);
        request.setBizContent(bizContent);
        String validate = ExceptionUtils.validate(bizContent);
        if (null == validate) {
            MybankEnterpriseMngcardCardendcpResponseV1 response = client.execute(request);
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
