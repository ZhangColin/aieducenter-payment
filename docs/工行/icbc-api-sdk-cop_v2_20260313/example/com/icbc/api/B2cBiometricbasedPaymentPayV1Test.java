package com.icbc.api;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import com.icbc.api.internal.util.codec.Base64;
import com.icbc.api.request.B2cBiometricbasedPaymentPayRequestV1;
import com.icbc.api.request.B2cBiometricbasedPaymentPayRequestV1.B2cBiometricbasedPaymentPayRequestV1Biz;
import com.icbc.api.response.B2cBiometricbasedPaymentPayResponseV1;

public class B2cBiometricbasedPaymentPayV1Test {
	
	//appid
	protected static final String APP_ID = "10000000000000002156";
	
	//合作方私钥
	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDs6rAC+FOC9YtxP4BCjoO1N33H8DXn7GlOg1ohuaBeqKU+zHTNXpYrB9Egawe7D8UzrT0WQY+oyAnfKeGHjnrsk6sdzYtNJqoywaJlh9UVDsVDhnFaROeLC6CsobfZ2As4ZNtypdjO4aAKJmsAsZ5jvkuvgNJGFgz6A3GsH8H8/OoDw3o/LEn41/79Mrt6SyJEBkGfje+YdKsIgzgnlnWMY87HXGMasDQj4iqFAHU12EU272Wt4BXnilT7Lmh9v5e/wPJwD8waW7hSHlj1+mmfElq722+sqlm6JLgVD7XiH71VEKfSDxleVw/h1mJ4fMH5W5e92zKiw5XgF6hXu98xAgMBAAECggEBAMMRxZTcdJ6KoKLBX1Dt2QI3y4f5f9kTLe3odyj3l1T5VP3tCGska07I4+bmWXorVPrktDyEBM46zu4BpOGhbhMmzyj92anPoSmjh5xDiC4LeF7N7r0dqOwyybv6d8+VdD+dw5aVeXt2CPjygv4SQVDtGZJK5lewxkSA23KWv3oWCs8fi5mKNpc+1eL2tDaKQYq6vWjkE3jozXAa6VhV32KVm68YbuRBDY2dhXx/NI19CPcJB3mO6qqtA+n7v3bNOVia5bAYloCjv83vYVdYmM/9Zvq6mrwC+k+FefVGHVT9I7+4Ng9349Ec7Sm4blMFi7fBJj2WqLMpcxtu0JMMZ3ECgYEA+6Di6ElCxmFTdbMwv8rHie4ExELW2pASdaIcHlEGgoE03VEk7r41q5PU9aFODqYEQNMQsKD7Nupyrle+1XoqIIMTko3LGinjdj2m2wMt3rhwIkhEYrKbsGfGopfWd1lvJc/SSnVANuJA+rDbe1eu+lFo1rpW/YIiT6OQiSd3a6UCgYEA8Qhe/nk43A8VO5XYXwg0OsCpsFD3X5ZgEAVqg7x7ipMwbn2wKRKizYe9TNQTZ4WH0txVeGS2IWUJ4WxkEBO+dGVlMM65zobPrArb5DhA/zTE4uPNEgCvrUpNrltdOJ4TA/8cqcYLIl/Yb44el87qVha4bxaEbdvrZCYivqU4f50CgYBRdl+fdgBROzcK1s1EekCpTc5rP3xRW+0t+iKCShcCbxQPRnLfe+LiPkTmlKcxpNuhpPM1S5ioybQA71VPxXIX/nd6rb8AJGmIYSNAqkHSZgGfh6fkEBDJRn+PKfr7vo92PVbGVjlIy2zf/BvCTbvPPEXlpoP9JIE5KSnkzUsjJQKBgQCugioMwgV5xe3Qgy/xtgXil8UDo8tGSKBqWRGCKX+kl+aTyKo3QofemK1kp5MY+2OYNfAnx3rZ+MZLkGduAG1+5j56NOHv9kj7Pa1wsY3VeAYoY1gA9cA7jbVqTDnVGRWZaiX9gSZkj9IrClF6BdRB2hdUTpA43eWyT64OK58ZPQKBgQC/CzncRwpeNd5xAy5CX1ZgH3DPGGJuYMA/07gSmQ7CsD6LK7uORJAY2YwToy3OcFb3bon/3yU0jQM/KDmSfBt0M4LKxkOLtdo1DkzSTgVni2KlfU0gcRyWYlEi7/KEhJQaPjMf5AJZReh83JumiusqrgdkMfh0XcR1mYbewAn9iQ==";
	
