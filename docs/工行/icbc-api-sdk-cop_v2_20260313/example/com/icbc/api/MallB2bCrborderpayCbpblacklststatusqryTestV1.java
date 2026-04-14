package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.MallB2bBlacklststatusqryRequestV1;
import com.icbc.api.request.MallB2bBlacklststatusqryRequestV1.MallB2bBlacklststatusqryRequestV1Biz;
import com.icbc.api.response.MallB2bBlacklststatusqryResponseV1;

import java.util.HashMap;
import java.util.Map;

/**
 * B2B跨境支付黑名单检查结果查询
 * @author kfzx-jinsx01
 * @version 2019年4月版
 * @date 2019/4/10 20:03
 */
public class MallB2bCrborderpayCbpblacklststatusqryTestV1 {

    protected static final String	MY_PRIVATE_KEY		= "-----请使用正确的APP私钥-----EvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCXY3LeSkYCtpk2mmyeLKYA2OrFyp36vW7OOkfU0w6OkAnGnOFY5qn92ig49Psn2sw96yRbdbq2466SM9Vy13RCYLg2WnGDNmx2+QZr1j5gSmCusk76fVvFv5c0/WCdg/9IQt/NPLR3Pg9vyVGTrw1zZwP5MRtizOTTBAMMgfbPOD8JrS2+my1CwX7P9vrBxvQZMLGMHJsMneaAuoG9Kfo7UpEUnIBiKLq/9DbDMQQ1fSS0l0xA64IJLuLG+nwiwzgyWX7AblQtCtdPTHmBCPk4o8Y5hpNAyQ4dtcWk1F90i/YxexyDwbDBnPJ9uP+HFp895Je8XoPy3VqMEQToEdF/AgMBAAECggEAYoeJVYAFvPJXGaC8HN52VIrZkk3jMDRmj7aC1dNf4A2DL08fo5rM/LkmAJwBctkGoS4Pha9EDJB3KBSwUYRE2IbWnhmTU5DyWySMt1bDVixSbKEEoidAuZ2OriBS53HnjoJn4s4lro4nwdvHSlVebcOYZHE/n7g3Hde4nEVh+09hb6JXXFC3EK/7p80zt0wjp1yv9/iLPgXSvyXRW9qM8ED5JbDpsO4nDvF5ZwXWjQ3iauQjsKjVgYcNg31PNq6tl39I3sivx2AP8IYoYz/J5IukKrc6Yeb9KLyjrhMzeoJr3gzpp5T/VPb2vgeH+z8ytU91inMiS7WqmD+xNq33QQKBgQDSph1K/fq8+ZyckERklNudVdtsIKHtNL5kFvPFZUSF94oUgg6y6IkWO1wtDUIUAxi9B88/l34TBeYqmvCfDe/MN7npAjNbDhh6m6VyPZqgUkCdLCUqPpoI3vEP7N8iLPCXZoVs6nvrOqh0tbsLUplQpDc3KOzQqYhhGGyoqZNfUQKBgQC3+zRUH0Y8TUdtkD+VWB74+FzfmgalNZQZMjVbhkpmPWLCj69IxtHIFwWtKQQdeAvYii8PgHrstkQOSEcpXTItIJXcHpwRCypyZpO1i/ScvflhdHJdWKYsaho7k/peGWTP09mD7UHv3SzEC0taU6GzvUE7ZsZbZCwQf8OPkbIPzwKBgQCROH4HXjMHJtpmQTs1UD5bKH6d+51Gh88d94jVNCxaYz5pRwRVKOShVAkG8hX07/GVGBYz0//Ro4Z7Jtft/1CrLFvZnG/FPWF43Wbg7EF2d14ePPneNsxAg1oTTYzC5dJzvfUMEled4pGR7Ftnwej6ZEvAllE0xTvFHG6ElGuKEQKBgDuIBzipHfrpLpGfOVPDvdHl23dmyXuvyIoWmvlQjMZkWo6GG7u6yaqq+GU9fl1iuMKKhAvKEPyDRaYxcQVLaK79ekBNj0ZcjJGT63u1G6OUPRtxqIfX5X09n9tjWIIWSW6OKDXuEYXh1VhiJzHFGe+k73SfHtP3p+J21PqhtDI1AoGBAMQZUXTDb6wSA76b2Lc5/pqYYvoO1GQYJvWm8o4ZTlno7iBdFTAOEz7ekKFmyuW/NiZq58hOA5PQevRmXKggLix5qSlvjLJc8y93gC6kqW/Znuaf2fYHR7fl7jR1neltETtEWvrW0Ei7YugeBb4sim1lagbPLI87Ab8hwSJahwHh";
    protected static final String	APIGW_PUBLIC_KEY	= "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
    protected static final String	APP_ID				= "10000000000007354574";
    protected static final String	ENCRYPT_KEY			= "mQbJILokBccRHUkS+XBk7A==";

    public static void main(String[] args) throws Exception {
        new MallB2bCrborderpayCbpblacklststatusqryTestV1().test_cop();
    }

    public void test_cop() throws Exception {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA", MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, "AES", ENCRYPT_KEY, "", "");
        MallB2bBlacklststatusqryRequestV1 request = new MallB2bBlacklststatusqryRequestV1();
        request.setServiceUrl("http://ip:port/api/mall/b2b/crborderpay/cbpblacklststatusqry/V1");
        MallB2bBlacklststatusqryRequestV1Biz biz = new MallB2bBlacklststatusqryRequestV1Biz();
        String json = getJson(); // 获取Json
        biz.setReqJsonData(json);
        request.setBizContent(biz);

        MallB2bBlacklststatusqryResponseV1 response;
        try {
            response = client.execute(request, System.currentTimeMillis() + "");
            if (response.isSuccess()) {
                // 业务成功处理
                System.out.println("成功");
            } else {
                // 失败
                System.out.println("失败");
            }
            System.out.println(response.getReturnCode() + "  " + response.getReturnMsg());
            System.out.println(JSONObject.toJSONString(response.getResdata()));
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据实际数据上送
     * @return
     * @throws Exception
     */
    public String getJson() throws Exception {
        Map<String,String> map = new HashMap<String, String>();
        map.put("applyNo", "201809191936380001"); //申请编号
        map.put("parentVendorId", "10002778"); //平台供应商编号
        map.put("vendorId", "B2BKJLS00100048"); //供应商编号
        String data = JSON.toJSONString(map);
        return data;
    }
}
