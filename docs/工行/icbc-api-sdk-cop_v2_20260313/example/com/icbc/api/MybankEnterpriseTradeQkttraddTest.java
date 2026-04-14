package com.icbc.api;

import com.icbc.api.request.MybankEnterpriseTradeQkttraddRequestV1;
import com.icbc.api.request.MybankEnterpriseTradeQkttraddRequestV1.MybankEnterpriseTradeQkttraddRequestBizV1;
import com.icbc.api.response.MybankEnterpriseTradeQkttraddResponseV1;

public class MybankEnterpriseTradeQkttraddTest {

    protected static String APP_ID = "[需替换]工行APP编号";
    protected static String APIGW_PUBLIC_KEY = "[需替换]工行网关公钥";
    protected static String PRI_KEY = "[需替换]工行网关私钥";
    protected static String BASE_URL = "[需替换]工行API地址"; // api地址

    public static void main(String[] args) throws Exception {
        String URI = "mybank/enterprise/trade/qkttradd/V1";
        
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, CA_PRIVATE_STR, APIGW_PUBLIC_KEY, CA_PUBLIC_STR, CA_PASSWORD);
        try {
            MybankEnterpriseTradeQkttraddRequestBizV1 bizContent =
                    new MybankEnterpriseTradeQkttraddRequestBizV1();
            MybankEnterpriseTradeQkttraddRequestV1 request = new MybankEnterpriseTradeQkttraddRequestV1();
            // 请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
            bizContent.setTransCode("QKTTRADD");
            bizContent.setTranDate("20190101");
            bizContent.setTranTime("103231001");
            bizContent.setLanguage("zh_CN");
            bizContent.setfSeqNo("QKTTRADD1909431305");
            bizContent.setContractType("1");
            bizContent.setCptNo("62019012000022");
            request.setServiceUrl(BASE_URL + "/" + URI);
            request.setBizContent(bizContent);
            MybankEnterpriseTradeQkttraddResponseV1 response = client.execute(request);
            if (response.isSuccess()) {
                // 业务成功处理
                System.out.println("success");
            } else {
                // 失败
                System.out.println("error");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
