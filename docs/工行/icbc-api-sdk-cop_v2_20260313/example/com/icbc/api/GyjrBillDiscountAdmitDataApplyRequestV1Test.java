package com.icbc.api;

import com.alibaba.fastjson.JSON;
import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcConstants;
import com.icbc.api.request.GyjrBillDiscountAdmitDataApplyRequestV1;
import com.icbc.api.response.GyjrBillDiscountAdmitDataApplyResponseV1;

/**
 * @author kfzx-zhengld
 * @version 1.0
 * @description TODO
 * @date 2022/12/29 16:05
 */
public class GyjrBillDiscountAdmitDataApplyRequestV1Test {
    protected static String APP_ID = "11000000000000001051";
//    protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCiVqi8x6oUbUNjm7/8N53L4Qz7h9FWI/l188CFs8QujWZmLJCiiqNdM0L2h48ynboLFc8U9W7Szg6LUP7HgRIiPOrk+FMMRJbpwzfeg6TMS4PXxuYmaLOq3qHaFiy51ICP3yOGf98x3be9Cd/pADgCV2DdDFXjJbQdQiBi8nOJEjkfWtA56GyfD9ZSf0MdclMX9WQDJ6ZKZ1/a+e2/tw7alBV3SVssRZzkcHehyedJWJbV/MR/yRk4AZV5gT0zcMOuSVI2nFfFdeXjaZuksM3SIpu2HFAbrQ8tW1AqvvHIYr5W5cMUPyMzC4SGor5/KpXVAUuswRcBtdnu4f4DAflTAgMBAAECggEAaccLtc5n7V1wrqPaye43XJRs0CykY3uXXZsRVE1ZRHgQQ/0/1Rl+7wiq1lZoITQS+IwiXea6apOneHGzQa3Bchu9dqqz+mdq6jCw48VliVSfOOc6KcbJY0ojriYKyJ9Fkfl0fXwuo6Bzy7J2eq0Q0b27uO0Dvblgjjv7PF38mE/+dEjFebDIE/oSqho8/kpJI3ZeaxNF56xyOpOCdRmY2ROFT6sIK8/hdC62eA3SOJDEL9UkGMSZkAUUyebavMwzDXpBT74BOttPG3fnshHXp2uPDCCqI3eXCGrgZy30OiUNMPK32IDGiRoQyiXSMZsMqq06+ytyvs6b2+P+s81dCQKBgQD9h5iI1So+BSMcL2FMCrRR03pSSAbvA7tUIsJEIw0dJmZ2ql4oUZWALi3N1j/v63EYVZr/6Pb25ynVDfzClfSOGXYRoS5sBqgMVfnLyWdB6paMackp8FBnwnHMCjQ4ZoafF0kKn3uR2bu1doMtWAVXq4PeQOJvgbhlwQYwxHImRwKBgQCj65idpTLO4FDcqq4hOeSoT45nTKfdbYp0+9wBoyl3xo5sbiMsh9OBIc7NUPQdwXxNCLW4ijo1nZqCSfv0EV3sip67dgnIRjzPPhsy8kS7+FBFFhKeUwOH5wTfNQxcfyxX/wSKlnzMPMpZ5DgSdElM7FMcjD7fdGx8visomg++lQKBgDgO8mhXEfcStbe2tkHVIBfztdlZucbBwXsSEHh2c6Dda8yTUSyDFZZ5H4Gi/RI0shYaX6J0RZ9Eb9h/NkNkWuXMtXGifRw/wIqxvAEWcHF/yHgVr/hI/c5jjTv7iZ5q9RtaT7k8mFsglMPEB69QBiZjc04CdM8JFNfS/WZtGXxjAoGAM0Ty/Qu4coAyinJP+FB1Njd74M9XCajDT4ee7lwsEixmS3+/PKRXAOKkHg+d6tijmiyJOejx/+cFdo7RYSs1Z1CMCTIIKT3esytHso2X9frakGEw/ZgVWKJnIbEj5qJYKkW3E+mxquewmqEI8KyyaH7aF+m9mIJ/lByevxpApmkCgYEAvaBoJoau0k5iOv2ZVRSGmV08ONS9g0MF9bZlXzoD71oJj5YgeTyo1+y0mtKtTSrdamq6oX4u2lyr/Zv9xllTGUEn8wPxgrrXOVRq5MtTCOKOUWlyKZ31IUS08dgV1qu54K68NSSkECyeNjMEPf5q1pa48qcKAg/qRHwhoQhLUSo=";

