package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSONArray;
import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.MybankAccountTrucketcCreditproofsubmitRequestV1;
import com.icbc.api.request.MybankAccountTrucketcCreditproofsubmitRequestV1.MybankAccountTrucketcCreditproofsubmitRequestV1Biz;
import com.icbc.api.response.MybankAccountTrucketcCreditproofsubmitResponseV1;

public class MybankAccountTrucketcCreditproofsubmitV1Test {
	public static void main(String[] args) {

		String APP_ID = "appid";
		String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
		String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCH+uktjoK4ERw847PN86k5wzeMIlHdjnJUVJbDvCY38JXDhKKUJmqxk4xBQJM9OFBXM+NI3+0axLUuSf9O9zEAEh4Oiotb8oy0f50ha8G6AfrynEQf/vcYdLRf2al982ZDY7rggTL6y4EqgCMOt4UxscY1pnDtNkQDzurrqitK4OsKrkLQ5hwZptf0eh86lyxzOBcv4SXjgH4xnkiz3Lipwlb7Ga9B5trLB3T7TEk+5BcXKHJozH7XM6n6P8wTexBcZOfPxnwASZhn0Ekmma4JGWzBvRdDSkCFHHncVxDRTtW8N5uGsEmbpKZS+LpRi+VdciDsyaMsyk2ZxCV99WrPAgMBAAECggEAUiuyisAKiBZ23Oiwd+0dPvWT9I+srtjsh79SF9fztGwi5DKbWDFa2hISeEAAecc07W1ClIAUtM9XuTAoItzHdIZ+OUVJb3fDxTM0jcSjHbcioUcC997IDLRBZ4aNoLnb+y8h95a7ashdNoSWKseFqoEGCNeaeA4Jzc9VMLH1VVpppsFOaYyf3heqzM2Xcd4r13+Jdc5SuO2R8LnzuLSV2YfoKBpchp4wTqo/UfbjtnFNK1tL/jW74A4ogex9s1aD3MVmP4TH3bHRYQalraajDQPJWOf8c9cddnEr3iKtu/aROkr+P0HvENskWG7kr22vThuFMsDGrTmSWsFLcl51sQKBgQDz+bDN3fBBCHaF4nN1fdgciAKFey8bFOMN9nNoiWy8PTY/6bodVX2sMFa+nl6F4krvwkRJe8oYGemg/T4JNKSP2JzG4mM7xS9BqJ3zedz6qQq93PL2Sz1nYg1ytQeDXrPwhey8cnJWgwFgpWOm5jQWRPTcJMtqVULQlyTdM2BPJwKBgQCOrpzCKGswvrI5mBVpor/+saZja5ZXAuvKkn+6TQtoGMKpnDhVwVePrW0hM4L1PKv0M8xX+aK5i+9lxI/W3yWTpZZs8+jrs9YDIk4ODMrdlqKh6MGjl15b98buYyv5guSNngCBFq8sdeUTdE1IvxwP/0RvD77P9gGStvscY5LQGQKBgBi9u3ZFCXYu24YcPsUta9sI1+idY1uFtLjXw0BYn1Q/iN7WXu9WmhX6GPHfsYo2No81ziL9kLL8/bo7tCzOz9leiBeLIbEQAAjShqIIO6aXueUjIOhlHOb9BIwbW5Ww5uJ/UlCR8pJfWqZoX0Cvm5yehY5FlPNFji8FBNIBwcSFAoGAbIMbsQXwT4gYH3ksf8RI5DjTZDVEhzQvg0ZbYaKKlEaQdVHb/kwmfZzVrDRy2nOkCvsB4751JUvpa6pMAox/FYUPnBj8AZYpowSJ/GF6P3/6e8x6yCGsn6EXZ+GAZihIDfQmSgaoftXCvELtK/A8NWZb82M0KRDdLWmAHJ0V4LECgYBh9udjQc7H1MySJDHbOP9BcsALys++mik3TvQiIQPwZBSVEr7gcIrfsCepR5V1RO1hU76ZvdD62Fdt5CXKMJCVRT96UKi55/7MeZmgje5o1IqUUOv5bn2FcRDfmDueTkYreeD7jO2XuI8GosOD6UUR9rGwol9bYlew2MJLcxUjgg==";

		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, APIGW_PUBLIC_KEY); // 加载APPID，合作方签名私钥，API网关公钥

		MybankAccountTrucketcCreditproofsubmitRequestV1 request = new MybankAccountTrucketcCreditproofsubmitRequestV1();
		MybankAccountTrucketcCreditproofsubmitRequestV1Biz bizContent = new MybankAccountTrucketcCreditproofsubmitRequestV1Biz();

		// request.setServiceUrl("http://ip:port/api/mybank/account/trucketc/creditproofsubmit/V1"); // 内网
		 request.setServiceUrl("http://gw.dccnet.com.cn:8086/api/mybank/account/trucketc/creditproofsubmit/V1"); //外网

		bizContent.setMsgId(String.valueOf(System.currentTimeMillis()));
		bizContent.setRealName("张三");
		bizContent.setIdcard("431fdab");

//		bizContent.setAgrPlyNo("202004290001"); // 承保确认函编号
//		bizContent.setGrantingId("202004280001");
		
		bizContent.setAgrPlyNo(String.valueOf(System.currentTimeMillis())); // 承保确认函编号
		bizContent.setGrantingId(String.valueOf(System.currentTimeMillis()));
		bizContent.setInsurCode("ABCD"); // 保险公司代码
		bizContent.setInsurName("保险公司名称132"); // 保险公司名称
		bizContent.setInsurGdsCode("A123");  // 保险产品代码
		bizContent.setInsurGdsName("货车ETC保险33"); // 保险产品名称
		bizContent.setInsuScheCode("2"); // 保险产品额度代码
		bizContent.setInsuScheName("保险产品额度名称-2万"); // 保险产品额度名称
		bizContent.setBillType("01"); // 单据类型
		
