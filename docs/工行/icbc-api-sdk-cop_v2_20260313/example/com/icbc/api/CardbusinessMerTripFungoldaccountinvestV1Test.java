package com.icbc.api;

import java.math.BigDecimal;

import com.icbc.api.request.CardbusinessMerTripFungoldaccountinvestRequestV1;
import com.icbc.api.request.CardbusinessMerTripFungoldaccountinvestRequestV1.CardbusinessMerTripFungoldaccountinvestRequestV1Biz;
import com.icbc.api.response.CardbusinessMerTripFungoldaccountinvestResponseV1;


public class CardbusinessMerTripFungoldaccountinvestV1Test {

    //1、网关公钥
	protected static final String APIGW_PUBLIC_KEY = "****";

	//2、appid
	protected static final String APP_ID = "****";

	//3、密钥对认证方式，公钥在API平台登记，此处为私钥
	protected static final String MY_PRIVATE_KEY = "**";
	
	public static void main(String[] args) throws Exception {

       //签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

		CardbusinessMerTripFungoldaccountinvestRequestV1 request = new CardbusinessMerTripFungoldaccountinvestRequestV1();
		//4、根据测试环境和生产环境替换相应ip和端口
		request.setServiceUrl("https://ip:port/api/cardbusiness/mer/trip/fungoldaccountinvest/V1");
		//5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
		CardbusinessMerTripFungoldaccountinvestRequestV1Biz biz = new CardbusinessMerTripFungoldaccountinvestRequestV1Biz();
		request.setBizContent(biz);
		biz.setCorpId("*");           
	    biz.setFunaccountId("*");      
			biz.setFunaccountSubaccno("*");
			biz.setFunaccountAmt(new BigDecimal("1.00"));     
			biz.setTicketNo("*");          
			biz.setTicketType(0);        
			biz.setSalesChannel("2");      
			biz.setTotalAmt(new BigDecimal("1.00"));          
			biz.setPayAmt(new BigDecimal("1.00"));            
			biz.setPayWay(2);            
			biz.setUserId("*");            
			biz.setPosTransInfo("");
		CardbusinessMerTripFungoldaccountinvestResponseV1 response;
		try {
			response = client.execute(request, "124");//msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一
			
			if (response.isSuccess()) {
				// 6、业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
				//System.out.println("ReturnCode:"+response.getReturnCode());
				//System.out.println("response:" + response);
				
			} else {
				// 失败
				//System.out.println("ReturnCode:"+response.getReturnCode());
				//System.out.println("ReturnMsg:"+response.getReturnMsg());
			}		
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}
	}


}
