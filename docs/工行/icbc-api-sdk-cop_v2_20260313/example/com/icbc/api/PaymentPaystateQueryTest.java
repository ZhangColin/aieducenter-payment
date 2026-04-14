package com.icbc.api;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.request.PaymentPaystateQueryRequestV1;
import com.icbc.api.response.PaymentPaystateQueryResponseV1;

public class PaymentPaystateQueryTest{
	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1588786734333856129L;
	
	public static void main(String[] args) {

		
		String appId ="aaaaaaa";
		String MY_PRIVATE_KEY ="-----请使用正确的APP私钥-----EvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCJpajuZwxgVzEbHJBhlwabIvFDP6Pznk6NBmZxxPpq9g+c0Rean6f6VrmoMD7LMZCc9TpoBuv3DArXndv7L6mkStGx4vlsRiPRrOgRwxa4RxFvJ8Z+T5sPACLeGl7Gse29IG7Fvd7k6ZvaS4PE/v0i5MkArgzOAWTYyFSDHcTCF0e+I97iSwrJZ0pB9DSjWfJkNFibnmDe3hV2eBKl+ctfFZs/9vj4jy0eQEbcK1J3OiHACK2Qs3C5iFFuoqRl1WNjrRe6u7rDxCIgVQ5rSYZVEhWsrujYK6Rk+PiumLZh+vi+m6kKnu+Uo/ugHXqd+oqQYN0I4knjlST9gsii5LJLAgMBAAECggEAT1fHfcy2rJ3192vKqgZVT32f1qTLi/VX0CC9Up05wAggTcOVOJMJT9tH33QX7iae9mrBBQ/gWqJ9aXZcRHlf7w0VvNVZ8xinMwjZKMvsv8JvgURLPaoDdm0crCxFL4GGybr1P1TIM+mY5gcbaKWXItMALj0lsmXJTNCFLqMtNLc+adRtGA6ulfx3sYIwX0Cn2NedSuHWvJCIcSTBwMg78b/vCyMR2hEGmrvXPrWfihdvkP0wdgy4iJ67nCIts5rs96NQMMFhKo2MUh4hDaiy9Md2AIaDX1OunXwbGIqyCEIUV/2VnmXUz6+DVHnCe+SWjbDE9FA6o6jVoamBFh3mIQKBgQDV2fsE565qdJYNwVZg1XUlig5yltdels17zdxG0T/h5YSfiwfdNdz6dLQEmfJC8kQwQUak3sQT+sFiaqT3VCWztRW5G3PQXHhFuwwi5OF6L3n4c9F1d+plzT9CZwZYVxJFf22SLmouoWLtqst5aT3UPXCZ5rHDeikQ1d+hMRRN2wKBgQCkxryTSDZZbg2Vj49K1ta0RR7X2GxvcXRdjWK189FApHvCojkYdZsvz5vtLm+jQ9S01beRXTZstWc53TkuvbFV+6OyixNzn6AdiOEbo/DJ2tTxRqGx+roxnwFiYXkufBPesPCBkJbwSAVB8tG0E7Uc0zluhFBJjwCGch49Rk0wUQKBgD5Vlpy8Z5OyPhbuVx5MuiPiFQoDu+1faa//toiR79wJydmzbexmCe6bW+nHcd1fvs6OCGiAYoksW3gze7h4N7ElRJq7TMvcWFaDCiuVqb+y3Qu1vsVN2e4ooJrbavcPEk1dsnOcweirtClw0nSmX5Zg/lRt8Tz4e8XjgZb7tl5nAoGAJTk0Hn2AsaE39mJw93IjHKREvHQKwv45UG0BMKN80QKXZreo+EqRHTLkQads1tAa/CMPjgololc0k5DTOyjlJ8xqBYp/gW49CI8Ec28MlT0XP8459u/yIgWOlX/5lTO4dWU5X7ufLXbHoUdOckTFdFaPMdU4IfXpI3YiOKtqApECgYAJxD+7TjKkL5eRQxJrqKVwmU963tJYbgakVj9Iyuu/5kcPWMscTtSOW8Nhp2uzQEgrQBR4Vmy17GVKc4au3k1NOM2BukJMd3SQWBzZCZbpK4FWGgIhBn49UkwAlxlxqRbwaRnXyMrwaeJWe8Fs5xZQw4pQlvzFPGz7/0Ye87d0Rg==";
//		String appId ="10000000000000002260";
//		String MY_PRIVATE_KEY ="-----请使用正确的APP私钥-----EvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCWi84Nrd9RYAnjhD/XoAjWZQEQ0BBxSvPGz4B8t+p8QSPWia+AqTkoy+LCHYBq6K1qbRoVwZn77DRCiCqlLjEOezZb+HQCeWrWO8XO81WL7I2JuBRYuZQez0h/9pa9SKM4maF3uqzW5Cpg6aX2qrfTIEDFDs1R1z18vDbwNhCv7gMui+oFmk4Fa8TczHglvLec/gUarH4Dus7x1BfocgM0Sggel21fEqc64jKHN2IzClzhfQ/sS7cHtbva5AS9BZH3SdWhVrSM/5pVyttIZHLw12icsE3Bz3ZollDlwGFZaJUkknaD2U39QxBUmUkKz4MS3PjX1jeTqSBTR0/h9lczAgMBAAECggEAVADUzGs91jFpsyuDWDUzWTZo/tGqqxzwo/n7LxyM2iKa9cZESN2kSHBkKn69vmnxAha43HT4EEt/6bQDX8JDm1oDPhSH3y2TANG/kbfT/0zSuYsex1deIEb3pHebKymR8i4/nAhtE0ejogoeEmK2SjeQDKUyyjz5UVXQrf7tFs0xb/ChWOIY4rUHrFY3iD3s8wYdyStR77bknmkS0dsPLMXRbT1OkyXzuEGnNolJ/bzaGIttH++iTG1AjeJaHhCw5lAWJDYICOzFrzs6otTwkHnWnlSJ9Udrw/sQAIKLFxL22Ebmi+1ZOat3mDRaqYDXH1/yrHOVUL9MC3tUTQ/jGQKBgQDozc/2zQUn5fjXiW+0SO5D3nrbCtPIQjHUD8qjNqlIyrf9NiA/XfAKiDVbMHPxKVyo9le42fveUoi0p4NAkOsjD9KnxVcKXQMuUvDZxeO9SX15zbv0Gd6n48k6zWXk9AZp6JYtq3o19X9BM4r422sd/AKCk2cJ30qQtHOy4MebxQKBgQCli9JSUZlixO0wAKN94CukFvoN9cTgflwcmFaUDpqmoVfeCqvzVJHvNgIeF17neLMzB7trHH/P4v9AkYyE6cSoIggSUw+3E1sAPSxN5f8QsR03l1BOah+3baw7tizU6Z8zErn9y19RGuDYXXMEDlp8HooVEfAp6j2cfSNEH7z+lwKBgE10C/ikhU6c6IltJEjq6rqRznYJyCQcQc+34Ucj7HZXW5dW5quuaSpX3siT+j8F79oW+2B4xZ9V/j18beKRVGm53fALa8X65BAJcDBD8l0Lkj9v/1RbNwTMeCqkI35k4a1iwHQ5VNOW9duPdnENLzoumfcva1yReZFjOAsPVdmxAoGAeBX5N2Wh0pfvRobwEftvLNTOBxpk+kQvJT8MbtI5NO6NArDTEuwiAFg5KQLjAR35P+c3+PbvcCnF51ythizdTumA2WoPWEpeFMpLPSa946nW++BzjK5G25Re/+Gim7/sj+vhp0zDk93fhJQlPqf601aD68zbXkxg7QeJcX1kZ3kCgYAk8wO354XYV+pZRZf8/cZHUxQJNFH5g/CVbA6G0un2XvVzZOZm4V12wF/MTkvnECL8AdGt0J2LXro5hyuOmYOL7D9pL9wLk5rlGWuh/uY71iPtI4g2Mtm0P1twqAXwGWr0ddCy7rxY6AMPRFZJSbIqpDfyK+OBdDndZOg9EbNuxg==";

		String APIGW_PUBLIC_KEY ="-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16"+
			"bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCL"+
			"T7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP"+
			"3CiNgg0W6O3AGqwIDAQAB";
		
			
		DefaultIcbcClient client = new DefaultIcbcClient(appId, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);


		PaymentPaystateQueryRequestV1 request = new PaymentPaystateQueryRequestV1();		
		request.setServiceUrl("http://ip:port/api/payment/paystate/query/V1");
		PaymentPaystateQueryRequestV1.PaymentPaystateQueryRequestV1Biz bizContent = new PaymentPaystateQueryRequestV1.PaymentPaystateQueryRequestV1Biz();
		request.setBizContent(bizContent);
		bizContent.setPayRefguid("aaaaaa");
		bizContent.setChanSerno("aaaaaa");
		bizContent.setTrxDate("2017-10-01");

		

		
		PaymentPaystateQueryResponseV1 response;
		try {
			response = client.execute(request, "msgId1233123");
			log.fine(response.getReturnCode());
			log.fine(response.getReturnMsg());
			log.fine(response.getMsgId());
			log.fine(response.getState());
			

		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

}
