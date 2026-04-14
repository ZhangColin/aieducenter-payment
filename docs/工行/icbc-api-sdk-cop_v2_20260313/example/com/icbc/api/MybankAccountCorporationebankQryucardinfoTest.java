package com.icbc.api;

import com.icbc.api.request.CorporationebankQryucardinfoRequestV1;
import com.icbc.api.response.CorporationebankQryucardinfoResponseV1;
import org.junit.Assert;
import org.junit.Test;

/**
 * U盾介质查询测试案例
 */
public class MybankAccountCorporationebankQryucardinfoTest {


	public void test_qryucardinfo() {
		StringBuffer msgStr = new StringBuffer();
		// 示例总体说明：请使用F-APIP最新的行外网关SDK进行开发，签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值

		//1、合作方在工行API平台的APP的编号,应用在API开放平台注册时生成
		String APP_ID = "10000000000004097582";
		//2、RSA公私钥，API平台的公钥，生产公钥与测试公钥是不一样的
		String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
		//3、RSA公私钥，合作方应用的私钥，应用公钥需要维护在API平台的APP管理中
		String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCIoI4kzHYZTZZMmxhfxxZqQoSD6L0VIhmheV6/DqGBj3srhUORXt1Onea5/DUt0SbE/MUPrWpDuHmXikHfavyYh167MW6WeKIUTwFmv14a/DyzT7msjEzvNJGH+KphmE866rkddtNTXrfiLTmVneF5VwY/Dc06JYqpt4HVAUsXhJFtwxYvztp99LrlEVDTiJpRObRuXlra4b7g1naIsrOtDogNCyikWW+wGIyl70G7cOYzc3/xKLtKofGNh1TT4IJ/MUqqYJtlpfCYcI0jWDhvpkMo4mK3H21zzR/p/F1KhM4Gfs6GhCyAJPXSWHm0L5u+qygm/7+ptvJao+PhEWbFAgMBAAECggEADAkAnHM6T/5KqBLdccpqyg9z9t6wkUUAtv2m//N8/Jm0AuBpVEvj+MHmCioSUSfkX2PYxAnFm5OLenLeXJzjBVifWqP/FtgnhtxyxidDVeM0QaNbOrzdJhEeZLjd4Vz2Vb1JczP9Z5GnZxAOy4Qt0W9+CF3h3yZhcZPNpwLHJ5oPDOt7HoWRxpHX1zWGtrSLlTWBWJsb8sxLSjkPIFlw74rOTi6d13Buhmmy/O18eftolROy6DUHp+DpOJiTx9e7IGswqZn1Db31fYMFqdxh23RkTSSXY9FQYig0qVYwMRHC4tHMLbefkMAH7irShaH5DxlwVAyD1OWogVLQwnfvfQKBgQDshjBe+0oGrlKUr3MJqTX41oXUTM693pLoCFDroo57ZRtxaOzp6NfhLoHSrogArpNWdFbkIFwf7bTwAKx5Hsc/e6lx8cUqri5RBMDx4L9hU30hkvf0Yfr7P1Y8PEKjNzQrzRM6dM84DWeDsxS9vCtq07zfW915HBA7vVBbXCNY5wKBgQCT4JZk/ZcKK8xCGYgyqv+mUuCjgeauVA4A/o27FUx4zYa3s0mRhVl4kAj77EvU1EmD1zj9YJxdxdHGMenf0BTq2b6FAhpHzNc4MHcD6wPPtjOyKzpcONHHgKqPAE0l/4obIChw7PREHlOFco3kTVAfT03qikPZfgUxflwWiqnxcwKBgQDJIH6ndgYH0lq0xtEK6RHVLQ6Wbk3rlQXVO6UcCpoggeSpNrEnYvGYMYUB05ohAlKV1M5CpOXR3L/cyqXfRPXnnM+E7jNcTMtj56fuxs/XAjjIwQIKlProhX5eMFv2EK5ThMqSDdxb7MIWcsTzZryA1dapFKQrJVsEgcKAPcRtBwKBgEY+/bJjtIgkXhdYQZawHFlKLWP+pTJSw4Q9Z95jM7x/BWvmQyBZlDLtWRczHKKxZWq3pl7WsfDps8jOlSa4lfcqEJLmeM16xRHwE9lMnrbg+pW8azC73QtEV8Euh3dxeI2CLTPXnAFv1oUh8b1t7I2zAnRT0f9TGex09hHPNQN5AoGBAOkU5KBkNggctHXvgKsPDeQTWhsEXO9CY/IeBvmX78oV/+cs277Aa4Q9HxuixYvgLDkTTEjdAq+y07tfV4ZdIov3kjDmfSjZlE0AWybAVyNwTr/NdRv+SlBUdytoChDyA52JdUxsZsFwkrAO+UNHCh65rg+HjkNpwWLLuVFZSij/";
		//4、AES加密秘钥
		String AES_KEY = "NTF9Fjy668DxUulsCd2Jqw==";

		//5、创建与F-APIP外网网关的连接
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8",
				"json", APIGW_PUBLIC_KEY, IcbcConstants.ENCRYPT_TYPE_AES, AES_KEY, (String) null, (String) null);

