package com.icbc.api;

import com.icbc.api.request.JftApiVendorOpenAccountRequestV1;
import com.icbc.api.request.JftApiVendorOpenAccountRequestV1.JftApiVendorOpenAccountRequestV1Biz;
import com.icbc.api.response.JftApiVendorOpenAccountResponseV1;

/**
  * @Author dccb-wblit
  * @Description 往来户预约开户API接口V1-测试类
  * @Date 2021/1/18 16:35
  * @Version 1.0.0
  */
public class JftApiVendorOpenAccountTestV1 {

	// 1、网关公钥（不是APP的公钥）
	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5"
			+ "BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lT"
			+ "W3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
	// 2、appid
	protected static final String APP_ID = "10000000000001474554";
	// 3、密钥对认证方式，公钥在API平台登记，此处为私钥
	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCF7eCVTURFK5+gp...";

	public static void main(String[] args) throws Exception {

		// 签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, "UTF-8", "json",
				APIGW_PUBLIC_KEY, "AES", "5xGJdh7qb+B95SUoxDlatg==", "", "");

		JftApiVendorOpenAccountRequestV1 request = new JftApiVendorOpenAccountRequestV1();

		// 4、根据测试环境和生产环境替换相应ip和端口
		request.setServiceUrl("https://ip:port/api/jft/api/vendor/open/account/V1");

		// 5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
		JftApiVendorOpenAccountRequestV1Biz bizContent = new JftApiVendorOpenAccountRequestV1Biz();
		bizContent.setAppId(APP_ID);
		bizContent.setTrx_date("2018-06-12");//渠道交易日期
		bizContent.setTrx_time("15:11:11");//渠道交易时间
		bizContent.setAccname("户名");//户名
		bizContent.setCurrtype("1");//币种
		bizContent.setZoneno("200");//开户地区号
		bizContent.setBrno("620");//开户网点号
		bizContent.setAccatrbt("1");//账户性质
		bizContent.setAcctype("1");//账户类型
		bizContent.setAccpertype("1");//账户类别
		bizContent.setAccpername("a公司");//存款人名称/客户名称
		bizContent.setBus_license("915001035789578375");//营业执照号码
		bizContent.setCorp_flag("1");//法定代表人或单位负责人
		bizContent.setCorp_name("张三");//法定代表人证件或单位负责人证件-姓名
		bizContent.setPre_appl_tel("18818653325");//预约人手机
		bizContent.setNoitfyFlag("1");//消息通知标志 0-不通知 1-通知
		request.setBizContent(bizContent);

		JftApiVendorOpenAccountResponseV1 response;
		try {
			response = (JftApiVendorOpenAccountResponseV1) client.execute(request, System.currentTimeMillis() + "");
			if (response.isSuccess()) {
				// 6、业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
				System.out.println("ReturnCode:" + response.getReturnCode());
				System.out.println("response:" + response);
			} else {
				// 失败
				System.out.println("ReturnCode:" + response.getReturnCode());
				System.out.println("ReturnMsg:" + response.getReturnMsg());
			}
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}

	}

}
