package com.icbc.api;

import com.icbc.api.request.ApplyCurrentAccountRequestV2;
import com.icbc.api.request.ApplyCurrentAccountRequestV2.ApplyCurrentAccountRequestV2Biz;
import com.icbc.api.response.ApplyCurrentAccountResponseV2;


/**
 * 往来户预约开户
 * 
 * 请商户配置SDK中的lib文件夹中的jar包后，根据备注1-6提示进行数据替换
 */
public class ApplyCurrentAccountTestV2 {

	//1、网关公钥
	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

	//2、appid
	protected static final String APP_ID = "10000000000000002156";

	//3、密钥对认证方式，公钥在API平台登记，此处为私钥
	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----CdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIJBzZNaiobplRUgtJ4OzmUvZRK99ih/fUyDBOoFLtpJJCCRzp8T6V11YNlE7Xg5dt+EG7byQs2NImqg0eWEj/mBdZ7UmlAct7BNw2hyF2h4R5OEfXyjoH3wqGjKJayhaHTvLM1DYy/mDFBb0ShJYI1QMScQJZhsOhMMFhrrZwIZAgMBAAECgYAA2kdrOIOBoJPOQJmOE1C8jtPdjIrI9xSt5Imqsn/9A8+NuwacOfgkGXmZ0n6vc8jYa7f2uZ1AVTUtd4IIO5bpq8s0Tw2BfWALYwr/JdUuNKSjHVQsh/Do+wl8BgOgB4RqsNXWNGtoMC8lHKHmrVcpyJMfDc3cP07NZ1wG2zB0lQJBAM+dNZv2L/Z4RzvQcoVZEthYavZ4pkFoWGYC4jwc5G8um76zoQyrtxWYrtTP0GS+xFFX2dEuiGXxwzmSQJrPdrMCQQCgnUXcQe/if2c6TFt4x9v+6L0pmFClYyiOi9RuBSz1sHmPouuc/YYvuxAOdOzu3yzOkeo7b5KcCKITTWvKI+oDAkA5dl6vIw2VXycAJCp+Q/AWVyqLu0rw0Yud+HBbiPek2jabKqaJlkFfRdol5rrcF3zIstMDtahk5uxM0/DzqDZHAkBGnZ8vfdYIUVeDbDrzWXvCEXXJqewbKwOT2KqnTKM9yj9IBatttJGgvrAKiyH4zCqZD9JaG23sKGeJ8QopL60dAkEAtc4tlKoj3XZzRUXboqz0EhkgkjzDj50zpCD1sJKZ2EZH+A/7tXwPug+RnuSmKpM2uv3msqw3prdS3K4En8+rog==";
	public static void main(String[] args) throws Exception {

		//签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
		ApplyCurrentAccountRequestV2 request = new ApplyCurrentAccountRequestV2();
		
		//4、根据测试环境和生产环境替换相应ip和端口
        request.setServiceUrl("http://ip:port/api/mybank/account/enterprise/applycurrentaccount/V2");
        //request.setServiceUrl("http://ip:port/api/mybank/account/enterprise/applycurrentaccount/V2");
		
		//5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值，非必输字段可不上送
		ApplyCurrentAccountRequestV2Biz bizContent = new ApplyCurrentAccountRequestV2Biz();
		bizContent.setTrxDate("2018-06-12");//渠道交易日期
		bizContent.setTrxTime("15:11:11");//渠道交易时间
		bizContent.setAccname("linyg");//户名
		bizContent.setCurrtype("1");//币种
		bizContent.setCis("");//集团CIS号
		bizContent.setZoneno("200");//开户地区号
		bizContent.setBrno("2");//开户网点号
		bizContent.setAccatrbt("2");//账户性质
		bizContent.setAcctype("1");//账户类型
		bizContent.setAccpertype("1");//账户类别
		bizContent.setAccpername("a公司");//存款人名称/客户名称
		bizContent.setCisPhone("2653882");//单位电话
		bizContent.setCisPost("519000");//单位邮政编码
		bizContent.setRegCurr("1");//注册资金币种
		bizContent.setRegCapital("100");//注册资金
		bizContent.setIndustryForPrimary("服饰");//经营范围
		bizContent.setBusEnddate("2025-12-12");//营业执照有效期
		bizContent.setBusLicense("915001035789578375");//营业执照号码
		bizContent.setTaxHno("915001035789578375");//税务登记证信息－国税税务登记证号码
		bizContent.setTaxHenddate("2025-12-12");//税务登记证信息－国税税务登记证有效日期
		bizContent.setTaxLno("915001035789578375");//税务登记证信息－地税税务登记证号码
		bizContent.setTaxLenddate("2025-12-12");//税务登记证信息－地税税务登记证有效日期
		bizContent.setTaxOtherCer("");//税务登记证信息－无须办理的证明
		bizContent.setCorpFlag("1");//法定代表人或单位负责人
		bizContent.setCorpName("张三");//法定代表人证件或单位负责人证件-姓名
		bizContent.setCorpType("000");//法定代表人证件或单位负责人证件-身份证件种类
		bizContent.setCorpNo("440923199111113718");//法定代表人证件或单位负责人证件-身份证件号码
		bizContent.setCorpEnddate("2025-12-12");//法定代表人证件或单位负责人证件-身份证件有效期
		bizContent.setCorpPhone("2653882");//法定代表人证件或单位负责人证件-固定电话
		bizContent.setCorpMvphone("18818653326");//法定代表人证件或单位负责人证件-手机
		bizContent.setAuthName("李四");//授权代理人证件-姓名
		bizContent.setAuthType("000");//授权代理人证件-身份证件种类
		bizContent.setAuthNo("440923199111113719");//授权代理人证件-身份证件号码
		bizContent.setAuthEnddate("2025-12-12");//授权代理人证件-身份证件有效期
		bizContent.setAuthPhone("2653883");//授权代理人证件-固定电话
		bizContent.setAuthMvphone("18818653327");//授权代理人证件-手机
		bizContent.setParentCorpFlag("1");//上级单位-法定代表人或单位负责人
		bizContent.setParentCorpName("王五");//上级单位-法定代表人证件或单位负责人证件-姓名
		bizContent.setParentCorpType("000");//上级单位-法定代表人证件或单位负责人证件-身份证件种类
		bizContent.setParentCorpNo("440923199111113710");//上级单位-法定代表人证件或单位负责人证件-身份证件号码
		bizContent.setParentCorpEnddate("2025-12-12");//上级单位-法定代表人证件或单位负责人证件-身份证件有效期
		bizContent.setParentCorpPhone("2653884");//上级单位-法定代表人证件或单位负责人证件-固定电话
		bizContent.setParentCorpMvphone("18818653328");//上级单位-法定代表人证件或单位负责人证件-手机
		bizContent.setParentName("b公司");//上级单位名称
		bizContent.setParentOrgexno("123");//上级单位基本存款账户许可证号
		bizContent.setParentOrgno("123");//上级单位组织机构代码证号码
		bizContent.setFinaPhone("2653884");//财务主管人电话
		bizContent.setFinaMvphone("18818653328");//财务主管人手机
		bizContent.setHolderPhone("2653884");//控股股东电话
		bizContent.setHolderMvphone("18818653328");//控股股东手机
		bizContent.setRholderPhone("2653884");//实际控制人电话
		bizContent.setRholderMvphone("18818653328");//实际控制人手机
		bizContent.setWtUnitName("a公司");//委托单位名称
		bizContent.setAuthExpDate("2025-12-12");//授权到期日
		bizContent.setAuthHandle("法人");//授权办理人职务
		bizContent.setRegAddArea("156");//注册地址-国家
		bizContent.setRegAddPro("江苏省");//注册地址-省（直辖市、自治区）
		bizContent.setRegAddCity("南京市");//注册地址-市
		bizContent.setRegAddDis("玄武区");//注册地址-县、区
		bizContent.setRegAddress("珠江路1号");//注册地址-地址
		bizContent.setRegAddPhoarno("86");//注册地址-国际电话国家代码
		bizContent.setRegAddPhocino("100");//注册地址-地区代码
		bizContent.setRegAddPhonum("2653882");//注册地址-号码主体
		bizContent.setRegAddPhobodyNum("123");//注册地址-分机号码
		bizContent.setRegAddPost("519000");//注册地址-单位邮政编码
		bizContent.setFinaExecName("王五");//财务主管人证件-姓名
		bizContent.setFinaExecType("000");//财务主管人证件-身份证件种类
		bizContent.setFinaExecTypeno("440923199111113710");//财务主管人证件-身份证件号码
		bizContent.setFinaExecEnddate("2025-12-12");//财务主管人证件-身份证件有效期
		bizContent.setFinaExecPhone("2653884");//财务主管人证件-固定电话
		bizContent.setFinaExecMvphone("18818653328");//财务主管人证件-手机
		bizContent.setPreApplTel("18818653325");//预约人手机
		bizContent.setWorkAddArea("156");//办公地址-国家
		bizContent.setWorkAddPro("江苏省");//办公地址-省（直辖市、自治区）
		bizContent.setWorkAddCity("南京市");//办公地址-市
		bizContent.setWorkAddDis("玄武区");//办公地址-县、区
		bizContent.setWorkAddress("珠江路1号");//办公地址-地址
		bizContent.setWorkAddPhonum("2653884");//办公地址-电话号码
		bizContent.setWorkAddPhobodyNum("123");//办公地址-分机号码
		bizContent.setWorkAddPost("519000");//办公地址-单位邮政编码
		bizContent.setLpPhobodyNum("123");//法定代表人证件或单位负责人证件-分机号码
		bizContent.setFePhobodyNum("123");//财务主管人证件-分机号码
		bizContent.setHolderName("赵六");//控股股东信息-姓名
		bizContent.setHolderPhobodyNum("123");//控股股东信息-分机号码
		bizContent.setAcPhobodyNum("123");//授权代理人证件-分机号码
		bizContent.setNoitfyFlag("1");//消息通知标志 0-不通知 1-通知
		bizContent.setProcessFlag("1");//新流程试点开关 0-关 1-开
		request.setBizContent(bizContent);

		ApplyCurrentAccountResponseV2 response = new ApplyCurrentAccountResponseV2();
		try {
			response = client.execute(request, "msgId");//msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一
			if (response.isSuccess()) {
				//6、业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
				System.out.println("ReturnCode:"+response.getReturnCode());
				System.out.println("response:" + response);
			} else {
				//失败
				System.out.println("ReturnCode:" + response.getReturnCode());
				System.out.println("ReturnMsg:" + response.getReturnMsg());
			}
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}
	}
}

