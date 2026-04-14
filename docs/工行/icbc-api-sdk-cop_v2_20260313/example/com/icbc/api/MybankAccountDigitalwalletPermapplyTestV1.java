package com.icbc.api;

import java.util.UUID;

import com.icbc.api.request.MybankAccountDigitalwalletPermapplyRequestV1;
import com.icbc.api.response.MybankAccountDigitalwalletPermapplyResponseV1;
/**
 * �Թ�Ǯ�������ҽ������ύ
 * @author kfzx-lusx
 *
 */
public class MybankAccountDigitalwalletPermapplyTestV1 {
	private static final String APP_ID = "";//APPID���Ż���ȡ
	private static final String MY_PRIVATE_KEY = "";//���÷�˽Կ
	private static final String APIGW_PUBLIC_KEY = "";//���ع�Կ
	private static final String AESKey = "";//���÷�������Կ
	private static final String MSG_ID = UUID.randomUUID().toString();
	public static void main(String[] args) {
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8,
				IcbcConstants.FORMAT_JSON, APIGW_PUBLIC_KEY, IcbcConstants.ENCRYPT_TYPE_AES, AESKey, null, null,null,null,null);
		
		MybankAccountDigitalwalletPermapplyRequestV1 request = new MybankAccountDigitalwalletPermapplyRequestV1();
		request.setServiceUrl("https://ip:port/api/mybank/account/digitalwallet/permapply/V1");
		MybankAccountDigitalwalletPermapplyRequestV1.MybankAccountDigitalwalletPermapplyRequestV1Biz bizContent= 
				new MybankAccountDigitalwalletPermapplyRequestV1.MybankAccountDigitalwalletPermapplyRequestV1Biz();
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
		bizContent.setWalletid("xxx");
		bizContent.setCurrtype(1);
		bizContent.setCompanyname("xxx");
		bizContent.setCreditType("100");
		bizContent.setCreditid("xxx");
		bizContent.setCompanyAddress("xxx");
		bizContent.setCompanyEnabledates("xxx");
		bizContent.setCompanyEnabledatee("xxx");
		bizContent.setLpname("xxx");
		bizContent.setLpcreditType(1);
		bizContent.setLpidNumber("xxx");
		bizContent.setLpidNumberEnabledates("xxx");
		bizContent.setLpidNumberEnabledatee("xxx");
		bizContent.setLpphoneno("xxx");
		bizContent.setSurveyResult(1);
		bizContent.setApplyPerm("xxx");
		
		request.setBizContent(bizContent);
		request.setNeedEncrypt(true);
		
		MybankAccountDigitalwalletPermapplyResponseV1 response;
		try {
			response = client.execute(request,  MSG_ID);
			if (response.isSuccess()) {
				// ҵ��ɹ�����
				System.out.println(response.getApplyno());
			} else {
				// ʧ��
			}
		} catch (IcbcApiException e) {
			e.printStackTrace();
		} 
		
	}
}
