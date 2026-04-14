package com.icbc.api;


import java.util.logging.Logger;


import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.internal.util.internal.util.fastjson.JSONArray
;
import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.CardbusinessAggregatepayB2cOnlineConsumepurchaseRequestV2;
import com.icbc.api.request.CardbusinessAggregatepayB2cOnlineConsumepurchaseRequestV2.CardbusinessAggregatepayB2cOnlineConsumepurchaseRequestV2Biz;
import com.icbc.api.response.CardbusinessAggregatepayB2cOnlineConsumepurchaseResponseV2;

public class CardbusinessAggregatepayB2cOnlineConsumepurchaseV2Test {
	// 1、网关公钥
	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
	// 2、密钥对认证方式，公钥在API平台登记，此处为私钥
	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCSZDKeAVMbPPgQVte7JuNIsbuhAcmPNdj4WYhvRfYZWPDkfT2RvyYnAeBrxAkYCrVmZefPaGjzvvWi4xJhPlJYXtnI0QROH8bejGV6qj8u/3R1CDAIZhwcW4lH998eNa6TvwDxb61eQlCUhph16AmAb/tmeD5yyDzqhY2noL4Gmhc1kZs+Y7e+3veOwew1bgtNqXwpOYHyMhsXKvdhavP/FNdWVc4xzcJQAm1LhDdD093bdPj869O03gov2pYNGIr5qtWYY4Ar/W7zjzKnoORKAXPtxsxaLYwzy5hh19d6D+/E3NlYgvPbsQ1GWAKm41EkFX4TAv4+gaqxNQSCk7GnAgMBAAECggEBAIpjUj5wPiInttGrZvEzR/U2n4sLZ4GMg/nymbmnR4iavc8iXgvjyatiaMc3YOlrZiTQ/RzjNSQHR6qeQofPJIUJ1ZOzlABOpe9Ihb46JtTFBVCFVvL5pGViWxq/xhqG4OwmyjtzOU88HXTq8EG2AxV5xw0NauuTQ5LoggsPN9SAaYmgeAoOSqFoqo6GR6nqnPaj2Vtvt9kl8rqhAL08/KQ6WJ3xGci2CMX9jrzA2NkBe3gf2fnJCdTDTc6crQBATDFRTWRkclnbIBElhu5Wtyxom9EndCvjIlNej756fv2dHqDoL0adcrtT3TKhBXtwDxhALOrgXayiMqByoF/lVAECgYEA93iQYmnL/KTSrvmG6FkK6ZaqNlgxyEC8TW8cAkQN1s5RDMHFhl/KIjWXCGDdT3JVj06evaNj0XT6awBmLjNvQ1uLMlN2qyx9d1VfH2dGHxRRcdHPEvx/v8jbYepjqo9qak+l66Y0SJtXj0BsotyzpqwPvqh8BE8f2kPxYfygEKcCgYEAl2/PE4PzBclZ3BPPTHxfZqIPgwpnb5v/t31RLlwbTGg2puLVwCGdmyUDSYDDuXTw+HjOpzUOpHOmwO/LKi/vIH0AtdC7L5jgk3aHk4EF+1Grxqp2Qo3nQjJskVoZMFiEwtPixgASBU2YOT8XGX4dvRhVl3AXjl9Yj7LvOJDadwECgYB8tTL9hRcYz+6j4ncLq6OGJzjLfhJIOecqlm1k7uqSbxIE/bkOiEMSjjL4M+gS2ykmuJXiNu5cZTi0p1TWv3C4Xu98gvbwN9FaS5lN9/ssOk74rqRNixTNjuiPJVDoaLFVtaGFaRI77JxpQiv8vn+B5H9iswEcIsuIcGi52lbHTQKBgDKb7SpqeHdAmUVRYYAAOKnGE0UJ3xOPoOAjg1Po+D6KvWj8ec7h7RgCQJz56W87Q6TmSrnao3AWLm5gNRd+OX40CF66yTPY9YSSojCLeAJLgwq7wRXw8o5O57WT1MMxcfzibvEY6OzYx7yp9wBQA4REc6ZOkbDE8IXlmMjgsAIBAoGBAN0bgHEIP0n4M7APvITIgNpfOeSq+HJW4sBNoJkpmSxQJaI29gRuRqrkvWetW658+o3PvJW2FBPpIihjzUCqRhg7t9z9Wj+h8GzAsj3CzXwNVLVJ36VJOz5vx4o9exjNffCYWxot+ctZXWFbEuRkr6cu422ZfB4cXXg88eoWu9s+";
	// 3、appid
	protected static final String APP_ID = "***";
	