	//网关公钥
	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
	
	//AES秘钥
	protected static final String AES_KEY = "LEWdRZPqgkloxb3bMhnhQQ==";
		

	public void test_cop() throws IcbcApiException {
		DefaultIcbcClient client = new DefaultIcbcClient(
														APP_ID,
														IcbcConstants.SIGN_TYPE_RSA2, 
														MY_PRIVATE_KEY,
														IcbcConstants.CHARSET_UTF8, 
														IcbcConstants.FORMAT_JSON,
														APIGW_PUBLIC_KEY, 
														IcbcConstants.ENCRYPT_TYPE_AES,
														AES_KEY, 
														null, null);
		
		B2cBiometricbasedPaymentPayRequestV1 request = new B2cBiometricbasedPaymentPayRequestV1();
		Date date = new Date();
		Base64.encodeBase64String(null);
        SimpleDateFormat d = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateStr = d.format(date);
        
		//根据测试环境和生产环境替换相应ip和端口http://gw.dccnet.com.cn/
        request.setServiceUrl("https://apipcs4.dccnet.com.cn/api/biometricbased/payment/pay/V1");

		
		B2cBiometricbasedPaymentPayRequestV1Biz bizContent = new B2cBiometricbasedPaymentPayRequestV1Biz();
		bizContent.setConsumerId("XX商城编号YYY售卖机");//调用方名称,用于日志记录
		bizContent.setMerchantId("020001020461");//商户ID，商户在工行开户线上商户时，由工行告知商户。
		bizContent.setMerchantType("2");//线上线下商户标志，1-线上；2-线下。
		bizContent.setOrderId(dateStr);//订单号。客户支付后商户网站产生的一个唯一的定单号，该订单号应该在相当长的时间内不重复。工行通过订单号加订单日期来唯一确认一笔订单的重复性。
		bizContent.setOrderAmt("6050");//订单金额。客户支付订单的总金额，一笔订单一个，以分为单位。不可以为零，必需符合金额标准。
		bizContent.setOrderDate(dateStr);//交易日期时间 格式为：YYYYMMDDHHmmss 要求在银行系统当前时间的前1小时和后12小时范围内，否则判定交易时间非法。
		bizContent.setEndTime("20180430000000");//交易截止日期时间 格式为：YYYYMMDDHHmmss 
		bizContent.setCustomerId("18100000014");//上送用于识别客户的标识信息，如手机号等
		bizContent.setFeatureType("face");//生物特征类型 暂只支持上送face-面部识别
		bizContent.setFeatureValue("$具体的生物识别采集信息");//生物特征数据  对于刷脸支付需要进行base64编码处理
		bizContent.setPayLimit("no_credit");//支付方式限定 送”no_credit“表示不支持信用卡支付；不上送或上送空表示无限制
		bizContent.setGoodsBody("芬达");//商品描述
		bizContent.setGoodsDetail("{'good_name':'芬达橙味300ml罐装','good_id':'1001','good_num':'1'}");//商品详情
		bizContent.setAttach("");//业务扩展参数，商户可定制内容，格式为json String
		
		request.setBizContent(bizContent);

		B2cBiometricbasedPaymentPayResponseV1 response;
		try {
			response = (B2cBiometricbasedPaymentPayResponseV1) client.execute(request, "msgId");
			if (response.isSuccess()) {
				// 业务成功处理
				String orderId = response.getOrderId();
			} else {
				// 失败
			}
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}
		UiIcbcClient client1 = new UiIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
		client1.buildPostForm(request);
	}
	
}
