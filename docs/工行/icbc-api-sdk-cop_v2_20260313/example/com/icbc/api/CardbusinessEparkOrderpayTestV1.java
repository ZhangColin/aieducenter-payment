package com.icbc.api;
import java.util.HashMap;
import java.util.logging.Logger;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.CardbusinessEparkOrderpayRequestV1;
import com.icbc.api.request.CardbusinessEparkOrderpayRequestV1.CardbusinessEparkOrderpayRequestV1Biz;
import com.icbc.api.request.CardbusinessEparkOrderpayRequestV1.CardbusinessEparkOrderpayRequestV1PrivateInfo;
import com.icbc.api.response.CardbusinessEparkOrderpayResponseV1;

/**
 * 银行卡场景E停车出场
 * 
 * 请商户配置SDK中的lib文件夹中的jar包后，根据备注1-6提示进行数据替换
 */
public class CardbusinessEparkOrderpayTestV1 {
	//1、网关公钥
	protected static final String APIGW_PUBLIC_KEY = "XXXXXX";
	//2、appid
	protected static final String APP_ID = "XXXXXX";

	//3、密钥对认证方式，公钥在API平台登记，此处为私钥
	protected static final String MY_PRIVATE_KEY = "XXXXXX";
	public static void main(String[] args) throws Exception {
	
		//签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

		CardbusinessEparkOrderpayRequestV1 request = new CardbusinessEparkOrderpayRequestV1();
		
		//4、根据测试环境和生产环境替换相应ip和端口 http://ip:port/api
		request.setServiceUrl("http://XXXXXX:XX/api/cardbusiness/epark/orderpay/V1");
		//5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
		CardbusinessEparkOrderpayRequestV1Biz bizContent = new CardbusinessEparkOrderpayRequestV1Biz();
		CardbusinessEparkOrderpayRequestV1PrivateInfo retMap = new CardbusinessEparkOrderpayRequestV1PrivateInfo();
		retMap.setDepotId("znpark0000000070");
		retMap.setStartTime("2020-05-07 07:00:00");
		retMap.setEndTime("2020-05-07 10:00:00");
		retMap.setPlateNumber("粤A00239");
		retMap.setOrderId("10002784");
		retMap.setIsOut("2");
		retMap.setActualPay("1.5");
		retMap.setDepotMid("1");
		bizContent.setPrivateInfo(retMap);
		request.setBizContent(bizContent);
		CardbusinessEparkOrderpayResponseV1 response;
		String msgId = String.valueOf(System.currentTimeMillis());
		try {
			response = client.execute(request, msgId);//msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一
//			if(response.getReturnCode()==0){
//				System.out.println("data:"+ JSON.toJSONString(response.getData()));
//			}
//
//			System.out.println("ReturnCode:"+response.getReturnCode());
//			System.out.println("ReturnMsg:"+response.getReturnMsg());
			
		    }catch (IcbcApiException e) {
			       e.printStackTrace();
		    }
		} 
}
