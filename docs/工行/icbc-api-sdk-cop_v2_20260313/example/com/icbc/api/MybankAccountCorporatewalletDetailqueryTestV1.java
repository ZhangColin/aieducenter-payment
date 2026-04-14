package com.icbc.api;

import java.util.Random;

import com.icbc.api.request.MybankAccountCorporatewalletDetailqueryRequestV1;
import com.icbc.api.response.MybankAccountCorporatewalletDetailqueryResponseV1;

public class MybankAccountCorporatewalletDetailqueryTestV1 {


    protected static final String MY_PRIVATE_KEY = "xxx";
    protected static final String APIGW_PUBLIC_KEY = "xxx";
    protected static final String APP_ID = "xxx";

    public static void main(String[] args) {

        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        MybankAccountCorporatewalletDetailqueryRequestV1 request = new MybankAccountCorporatewalletDetailqueryRequestV1();
        request.setServiceUrl("http://IP:PORT/api/mybank/account/corporatewallet/detailquery/V1");
        MybankAccountCorporatewalletDetailqueryRequestV1.MybankAccountCorporatewalletDetailqueryRequestV1Biz bizContent = new MybankAccountCorporatewalletDetailqueryRequestV1.MybankAccountCorporatewalletDetailqueryRequestV1Biz();


        Random rand = new Random();
        String msgId = rand.nextInt(99999) + "msg";

        bizContent.setAgr_no("xxx");
        bizContent.setBus_serialno("xxx");
        bizContent.setWork_date("2020-12-12");
        bizContent.setWork_time("12:12:12");
        bizContent.setMac("xx:xx:xx:xx:xx:xx");
        bizContent.setIp("xxxx:xxxx:xxxx:xxxx:xxxx:xxxx:xxxx:xxxx");
        bizContent.setWallet_id("xxx");
        bizContent.setStrart_date("2000-12-22");
        bizContent.setStrart_time("20:56:00");
        bizContent.setEnd_date("3020-12-22");
        bizContent.setEnd_time("20:57:00");
        bizContent.setLoan_sign("1");
        bizContent.setPage_size(10);
        bizContent.setPage_num(1);

        request.setBizContent(bizContent);

        MybankAccountCorporatewalletDetailqueryResponseV1 response;
        try {
            response = client.execute(request,msgId);
            if (response.isSuccess()) {
                System.out.println("ReturnCode:"+response.getReturnCode());
                System.out.println("ReturnMsg:"+response.getReturnMsg());
                System.out.println("response:" + response);
            } else {
                // ʧ��
                System.out.println("ReturnCode:"+response.getReturnCode());
                System.out.println("ReturnMsg:"+response.getReturnMsg());
            }
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }
}
