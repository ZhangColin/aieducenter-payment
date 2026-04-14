package com.icbc.api;

import com.icbc.api.request.BusinessopEiopoosAsiaActivityVerifyRequestV1;
import com.icbc.api.response.BusinessopEiopoosAsiaActivityVerifyResponseV1;
import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;

public class BusinessopEiopoosAsiaActivityVerifyTestV1 {
    private static String APP_ID;
    String serviceUrl;
    private static final String MY_PRIVATE_KEY = new String(
            "-----请使用正确的APP私钥-----EvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDSWGaqBWWaWFYMOyqQ7wRHKEjdUHH6RXNs/eopPTHvo5xIk1QjXyHCwSqBKb6U9pytkelKe4HV/37npNz03rCGvxUKZC47Vu9dMZ5/By+jcIP2otj+KO91QcOke96juISVSOJ7dNuBOpduGMOJ4Lw/6kH56m1aQa339CAoEwA/vouxslUYAFrwbwCBJ0E8UPzkMCj2esZakDvM7A+sphsqTC641mR4aI0aU+OkIIBYtPAuUK75JKiHjDZ9t/xkzyMIrqJatAWeef92WwRgFXBHb5FskjEZGPcsR3CNTbzBppGxKksHu2PA6yhD6o2joyRAZvz1sXJI33Z1IC1a1wmvAgMBAAECggEAe6EXnkCTyha3ZHL1/r9s3UHRKbrZcsaVI+9NTLW5/uZDuehV4IBOgIA2MjpN2JHl3qJO4scataN4fD/K2mV0jY4LmNLu1VOwVi8OwAyCNGayOSjXrvJyY5GmoqtnXQTbcD6pVvSE+zxrunr1mrkrC8nswy3E3fKShJTIvaUFVXDgSlylUxSjwTqRCm4YGDOfRHG3txjgxu21+32Crx5OeHT2jxo++pBaEfYN05o2GTMPvZtb+9ufIgemhwKNT4EacZ1DyWjLN1GPMLQAmmQPfKp9bDLWRZ/embYMjlQxw5CjfNhcyUdtnuXOEVQNfYJpBcW2ChkqjvLz66cji3jbyQKBgQD720I1CjJ/0aSRYy5kxkNj0Z+gnk+DlDeEqZ51QEgXbHoRr0MgsgSKfXa6w2GRxhersR6NEc7jaTZ4N0tVkFZXGS7ScynTwcHBFAfcTD90XXHz2LM9uYVnRRRYarBbVNKtGZ2VoCYALvi3x+IqmSByIZ4p+kdXFLX8QGpP13aszQKBgQDVzk9tunZvQxbm5jQ70tqlYyKPGs1HkxUISq8556bkOjZeimdriie2fJAsSiVG2YiScxzyjjvZsnGt/OKXOYCvlCGKpd27UnaGIiEQJiXlr0rxJbsBbvDxzyZYaCd98DfFDZyHWYhG4+CAew/HTMkqzGNkoBbmVWAs5sPQZzAQawKBgQDTOogjneLOqtAgE5PUdygL7sK3Xi8IBuYtQGkio4ATDSqv7FMbd9IxKJrMdpM9RGuT1t/tprLirdo+Jvnq0DxpnjhTzryU2hsbSSZykAxkFv88aVGjAhatjxVKNmikoNZ00VgGJqPYYYy97kl0OqsoraNOR5dPpJIg9xFy1x20PQKBgQCJeRVTTZB3a+De6Gfvi10w+HQxJZ+WhPkCEttmVQ/XMaLh2QY0fGmaia89+xLK4nZRDBkx0Mik6nrzz2V2rO+UdZ+3gXz3xEcXafxuYMj/TV3XflYmzsm4AWT7dUTnUjzZxC3ATY41XrJsijQUBshWb4L9IX537a9gT6TabG7YLwKBgQDXxTjd9+AKvr/CCrRmFObkfJB/uECIWDVath/Ea1eHPBH6XI92c4eMkpoFBhuJ790PBnIsXTWr0n9pqj1NWG7Fng9rWO5HV0NsfoaNqmC4LntKZe4gQn82rP7JmepgbzeMzuxgFdEsT5Gbvttpi/2oNvDsEzyXo9vf+3J1n9CAnw==");

    private static final String APIGW_PUBLIC_KEY = new String(
            "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB");
    private static final String AES_KEY = "oYu6vN8oDimqEM8jztvcog==";

    public String returnInfo()
    {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, "AES", "oYu6vN8oDimqEM8jztvcog==", null, null);

        BusinessopEiopoosAsiaActivityVerifyRequestV1 request = new BusinessopEiopoosAsiaActivityVerifyRequestV1();

        request.setServiceUrl(this.serviceUrl + "/businessop/eiopoos/asiaactivity/verify/V1");

