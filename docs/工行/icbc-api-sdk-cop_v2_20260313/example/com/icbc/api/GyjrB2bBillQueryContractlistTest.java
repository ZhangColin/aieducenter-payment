package com.icbc.api;

import com.icbc.api.request.GyjrB2bBillQueryContractListRequestV1;
import com.icbc.api.response.GyjrB2bBillQueryContractListResponseV1;

public class GyjrB2bBillQueryContractlistTest {
    protected static String APP_ID = "10000000000004097890";
    protected static String APIGW_PUBLIC_KEY = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
    protected static String MY_PRIVATE_KEY = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
    protected static String BASE_URL = "http://ip:port/api";

    public static void main(String[] args) throws Exception {
        String URI = "/gyjr/b2b/bill/querycontractlist/V1";//api地址
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        try {
            GyjrB2bBillQueryContractListRequestV1.TransInfo transInfo = new GyjrB2bBillQueryContractListRequestV1.TransInfo();
            GyjrB2bBillQueryContractListRequestV1.RequestV1Biz bizContent = new GyjrB2bBillQueryContractListRequestV1.RequestV1Biz();
            GyjrB2bBillQueryContractListRequestV1 request = new GyjrB2bBillQueryContractListRequestV1();
            //请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
            transInfo.setAppid("10000000000004097890");
            transInfo.setEventNo(String.valueOf(System.currentTimeMillis()));
            transInfo.setProtocolCode("GYJR20260701105640610539511");
            transInfo.setPlatVendorId("3031");
            transInfo.setUkeyId("wangwxfgmmh.c.0200");
            transInfo.setAccountNo("0200003309004709145");
            transInfo.setConfirmStatus("1");
            transInfo.setPageIndex("1");
            transInfo.setPageSize("10");
            bizContent.setTransInfo(transInfo);
            request.setBizContent(bizContent);
            request.setServiceUrl(BASE_URL + "/" + URI);
            GyjrB2bBillQueryContractListResponseV1 response = client.execute(request);
            if (response.isSuccess()) {
                //业务成功处理
                System.out.println(response.getReturnCode());//
            } else {
                //失败
                System.out.println("error");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
