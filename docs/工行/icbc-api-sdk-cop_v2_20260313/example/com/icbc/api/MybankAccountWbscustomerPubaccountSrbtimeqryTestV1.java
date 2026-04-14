package com.icbc.api;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcConstants;
import com.icbc.api.request.MybankAccountWbscustomerPubaccountSrbtimeqryRequestV1;
import com.icbc.api.request.MybankAccountWbscustomerPubaccountSrbtimeqryRequestV1.MybankAccountWbscustomerPubaccountSrbtimeqryRequestV1Biz;
import com.icbc.api.response.MybankAccountWbscustomerPubaccountSrbtimeqryResponseV1;
import java.util.logging.Logger;

public class MybankAccountWbscustomerPubaccountSrbtimeqryTestV1 {
	// 1、网关公钥
	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

	// 2、appid 通行证第三方测试1 (行外)
	protected static final String APP_ID = "xxxxxxxxxxxxxxxxxxxx";

	// 3、密钥对认证方式，公钥在API平台登记，此处为私钥 行外访问
	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCtYYdczCKq6KaKFbvin43MszzGnYrShnEml/TnFJZcbPxX4x2xO+3cuLxOOucp876/yXKNYUrUjy6aPLspXnKFsL8NeAXSrc0Oj8paHJNgzqD+7OPu90VQuxf9jID34MZXYOUr81p/lH3fPZGQ30iJlDF1AGMCZIlTQAssQa5xNFZ7CbqS3fCL7aOVLfEkulbLwQ5ln50ebKZRCKGN4vyciLEp5/BZ9Wk22GZTMcAwgiCFOvydSaFV9gJxXrd98Y7CABU8rlL+kIXySDL+qC+MALuboFcYAO+kdv5TA6DK6syPE/xuxM2ESlNHCpkJl2hBhmV+muYWGPxbpUdGRUlbAgMBAAECggEAMdmXzNOYqCEqOQuUsM0FPTTPpZ+IWY10HQ+Vx5cAwGRJjY9saxf7BPpMkgWXj1VYo9XszL+Lf2AHmysY+QUB0nSGCf5XVkLIcYZgPNZ4fV4LpiBATOXllRemBy2NqzeCb38ZhTB79bChHD2ci9XXCk+EhMpurTJw1XHVhI0B9eQICGXnmi20E9B/tHpg7mokHBs/0DY82SHRmgApvdu9B1g+hZ7ndQRbHoD1cHPGZnHDD5tFtvBLynGvyyP8nDGogpgVG85lcxBQ1lDpCv8KQUGcqtU5a+SfNgmY4mT8ousaxra0rO+HOJAOdRs9pdjH2xb+DBOLnkH7NP0iPj/EWQKBgQDhZ1JUbXmisqm8OGodoYFW9w1zmwlaHD2KKonOQM6j6WYrPno0JgP7gw6SidljcdyyLER4oZ+EByg0/RCDsOpWFu8TrIJPFllaH61h0zJeYaqfrWHW65zuyHfSgaA2Va7a2PNq27Zw/hWpjDFdq+vSdq7S4uzooGt+1D6pzLOhpwKBgQDE6nFx6Q1unGftN91H3Y38Y+f6xRID7IhVHmNGzvtWFxHCh0RrU6s26X4ZmV7KWmgqRuOc9voqo6DKAX5eL8Ro4Z6aVrJPEmcvbY5QKKyJr4jocouqV7O5al3TVS/3J8HMDbyHsFFFJD9M74Z/L43BIh7J0grlGk3WxoDAWEUJLQKBgQC7qCuRAru7yocpnsYp+SYXYwOd9btpfA14FEY6oxXmvRBEfHV9KREzuj7NnVqRQmB9LgGrdNO7NcIxq2auXQIbvmg4tuyeL3sgXzMYOJ68mQ9WdSD5r5st6VRou2bJFbjHlsvBcT6I/+DFEWSgGdvxksGl7StT2QrlwoqRD02WDQKBgGtoQXbTmtsBfwJ6XT2M0JyhVfCHu7reMsLS7225Coi7zpkftSchnRs3ouuNp5GSXIzq285LPMszM0ncJLR1M5nNrvgaX+oXbKcHCAAbH3l+gJvhluLUMZjqNsFGDwKt8dw+hd4xQzMGgWySnfcT1itJVCfbZMvwXBLRdXr06rsFAoGAV8d6ApMgdkIwII2/iYr1OgZ7FNeN9op+lG5yRZioXMZmFYl6blQkfzfPojm911Tz6fvTAED6lMv7IEuaryfjuWj2MprKhhl/+Bpl/buHhe7VnTyNdvCpdR48PPLYVLVqeY5obS9cndKZVIC+Zd43ZHyG4Noos3VpAlhQA/thiug=";

	private static Logger logger = Logger.getLogger(MybankAccountWbscustomerPubaccountSrbtimeqryTestV1.class.getName());
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// 签名类型为SHA256WithRSA时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

		MybankAccountWbscustomerPubaccountSrbtimeqryRequestV1 request = new MybankAccountWbscustomerPubaccountSrbtimeqryRequestV1();

		// 4、根据测试环境和生产环境替换相应ip和端口
		// 行外流程
		request.setServiceUrl("http://ip:port/api/wbs/customer/pubaccount/srbtimeqry/V1");

		// 5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
		MybankAccountWbscustomerPubaccountSrbtimeqryRequestV1Biz bizContent = new MybankAccountWbscustomerPubaccountSrbtimeqryRequestV1Biz();

		bizContent.setAppid("xxxxxxxxxxxxxxxxxxxx");
		bizContent.setParabcode("DZYH");
		bizContent.setOpenid("oITMP1C64i488MWhhaOoNTaAYMVQ");

		request.setBizContent(bizContent);
		// 建立返回项
		MybankAccountWbscustomerPubaccountSrbtimeqryResponseV1 response = new MybankAccountWbscustomerPubaccountSrbtimeqryResponseV1();
		try {
			response = client.execute(request);
			logger.info("return_code:" + response.getReturnCode());
			logger.info("return_msg:" + response.getReturnMsg());
			logger.info("tranerrorcode:" + response.getTranerrorcode());
			logger.info("tranerrormsg:" + response.getTranerrormsg());
			logger.info("subscrb_time:" + response.getSubscrb_time());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
