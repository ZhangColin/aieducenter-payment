package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.CardbusinessUnionPwdcheckRequestV1;
import com.icbc.api.response.CardbusinessUnionPwdcheckResponseV1;
import com.icbc.api.request.CardbusinessUnionPwdcheckRequestV1.CardbusinessUnionPwdcheckRequestV1Biz;
import org.junit.Test;

import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;

public class CardbusinessUnionPwdcheckV1Test {
	// 1、网关公钥
	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
	// 2、密钥对认证方式，公钥在API平台登记，此处为私钥
	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDYLo0htdHwedbvoJA0o5AA/UUE1zb/J4OO9SYmeMBZnLeiNC7TwG53PXgwCSHsp2gM02QatfohUUDHQJg/ezYCSDSMoWfYKv/dfz0DlXp1C8xOnc/im2y0PnZV1YVaTsFHnax91N7nxtfsGVRlT8e2UdkWlW0HX2QOxCBpuevDssfosA151f4JV07VHl+pyHxTS48wGeqDUU0z+hvD8P+KIA01/UOQqXdx47I9Zm5Rm7nfBq1tAxkG6S32D5fsFF2/N+Pa1fTZ//mHFVnn6hbu1c45EFRSBo0O1G+/qq7qTy5+tq5R0Vskgm6FzHJaX2ffQ2tmBwfm/OnZmxKaHmhFAgMBAAECggEAUTE6pFyLWswH47vkLUD5BsYYs/a4myAWEw0TpQNZCs8HUQ5UQAdX9cTKbRAhA6bkN1z1jeqm5PiFYdBq3fzCjhzcT60XOWL88g2ltsDfWzJxK12uBCfnrdJ/00D8cqx9fw5DCId4qIhP9EaXIe3SzjDzXb4FUu2KwNj8a3j+iWLc4rxMo84CRieXLrSIaL6pgE6wxm/G7lcfL2IicrcJGBQEj65Ivyj9UBAmu3TiOFWhQDgZYnAHjIi62MWmoY2hArSDB2BEc4Ul0q4AFj5bK5B7+MAg2bKcNZyKXTFvI5euMTIlqGoFR58oFwI0fh3BLk9ABVgeM+AAPw7hWjk6AQKBgQD+B9RXX7TjjcYx45fc/YB+FsbdFAbdU5q04aLtFpWrkyu9ZQwIlgpDVFuxJ5apgXjjHTsrw6n3oxbuls+Lp3gVMVnWNgtHW/AyqdTnchHjaJ3FTMpPL32suBLSGSHfc8MQmkD1CDZAEoo/HSWxEMhkrZdud1EDEiJPkFeXo1/9hQKBgQDZ25qhh1QmETY9WT42/CrZHfWdMVkodJsvGqRgkgIya+22DpvmSEvT2349fvHYL+Kr7dEl0ZrCHV1knIEkF/ftcQq8qnpNxIT05igPZxIzdnSHjDEeePgZ5Ldsaruvxa/wSYInbIN6knQpxRwJhs4fQQ9qB0aXetMHoH3yubpbwQKBgQDab50VrMR8z5JXn98cNhfhVHCX9fqZIqTrWQKiYEM/EAQSjes1Dt+wWb+mq+YPx7dNg/s3fL6QpHq5mpDJA65ses1HN52nNNVsm0Dp69qZ84GHAKsOQEYO2RHF+7p2zLI1eo7UpMURf1/FOakJgubuO571bEALJfK++912FRQbiQKBgEw15FwJSUif0MeZRohZomudbWR19OiQEhFiUoptyVL2Kov7hFIEjHIqYHkGxXeJGNRoNzfxoR+ywg8GKK8Fq3dmigkB4hL+YjyBnxX0SouyLoWUZ0Jvsurr9bZ5h/qvPyLhtCQyc7QXM4fBKlOy0rxgOBIxRWKMVvJeFHEqw6SBAoGAPuR/+v2VCePORhobY0EIIRLK1sO+Q2IVJIU4ecm0irD99/uwEc+RgJF0IakYlrw1PtmhDYniXO07GMIecj4zsnnPZtkttoNxmikMrK51YsFcsz8I55XzRPPGj1V8CxpfRxmEj9ofyHYHDDvSoHAR28xJdubbn3PuB7nBp94yNWk=";
	// 3、appid
	protected static final String APP_ID = "xxx";

	private Logger logger = Logger.getLogger(CardbusinessUnionPwdcheckV1Test.class.getName());


	public void test_cop() throws IcbcApiException {

		// 4、签名类型为RSA时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA，其他参数使用缺省值
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA, MY_PRIVATE_KEY,
				APIGW_PUBLIC_KEY);
		CardbusinessUnionPwdcheckRequestV1 request = new CardbusinessUnionPwdcheckRequestV1();
		// 5、根据测试环境和生产环境替换相应ip和端口
		request.setServiceUrl("http://ip:port/api/cardbusiness/union/pwdcheck/V1");
		CardbusinessUnionPwdcheckRequestV1Biz bizContent = new CardbusinessUnionPwdcheckRequestV1Biz();
		request.setBizContent(bizContent);
		// 6、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值

