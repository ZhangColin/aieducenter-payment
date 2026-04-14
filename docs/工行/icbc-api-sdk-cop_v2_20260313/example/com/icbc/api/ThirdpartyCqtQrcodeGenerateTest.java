package com.icbc.api;

import com.icbc.api.request.ThirdpartyCqtQrcodeGenerateRequestV1;
import com.icbc.api.request.ThirdpartyCqtQrcodeGenerateRequestV1.ThirdpartyCqtQrcodeGenerateRequestV1Biz;
import com.icbc.api.response.ThirdpartyCqtQrcodeGenerateResponseV1;

/**
 * 银联二维码生成接口
 * 
 * 请商户配置SDK中的lib文件夹中的jar包后，根据备注1-6提示进行数据替换
 */
public class ThirdpartyCqtQrcodeGenerateTest {

	// 1、网关公钥
	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

	// 2、appid
	protected static final String APP_ID = "10000000000000002017";

	// 3、密钥对认证方式，公钥在API平台登记，此处为appid对应的私钥
	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCTcrAEz74oZgaABTReAAXb/UZvP/ZvSiqZXhdJDJqwXbxbC7vTSeJ0OASCG0SNpz9xir554d7ddcdN5VEi/7NIEyoIPGqwDWm3GDl2CvBaxKICRXiCSLI9/SJjAufl2ZpoWuJlMfNq068JHJ147Lkw0Mq9QIWJ8RftHDOiYB1UyOIH2E5vUvM4p1LPtBKM/uVWSwdN/sdi6ehJSgNRWC4Iu+jg7y/kFNiSZ20xnCwT0Qy4benF950DDSix5UZ/ponotH6E8WlxMXg8C92IedzqVrm/k25j1okmTEkj4TVFaD/w8wwo3k+B0sgvfBmEad8WVbYZu/qpKdDqXQP2gF1BAgMBAAECggEBAICfDf7UfYveHBN29sx89p+Fm/iV8lH4f5qT4nodnZ711cYSQUE59WnuJ7ibMrzixUw1fJzztKa5BCfmP1XpeioIVaUjlumGec53OkDHoA1989lJYqs74QiEFYgLU+FEZhZcGZuu7CknCUxIgvGmhpGXwRNAAn1nK7jx3fTqJQv8cRpi42KCAoJLdvsBDBvKP935Q7njymH72GRPJV/Hir3PY7uIqLB623R74wygDObwnM0KuDwz6RZGHZMq7tkCGiqi1gDr8wSY/uXBrRRo+Zq7Rm3xI+gkD0GdWKiy+CsPJ96BFLdILV9787x/pd/PpLjutCloKmGbRE9k9BKTrZUCgYEAzAF7fEfDB1qeKM6cSIxz5q0l0x92jkQ+HmrPT5hsfg84iJYm2Sz9JKabY54qlEwjqyOE4Dbg7ciCwheDIKWA6zxtBXUg0yj/dc2gcYoFYw4GKH3MJBf3n4u0d/6oWpWLKmE0rubtN0q29LytXA++wgKFVUKfEH1srSRmzPxsMdMCgYEAuQcMOSne/QxAutCMHeWzVEc1HGF5t8MCxoqPYfSNzOMgmDaO8sRHF+BatP+juKA00EF71jcQm0rBHz28nafPfp3R41McEndyVmhPIqdYW+CnhiaRYX7RCb02v0hblfkBSMgl6noj+qYcdfppYmq750yR8j5MAqKKWwz2hq++9BsCgYB6ekFa4fKTDbJ6qWBmpfxHd5iH4hHZY9svg8ym6snn/NdiGVOFe18Xi/cqnaDOm8+mRZLW0dvEpmbqhBczmGmH0SVa63EjlS+Z2DQFNmn5iEfmM3BGEyWVYdidWgRVLgb5EJcCkp3EXlTtJgGmjcVm0QCfqXm5+rMuV/ZoBvwmBQKBgFssk+NfNJhw/zyPI1xgIfv3kr5HchkRKT74bCsSvQUnLpqS1+HcwkOLtGbYSa7ikuPEQOeP/sjnDz77xwPQ+zgjQJfKDl0zKs5VXBlxaOLtUt+SBbWLLiqkaGPdnfPK3c0lgzmFslECpaelG2T5v1n8qzY5aG8r9Q1k194KoAfdAoGBAKuyQxTF24xUTL5cNnHK+unwfk1oBgoLT8qAmiQVGzy10pF7y5kGlxTfzc2nNnqs/53Jp172rWQu/uOoy003xWVzqKh1SGZkwwQ0IA3k0zg1KCR9enTri0yZUEb4Yrwy4dUIiRmuMU3vFhS1YZQ33WWBgkll0ztu+Uryuu2YlV4k";

	private static final String apiurl = "http://ip:port";// apip行外网关

	public static void main(String[] args) throws Exception {
		// 构造client对象
		// 签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY,
				APIGW_PUBLIC_KEY);
		// 设置请求对象request
		ThirdpartyCqtQrcodeGenerateRequestV1 request = new ThirdpartyCqtQrcodeGenerateRequestV1();
		
		// 4、根据测试环境和生产环境替换相应ip和端口
		request.setServiceUrl(apiurl + "/api/cardbusiness/qrcode/thirdparty/cqtqrcodegenerate/V1");

		// 设置业务参数，每个Request请求实现类都有一个RequestBiz内部类用来设置业务参数
		// 5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
		ThirdpartyCqtQrcodeGenerateRequestV1Biz bizContent = new ThirdpartyCqtQrcodeGenerateRequestV1Biz();
		bizContent.setConsumerID("e-lvnan-test");
		bizContent.setCorp_no("10000000000000013029");
		bizContent.setOrderno("ordernoUPsuccess");
		bizContent.setCorp_medium_id("zj2_syhybt_20181221_0910");
		bizContent.setMerurl("http://ip:port:11450/servlet/MerAcNotifyServlert?flag=22");
		bizContent.setQrOrderTime("20181228162712");
		bizContent.setDeviceID("IMEI");
		bizContent.setUsrRgstrDt("20190601");
		bizContent.setDeviceType("1");
		bizContent.setAccountIdHash("1f788064c414b750fc0a3c7817c543b9dbc448f53c2a19a9ddf86f9fef0b9fe2");
		bizContent.setSourceIP("ip:port");
		bizContent.setMerno("360204108251");
		bizContent.setCorp_info("49990028");
		bizContent.setPinFree("10000");
		request.setBizContent(bizContent);

		ThirdpartyCqtQrcodeGenerateResponseV1 response;
		try {
			// request组装完成，开始发起调用
			response = client.execute(request, "msgId");// msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一

			// 判断调用是否成功，进行后续业务处理
			if (response.isSuccess()) {
				// 6、业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
				System.out.println("成功");
				System.out.println("ReturnCode:"+response.getReturnCode());
				System.out.println("response:" + response.getPayInfo());
			} else {
				// 失败
				System.out.println("失败");
				System.out.println("ReturnCode:" + response.getReturnCode());
				System.out.println("ReturnMsg:" + response.getReturnMsg());
				System.out.println("TranErrorCode:" + response.getTranErrorCode());
				System.out.println("TranErrorMsg:" + response.getTranErrorDisplayMsg());
			}
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}
	}

	
}

