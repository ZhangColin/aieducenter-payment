package com.icbc.api;

import com.icbc.api.request.ThirdpartyOrderInfobackRequestV1;
import com.icbc.api.response.ThirdpartyOrderInfobackResponseV1;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ThirdpartyOrderInfobackTestV1 {

	private  static final Logger logger = LoggerFactory.getLogger(ThirdpartyOrderInfobackTestV1.class);
	// 1、网关公钥
	protected static final String APIGW_PUBLIC_KEY = "请使用正确的网关公钥";

	// 2、appid
	protected static final String APP_ID = "11000000000000006290";

	// 3、密钥对认证方式，公钥在API平台登记，此处为私钥
	protected static final String MY_PRIVATE_KEY = "请使用正确的APP私钥";

	public static void main(String[] args) throws Exception {

		// 签名类型为RSA2时，需传入appid，私钥和网关公钥，签名类型使用定值IcbcConstants.SIGN_TYPE_RSA2，其他参数使用缺省值
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,
				IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

		ThirdpartyOrderInfobackRequestV1 request = new ThirdpartyOrderInfobackRequestV1();
		// 4、根据测试环境和生产环境替换相应ip和端口
		request.setServiceUrl("https://ip:port/api/ecoupon/ecoupon/qrybyecid/V1");

		// 5、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
		ThirdpartyOrderInfobackRequestV1.ThirdpartyOrderInfobackRequestV1Biz bizContent = new ThirdpartyOrderInfobackRequestV1.ThirdpartyOrderInfobackRequestV1Biz();
		bizContent.setOrderChannel("314");//渠道号
		bizContent.setCustId("EP2016082267922646");// 用户统一认证号
		bizContent.setActId("HD002012DRNBep9acX");//e生活活动ID
		bizContent.setCardNo("6258590057845774");//交易卡号。（支付成功场景下必输）
		bizContent.setOrderTime("20251127135522");//下单时间。格式：yyyyMMddHHmmss
		bizContent.setInvalidTime("20251127135622");//订单超时时间。格式：yyyyMMddHHmmss
		bizContent.setPayTime("20251127135622");//订单支付时间。格式：yyyyMMddHHmmss
		bizContent.setOrderStatus("2");//订单状态。字典值 4-待支付 2-支付成功 3-订单关闭
		bizContent.setGoodsId("g03202220211084735");//商品ID
		bizContent.setGoodsName("瑞幸29元咖啡代金券");//商品名称
		bizContent.setTranWay("0");//0-其它（i豆兑换订单固定上送0）
		bizContent.setTranType("0");//交易类型（i豆兑换订单固定上送0） 字典值	0-消费;	1-退货;	2-团购消费
		bizContent.setPayWay("0");//支付方式（i豆兑换订单固定上送0
		bizContent.setPaytypeCC("0");//提交支付平台支付方式（i豆兑换订单固定上送0）
		bizContent.setOutTradeNo("320207011073202202173276734");//商户订单号
		bizContent.setShopId("32020056813");//商户编号
		bizContent.setShopType("1");//商户类型。字典值	1-特约商户	2-特约部门
		bizContent.setMercName("武汉港业企业管理有限公司");//商户名称
		bizContent.setPointsNum("1000");//商品兑换使用i豆数量
		bizContent.setPointsAmt("1");//i豆抵扣金额
		bizContent.setTotalAmt("100");//订单金额（i豆抵扣金额（优惠前））
		bizContent.setOrderAmt("99");//订单实际支付金额（i豆抵扣金额（优惠后））
		bizContent.setDisAmt("1");//优惠金额
		bizContent.setOrderSource("16");//订单来源（i豆商城订单固定上送16）
		bizContent.setGoodsImgUrl("https://ip:port/oaasImage/operationDefault/4/1772012863786.png");//商品图片地址
		request.setBizContent(bizContent);
		ThirdpartyOrderInfobackResponseV1 response;
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
			String msg_id = dateFormat.format(new Date());
			response = (ThirdpartyOrderInfobackResponseV1) client.execute(request, msg_id);
			if (response.isSuccess()) {
				// 业务成功处理
				ThirdpartyOrderInfobackResponseV1.ThirdpartyOrderInfobackResponseV1BizContent responseV1BizContent = new ThirdpartyOrderInfobackResponseV1.ThirdpartyOrderInfobackResponseV1BizContent();
				String return_code = responseV1BizContent.getReturn_code();
				String return_msg = responseV1BizContent.getReturn_msg();
				logger.info("ReturnCode:" + response.getReturnCode());
			} else {
				// 失败
				logger.info("ReturnCode:" + response.getReturnCode());
			}
		} catch (IcbcApiException e) {
			logger.info("error :" + e.getErrMsg());
		}
	}
}
