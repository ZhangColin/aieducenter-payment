package com.icbc.api.test;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.request.JftApiDepositTranReceiveRequestV1;
import com.icbc.api.request.JftApiDepositTranReceiveRequestV1.JftApiDepositTranReceiveRequestV1Biz;
import com.icbc.api.response.JftApiDepositTranReceiveResponseV1;

import java.util.ArrayList;
import java.util.List;

/**
 * 聚富通签订e支付小额免密协议查询接口
 * 
 * 请商户配置SDK中的lib文件夹中的jar包后，根据备注1-6提示进行数据替换
 */
public class JftApiDepositTranReceiveTestV1 {
	//1、网关公钥
	protected static final String APIGW_PUBLIC_KEY = "[需替换]工行API网关公钥，请向工行联系获取";
	//2、appid
	protected static final String APP_ID = "[需替换]APP的编号,应用在API开放平台注册时生成";
	//3、密钥对认证方式，公钥在API平台登记，此处为私钥
	protected static final String MY_PRIVATE_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";

	protected static String host = "[需替换]工行API网关地址";
	protected static String apiUrl= host + "api/jft/api/deposit/tran/receive/V1 ";
	protected static String encryptKey = "[需替换]应用方加密串，与工行API开放平台一致";
	public static void main(String[] args) throws Exception {
		//签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,"RSA2", MY_PRIVATE_KEY,"UTF-8", "json",APIGW_PUBLIC_KEY,"AES",encryptKey, "", "");
		JftApiDepositTranReceiveRequestV1 request = new JftApiDepositTranReceiveRequestV1();
		//4、根据测试环境和生产环境替换相应ip和端口
		request.setServiceUrl(apiUrl);
		//5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
		JftApiDepositTranReceiveRequestV1Biz bizContent = new JftApiDepositTranReceiveRequestV1Biz();
		bizContent.setAppId(APP_ID);
		bizContent.setBusiSerialNo("202010081456000000000001");
		bizContent.setBusiType("1");
		bizContent.setTranType("2");
		bizContent.setTranChannel("1");
		bizContent.setNotifyUrl("http://www.xxxxx.com/notifyUrl");
		List<JftApiDepositTranReceiveRequestV1.Tran> tranList = new ArrayList<>();
		JftApiDepositTranReceiveRequestV1.Tran tran1 = new JftApiDepositTranReceiveRequestV1.Tran();
		tran1.setOutTranId("10000000000000000001");
		tran1.setAmount("5.01");
		tran1.setOriOrderId("2018040809462200000");
		tran1.setOriOrderType("02");
		tran1.setOriOrderId("2018040809462200001");
		tran1.setExtension("test");
		tranList.add(tran1);
		bizContent.setTranList(tranList);
		request.setBizContent(bizContent);
		
		JftApiDepositTranReceiveResponseV1 response;
		try {
			response = (JftApiDepositTranReceiveResponseV1) client.execute(request, System.currentTimeMillis()+"");
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
			e.printStackTrace();
		}
	}

}

