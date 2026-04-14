package com.icbc.api;

import com.icbc.api.request.MybankAccountCorporatewalletCoopapplyRequestV1;
import com.icbc.api.response.MybankAccountCorporatewalletCoopapplyResponseV1;

public class MybankAccountCorporatewalletCoopapplyTestV1 {

    public static void main(String[] args) throws Exception{
        //1、密钥对认证方式，公钥在F-APIP平台登记，此处为私钥
        String MY_PRIVATE_KEY = "xxx";
        //2、appid
        String APP_ID = "xxx";
        //3、网关公钥
        String APIGW_PUBLIC_KEY = "xxx";
        String AESKey = "xxx";//调用方加密秘钥
        String MSG_ID = String.valueOf(System.currentTimeMillis());
        //4、创建与F-APIP外网网关的连接
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, IcbcConstants.ENCRYPT_TYPE_AES, AESKey, null, null);

        MybankAccountCorporatewalletCoopapplyRequestV1 request = new MybankAccountCorporatewalletCoopapplyRequestV1();
        request.setServiceUrl("http://IP:PORT/api/mybank/account/corporatewallet/coopapply/V1");

        //5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值，非必输字段可不上送
        MybankAccountCorporatewalletCoopapplyRequestV1.MybankAccountCorporatewalletCoopapplyRequestV1Biz bizContent = new MybankAccountCorporatewalletCoopapplyRequestV1.MybankAccountCorporatewalletCoopapplyRequestV1Biz();

        bizContent.setAgr_no("BOB01");
        bizContent.setBus_serialno("11xxxxxxxxxxxxxxxxxxxxxxx63");
        bizContent.setWork_date("2020-12-12");
        bizContent.setWork_time("12:12:12");
        bizContent.setMac("00:xx:00:xx:89:4A");
        bizContent.setIp("122.xx.xx.240");
        bizContent.setCredittype("101");
        bizContent.setCreditid("C02Pxxxxxxxxxxxxxxxx");
        bizContent.setCompanyenabledates("2000-01-01");
        bizContent.setCompanyenabledatee("2321-01-01");
        bizContent.setCompanyname("XXXXX");
        bizContent.setCompanyphoneno("xxxxxxxxxxx");
        bizContent.setCompanyaddress("XXXXXXXXXX");
        bizContent.setTaxno("XXXXXXXXXX");
        bizContent.setTaxnoenddate("2321-01-01");
        bizContent.setLocaltaxno("XXXXXXXXXXXX");
        bizContent.setLocaltaxnoenddate("2321-01-01");
        bizContent.setInstcode("XXXXXXXXXXXXX");
        bizContent.setIndustrycode("A0000-A");
        bizContent.setEmpno("5");
        bizContent.setGrossrevn("11");
        bizContent.setTotalassets("5");
        bizContent.setIndustryforprimary("xxxxxxxxxxx");
        bizContent.setLpname("关XX");
        bizContent.setLpcredittype("000");
        bizContent.setLpidnumber("xxxxxxxxxxxxxxxxxx");
        bizContent.setLpidnumdates("2000-01-01");
        bizContent.setLpidnumdatee("2021-01-01");
        bizContent.setLpidinvidflag("0");
        bizContent.setLpphoneno("xxxxxxxxxxx");
        bizContent.setApplyname("关XX");
        bizContent.setApplycredittype("000");
        bizContent.setApplyidnumber("xxxxxxxxxxxxxxxxxx");
        bizContent.setApplyidnumdates("2000-01-01");
        bizContent.setApplyidnumdatee("2021-01-01");
        bizContent.setApplyidinvidflag("0");
        bizContent.setApplyphoneno("xxxxxxxxxxx");
        bizContent.setPartnername("关XX");
        bizContent.setPartneridtype("000");
        bizContent.setPartneridnumber("xxxxxxxxxxxx");
        bizContent.setPartnernumdates("2000-01-01");
        bizContent.setPartnernumdatee("2021-01-01");
        bizContent.setPartnerinvidflag("0");
        bizContent.setPartnercontactno("xxxxxxxxxxx");
        bizContent.setBenefitname("古XX");
        bizContent.setBenefitcredittype("000");
        bizContent.setBenefitidnumber("xxxxxxxxxxxxxxxxxx");
        bizContent.setBenefitidnumdates("2000-01-01");
        bizContent.setBenefitidnumdatee("2021-01-01");
        bizContent.setBenefitinvidflag("0");
        bizContent.setBenefitaddr("XXXXXXXXXXXXXX");
        bizContent.setWorkprovince("XX省");
        bizContent.setWorkcity("XX市");
        bizContent.setWorkcounty("XX区");
        bizContent.setWorkaddr("XX省XX市XX区XX镇XX村XXXX号");
        bizContent.setWorkareacode("110000");
        bizContent.setRegprovince("XX省");
        bizContent.setRegcity("XX市");
        bizContent.setRegcounty("XX区");
        bizContent.setRegaddr("XX省XX市XX区XX镇XX村XXXX号");
        bizContent.setRegareacode("110000");
        bizContent.setWalletsinglelimit("500000");
        bizContent.setWalletdaylimit("1000000");
        bizContent.setZoneno("200");
        bizContent.setBrno("620");
        bizContent.setBusinessarea("0");
        bizContent.setEmployertype("2");
        bizContent.setCall_back_uri("https://xxxxxx");
        bizContent.setMaterials_id("xxxxxx");

        request.setBizContent(bizContent);


        System.out.println("request:" + request);
        MybankAccountCorporatewalletCoopapplyResponseV1 response;
        try {
            response = client.execute(request, MSG_ID); //msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一
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
