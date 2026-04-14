package com.icbc.api;

import com.icbc.api.request.MasaInfocheckIdImageCheckRequestV1;
import com.icbc.api.response.MasaInfocheckIdImageCheckResponseV1;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * @ClassName MasaInfocheckIdImageCheckTestV1
 * @Description TODO
 * @author: kfzx-lij1
 * @create: 2022/11/28
 **/
public class MasaInfocheckIdImageCheckTestV1 {

    protected static Logger log = Logger.getLogger(MasaInfocheckIdImageCheckTestV1.class.getName());

    protected static String APP_ID = "90000000000000003382";

    protected static String APIGW_PUBLIC_KEY =
            "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY"
                    + "0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

    protected static String PRI_KEY =
            "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC3NdXULtLHXlmvn8nGuac8Us84tmdEd9UuXLAY7j4c8KAcNnPDRLDJkLD2p+gYVw+EF0WrVgdZwXmIo95rJnW4HQMTlkzCvMZD5I7u1d9EbyoGL8vTYSmcYbitDj5s3lsSAOSHfmRGBMV3JkSk2yhB6RTyW7CXqiQ5ty3BxLAcHvKLDpbStQrxLvU1rnmWGowm8uVeuZZGe18I+PlV+MRrE+5xHXl8N5C3fiNHxDLPjFlJLnPLS5gkIo/URoQOGrHzf4U58B6rQBRp2+EAkx/pP8tFn8XdWUj1YZ+Jqbahp5Xvz6dTyihHVBfAFWXZanVSh+9JWURdCf+GUYbeEhQ7AgMBAAECggEANJBymB0TBnZwfu32Y1jSvYD4yUm0GTXUaSfYsl5wXbdACLgqwISNwFcOHsaP1aPs5LYUnBLjqwHSj7yMTOvM7JUOU+lUfq7Qp5wE0kt0clr+kKXX8f63hJGY/bXPfUbSlDjDWIiP1nL6ALRvD2l9hGOOSfa6lNkQ6ao/q492A6r0kDViM3c7JV0/L1mDmG1o44iu0tIxbU4T5ZIMOuueAO1XzkJZypK4/TUjpHLAmGk94w555Ew3YYYKvs3LibY/WW1ClrDbNbgjlwWRKQkKsseVZ5yi1uwG4WsxLl+/0FihnLzEjG7YgX+pJmZt9Mqtup5HOqAyImlTD4IU9L5gwQKBgQDuK1xQpwJk8PppwBzicVW4noVpcZFnQflqLPvK9a5ZYnmijXqqGPPdBIURO4F9eovE4/NmlAfy7dpD4W1m9Hh2HWLvNxbzB4EaSvr/V4jtHROMNv4sKfdYTxEas1+5gFF+qVo4pGM0+tIK1IrxShyX9AbcYA9baTV87VDsXfV1WwKBgQDE7SfHPdOybE1DCKPxp+utxB7521y01MfdsQ/nf2cXjbXbRciFxfcR5rUl52nWOSmAeQyHx6J2qpoXTVaxQADXrSZf9fnqYDYrux7MoXsYrE9ssQPWyG7WA1EKrUuwJotytSDvSc2DCiyYR+VlzFutPJNtw7S4wuDkqu5ham6yoQKBgQDoPB+oBTGwe8mxlEKWCy0q+GfeiX7e0NYDUhmzrhxzzDIO7yXM9sfTm7SlOQDq+bOQKTYNsAqQNp290vJ6kadkhQMTfwRBte+NU8+rbA94JgIJlxYAumsVEQP8pSuSFi1g9IGk+aZC4tAs/1L8NOfsw2ZtPL28xLoDNuTRlkziQQKBgHhDvX9YqujUa3r80+LJgo1ksOY2qa9mI4mokOX4UKV5zg5T19qaxAESvJ6hbWl/D3F+e8CQkw+VsPV6Le+h/uG4xS7lsfMJPwwGAxFFrT+sO/VNReLuFYJuFBhJLWguogszwyFgGTpmBEYb4mUBIRvzWrY2RzBvE5mTKnIj5ecBAoGAVaNA2I3wwuvcazxr0M9sWECf9cpfuaUmqSn42NBSL3Y1QgX/zI3gOuFDjHbdreTf7QoQLvcXLHhLBTMrUro+3YabYRLo3gs0dR9GyXJcqr+IJ3LASm5horgzA+Tm5bDoo+nvoFxe5WaXX5AyFG1uokqe0dx5lr6vGlvZK9+TFwI=";

