import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcClient;
import com.icbc.api.response.MybankEnterpriseTradeQrahisdResponseV1;

public class MybankEnterpriseTradeQrahisdTest {
    protected static final String BASE_URL = "[需替换]工行API地址"; // https://xxxx/api/
    protected static final String APP_ID = "[需替换]工行APP编号";
    protected static final String PRI_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";
    protected static final String APIGW_PUBLIC_KEY = "[需替换]工行API网关公钥，请向工行联系获取";

    public static void main(String[] args) throws Exception {
        String URI = "mybank/enterprise/trade/qrahisd/V1";
        IcbcClient client = new DefaultIcbcClient(APP_ID, PRI_KEY, APIGW_PUBLIC_KEY);
        try {
            MybankEnterpriseTradeQrahisdRequestV1.MybankEnterpriseTradeQrahisdRequestBizV1 bizContent =
                    new MybankEnterpriseTradeQrahisdRequestV1.MybankEnterpriseTradeQrahisdRequestBizV1();
            MybankEnterpriseTradeQrahisdRequestV1 request = new MybankEnterpriseTradeQrahisdRequestV1();
            // 请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
            bizContent.setTransCode("QRAHISD");
            bizContent.setLoginId("02000620102120202");
            bizContent.setTranDate("20190101");
            bizContent.setTranTime("103231001");
            bizContent.setLanguage("zh_CN");
            bizContent.setfSeqNo("QRAHISD0077022947");
            bizContent.setAccNum("02000620102120202");
            bizContent.setCurrType("CNY");
            bizContent.setBeginDate("2018-01-14");
            bizContent.setEndDate("2019-01-14");
            bizContent.setMinAmount(1L);
            bizContent.setMaxAmount(10000000L);
            bizContent.setNextTag(0);
            request.setServiceUrl(BASE_URL + "/" + URI);
            request.setBizContent(bizContent);
            MybankEnterpriseTradeQrahisdResponseV1 response = client.execute(request);
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
