package com.icbc.api;

import java.util.List;

import org.junit.Test;

import com.icbc.api.request.MybankAccountB2bMemberagreementqueryV1Request;
import com.icbc.api.request.MybankAccountB2bMemberagreementqueryV1Request.MybankAccountB2bMemberagreementqueryV1RequestBiz;
import com.icbc.api.response.MybankAccountB2bMemberagreementqueryV1Response;
import com.icbc.api.response.MybankAccountB2bMemberagreementqueryV1Response.MybankAccountB2bMemberagreementqueryV1ResponseList;

/**
 * 合作方会员产品协议查询
 * 
 * 请商户配置SDK中的lib文件夹中的jar包后，根据备注1-6提示进行数据替换
 */
public class MybankAccountB2bMemberagreementqueryV1Test {

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
		MybankAccountB2bMemberagreementqueryV1Request request = new MybankAccountB2bMemberagreementqueryV1Request();
		
		//4、根据测试环境和生产环境替换相应ip和端口
		  request.setServiceUrl("http://ip:port/api/mybank/account/b2b/memberagreementquery/V1");
		//5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值，非必输字段可不上送
        MybankAccountB2bMemberagreementqueryV1RequestBiz bizContent = new MybankAccountB2bMemberagreementqueryV1RequestBiz();

                
      	bizContent.setTrxDate("2018-08-23");                      //交易日期                
    	bizContent.setCheckWorkDateFlag("1");                     //核对交易日期标志        
    	bizContent.setQryType("1");                               //查询方式  
    	bizContent.setInitFlag("1");                              //查询标志                
    	bizContent.setRowReq("5");                                //一次查询的最大记录数    
    	bizContent.setPartnerProno("1800128");                       //合作方协议编号          
    	bizContent.setMemberNo("153681830760");                     //会员编号                
    	bizContent.setStatus("");                                 //查询状态集合            
    	bizContent.setBegMemberNo("");                            //翻页会员编号            
		request.setBizContent(bizContent);

		MybankAccountB2bMemberagreementqueryV1Response response = new MybankAccountB2bMemberagreementqueryV1Response();
		try {
			response = client.execute(request, String.valueOf(System.currentTimeMillis()));//msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一
			if (response.isSuccess()) {
				//6、业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
			if (!"0".equals(response.getItems())){
				//记录条数
				response.getItems();
				//查询成功 获取返回结果
				List<MybankAccountB2bMemberagreementqueryV1ResponseList> list = response.getList();
				//获取结果集中字段
				for(MybankAccountB2bMemberagreementqueryV1ResponseList detail:list){
					//获取信息.....
					detail.getAccno();          		
					System.out.println("Accno:" + detail.getAccno());
			}
			} else {
				//失败
				System.out.println("returnCode:"+response.getReturnCode());
				System.out.println("response:" + response);
				System.out.println("items:" + response.getItems());
			}
		}else {
			System.out.println("returnCode:"+response.getReturnCode());
			System.out.println("returnMsg:"+response.getReturnMsg());		
		}		
	}catch (IcbcApiException e) {
	e.printStackTrace();
}
}
}

