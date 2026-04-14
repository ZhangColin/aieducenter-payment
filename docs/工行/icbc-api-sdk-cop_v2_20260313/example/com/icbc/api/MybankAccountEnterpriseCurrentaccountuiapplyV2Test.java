package com.icbc.api;

import com.icbc.api.request.MybankAccountEnterpriseCurrentaccountuiapplyRequestV2;
import com.icbc.api.request.MybankAccountEnterpriseCurrentaccountuiapplyRequestV2.MybankAccountEnterpriseCurrentaccountuiapplyRequestV2Biz;
import java.io.PrintStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.Test;

public class MybankAccountEnterpriseCurrentaccountuiapplyV2Test
{
  protected static final String MY_PRIVATE_KEY = "BKj2nk+Q6SJ9iRNULQEJM1nIOtPjsgYtiK9KnhTYdSWnInZCnMBpoWmmcBt+zW6eACu6nYDmzc+n0wVCWGhAJQNLpLFsy4ENmbH5q2ZCuIjGN5uLYoWhmSmfWMZ5yaIesHcVSCmFkOT32cXEeUE2dtAhe4lWGMEB3rzJXhTPQWtVRn8o8WRMyJlR0AurxGs5ysx3S6UfGJBPcDjvEnQkdvd2VUGCuX54zIP8MuJldYigNK08wKh7sEHsawFv9z14qPkWZlF6lYN6roVFkcX5TAZs2B9si4F2+N+7IfPswzoM+1iGI3YyT+HZ/ydgwsJFWzB+X6xVaWxYpIP8J9dHUrb76MD8ykBORGTrb/jfXvfsZbYLU2SzknZJJfP2Nja9AEuS2FgfoWGihL0xeVOe/d3yU2sOYFGilx4N2j6RLOAytvUpillF0LPwBT3iOuxM7Ki3NgPjz+Yt6kstf4bdnov97olsz+f3jardcpGejjxJ5UAmW7o4fnUWtUTafzKC+BMYh4IVw3BaXZ2kCZVkJ/Hnm14ahD8p950offMZHd0rQTiZ3T6DtQQWH9Yxq/59V9H93Xk7o7gt03FvogkdIs5eeE5Xv2Jvm0cGsq2eigFJXxH9F5kxQNlk3GIh/COuNjQnsCqU9un15QZV3f2kATP6G+nvdAFL5a9bf+2KZeYhKCpgk2MuwD1xT0hnUvxfkIyEMREHUqSk/ifoNPMI9iWfGbBJAsYy43wpthAb4mAMz806MUPA5ytW7cOpkwoW+dHwXWLM7Y2bQK7dw4lhJ9FajUbEEI2cFODSJZwjKZaQwjU/v/dHihIufjTQX2B/8a+QjkpjZC+Qy6KoIOHkB6NqknxZhjO8cyrGKtfcOutR4Hyfbjf+Ja3FW5V+EOIPg31yqKWU1rT81R/pwikXru5JX5p3EwstPt7sbN74S2w/QRVz9E55zOS/uB4YAE4jkCNnlJACJqCm3bHgC2YFcWgPZP81wNPrsTOiKzq+7XJMy0eU8ywn2mfctXUVKbd93m008/PxxjtzGfeD0hdvB6BBmiVo0WsoagfQrytsNC5B732vCTNEg+JxtBsnifkBSFD0hceQcS4iCp1ls7hx6A/ARqyOCMg7t1txLGivLeT7jq/t2ieTxbMMfC57GTA/jQHOxVFaZYKdKm+hAcTMgLaXKhwwqbaxqgYON+mG6jzDbGy2y1ySE23dk5V/pXCzW8mK1Xt2odh2n2kYmQPGKk6DkZhRRXr0sD4XHsjmINpiYgXpeBxRiVdovG9ctII6L9CnU+esnXTff4EhKaGwiogXau5lMBp7NcVjcO3+4ZZocyYF0z77Hn8I7empuq+RPTVB4sm+Y6WE6ediigXlAhDpp40GU7lMh+U3BE9KbbYVlnwRweSVQ92e/yhzehGav2IUfKb2Nb3B8A+6IBIw3etDwUx3/UG16nOq5r1Nxa3uruX6dG3cQMI7sF5E0gEZ+r98Ed8MzAZJLIvBwUAWeQ7XOuy7hfRbp/9uADYRrKymA65ZBZpQFfWusK1j0KzLWe8oYbyDirNMLzcYdy6l+SO4p6TbjG9mjZuonuymw53DkqIcsM5FU9Zv";
  protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的APP私钥-----DljCCAn6gAwIBAgIKbaHKEE0tAAI0PzANBgkqhkiG9w0BAQsFADA3MRkwFwYDVQQKExBjb3JiYW5rNDMuY29tLmNuMRowGAYDVQQDExFjb3JiYW5rMTI4IHNkYyBDTjAeFw0xODA1MjAwODIxNDVaFw0zOTA1MjMwODIxNDVaMEkxHTAbBgNVBAMMFDAyMDBFSDAwMDYwMzMuZS4wMjAwMQ0wCwYDVQQLDAQwMjAwMRkwFwYDVQQKDBBjb3JiYW5rNDMuY29tLmNu-----请使用正确的APP私钥-----BIjANBgkqhkiG9w0BAQEFAAOCAQ8A-----请使用正确的APP私钥-----BCgKCAQEA92w3hDXZiFqK6BedMo3fTDa/8O/giQ+qBTbLfrKSmLT9yLeDt0t7bnhJ5KjGuKrA+FZWFGcw/0WLV0z/SD2B1B2EtmDhr+psRJIwxWBlIe3KRikbCPvNz3j54uv27POf4378lUN7iSD14pzkL6tm7rmemVQvzucSxqSoaYOHAuXWBrr7ZpNQJrzVaDxSMlro4jGMHUPLsVyLn3G/3uQBemR1dD0WSSV2UdW2bACgb9Hf/NY/5VMu3dhu5lPGcahm6r58VLWOSNd4uBFyjFZdY1mrmDYfr8DcknyCcMWHXwEMwDKyO6lgc8gen35ZCI5qFH/ftC8HPy+n/g8kmFaOHwIDAQABo4GRMIGOMB8GA1UdIwQYMBaAFJ6AEaO7V853/fEnZ/7k4X8i8lHzMEwGA1UdHwRFMEMwQaA/oD2kOzA5MQ0wCwYDVQQDDARjcmwxMQ0wCwYDVQQLDARjY3JsMRkwFwYDVQQKDBBjb3JiYW5rNDMuY29tLmNuMB0GA1UdDgQWBBR7oaJnGXMudnW3LoADXRPsJ0UjDjANBgkqhkiG9w0BAQsFAAOCAQEAYY4Io+MDau7g2Va9oqsaormyingKnYqf2wlK3BkudGh1Nfg+e9EvViVhLRVcXTMwJsEmSbNWvdzMr58U7VF8CcK7mugSTYo7Hr2qQQq61l4NpeVBTCzQNG9BlQLN7G9FccQ4SNR+O/bkt7ghHKYm3WIG0YvluaDXEq2O7xAIOdLl3jHtHf4qiLjw31/mTYA4JtECaNphBR65wXGFXptc2g/JsTEdViO03o8tqkcsjA0n+8QOEa3dBKey1JUw8lsuMZQC/DRIH8pLdFWKqf6DjdidIc9azC8PfaoOiQeSVA8/XNZ8GHwdQdtDZxfF08LF+DwK63aK+4AkOysVOAVwrg==";
  protected static final String APP_ID = "10000000000000011028";
  protected static final String PASSWORD = "12345678";


