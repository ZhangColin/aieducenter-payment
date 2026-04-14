package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.LbpasiaEcommerceOrderMutualRequestV1;
import com.icbc.api.response.LbpasiaEcommerceOrderMutualResponseV1;

/**
 * 万象电商平台服务
 * 
 * 请商户配置SDK中的lib文件夹中的jar包后，根据备注1-6提示进行数据替换
 */public class LbpasiaEcommerceOrderMutualTestV1 {

	    public static void main(String[] args) {
	        // 示例总体说明：请使用F-APIP最新的行外网关SDK进行开发，签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
	    	//以下请求以分账请求为例
	    	
	        //1、密钥对认证方式，公钥在F-APIP平台登记，此处为私钥
	        String MY_PRIVATE_KEY = "xxxx";
	        //2、appid
	        String APP_ID = "xxxxxx";
	        //3、网关公钥
	        String APIGW_PUBLIC_KEY = "xxxx";

	        //4、创建与F-APIP外网网关的连接
	        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String)null, (String)null, (String)null, (String)null);
	        //5、设置请求对象
	        LbpasiaEcommerceOrderMutualRequestV1 request = new LbpasiaEcommerceOrderMutualRequestV1();
	        //6、设置请求路径；联测时，请联系我行告知并替换成测试环境地址。
	        request.setServiceUrl("http://[需替换]工行API网关地址/api/lbpasia/ecommerce/order/mutual/V1");
	        //7、设置业务参数
	        LbpasiaEcommerceOrderMutualRequestV1.LbpasiaEcommerceOrderMutualRequestV1Biz bizContent 
	            = new LbpasiaEcommerceOrderMutualRequestV1.LbpasiaEcommerceOrderMutualRequestV1Biz();
	        LbpasiaEcommerceOrderMutualRequestV1.Absp absp = bizContent.getAbsp();
	        LbpasiaEcommerceOrderMutualRequestV1.Control control = absp.getControl();
	        LbpasiaEcommerceOrderMutualRequestV1.OrderData orderData = absp.getOrderData();
	        LbpasiaEcommerceOrderMutualRequestV1.OrderPublicInfo orderPublicInfo = orderData.getOrderPublicInfo();
	        LbpasiaEcommerceOrderMutualRequestV1.OrderDistriInfo orderDistriInfo = orderData.getOrderDistriInfo();

        
	        //8、组装请求参数
	        control.setTarApp("ASIAECP");
	        control.setTarFunc("ORDER_RECEIVE");
	        control.setSrcApp("F-MBANKP(FOVA)");
	        control.setSrcSeq("20240717190411");

	        orderPublicInfo.setOrderNum("SO20230528050005");
	        orderPublicInfo.setSuppNo("10401");
	        orderPublicInfo.setSuppName("TPhanKham Power Agriculture Development");
	        orderPublicInfo.setAmount("100000");
	        orderPublicInfo.setCurrency("CNY");
	        orderPublicInfo.setBuyerId("10304");
	        orderPublicInfo.setBuyerName("文焕物流有限公司");
	        orderPublicInfo.setEcpSaleMan("HUANG XUE");
	        orderPublicInfo.setEcpCheckMan("LI HUA");
	        orderPublicInfo.setOrderDate("20230630");
	        orderPublicInfo.setOrderTime("074118");
	        orderPublicInfo.setReqType("003");
	        orderPublicInfo.setUpdTime("20240717191011");
	        
	        orderDistriInfo.setDistriAmount("5000");

	        
	        orderData.setOrderPublicInfo(orderPublicInfo);
	        orderData.setOrderDistriInfo(orderDistriInfo);
	        absp.setOrderData(orderData);
	        absp.setControl(control);

	        bizContent.setAbsp(absp);
	        
	        request.setBizContent(bizContent);

	        LbpasiaEcommerceOrderMutualResponseV1 response = new LbpasiaEcommerceOrderMutualResponseV1();;
	        try {
	            System.out.println("request:"+ JSON.toJSONString(request));
	            response = client.execute(request);
	            System.out.println("response:" + JSON.toJSONString(response));
	        } catch (IcbcApiException e) {
	            e.printStackTrace();
	        }
	        String returnCode = response.getAbspRespon().getControlRespon().getRetcode();
	    }
	}


