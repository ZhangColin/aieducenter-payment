package com.icbc.api;

import java.util.logging.Logger;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.CardbusinessEparkMembersyncRequestV1;
import com.icbc.api.request.CardbusinessEparkMembersyncRequestV1.CardbusinessEparkMembersyncRequestV1Biz;
import com.icbc.api.response.CardbusinessEparkMembersyncResponseV1;

/**
 * 银行卡场景E停车会员同步
 * 
 * 请商户配置SDK中的lib文件夹中的jar包后，根据备注1-6提示进行数据替换
 */
public class CardbusinessEparkMembersyncTestV1 {
	// 1、网关公钥
	protected static final String APIGW_PUBLIC_KEY = "XXXXXX";
	// 2、appid
	protected static final String APP_ID = "XXXXXX";

	// 3、密钥对认证方式，公钥在API平台登记，此处为私钥
//  String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCpx55HRWBecD9PDGnjgG14gySvwgsWahRXl+6wvlsC/oT1Gr4vQyVIQ6JJDkWFopyKrbcfSm5YPanGLiM2iBZ4/Vsa5mUGmr7Ne4DAD2zEFhDPlFrMuyP7h5t89qtnL9Q4vgz89W35qDAiJ3lLjutRgk2LmyvYFpgTazdH/34cIkBIfhlTLLUGKEXL2fEc0A5oKkLPHtu1/YF6u8rlsu7Xe6WLOBeHk9KqBR7BH+5ctL75oZBBRbwKauFPRWE/tQcjXv+qxJJVq6TfbQCRPKpzdmt/YcsiWnJwL1CEeXndXsY6mX14fSH1jHmU2cnkjccudA/bBJAKPSzFfQ1FCp2NAgMBAAECggEBAKiAqiJqRJ72IJ8/3sggaoW1AvA9UevJFK9q50cbZhCusx7gj+kwZww1tBd1xqI/JcatapXvtVx2ts0udVxl7IfgTE+CYroPU1d1P+9eX8CtdbnPr96JzciSaku4NZ2P5rz6QsG8hI3H0x3St5u6GOsou/XW58jrg7uiKZeXh347DwtxFJEhgjZBGUDw1+GOWapz1WFXfkjxEiikYTW6YSLYGHR765YXG5wpanIqknz1iTBkXxedlzc4WbIfJMYNlilrYGUhEwaMuCHHz6HIVqSRQxmmJT+ZwZ4aCFeZnvBKe7dDRrhisqigJh0QqqnwO0aIVc0fBdKBFbb0sxrkaYECgYEA7ML1DPoGGMrpWOUiFzwpOkg3ZpL/9+KyReTzxAY/eMV9C4dUMXSE0yh46YueeYiqlinj88Ob7Q2AmzHh4M+J9BbmyR8pV8uM/W7X7Rzs9YkgcB2dQcU7WBfSaErwvIVTR62ABLoCgv9q74sWdPOTGxfIGQafzJNBEKmUdJxJx9kCgYEAt5NTbUk99cONlI7k98Ow8FVcAnx1qvmZfuVukRb6lufgbLhCKy3bz23hYkyUyVLl8Q6o+Vgg/TTuXQ8DCCPF5F/Cv/s8S+/ulF1sT7J/P9x3a/tCSCICfiRNM5jByG9zDZI4UgxzDV8AR2BvH19sCL355ENA/8ljkrvZkbfSRtUCgYEA5rMVOp7JoBDgDWm/rSpG9e52ZTGpz24KzgZI7OI1qMWukbQ8+q+R2XtfThpowf3EUuvgnZ8l6Xe1sAOMGJXB06EZIjKGA3FpIW2HYxlDYAFNJVIF8JdxuuYDdPJI3zl+q6JI0tI5LlIZbe52ZGEPH2At7wzAWwPFa6oB6Rp83gkCgYBo6kMgz14g6j0tLSmAzI+AG1i1dwoMyA/IrTkupLJcsnLZz8jJQrFAcG0CMABMuxr9VMKPlqVr8OdVufGiokdECNKVw/Em+R4iHu568em1xrtdtECiVwWTsA2LMn91nPRlSi7z/Xd3qdbP9DYt+OwWZvS9Vw574U4OG1vx4tvDbQKBgQCpIFfrj19CQU/+oWwtkdfdK/E/hMfK1ESFHfWAX+Pq4pIyEIz/Kb3IweKy9uEGbE/zNfi3m5ClCf9H3B4BYk5EKbSk3lVDKg3F4U+1ETNE4fwvKdBfCgpJMi12xdTtkuhfrVUjhaw7T0pFc8fJkP5P5Q+elY6iwYgn+JPm+2yb8A==";
  static final String MY_PRIVATE_KEY = "XXXXXX";
	public static void main(String[] args) throws Exception {

		// 签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY,
				APIGW_PUBLIC_KEY);

		CardbusinessEparkMembersyncRequestV1 request = new CardbusinessEparkMembersyncRequestV1();

		// 4、根据测试环境和生产环境替换相应ip和端口 http://ip:port/api
		request.setServiceUrl("http://XXXXXX:XX/api/cardbusiness/epark/membersync/V1");
		// 5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
		CardbusinessEparkMembersyncRequestV1Biz bizContent = new CardbusinessEparkMembersyncRequestV1Biz();
		bizContent.setDepotId("znpark0000000001");
		bizContent.setQueryType("1");

		request.setBizContent(bizContent);
		CardbusinessEparkMembersyncResponseV1 response;
		String msgId = String.valueOf(System.currentTimeMillis());
		try {
			response = client.execute(request, msgId);// msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一
//			if (response.isSuccess()) {
//				System.out.println("data:" + JSON.toJSONString(response.getData()));
//			}
//
//			System.out.println("ReturnCode:" + response.getReturnCode());
//			System.out.println("ReturnMsg:" + response.getReturnMsg());

		} catch (IcbcApiException e) {
			e.printStackTrace();
		}
	}
}
