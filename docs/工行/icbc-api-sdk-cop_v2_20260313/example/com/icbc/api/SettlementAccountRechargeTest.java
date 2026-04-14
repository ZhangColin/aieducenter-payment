package com.icbc.api;

import java.math.BigDecimal;

import org.junit.Test;

import com.icbc.api.internal.util.fastjson.annotation.JSONField;
import com.icbc.api.request.SettlementAccountRechargeRequestV1;
import com.icbc.api.request.SettlementAccountRechargeRequestV1.SettlementAccountRechargeRequestV1Biz;
import com.icbc.api.response.SettlementAccountRechargeResponseV1;

public class SettlementAccountRechargeTest {
	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC3v/0CdVTv6vRdxF7syUpYwa1x90VKv91VbBKSL6ea0VBJxN14CphDjPD9TRAf/4z+cVky2NxdIDEvJS5ItXcD7KoKLlxQ+BBQJvLyeXofQJ6tWnX6snhOibJF6mGFef63FS2Gm1bECKOa6SC38I0eD8i0LZttrOvyEly4cGtrk5CMll3XYwvzt9KKozvdCOivhZavt42JF7IoMSIUIlm9tTJfwB3jtX1KdCeiV8//pft/k0ZJmpJHWmDymfrZAEodANvNPDqAo4SI3QbMC+hVw5BEjNuDZcm8cMnBDHk+siyJfPjZd+ec7CE+TTH6u3O/BteaDyOPRcl1TOzlzTaJAgMBAAECggEAUzIpMzi+uVYvSFlGBX3qHJ+skWI8zGhspWWmTuwmvfExogTxpY1efY9N+dIvj91cEERv95a6x6twuYDjIOaQuYUpRCzTfZGjiTbpXyHjSk0uyxyfOGAgGqc7wW4qs42V530+PKkOsvmaYeRV/RJ8QnUxEWto6VKXDeaEdrnn2S+ToGuaRlU+U1EGb27xahldUs+Y7eQrtacidvxS421EuW1kOEC2XaPDsC+r6T3DcuFb+J0EUQiI4EKbcSQDf0/xT1cIkkCPkKzwZIqFI+icZuwnRaIvI9xXv3DvCdhWyLCnDBDgwOewB30+rLAc6yqGtN/4iD2EQvpU9gRsm683kQKBgQD6XZ0g2NdnYAj07ms9TVTUjfO8nw2uizdUduMW+tG83js4Uq1rjt9IeLwrSHaRGTtnECMXKEPK/+TsX+aKrfbU9D/Lw34q4hVlnHAe9mswFl+nH87k4f3ntMX10puW8NWVxgZ/I+YQ80mJ+B3DmnxUbnZ00rC3itOXBL5XcoOr7QKBgQC74pfuyanx9acIvj3nyxieTjv8XzcEr6uvrW7YgGJ++1Ma2MZFH4KEmwbUmM+Lhdfa+0eGHp0v7Bun1s0tPibCbGpo+9npaByyNuo2WTbyCavlVQIc6xryiTXEfYXMyYgCKNOQ+qmZT9bNZyKSNi8uFwWwT6euZpYAS1xtb4b5jQKBgHD3uoVuskr3vzLdPVAsy4EZCAhs8Bx4vp+DAVaGGAsTbz8rtwpYLhGiQK17BdzUuMP5OQwi4+v96MprPzgF5OET0JFdhVlOJ/9wRfsCjzzka4aaSJ1oB0v8enI/9Gp4AreQ4a+OgCnXzK/PX4a59y2VpidyRcCbiHuU7VnB1v3FAoGAKHudcwUeyhS9JeQf4JbhOhLvV+SILUlQu+ypqpqXHnc2o907c19jtwDUSvCWH6D6LqPf+GUWR2IfX12oHex7W38SuoYxoNoe/MNaWdWFQkeVMzfK2cJKryGjZTAKqWJvHNABLgbkjjUmumFTkJ9+MPci8adoIOrXvT5/rtDOC20CgYEAi58Pz/9zHBnI15sgFtWdp02myuDoSlAXS6MCelYoMMFgY2iM1DH8ip4koDPPz/oieN0k8VWSq2kCqk2hyRa0Llib0ilQpUaImvlbBRKtDc2hIfCNsL8OqkyEZZ5LnGSLMJ3oIUwJ2S5zvpbNaB+rqxFWEpmgrDb7TC5TcSNrtHY=";
	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
	protected static final String APP_ID = "10000000000000021587";


	public void test_cop() throws IcbcApiException {
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
		SettlementAccountRechargeRequestV1 request = new SettlementAccountRechargeRequestV1();
		SettlementAccountRechargeRequestV1Biz bizContent = new SettlementAccountRechargeRequestV1Biz();

		request.setServiceUrl("http://ip:port/api/settlement/account/V1/recharge");
		bizContent.setCorpNo("corpInst1234"); //合作方机构编号
		bizContent.setTrxAccDate("2017-03-14"); //合作方交易日期
		bizContent.setTrxAccTime("00:00:00"); //合作方交易时间
		bizContent.setCorpDate("2017-03-14"); //合作方工作日期
		bizContent.setCorpSerno("20202020"); //合作方交易单号
		bizContent.setOutServiceCode("recharge"); //外部服务代码
		bizContent.setMediumId("6222888888888888888"); //工行联名卡号
		bizContent.setBindMedium("6222888888888888888"); //绑定的I类卡号
		bizContent.setCcy(1); //币种
		bizContent.setCashExFlag(0); //钞汇标志
		bizContent.setAmount("300"); //交易金额
		bizContent.setSummary("DY"); //摘要
		bizContent.setRemarks("DY"); //注释
		bizContent.setNotifyAddr("aaaaa"); //通知地址
		bizContent.setSecretKey("ASDQWEQDZCSDFAWWQDA"); //sm4对称密钥
		bizContent.setMediumIdHash("SDFDFHTEWTGDFWADADAFSDGSESEFD"); //卡号哈希值
		bizContent.setBindMediumHash("SDFDFHTEWTGDFWADADAFSDGSESEFD"); //绑定介质哈希值
		bizContent.setCorrectionFlag(0);//冲账标志
		bizContent.setCorrectionDate("2017-03-14");//冲账日期
		request.setBizContent(bizContent);

		SettlementAccountRechargeResponseV1 response = client.execute(request, "msgId");
		if (response.isSuccess() ) {
			//成功
			//response.getAccountBalance();
		} else {
			//失败
		}
	}
}
