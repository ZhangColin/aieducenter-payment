package com.icbc.api;

import org.junit.Test;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.request.CustomerCampaignQueryRequestV1;
import com.icbc.api.request.CustomerCampaignQueryRequestV1.CustomerCampaignQueryRequestV1Biz;
import com.icbc.api.response.CustomerCampaignQueryResponseV1;

public class CustomerCampaignQueryV1Test {
	
	//appid
	protected static final String APP_ID = "xxxxxx";
	
	//合作方私钄1�7
	protected static final String MY_PRIVATE_KEY = "xxxxxx";
	
	//网关公钥
	protected static final String APIGW_PUBLIC_KEY = "xxxxxx";
	

	public void test_cop() throws IcbcApiException {
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
		CustomerCampaignQueryRequestV1 request = new CustomerCampaignQueryRequestV1();
		
		//根据测试环境和生产环境替换相应ip和端叄1�7
		request.setServiceUrl("https://ip:port/api/customer/campaign/query/V1");

		CustomerCampaignQueryRequestV1Biz bizContent = new CustomerCampaignQueryRequestV1Biz();
		bizContent.setUserId("000000009");
		bizContent.setChncd("404");
		bizContent.setCino("234562345650816");
		
		//���������Ǳ��䣬��ο�md�ĵ���ʵ����������
		bizContent.setQryType("1");
		bizContent.setCampId("EBM_0101_0000_2020_000159");
		bizContent.setCardNum("6251620000007995");
		bizContent.setProdNum(2);
		bizContent.setBegDt("2020-01-01");
		bizContent.setEndDt("2021-12-31");
		
		request.setBizContent(bizContent);

		CustomerCampaignQueryResponseV1 response;
		try {
			response = client.execute(request, "msgId");
			if (response.isSuccess()) {
				// 业务成功处理
				if(response.getReturnCode() == 0){
					int totalNum = response.getTotalnum();
					String rds = response.getRds();
				}else{
					String retMsg = response.getReturnMsg();//���ݷ�����ʾ��Ϣ�ʹ�����봦��
				}
				
			} else {
				//失败
			}
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}
	}
	
}
