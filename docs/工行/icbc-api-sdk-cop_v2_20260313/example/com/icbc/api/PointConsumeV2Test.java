package com.icbc.api;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.icbc.api.request.PointConsumeRequestV2;
import com.icbc.api.request.PointConsumeRequestV2.GetPointConsumeRequestV2Biz;
import com.icbc.api.response.PointConsumeResponseV2;

/**
 * 积分消费接口
 * 
 * 请商户配置SDK中的lib文件夹中的jar包后，根据备注1-6提示进行数据替换
 */
public class PointConsumeV2Test {
	
	private static final Log logger = LogFactory.getLog(PointConsumeV2Test.class);
	
	//1、网关公钥
	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
	
	//2、appid
	protected static final String APP_ID = "1000********4554";

	//3、密钥对认证方式，公钥在API平台登记，此处为私钥
	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDgoQYufS5C8vZemxlnBmtgZ3rPXRCPAjP1scZ3A+q/g9ZyXGIr0r2WMwA9d4HNhW4eVer+E9ipXFGb1MQkqwYIR/Oc9fLgVmpW1Qp5F5dCQLfpgQb9fRrJ3sYV625BAOkl+Z++LDEatzHPmtUK7tug5GumYJAGoyQmZv7laM5H1GzurEc4/8JwcsuYJwJ409rS47NEY99ZQUEJijbhqzJ3SC3lK3UOXVC0oTlBShAjVYuE6ZzfF52wwDMUIZ5KsXNv/rWYbiF7sJYZ75/s2otwxoKwXtKx3yUhuMsy50XeVIuz84EKAHCyqASGF9NsUjPqQu5kjK2pGvRMrXahM/xRAgMBAAECggEBAN6FZXREOcHEod8SUlXG/DPiDrzA+phQnFx2IZ7g2HmkBpn/fpjfCURzfXdmncWUuoN+pW3RBz6u34E5gpy4dx9Y97Gzy0gFEw61uMxAXG0bY5UDZoNSnWkBMMWiFfR7DHu8D0z6xiIq/EMmpIlSGWwHG8UTv1lz32nTegqUilSH9aa7W2M42ubTDcCNGWVG3n1bWmqyuRqq1EHamksYVPg/7lMNakSfzJcqtjdwZ/O5/cmS24t6BgIVpjuB5oJWe46Udw7hgdGjeYcSS3iwNROOQDIs/rGZ/qtOLMZaBlBKmvbjGUenVBQwd6g1IbmHgMgdWdUlELZXMSqs3VE+82ECgYEA9eaybhwBmHaCdzz2evI/VVaEbd9tSasX7f0o7RnOFxWL9vQXkbQ4vRiS5NztTTpN+MmKDbNulw2fwssaOSzkgy+lb8JBkzLszWloZTMLSeWrRsZ6N3f3wlzgXqQHX4vUagv343cQahaT5IrV+9f1JflY2vGRMJ+VgtS/3nvEJf0CgYEA6dquM49YL8QlWvVrlzvhF14IqeX22IELLdJpZROiNg8jNWRgsDt/AUghvZB159Nv10xCaiaJKr2cTQtZU5uRmAWshd5nu4Qa0EIiouq45CNQ+Q0Z3BTb2NKwyWkMYFTQw8tebGQ5YM7ldjdpVFr4K6qXRunr27EjjzFaStv1VeUCgYAF0crhPjpvcEaQKdoveLI1A9Iog4QjnS/4yugADH9LHpBsbMRuB1TcbMt1LSFUlY+o1qQ0xM1WN9VTF9lsBvr1e9gvd+pzYF4oUllNI9MdkZjnGOVlkKpFaEtLEhi2kymYxOW99WBnEle3re14g1ji6C7JJ7Rm8S/po71St50hxQKBgEPZzidplNWvHIOKp5/moMWN5AefkIJ61oqB/VB1ZOA4fie0qxUW/h+aFSgSLROJVm7qUm7IJY5AG4SDIXTPs2ufYdIpmIFgsALy3WfVoLWYxZqtQW6Ios0DPIYDs7KTiUr1y7y5S6iZ/xvQsf/msRDCW0gZAPTqLX4TfLYxobo9AoGBAOc+zma9pn+VgY4HCo/ReL5PqF3YjRkXGnxq2BUP3mDyX0m1eii2seu+GmabVaQfWS4dx9uRGPIGoJWuZ2nNRR8bjRzwux3NG8uoPAY3LcNnrV62KWpv0hm1M99xa4x/W+1wD3bgTcIBn1mFp+ZW3VVF4eFdGsXodE3TVcnAzJy2";	
	public static void main(String[] args) throws Exception {
		
		//签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
				
		PointConsumeRequestV2 request = new PointConsumeRequestV2();
		
		//4、根据测试环境和生产环境替换相应ip和端口
		request.setServiceUrl("http://ip/api/saes/consume/point/V2");
		//5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
		GetPointConsumeRequestV2Biz bizContent = new GetPointConsumeRequestV2Biz();
		bizContent.setZoneNo("200"); //交易地区号
		bizContent.setBrno("998"); //交易网点号
		bizContent.setMerNo("020001010017");
		bizContent.setIftrxsernb("567700085"); //交易流水号，9位 
		bizContent.setSeqNo("20181*****0000085"); //交易序列号
		bizContent.setoSeqNo(""); //原交易序列号,在revtranf为1时必输
		bizContent.setSerialNo("20190********0085");
		bizContent.setRevtranf("0"); //交易类型0-消费，1-退货
		bizContent.setMdCardNo("62220******059"); //交易卡号
		bizContent.setPtAmount("0"); //积分值，非金额
		bizContent.setChnSeqNo("201902**********001");
		bizContent.setChanelType("0"); //渠道类型
		bizContent.setAppType("0"); //应用类型
		bizContent.setInTrxCode("50221"); //渠道内部交易码 
		bizContent.setCurrType("001"); //币种
		bizContent.setAmount("10023"); //交易金额
		bizContent.setMac("2233445566");
		bizContent.setIp("1");
		bizContent.setTermId("VTSI101.1"); //终端号
		bizContent.setReadFlag("1");
		bizContent.setNotes1("");
		bizContent.setCommUse("");
		bizContent.setTranUse("");
		bizContent.setTranTime("");
		bizContent.setTraceNo("");
		bizContent.setGdtlFlag("0"); //积分平台
		bizContent.setGdtlType("0"); //积分平台
		bizContent.setGdtlNote("");
		bizContent.setCkPinFlag("0"); //TODO上送主机  密码检查标志  0-不检查 1-检查
		bizContent.setAccpin("");
		bizContent.setCertType("");
		bizContent.setCertNo("");
		bizContent.setPtrssernb("");
		bizContent.setCvv("");
		bizContent.setSvrCode("");
		bizContent.setExpdate("");
		bizContent.setCvv2("");
		bizContent.setMsgdelayf("0"); //TODO上送主机   短信延时发送标志   1-延时发送短信 非1-不延时发送短信
		bizContent.setAgentNo("");
		bizContent.setAgentType("");
		bizContent.setFlagPart("0"); //TODO上送主机  部分扣款标志  1-部分扣款 非1-非部分扣款
		bizContent.setAmountLp("0");
		bizContent.setPswChkF("0");  //TODO上送主机  
		bizContent.setFlagTc("0");  //TODO上送主机   交易验证方式
		bizContent.setTrxSpot("");
		bizContent.setNbhFlag("0");  //TODO上送主机  实时入内部户标志
		bizContent.setSummary("");
		bizContent.setPayType("");
		bizContent.setIntegAmount("");
		bizContent.setPointType("");
		
		request.setBizContent(bizContent);
		PointConsumeResponseV2 response;
		try {
			response = client.execute(request, System.currentTimeMillis()+"");//msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一
			if (response.isSuccess()) {
				// 6、业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
				logger.info("ReturnCode:"+response.getReturnCode());
				logger.info("response:" + response);
			} else {
				// 失败
				logger.info("ReturnCode:"+response.getReturnCode());
				logger.info("ReturnMsg:"+response.getReturnMsg());
			}		
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}
	}

	
}

