package com.icbc.api;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.icbc.api.request.BondSubscriptionInformationBondqryRequestV1;
import com.icbc.api.request.BondSubscriptionInformationBondqryRequestV1.BondSubscriptionInformationBondqryRequestV1Biz;
import com.icbc.api.response.BondSubscriptionInformationBondqryResponseV1;

public class BondSubscriptionInformationBondqryV1Test {
	//appid
	protected static final String APP_ID = "[需替换]应用方APP_ID，由应用方至工行API开放平台申请获得";
	
	//合作方私钥
	protected static final String MY_PRIVATE_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";
	
	//网关公钥
	protected static final String APIGW_PUBLIC_KEY = "[需替换]工行API网关公钥，请向工行联系获取";
	
	//加密密钥
	protected static final String AES_KEY = "[需替换]应用方私钥，由应用方生成，并将之上传至工行API开放平台";

	
	public void testCop() throws IcbcApiException {
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY,
				IcbcConstants.CHARSET_UTF8, IcbcConstants.FORMAT_JSON, APIGW_PUBLIC_KEY, IcbcConstants.ENCRYPT_TYPE_AES, AES_KEY, null, null);
        BondSubscriptionInformationBondqryRequestV1 request = new BondSubscriptionInformationBondqryRequestV1();
        
        //根据测试环境和生产环境替换相应ip和端口
        request.setServiceUrl("https://ip:port/api/bond/subscription/information/bondqry/V1");
        
        BondSubscriptionInformationBondqryRequestV1Biz bizContent = new BondSubscriptionInformationBondqryRequestV1.BondSubscriptionInformationBondqryRequestV1Biz();
        bizContent.setInvestor("投资机构");
        bizContent.setInvestorName("投资机构名称");
        bizContent.setBegNum("查询起始位置");
        bizContent.setFetchNum("查询条数");
        request.setBizContent(bizContent);

        BondSubscriptionInformationBondqryResponseV1 response;
        try {
            response = client.execute(request);
			if (response.isSuccess()) {
                //业务处理成功
                response.getReturnCode();
                assertNotNull(response);
			} else {
				//失败
				assertNotNull(response);
			}
        } catch (IcbcApiException e) {
        	assertNotNull(client);
        }
    }
}
