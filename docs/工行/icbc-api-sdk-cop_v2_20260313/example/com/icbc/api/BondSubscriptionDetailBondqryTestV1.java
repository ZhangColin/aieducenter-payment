package com.icbc.api;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

import com.icbc.api.request.BondSubscriptionDetailBondqryRequestV1;
import com.icbc.api.request.BondSubscriptionDetailBondqryRequestV1.BondSubscriptionDetailBondqryRequestV1Biz;
import com.icbc.api.response.BondSubscriptionDetailBondqryResponseV1;
import com.icbc.api.response.BondSubscriptionDetailBondqryResponseV1.BondSubscriptionDetailBondqryResponseV1Result;

public class BondSubscriptionDetailBondqryTestV1 {
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
		BondSubscriptionDetailBondqryRequestV1 request = new BondSubscriptionDetailBondqryRequestV1();
		
		//根据测试环境和生产环境替换相应ip和端口
		request.setServiceUrl("https://ip:port/api/bond/subscription/detail/bondqry/V1");

		BondSubscriptionDetailBondqryRequestV1Biz bizContent = new BondSubscriptionDetailBondqryRequestV1Biz();
		bizContent.setItemId("项目编号");
		bizContent.setTerm("期限");
		bizContent.setPriEstimate1("主体评级");
		bizContent.setBondRating1("债项评级");
		bizContent.setBeginIssueDate("发行日起始值");
		bizContent.setEndIssueDate("发行日结束值");
		bizContent.setBondName("债券全称");
		bizContent.setBondTypeCode("债券类型代码");
		bizContent.setIsICBC("是否工行主承");
		bizContent.setBegNum("查询起始位置");
		bizContent.setFetchNum("查询条数");
		bizContent.setIssuFlag("发行状态");
		request.setBizContent(bizContent);

		BondSubscriptionDetailBondqryResponseV1 response;
		try {
			response = client.execute(request);
			if (response.isSuccess()) {
				// 业务成功处理
				response.getItems();
				List<BondSubscriptionDetailBondqryResponseV1Result> resultList = response.getResultList();
				BondSubscriptionDetailBondqryResponseV1Result result = resultList.get(0);
				result.getBondCode();
				assertNotNull(response);
			} else {
				// 失败
				assertNotNull(response);
			}
		} catch (IcbcApiException e) {
			assertNotNull(client);
		}
	}
}
