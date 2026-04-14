package com.icbc.api;

import com.icbc.api.request.AccountBillInfoQueryRequestV2;
import com.icbc.api.request.AccountBillInfoQueryRequestV2.AccountBillInfoQueryRequestV2Biz;
import com.icbc.api.response.AccountBillInfoQueryResponseV2;

public class CardorganizationSendequitymsgV1 {
	// 1、网关公钥
	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的APP私钥-----BIjANBgkqhkiG9w0BAQEFAAOCAQ8A-----请使用正确的APP私钥-----BCgKCAQEAz1/K8SI4mPR0nVKf+K65C7b2dKSknIY4qMvde2TrHxEz5N1jeP5bw6dj6WcUC4ACoIvtl+8h+vwzoz0449EXsxB0GomCDU8jG2i2BaamUWxw1JikIgaZrecie/7LdvEMNE5NwlOT0U/ILTnXJAdHAJQcgDOVv5bGIzWMcP8/zukPYk5Fmn7BsS/Wnt5NSKOlqOBvq08S1rnsJoHFIHnaqggRRTrUU9wYMOLawnwe1Z270Xmx3jpKvi3XZEVsDdsTqIuNA6AA7Bf+cDNxs833o3eGUAEiKD6t/PAj9gnWwnlGrl5ZNxc6WpBwxQ9TOymqy0+Gd+4h5gFmj0R9q8md4QIDAQAB";

	// 2、appid
	protected static final String app_id = "10000000000000002156";//例如 10000000000000002156

	// 3、密钥对认证方式，公钥在API平台登记，此处为私钥
	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDPX8rxIjiY9HSdUp/4rrkLtvZ0pKSchjioy917ZOsfETPk3WN4/lvDp2PpZxQLgAKgi+2X7yH6/DOjPTjj0RezEHQaiYINTyMbaLYFpqZRbHDUmKQiBpmt5yJ7/st28Qw0Tk3CU5PRT8gtOdckB0cAlByAM5W/lsYjNYxw/z/O6Q9iTkWafsGxL9ae3k1Io6Wo4G+rTxLWuewmgcUgedqqCBFFOtRT3Bgw4trCfB7VnbvRebHeOkq+LddkRWwN2xOoi40DoADsF/5wM3Gzzfejd4ZQASIoPq388CP2CdbCeUauXlk3FzpakHDFD1M7KarLT4Z37iHmAWaPRH2ryZ3hAgMBAAECggEAPbpR+n0pQ75giAP0Ql4gDvCjZA0uQrlhXD1y7CwaIOFKBTIxTe7I+fB1uSBJwzKTlZ6RVsyD3OX9qPWsXYP7/f1D0jJkV/nahWoh5IS6tToGTAIzuT0lubI3fZlWj00pSNiWJOYj9gY9m9i/xaW06OMtqKuyQ5lp2UkzSN9x6vDDuDnDpjWWUm5Xy5RO8EiBvlETHezzPakO54o7y0Pll8o09QEnVglVduEtZufDRfBsOvPB8qmllw3CBv8W9xprmWh3mthbB6WPhMY95xrFw3wyY+WIphbPC/O551JRqLqKhGPzMuw0xPPRmjJM73V/j4Y8fL7Yhvl4iP8sOPuBwQKBgQDuQniBDYjc+nDz8jTrlVPOUQl8PLtDvNkmgT8VczZYWjr/nJsl/9v/cKYZngq/vsGFg60xMcqhdp8WKNckkuGODMDPf+g2dFFBFMzMWx8L8VpzMn9+11d9itOyCsJItOHDFMaUEAmqHKkCC5xRKPZXMuhq7dPTBf7Yk+JoJ00r3QKBgQDe0Jswa6oxqnQ2sBJ9F9cIOlrlKtzcjp1dpwe/TK8NavERdYWkDOsbUfiQiMp5GkawP9YYwvXTXkt/0W+rtb8rKxegcYEEk2EFw62UN3KLWDkHJWGPAcDbDwYPMgjrgAUtoh3WmC30YUaDYSkBu6m1DueNuKvZiHsUv0jR2q4r1QKBgByPe0J9DNrwannTnuRekh05OZna7dfA24byF03K8cjFiCz76N01PnnwVvjbKQ6zHx+SGUC8Zz3Tql5NpvyLXt5YSQSWoI9mghb7F1Gn2qDj3aPRedcKmUJDUf0VrRRqGmX4J5W4FnB93wSPv70628y4HYQz3iB9p37uu0Ug8O9BAoGBAIWFisoMatQSakvvXVT7OU/j8F5OPkYpbGIX2wunP2uR/15QB8LUPvuDNqoeXv8NYPjzeDAkfL2qTyIMdpf2dNXAoAr0mbUNQGH87gLY61pXhu0iWIEjMtfS+/WVLUU61V+QO8NCNJ+yv1exnKo+NCedA+Oom8ri/6biLhgbrK31AoGBAIIgSGLC8QBVv0DMB2jwLUsmkUYbQyzu8bZG9h+jqdXLAol/V43HuRCVI0h5BdJ+pj63EUyKVd0G2EIRaXVND2NgKJ/NH6Q5eGiHWloCHJf4dt9wNGfhRk9WaOefc0JRlZ4VJsDriiS139bX2i/NaAMxxZGIsrrrypbR/560zDtr";

	public static void main(String[] args) throws Exception {

		// 签名类型为RSA2时，需传入APIGW-APPID，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
		DefaultIcbcClient client = new DefaultIcbcClient(app_id, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY,
				APIGW_PUBLIC_KEY);

		CardOrganizationSendEquityMsgRequestV1 request = new CardOrganizationSendEquityMsgRequestV1();
		// 4、根据测试环境和生产环境替换相应ip和端口
		String host = "http://ip:port/api";
		String apiUrl = host+"/cardorganization/sendEquityMsg/V1";
		request.setServiceUrl(apiUrl);
		// 5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
		CardOrganizationSendEquityMsgRequestV1.CardOrganizationSendEquityMsgRequestV1Biz bizContent = new CardOrganizationSendEquityMsgRequestV1.CardOrganizationSendEquityMsgRequestV1Biz();
		String equityId = "123455";
		String transType="0";
		bizContent.setEquityId(equityId);// 权益ID
		bizContent.setTransType(transType);// 权益更新状态
        ...
		request.setBizContent(bizContent);
		CardOrganizationSendEquityMsgResponseV1 response;
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
			String msg_id = dateFormat.format(new Date());
			response = (CardOrganizationSendEquityMsgResponseV1) client.execute(request, msg_id);
			if (response.isSuccess()) {
				// 业务成功处理
				CardOrganizationSendEquityMsgResponseV1.CardOrganizationSendEquityMsgResponseV1BizContent response_biz_content = new CardOrganizationSendEquityMsgResponseV1.CardOrganizationSendEquityMsgResponseV1BizContent();
				String return_code = response_biz_content.getReturn_code();
				String return_msg = response_biz_content.getReturn_msg();
				System.out.println("response_biz_content:"+response_biz_content);
				System.out.println("response:" + response);
			} else {
				// 失败
				System.out.println("ReturnCode:" + response.getReturnCode());
				System.out.println("ReturnMsg:" + response.getReturnMsg());
			}
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}
	}
}


