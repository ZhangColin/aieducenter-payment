package com.icbc.api;

import static org.junit.Assert.*;

import java.util.logging.Logger;

import org.junit.Test;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.CardbusinessAggregatepayB2cOnlineConsumptionapforsdkRequestV1;
import com.icbc.api.request.CardbusinessAggregatepayB2cOnlineConsumptionapforsdkRequestV1.CardbusinessAggregatepayB2cOnlineConsumptionapforsdkRequestV1Biz;
import com.icbc.api.response.CardbusinessAggregatepayB2cOnlineConsumptionapforsdkResponseV1;

public class CardbusinessAggregatepayB2cOnlineConsumptionapforsdkV1Test {
	// 1、网关公钥
	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
	// 2、密钥对认证方式，公钥在API平台登记，此处为私钥
	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDYLo0htdHwedbvoJA0o5AA/UUE1zb/J4OO9SYmeMBZnLeiNC7TwG53PXgwCSHsp2gM02QatfohUUDHQJg/ezYCSDSMoWfYKv/dfz0DlXp1C8xOnc/im2y0PnZV1YVaTsFHnax91N7nxtfsGVRlT8e2UdkWlW0HX2QOxCBpuevDssfosA151f4JV07VHl+pyHxTS48wGeqDUU0z+hvD8P+KIA01/UOQqXdx47I9Zm5Rm7nfBq1tAxkG6S32D5fsFF2/N+Pa1fTZ//mHFVnn6hbu1c45EFRSBo0O1G+/qq7qTy5+tq5R0Vskgm6FzHJaX2ffQ2tmBwfm/OnZmxKaHmhFAgMBAAECggEAUTE6pFyLWswH47vkLUD5BsYYs/a4myAWEw0TpQNZCs8HUQ5UQAdX9cTKbRAhA6bkN1z1jeqm5PiFYdBq3fzCjhzcT60XOWL88g2ltsDfWzJxK12uBCfnrdJ/00D8cqx9fw5DCId4qIhP9EaXIe3SzjDzXb4FUu2KwNj8a3j+iWLc4rxMo84CRieXLrSIaL6pgE6wxm/G7lcfL2IicrcJGBQEj65Ivyj9UBAmu3TiOFWhQDgZYnAHjIi62MWmoY2hArSDB2BEc4Ul0q4AFj5bK5B7+MAg2bKcNZyKXTFvI5euMTIlqGoFR58oFwI0fh3BLk9ABVgeM+AAPw7hWjk6AQKBgQD+B9RXX7TjjcYx45fc/YB+FsbdFAbdU5q04aLtFpWrkyu9ZQwIlgpDVFuxJ5apgXjjHTsrw6n3oxbuls+Lp3gVMVnWNgtHW/AyqdTnchHjaJ3FTMpPL32suBLSGSHfc8MQmkD1CDZAEoo/HSWxEMhkrZdud1EDEiJPkFeXo1/9hQKBgQDZ25qhh1QmETY9WT42/CrZHfWdMVkodJsvGqRgkgIya+22DpvmSEvT2349fvHYL+Kr7dEl0ZrCHV1knIEkF/ftcQq8qnpNxIT05igPZxIzdnSHjDEeePgZ5Ldsaruvxa/wSYInbIN6knQpxRwJhs4fQQ9qB0aXetMHoH3yubpbwQKBgQDab50VrMR8z5JXn98cNhfhVHCX9fqZIqTrWQKiYEM/EAQSjes1Dt+wWb+mq+YPx7dNg/s3fL6QpHq5mpDJA65ses1HN52nNNVsm0Dp69qZ84GHAKsOQEYO2RHF+7p2zLI1eo7UpMURf1/FOakJgubuO571bEALJfK++912FRQbiQKBgEw15FwJSUif0MeZRohZomudbWR19OiQEhFiUoptyVL2Kov7hFIEjHIqYHkGxXeJGNRoNzfxoR+ywg8GKK8Fq3dmigkB4hL+YjyBnxX0SouyLoWUZ0Jvsurr9bZ5h/qvPyLhtCQyc7QXM4fBKlOy0rxgOBIxRWKMVvJeFHEqw6SBAoGAPuR/+v2VCePORhobY0EIIRLK1sO+Q2IVJIU4ecm0irD99/uwEc+RgJF0IakYlrw1PtmhDYniXO07GMIecj4zsnnPZtkttoNxmikMrK51YsFcsz8I55XzRPPGj1V8CxpfRxmEj9ofyHYHDDvSoHAR28xJdubbn3PuB7nBp94yNWk=";
	// 3、appid
	protected static final String APP_ID = "xxx";
	
