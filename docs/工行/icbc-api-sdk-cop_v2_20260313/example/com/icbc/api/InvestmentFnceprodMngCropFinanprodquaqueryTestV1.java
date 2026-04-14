package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.InvestmentFnceprodMngCropFinanprodquaqueryRequestV1;
import com.icbc.api.response.InvestmentFnceprodMngCropFinanprodquaqueryResponseV1;


public class InvestmentFnceprodMngCropFinanprodquaqueryTestV1 {

    // 应用私钥
    protected static final String	MY_PRIVATE_KEY	= "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCAFu7nKzBvGhe7m8G6XcoetCjaO6za9/hIA3lpsWNn8QtT34VxoVLKyh69hhCORK2dA84jRttix0TV1ntXHQAC5ecDHsRy1ctdll6qo793CcYHcAcg5KNMCZbQugSajUVNWICcm02+AlfoxRhXXNrrQQLDZbBwD1ivoDODPufg9j1c3phfDv4+nfdmt9vNNJf5oOqufsWcL66r8Zh7nJcZUNaJZsL/2cI0u6rPe3YVpqnaTYzUdpROPzxxavcpPPRgPLfn/X2O/O9V9rOUfdpee3+uJ5z9oXXN2h3DBkqrMubeRjQJoyImLZTYZSmQmhWI3pp6klNzq+dyehRZRuDxAgMBAAECggEAGWRyd+FX+K5zhf2aqhdtuih88MZjvgTqc1w89Xb0/eYLGNV23s/cWdc3wNqS7aEvybC3bMbfNvXJ75pVWS+mkOyCn1u6FgnMBwGIQsuzaSXG3DKZNywcMzazby1xPDJFStAArwsanlBYnQx1htwhigGZSqMeFxjzu91GWP5feXkPZTWiuxuf8dPxpym/2LfgtoPoRPDNmlGN92G5x/zOjVbjFCxbm4hxN1WnpcDtS7MngREleGAiqck7aRIihFAByT8JuwZxI4riUasfKQv0snVKC25Q5Zs9bWOGkTgFycSWTouXllVBQAxUdYatbAILbFxwxYgf2OKPLUjZeNu/AQKBgQC1GFHJe4MOLFrOlYVhYH3hhcBv+BVuCKlYQY9xiI7poKFd+w6HaRA3sDUvrKFuCcK7An0dXHLM51GGFVsRKlOof4kYm+nezmlqsp/3iAf/UlqyEEQCChZOSKfP45uXKzTQ+wiCBsJajjhz6eHaj44vLmQEyMLCApR2YYeg8a5WawKBgQC1EgHut3DsBePhCCUZRch/T9hOZngcFhBDUr6A7O73rOnJrZFNFrUczss66U2s/+a1ELUxfPLaH3FkqLinwqUT5Hf6NlHN2J19xR+LuubEYQ30sjeKKilcUNe+VLmyNB+As+m2YxkQURG6v1FwDfussLVIGVwSrtZWRXBtEMslEwKBgEHJk+yd8f40HsjtiIbXhuNms4H5nc9bVim5p2KXOVOmBvkdOyn2AO46FRg8nmhbI2LMhKcl7o6Behw7pdmWhs2vhx5phYxrb+BTA8nooP8f69LEFfCnuXK28j+Tb/J6v7UrfOu/iNtczyVM0b+UZxK8x5j1VcslJMncpGjTY4IBAoGBAI+W1yd6GzdgOGTks57GAwhKxiruAYOsT3tD68SLn7mTStZfKOGsBzXwG6P9DVcKm+aseDPldSRaWO7NCZontgb/X9FxMBAAHmx1XN3O+l9y48FPbtHHO4N0rJHH2Haj9PN4OPckwOR6lYe1XhxxSkqY2zlwut4p/djuOgiTj7U3AoGAf3sKaPJFjaEtOhW3rt0+WStazt9bLkCfQFwAuOXsJ1YDTjUyl0nS71rDdQdw91dQ5HdkVCTBq1EAa9yZZK9v8mFrxAlBG5uwoQMKXHWBwLi4evQIHNP9UBreX/mvlfY8/L0ztWWRd1QDuWu9CNgKqezTw/1ao2ChAxBnsi28tz8=";
    // 网关公钥
    protected static final String	APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
    // 应用ID，暂定，APP申请分配
    protected static final String	APP_ID	= "10000000000004098185";
    protected static final String SIGN_TYPE = "RSA2";

