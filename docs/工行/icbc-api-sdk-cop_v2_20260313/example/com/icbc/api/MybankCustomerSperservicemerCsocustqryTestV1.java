package com.icbc.api;

import java.util.Map;


import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.request.MybankCustomerSperservicemerCsocustqryRequestV1;
import com.icbc.api.request.MybankCustomerSperservicemerCsocustqryRequestV1.MybankCustomerSperservicemerCsocustqryResponseV1Biz;
import com.icbc.api.response.MybankCustomerSperservicemerCsocustqryResponseV1;
import com.icbc.api.response.MybankCustomerSperservicemerCsocustqryResponseV1.MybankCustomerSperservicemerCsocustqryResponseV1BizContentMap;
import com.icbc.api.response.MybankCustomerSperservicemerCsocustqryResponseV1.MybankCustomerSperservicemerCsocustqryResponseV1BizContentMap.MybankCustomerSperservicemerCsocustqryResponseV1SpecsperInf;

public class MybankCustomerSperservicemerCsocustqryTestV1 {
	
	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC/rI/Eymn/difssYpef5iNgTuRjuFFVA0wfzl2ttPXdOEevtTEvGd8CGIcFUGoryGbt2x5yJuEuejyX4RMv4kCX4rsbynEEpCP3MsNOWyRq+D0Lrkov8zgoviA/0alr0sQcfsKo9Mhjl0JO3D32iUkRcb/dZMKQmhj1n+VE4EHITGe+iARmGoUXnOYCdPlKnZdittSO2mrFDWVx6DK2oDmXfxCtS9aJZ+EjzeB8twTlivdy8ok7xZDp8UbQ1dgmQX2Sf2FEYYy+Dji4leyyq/TCKyLxWPwmjWAIoHP2I9rn5AU2uzOPlz/KNFuMbaBTXuIFGTJMaEdQjJPv0oCH9zXAgMBAAECggEAHmMnL9W/VEbRMY88Fps3/kccwIjcYfdfV+7zl0SI9UGMZylt5frQlTNnCni8Phf1xhAgXHZXYYVvzu0htmEvQXcQOUeuYW1oCcbBUklmUvni4hN+O12dB4gOybSu6JmP1v2bBSbP1CQTz+Dimgi1lriRQfLL6vZ4QkvBUksTS6PLSqwpKoh4yGOg/QQjsOhaiqKt5BBAJqPnKzw63nbFZM9q3UMy5wa/mAf4uRUP6UNiAg463wDBL2Ec+nIU5kyXbnxMQZU8ux0I5ibxLIw0+v/d7fcwcaJLAMbejv3va8zGHWmGWjWsEwJGgI+LOOeo3aH1f/WVhcvaqZifGJfg4QKBgQDkhZzjYZU3Dd4s2i7TBl5FIr0k8n3MeHq09867D3OLObIFOdGG1uwVGRWVNliNylCHIBn5B1QdvWsi3XcJ0SV/Y6yhhpXZSZXrcWqp3der8Ex98eG6m/bLUfpfXSxjCQH9dngg8ikoPs2lKGcl7MbHcemLBJV2f9KhytquU5rD7wKBgQDWuLGnjgT9fPSjK+pDH6WFxEJwerE9baIuGO+halk27djET413eaxkjhRADtkcaKFePOAqLh9tA72vNmFWE3rf785sD8rO9Ee9xKcmhs8zm0LJfIqOdCh/1hmQietvtKipk+YHpADyHEzxj85QtGMt+AnB0iKC78uBmfPRagxtmQKBgBzYAIVyu7LHPVPL9j0MgLmZOOaA9P/p9RMCdEWIHb1k+uNN+PRcDFBdaiv7QBIlhySHGMAl/KgEUJGGApBqpaD+o7t/kUprz1B4cE4ggihhnEYJUcSMCwp+vmtrBz8409d6LI98AVXjF3HRN7PBvxVNe3zghzz9xUhGonN+efphAoGBAM6REBfpgNN8ir0PWmcI4uw1StV6s1gtSICgbKgU747wckEpZycxXq++H60gm63A2r0WQ3FQvdraZnlRD4MHaoZU5XkAGFPQndPqhAOEXYGANFgx//nbtbqF9kIe7OiiHEBHkFx5Yo+Iul0Jw6nQYDmxDXHw/lKI7nbX7L/iDeLBAoGAE8fhyi3zkBdYNIkG+DBy4Xa5N8awPrBL/5EunRtXejG9jovPvf9MvOBcvvuJfbWGbcyZxRPHHsaapLMQyE1rSpOTyjoKAwdNBhSV3pygmYqZEGZwaxRnm0u7QQHZIKmNGPgQ8lITTtIle3fD/6rF9ld9fI8b/tc4juChSFuoP84=";
	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
	//Appid：通过内管查看详情，可获取对应的组织机构、接入方式、公钥等信息
	//APP管理中查看
	protected static final String APP_ID = "10000000000000008074";
	
