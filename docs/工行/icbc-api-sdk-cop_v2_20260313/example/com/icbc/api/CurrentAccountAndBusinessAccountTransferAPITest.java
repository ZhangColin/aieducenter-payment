package com.icbc.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import com.icbc.api.request.CurrentAccountAndBusinessAccountTransferAPIRequestV1;
import com.icbc.api.request.CurrentAccountAndBusinessAccountTransferAPIRequestV1.CurrentAccountAndBusinessAccountTransferAPIRequestV1Biz;
import com.icbc.api.response.CurrentAccountAndBusinessAccountTransferAPIResponseV1;


public class CurrentAccountAndBusinessAccountTransferAPITest{
	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC3v/0CdVTv6vRdxF7syUpYwa1x90VKv91VbBKSL6ea0VBJxN14CphDjPD9TRAf/4z+cVky2NxdIDEvJS5ItXcD7KoKLlxQ+BBQJvLyeXofQJ6tWnX6snhOibJF6mGFef63FS2Gm1bECKOa6SC38I0eD8i0LZttrOvyEly4cGtrk5CMll3XYwvzt9KKozvdCOivhZavt42JF7IoMSIUIlm9tTJfwB3jtX1KdCeiV8//pft/k0ZJmpJHWmDymfrZAEodANvNPDqAo4SI3QbMC+hVw5BEjNuDZcm8cMnBDHk+siyJfPjZd+ec7CE+TTH6u3O/BteaDyOPRcl1TOzlzTaJAgMBAAECggEAUzIpMzi+uVYvSFlGBX3qHJ+skWI8zGhspWWmTuwmvfExogTxpY1efY9N+dIvj91cEERv95a6x6twuYDjIOaQuYUpRCzTfZGjiTbpXyHjSk0uyxyfOGAgGqc7wW4qs42V530+PKkOsvmaYeRV/RJ8QnUxEWto6VKXDeaEdrnn2S+ToGuaRlU+U1EGb27xahldUs+Y7eQrtacidvxS421EuW1kOEC2XaPDsC+r6T3DcuFb+J0EUQiI4EKbcSQDf0/xT1cIkkCPkKzwZIqFI+icZuwnRaIvI9xXv3DvCdhWyLCnDBDgwOewB30+rLAc6yqGtN/4iD2EQvpU9gRsm683kQKBgQD6XZ0g2NdnYAj07ms9TVTUjfO8nw2uizdUduMW+tG83js4Uq1rjt9IeLwrSHaRGTtnECMXKEPK/+TsX+aKrfbU9D/Lw34q4hVlnHAe9mswFl+nH87k4f3ntMX10puW8NWVxgZ/I+YQ80mJ+B3DmnxUbnZ00rC3itOXBL5XcoOr7QKBgQC74pfuyanx9acIvj3nyxieTjv8XzcEr6uvrW7YgGJ++1Ma2MZFH4KEmwbUmM+Lhdfa+0eGHp0v7Bun1s0tPibCbGpo+9npaByyNuo2WTbyCavlVQIc6xryiTXEfYXMyYgCKNOQ+qmZT9bNZyKSNi8uFwWwT6euZpYAS1xtb4b5jQKBgHD3uoVuskr3vzLdPVAsy4EZCAhs8Bx4vp+DAVaGGAsTbz8rtwpYLhGiQK17BdzUuMP5OQwi4+v96MprPzgF5OET0JFdhVlOJ/9wRfsCjzzka4aaSJ1oB0v8enI/9Gp4AreQ4a+OgCnXzK/PX4a59y2VpidyRcCbiHuU7VnB1v3FAoGAKHudcwUeyhS9JeQf4JbhOhLvV+SILUlQu+ypqpqXHnc2o907c19jtwDUSvCWH6D6LqPf+GUWR2IfX12oHex7W38SuoYxoNoe/MNaWdWFQkeVMzfK2cJKryGjZTAKqWJvHNABLgbkjjUmumFTkJ9+MPci8adoIOrXvT5/rtDOC20CgYEAi58Pz/9zHBnI15sgFtWdp02myuDoSlAXS6MCelYoMMFgY2iM1DH8ip4koDPPz/oieN0k8VWSq2kCqk2hyRa0Llib0ilQpUaImvlbBRKtDc2hIfCNsL8OqkyEZZ5LnGSLMJ3oIUwJ2S5zvpbNaB+rqxFWEpmgrDb7TC5TcSNrtHY=";
	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
	//Appid：通过内管查看详情，可获取对应的组织机构、接入方式、公钥等信息
	//APP管理中查看
	protected static final String APP_ID = "10000000000000021587";
	
	
	public void test_cop() throws IcbcApiException {
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
		CurrentAccountAndBusinessAccountTransferAPIRequestV1 request = new CurrentAccountAndBusinessAccountTransferAPIRequestV1();
		CurrentAccountAndBusinessAccountTransferAPIRequestV1Biz bizContent = new CurrentAccountAndBusinessAccountTransferAPIRequestV1Biz();
		request.setServiceUrl("ip:port/api/mybank/pay/currentcorpor/transfer/V1");
		bizContent.setAppStatTransOk(""); //交易运行标志
		bizContent.setAppStatErrNo(""); //错误消息代码
		bizContent.setAppStatMsgNo(""); //消息号码
		bizContent.setAppStatTsfStat(""); //传输标志
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
		bizContent.setCtrlCommTrxNo(""); //外围接口编号
		bizContent.setCtrlCommUserFlag(""); //柜员检查标志
		bizContent.setCtrlCommPinFlag(""); //密码检查标志
		bizContent.setCtrlCommActiveFlag(""); //累计额度检查标志
		bizContent.setCtrlCommFlag6(""); //标志6
		bizContent.setCitrcommIftrxsernb(""); //外围大交易流水号
		bizContent.setCitrcommTrxcode(""); //交易代码
		bizContent.setCitrcommZoneno(""); //地区号
		bizContent.setCitrcommBrno(""); //网点号
		bizContent.setCitrcommTellerno(""); //柜员号
		bizContent.setCitrcommAuthtlno(""); //授权柜员号
		bizContent.setCitrcommAuthcode(""); //授权代码
		bizContent.setCitrcommLevel(""); //柜员级别
		bizContent.setCitrcommCardno(""); //卡号
		bizContent.setCitrcommAuthpwd(""); //授权密码
		bizContent.setCitrcommDutyno(""); //岗位编号
		bizContent.setCitrcommMdcardno(""); //借方卡号
		bizContent.setCitrcommMdcardno1(""); //卡号
		bizContent.setCitrcommAccno(""); //借方帐号
		bizContent.setCitrcommAccno1(""); //贷方帐号
		bizContent.setCitrcommNoaccf(""); //借方新旧帐号标志
		bizContent.setCitrcommNoaccf1(""); //贷方新旧帐号标志
		bizContent.setCitrcommCurrtype(""); //币种
		bizContent.setCitrcommCashexf(""); //借方钞汇标志
		bizContent.setCitrcommAmount(""); //转帐金额
		bizContent.setCitrcommAmount1(""); //抵扣积分的客户信息号
		bizContent.setCitrcommAccpin(""); //客户密码
		bizContent.setCitrcommLgldoctp(""); //证件类型
		bizContent.setCitrcommIdcode(""); //证件号码
		bizContent.setCitrcommAgentno(""); //代理业务编号
		bizContent.setCitrcommAgenttyp(""); //代理业务种类
		bizContent.setCitrcommWorkdate(""); //工作日期
		bizContent.setCitrcommValueday(""); //起/止息日期
		bizContent.setCitrcommRevtranf(""); //反交易标志
		bizContent.setCitrcommPtrxsernb(""); //原交易流水号
		bizContent.setCitrcommCatrflag(""); //现金转帐标志
		bizContent.setCitrcommDrcrf(""); //借贷标志(1借,2贷)
		bizContent.setCitrcommServface(""); //服务界面
		bizContent.setCitrcommTermid(""); //终端号
		bizContent.setCitrcommCashnote(""); //现金注释
		bizContent.setSpcfcommAppno(""); //核对预算值标志
		bizContent.setSpcfcommAccode(""); //借方帐户代码
		bizContent.setSpcfcommAccode1(""); //帐户代码1
		bizContent.setSpcfcommOszoneno(""); //借方旧地区号
		bizContent.setSpcfcommOszoneno1(""); //旧系统地区号1
		bizContent.setSpcfcommSummflag(""); //摘要
		bizContent.setSpcfcommClosint(""); //预算的金额
		bizContent.setSpcfcommInttax(""); //预算的积分值
		bizContent.setSpcfcommFlag1(""); //检查通兑标志
		bizContent.setSpcfcommFlag2(""); //授权标志
		bizContent.setSpcfcommFlag3(""); //出示证件标志
		bizContent.setSpcfcommFlag5(""); //抵扣积分标志
		bizContent.setSpcfcommFlag6(""); //T+0透支标志
		bizContent.setSpcfcommPretelno(""); //原交易柜员号
		bizContent.setSpcfcommVouhtype(""); //凭证种类
		bizContent.setSpcfcommVouhno(""); //凭证号
		bizContent.setSpcfcommVouhdate(""); //签票日期
		bizContent.setSpcfcommVhquota(""); //支票限额
		bizContent.setSpcfcommVouhpwd(""); //凭证帐户密码
		bizContent.setSpcfcommStatcode(""); //外汇统计代码
		bizContent.setSpcfcommSettmode(""); //外汇结算方式
		bizContent.setSpcfcommDrgonouf(""); //同步会计要素标志
		bizContent.setIcomci11095Readflagsa(""); //是否校验CVV的输入标志
		bizContent.setIcomci11095Chkflagsa(""); //无CVV是否允许操作
		bizContent.setIcomci11095Vouhnosa(""); //存折凭证号
		bizContent.setIcomci11095Bookcvvsa(""); //存折CVV
		bizContent.setIcomci11095Ybflag(""); //银保平台虚存实取标志
		bizContent.setIcomci11095Crowflg(""); //短信延时发送标志
		bizContent.setIcomci11095Cancelf(""); //取消原交易标志
		bizContent.setIcomci11095Activef(""); //支持待启用卡进行支取的标志
		bizContent.setIcomci11095Flatseqno(""); //平台流水号
		bizContent.setIcomci11095Dypinf(""); //动态密码检查标志
		bizContent.setIcomci11095Busicode(""); //业务种类标识
		bizContent.setIcomci11095Zoneflag(""); //区域中心标志
		bizContent.setIcomci11095Tranzoneno(""); //受理地区号
		bizContent.setIcomci11095Tranbrno(""); //受理网点号
		bizContent.setIcomci11095Trantellerno(""); //受理柜员号
		bizContent.setIcomci11095Busseqno(""); //受理交易序号
		bizContent.setIcomci11095Pbusseqno(""); //受理原交易序号
		bizContent.setIcomci11095Functyp(""); //工作类型
		bizContent.setIcomci11095Opertyp(""); //操作类型
		bizContent.setIcomci11095Endflag(""); //结卷标志
		bizContent.setIcomci11095Bustypno(""); //业务种类代码
		bizContent.setIcomci11095Busmcelno(""); //业务处理单元代码
		bizContent.setIcomci11095Startdate(""); //当前操作开始日期
		bizContent.setIcomci11095Starttime(""); //当前操作开始时间
		bizContent.setIcomci11095Transeqno(""); //业务流水号
		bizContent.setIcomci11095Subdeduckf(""); //部分扣款标志
		bizContent.setIcomci11095Deflooramt(""); //部分扣除金额下限
		bizContent.setIcomci11095Opflag(""); //操作标识
		bizContent.setIcomci11095Bakfield1(""); //未扣除金额
		bizContent.setIcomci11095Bakfield2(""); //未扣除金额
		bizContent.setIcomci11095Bakfield3(""); //未扣除金额
		bizContent.setCommaccnovatCounts(""); //输入计数器
		List<Map<String,Object>> tmpList = new ArrayList<Map<String, Object>>();
		Map<String,Object> map1 = new HashMap<String, Object>();
		map1.put("","");
		tmpList.add(map1);
		bizContent.setCommaccnovatList(tmpList); //输入涉税列表
		/*
		bizContent.setCommaccnovatZoneno(""); //发票打印地区号内部户开户地区号
		bizContent.setCommaccnovatBrno(""); //发票打印网点号内部户开户地区号
		bizContent.setCommaccnovatProdim(""); //产品识别方式
		bizContent.setCommaccnovatProdmsg1(""); //产品信息1
		bizContent.setCommaccnovatProdmsg2(""); //产品信息2
		bizContent.setCommaccnovatProdmsg3(""); //产品信息3
		bizContent.setCommaccnovatProdinfo(""); //产品信息
		bizContent.setCommaccnovatTpfeattype(""); //客户识别方式
		bizContent.setCommaccnovatTpfeat(""); //客户识别特征值
		bizContent.setCommaccnovatPcflag(""); //收票人个人对公标志
		bizContent.setCommaccnovatCurrtype(""); //币种
		bizContent.setCommaccnovatDrcrf(""); //借贷标志
		bizContent.setCommaccnovatUpdtranf(""); //红字冲正标志
		bizContent.setCommaccnovatRcvtrnovr(""); //应收营业额
		bizContent.setCommaccnovatRealtrnovr(""); //实收营业额
		bizContent.setCommaccnovatFeeitem(""); //收费项目
		bizContent.setCommaccnovatSeptype(""); //价税分离方式
		bizContent.setCommaccnovatInvprtflag(""); //是否要打发票
		bizContent.setCommaccnovatSummary(""); //摘要
		bizContent.setCommaccnovatAccno(""); //内部户账号
		*/
		bizContent.setCommei41853Organno(""); //账务处理机构编码
		bizContent.setCommei41853Currtype(""); //币种
		bizContent.setCommei41853Amount(""); //金额
		bizContent.setCommei41853Drorgan(""); //借方（红字贷方）账户机构编码
		bizContent.setCommei41853Crorgan(""); //贷方（红字借方）账户机构编码
		bizContent.setCommei41853Bztrxcode(""); //业务交易代码（用于查新核算解析分录表KTHPAIA4）
		bizContent.setCommei41853Prodid(""); //产品ID（用于查新核算解析分录表KTHPAIA4）
		bizContent.setCommei41853Operflag(""); //操作标识（用于查新核算解析分录表KTHPAIA4）
		bizContent.setCommei41853Currtyp2(""); //币种2
		bizContent.setCommei41853Amount2(""); //发生额2
		bizContent.setCommei41853Currtyp3(""); //币种3
		bizContent.setCommei41853Amount3(""); //发生额3
		bizContent.setCommei41853Currtyp4(""); //币种4
		bizContent.setCommei41853Amount4(""); //发生额4
		bizContent.setCommei41853Bzorganno(""); //产品层指定归属机构
		bizContent.setCommei41853Bzdrcrf(""); //产品层指定借贷标志
		bizContent.setCommei41853Bzupdtranf(""); //产品层指定冲正标志
		bizContent.setCommei41853Assmsg1(""); //辅助信息1（用于查询新核算解析补充序号表）
		bizContent.setCommei41853Assmsg2(""); //辅助信息2（用于查询新核算解析补充序号表）
		bizContent.setCommei41853Outotactno(""); //原付款人账号
		bizContent.setCommei41853Outotactnam(""); //原付款人名称
		bizContent.setCommei41853Inotactno(""); //原收款人账号
		bizContent.setCommei41853Inotactnam(""); //原收款人名称
		bizContent.setCommei41853Trxinfo(""); //原交易信息
		bizContent.setCommei41853Summary(""); //摘要
		bizContent.setCommei41853Bakflag13(""); //补账账号销账账户检查标志
		bizContent.setCommei41853Bakfield4(""); //补账账号核算核销编号
		bizContent.setCommei41853Bakfield1(""); //补账账号挂账来源
		bizContent.setCommei41853Drfield11(""); //补账借方（红字贷方）账号产品核销编号
		bizContent.setCommei41853Crfield11(""); //补账贷方（红字借方）账号产品核销编号
		bizContent.setCommei41853Bakflag9(""); //补账账号是否必须全额销账标志
		bizContent.setCommei41853Bakfield6(""); //补账账号挂账天数
		bizContent.setCommei41853Bakflag6(""); //补账借方（红字贷方）账号是否支持异地记账标志
		bizContent.setCommei41853Bakflag11(""); //补账借方（红字贷方）/贷方（红字借方）账号是否支持异地/跨核算网点核销标志
		bizContent.setCommei41853Flag1(""); //备用标志1
		bizContent.setCommei41853Flag2(""); //备用标志2
		bizContent.setCommei41853Flag3(""); //备用标志3
		bizContent.setCommei41853Field1(""); //备用字段1
		bizContent.setCommei41853Field2(""); //备用字段2
		bizContent.setCommei41853Field3(""); //备用字段3
		bizContent.setCommei41853Field4(""); //备用字段4

    //request.setSecretKey("h8ZujhDntpKRohwFmGXcnXygNjJHRQbyuVG3183uOdXI2fRgCXeDED9zOw5dO2JucVy7vZ19od7CSUxI2/sVOn6dPklVoaquVElgKRUPlr6D/cqLu25K7hJ3Pt/uOnx4qF/ykmO/IB2qS0rs/sp0Zw=="); 
		request.setBizContent(bizContent);
		CurrentAccountAndBusinessAccountTransferAPIResponseV1 response = client.execute(request, "msgId");
		if (response.isSuccess() ) {
			//成功
			//response.getAccountBalance();
		} else {
			//失败
		}
	}
}
