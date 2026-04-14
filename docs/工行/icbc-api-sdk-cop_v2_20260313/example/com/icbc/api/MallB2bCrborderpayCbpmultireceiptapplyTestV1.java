package com.icbc.api;

import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.MallB2bPaymentGuideApplyRequestV1;
import com.icbc.api.request.MallB2bPaymentGuideApplyRequestV1.PaymentGuideApplyRequestV1Biz;
import com.icbc.api.request.MallB2bPaymentGuideApplyRequestV1.PaymentGuideApplyRequestV1Biz.Order;
import com.icbc.api.request.MallB2bPaymentGuideApplyRequestV1.PaymentGuideApplyRequestV1Biz.OrderProd;
import com.icbc.api.response.MallB2bPaymentGuideApplyResponseV1;

// B2B跨境支付收款申请（多订单）
public class MallB2bCrborderpayCbpmultireceiptapplyTestV1 {

	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCZfAdmBaJDAa5mMfLmwwS4E0cFTycoGuRXD/xEniso9tyJ95Px4J3AXeajVt/y5Mi1Ce937dbhDyZGXzciXfiLLRUU2FQdKaU3VsSBMMAbQP2cX42jMz5vc6BYmCGYejmlpB0F3Ze6KYl35fACnPQMxDZhf/pkvcUqnGBIBAUcyFFt/difoHbjamL5akGdxXBRNrunKbsQjBBmcY3xlZsufKiEuwaYWUJksB03o6hogmeo7bp4WtWACyUEwlYYi34RVLxaynF4XccJXe9laPhDUikL5bohdwIqadCbjeA1Ug6y3QGlp5kHDVSuFdyh8CA1TivWMk1SIFtvnKbWBSIlAgMBAAECggEAGNKFQ8uvdVTFRsPXO9jYX4jGmKY3d/X5bdco/lPyOPrLqgh/RcQE30KQdWQsbdTwXM8RinzNnrJbh1AI06cM9j1BRpDB8cF1deCzgQ1IVGALZAnUF0vHUjEKAqn/7O//eLl09CjlS/dBw0FNsq59jb8B6HzRGw/R3lVSAPOdfAztbZpfmi7vebA+BzOZaX+xW5L29eEuZqWWcQ3h9Sdp0012ya7Njf1KWzpo01rA42okNALQkJuTixHAxmWCvQB++DpvjSd+y36d5YLNr1siibTEGGOvGmZNZuizC+gY9FW1+aNBXT1p81+yzC9Yk6/GmyCvDX6G44X5XjJmryQhYQKBgQDNp8JCvgdAVcfCjhP3/lkV9wI/wvlI1LWUj18vhPyFELz+vVxzIMpZjLIetBARlYejfa2BpJvJa6X6EghNOzcipk8QjJgQAnaPdn4sSCrL7DlqUmH2hgSXlzM7KPa4LOHH7/2fodgaOU7JaJENwpWe7oBEIcCYc+UqsiGTLGM++QKBgQC/DsUJWCdWwjMR2zMbDY81og2VmBppZdcum1U/s09GTwnV5zaYwvflLx5VhQMAiepTwZSAuJg6tDW8Dp3kkvTCY9CQQawXC3MzIF7yw8JwVduFek+hKaa8qrANKRa2gJvjv63FqLvtNpPo0d6jwhhwWPXmAoR9DHzp95MpEszLjQKBgQDIdKSdeayHwRUdRm+gYoNjLYk7rUhe/Tagv78ACpg4+7+8bccXUPbrooBb/RdMjHvRmEbu/wv6oLr/rWP6I6XyWDjo4KG3uS7nV5BUiWNtsaulPvfN24POXsrI93zEuLq6r+p/FroVlZZ+KzZ4Oxqr971+q4eXEiBmJYskcTerwQKBgE+T9kvTFOeVEbJxS5MMDcc4DVJuvSmDSqJV/qb+p5lQWHdoy5vLMXebLZZPRZXW2+ww6ArJWFazMuJ2cBuBfkLtUjSTtfsPQNddt06EbRtfF4U8Eai98X4J1jRVFenEHao570Yx5ryCDA9gQsyQjp9U3m8EQ2zWY70L6pakBvsNAoGAA9LE6nJ0JQBpQQ/e7pPvzl2IeMnUISCrrNYTArL9eQ13Xv1PLk+P+xX/NU/tIOweC4z4+HTzPT/LQtxNVMHI95v2MY4tGaveUNSl6DcScKIH1zZRtvGE3ML0hfXaYGwo2N8hR7IybuIXdnwkZD1mo+DVyX49f8xqjXSK8MGbJyk=";

	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的APP私钥-----BIjANBgkqhkiG9w0BAQEFAAOCAQ8A-----请使用正确的APP私钥-----BCgKCAQEAgNM2wGxiwU5TwQvLDDF6Z1rER2xKil5H4Al06HiISMkvYgBqzjDVfhZD+0wn5AuewTbmwEVj+p+6b0WOk/xwubW/0NA5GRoi9pKNM3Ee7NXC8YiVa2mneskM/in0lMVnu0dYm3Ui9d36to6O/RqvoUSKOOkveScp8HCQwMxMSagJ2Z2+3S0glVR4pt+LXngBMp1+a4iNzkoEN/ciiAkbqTXbhFOJ0bk9dp+uH/I1Xat7OHJw74Hk9wliHcgsQ8zSbK2JlcUxgpaCnxKe7N4SQ1hBO4W6z2QKb6gNMo2SlqNwVB1owAbFraupoHyZW6LE/SX21wKgsaep0fGEheRxrQIDAQAB";

