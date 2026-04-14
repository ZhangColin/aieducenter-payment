package com.icbc.api.test;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.request.JftUserEntrustprtlMngRequestV1;
import com.icbc.api.request.JftUserEntrustprtlMngRequestV1.JftUserEntrustprtlMngRequestV1Biz;
import com.icbc.api.response.JftUserEntrustprtlMngResponseV1;

public class JftApiUserEntrustProtocolMngV1Test {

	protected static final String APIGW_PUBLIC_KEY = "[需替换]工行API网关公钥，请向工行联系获取";
	//2、appid
	protected static final String APP_ID = "[需替换]APP的编号,应用在API开放平台注册时生成";
	//3、密钥对认证方式，公钥在API平台登记，此处为私钥
	protected static final String MY_PRIVATE_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";
    String host = "[需替换]工行API网关地址";
    String apiUrl= host+"/api/jft/api/user/entrust/prtl/msg/V1";
    String encryptKey = "[需替换]应用方加密串，与工行API开放平台一致";

	public static void main(String[] args) throws Exception {
		//签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, "AES", encryptKey, "", "");
		JftUserEntrustprtlMngRequestV1 request = new JftUserEntrustprtlMngRequestV1();		

		//4、根据测试环境和生产环境替换相应ip和端口
		request.setServiceUrl(apiUrl);

		//5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
		JftUserEntrustprtlMngRequestV1.JftUserEntrustprtlMngRequestV1Biz bizContent = new JftUserEntrustprtlMngRequestV1.JftUserEntrustprtlMngRequestV1Biz();
		bizContent.setAppId(APP_ID);
		bizContent.setOutVendorId("[需替换]");
        bizContent.setSrVid("1239841923419123");
		bizContent.setDealFlag("0");
		bizContent.setProjectId("PJ14001401100000xxxx");
		bizContent.setBusiCode("10002");
		bizContent.setBankNo("102");
		bizContent.setAcctNo("622202xxxxxxxx72500");
		bizContent.setAcctName("XUZZ");
		bizContent.setMobile("151xxxx10");
		bizContent.setVerifyCode("620111");
		bizContent.setSendNo("620142");
		bizContent.setIdType("0");
		bizContent.setIdNo("33108xxxxxxxx8108");
		bizContent.setProtocolEndDate("2018-03-02");
		bizContent.setProtocolLimitAmount("20000000");
        bizContent.setCnv2("463");
        bizContent.setExpired("2002");
        bizContent.setBankFlag("1");
        bizContent.setQueryItem1("");
		//.........
		bizContent.setQueryItem15("");
        
        request.setBizContent(bizContent);
		JftUserEntrustprtlMngResponseV1 response;
		try {
			response = (JftUserEntrustprtlMngResponseV1) client.execute(request, System.currentTimeMillis() + "");//msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一
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
