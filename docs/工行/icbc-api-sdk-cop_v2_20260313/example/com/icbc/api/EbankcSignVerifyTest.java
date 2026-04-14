package com.icbc.api;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.Test;

import com.icbc.api.request.EbankcSignVerifyRequestV1;
import com.icbc.api.request.EbankcSignVerifyRequestV1.EbankcSignVerifyRequestV1Biz;

public class EbankcSignVerifyTest {

	/**
	 * @param args
	 */

	protected static final String MY_PRIVATE_KEY = "BKa0yNWUiVVX/fv9rqRAhiCRK4OjxG7rOBj4Kpp9R6jDiJtpFD2RxGxN6gDZ/ucbZJ/FOz0v1uzWw9coJBVCOhawBBMZvQ36ha45yFw28Vp8xm091vBB9e3MfsW3B94OaVrGFqpbzds0qvSzubB3c9UaFQomX2Jpzpk0Wgm+Wl4RhGxVfWdJNu3E1NQWA7DxVl8fhra2o72yGbP/izXfZn0jSJEWZ4Pr4aFKeMkuGBfEVav0zsTWwsxsR9RGKfTMLF6z6dbaCjkjWBVB1aJUwXDChuwr8fgdcQVnDBYcf4jeogwavZLBiyY+r0F9hWSgylefoddac66Asko1JhJOO6KU4WSx+WmczF3myZ069rfq4yMfGzhvTJZ5F6sEOjtlMrNo/FBSLnRARRzLt4s+1Ki/vqhyF/hZ3RD91N90s0a3v1YwQ8j2f0RhYBHW945FfTCADsmjC2VoRd41pz0AzWVEHotAxOUG/DstYzvS6IOXZwLI57IYf3ObKCGR6pc1lOSxDh89i40zgfkC2iYDXKmM16h9FfyNKiLnvZDYllBr45CQW1tQT8Fkmz2vb4dZyT0ck0rGdYdTwfc9BvniekQIqh7uprmTtCevASWlW24mMeyi4in7dBvkT5bfkfjCyIV6aWOBkNWEfdSaOEBTnZMQ7KI/aqUKS6Zvj2c6A73jVmPlgkjXmanSVPKQD6NlvWt+VeyOHNb3uAw5eRplo87vg2bUDPT18uQ5J1xwM6gq9oYE4CzmtztTP9SkEp7Lzp0Py7KrG+d+9qNaISdtWbkggvqbHQ58MluZkfYoRgGbwxOYtzDXofma0Fu76iE06FQZGINwp7tMODl1qxeu6oTlM9VnSGIobNLKR1orGZhxGd4wILBTFBeDUpsJnlG/WNociDa6A5gGLF+Un4cF8gmjhjhM4NFdblMlk4PZslG2lvkRo4Sn/BKq7DKc8P8fOHCPqXvKHKy0OaOtnQXsdvbZenutaUU1FJXq7j6T0TciajiIzNPd0Nh7NolmYVaRAqHd58ulX6d9ZA99RyD0h/vGLZKSmABlq6fJd2+vSIN1cewIaz3XJp/BiT0iRg1eniFV3MA6Tu5sLUDPy7VDjGGghVn9PPH91aROpRuNlwFKIp2aHkdx1jFlJqxRkgbqfNcSgkQOm/jnFfCkC6aEu0y3yNDRVsmFSAKpKQTnbialLJc4Mqrtw//uaRtDKoKHGO0BcfM1Iy66PKWz66Jek9vMRBEuTuNJzbVwNOuzLUNDiHmsUAi15nVn/1J1oSgtRYfsJ3WUWpSBDcSlAhkg8/h61I9fmu1kimfs+w3uDTKpJ2qDDVIQSknd+Amlt818rVergssFnvY9dxtY2LroXsDW1tlBFOgHNQQJTcSvDCGdA7IOUaOpVArGZZcnuo6SQzy3ZeQza8/LsmlcDrjJcOAwx7uutUkVyP8u20zey4pH1a3wle9yMscGVrtK0XR+3DZQj+hiZ9jJxyVP4SKb0PuuFfBcwoqYwFkT9ScvZk+4//qZBe9oEmGfUscTNWipGwDhMkXwYYYo8VT22/d7nFeLL8RS8NYQikfFHGjU6OvLCR4Yn9JY7g==";

	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

	protected static final String APP_ID = "0200EH0005040"; 

	protected static final String password = "12345678";

