package com.icbc.api;



import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.CardbusinessMerTripIdentitycheckRequestV1;
import com.icbc.api.request.CardbusinessMerTripIdentitycheckRequestV1.CardbusinessMerTripIdentitycheckRequestV1Biz;
import com.icbc.api.response.CardbusinessMerTripIdentitycheckResponseV1;
import com.icbc.api.response.RSAUtil;
import org.apache.commons.httpclient.util.DateUtil;

import java.util.Date;

public class CardbusinessMerTripIdentitycheckV1Test {



	//1????????
	//protected static final String APIGW_PUBLIC_KEY = "****";

	//2、appid
	protected static final String APP_ID = "10000000000004097118";

	//1、网关公钥
	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";


	///3、密钥对认证方式，公钥在API平台登记，此处为私钥
	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCpx55HRWBecD9PDGnjgG14gySvwgsWahRXl+6wvlsC/oT1Gr4vQyVIQ6JJDkWFopyKrbcfSm5YPanGLiM2iBZ4/Vsa5mUGmr7Ne4DAD2zEFhDPlFrMuyP7h5t89qtnL9Q4vgz89W35qDAiJ3lLjutRgk2LmyvYFpgTazdH/34cIkBIfhlTLLUGKEXL2fEc0A5oKkLPHtu1/YF6u8rlsu7Xe6WLOBeHk9KqBR7BH+5ctL75oZBBRbwKauFPRWE/tQcjXv+qxJJVq6TfbQCRPKpzdmt/YcsiWnJwL1CEeXndXsY6mX14fSH1jHmU2cnkjccudA/bBJAKPSzFfQ1FCp2NAgMBAAECggEBAKiAqiJqRJ72IJ8/3sggaoW1AvA9UevJFK9q50cbZhCusx7gj+kwZww1tBd1xqI/JcatapXvtVx2ts0udVxl7IfgTE+CYroPU1d1P+9eX8CtdbnPr96JzciSaku4NZ2P5rz6QsG8hI3H0x3St5u6GOsou/XW58jrg7uiKZeXh347DwtxFJEhgjZBGUDw1+GOWapz1WFXfkjxEiikYTW6YSLYGHR765YXG5wpanIqknz1iTBkXxedlzc4WbIfJMYNlilrYGUhEwaMuCHHz6HIVqSRQxmmJT+ZwZ4aCFeZnvBKe7dDRrhisqigJh0QqqnwO0aIVc0fBdKBFbb0sxrkaYECgYEA7ML1DPoGGMrpWOUiFzwpOkg3ZpL/9+KyReTzxAY/eMV9C4dUMXSE0yh46YueeYiqlinj88Ob7Q2AmzHh4M+J9BbmyR8pV8uM/W7X7Rzs9YkgcB2dQcU7WBfSaErwvIVTR62ABLoCgv9q74sWdPOTGxfIGQafzJNBEKmUdJxJx9kCgYEAt5NTbUk99cONlI7k98Ow8FVcAnx1qvmZfuVukRb6lufgbLhCKy3bz23hYkyUyVLl8Q6o+Vgg/TTuXQ8DCCPF5F/Cv/s8S+/ulF1sT7J/P9x3a/tCSCICfiRNM5jByG9zDZI4UgxzDV8AR2BvH19sCL355ENA/8ljkrvZkbfSRtUCgYEA5rMVOp7JoBDgDWm/rSpG9e52ZTGpz24KzgZI7OI1qMWukbQ8+q+R2XtfThpowf3EUuvgnZ8l6Xe1sAOMGJXB06EZIjKGA3FpIW2HYxlDYAFNJVIF8JdxuuYDdPJI3zl+q6JI0tI5LlIZbe52ZGEPH2At7wzAWwPFa6oB6Rp83gkCgYBo6kMgz14g6j0tLSmAzI+AG1i1dwoMyA/IrTkupLJcsnLZz8jJQrFAcG0CMABMuxr9VMKPlqVr8OdVufGiokdECNKVw/Em+R4iHu568em1xrtdtECiVwWTsA2LMn91nPRlSi7z/Xd3qdbP9DYt+OwWZvS9Vw574U4OG1vx4tvDbQKBgQCpIFfrj19CQU/+oWwtkdfdK/E/hMfK1ESFHfWAX+Pq4pIyEIz/Kb3IweKy9uEGbE/zNfi3m5ClCf9H3B4BYk5EKbSk3lVDKg3F4U+1ETNE4fwvKdBfCgpJMi12xdTtkuhfrVUjhaw7T0pFc8fJkP5P5Q+elY6iwYgn+JPm+2yb8A==";

