package com.icbc.api;

import java.util.UUID;

import com.icbc.api.request.MybankAccountDigitalwalletPermconfirmRequestV1;
import com.icbc.api.response.MybankAccountDigitalwalletPermconfirmResponseV1;
/**
 * 对公钱包渠道挂接确认
 * @author kfzx-lusx
 *
 */
public class MybankAccountDigitalwalletPermconfirmTestV1 {
	private static final String APP_ID = "";//APPID，门户获取
	private static final String MY_PRIVATE_KEY = "";//调用方私钥
	private static final String APIGW_PUBLIC_KEY = "";//网关公钥
	private static final String AESKey = "";//调用方加密秘钥
	private static final String MSG_ID = UUID.randomUUID().toString();
	public static void main(String[] args) {
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8,
				IcbcConstants.FORMAT_JSON, APIGW_PUBLIC_KEY, IcbcConstants.ENCRYPT_TYPE_AES, AESKey, null, null,null,null,null);

		MybankAccountDigitalwalletPermconfirmRequestV1 request = new MybankAccountDigitalwalletPermconfirmRequestV1();
		request.setServiceUrl("https://ip:port/api/mybank/account/digitalwallet/permconfirm/V1");
		MybankAccountDigitalwalletPermconfirmRequestV1.MybankAccountDigitalwalletPermconfirmRequestV1Biz bizContent= 
				new MybankAccountDigitalwalletPermconfirmRequestV1.MybankAccountDigitalwalletPermconfirmRequestV1Biz();
		bizContent.setCooperatorNo("12345xxxxx");
		bizContent.setCooperatorType("2");
		bizContent.setAgentCooperatorNo("11223xxxxx");
		bizContent.setSerialNo("002000000201234567891xxxxx");
		bizContent.setRelatedSerialNo("");
		bizContent.setOriginalSerialNo("");
		bizContent.setWorkDate("2020-12-12");
		bizContent.setWorkTime("12:12:12");
		bizContent.setMac("00:AA:00:3F:89:4A");
		bizContent.setIP("2a31:12a2:bd33:12a3:12f5:12e6:xxxxx");	
		bizContent.setApplyno("xxx");
		bizContent.setCompanyname("xxx");
		bizContent.setWalletid("xxx");
		bizContent.setCurrtype(1);
		bizContent.setConfirmResult("xxx");
		bizContent.setReason("xxx");
		
		request.setBizContent(bizContent);
		request.setNeedEncrypt(true);

		MybankAccountDigitalwalletPermconfirmResponseV1 response;
		try {
			response = client.execute(request,  MSG_ID);
			if (response.isSuccess()) {
				// 业务成功处理
				System.out.println(response.getReturnCode());
			} else {
				// 失败
			}
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}
	}
}
