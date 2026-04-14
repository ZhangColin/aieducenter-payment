package com.icbc.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.IicMergersAssetsprojectaccessUpdateRequestV1.IicMergersAssetsprojectaccessUpdateRequestV1Biz;
import com.icbc.api.request.IicMergersDomesticprojectUpdateRequestV1;
import com.icbc.api.request.IicMergersDomesticprojectUpdateRequestV1.IicMergersDomesticprojectUpdateRequestV1Biz;
import com.icbc.api.response.IicMergersDomesticprojectUpdateResponseV1;

/**
 * 信用卡账单信息查询接口
 * 
 * 请商户配置SDK中的lib文件夹中的jar包后，根据备注1-6提示进行数据替换
 */
public class IicMergersAssetsprojectaccessUpdateTestV1 {
	
	//1、网关公钥
	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
	
	//2、appid
	protected static final String APP_ID = "10000000000004096980";

	//3、密钥对认证方式，公钥在API平台登记，此处为私钥
	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQC6eAQfWrf74g/hUS5if885seE1k29HpSrn8xFJYcBhpU6mms3q5ajKRsdkmAboY15MZh2h8H1Bw1aO+P2VJEECQt3nSalPLNEz+rzGuTASriQXloWavtb9E1zfBSlyXmGjjwJ9fB1byAeSyeQT5QfQXA2HiiazH0NeDhBpvBh4RvSavZa2GyJok2QLurKwLd7iFr1lOLX0kfkWAp1Tp+swnXXbJjiLztDvjIlJsh03FIA/J/crXymgd+sCXH8pwZJ8eqQKlD+8bF+Oy/HFufI0J+V8SQB1hVBf/3L48cR93iGAIU9kF87qcdrH8be0ee+WV/qboyO/HCysV/xGVcE7AgMBAAECggEBALKfIvvSECpmO+YPuQo7+or+/Ygv6kpMStSUP/v2eq5PBw+LfTPFubHCqWCQTRWxknRG4Z3t3F7xuuoMVAXZC7kZyq2zH0+r098iElGethE+5W3/D47TvugA81bj//cBuDBQXwdLrGfuLmsLoML6A0kx1PgAe9/cU9E2UbynpR0Y4MGMp2VCc5ve62j6igIbeII1AuwR6/cTycxmy6j4abBaSCX2ejKU6Xe9qjJcrzQcYevb9WJlXgWT7/UIFmBj1JEmHulcmum1YZLzU0KnKZHZwl7hKiFu1N/yNVNWdSc9sHT4iQB8+Y2j0bRNVXyn5DVQ89PkCk5tnlCYE/HITrkCgYEA25Kiqgm2utpwlyNC71yZldMClppyP9wjovjqKyEQzL+4XT/r6INMxLqjNlrhe7gY0UX3a3HKOdRdOdu1WsrQDTSsSgxgoJVM45dXzRJ2VTrBXRqlhCaUh8s7nrwhBNwpgalyZzztjblcxw+nrx3SsaVac33yNqeH/huodFkjKRcCgYEA2WdwNTBwppJ6BO7vd34+Ry82tdhOVmHlyKEwcIjqvmGch5lSJpk3YQKM0s7yjT4Q+TmaXiI7NhQNln2QE45hLdUMy2o4aoLiawe5eSOnlWKoyr2o+nPbZOuw5hiY6dJf7PnAl6Ubn5P2mzhyLzSMURsttuuueolnFiq29WEXd30CgYBZQc2QE6zNXljgh3wBM4JXjlSCBjylSwbNjCQc7i8XHGSEu6RZe25slnYcdWst0NN91tRSQAp14acYCwE47DMQ+zWCUG/DN43vAqm1emjSErVh9KAODWTZorsUtuMxR5Iz3D7eSr1CnHRjkzCWFih9AHu5cRxSLV4x5OpgiY+qdwKBgDikdZy2ETOlCDuO0T9t2eYMDBIW2FozSX4bWRaI5bhSkEjYDopV8xLIak5mctWP7TYx5uFNw+WUFfI2prohp3ocfyfkdexyS3GdYpqISppmSRoBjiwq0Wea7M0WbYjGMg35CE+6Sdcq4t6QN1fLNh/Pl/qHrz7n277vxBlsgYO1AoGBANPQSg74ZYGk3cherupU9LF/NNexqTodJkAGR3XqVg0gCjwKxBgpp/SYFubze5VVt8ugHbJ6oon6PjE6/OQX/Syun+BdtYew33eMwrFNFQ8F+FToo1rQVfRwjJO0iBMzXKi3ZqVPYSZ8pSiHCM0N5gc8hI5QWrqNlwMp17GxvuRA";
	public static void main(String[] args) throws Exception {

		//签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
		
		IicMergersDomesticprojectUpdateRequestV1 request = new IicMergersDomesticprojectUpdateRequestV1();
		
		//4、根据测试环境和生产环境替换相应ip和端口
		request.setServiceUrl("http://ip:port/api/iic/mergers/assetsprojectaccess/update/V1");

		//5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
		IicMergersAssetsprojectaccessUpdateRequestV1Biz bizContent = new IicMergersAssetsprojectaccessUpdateRequestV1Biz();
		
		//项目必要部分
		Map<String,Object> necessary = new HashMap<>();
		necessary.put("iduFlag", "I");
		necessary.put("source", "S367");
		necessary.put("projectId", "cttest2020022701");
		necessary.put("projectName", "陈涛测试2020022701");
		necessary.put("projectType", "1");
		necessary.put("projectStatus", "A01001");
		necessary.put("recordType", "A01001");
		necessary.put("recordTypeName", "A01001");
		necessary.put("sellerId", "seller01");
		necessary.put("sellerName", "转让方01");
		necessary.put("sellerType", "A11001");
		necessary.put("currencyCode", "EUR");
		necessary.put("sellPrice", "50000.00");
		necessary.put("industryCode", "A01");
		necessary.put("zoneCode", "ZJ");
		necessary.put("pubType", "1");
		necessary.put("createDate", "2020/2/27 9:07:56");
		necessary.put("publishDate", "2020/2/27 9:07:56");
		necessary.put("expireDate", "2020/4/27 9:07:56");
		necessary.put("equityPercent", "21.0000");
		necessary.put("aaa", 1);
		bizContent.setNecessary(necessary);
		
		//模块1
		List<Map<String,Object>> addition = new ArrayList<>();
		Map<String,Object> module1 = new HashMap<>();
		module1.put("moduleBigType", "M1");
		module1.put("sellerId", "seller01");
		module1.put("projectId", "cttest2020022701");
		module1.put("sellerName", "转让方01");
		module1.put("sellerType", "A11001");
		module1.put("sellerTypeName", "法人或其他经济组织");
		module1.put("sellerStockPercent", "seller01");
		module1.put("sellerStockMount", "seller01");
		module1.put("sellPercent", "21.0000");
		module1.put("economyType", "A05001");
		module1.put("economyTypeName", "国资监管机构/政府部门");
		module1.put("economyNature", "A19001");
		module1.put("economyNatureName", "全民所有制企业");
		module1.put("sellerCode", "AE86");
		module1.put("zone", "ZJ");
		module1.put("zoneCn", "浙江");
		addition.add(module1);
		
		//模块2
		Map<String,Object> module2 = new HashMap<>();
		module2.put("moduleBigType", "M2");
		module2.put("objectId", "object01");
		module2.put("projectId", "cttest2020022701");
		module2.put("objectName", "转让标的01");
		module2.put("objectCode", "AE86");
		module2.put("managerScale", "A04001");
		module2.put("managerScaleName", "大型");
		module2.put("zone", "ZJ");
		module2.put("zoneCn", "浙江");
		module2.put("registeredPlace", "浙江");
		module2.put("legalRepresentative", "马云");
		module2.put("inauguralDate", "2020-02-28");
		module2.put("industryCode", "A01");
		module2.put("economyType", "A0101");
		module2.put("economyTypeName", "国有相对控股");
		module2.put("economyNature", "A19001");
		module2.put("economyNatureName", "全民所有制企业");
		module2.put("employeeQuantity", "1000000.000000");
		module2.put("businessScope", "全民所有制企业");
		module2.put("registeredCapital", "全民所有制企业");
		module2.put("sellPercent", "15.000000");
		module2.put("sellAmount", "1000");
		module2.put("auditYear", "2019");
		module2.put("auditUnit", "浙江分行");
		module2.put("auditAsset", "5000000.000000");
		module2.put("auditEquity", "2000000.000000");
		module2.put("auditDebt", "1000000.000000");
		module2.put("auditEarning", "500000.000000");
		module2.put("auditProfit", "250000.000000");
		module2.put("auditNetProfit", "100000.000000");
		module2.put("stmtDate", "2020-02-28");
		module2.put("stmtType", "月报");
		module2.put("stmtAsset", "5000000.000000");
		module2.put("stmtEquity", "2000000.000000");
		module2.put("stmtDebt", "1000000.000000");
		module2.put("stmtEarning", "500000.000000");
		module2.put("stmtProfit", "250000.000000");
		module2.put("stmtNetProfit", "100000.000000");
		module2.put("evaluateUnit", "评估机构A");
		module2.put("approveUnit", "评估核准单位A");
		module2.put("approveDate", "2020-2-28");
		module2.put("evaluateDate", "2020-2-28");
		module2.put("evaluateDateAproOrg", "基准审计机构A");
		module2.put("evaluateAsset", "100000.000000");
		module2.put("evaluateEquity", "100000.000000");
		module2.put("evaluateDebt", "100000.000000");
		module2.put("bookAsset", "100000.000000");
		module2.put("bookEquity", "100000.000000");
		module2.put("bookDebt", "100000.000000");
		module2.put("objectEvaluateValue", "100000.000000");
		module2.put("hasContainGround", "T");
		addition.add(module2);
		
		bizContent.setAddition(addition);
		
		request.setBizContent(bizContent);
		IicMergersDomesticprojectUpdateResponseV1 response;
		try {
			response = client.execute(request, "00097");//msgId消息通讯唯一编号，要求每次调用独立生成，APP级唯一
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


