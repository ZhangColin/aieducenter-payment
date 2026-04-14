package com.icbc.api;

import com.icbc.api.request.QueryBillinfoqryRequestV1;
import com.icbc.api.request.QueryBillinfoqryRequestV1.QueryBillinfoqryRequestV1Biz;
import com.icbc.api.response.QueryBillinfoqryResponseV1;
/**
 * @ClassName
 * @Descirption 个人经营贷款借据查询
 * @Author kfzx-wanc
 */
public class QueryBillinfoqryTest {
	//appid,privateKey,apigwPublicKey  根据实际的应用场景进行修改
		protected static final String appId="123";
		protected static final String privateKey="UZNwr9Wf1V6FbD2kDflqZRBuV8C";
		protected static final String apigwPublicKey="lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

		public static void main(String[] args) throws IcbcApiException{
			DefaultIcbcClient client = new DefaultIcbcClient(appId, privateKey,apigwPublicKey);
			QueryBillinfoqryRequestV1 request = new QueryBillinfoqryRequestV1();
			
			request.setServiceUrl("123");
			QueryBillinfoqryRequestV1Biz bizContent = new QueryBillinfoqryRequestV1Biz();
			
			//公共参数
		
			bizContent.setSerialNo("123");
			bizContent.setAppNo("F-APIP");
			bizContent.setLanguage("zh_CN");
			bizContent.setTransNo("IQueryPerBillInfCocoaService");
			bizContent.setVer("1.0");  
			bizContent.setTurnPageFlag("1");
			bizContent.setBeginRow("1");
			bizContent.setRowCount("5");
			//私有参数
			bizContent.setCooperUnit("123");
			bizContent.setApply_no("123");
			bizContent.setEbank_loan_apply_no("123");
			bizContent.setCnltype("405");
		
	       // System.out.println("-------合作方借据查询申请入参--------"+bizContent.toString());
			
	        request.setBizContent(bizContent);
			//System.out.println("-------param--------"+bizContent);
			QueryBillinfoqryResponseV1 response=new QueryBillinfoqryResponseV1();
			try{
				response = client.execute(request);
			}catch(Exception e){
			//	System.out.println(e);
			}
			if(response.isSuccess()){
			//	System.out.println("success!");
			}else{
				//System.out.println("fail"+response.getReturnMsg());
			}
			}
}
