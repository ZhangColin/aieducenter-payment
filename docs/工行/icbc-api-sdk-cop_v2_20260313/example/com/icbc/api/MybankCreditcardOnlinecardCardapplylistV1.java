package com.icbc.api.test;


import com.alibaba.fastjson.JSONObject;
import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.request.MybankCreditcardOnlinecardCardapplylistRequestV1;
import com.icbc.api.request.MybankCreditcardOnlinecardCardapplylistRequestV1.MybankCreditcardOnlinecardCardapplylistRequestBizV1;
import com.icbc.api.response.MybankCreditcardOnlinecardCardapplylistReponseV1;



public  class MybankCreditcardOnlinecardCardapplylistV1 {


	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
	//protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
    //重新生成
	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCKhELDa2Vd78EYVZV9OV/yV/W9GZEcqTgx7zFAxOwm686n9MzAwOj4OZQ2UTDIthtI0ik2qpw5KYY4YuGeBtwcVoP4azMaNhq+fNFOqyP8Fwp1Mq9lVIj+5m/OxhHq12zeTlujt9NeLJTMxW8Y7kGc0ovn5jO/GgF/c3mIwyXIq5w7tLIiDD/2/x4IKdSS2nZdqh9hwynpY6mOySqqLdxmQ/hH63nW5vmBH6enR4FxqL7cR8NuEIirZM5YNyQACPwJYhdKSKlDWkVqWwCws620GGRl0gDraFXfO3DeKZl09ZgITRmJEKpQLpb5qHmZ67gv2Kg+gH7OVjWGiXftArIzAgMBAAECggEASlcjkcgA1kKHqqy8F0IEuYb1rzCajfxP2ZTbJTzK1TS3a7LZdRXTsu0MG37FpOL/j7yjO+CjCZa8n2SKXAqodVkFaPz3pzikb9mDaCo3nOMszKuQMN0GzW8VUK9DDHvjxQfe7OYKvb9FeQT5VDMC6cyiI6qL2SN27FUoQ/sgOyr27txtK+JaxmVSF2ojjRmT1IGjiKc91iMjtBsy+qx7bJcOfFRNtBJLIY04xsIB+xbNXlASsUcQXgFyvITtCuUm3D/MMla44O4YYeLmXorqHqGHOnu+End+ng2A4ZO2LP29z4T8qVZqL0t1D1wq6ktJR69T5JNYJ2d6hbhw6URy4QKBgQDwhrVSxwsmrx3riFl+YNN6kiekTdu26+LGJmiQnLHg9tbAFfqO5F3D/xEhQS0zzbeDQGW11wx13auYMkZSoNOeZk9duFGV8Ew8xPOSA0H5s+NlcFG7sciBPmVhVzevd77Kk+4Bgol2SDh8p15kgnu0srJ7WJy9za7wRqMLOdhXIwKBgQCTbYd6BV0mZ8eh3drtSLwTYiNf8wlxTmMzhK7zwGG+PvivmaiV7xugFANuMtfarcUFZFbKNGsddWNSck6jcnNuAMTyE/awKtINCtb8MZoFKEMaMcBJUGEmL13PE71UfM2u8Sb76g/mkfXwq0PbgJBiJLwtWZda/7W7qLNkS6hxsQKBgQDDrnR6YeEamcGXy5gzjwa+Y4XI0Gb+TK50Uu8jJ7g3s9pG8Bqmgfge6ra+/U2PJcA1EgEtjUVf5vYhnlVEB//ikI3lS31kpNkBX6JhmjDku4s3DcgREbR0IZVNOi3dnWglW3B43gCDsIH0OB49W0LVYcR/QTpT2LCIXo9rJKZzTQKBgQCFXbIOykdn9L39SgtAWGn7Ru4lYGCMZZMMCd2Sl9hUF/c0M1b0u6dL+o7X45qtMkFnDUwOA7lantTIwqss5N5TBtMjArhIyZPXRJUS83QXckKxsYv3RoV726GLMJo3kecnePQBM85KkIPhwazTJsx2XDD2/XMUWPp31rbh5idtIQKBgGs6reN9Zyj44XJiWjR+3GOm/11Qpsug4QNmxhKjQMXQbMXcJOOyMrDX3tBJxBvXbnkPp946Q3Bx1IdjfuwSNp6ObrohlgzNF0GyJjrcceUuRIjWUZfRiIdi6iz5yp+hT1VB42urJod+8j9fn5pV04s6Gq/ztbTpaDdz4LpActJA" ;
	//protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的APP私钥-----BIjANBgkqhkiG9w0BAQEFAAOCAQ8A-----请使用正确的APP私钥-----BCgKCAQEAn6NMBev/q6NPxq3Cg+8hRP2wm9l6zqrBj5czX4/TnrWjY05KakzVtfx+lgr7F3c/2YJt+5JhmIhdaarRoa9BI41OUUrAR6tjtbd1sHMp87ceHp0nyWd3uYA2OWUkwAxp4FVP//Pyr6gfgWJI78WfmjK5czaDp75LX5RQFHWjhgjkCkJgzhCewsqJ1OCEK/CoCJdOyyZ21tvYE+uEPuQrqj92uZ/QlbwJa/2EIYm2QxHk5OlTlM6BBTC/n1wcVrkc6r4QuAl2CsQvaaZzoKZV8mQvDRkjaJXRSXv/fX//42wRekkJac7WWVvImcK56RzANQiNXcaqfoLIyVTpQqN06QIDAQAB";

	//APP_ID：工行API接口的ID
	protected static final String APP_ID = "10000000000000012137";
	
	public static void main(String[] args) throws Exception{
		MybankCreditcardOnlinecardCardapplylistV1 a=new MybankCreditcardOnlinecardCardapplylistV1();
		a.test_cop();
	}

	
	
	public void test_cop() throws IcbcApiException {
		MybankCreditcardOnlinecardCardapplylistRequestV1 request = new MybankCreditcardOnlinecardCardapplylistRequestV1();
		request.setServiceUrl("http://ip:port/api/mybank/creditcard/onlinecard/cardapplylistquery/V1");
		MybankCreditcardOnlinecardCardapplylistRequestBizV1 bizContent = new MybankCreditcardOnlinecardCardapplylistRequestBizV1();
		bizContent.setName("网银测试");  //中文名
		bizContent.setCity_num("00200");  //开户地区号
		bizContent.setId_card_no("440111199111111115");  //证件号码
		bizContent.setId_card_type("0");  //证件类型
		bizContent.setApply_id("1234567890000000000000007");  //流水号
		bizContent.setFirm_num("WYXY");  //公司代码
		bizContent.setChannel("0011");  //渠道代码
		bizContent.setPage_num("0000");  //页面位置代码
		bizContent.setCard_logo("46000159");
		request.setBizContent(bizContent);
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
		MybankCreditcardOnlinecardCardapplylistReponseV1 response = client.execute(request);
		
		System.out.println(JSONObject.toJSONString(response));


	}

																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																														
	
}	
