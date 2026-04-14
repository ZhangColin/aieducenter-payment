package com.icbc.api;

import com.icbc.api.request.MybankPayComdrftBillinfoQuerystockbillRequestV1;
import com.icbc.api.response.MybankPayComdrftBillinfoQuerystockbillResponseV1;

public class MybankPayComdrftBillinfoQuerystockbillTestV1 {

    protected static String APP_ID = "12345678912345678912";
    protected static String APIGW_PUBLIC_KEY = "";
    protected static String PRI_KEY = "";

    public static void main (String[] args) throws Exception {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, PRI_KEY, APIGW_PUBLIC_KEY);
        try {
            MybankPayComdrftBillinfoQuerystockbillRequestV1.MybankPayComdrftBillinfoQuerystockbillRequestBizV1 bizContent = new MybankPayComdrftBillinfoQuerystockbillRequestV1.MybankPayComdrftBillinfoQuerystockbillRequestBizV1();
            MybankPayComdrftBillinfoQuerystockbillRequestV1 request = new MybankPayComdrftBillinfoQuerystockbillRequestV1();
            //请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
            bizContent.setHolderAcctId("12345678912345678912");
            request.setServiceUrl("https://IP:PORT/api/mybank/pay/comdrft/billinfo/querystockbill/V1");
            request.setBizContent(bizContent);
            //输出响应对象，需根据实际调用服务更换
            MybankPayComdrftBillinfoQuerystockbillResponseV1 response = client.execute(request);
            if (response.isSuccess()) {
                //业务成功处理
                System.out.println("success");
            } else {
                //失败
                System.out.println("error");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
