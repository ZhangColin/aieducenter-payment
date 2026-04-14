package com.icbc.api;

import java.util.Map;


import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.request.MybankCustomerShoponlineCsocustqryRequestV1;
import com.icbc.api.request.MybankCustomerShoponlineCsocustqryRequestV1.MybankCustomerShoponlineCsocustqryRequestV1Biz;
import com.icbc.api.response.MybankCustomerShoponlineCsocustqryResponseV1;
import com.icbc.api.response.MybankCustomerShoponlineCsocustqryResponseV1.MybankCustomerShoponlineCsocustqryResponseV1BizContentMap;
import com.icbc.api.response.MybankCustomerShoponlineCsocustqryResponseV1.MybankCustomerShoponlineCsocustqryResponseV1BizContentMap.MybankCustomerShoponlineCsocustqryResponseV1ShopFile;

public class MybankCustomerShoponlineCsocustqryTestV1 {
	
	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC/rI/Eymn/difssYpef5iNgTuRjuFFVA0wfzl2ttPXdOEevtTEvGd8CGIcFUGoryGbt2x5yJuEuejyX4RMv4kCX4rsbynEEpCP3MsNOWyRq+D0Lrkov8zgoviA/0alr0sQcfsKo9Mhjl0JO3D32iUkRcb/dZMKQmhj1n+VE4EHITGe+iARmGoUXnOYCdPlKnZdittSO2mrFDWVx6DK2oDmXfxCtS9aJZ+EjzeB8twTlivdy8ok7xZDp8UbQ1dgmQX2Sf2FEYYy+Dji4leyyq/TCKyLxWPwmjWAIoHP2I9rn5AU2uzOPlz/KNFuMbaBTXuIFGTJMaEdQjJPv0oCH9zXAgMBAAECggEAHmMnL9W/VEbRMY88Fps3/kccwIjcYfdfV+7zl0SI9UGMZylt5frQlTNnCni8Phf1xhAgXHZXYYVvzu0htmEvQXcQOUeuYW1oCcbBUklmUvni4hN+O12dB4gOybSu6JmP1v2bBSbP1CQTz+Dimgi1lriRQfLL6vZ4QkvBUksTS6PLSqwpKoh4yGOg/QQjsOhaiqKt5BBAJqPnKzw63nbFZM9q3UMy5wa/mAf4uRUP6UNiAg463wDBL2Ec+nIU5kyXbnxMQZU8ux0I5ibxLIw0+v/d7fcwcaJLAMbejv3va8zGHWmGWjWsEwJGgI+LOOeo3aH1f/WVhcvaqZifGJfg4QKBgQDkhZzjYZU3Dd4s2i7TBl5FIr0k8n3MeHq09867D3OLObIFOdGG1uwVGRWVNliNylCHIBn5B1QdvWsi3XcJ0SV/Y6yhhpXZSZXrcWqp3der8Ex98eG6m/bLUfpfXSxjCQH9dngg8ikoPs2lKGcl7MbHcemLBJV2f9KhytquU5rD7wKBgQDWuLGnjgT9fPSjK+pDH6WFxEJwerE9baIuGO+halk27djET413eaxkjhRADtkcaKFePOAqLh9tA72vNmFWE3rf785sD8rO9Ee9xKcmhs8zm0LJfIqOdCh/1hmQietvtKipk+YHpADyHEzxj85QtGMt+AnB0iKC78uBmfPRagxtmQKBgBzYAIVyu7LHPVPL9j0MgLmZOOaA9P/p9RMCdEWIHb1k+uNN+PRcDFBdaiv7QBIlhySHGMAl/KgEUJGGApBqpaD+o7t/kUprz1B4cE4ggihhnEYJUcSMCwp+vmtrBz8409d6LI98AVXjF3HRN7PBvxVNe3zghzz9xUhGonN+efphAoGBAM6REBfpgNN8ir0PWmcI4uw1StV6s1gtSICgbKgU747wckEpZycxXq++H60gm63A2r0WQ3FQvdraZnlRD4MHaoZU5XkAGFPQndPqhAOEXYGANFgx//nbtbqF9kIe7OiiHEBHkFx5Yo+Iul0Jw6nQYDmxDXHw/lKI7nbX7L/iDeLBAoGAE8fhyi3zkBdYNIkG+DBy4Xa5N8awPrBL/5EunRtXejG9jovPvf9MvOBcvvuJfbWGbcyZxRPHHsaapLMQyE1rSpOTyjoKAwdNBhSV3pygmYqZEGZwaxRnm0u7QQHZIKmNGPgQ8lITTtIle3fD/6rF9ld9fI8b/tc4juChSFuoP84=";
	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
	//Appid：通过内管查看详情，可获取对应的组织机构、接入方式、公钥等信息
	//APP管理中查看
	protected static final String APP_ID = "10000000000000008073";
	
