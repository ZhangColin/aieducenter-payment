package com.icbc.api;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Assert;
import org.junit.Test;

import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.ApplePayTransferRequestV1;
import com.icbc.api.request.ApplePayTransferRequestV1.ApplePayTransferRequestV1Biz;
import com.icbc.api.request.ApplePayTransferRequestV1.ApplePayTransferRequestV1Custom;
import com.icbc.api.request.ApplePayTransferRequestV1.ApplePayTransferRequestV1Message;
import com.icbc.api.request.ApplePayTransferRequestV1.ApplePayTransferRequestV1OrderInfo;
import com.icbc.api.response.ApplePayTransferResponseV1;

public class ApplePayTransferTest {

	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

	protected static final String APP_ID = "10000000000000002135";


	public void test_CA() throws IcbcApiException {
		String castr = "-----请使用正确的APP私钥-----DCTCCAfGgAwIBAgIKbaHKEE0tAAAVmTANBgkqhkiG9w0BAQUFADA3MRowGAYDVQQDExFjb3JiYW5rMTAzIHNkYyBDTjEZMBcGA1UEChMQY29yYmFuazQzLmNvbS5jbjAeFw0xMzA2MDEwNjE5NTFaFw0xNDA2MDEwNjE5NTFaMEAxFDASBgNVBAMTC0xMTEwuZS4wMjAwMQ0wCwYDVQQLEwQwMjAwMRkwFwYDVQQKExBjb3JiYW5rNDMuY29tLmNuMIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDUebVQUS8jnDERJopOwNgRlKEafEQUryj9EV64TUsaR850v/3KnctPRJ09GCqeucvbbOXlHz/RlwJSVWqryCTOldOUv1F58KQ0C59sY/dhh+W57fLIQKO90Sd344o/fPiytqmGtBc2m+DE/3L6morgC8m05Ygm16MkIk89Nz184wIDAQABo4GRMIGOMB8GA1UdIwQYMBaAFKnyXV7yfyOkd7D4zZtPLyquqLWdMEwGA1UdHwRFMEMwQaA/oD2kOzA5MQ0wCwYDVQQDEwRjcmw2MQ0wCwYDVQQLEwRjY3JsMRkwFwYDVQQKExBjb3JiYW5rNDMuY29tLmNuMB0GA1UdDgQWBBTkDBRsd9NghIrtNaUe6gSxZQ9CfzANBgkqhkiG9w0BAQUFAAOCAQEAXTWymvrTDMgV9LK7Ps6o52mlZIPmp3n7hmZttgJR/6KmZ/uCChPqHd9Ixw3DBnzHvoxmgtCKNVNc+iYQ4ks8cZgQoQ3uKT9bYinRCgECOv0Hiv7Q63DHJB46QamYcPc9dmmKAAOMd5AtnKI8QBRG3kxEmD6DPAcyx7hZ9Iw0MVwu4J1RfByJ1kM/bnhFpGwTma+5kxQlP+8Zurx4Cow/TUIj+kiLa/1ZmKXok7XOUr1UTFJhIqe0v3w2ekidchVML/t6n6Yw8Q5UCAbvKP4iHWdxeEGYsn+/a38oqCqIya66d5FCUqcOXXdRTdwdaSg6IGA4X6//O9TCvle1SCn7LQ==";
		// 去除签名数据及证书数据中的空格 added for Safari
		Pattern p = Pattern.compile("\\s*|\t");
		Matcher m2 = p.matcher(castr);
		castr = m2.replaceAll("");

		DefaultIcbcClient client = new DefaultIcbcClient(
				APP_ID,
				"AmEvvpDym7qi4Tmw/gO9v1/Ku8R3Qt46t16I6FA/CPwLopau7fNKcUxYCL9pqjb76phR4NPBr+Fa9O3PqZ5bJm7/io3hwqnmtu9KHs57HO6HOXycjQi9ZH4fqVZJbPtXQpGGXZhX1VB5teIcoKf5PxWgahBH2p7GnBZA7VgpO/1z2ZkxbqR1yUNR+gq2MpJQvMSab07+Vgz9l9dYDsl1UX6RqsAN7IgpZoZhlSpmxvt04i/um78hrGVuf0Y/KqACxZbyS8rz2nE8m1R1xys8wziEu18hKVZtl0v3yKTjvAqDuwV2aFXjumfkcsQTHLnPk0X/BdTpNSDDgDkw1p9nQhYrPTYUe05U3WyZBBDLmeJ/Niuj3RrPkc6z96TmLRdL/gzHoedeMtcjWHFbZLh8vGi9FLzKaRQo2/AbYqT2DWAatw0nDePsQk+9Av7ZS+7vZ51h5cv16ZP8lge5z1DeAYxMjV3RB5He5u8NPgehPd26bZQv7PUmyn2Br3SS6SAZcqvrN4dcLwV+Xb6QnjHEV5eUeEKky6qy/EwUC6DPJi/PSFPkUbzDR3KlJGw35f0oQbulJ3NnYv2UQ+wf3WhBQu82uGw7MKJrQDjE4bv018ycI47tFsW1kTqVNs1LNpafRcZbizfEphEbynz0UvQiL0YORUqT5RaS1pSYvCo1qXpCNILmW4bQ0Hz3W8eEDYpjSmBWnQTOpDHVfm0rXFGMsJxJRfGiSZh5gSQTbgEsTCa/Wauzx+tgL39EYYKVQn+1wQHQEZ1NvbPbzB6KHOG9XahzUcgMGb51t6zz+hX8y712lfE=",
				APIGW_PUBLIC_KEY, castr, "12345678");

		ApplePayTransferRequestV1 request = new ApplePayTransferRequestV1();
		request.setNotifyUrl("https://www.scgsj.com/notify.do");
		request.setServiceUrl("http://ip:port/api/apple/pay/V1/transfer");
		request.setPaymentTokenData("xxxxxxxxxxxx");

		ApplePayTransferRequestV1Custom custom = new ApplePayTransferRequestV1Custom();
		custom.setLanguage("zh_CN");
		custom.setVerifyJoinFlag("0");
		
		ApplePayTransferRequestV1Message message = new ApplePayTransferRequestV1Message();
		message.setGoodsID("4287671984");
		message.setGoodsName("超市");
		message.setGoodsNum("1");
		message.setCarriageAmt("0");
		message.setMerHint("0");
		message.setRemark1("");
		message.setRemark2("");
		message.setMerVAR("TEST");
		message.setNotifyType("HS");
		message.setResultType("0");
		
		ApplePayTransferRequestV1OrderInfo orderInfo = new ApplePayTransferRequestV1OrderInfo();	
		orderInfo.setOrderDate(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
		orderInfo.setOrderId("888810222");
		orderInfo.setAmount("110");
		orderInfo.setInstallmentTimes("1");
		orderInfo.setCurType("001");
		orderInfo.setMerID("0200EG0000602");
		orderInfo.setMerAcct("6212880200000038618");
		
		ApplePayTransferRequestV1Biz bizContent = new ApplePayTransferRequestV1Biz();
		bizContent.setCustom(custom);
		bizContent.setMessage(message);
		bizContent.setOrderInfo(orderInfo);

		request.setBizContent(bizContent);

		ApplePayTransferResponseV1 response = client.execute(request, "msgId");
		Assert.assertEquals(response.getOrderInfo().getAmount(), "100");
		System.out.println(JSONObject.toJSONString(response));

	}

}
