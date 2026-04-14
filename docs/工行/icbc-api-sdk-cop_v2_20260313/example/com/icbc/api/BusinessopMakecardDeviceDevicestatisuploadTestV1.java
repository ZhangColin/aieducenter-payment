package com.icbc.api;

import java.util.logging.Logger;

import org.junit.Test;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.BusinessopMakecardDeviceDevicestatisuploadRequestV1;
import com.icbc.api.request.BusinessopMakecardDeviceDevicestatisuploadRequestV1.BusinessopMakecardDeviceDevicestatisuploadRequestV1Biz;
import com.icbc.api.response.BusinessopMakecardDeviceDevicestatisuploadResponseV1;

public class BusinessopMakecardDeviceDevicestatisuploadTestV1 {
	
	// 1、网关公钥
	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
	
	// 2、密钥对认证方式，公钥在API平台登记，此处为私钥
	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDYLo0htdHwedbvoJA0o5AA/UUE1zb/J4OO9SYmeMBZnLeiNC7TwG53PXgwCSHsp2gM02QatfohUUDHQJg/ezYCSDSMoWfYKv/dfz0DlXp1C8xOnc/im2y0PnZV1YVaTsFHnax91N7nxtfsGVRlT8e2UdkWlW0HX2QOxCBpuevDssfosA151f4JV07VHl+pyHxTS48wGeqDUU0z+hvD8P+KIA01/UOQqXdx47I9Zm5Rm7nfBq1tAxkG6S32D5fsFF2/N+Pa1fTZ//mHFVnn6hbu1c45EFRSBo0O1G+/qq7qTy5+tq5R0Vskgm6FzHJaX2ffQ2tmBwfm/OnZmxKaHmhFAgMBAAECggEAUTE6pFyLWswH47vkLUD5BsYYs/a4myAWEw0TpQNZCs8HUQ5UQAdX9cTKbRAhA6bkN1z1jeqm5PiFYdBq3fzCjhzcT60XOWL88g2ltsDfWzJxK12uBCfnrdJ/00D8cqx9fw5DCId4qIhP9EaXIe3SzjDzXb4FUu2KwNj8a3j+iWLc4rxMo84CRieXLrSIaL6pgE6wxm/G7lcfL2IicrcJGBQEj65Ivyj9UBAmu3TiOFWhQDgZYnAHjIi62MWmoY2hArSDB2BEc4Ul0q4AFj5bK5B7+MAg2bKcNZyKXTFvI5euMTIlqGoFR58oFwI0fh3BLk9ABVgeM+AAPw7hWjk6AQKBgQD+B9RXX7TjjcYx45fc/YB+FsbdFAbdU5q04aLtFpWrkyu9ZQwIlgpDVFuxJ5apgXjjHTsrw6n3oxbuls+Lp3gVMVnWNgtHW/AyqdTnchHjaJ3FTMpPL32suBLSGSHfc8MQmkD1CDZAEoo/HSWxEMhkrZdud1EDEiJPkFeXo1/9hQKBgQDZ25qhh1QmETY9WT42/CrZHfWdMVkodJsvGqRgkgIya+22DpvmSEvT2349fvHYL+Kr7dEl0ZrCHV1knIEkF/ftcQq8qnpNxIT05igPZxIzdnSHjDEeePgZ5Ldsaruvxa/wSYInbIN6knQpxRwJhs4fQQ9qB0aXetMHoH3yubpbwQKBgQDab50VrMR8z5JXn98cNhfhVHCX9fqZIqTrWQKiYEM/EAQSjes1Dt+wWb+mq+YPx7dNg/s3fL6QpHq5mpDJA65ses1HN52nNNVsm0Dp69qZ84GHAKsOQEYO2RHF+7p2zLI1eo7UpMURf1/FOakJgubuO571bEALJfK++912FRQbiQKBgEw15FwJSUif0MeZRohZomudbWR19OiQEhFiUoptyVL2Kov7hFIEjHIqYHkGxXeJGNRoNzfxoR+ywg8GKK8Fq3dmigkB4hL+YjyBnxX0SouyLoWUZ0Jvsurr9bZ5h/qvPyLhtCQyc7QXM4fBKlOy0rxgOBIxRWKMVvJeFHEqw6SBAoGAPuR/+v2VCePORhobY0EIIRLK1sO+Q2IVJIU4ecm0irD99/uwEc+RgJF0IakYlrw1PtmhDYniXO07GMIecj4zsnnPZtkttoNxmikMrK51YsFcsz8I55XzRPPGj1V8CxpfRxmEj9ofyHYHDDvSoHAR28xJdubbn3PuB7nBp94yNWk=";
	
	// 3、appid
	protected static final String APP_ID = "xxx";
	
	private static Logger logger = Logger.getLogger(BusinessopMakecardDeviceDevicestatisuploadTestV1.class.getName());
	

	public static void main(String[] args) throws Exception {
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
		BusinessopMakecardDeviceDevicestatisuploadRequestV1 request = new BusinessopMakecardDeviceDevicestatisuploadRequestV1();
		//1、根据测试环境和生产环境替换相应ip和端口
		request.setServiceUrl("https://ip:port/api/businessop/makecard/device/devicestatisupload/V1");
		BusinessopMakecardDeviceDevicestatisuploadRequestV1Biz bizContent = new BusinessopMakecardDeviceDevicestatisuploadRequestV1Biz();
		//2、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
		bizContent.setUuid("202009259e19097e9f664c0f9fb8dd6083efb163");
		bizContent.setResourceid("1234567890AB");
		bizContent.setType("0");
		bizContent.setSubtype("");
		bizContent.setValue("");
		bizContent.setDate("20200910");
		bizContent.setTime("16:30:01");
		bizContent.setDeviceip("IP");
		bizContent.setDeviceos("1.0");
		bizContent.setAntivirus("Y");
		bizContent.setAntivirusversion("Y1.0");
		bizContent.setCardboxModule("Y");
		bizContent.setPrintModule("Y");
		bizContent.setCharModule("Y");
		bizContent.setSmartcardModule("Y");
		bizContent.setCleanModule("Y");
		bizContent.setColorModule("Y");
		bizContent.setMagModule("Y");
		bizContent.setCardoutModule("Y");
		bizContent.setCardDistributeModule("Y");
		bizContent.setMailpackModule("Y");
		request.setBizContent(bizContent);


		BusinessopMakecardDeviceDevicestatisuploadResponseV1 response = null;
		response = client.execute(request, "msgId");
		if (response.isSuccess()) {
			// 成功
			logger.info("success response:" + JSON.toJSONString(response));
			logger.info("return_code:" + response.getReturnCode());
		} else {
			// 失败
			logger.info("error response:" + JSON.toJSONString(response));
		}
	}

}