	public static void main(String[] args) throws IcbcApiException {
		DefaultIcbcClient  client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
		
		MybankCustomerShoponlineCsocustqryRequestV1 request = new MybankCustomerShoponlineCsocustqryRequestV1();
		request.setServiceUrl("http://ip:port/api/mybank/customer/shoponline/csocustqry/V1");
		
		MybankCustomerShoponlineCsocustqryRequestV1Biz bizContent = new MybankCustomerShoponlineCsocustqryRequestV1Biz();
//		bizContent.setShop_code("0200EE20232009"); // 商城代码
//		bizContent.setTask_id("210803100017002"); // 营销任务编号
		Map<String, Object> biz_content = bizContent.setBizContent("210803100017002", "0200EE20232009");
		bizContent.setBiz_content(biz_content);

		request.setBizContent(bizContent);

		MybankCustomerShoponlineCsocustqryResponseV1 response = new MybankCustomerShoponlineCsocustqryResponseV1();
		
		try {
			response = client.execute(request, "msgId47");
		} catch(IcbcApiException e) {
			e.printStackTrace();
		}
		
		if (response.isSuccess() && response.getReturnCode() == 0) {
			// 业务成功处理
			MybankCustomerShoponlineCsocustqryResponseV1BizContentMap bizContentMap = new MybankCustomerShoponlineCsocustqryResponseV1BizContentMap();
			bizContentMap = response.getResponse_biz_content();
			MybankCustomerShoponlineCsocustqryResponseV1ShopFile shopFile = new MybankCustomerShoponlineCsocustqryResponseV1ShopFile();
			shopFile = bizContentMap.getShop_file();
			if(shopFile == null) {
				System.out.println("查询结果为空");
			} else {
				shopFile.getSerno(); // 渠道商户代码
				shopFile.getGroupcis(); // 客户信息号
				shopFile.getMercusttype(); // 商户证件类型
				shopFile.getMercustsort(); // 商户证件号
				shopFile.getMercname(); // 特约商户中文名称
				shopFile.getMerename(); // 特约商户英文名称
				shopFile.getMershname(); // 商户简称
				shopFile.getSaledepname(); // 门店名称
				shopFile.getShopaddr(); // 商城地址
				shopFile.getPostcode(); // 邮政编码
				shopFile.getLinkman(); // 联系人
				shopFile.getLinktel(); // 联系电话
				shopFile.getLinkcode(); // 联系人身份证号
				shopFile.getEmail(); // 联系人邮箱
				shopFile.getZoneid(); // 地区号
				shopFile.getAdminnetcode(); // 管辖网点号
				shopFile.getFeetype(); // 收取方式
				shopFile.getClearaccttype(); // 消费清算账户类型
				shopFile.getClearacctno(); // 消费清算账户账号
				shopFile.getClearacctname(); // 消费清算账户户名
				shopFile.getClearbankno(); // 消费清算账户跨行支付行行号
				shopFile.getNcardretacctype(); // 退货清算账户类型
				shopFile.getNcardretaccno(); // 退货清算账户账号
				shopFile.getThcardretacctype(); // 回佣清算账户类型
				shopFile.getThcardretaccno(); // 回佣清算账户账号
				shopFile.getYlkjjfflag(); // 银联卡快捷支付开通标志
				shopFile.getYlkjcardflag(); // 银联卡快捷支付借记卡标志
				shopFile.getDncsinglemaxquota(); // 银联卡快捷支付借记单笔最高限额
				shopFile.getDncdaymaxquota(); // 银联卡快捷支付借记日累计最高限额
				shopFile.getTjkjjfflag(); // 银联卡快捷支付贷记
				shopFile.getCncsinglemaxquota(); // 银联卡快捷支付贷记单笔最高限额
				shopFile.getCncdaymaxquota(); // 银联卡快捷支付贷记日累计最高限额
				shopFile.getYlpay_flag(); // 银联卡网银支付开通标志
				shopFile.getSinglemaxquota(); // 银联卡网银支付单笔最高限额
				shopFile.getDaymaxquota(); // 银联卡网银支付日累计最高限额
				shopFile.getProkind(); // 渠道商本金分流类型
				shopFile.getProtype(); // 渠道商本金分流方式
				shopFile.getProamt(); // 渠道商本金分流单笔金额
				shopFile.getProrate(); // 渠道商本金分流单笔比例
				shopFile.getRatlimi1(); // 本行借记卡手续费率
				shopFile.getBratlimi2(); // 本行贷记卡手续费率
				shopFile.getWxcrate(); // 微信支付手续费率
				shopFile.getAlipaycrate(); // 支付宝支付手续费率
				shopFile.getTask_status(); // 任务状态
			}
			

		} else {
			// 失败
			System.out.println(response.getReturnCode());
			System.out.println(response.getReturnMsg());
		}
	}

}
