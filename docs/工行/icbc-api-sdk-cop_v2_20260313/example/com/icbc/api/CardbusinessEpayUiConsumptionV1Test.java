package com.icbc.api;

import static org.junit.Assert.assertEquals;

import java.io.UnsupportedEncodingException;
import java.util.logging.Logger;

import org.junit.Test;

import com.icbc.api.request.CardbusinessEpayUiConsumptionRequestV1;
import com.icbc.api.request.CardbusinessEpayUiConsumptionRequestV1.CardbusinessEpayUiConsumptionRequestV1Biz;

public class CardbusinessEpayUiConsumptionV1Test {
	// 1、网关公钥
	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
	// 2、密钥对认证方式，公钥在API平台登记，此处为私钥
	protected final static String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDYLo0htdHwedbvoJA0o5AA/UUE1zb/J4OO9SYmeMBZnLeiNC7TwG53PXgwCSHsp2gM02QatfohUUDHQJg/ezYCSDSMoWfYKv/dfz0DlXp1C8xOnc/im2y0PnZV1YVaTsFHnax91N7nxtfsGVRlT8e2UdkWlW0HX2QOxCBpuevDssfosA151f4JV07VHl+pyHxTS48wGeqDUU0z+hvD8P+KIA01/UOQqXdx47I9Zm5Rm7nfBq1tAxkG6S32D5fsFF2/N+Pa1fTZ//mHFVnn6hbu1c45EFRSBo0O1G+/qq7qTy5+tq5R0Vskgm6FzHJaX2ffQ2tmBwfm/OnZmxKaHmhFAgMBAAECggEAUTE6pFyLWswH47vkLUD5BsYYs/a4myAWEw0TpQNZCs8HUQ5UQAdX9cTKbRAhA6bkN1z1jeqm5PiFYdBq3fzCjhzcT60XOWL88g2ltsDfWzJxK12uBCfnrdJ/00D8cqx9fw5DCId4qIhP9EaXIe3SzjDzXb4FUu2KwNj8a3j+iWLc4rxMo84CRieXLrSIaL6pgE6wxm/G7lcfL2IicrcJGBQEj65Ivyj9UBAmu3TiOFWhQDgZYnAHjIi62MWmoY2hArSDB2BEc4Ul0q4AFj5bK5B7+MAg2bKcNZyKXTFvI5euMTIlqGoFR58oFwI0fh3BLk9ABVgeM+AAPw7hWjk6AQKBgQD+B9RXX7TjjcYx45fc/YB+FsbdFAbdU5q04aLtFpWrkyu9ZQwIlgpDVFuxJ5apgXjjHTsrw6n3oxbuls+Lp3gVMVnWNgtHW/AyqdTnchHjaJ3FTMpPL32suBLSGSHfc8MQmkD1CDZAEoo/HSWxEMhkrZdud1EDEiJPkFeXo1/9hQKBgQDZ25qhh1QmETY9WT42/CrZHfWdMVkodJsvGqRgkgIya+22DpvmSEvT2349fvHYL+Kr7dEl0ZrCHV1knIEkF/ftcQq8qnpNxIT05igPZxIzdnSHjDEeePgZ5Ldsaruvxa/wSYInbIN6knQpxRwJhs4fQQ9qB0aXetMHoH3yubpbwQKBgQDab50VrMR8z5JXn98cNhfhVHCX9fqZIqTrWQKiYEM/EAQSjes1Dt+wWb+mq+YPx7dNg/s3fL6QpHq5mpDJA65ses1HN52nNNVsm0Dp69qZ84GHAKsOQEYO2RHF+7p2zLI1eo7UpMURf1/FOakJgubuO571bEALJfK++912FRQbiQKBgEw15FwJSUif0MeZRohZomudbWR19OiQEhFiUoptyVL2Kov7hFIEjHIqYHkGxXeJGNRoNzfxoR+ywg8GKK8Fq3dmigkB4hL+YjyBnxX0SouyLoWUZ0Jvsurr9bZ5h/qvPyLhtCQyc7QXM4fBKlOy0rxgOBIxRWKMVvJeFHEqw6SBAoGAPuR/+v2VCePORhobY0EIIRLK1sO+Q2IVJIU4ecm0irD99/uwEc+RgJF0IakYlrw1PtmhDYniXO07GMIecj4zsnnPZtkttoNxmikMrK51YsFcsz8I55XzRPPGj1V8CxpfRxmEj9ofyHYHDDvSoHAR28xJdubbn3PuB7nBp94yNWk=";
	// 3、appid
	protected static final String APP_ID = "xxx";
	
	private Logger logger = Logger.getLogger(CardbusinessEpayUiConsumptionV1Test.class.getName());
	

	public void test_cop() throws IcbcApiException, UnsupportedEncodingException {
		
		//4、需传入appid，私钥，字符集
		UiIcbcClient client = new UiIcbcClient(APP_ID,MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8);
		CardbusinessEpayUiConsumptionRequestV1 request = new CardbusinessEpayUiConsumptionRequestV1();
		//5、根据测试环境和生产环境替换相应ip和端口
		request.setServiceUrl("https://ip:port/ui/cardbusiness/epay/ui/consumption/V1");
		CardbusinessEpayUiConsumptionRequestV1Biz bizContent = new CardbusinessEpayUiConsumptionRequestV1Biz();
		request.setBizContent(bizContent);
		//6、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
		bizContent.setClientType("xxx");
		bizContent.setIcbc_appid("xxx");
		bizContent.setOrder_date("xxx");
		bizContent.setOut_trade_no("xxx");
		bizContent.setAmount("xxx");
		bizContent.setInstallment_times("xxx");
		bizContent.setCur_type("xxx");
		bizContent.setMer_id("xxx");
		bizContent.setMer_prtcl_no("xxx");
		bizContent.setMer_acct("xxx");
		bizContent.setMerchant_info("xxx");
		bizContent.setLanguage("xxx");
		bizContent.setGoods_id("xxx");
		bizContent.setGoods_name("xxx");
		bizContent.setGoods_num("xxx");
		bizContent.setCarriage_amt("xxx");
		bizContent.setMer_hint("xxx");
		bizContent.setRemark1("xxx");
		bizContent.setRemark2("xxx");
		bizContent.setMer_reference("xxx");
		bizContent.setMer_custom_ip("xxx");
		bizContent.setReturn_url("xxx");
		bizContent.setMer_var("xxx");
		bizContent.setAuto_refer_sec("xxx");
		bizContent.setBackup1("xxx");
		bizContent.setMer_url("xxx");
		try {
			boolean testFlag = true;
			logger.info(client.buildPostForm(request));
			assertEquals(true, testFlag);
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}
	}
}
