package com.icbc.api;

import com.icbc.api.request.CardbusinessNcpayuiNcagreementsignRequestV1;
import com.icbc.api.request.CardbusinessNcpayuiNcagreementsignRequestV1.CardbusinessNcpayuiNcagreementsignRequestV1Biz;

import java.text.SimpleDateFormat;
import java.util.Random;


public class CardbusinessNcpayuiNcagreementsignTest {
    public static void main(String[] args) throws IcbcApiException {

        //APPID
//        String APP_ID = "XXX";


//        String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";


//        String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCpx55HRWBecD9PDGnjgG14gySvwgsWahRXl+6wvlsC/oT1Gr4vQyVIQ6JJDkWFopyKrbcfSm5YPanGLiM2iBZ4/Vsa5mUGmr7Ne4DAD2zEFhDPlFrMuyP7h5t89qtnL9Q4vgz89W35qDAiJ3lLjutRgk2LmyvYFpgTazdH/34cIkBIfhlTLLUGKEXL2fEc0A5oKkLPHtu1/YF6u8rlsu7Xe6WLOBeHk9KqBR7BH+5ctL75oZBBRbwKauFPRWE/tQcjXv+qxJJVq6TfbQCRPKpzdmt/YcsiWnJwL1CEeXndXsY6mX14fSH1jHmU2cnkjccudA/bBJAKPSzFfQ1FCp2NAgMBAAECggEBAKiAqiJqRJ72IJ8/3sggaoW1AvA9UevJFK9q50cbZhCusx7gj+kwZww1tBd1xqI/JcatapXvtVx2ts0udVxl7IfgTE+CYroPU1d1P+9eX8CtdbnPr96JzciSaku4NZ2P5rz6QsG8hI3H0x3St5u6GOsou/XW58jrg7uiKZeXh347DwtxFJEhgjZBGUDw1+GOWapz1WFXfkjxEiikYTW6YSLYGHR765YXG5wpanIqknz1iTBkXxedlzc4WbIfJMYNlilrYGUhEwaMuCHHz6HIVqSRQxmmJT+ZwZ4aCFeZnvBKe7dDRrhisqigJh0QqqnwO0aIVc0fBdKBFbb0sxrkaYECgYEA7ML1DPoGGMrpWOUiFzwpOkg3ZpL/9+KyReTzxAY/eMV9C4dUMXSE0yh46YueeYiqlinj88Ob7Q2AmzHh4M+J9BbmyR8pV8uM/W7X7Rzs9YkgcB2dQcU7WBfSaErwvIVTR62ABLoCgv9q74sWdPOTGxfIGQafzJNBEKmUdJxJx9kCgYEAt5NTbUk99cONlI7k98Ow8FVcAnx1qvmZfuVukRb6lufgbLhCKy3bz23hYkyUyVLl8Q6o+Vgg/TTuXQ8DCCPF5F/Cv/s8S+/ulF1sT7J/P9x3a/tCSCICfiRNM5jByG9zDZI4UgxzDV8AR2BvH19sCL355ENA/8ljkrvZkbfSRtUCgYEA5rMVOp7JoBDgDWm/rSpG9e52ZTGpz24KzgZI7OI1qMWukbQ8+q+R2XtfThpowf3EUuvgnZ8l6Xe1sAOMGJXB06EZIjKGA3FpIW2HYxlDYAFNJVIF8JdxuuYDdPJI3zl+q6JI0tI5LlIZbe52ZGEPH2At7wzAWwPFa6oB6Rp83gkCgYBo6kMgz14g6j0tLSmAzI+AG1i1dwoMyA/IrTkupLJcsnLZz8jJQrFAcG0CMABMuxr9VMKPlqVr8OdVufGiokdECNKVw/Em+R4iHu568em1xrtdtECiVwWTsA2LMn91nPRlSi7z/Xd3qdbP9DYt+OwWZvS9Vw574U4OG1vx4tvDbQKBgQCpIFfrj19CQU/+oWwtkdfdK/E/hMfK1ESFHfWAX+Pq4pIyEIz/Kb3IweKy9uEGbE/zNfi3m5ClCf9H3B4BYk5EKbSk3lVDKg3F4U+1ETNE4fwvKdBfCgpJMi12xdTtkuhfrVUjhaw7T0pFc8fJkP5P5Q+elY6iwYgn+JPm+2yb8A==";

//        UiIcbcClient client = new UiIcbcClient(APP_ID, MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8);

        CardbusinessNcpayuiNcagreementsignRequestV1 request = new CardbusinessNcpayuiNcagreementsignRequestV1();

        request.setServiceUrl("http://XXX:XXX/ui/cardbusiness/ncpayui/ncagreementsign/V1");

        CardbusinessNcpayuiNcagreementsignRequestV1Biz bizContent = new CardbusinessNcpayuiNcagreementsignRequestV1Biz();


        bizContent.setTrxSerno(createTrxSerno());
        bizContent.setTrxDate(createTrxDate());
        bizContent.setTrxTime(createTrxTime());
        bizContent.setMerchantno("020001020483");
        bizContent.setMerGroupno("4600000000001");
        bizContent.setSignMedtype(3);
        bizContent.setSignMedid("湘T44444");
        bizContent.setNotifyUrl("https://paymentdev.vbsave.com/sign/notify.html");
        bizContent.setRedirectUrl("https://www.baidu.com/notify.html");
        bizContent.setProtocolNo("46001000");
        bizContent.setBackUp("");
        request.setBizContent(bizContent);
//        String msg_id = String.valueOf(System.currentTimeMillis());
//        System.out.println(client.buildGetUrl(request, msg_id));
    }

    public static String createTrxSerno() {
        String lastRandon = "";
        Random initRandom = new Random();
        String singleRandom = "";
        for (int i = 0; i < 6; i++) {
            singleRandom = initRandom.nextInt(9) + "";
            lastRandon += singleRandom;
        }

        SimpleDateFormat dsfDate = new SimpleDateFormat("MMdd");
        SimpleDateFormat dsfTime = new SimpleDateFormat("HHmmss");
        long currTimeMills = System.currentTimeMillis();

        String trxSerno = dsfDate.format(currTimeMills) + dsfTime.format(currTimeMills) + lastRandon;
        return trxSerno;
    }

    public static String createTrxDate() {
        long currTimeMills = System.currentTimeMillis();
        SimpleDateFormat dsfDate = new SimpleDateFormat("yyyy-MM-dd");
        String trxDate = dsfDate.format(currTimeMills);
        return trxDate;
    }

    public static String createTrxTime() {
        long currTimeMills = System.currentTimeMillis();
        SimpleDateFormat dsfTime = new SimpleDateFormat("HH:mm:ss");
        String trxTime = dsfTime.format(currTimeMills);
        return trxTime;
    }

}
