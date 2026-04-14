package com.icbc.api;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import cn.com.infosec.icbc.ReturnValue;

import com.icbc.api.request.PersonalSignVerifyRequestV1;
import com.icbc.api.request.PersonalSignVerifyRequestV1.PersonalSignRequestBiz;
import com.icbc.api.utils.IcbcSignature;

public class PersonalSignVerifyTest {

	
	public void check_sign_rsa() throws IcbcApiException {
		UiIcbcClient client = new UiIcbcClient("10000000000000016542",
				"-----请使用正确的APP私钥-----CdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALAWAcPiTMRU906PTdy0ozspX7XptZnkEw2C8R64RDB9BiRFXAj0cU4aTA1MyfmGIlceeVdgJf7OnmvpHnYxjQ7sGxMItPtodrGwA2y8j0AEbHc5pNWU8Hn0zoY9smHS5e+KjSbWv+VNbdnrRFTpDeiJ3+s2E/cKI2CDRbo7cAarAgMBAAECgYABiA933q4APyTvf/uTYdbRmuiEMoYr0nn/8hWayMt/CHdXNWs5gLbDkSL8MqDHFM2TqGYxxlpOPwnNsndbW874QIEKmtH/SSHuVUJSPyDW4B6MazA+/e6Hy0TZg2VAYwkB1IwGJox+OyfWzmbqpQGgs3FvuH9q25cDxkWntWbDcQJBAP2RDXlqx7UKsLfM17uu+ol9UvpdGoNEed+5cpScjFcsB0XzdVdCpp7JLlxR+UZNwr9Wf1V6FbD2kDflqZRBuV8CQQCxxpq7CJUaLHfm2kjmVtaQwDDw1ZKRb/Dm+5MZ67bQbvbXFHCRKkGI4qqNRlKwGhqIAUN8Ynp+9WhrEe0lnxo1AkEA0flSDR9tbPADUtDgPN0zPrN3CTgcAmOsAKXSylmwpWciRrzKiI366DZ0m6KOJ7ew8z0viJrmZ3pmBsO537llRQJAZLrRxZRRV6lGrwmUMN+XaCFeGbgJ+lphN5/oc9F5npShTLEKL1awF23HkZD9HUdNLS76HCp4miNXbQOVSbHi2QJAUw7KSaWENXbCl5c7M43ESo9paHHXHT+/5bmzebq2eoBofn+IFsyJB8Lz5L7WciDK7WvrGC2JEbqwpFhWwCOl/w==",
				IcbcConstants.CHARSET_UTF8);

		PersonalSignVerifyRequestV1 request = new PersonalSignVerifyRequestV1();
		request.setNotifyUrl("https://www.scgsj.com/notify.do");
		request.setServiceUrl("https://ip:port/ui/personal/sign/V1/verify");

		PersonalSignRequestBiz bizContent = new PersonalSignRequestBiz();
		bizContent.setLanguage("ZH_CN");
		bizContent.setTranTime("20161021144040");
		bizContent.setVerifiedCorpId("2000EG0000136");
		bizContent.setVerifiedCorpName("uaTJzL7W");
		bizContent.setVerifiedId("800136");
		bizContent.setLogonId("020000206164898.p.0200");
		bizContent.setAuthenAcctName("x9G3wg==");
		bizContent.setAuthenAcctNo("6222020200106190966");
		bizContent.setCertType("0");
		bizContent.setCertNo("428767198408147542");
		bizContent.setAuthenName("x9G3wg==");
		bizContent.setVerifiedFlag("1");
		bizContent.setVerifiedKind("0");
		bizContent.setVerifiedType("0");
		bizContent.setNotifyType("HS");
		bizContent.setVerifiedInfo("1eLKx9K7uPbHqcP7xNrI3cW2");
		bizContent.setRequestIp("ip:port");
		bizContent.setAutoTurnFlag("0");

		request.setBizContent(bizContent);

		System.out.println(client.buildPostForm(request));
	}

	
	public void check_sign_ca() throws IcbcApiException, IOException {
		FileInputStream fileKey = new FileInputStream("C:\\Users\\kfzx-wangyg\\Desktop\\204.key");
//		System.out.println(fileKey.available());
		byte[] PriK = new byte[fileKey.available()];
		fileKey.read(PriK);
		fileKey.close();
		String x= new String(ReturnValue.base64enc(PriK));
		System.out.println(new String(ReturnValue.base64enc(PriK)));
		String castr = "-----请使用正确的APP私钥-----DFDCCAfygAwIBAgIKG5LKECVWAAKSnzANBgkqhkiG9w0BAQUFADA7MR8wHQYDVQQDExZJQ0JDIFRlc3QgQ29ycG9yYXRlIENBMRgwFgYDVQQKEw90ZXN0aWNiYy5jb20uY24wHhcNMTcwNDAyMDc1MTU5WhcNMTgwNDAyMDc1MTU5WjBIMR0wGwYDVQQDDBQwMjAwRUcwMDAwMjA0LmUuMDIwMDENMAsGA1UECwwEMDIwMDEYMBYGA1UECgwPdGVzdGljYmMuY29tLmNuMIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC7Qszav/891OWRhx69SRb8SRb8Ty+AMqYBIUvFlb4Ve1IvKasJkDBKozRiCZXn4/gqf1iQyS2hfzcOXNyw659Q/tcVzACpYeDIK6gjIz9cqbn0KbG7MnKWIWwRVMwRJ9C3EHqFla+pJ4kLtIWNXahE4AWTKSMDfRyWVcaPJqi2bwIDAQABo4GQMIGNMB8GA1UdIwQYMBaAFER9t5AsN6TZ7WzipIdXZwq18E0UMEsGA1UdHwREMEIwQKA+oDykOjA4MQ4wDAYDVQQDDAVjcmwzNDEMMAoGA1UECwwDY3JsMRgwFgYDVQQKDA90ZXN0aWNiYy5jb20uY24wHQYDVR0OBBYEFJM28/ijwPuLwD4tTdWz+yAuI8V2MA0GCSqGSIb3DQEBBQUAA4IBAQAr6MnUmzSDpdZ7SSp4hpoygALfkPweOtlky6KQgNX6Nb2gveYMPF1jdKNKoxmSk5W5Qjl7vc1IAjdEwrhelrCp/PGmA+4G3CEjM8eyEjY30Efnk8mr1D3TcFjdAwgBLIkiMnUwRZ3h/U89dhlCs8YKHNZ9S8ilj0LOFuk72GpJSD+T6Nse791nQp6k8PbwWvadvmxJK6knx+19TUAO+x98trIvildAQqsrFO17QhV90F1yqSkP/0EE/zkictL/ca9Tg6+w7eGntmzb8Jkof7P/t5G+fBV473mSnHSw5PURlQHS9K14L+7rzupVxyfa3EgkzMHDz8HngnJxbqq3CIP1";		// 去除签名数据及证书数据中的空格 added for Safari
		Pattern p = Pattern.compile("\\s*|\t");
		Matcher m2 = p.matcher(castr);
		castr = m2.replaceAll("");
		UiIcbcClient client = new UiIcbcClient("2502EG0000202",x,
								IcbcConstants.CHARSET_GBK, castr, "12345678");

		PersonalSignVerifyRequestV1 request = new PersonalSignVerifyRequestV1();
		request.setNotifyUrl("https://www.scgsj.com/notify.do");
		request.setServiceUrl("https://apipcs3.dccnet.com.cn/ui/personal/sign/V1/verify");

		PersonalSignRequestBiz bizContent = new PersonalSignRequestBiz();
		bizContent.setLanguage("ZH_CN");
		bizContent.setTranTime("20161021144040");
		bizContent.setVerifiedCorpId("2000EG0000136");
		bizContent.setVerifiedCorpName("uaTJzL7W");
		bizContent.setVerifiedId("800136");
		bizContent.setLogonId("020000206164898.p.0200");
		bizContent.setAuthenAcctName("x9G3wg==");
		bizContent.setAuthenAcctNo("6222020200106190966");
		bizContent.setCertType("0");
		bizContent.setCertNo("428767198408147542");
		bizContent.setAuthenName("你好");
		bizContent.setVerifiedFlag("1");
		bizContent.setVerifiedKind("0");
		bizContent.setVerifiedType("0");
		bizContent.setNotifyType("HS");
		bizContent.setVerifiedInfo("1eLKx9K7uPbHqcP7xNrI3cW2");
		bizContent.setRequestIp("ip:port");
		bizContent.setAutoTurnFlag("0");

		request.setBizContent(bizContent);

		System.out.println(client.buildPostForm(request));
	}

	

}
