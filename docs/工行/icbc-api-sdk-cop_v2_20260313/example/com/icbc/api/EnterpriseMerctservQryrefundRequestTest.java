package com.icbc.api;


import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.request.EnterpriseMerctservQryrefundRequestV1;
import com.icbc.api.request.EnterpriseMerctservQryrefundRequestV1.EnterpriseMerctservQryrefundRequestV1Biz;
import com.icbc.api.response.EnterpriseMerctservQryrefundResponseV1;

public class EnterpriseMerctservQryrefundRequestTest {

	//证书私钥  

	protected static final String MY_PRIVATE_KEY ="";  

	//网关公钥  

	protected static final String APIGW_PUBLIC_KEY = "";  

	 protected static final String APP_ID = "Oikeclo001";  

	//证书密码

	 protected static final String password = "12345678";

	 //证书公钥  

	protected static final  String MY_PUB_KEY = "";  

//
	public static void test_cop() {
//		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
		
		// 构造client对象  

		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY,MY_PUB_KEY,password);
		// 设置请求对象request
		EnterpriseMerctservQryrefundRequestV1 request = new EnterpriseMerctservQryrefundRequestV1();	
		// 设置请求路径
		request.setServiceUrl("https://ip:port/api/enterpris/merctserv/qryrefund/V1");
		EnterpriseMerctservQryrefundRequestV1Biz bizContent = new EnterpriseMerctservQryrefundRequestV1Biz();
		bizContent.setFunctionID("F-EBANKC-B2CRefund");
		bizContent.setO2oFlag("1");//1-线上，2-线下不上送;不上送不校验，上送会按照标志校验订单类型，如不符合则报错
		bizContent.setOnLine_merID("0200EC20001259");
		bizContent.setOffLine_merID("0200EC20001259");
		bizContent.setChannel_merID("0200EC20001259");
		bizContent.setOrderNum("20081114110804");//1、行外订单号 2、线上分支最长支持30位，线下分支最长支持50位;
		bizContent.setEmallRejectId("20081114110804");
		bizContent.setSerialNo("HFI111");
		bizContent.setRejectType("1");
 

		request.setBizContent(bizContent);
		try {
			// 生成自提交的表单返回客户浏览器，该表单会自动提交完成调用
			EnterpriseMerctservQryrefundResponseV1 response = (EnterpriseMerctservQryrefundResponseV1) client.execute(request, "msgId");
			if (response.isSuccess()) {
			    System.out.println("resultS:"+response.getReturnCode());
			    System.out.println("MsgS:"+response.getReturnMsg());
			} else {
			    System.out.println("resultS:"+response.getReturnCode());
			    System.out.println("MsgS:"+response.getReturnMsg());
			}
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}
		
		
		 
	}
	
	/**
	 * TODO 方法的描述：。
	 * 
	 * <pre>
	 * 
	 * </pre>
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		test_cop();

	}
}
