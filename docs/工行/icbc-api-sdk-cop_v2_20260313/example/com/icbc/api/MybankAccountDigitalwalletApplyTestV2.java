package com.icbc.api;

import com.icbc.api.request.MybankAccountDigitalwalletApplyRequestV2;
import com.icbc.api.response.MybankAccountDigitalwalletApplyResponseV2;

public interface MybankAccountDigitalwalletApplyTestV2 {

    public static void main(String[] args) {
        //1、密钥对认证方式，公钥在F-APIP平台登记，此处为私钥
        String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCfD0yRGoF2Df9oWEPXivEFzOe7doS53aShTAQy2JkCoI/5Ua4L9BlNd68PLm8nyPGXbKlbS+VM4EuxTaC6LUi091i5MXSSjI0hsNaGJKhyq4Ttd2ALLswexpwYD5D38t7dNwtRpQto2+OlERaqh24CbAe6sBqWi1N3frLN2wlLh9yaZ75PkxoZn1T1nYQUKmXXIiJj40UbOn0OnSX/I7IlcWFAu+iFbe66I5Iw9j4CVa+hT5OMH++ZMDxC6bfrktYdjGWGKJV+JlONidyuQhL9hgDwT1xzxUTBlgZbfQFF3GUGpSMpoXlGp/ACPTxwFtEcy0jH8Mh2wZG0hsfMgsvhAgMBAAECggEBAI7znQi8HaYQJn+M9Ev/X2arJYlhlx/Eh3U/cHaSG9I0o7CbLCUqGKN68SZfwR6shhRlYD/hEg/pdZ2/+rfYgwPBWUf3nqEnGKAy4lplD88fP7R8GSFzyZfPP/KP6uYsrQd7tPyHrJ1tIkbz+L8CQGiE1t198CdkyHsYr3I7bTsnTus3jOcsYRj55QWgvSUHU9S1nqLslLNm/4yMx04krIpe6U9EYeCqCSwEGMZEihNdRnt7cvALNl0uUbFMpjOFcTVNyUJjicTSE6fgiSpWYODEZoVO4PWCz4mP0I3tFA4msDU1ljPPgzL3aonS+NxStPJ0qMWNXq5ajEbXv/L6g5ECgYEA4ETufW/tuzgw+OUUG3pJAbpDGxjpth5gXppL7FC1u5rovmu73pVtn5wGNXVTVpRhE4ZijNIuGRw9NZgAYgxR4iTVuetONbePI6/A5b1GCJXo4gML+nu+H8B7iEcchIrBXxXKGO/Abu32ePNkJWFBnKaYjuFEF59K30ZDeduR6UMCgYEAtZB4DaT29YlcbTVrwSMi1+Y9bx25WxwYSzBfrFe+/Bdbm/kD7Zy1SHetww2aUGucfhg9ebK83xeW59zDOpOGQ/Mnk82zrG0pJxh6L9TOBmACwko793bWh8lZ8KRNs8+pGpPnzaqrBz23lARFaCWBWYFEArgJodJmL9NXbLOTwgsCgYAr4lEoinZiZCRxD6JXnh4bEG+BJebVlCwXVun0W/aztyRD3qDsdSD2UnmEjGi6UF6Oi4VNOPN8RmJT0mKAzRhYwAxyTPEI4dpIiWOdkOPbWjovv5jgcsZJQEjr6b5S0TP1fzYEmyV0j2vjTv0Rs247UP4ZN0sztB/agM1DUwvCMQKBgGVqmBSPA78tBeqef1z3glv0aHZrtQB2Vf+Ruq8qqCxp46rg0lfkQ8KBzxZ3v62SHmy0n4od07ANniZPQuGyS7oi1QWTTfxPUUBcRMv440feU0wxaQOWZsicKNfo/tokcYG+Ain4XeYCjEtqg1ZkNLdJJDMcl4zDWH74XwoB2NP3AoGAAxX+4pQi6SkVqZKfCkTs8JlZgHK6NsqNUVk7kpWolF6ucQ8VWr/6L4cNTsn1Tqrh8PXH+bZGmLAgf/qlRlzxENrNBsGikUJwF5cn9Tf6z3EiauOB//RemS3SZ2P6yJgZHodXyg1Y1mQTCtmhmXU8IywLT3MI9/9zsubfXAdHTpc=";
        //2、appid
        String APP_ID = "10000000000004097569";
        //3、网关公钥
        String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

        //4、创建与F-APIP外网网关的连接
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String)null, (String)null, (String)null, (String)null);
        MybankAccountDigitalwalletApplyRequestV2 request = new MybankAccountDigitalwalletApplyRequestV2();
        request.setServiceUrl("http://IP:PORT/api/mybank/account/digitalwallet/apply/V2");
        //5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值，非必输字段可不上送
        MybankAccountDigitalwalletApplyRequestV2.DigitalwalletApplyRequestV1Biz bizContent = new MybankAccountDigitalwalletApplyRequestV2.DigitalwalletApplyRequestV1Biz();
		bizContent.setCooperatorNo("PT00000000");
		bizContent.setAgentCooperatorNo("1122334455");
		bizContent.setSerialNo("1020201028500193711001");
		bizContent.setRelatedSerialNo("");
		bizContent.setOriginalSerialNo("");
		bizContent.setWorkDate("2021-01-19");
		bizContent.setWorkTime("12:12:12");
		bizContent.setMac("xx:xx:xx:xx:xx:xx");
		bizContent.setIP("xxxx:xxxx:xxxx:xxxx:xxxx:xxxx:xxxx:xxxx");
        bizContent.setCreditType("1");
        bizContent.setCompanyName("单位名称");
        bizContent.setCreditId("22345678");
        bizContent.setCompanyEnableDateE("9999-12-22");
        bizContent.setLpPhoneNo("13355554444");
        bizContent.setLpName("法人姓名");
        bizContent.setLpCreditType("1");
        bizContent.setLpIdNumber("法人证件号码");
        bizContent.setLpIdNumberEnableDateE("2029-12-22");
        bizContent.setApplyPhoneNo("13355556668");
        bizContent.setApplyName("申请人姓名");
        bizContent.setApplyCreditType("1");
        bizContent.setApplyIdNumber("申请人证件号码");
        bizContent.setApplyIdNumberEnableDateE("2029-12-22");
        bizContent.setPartnerCreditType("1");
        bizContent.setPartnerIdNumber("1");
        bizContent.setPartnerName("1");
        bizContent.setPartnerIdNumberEnableDateE("2020-12-22");
        bizContent.setLoginType("1");
        bizContent.setWalletSingleLimit("100000000");
        bizContent.setWalletDayLimit("100000000");
        bizContent.setCallBackUri("回调地址");
        bizContent.setMaterialsId("10000000000000");
        bizContent.setAccno("11");
        bizContent.setAccname("1212");
        bizContent.setIndustryForPrimary("1");
        bizContent.setEmpno("1");
        bizContent.setGrossrevn("1");
        bizContent.setTotalAssets("1");
        bizContent.setIndustryCode("1");
        bizContent.setWorkProvince("1");
        bizContent.setWorkCity("1");
        bizContent.setWorkCounty("1");
        bizContent.setWorkAddr("11");
        bizContent.setWorkAreacode("0200");
        bizContent.setRegProvince("1");
        bizContent.setRegCity("1");
        bizContent.setRegCounty("1");
        bizContent.setRegAddr("1");
        bizContent.setRegAreacode("1");
        bizContent.setNoTaxno("1");
        bizContent.setTaxno("1");
        bizContent.setLocalTaxno("1");
        bizContent.setAgreeResult("1");
		bizContent.setCooperatorType("2");
        bizContent.setBusinessInvestDes("");
        bizContent.setBusinessInvestType("1");
        bizContent.setLinkInvestDes("");
        bizContent.setLinkInvestType("1");
        bizContent.setAddressInvestDes("");
        bizContent.setAddressInvestType("1");
        bizContent.setManageFormInvestDes("");
        bizContent.setManageFormInvestType("1");
        bizContent.setSupportPaperInvestDes("");
        bizContent.setSupportPaperInvestType("1");
		bizContent.setInstCode("1");
        bizContent.setCorpSurveyResult("1");
        bizContent.setCompanyPhoneNo("18545236542");
        bizContent.setBenefitAddr1("dada");
        bizContent.setBenefitIdNumberEnableDateE1("2021-09-14");
        bizContent.setBenefitIdNumber1("d4456456");
        bizContent.setBenefitCreditType1("1");
        bizContent.setBenefitName1("张三");
        bizContent.setBenefitAddr2("dada");
        bizContent.setBenefitIdNumberEnableDateE2("2021-09-14");
        bizContent.setBenefitIdNumber2("d4456456");
        bizContent.setBenefitCreditType2("1");
        bizContent.setBenefitName2("李四");
        bizContent.setBenefitAddr3("dada");
        bizContent.setBenefitIdNumberEnableDateE3("2021-09-14");
        bizContent.setBenefitIdNumber3("d4456456");
        bizContent.setBenefitCreditType3("1");
        bizContent.setBenefitName3("王五");
        bizContent.setBenefitAddr4("dada");
        bizContent.setBenefitIdNumberEnableDateE4("2021-09-14");
        bizContent.setBenefitIdNumber4("d4456456");
        bizContent.setBenefitCreditType4("1");
        bizContent.setBenefitName4("赵六");
        bizContent.setVersion("2");
		
        request.setBizContent(bizContent);
        System.out.println("request:" + request);
        MybankAccountDigitalwalletApplyResponseV2 response;
        try {
        	response = client.execute(request, String.valueOf(System.currentTimeMillis()));//msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一
            if (response.isSuccess()) {
                //6、业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
				System.out.println("ReturnCode:"+response.getReturnCode());
                System.out.println("ResultCode:"+response.getResultCode());
                System.out.println("ApplyNo:"+response.getApplyNo());
				System.out.println("response1:" + response);
			}else {
				System.out.println("returnCode:"+response.getReturnCode());
				System.out.println("returnCode:"+response.getReturnMsg());
            }
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }

}
