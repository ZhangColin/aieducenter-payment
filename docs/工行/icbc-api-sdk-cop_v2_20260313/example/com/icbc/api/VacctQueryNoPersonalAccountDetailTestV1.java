package com.icbc.api;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.request.VacctQueryNoPersonalAccountDetailRequestV1;
import com.icbc.api.request.VacctQueryNoPersonalAccountDetailRequestV1.VacctQueryNoPersonalAccountDetailRequestV1Biz;
import com.icbc.api.response.VacctQueryNoPersonalAccountDetailResponseV1;

/**
 * 聚富通汇总支付托管指令结果查询接口
 * 
 * 请商户配置SDK中的lib文件夹中的jar包后，根据备注1-6提示进行数据替换
 */
public class VacctQueryNoPersonalAccountDetailTestV1 {
	//1、网关公钥
	protected static final String APIGW_PUBLIC_KEY = "[需替换]工行API网关公钥，请向工行联系获取";
	//2、appid
	protected static final String APP_ID = "[需替换]APP的编号,应用在API开放平台注册时生成";
	//3、密钥对认证方式，公钥在API平台登记，此处为私钥
	protected static final String MY_PRIVATE_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";

	protected static String host = "[需替换]工行API网关地址";
	protected static String apiUrl= host+"api/jft/api/jft/account/transaction/V1/search/V1";
	protected static String encryptKey = "[需替换]应用方加密串，与工行API开放平台一致";
	public static void main(String[] args) throws Exception {
		//签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,"RSA2", MY_PRIVATE_KEY,"UTF-8", "json",APIGW_PUBLIC_KEY,"AES",encryptKey, "", "");
		VacctQueryNoPersonalAccountDetailRequestV1 request = new VacctQueryNoPersonalAccountDetailRequestV1();
		//4、根据测试环境和生产环境替换相应ip和端口
		request.setServiceUrl(apiUrl);
		//5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
		VacctQueryNoPersonalAccountDetailRequestV1Biz bizContent = new VacctQueryNoPersonalAccountDetailRequestV1Biz();
		bizContent.setCurrtype("1");
		bizContent.setStardate("2024-10-25");
		bizContent.setEnddate("2024-10-25");
		bizContent.setFuncType("1");
		bizContent.setInit_flag("1");
		bizContent.setYearmon(null);
		bizContent.setSerialno(null);
		bizContent.setAppId(APP_ID);
		bizContent.setOutVendorId("ZM1127001");
		bizContent.setChannel("01");
		request.setBizContent(bizContent);

		VacctQueryNoPersonalAccountDetailResponseV1 response;
		try {
			response = (VacctQueryNoPersonalAccountDetailResponseV1) client.execute(request, System.currentTimeMillis()+"");
			System.out.println(response);
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
			System.out.println(e);
		}
	}

}

