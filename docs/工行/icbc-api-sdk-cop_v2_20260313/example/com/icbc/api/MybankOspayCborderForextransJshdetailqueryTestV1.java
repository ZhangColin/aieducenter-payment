package com.icbc.api;


import com.icbc.api.request.MybankOspayCborderForextransJshdetailqueryRequestV1;
import com.icbc.api.request.MybankOspayCborderForextransJshdetailqueryRequestV1.MybankOspayCborderForextransJshdetailqueryRequestV1Biz;
import com.icbc.api.response.MybankOspayCborderForextransJshdetailqueryResponseV1;
import org.junit.Test;

public class MybankOspayCborderForextransJshdetailqueryTestV1 {
    // 应用私钥
    protected static final String MY_PRIVATE_KEY = "私钥";
    // 网关公钥
    protected static final String APIGW_PUBLIC_KEY = "网关公钥";
    // 应用ID，暂定，APP申请分配
    protected static final String APP_ID = "00000000000000008018";
    protected static final String SIGN_TYPE = "RSA2";

    
    public void test() throws IcbcApiException{
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, SIGN_TYPE, MY_PRIVATE_KEY,APIGW_PUBLIC_KEY);

        MybankOspayCborderForextransJshdetailqueryRequestV1 request = new MybankOspayCborderForextransJshdetailqueryRequestV1();
        request.setServiceUrl("http://IP:port/api/mybank/ospay/cborder/forextrans/jshdetailquery/V1");

        MybankOspayCborderForextransJshdetailqueryRequestV1Biz bizContent = new MybankOspayCborderForextransJshdetailqueryRequestV1Biz();

        bizContent.setClientId("20180701401");
        bizContent.setPageNum("1");
        bizContent.setPageSize("10");
        bizContent.setTrandId("");
        bizContent.setBeginDate("2024-03-31");
        bizContent.setEndDate("2024-04-06") ;

        request.setBizContent(bizContent);

        try {
            MybankOspayCborderForextransJshdetailqueryResponseV1 response = client.execute(request);
            if(response.isSuccess()){
                System.out.println("return_code             :       " + response.getReturnCode());
                System.out.println("return_msg              :       " + response.getReturnMsg());
            }else{
                System.out.println("return_code             :       " + response.getReturnCode());
                System.out.println("return_msg              :       " + response.getReturnMsg());
            }
        } catch (IcbcApiException e) {

            e.printStackTrace();
        }
    }

}
