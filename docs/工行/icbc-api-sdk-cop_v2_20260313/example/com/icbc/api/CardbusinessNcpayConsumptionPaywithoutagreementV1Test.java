package com.icbc.api;

import com.icbc.api.request.CardbusinessNcpayConsumptionPaywithoutagreementRequestV1;
import com.icbc.api.request.CardbusinessNcpayConsumptionPaywithoutagreementRequestV1.CardbusinessNcpayConsumptionPaywithoutagreementRequestV1Biz;
import com.icbc.api.response.CardbusinessNcpayConsumptionPaywithoutagreementResponseV1;

/**
 * 新型无卡非签约支付
 * 
 * 请商户配置SDK中的lib文件夹中的jar包后，根据备注1-6提示进行数据替换
 */
public class CardbusinessNcpayConsumptionPaywithoutagreementV1Test {
	//1、网关公钥
	protected static final String APIGW_PUBLIC_KEY = "****";

	//2、appid
	protected static final String APP_ID = "******";

	//3、密钥对认证方式，公钥在API平台登记，此处为私钥
	protected static final String MY_PRIVATE_KEY = "***";
	
	public static void main(String[] args) throws Exception {
	
		//签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

		CardbusinessNcpayConsumptionPaywithoutagreementRequestV1 request = new CardbusinessNcpayConsumptionPaywithoutagreementRequestV1();
		
		//4、根据测试环境和生产环境替换相应ip和端口
		request.setServiceUrl("http://****/api/cardbusiness/ncpay/consumption/paywithoutagreement/V1");
		//5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
		CardbusinessNcpayConsumptionPaywithoutagreementRequestV1Biz bizContent = new CardbusinessNcpayConsumptionPaywithoutagreementRequestV1Biz();
		
		bizContent.setOutTradeNo("02001384*****12");
		bizContent.setOrigDateTime("2020-03-05T17:40:00");
		bizContent.setMerPrtclNo("234****21");
		bizContent.setMerId("02004***01");
		bizContent.setBizType("100003");
		bizContent.setCardNo("0200****01");
		bizContent.setMobileNo("135****1234");
		bizContent.setIdType("1");
		bizContent.setIdNo("41******01");
		bizContent.setHolderName("张三");
		bizContent.setCardExpiredDate("1");
		bizContent.setCardCvv2("1");
		bizContent.setVerifyCode("1");
		bizContent.setVerifyCodeNo("1");
		bizContent.setBody("");
		bizContent.setFeeType("001");
		bizContent.setTotalFee("1");
		bizContent.setAttach("");
		bizContent.setExtendInfo("");
		bizContent.setAccessType("5");
		
		request.setBizContent(bizContent);
		CardbusinessNcpayConsumptionPaywithoutagreementResponseV1 response;
		try {
			response = client.execute(request, "2334***4439");//msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一			
			
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