    protected static String BASE_URL = "http://ip:port/api";

    public static void main(String[] args) {

        String URI = "masa/infocheck/idImage/V1";
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, PRI_KEY, APIGW_PUBLIC_KEY);
        try {
            MasaInfocheckIdImageCheckRequestV1.MasaInfocheckIdImageCheckRequestV1Biz bizContent = new MasaInfocheckIdImageCheckRequestV1.MasaInfocheckIdImageCheckRequestV1Biz();
            MasaInfocheckIdImageCheckRequestV1 request = new MasaInfocheckIdImageCheckRequestV1();

            MasaInfocheckIdImageCheckRequestV1.MasaInfocheckIdImageCheckReqChanCommEntity chanCommEntity = new MasaInfocheckIdImageCheckRequestV1.MasaInfocheckIdImageCheckReqChanCommEntity();
            chanCommEntity.setChannelSerialNo("202208187508286800018108150");
            chanCommEntity.setChannelType("000");
            chanCommEntity.setApp("masa");

            MasaInfocheckIdImageCheckRequestV1.MasaInfocheckIdImageCheckReqInfoCommEntity infoCommEntity = new MasaInfocheckIdImageCheckRequestV1.MasaInfocheckIdImageCheckReqInfoCommEntity();
            infoCommEntity.setTrxCode("76468");
            infoCommEntity.setWorkdate("20221128");
            infoCommEntity.setWorktime("111500");
            infoCommEntity.setZoneNo("00200");
            infoCommEntity.setBranchNo("00998");
            infoCommEntity.setTellerNo("93300");

            MasaInfocheckIdImageCheckRequestV1.MasaInfocheckIdImageCheckReqFeedbackCommEntity feedbackCommEntity = new MasaInfocheckIdImageCheckRequestV1.MasaInfocheckIdImageCheckReqFeedbackCommEntity();
            feedbackCommEntity.setUserCode("00200 00998 93300");
            feedbackCommEntity.setBusinessCode("01");
            feedbackCommEntity.setBnkCode("102100099996");
            feedbackCommEntity.setMainTrade("76468");
            feedbackCommEntity.setCount("1");

            MasaInfocheckIdImageCheckRequestV1.CertifInfoQueryUser certifInfoQueryUser = new MasaInfocheckIdImageCheckRequestV1.CertifInfoQueryUser();
            certifInfoQueryUser.setIdType("0");
            certifInfoQueryUser.setId("110110199909092344");
            certifInfoQueryUser.setName("张三");
            certifInfoQueryUser.setSignDate("20221128");

            List<MasaInfocheckIdImageCheckRequestV1.CertifInfoQueryUser> list = new ArrayList<>();
            list.add(certifInfoQueryUser);
            feedbackCommEntity.setCertifInfoQueryUser(list);


            bizContent.setChanCommEntity(chanCommEntity);
            bizContent.setInfoCommEntity(infoCommEntity);
            bizContent.setFeedbackCommEntity(feedbackCommEntity);

            request.setServiceUrl(BASE_URL + "/" + URI);
            request.setBizContent(bizContent);
            MasaInfocheckIdImageCheckResponseV1 response = client.execute(request);
            if ("0".equals(response.getTransStatCommEntity().getTransOk())) {
                log.info("success");
            } else {
                log.info(response.getTransStatCommEntity().getReturnCode() + ";" + response.getTransStatCommEntity().getReturnMsg());
                log.info("error");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.info(e.getMessage());
        }
    }
}
