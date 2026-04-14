package com.icbc.api;

import com.icbc.api.request.PledgeInfoRecieveOutjudgereportRequestV1;
import com.icbc.api.request.PledgeInfoRecieveOutjudgereportRequestV1.PledgeInfoRecieveOutjudgereportRequestV1Biz;
import com.icbc.api.request.PledgeInfoRecieveOutjudgereportRequestV1.PledgeInfoRecieveOutjudgereportRequestV1Biz.PublicBean;
import com.icbc.api.request.PledgeInfoRecieveOutjudgereportRequestV1.PledgeInfoRecieveOutjudgereportRequestV1Biz.PrivateBean;
import com.icbc.api.request.PledgeInfoRecieveOutjudgereportRequestV1.PledgeInfoRecieveOutjudgereportRequestV1Biz.AssessData;
import com.icbc.api.response.PledgeInfoRecieveOutjudgereportResponseV1;

public class PledgeInfoRecieveOutjudgereportV1Test {
	public static void main(String[] args) {
		try {
			 String APP_ID = "xxxxxxx";
			 String APIGW_PUBLIC_KEY = "xxxxx";
			 String PRI_KEY =   "xxx";               
			 String URL = "https://xxxxx/api/pledge/info/recieve/outjudgereport/V1";
			
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, PRI_KEY, APIGW_PUBLIC_KEY);
        

        PledgeInfoRecieveOutjudgereportRequestV1Biz bizContent =
                    new PledgeInfoRecieveOutjudgereportRequestV1Biz();
        	
        	PublicBean publicBean =new PublicBean();
        	bizContent.setPublicBean(publicBean);
        	PrivateBean privateBean =new PrivateBean();
        	privateBean.setAssessOrgUsci("12345");
        	privateBean.setAssessType(1);
        	privateBean.setOrderNo("123");
        	privateBean.setReportUrl("123.pdf");
        	privateBean.setStatus(0);
        	AssessData assessData=new AssessData();
        	assessData.setAssessValueDate("");
        	assessData.setBuildArea("");
        	assessData.setBuildingName("");
        	
        	privateBean.setAssessData(assessData);
        	bizContent.setPrivateBean(privateBean);
        	
        	
        	
        	PledgeInfoRecieveOutjudgereportRequestV1 request =
                    new PledgeInfoRecieveOutjudgereportRequestV1();
            request.setServiceUrl(URL);
            request.setBizContent(bizContent);
            PledgeInfoRecieveOutjudgereportResponseV1 response = client.execute(request);
          
            if (response.isSuccess()) {
           
            	System.out.println(response);
            	
                
            } else {
            	//System.out.println("");
            	
            }

        } catch (Exception e) {
        	//System.out.println(e);
        	
        }
	}
}
