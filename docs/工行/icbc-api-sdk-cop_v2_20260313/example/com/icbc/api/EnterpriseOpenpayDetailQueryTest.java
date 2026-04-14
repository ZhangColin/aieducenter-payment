package com.icbc.api;

import org.junit.Test;

import com.icbc.api.request.EnterpriseOpenpayDetailQueryRequestV1;
import com.icbc.api.request.EnterpriseOpenpayDetailQueryRequestV1.EnterpriseOpenpayDetailQueryRequestV1Biz;

public class EnterpriseOpenpayDetailQueryTest {

	protected static final String MY_PRIVATE_KEY = "";

	protected static final String APIGW_PUBLIC_KEY = "";

	protected static final String APP_ID = "";
	
	protected static final String password = "";
	
	protected static final  String MY_PUB_KEY = "";

	
	public void test_cop() {

		// ����client����
	DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY,MY_PUB_KEY,password);

	// �����������request
	EnterpriseOpenpayDetailQueryRequestV1 request = new EnterpriseOpenpayDetailQueryRequestV1();
	// ��������·��
	request.setServiceUrl("http://ip:port/api/enterprise/openpay/detail/query/V1");

	EnterpriseOpenpayDetailQueryRequestV1Biz bizContent = new EnterpriseOpenpayDetailQueryRequestV1Biz();

  bizContent.setTransCode("QZJKZ"); 
	bizContent.setCis("dongxiao2017045"); 
	bizContent.setBankCode("102"); 
	bizContent.setId("xiaoqiao02030327.y.0200"); 
	bizContent.setTranDate("2021010"); 
	bizContent.setTranTime("111111111"); 
	bizContent.setfSeqno("234324323244"); 
  bizContent.setFileCode("0200EH0003042"); 
	bizContent.setTradePlatCode("0"); 
	bizContent.setFileSerialno("APICS042504"); 
	bizContent.setInstrType("0");

	request.setBizContent(bizContent);
	
	
	com.icbc.api.response.EnterpriseOpenpayDetailQueryResponseV1 response; 
	 try { 
	  response = (com.icbc.api.response.EnterpriseOpenpayDetailQueryResponseV1) client.execute(request, "msgId"); 
	  if (response.isSuccess() ) { 
	   // ҵ��ɹ����� 
		  System.out.println("success");//
		 } else { 
		   // ʧ�� 
		  System.out.println("error");
		 }   
	 } catch (IcbcApiException e) { 
	  e.printStackTrace(); 
	 } 
	}
}
 
