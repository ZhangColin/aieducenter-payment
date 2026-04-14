package com.icbc.api;

import org.junit.Test;
import java.util.UUID;
import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.request.MybankAccountMicrofundCashdepositCzcardbalancequeryRequestV1;
import com.icbc.api.request.MybankAccountMicrofundCashdepositCzcardbalancequeryRequestV1.MybankAccountMicrofundCashdepositCzcardbalancequeryRequestV1Biz;
import com.icbc.api.response.MybankAccountMicrofundCashdepositCzcardbalancequeryResponseV1;

public class MybankAccountMicrofundCashdepositCzcardbalancequeryTestV1 {

	// 应用私钥
	protected static final String MY_PRIVATE_KEY = "私钥";
	// 网关公钥
	protected static final String APIGW_PUBLIC_KEY = "网关公钥";
	// 应用ID，暂定，APP申请分配
	protected static final String APP_ID = "10000000000004098018";
	protected static final String SIGN_TYPE = "RSA2";


	public void test() throws IcbcApiException{
		try{
		// 签名类型为RSA时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA，其他参数使用缺省值
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, SIGN_TYPE, MY_PRIVATE_KEY,APIGW_PUBLIC_KEY);
		MybankAccountMicrofundCashdepositCzcardbalancequeryRequestV1 request = new MybankAccountMicrofundCashdepositCzcardbalancequeryRequestV1();
		request.setServiceUrl("http://端口/api/mybank/account/microfund/cashdeposit/czcardbalancequery/V1");
		MybankAccountMicrofundCashdepositCzcardbalancequeryRequestV1Biz bizContent = new MybankAccountMicrofundCashdepositCzcardbalancequeryRequestV1Biz();
		bizContent.setMarketNo("0541183");
		bizContent.setMemno("");
		bizContent.setOperType("2");
		bizContent.setOrderNo("");
		bizContent.setCardNo("62326000000000633");
		bizContent.setoApp("F-MBPS");
		request.setBizContent(bizContent);
		MybankAccountMicrofundCashdepositCzcardbalancequeryResponseV1 response = new MybankAccountMicrofundCashdepositCzcardbalancequeryResponseV1();
		String msgId = UUID.randomUUID().toString().replaceAll("-", "");
		response = client.execute(request, msgId); // msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一
		
		if (response.isSuccess()) {
			System.out.println("return_code             :       " + response.getReturnCode());
			System.out.println("return_msg              :       " + response.getReturnMsg());
			System.out.println("msg_id                  :       " + response.getMsgId());
			// 业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
			System.out.println("balance                 :       " + response.getBalance()); // 手续费
		} else {

			System.out.println("return_code             :       " + response.getReturnCode());
			System.out.println("return_msg              :       " + response.getReturnMsg());
			System.out.println("msg_id                  :       " + response.getMsgId());
		}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
		
	
}