    public static void main(String[] args) {

        // 签名类型为RSA时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA，其他参数使用缺省值
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, SIGN_TYPE, MY_PRIVATE_KEY,APIGW_PUBLIC_KEY);

        InvestmentFnceprodMngCropFinanprodquaqueryRequestV1 request = new InvestmentFnceprodMngCropFinanprodquaqueryRequestV1();
        request.setServiceUrl("http://ip:port/api/investment/fnceprod/mng/cropfinanprodquaquery/V1");
        InvestmentFnceprodMngCropFinanprodquaqueryRequestV1.InvestmentFnceprodMngCropFinanprodquaqueryRequestV1Biz bizContent = new InvestmentFnceprodMngCropFinanprodquaqueryRequestV1.InvestmentFnceprodMngCropFinanprodquaqueryRequestV1Biz();
        InvestmentFnceprodMngCropFinanprodquaqueryRequestV1.AcfsPrivateInput privateInput=new InvestmentFnceprodMngCropFinanprodquaqueryRequestV1.AcfsPrivateInput();
        privateInput.setCurrtype(1L);
        privateInput.setQrytype(1L);
        privateInput.setZoneno(200L);
        InvestmentFnceprodMngCropFinanprodquaqueryRequestV1.AcfsInqCommV10Map inqCommV10MapIn=new InvestmentFnceprodMngCropFinanprodquaqueryRequestV1.AcfsInqCommV10Map();
        inqCommV10MapIn.setInitflag(1L);
        inqCommV10MapIn.setInqtype(1L);
        inqCommV10MapIn.setRowreq(5L);

        InvestmentFnceprodMngCropFinanprodquaqueryRequestV1.AcfsInfoCommV10Map infoCommV10Map=new InvestmentFnceprodMngCropFinanprodquaqueryRequestV1.AcfsInfoCommV10Map();
        infoCommV10Map.setTrxtype(1L);
        infoCommV10Map.setTrxcode(40917L);
        infoCommV10Map.setServiceface(10L);
        infoCommV10Map.setWorkdate("2023-06-30");
        infoCommV10Map.setAuthamount(0L);
        infoCommV10Map.setRegflag(0L);
        infoCommV10Map.setZoneno(200L);
        infoCommV10Map.setBrno(0L);

        InvestmentFnceprodMngCropFinanprodquaqueryRequestV1.AcfsChanCommV10Map chanCommV10Map=new InvestmentFnceprodMngCropFinanprodquaqueryRequestV1.AcfsChanCommV10Map();

        InvestmentFnceprodMngCropFinanprodquaqueryRequestV1.AcfsCtrlCommMap ctrlCommMap=new InvestmentFnceprodMngCropFinanprodquaqueryRequestV1.AcfsCtrlCommMap();
        ctrlCommMap.setOperflag(0L);
        InvestmentFnceprodMngCropFinanprodquaqueryRequestV1.AcfsCtrlRowMap ctrlRowMap=new InvestmentFnceprodMngCropFinanprodquaqueryRequestV1.AcfsCtrlRowMap();
        ctrlRowMap.setProdid("test");
        ctrlRowMap.setCurrtype(1L);
        ctrlRowMap.setZoneno(0L);


        bizContent.setAcfsPrivateInput(privateInput);
        bizContent.setAcfsInfoCommV10Map(infoCommV10Map);
        bizContent.setAcfsCtrlRowMap(ctrlRowMap);
        bizContent.setAcfsInqCommV10Map(inqCommV10MapIn);
        bizContent.setAcfsChanCommV10Map(chanCommV10Map);
        bizContent.setAcfsCtrlCommMap(ctrlCommMap);
        request.setBizContent(bizContent);

        InvestmentFnceprodMngCropFinanprodquaqueryResponseV1 response = null;

        try {
            System.out.println(JSONObject.toJSONString(bizContent));
            System.err.println("--------------------");
            response = client.execute(request, System.currentTimeMillis() + "");
            System.out.println(JSONObject.toJSONString(response));

            if(response.isSuccess()){
                // 业务成功处理
                System.out.println("return_code             :       " + response.getReturnCode());
                System.out.println("return_msg              :       " + response.getReturnMsg());
            }else{
                // 失败
                System.out.println("return_code             :       " + response.getReturnCode());
                System.out.println("return_msg              :       " + response.getReturnMsg());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
