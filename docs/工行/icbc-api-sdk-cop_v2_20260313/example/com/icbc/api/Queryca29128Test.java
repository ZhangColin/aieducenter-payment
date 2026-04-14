package com.icbc.api;

import java.util.List;

import com.icbc.api.request.Queryca29128RequestV1;
import com.icbc.api.request.Queryca29128RequestV1.Queryca29128RequestV1Biz;
import com.icbc.api.request.Queryca29128RequestV1.Queryca29128RequestV1Pub;
import com.icbc.api.request.Queryca29128RequestV1.Queryca29128RequestV1In;
import com.icbc.api.response.Queryca29128ResponseV1;
import com.icbc.api.response.Queryca29128ResponseV1.Queryca29128ResponseV1Pub;
import com.icbc.api.response.Queryca29128ResponseV1.Queryca29128ResponseV1Out;
import com.icbc.api.response.Queryca29128ResponseV1.Queryca29128ResponseV1Detail;

public class Queryca29128Test {

	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCaXN+0wqwPGjeJwL4b7pf5KVnC3ObKEkvl7N1flLpMVWRVU5CZTdwsuzyxD3Licr//J3q1dyhj27DKKtHRARFr1DjFKwnT9Umiln2bUKPmxQmIJDuH12gi3BmcAsLa2wmFlMOfSYf8dHv0Qqg139TSsPMxHVfts8ialynWy1+I2W6DUw1WxXYcS61uRAIsOaI/V4FWV3kiI5KCKPWHffspN8BJy6IgVtYJlTvZGPxxeSqRYcS3i8jOGiqBGn2KSxvoEFON4wFz0O87XUJJdWq6qlk5WQty5jU1YVRCKNfGdbQ5ZFZORaq//0A5AfU+sTor9nYpiECZshFKmCOL0Yk3AgMBAAECggEAdY918DyyvW3CDm90WeGEbIZJjlaejhcRJrMWhczHdYszNqjF33uky4xt1Itne37ZKoHuunVdlW+jXNTvprWhhvXBpqf4fCy7P9BNKRRpPz+g2sZ9cB9ld+cYufCAZh2bStjZg16E6ofsP0LkyOMHOs7459bpOGdLyDCH9MY+pAF8zxIarVuFgwXDRGYmrLPIxvQoORcEsm83BlPzx2ZWho2ycDyBtZr4CdSJMGY/nR4PqKr4ZxPpetl+RjNleUR/tX89Si5HH7fIAur8CmZ7DB5TKCz42lnhEdF/36jxT2wLF7mzowu4GATW56245cPAYKZrgIQvAwPAF+W6DHx2gQKBgQD8wpzNuc+yFZhIBBtzlEQP3ex7/cP6jOLtcTpvxj8KlcAA7GhwIO4XNO3iU02wc25oiioLuIGqndhxd/0l90GTcrfxAg1CK9WbWrlq93acgwa3PxedN3dT8yPYN/1uoDpe+2KOaWzrGvSw3Y0Zy5XC+tUQQszq+900ytKZlDx1HwKBgQCcV2NFusy4At4zlynasZd/c11nBEzEKox2/+NZ1Uy8ui42c21cfwlRC32FuuSIFlhTSewtRWM8ronkiATJNa3HrcUbJJRgTUfQJnYi9MHYQcRhUYfLqn5KXURpCS48qyYQvXguvMLxxFLSwACxKydqXgafrhviuv3BJyc6BmcQ6QKBgQC2L3hieNoR3KrkzcETr/zXCvkX7jPCdqZiy0h5pIVwFbxNbhlhKPs0UvxcM/s9ghZE18GyrQ0Zc6i9MOWQ5gWoR/n5h6cS2RGgZuZbCX+/FFpU3u8gbQhfxFND2CeeA3PPXpcNW0QculNA4JCB1VJfNfk5PU0C2wP4t3DRLh3dtQKBgAX2Ixfulo3Rti1bb0IikXdPq9kYxeCZCsAi48VyJ7nuk4KKBjqHsZBAkum9hadWyslqQTI8uhr/QMzRDvWpMVYy3xg43duKNtnODaUplhar81QOqtHuT2CAi/JBYlz33by3rNSDaB+V796PabdXwOoCadg+6FtN7A0odAt3YQi5AoGBAK8hgOogeusHyk8XCvf/gBL8VPKVSTLYoos62dqhtfRf4+nK79g9Ix3v3hQcUypNzKX2i1nckngn/L6O4/eNkZQbC1ksQV+vce/MgL4Hda24lwwf5kNhRkHk42ND0SIUQjuI5j63DsedRyzIl/04nihpWF5I/IsSeFwf2mJpt90E";

	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

	protected static final String APP_ID = "10000000000000002116";

