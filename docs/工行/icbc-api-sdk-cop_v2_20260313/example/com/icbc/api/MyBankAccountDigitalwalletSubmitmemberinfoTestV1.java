package com.icbc.api;

import com.icbc.api.request.MyBankAccountDigitalwalletSubmitmemberinfoRequestV1;
import com.icbc.api.response.MyBankAccountDigitalwalletSubmitmemberinfoResponseV1;
import java.util.logging.Logger;

public interface MyBankAccountDigitalwalletSubmitmemberinfoTestV1 {
    static Logger logger = Logger.getLogger(MyBankAccountDigitalwalletSubmitmemberinfoTestV1.class);
    public static void main(String[] args) {
        //1、密钥对认证方式，公钥在F-APIP平台登记，此处为私钥
        String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----"
        //2、appid
        String APP_ID = "11100000000000005334";
        //3、网关公钥
        String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----"
        //4、创建与F-APIP外网网关的连接
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String)null, (String)null, (String)null, (String)null);
        MyBankAccountDigitalwalletSubmitmemberinfoRequestV1 request = new MyBankAccountDigitalwalletSubmitmemberinfoRequestV1();
        request.setServiceUrl("http://IP:PORT/api/mybank/account/digitalwallet/submitmemberinfo/V1");
        //5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值，非必输字段可不上送
        MyBankAccountDigitalwalletSubmitmemberinfoRequestV1.MyBankAccountDigitalwalletSubmitmemberinfoRequestV1Biz bizContent = new MyBankAccountDigitalwalletSubmitmemberinfoRequestV1.MyBankAccountDigitalwalletSubmitmemberinfoRequestV1Biz();
        bizContent.setPartnerType("0");
        bizContent.setPartnerAgreeCode("0");
        bizContent.setDepartmentName("0");
        bizContent.setPartnerName("0");
        bizContent.setProviderFlag("0");
        bizContent.setDcProviderCode("0");
        bizContent.setMemberCode("0");
        bizContent.setMemberKind("0");
        bizContent.setMemberType("0");
        bizContent.setMemberName("0");
        bizContent.setMemberSimpleName("0");
        bizContent.setCertificateType("0");
        bizContent.setCertificateId("0");
        bizContent.setZoneNo("0");
        bizContent.setBrNo("0");
        bizContent.setActZoneNo("0");
        bizContent.setMccCode("0");
        bizContent.setMemberProperty("0");
        bizContent.setMemberAddress("0");
        bizContent.setMemberAreaNo("0");
        bizContent.setTerminalNo("0");
        bizContent.setLinkMan("0");
        bizContent.setBussType("0");
        bizContent.setBussCode("0");
        bizContent.setNotes("0");
        bizContent.setMergeReceiptFlag("0");
        bizContent.setAccType("0");
        bizContent.setAccNo("0");
        bizContent.setAccName("0");
        bizContent.setRealName("0");
        bizContent.setOpenBankName("0");
        bizContent.setOpenBankNo("0");
        bizContent.setSettleCycle("0");
        bizContent.proposermobile("0");
        bizContent.proposerName("0");
        bizContent.proposerCertificateId("0");

        //System.out.println("request:" + request);
        MyBankAccountDigitalwalletSubmitmemberinfoResponseV1 response;
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
