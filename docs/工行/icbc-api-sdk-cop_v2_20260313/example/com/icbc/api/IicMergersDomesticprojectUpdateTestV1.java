package com.icbc.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.IicMergersDomesticprojectUpdateRequestV1;
import com.icbc.api.request.IicMergersDomesticprojectUpdateRequestV1.IicMergersDomesticprojectUpdateRequestV1Biz;
import com.icbc.api.response.IicMergersDomesticprojectUpdateResponseV1;

/**
 * 信用卡账单信息查询接口
 * 
 * 请商户配置SDK中的lib文件夹中的jar包后，根据备注1-6提示进行数据替换
 */
public class IicMergersDomesticprojectUpdateTestV1 {
	
	//1、网关公钥
	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

	//2、appid
	protected static final String APP_ID = "10000000000007534565";

	//3、密钥对认证方式，公钥在API平台登记，此处为私钥
	//protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----BIjANBgkqhkiG9w0BAQEFAAOCAQ8A-----请使用正确的APP私钥-----BCgKCAQEArzKak34V2DeaMAspL/aHBprk3s5FLqOhbNL/JRdr4rB1ws2Z+/XXkd4kep0QO11QOxMI0oHJqAdD0Z3vZlWNmh2UX0yS8j92COrjiuoz4zqT3x2fCz2xyPKWvHcf35hbETmth9Lw2EpGs8hvCayBRI+CXZUBkvEkc7ACeFKx1DAtDLEVNzbetJLyDSq320nUbeqHPqUp3zqT3i9K8DsjCTCMVjZwgUkTGD5IfGfSEq+ZZcMKXz7YzBFn5S2lzautVB0Wz0wkde1fUIg79TqQ/wR4n0CC5E2qTf8+g4+3JLu5Dmxt760v5haA4vHZ68JAtjeELuz7zLTFBOkyvCcUKQIDAQAB";
	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----CdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAMcOT6qHqmQpM8yQULdn14XyvHHGtqobq6VGJcliNhHE10YblLUu+WTGyQM+aCWVL3dwct/JfbYC6ikmTbC2Z688IHZRIN0aszlU/bmig5G1zYZh4Bw6KaGPzcktsaRRyB7PqC1qtCpn6gveA7PXOfoJthcrVvnod1wjdioYrd+3AgMBAAECgYBR5RZrr4Mv0C/Kgrliq6tlJZfYK4wtclLtHW4ga3KZXuW+p0xIOrpKdupuljdc9wLfXfk1Dv3Ua2CRa3vdLz56hF5PlYYfX5fIJku6BzFAtE7KC5mKaDNKSdPvi9JIhYGn1AiVQy6t5RoQloR7y85+0kPVVTyQZZ8gFR4FZmZCaQJBAPjPb7Irfk2rpmA3/rnRE529mt6YJFYFpL/C1Kobzrl4LaDCtkJOniYNIyXHPrDfwsqhsrAy7Mlrx83PFKe5uv0CQQDMztGfzmfoqDa8Qyilp2nYrogXoIOOv/8Uqrck12P8Ye5WeZiBMv+bojxtVVvTQ2nfkX7pu9CQSmNoO38q+4XDAkEAicRSVJVAmkExHpXitYDPG8iguCAF/puhXYzDtG0xSgEBQsKNqKlfaDJeA80PKWC8uvZNusJ32g+T0qGXDkgJAQJAHVz5iOPa2exkDJHEXhumfT5onJqSwgCc3aWsJEgNOb4Aob1FNCDPrMyRxUsboypjH36Ip5IZ0NYZcnQ1Q0BUaQJBALcduMqq/wG/ns4i1+jcWcxtifykcmySnV34KbI2QVTORKol0UUMgDfGZY0XEbGFeX+RalaK/DiT0w0Wbj+GXpk=";
	public static void main(String[] args) throws Exception {

		//签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
		
		IicMergersDomesticprojectUpdateRequestV1 request = new IicMergersDomesticprojectUpdateRequestV1();
		
		//4、根据测试环境和生产环境替换相应ip和端口
		request.setServiceUrl("http://ip:port/api/iic/mergers/domesticproject/update/V1");

		//5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
		IicMergersDomesticprojectUpdateRequestV1Biz bizContent = new IicMergersDomesticprojectUpdateRequestV1Biz();
		List<Object> financialsList = new ArrayList<Object>() ;
		Map<String, String> financialsMap = new HashMap<String, String>();
		financialsMap.put("year","5"); 
		financialsMap.put("equity_cn","6"); 
		financialsMap.put("equity_range_china","7"); 
		financialsMap.put("profit_definition_china","8"); 
		financialsMap.put("profit_cn","9"); 
		financialsMap.put("profit_range_china","0"); 
		financialsMap.put("total_asset_cn","1"); 
		financialsMap.put("total_assetRangeChina","2"); 
		financialsMap.put("totalAssetEquity_cn","3"); 
		financialsMap.put("totalAssetEquityRangeChina","4"); 
		financialsMap.put("audited_cn","5"); 
		financialsMap.put("auditingMethod" ,"6");
		financialsList.add(financialsMap);
		Map<String, Object> necessary = new HashMap<String, Object>();
		necessary.put("serial","0001");
		necessary.put("summary", "2");
		necessary.put("caption", "3");
		necessary.put("captionTocn", "4");
		necessary.put("listedTime", "5");
		necessary.put("createTime","6");
		necessary.put("expire", "7");
		necessary.put("struct_cn", "8");
		necessary.put("area_cn", "9");
		necessary.put("industry_cn", "0");
		necessary.put("industry_gics_four","10102020");
		necessary.put("salePrice_cn", "1");
		necessary.put("jiage02","2"); 
		necessary.put("jiage01","3");
		necessary.put("priceType","4"); 
		necessary.put("saleModel","5");
		necessary.put("saleModelChina", "6");
		necessary.put("term_cn", "7");
		necessary.put("caiwu_cn", "8");
		necessary.put("strengthDetail", "9");
		necessary.put("oppotunityDetail", "0");
		necessary.put("dificultiesDetail", "1");
		necessary.put("investerType_cn", "战略投资者");
		necessary.put("saleProperty","2"); 
		necessary.put("areaShen","3"); 
		necessary.put("areaShenChina","4");
		necessary.put("areaZhou","2287");
		necessary.put("areaZhouChina","欧洲");
		necessary.put("state","5");
		necessary.put("isOverdue","");
		necessary.put("financialsList", financialsList);
		//bizList.add(necessary);
		String Str_necessary = JSON.toJSONString(necessary);
		bizContent.setNecessary(necessary);
		Map<String, Object> addition = new HashMap<String, Object>();
		addition.put("rgemail","abc");
		addition.put("search","7");
		List<Object> bizList1 = new ArrayList<Object>() ;
		bizList1.add(addition);
		String Str_addition = JSON.toJSONString(addition);
		System.out.println("test++++++++1:" + Str_addition);
		System.out.println("test++++++++2:" + Str_necessary);
		bizContent.setAddition(addition);
		System.out.println("test++++++++3:" + bizContent.getNecessary());
		//bizList.add(bizContent);
		request.setBizContent(bizContent);
		IicMergersDomesticprojectUpdateResponseV1 response;
		try {
			response = client.execute(request, "00023");//msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一
			if (response.isSuccess()) {
				//6、业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
				System.out.println("ReturnCode:"+response.getReturnCode());
				System.out.println("response:" + response);
			} else {
				//失败
				System.out.println("ReturnCode:"+response.getReturnCode());
				System.out.println("ReturnMsg:"+response.getReturnMsg());
			}		
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}
	}

	
}


