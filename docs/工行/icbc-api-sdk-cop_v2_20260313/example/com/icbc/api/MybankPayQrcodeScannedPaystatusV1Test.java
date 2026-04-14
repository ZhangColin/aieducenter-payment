package com.icbc.api;

import com.icbc.api.request.QrcodeScannedPaystatusRequestV1.QrcodeScannedPaystatusRequestV1Biz;
import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.QrcodeScannedPaystatusRequestV1;
import com.icbc.api.response.QrcodeScannedPaystatusResponseV1;

/**
 * 积分消费接口
 * 
 * 请商户配置SDK中的lib文件夹中的jar包后，根据备注1-6提示进行数据替换
 */
public class MybankPayQrcodeScannedPaystatusV1Test {
	
	//1、网关公钥
	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----";
	//2、appid
	protected static final String APP_ID = "1000*****7354649";

	//3、密钥对认证方式，公钥在API平台登记，此处为私钥
	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----";
	public static void main(String[] args) throws Exception {
		
		//签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
				
		QrcodeScannedPaystatusRequestV1 request = new QrcodeScannedPaystatusRequestV1();
		
		//4、根据测试环境和生产环境替换相应ip和端口
		request.setServiceUrl("http://*****/api/mybank/pay/qrcode/scanned/paystatus/V1");
		//5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
		QrcodeScannedPaystatusRequestV1Biz bizContent = new QrcodeScannedPaystatusRequestV1Biz();
		bizContent.setOutTradeNo("111111******66666111"); //外部订单号
		bizContent.setOrderId("");
		bizContent.setMerId("0200*****460001"); //商户编号
		bizContent.setTradeDate("20230515"); //交易日期  格式:YYYYMMDD
		
		request.setBizContent(bizContent);
		QrcodeScannedPaystatusResponseV1 response;
		try {
//			System.out.println(client.execute(request, System.currentTimeMillis()+"").getCardNo());
			response = client.execute(request, System.currentTimeMillis()+"");//msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一
			/*System.out.println(response.getSaesReturnCode());
			System.out.println(response.getPayStatus());
			System.out.println(response.getReturnMsg());*/
			if (response.getReturnCode() == 0) {
				// 6、业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
				//System.out.println("response:" + JSON.toJSONString(response));
				//System.out.println("ReturnCode:"+response.getReturnCode());
				System.out.println("totalAmt:"+response.getTotalAmt());
				System.out.println("pointAmt:"+response.getPointAmt());
				System.out.println("ecouponAmt:"+response.getEcouponAmt());
				System.out.println("merDiscAmt:"+response.getMerDiscAmt());
				System.out.println("couponAmt:"+response.getCouponAmt());
				System.out.println("bankDiscAmt:"+response.getBankDiscAmt());
				System.out.println("paymentAmt:"+response.getPaymentAmt());
				System.out.println("totalDiscAmt:"+response.getTotalDiscAmt());
			} else {
				// 失败
				//System.out.println("response:" + JSON.toJSONString(response));
				//System.out.println("ReturnCode:"+response.getReturnCode());
				//System.out.println("ReturnMsg:"+response.getReturnMsg());
			}		
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}
	}

	
}

