package com.icbc.api;

import org.junit.Test;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.request.OrderDetailDeliverRequestV1;
import com.icbc.api.request.OrderDetailDeliverRequestV1.OrderDetailDeliverRequestV1Biz;
import com.icbc.api.response.OrderDetailDeliverResponseV1;

public class OrderDetailDeliverV1Test {
	
	//appid
	protected static final String APP_ID = "xxxxxx";
	
	//合作方私钥
	protected static final String MY_PRIVATE_KEY = "xxxxxx";
	
	//网关公钥
	protected static final String APIGW_PUBLIC_KEY = "xxxxxx";
	

	public void test_cop() throws IcbcApiException {
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
		OrderDetailDeliverRequestV1 request = new OrderDetailDeliverRequestV1();
		
		//根据测试环境和生产环境替换相应ip和端口
		request.setServiceUrl("https://ip:port/api/order/detail/deliver/V1");

		OrderDetailDeliverRequestV1Biz bizContent = new OrderDetailDeliverRequestV1Biz();
		bizContent.put("vendorId","AESU2023062900000018");
		bizContent.put("orderId","202408290740298398");
		bizContent.put("consigneeProvince","江苏");
		bizContent.put("consigneeCity","常州市");
		bizContent.put("consigneeCounty","市辖区");
		bizContent.put("consigneeDetailAddress","维吾尔");
		bizContent.put("consigneeZip","100000");
		bizContent.put("consigneeName","陈冲");
		bizContent.put("consigneeMobile","13521201214");
		bizContent.put("consigneeTel","021000000");
		bizContent.put("shipmentsId","201802281004448811");
		bizContent.put("shipmentsDate","20180228100444881");
		bizContent.put("shipmentName","张三");
		bizContent.put("shipmentTel","13521201214");
		bizContent.put("deliveryId","521512012512084");
		bizContent.put("deliverMerchant","顺丰");
		bizContent.put("shipId","201802280000000012");
		bizContent.put("attachment","清单1.xls");
		bizContent.put("attachmentData","文件流转的字符串");
		List<Map> prodList = new ArrayList<>();
		Map prod = new HashMap();
		prod.put("prodId","2604000211");
		prod.put("quantity",1);
		prodList.add(prod);
		bizContent.put("prodList",prodList);
		
		request.setBizContent(bizContent);

		OrderDetailDeliverResponseV1 response;
		try {
			response = client.execute(request, "msgId");
			if (response.isSuccess()) {
				// 业务成功处理
				if("0".equals(response.getCode())){
					String retMsg = response.getMsg();
				}else{
					String retMsg = response.getMsg();
				}
				
			} else {
				//失败
			}
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}
	}
	
}
