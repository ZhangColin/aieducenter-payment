package com.icbc.api;

import com.icbc.api.request.B2CPayTransferRequestV2;
import com.icbc.api.request.B2CPayTransferRequestV2.B2CPayTransferRequestV2OrderInfo;
import com.icbc.api.request.B2CPayTransferRequestV2.B2CPayTransferRequestV2Custom;
import com.icbc.api.request.B2CPayTransferRequestV2.B2CPayTransferRequestV2Message;
import com.icbc.api.request.B2CPayTransferRequestV2.B2CPayTransferRequestV2Biz;

public class B2CPayTransferRequestTestV2 {
	//1、网关公钥
	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

	//2、appid
	protected static final String APP_ID = "10000000000000002156";

	//3、密钥对认证方式，公钥在API平台登记，此处为私钥
	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----CdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIJBzZNaiobplRUgtJ4OzmUvZRK99ih/fUyDBOoFLtpJJCCRzp8T6V11YNlE7Xg5dt+EG7byQs2NImqg0eWEj/mBdZ7UmlAct7BNw2hyF2h4R5OEfXyjoH3wqGjKJayhaHTvLM1DYy/mDFBb0ShJYI1QMScQJZhsOhMMFhrrZwIZAgMBAAECgYAA2kdrOIOBoJPOQJmOE1C8jtPdjIrI9xSt5Imqsn/9A8+NuwacOfgkGXmZ0n6vc8jYa7f2uZ1AVTUtd4IIO5bpq8s0Tw2BfWALYwr/JdUuNKSjHVQsh/Do+wl8BgOgB4RqsNXWNGtoMC8lHKHmrVcpyJMfDc3cP07NZ1wG2zB0lQJBAM+dNZv2L/Z4RzvQcoVZEthYavZ4pkFoWGYC4jwc5G8um76zoQyrtxWYrtTP0GS+xFFX2dEuiGXxwzmSQJrPdrMCQQCgnUXcQe/if2c6TFt4x9v+6L0pmFClYyiOi9RuBSz1sHmPouuc/YYvuxAOdOzu3yzOkeo7b5KcCKITTWvKI+oDAkA5dl6vIw2VXycAJCp+Q/AWVyqLu0rw0Yud+HBbiPek2jabKqaJlkFfRdol5rrcF3zIstMDtahk5uxM0/DzqDZHAkBGnZ8vfdYIUVeDbDrzWXvCEXXJqewbKwOT2KqnTKM9yj9IBatttJGgvrAKiyH4zCqZD9JaG23sKGeJ8QopL60dAkEAtc4tlKoj3XZzRUXboqz0EhkgkjzDj50zpCD1sJKZ2EZH+A/7tXwPug+RnuSmKpM2uv3msqw3prdS3K4En8+rog==";
		
	public static void main(String[] args) throws Exception {
		//签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
		//prikey为AES秘钥
		UiIcbcClient client = new UiIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY,"UTF-8");

		B2CPayTransferRequestV2 request = new B2CPayTransferRequestV2();
		
		//4、根据测试环境和生产环境替换相应ip和端口
		request.setServiceUrl("https://ip:port/ui/aggregate/payment/request/V2");
		//5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
		B2CPayTransferRequestV2Biz bizContent = new B2CPayTransferRequestV2Biz();
		B2CPayTransferRequestV2Custom Custom=new B2CPayTransferRequestV2Custom();
		B2CPayTransferRequestV2Message Message=new B2CPayTransferRequestV2Message();
		B2CPayTransferRequestV2OrderInfo OrderInfo=new B2CPayTransferRequestV2OrderInfo();
		Custom.setLanguage("zh_CN");
		Custom.setVerifyJoinFlag("0");
		OrderInfo.setOrderDate("20170122152918");
		OrderInfo.setOrderId("00210554222222254");
		OrderInfo.setAmount("500");
		OrderInfo.setCurType("001");
		OrderInfo.setInstallmentTimes("1");
		OrderInfo.setMerID("0200EG0000202");
		OrderInfo.setMerAcct("6212880200000038618");
		Message.setGoodsID("428767198");
		Message.setGoodsName("超市");
		Message.setGoodsNum("1");
		Message.setCarriageAmt("0");
		Message.setMerHint("小心轻放");
		Message.setRemark1("");
		Message.setRemark2("");
		Message.setCreditType("2");
		Message.setMerReference("");
		Message.setMerCustomIp("");
		Message.setGoodsType("");
		Message.setMerCustomID("123123123");
		Message.setMerCustomPhone("189111112222");
		Message.setGoodsAddress("");
		Message.setMerOrderRemark("");
		Message.setMerVAR("重要客户");
		Message.setNotifyType("HS");
		Message.setResultType("0");
		Message.setIsReal("");
		Message.setSubidno("");
		Message.setPromptText("");
		Message.setReturnUrl("https://www.scgsj.com/notify.do");
		Message.setAutoReferSec("5");
		Message.setBackup1("");
		Message.setBackup2("");
		Message.setBackup3("");
		Message.setBackup4("");		
		bizContent.setCustom(Custom);
		bizContent.setMessage(Message);
		bizContent.setOrderInfo(OrderInfo);
		request.setBizContent(bizContent);
		System.out.println(client.buildPostForm(request));
		request.setBizContent(bizContent);


		
	}

	
}
