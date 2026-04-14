package icbc.api;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcConstants;
import com.icbc.api.request.CardbusinessC2bPreauthorizationConnPreauthorRequestV1;
import com.icbc.api.response.CardbusinessC2bPreauthorizationConnPreauthorResponseV1;

public class CardbusinessC2bPreauthorizationConnPreauthorV1Test {

    //1、网关公钥
    protected static final String APIGW_PUBLIC_KEY = "****";

    //2、appid
    protected static final String APP_ID = "******";

    //3、密钥对认证方式，公钥在API平台登记，此处为私钥
    protected static final String MY_PRIVATE_KEY = "***";

    public static void main(String[] args) throws Exception {

        //签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

        CardbusinessC2bPreauthorizationConnPreauthorRequestV1 request = new CardbusinessC2bPreauthorizationConnPreauthorRequestV1();
        //4、根据测试环境和生产环境替换相应ip和端口
        request.setServiceUrl("http://****/api/cardbusiness/ncpay/agreement/sendSMS/V1");
        //5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
        CardbusinessC2bPreauthorizationConnPreauthorRequestV1.CardbusinessC2bPreauthorizationConnPreauthorRequestV1Biz bizContent = new CardbusinessC2bPreauthorizationConnPreauthorRequestV1.CardbusinessC2bPreauthorizationConnPreauthorRequestV1Biz();
        request.setBizContent(bizContent);
        bizContent.setCardNo("***");
        bizContent.setpOutTradeNo("20200311095409005");
        bizContent.setOrigDateTime("20250501T11:24:31");
        bizContent.setMerPrtclNo("0200010401350201");
        bizContent.setMerId("020001040135");
        bizContent.setCardNo("6222020000000000");
        bizContent.setTrxCurrType("001");
        bizContent.setpTotalAmt("1000");
        bizContent.setAttach("附加数据");
        bizContent.setOrderApdInf("1");
        bizContent.setTerminalType("07");
        bizContent.setThreeDFlag("1");
        bizContent.setAv("1234512345");
        bizContent.setDsTransactionTd("12345123");
        bizContent.setEci("xxx");
        bizContent.setExtendInfo("xx");
        bizContent.setCardExpire("12345123451234512345");
        bizContent.setCsc4("xxxx");

        CardbusinessC2bPreauthorizationConnPreauthorResponseV1 response;
        try {
            response = client.execute(request, "2334***4439");//msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一

            if (response.isSuccess()) {
                // 6、业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
                //System.out.println("ReturnCode:"+response.getReturnCode());
                //System.out.println("response:" + response);

            } else {
                // 失败
                //System.out.println("ReturnCode:"+response.getReturnCode());
                //System.out.println("ReturnMsg:"+response.getReturnMsg());
            }
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }
}