	public void test_CA() throws IcbcApiException {
		
		String castr = "-----请使用正确的APP私钥-----DCTCCAfGgAwIBAgIKbaHKEE0tAAAVmTANBgkqhkiG9w0BAQUFADA3MRowGAYDVQQDExFjb3JiYW5rMTAzIHNkYyBDTjEZMBcGA1UEChMQY29yYmFuazQzLmNvbS5jbjAeFw0xMzA2MDEwNjE5NTFaFw0xNDA2MDEwNjE5NTFaMEAxFDASBgNVBAMTC0xMTEwuZS4wMjAwMQ0wCwYDVQQLEwQwMjAwMRkwFwYDVQQKExBjb3JiYW5rNDMuY29tLmNuMIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDUebVQUS8jnDERJopOwNgRlKEafEQUryj9EV64TUsaR850v/3KnctPRJ09GCqeucvbbOXlHz/RlwJSVWqryCTOldOUv1F58KQ0C59sY/dhh+W57fLIQKO90Sd344o/fPiytqmGtBc2m+DE/3L6morgC8m05Ygm16MkIk89Nz184wIDAQABo4GRMIGOMB8GA1UdIwQYMBaAFKnyXV7yfyOkd7D4zZtPLyquqLWdMEwGA1UdHwRFMEMwQaA/oD2kOzA5MQ0wCwYDVQQDEwRjcmw2MQ0wCwYDVQQLEwRjY3JsMRkwFwYDVQQKExBjb3JiYW5rNDMuY29tLmNuMB0GA1UdDgQWBBTkDBRsd9NghIrtNaUe6gSxZQ9CfzANBgkqhkiG9w0BAQUFAAOCAQEAXTWymvrTDMgV9LK7Ps6o52mlZIPmp3n7hmZttgJR/6KmZ/uCChPqHd9Ixw3DBnzHvoxmgtCKNVNc+iYQ4ks8cZgQoQ3uKT9bYinRCgECOv0Hiv7Q63DHJB46QamYcPc9dmmKAAOMd5AtnKI8QBRG3kxEmD6DPAcyx7hZ9Iw0MVwu4J1RfByJ1kM/bnhFpGwTma+5kxQlP+8Zurx4Cow/TUIj+kiLa/1ZmKXok7XOUr1UTFJhIqe0v3w2ekidchVML/t6n6Yw8Q5UCAbvKP4iHWdxeEGYsn+/a38oqCqIya66d5FCUqcOXXdRTdwdaSg6IGA4X6//O9TCvle1SCn7LQ==";

		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,
				"AmEvvpDym7qi4Tmw/gO9v1/Ku8R3Qt46t16I6FA/CPwLopau7fNKcUxYCL9pqjb76phR4NPBr+Fa9O3PqZ5bJm7/io3hwqnmtu9KHs57HO6HOXycjQi9ZH4fqVZJbPtXQpGGXZhX1VB5teIcoKf5PxWgahBH2p7GnBZA7VgpO/1z2ZkxbqR1yUNR+gq2MpJQvMSab07+Vgz9l9dYDsl1UX6RqsAN7IgpZoZhlSpmxvt04i/um78hrGVuf0Y/KqACxZbyS8rz2nE8m1R1xys8wziEu18hKVZtl0v3yKTjvAqDuwV2aFXjumfkcsQTHLnPk0X/BdTpNSDDgDkw1p9nQhYrPTYUe05U3WyZBBDLmeJ/Niuj3RrPkc6z96TmLRdL/gzHoedeMtcjWHFbZLh8vGi9FLzKaRQo2/AbYqT2DWAatw0nDePsQk+9Av7ZS+7vZ51h5cv16ZP8lge5z1DeAYxMjV3RB5He5u8NPgehPd26bZQv7PUmyn2Br3SS6SAZcqvrN4dcLwV+Xb6QnjHEV5eUeEKky6qy/EwUC6DPJi/PSFPkUbzDR3KlJGw35f0oQbulJ3NnYv2UQ+wf3WhBQu82uGw7MKJrQDjE4bv018ycI47tFsW1kTqVNs1LNpafRcZbizfEphEbynz0UvQiL0YORUqT5RaS1pSYvCo1qXpCNILmW4bQ0Hz3W8eEDYpjSmBWnQTOpDHVfm0rXFGMsJxJRfGiSZh5gSQTbgEsTCa/Wauzx+tgL39EYYKVQn+1wQHQEZ1NvbPbzB6KHOG9XahzUcgMGb51t6zz+hX8y712lfE=",
				APIGW_PUBLIC_KEY, castr, "12345678");
		
		Queryca29128RequestV1 request = new Queryca29128RequestV1();
		request.setServiceUrl("http://ip:port/api/queryca29128/query/V1");

		Queryca29128RequestV1Biz bizContent = new Queryca29128RequestV1Biz();
		
		Queryca29128RequestV1Pub pub = new Queryca29128RequestV1Pub();
		pub.setCode("29128");
		pub.setZone("0200");
		pub.setFr("0");
		pub.setDt("2018-12-26");
		
		Queryca29128RequestV1In in = new Queryca29128RequestV1In();
		in.setLineflag("2");
		in.setTydwno("123412413131000");
		in.setDateb("2017-07-06");
		in.setDatee("2017-07-06");
		in.setQuerytype("1");
		
		bizContent.setPub(pub);
		bizContent.setIn(in);
		
		request.setBizContent(bizContent);

		Queryca29128ResponseV1 response = client.execute(request, "msgId");

		if (response.isSuccess()) {
			// 接收成功
			Queryca29128ResponseV1Pub RetPub = response.getPub();
			Queryca29128ResponseV1Out RetOut = response.getOut();
			if(RetOut.getResult().equals("00000000")){
				//查询成功 获取返回结果
				List<Queryca29128ResponseV1Detail> resultList = RetOut.getDetail();
				//获取结果集中字段
				for(Queryca29128ResponseV1Detail detail:resultList){
					//获取信息.....
					detail.getTYDWNO();
				}
			} else {
				//查询失败
				System.out.println(RetOut.getResult());
			}
		} else {
			// 失败
			System.out.println(response.getReturnMsg());
		}
	}
}