		bizContent.setAppstat_transok("xxx");
		bizContent.setAppstat_errno("xxx");
		bizContent.setAppstat_msgno("xxx");
		bizContent.setAppstat_tsfstat("xxx");
		bizContent.setChancomm_chantype("xxx");
		bizContent.setChancomm_channo("xxx");
		bizContent.setChancomm_mac("xxx");
		bizContent.setChancomm_ip("xxx");
		bizContent.setChancomm_oapp("xxx");
		bizContent.setChancomm_serialno("xxx");
		bizContent.setChancomm_mserialn("xxx");
		bizContent.setChancomm_oserialn("xxx");
		bizContent.setChancomm_authzone("xxx");
		bizContent.setChancomm_authbrno("xxx");
		bizContent.setChancomm_authflag("xxx");
		bizContent.setChancomm_tellpass("xxx");
		bizContent.setChancomm_tritmsp("xxx");
		bizContent.setChancomm_prodid("xxx");
		bizContent.setChancomm_cobprodid("xxx");
		bizContent.setChancomm_flag1("xxx");
		bizContent.setChancomm_flag2("xxx");
		bizContent.setChancomm_flag3("xxx");
		bizContent.setChancomm_sevlevel("xxx");

		bizContent.setMdcardno("xxx");
		bizContent.setDeskey("xxx");
		bizContent.setTermid("xxx");
		bizContent.setTrack2("xxx");
        bizContent.setEntrymode("xxx");
        bizContent.setEmvflag("xxxx");
        bizContent.setEmvcharhpinf("xxxx");
        bizContent.setEmvcarserno("xxx");
        bizContent.setEmvcode("xxx");
        bizContent.setEmvwrmod("xxxx");



		bizContent.setChancomm_field1("xxx");
		bizContent.setChancomm_field2("xxx");
		bizContent.setChancomm_field3("xxx");
		bizContent.setChancomm_field4("xxx");
		bizContent.setChancomm_field5("xxx");
		bizContent.setChancomm_field6("xxx");
		bizContent.setChancomm_field7("xxx");
		bizContent.setChancomm_field8("xxx");
		bizContent.setChancomm_field9("xxx");
		bizContent.setCtrlcomm_trxno("xxx");
		bizContent.setCtrlcomm_ckusrf("xxx");
		bizContent.setCtrlcomm_flag1("xxx");
		bizContent.setCtrlcomm_flag6("xxx");
		bizContent.setInfocomm_trxcode("xxx");
		bizContent.setInfocomm_zoneno("xxx");
		bizContent.setInfocomm_brno("xxx");
		bizContent.setInfocomm_tellerno("xxx");
		bizContent.setInfocomm_authtlno("xxx");
		bizContent.setInfocomm_authcode("xxx");
		bizContent.setInfocomm_vagenlevel("xxx");
		bizContent.setInfocomm_cardno("xxx");
		bizContent.setInfocomm_authpwd("xxx");
		bizContent.setInfocomm_dutyno("xxx");
		bizContent.setInfocomm_workdate("xxx");
		bizContent.setInfocomm_worktime("xxx");
		bizContent.setInfocomm_servface("xxx");
		bizContent.setInfocomm_operflag("xxx");
		bizContent.setInfocomm_termid("xxx");
		bizContent.setInfocomm_chantype("xxx");
		bizContent.setInfocomm_apptype("xxx");
		bizContent.setInfocomm_chnseqno("xxx");
		bizContent.setInfocomm_intrxcode("xxx");
		bizContent.setInfocomm_mac("xxx");
		bizContent.setInfocomm_ip("xxx");
		bizContent.setInfocomm_zoneweb("xxx");
		bizContent.setInfocomm_brnoweb("xxx");
		bizContent.setInfocomm_zonecrd("xxx");
		bizContent.setInfocomm_brnocrd("xxx");
		bizContent.setInfocomm_regf("xxx");
		bizContent.setInfocomm_intelno("xxx");
		bizContent.setInfocomm_aintelno("xxx");
		bizContent.setTrancomm_mdcardno("xxx");
		bizContent.setTrancomm_accpin("xxx");
		bizContent.setBackup_flag1("xxx");
		bizContent.setBackup_flag2("xxx");
		bizContent.setBackup_flag3("xxx");
		bizContent.setBackup_flag4("xxx");
		bizContent.setBackup_flag5("xxx");
		bizContent.setBackup_data1("xxx");
		bizContent.setBackup_data2("xxx");
		bizContent.setBackup_data3("xxx");
		bizContent.setBackup_data4("xxx");
		bizContent.setBackup_data5("xxx");
		bizContent.setBackup_value1("xxx");
		bizContent.setBackup_value2("xxx");
		bizContent.setBackup_value3("xxx");
		bizContent.setBackup_value4("xxx");
		bizContent.setBackup_value5("xxx");
		bizContent.setBackup1("xxx");

		CardbusinessUnionPwdcheckResponseV1 response;
		try {
			boolean testFlag = true;
			response = client.execute(request, System.currentTimeMillis() + "");// msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一
			if ("0".equals(response.getAppstat_transok())) {
// 6、业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
				logger.info("appstat_transok:" + response.getAppstat_transok());
				logger.info("response:" + JSON.toJSONString(response));
			} else {
// 失败
				testFlag = false;
				logger.info("response:" + JSON.toJSONString(response));
				logger.info("appstat_transok:" + response.getAppstat_transok());
				logger.info("appstat_errno:" + response.getAppstat_errno());
			}
			assertEquals(true, testFlag);
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}
	}
}