		bizContent.setAppntName("投保人名称1ds"); // 投保人名称
		bizContent.setAppntIdCertType("101"); // 投保人证件类型 101-身份证（含临时身份证）
		bizContent.setAppntIdCertNo("32143aVc"); // 投保人证件号码
		bizContent.setAppntSex("男"); // 投保人性别
		bizContent.setAppntDegree("本科"); // 投保人学历
		bizContent.setAppntMrg("未婚"); // 投保人婚姻状态
		bizContent.setAppntPhoneNo("15121976265"); // 投保人手机号
		bizContent.setAppntEmail("15121976265@qq.com"); // 投保人邮箱
		bizContent.setAppntAdress("投保人居住地址1"); // 投保人居住地址
		bizContent.setAppntWorkPlace("投保人工作单位1"); // 投保人工作单位
		bizContent.setAppntDpt("投保人部门及职务1"); // 投保人部门及职务
		bizContent.setInsudIdCode("ICBC"); // 被保险人（银行代码）
		bizContent.setInsudName("工行"); // 被保险人名称（银行名称）
		bizContent.setInsuItem("货车ETC"); // 保险标的
		bizContent.setPlyAgreeCtnt("投保协议内容"); // 投保协议内容
		bizContent.setInsuBgnTm("2020-03-26 10:00:00"); // 承保确认函有效起期
		bizContent.setInsuEndTm("2021-02-28 23:59:59"); // 承保确认函有效止期
		bizContent.setIssueTm("2020-03-26 09:00:00"); // 签单时间
		bizContent.setInsuLossTm("2020-04-26"); // 承保确认函失效日期
		bizContent.setAmnt(50000L); // 保险金额
		bizContent.setOpenId("432143afdsercs"); // 部发行认证及监管系统用户编号

		JSONArray vehicleInfo = new JSONArray();
		JSONObject vehicleObj = new JSONObject();
		vehicleObj.put("idcard", "4321431afdsc"); // 身份证号
		vehicleObj.put("vehicleId", "ac40ce97fe164d7ab5c666056fef3c94"); // 车辆编号
		vehicleObj.put("name", "张司机"); // 姓名
		vehicleObj.put("plateNum", "粤A12345"); // 车牌号
		vehicleObj.put("plateColor", 1); // 车牌颜色
		vehicleObj.put("vin", "fdsafdsa2"); // 车架号
		vehicleObj.put("engineNum", "engineNum888"); // 发动机号
		vehicleObj.put("issueDate", "2018-08-01"); // 发证日期
		vehicleObj.put("registerDate", "2018-08-01"); // 注册日期
		vehicleObj.put("vehicleType", "1"); // 车辆类型
		vehicleObj.put("approvedCount", 5); // 核定人数
		vehicleObj.put("totalMass", 45000); // 总质量
		vehicleObj.put("maintenaceMass", 16000); // 整备质量
		vehicleObj.put("permittedWeight", 30000); // 核定载质量
		vehicleObj.put("outsideDimensions", "100*100*100"); // 车辆尺寸
		vehicleObj.put("useCharacter", "货运"); // 使用性质
		vehicleObj.put("address", "广州市天河区"); // 行驶证住址
		vehicleObj.put("fileNum", ""); // 档案编号
		vehicleObj.put("permittedTowWeight", 50000); // 准牵引总质量
		vehicleObj.put("noOfAxles", "3"); // 轴数
		vehicleObj.put("carType", "1");// 号牌种类
		vehicleObj.put("carBrand", "bmw");// 品牌
		vehicleObj.put("carModel", "1");// 车型
		vehicleObj.put("displacement", "1,987");// 排量
		vehicleObj.put("status", "0");// 机动车状态
		vehicleObj.put("jianCheTime", "2019-10-12");// 年检日期
		vehicleObj.put("color", "4");// 车身颜色
		vehicleObj.put("prodDate", "2019-09-12");// 出厂日期
		vehicleObj.put("fuelType", "2");// 燃料种类
		vehicleObj.put("retireDate", "9999-12-31");// 强制报废日期
		vehicleObj.put("engineModel", "323");// 发动机型号
		vehicleObj.put("enginesMaxPower", "43213");// 发动机最大功率
		vehicleObj.put("wheelbase", "3753");// 轴距
		vehicleObj.put("frontTread", "2222");// 前轮距
		vehicleObj.put("rearTread", "3333");// 后轮距
		vehicleObj.put("annexOriginalId", "fdsadsagdagdas");// 正本照片附件id
		vehicleObj.put("annexCopyId", "dfadsafdsafdsa");// 副本照片附件id

		vehicleInfo.add(vehicleObj);

		bizContent.setVehicleInfo(vehicleInfo);
		
		bizContent.setOperType("1"); // 操作标志 1-投保 2-批改 3-更新vehicleId

		request.setBizContent(bizContent);
		request.isNeedEncrypt();

		String msg_id = String.valueOf(System.currentTimeMillis());
		try {
			MybankAccountTrucketcCreditproofsubmitResponseV1 response = client.execute(request, msg_id);// 第二个参数需要用随机数，APi网关会做防重复检查

				if(response.getReturnCode()!=0){
					//调用接口报错
				}
		//	System.out.println("response=>" + response.getReturnCode() + "|" + response.getReturnMsg());
		//	System.out.println("msgId:" + response.getMsgId());
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}
	}
}
