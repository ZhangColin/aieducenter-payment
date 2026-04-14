package com.icbc.api;

import com.icbc.api.request.MyBankAccountDigitalwalletSubmitProviderInfoRequestV1;
import com.icbc.api.response.MyBankAccountDigitalwalletSubmitProviderInfoResponseV1;
import java.util.logging.Logger;

public interface MyBankAccountDigitalwalletSubmitProviderInfoTestV1 {
    static Logger logger = Logger.getLogger(MyBankAccountDigitalwalletSubmitProviderInfoTestV1.class);
    public static void main(String[] args) {
        //1、密钥对认证方式，公钥在F-APIP平台登记，此处为私钥
        String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----"
        //2、appid
        String APP_ID = "11100000000000005336";
        //3、网关公钥
        String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----"
        //4、创建与F-APIP外网网关的连接
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String)null, (String)null, (String)null, (String)null);
        MyBankAccountDigitalwalletSubmitProviderInfoRequestV1 request = new MyBankAccountDigitalwalletSubmitProviderInfoRequestV1();
        request.setServiceUrl("http://IP:PORT/api/mybank/account/digitalwallet/submitproviderinfo/V1");
        //5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值，非必输字段可不上送
        MyBankAccountDigitalwalletSubmitProviderInfoRequestV1.MyBankAccountDigitalwalletSubmitProviderInfoRequestV1Biz bizContent = new MyBankAccountDigitalwalletSubmitProviderInfoRequestV1.MyBankAccountDigitalwalletSubmitProviderInfoRequestV1Biz();
        bizContent.setPartnerType("0");
        bizContent.setPartnerAgreeCode("7");
        bizContent.setDepartmentName("100000");
        bizContent.setPartnerName("1000");
        bizContent.setProviderKind("1");
        bizContent.setProviderProperty("1000");
        bizContent.setAgreeName("1");
        bizContent.setProviderName("珠海唐家湾1234567");
        bizContent.setCertificateType("0022000134981491");
        bizContent.setCertificateId("200000");
		bizContent.setProviderSettleFlag("1");
		bizContent.setAccType("1");
		bizContent.setAccno("1");
		bizContent.setAccname("1");
		bizContent.setRealName("1");
		bizContent.setSettleCycle("");
		bizContent.setNotes("");
        bizContent.setZoneno("1");
        bizContent.setBrno("1");
        //System.out.println("request:" + request);
        MyBankAccountDigitalwalletSubmitProviderInfoResponseV1 response;
        try {
        	response = client.execute(request, String.valueOf(System.currentTimeMillis()));//msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一
            if (response.isSuccess()) {
                //6、业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
//				log.i("ReturnCode:"+response.getReturnCode());
//				System.out.println("response1:" + response);
                logger.info("ReturnCode:" + response.getReturnCode());
			}else {
//				System.out.println("returnCode:"+response.getReturnCode());
//				System.out.println("returnCode:"+response.getReturnMsg());
            }
        } catch (IcbcApiException e) {
            logger.info("error :" + e.getErrMsg());
        }
    }

}
