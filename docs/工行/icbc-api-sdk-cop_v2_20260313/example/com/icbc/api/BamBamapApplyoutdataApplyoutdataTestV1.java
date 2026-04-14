/**
 * 
 */
package com.icbc.api;


import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;

import com.icbc.api.request.BamBamapApplyoutdataApplyoutdataRequestV1;
import com.icbc.api.request.BamBamapApplyoutdataApplyoutdataRequestV1.BamBamapApplyoutdataApplyoutdataBizV1;
import com.icbc.api.response.BamBamapApplyoutdataApplyoutdataResponseV1;

/**
 * TODO 类的描述：。
 * 
 * <pre>
 * 
 * </pre>
 * 
 * <pre>
 * modify by KFZX-HUANGYB on 2017-8-4
 *    fix->1.
 *         2.
 * </pre>
 */

public class BamBamapApplyoutdataApplyoutdataTestV1 {

	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCqNhPalfp6V8kLGrKBLR/JEfgg4MrRrLPPEeWwaiBaKoQ2OTM4Pjn9jIa5LzWGGzZo7CVJfCU4GBKqyBbalale2WkZ30N6Wpq/98xgbDNEAKcbHDQ5g70kXLqdBp3oMYfntbwJIRnkPQQ8ljF25wuHkHNDbOlGTEof03s8xi2N3qPyZAhoVB2Uoyst0PQhVUjzKcN3NOFQKRN4PXC/JsKl4CYAHFv0TrTGOniLGYbPDSYGyu4j4gFz6+47QFqRHQsN1u9oWNqFzmyVNzAEVutlytfarwcAnNIdEXv7OgoXJdTzwCLbVoxcDy+AM+5eFXl2i15Job9G0QGRaW1B1MaNAgMBAAECggEADlIidX+s5WJBbge/ARNU20wBvnJrXhzTvBZbZu5m7m8pVI6XeqHxqx2JsEcCj9f2SfmkYXxqfT+5Qlk3Ywi4iVxtdVnNiZvZ7VDiBzLTVWNgq+1Bj50/6rFKmi+WVZTlRF+MBJCeZw+jLZ3NZ52k4LEMoZ2mOt3sVqUqd0CNUKRJWp1/h4GDqBM8ZX9SuPdGj23LF+5UDF/OZBeks4COYmWT6d4HPMUitAOHbFUavaDOUk04O/J8/FVgrTDjxHKxHF4P5d5ftXuhC5PSpFrcmlBTygkfioEZqdVfgiPFpKgARLx1iwcNsG00kJ0hlqsqOfb/d4BY3D18TCFKlWcYmQKBgQDwEpaiTP3a5LuiayiyEbZhiHcDRUKMHRoz6ts2XfgK/6toct45LwEN7HX/xpYvF7AFPUBhtRWovu2aQwkXeZB4MGCPMJfAHIi7z+X7bJlCHakmpU8DngWaC+C72kb5XiBh2tl5cvF99zIhlzkBmw78nkqU1M7kuVXjQeYMxZv84wKBgQC1gPVR0WBT4mA5JmVkfyOdeMu0UpC+XvwsiI29b+NTIulUAHpY3ivIyDUJoI3JDsLOX7VUYyhqN+bJ9hmwmScaqYCT2R92h5LT/949xu8bt4OOXwY+FiRB3csyu91BLEUynzpPjGUF4CH/zfk+e62VlZ6Yrzzgd9RrZdE/rOR5zwKBgQCeiqgP5uh+di+zwzDM6AWZw5VVA5g/D7kOMDs0XDpCiTWZdMJs4S3BFDeZAnyL/QixbRHndL8pAW3IQhzu88kElm4uIdkVdcKWxWUL9VwA3bWnX+Qeb20jnlR7ulrEs/SOqwiTu+TfWzAwHr324X+0NJTsVN2NDxg5THuwU5AAIQKBgAY2+C9o/+dfZPQQAIDEaa/GpxLxkMsz7WaI9nF6F0qNc3fQ3F+Wm+EwGcDOoowz2S+/zzGog4W/jEN2DcMiO1iKXUCyuoUQd1KS/EUZPq6wRezmDAysLWmj8oU2MYk9vtDQ6uo2YG8nEYL0xogBLBxnFxsbRPZapAdZYSTRArG5AoGAPJq08rzNF1q4hL9nLWpvn8/gUzLlrWHzLrp04PbuzF2W2tlHOyavnsFEUbAhvEW9CVDIZ5V8mEAJSBetkJ3p/iiKdfC1ms8Zqkk8Iv/6E2WtxPrgYGLlkLHqkPTyaK38w/+tBjcbcWyjdGCgbCEBT6AlhCUgBoPa9Zi/h4UJPyM=";

	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

	protected static final String APP_ID = "10000000000000002710";
	
	public static void main(String[] args) throws Exception{
		BamBamapApplyoutdataApplyoutdataTestV1 a=new BamBamapApplyoutdataApplyoutdataTestV1();
		a.test_cop();
	}

