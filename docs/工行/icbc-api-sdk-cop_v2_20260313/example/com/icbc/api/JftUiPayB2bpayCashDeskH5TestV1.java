package com.icbc.api.request;

import com.icbc.api.*;

public class JftUiPayB2bpayCashDeskH5TestV1 {

	public static void main(String[] args) throws IcbcApiException {

		String appId = "[需替换]合作方APP的编号";
		String MY_PRIVATE_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";
		String host = "[需替换]工行API网关地址";
		String apiUrl= host+"/ui/jft/ui/pay/b2bpay/cashDesk/h5/V1";

		UiIcbcClient client = new UiIcbcClient(appId, MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8);
		JftUiPayB2bpayCashDeskH5RequestV1 request = new JftUiPayB2bpayCashDeskH5RequestV1();
		request.setServiceUrl(apiUrl);

		JftUiPayB2bpayCashDeskH5RequestV1.JftUiPayB2bpayCashDeskH5RequestV1Biz bizContent = new JftUiPayB2bpayCashDeskH5RequestV1.JftUiPayB2bpayCashDeskH5RequestV1Biz();
		bizContent.setAppId(appId);
		bizContent.setOutOrderId("04221125");
		bizContent.setType("01");
		bizContent.setTradeTime("2021-01-01 23:59:59");
		request.setBizContent(bizContent);

		String form = client.buildPostForm(request);
		System.out.println(form);

	}
}
