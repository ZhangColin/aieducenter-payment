package com.icbc.api;

import java.util.UUID;

import org.junit.Test;

import com.icbc.api.request.ApiTradeDetailQueryRequestV1;
import com.icbc.api.request.ApiTradeDetailQueryRequestV1.ApiTradeDetailQueryRequestV1Biz;
import com.icbc.api.response.ApiTradeDetailQueryResponseV1;

public class ApiTradeDetailQueryTestV1 {
	//���÷�˽Կ
	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCJHCAy867FQTHpaWMbe7kVM45/k8pP1A0/j7K3pAYA7azEDz8R8SaV3O6Go4BVBd68GO5jlD4qlb7ki06nyLj6YEBAx7VaIef8urRS34wiPX+nKzpLTMmUArFCgNVCNotHKGMgP3Y+v3vm6gytzS8fFoDiXZsQu3Ee2fhLB/aP+JdyXR4ufkLxsbo2VOlh4kCzENlr3D08oF2YQrOOygUSLO70tkOot8dxrEjeZCdE88HRoN27i/vCye0ajjAFPxi/x+FTkg8BP631V8EDKvzsrjb+cmub1W3CHuJaLObWTPDMnH9TeV6Uda8NoZ6duMS4di6ScnMxjw3kcRS9BNKRAgMBAAECggEABTlWHLreINPgE/WY+hYXsjAZsNCvt6TrsE1IUOjl9akYmQu/qm5WuwUE7YpTPGoR13bXtmGSkMrN0CVipM/oBFogeJdjWIDMNvviHPo5e6B+OZ9TBEVztLj5igAgn/o2CksGo9bnKXLgRKcMPp7/uZ6A4J8SZmvN3pANfsZZ5ZhTcxysBlHy4rOYGqvsjNEgn2T+Kdu6aur0ygxeRbGonHLkmyK5DArBw3/Oy0d00b5xo+f0GzbpPp9fhu9T+HNstOEgxPZhbhPnOuqt67uDI3dD1doBomq72yFU+n2eYAEz/6qXbbRBvntNSAxNAVjhufDWzKvDPpleZzM0QLlO5QKBgQDufbvXwEC9OYyndtJD3L3qAIClb87FDU+Jf0o+L97rGgb6+0Y+OPal0IKS8ZAxNQoBshPyypaqh9uW2C2h0TNxdHy5xh7DqS6SSrY2IAHaZp3JqfcIEL7SGzXWU2aJJJGaYKV7NUwuT5wXOhJ7USR4lzv3GM9cL6Cdq27YlplpJwKBgQCTLQFTPy9aY08gqfpEqZIYg9oq4BMutkzcMkcPjxx8e6I0j5mgebghFzX7vABX83y6p1Uk2XqM2AkUgqyI5Q/xfVd+Rxqsl2RTQKGpZaDK3mXMQR8Re0MTNTFqxqMCYoZVNHB/Az7GzLAys+we7T6Uobu6230qPqooZfQ+0rQJhwKBgCpFXuX+zXq8mJGWHe6pUYAIVsT77EpA8bgNN7uOAJTrKBGUN/8PWahKLNqetlACdqOWTLLb9ZiEPAegiXYE/nk9jF3hH9LOSdLkKyesxKdRSOZVmdN0JF5ZNFGotqPYN3aFas9LwxTyR1hWkrAI0c4OADLr0tUkG/PWTu9UwhxBAoGBAI8iMOy+QZZdiOISjbRjWhN/gBk8rUs7vopUV2nZW/Hw53LIybcZSardAQJe99KRmheJj7Pz0kzR+hq5b4T/iT7qEMJd8qIx5j9VVyQBE5zZKBW6q7V7tGMk03et0XELUuvgwRu1k0MSZLYa56O4RamVlZcHybXhFemOJjlHABNXAoGASjbc0LGvUroZS7xcZMCMbBggkgR+YGes5u2i4rDUTyCoDWUV5gmbYhGsSRk2Md8dOkUvdbo1cWttnZxMwAl3AFVbfR8LQgMroTOnDU565hGvW5IE/kKdkFN0LOE1DVdiFSZYWz2UzTlp+EkFN9eo+o7xN1TWr7KTNzRofzQhlf8=";
	//���ع�Կ
	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
	//���÷�Appid��ͨ���Ż��鿴�����Ϣ
	protected static final String APP_ID = "10000000000000008024";


	public void test() throws Exception {
		
		// ����client����
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
		
		// �����������request
		ApiTradeDetailQueryRequestV1 request = new ApiTradeDetailQueryRequestV1();
		ApiTradeDetailQueryRequestV1Biz bizContent = new ApiTradeDetailQueryRequestV1Biz();

		// ��������·��
		request.setServiceUrl("http://ip:port/api/gfmm/tradedetail/query/V1");

		bizContent.setProductgroupId("001");
		bizContent.setQryDate("20180704");
		bizContent.setStartNum(1);

		request.setBizContent(bizContent);

		// �������ύ�ı����ؿͻ���������ñ����Զ��ύ��ɵ���
		ApiTradeDetailQueryResponseV1 response = (ApiTradeDetailQueryResponseV1) client.execute(request, UUID.randomUUID().toString());
		if (response.isSuccess()) {
			if (0 == response.getReturnCode()) {
				String msgId=response.getMsgId();
				int returnCode=response.getReturnCode();
				String returnMsg=response.getReturnMsg();
				String returnMsgId=response.getReturnMsgId();
				String productgroupId=response.getProductgroupId();
				int returnSize=response.getReturnSize();
				int nextNum=response.getNextNum();
				int totalSize=response.getTotalSize();
				String returnContent=response.getReturnContent();
			}
		} else {
			String msgId=response.getMsgId();
			int returnCode=response.getReturnCode();
			String returnMsg=response.getReturnMsg();
			String returnMsgId=response.getReturnMsgId();
		}
	}
}
