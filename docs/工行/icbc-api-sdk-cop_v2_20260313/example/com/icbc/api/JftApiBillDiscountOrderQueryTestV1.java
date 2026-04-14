package com.icbc.api;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.request.JftApiBillDiscountOrderQueryRequestV1;
import com.icbc.api.response.JftApiBillDiscountOrderQueryResponseV1;

public class JftApiBillDiscountOrderQueryTestV1 {
	protected static final String MY_PRIVATE_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";
	protected static final String APIGW_PUBLIC_KEY = "[需替换]工行API网关公钥，请向工行联系获取";
	protected static final String APP_ID = "[需替换]应用方APPID";
	String host = "[需替换]工行API网关地址";
    String apiUrl= host+"/api/jft/api/billdis/orderinfo/qry/V1";
    String encryptKey = "[需替换]应用方加密串，与工行API开放平台一致";

	public void test_cop() throws Exception {

		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,"RSA2", MY_PRIVATE_KEY,"UTF-8", "json",APIGW_PUBLIC_KEY,"AES",encryptKey, "", "");

		JftApiBillDiscountOrderQueryRequestV1 request = new JftApiBillDiscountOrderQueryRequestV1();
		request.setServiceUrl(apiUrl);

		//请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
		JftApiBillDiscountOrderQueryRequestV1.JftApiBillDiscountOrderQueryRequestV1Biz bizContent = new JftApiBillDiscountOrderQueryRequestV1.JftApiBillDiscountOrderQueryRequestV1Biz();
		bizContent.setAppId(APP_ID);//合作方编号
		bizContent.setOutVendorId("999900001");//商户外部系统编号
		bizContent.setOrderNo("20231162156162151161151616515");//订单号
		request.setBizContent(bizContent);

		JftApiBillDiscountOrderQueryResponseV1 response;
		try {
			response = (JftApiBillDiscountOrderQueryResponseV1) client.execute(request, System.currentTimeMillis()+"");
			if (response.isSuccess()) {
				//业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
				System.out.println("returnCode:"+response.getReturnCode());
				System.out.println("response:" + response);
			} else {
				//失败
				System.out.println("returnCode:"+response.getReturnCode());
				System.out.println("returnMsg:"+response.getReturnMsg());
			}
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		new JftApiBillDiscountOrderQueryTestV1().test_cop();
	}

}
