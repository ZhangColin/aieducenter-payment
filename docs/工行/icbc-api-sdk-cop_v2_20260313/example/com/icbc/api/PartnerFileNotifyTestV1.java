package com.icbc.api.test;

import com.alibaba.fastjson.JSONObject;
import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.request.PartnerFileNotifyRequestV1;
import com.icbc.api.request.PartnerFileNotifyRequestV1.PartnerFileNotifyRequestBizV1;
import com.icbc.api.response.PartnerFileNotifyResponseV1;

public class PartnerFileNotifyTestV1 {

	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
    //重新生成
	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCS3kNPMtIhZy+50M32qQRe0XROgNybya8qvZflRiEhZXHxvzhgMPVVilYTxEFQEN96tjIRpjfo67sPZ4nGCOahYbRfz8POmbyj0VRqjSGwDAt98PBaFRncYJtiUeItLaVPVbTS8xjf6OQjzkl2xGNlGMrgSASvKrh/yFQMbV6Ch3BCwAJwThk2UCgeVhMawALdCw+Tl/HHyl+ZaeUdUFSX5HSnG3hY69B5mapT3I5SmArsfX2jaDMKCngR5v8olzK/DM8ME9pm485PIarHw04hgH0nfnTCeY9aL822+QmGVKfC9DHmdGHmfKyPnwgPSNlDHuWzYUGqIJ8vw6epPtXzAgMBAAECggEAQk5+bP8wx0vizC1tD3N3OaeurtKWxu7jWpSGQ0jKJF28d7wxI5x25sux2PK92fwEKU6ghD9a9pmWtnm0Reg2151Y5fjTGi9mbz9OsVxOXUDS32Ur3j+wkUI8ZMM7LdJx08OKA8Mk89IdTd0QZcJ8bWQoE+Hv23WaHna/G201QhBWhK3FaIJ5jUuuzS67dhEnjnfoR+7iMV6FwFr75rdGik/VS+4RFNorcBJ2sAt95JN5j6c544UFb0j1lvNI4MpLsUl2W8h/i47hK0XdQk5FHf7fRVGs+NqY9Wklttpc6AMLOAnWvdx9yegDvZe3WhfleKPmIL7f6k6b7MPerHAtAQKBgQD8FaU1t8VDhqjVzsAu5xyrOaoqEAzf58GpFLiCmxRSIcmLezP2jvjh6y+qKKuS3YPHD760/uyL2EBBH4rpteEgTnutnzlTAj6reRe9cfQx9YFNSXjpzw2jYuPXM9TANDqIXmtu268xB6wV6LooV2jI6Pq1ZW3ykiw69mN9ROgvkwKBgQCVJj/qrBOQFxaNhMzhAsPt+zhG3olbCRu4gC/7Hjj84Cj3Hu59OtlIy9Pyyb/Ll9B7n9PHIW35dOAUaHisB5HvuxqZXjCApNuRtBiYtzOnMUHmxa8AWyW4QrmuCrP7xt85tAVl6bDpzFF07eGLFaLy5UGVApBtKIKl84mHGxT8IQKBgQDTPDwdgGnfhMaTq1VluCpQ3NhXuO1g7q5cRc27O8JSdJeTtuC7OeEaRt7BIqVrQw36z5nJx1hmcwacy5a28r68OKfwvFznzxi+zZD0Iboz5vq7zdZxbhyKjgJIQdmEWf+2aBB0a8gpOLmd/k+IIH7g5EG5oU35lz1TB0YlIYnC2wKBgEvJ8fS3mAFCGi2za8By/UiyvXgVoMpnqHRqfvcl9mNZLLfWOe3ct4jsOjlHF3THIiOPpbesXwvBvhPgumEAP8IH245PdGoKW6CAWXdQsKimuQwhK7oMlvA4x82OlsK73baeqZeN1l4XetTmBBP+3Jx8roSflvBBA5J8XEgRFOrBAoGBAIW2S2j3RRN/7WVeMdG/zSoekfHw4gbpdO3+eM9UaGT+UJxC0D1Vih+LH/QzrYYNefzKu8CfsLMQxiS4Tdxhtpsv/s6i4a4037BlShILLn6IaUjvZ7wRKZVPPl/iceesOLjLJvDN5kAKpMJZBeae0ZO+LQqO1pMzmvkCZN2ufW46";
	//APP_ID：工行API接口的ID
	protected static final String APP_ID = "10000000000004097692";
	private static final String PRE_URL = "http://ip:port/api";
	private static final String SUF_URL = "/partner/file/notify/V1";
	
	public static void main(String[] args) throws Exception{
		PartnerFileNotifyTestV1 partnerFileNotifyTestV1 = new PartnerFileNotifyTestV1();
		partnerFileNotifyTestV1.testCop();
	}
	
	public void testCop() throws IcbcApiException{
		PartnerFileNotifyRequestV1 request = new PartnerFileNotifyRequestV1(PRE_URL + SUF_URL);
		PartnerFileNotifyRequestBizV1 param = new PartnerFileNotifyRequestBizV1();
		//渠道号
		param.setChannelCode("11");
		//合作伙伴代码
		param.setPartnerCode("11");
		//签名
		param.setSign("111");
		/*
		 * 文件类型
		 * 1.入会文件,2.加分文件,3.核销卡文件,4.积分调整类型
		 */
		param.setFileType("11");
		//文件名称
		param.setFileName("11");
		//文件路径
		param.setFilePath("11");
		//文件就绪时间,格式：yyyy-MM-dd HH:mm:ss
		param.setFileReadyTime("2021-07-01 12:12:12");
		
		request.setBizContent(param);
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
		PartnerFileNotifyResponseV1 response = client.execute(request);
		
		System.out.println(JSONObject.toJSONString(response));
	}
}
