package com.icbc.api;


import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.request.EnterpriseMerctservRefundRequestV1;
import com.icbc.api.request.EnterpriseMerctservRefundRequestV1.EnterpriseMerctservRefundRequestV1Biz;
import com.icbc.api.response.EnterpriseMerctservRefundResponseV1;

public class EnterpriseMerctservRefundRequestTest {

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

//		DefaultIcbcClient client = new DefaultIcbcClient("999", "999", "999");
		EnterpriseMerctservRefundRequestV1 request = new EnterpriseMerctservRefundRequestV1();		
		request.setServiceUrl("https://ip:port/api/enterpris/merctserv/refund/V1");
		EnterpriseMerctservRefundRequestV1Biz bizContent = new EnterpriseMerctservRefundRequestV1Biz();
		bizContent.setFunctionID("F-EBANKC-B2CRefund");
		bizContent.setO2oFlag("1");//1-线上，2-线下不上送;不上送不校验，上送会按照标志校验订单类型，如不符合则报错
		bizContent.setOnLine_merID("0200EC20001259");
		bizContent.setOffLine_merID("0200EC20001259");
		bizContent.setChannel_merID("0200EC20001259");
		bizContent.setPayDate("20180529");
		bizContent.setOrderNum("20081114110804");//1、行外订单号 2、线上分支最长支持30位，线下分支最长支持50位;
		bizContent.setEmallRejectId("20081114110804");

		bizContent.setRejectReson("退款");
		bizContent.setRejectAmt("100");
		bizContent.setThirdPayFlag("1");
		bizContent.setOrderNumType("0");//0-商户自定义订单号，1-工行订单号
		bizContent.setMerattach("商户自定义对账字段");
		
		request.setBizContent(bizContent);

		EnterpriseMerctservRefundResponseV1 response;
		try {
			response = client.execute(request, "msgId");
			if (response.isSuccess() ) {
				 // 业务成功处理 
				  System.out.println("success");//
			} else {
				 // 失败 
				  System.out.println("error");
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
