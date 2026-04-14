package com.icbc.api;

import com.icbc.api.request.JftApiVendorSettleProtocolInfoRequestV1;
import com.icbc.api.request.JftApiVendorSettleProtocolInfoRequestV1.JftApiVendorSettleProtocolInfoV1Biz;
import com.icbc.api.response.JftApiVendorSettleProtocolInfoResponseV1;

/**
 * 聚富通子商户入驻协议查询接口
 * 
 * 请商户配置SDK中的lib文件夹中的jar包后，根据备注1-6提示进行数据替换
 */
public class JftApiVendorSettleProtocolInfoTestV1 {

	//1、网关公钥
	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
	//2、appid
	protected static final String APP_ID = "10000000000000005452";
	//3、密钥对认证方式，公钥在API平台登记，此处为私钥
	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDI/Pm9FdqP7cTo+B9hQtfC9f/t5pCGSO5bmgTLAXaxEwJbL3fw91supunhtc3RrVuaeHbllWUEIWJr4lLlssHhaypiZTkgvwCEt2Nvxj3d9u0NGSO4m+grO9KaX2cO6JPHdILqavTczaHCmn/XatryEwUHoRx3X1lM30m3+MWYtJmb2ae1jKc4hI2vhHUEqmgTZyruHuLgyKwTyund3eY2Uo3Yhh5nYcitz6AraQmuSBGUIGEm1dl+REbLmrQHbr1mZ2EtO1GMrwmK692jD1G1Vx6/2PDT3Cnh6tBRLdEQaewRmafXmJbLXLNpHO1UaPINR74DZp27VfzBbeo9CV8dAgMBAAECggEBALAyPgmKmAFnUyFdK71LKPVMTWHUp8xhMTxyiFMjZFcVsBIaSg6f+nqqbwylIdr4G/8OLkVUM6tsTOkBDuU5UYh5Nz3tOSjA6QZv8qcxZlkpOGUsLcmQO/dyyUz159YFBFBtjrtMqn7Lcky0vvAtR+XB0L4AeoEXwKSIdwrl9uvHkZ9ffj5Af+2ykj6kN/tT4KIasHCtvlCqkUWHYjF+V5lxYG7p54NzF3zHDPDK0etieOiGVgBj7b9U5IMQi5zji2R5B0vZgRbY1JPHfvfeHVEVjz2hzUuC0EK5ofDOg2U4tLRKPuHMAf+fbXsoH1OilvhRTzOqYQNaogjlhSD5skECgYEA6fV5UIrCfkmhUJogrA5eVEGzXld75clUwHbBGo4BismiakXRZ8gygljXoUQF3Uu31/+ecT9NQV+BCf3qp+E1AtlNst9HQwLgsqvJ61FKSP2Mt1mIMCDVhZrIuZaQx/ZB39LTl8l0wfrHkllVIi4VhE5Bk09itTozNIgrAzC3sG0CgYEA2+xT4j0fP1Kf2V7Phz/vQ1BTh2Itct2svb/RR7VqU/LbczXoV0wF6D94suJF74Ng861iH+0M2MAoiotV+8WyziuIrSDzU4j5H0hXz0FGG/vnHaDZ7ywN6QCBJpFC7LhFRz/H6mK1F4Gx4vMNFXOXEdqzzsHtngEfMYaO8OAdG3ECgYEAtoRZQvstZgYdAdtrt65x6nti6iuHrakWJBvA4+16yIIq4YtcJQGqXcPfXKDLjmt+ndR+D80ZgPF3oorjYFqx12odUnkbh+ybzOd1282MDUs4VuVS+HD1AojRJO+QPV8g3vMDPTvdiWMSBFsKrmKTSKcgQ0LAN6+SuN2t6Iw+ETUCgYEAmqGQWYKgdeWifzQqtu49TRctpANj8V/sFX7Jp6HnIR+hLWC5/Kd3f9UhCKYQhlfZ03wVgl0qqzxxRPizfFOFZXE2qISCkjm88EHte8xpf0OfPTXcBk7zeODh7jw00jy+eVpfNm4w5lZKvUgn6u1bSzJ7OuUgLfib9jamSrdJwAECgYBl+yZnxoTmXXMvlrovQr6GJj0QeezQqsElH5u34Bbay51Ma8oZqWIvYgdN0qHEhcZND+/Vl5Kh9l+DmA+mPLvgFAbM648LAaBmubgPL2nzwolJ29OKv4C2zHpStt7Sm1h4cQQl5YGF7aB5tTfwtgOo2LtfCnh/PI/tjTs8HSZ8Lw==";	
	
	public static void main(String[] args) throws Exception {
		
		//签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,"RSA2", MY_PRIVATE_KEY,"UTF-8", 
				"json",APIGW_PUBLIC_KEY,"AES","yuzLR7IMjAOZzlNZ83iXIw==", "", "");
		
		JftApiVendorSettleProtocolInfoRequestV1 request = new JftApiVendorSettleProtocolInfoRequestV1();
		
		//4、根据测试环境和生产环境替换相应ip和端口
		request.setServiceUrl("https://ip:port/api/jft/api/vendor/settle/protocol/info/V1");
		
		//5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
		JftApiVendorSettleProtocolInfoV1Biz bizContent = new JftApiVendorSettleProtocolInfoV1Biz();
		bizContent.setAppId("10000000000000045006");
		bizContent.setOutVendorId("RegistApiTest0006");
		
		
		request.setBizContent(bizContent);

		JftApiVendorSettleProtocolInfoResponseV1 response;
		try {
			response =  (JftApiVendorSettleProtocolInfoResponseV1) client.execute(request, System.currentTimeMillis()+"");
			if (response.isSuccess()) {
				//6、业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
				System.out.println("ReturnCode:"+response.getReturnCode());
				System.out.println("response:" + response);
			} else {
				//失败
				System.out.println("ReturnCode:"+response.getReturnCode());
				System.out.println("ReturnMsg:"+response.getReturnMsg());
			}
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}


	}
	
}

