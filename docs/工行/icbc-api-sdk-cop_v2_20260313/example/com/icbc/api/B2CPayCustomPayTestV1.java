package com.icbc.api.demo;

import com.icbc.api.*;
import com.icbc.api.request.*;
import com.icbc.api.request.B2CPayCustomPayRequestV1.*;

public class B2CPayCustomPayTestV1 {
	//1、网关公钥
	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

	//2、appid
	protected static final String APP_ID = "appid";

	//3、CA证书私钥
	protected static final String MY_PRIVATE_KEY = "BKhzPU9FJk9/Ui1GPx6mRar0BYLaBDfJUsvrNaWXIjL3Tyi3nHQXD5Rl7QjhzRk1SzfyBsATcsf+TyZUXCfrbvd2owl1NH7PMneQR2rAvJdpP1Im/RhKvpPI3DdiHwA+S/dtTdJ3jUe1Q8STMF8BqCBipIKL0NJTDbDhFX4+vAfS7HybxlbybRO1fsHTj3UK3gzaYCQ8on2501iDmNq5jqHyGo+C2Mn7a2ZNKPN8sz0yBYfMchdvXpXiqmN4N9JvIfx38qTyqzWwv4XyvLdDoWsoYgxWQ3EgJqO4j4DXpWYJOLEbFVindQ2aWgl8HOKSturHbIYxQirH1NQ1ajy84oGELEe+YeyIDfTsOnTPijXiM1hQTt9KWNL5A0aIA5s+kUOhHauctEujfr4+RXgHzpFbdJ2JWAVo5vkFwuQAXrUstChaS4Td+dGJ8betaSNJjVmN0snfMxCnSWr/sN9WnGGWidjctyixUlxSY/5L/w/ZjZM1RlerI1G+PSbpEnYd+4dz2AwveBAfMRwkoYJvJYOHTRJgrgJ+xTMz65BEz2yfGG9y37HCzj5ym4AN3vO0ry2SE5JJ1+LBp07JbxU1/EHax8zK8c8riVWNfar3H5WK98eJXKVK8mDKnpBBImbgcFEh1bcbi+ywJceHRiA4JBi/gpQFRzAbQ/f9uub2lL4M2mGa9S4ZjP13xhx6z7FEF9Ctp6KzMa3BrxsN1flLfhypQfOjfShiln/da90qrlaah//lqsmzQZhnXkCvmv74t1bdOyERrKpi0tbA01clQRqdvktrWvNKUsnVo4VlmFUAxygKl3swfTOVsKrjjtiQnji4Ml+qDvVg7LU2mXSbQI91EaZH9ek0DsFXLbquqvXM/vqLhhWW/wjpGZy7cYCOwS2nID0AwY4NPIN3mC+wDmSPrYSGYGuaojRt0u205X0o60UV1IkHCvrvJ6Kqw3kZFjW02Vx9Y9gQOEMgek4wwu7STUjIK4gIv5gCTJE+k/QMPoWbKxM6yv1CHAr0JluLKBlUWf/XfjicTh9uboBJXpp9baJJcUHIVxEBXTaYMBrg0fSzlryDE3Fq8K8trlLCr2dLNendwDlKCV/RYPFz1YjanjMuAajq7y4slCIqSDqSa/YWZnSASFwu4YC7IfIVjwQN9/5pvDXG4EWhwjhRNcWoW1aAeZYSEewg7GflLhRTjFolRLg4xd5xScx08Fe+UHfhQgyFu3mVX2B2z90C7+AzVYMJUSzgiUBiOJrSWQuxelvTi8MwqgIC7ZKQJA6ivka8Uco+nHLUhej1c/SwOMekOrJJpZjbhoV2sma6uD4EDHGEWr4/kgu56p8HtnqUjnC0O51UD+WrpCeSqyxT8HqCPCJiWfQf/SwpzIV5O25nC8eXzCi46XRz7xqWIfI/6iyHVngohHCsKTddvC1mUeowXx+5ANCaGz3owBigM7Io0hgJLvyj08vtjwSaLF6bgFGljFW43p8UoeAp14Uw/cl9J9002pjUsS0qp/uEtVQBo21apJIxiXW+Gr1OOybzHD6gJb/3pNjKDaKwnwFLUZXD5DbWDJ3EIYRl5CRHt26rGTlgPMtF7tYH";

