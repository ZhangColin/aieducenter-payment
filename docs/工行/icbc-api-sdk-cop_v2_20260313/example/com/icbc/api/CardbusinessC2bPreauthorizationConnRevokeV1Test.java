package icbc.api;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcConstants;
import com.icbc.api.request.CardbusinessC2bPreauthorizationConnRevokeRequestV1;
import com.icbc.api.response.CardbusinessC2bPreauthorizationConnRevokeResponseV1;

public class CardbusinessC2bPreauthorizationConnRevokeV1Test {

    //1、网关公钥
    protected static final String APIGW_PUBLIC_KEY = "****";

    //2、appid
    protected static final String APP_ID = "******";

    //3、密钥对认证方式，公钥在API平台登记，此处为私钥
    protected static final String MY_PRIVATE_KEY = "***";

    public static void main(String[] args) throws Exception {

        //签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

        CardbusinessC2bPreauthorizationConnRevokeRequestV1 request = new CardbusinessC2bPreauthorizationConnRevokeRequestV1();
        //4、根据测试环境和生产环境替换相应ip和端口
        request.setServiceUrl("http://****/api/cardbusiness/ncpay/agreement/sendSMS/V1");
        //5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
        CardbusinessC2bPreauthorizationConnRevokeRequestV1.CardbusinessC2bPreauthorizationConnRevokeRequestV1Biz bizContent = new CardbusinessC2bPreauthorizationConnRevokeRequestV1.CardbusinessC2bPreauthorizationConnRevokeRequestV1Biz();
        request.setBizContent(bizContent);
        bizContent.setOuttrxSerialNo("20200708020026");
        bizContent.setOrigDateTime("20210320143100");
        bizContent.setMerPrtclNo("0200010402460201");
        bizContent.setMerId("020001040246");
        bizContent.setOpOrderId("020001040246000722007080057001");
        bizContent.setOpOutTradeNo("");
        bizContent.setoTrxAmt("1");
        bizContent.setoAuthorNo("523177");
        bizContent.setAttach("");
        bizContent.setOrderApdInf("");
        bizContent.setTerminalType("07");
        bizContent.setEci("");

        CardbusinessC2bPreauthorizationConnRevokeResponseV1 response;
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
