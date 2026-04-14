package com.icbc.api;

import org.junit.Test;

import com.icbc.api.request.MybankAccountB2bShoparchivesaddV1Request;
import com.icbc.api.request.MybankAccountB2bShoparchivesaddV1Request.MybankAccountB2bShoparchivesaddV1RequestBiz;
import com.icbc.api.response.MybankAccountB2bShoparchivesaddV1Response;


/**
 * 合作方会员产品协议录入
 * 
 * 请商户配置SDK中的lib文件夹中的jar包后，根据备注1-6提示进行数据替换
 */
public class MybankAccountB2bShoparchivesaddV1Test {

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
		MybankAccountB2bShoparchivesaddV1Request request = new MybankAccountB2bShoparchivesaddV1Request();
		
		//4、根据测试环境和生产环境替换相应ip和端口
		request.setServiceUrl("http://ip:port/api/mybank/account/b2b/shoparchivesadd/V1");
		
		//5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值，非必输字段可不上送
		MybankAccountB2bShoparchivesaddV1RequestBiz bizContent = new MybankAccountB2bShoparchivesaddV1RequestBiz();
                         
	       bizContent.setTrxDate("2018-08-22");//交易日期         
	       bizContent.setCheckWorkDateFlag("2");//核对交易日期标志 
	       bizContent.setPartnerProno("1801627");//合作方协议编号    
	       bizContent.setPartnerProno("1001");//合作方协议编号
		   bizContent.setMemberNo("1536818307451");//会员编号
		   bizContent.setIcbc_mcc("0602");//我行MCC
	       bizContent.setMere_name("SHANXITAIYUANKERUYUN");//特约商户英文名称
	       bizContent.setSale_dep_name("1111111");//门店名称
	       bizContent.setMer_addr("广东省广州市白云区XX路xx号");//营业地址
	       bizContent.setMer_pdc("100000");//商户地址邮编
	       bizContent.setMt_user_code("000870943");//管户客户经理
	       bizContent.setMt_user_phone("18612344321");//维护联系电话
	       bizContent.setEmail("xxxxxxxx@163.com");//EMAIL地址
	       bizContent.setWx_mcc("551");//微信经营类目
	       bizContent.setZfb_mcc("2015050700000000");//支付宝经营类目
	       bizContent.setSh_fx_dj("3");//商户风险等级
	       bizContent.setMer_gm("2");//商户规模
	       bizContent.setReg_amt("1000000");// 注册资金
	       bizContent.setMer_ser_tel("18622334455");//商户门店联系电  
	       bizContent.setZfb_mer_prov_code("440000");  //支付宝商户所在省份编码
	       bizContent.setZfb_mer_city_code("440100");//支付宝商户所在城市编码
	       bizContent.setZfb_mer_area_code("440104"); //支付宝商户所在区县编码
	       bizContent.setOth_bank_no("");//跨行支付行行号
	       bizContent.setRet_acc_tp("");//退货清算账户类型
	       bizContent.setRet_acc_no("");//退货清算账号
	       bizContent.setZfb_life_code("");//支付宝生活号
	       bizContent.setZfb_source_id("");//支付宝商户来源
	       

		request.setBizContent(bizContent);

		MybankAccountB2bShoparchivesaddV1Response response = new MybankAccountB2bShoparchivesaddV1Response();
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