  public static void check_sign_rsa()
    throws IcbcApiException
  {
    String castr = "-----请使用正确的APP私钥-----DljCCAn6gAwIBAgIKbaHKEE0tAAI0PzANBgkqhkiG9w0BAQsFADA3MRkwFwYDVQQKExBjb3JiYW5rNDMuY29tLmNuMRowGAYDVQQDExFjb3JiYW5rMTI4IHNkYyBDTjAeFw0xODA1MjAwODIxNDVaFw0zOTA1MjMwODIxNDVaMEkxHTAbBgNVBAMMFDAyMDBFSDAwMDYwMzMuZS4wMjAwMQ0wCwYDVQQLDAQwMjAwMRkwFwYDVQQKDBBjb3JiYW5rNDMuY29tLmNu-----请使用正确的APP私钥-----BIjANBgkqhkiG9w0BAQEFAAOCAQ8A-----请使用正确的APP私钥-----BCgKCAQEA92w3hDXZiFqK6BedMo3fTDa/8O/giQ+qBTbLfrKSmLT9yLeDt0t7bnhJ5KjGuKrA+FZWFGcw/0WLV0z/SD2B1B2EtmDhr+psRJIwxWBlIe3KRikbCPvNz3j54uv27POf4378lUN7iSD14pzkL6tm7rmemVQvzucSxqSoaYOHAuXWBrr7ZpNQJrzVaDxSMlro4jGMHUPLsVyLn3G/3uQBemR1dD0WSSV2UdW2bACgb9Hf/NY/5VMu3dhu5lPGcahm6r58VLWOSNd4uBFyjFZdY1mrmDYfr8DcknyCcMWHXwEMwDKyO6lgc8gen35ZCI5qFH/ftC8HPy+n/g8kmFaOHwIDAQABo4GRMIGOMB8GA1UdIwQYMBaAFJ6AEaO7V853/fEnZ/7k4X8i8lHzMEwGA1UdHwRFMEMwQaA/oD2kOzA5MQ0wCwYDVQQDDARjcmwxMQ0wCwYDVQQLDARjY3JsMRkwFwYDVQQKDBBjb3JiYW5rNDMuY29tLmNuMB0GA1UdDgQWBBR7oaJnGXMudnW3LoADXRPsJ0UjDjANBgkqhkiG9w0BAQsFAAOCAQEAYY4Io+MDau7g2Va9oqsaormyingKnYqf2wlK3BkudGh1Nfg+e9EvViVhLRVcXTMwJsEmSbNWvdzMr58U7VF8CcK7mugSTYo7Hr2qQQq61l4NpeVBTCzQNG9BlQLN7G9FccQ4SNR+O/bkt7ghHKYm3WIG0YvluaDXEq2O7xAIOdLl3jHtHf4qiLjw31/mTYA4JtECaNphBR65wXGFXptc2g/JsTEdViO03o8tqkcsjA0n+8QOEa3dBKey1JUw8lsuMZQC/DRIH8pLdFWKqf6DjdidIc9azC8PfaoOiQeSVA8/XNZ8GHwdQdtDZxfF08LF+DwK63aK+4AkOysVOAVwrg==";

    Pattern p = Pattern.compile("\\s*|\t");
    Matcher m2 = p.matcher(castr);
    castr = m2.replaceAll("");
    UiIcbcClient client = new UiIcbcClient("10000000000000011028", "BKj2nk+Q6SJ9iRNULQEJM1nIOtPjsgYtiK9KnhTYdSWnInZCnMBpoWmmcBt+zW6eACu6nYDmzc+n0wVCWGhAJQNLpLFsy4ENmbH5q2ZCuIjGN5uLYoWhmSmfWMZ5yaIesHcVSCmFkOT32cXEeUE2dtAhe4lWGMEB3rzJXhTPQWtVRn8o8WRMyJlR0AurxGs5ysx3S6UfGJBPcDjvEnQkdvd2VUGCuX54zIP8MuJldYigNK08wKh7sEHsawFv9z14qPkWZlF6lYN6roVFkcX5TAZs2B9si4F2+N+7IfPswzoM+1iGI3YyT+HZ/ydgwsJFWzB+X6xVaWxYpIP8J9dHUrb76MD8ykBORGTrb/jfXvfsZbYLU2SzknZJJfP2Nja9AEuS2FgfoWGihL0xeVOe/d3yU2sOYFGilx4N2j6RLOAytvUpillF0LPwBT3iOuxM7Ki3NgPjz+Yt6kstf4bdnov97olsz+f3jardcpGejjxJ5UAmW7o4fnUWtUTafzKC+BMYh4IVw3BaXZ2kCZVkJ/Hnm14ahD8p950offMZHd0rQTiZ3T6DtQQWH9Yxq/59V9H93Xk7o7gt03FvogkdIs5eeE5Xv2Jvm0cGsq2eigFJXxH9F5kxQNlk3GIh/COuNjQnsCqU9un15QZV3f2kATP6G+nvdAFL5a9bf+2KZeYhKCpgk2MuwD1xT0hnUvxfkIyEMREHUqSk/ifoNPMI9iWfGbBJAsYy43wpthAb4mAMz806MUPA5ytW7cOpkwoW+dHwXWLM7Y2bQK7dw4lhJ9FajUbEEI2cFODSJZwjKZaQwjU/v/dHihIufjTQX2B/8a+QjkpjZC+Qy6KoIOHkB6NqknxZhjO8cyrGKtfcOutR4Hyfbjf+Ja3FW5V+EOIPg31yqKWU1rT81R/pwikXru5JX5p3EwstPt7sbN74S2w/QRVz9E55zOS/uB4YAE4jkCNnlJACJqCm3bHgC2YFcWgPZP81wNPrsTOiKzq+7XJMy0eU8ywn2mfctXUVKbd93m008/PxxjtzGfeD0hdvB6BBmiVo0WsoagfQrytsNC5B732vCTNEg+JxtBsnifkBSFD0hceQcS4iCp1ls7hx6A/ARqyOCMg7t1txLGivLeT7jq/t2ieTxbMMfC57GTA/jQHOxVFaZYKdKm+hAcTMgLaXKhwwqbaxqgYON+mG6jzDbGy2y1ySE23dk5V/pXCzW8mK1Xt2odh2n2kYmQPGKk6DkZhRRXr0sD4XHsjmINpiYgXpeBxRiVdovG9ctII6L9CnU+esnXTff4EhKaGwiogXau5lMBp7NcVjcO3+4ZZocyYF0z77Hn8I7empuq+RPTVB4sm+Y6WE6ediigXlAhDpp40GU7lMh+U3BE9KbbYVlnwRweSVQ92e/yhzehGav2IUfKb2Nb3B8A+6IBIw3etDwUx3/UG16nOq5r1Nxa3uruX6dG3cQMI7sF5E0gEZ+r98Ed8MzAZJLIvBwUAWeQ7XOuy7hfRbp/9uADYRrKymA65ZBZpQFfWusK1j0KzLWe8oYbyDirNMLzcYdy6l+SO4p6TbjG9mjZuonuymw53DkqIcsM5FU9Zv", "UTF-8", castr, "12345678");

    MybankAccountEnterpriseCurrentaccountuiapplyRequestV2 request = new MybankAccountEnterpriseCurrentaccountuiapplyRequestV2();

    MybankAccountEnterpriseCurrentaccountuiapplyRequestV2Biz bizContent = new MybankAccountEnterpriseCurrentaccountuiapplyRequestV2Biz();

    request.setServiceUrl("http://IP:PORT/ui/mybank/account/enterprise/currentaccountuiapply/V2");

    bizContent.setApi_name("PRSIGN");
    bizContent.setApi_version("001.001.002.001");
    bizContent.setFile_code("0200EG0000001");
    bizContent.setFile_name("平台名称");
    bizContent.setFile_serialno("6633074382999");
    bizContent.setTradeplat_name("交易平台名称");
    bizContent.setTradeplat_code("773382910333");
    bizContent.setTrade_orgcode("32444324343");
    bizContent.setTrade_time("20180310162230");
    bizContent.setEnt_name("三丫医疗器械销售责任有限公司");
    bizContent.setEnt_orgcode("429006NA000000022X");
    bizContent.setEnt_type("股份有限公司");
    bizContent.setFr_name("王二丫");
    bizContent.setCorp_type("000");
    bizContent.setIdentity_cardnt("431128198512270068");
    bizContent.setTel("18026285748");
    bizContent.setRegcap_cur("001");
    bizContent.setRegcap("3895.12");
    bizContent.setOp_from("20150203");
    bizContent.setOp_to("20220203");
    bizContent.setBus_enddate("20220203");
    bizContent.setOp_scope("医疗器械销售");
    bizContent.setDom("珠海市软件园路2号");
    bizContent.setCis_phone("020-82706349");
    bizContent.setHolder_name("王大丫");
    bizContent.setHolder_corptype("000");
    bizContent.setHolder_custid("431128198512270059");
    bizContent.setEnt_status("营业中");
    bizContent.setNeed_addinfo("1");
    bizContent.setIs_fr("1");
    bizContent.setSign_name("王小Y");
    bizContent.setSign_id("431128198512270999");
    bizContent.setPhonenumber("18026284788");
    bizContent.setNotifyurl("https://xxxx/do.jsp");
    bizContent.setFlow_id("H000001");
    bizContent.setProvince_name("北京");
    bizContent.setCity_name("北京");
    request.setBizContent(bizContent);

    System.out.println(client.buildPostForm(request));
  }

  public static void main(String[] args)
  {
    try {
      check_sign_rsa();
    }
    catch (IcbcApiException e) {
      e.printStackTrace();
    }
  }
}