	public void test_cop() throws IcbcApiException {

		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

		BamBamapApplyoutdataApplyoutdataRequestV1 request = new BamBamapApplyoutdataApplyoutdataRequestV1();
		request.setServiceUrl("http://ip:port/api/bam/bamap/applyoutdata/V1/applyoutdata");

		BamBamapApplyoutdataApplyoutdataBizV1 bizContent = new BamBamapApplyoutdataApplyoutdataBizV1();
		bizContent.setCompanycode("JDJR");// 公司代码
		bizContent.setSerialnum("1234567890123456789012345");// 流水号
		bizContent.setData1("1");// 外部字段1
		bizContent.setData2("1");// 外部字段2
		bizContent.setData3("1");// 外部字段3
		bizContent.setData4("1");// 外部字段4
		bizContent.setData5("1");// 外部字段5
		bizContent.setData6("1");// 外部字段6
		bizContent.setData7("1");// 外部字段7
		bizContent.setData8("1");// 外部字段8
		bizContent.setData9("1");// 外部字段9
		bizContent.setData10("1");// 外部字段10
		bizContent.setData11("1");// 外部字段11
		bizContent.setData12("1");// 外部字段12
		bizContent.setData13("1");// 外部字段13
		bizContent.setData14("1");// 外部字段14
		bizContent.setData15("1");// 外部字段15
		bizContent.setData16("1");// 外部字段16
		bizContent.setData17("1");// 外部字段17
		bizContent.setData18("1");// 外部字段18
		bizContent.setData19("1");// 外部字段19
		bizContent.setData20("1");// 外部字段20
		bizContent.setData21("1");// 外部字段21
		bizContent.setData22("1");// 外部字段22
		bizContent.setData23("1");// 外部字段23
		bizContent.setData24("1");// 外部字段24
		bizContent.setData25("1");// 外部字段25
		bizContent.setData26("1");// 外部字段26
		bizContent.setData27("1");// 外部字段27
		bizContent.setData28("1");// 外部字段28
		bizContent.setData29("1");// 外部字段29
		bizContent.setData30("1");// 外部字段30
		bizContent.setData31("JD123445232342");// 外部字段31
		bizContent.setData32("1");// 外部字段32
		bizContent.setData33("1");// 外部字段33
		bizContent.setData34("50");// 外部字段34
		bizContent.setData35("1");// 外部字段35
		bizContent.setData36("1");// 外部字段36
		bizContent.setData37("2");// 外部字段37
		bizContent.setData38("3");// 外部字段38
		bizContent.setData39("4");// 外部字段39
		bizContent.setData40("5");// 外部字段40
		bizContent.setData41("6");// 外部字段41
		bizContent.setData42("1");// 外部字段42
		bizContent.setData43("1");// 外部字段43
		bizContent.setData44("1");// 外部字段44
		bizContent.setData45("1");// 外部字段45
		bizContent.setData46("1");// 外部字段46
		bizContent.setData47("2");// 外部字段47
		bizContent.setData48("2");// 外部字段48
		bizContent.setData49("2");// 外部字段49
		bizContent.setData50("2");// 外部字段50
		bizContent.setData51("2");// 外部字段51
		bizContent.setData52("2");// 外部字段52
		bizContent.setData53("2");// 外部字段53
		bizContent.setData54("2");// 外部字段54
		bizContent.setData55("2");// 外部字段55
		bizContent.setData56("2");// 外部字段56
		bizContent.setData57("2");// 外部字段57
		bizContent.setData58("2");// 外部字段58
		bizContent.setData59("2");// 外部字段59
		bizContent.setData60("3");// 外部字段60
		bizContent.setData61("4");// 外部字段61
		bizContent.setData62("2");// 外部字段62
		bizContent.setData63("1");// 外部字段63
		bizContent.setData64("3");// 外部字段64
		bizContent.setData65("3");// 外部字段65
		bizContent.setData66("2");// 外部字段66
		bizContent.setData67("4");// 外部字段67
		bizContent.setData68("2");// 外部字段68
		bizContent.setData69("1");// 外部字段69
		bizContent.setData70("2");// 外部字段70
		bizContent.setData71("2");// 外部字段71
		bizContent.setData72("2");// 外部字段72
		bizContent.setData73("2");// 外部字段73
		bizContent.setData74("2");// 外部字段74
		bizContent.setData75("2");// 外部字段75
		bizContent.setData76("2");// 外部字段76
		bizContent.setData77("2");// 外部字段77
		bizContent.setData78("2");// 外部字段78
		bizContent.setData79("2");// 外部字段79
		bizContent.setData80("2");// 外部字段80
		bizContent.setData81("2");// 外部字段81
		bizContent.setData82("2");// 外部字段82
		bizContent.setData83("2");// 外部字段83
		bizContent.setData84("2");// 外部字段84
		bizContent.setData85("3");// 外部字段85
		bizContent.setData86("4");// 外部字段86
		bizContent.setData87("2");// 外部字段87
		bizContent.setData88("2");// 外部字段88
		bizContent.setData89("1");// 外部字段89
		bizContent.setData90("2");// 外部字段90
		bizContent.setData91("2");// 外部字段91
		bizContent.setData92("2");// 外部字段92
		bizContent.setData93("2");// 外部字段93
		bizContent.setData94("2");// 外部字段94
		bizContent.setData95("2");// 外部字段95
		bizContent.setData96("2");// 外部字段96
		bizContent.setData97("2");// 外部字段97
		bizContent.setData98("2");// 外部字段98
		bizContent.setData99("2");// 外部字段99
		bizContent.setData100("2");// 外部字段100

		request.setBizContent(bizContent);
		BamBamapApplyoutdataApplyoutdataResponseV1 response = client.execute(request);
		

		System.out.println(JSONObject.toJSONString(response));
	}

}
