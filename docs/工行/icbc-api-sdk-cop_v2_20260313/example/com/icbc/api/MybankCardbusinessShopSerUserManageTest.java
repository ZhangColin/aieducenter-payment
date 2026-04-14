package icbc.api;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.request.MybankCardbusinessShopSerUserManageRequestV1;
import com.icbc.api.response.MybankCardbusinessShopSerUserManageResponseV1;

public class MybankCardbusinessShopSerUserManageTest {

    public static void main(String[] args) throws Exception{

        String MY_PRIVATE_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";
        String APP_ID = "[需替换]工行API APPID，请向工行联系获取";
        String APIGW_PUBLIC_KEY = "[需替换]工行API网关公钥，请向工行联系获取";

        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,"RSA2",
                MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);  //signType具体见申请的APP的签名类型，要一致

        MybankCardbusinessShopSerUserManageRequestV1 request = new MybankCardbusinessShopSerUserManageRequestV1();

        request.setServiceUrl("http://[需替换]地址/api/mybank/cardbusiness/shop/serUserManage/V1");
        //	request.setServiceUrl("https://ip:port/api/mybank/customer/shoponline/csocustopen/V1");
        MybankCardbusinessShopSerUserManageRequestV1.MybankCardbusinessShopSerUserManageRequestV1Biz bizRes = new MybankCardbusinessShopSerUserManageRequestV1.MybankCardbusinessShopSerUserManageRequestV1Biz();

        bizRes.setCustName("牡丹行");
        bizRes.setMobileNo("13234345654");
        bizRes.setSerId("1232");
        bizRes.setTrxdate("2022-02-10");
        bizRes.setOperFlag("1");
        bizRes.setNewMobileNo("13234543456");
        bizRes.setTrxtime("02:12:10");
        request.setBizContent( bizRes);
        MybankCardbusinessShopSerUserManageResponseV1 response = new MybankCardbusinessShopSerUserManageResponseV1();
        try{
            response = client.execute(request, "msgId");
            // 判断调用是否成功，进行后续业务处理
            if (response.isSuccess()) {
                // TODO 业务成功处理
                System.out.println(" success:"+response.getReturnCode()+response.getReturnMsg());
            } else {
                // TODO 失败
                System.out.println(" fail:"+response.getReturnCode()+response.getReturnMsg());
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
