package com.icbc.api;

import com.icbc.api.request.MybankAccountDigitalwalletBindingaccountqueryRequestV1;
import com.icbc.api.response.MybankAccountDigitalwalletBindingaccountqueryResponseV1;

import java.util.Random;

public class AccountBindingQueryTest {
	protected static final String MY_PRIVATE_KEY = "xxx";
	protected static final String APIGW_PUBLIC_KEY = "xxx";
	protected static final String APP_ID = "xxx";

    public static void main(String[] args) {
    	DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        MybankAccountDigitalwalletBindingaccountqueryRequestV1 request = new MybankAccountDigitalwalletBindingaccountqueryRequestV1();

        MybankAccountDigitalwalletBindingaccountqueryRequestV1.MybankAccountDigitalwalletBindingaccountqueryV1Biz bizContent= new MybankAccountDigitalwalletBindingaccountqueryRequestV1.MybankAccountDigitalwalletBindingaccountqueryV1Biz();
        request.setServiceUrl("http://IP:PORT/api/mybank/account/digitalwallet/bindingaccountquery/V1");
        
        bizContent.setWallet_id("xxx");
        bizContent.setCooperator_no("xxx");
        bizContent.setAgent_cooperator_no("xxx");
        bizContent.setWork_date("2020-12-12");
        bizContent.setWork_time("12:12:12");
        bizContent.setMac("xxx");
        bizContent.setIP("xxx.xxx.xxx.xxx");
        bizContent.setSerial_no("xxx");
        bizContent.setIsSupportSubWallet("true");
        request.setBizContent(bizContent);
        request.setNeedEncrypt(true);
        MybankAccountDigitalwalletBindingaccountqueryResponseV1 response;
        
    	Random rand = new Random();
		String msgId = rand.nextInt(99999) + "msg";
		System.out.println(request.getBizContent());
        try {
            response = client.execute(request,msgId);
            System.out.println("bing_list:" + response.getBind_list());
            if (response.isSuccess()) {
                System.out.println("ReturnCode:"+response.getReturnCode());
                System.out.println("response:" + response.getMsgId());
                System.out.println("response:" + response.getReturnMsg());
            } else {
                System.out.println("ReturnCode:"+response.getReturnCode());
                System.out.println("ReturnMsg:"+response.getReturnMsg());
            }
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }
    }
    
