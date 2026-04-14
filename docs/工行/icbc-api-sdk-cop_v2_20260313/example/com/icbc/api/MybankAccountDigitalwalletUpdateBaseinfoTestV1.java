package com.icbc.api;

public interface MybankAccountDigitalwalletUpdateBaseinfoTestV1 {

    public static void main(String[] args) {
        //1、密钥对认证方式，公钥在F-APIP平台登记，此处为私钥
        String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCfD0yRGoF2Df9oWEPXivEFzOe7doS53aShTAQy2JkCoI/5Ua4L9BlNd68PLm8nyPGXbKlbS+VM4EuxTaC6LUi091i5MXSSjI0hsNaGJKhyq4Ttd2ALLswexpwYD5D38t7dNwtRpQto2+OlERaqh24CbAe6sBqWi1N3frLN2wlLh9yaZ75PkxoZn1T1nYQUKmXXIiJj40UbOn0OnSX/I7IlcWFAu+iFbe66I5Iw9j4CVa+hT5OMH++ZMDxC6bfrktYdjGWGKJV+JlONidyuQhL9hgDwT1xzxUTBlgZbfQFF3GUGpSMpoXlGp/ACPTxwFtEcy0jH8Mh2wZG0hsfMgsvhAgMBAAECggEBAI7znQi8HaYQJn+M9Ev/X2arJYlhlx/Eh3U/cHaSG9I0o7CbLCUqGKN68SZfwR6shhRlYD/hEg/pdZ2/+rfYgwPBWUf3nqEnGKAy4lplD88fP7R8GSFzyZfPP/KP6uYsrQd7tPyHrJ1tIkbz+L8CQGiE1t198CdkyHsYr3I7bTsnTus3jOcsYRj55QWgvSUHU9S1nqLslLNm/4yMx04krIpe6U9EYeCqCSwEGMZEihNdRnt7cvALNl0uUbFMpjOFcTVNyUJjicTSE6fgiSpWYODEZoVO4PWCz4mP0I3tFA4msDU1ljPPgzL3aonS+NxStPJ0qMWNXq5ajEbXv/L6g5ECgYEA4ETufW/tuzgw+OUUG3pJAbpDGxjpth5gXppL7FC1u5rovmu73pVtn5wGNXVTVpRhE4ZijNIuGRw9NZgAYgxR4iTVuetONbePI6/A5b1GCJXo4gML+nu+H8B7iEcchIrBXxXKGO/Abu32ePNkJWFBnKaYjuFEF59K30ZDeduR6UMCgYEAtZB4DaT29YlcbTVrwSMi1+Y9bx25WxwYSzBfrFe+/Bdbm/kD7Zy1SHetww2aUGucfhg9ebK83xeW59zDOpOGQ/Mnk82zrG0pJxh6L9TOBmACwko793bWh8lZ8KRNs8+pGpPnzaqrBz23lARFaCWBWYFEArgJodJmL9NXbLOTwgsCgYAr4lEoinZiZCRxD6JXnh4bEG+BJebVlCwXVun0W/aztyRD3qDsdSD2UnmEjGi6UF6Oi4VNOPN8RmJT0mKAzRhYwAxyTPEI4dpIiWOdkOPbWjovv5jgcsZJQEjr6b5S0TP1fzYEmyV0j2vjTv0Rs247UP4ZN0sztB/agM1DUwvCMQKBgGVqmBSPA78tBeqef1z3glv0aHZrtQB2Vf+Ruq8qqCxp46rg0lfkQ8KBzxZ3v62SHmy0n4od07ANniZPQuGyS7oi1QWTTfxPUUBcRMv440feU0wxaQOWZsicKNfo/tokcYG+Ain4XeYCjEtqg1ZkNLdJJDMcl4zDWH74XwoB2NP3AoGAAxX+4pQi6SkVqZKfCkTs8JlZgHK6NsqNUVk7kpWolF6ucQ8VWr/6L4cNTsn1Tqrh8PXH+bZGmLAgf/qlRlzxENrNBsGikUJwF5cn9Tf6z3EiauOB//RemS3SZ2P6yJgZHodXyg1Y1mQTCtmhmXU8IywLT3MI9/9zsubfXAdHTpc=";
        //2、appid
        String APP_ID = "10000000000004097569";
        //3、网关公钥
        String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

        //4、创建与F-APIP外网网关的连接
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String)null, (String)null, (String)null, (String)null);
        com.icbc.api.request.MybankAccountDigitalwalletUpdatebaseinfoRequestV1 request = new com.icbc.api.request.MybankAccountDigitalwalletUpdatebaseinfoRequestV1();
        request.setServiceUrl("http://IP:PORT/api/mybank/account/digitalwallet/updatebaseinfo/V1");
        //5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值，非必输字段可不上送
        com.icbc.api.request.MybankAccountDigitalwalletUpdatebaseinfoRequestV1.DigitalwalletUpdateBaseinfoRequestV1Biz bizContent = new com.icbc.api.request.MybankAccountDigitalwalletUpdatebaseinfoRequestV1.DigitalwalletUpdateBaseinfoRequestV1Biz();
		bizContent.setCooperatorNo("PT00000000");
		bizContent.setAgentCooperatorNo("1122334455");
        bizContent.setCooperatorType("2");
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
        bizContent.setWalletId("0022000134981491");
        bizContent.setWalletClassNew("1");
        bizContent.setWalletClassOld("2");
        bizContent.setOperFlag("3");
        bizContent.setMaterialsId("test.zip");
        bizContent.setCompanyEnableDateE("9999-12-22");
        bizContent.setLpName("法人姓名");
        bizContent.setLpCreditType("1");
        bizContent.setLpIdNumber("法人证件号码");
        bizContent.setLpIdNumberEnableDateE("9999-12-22");
        bizContent.setApplyName("授权办理人姓名");
        bizContent.setApplyCreditType("1");
        bizContent.setApplyIdNumber("授权办理人身份证件号码");
        bizContent.setApplyIdNumberEnableDateE("9999-12-22");
        bizContent.setCallBackUri("回调地址");

		
        request.setBizContent(bizContent);
        System.out.println("request:" + request);
        com.icbc.api.response.MybankAccountDigitalwalletUpdatebaseinfoResponseV1 response;
        try {
        	response = client.execute(request, String.valueOf(System.currentTimeMillis()));//msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一
            if (response.isSuccess()) {
                //6、业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
				System.out.println("ReturnCode:"+response.getReturnCode());
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
