package com.icbc.api;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.icbc.api.request.MybankAccountElifeCreditcardcheckRequestV1;
import com.icbc.api.request.MybankAccountElifeCreditcardcheckRequestV1.MybankAccountElifeCreditcardcheckRequestV1Biz;
import com.icbc.api.response.MybankAccountElifeCreditcardcheckResponseV1;
import com.icbc.elife.web.api.customcard.ApiCustomCardApplyController;

public class ElifeMarketingQueryTestV1 {
	
	private  static final Logger logger = LoggerFactory.getLogger(ElifeMarketingQueryTestV1.class);

	// 1、网关公钥
	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

	// 2、appid
	protected static final String APP_ID = "";//例如 10000000000000002697

	// 3、密钥对认证方式，公钥在API平台登记，此处为私钥
	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----CdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIh0pL84ShSJqeoz01ef2b9PoDZKgEz396kXWa69tjz2jRHvhoD3beQRqTPnDYCn1FqT/vV4tElxXNfEPZcmDgGKUxMu8F+t3vyOKS6RKCWJYTddeljLYnPTLL7rPWEhHLPiTNFfkf5qIh3lca9SV1bnARNIc25DweZAcZCkot/3AgMBAAECgYBiLhsDgsq6cDqTSmq710XiwrXxmjUqLBhDz4s1kUZ4IqVpzD+ot16YBKULtSkf8qF18po1oH2Hgktc69Os1zKnkqBXq9PUGF6XRFqCSfus5EWXLKrg9puB02FqbBl6Vja+R0GrYl3f9zl6O7Fooj2p8y04Y7CV4boXcj8DVa+icQJBAOSkuFIp1ywpdr65KQMnxYXr2VissU78nDPFjSqTjxzNks0po/QXTB4HD1PXaxQHtbhiqjUjYui2eZ2lft5IPv0CQQCYyD0nZQoQUJlkoNYBMdFcYhjlJBjbhcX+yMQI5D7AO/OFsS+tmRCKAqluPWruuFJheLPtAtXNViSc2vVEx58DAkBWAVAEOBDkX8R3AZdXxIIcWhcrr+oSWBs5rjnnphHTN9Dcs6kkjA7pzKYLV6DDrBLDBPoZevIdr2YX0sTiQDLRAkBPm/8pmN9+ktDq+1entbK25bK9K2r8cB80XJ6xV2VPO63v2tzSIzDYCP0KIVyn2Umr2n4+oLyRSzK2vO0SksXLAkEAsaSosxPz80d7QVT8Dp2qcT7Zb2omxxJuL9JJSL9cqroIuTKhET7SNCk5DZ8dQRm/t0YM1qf1+Ghg23dXuFt/ZQ==";

		public static void main(String[] args) throws Exception {

			// 签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
			DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY,
					APIGW_PUBLIC_KEY);

			ElifeMarketingQueryRequestV1 request = new ElifeMarketingQueryRequestV1();
			// 4、根据测试环境和生产环境替换相应ip和端口
			request.setServiceUrl("https://ip:port/api/elife/marketing/query/V1");
			// 5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
			ElifeMarketingQueryRequestV1Biz bizContent = new ElifeMarketingQueryRequestV1Biz();
			bizContent.setSaleinfo("13011111234");// 营销人信息
			bizContent.setChannel("315");// 渠道
			request.setBizContent(bizContent);
			ElifeMarketingQueryResponseV1 response;
			try {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
				String msgId = dateFormat.format(new Date());
				response = (ElifeMarketingQueryResponseV1) client.execute(request, msgId);
				if (response.isSuccess()) {
					// 业务成功处理
					logger.info("ReturnCode:" + response.getReturnCode());
					logger.info("response:" + response);
				} else {
					// 失败
					logger.info("ReturnCode:" + response.getReturnCode());
					logger.info("ReturnMsg:" + response.getReturnMsg());
				}
			} catch (IcbcApiException e) {
				e.printStackTrace();
			}
	}
}
