package com.icbc.api;

import org.junit.Test;
import com.icbc.api.request.ManyToManyTransferAPIRequestV1;
import com.icbc.api.request.ManyToManyTransferAPIRequestV1.ManyToManyTransferAPIRequestV1Biz;
import com.icbc.api.response.ManyToManyTransferAPIResponseV1;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManyToManyTransferAPITest{
	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC3v/0CdVTv6vRdxF7syUpYwa1x90VKv91VbBKSL6ea0VBJxN14CphDjPD9TRAf/4z+cVky2NxdIDEvJS5ItXcD7KoKLlxQ+BBQJvLyeXofQJ6tWnX6snhOibJF6mGFef63FS2Gm1bECKOa6SC38I0eD8i0LZttrOvyEly4cGtrk5CMll3XYwvzt9KKozvdCOivhZavt42JF7IoMSIUIlm9tTJfwB3jtX1KdCeiV8//pft/k0ZJmpJHWmDymfrZAEodANvNPDqAo4SI3QbMC+hVw5BEjNuDZcm8cMnBDHk+siyJfPjZd+ec7CE+TTH6u3O/BteaDyOPRcl1TOzlzTaJAgMBAAECggEAUzIpMzi+uVYvSFlGBX3qHJ+skWI8zGhspWWmTuwmvfExogTxpY1efY9N+dIvj91cEERv95a6x6twuYDjIOaQuYUpRCzTfZGjiTbpXyHjSk0uyxyfOGAgGqc7wW4qs42V530+PKkOsvmaYeRV/RJ8QnUxEWto6VKXDeaEdrnn2S+ToGuaRlU+U1EGb27xahldUs+Y7eQrtacidvxS421EuW1kOEC2XaPDsC+r6T3DcuFb+J0EUQiI4EKbcSQDf0/xT1cIkkCPkKzwZIqFI+icZuwnRaIvI9xXv3DvCdhWyLCnDBDgwOewB30+rLAc6yqGtN/4iD2EQvpU9gRsm683kQKBgQD6XZ0g2NdnYAj07ms9TVTUjfO8nw2uizdUduMW+tG83js4Uq1rjt9IeLwrSHaRGTtnECMXKEPK/+TsX+aKrfbU9D/Lw34q4hVlnHAe9mswFl+nH87k4f3ntMX10puW8NWVxgZ/I+YQ80mJ+B3DmnxUbnZ00rC3itOXBL5XcoOr7QKBgQC74pfuyanx9acIvj3nyxieTjv8XzcEr6uvrW7YgGJ++1Ma2MZFH4KEmwbUmM+Lhdfa+0eGHp0v7Bun1s0tPibCbGpo+9npaByyNuo2WTbyCavlVQIc6xryiTXEfYXMyYgCKNOQ+qmZT9bNZyKSNi8uFwWwT6euZpYAS1xtb4b5jQKBgHD3uoVuskr3vzLdPVAsy4EZCAhs8Bx4vp+DAVaGGAsTbz8rtwpYLhGiQK17BdzUuMP5OQwi4+v96MprPzgF5OET0JFdhVlOJ/9wRfsCjzzka4aaSJ1oB0v8enI/9Gp4AreQ4a+OgCnXzK/PX4a59y2VpidyRcCbiHuU7VnB1v3FAoGAKHudcwUeyhS9JeQf4JbhOhLvV+SILUlQu+ypqpqXHnc2o907c19jtwDUSvCWH6D6LqPf+GUWR2IfX12oHex7W38SuoYxoNoe/MNaWdWFQkeVMzfK2cJKryGjZTAKqWJvHNABLgbkjjUmumFTkJ9+MPci8adoIOrXvT5/rtDOC20CgYEAi58Pz/9zHBnI15sgFtWdp02myuDoSlAXS6MCelYoMMFgY2iM1DH8ip4koDPPz/oieN0k8VWSq2kCqk2hyRa0Llib0ilQpUaImvlbBRKtDc2hIfCNsL8OqkyEZZ5LnGSLMJ3oIUwJ2S5zvpbNaB+rqxFWEpmgrDb7TC5TcSNrtHY=";
	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
	//Appid：通过内管查看详情，可获取对应的组织机构、接入方式、公钥等信息
	//APP管理中查看
	protected static final String APP_ID = "10000000000000021587";
	

	public void test_cop() throws IcbcApiException {
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
		ManyToManyTransferAPIRequestV1 request = new ManyToManyTransferAPIRequestV1();
		ManyToManyTransferAPIRequestV1Biz bizContent = new ManyToManyTransferAPIRequestV1Biz();
		request.setServiceUrl("ip:port/api/mybank/pay/personpay/personmult2multtrans/V2");
		bizContent.setAppStatTransOk(""); //流程控制变量
		bizContent.setAppStatErrNo(""); //出错信息
		bizContent.setAppStatMsgNo(""); //消息号
		bizContent.setAppStatTsfStat(""); //通信区传输状态
		bizContent.setChanCommChannelType(""); //渠道种类
		bizContent.setChanCommCtbId(""); //渠道终端编号
		bizContent.setChanCommMac(""); //渠道MAC地址
		bizContent.setChanCommCtbIp(""); //渠道IP地址
		bizContent.setChanCommApp(""); //发起应用
		bizContent.setChanCommLevel(""); //业务等级
		bizContent.setChanCommSerialNo(""); //事件编号
		bizContent.setChanCommRelatedSerialNo(""); //关联事件编号
		bizContent.setChanCommOriginalserialNo(""); //原事件编号
		bizContent.setChanCommPreFlag(""); //预后处理标志
		bizContent.setChanCommAuthzoneNo(""); //授权地区号
		bizContent.setChanCommAuthbrNo(""); //授权网点号
		bizContent.setChanCommAuthType(""); //远程授权标志
		bizContent.setChanCommAuthSsi(""); //授权柜员密码
		bizContent.setChanCommTimeStamp(""); //主机时间戳
		bizContent.setChanCommProdId(""); //产品编码预留1
		bizContent.setChanCommCobProdId(""); //产品编码预留2
		bizContent.setChanCommFlag1(""); //预留公用备用标志1
		bizContent.setChanCommFlag2(""); //预留公用备用标志2
		bizContent.setChanCommFlag3(""); //预留公用备用标志3
		bizContent.setChanCommField1(""); //预留公用字段1
		bizContent.setChanCommField2(""); //预留公用字段2
		bizContent.setChanCommField3(""); //预留公用字段3
		bizContent.setChanCommField4(""); //预留公用字段4
		bizContent.setChanCommField5(""); //预留公用字段5
		bizContent.setChanCommField6(""); //预留公用字段6
		bizContent.setChanCommField7(""); //预留公用字段7
		bizContent.setChanCommField8(""); //预留公用字段8
		bizContent.setChanCommField9(""); //预留公用字段9
		bizContent.setCtrlCommTrxNo(""); //外围交易编号
		bizContent.setCtrlCommUserFlag(""); //柜员检查标志
		bizContent.setCtrlCommPinFlag(""); //密码检查标志
		bizContent.setFpcicommIftrxsernb(""); //外围大交易流水号
		bizContent.setFpcicommPiftrxseqnb(""); //原交易流水号
		bizContent.setFpcicommTrxcode(""); //交易代码
		bizContent.setFpcicommZoneno(""); //地区号
		bizContent.setFpcicommBrno(""); //网点号
		bizContent.setFpcicommTellerno(""); //柜员号
		bizContent.setFpcicommAuthtlno(""); //授权柜员号
		bizContent.setFpcicommAuthcode(""); //授权代码
		bizContent.setFpcicommVagenlevel(""); //柜员级别
		bizContent.setFpcicommCardno(""); //卡号
		bizContent.setFpcicommAuthpwd(""); //授权密码
		bizContent.setFpcicommDutyno(""); //岗位编号
		bizContent.setFpcicommRevtranf(""); //反交易标志
		bizContent.setFpcicommTrdrino(""); //转账借方笔数
		bizContent.setFpcicommTrcrino(""); //转账贷方笔数
		bizContent.setFpcicommServface(""); //服务界面
		bizContent.setFpcicommTermid(""); //终端号
		List<Map<String,Object>> tmpList1 = new ArrayList<Map<String, Object>>();
		Map<String,Object> map1 = new HashMap<String, Object>();
		map1.put("","");
		tmpList1.add(map1);
		bizContent.setTotidtlcommList(tmpList1); //多对多明细记录

		bizContent.setCommaccnovatCounts(""); //输入计数器
		
		List<Map<String,Object>> tmpList2 = new ArrayList<Map<String, Object>>();
		Map<String,Object> map2 = new HashMap<String, Object>();
		map1.put("","");
		tmpList2.add(map2);
		bizContent.setCommaccnovatList(tmpList2); //输入涉税列表

    //request.setSecretKey("h8ZujhDntpKRohwFmGXcnXygNjJHRQbyuVG3183uOdXI2fRgCXeDED9zOw5dO2JucVy7vZ19od7CSUxI2/sVOn6dPklVoaquVElgKRUPlr6D/cqLu25K7hJ3Pt/uOnx4qF/ykmO/IB2qS0rs/sp0Zw=="); 
		request.setBizContent(bizContent);
		ManyToManyTransferAPIResponseV1 response = client.execute(request, "msgId");
		if (response.isSuccess() ) {
			//成功
			//response.getAccountBalance();
		} else {
			//失败
		}
	}
}
