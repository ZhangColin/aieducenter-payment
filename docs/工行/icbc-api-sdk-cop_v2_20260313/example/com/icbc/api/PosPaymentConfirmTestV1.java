package com.icbc.api;


import com.icbc.api.request.PosPaymentConfirmRequestV1;
import com.icbc.api.request.PosPaymentConfirmRequestV1.PosPaymentConfirmRequestV1Biz;
import com.icbc.api.response.PosPaymentConfirmResponseV1;

public class PosPaymentConfirmTestV1 {
	
	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDc+m8MNbroOkaAFI631dSZwgDnHwfFAWdU5Eed73180gJAa5VOcijhgk4RwVdjpJVdulZe21GD0yTfGA468x4IRXYH6QQLLh+ozJW3YQfBpqVfulwCH1n7l1FP4s9UjuU/tzvp9bK5RDFvF7JFCOqRIvTue6SO9dxVwnkk7GpezqH2R6kt4VE0Kzd3trS9dhXCeqrjQqPpLdp6qbZo/0GvJB3EQPcPMiJLfFsxL/r8ZlQirDQ3EnEUINNcOObFeBECl4AiOm5/W8e3AmNgLP/Z0H9S5K6lT4bS5YXA2ALxxYVJNkWHg/9kyk0WfUmZQRjzi/X2KW1p5+hb4w/slBi/AgMBAAECggEAfuzNmfrGp5rJYcntC8Sjf8mCET6ujMa4nnccd6fWsTpDFtN0iAFkX9sjkmk2E8K2FIbyEzvv/0Lo+8dqbQu1pVSYbgS2UOGmR6NzH0h5Vw6sEGcPcByn563Lg5IoOk51ePp7TG/WvzlfF4tJ1wM/ECw++ThFleO8Mfoo8T9l/ZuqUL0ODKnfm0ORnCz1T10Pc74pS1zL7ynTcco17vXSA6Rddb01TGLTTpo1JRL+fZcPIzUrMf3WtsRcsQWxFrjTbVs558xXbZBFaDjen5DHKgtso7BUC4LggXP8dyS9MxRyQIrZO/aDYQfo17MnXIZpdpAl94M9hsy9WmOHgeyuuQKBgQD/1YNmJiAovcK41viZfxnxpi0OxXIjiJq7eGCZDOH04/1MHgx7s4icdNgHsVjTVu/N86AUGvRdQDhMjsyTH3tuFwS3Pg6UuckPz6L4T78HxcQzp+q4G7cGZbBptJJAMWk1bQdbhOhhAUZ9d2tFSIGCLIo4Vc76gJaOfZns/XyWAwKBgQDdHyHHuKfdQlO0pdpW+qK9n/YQUSjM2E9eOy0Tv80gVYKNp6DtFSo6kKUvTdA1HVeywsHYVzH6eHOXdiyHP+GYflJrulaa97eLZzs/BQkds4A1nMGdLUvz1u9jlX8N+b2kTE5nLBLcRY5B/65+mEQ1zJDN3K5j2rOBRxX2LppDlQKBgCXujWX23yvLPvTTAtcXbkxS/DlFcy/rkC2doozuyQ3bQ/ImPmmU6/tuo504YVgseq/JoIF6oaK14+MOBS0HLPDcvtlAfT718ObEPYCdqlBHtViHXBjiGxf6udZibJDSAqxmNwINybqgSshNjX0fS/x2CCRkLKUdrbUA0HK//tnrAoGBAIjAXzDYh6B0C7aWN/FA8nLZweEXgGcng/ZgHKGxJhhQKfyXZhQZ2dL8b9xQgGrI6drZTe8RBgzEf6B1alZ+8HNR9IFBbZxLhU0GnAJ+h8PBppIBj1A4mleEXuq0oy4ufNVmh7qQ7F0j+zXf/be3TcAD8q4ORB/nOsrB/tgQvED9AoGBAJdl5rHZN+TdSgOgBqwd8a4LBpCBHXN/kObiH5YayiTFvYlRt312nN98dLQ3VivgWRQtjSbspi2cEzEhmlV6drdHsCBVpwMrAq0R+f9B+zDAyTVGhh9SDNod1hD+vjempGqVCs7iONBvrZRJLH9JgSgnf1vDqlRaAyumuwr7IyOO";

	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

	protected static final String AES_KEY = "4+boqULIzNa7SWbYKZtHZA==";

	protected static final String APP_ID = "10000000000000054006";
	
	
	public static void main(String[] args) {

		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,"RSA2", MY_PRIVATE_KEY,"UTF-8",
				"json",APIGW_PUBLIC_KEY,"AES",AES_KEY, "", "");
		
		PosPaymentConfirmRequestV1 request = new PosPaymentConfirmRequestV1();
		request.setServiceUrl("http://ip:port/api/jft/pos/api/pay/confirm/payment/V1");
		
		PosPaymentConfirmRequestV1Biz bizContent = new PosPaymentConfirmRequestV1Biz();
		bizContent.setAppId("10000000000000008026");
		bizContent.setOutOrderId("20180712140009");
		bizContent.setOutVendorId("20180510222002");
		bizContent.setOutUserId("120180126456987458");
		bizContent.setPayAmount("0.01");
		bizContent.setSerialNo("20180625000015");
		bizContent.setPosTermId("200810000011");
		bizContent.setCardNo("6329938193874184");
		bizContent.setCardName("");
		bizContent.setCardIssuer("");
		bizContent.setCardOrg("");
		bizContent.setTransTime("20180630111112");
		bizContent.setTransType("PURCHASE");
		bizContent.setRefNo("10011");
		bizContent.setFundStatus("1");
		bizContent.setFailDesc("");
		bizContent.setQrCode("");
		bizContent.setMerBranchId("1000001101");
		bizContent.setMerchantId("10000011101");
		bizContent.setQrType("1");
		
		request.setBizContent(bizContent);
		
		PosPaymentConfirmResponseV1 response;
		try {
			response = client.execute(request, System.currentTimeMillis()+"");//msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一
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

}
