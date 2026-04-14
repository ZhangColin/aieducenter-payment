package com.icbc.api;

import java.util.ArrayList;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.request.InvestmentFundoutsourceValuationreportqueryRequestV1;
import com.icbc.api.request.InvestmentFundoutsourceValuationreportqueryRequestV1.InvestmentFundoutsourceValuationreportqueryRequestV1Biz;
import com.icbc.api.request.InvestmentFundoutsourceValuationreportqueryRequestV1.InvestmentFundoutsourceValuationreportqueryRequestV1In;
import com.icbc.api.request.InvestmentFundoutsourceValuationreportqueryRequestV1.InvestmentFundoutsourceValuationreportqueryRequestV1InRecord;
import com.icbc.api.response.InvestmentFundoutsourceValuationreportqueryResponseV1;


public class InvestmentFundoutsourceValuationreportqueryTest {

	//估值表
	protected static String APP_ID = "10000000000000013551";
	protected static String BASE_URL = "http://ip:port/api"; // 行外

	//网关公钥
	protected static String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
	//应用私钥（与内管平台注册app时的“签名公钥”配对）
	protected static String PRI_KEY = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDV/bSiP7O4FC3uKdjEoNFvJAJrwOKOvQEVTE3YMRP9JkLEqvU50hmIDKHWluIDq3tIaI6xPKDCaYxuOMa+lft9aIYK/2wMHWp0S4voHWfD/x7/m8bA4nTNw34fkp636Hp7TUx7X6Yi9BucVEgH2JA1MdVrOIs+cpB4rODqLyaXj4c27hEJzyJQvYgQR+v35Ss1N5HnIOhDLqQuYxXx69T43/ExWFNcm9ZktQ4jkOf7AEINzwDCUgUpOwpby5n8vW672/wlxeGvur0+LUwo0soF2cfZ1YHQbohwhqjviV3YDvX1OUNSa9D/WGkqcESw4eJqj4lmdukNYh6kNDklRSbTAgMBAAECggEAFoKfs8mbsCt/QWRphxWbsmNJVehubLF5IbJrJejTv3d84o9RSInPYb5ib95jXBt31oAZMo+8IXdU7GtQjM/eKq0KHLINvaCCiEVCLGKbfcSikCTk3Xl07Ag6hju4MoRkDgBtoTTIlziyhtle0mR3lwDtAGBsVV9bzzXwWvImzx56xdFkuQu/h6G5SsxRpV6xJ8+92o3NuCbllpUGHJ1ba46tcLC+vx5NQIAczmrt34iixUbgR95YWehV8S5+6Vdg0TzLJP62pDZzuvdrUykPZtkp914E528qgN/JWgSnmdyl7x9xC2Of1oV4xCH2L5/u9y21WB/Ct3byP5xVQi7tgQKBgQDzCSFs/9POoDG5DRQnvZ8c2rmE54SFLm33/HIxnMNbyub/LZBx/43H68Q6+Lc0GY+BG/0kyO2bC+7ubu7F+fBlPbtAkZ6E5TdK2s+SqVf9Pq8wIIEOXLQGPTyFWq1/eJxSCgzNqt1SyB7EQda77oBqErN8Pa4PdH3bhrPJ2bUsoQKBgQDhZ/HUJeOKOZjrcAS9HAjEETs49C+5O6VaExjldcf/pN79LaknjSQsq2RWJ9C0myodMCSvFsdnp3vLXOp9a83sIlkDZzoqZ0Biqj+1sFVZJZ0VN1zCRSkFWvALTaLYaYn3xm84R1r658jbu5EOTr3pQ0MwhFC6bwRuPnRyBh2K8wKBgFp94dZGA0WCXEE8325UqnQB9OXJloxIV2Xot1RFvG/nQs9s1cW8k7wGy0/x37Ukd0xpcX5K2j3K2bIp1/obMCliLODD17Q/7LCQfI1tRHfk6TIQSD1iwx03m9tDgipebEYXu+CwaCw0m1AT+7KKAYJ9aBswlCjpBRvCMhtMVqtBAoGAfTMxPbc15ecMK2nzF99kdxNjI8VuZKLeu2N0Jq//QN4hBdvA4zkgaLdiagB/inHNOJ0vIxmx9L8wocqgbL3eiVSiwKMrybXEK4mJDbA+F+AumkQ//6BOoqzaPE0JMM+eVMQhrs7jmA36oSJT9fCCx/gWb/JurL9RZLLIFzWSX0UCgYEA5MpOSCQrhRQMa8kyDjGcIlmYxDhsNBVrMgtY2WQFMSpYwYOxHlNrazE+vhkm+mBjl2UhCyIQpXaHUFwdJpH0aDE2n9KF3N2jyE4gS1Xb331u6RmqNE6Myi+tVnpeeaPRYE4w3H+zCE0TzyV/k2NNIWXgBU18NUDHETRq6dPJNPU=";

	public static void main(String[] args) throws Exception {
		String URI = "investment/fundoutsource/valuationreportquery/V1"; // api地址
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, PRI_KEY, APIGW_PUBLIC_KEY);
		try {

			// 请求实体
			InvestmentFundoutsourceValuationreportqueryRequestV1 request = new InvestmentFundoutsourceValuationreportqueryRequestV1();
			// bizContent实体
			InvestmentFundoutsourceValuationreportqueryRequestV1Biz bizContent = new InvestmentFundoutsourceValuationreportqueryRequestV1Biz();
			// 请求报文IN节点实体
			InvestmentFundoutsourceValuationreportqueryRequestV1In requestIn = new InvestmentFundoutsourceValuationreportqueryRequestV1In();
			// Record实体
			InvestmentFundoutsourceValuationreportqueryRequestV1InRecord instBean = new InvestmentFundoutsourceValuationreportqueryRequestV1InRecord();

			// Record实体 设置属性
//			instBean.setReportKind("04");

			ArrayList<InvestmentFundoutsourceValuationreportqueryRequestV1InRecord> list = new ArrayList<>();
			list.add(instBean);

			// 请求报文IN节点实体 设置属性
			requestIn.setFileType("4501");
			requestIn.setFundId("333020");
			requestIn.setReportType("01");
			requestIn.setBeginDate("20110331");
			requestIn.setEndDate("20110331");
			requestIn.setDeptCode("G001");
			requestIn.setCertId("");
			requestIn.setSerialNo("20181024000001012379");
			requestIn.setInRecords(list);

			// bizContent实体 设置属性
			bizContent.setIn(requestIn);

			// 请求实体 设置属性
			request.setServiceUrl(BASE_URL + "/" + URI);
			request.setBizContent(bizContent);

			System.out.println(request.getMethod());
//			System.out.println(instBean.getReportkind());
			// 输出支付指令提交响应对象，需根据实际调用服务更换（InvestmentFundoutsourceValuationreportqueryResponseV1）
			InvestmentFundoutsourceValuationreportqueryResponseV1 response = client.execute(request);
			if (response.isSuccess()) {
				// 业务成功处理
				System.out.println("success");//
			} else {
				// 失败
				System.out.println("error");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