	//3、密钥对认证方式，公钥在API平台登记，此处为私钥
	//protected static final String MY_PRIVATE_KEY = "***";
	//548426015   440100
	public static void main(String[] args) throws Exception {

		// 签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

		CardbusinessMerTripIdentitycheckRequestV1 request = new CardbusinessMerTripIdentitycheckRequestV1();
		// 4、根据测试环境和生产环境替换相应ip和端口
		// http://apip-gateway-gw-out-gn-F.month.sdc.cs.icbc/api
		request.setServiceUrl("http://ip:port/api/cardbusiness/mer/trip/identitycheck/V1");
		// 5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
		CardbusinessMerTripIdentitycheckRequestV1Biz biz = new CardbusinessMerTripIdentitycheckRequestV1Biz();
		request.setBizContent(biz);
		biz.setCorpId("2000000137");
		biz.setStrTESn("BCSSXINDL01NANL00019835");

		biz.setTrxserno(new Date().getTime()+"");
		biz.setMac("48-5F-99-81-E7-9A");
		biz.setmId("ip:port");
		 setParam1(biz);
		System.out.println(JSONObject.toJSONString(biz));
		CardbusinessMerTripIdentitycheckResponseV1 response;
		try {
			response = client.execute(request, new Date().getTime()+"");//msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一
			System.out.println(JSON.toJSON(response));
			if (response.isSuccess()) {
				// 6、业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
				System.out.println("ReturnCode:"+response.getReturnCode());
				System.out.println("response:" + response);
				if (biz.getOperation()==4){
					String str = RSAUtil.decryptWithRSA("-----请使用正确的APP私钥-----EvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDDXZSANoYJZSoGc7ZP9M9rjVNiBkllC3JlOLMPCZstdvlSRZcdpwhjMIQiqtM3olUkeDUN8aUawJNAg9q3n7FWHTCSoR3EXhqUR2+Y9bsolyj+YMi5AtVu/hEqwuMAIMxko0NvanmEUxQJDKh3p0WqrLjOz/R+K+FHtWUKxdMY5HClEmn8QtUE6EL1uozHOfWaaYZ43HlCdKxjSct0tf5JRVIMfJAZnyx2Cnse+hPcHRC0FZBldVXOt/2FK+ZNSDJf8ORN2+2IxerJTSguRHUqoryhNJzjQrf2dfwxjQGkl/DNd7z4O/GqF7ri4FWc5K2oL7HwVRnyFQYXOlFjePx3AgMBAAECggEABiKIKUaNCvp8SVuU3AnqAYyck7Vy1JN5cihHpy88Cj90MNsylaHLJ6I8ZAJNQ6/7UHvd3qQ59Q8bEKmTUrTXEscz4Ubh8mqqFwHvRTRiPjIQ/Xv4kaa/IQ8lZtxb2IMvJ/Hgij0jt74MIQSevbMpAuUNoKDzIosqNeBCXgY8TMnBloWbLtp5oqLBj9w7UCENdWwdq7vZakoz3CSAo8QmfXjZmOsZRGtrDXcpCqAcoGcV2PurGJtsg9rDwpOR6PxMOIYoVo29rXWL23ff5YIpWejTLc1gSm14JYz/ZkULKKYtucgDbft5vYr3i40GqtyXbNFJU5gNJYXJKE8+CBZNYQKBgQDTe+4RlY4+WKU2lq64H+1BfdqrcjzfLFmGAte5qGTqIihbvHe+FNWhUiebo+jZdUW9sIsWRJvdo2Ia5Z4+vGwmQbji0D9UAsr40ZZxV+kdLB7UgRNC3Jl4aBuwdW4Vcv/8fthcwJep0CcihVUCq9LJpTDUvFQhefCH1WmGPJ+OiQKBgQDsfRU0xIo7+hU/S6qoktqP+ocB2uWdzQLAFURxy7iKnxwVMNWHMbttrHFdJNgZvzIZiTLlElryaZ8Td5UOvhzUiWXsgRW6thej+L4CDBZSwsjvA3NwcOwZnSMyM3uKtUiLktJ5mA7LlLCFpGCBen3T6gdOCshnewMSRb8WXkly/wKBgQCXl5EJ97sTg7VBkPdCslf+Bvm0xVlO29p8DhOWWFel4MAvKcRnc0XCskID9ZUWM1K/awn390wGrnPHNeqdq7CWJ6wgoQeT78CQZKPDMTG/IH4k8zTcS4aS4YVfkn1LULifxEjRC5hrN/Klcw+Br6qTsh5zj196845vv5E5kMEKiQKBgQCNxCINQvC3KaDJE9wXOAmrm/yI4Sca5MYMTfN84Codd2pHTcIDTI3WHjTLKIYfUR2Ab13CMrGwdh61dc82SkNQKZmfIHbdOcGMO1X0Nji/niMEHSZ7tkXiyO9Lbc9WhHwbp4Vi/QOJiPYU90ge9kYt3jJI12/xkK1zq7n0d8tCEwKBgQDCQRbzy8Nlz36cAOYY9uLjkYPsdx73vVnYlVUta7tfLWDXEhbMMtv8EtsAMfJffZXagdsUgnAOfg8sFzHKtU4FkKQfnoRHtGJYk3s3GcyGnTcq5IkcJa58R6SG8KOHeFr3NS51HG4Yn/lYHBpU5WMCxFojIyeT6pHxK4qNKiYY0g==",response.getReturnData());
					System.out.println(str);
				}
			} else {
				// 失败
				System.out.println("ReturnCode:"+response.getReturnCode());
				System.out.println("ReturnMsg:"+response.getReturnMsg());
			}		
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}
	}