	//4、CA证书公钥	
	protected static final String CA = "-----请使用正确的APP私钥-----DjTCCAnWgAwIBAgIKbaHKEE0tAAI/nDANBgkqhkiG9w0BAQsFADA3MRkwFwYDVQQKExBjb3JiYW5rNDMuY29tLmNuMRowGAYDVQQDExFjb3JiYW5rMTI4IHNkYyBDTjAeFw0xODAxMDkwNjA1MTFaFw0zNjAxMDkwNjA1MTFaMEAxFDASBgNVBAMMC0xMTEwuZS4wMjAwMQ0wCwYDVQQLDAQwMjAwMRkwFwYDVQQKDBBjb3JiYW5rNDMuY29tLmNu-----请使用正确的APP私钥-----BIjANBgkqhkiG9w0BAQEFAAOCAQ8A-----请使用正确的APP私钥-----BCgKCAQEAmeQdkxn2g5bJAz1ZivvLtaOnoNQUlqNu1GaeXtXmcSqQXaQ3Jcb8OAn8vvauEVwXamiuEau4/i80dsQchIgFjmQ2PwCJdZTl+VZabWUS7Mztc/jiami5B2d2KjEsfg387v63eQLCYai2MT9ZlVdae6EYgkzIjLuMUCDpSw+pac6+VgDrUqID+Qkov4Ni5aD4KcxwUwuOlr4FrftKyCV0PnWVAdBnbNXPTFeeUVMZUilZpa2X+XTqVCAukcwwCRyDcE6XlzVUs9evPWHYxwEUCgl/cU8NpOsCfKqb//x4zVU12HaOyQybKvfslMzB7lXAZxG97bAyivu9O+pqK9UTdwIDAQABo4GRMIGOMB8GA1UdIwQYMBaAFJ6AEaO7V853/fEnZ/7k4X8i8lHzMEwGA1UdHwRFMEMwQaA/oD2kOzA5MQ0wCwYDVQQDDARjcmwxMQ0wCwYDVQQLDARjY3JsMRkwFwYDVQQKDBBjb3JiYW5rNDMuY29tLmNuMB0GA1UdDgQWBBRvki/a/aTB8VHFZSV3j4Y5jnua2jANBgkqhkiG9w0BAQsFAAOCAQEAJC5AqnEAHXW1J8rNg/XpvnksPAUpGqDqH7Zu/mVHzDouHzvFVM0L4JsTK2dwu5JmAhtII9cYWPoENzsUAFcYRKUC/MvFxIIyPgssIOQ16ZydBah9yaEQp5LC83Gt9S+yQdhcEpRRsUuVeIg0CUU+924Ysqg/4z8+wuEIISh/OFvubuGl+se4xpU65Uj0NL3d40Xn4WHG13lnh4gwyLqf69dxOeiruv0Y5eJclR0YXL2bs02hlm70XfUVzHwWuKS65NlbBgdrpckAyFUqmF+n1BcgUtpjC0XQVf6qAanbH9CBGmmNL2LEk8uaGcBQI8OWUBJxJW0PpUY5ftypWHhGWw==";
	
	//5、CA证书密码
	protected static final String PASS_WORD = "12345678";
	