    // API网关公钥(变量值需替换)-----请使用正确的APP私钥-----BIjANBgkqhkiG9w0BAQEFAAOCAQ8A-----请使用正确的APP私钥-----BCgKCAQEAjP2YbjIzHcJxfka1P7MLWESi0tfADwLRXeMDhLE2HCWT5aH2IdJuYfyqT1Q4Q43Podx6hXagkVteJYcOdXmgfc+jq/V2ArBKzqjCQs010sJAiyyRsGQ2fusW3NpEyu9xfBPd7texFjXi+fnCud68t8fqAeNHHxM5deshlFRMcq+0cBQagYgpFeekY9/i930vVbYncOdf6cIDtpB4DG89AMssLo+crPkNFDdKESxKXqzTtRCAtLBObhMySk5ldE92XamLdHxfHdzeNV7Ox2KfHWHnSwoQK4HsphdNFCS4SolEdHh9cUHr8RR+8QzEvjHOlpj8sDTT6qbzL7T0Xu12BwIDAQAB
    protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
//    protected static String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCDkBboKWjOqO++2CXIE2RmE5Lym5gZvsgeqn6AGdzVT6zOnr+79PLhjfbUntXWS8e4XooVvLYdMuyasH5Wq8+Gb4pY4pCU85rMFqqIj8gYvSGU2P+EsAbngTqs/ck9fBYUcpZe+aDwvvE4snYgpJVM4sDJL6nRjFn6h3WFUnLNTOiknxAtxNKksP5RmKFkOMEjxh/SxltkOI9Qndpnj3CW2u+SDqRpoPFkQm0obK7HGFumMRbWTZZeFnmAVR/16SRcBQRXE4AKZ3KynCHPIHMNRIFHw4+sqKqHgiH6NpCo+k4dXnJy1OWTAgC6x2ghQ77mJ4k+B2TWbvivLpzS4stPAgMBAAECggEAIfbV4xOlqgx08Eju4AsZlTyiaB2BE8xr73bGd521pCAQlbrn9scS2OYEffmPbOZdZhyJ+3ifb0RRTtIXm5p5VdR/29iJyNpm8R884Up1NuXEwJ+Qv5mN/sAe/L/77vKU5Kx8Y4KAaQZyUdfIpT64CIRVjtrONXnNVN8ZmNSjLJq4xkQmxWtG7gqZpMo2RIJzsZm/9sgOcl8FA6DGJV+R/SjlPblze7d8c0ZonV3+Gzp9vV32ON9GbaHmhrFdfiltEW+Ch9Catfiu3dYGBTWbF25ntlnNY7d47MlcsF6gJNF2Jm9V3l2uq6vcA9rjtflzGlMu0Hd7KULBpNfcNSDbKQKBgQDeXtZ8oo0hBz5L5XA3P1IOd/Etoi9alc0AxzXjOT2UrFnFAH3SH3eOGJbhJUG4C15uiEHmLItVCLdvWHkTPWxZl6Vfqi3B2fpainnQTBCOEfR30LE0DbLW8gpnE2eNXGV8lZPllh8u0Y/E4tQdOEntNRqPysKxk5bOUnWipZ7EOwKBgQCXdZkrrTNr7q2LvfFSdA4kIx4FFI1M/8kHnqNT2wdCFYYEagiE3Lw+/CjFpkE6tpKd2SN5P6c98g/jL0Kej0HHKwNUfeI1n7klEhroC8KtROMEO8fuzoDyEZjvbNGEUFMa+jvvzsfyMx3mFfkzQzL3qJKzCUl0/HTkmX7vLUPH/QKBgBAfsE25O6J5c/rs2AYpgTmrWMGGUQiwc/zqdO3U6LIvDRNLxdPGlFSpU8dffDRsHpzFJIIcGL5Bs4tPTvgmLAUTCdq17DtyIsUN5WdFHK4wfxlw+Ghd1engAwEWVxvY50LxVZgAGqK6b0JHcDnuKu4YO6TRfuyBVx3+vKjeeXShAoGBAJSdTYhv0M8NSbiezhYrzlxjIOe3PNba/TS+0mBLwJl1aSUXrfhAZ24eGxulgMfNsZu1Hnuwm7fKtxAipWnUA6UcbY4QX1Biw1a+pywdrg46U88poYqPkjL0VW5JBmSgNIt6EinCaqsawTGtSfKk83PTMu7+I9foiAJvIwApGCM5AoGBAKfIvZ9nrdmxm1E5SkSylWYJh3X0ZqgX505MX+FcdGTLUWwpuw/AW6fLd8zzrYgExPZkH9lNSXpYxKqUDnZvhVUakwQ6DCV5bEA9jxARtpUHMAi35caAbX49IUrs2BzsfOXCajnyoCZN2O7D0JaFR6IuvfePLr68aYM3Himhqq+0";
//    protected static String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCXzp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

