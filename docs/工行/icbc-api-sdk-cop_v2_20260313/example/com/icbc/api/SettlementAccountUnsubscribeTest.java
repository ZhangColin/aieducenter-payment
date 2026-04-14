package com.icbc.api;

import org.junit.Test;

import com.icbc.api.request.SettlementAccountUnsubscribeRequestV1;
import com.icbc.api.request.SettlementAccountUnsubscribeRequestV1.SettlementAccountUnsubscribeRequestV1Biz;
import com.icbc.api.response.SettlementAccountUnsubscribeResponseV1;

public class SettlementAccountUnsubscribeTest {
	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC3v/0CdVTv6vRdxF7syUpYwa1x90VKv91VbBKSL6ea0VBJxN14CphDjPD9TRAf/4z+cVky2NxdIDEvJS5ItXcD7KoKLlxQ+BBQJvLyeXofQJ6tWnX6snhOibJF6mGFef63FS2Gm1bECKOa6SC38I0eD8i0LZttrOvyEly4cGtrk5CMll3XYwvzt9KKozvdCOivhZavt42JF7IoMSIUIlm9tTJfwB3jtX1KdCeiV8//pft/k0ZJmpJHWmDymfrZAEodANvNPDqAo4SI3QbMC+hVw5BEjNuDZcm8cMnBDHk+siyJfPjZd+ec7CE+TTH6u3O/BteaDyOPRcl1TOzlzTaJAgMBAAECggEAUzIpMzi+uVYvSFlGBX3qHJ+skWI8zGhspWWmTuwmvfExogTxpY1efY9N+dIvj91cEERv95a6x6twuYDjIOaQuYUpRCzTfZGjiTbpXyHjSk0uyxyfOGAgGqc7wW4qs42V530+PKkOsvmaYeRV/RJ8QnUxEWto6VKXDeaEdrnn2S+ToGuaRlU+U1EGb27xahldUs+Y7eQrtacidvxS421EuW1kOEC2XaPDsC+r6T3DcuFb+J0EUQiI4EKbcSQDf0/xT1cIkkCPkKzwZIqFI+icZuwnRaIvI9xXv3DvCdhWyLCnDBDgwOewB30+rLAc6yqGtN/4iD2EQvpU9gRsm683kQKBgQD6XZ0g2NdnYAj07ms9TVTUjfO8nw2uizdUduMW+tG83js4Uq1rjt9IeLwrSHaRGTtnECMXKEPK/+TsX+aKrfbU9D/Lw34q4hVlnHAe9mswFl+nH87k4f3ntMX10puW8NWVxgZ/I+YQ80mJ+B3DmnxUbnZ00rC3itOXBL5XcoOr7QKBgQC74pfuyanx9acIvj3nyxieTjv8XzcEr6uvrW7YgGJ++1Ma2MZFH4KEmwbUmM+Lhdfa+0eGHp0v7Bun1s0tPibCbGpo+9npaByyNuo2WTbyCavlVQIc6xryiTXEfYXMyYgCKNOQ+qmZT9bNZyKSNi8uFwWwT6euZpYAS1xtb4b5jQKBgHD3uoVuskr3vzLdPVAsy4EZCAhs8Bx4vp+DAVaGGAsTbz8rtwpYLhGiQK17BdzUuMP5OQwi4+v96MprPzgF5OET0JFdhVlOJ/9wRfsCjzzka4aaSJ1oB0v8enI/9Gp4AreQ4a+OgCnXzK/PX4a59y2VpidyRcCbiHuU7VnB1v3FAoGAKHudcwUeyhS9JeQf4JbhOhLvV+SILUlQu+ypqpqXHnc2o907c19jtwDUSvCWH6D6LqPf+GUWR2IfX12oHex7W38SuoYxoNoe/MNaWdWFQkeVMzfK2cJKryGjZTAKqWJvHNABLgbkjjUmumFTkJ9+MPci8adoIOrXvT5/rtDOC20CgYEAi58Pz/9zHBnI15sgFtWdp02myuDoSlAXS6MCelYoMMFgY2iM1DH8ip4koDPPz/oieN0k8VWSq2kCqk2hyRa0Llib0ilQpUaImvlbBRKtDc2hIfCNsL8OqkyEZZ5LnGSLMJ3oIUwJ2S5zvpbNaB+rqxFWEpmgrDb7TC5TcSNrtHY=";
	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
	//Appid：通过内管查看详情，可获取对应的组织机构、接入方式、公钥等信息
	//APP管理中查看
	protected static final String APP_ID = "10000000000000021587";
	

	public void test_cop() throws IcbcApiException{
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
		
		SettlementAccountUnsubscribeRequestV1 request = new SettlementAccountUnsubscribeRequestV1();
		SettlementAccountUnsubscribeRequestV1Biz bizContent = new SettlementAccountUnsubscribeRequestV1Biz();
 
		
		request.setServiceUrl("ip:port/api/settlement/account/unsubscribe/V1");	
		
		bizContent.setCorpNo("88888888"); //合作方机构编号
		bizContent.setTrxAccDate("2017-03-23");
		bizContent.setTrxAccTime("12:30:21");
		bizContent.setCorpSerno("123456789"); //合作方交易单号
		bizContent.setCorpDate("2017-03-23"); //合作方工作日期
		bizContent.setOutServiceCode("unsubscribe"); //外部服务代码
		bizContent.setMediumId("1myGlhZWe8ZVG7DKe/7pAr9ew9Yd08XzaX+L2juTb7Q="); //工行联名卡号
		bizContent.setCcy(1);//币种
		bizContent.setName("张三"); //客户户名
		bizContent.setAmount("30000"); //交易额300元
 
		bizContent.setHoldType(57); //保留种类
		bizContent.setHoldDate("2017-03-02");//保留日期/
		bizContent.setBindMediumId("6888888888888888888");
		bizContent.setSummary("资金解除保留"); //摘要
		bizContent.setRemarks("WK0001"); //注释
		bizContent.setSecretKey("h8ZujhDntpKRohwFmGXcnXygNjJHRQbyuVG3183uOdXI2fRgCXeDED9zOw5dO2JucVy7vZ19od7CSUxI2/sVOn6dPklVoaquVElgKRUPlr6D/cqLu25K7hJ3Pt/uOnx4qF/ykmO/IB2qS0rs/sp0Zw==");
		request.setBizContent(bizContent);

		SettlementAccountUnsubscribeResponseV1 response = client.execute(request, "msgId");

		if (response.isSuccess() && response.getReturnCode() == 0) {
			// 业务成功处理
			System.out.println(response.getEventNo());//
		} else {
			// 失败
			System.err.println(response.getReturnCode());//
			System.err.println(response.getReturnMsg());//
		}
	}
}
