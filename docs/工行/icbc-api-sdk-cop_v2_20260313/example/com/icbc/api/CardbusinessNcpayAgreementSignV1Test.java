package com.icbc.api;

import com.icbc.api.request.CardbusinessNcpayAgreementSignRequestV1;
import com.icbc.api.request.CardbusinessNcpayAgreementSignRequestV1.CardbusinessNcpayAgreementSignRequestV1Biz;
import com.icbc.api.response.CardbusinessNcpayAgreementSignResponseV1;

public class CardbusinessNcpayAgreementSignV1Test {
	//1、网关公钥
	protected static final String APIGW_PUBLIC_KEY = "****";

	//2、appid
	protected static final String APP_ID = "******";

	//3、密钥对认证方式，公钥在API平台登记，此处为私钥
	protected static final String MY_PRIVATE_KEY = "***";
	
	public static void main(String[] args) throws Exception {
	
		//签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

		CardbusinessNcpayAgreementSignRequestV1 request = new CardbusinessNcpayAgreementSignRequestV1();
		//4、根据测试环境和生产环境替换相应ip和端口
		request.setServiceUrl("http://****/api/cardbusiness/ncpay/agreement/sign/V1");
		//5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
		CardbusinessNcpayAgreementSignRequestV1Biz bizContent = new CardbusinessNcpayAgreementSignRequestV1Biz();
		request.setBizContent(bizContent);
		bizContent.setTrxSerno("***");
		bizContent.setTrxDate("***");
		bizContent.setTrxTime("***");
		bizContent.setMerSerPrtclno("***");
		bizContent.setMerSerId("***");
		bizContent.setMerSerFlag("***");
		bizContent.setMerGroupNo("***");
		bizContent.setCardNo("***");
		bizContent.setIdType("***");
		bizContent.setIdNo("***");
		bizContent.setHolderName("***");
		bizContent.setCardExpiredDate("***");
		bizContent.setCardCvv2("***");
		bizContent.setMobileNo("***");
		bizContent.setVerifyCode("***");
		bizContent.setVerifyCodeNo("***");
		bizContent.setExtendInfo("***");
		CardbusinessNcpayAgreementSignResponseV1 response;
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


