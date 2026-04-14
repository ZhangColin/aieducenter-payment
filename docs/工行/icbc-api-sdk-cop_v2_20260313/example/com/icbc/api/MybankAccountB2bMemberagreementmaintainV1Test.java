package com.icbc.api;

import org.junit.Test;

import com.icbc.api.request.MybankAccountB2bMemberagreementmaintainV1Request;
import com.icbc.api.request.MybankAccountB2bMemberagreementmaintainV1Request.MybankAccountB2bMemberagreementmaintainV1RequestBiz;
import com.icbc.api.response.MybankAccountB2bMemberagreementmaintainV1Response;


/**
 * 合作方会员产品协议维护
 * 
 * 请商户配置SDK中的lib文件夹中的jar包后，根据备注1-6提示进行数据替换
 */
public class MybankAccountB2bMemberagreementmaintainV1Test {

	// 1、网关公钥
	protected static final String APIGW_PUBLIC_KEY = "XXXXXXX";

	// 2、appid
	protected static final String APP_ID = "XXXX";

	// 3、密钥对认证方式，公钥在API平台登记，此处为私钥
	protected static final String MY_PRIVATE_KEY = "XXXXXXX";

	//加密类型和秘钥
	protected static final String encryptType = "xxxx";
	protected static final String encryptKey = "xxxx";

	public void test() throws Exception {

		//签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
		MybankAccountB2bMemberagreementmaintainV1Request request = new MybankAccountB2bMemberagreementmaintainV1Request();
		
		//4、根据测试环境和生产环境替换相应ip和端口
        //request.setServiceUrl("http://ip:port/api/mybank/account/enterprise/applycurrentaccount/V2");		
		request.setServiceUrl("http://ip:port/api/mybank/account/b2b/paragrment/maintain/V1");
		
		//5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值，非必输字段可不上送
		MybankAccountB2bMemberagreementmaintainV1RequestBiz bizContent = new MybankAccountB2bMemberagreementmaintainV1RequestBiz();
                        
	      bizContent.setTrxDate("2018-08-22");                        //交易日期         
	      bizContent.setCheckWorkDateFlag("1");                       //核对交易日期标志 
	      bizContent.setOperation_type("2");                          //操作类型
		  bizContent.setPartnerProno("1800128");                       //合作方协议编号         
	      bizContent.setMemberNo("1536818307601");                       //会员编号               
	      bizContent.setMemberNameOld("dfasf");                          //会员名称旧值           
	      bizContent.setMemberTypeOld("");                              //会员类型旧值           
	      bizContent.setCorpRepreNameOld("");                            //法人代表姓名旧值              
	      bizContent.setIdCardNoOld("");                                //身份证号码旧值 
	      bizContent.setIdCardSignDateOld ("");                          //身份证签发日期旧值	      
	      bizContent.setTelphoneNoOld("");                              //手机号旧值             
	      bizContent.setAccBankFlagOld("");                             //账号本他行标志旧值     
	      bizContent.setAccnoOld("");                                   //账号旧值               
	      bizContent.setAccNameOld("");                                 //账户名称旧值
	      bizContent.setAccBankNoOld("");                             //开户行号旧值 	      
	      bizContent.setAccBankNameOld("");                             //账户开户行行名旧值   
	      bizContent.setDealNameOld("");                                  //经办人姓名旧值
	      bizContent.setTranTelphoneNoOld("");                          //经办人手机号旧值       
	      bizContent.setSociCreCodeOld("");                             //统一社会信用代码旧值      
	      bizContent.setSingleUnauthAmountOld("");                      //单笔免验证码额度旧值   
	      bizContent.setMemberNameNew("bbb");                            //会员名称新值           
	      bizContent.setMemberTypeNew("");                              //会员类型新值           
	      bizContent.setCorpRepreNameNew("");                            //法人代表姓名新值             
	      bizContent.setIdCardNoNew("");                                //身份证号码新值   
	      bizContent.setIdCardSignDateNew("");                          //身份证签发日期新值 	      
	      bizContent.setTelphoneNoNew("");                              //手机号新值             
	      bizContent.setAccBankFlagNew("");                             //账号本他行标志新值     
	      bizContent.setAccnoNew("");                                   //账号新值               
	      bizContent.setAccNameNew("");                                 //账户名称新值   
	      bizContent.setAccBankNoNew("");                             //开户行号新值    	      
	      bizContent.setAccBankNameNew("");                             //账户开户行行名新值     
	      bizContent.setDealNameNew("");                                   //经办人姓名新值         
	      bizContent.setTranTelphoneNoNew("");                          //经办人手机号新值    
	      bizContent.setSociCreCodeNew("");                           //统一社会信用代码新值	      
	      bizContent.setSingleUnauthAmountNew("");                      //单笔免验证码额度新值   
	      bizContent.setConfFlagOld("1");                      //认证标志旧值 
	      bizContent.setConfFlagOld("2");                      //认证标志新值
	      bizContent.setPickCashDaysOld("12");                      //货款自动提现天数旧值  
	      bizContent.setPickCashDaysNew("13");                      //货款自动提现天数新值 
	      bizContent.setPayModeOld("1"); //入账方式旧值
	      bizContent.setPayModeNew("0"); //入账方式新值
       	 request.setBizContent(bizContent);

		MybankAccountB2bMemberagreementmaintainV1Response response = new MybankAccountB2bMemberagreementmaintainV1Response();
		try {
			response = client.execute(request, String.valueOf(System.currentTimeMillis()));//msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一
			if (response.isSuccess()) {
				//6、业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
				System.out.println("ReturnCode:"+response.getReturnCode());
				System.out.println("response:" + response);
			}else {
				System.out.println("returnCode:"+response.getReturnCode());
				System.out.println("returnMsg:"+response.getReturnMsg());		
			}
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}
	}
}

