package com.icbc.api;


import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.MybankPayOverSeasCppayapplyRequestV1;
import com.icbc.api.response.MybankPayOverSeasCppayapplyResponseV1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

public class MybankPayOverSeasCppayapplyTestV1 {
	
	public static Logger log = Logger.getLogger(MybankPayOverSeasCppayapplyTestV1.class.getName());


	//1、网关公钥	
	protected static final String APIGW_PUBLIC_KEY = "xxxx";

	//2、appid	
	protected static final String APP_ID = "123";
	
	//3、密钥对认证方式，公钥在API平台登记，此处为私钥	
	protected static final String MY_PRIVATE_KEY = "xxxx";
	
			
	
	public static void main(String[] args) {

		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);


		MybankPayOverSeasCppayapplyRequestV1 request = new MybankPayOverSeasCppayapplyRequestV1();
		
		request.setServiceUrl("http://IP:PORT/api/mybank/pay/overseas/cppayapply/V1");

//		long timeMillis = System.currentTimeMillis();
//		String time=new SimpleDateFormat("yyyyMMddHHmmss").format(timeMillis) ;// 
		MybankPayOverSeasCppayapplyRequestV1.MybankPayOverSeasCppayapplyRequestV1Biz bizContent = new MybankPayOverSeasCppayapplyRequestV1.MybankPayOverSeasCppayapplyRequestV1Biz();



		List<MybankPayOverSeasCppayapplyRequestV1.BeanGoodsInfo> beanGoodsInfoList = new ArrayList<MybankPayOverSeasCppayapplyRequestV1.BeanGoodsInfo>();
		MybankPayOverSeasCppayapplyRequestV1.BeanGoodsInfo beanGoodsInfo = new MybankPayOverSeasCppayapplyRequestV1.BeanGoodsInfo();
		beanGoodsInfo.setGoodsSubId("1");
		beanGoodsInfo.setGoodsName("商品名称01");
		beanGoodsInfo.setMallName("北京链家房地产经纪有限公司");
		beanGoodsInfo.setGoodsNumber("1");
		beanGoodsInfo.setGoodsAmt("1000");
		beanGoodsInfo.setGoodsUnit("个");
		beanGoodsInfo.setGoodsCurr("1");
		beanGoodsInfoList.add(beanGoodsInfo);

		List<MybankPayOverSeasCppayapplyRequestV1.PayAccountDetailBean> beanRecvMallInfoList = new ArrayList<MybankPayOverSeasCppayapplyRequestV1.PayAccountDetailBean>();
		MybankPayOverSeasCppayapplyRequestV1.PayAccountDetailBean beanRecvMallInfo = new MybankPayOverSeasCppayapplyRequestV1.PayAccountDetailBean();
		beanRecvMallInfo.setMallCode("123");
		beanRecvMallInfo.setMallName("北京链家房地产经纪有限公司");
		beanRecvMallInfo.setPayeeLicense("1");
		beanRecvMallInfo.setPayeeLicenseType("0");
		beanRecvMallInfo.setPayeePhone("123");
		beanRecvMallInfo.setPayeeSysflag("1");
		beanRecvMallInfo.setPayeeAccno("123");
		beanRecvMallInfo.setPayeeCompanyName("电信诈骗账户");
		beanRecvMallInfo.setPayeeAmount("100");
		beanRecvMallInfo.setPayeeAccCurr("1");
		beanRecvMallInfo.setRbankname("中国工商银行-北京分行");
		beanRecvMallInfo.setPayeeBankCountry("840");
		beanRecvMallInfo.setPayeeBankSign("CHASUS33XXX");
		beanRecvMallInfo.setPayeeCountry("840");
		beanRecvMallInfo.setPayeeAddress("123");
		beanRecvMallInfoList.add(beanRecvMallInfo);

		bizContent.setAgreeCode("123");
		bizContent.setPartnerSeq("123");
		bizContent.setPayChannel("1");
		bizContent.setInternationalFlag("2");
		bizContent.setPayMode("2");
		bizContent.setReservDirect("1");
		bizContent.setOrderCode("123");
		bizContent.setOrderAmount("100");
		bizContent.setOrderCurr("1");
		bizContent.setPayAmount("100");
		bizContent.setPayCurr("1");
		bizContent.setPayMemno("123");
		bizContent.setOrderRemark("备注");
		bizContent.setReceiptRemark("");
		bizContent.setPurpose("");
		bizContent.setSummary("");
		bizContent.setSubmitTime("20220228160800");
		bizContent.setLanguageLocal("zh_CN");
		bizContent.setReturnUrl("");
		bizContent.setCallbackUrl("www.baidu.com");
		bizContent.setPartnerSeqOrigin("");
		bizContent.setSumPayamtOrigin("");

		bizContent.setPayeeList(beanRecvMallInfoList);
		bizContent.setGoodsList(beanGoodsInfoList);
		request.setBizContent(bizContent);

		Random rand = new Random();
		String msgId = rand.nextInt(99999) + "msg";
		log.fine("msgId:"+msgId);
		client.setHeaderParam("X-Fova-Region", "00119");

		request.setBizContent(bizContent);

		MybankPayOverSeasCppayapplyResponseV1 response = new MybankPayOverSeasCppayapplyResponseV1(); 
		
		try {
			response = client.execute(request, msgId);
			log.info("response:" + JSON.toJSONString(response));
			if (response.isSuccess()) {
				// 业务成功处理
				log.info("success");
			} else {
				// 失败
				log.info("error");
			}
		} catch (IcbcApiException e) {
			e.printStackTrace();
		} finally {
		}
	}
}
