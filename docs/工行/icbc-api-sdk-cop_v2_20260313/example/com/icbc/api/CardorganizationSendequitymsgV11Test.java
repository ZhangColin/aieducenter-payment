import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcConstants;
import com.icbc.api.request.CardorganizationSendequitymsgRequestV1;
import com.icbc.api.response.CardorganizationSendequitymsgResponseV1;

public class CardorganizationSendequitymsgV11Test
 {
    public static void main(String[] args) {
        //1、网关公钥
        final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16\n" +
                "bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCL\n" +
                "T7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP\n" +
                "3CiNgg0W6O3AGqwIDAQAB";

        //2、appid
        final String APP_ID = "10000000000000002156";

        //3、密钥对认证方式，公钥在API平台登记，此处为私钥
          final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDPX8rxIjiY9HSdUp/4rrkLtvZ0pKSchjioy917ZOsfETPk3WN4/lvDp2PpZxQLgAKgi+2X7yH6/DOjPTjj0RezEHQaiYINTyMbaLYFpqZRbHDUmKQiBpmt5yJ7/st28Qw0Tk3CU5PRT8gtOdckB0cAlByAM5W/lsYjNYxw/z/O6Q9iTkWafsGxL9ae3k1Io6Wo4G+rTxLWuewmgcUgedqqCBFFOtRT3Bgw4trCfB7VnbvRebHeOkq+LddkRWwN2xOoi40DoADsF/5wM3Gzzfejd4ZQASIoPq388CP2CdbCeUauXlk3FzpakHDFD1M7KarLT4Z37iHmAWaPRH2ryZ3hAgMBAAECggEAPbpR+n0pQ75giAP0Ql4gDvCjZA0uQrlhXD1y7CwaIOFKBTIxTe7I+fB1uSBJwzKTlZ6RVsyD3OX9qPWsXYP7/f1D0jJkV/nahWoh5IS6tToGTAIzuT0lubI3fZlWj00pSNiWJOYj9gY9m9i/xaW06OMtqKuyQ5lp2UkzSN9x6vDDuDnDpjWWUm5Xy5RO8EiBvlETHezzPakO54o7y0Pll8o09QEnVglVduEtZufDRfBsOvPB8qmllw3CBv8W9xprmWh3mthbB6WPhMY95xrFw3wyY+WIphbPC/O551JRqLqKhGPzMuw0xPPRmjJM73V/j4Y8fL7Yhvl4iP8sOPuBwQKBgQDuQniBDYjc+nDz8jTrlVPOUQl8PLtDvNkmgT8VczZYWjr/nJsl/9v/cKYZngq/vsGFg60xMcqhdp8WKNckkuGODMDPf+g2dFFBFMzMWx8L8VpzMn9+11d9itOyCsJItOHDFMaUEAmqHKkCC5xRKPZXMuhq7dPTBf7Yk+JoJ00r3QKBgQDe0Jswa6oxqnQ2sBJ9F9cIOlrlKtzcjp1dpwe/TK8NavERdYWkDOsbUfiQiMp5GkawP9YYwvXTXkt/0W+rtb8rKxegcYEEk2EFw62UN3KLWDkHJWGPAcDbDwYPMgjrgAUtoh3WmC30YUaDYSkBu6m1DueNuKvZiHsUv0jR2q4r1QKBgByPe0J9DNrwannTnuRekh05OZna7dfA24byF03K8cjFiCz76N01PnnwVvjbKQ6zHx+SGUC8Zz3Tql5NpvyLXt5YSQSWoI9mghb7F1Gn2qDj3aPRedcKmUJDUf0VrRRqGmX4J5W4FnB93wSPv70628y4HYQz3iB9p37uu0Ug8O9BAoGBAIWFisoMatQSakvvXVT7OU/j8F5OPkYpbGIX2wunP2uR/15QB8LUPvuDNqoeXv8NYPjzeDAkfL2qTyIMdpf2dNXAoAr0mbUNQGH87gLY61pXhu0iWIEjMtfS+/WVLUU61V+QO8NCNJ+yv1exnKo+NCedA+Oom8ri/6biLhgbrK31AoGBAIIgSGLC8QBVv0DMB2jwLUsmkUYbQyzu8bZG9h+jqdXLAol/V43HuRCVI0h5BdJ+pj63EUyKVd0G2EIRaXVND2NgKJ/NH6Q5eGiHWloCHJf4dt9wNGfhRk9WaOefc0JRlZ4VJsDriiS139bX2i/NaAMxxZGIsrrrypbR/560zDtr";

            DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
            CardorganizationSendequitymsgRequestV1 request = new CardorganizationSendequitymsgRequestV1();

            request.setServiceUrl("http://ip:port/api/cardorganization/sendEquityMsg/V1");

            CardorganizationSendequitymsgRequestV1.CardorganizationSendequitymsgRequestV1Biz bizContent = new CardorganizationSendequitymsgRequestV1.CardorganizationSendequitymsgRequestV1Biz();
            bizContent.setEquityId("1811280016");
            bizContent.setTransType("0");


            bizContent.setUsagescenario("2");
            bizContent.setName("悦榕酒店和度假村");
            bizContent.setMainName("住3付2");
            bizContent.setCardOrgainzation("2");
            bizContent.setCardProductno("");
            bizContent.setCardBin("");
            bizContent.setCardLevel("0123");
            bizContent.setServiceContent("在悦榕酒店");
            bizContent.setServiceRule("须通过以下");
            bizContent.setServiceProcess("使用折扣码");
            bizContent.setStartTime("2020-12-31");
            bizContent.setEndTime("2023-12-30");
            bizContent.setServiceLink("https://www.banyantree.com/zh/offers/mastercard-offer");
            bizContent.setStock("");
            request.setBizContent(bizContent);

            CardorganizationSendequitymsgResponseV1 response;
            try {
                response = client.execute(request, "1111234");//msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一
                if (response.isSuccess()) {
                    //6、业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
                    System.out.println("ReturnCode:"+response.getReturnCode());
                    System.out.println("response:" + response);
                } else {
                    //失败
                    System.out.println("ReturnCode:"+response.getReturnCode());
                    System.out.println("ReturnMsg:"+response.getReturnMsg());
                }
            } catch (IcbcApiException e) {
                e.printStackTrace();
            }
        }
    }

