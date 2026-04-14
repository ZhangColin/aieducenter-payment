package com.icbc.api;

import com.icbc.api.request.IicUserserviceCheckTokenUpdateRequestV1;
import com.icbc.api.request.IicUserserviceCheckTokenUpdateRequestV1.IicUserserviceCheckTokenUpdateRequestV1Biz;
import com.icbc.api.response.IicUserserviceCheckTokenUpdateResponseV1;
import org.junit.Assert;

/**
 * 信用卡账单信息查询接口
 * 
 * 请商户配置SDK中的lib文件夹中的jar包后，根据备注1-6提示进行数据替换
 */
public class IicUserserviceCheckTokenUpdateTestV1 {
	
	//1、网关公钥
	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
	
	//2、appid
	protected static final String APP_ID = "xxxxxxxxxxxxxx";

	//3、密钥对认证方式，公钥在API平台登记，此处为私钥
	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCjdo29EWrjWrsrPLauVLVm37e+vXbLwk1fTWUpLjRukTDQDDdFDSM65/G8l5EDFQwKIimlsCMnPIorZl3vZCKHGS63WHhC6Lh1PlTbgZHi/cmRsw2OnqBdxDfHWbnrmmC4BJl0GroUxhEaZjZlU5NLRogNCLd8DzkeZebxghxtO09qxqQNFGD4norOP2GzClt1hx+2ifZO6jXZKAKxhzqDFJhWKihp47G0Ruo5IEPofaT4GKzi8hedUGrLslSkaSCHBh7nfoH99DgPwg50bzE6oFvCeFIC5tt8zLvzS4SFzKrkYnXv9hCGU5gFk6NxCGaFkYg1dgi7SJAO1s6kOB5jAgMBAAECggEAUvUbwdRRFsk5LBxH6fl7hpYLPnYREB+v/KneqSOV4Hc2+p74fcbJFmAYQsn5AkNwNMRZtojJjmQfCx+MlK2RCFczakEv52UMGBRmJHCBhV2fKp5gRv0iDfRDCpzyyisy4rES3+WggmJy058EBDCCFDXbXiIKXJ+tMUwuBGsv2g7oaO6DcY9nZZhPiQOruTUJtKfwfI32wvPFQhWsSYbXg8/JYWFFKoJnx3fUfsM0ezI2v8E+KgAgNfpa35PJ3r4e/WlxSAa+jLBNnuCudMZ7kILtJjK119DEbJocbkAgP/qX66uScf5C76+i9wEsJZi6jTrrJ+Q7o6zRbNkNk5rPwQKBgQD2UJdWlzuC6bG/w/ZxhKc3O2+xlrE7ZQusLEzgBqwZDtDxEYotuD29B8S/UIuSOr7fOzFKzYoJe9c1wHJ1aYI7LszaC4e7id4y1qcJZLB8TBRQ4Z3n/r3p45X63ZTzow+khgdVFHXB+PAi8pnH7cctRNsjiCeLjER7UwteWrVzSwKBgQCp4/nSRy6K1NSteUfiwEDx2FvNCgevysj0N5/hrj/UT3pyRf1uZVwMLmeo3zlCo7/N4aoZzMK1QtvAjx+oKYHGj3v+/ZV/NwrzKC9z2vexNtknKeH80tFhr6N9EmohrfojqTxTB4WwAuKuwACDn4q+XSYmV0pWFX1IqN83asn6SQKBgQCV1iKp1yeplHW/fPrexV5fN/XTJyM7SVWXb/93zDdGqv+plgzjkQ9lfWNPKjUtSDUyYLS0l2IgMMiS9zXpfLxvOiH9hkQUbmLPtEo6aZj+k91L8mN3bDylGf/zcnWY3Bm48JtnQyH7VG7oI4Iu39NpE1jnPX0o47QI/VSOGkoFDwKBgD81KZpZj3BptOMBz4QGoWMZazZC+A8odFGgbGSrzuWv2E+xyJONNIz3quuCeaxYfiEcR4wnyX1k7Zub5FxNbRIyCiEZuchF81X6aoVBP4x4apyOkZzd1rbwgPQkqMX1E6625VHC57NPr4TyvH48GoOu5mW3blnOzvj8k2fr3E+5AoGAdoKiIdkEdJUW0LGu4V/Be7Od4krK+5pzdtoKJVyo66y9qsw0A4X1WMbTeEUxchqtdsL9guiNDW++wM/yPte5KdDoAV8jnPfRFsKym57wLC/9dkpmOqKoI1KE7Vg1dHjB7dwOLVE5tT8+ONzCVgUgUmg6rmE7eDt28eMOmga5QyE=";
	public static void main(String[] args) throws Exception {

		//签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2 , MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
		
		IicUserserviceCheckTokenUpdateRequestV1 request = new IicUserserviceCheckTokenUpdateRequestV1();
		
		//4、根据测试环境和生产环境替换相应ip和端口
		request.setServiceUrl("http://ip:port/api/iic/userservice/checktoken/update/V1");

		//5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
    IicUserserviceCheckTokenUpdateRequestV1Biz bizContent = new IicUserserviceCheckTokenUpdateRequestV1Biz();

     //项目必要部分
    bizContent.setTokenId("71a11ee9-0f05-436a-ad70-9ef5dae5169d");
		request.setBizContent(bizContent);
    IicUserserviceCheckTokenUpdateResponseV1 response;
    try {
		response = client.execute(request, "01102");//msgId��ϢͨѶΨһ��ţ�Ҫ��ÿ�ε��ö������ɣ�APP��Ψһ
		Assert.assertNotNull(response);
	} catch (IcbcApiException e) {
		e.printStackTrace();
	}
	}

	
}