	private Logger logger = Logger.getLogger(CardbusinessAggregatepayB2cOnlineConsumptionapforsdkV1Test.class.getName());
	
	
	public void test_cop() throws IcbcApiException {
		
		//4、签名类型为RSA时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA，其他参数使用缺省值
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,IcbcConstants.SIGN_TYPE_RSA,MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
		CardbusinessAggregatepayB2cOnlineConsumptionapforsdkRequestV1 request = new CardbusinessAggregatepayB2cOnlineConsumptionapforsdkRequestV1();
		//5、根据测试环境和生产环境替换相应ip和端口
		request.setServiceUrl("http://ip:port/api/cardbusiness/aggregatepay/b2c/online/consumptionapforsdk/V1");
		CardbusinessAggregatepayB2cOnlineConsumptionapforsdkRequestV1Biz bizContent = new CardbusinessAggregatepayB2cOnlineConsumptionapforsdkRequestV1Biz();
		request.setBizContent(bizContent);
		//6、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
		bizContent.setClientType("xxx");
		bizContent.setInterfaceName("xxx");
		bizContent.setInterfaceVersion("xxx");
		bizContent.setOrderDate("xxx");
		bizContent.setOrderid("xxx");
		bizContent.setAmount("xxx");
		bizContent.setInstallmentTimes("xxx");
		bizContent.setCurType("xxx");
		bizContent.setMerID("xxx");
		bizContent.setMer_prtcl_no("xxx");
		bizContent.setMerAcct("xxx");
		bizContent.setAppName("xxx");
		bizContent.setVerifyJoinFlag("xxx");
		bizContent.setExpireTime("xxx");
		bizContent.setUnipassID("xxx");
		bizContent.setShopAppID("xxx");
		bizContent.setIcbcappid("xxx");
		bizContent.setLanguage("xxx");
		bizContent.setGoodsID("xxx");
		bizContent.setGoodsName("xxx");
		bizContent.setGoodsNum("xxx");
		bizContent.setCarriageAmt("xxx");
		bizContent.setMerHint("xxx");
		bizContent.setRemark1("xxx");
		bizContent.setRemark2("xxx");
		bizContent.setMerURL("xxx");
		bizContent.setMerVAR("xxx");
		bizContent.setNotifyType("xxx");
		bizContent.setResultType("xxx");
		bizContent.setLimitPay("xxx");
		bizContent.setBackup1("xxx");
		bizContent.setBackup2("xxx");
		bizContent.setBackup3("xxx");
		bizContent.setBackup4("xxx");
		bizContent.setIsSupportDISCOUFlag("xxx");
		bizContent.setThirdPartyFlag("xxx");
		bizContent.setMer_custom_ip("xxx");
			
		CardbusinessAggregatepayB2cOnlineConsumptionapforsdkResponseV1 response;
		try {
			boolean testFlag = true;
			response = client.execute(request, System.currentTimeMillis()+"");//msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一
			if (response.getReturnCode() == 0) {
				// 6、业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
				logger.info("ReturnCode:"+response.getReturnCode());
				logger.info("response:" + JSON.toJSONString(response));
			} else {
				// 失败
				testFlag = false;
				logger.info("ReturnCode:"+response.getReturnCode());
				logger.info("response:" + JSON.toJSONString(response));
				logger.info("ReturnMsg:"+response.getReturnMsg());
			}	
			assertEquals(true, testFlag);
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}
	}
}