	protected static final String APP_ID = "10000000000000012090";
	
	public static void main(String[] args) throws Exception{

	DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,"RSA", MY_PRIVATE_KEY,"UTF-8",
				"json",APIGW_PUBLIC_KEY,"AES","g1FUGA1Dfn120r6uy9zQyA==", "", "");
		MallB2bPaymentGuideApplyRequestV1 request = new MallB2bPaymentGuideApplyRequestV1();
		
		request.setServiceUrl("http://ip:port/api/mall/b2b/payment/guide/apply/V1");
		PaymentGuideApplyRequestV1Biz biz = new PaymentGuideApplyRequestV1Biz();
		//订单1
		List<OrderProd> orderProdList=new ArrayList<OrderProd>();
		OrderProd orderProd = new OrderProd();
		orderProd.setProdName("苹果");
		orderProd.setProdBrandName("123");
		orderProd.setProdModel("456");
		orderProd.setProdNorms("789");
		orderProd.setProdAmount(new BigDecimal(100.00));
		orderProd.setProCurrent("RMB");
		orderProd.setProNumber((long)100);
		orderProdList.add(orderProd);

		List<Order> orderList=new ArrayList<Order>();
		Order order = new Order();
		order.setOrderNo("201809280000000752");
		order.setOrderName("123");
		order.setOrderAmount(new BigDecimal(50.00));
		order.setOrderCurrent("RMB");
		order.setPayors_Country("英国");
		order.setPayors_Province("夏威夷州");
		order.setPayors_City("曼彻斯特市");
		order.setPayors_Street("456");
		order.setPayors_LastName("李");
		order.setPayors_FirstName("四");
		order.setPayors_Account("111111111111");
		order.setBuyer_Country("英国");
		order.setBuyer_Province("夏威夷州");
		order.setBuyer_City("曼彻斯特市");
		order.setBuyer_Street("456");
		order.setBuyer_LastName("阿道夫");
		order.setBuyer_FirstName("试试");
		order.setBuyer_Email("90548764@163.com");
		order.setOrderProdList(orderProdList);
		orderList.add(order);
	
		biz.setApplyNo("201809280000000001");
		biz.setApplyName("科创申请");
		biz.setVendorId("10002845");
		biz.setParentVendorId("10001431");
		biz.setApplyCurrent("RMB");
		biz.setApplyAmount(new BigDecimal(100.00));
		biz.setPaymentType(1);
		biz.setPaymentRatio("80|10|10");
		biz.setOrderList(orderList);
		request.setBizContent(biz);

		MallB2bPaymentGuideApplyResponseV1 response;
		try {
			System.err.println("--------------------");
			response = (MallB2bPaymentGuideApplyResponseV1) client.execute(request, System.currentTimeMillis()+"");
			System.out.println(JSONObject.toJSONString(response));
			String resdata = response.getResdata();
			if(resdata != null && resdata.length() >0){
				resdata = URLDecoder.decode(resdata,"UTF-8");
				System.out.println("resdata = " + resdata);
			}else{
				System.out.println("resdata is null");
			}
			if (response.isSuccess()) {
				// 业务成功处理
				System.out.println(response.getReturnCode());
			} else {
				// 失败
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
}

