package com.icbc.api;

import com.icbc.api.request.IsfcsdsDatafabricBusRequestV1;
import com.icbc.api.response.IsfcsdsDatafabricBusResponseV1;

public class IsfcsdsDatafabricBusTestV1 {
    //1、网关公钥
    protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

    //2、appid
    protected static final String APP_ID = "10000000000004096751";
    //http://gw.inner.icbc.com.cn
    protected static final String URL = "/url";

    //3、密钥对认证方式，公钥在API平台登记，此处为私钥
    protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCgTopO/LHK5nTyEpAVYT2cqrrFuBPGH3SLafoSjZsprYqfkIMAGVm2wR1Fx/vLQJ0EqJv68jZNu27H4864Ws3g2Ittt5WO3oV1xiWIc/UmWIbfzpdXl4t4gAi+s09NtwD2U1qi87Pw9swzZmiknq0x6dWVT6At9wg9Gy9g6ol6OwRgJxx1nITK1i0IWLyRCSQQ8FeZ8WwjmTKfjRSKZ6thwiNZjT3ljoTH07Ioq8AFK+m67yZbth6WH20tnLnn1mNb2EKgKp5pFHqLt1p0NFjGM66zw2LmVtYbX/LVqAtFcfXAbMlQrFoDK8QhGJ7qsm0YOsZG+UgmGim1UqHuEOuxAgMBAAECggEAK2NWl+5ZCLu7Z1GS4daB4IdtKdqar4MP/jPe1WqyVmN8I5fvfPVfjNFT5PEtu2NvnDUukotAG2XhpASKjOc21eaGRlnsBtE/ehL5Uadg8NIBH05BNBn2S9ULiOHrUvN3NTR2Uah4SX3i4v6S0AuKgPnzLwhwKEC2YCOdfTO9pfEpLbB0S3BEQSB3zIzK2g5s6lkDwD9eWeFk3HzrO8e0u4Y2YF5c5YY7t9DQjum+pQKIQlwHbEeDIT7SUsol4MOhj1O8Nwv4KTKMMaWLohyJXioFKtw/s1uFwIwTwAUb2daPBHEtHdYMAvh6QtdRHAZinKnw0Gsg1UPcNxq+85MggQKBgQDd9PqjSEPvZuH21EP/Q0fMZmBXM5tZEkvtZ3HJOaj4BMbXwPAA/fGlXIRM6c4EF7ahcs74yk/iMNFqvQrHS35AflZWTGFhtvnkLtDmTgUpOIWhV3p4CMbD/KNQuEZgvVwYdcRyHJnusjGyE2uz7QiiEaReTX2aKlmLhtdHHtzeqQKBgQC45OamcDeYhD41CL8qI8vr9holYsxwg1l4Krd0o7AsomqKdG+r3KDAY5h5qCGDt6M3aXXLIqHoVMQZy/o5znXVStPvHamOIn4I3as75aQCHEefFcbun6FuZi3dX1MeyNKgX3dVvt1tD/d72hm89hunl//Tv+2Pq3+1fX/Mj2HxyQKBgHJiQ6BOJ6SCksTPU1ODasbto4yM7ewTP8nS25LmAiv+2jJqHcWFGTks2omAnEkDp3zAW+Ve7ZgVrHajgaB/SUsXUIscxbrDvzhW4BUv8/zwQf2JxXWCoFx0H/4DWVGVhJaeamScVL/sWSv6UjDhYOeUy+5FZQtD8+2xydO/FfgxAoGARBQshU4+ek7/JMHyuY5ysJBuciOdyTPkBqYb5Lu8y8KF3jMfRzW2jwBdDTaGGJzOYUHYmJJI8WX8ys7bdNt7cazaumm+m9JBe/w655QXvpsxF/eJxyVJ6H+BFcjarzQcseZU52Ke32CIrLbS0UwBRMVn8kzyKPtpy6bJHsYQt1kCgYEArruJ+Sr0i/VT3B1w1mKZN7MkpmKxqWAL/SeUQ5ARbCk4xMQJ8GXss1hEN3F8dIqMpwjRjT9MFfee0LGW/5MkidVGHq8SS4+osYXoWucNiKnIDR/blLDO/CuRkMVsKwVC6vtFuW0KAluZB4jZ/5uIi5d3BhbH2uHEzUulAFFhbdA=";
    public static void main(String[] args) throws IcbcApiException{
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY,APIGW_PUBLIC_KEY);
        IsfcsdsDatafabricBusRequestV1 request = new IsfcsdsDatafabricBusRequestV1();
        request.setServiceUrl(URL + "/api/isfcsds/datafabric/bus/V1");
        IsfcsdsDatafabricBusRequestV1.IsfcsdsDatafabricBusBizContent bizContent = new IsfcsdsDatafabricBusRequestV1.IsfcsdsDatafabricBusBizContent();

        //公共参数
        bizContent.setAppId("11");
        bizContent.setBusinessType("3");
        bizContent.setMsgContent("3");
        bizContent.setOrgId("4");
        bizContent.setTopic("55");
        bizContent.setIdNo("412829200005123265");
        bizContent.setIdType("0");
        bizContent.setBizId("11365");
        bizContent.setBizScene("种植类");
        bizContent.setTranInfo("abcd");
        bizContent.setOriginTopic("ISFC_SDS_WRITEOFFMONITOR");
        bizContent.setOriginMsg("{\"aa\": \"85216\"}");


        System.out.println("-------普惠业务分行外部数据接入入参--------"+bizContent);


        request.setBizContent(bizContent);
        System.out.println("-------bizContent--------"+bizContent);
        IsfcsdsDatafabricBusResponseV1 response=new IsfcsdsDatafabricBusResponseV1();
        try{
            response = client.execute(request);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        if(response.isSuccess()){
            System.out.println("success!");
        }else{
            System.out.println("fail"+response.getReturnMsg());
        }
    }
}
