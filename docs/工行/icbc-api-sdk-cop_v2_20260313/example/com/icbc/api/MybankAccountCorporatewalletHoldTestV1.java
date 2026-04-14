package com.icbc.api;

import com.icbc.api.request.MybankAccountCorporatewalletHoldRequestV1;
import com.icbc.api.response.MybankAccountCorporatewalletHoldResponseV1;

public interface MybankAccountCorporatewalletHoldTestV1 {

    public static void main(String[] args) {
        //1銆佸瘑閽ュ璁よ瘉鏂瑰紡锛屽叕閽ュ湪F-APIP骞冲彴鐧昏锛屾澶勪负绉侀挜
        String MY_PRIVATE_KEY="xxx";
        //2銆乤ppid
        String APP_ID = "xxx";
        //3銆佺綉鍏冲叕閽�
        String APIGW_PUBLIC_KEY ="xxx";
        //4銆佸垱寤轰笌F-APIP澶栫綉缃戝叧鐨勮繛鎺�
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String)null, (String)null, (String)null, (String)null);
        MybankAccountCorporatewalletHoldRequestV1 request = new MybankAccountCorporatewalletHoldRequestV1();
        request.setServiceUrl("http://IP:PORT/api/mybank/account/corporatewallet/hold/V1");
        //5銆佽瀵圭収鎺ュ彛鏂囨。鐢╞izContent.setxxx()鏂规硶瀵逛笟鍔′笂閫佹暟鎹繘琛岃祴鍊硷紝闈炲繀杈撳瓧娈靛彲涓嶄笂閫�
        MybankAccountCorporatewalletHoldRequestV1.orate bizContent = new MybankAccountCorporatewalletHoldRequestV1.CorporatewalletHoldRequestV1Biz();
        bizContent.setBus_serialno("**20000002012****7891200001");
        bizContent.setWork_date("2020-12-12");
        bizContent.setWork_time("12:12:12");
        bizContent.setMac("xx:xx:xx:xx:xx:xx");
        bizContent.setIp("xxxx:xxxx:xxxx:xxxx:xxxx:xxxx:xxxx:xxxx");
        bizContent.setAgrno("");
        bizContent.setAgr_no("BOB******1");
        bizContent.setWalletid("0022*********1491");
        bizContent.setCurrtype(1);
        bizContent.setHoldtype(1);
        bizContent.setHoldceil(5*0);
        bizContent.setHoldlife("9999-12-30");
        bizContent.setApp_id("100********11001");
        bizContent.setNotes("0022*********1491");
		request.setBizContent(bizContent);

        request.setBizContent(bizContent);
        System.out.println("request:" + request);
        MybankAccountCorporatewalletHoldResponseV1 response;
        try {
        	response = client.execute(request, String.valueOf(System.currentTimeMillis()));//msgId娑堟伅閫氳鍞竴缂栧彿锛岃姹傛瘡娆¤皟鐢ㄧ嫭绔嬬敓鎴愶紝APP绾у敮涓�
            if (response.isSuccess()) {
             	//6銆佷笟鍔℃垚鍔熷鐞嗭紝璇锋牴鎹帴鍙ｆ枃妗ｇ敤response.getxxx()鑾峰彇鍚屾杩斿洖鐨勪笟鍔℃暟鎹�
				System.out.println("ReturnCode:"+response.getReturnCode());
				System.out.println("response1:" + response);
			}else {
				System.out.println("returnCode:"+response.getReturnCode());
				System.out.println("response2:"+response.getWalletId());	
            }
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }

}
