package com.icbc.api;

import com.icbc.api.request.MybankCardbusinessServiceTicketModifyRequestV1;
import com.icbc.api.response.MybankCardbusinessServiceTicketModifyResponseV1;

import java.util.ArrayList;
import java.util.List;

public class MybankCardbusinessServiceTicketModifyTest {

    public static void main(String[] args) {

        String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----";
        String APP_ID = "请使用正确的APPID";
        String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----";

        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2",
                MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);  //signType具体见申请的APP的签名类型，要一致

        MybankCardbusinessServiceTicketModifyRequestV1.MybankCardbusinessServiceTicketModifyRequestV1BizData data;
        MybankCardbusinessServiceTicketModifyRequestV1 request = new MybankCardbusinessServiceTicketModifyRequestV1();
        request.setServiceUrl("http://ip:port/api/mybank/cardbusiness/service/ticketmodify/V1");
        MybankCardbusinessServiceTicketModifyRequestV1.MybankCardbusinessServiceTicketModifyRequestV1Biz bizContent = new MybankCardbusinessServiceTicketModifyRequestV1.MybankCardbusinessServiceTicketModifyRequestV1Biz();
        bizContent.setSerno("360200012345"); // 示例值
        List<MybankCardbusinessServiceTicketModifyRequestV1.MybankCardbusinessServiceTicketModifyRequestV1BizData> dataList = new ArrayList<MybankCardbusinessServiceTicketModifyRequestV1.MybankCardbusinessServiceTicketModifyRequestV1BizData>();
        data = new MybankCardbusinessServiceTicketModifyRequestV1.MybankCardbusinessServiceTicketModifyRequestV1BizData();
        data.setTicketNo("00000000000000000000000000000001"); // 示例值
        data.setTransactor("12345");
        data.setFinishRemark("完成示例");
        data.setStatus("4"); // “已完成”
        data.setFinishTimeOutser("2024-12-20 21:14:33");
        dataList.add(data);
        data = new MybankCardbusinessServiceTicketModifyRequestV1.MybankCardbusinessServiceTicketModifyRequestV1BizData();
        data.setTicketNo("00000000000000000000000000000002"); // 示例值
        data.setTransactor("54321");
        data.setFinishRemark(null); // 非必输项
        data.setStatus("2"); // “处理中”
        data.setFinishTimeOutser("2024-12-20 21:14:33");
        dataList.add(data);
        bizContent.setDataList(dataList);
        request.setBizContent(bizContent);

        try {
            MybankCardbusinessServiceTicketModifyResponseV1 response = client.execute(request);
            if (response.isSuccess()) {
                System.out.println(" success:" + response.getReturnCode() + response.getReturnMsg());
            } else {
                System.out.println(" fail:" + response.getReturnCode() + response.getReturnMsg());
            }
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }

}
