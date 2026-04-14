package com.icbc.api;


import com.icbc.api.request.MybankOspayCborderVendorOrderdetailqueryRequestV1;
import com.icbc.api.request.MybankOspayCborderVendorOrderdetailqueryRequestV1.MybankOspayCborderVendorOrderdetailqueryRequestV1Biz;
import com.icbc.api.response.MybankOspayCborderVendorOrderdetailqueryResponseV1;
import org.junit.Test;

public class MybankOspayCborderVendorOrderdetailqueryTestV1 {
    // 应用私钥
    protected static final String MY_PRIVATE_KEY = "私钥";
    // 网关公钥
    protected static final String APIGW_PUBLIC_KEY = "网关公钥";
    // 应用ID，暂定，APP申请分配
    protected static final String APP_ID = "00000000000000008018";
    protected static final String SIGN_TYPE = "RSA2";


    public void test() throws IcbcApiException{
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, SIGN_TYPE, MY_PRIVATE_KEY,APIGW_PUBLIC_KEY);

        MybankOspayCborderVendorOrderdetailqueryRequestV1 request = new MybankOspayCborderVendorOrderdetailqueryRequestV1();
        request.setServiceUrl("http://IP:port/api/mybank/ospay/cborder/vendor/orderdetailquery/V1");

        MybankOspayCborderVendorOrderdetailqueryRequestV1Biz bizContent = new MybankOspayCborderVendorOrderdetailqueryRequestV1Biz();

        bizContent.setPaymentNo("0000502");
        bizContent.setPageNum("1");
        bizContent.setPageSize("10");
        bizContent.setOrderNo("11");

        request.setBizContent(bizContent);

        try {
            MybankOspayCborderVendorOrderdetailqueryResponseV1 response = client.execute(request);
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
