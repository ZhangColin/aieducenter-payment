package com.icbc.api;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.BcssMemberCustTradedetailQueryRequestV1;
import com.icbc.api.response.BcssMemberCustTradedetailQueryResponseV1;

import java.io.IOException;
import java.util.List;
public class BcssMemberCustTradedetailQueryTestV1 {
	protected static final String MY_PRIVATE_KEY = "********";

	// APIGW_PUBLIC_KEY
	protected static final String APIGW_PUBLIC_KEY = "*******";

	// APP_ID
	protected static final String APP_ID = "100000000000xxxxxxxxxxxxx";

	protected static final String RSA = "RSA";
	protected static final String RSA2 = "RSA2";


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

		BcssMemberCustTradedetailQueryRequestV1 request = new BcssMemberCustTradedetailQueryRequestV1();
		request.setServiceUrl("https://********/api/bcss/member/cust/tradedetail/query/V1");

		BcssMemberCustTradedetailQueryRequestV1.BcssMemberCustTradedetailQueryRequestBizV1 bizContent = new BcssMemberCustTradedetailQueryRequestV1.BcssMemberCustTradedetailQueryRequestBizV1();
		bizContent.setClientTransNo("12345678912345678922");
		bizContent.setTimeStamp("2021-10-25 10:10:12");
		bizContent.setCorpId("2000000005");
		bizContent.setStartDate("2025-10-10");//
		bizContent.setEndDate("2025-12-10、");//
		bizContent.setUserType("4");
		bizContent.setUserInfo("assdfghjlzc");
		bizContent.setPageNum("1");
		bizContent.setPageSize("10");
		request.setBizContent(bizContent);

		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, RSA2, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
		BcssMemberCustTradedetailQueryResponseV1 response = client.execute(request);


		if(response.getReturnCode()==0 ){
			String x1=response.getDataInfo();
			if(x1!=null ||!x1.isEmpty()){
				List<BcssMemberCustTradedetailQueryResponseV1.DataInfoList> dataInfoLists=JSONObject.parseObject(x1,List.class);

				for(int i=0;i<dataInfoLists.size();i++){
					BcssMemberCustTradedetailQueryResponseV1.DataInfoList data=JSONObject.parseObject(JSONObject.toJSONString(dataInfoLists.get(0)),BcssMemberCustTradedetailQueryResponseV1.DataInfoList.class);
				}
				//获取结果结束
			}
		}

	}

}