package com.icbc.api;

import java.util.ArrayList;
import java.util.List;

import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.MallB2bPaymentGuideApplySingleOrderRequestV1;
import com.icbc.api.request.MallB2bPaymentGuideApplySingleOrderRequestV1.PaymentGuideApplySingleOrderRequestV1;
import com.icbc.api.request.MallB2bPaymentGuideApplySingleOrderRequestV1.PaymentGuideApplySingleOrderRequestV1.OrderProd;
import com.icbc.api.response.MallB2bPaymentGuideApplySingleOrderResponseV1;

// B2B跨境支付收款申请（单订单）
public class MallB2bCrborderpayCbpsinglereceiptapplyTestV1 {

	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCoi831KoMXZsoaEsXkW8wvG7TAz1gD1wmP4bTwzDuRV6PqeBxS/cadIlYEurteuxP7rEhVwS1EhBUT+Qcp7477XpW9oSfYwLZuxDhTQO9N+aU8ynHHGZsvpLqwnLNawFYFpE1+oegHHDC6lAdEn5OGN03xb00i3TPGSX/ILHfxC6Yz+TBGglmgIFVDBFmtgXhmGxab9O4c9Jwdw2DujVEVFOovgTGAaduj/CqCp/w+/eZ7yWyxaAKxP2No86fwyFqkR77YJwBj1x59VYdiAbJhpN8zlgEcVcBfqdUTom1NYFPwhASoEbVFYUE1jOit+kdqOc1RLvIWQzSqe7hhKC3ZAgMBAAECggEBAI0F5P8UYflLnSBxwriOgmqMIRIOqtMTssF7lbzA9bpnfRQrCW/7PJqvZw+v+iSKHW8/uIpgBvNEHKrIe9D/yz/R5TJ0S/XVUQ+fCI3IUDKLBlG2BbsUVr9ncnlhs8PkGFXCIsEo0Q5Z7P+/6dDmQDZVwdaYTQ81zCNFzi/73SOGQEQ47fhJEzHRwiphbaYtNrBH/VrVD2cWcNbfKv7Q1BpYxd7oU8Phkqt5FpsqHBu50BXCkkJ4oD0+FPsVTq21M0wPPkyXb9S31I5knItySLcpfSlUti8ZugnHPKdxPTDBnANs7iAWjxx60kEQy5W518TL1JlD8IVARcKpXsi0iYECgYEA7CBD/Xcs/rO40wy/zKSHLqQcRhpZTCCkWaTJT1IJ1K2Nc56CO+nBwdCwHYzwcg8KIsJixOq2BK9YPkOtTkFfWhGyUNmrd+N6tqdR8l2AZpSBH62KG3N1sqDpa5Yckey/vf83fIhGctS4f5cmKmcyHWklS+ngAMbLcYSdPJPZ8MkCgYEAtrtqTf+f7HdNo+iwDICa88eJ10ktXqx6M8d4GgPNLLwZvLewHk1Ng7BxW002KxXERIDnXzU/Ql6gz/UxKmIkPAVNLqId0vbNviR33KuVfwSWSh6nFP63XdroBwI8fNpzC4neEj0QBr9vyFZbJL+ubcBR4FyGsztXuNN5JCsSbJECgYEAsS6Q9iwsNj9z6+JANfYrfgMlODFjWm2QBbhSWtPrjoxgfSavAPgqLR5EWRg+AMhqSaTlam/Y/3D33Vj5PaRuQW7QCD0dnouytEafhidFRzQeJEAbRvf/lHeMT+/HJnSpeI9++lJj0pzuiMdJmz3ubAVPOESZktuZI2hoeGFVg4kCgYBbp5Wimd01VoHy/PuQydDvBd71xRakrg59ekebhNyc25xtNBV6nXuL2iyBhzDsW3aehamH78VTHPO8W1fV0nQqRt53zYj4ECMShGg6nzXVCA/6DXXw6fI/eVV4bn64gTnDR9vDei1XtR30dpwwypRLKHPo/X/y1okhg7D3m0MagQKBgQDsBKZ/KCv9eGO9607i8sEFBcujnNV6OpS2wmGr3QJIsT94TLjjunbgj/upN5z1VPKWCNshu4jFk17a0xXRC4FAf8JKxQm5jFFAnjcnnyzemidds3gVco4AQGXqiHthRIYLuGr0lkk+NjgRBHUHIZj7PBYlzq+E7V8pYH4VwO8T1Q==";

	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

	protected static final String APP_ID = "10000000000000014034";
	
	public static void main(String[] args) throws Exception {
		test();
	}
	public static void test() throws Exception{

	DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,"RSA", MY_PRIVATE_KEY,"UTF-8",
				"json",APIGW_PUBLIC_KEY,"AES","3pkjRkulbauaFLna/jfaxw==", "", "");
		MallB2bPaymentGuideApplySingleOrderRequestV1 request = new MallB2bPaymentGuideApplySingleOrderRequestV1();
		request.setServiceUrl("http://IP/api/mall/b2b/crborderpay/cbpsinglereceiptapply/V1");
		PaymentGuideApplySingleOrderRequestV1 biz = new PaymentGuideApplySingleOrderRequestV1();
		//订单1
		List<OrderProd> orderProdList=new ArrayList<OrderProd>();
		OrderProd orderProd = new OrderProd();
		orderProd.setProdName("苹果");
		orderProd.setProdBrandName("红富士");
		orderProd.setProdModel("456");
		orderProd.setProdNorms("123456");
		orderProd.setProdAmount("100");
		orderProd.setProCurrent("027");
		orderProd.setProNumber("1");
		orderProdList.add(orderProd);
		
		biz.setApplyNo("201810251102454666");
		biz.setApplyName("中非申请");
		biz.setVendorId("9001326");
		biz.setParentVendorId("00000011");
		biz.setChildVendorId("123");
		biz.setOrderNo("201809280000000765");
		biz.setOrderName("中非申请");
		biz.setOrderAmount("100");
		biz.setOrderCurrent("027");
		biz.setPaymentType("1");
		biz.setPaymentRatio("80|10|10");
		biz.setPayors_Country("100");
		biz.setPayors_Province("夏威夷州");
		biz.setPayors_City("曼彻斯特市");
		biz.setPayors_Street("456");
		biz.setPayors_LastName("李");
		biz.setPayors_FirstName("四");
		biz.setPayors_Account("111111111111");
		biz.setBuyer_Country("100");
		biz.setBuyer_Province("夏威夷州");
		biz.setBuyer_City("曼彻斯特市");
		biz.setBuyer_Street("456");
		biz.setBuyer_LastName("阿道夫");
		biz.setBuyer_FirstName("试试");
		biz.setBuyer_Email("90548764@163.com");
		biz.setReceiptProperty("1");
		biz.setOrderProdList(orderProdList);
		request.setBizContent(biz);

		MallB2bPaymentGuideApplySingleOrderResponseV1 response;
		try {
			System.out.println("Request: " + JSONObject.toJSONString(biz));
			response =client.execute(request, System.currentTimeMillis() + "");
			System.out.println("Response: " + JSONObject.toJSONString(response));
			if (response.isSuccess()) {
				System.out.println("execute success");
			} else {
				System.out.println("execute failue");
			}
		} catch (IcbcApiException e) {
			System.out.println("execute exception" + e);
			e.printStackTrace();
		}

	}
	
}

