package com.icbc.api;

import com.icbc.api.request.MybankCardbusinessServiceTicketimagedownloadRequestV1;
import com.icbc.api.response.MybankCardbusinessServiceTicketimagedownloadResponseV1;

import java.util.HashMap;
import java.util.Map;

public class MybankCardbusinessServiceTicketimagedownloadTest {

    public static void main(String[] args) {
        String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----";
        String APP_ID = "请使用正确的APPID";
        String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----";

        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2",
                MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);  //signType具体见申请的APP的签名类型，要一致

        MybankCardbusinessServiceTicketimagedownloadRequestV1 request = new MybankCardbusinessServiceTicketimagedownloadRequestV1();
        request.setServiceUrl("http://ip:port/api/copbig/mybank/cardbusiness/service/ticketimagedownload/V1");
        MybankCardbusinessServiceTicketimagedownloadRequestV1.MybankCardbusinessServiceTicketimagedownloadRequestV1Biz bizContent = new MybankCardbusinessServiceTicketimagedownloadRequestV1.MybankCardbusinessServiceTicketimagedownloadRequestV1Biz();
        bizContent.setSerno("360212545212");//外包服务机构编号
        bizContent.setTicketNo("00000000020024101210000050");//工单编号
        bizContent.setImageType("1");//影像类型，1-创建工单影像，2-完成工单影像
        request.setBizContent(bizContent);
        MybankCardbusinessServiceTicketimagedownloadResponseV1 response;

        try {
            response = client.execute(request);
            if (response.isSuccess()) {
                System.out.println(" success:" + response.getReturn_code() + response.getReturn_msg());
            } else {
                System.out.println(" fail:" + response.getReturn_code() + response.getReturn_msg());
            }
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }

}
