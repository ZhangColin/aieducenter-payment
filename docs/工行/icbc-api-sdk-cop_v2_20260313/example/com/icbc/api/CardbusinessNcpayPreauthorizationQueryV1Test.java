package com.icbc.api;

import com.icbc.api.request.CardbusinessNcpayPreauthorizationQueryRequestV1;
import com.icbc.api.request.CardbusinessNcpayPreauthorizationQueryRequestV1.CardbusinessNcpayPreauthorizationQueryRequestV1Biz;
import com.icbc.api.response.CardbusinessNcpayPreauthorizationQueryResponseV1;

/**
 * 新型无卡非签约支付
 * 
 * 请商户配置SDK中的lib文件夹中的jar包后，根据备注1-6提示进行数据替换
 */
public class CardbusinessNcpayPreauthorizationQueryV1Test {
	//1、网关公钥
	protected static final String APIGW_PUBLIC_KEY = "********";

	//2、appid
	protected static final String APP_ID = "******";

	//3、密钥对认证方式，公钥在API平台登记，此处为私钥
	protected static final String MY_PRIVATE_KEY = "************";
	
	public static void main(String[] args) throws Exception {
	
		//签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

		CardbusinessNcpayPreauthorizationQueryRequestV1 request = new CardbusinessNcpayPreauthorizationQueryRequestV1();
		
		//4、根据测试环境和生产环境替换相应ip和端口 http://ip:port/api
		request.setServiceUrl("http://ip:port/api/cardbusiness/ncpay/preauthorization/query/V1");
		//5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
		CardbusinessNcpayPreauthorizationQueryRequestV1Biz bizContent = new CardbusinessNcpayPreauthorizationQueryRequestV1Biz();
		bizContent.setOutTradeNo("202007***20026");
		bizContent.setMerId("0200****40246");
		bizContent.setOrderId("020001*************057001");
		bizContent.setQryFlag("1");
		
		request.setBizContent(bizContent);
		CardbusinessNcpayPreauthorizationQueryResponseV1 response;
		try {
			response = client.execute(request, "2323***4345345");//msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一			
			
			if (response.isSuccess()) {
				// 6、业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
				//System.out.println("ReturnCode:"+response.getReturnCode());
				//System.out.println("response:" + response);
				
			} else {
				// 失败
				//System.out.println("ReturnCode:"+response.getReturnCode());
				//System.out.println("ReturnMsg:"+response.getReturnMsg());
			}		
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}
	}
}