	public static void main(String[] args) throws Exception {
		//签名类型为CA时，需传入appid，CA私钥、CA证书和证书密码，签名类型使用定值IcbcConstants.CA，其他参数使用缺省值
	    UiIcbcClient client = new UiIcbcClient(APP_ID, MY_PRIVATE_KEY, "GBK",CA,PASS_WORD);

		B2CPayCustomPayRequestV1 request = new B2CPayCustomPayRequestV1();
		
		//4、根据测试环境和生产环境替换相应ip和端口
		request.setServiceUrl("https://ip:port/ui/b2c/pay/custompay/V1");
		request.setNotifyUrl("https://www.scgsj.com/notify.do");
		request.setInterfaceName("ICBC_WAPB_B2C");
		request.setInterfaceVersion("xxxx");
		request.setClientType("0");
		//5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
		B2CPayCustomPayRequestV1Biz bizContent = new B2CPayCustomPayRequestV1Biz();
		B2CPayCustomPayRequestV1OrderInfo orderInfo = new B2CPayCustomPayRequestV1OrderInfo();
		orderInfo.setOrderDate("20170122152918");
		orderInfo.setOrderId("xxxx");
		orderInfo.setAmount("500");
		orderInfo.setInstallmentTimes("1");
		orderInfo.setCurType("001");
		orderInfo.setMerID("0200EG0000202");
		orderInfo.setMerAcct("xxxx");
		orderInfo.setWalletFlag("0");
		orderInfo.setExternalAppId("100000064100000");
		orderInfo.setMerchantInfo("杭州XXX传媒有限公司");
		B2CPayCustomPayRequestV1Custom custom = new B2CPayCustomPayRequestV1Custom();
		custom.setVerifyJoinFlag("0");
		custom.setLanguage("zh_CN");
		custom.setCheckFlag("0");
		custom.setCustName("国债三");
		custom.setCustCertType("0");
		custom.setCustCertNo("0");
		custom.setCustId("");
		custom.setMobile("");
		B2CPayCustomPayRequestV1Message message = new B2CPayCustomPayRequestV1Message();
		message.setGoodsID("428767198");
		message.setGoodsName("超市");
		message.setGoodsNum("1");
		message.setCarriageAmt("0");
		message.setMerHint("小心轻放");
		message.setRemark1("");
		message.setRemark2("");
		message.setCreditType("");
		message.setMerReference("");
		message.setMerCustomIp("");
		message.setGoodsType("");
		message.setMerCustomID("12345678");
		message.setMerCustomPhone("");
		message.setGoodsAddress("");
		message.setMerOrderRemark("");
		message.setMerVAR("");
		message.setNotifyType("");
		message.setResultType("");
		message.setIsReal("0");
		message.setSubidno("");
		message.setPromptText("");
		message.setReturnUrl("");
		message.setAutoReferSec("");
		message.setBackup1("");
		message.setBackup2("");
		message.setBackup3("");
		message.setBackup4("");
		bizContent.setOrderInfo(orderInfo);
		bizContent.setCustom(custom);
		bizContent.setMessage(message);
		request.setBizContent(bizContent);
		client.buildPostForm(request);	
		/*
		 <form name="auto_submit_form" method="post" action="https://ip:port/ui/b2c/pay/custompay/V1?charset=GBK&format=json&sign=Os4mjAPu9n2DCVlnK%2Brb97T6YYhn8QacP7fL42kcVN2cgZiG5%2Fo1JP7PcHkuKe6HHXJ1W%2Bl4DKmOhBILYihD5QC8GN0V4ReaeFH%2BXnFIiwU87B3YJYL32x9VXvdlGDxC%2BZg17ypg5HkQRqWy36jRYlb7IOt8PKnwfbm8bb3lt1scin1NFY%2Fj7252CotPn7ti6S1%2BPLWZE88hzthEgbpONX8atIkY1tS7mOInRvo9xBAZaiorZMvkD%2FkRREVhaq7OhWdKshQ97aceqkBBqDxVLdARTarDDS%2FY6V0pg%2FzuK1Tt%2B0nZlC0gCFnUj8jMWFI70fXPQgHP7esMTY52Hx2VLA%3D%3D&msg_id=44fed1e1d8964497bab243d1432b3343&app_id=10000000000000002156&sign_type=CA&timestamp=2019-09-24+16%3A17%3A45">
			<input type="hidden" name="biz_content" value="{&quot;custom&quot;:{&quot;check_flag&quot;:&quot;0&quot;,&quot;cust_cert_no&quot;:&quot;0&quot;,&quot;cust_cert_type&quot;:&quot;0&quot;,&quot;cust_id&quot;:&quot;&quot;,&quot;cust_name&quot;:&quot;国债三&quot;,&quot;language&quot;:&quot;zh_CN&quot;,&quot;mobile&quot;:&quot;&quot;,&quot;verify_join_flag&quot;:&quot;0&quot;},&quot;message&quot;:{&quot;auto_refer_sec&quot;:&quot;&quot;,&quot;backup1&quot;:&quot;&quot;,&quot;backup2&quot;:&quot;&quot;,&quot;backup3&quot;:&quot;&quot;,&quot;backup4&quot;:&quot;&quot;,&quot;carriage_amt&quot;:&quot;0&quot;,&quot;credit_type&quot;:&quot;&quot;,&quot;goods_address&quot;:&quot;&quot;,&quot;goods_id&quot;:&quot;428767198&quot;,&quot;goods_name&quot;:&quot;超市&quot;,&quot;goods_num&quot;:&quot;1&quot;,&quot;goods_type&quot;:&quot;&quot;,&quot;isReal&quot;:&quot;0&quot;,&quot;mer_custom_id&quot;:&quot;12345678&quot;,&quot;mer_custom_ip&quot;:&quot;&quot;,&quot;mer_custom_phone&quot;:&quot;&quot;,&quot;mer_hint&quot;:&quot;小心轻放&quot;,&quot;mer_order_remark&quot;:&quot;&quot;,&quot;mer_reference&quot;:&quot;&quot;,&quot;mer_var&quot;:&quot;&quot;,&quot;notify_type&quot;:&quot;&quot;,&quot;prompt_text&quot;:&quot;&quot;,&quot;remark1&quot;:&quot;&quot;,&quot;remark2&quot;:&quot;&quot;,&quot;result_type&quot;:&quot;&quot;,&quot;return_url&quot;:&quot;&quot;,&quot;subidno&quot;:&quot;&quot;},&quot;order_info&quot;:{&quot;amount&quot;:&quot;500&quot;,&quot;cur_type&quot;:&quot;001&quot;,&quot;external_app_id&quot;:&quot;100000064100000&quot;,&quot;installment_times&quot;:&quot;1&quot;,&quot;mer_acct&quot;:&quot;6212880200000038618&quot;,&quot;mer_id&quot;:&quot;0200EG0000202&quot;,&quot;merchant_info&quot;:&quot;杭州XXX传媒有限公司&quot;,&quot;order_date&quot;:&quot;20170122152918&quot;,&quot;order_id&quot;:&quot;00210554222222254&quot;,&quot;wallet_flag&quot;:&quot;0&quot;}}">
			<input type="hidden" name="clientType" value="0">
			<input type="hidden" name="interfaceVersion" value="ip:port">
			<input type="hidden" name="interfaceName" value="ICBC_WAPB_B2C">
			<input type="hidden" name="notify_url" value="https://www.scgsj.com/notify.do">
			<input type="hidden" name="ca" value="-----请使用正确的APP私钥-----DjTCCAnWgAwIBAgIKbaHKEE0tAAI/nDANBgkqhkiG9w0BAQsFADA3MRkwFwYDVQQKExBjb3JiYW5rNDMuY29tLmNuMRowGAYDVQQDExFjb3JiYW5rMTI4IHNkYyBDTjAeFw0xODAxMDkwNjA1MTFaFw0zNjAxMDkwNjA1MTFaMEAxFDASBgNVBAMMC0xMTEwuZS4wMjAwMQ0wCwYDVQQLDAQwMjAwMRkwFwYDVQQKDBBjb3JiYW5rNDMuY29tLmNu-----请使用正确的APP私钥-----BIjANBgkqhkiG9w0BAQEFAAOCAQ8A-----请使用正确的APP私钥-----BCgKCAQEAmeQdkxn2g5bJAz1ZivvLtaOnoNQUlqNu1GaeXtXmcSqQXaQ3Jcb8OAn8vvauEVwXamiuEau4/i80dsQchIgFjmQ2PwCJdZTl+VZabWUS7Mztc/jiami5B2d2KjEsfg387v63eQLCYai2MT9ZlVdae6EYgkzIjLuMUCDpSw+pac6+VgDrUqID+Qkov4Ni5aD4KcxwUwuOlr4FrftKyCV0PnWVAdBnbNXPTFeeUVMZUilZpa2X+XTqVCAukcwwCRyDcE6XlzVUs9evPWHYxwEUCgl/cU8NpOsCfKqb//x4zVU12HaOyQybKvfslMzB7lXAZxG97bAyivu9O+pqK9UTdwIDAQABo4GRMIGOMB8GA1UdIwQYMBaAFJ6AEaO7V853/fEnZ/7k4X8i8lHzMEwGA1UdHwRFMEMwQaA/oD2kOzA5MQ0wCwYDVQQDDARjcmwxMQ0wCwYDVQQLDARjY3JsMRkwFwYDVQQKDBBjb3JiYW5rNDMuY29tLmNuMB0GA1UdDgQWBBRvki/a/aTB8VHFZSV3j4Y5jnua2jANBgkqhkiG9w0BAQsFAAOCAQEAJC5AqnEAHXW1J8rNg/XpvnksPAUpGqDqH7Zu/mVHzDouHzvFVM0L4JsTK2dwu5JmAhtII9cYWPoENzsUAFcYRKUC/MvFxIIyPgssIOQ16ZydBah9yaEQp5LC83Gt9S+yQdhcEpRRsUuVeIg0CUU+924Ysqg/4z8+wuEIISh/OFvubuGl+se4xpU65Uj0NL3d40Xn4WHG13lnh4gwyLqf69dxOeiruv0Y5eJclR0YXL2bs02hlm70XfUVzHwWuKS65NlbBgdrpckAyFUqmF+n1BcgUtpjC0XQVf6qAanbH9CBGmmNL2LEk8uaGcBQI8OWUBJxJW0PpUY5ftypWHhGWw==">
			<input type="submit" value="立刻提交" style="display:none" >
		</form>
		<script>document.forms[0].submit();</script>

		 */
	}
}