	protected static final String MY_PUB_KEY = "-----请使用正确的APP私钥-----DljCCAn6gAwIBAgIKbaHKEE0tAAI0PTANBgkqhkiG9w0BAQsFADA3MRkwFwYDVQQKExBjb3JiYW5rNDMuY29tLmNuMRowGAYDVQQDExFjb3JiYW5rMTI4IHNkYyBDTjAeFw0xNzA1MjMwODE3NDJaFw0zMjA1MjMwODE3NDJaMEkxHTAbBgNVBAMMFDAyMDBFSDAwMDUwNDAuZS4wMjAwMQ0wCwYDVQQLDAQwMjAwMRkwFwYDVQQKDBBjb3JiYW5rNDMuY29tLmNu-----请使用正确的APP私钥-----BIjANBgkqhkiG9w0BAQEFAAOCAQ8A-----请使用正确的APP私钥-----BCgKCAQEAmZryMysnjnxTL7CDTQk88R5jrwvZxrxCr1VsfKa62kxXh2LeDqhVkaYC3ZjhZeJ+OpNdU2k8kZahx++L5ZtZeZ4loKOzTHQ8nlVPD8cvnprLsNQzVEhAzaOOq91a0jgFHMYdAP9InIWllHvAluJqQThlGKLrpWfIdm7aUIV1EgglclMlCpw97R2MZnL6GcitSsJiof6XamEi00MyJ04MgJjFZouhBZhGkeZlXGG5O/iUzEAa1Zdj/dYHY5LpfES7BycLjmhvAot8n0HernjMF3afaJRr0IbBj81A9Ck8MSpaVypSsM0eQg/RU/bmN80I4cmHf7Rlkx8NQKj5yvFYmwIDAQABo4GRMIGOMB8GA1UdIwQYMBaAFJ6AEaO7V853/fEnZ/7k4X8i8lHzMEwGA1UdHwRFMEMwQaA/oD2kOzA5MQ0wCwYDVQQDDARjcmwxMQ0wCwYDVQQLDARjY3JsMRkwFwYDVQQKDBBjb3JiYW5rNDMuY29tLmNuMB0GA1UdDgQWBBT8MO4OzlJWZjHPeyjDNbtfNLbe/zANBgkqhkiG9w0BAQsFAAOCAQEAPFc63X7Nf9FT04gFWHwv3Zw7jM0oTejlQjfc1y2dI2XhSAa9nYjRXsGLxwFGyudf5AyZopOaNvrhbZpjVbOMl9gSwT/sWGjixOZ392Au4AYK0CZI0XzwDg9QOKucorwxb8xRCmCfgB2KVj0+Gw3qaMm5O7gQrvIsWqQVjjtuzin9Kg8vG9J+MrWbJcfCPIayiuhTPuWb0Kexxv+jjTFqb5G37x81Lqfjy9hPQjHObhHOAIoGE3OI6ua4N84dISsLY6xyp9OZGAToV5N4YH1Goi47O1vSI++ubv8D+gqEJDQ3H8vivP34A7ajVwCYwF5rVY03TvLWxAU+/v9RjsL+0g==";

	public static void main(String[] args) throws IcbcApiException {
		// TODO 自动生成的方法存根

		// 构造client对象
		String castr = APIGW_PUBLIC_KEY;
		// 去除签名数据及证书数据中的空格 added for Safari
		Pattern p = Pattern.compile("\\s*|\t");
		Matcher m2 = p.matcher(castr);
		castr = m2.replaceAll("");
		UiIcbcClient client = new UiIcbcClient(APP_ID, MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8, castr, password);
	
		// 设置请求对象request
		EbankcSignVerifyRequestV1 request = new EbankcSignVerifyRequestV1();
		// 设置请求路径
		request.setServiceUrl("https://ip:port/ui/ebankc/sign/V1/verify");
	
		EbankcSignVerifyRequestV1Biz bizContent = new EbankcSignVerifyRequestV1Biz();

		bizContent.setApiName("JSON");
		bizContent.setApiVersion("001.001.001.001");
		bizContent.setSerialNo("1000000000000000213620170122012021000001");
		bizContent.setTranTime("20181230123211");
		bizContent.setVerifiedCode("0200EG0000002");
		bizContent.setVerifiedName("XXX集团有限公司");
		bizContent.setSignFlag("1");
		bizContent.setSignKind("1");
		bizContent.setSignMode("3");
		bizContent.setCustAcctName("XXX集团有限公司");
		bizContent.setCustAcctNo("6222020200106190966");
		bizContent.setCustType("100");
		bizContent.setCustId("428767198408147542");
		bizContent.setCustName("XXX集团有限公司");
		bizContent.setLegalPersonName("张三");
		bizContent.setLegalPersonType("0");
		bizContent.setLegalPersonNo("110101199901010023");
		bizContent.setFileType("0");
		bizContent.setSignInfo("INK7oaLW2Mfs6LXJvbmk0vi05z4=");
		bizContent.setAutoTurnFlag("1");
		bizContent.setUkeyId("11yue0.c.0200");
		bizContent.setRequestIp("ip:port");
		
		
		request.setBizContent(bizContent);
	
		// 生成自提交的表单返回客户浏览器，该表单会自动提交完成调用
		System.out.println(client.buildPostForm(request));
	}
}
