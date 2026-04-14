package com.icbc.api;

import com.icbc.api.request.JftApiPayEtransferAplRequestV1;
import com.icbc.api.request.JftApiPayEtransferAplRequestV1.JftApiPayEtransferAplRequestV1Biz;
import com.icbc.api.response.JftApiPayEtransferAplResponseV1;

public class JftApiPayEtransferAplTestV1 {
	protected static final String MY_PRIVATE_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";
	protected static final String APIGW_PUBLIC_KEY = "[需替换]工行API网关公钥，请向工行联系获取";
	protected static final String APP_ID = "[需替换]应用方APPID";
	String host = "[需替换]工行API网关地址";
    String apiUrl= host+"/api/jft/api/pay/etransfer/apl/V1";
    String encryptKey = "[需替换]应用方加密串，与工行API开放平台一致";

	public void test_cop() throws Exception {

		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,"RSA2", MY_PRIVATE_KEY,"UTF-8", "json",APIGW_PUBLIC_KEY,"AES",encryptKey, "", "");

		JftApiPayEtransferAplRequestV1 request = new JftApiPayEtransferAplRequestV1();
		request.setServiceUrl(apiUrl);

		//请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
		JftApiPayEtransferAplRequestV1Biz bizContent = new JftApiPayEtransferAplRequestV1Biz();
		bizContent.setAppId(APP_ID);//合作方机构编号
		bizContent.setChanSerialNo("202109100000");//渠道请求序列
		bizContent.setOutVendorId("999900001");//会员外部系统编号
		bizContent.setOutUserId("20210910000089");//用户外部系统编号
		bizContent.setAmount("2.90");//金额（元）
		bizContent.setSrcacctNo("622200008987654");//付款方账户
		bizContent.setSrcacctName("测试账号");//付款方户名
		bizContent.setDestacctNo("622200008987656");//收款方账号
		bizContent.setDestacctName("测试账号二");//收款方户名
		bizContent.setNotifyUrl("http://test.com");//通知地址
		bizContent.setSrcacctRmk("备注");//付方回执单备注
		bizContent.setSummary("摘要");//摘要
		bizContent.setNote("附言");//附言
		request.setBizContent(bizContent);

		JftApiPayEtransferAplResponseV1 response;
		try {
			response = (JftApiPayEtransferAplResponseV1) client.execute(request, System.currentTimeMillis()+"");
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
		new JftApiPayEtransferAplTestV1().test_cop();
	}

}
