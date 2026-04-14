package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.BcssMemberCustAuthinfoQueryRequestV1;
import com.icbc.api.response.BcssMemberCustAuthinfoQueryResponseV1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BcssMemberCustAuthinfoQueryTestV1 {
	protected static final String MY_PRIVATE_KEY = "";

	// APIGW_PUBLIC_KEY
	protected static final String APIGW_PUBLIC_KEY = "";

	// APP_ID
	protected static final String APP_ID = "100000000000040xxxxx";

	protected static final String RSA = "RSA";
	protected static final String RSA2 = "RSA2";

	public static String format(int number) {
		return String.format("%09d", number);
	}

	public static void main(String[] args)  {


		try {
			testApi();
		} catch (IcbcApiException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}



	public static void testApi() throws IcbcApiException, IOException {
		JSONObject chanelJson=new JSONObject();
		chanelJson.put("clientType","3");
//		chanelJson.put("bcssBasChannelInfo",本地存储的auth人脸 渠道信息);
//		chanelJson.put("bcssBasEncryptionVersion",bas加密构件的版本号，即BASApi类的getVersion());

		BcssMemberCustAuthinfoQueryRequestV1 request = new BcssMemberCustAuthinfoQueryRequestV1();
		request.setServiceUrl("http://***.****.***.***:**/api/bcss/member/cust/authinfo/query/V1");
		List<String> businessNoList=new ArrayList<>();
		businessNoList.add("1");
		businessNoList.add("2");
		BcssMemberCustAuthinfoQueryRequestV1.BcssMemberCustAuthinfoQueryRequestBizV1 bizContent = new BcssMemberCustAuthinfoQueryRequestV1.BcssMemberCustAuthinfoQueryRequestBizV1();
		bizContent.setClientTransNo("12345678912345678922");
		bizContent.setTimeStamp("2021-10-25 10:10:12");
		bizContent.setCorpId("2000000005");
		bizContent.setOperType("4");//厂商编号
		bizContent.setStartDate("2025-10-10 12:00:00");//硬件序列号
		//2000000005
		bizContent.setEndDate("2025-12-10 12:00:00");//硬件序列号
		bizContent.setMerNo("20000000050000000001");//默认送空字符串
		bizContent.setUserType("25");
		bizContent.setUserInfo("assdfghjlzc");
		bizContent.setPageNum("10");
		bizContent.setPageSize("10");
		bizContent.setBusinessNoList(JSONObject.toJSONString(businessNoList));
		request.setBizContent(bizContent);
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, RSA2, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
		BcssMemberCustAuthinfoQueryResponseV1 response = client.execute(request);

		if(response.getReturnCode()==0&&"4".equals(bizContent.getOperType()) ){
			String x1=response.getDataInfo();
			if(x1!=null ||!x1.isEmpty()){
				List<BcssMemberCustAuthinfoQueryResponseV1.DataInfoList> dataInfoLists=JSONObject.parseObject(x1,List.class);

				for(int i=0;i<dataInfoLists.size();i++){
					BcssMemberCustAuthinfoQueryResponseV1.DataInfoList data=JSONObject.parseObject(JSONObject.toJSONString(dataInfoLists.get(0)),BcssMemberCustAuthinfoQueryResponseV1.DataInfoList.class);
					
				}
				//获取结果结束
			}
		}

	}

}