		//6、设置请求对象
		CorporationebankQryucardinfoRequestV1 request = new CorporationebankQryucardinfoRequestV1();
		//7、设置请求路径；联测时，请联系我行告知并替换成测试环境地址。
		request.setServiceUrl("http://ip:port/api/mybank/account/pension/branch/accountpreopennosms/V1");
		//8、设置业务参数
		CorporationebankQryucardinfoRequestV1.CorporationebankQryucardinfoRequestV1Biz bizContent =
				new CorporationebankQryucardinfoRequestV1.CorporationebankQryucardinfoRequestV1Biz();
		CorporationebankQryucardinfoRequestV1.CorporationebankQryucardinfoRequestV1Biz.ChanCommV10 chanCommV10 = new CorporationebankQryucardinfoRequestV1.CorporationebankQryucardinfoRequestV1Biz.ChanCommV10();
		CorporationebankQryucardinfoRequestV1.CorporationebankQryucardinfoRequestV1Biz.InfoCommV10 infoCommV10 = new CorporationebankQryucardinfoRequestV1.CorporationebankQryucardinfoRequestV1Biz.InfoCommV10();
		CorporationebankQryucardinfoRequestV1.CorporationebankQryucardinfoRequestV1Biz.IfssCtrlComm ifssCtrlComm = new CorporationebankQryucardinfoRequestV1.CorporationebankQryucardinfoRequestV1Biz.IfssCtrlComm();
		CorporationebankQryucardinfoRequestV1.CorporationebankQryucardinfoRequestV1Biz.InChanCommV10 inChanCommV10 = new CorporationebankQryucardinfoRequestV1.CorporationebankQryucardinfoRequestV1Biz.InChanCommV10();
		CorporationebankQryucardinfoRequestV1.CorporationebankQryucardinfoRequestV1Biz.ICom iCom = new CorporationebankQryucardinfoRequestV1.CorporationebankQryucardinfoRequestV1Biz.ICom();
		//9、组装请求参数
		chanCommV10.setChantype("302");//渠道种类
		chanCommV10.setSerialno("870402003002002194809781856");//事件编号

		infoCommV10.setTrxtype("1");//交易类型
		infoCommV10.setTrxcode("90026");//交易代码
		infoCommV10.setWorkdate("2022-11-21");//工作日期(yyyy-MM-dd)
		infoCommV10.setWorktime("19:05:41");//交易时间（HH:MM:SS）

		ifssCtrlComm.setSceneId("ebbcZ3FJonWKRc");//场景代码
		inChanCommV10.setIntrxcode("90026");//渠道内部交易代码

		iCom.setIccardno("1023140025423201416");
		iCom.setRegqryflag("1");

		bizContent.setChanCommV10(chanCommV10);
		bizContent.setInfoCommV10(infoCommV10);
		bizContent.setIfssCtrlComm(ifssCtrlComm);
		bizContent.setInChanCommV10(inChanCommV10);
		bizContent.setICom(iCom);
		bizContent.setVersionid("10000000000004097738");//版本号
		bizContent.setAppsenderid("F-XXX");//发起应用,标识报文原始业务发起方，总行应用为F-应用英文
		bizContent.setAppreceiverid("F-XXX");//接收应用，标识报文最终业务接收方，总行应用为F-应用英文
		bizContent.setOrigsenddate("2021-04-26");//合作方交易日期yyyy-MM-dd
		bizContent.setOrigsendtime("10:38:01");//合作方交易时间HH:mm:ss
		bizContent.setMesgcharset("UTF-8");//报文字符集
		bizContent.setStructtype("JSON");//报文格式
		bizContent.setMesgstat("0");//报文传输状态，0-通讯正常；9-通讯故障；否送9，返回0或9；
		bizContent.setMesgpriority("1");//报文优先级1：普通；2：紧急；3：特急
		request.setBizContent(bizContent);
		CorporationebankQryucardinfoResponseV1 response = null;
		try {
			response = client.execute(request);
		} catch (IcbcApiException e) {
			//赋默认值
			response = new CorporationebankQryucardinfoResponseV1();
		} catch (Throwable e2) {
			e2.printStackTrace();
		}

		if (response.isSuccess()) {
			// 业务成功处理
			msgStr.append("【 CorporationebankQryucardinfoResponseV1 ReturnCode : ").append(response.getAppStatV10().getReturncode()).append("】");
			msgStr.append("【 - ReturnMsg : ").append(response.getAppStatV10().getReturnmsg()).append("】");
		} else {
			// 失败
			msgStr.append("【 CorporationebankQryucardinfoResponseV1 ReturnCode : ").append(response.getAppStatV10().getReturncode()).append("】");
			msgStr.append("【 - ReturnMsg : ").append(response.getAppStatV10().getReturnmsg()).append("】");
		}

		Assert.assertNotNull(msgStr.toString(), response);
	}
}
