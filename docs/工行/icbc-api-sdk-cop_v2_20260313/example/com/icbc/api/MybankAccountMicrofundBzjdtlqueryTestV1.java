package com.icbc.api;


import com.icbc.api.request.MybankAccountMicrofundBzjdtlqueryRequestV1;
import com.icbc.api.request.MybankAccountMicrofundBzjdtlqueryRequestV1.MybankAccountMicrofundBzjdtlqueryRequestV1Biz;
import com.icbc.api.response.MybankAccountMicrofundBzjdtlqueryResponseV1;


public class MybankAccountMicrofundBzjdtlqueryTestV1 {
    // 应用私钥
    protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCg53mKUSh/S2oP4nftjxqeYKBP0vnTVTmlXo61JUIJs5TIQulOj2yN7woNbXZLoabm2Ua5GpjwN1PoZePv3SOaHQWyadL3IpBCY4PjiEyx9gIrA8lKNzdFamMYOc2FaXNDxDjiirJrxVxTRyuCSUSDW2TEYRFQcTlSeRwx2dgVKYEIYtePUS7sfnFZXzj+hD2vrfUcecqv9IfkMA99kRcFsFiGpLVobj3fnJcGkKm7ED29bC6QtcEBY5yKqtiX/hKiKvjNWbctagsweoI6V9J9jNZVwFj7Ci7UCiT8G7vytTzqacMbS+Wy9G9Ql46Uin69L1h8AKJIO+KPtyTDv8qjAgMBAAECggEBAJO990uZ2aWIiYqsV5I5pCdoccrZ3aq+xn+ho0eRPmHKEwMO7UrFRtfMdV5+8+/eLnIpb1bnpSZspw7s/65vTxFjNKfto1ho1C0hUyjJLiL50GPOxjTvHkEOvo6wBJZLjRrcQZ9B2zI5tdyBrOGkhNRYRt5KgqtqS0f/DHR5on9KdX+AFbQB1H2zeRUmBeYv+J9fl95jCYHsszGxaCkvt69fLobZr+X02ZHwEnnLlNTC86JKOfc9uCHlwqjiSraI0Bh7H0OM5oJ6g9cYemLYTkXBOpJ60BPD7J0FoylJ147IkjCyzOtYckkGG5yGWKB8JXn9ak00TM20VfeO6DHw8YECgYEA5BBvmU+9vfRgR0EMQLpVfTy9yiTFCT++4fwvwCLwnB9Ta8begTgLHyan8o8IYG6UCnAIBDTL7ZsThYAO7JugnsNfVU/SNSGN/xHJG7XlbZmjPgeFe0cfZ9A36uYykmsSTymmSaUwdmoIyHLPAVGWuBiXaskXT8fbIdkWsvmUoMcCgYEAtJ0OlX4qYLQEVpL8Eb1RIXJ+q1m+hhASkmC7hTfPpZTs9qlG9TqCS/VKQICpX2tJUZHgMxC8FmEUpet7O/67fjYCd7qaDCgry4R4A3/xZwI0VMRwffBGh7Vl/+STMKPiOhWwoUnXPdyohJ4QegmemUjCP3hhNri7id5s2crg40UCgYBWYBc0NGxDZiuUsQEn/qpkHT4wMg7h+t+bjTy0g0cnigG5Pv+EZypnKOfQyPU+OIMfFOEkXq6hlesl06NTX3nMx2fy0qFleAjkguLs+M4k3VxjIMwgLYaqdcTH1Vlv9NdhXf/ZwpOBNZWP/VODR1hS7xVh3zlSAPpwblzXegkdfwKBgCuHisNG1AnYeQ8wf6X63bnU8Wh0ybFf4En9HFTAHcCHgxDzvVFPF6Yc6RdgqvF/Vv1czg8gomQ2Y5aViW7tNtz1QWT/yS7S47M6Kv6U8GZmJpyjd4UyypzcgEfLHBpl9RkQrzBALqsPKmJEAi6XcOz6ly5a4fshTLmurMdU2RyNAoGBALeuLbireyqPkfiN4sseZOPMv2Ez8ZChM9qhakOGgzKM3lHWbiT3GiWGUKdNfe3GSKB+B8Z2AOUyKCxqnwYLcQrjwFDTRCOOFljGaslvUAO79f/NIwaSHsOr7S3N0x032uDZsuUxoekP3aVGFp0T77lcdgzJ/+RRmwSulG3SfKHE";
    // 网关公钥
    protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
    // 应用ID，暂定，APP申请分配
    protected static final String APP_ID = "10000000000004097551";


    public static void main(String[] args){

        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

        MybankAccountMicrofundBzjdtlqueryRequestV1 request = new MybankAccountMicrofundBzjdtlqueryRequestV1();
        request.setServiceUrl("http://IP:port/api/mybank/account/microfund/bzjdtlquery/V1");

        MybankAccountMicrofundBzjdtlqueryRequestV1Biz bizContent = new MybankAccountMicrofundBzjdtlqueryRequestV1Biz();
        MybankAccountMicrofundBzjdtlqueryRequestV1.QueryConditionComm queryConditionComm = new MybankAccountMicrofundBzjdtlqueryRequestV1.QueryConditionComm();
        queryConditionComm.setQueryType("001");
        queryConditionComm.setMarketNo("0000502");
        queryConditionComm.setMemno("00223344559");
        queryConditionComm.setBeginBusiDate("2017-07-25");
        queryConditionComm.setEndBusiDate("2017-07-25");
        queryConditionComm.setBegNum("1");
        queryConditionComm.setFetchNum("50");
        bizContent.setQueryConditionComm(queryConditionComm);


        request.setBizContent(bizContent);

        try {
            MybankAccountMicrofundBzjdtlqueryResponseV1 response = client.execute(request);
        } catch (IcbcApiException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }//请求通讯唯一编号
    }
}
