package com.icbc.api;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.MallB2bPaymentStatusQueryRequestV1;
import com.icbc.api.request.MallB2bPaymentStatusQueryRequestV1.MallB2bPaymentStatusQueryRequestV1Biz;
import com.icbc.api.response.MallB2bPaymentStatusQueryResponseV1;

// B2B跨境支付状态查询
public class MallB2bCrborderpayCbptradestatusqryTestV1 {

	private static final Log logger = LogFactory.getLog(MallB2bCrborderpayCbptradestatusqryTestV1.class);
	
	protected static final String APP_ID = "10000000000000014034";
	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCoi831KoMXZsoaEsXkW8wvG7TAz1gD1wmP4bTwzDuRV6PqeBxS/cadIlYEurteuxP7rEhVwS1EhBUT+Qcp7477XpW9oSfYwLZuxDhTQO9N+aU8ynHHGZsvpLqwnLNawFYFpE1+oegHHDC6lAdEn5OGN03xb00i3TPGSX/ILHfxC6Yz+TBGglmgIFVDBFmtgXhmGxab9O4c9Jwdw2DujVEVFOovgTGAaduj/CqCp/w+/eZ7yWyxaAKxP2No86fwyFqkR77YJwBj1x59VYdiAbJhpN8zlgEcVcBfqdUTom1NYFPwhASoEbVFYUE1jOit+kdqOc1RLvIWQzSqe7hhKC3ZAgMBAAECggEBAI0F5P8UYflLnSBxwriOgmqMIRIOqtMTssF7lbzA9bpnfRQrCW/7PJqvZw+v+iSKHW8/uIpgBvNEHKrIe9D/yz/R5TJ0S/XVUQ+fCI3IUDKLBlG2BbsUVr9ncnlhs8PkGFXCIsEo0Q5Z7P+/6dDmQDZVwdaYTQ81zCNFzi/73SOGQEQ47fhJEzHRwiphbaYtNrBH/VrVD2cWcNbfKv7Q1BpYxd7oU8Phkqt5FpsqHBu50BXCkkJ4oD0+FPsVTq21M0wPPkyXb9S31I5knItySLcpfSlUti8ZugnHPKdxPTDBnANs7iAWjxx60kEQy5W518TL1JlD8IVARcKpXsi0iYECgYEA7CBD/Xcs/rO40wy/zKSHLqQcRhpZTCCkWaTJT1IJ1K2Nc56CO+nBwdCwHYzwcg8KIsJixOq2BK9YPkOtTkFfWhGyUNmrd+N6tqdR8l2AZpSBH62KG3N1sqDpa5Yckey/vf83fIhGctS4f5cmKmcyHWklS+ngAMbLcYSdPJPZ8MkCgYEAtrtqTf+f7HdNo+iwDICa88eJ10ktXqx6M8d4GgPNLLwZvLewHk1Ng7BxW002KxXERIDnXzU/Ql6gz/UxKmIkPAVNLqId0vbNviR33KuVfwSWSh6nFP63XdroBwI8fNpzC4neEj0QBr9vyFZbJL+ubcBR4FyGsztXuNN5JCsSbJECgYEAsS6Q9iwsNj9z6+JANfYrfgMlODFjWm2QBbhSWtPrjoxgfSavAPgqLR5EWRg+AMhqSaTlam/Y/3D33Vj5PaRuQW7QCD0dnouytEafhidFRzQeJEAbRvf/lHeMT+/HJnSpeI9++lJj0pzuiMdJmz3ubAVPOESZktuZI2hoeGFVg4kCgYBbp5Wimd01VoHy/PuQydDvBd71xRakrg59ekebhNyc25xtNBV6nXuL2iyBhzDsW3aehamH78VTHPO8W1fV0nQqRt53zYj4ECMShGg6nzXVCA/6DXXw6fI/eVV4bn64gTnDR9vDei1XtR30dpwwypRLKHPo/X/y1okhg7D3m0MagQKBgQDsBKZ/KCv9eGO9607i8sEFBcujnNV6OpS2wmGr3QJIsT94TLjjunbgj/upN5z1VPKWCNshu4jFk17a0xXRC4FAf8JKxQm5jFFAnjcnnyzemidds3gVco4AQGXqiHthRIYLuGr0lkk+NjgRBHUHIZj7PBYlzq+E7V8pYH4VwO8T1Q==";
	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
	protected static final String ENCRYPT_KEY = "3pkjRkulbauaFLna/jfaxw==";
	
	public static void main(String[] args) throws Exception {
		test();
	}
	
	public static void test() {
		String serviceUrl = "http://ip:port/api/mall/b2b/zfw/payment/status/query/V1";
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA", MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, "AES", ENCRYPT_KEY, "", "");
		MallB2bPaymentStatusQueryRequestV1Biz bizContent = new MallB2bPaymentStatusQueryRequestV1Biz("REG201901250110001", "10002778", "20009602");
		MallB2bPaymentStatusQueryRequestV1 request = new MallB2bPaymentStatusQueryRequestV1();
		request.setServiceUrl(serviceUrl);
		request.setBizContent(bizContent);
		
		MallB2bPaymentStatusQueryResponseV1 response;
		try {
			logger.info("Request: " + JSONObject.toJSONString(bizContent));
			response = client.execute(request, System.currentTimeMillis() + "");
			logger.info("Response: " + JSONObject.toJSONString(response));
			if (response.isSuccess()) {
				System.out.println("execute success");
			} else {
				System.out.println("execute failue");
			}
		} catch (IcbcApiException e) {
			logger.error("execute exception" + e);
			e.printStackTrace();
		}
		
	}
	
}