	public static void main(String[] args) throws IcbcApiException {
		DefaultIcbcClient  client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
		
		MybankCustomerSperservicemerCsocustqryRequestV1 request = new MybankCustomerSperservicemerCsocustqryRequestV1();
		request.setServiceUrl("http://ip:port/api/mybank/customer/sperservicemer/csocustqry/V1");
		
		MybankCustomerSperservicemerCsocustqryResponseV1Biz bizContent = new MybankCustomerSperservicemerCsocustqryResponseV1Biz();
		
//		bizContent.setSerno("200000000000"); // 服务商编号
//		bizContent.setTask_id("201220100000500"); // 营销任务编号
		Map<String, Object> biz_content = bizContent.setBizContent("201220100000500", "200000000000");
		bizContent.setBiz_content(biz_content);
		
		request.setBizContent(bizContent);
		
		MybankCustomerSperservicemerCsocustqryResponseV1 response = new MybankCustomerSperservicemerCsocustqryResponseV1();
		try {
			response = client.execute(request, "msgId28");
		} catch(IcbcApiException e) {
			e.printStackTrace();
		}
		
		if (response.isSuccess() && response.getReturnCode() == 0) {
			// 业务成功处理
			MybankCustomerSperservicemerCsocustqryResponseV1BizContentMap bizContentMap = new MybankCustomerSperservicemerCsocustqryResponseV1BizContentMap();
			bizContentMap = response.getResponse_biz_content();
			MybankCustomerSperservicemerCsocustqryResponseV1SpecsperInf specsperInf = new MybankCustomerSperservicemerCsocustqryResponseV1SpecsperInf();
			specsperInf = bizContentMap.getSpecsper_inf();
			if(specsperInf == null) {
				System.out.println("查询结果为空");
			} else {
				specsperInf.getSerno(); // 服务商商户编号
				specsperInf.getSertype(); // 服务商商户类型
				specsperInf.getSerorigin(); // 平台服务商来源
				specsperInf.getRecstat(); // 档案记录状态
				specsperInf.getApiid(); // 服务商API证书编号
				specsperInf.getZoneno(); // 服务商地区号
				specsperInf.getAdmibrno(); // 管辖行网点号
				specsperInf.getMerinmode(); // 商户入账模式
				specsperInf.getFreezeday(); // 资金冻结天数
				specsperInf.getWxflag(); // 微信开通标志
				specsperInf.getZfbflag(); // 支付宝开通标志
				specsperInf.getUnitno(); // 单位客户编号
				specsperInf.getSercname(); // 服务商户中文名称
				specsperInf.getSerename(); // 服务商户英文名称
				specsperInf.getSershname(); // 服务商户简称
				specsperInf.getBacctype(); // 服务商本金分润账户类型
				specsperInf.getBaccno(); // 服务商本金分润账户
				specsperInf.getBaccname(); // 服务商本金分润账户户名
				specsperInf.getBaccbankno(); // 服务商本金分润清算行行号
				specsperInf.getBprotype(); // 服务商本金分润方式
				specsperInf.getBproamt(); // 服务商本金分润单笔金额
				specsperInf.getBprorate(); // 服务商本金分润单笔比例
				specsperInf.getRacctype(); // 服务商分润退货账户类型
				specsperInf.getRaccno(); // 服务商分润退货账户
				specsperInf.getSfeeacctype(); // 服务商手续费账户类型
				specsperInf.getSfeeaccno(); // 服务商手续费账户
				specsperInf.getSfeecycle(); // 服务商手续费记账周期
				specsperInf.getSfeemonday(); // 服务商手续费每月记账日
				specsperInf.getScostacctype(); // 服务商服务费账户类型
				specsperInf.getScostaccno(); // 服务商服务费账户
				specsperInf.getScostcycle(); // 服务商服务费记账周期
				specsperInf.getScostmonday(); // 服务商服务费每月记账日
				specsperInf.getOfeeacctype(); // 其他机构手续费账户类型
				specsperInf.getOfeeaccno(); // 其他机构手续费账户
				specsperInf.getOfeecycle(); // 其他机构手续费记账周期
				specsperInf.getOfeemonday(); // 其他机构手续费每月记账日
				specsperInf.getShopaddr(); // 商城地址
				specsperInf.getPostcode(); // 邮政编码
				specsperInf.getMerlinktel(); // 商户门店联系电话
				specsperInf.getLinkman(); // 联系人
				specsperInf.getLinktel(); // 联系电话
				specsperInf.getLinksort(); // 联系人证件类型
				specsperInf.getLinkcode(); // 联系人证件号码
				specsperInf.getLinkemail(); // 联系人邮箱
				specsperInf.getLpname(); // 法定代表人姓名
				specsperInf.getLpcustsort(); // 法人代表证件类型
				specsperInf.getLpcustcode(); // 法人代表证件号码
				specsperInf.getShopnamec(); // 商城中文名称
				specsperInf.getShopnamee(); // 商城英文名称
				specsperInf.getDomainname(); // 商城域名
				specsperInf.getSolbhdrfeetype(); // 服务商线上本行卡借记手续费计算方式
				specsperInf.getSolbhdrfeesigamt(); // 服务商线上本行卡借记单笔手续费金额
				specsperInf.getSolbhdrfeesigrate(); // 服务商线上本行卡借记单笔手续费费率
				specsperInf.getSolbhcrfeetype(); // 服务商线上本行卡贷记手续费计算方式
				specsperInf.getSolbhcrfeesigamt(); // 服务商线上本行卡贷记单笔手续费金额
				specsperInf.getSolbhcrfeesigrate(); // 服务商线上本行卡贷记单笔手续费费率
				specsperInf.getSolthdrfeetype(); // 服务商线上他行卡借记手续费计算方式
				specsperInf.getSolthdrfeesigamt(); // 服务商线上他行卡借记单笔手续费金额
				specsperInf.getSolthdrfeesigrate(); // 服务商线上他行卡借记单笔手续费费率
				specsperInf.getSolthcrfeetype(); // 服务商线上他行卡贷记手续费计算方式
				specsperInf.getSolthcrfeesigamt(); // 服务商线上他行卡贷记单笔手续费金额
				specsperInf.getSolthcrfeesigrate(); // 服务商线上他行卡贷记单笔手续费费率
				specsperInf.getSolzfbfeetype(); // 服务商线上支付宝单笔手续费计算方式
				specsperInf.getSolzfbfeesigamt(); // 服务商线上支付宝单笔手续费金额
				specsperInf.getSolzfbfeesigrate(); // 服务商线上支付宝单笔手续费费率
				specsperInf.getSolwxfeetype(); // 服务商线上微信单笔手续费计算方式
				specsperInf.getSolwxfeesigamt(); // 服务商线上微信单笔手续费金额
				specsperInf.getSolwxfeesigrate(); // 服务商线上微信单笔手续费费率
				specsperInf.getSolbhdrcosttype(); // 服务商线上本行卡借记服务费计算方式
				specsperInf.getSolbhdrcostsigamt(); // 服务商线上本行卡借记单笔服务费金额
				specsperInf.getSolbhdrcostsigrate(); // 服务商线上本行卡借记单笔服务费费率
				specsperInf.getSolbhcrcosttype(); // 服务商线上本行卡贷记服务费计算方式
				specsperInf.getSolbhcrcostsigamt(); // 服务商线上本行卡贷记单笔服务费金额
				specsperInf.getSolbhcrcostsigrate(); // 服务商线上本行卡贷记单笔服务费费率
				specsperInf.getSolthdrcosttype(); // 服务商线上他行卡借记服务费计算方式
				specsperInf.getSolthdrcostsigamt(); // 服务商线上他行卡借记单笔服务费金额
				specsperInf.getSolthdrcostsigrate(); // 服务商线上他行卡借记单笔服务费费率
				specsperInf.getSolthcrcosttype(); // 服务商线上他行卡贷记服务费计算方式
				specsperInf.getSolthcrcostsigamt(); // 服务商线上他行卡贷记单笔服务费金额
				specsperInf.getSolthcrcostsigrate(); // 服务商线上他行卡贷记单笔服务费费率
				specsperInf.getSolzfbcosttype(); // 服务商线上支付宝服务费计算方式
				specsperInf.getSolzfbcostsigamt(); // 服务商线上支付宝单笔服务费金额
				specsperInf.getSolzfbcostsigrate(); // 服务商线上支付宝单笔服务费费率
				specsperInf.getSolwxcosttype(); // 服务商线上微信服务费计算方式
				specsperInf.getSolwxcostsigamt(); // 服务商线上微信单笔服务费金额
				specsperInf.getSolwxcostsigrate(); // 服务商线上微信单笔服务费费率
				specsperInf.getOolbhdrfeetype(); // 其他机构线上本行卡借记手续费计算方方式
				specsperInf.getOolbhdrfeesigamt(); // 其他机构线上本行卡借记单笔手续费金额
				specsperInf.getOolbhdrfeesigrate(); // 其他机构线上本行卡借记单笔手续费费率
				specsperInf.getOolbhcrfeetype(); // 其他机构线上本行卡贷记手续费计算方式
				specsperInf.getOolbhcrfeesigamt(); // 其他机构线上本行卡贷记单笔手续费金额
				specsperInf.getOolbhcrfeesigrate(); // 其他机构线上本行卡贷记单笔手续费费率
				specsperInf.getOolthdrfeetype(); // 其他机构线上他行卡借记手续费计算方式
				specsperInf.getOolthdrfeesigamt(); // 其他机构线上他行卡借记单笔手续费金额
				specsperInf.getOolthdrfeesigrate(); // 其他机构线上他行卡借记单笔手续费费率
				specsperInf.getOolthcrfeetype(); // 其他机构线上他行卡贷记手续费计算方式
				specsperInf.getOolthcrfeesigamt(); // 其他机构线上他行卡贷记单笔手续费金额
				specsperInf.getOolthcrfeesigrate(); // 其他机构线上他行卡贷记单笔手续费费率
				specsperInf.getOolzfbfeetype(); // 其他机构线上支付宝手续费计算方式
				specsperInf.getOolzfbfeesigamt(); // 其他机构线上支付宝单笔手续费金额
				specsperInf.getOolzfbfeesigrate(); // 其他机构线上支付宝单笔手续费费率
				specsperInf.getOolwxfeetype(); // 其他机构线上微信手续费计算方式
				specsperInf.getOolwxfeesigamt(); // 其他机构线上微信单笔手续费金额
				specsperInf.getOolwxfeesigrate(); // 其他机构线上微信单笔手续费费率
				specsperInf.getWxconnection(); // 微信直间联标志
				specsperInf.getWxsubmerid(); // 微信商户识别码
				specsperInf.getWxratechanl(); // 微信费率通道
				specsperInf.getWxmcc(); // 微信经营类目
				specsperInf.getWxsubappid(); // 子商户应用ID
				specsperInf.getZfbconnection(); // 支付宝直间联标志
				specsperInf.getZfbsubmerid(); // 支付宝商户识别码
				specsperInf.getZfbratechanl(); // 支付宝结算成本通道
				specsperInf.getZfbmcc(); // 支付宝经营类目
				specsperInf.getLastoper_date(); // 最后操作日期
			}

			

		} else {
			// 失败
			System.out.println(response.getReturnCode());
			System.out.println(response.getReturnMsg());
		}
	}

}
