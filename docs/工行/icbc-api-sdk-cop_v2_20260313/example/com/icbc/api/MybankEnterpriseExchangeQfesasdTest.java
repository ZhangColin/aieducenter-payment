package com.icbc.api;

import com.icbc.api.request.MybankEnterpriseExchangeQfesasdRequestV1;
import com.icbc.api.request.MybankEnterpriseExchangeQfesasdRequestV1.MybankEnterpriseExchangeQfesasdRequestBizV1;
import com.icbc.api.response.MybankEnterpriseExchangeQfesasdResponseV1;

public class MybankEnterpriseExchangeQfesasdTest {

    protected static final String BASE_URL = "[需替换]工行API地址"; // https://xxxx/api/
    protected static final String APP_ID = "[需替换]工行APP编号";
    protected static final String PRI_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";
    protected static final String APIGW_PUBLIC_KEY = "[需替换]工行API网关公钥，请向工行联系获取";

    public static void main(String[] args) throws Exception {
        String serviceUri = BASE_URL + "mybank/enterprise/exchange/qfesasd/V1";
        IcbcClient client = new DefaultIcbcClient(APP_ID, PRI_KEY, APIGW_PUBLIC_KEY);
        MybankEnterpriseExchangeQfesasdRequestBizV1 bizContent =
                new MybankEnterpriseExchangeQfesasdRequestBizV1();
        MybankEnterpriseExchangeQfesasdRequestV1 request =
                new MybankEnterpriseExchangeQfesasdRequestV1();
        // 请对照接口文档用bizContent.setXxx()方法对业务上送数据进行赋值
        bizContent.setTransCode("QFESASD");
        bizContent.setTranDate("20190101");
        bizContent.setTranTime("103231001");
        bizContent.setLanguage("zh_CN");
        bizContent.setfSeqNo("QFESASD0943655407");
        bizContent.setQryfSeqno("null");
        request.setServiceUrl(serviceUri);
        request.setBizContent(bizContent);
        String validate = ExceptionUtils.validate(bizContent);
        if (null == validate) {
            MybankEnterpriseExchangeQfesasdResponseV1 response = client.execute(request);
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
