package com.icbc.api;

import java.util.ArrayList;
import java.util.List;

import com.icbc.api.request.MarketingActivityCouponDistributeRequestV1;
import com.icbc.api.request.MarketingActivityCouponDistributeRequestV1.MarketingActivityCouponDistributeRequestV1Biz;
import com.icbc.api.request.MarketingActivityCouponDistributeRequestV1.MarketingActivityCouponDistributeRequestV1ChanInfo;
import com.icbc.api.request.MarketingActivityCouponDistributeRequestV1.MarketingActivityCouponDistributeRequestV1DistrbInfo;
import com.icbc.api.request.MarketingActivityCouponDistributeRequestV1.MarketingActivityCouponDistributeRequestV1TrxInfo;
import com.icbc.api.response.MarketingActivityCouponDistributeResponseV1;

/**
 * 优惠券发券测试类
 * 
 * @author kfzx-caixiang
 *
 */
public class MarketingActivityCouponDistributeTestV1 {
	public static void main(String[] args) {
		// 示例总体说明：请使用F-APIP最新的行外网关SDK进行开发，签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值

		// 1、密钥对认证方式，公钥在F-APIP平台登记，此处为私钥
		String MY_PRIVATE_KEY = "xxxxxxxxxxxx";
		// 2、appid
		String APP_ID = "xxxxxx";
		// 3、网关公钥
		String APIGW_PUBLIC_KEY = "xxxxxxxxxxxx";

		// 4、创建与F-APIP外网网关的连接
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8",
				"json", APIGW_PUBLIC_KEY, (String) null, (String) null, (String) null, (String) null);
		// 5、设置请求对象
		MarketingActivityCouponDistributeRequestV1 request = new MarketingActivityCouponDistributeRequestV1();
		// 6、设置请求路径；联测时，请联系我行告知并替换成测试环境地址
		request.setServiceUrl("https://[需替换]工行API网关地址/api/marketing/acitvity/coupon/distribute/V1");
		// 7、设置业务参数
		MarketingActivityCouponDistributeRequestV1Biz bizContent = new MarketingActivityCouponDistributeRequestV1Biz();
		MarketingActivityCouponDistributeRequestV1ChanInfo chanInfo = bizContent.getChanInfo();
		MarketingActivityCouponDistributeRequestV1TrxInfo trxInfo = bizContent.getTrxInfo();
		MarketingActivityCouponDistributeRequestV1DistrbInfo distrbInfo = bizContent.getDistrbInfo();

		// 8、组装请求参数
		chanInfo.setSerialno("1234567890123456");

		trxInfo.setZoneno("0119");
		trxInfo.setWorkdate("2022-09-01");
		trxInfo.setWorktime("12:00:00");

		distrbInfo.setCooperBizNo("1234567890123456");
		distrbInfo.setCooperActId("0119001000001");
		distrbInfo.setPhoneCountry("852");
		distrbInfo.setPhoneNo("189888888");
		distrbInfo.setLanguage("zh-CN");
		distrbInfo.setDistrbMode("1");
		distrbInfo.setCouponNum("1");
		List<String> counponAmtList = new ArrayList<String>();
		counponAmtList.add("500");
		distrbInfo.setCouponAmt(counponAmtList);

		bizContent.setChanInfo(chanInfo);
		bizContent.setTrxInfo(trxInfo);
		bizContent.setDistrbInfo(distrbInfo);

		request.setBizContent(bizContent);

		MarketingActivityCouponDistributeResponseV1 response = null;
		try {
			System.out.println("request:" + request.toString());
			response = client.execute(request);
			System.out.println("response:" + response.toString());
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}
		System.out.println("returnCode:" + response.getReturnCode());
	}
}
