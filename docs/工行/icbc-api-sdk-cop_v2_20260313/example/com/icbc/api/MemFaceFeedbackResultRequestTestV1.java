package com.icbc.api;

import com.icbc.api.request.CardbusinessMerMemberScenicfaceresultgetRequestV1;
import com.icbc.api.request.CardbusinessMerMemberScenicfaceresultgetRequestV1.MemFaceFeedbackResultRequestV1Biz;
import com.icbc.api.response.CardbusinessMerMemberScenicfaceresultgetResponseV1;

public class MemFaceFeedbackResultRequestTestV1 {
	
	public static void main(String[] args){
		// 企业交易秘钥
		String corpPayKeyAppId = "10000000BCSS000004100423";
		String corpPayKeyRsaPriKey = "-----请使用正确的APP私钥-----EvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCpGWfAr2iuXwu7xofZowEdve+5+3+Z5vEMHRI7ObAm5ZGyPJZhL34lf2xNZh7jfmZjid6rbuSq0WsEPTI0j/DGTxOhQuPJyPIVD+4gQZRhdaMT+SiNED5E6TK+l3xDhsOUpTZcXLT0zqgOXJ883DJOEiO01UBJyioX1C6Bob4fH483QnVjpuboWZHXDVLbrnGsmigsSS6v+VCwkDUejAOxc3sYBnFN8EshEpEbBfy+kg8rFdKhGueQlCfCvzGKz8HGp81C0uYzVYIS9wtidRazzA5QEPBurXgsOv/SPvzxXsEuYp6/srLSTje+wE+akxLcpA7ZFCWEKxG108ml5pbzAgMBAAECggEBAKbUy8oMxQiT+S2PV2RfL7C6PZOOD3w1nsfGvr/z0Fjv0Wi54duMUbaJGr7Z68uiXz4BPdCBHdVxu1V5UtkM0g204rcNGVNxj3U8PEFSsiPdPcFs5pcV2Xl/YXMsXYr5oGg8/37scBO+94sD0o7m6DKiLtwwMLccX58W1bNLchmdF8ycmm1RsGcvH73JEd7OXMTvGz0OC5q+NF+3w4CysWxQQdCbLYPuY4E0gVhlbl1E3JIQUAYsKWoMMmZDooSkMnexL/6tvKX+P//bNmPIMMQ9ip8O0JWQfKNVurPUx7Prhk35vRQic98aKZfxR/3KMnFlqTWuy5HBruaFYlF7xGECgYEA8zqXkbDo3+hAFORnr3U/uEUWkDwZT6JOl5Bf+v9/g78b2HA5/gDFpK6iptjNLdugjduC0CdKAP2wRGt1FJpXsCjos90cN6Rs4i0XXw8+dyWQTDd+dEJu43tGlVu70G3ttdr+g53RqKS2bqUKjqvvIRIOIPS/E58KDzySFQNqZXkCgYEAsfpiqO9HiOKxGgJo42QGO3Q/Y9lgWqzc6B9zj9aRuLwZ7OGBX7DMc/GiJf4wtFUsDEEuxJcVFtpDD02a3RtEImNhQ+OUonWvNwIHBQZutVyXMNG4C1jose1nXq6L3s46T2FLwI1YFHE9w6jyIhTCflUAjPaNjl9/cbKDzHlhIMsCgYAJUqWcn/tNGcnbAyY2Q3g9x+glfyg5NS9t/8quBD0O/7RBh4uxgLYSqd7yJj03FMQCq/dHozni79ZhyPyj3Yae6fKBcmgyecryoGfU7PCoAHiXc0kVi7ZUYdVCxFFem9gQsGQxB0uR+z5C33CYJ17zFY4VFUx9pAruqpOu2oKpgQKBgCDyXPkw8mkgrfBGqewORCkVZ/av8upWPb6qs9XYvrkX+SSkyjJQpEiCXqXx3EpFyhWqq2GxJjJIzUkz8Isl1QMYqpTKuGUqq/je4zXG7w+byFE9QXi4SLtHW29nmHTycFDpjgBjKf7+x5RE3i348dg5HBT8P1Av1YPSrBtaDOTpAoGBALfSZ3BnLrbL1I6ccL0m/pwaSNHnemNolc2X+e3uVUF3QoSZDuyIS7E5mpJwJK11JXW09EgSx03fDSfkPFi3qqC45YrF21MzBXHj0M9oaaHr2ZztbYINqrgzPvhnVO48zoBI1AncVZwQu5UyKT82IkLQzQCr4AA6EN1msH1Ncfpm";
		
		// API gateway 公钥
		String apigwPublicKey = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
		
		// 构造请求
		MemFaceFeedbackResultRequestV1Biz biz = new MemFaceFeedbackResultRequestV1Biz();
		biz.setCorpId("0000000272");
		biz.setPersonIds("[\"14208389BCSS142976\",\"16257644BCSS520384\"]");
		biz.setStatus("0");
		// 请求IP地址，请根据测试环境和生产环境替换相应ip和端口
		String serviceUrl = "https://ip:port/api/cardbusiness/mer/member/scenicfaceresultget/V1";
		CardbusinessMerMemberScenicfaceresultgetRequestV1 requestV1 = new CardbusinessMerMemberScenicfaceresultgetRequestV1();
		requestV1.setBizContent(biz);
		requestV1.setServiceUrl(serviceUrl);
		requestV1.isNeedEncrypt();
		
		DefaultIcbcClient client = new DefaultIcbcClient(corpPayKeyAppId, "RSA2", corpPayKeyRsaPriKey, apigwPublicKey);
		try {
			CardbusinessMerMemberScenicfaceresultgetResponseV1 responseV1 = client.execute(requestV1);
			if (responseV1.isSuccess()) {
				//BcssLog.info("处理成功!");
			} else {
				//BcssLog.info("处理失败!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