    //    protected static String APIGW_PUBLIC_KEY = "-----请使用正确的APP私钥-----BIjANBgkqhkiG9w0BAQEFAAOCAQ8A-----请使用正确的APP私钥-----BCgKCAQEAtj0UFP7IpFoYUocgSNMjoVzEKbj49uD2prend/1XfvSAQI+CDiNP/i84fUH1d622EgQ5SHYMISUEZmLXME0IFtWDGttBYWCAxIhYMmXoxQ+8r8NrJyOfredOA1pA9l01bpxkhiCITVIDrHfAhnNSXspk9054FiFN4z6pLhGL68uz5OMc3iYK8ZxJkdNasdU87ZLwPHhnwv/AuGVAz8AB56y/MEZNCQ2h2TF+n7/sf2EPcx06Hy/FMlrAmjyTgRZ/ie7/+CO98k/hto6NKvif58Fp6GZOb8CQQOUbk03rYBYTIBU3HW+gX00YKsoJdzCg0SNzAj9noHIsLzSFwIlFIQIDAQAB";
    protected static String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCM/ZhuMjMdwnF+RrU/swtYRKLS18APAtFd4wOEsTYcJZPlofYh0m5h/KpPVDhDjc+h3HqFdqCRW14lhw51eaB9z6Or9XYCsErOqMJCzTXSwkCLLJGwZDZ+6xbc2kTK73F8E93u17EWNeL5+cK53ry3x+oB40cfEzl16yGUVExyr7RwFBqBiCkV56Rj3+L3fS9Vtidw51/pwgO2kHgMbz0Ayywuj5ys+Q0UN0oRLEperNO1EIC0sE5uEzJKTmV0T3ZdqYt0fF8d3N41Xs7HYp8dYedLChArgeymF00UJLhKiUR0eH1xQevxFH7xDMS+Mc6WmPywNNPqpvMvtPRe7XYHAgMBAAECggEBAINW/jexZfLUHxvOTKwBY5EfpzqYp56UNmuqva1P1CNWUjc5sGsDd7aqRBFQWHV91q1nEW/g+WVPYx28eZiv6/Unh8X6DKwmhBmOFiFav0gNk0jTBQj34dhLsemdHzaY5fP7IeSMY7hYDJBnvaGev9jIT9LgYXGuW/ngR1DBUtIGN5FqaGiHDPiRXuI3KdzfxEQxZuPUXguXku//VSTA+F4A2Qg+AzPL5DZ0/AfRs5OUQr9UIXaIyxhaiK1vL87N8M0PTStAPndTwHZupZKWHzx8/olH6rtIdX2B5xSGfk/4dR+L5gTSlldWJDygmVy/21vspI8C5G1aEhouiEuFZRkCgYEA7MfTGIE5pPqGq1ypv2Foau8rmNSCrlMQzszRvKUWRCET1RRSr/IYpEzJOnV2d7ikVsNxWYihhNRp0vB5jI2nu3kI7+WNS8WlYPycGVovWLrPHqaEh6+w3uKXqU1ZBgrTboQGoZPH92ukg0BLTLxJlsEqEyBihJd1ldowLQbcjtUCgYEAmG9OVJUo5HXTNOaQGqeQbtNZIq9Zi2JprTW3cqWYaSsWnCXJMb4jdoTLci7kqvRUXfucRe0yOdXRrF88r+IMQ5iZiXQzTfexNNzh8JM4sX1jVU6fJ1SIpXHq/fMpZPUjnRq4fb7SSeurkg9kop4Q0yJzNVfRuaSW5a8MrtjWN2sCgYAWEGzYXLpdaEcCUukyf0Y2qMDcRflDe1QWoTluJcgUYyyw72rhIGMxdEJQIz/92gPeXeoDGNtMQGr4EBaThWO68YVmrXJkPC+0APYe2quMziOczJGO/00xB9ZwHVgNWxbmtoDTr6yznqE+5c/ef7EvziI4lAz6JA79/s38dCY7XQKBgQCAhjCcPluJLRMUYmYUIz4zQz2/cetlC2ggo871+ktkOPFKXM2jhDZwYo0KzCD/RxyvVUsw5kpX20F7PZ10inpesBacf34WegZOlQJnjinZ9OfA8JZeWfcQz9kbn19CgTIy72molDDIJM23TAVE21NCQaSz0ETDatPT+uENt0P+2wKBgD0ZeeVhJJwv9/uSzTG9Rw3i+AeesUhgsNH2XjpxDCQNdJd+ckErUiqQyYiI2T8aMlZc54AmnadbQHSvYIIYt8xsTs2VQ4Hil1iV9PRNc2FgO09lMspbCZY5nYP0qYITD2fixktMH08CL3HRL1zpR/qRhqIZkadM+noJP+TQ5bi7";
    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        try {
            GyjrBillDiscountAdmitDataApplyRequestV1 request = new GyjrBillDiscountAdmitDataApplyRequestV1();
            request.setServiceUrl("http://ip:port/api/gyjr/b2b/comdrftParaAdmittance/billAdmitApply/V1");
            GyjrBillDiscountAdmitDataApplyRequestV1.GyjrBillDiscountAdmitDataApplyRequestV1Biz bizContent = new GyjrBillDiscountAdmitDataApplyRequestV1.GyjrBillDiscountAdmitDataApplyRequestV1Biz();
            GyjrBillDiscountAdmitDataApplyRequestV1.GyjrBillDiscountAdmitDataApplyRequestV1Biz.TransInfo transInfo = new  GyjrBillDiscountAdmitDataApplyRequestV1.GyjrBillDiscountAdmitDataApplyRequestV1Biz.TransInfo();
            transInfo.setProtocolCode("GYJR20220119155723233607597");
            transInfo.setAppId(APP_ID);
            transInfo.setCustName("测试11000000000000001051");
            transInfo.setSocialCode("123456");
            transInfo.setApplyNo("1234561345612341");
            transInfo.setAddress("1000000");
            transInfo.setContact("001");
            transInfo.setContactMobile("18569452365");
            transInfo.setImageBusinessFileName("businessFile.doc");
            transInfo.setLcncumEndDate("20220915");
            transInfo.setIdType("001");
            transInfo.setIdNumber("20221018164443");
            transInfo.setImageLegalFileName("111.doc");
            transInfo.setImageAssetFileName("222.png");
            transInfo.setImageProfitsFileName("333.xls");
            transInfo.setImageOtherFileName("444.doc");
            transInfo.setImageAccreditFileName("555.doc");
            transInfo.setImageServiceFileName("666.doc");
            transInfo.setZipFileName("0000.zip");
            transInfo.setInstitution("1");
            transInfo.setInstitutionCode("");
            transInfo.setInstitutionName("");
            transInfo.setNotifyUrl("0200000709089135235");
            bizContent.setTransInfo(transInfo);
            request.setBizContent(bizContent);
            System.out.println("入参：" + JSON.toJSONString(request));
            GyjrBillDiscountAdmitDataApplyResponseV1 response  = new GyjrBillDiscountAdmitDataApplyResponseV1();
            response =  client.execute(request);
            System.out.println("出参：" + JSON.toJSONString(response));
            if ("0".equals(response.getReturn_code())) {
                System.out.println("success");// 成功
            } else {
                System.out.println("error");// 失败
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
