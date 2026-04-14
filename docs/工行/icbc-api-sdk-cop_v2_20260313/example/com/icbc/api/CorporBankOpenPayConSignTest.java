package com.icbc.api;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import com.icbc.api.request.CorporBankOpenPayConSignRequestV1;
import com.icbc.api.request.CorporBankOpenPayConSignRequestV1.CorporBankOpenPayConSignRequestV1Biz;

public class CorporBankOpenPayConSignTest {

	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCaXN+0wqwPGjeJwL4b7pf5KVnC3ObKEkvl7N1flLpMVWRVU5CZTdwsuzyxD3Licr//J3q1dyhj27DKKtHRARFr1DjFKwnT9Umiln2bUKPmxQmIJDuH12gi3BmcAsLa2wmFlMOfSYf8dHv0Qqg139TSsPMxHVfts8ialynWy1+I2W6DUw1WxXYcS61uRAIsOaI/V4FWV3kiI5KCKPWHffspN8BJy6IgVtYJlTvZGPxxeSqRYcS3i8jOGiqBGn2KSxvoEFON4wFz0O87XUJJdWq6qlk5WQty5jU1YVRCKNfGdbQ5ZFZORaq//0A5AfU+sTor9nYpiECZshFKmCOL0Yk3AgMBAAECggEAdY918DyyvW3CDm90WeGEbIZJjlaejhcRJrMWhczHdYszNqjF33uky4xt1Itne37ZKoHuunVdlW+jXNTvprWhhvXBpqf4fCy7P9BNKRRpPz+g2sZ9cB9ld+cYufCAZh2bStjZg16E6ofsP0LkyOMHOs7459bpOGdLyDCH9MY+pAF8zxIarVuFgwXDRGYmrLPIxvQoORcEsm83BlPzx2ZWho2ycDyBtZr4CdSJMGY/nR4PqKr4ZxPpetl+RjNleUR/tX89Si5HH7fIAur8CmZ7DB5TKCz42lnhEdF/36jxT2wLF7mzowu4GATW56245cPAYKZrgIQvAwPAF+W6DHx2gQKBgQD8wpzNuc+yFZhIBBtzlEQP3ex7/cP6jOLtcTpvxj8KlcAA7GhwIO4XNO3iU02wc25oiioLuIGqndhxd/0l90GTcrfxAg1CK9WbWrlq93acgwa3PxedN3dT8yPYN/1uoDpe+2KOaWzrGvSw3Y0Zy5XC+tUQQszq+900ytKZlDx1HwKBgQCcV2NFusy4At4zlynasZd/c11nBEzEKox2/+NZ1Uy8ui42c21cfwlRC32FuuSIFlhTSewtRWM8ronkiATJNa3HrcUbJJRgTUfQJnYi9MHYQcRhUYfLqn5KXURpCS48qyYQvXguvMLxxFLSwACxKydqXgafrhviuv3BJyc6BmcQ6QKBgQC2L3hieNoR3KrkzcETr/zXCvkX7jPCdqZiy0h5pIVwFbxNbhlhKPs0UvxcM/s9ghZE18GyrQ0Zc6i9MOWQ5gWoR/n5h6cS2RGgZuZbCX+/FFpU3u8gbQhfxFND2CeeA3PPXpcNW0QculNA4JCB1VJfNfk5PU0C2wP4t3DRLh3dtQKBgAX2Ixfulo3Rti1bb0IikXdPq9kYxeCZCsAi48VyJ7nuk4KKBjqHsZBAkum9hadWyslqQTI8uhr/QMzRDvWpMVYy3xg43duKNtnODaUplhar81QOqtHuT2CAi/JBYlz33by3rNSDaB+V796PabdXwOoCadg+6FtN7A0odAt3YQi5AoGBAK8hgOogeusHyk8XCvf/gBL8VPKVSTLYoos62dqhtfRf4+nK79g9Ix3v3hQcUypNzKX2i1nckngn/L6O4/eNkZQbC1ksQV+vce/MgL4Hda24lwwf5kNhRkHk42ND0SIUQjuI5j63DsedRyzIl/04nihpWF5I/IsSeFwf2mJpt90E";

	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

	protected static final String APP_ID = "10000000000000002116";
	
	protected static final String PASSWORD = "12345678";


	public void test_cop() throws IcbcApiException {

		// 构造client对象
		String castr = APIGW_PUBLIC_KEY;
		// 去除签名数据及证书数据中的空格 added for Safari
		Pattern p = Pattern.compile("\\s*|\t");
		Matcher m2 = p.matcher(castr);
		castr = m2.replaceAll("");
		UiIcbcClient client = new UiIcbcClient(APP_ID, MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8, castr, PASSWORD);
	
		// 设置请求对象request
		CorporBankOpenPayConSignRequestV1 request = new CorporBankOpenPayConSignRequestV1();
		// 设置请求路径
		request.setServiceUrl("http://ip:port/ui//enterprise/openpay/con/sign/V1");
	
		CorporBankOpenPayConSignRequestV1Biz bizContent = new CorporBankOpenPayConSignRequestV1Biz();
	
		bizContent.setApi_name("JSON");
		bizContent.setApi_version("001.001.001.001");
		bizContent.setFile_code("0200ED1234567");
		bizContent.setFile_name("XXX企业");
		bizContent.setAcct_num("0200004012345678901");
		bizContent.setAcct_num_name("XXX交易平台");
		bizContent.setAcct_orgcode("1234567890");
		bizContent.setFile_time("20180128132010");
		bizContent.setFile_serialno("1234567890876543");
		bizContent.setIs_sign("1");
		bizContent.setTradeplat_name("XXX交易平台");
		bizContent.setTradeplat_code("0200EE1234567");
		
		request.setBizContent(bizContent);
	
		// 生成自提交的表单返回客户浏览器，该表单会自动提交完成调用
		System.out.println(client.buildPostForm(request));
	}
}