        BusinessopEiopoosAsiaActivityVerifyRequestV1.BusinessopEiopoosAsiaActivityVerifyRequestV1Biz bizContent = new BusinessopEiopoosAsiaActivityVerifyRequestV1.BusinessopEiopoosAsiaActivityVerifyRequestV1Biz();
        BusinessopEiopoosAsiaActivityVerifyRequestV1.BusinessopEiopoosAsiaActivityVerifyRequestV1Biz.InfoCommV10 infoCommV10 = new BusinessopEiopoosAsiaActivityVerifyRequestV1.BusinessopEiopoosAsiaActivityVerifyRequestV1Biz.InfoCommV10();
        BusinessopEiopoosAsiaActivityVerifyRequestV1.BusinessopEiopoosAsiaActivityVerifyRequestV1Biz.ChanCommV10 chanCommV10 = new BusinessopEiopoosAsiaActivityVerifyRequestV1.BusinessopEiopoosAsiaActivityVerifyRequestV1Biz.ChanCommV10();
        BusinessopEiopoosAsiaActivityVerifyRequestV1.BusinessopEiopoosAsiaActivityVerifyRequestV1Biz.PrivateComm privateComm = new BusinessopEiopoosAsiaActivityVerifyRequestV1.BusinessopEiopoosAsiaActivityVerifyRequestV1Biz.PrivateComm();
        String returns = "";

        // 设置infoCommV10对象的属性
        infoCommV10.setApptype("0");
        infoCommV10.setBrno("228");
        infoCommV10.setScene("002");
        infoCommV10.setTrxcode("500185");
        infoCommV10.setBrnocrd("228");
        infoCommV10.setBrnoweb("00120");
        infoCommV10.setDeviceid("5FFD0F50-C546-4723-96D3-27BDCD7442C7");
        infoCommV10.setDse_locale("zh-CN");
        infoCommV10.setEffect_range("900000");
        infoCommV10.setFunctionid("");
        infoCommV10.setMenuid("");
        infoCommV10.setServiceface("53");
        infoCommV10.setSubmenuid("");
        infoCommV10.setTellerno("59");
        infoCommV10.setTrxsqnb("7731065");
        infoCommV10.setTrxsqns("0");
        infoCommV10.setUseragent(null);
        infoCommV10.setZonecrd("0119");
        infoCommV10.setZoneno("0119");
        infoCommV10.setZoneweb("0119");

        chanCommV10.setAppreceiverid("EIOPOOS");
        chanCommV10.setChanneltype("9");
        chanCommV10.setChantype("4");
        chanCommV10.setChanlno("011900900000059");
        chanCommV10.setChnseqNo("ip:port.20260620133933.60");
        String ip = "xxx.xxx";
        chanCommV10.setRemoteAddr(ip);
        chanCommV10.setIp(ip);
        chanCommV10.setMac(null);
        chanCommV10.setMesgcharset("UTF-8");
        chanCommV10.setOapp("MBANKPFOVA");
        chanCommV10.setSerialno("13030119617230000038413144");
        chanCommV10.setStructtype("JSON");
        chanCommV10.setTermid("53");
        chanCommV10.setTimestamp("2026-06-20 13:39:33");
        chanCommV10.setVersionid("1.0");

        privateComm.setAcctType("2");
        privateComm.setAmt("");
        privateComm.setAreaCurrType("013");
        privateComm.setCashbackAcct("861828091432");
        privateComm.setCashbackCurr("013");
        privateComm.setCashbackMedseno("");
        privateComm.setCino("016500000114840");
        privateComm.setCountrycode("86");
        privateComm.setCurr("");
        privateComm.setCustEnName("YI XIN");
        privateComm.setCustName("新一");
        privateComm.setMainsign("2");
        privateComm.setMEDIUMID("861828091430");
        privateComm.setMediumNo("0119012030101000000147");
        privateComm.setPeriod("");
        privateComm.setPeriodunit("");
        privateComm.setPhoneno("1593****000");
        privateComm.setTriggerid("01192022120300068");

        bizContent.setInfoCommV10(infoCommV10);
        bizContent.setChanCommV10(chanCommV10);
        bizContent.setPrivateComm(privateComm);

        request.setBizContent(bizContent);
        try
        {
            BusinessopEiopoosAsiaActivityVerifyResponseV1 response = client.execute(request);

            if (response.isSuccess())
            {
                returns = JSONObject.toJSONString(response);
                System.out.println(returns);
            }
            else
            {
                returns = JSONObject.toJSONString(response);
                System.out.println(returns);
            }
        } catch (IcbcApiException e) {
            System.out.println(e.getErrMsg());
        }
        return returns;
    }

    public String getServiceUrl() {
        return this.serviceUrl;
    }

    public void setServiceUrl(String serviceUrl) {
        this.serviceUrl = serviceUrl;
    }

    public static String getAPP_ID() {
        return APP_ID;
    }

    public static void setAPP_ID(String aPP_ID) {
        APP_ID = aPP_ID;
    }
}
