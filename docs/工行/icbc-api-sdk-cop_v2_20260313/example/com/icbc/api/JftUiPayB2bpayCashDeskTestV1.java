package com.icbc.api.request;

import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcConstants;
import com.icbc.api.UiIcbcClient;

public class JftUiPayB2bpayCashDeskTestV1 {

	public static void main(String[] args) throws IcbcApiException {

		String appId = "[需替换]合作方APP的编号";
		String MY_PRIVATE_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";
		String host = "[需替换]工行API网关地址";
		String apiUrl= host+"/ui/jft/ui/pay/b2bpay/cashDesk/V1";

		UiIcbcClient client = new UiIcbcClient(appId, MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8);
		JftUiPayB2bpayCashDeskRequestV1 request = new JftUiPayB2bpayCashDeskRequestV1();
		request.setServiceUrl(apiUrl);

		JftUiPayB2bpayCashDeskRequestV1.JftUiPayB2bpayCashDeskRequestV1Biz bizContent = new JftUiPayB2bpayCashDeskRequestV1.JftUiPayB2bpayCashDeskRequestV1Biz();
		bizContent.setAppId(appId);
		bizContent.setOutOrderId("04221125");
		bizContent.setType("01");
		bizContent.setTradeTime("2021-01-01 23:59:59");
		request.setBizContent(bizContent);

		String form = client.buildPostForm(request);
		System.out.println(form);

	}
}
