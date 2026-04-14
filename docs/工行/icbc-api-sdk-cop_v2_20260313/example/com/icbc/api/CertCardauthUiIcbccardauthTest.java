import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcConstants;
import com.icbc.api.UiIcbcClient;
import com.icbc.api.request.CertCardauthUiIcbccardauthRequestV1;
import com.icbc.api.request.CertCardauthUiIcbccardauthRequestV1.CertCardauthUiIcbccardauthRequestV1Biz;


public class CertCardauthUiIcbccardauthTest {
	public static void main(String[] args) throws IcbcApiException {
 		//1、APPID
		//2、网关公钥(需在API平台登记)
		//3、密钥对认证方式，公钥在API平台登记，此处为私钥
		String APP_ID = "10000000000004096696";
		String APIGW_PUBLIC_KEY = "{网关公钥(需在API平台登记)}";
		String MY_PRIVATE_KEY = "{密钥对认证方式，公钥在API平台登记，此处为私钥}";

		UiIcbcClient client = new UiIcbcClient(APP_ID, MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8);
		CertCardauthUiIcbccardauthRequestV1 request = new CertCardauthUiIcbccardauthRequestV1();
		//IP,端口 为API网关地址
		request.setServiceUrl("http://ip:port/ui/cert/cardauth/ui/icbccardauth/V1");
		
		CertCardauthUiIcbccardauthRequestV1Biz bizContent = new CertCardauthUiIcbccardauthRequestV1Biz();
		bizContent.setPhoneNo("{手机号码}");
		bizContent.setSignature("12313"); //APP签名信息
		bizContent.setSignChannelId("12345678901231312"); //账户与渠道关联ID
		bizContent.setTransNo("12313"); //交易序号
		bizContent.setUserId("88888888"); //App用户唯一标识
		bizContent.setZoneNo("00901"); //地区号
		bizContent.setZoneFlag("1"); //校验地区标志 1-校验 0-不校验
		bizContent.setNotifyFlg("1"); //是否通知分行标志  0-不通知 其他或不送该标志时通知
		bizContent.setNotifyUrl("{分行接收处理结果地址}"); //分行接收处理结果地址
		bizContent.setRedirectUrl("");// APP地址/页面跳转url，若redirectUrl为http开头则页面会post请求该redirectUrl，否则调用bankinfo.OnBankSignedResult接口将处理结果返回给app，该js接口由app端实现。
		bizContent.setBackup("6buRQTMxODAxMg=="); //备用字段
		bizContent.setAccno("{卡号}");
		try {
			bizContent.setAccnoName(URLEncoder.encode("{姓名}", "GBK"));
		} catch (UnsupportedEncodingException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		bizContent.setCertNo("{证件号码}");
		bizContent.setPhoneChkFlg("1");//是否支持二三类户标志 1-支持 其他不支持
		bizContent.setCertFlag("0"); //是否控制用户名及证件号码不可修改	0否 1是。当送1且户名及证件号码有送值时，H5页面置灰不可修改
		bizContent.setPhoneFlag("0"); //是否控制卡号及手机号不可修改	0否 1是。当送1且卡号及手机号有送值时，H5页面置灰不可修改
		bizContent.setSubProtocol(""); //子协议号，当一个地区号zoneNo有多个业务功能需展示不同协议时上送该字段，以取不同的协议文本。没有子协议则不送该字段，或送空     
		bizContent.setSmsType("1"); //短信验证码类型  1-身份认证 0或不送为etc
		bizContent.setLogoFlg("1"); //是否展示logo标志 0-不展示 其他或不送该标志时展示
		bizContent.setLogoPic("icbc_logo.png"); //页面logo 不送或送空时展示默认工行logo。个性化logo需提前联系f-bcss将对应logo图片发版。

		request.setBizContent(bizContent);
		String msg_id = String.valueOf(System.currentTimeMillis());
		System.out.println(client.buildPostForm(request,msg_id));
		
	}
}
