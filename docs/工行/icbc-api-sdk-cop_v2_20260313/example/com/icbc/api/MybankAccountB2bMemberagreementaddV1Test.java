package com.icbc.api;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.junit.Test;

import com.icbc.api.request.MybankAccountB2bMemberagreementaddV1Request;
import com.icbc.api.request.MybankAccountB2bMemberagreementaddV1Request.MybankAccountB2bMemberagreementaddV1RequestBiz;
import com.icbc.api.response.MybankAccountB2bMemberagreementaddV1Response;

/**
 * 合作方会员产品协议录入
 * 
 * 请商户配置SDK中的lib文件夹中的jar包后，根据备注1-6提示进行数据替换
 */
public class MybankAccountB2bMemberagreementaddV1Test {

	// 1、网关公钥
	protected static final String APIGW_PUBLIC_KEY = "XXXXXXX";

	// 2、appid
	protected static final String APP_ID = "XXXX";

	// 3、密钥对认证方式，公钥在API平台登记，此处为私钥
	protected static final String MY_PRIVATE_KEY = "XXXXXXX";

	//加密类型和秘钥
	protected static final String encryptType = "xxxx";
	protected static final String encryptKey = "xxxx";

	SimpleDateFormat sd = new SimpleDateFormat("yyMMddHHmmss");

	 // 增加企业会员
	public void testAddEnterprise() throws Exception {

		// 签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA, MY_PRIVATE_KEY,
				APIGW_PUBLIC_KEY);

		MybankAccountB2bMemberagreementaddV1Request request = new MybankAccountB2bMemberagreementaddV1Request();

		// 4、根据测试环境和生产环境替换相应ip和端口
		//request.setServiceUrl("http://xxx.xxx.xxx.xxx:xxx/api/mybank/account/b2b/memberagreementadd/V1");
		request.setServiceUrl("http://IP:PROT/api/mybank/account/b2b/memberagreementadd/V1");

		// 5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值，非必输字段可不上送
		MybankAccountB2bMemberagreementaddV1RequestBiz bizContent = new MybankAccountB2bMemberagreementaddV1RequestBiz();

		bizContent.setTrxDate("2021-10-20"); // 交易日期
		bizContent.setCheckWorkDateFlag("2"); // 核对交易日期标志
		bizContent.setPartnerProno("1801627"); // 合作方协议编号
		bizContent.setMemberNo(sd.format(new Date())); // 会员编号
		bizContent.setMemberName("北京大宗商品交易所有限公司"); // 会员名称
		bizContent.setMemberType("1"); // 会员类型
		bizContent.setCorpRepreName("阿宝"); // 法人代表姓名
		bizContent.setIdCardNo("34012319840503233X"); // 身份证号码
		bizContent.setIdCardSignDate("2019-06-21"); // 身份证签发日期
		bizContent.setTelphoneNo("18666130001"); // 手机号
		bizContent.setAccBankFlag("1"); // 账号本他行标志
		bizContent.setAccno("0200041719213559609"); // 账号
		bizContent.setCurrtype("1"); // 币种
		bizContent.setAccName("北京大宗商品交易所有限公司（北京）"); // 账户名称
		// bizContent.setAccBankNo(""); // 开户行号
		// bizContent.setAccBankName("工商银行"); // 开户银行
		bizContent.setDealName("丹娜"); // 经办人姓名
		bizContent.setDealTelphoneNo("18666130002"); // 经办人手机号
		bizContent.setSociCreCode("911101057263777049"); // 统一社会信用代码
		bizContent.setPickCashDays("12");//货款自动提现天数 
		bizContent.setConfFlag("2"); //认证标志
		// bizContent.setSingNoNoteAmtd("100000"); // 单笔免短信验证码额度
		bizContent.setPayMode("0"); //入账方式

		request.setBizContent(bizContent);

		MybankAccountB2bMemberagreementaddV1Response response = new MybankAccountB2bMemberagreementaddV1Response();
		try {
			response = client.execute(request, UUID.randomUUID().toString().replaceAll("-", ""));// msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一
			if (response.isSuccess()) {
				// 6、业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
				System.out.println("ReturnCode:" + response.getReturnCode());
				System.out.println("returnMsg:" + response.getReturnMsg());
			} else {
				System.out.println("returnCode:" + response.getReturnCode());
				System.out.println("returnMsg:" + response.getReturnMsg());
			}
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}
	}

/*	 // 增加个人会员
	public void testAddPersonalCustomer() throws Exception {

		// 签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA, MY_PRIVATE_KEY,
				APIGW_PUBLIC_KEY);

		MybankAccountB2bMemberagreementaddV1Request request = new MybankAccountB2bMemberagreementaddV1Request();

		// 4、根据测试环境和生产环境替换相应ip和端口
		request.setServiceUrl("http://xxx.xxx.xxx.xxx:xxx/api/mybank/account/b2b/memberagreementadd/V1");

		// 5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值，非必输字段可不上送
		MybankAccountB2bMemberagreementaddV1RequestBiz bizContent = new MybankAccountB2bMemberagreementaddV1RequestBiz();

		bizContent.setTrxDate("2021-10-20"); // 交易日期
		bizContent.setCheckWorkDateFlag("2"); // 核对交易日期标志
		bizContent.setPartnerProno("1801627"); // 合作方协议编号
		bizContent.setMemberNo(sd.format(new Date())); // 会员编号
		bizContent.setMemberName("阿宝"); // 会员名称
		bizContent.setMemberType("2"); // 会员名称,企业名称/个人名称
		// bizContent.setCorpRepreName(""); // 法人代表姓名
		bizContent.setIdCardNo("34012319840503233X"); // 身份证号码
		bizContent.setIdCardSignDate("2019-06-21"); // 身份证签发日期
		bizContent.setTelphoneNo("18666130079"); // 手机号
		bizContent.setAccBankFlag("1"); // 账号本他行标志
		bizContent.setAccno("6222080200001187619"); // 账号
		bizContent.setCurrtype("1"); // 币种
		bizContent.setAccName("阿宝"); // 账户名称
		// bizContent.setAccBankNo(""); // 开户行号
		bizContent.setAccBankName("工商银行"); // 开户银行
		// bizContent.setDealName(""); // 经办人姓名
		// bizContent.setDealTelphoneNo(""); // 经办人手机号
		// bizContent.setSociCreCode(""); // 统一社会信用代码
		// bizContent.setSingNoNoteAmtd("100000"); // 单笔免短信验证码额度

		request.setBizContent(bizContent);

		MybankAccountB2bMemberagreementaddV1Response response = new MybankAccountB2bMemberagreementaddV1Response();
		try {
			response = client.execute(request, UUID.randomUUID().toString().replaceAll("-", ""));// msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一
			if (response.isSuccess()) {
				// 6、业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
				System.out.println("ReturnCode:" + response.getReturnCode());
				System.out.println("returnMsg:" + response.getReturnMsg());
			} else {
				System.out.println("returnCode:" + response.getReturnCode());
				System.out.println("returnMsg:" + response.getReturnMsg());
			}
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}
	}*/

}
