package com.icbc.api;

import com.icbc.api.request.MybankCardbusinessShopMerRegistrationQueryRequestV1;
import com.icbc.api.request.MybankCardbusinessShopMerRegistrationQueryRequestV1Biz;
import com.icbc.api.response.MybankCardbusinessShopMerRegistrationQueryResponseV1;

public class MybankCardbusinessShopMerRegistrationQueryTest {
    protected static String app_id="10000000000004099048";
    protected static String apigw_public_key="公钥";
    protected static String pri_key="私钥";
    protected static String base_url="http://ip:port/api";

    public static void main(String[]args) throws Exception{
        String uri="/mybank/cardbusiness/shop/merregistrationquery/v1";//api地址
        DefaultIcbcClient client=new DefaultIcbcClient(app_id,pri_key,apigw_public_key);
        try{
            MybankCardbusinessShopMerRegistrationQueryRequestV1Biz bizcontent=new MybankCardbusinessShopMerRegistrationQueryRequestV1Biz();
            MybankCardbusinessShopMerRegistrationQueryRequestV1 request=new MybankCardbusinessShopMerRegistrationQueryRequestV1();
//请对照接口文档用bizcontent.setxxx()方法对业务上送数据进行赋值
            bizcontent.setCrftType("101");
            bizcontent.setCrftNo("123drre");
            bizcontent.setSerId("121212");

            request.setServiceUrl(base_url+"/"+uri);
            request.setBizContent(bizcontent);
//输出支付指令提交响应对象，需根据实际调用服务更换（mybankcardbusinessshopmerregistrationaddv1）
            MybankCardbusinessShopMerRegistrationQueryResponseV1 response=client.execute(request);
            if(response.isSuccess()){
//业务成功处理
                response.getResults();//查到的結果
                System.out.println("success");//
            }else{
//失败
                System.out.println("error");
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
