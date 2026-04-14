package com.icbc.api;

import org.junit.Test;

import com.icbc.api.request.SettlementAccountInfoQueryRequestV1;
import com.icbc.api.request.SettlementAccountInfoQueryRequestV1.SettlementAccountInfoQueryRequestV1Biz;
import com.icbc.api.response.SettlementAccountInfoQueryResponseV1;

public class SettlementAccountInfoQueryTest {
	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC3v/0CdVTv6vRdxF7syUpYwa1x90VKv91VbBKSL6ea0VBJxN14CphDjPD9TRAf/4z+cVky2NxdIDEvJS5ItXcD7KoKLlxQ+BBQJvLyeXofQJ6tWnX6snhOibJF6mGFef63FS2Gm1bECKOa6SC38I0eD8i0LZttrOvyEly4cGtrk5CMll3XYwvzt9KKozvdCOivhZavt42JF7IoMSIUIlm9tTJfwB3jtX1KdCeiV8//pft/k0ZJmpJHWmDymfrZAEodANvNPDqAo4SI3QbMC+hVw5BEjNuDZcm8cMnBDHk+siyJfPjZd+ec7CE+TTH6u3O/BteaDyOPRcl1TOzlzTaJAgMBAAECggEAUzIpMzi+uVYvSFlGBX3qHJ+skWI8zGhspWWmTuwmvfExogTxpY1efY9N+dIvj91cEERv95a6x6twuYDjIOaQuYUpRCzTfZGjiTbpXyHjSk0uyxyfOGAgGqc7wW4qs42V530+PKkOsvmaYeRV/RJ8QnUxEWto6VKXDeaEdrnn2S+ToGuaRlU+U1EGb27xahldUs+Y7eQrtacidvxS421EuW1kOEC2XaPDsC+r6T3DcuFb+J0EUQiI4EKbcSQDf0/xT1cIkkCPkKzwZIqFI+icZuwnRaIvI9xXv3DvCdhWyLCnDBDgwOewB30+rLAc6yqGtN/4iD2EQvpU9gRsm683kQKBgQD6XZ0g2NdnYAj07ms9TVTUjfO8nw2uizdUduMW+tG83js4Uq1rjt9IeLwrSHaRGTtnECMXKEPK/+TsX+aKrfbU9D/Lw34q4hVlnHAe9mswFl+nH87k4f3ntMX10puW8NWVxgZ/I+YQ80mJ+B3DmnxUbnZ00rC3itOXBL5XcoOr7QKBgQC74pfuyanx9acIvj3nyxieTjv8XzcEr6uvrW7YgGJ++1Ma2MZFH4KEmwbUmM+Lhdfa+0eGHp0v7Bun1s0tPibCbGpo+9npaByyNuo2WTbyCavlVQIc6xryiTXEfYXMyYgCKNOQ+qmZT9bNZyKSNi8uFwWwT6euZpYAS1xtb4b5jQKBgHD3uoVuskr3vzLdPVAsy4EZCAhs8Bx4vp+DAVaGGAsTbz8rtwpYLhGiQK17BdzUuMP5OQwi4+v96MprPzgF5OET0JFdhVlOJ/9wRfsCjzzka4aaSJ1oB0v8enI/9Gp4AreQ4a+OgCnXzK/PX4a59y2VpidyRcCbiHuU7VnB1v3FAoGAKHudcwUeyhS9JeQf4JbhOhLvV+SILUlQu+ypqpqXHnc2o907c19jtwDUSvCWH6D6LqPf+GUWR2IfX12oHex7W38SuoYxoNoe/MNaWdWFQkeVMzfK2cJKryGjZTAKqWJvHNABLgbkjjUmumFTkJ9+MPci8adoIOrXvT5/rtDOC20CgYEAi58Pz/9zHBnI15sgFtWdp02myuDoSlAXS6MCelYoMMFgY2iM1DH8ip4koDPPz/oieN0k8VWSq2kCqk2hyRa0Llib0ilQpUaImvlbBRKtDc2hIfCNsL8OqkyEZZ5LnGSLMJ3oIUwJ2S5zvpbNaB+rqxFWEpmgrDb7TC5TcSNrtHY=";
	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
	protected static final String APP_ID = "10000000000000021587";


	public void test_cop() throws IcbcApiException {
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
		SettlementAccountInfoQueryRequestV1 request = new SettlementAccountInfoQueryRequestV1();
		SettlementAccountInfoQueryRequestV1Biz bizContent = new SettlementAccountInfoQueryRequestV1Biz();

		request.setServiceUrl("http://ip:port/api/settlement/account/info/V1/query");
		bizContent.setCorpNo("corpInst1234");
		bizContent.setTrxAccDate("2017-03-02");
		bizContent.setTrxAccTime("10:38:01");
		bizContent.setCorpDate("2017-03-02");
		bizContent.setCorpSerno("ABC123456789");
		bizContent.setOutServiceCode("queryinfo");
		bizContent.setMediumId("6888888888888888888");
		bizContent.setSecretKey("jadfaijdfaifjakj");
		request.setBizContent(bizContent);

		SettlementAccountInfoQueryResponseV1 response = client.execute(request, "msgId");
		if (response.isSuccess()) {
			// 成功
			// response.getAccountBalance();
		} else {
			// 失败
		}
	}
}
