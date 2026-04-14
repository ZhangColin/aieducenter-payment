package com.icbc.api;

import com.icbc.api.request.CardbusinessNcpayConsumptionPayRequestV1;
import com.icbc.api.request.CardbusinessNcpayConsumptionPayRequestV1.CardbusinessNcpayConsumptionPayRequestV1Biz;
import com.icbc.api.response.CardbusinessNcpayConsumptionPayResponseV1;

/**
 * 新型无卡非签约支付
 * 
 * 请商户配置SDK中的lib文件夹中的jar包后，根据备注1-6提示进行数据替换
 */
public class CardbusinessNcpayConsumptionPayV1Test {
	//1、网关公钥
	protected static final String APIGW_PUBLIC_KEY = "********";

	//2、appid
	protected static final String APP_ID = "******";

	//3、密钥对认证方式，公钥在API平台登记，此处为私钥
	protected static final String MY_PRIVATE_KEY = "************";
	
	public static void main(String[] args) throws Exception {
	
		//签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

		CardbusinessNcpayConsumptionPayRequestV1 request = new CardbusinessNcpayConsumptionPayRequestV1();
		
		//4、根据测试环境和生产环境替换相应ip和端口 http://ip:port/api
		request.setServiceUrl("http://****/api/cardbusiness/ncpay/consumption/pay/V1");
		//5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
		CardbusinessNcpayConsumptionPayRequestV1Biz bizContent = new CardbusinessNcpayConsumptionPayRequestV1Biz();
		bizContent.setOutTradeNo("2020*****9005");
		bizContent.setOrigDateTime("20250501'T'11:24:31");
		bizContent.setMerPrtclNo("020****50201");
		bizContent.setMerId("0200**40135");
		bizContent.setSignNo("20250***842561");
		bizContent.setMerGroupNo("320250***8461");
		bizContent.setCardNo("");
		bizContent.setBizType("100003");
		bizContent.setFeeType("001");
		bizContent.setTotalFee("1000");
		bizContent.setBody("商品描述");
		bizContent.setAttach("附加数据");
		bizContent.setExtendInfo("扩展信息");
		bizContent.setAccessType("5");
		bizContent.setVerifyCode("123456");
		bizContent.setVerifyCodeNo("2017101312341234");
		
		request.setBizContent(bizContent);
		CardbusinessNcpayConsumptionPayResponseV1 response;
		try {
			response = client.execute(request, "2323***4345345");//msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一			
			
			if (response.isSuccess()) {
				// 6、业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
				//System.out.println("ReturnCode:"+response.getReturnCode());
				//System.out.println("response:" + response);
				
			} else {
				// 失败
//				System.out.println("ReturnCode:"+response.getReturnCode());
//				System.out.println("ReturnMsg:"+response.getReturnMsg());
			}		
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}
	}
}
