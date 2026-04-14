package com.icbc.api;
import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcClient;
import com.icbc.api.response.MybankEnterpriseTradeQrasabinfResponseV1;

public class MybankEnterpriseTradeQrasabinfTest {
    protected static final String BASE_URL = "[需替换]工行API地址"; // https://xxxx/api/
    protected static final String APP_ID = "[需替换]工行APP编号";
    protected static final String PRI_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";
    protected static final String APIGW_PUBLIC_KEY = "[需替换]工行API网关公钥，请向工行联系获取";

    public static void main(String[] args) throws Exception {
        String URI = "mybank/enterprise/trade/qrasabinf/V1";
        IcbcClient client = new DefaultIcbcClient(APP_ID, PRI_KEY, APIGW_PUBLIC_KEY);
        try {
            MybankEnterpriseTradeQrasabinfRequestV1.MybankEnterpriseTradeQrasabinfRequestBizV1 bizContent =
                    new MybankEnterpriseTradeQrasabinfRequestV1.MybankEnterpriseTradeQrasabinfRequestBizV1();
            MybankEnterpriseTradeQrasabinfRequestV1 request = new MybankEnterpriseTradeQrasabinfRequestV1();
            // 请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
            bizContent.setTransCode("Qrasabinf");
            bizContent.setLoginId("htt000.y.0300");
            bizContent.setTranDate("20190101");
            bizContent.setTranTime("103231001");
            bizContent.setLanguage("zh_CN");
            bizContent.setfSeqNo("Qrasabinf0077022947");
            bizContent.setAccount("0200099809182994396");
            bizContent.setCurrType("CNY");
            request.setServiceUrl(BASE_URL + "/" + URI);
            request.setBizContent(bizContent);
            MybankEnterpriseTradeQrasabinfResponseV1 response = client.execute(request);
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
