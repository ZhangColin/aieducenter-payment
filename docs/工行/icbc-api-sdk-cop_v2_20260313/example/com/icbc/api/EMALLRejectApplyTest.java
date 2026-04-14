package com.icbc.api;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.icbc.api.internal.apache.http.client.ClientProtocolException;
import com.icbc.api.request.EMALLRejectApplyRequestV1;
import com.icbc.api.request.EnterpriseBranchRecoverGroupinfoRequestV1;
import com.icbc.api.response.EMALLRejectApplyResponseV1;
import com.icbc.apip.config.SdkUtil;
import com.icbc.apip.exception.ConfigException;
import com.icbc.apip.exception.InvokerException;
import com.icbc.apip.invoker.DefaultInvoker;
import com.icbc.apip.invoker.Invoker;
import com.icbc.apip.invoker.Invokers;
import com.icbc.apip.invoker.JsonBuilder;
//import org.junit.Test;


public class EMALLRejectApplyTest {

	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCaXN+0wqwPGjeJwL4b7pf5KVnC3ObKEkvl7N1flLpMVWRVU5CZTdwsuzyxD3Licr//J3q1dyhj27DKKtHRARFr1DjFKwnT9Umiln2bUKPmxQmIJDuH12gi3BmcAsLa2wmFlMOfSYf8dHv0Qqg139TSsPMxHVfts8ialynWy1+I2W6DUw1WxXYcS61uRAIsOaI/V4FWV3kiI5KCKPWHffspN8BJy6IgVtYJlTvZGPxxeSqRYcS3i8jOGiqBGn2KSxvoEFON4wFz0O87XUJJdWq6qlk5WQty5jU1YVRCKNfGdbQ5ZFZORaq//0A5AfU+sTor9nYpiECZshFKmCOL0Yk3AgMBAAECggEAdY918DyyvW3CDm90WeGEbIZJjlaejhcRJrMWhczHdYszNqjF33uky4xt1Itne37ZKoHuunVdlW+jXNTvprWhhvXBpqf4fCy7P9BNKRRpPz+g2sZ9cB9ld+cYufCAZh2bStjZg16E6ofsP0LkyOMHOs7459bpOGdLyDCH9MY+pAF8zxIarVuFgwXDRGYmrLPIxvQoORcEsm83BlPzx2ZWho2ycDyBtZr4CdSJMGY/nR4PqKr4ZxPpetl+RjNleUR/tX89Si5HH7fIAur8CmZ7DB5TKCz42lnhEdF/36jxT2wLF7mzowu4GATW56245cPAYKZrgIQvAwPAF+W6DHx2gQKBgQD8wpzNuc+yFZhIBBtzlEQP3ex7/cP6jOLtcTpvxj8KlcAA7GhwIO4XNO3iU02wc25oiioLuIGqndhxd/0l90GTcrfxAg1CK9WbWrlq93acgwa3PxedN3dT8yPYN/1uoDpe+2KOaWzrGvSw3Y0Zy5XC+tUQQszq+900ytKZlDx1HwKBgQCcV2NFusy4At4zlynasZd/c11nBEzEKox2/+NZ1Uy8ui42c21cfwlRC32FuuSIFlhTSewtRWM8ronkiATJNa3HrcUbJJRgTUfQJnYi9MHYQcRhUYfLqn5KXURpCS48qyYQvXguvMLxxFLSwACxKydqXgafrhviuv3BJyc6BmcQ6QKBgQC2L3hieNoR3KrkzcETr/zXCvkX7jPCdqZiy0h5pIVwFbxNbhlhKPs0UvxcM/s9ghZE18GyrQ0Zc6i9MOWQ5gWoR/n5h6cS2RGgZuZbCX+/FFpU3u8gbQhfxFND2CeeA3PPXpcNW0QculNA4JCB1VJfNfk5PU0C2wP4t3DRLh3dtQKBgAX2Ixfulo3Rti1bb0IikXdPq9kYxeCZCsAi48VyJ7nuk4KKBjqHsZBAkum9hadWyslqQTI8uhr/QMzRDvWpMVYy3xg43duKNtnODaUplhar81QOqtHuT2CAi/JBYlz33by3rNSDaB+V796PabdXwOoCadg+6FtN7A0odAt3YQi5AoGBAK8hgOogeusHyk8XCvf/gBL8VPKVSTLYoos62dqhtfRf4+nK79g9Ix3v3hQcUypNzKX2i1nckngn/L6O4/eNkZQbC1ksQV+vce/MgL4Hda24lwwf5kNhRkHk42ND0SIUQjuI5j63DsedRyzIl/04nihpWF5I/IsSeFwf2mJpt90E";

	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

	protected static final String APP_ID = "10000000000000002116";
//
//	
//	public void test_cop() throws IcbcApiException {}

		public static void main(String[] args) throws Exception {
			//SDK数据接口环境配置信息录入
			System.setProperty(SdkUtil.SDK_CONFIG_FOLDER_PATH, "config");
			DefaultInvoker invoker = Invokers.getDefaultInvoker();
			invoker.setUri("/enterprise/shopserv/emallrejectapply/V1");
			invoker.setMethod(Invoker.HttpMethodType.POST);

			try {
				String req_json1 = JsonBuilder.create()
						.add("PUBLIC.apiCode", "API00172")
						.add("PUBLIC.channelID", "IP.20190906093519.81170")
						.add("PUBLIC.from", "F-WAPB")
						.add("PUBLIC.to", "F-EBANKC")
						.add("PUBLIC.reqDate", "2019-10-22")
						.add("PUBLIC.reqTime", "10:10:10")
						.add("PRIVATE.reject_count","1")
						.add("PRIVATE.emallrejectid","100")
						.build();
				String req_json = JsonBuilder.create()
						.add("reqData", req_json1 )
						.add("reqDataDes", "" )
						.build();
				invoker.addParameter("biz_content", req_json);

				System.out.println(invoker.toString());
				//调用方法
				Map<String, Object> resp = invoker.syncInvoke();

				System.out.println(resp);
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (InvokerException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ConfigException e) {
				e.printStackTrace();
			}
	}
}
