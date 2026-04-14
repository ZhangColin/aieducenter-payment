package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.MybankPayOpayCardexchangeRequestV1;
import com.icbc.api.request.MybankPayOpayCardexchangeRequestV1.MybankPayOpayCardexchangeRequestV1Biz;
import com.icbc.api.response.MybankPayOpayCardexchangeResponseV1;
import java.io.PrintStream;

public class MybankPayOpayCardexchangeTestV1
{
  private static String APP_ID;
  String serviceUrl;
  private static final String MY_PRIVATE_KEY = new String(
    "-----请使用正确的APP私钥-----EvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDSWGaqBWWaWFYMOyqQ7wRHKEjdUHH6RXNs/eopPTHvo5xIk1QjXyHCwSqBKb6U9pytkelKe4HV/37npNz03rCGvxUKZC47Vu9dMZ5/By+jcIP2otj+KO91QcOke96juISVSOJ7dNuBOpduGMOJ4Lw/6kH56m1aQa339CAoEwA/vouxslUYAFrwbwCBJ0E8UPzkMCj2esZakDvM7A+sphsqTC641mR4aI0aU+OkIIBYtPAuUK75JKiHjDZ9t/xkzyMIrqJatAWeef92WwRgFXBHb5FskjEZGPcsR3CNTbzBppGxKksHu2PA6yhD6o2joyRAZvz1sXJI33Z1IC1a1wmvAgMBAAECggEAe6EXnkCTyha3ZHL1/r9s3UHRKbrZcsaVI+9NTLW5/uZDuehV4IBOgIA2MjpN2JHl3qJO4scataN4fD/K2mV0jY4LmNLu1VOwVi8OwAyCNGayOSjXrvJyY5GmoqtnXQTbcD6pVvSE+zxrunr1mrkrC8nswy3E3fKShJTIvaUFVXDgSlylUxSjwTqRCm4YGDOfRHG3txjgxu21+32Crx5OeHT2jxo++pBaEfYN05o2GTMPvZtb+9ufIgemhwKNT4EacZ1DyWjLN1GPMLQAmmQPfKp9bDLWRZ/embYMjlQxw5CjfNhcyUdtnuXOEVQNfYJpBcW2ChkqjvLz66cji3jbyQKBgQD720I1CjJ/0aSRYy5kxkNj0Z+gnk+DlDeEqZ51QEgXbHoRr0MgsgSKfXa6w2GRxhersR6NEc7jaTZ4N0tVkFZXGS7ScynTwcHBFAfcTD90XXHz2LM9uYVnRRRYarBbVNKtGZ2VoCYALvi3x+IqmSByIZ4p+kdXFLX8QGpP13aszQKBgQDVzk9tunZvQxbm5jQ70tqlYyKPGs1HkxUISq8556bkOjZeimdriie2fJAsSiVG2YiScxzyjjvZsnGt/OKXOYCvlCGKpd27UnaGIiEQJiXlr0rxJbsBbvDxzyZYaCd98DfFDZyHWYhG4+CAew/HTMkqzGNkoBbmVWAs5sPQZzAQawKBgQDTOogjneLOqtAgE5PUdygL7sK3Xi8IBuYtQGkio4ATDSqv7FMbd9IxKJrMdpM9RGuT1t/tprLirdo+Jvnq0DxpnjhTzryU2hsbSSZykAxkFv88aVGjAhatjxVKNmikoNZ00VgGJqPYYYy97kl0OqsoraNOR5dPpJIg9xFy1x20PQKBgQCJeRVTTZB3a+De6Gfvi10w+HQxJZ+WhPkCEttmVQ/XMaLh2QY0fGmaia89+xLK4nZRDBkx0Mik6nrzz2V2rO+UdZ+3gXz3xEcXafxuYMj/TV3XflYmzsm4AWT7dUTnUjzZxC3ATY41XrJsijQUBshWb4L9IX537a9gT6TabG7YLwKBgQDXxTjd9+AKvr/CCrRmFObkfJB/uECIWDVath/Ea1eHPBH6XI92c4eMkpoFBhuJ790PBnIsXTWr0n9pqj1NWG7Fng9rWO5HV0NsfoaNqmC4LntKZe4gQn82rP7JmepgbzeMzuxgFdEsT5Gbvttpi/2oNvDsEzyXo9vf+3J1n9CAnw==");

  private static final String APIGW_PUBLIC_KEY = new String(
    "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB");
  private static final String AES_KEY = "oYu6vN8oDimqEM8jztvcog==";

  public String returnInfo(String areaCode, String cisNo, String oldCardNo, String newCardNo, String cardKind)
  {
    DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, "AES", "oYu6vN8oDimqEM8jztvcog==", null, null);

    MybankPayOpayCardexchangeRequestV1 request = new MybankPayOpayCardexchangeRequestV1();

    request.setServiceUrl(this.serviceUrl + "/mybank/pay/opay/cardexchange/V1");

    MybankPayOpayCardexchangeRequestV1.MybankPayOpayCardexchangeRequestV1Biz bizContent = new MybankPayOpayCardexchangeRequestV1.MybankPayOpayCardexchangeRequestV1Biz();

    String returns = "";

    bizContent.setCisNo(cisNo);
    bizContent.setOldCardNo(oldCardNo);
    bizContent.setNewCardNo(newCardNo);
    bizContent.setAreaCode(areaCode);
    bizContent.setCardKind(cardKind);
   
    request.setBizContent(bizContent);
    try
    {
      MybankPayOpayCardexchangeResponseV1 response = (MybankPayOpayCardexchangeResponseV1)client.execute(request);

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
      e.printStackTrace();
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