	private static Logger logger = Logger.getLogger(CardbusinessAggregatepayB2cOnlineConsumepurchaseV2Test.class.getName());
	
	
	public static void main(String[] args) throws IcbcApiException {
		
		//4、签名类型为RSA时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA，其他参数使用缺省值
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,IcbcConstants.SIGN_TYPE_RSA,MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
		CardbusinessAggregatepayB2cOnlineConsumepurchaseRequestV2 request = new CardbusinessAggregatepayB2cOnlineConsumepurchaseRequestV2();
		//5、根据测试环境和生产环境替换相应ip和端口
		request.setServiceUrl("http://****:***/api/cardbusiness/aggregatepay/b2c/online/consumepurchase/V2");
		CardbusinessAggregatepayB2cOnlineConsumepurchaseRequestV2Biz bizContent = new CardbusinessAggregatepayB2cOnlineConsumepurchaseRequestV2Biz();
		request.setBizContent(bizContent);
		//6、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
		bizContent.setMer_id("***");
		bizContent.setOut_trade_no("65964126858");
		bizContent.setPay_mode("9");
		bizContent.setAccess_type("5");
		bizContent.setMer_prtcl_no("965412357");
		bizContent.setOrig_date_time("2019-07-09T12:11:03");
		bizContent.setDecive_info("***");
		bizContent.setBody("喜士多");
		bizContent.setFee_type("001");
		bizContent.setSpbill_create_ip("***");
		bizContent.setTotal_fee("100");
		bizContent.setMer_url("***");
		bizContent.setShop_appid("wx8888888888888888");
		bizContent.setIcbc_appid("***");
    	bizContent.setOpen_id("");
		bizContent.setUnion_id("");
		bizContent.setMer_acct("*******");
		bizContent.setExpire_time("120");
		bizContent.setAttach("{ \"id\": \"SZTX001\", \"name\": \"腾大餐厅\", \"area_code\": \"440305\", \"address\": \"科技园中一路腾讯大厦\" }");
		bizContent.setNotify_type("HS");
		bizContent.setResult_type("0");
		bizContent.setPay_limit("no_credit");
    	bizContent.setOrder_apd_inf("");
		bizContent.setDetail("xxx");
		bizContent.setReturn_url("xxx");
		bizContent.setQuit_url("xxx");
		bizContent.setCust_name("xxx");
		bizContent.setCust_cert_type("xxx");
		bizContent.setCust_cert_no("xxx");
		bizContent.setGoods_tag("xxx");
		bizContent.setAcq_addn_data("xxx");

		bizContent.setSer_id("xxx")
		JSONObject orderSubmitActInput = JSON.parseObject("{\"rec_num\":\"1\",\"oper_flag\":\"0\",\"sub_order_no\":\"55555\",\"seq_no\":\"020001030604000521912200000001\",\"busi_type\":\"2\",\"sub_mer_id\":\"***\",\"sub_mer_prtcl_no\":\"***\",\"classify_amt\":\"1\",\"mer_sp_infor\":\"商户自定义信息\",\"term_id\":\"1\",\"ori_mer_id\":\"***\",\"ori_trx_date\":\"2020-01-02\",\"mer_acct\":\"***\"}");
		JSONArray orderSubmitActInputArray = new JSONArray();
		orderSubmitActInputArray.add(orderSubmitActInput);
		bizContent.setIs_suborder("1");
		bizContent.setOrder_num("1");
		bizContent.setOrder_submit_act_input(orderSubmitActInputArray);
		
		CardbusinessAggregatepayB2cOnlineConsumepurchaseResponseV2 response;
		try {
			boolean testFlag = true;
			response = client.execute(request, System.currentTimeMillis()+"");//msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一
			if (response.getReturnCode() == 0) {
				// 6、业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
				logger.info("ReturnCode:"+response.getReturnCode());
				logger.info("response:" + JSON.toJSONString(response));
			} else {
				// 失败
				testFlag = false;
				logger.info("ReturnCode:"+response.getReturnCode());
				logger.info("response:" + JSON.toJSONString(response));
				logger.info("ReturnMsg:"+response.getReturnMsg());
			}	
			
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}
	}
}
