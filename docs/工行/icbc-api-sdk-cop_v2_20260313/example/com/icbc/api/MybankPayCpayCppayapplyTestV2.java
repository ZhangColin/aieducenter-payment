package api.test;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.MybankPayCpayCppayapplyRequestV2;
import com.icbc.api.response.MybankPayCpayCppayapplyResponseV2;

import java.util.*;
import java.util.logging.Logger;

public class MybankPayCpayCppayapplyTestV2 {
	protected static final String MY_PRIVATE_KEY = "xxx";
	protected static final String APIGW_PUBLIC_KEY = "xxxx";
	
	protected static final String APP_ID = "xxx";
	
	static Logger log = Logger.getLogger(MybankPayCpayCppayapplyTestV2.class.getName());
	public static void main(String[] args) {
	
//		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
		MybankPayCpayCppayapplyRequestV2 request = new MybankPayCpayCppayapplyRequestV2();
		request.setServiceUrl("http://IP:PORT/api/mybank/pay/cpay/cppayapply/V2");
		
//		long timeMillis = System.currentTimeMillis();
//		String time=new SimpleDateFormat("yyyyMMddHHmmss").format(timeMillis) ;// 当前日期
		MybankPayCpayCppayapplyRequestV2.MybankPayCpayCppayapplyRequestV2Biz bizContent = new MybankPayCpayCppayapplyRequestV2.MybankPayCpayCppayapplyRequestV2Biz();
		
		List<MybankPayCpayCppayapplyRequestV2.BeanGoodsInfo> beanGoodsInfoList = new ArrayList<MybankPayCpayCppayapplyRequestV2.BeanGoodsInfo>();
		MybankPayCpayCppayapplyRequestV2.BeanGoodsInfo beanGoodsInfo = new MybankPayCpayCppayapplyRequestV2.BeanGoodsInfo();
		beanGoodsInfo.setGoodsSubId("1");
		beanGoodsInfo.setGoodsName("倚天剑");
		beanGoodsInfo.setPayeeCompanyName("张三");
		beanGoodsInfo.setGoodsNumber("1");
		beanGoodsInfo.setGoodsAmt("10000");
		beanGoodsInfo.setGoodsUnit("单位");
		beanGoodsInfoList.add(beanGoodsInfo);
		
		List<MybankPayCpayCppayapplyRequestV2.BeanRecvMallInfo> beanRecvMallInfoList = new ArrayList<MybankPayCpayCppayapplyRequestV2.BeanRecvMallInfo>();
		MybankPayCpayCppayapplyRequestV2.BeanRecvMallInfo beanRecvMallInfo = new MybankPayCpayCppayapplyRequestV2.BeanRecvMallInfo();
		beanRecvMallInfo.setMallCode("0200EH0013035");
		beanRecvMallInfo.setMccCode("323451");
		beanRecvMallInfo.setMccName("1");
		beanRecvMallInfo.setBusinessLicense("1");
		beanRecvMallInfo.setBusinessLicenseType("0");
		beanRecvMallInfo.setMallName("mallName");
		beanRecvMallInfo.setPayeeCompanyName("test");
		beanRecvMallInfo.setPayeeSysflag("3");
		beanRecvMallInfo.setPayeeBankCode("");
		beanRecvMallInfo.setPayeeHeadBankCode("123456789120");
		beanRecvMallInfo.setPayeeAccno("aaa-111111");
		beanRecvMallInfo.setPayAmount("11223311");
		beanRecvMallInfo.setRbankname("afica bank");
		beanRecvMallInfo.setPayeeBankCountry("840");
		beanRecvMallInfo.setPayeeBankSign("CHASUS33XXX");
		beanRecvMallInfo.setPayeeCountry("840");
		beanRecvMallInfo.setPayeeAddress("urtyusastt");
		beanRecvMallInfo.setRacaddress1("urtyusastt");
		beanRecvMallInfo.setRacaddress2("urtyusastt");
		beanRecvMallInfo.setRacaddress3("urtyusastt");
		beanRecvMallInfo.setRacaddress4("urtyusastt");
		beanRecvMallInfo.setRacpostcode("");
		beanRecvMallInfo.setAgentbic("");
		beanRecvMallInfo.setPayeePhone("13117512457");
		beanRecvMallInfo.setPayeeOrgcode("1");
		beanRecvMallInfo.setReceivableAmount("");
		beanRecvMallInfo.setPayeeWalletId("1");
		beanRecvMallInfo.setPayeeWalletName("中国长城");
		beanRecvMallInfoList.add(beanRecvMallInfo);
		
		bizContent.setAgreeCode("0020000998060410006041000000687007");
		bizContent.setPartnerSeq("030240098767001490600157339");
		bizContent.setPayChannel("2");
		bizContent.setInternationalFlag("2");
		bizContent.setPayMode("1");
		bizContent.setReservDirect("1");
		bizContent.setOperType("301");
		bizContent.setPayEntitys("1");
		bizContent.setAsynFlag("0");
		bizContent.setLogonId("");
		bizContent.setPayerAccno("");
		bizContent.setPayerAccname("");
		bizContent.setPayerFeeAccno("");
		bizContent.setPayerFeeAccName("");
		bizContent.setPayerFeeCurr("");
		bizContent.setPayMemno("0200EH0013035");
		bizContent.setOrgcode("10237605X");
		bizContent.setOrderCode("2019062730002");
		bizContent.setOrderAmount("11223311");
		bizContent.setOrderCurr("88");
		bizContent.setSumPayamt("11223311");
		bizContent.setOrderRemark("订单备注-直接支付-境内");
		bizContent.setRceiptRemark("回单补充信息备注");
		bizContent.setPurpose("");
		bizContent.setSummary("");
		bizContent.setSubmitTime("20240615162211");
		bizContent.setReturnUrl("");
		bizContent.setCallbackUrl("www.baidu.com");
		bizContent.setAgreementId("34567891");
		bizContent.setInvoiceId("");
		bizContent.setManifestId("");
		bizContent.setAgreementImageId("");
		bizContent.setEnterpriseName("");
		bizContent.setPayRemark("");
		bizContent.setBakReserve1("");
		bizContent.setBakReserve2("");
		bizContent.setBakReserve3("");
		bizContent.setPartnerSeqOrigin("");
		bizContent.setSumPayamtOrigin("");
		bizContent.setReporterName("");
		bizContent.setReporterContact("");
		bizContent.setIdentityMode("0");
		bizContent.setPayerPhone("");
		bizContent.setPayerWalletId("");
		bizContent.setPayerWalletName("");
		bizContent.setRepetCard("2");
		bizContent.setBatchNo("");
		
		bizContent.setIsPersonalPay("1");
		bizContent.setOrderExpireDate("2024-10-10");
		bizContent.setAcceptTerminalId("502");
		bizContent.setAcceptTerminalIp("127.0.0.1");
		bizContent.setAcceptAddress("受理终端地理位置");
		bizContent.setMemberOrg("02000620");
		bizContent.setPayerBankCode("");
		bizContent.setDicbusscode("");
		bizContent.setIsOnlyShowTPlus0Bank("1");
		bizContent.setIsHideScanPaymentFunction("1");
		bizContent.setReInterestFlag(0);
		bizContent.setReInterestType(0);

		Map personalMsg = new HashMap<>();
		personalMsg.put("goods_name","goods_name");
		personalMsg.put("access_type","5");
		personalMsg.put("mer_custom_id","100001");
		personalMsg.put("mer_id","020053010625");
		personalMsg.put("scheme","scheme");
		personalMsg.put("biz_type","100001");
		personalMsg.put("goods_id","goods_id");
		personalMsg.put("remark","remark");
		personalMsg.put("order_apd_inf","order_apd_inf");
		personalMsg.put("body","body");
		personalMsg.put("mer_custom_phone","mer_custom_phone");
		personalMsg.put("extend_info","extend_info");
		personalMsg.put("mer_prtcl_no","0200530106250201");
		personalMsg.put("sub_open_id","openid");
		personalMsg.put("wxpay_detail","wxpay_detail");
		personalMsg.put("goods_address","goods_address");
		personalMsg.put("package_name","packagename");
//		personalMsg.put("goods_num","E支付PC页面上展示");
//		personalMsg.put("alipay_detail","支付宝商品详细描述，对于使用单品优惠的商户，该字段必");
		personalMsg.put("shop_appid","appid");
		
		bizContent.setPersonalMsg(personalMsg);
		
		bizContent.setPayeeList(beanRecvMallInfoList);
		bizContent.setGoodsList(beanGoodsInfoList);
		request.setBizContent(bizContent);
		
		Random rand = new Random();
		String msgId = rand.nextInt(99999) + "msg";
		log.fine("msgId:"+msgId);
		
		request.setBizContent(bizContent);
		
		MybankPayCpayCppayapplyResponseV2 response;
		try {
			response = client.execute(request,msgId);
			log.fine("JSONObject.toJSONString(response):"+JSONObject.toJSONString(response));
			if (response.isSuccess()) {
				// 业务成功处理
				log.fine("response.getReturnCode():"+response.getReturnCode());
			} else {
				//失败
				log.fine("response.getReturnCode():"+response.getReturnCode());
				log.fine("response.getReturnMsg():"+response.getReturnMsg());
			}
		} catch (IcbcApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
