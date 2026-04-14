package com.icbc.api;

import com.icbc.api.request.CardbusinessNcpayPreauthorizationRevokeRequestV1;
import com.icbc.api.request.CardbusinessNcpayPreauthorizationRevokeRequestV1.CardbusinessNcpayPreauthorizationRevokeRequestV1Biz;
import com.icbc.api.response.CardbusinessNcpayPreauthorizationRevokeResponseV1;

/**
 * 新型无卡非签约支付
 * 
 * 请商户配置SDK中的lib文件夹中的jar包后，根据备注1-6提示进行数据替换
 */
public class CardbusinessNcpayPreauthorizationRevokeV1Test {
	//1、网关公钥
	protected static final String APIGW_PUBLIC_KEY = "********";

	//2、appid
	protected static final String APP_ID = "******";

	//3、密钥对认证方式，公钥在API平台登记，此处为私钥
	protected static final String MY_PRIVATE_KEY = "************";
	
	public static void main(String[] args) throws Exception {
	
		//签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

		CardbusinessNcpayPreauthorizationRevokeRequestV1 request = new CardbusinessNcpayPreauthorizationRevokeRequestV1();
		
		//4、根据测试环境和生产环境替换相应ip和端口 http://ip:port/api
		request.setServiceUrl("http://ip:port/api/cardbusiness/ncpay/preauthorization/revoke/V1");
		//5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
		CardbusinessNcpayPreauthorizationRevokeRequestV1Biz bizContent = new CardbusinessNcpayPreauthorizationRevokeRequestV1Biz();
		bizContent.setOutTradeNo("20200****20026");
		bizContent.setOrigDateTime("2021-03-20T14:31:00");
		bizContent.setMerPrtclNo("0200****60201");
		bizContent.setMerId("020*****246");
		bizContent.setSignNo("20210******7505");
		bizContent.setCardNo("");
		bizContent.setoOrderId("0200010**********07080057001");
		bizContent.setoOutTradeNo("");
		bizContent.setoTrxAmt("1");
		bizContent.setoAuthorNo("523177");
		bizContent.setExtendInfo("523177");
		
		request.setBizContent(bizContent);
		CardbusinessNcpayPreauthorizationRevokeResponseV1 response;
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
