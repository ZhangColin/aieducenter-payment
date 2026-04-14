package com.icbc.api;

import java.util.UUID;

import com.icbc.api.request.MybankAccountDigitalwalletPermapplyqueryRequestV1;
import com.icbc.api.response.MybankAccountDigitalwalletPermapplyqueryResponseV1;
/**
 * �Թ�Ǯ�������ҽ������ѯ
 * @author kfzx-lusx
 *
 */
public class MybankAccountDigitalwalletPermapplyqueryTestV1 {
	private static final String APP_ID = "";//APPID���Ż���ȡ
	private static final String MY_PRIVATE_KEY = "";//���÷�˽Կ
	private static final String APIGW_PUBLIC_KEY = "";//���ع�Կ
	private static final String AESKey = "";//���÷�������Կ
	private static final String MSG_ID = UUID.randomUUID().toString();
	 
	
	public static void main(String[] args) {
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8,
				IcbcConstants.FORMAT_JSON, APIGW_PUBLIC_KEY, IcbcConstants.ENCRYPT_TYPE_AES, AESKey, null, null,null,null,null);

		MybankAccountDigitalwalletPermapplyqueryRequestV1 request = new MybankAccountDigitalwalletPermapplyqueryRequestV1();
			request.setServiceUrl("https://ip:port/api/mybank/account/digitalwallet/permapplyquery/V1");
			MybankAccountDigitalwalletPermapplyqueryRequestV1.MybankAccountDigitalwalletPermapplyqueryRequestV1Biz bizContent= 
					new MybankAccountDigitalwalletPermapplyqueryRequestV1.MybankAccountDigitalwalletPermapplyqueryRequestV1Biz();
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
			bizContent.setWalletid("00200000088888888xxxxx");
			bizContent.setCreditid("xxx");
			bizContent.setStatus("xxx");
			bizContent.setPageSize(10);
			bizContent.setPageNum(1);
			
			request.setBizContent(bizContent);
			request.setNeedEncrypt(true);

			MybankAccountDigitalwalletPermapplyqueryResponseV1 response;
			try {
				response = client.execute(request,  MSG_ID);
				if (response.isSuccess()) {
					// ҵ��ɹ�����
					System.out.println(response.getDetailList());
				} else {
					// ʧ��
				}
			} catch (IcbcApiException e) {
				e.printStackTrace();
			}
	}

}
