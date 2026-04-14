package com.icbc.api;

import com.icbc.api.request.MybankCustomerPointbalqryequeryRequestV1;
import com.icbc.api.request.MybankCustomerPointbalqryequeryRequestV1.MybankCustomerPointbalqryequeryRequestV1Biz;
import com.icbc.api.request.QrcodeGenerateRequestV2;
import com.icbc.api.request.QrcodeGenerateRequestV2.QrcodeGenerateRequestV2Biz;
import com.icbc.api.response.MybankCustomerPointbalqryequeryResponseV1;
import com.icbc.api.response.QrcodeGenerateResponseV2;


/**
 * 积分余额查询
 * 
 * 请商户配置SDK中的lib文件夹中的jar包后，根据备注1-6提示进行数据替换
 */
public class MybankCustomerPointbalqryequeryTest {

	//1、网关公钥
	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

	//2、appid
	protected static final String APP_ID = "10000000000000005540";

	//3、密钥对认证方式，公钥在API平台登记，此处为私钥
	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----CdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIJBzZNaiobplRUgtJ4OzmUvZRK99ih/fUyDBOoFLtpJJCCRzp8T6V11YNlE7Xg5dt+EG7byQs2NImqg0eWEj/mBdZ7UmlAct7BNw2hyF2h4R5OEfXyjoH3wqGjKJayhaHTvLM1DYy/mDFBb0ShJYI1QMScQJZhsOhMMFhrrZwIZAgMBAAECgYAA2kdrOIOBoJPOQJmOE1C8jtPdjIrI9xSt5Imqsn/9A8+NuwacOfgkGXmZ0n6vc8jYa7f2uZ1AVTUtd4IIO5bpq8s0Tw2BfWALYwr/JdUuNKSjHVQsh/Do+wl8BgOgB4RqsNXWNGtoMC8lHKHmrVcpyJMfDc3cP07NZ1wG2zB0lQJBAM+dNZv2L/Z4RzvQcoVZEthYavZ4pkFoWGYC4jwc5G8um76zoQyrtxWYrtTP0GS+xFFX2dEuiGXxwzmSQJrPdrMCQQCgnUXcQe/if2c6TFt4x9v+6L0pmFClYyiOi9RuBSz1sHmPouuc/YYvuxAOdOzu3yzOkeo7b5KcCKITTWvKI+oDAkA5dl6vIw2VXycAJCp+Q/AWVyqLu0rw0Yud+HBbiPek2jabKqaJlkFfRdol5rrcF3zIstMDtahk5uxM0/DzqDZHAkBGnZ8vfdYIUVeDbDrzWXvCEXXJqewbKwOT2KqnTKM9yj9IBatttJGgvrAKiyH4zCqZD9JaG23sKGeJ8QopL60dAkEAtc4tlKoj3XZzRUXboqz0EhkgkjzDj50zpCD1sJKZ2EZH+A/7tXwPug+RnuSmKpM2uv3msqw3prdS3K4En8+rog==";
		
	public static void main(String[] args) throws Exception {

		//签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
		
		MybankCustomerPointbalqryequeryRequestV1 request = new MybankCustomerPointbalqryequeryRequestV1();
		
		
		//4、根据测试环境和生产环境替换相应ip和端口
		request.setServiceUrl("http://ip:port/api/mybank/customer/pointbalqry/V1");
		
		//5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
		MybankCustomerPointbalqryequeryRequestV1Biz bizContent = new MybankCustomerPointbalqryequeryRequestV1Biz();
		
		bizContent.setEventid("010102008698011871963402629");
		bizContent.setChncode("101");
		bizContent.setOapp("F-CCIS");
		bizContent.setTrancode("Ats000003QryBussPtBal");
		bizContent.setChnReventid("0");
		bizContent.setChnZoneno("00200");
		bizContent.setChnBbrno("00620");
		bizContent.setChnTellerno("13");
		bizContent.setChnTerid("0");
		bizContent.setChnIp("ip:port");
		bizContent.setChnIntrxcode("4334");
		bizContent.setChnIntercod("6");
		bizContent.setChnTrantype("1");
		bizContent.setChnPreflag("0");
		bizContent.setChnDateflag("0");
		bizContent.setChnWorkdate("2020-01-01");
		bizContent.setChnServface("1");
		bizContent.setQryflag("2");
		bizContent.setMdcardno("4518110021553684");
		bizContent.setCino("020006202558334");
		bizContent.setReadf("1");
		bizContent.setCkpinF("0");		
		request.setBizContent(bizContent);

		MybankCustomerPointbalqryequeryResponseV1 response = new MybankCustomerPointbalqryequeryResponseV1();
		try {
			response = client.execute(request, "msgId");//msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一
			if (response.isSuccess()) {
				//6、业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
				System.out.println("ReturnCode:"+response.getReturnCode());
				System.out.println("response:" + response);
			} else {
				//失败
				System.out.println("ReturnCode:" + response.getReturnCode());
				System.out.println("ReturnMsg:" + response.getReturnMsg());
			}
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}
	}

	
}

