package icbc.api;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcConstants;
import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.TipsTaxActiveTaxPayQueryRequestV1;
import com.icbc.api.response.TipsTaxActiveTaxPayQueryResponseV1;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 自助渠道主动缴税申报查询接口
 * 
 * 请商户配置SDK中的lib文件夹中的jar包后，根据备注1-6提示进行数据替换
 */
public class TipsTaxActiveTaxPayQueryTest {
	
	//1、网关公钥
	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

	//2、appid
	protected static final String APP_ID = "10000000000004098264";

	//3、密钥对认证方式，公钥在API平台登记，此处为私钥
	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCkTcMeaGHKV89swARJarXBnf2PRFRDc6YjcEHOlLuo2ZSR8aPiPBqz3UUX3vLj+Xej2r2byK5JiTe3EXTOkXLPK006PIzZPc2kr28c0jfgFTjoYOHEAiUxDpHHZ+3G7WwDORdnjuVYj+rVKrSwfdmSQB5DxIvQIKrHkaCdfGZIHEWb5H8l+YTQYfJcs1cqYknIPf9Ut89OAgQPCVwzfcOpuiPmKa7D+dFyU0W17Zx0BPuntzfcm/TMRWfGwiGwiRmIjE437dJ/aqzvC8qBKjHrMRN/dukt5ZxmxE0gSaxrzaVXL9clhTsVUm6R+ToqVduCa62RSRJKjdnI0SLYapSXAgMBAAECggEAF9eI5tKyzg/r85XxiU3Izi3E65M4vwj7JM1XX0jJ6niYTI1KALxEC7GiIxKhIF/E8qv9M6vtTpJw2UnZL1h/1nwsGrVKowOK4cjrUL1YilXKCj50xGKjTvkUQmFcaa9uKxGPyeE8ept3sjMeQTYh6k0PlzngJ/V1krwZGIFCvlfPi8vdDx5I8GUT6kSzlo41Y/6slhu0nfVWQq9k4/3HngPV5kN4WBm3XAXIFo66szUSjMVqfjJ6sCH8Kq9+PqsDGxr0HfWalKe2DATZVipysvMER3Bd9qtqLDuu19giQ0l9eDbsZxsMnLSi0vXNEZ77swleG0ViRXL/S27AjnsZwQKBgQDyuJUNihDj0ElxO6Bw1YM2Agy0GMO0gn+F8IFkckwhbWP+OFtiv+zM1u7dFyZ3B/rJyowzAQMyzHS/xMtvOr7ZX7cMe4JpktG/6N6+IuJFLyUYspkC84sJFkuO4zbQP8Yr0jvR6loHDvagfL40K1xEj3l0tgK5Sy7pGlvFxUlvBwKBgQCtSukg4Ru4PXh9RTyFOkfmwpVqwyIQFxmApiiUAl+tAkKNbBYnhnHs0U6ntZVP5ueHlA6O9n1UlXd+DL2MxOXt8hP/woLC06mYxlqPD05qdnEbvKTmoXhu0lU8H+vFTnA5IxClSnk44HoTHTxQk8gvQaDzHnp5p6Ju3cflyku58QKBgHFs9KqpFI+BbHCv7qAMBw8cEhevYvVd7n4HEFCCYTVVy16ns30IN37aCjHjeaQyZG4cazwCgx4gEYb0TiUijMFES2RX62+unrpR9pVf8+Fk0K8j9wE8EoMimhih8aGLy+36ht3n6D5YLcR9xtzWTn3zlkyGKeuxSLDCEpTvRwvNAoGAAz9AFwmgx1b/hvlhJO3uovhKbHGzlbGZHLwATqWsvUUpkhTQZXUzdUiGvMW+Qn0FIZk4nJ0pxAf48Tez5Yta85ZxoX03jgcBSoeM8tlKktwdSnPCcz/bTdhLGJFQjaXmnGt3yGEY3GP4ZZtKogfi3F/ScDvF97mYZZNqR3f+60ECgYEAh1ILWFAN1b9TEDuSZNjy6Qljfk/mpokdyioNavjl+IPDMTmcvUIXP1rOHQS99INLCLl/BtxwGu+/Gdb9Z/iosrl410adnXH/NkfAezR5yhlV0Xabo6UsQ2vuCV27vTjej2FS1e7ytkzjxuhwpPicLJ4nuvGbOf+csLWyNQBNUZQ=";
	//4、服务url
	protected static final String URL = "http://ip:port/api/mybank_pay/tipstax/activetaxpayquery/V1";
	// 加密类型和秘钥
	protected static final String encryptKey = "vyy6YTzVU5wJiknWqNTLPg==";

	public static void main(String[] args) throws Exception {
		// 签名类型为RSA2时，需传入APIGW-APPID，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
//		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY,
//				APIGW_PUBLIC_KEY);
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY,
				IcbcConstants.CHARSET_UTF8,
				IcbcConstants.FORMAT_JSON, APIGW_PUBLIC_KEY,
				IcbcConstants.ENCRYPT_TYPE_AES,
				encryptKey, null, null);
		TipsTaxActiveTaxPayQueryRequestV1 request= new TipsTaxActiveTaxPayQueryRequestV1();
		// 4、根据测试环境和生产环境替换相应ip和端口
		request.setServiceUrl(URL);
		// 5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
		TipsTaxActiveTaxPayQueryRequestV1.TipsTaxActiveTaxPayQueryBiz bizContent = new TipsTaxActiveTaxPayQueryRequestV1.TipsTaxActiveTaxPayQueryBiz();
		TipsTaxActiveTaxPayQueryRequestV1.TipsTaxActiveTaxPayQueryBiz.Msg msg = new TipsTaxActiveTaxPayQueryRequestV1.TipsTaxActiveTaxPayQueryBiz.Msg();
		bizContent.setTrxCode(TipsTaxActiveTaxPayQueryRequestV1.TRX_CODE);
		bizContent.setMsg(msg);
		msg.setTrxCode("95178A");
		msg.setAppCode("10");
		msg.setTrxOrigin("7020");
		msg.setZoneNo("01202");
		msg.setBrNo("00226");
		msg.setOperNo("00487");
		msg.setTaxOrgCode("5593571617");
		msg.setTaxPayCode("24600022100");
		msg.setCorpCode("1");
		msg.setOuterLevyNo("5593571617");
		msg.setEntrustDate("2023-07-02");
		request.setBizContent(bizContent);
		TipsTaxActiveTaxPayQueryResponseV1 response;
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
			String msgId = dateFormat.format(new Date());
			response = client.execute(request, msgId);
			System.out.println(JSON.toJSON(response));
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}
	}

}