	private static void setParam1(CardbusinessMerTripIdentitycheckRequestV1Biz biz){
		biz.setOperation(1);
		biz.setKeyword("320900|0081544C9786843209AB8B9EC6|03|320900D156000005AB8B9EC6D673A500|168EB532D90AE49B|C6ACEDDC215EA40C|70F78B8C5DB3FFEE|70F78B8C5DB3FFEE");
	}

	private static void setParam2(CardbusinessMerTripIdentitycheckRequestV1Biz biz){
		biz.setOperation(2);
		biz.setKeyword("440100|445202199309214055|548426015||邱");
		//发卡地行政区划代码|社会保障号码|社会保障卡卡号|卡识别码|姓名
		//548426015   440100
	}

	private static void setParam3(CardbusinessMerTripIdentitycheckRequestV1Biz biz){
		biz.setOperation(3);
		biz.setAuthTime(DateUtil.formatDate(new Date(),"yyyy-MM-dd HH:mm:ss"));
		biz.setName("xxx");
		biz.setSocialSecurityCardNo("445202199309214055");
	}

	private static void setParam4(CardbusinessMerTripIdentitycheckRequestV1Biz biz){
		biz.setOperation(4);
		biz.setPublicKey("-----请使用正确的APP私钥-----BIjANBgkqhkiG9w0BAQEFAAOCAQ8A-----请使用正确的APP私钥-----BCgKCAQEAw12UgDaGCWUqBnO2T/TPa41TYgZJZQtyZTizDwmbLXb5UkWXHacIYzCEIqrTN6JVJHg1DfGlGsCTQIPat5+xVh0wkqEdxF4alEdvmPW7KJco/mDIuQLVbv4RKsLjACDMZKNDb2p5hFMUCQyod6dFqqy4zs/0fivhR7VlCsXTGORwpRJp/ELVBOhC9bqMxzn1mmmGeNx5QnSsY0nLdLX+SUVSDHyQGZ8sdgp7HvoT3B0QtBWQZXVVzrf9hSvmTUgyX/DkTdvtiMXqyU0oLkR1KqK8oTSc40K39nX8MY0BpJfwzXe8+Dvxqhe64uBVnOStqC+x8FUZ8hUGFzpRY3j8dwIDAQAB");
		biz.setQrCode("9200000003190145526594");
	}

	private static void setParam5(CardbusinessMerTripIdentitycheckRequestV1Biz biz){
		biz.setOperation(5);
		biz.setAuthTime(DateUtil.formatDate(new Date(),"yyyy-MM-dd HH:mm:ss"));
		biz.setName("xxx");
		biz.setSocialSecurityCardNo("445202199309214055");
		biz.setBusinessStatus("1");
	}

}
