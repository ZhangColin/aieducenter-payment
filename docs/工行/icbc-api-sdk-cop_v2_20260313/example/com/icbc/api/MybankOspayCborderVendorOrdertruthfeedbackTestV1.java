package com.icbc.api;


import com.icbc.api.request.MybankOspayCborderVendorOrdertruthfeedbackRequestV1;
import com.icbc.api.request.MybankOspayCborderVendorOrdertruthfeedbackRequestV1.MybankOspayCborderVendorOrdertruthfeedbackRequestV1Biz;
import com.icbc.api.request.MybankOspayCborderVendorOrdertruthfeedbackRequestV1.MybankOspayCborderVendorOrdertruthfeedbackRequestV1Biz.InnerVendorOrdertruthfeedbackRequestV1;
import com.icbc.api.response.MybankOspayCborderVendorOrdertruthfeedbackResponseV1;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class MybankOspayCborderVendorOrdertruthfeedbackTestV1 {
    // 应用私钥
    protected static final String MY_PRIVATE_KEY = "私钥";
    // 网关公钥
    protected static final String APIGW_PUBLIC_KEY = "网关公钥";
    // 应用ID，暂定，APP申请分配
    protected static final String APP_ID = "00000000000000008018";
    protected static final String SIGN_TYPE = "RSA2";


    public void test() throws IcbcApiException{
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, SIGN_TYPE, MY_PRIVATE_KEY,APIGW_PUBLIC_KEY);

        MybankOspayCborderVendorOrdertruthfeedbackRequestV1 request = new MybankOspayCborderVendorOrdertruthfeedbackRequestV1();
        request.setServiceUrl("http://IP:port/api/mybank/ospay/cborder/vendor/ordertruthfeedback/V1");

        MybankOspayCborderVendorOrdertruthfeedbackRequestV1Biz bizContent = new MybankOspayCborderVendorOrdertruthfeedbackRequestV1Biz();

        InnerVendorOrdertruthfeedbackRequestV1 order = new InnerVendorOrdertruthfeedbackRequestV1();

        bizContent.setPaymentNo("0000502");
        bizContent.setParentVendorId("11");
        bizContent.setVendorId("11");

        List<InnerVendorOrdertruthfeedbackRequestV1> orderList = new ArrayList<>() ;
        order.setOrderNo("1");
        order.setReason("原因");
        order.setStatus("001");
        orderList.add(order) ;
        bizContent.setOrderList(orderList);
        request.setBizContent(bizContent);

        try {
            MybankOspayCborderVendorOrdertruthfeedbackResponseV1 response = client.execute(request);
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
