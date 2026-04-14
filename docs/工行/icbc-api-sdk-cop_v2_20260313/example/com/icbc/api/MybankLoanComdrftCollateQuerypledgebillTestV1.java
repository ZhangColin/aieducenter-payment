package com.icbc.api;

import com.icbc.api.request.MybankLoanComdrftCollateQuerypledgebillRequestV1;
import com.icbc.api.response.MybankLoanComdrftCollateQuerypledgebillResponseV1;

public class MybankLoanComdrftCollateQuerypledgebillTestV1 {

    public static void main(String[] args) {
        //1、密钥对认证方式，公钥在F-APIP平台登记，此处为私钥
        String PRI_KEY = "xxx";
        //2、appid
        String APP_ID = "xxx";
        //3、网关公钥
        String APIGW_PUBLIC_KEY = "xxx";
        //4、创建与F-APIP外网网关的连接
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, PRI_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String) null, (String) null, (String) null, (String) null);

        MybankLoanComdrftCollateQuerypledgebillRequestV1 request = new MybankLoanComdrftCollateQuerypledgebillRequestV1();
        request.setServiceUrl("https://ip:port/api/mybank/loan/comdrft/collate/querypledgebill/V1");

        //5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值，非必输字段可不上送
        MybankLoanComdrftCollateQuerypledgebillRequestV1.MybankLoanComdrftCollateQuerypledgebillRequestBizV1 bizContent = new MybankLoanComdrftCollateQuerypledgebillRequestV1.MybankLoanComdrftCollateQuerypledgebillRequestBizV1();
        bizContent.setAccptrClass("");
        bizContent.setCdTp("AC01");
        bizContent.setDueDateBgn("20221212");
        bizContent.setDueDateEnd("20231212");
        bizContent.setGroupId("1XXXXXXX1");
        bizContent.setPackAmtBgn("0");
        bizContent.setPackAmtEnd("100000");
        bizContent.setHolderAcctId("02xxxxxxxxxxxxxxx71");
        bizContent.setPageIndex("1");
        bizContent.setPageSize("10");
        request.setBizContent(bizContent);

        System.out.println("request:" + request);
        MybankLoanComdrftCollateQuerypledgebillResponseV1 response;
        try {
            response = client.execute(request, String.valueOf(System.currentTimeMillis())); //msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一
            if (response.isSuccess()) {
                //6、业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
                System.out.println("ReturnCode:" + response.getReturnCode());
                System.out.println("response1:" + response.getReturnMsg());
            } else {
                System.out.println("returnCode:" + response.getReturnCode());
                System.out.println("response2:" + response.getReturnMsg());
            }
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }

}
