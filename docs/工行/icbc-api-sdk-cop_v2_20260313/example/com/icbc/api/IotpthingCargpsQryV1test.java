package com.icbc.api;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.IotpthingCargpsQryRequestV1;
import com.icbc.api.request.IotpthingCargpsQryRequestV1.IotpthingCargpsQryRequestV1Biz;
import com.icbc.api.response.IotpthingCargpsQryResponseV1;
import com.icbc.api.response.IotpthingCargpsQryResponseV1.IotpthingCargpsQryRequestV1DevInfo;

public class IotpthingCargpsQryV1test {
	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----CdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKBxOzp8zRz/d66iERz8H+rWFdp/AbCmYIKGowAnhRFd6YRi0ZgcuUHFjrnI+2TimwdUjngyvgEtWxcGgUqbKxQ2B3lGcLD4nOy5kolUwWqqutuaTcvPfms56CeI/O4ZfELCQ6ZWSTh/Q+Azelhqa3DFx6TXB394lbwzKEE7XY6DAgMBAAECgYEAgUrebQKXlG+2tc86tT3kRq2hTMOezAk3qtfu0TgHvvgML7gmqUs0a3FV1BfHpwQJ5H1aljhl77LqPgLq/AFRU6COCH7ehBMjl3IDQYZctee72xIizvRdq9dqmIVo2EwcvIi0WDwAGsZ8C7BLGp0iWYJxVFpd4v+wTmo/qs08G1ECQQDNlsjPYbUcNsHll1l5EIsWZlYsqQEqh+tQ3QwhVH4umjzLn9MeAl+zJGCUujZ30BA6h9YjHQV1YQE5ilYfnsypAkEAx8iAqpmHhdP9UtdS9s90IJosQe+vx6Ro5lncfey1NbIiPshjdEPi4bf1o+xv8vr4567a4rDthA4hgdNKqxlxSwJARJZRWB5YtZjvBxF/IAyM2mQ5y5x2byH5N3kHWvc9QX7jWWtwm0pfcvxn3DZjhYU8ZMp8fipob8h1CCVGpjXsQQJAWUs4lwfAFwTFOP2cy0u178x484+Z1XWanTqU3oOqCrHvtHgEW7br9U8vr3DCKKwcDBGPK7tArcFTIJ+7w/W/OQJAD0d2+So02KjhjUmihkg2PRq3+ouiRCPSlSUWgy1jQP295MdCFdg32Te1rcLlq695tdma1rXqVErknGHf/XamkA==";

	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

	protected static final String APP_ID = "10000000000000002674";


	public void test_cop() throws IcbcApiException {
		
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
		
		IotpthingCargpsQryRequestV1 request = new IotpthingCargpsQryRequestV1();
		
		request.setServiceUrl("https://ip:port/api/iotpthing/cargps/V1/query");
		
		IotpthingCargpsQryRequestV1Biz bizContent = new IotpthingCargpsQryRequestV1Biz();		
		bizContent.setCorpId("GPS0100001");
		bizContent.setVin("LSGJA52H1GS068943");		
		request.setBizContent(bizContent);
		
		IotpthingCargpsQryResponseV1 response = client.execute(request);
		
		System.out.println(JSONObject.toJSONString(response));
		// 判断调用是否成功，进行后续业务处理 
		if (response.isSuccess()) { 
			// TODO 业务成功处理 
			System.out.println(response.getReturnMsg()); 
			System.out.println("custName :" + response.getCustName()); 
			List devLst = response.getDevLst();
			if(devLst != null) {
				System.out.println("devLst : ["); 
				for(int i=0;i<devLst.size();i++) {
					IotpthingCargpsQryRequestV1DevInfo devInf = (IotpthingCargpsQryRequestV1DevInfo)devLst.get(i);
					System.out.println("devSn :" + devInf.getDevSn() + ",");
					System.out.println("devType :" + devInf.getDevType() + ",");
					System.out.println("devModel :" + devInf.getDevModel());
				}
				System.out.println("]"); 
			}
		} else { 
			// TODO 失败 
			System.out.println(response.getReturnMsg()); 
		}
	}
}
