package com.icbc.api;

import org.junit.Test;

import com.icbc.api.request.MybankAccountBankconfirmationrequestReplyprocessqrypaperRequestV1;
import com.icbc.api.request.MybankAccountBankconfirmationrequestReplyprocessqrypaperRequestV1.MybankAccountBankconfirmationrequestReplyprocessqrypaperRequestV1Biz;
import com.icbc.api.response.MybankAccountBankconfirmationrequestReplyprocessqrypaperResponseV1;

import java.util.UUID;
/**
 * 纸质函证进度查询
 * 
 * 请商户配置SDK中的lib文件夹中的jar包后，根据备注1-6提示进行数据替换
 */
public class MybankAccountBankconfirmationrequestReplyprocessqrypaperV1Test {
	// 1、网关公钥
	protected static final String APIGW_PUBLIC_KEY = "test";

	// 2、appid
	protected static final String APP_ID = "test";

	// 3、密钥对认证方式，公钥在API平台登记，此处为私钥
	protected static final String MY_PRIVATE_KEY = "test";
	

	public void test() throws Exception {
		String msg_id = UUID.randomUUID().toString();
		// 签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,
				IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
		
		MybankAccountBankconfirmationrequestReplyprocessqrypaperRequestV1 request = new MybankAccountBankconfirmationrequestReplyprocessqrypaperRequestV1();
		request.setServiceUrl("http://ip:port/api/mybank/account/bankconfirmationrequest/replyprocessqrypaper/V1");

		MybankAccountBankconfirmationrequestReplyprocessqrypaperRequestV1Biz bizContent = new MybankAccountBankconfirmationrequestReplyprocessqrypaperRequestV1Biz();
         //封装的set方法名与通讯区表格描述中的字段名略有不同，如使用，请按如下set方法使用。
  		bizContent.setRequestType("1");//询证类型	   	
		bizContent.setExpressId("SF123464654");//来函快递编号
		bizContent.setLetterId("202307180000001");//来函业务编号
		bizContent.setConfirmDate("2023-07-11");//询证基准日期
		request.setBizContent(bizContent);

		MybankAccountBankconfirmationrequestReplyprocessqrypaperResponseV1 response = new MybankAccountBankconfirmationrequestReplyprocessqrypaperResponseV1();
		try {
			response = client.execute(request, String.valueOf(msg_id)); //msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一
			 if ("0".equals(response.getErrorNo())) {
                // 业务成功处理
            } else {
                // 失败
            }
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}
	}
	
}
