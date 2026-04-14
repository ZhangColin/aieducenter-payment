package com.icbc.api;
import java.util.HashMap;

import com.icbc.api.request.GCMSAPICorpTestBizContent;
import com.icbc.api.request.GCMSAPICorpTestRequest;
import com.icbc.api.response.GCMSAPICorpTestResponse;

public class PersonaLoanSellAddTest {

	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
	protected static final String PRIVATE_KEY = "-----请使用正确的APP私钥-----CdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAKloPle1GQER+4R0dYoZjJoPiyDCSrjuaOgyYSifNpQT+k85OfmyXNxcIY7MU5wn6z9W5nf8mdtvqarNJPMABVK4XGA8oD3v+d1/va7SNmAREwRKelySu4Z45deU4YKBmz7JZqOX9PZAi0ZDiOs8uIsaOeKUNfxpk8jOoDFmUUBjAgMBAAECgYBrccBIpIWtVhhuE+cQpc+1TrJxHomB7UQAMSPKPeDB3kHdyW/p3KRk5tIp3ZIR8oeT7qvR4hY7RQEZYW9T5+l3Wt9gnKdTk7qsf9axqbnIg9RnKRJXNYwR9SKNmhOkRYOkz18o4QRM2FEhLHC+DMazZbz4JVt47ivrz4M72oN9oQJBAN3z2DhUnYBuPz14xkWAgUw1yJnQhuig6dW/pguc+3FpVELl2ULecUXiAGoCFonvjtfJo2pmkv5LJ5FYylWExfkCQQDDZOzmJ3wnRonucIEHUyd4hn9qlzurJKEWwrLLfhxostgXfuErHTV21jLoW5et3Z3XWHljKf6s8ccFfD/r3KA7AkEAzumMILeBPUGJ4xy8ef6aixl4sSwdGLEqTQIlIZ5vKgMvck4khJoqryNJj4nyyRRy7egkWATX9AjMEkvcFG16wQJBAIMkL+PhSHDL5TOFivQxmWJJUydlTYT2VrACCSKPB941etd6LwJS3lleNljBcLIe9htcpEwsGWRV+e/yjqxtLZUCQAL8XMwHX/IghOmbFeHYp1T/ITTxQrBFUGgOHzqSkdgqwAYp0YdOgy5Kh4FYj/16w+q3DGag05v76SdYBroD/Ks=";
	protected static final String APP_ID = "10000000000000003178";
	protected static final String URL = "http://ip:port/api/personalloan/sellAdd/V1";
	public static void test_CA(){

		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, PRIVATE_KEY, APIGW_PUBLIC_KEY);

		GCMSAPICorpTestRequest request = new GCMSAPICorpTestRequest();
		request.setServiceUrl(URL);

		GCMSAPICorpTestBizContent bizContent = new GCMSAPICorpTestBizContent();
		HashMap<String,Object> bizParaMap = new HashMap<String,Object>();
		HashMap<String,String> dateInfoMap = new HashMap<String,String>(); 
	    bizParaMap.put("apply_no", "ZJ000000001");    
		bizParaMap.put("cooper_cis", "020006202540160");
        bizParaMap.put("this_month", "201708");
		bizParaMap.put("cooper_cis", "020006202540160");;   		
	    bizContent.setParam(bizParaMap);
		request.setBizContent(bizContent);
		GCMSAPICorpTestResponse response;
		try {
			response = client.execute(request);
			if (response.isSuccess()) {
				System.out.println(response.getReturnContent());
			} else {
				// ʧ��
				System.out.println(response.getReturnCode()+"|"+response.getReturnMsg());
			}
		} catch (IcbcApiException e) {
		}

	}
	public static void main(String[] args) {
		test_CA();
	}

}
