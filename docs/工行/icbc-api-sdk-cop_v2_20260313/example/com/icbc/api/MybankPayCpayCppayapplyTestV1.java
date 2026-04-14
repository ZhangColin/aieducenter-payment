package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.MybankPayCpayCppayapplyRequestV1;
import com.icbc.api.response.MybankPayCpayCppayapplyResponseV1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MybankPayCpayCppayapplyTestV1 {

	protected static final String MY_PRIVATE_KEY = "xxxx";

	protected static final String APIGW_PUBLIC_KEY = "xxxx";

	protected static final String APP_ID = "1234";

	public static void main(String[] args) {
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

		MybankPayCpayCppayapplyRequestV1 request = new MybankPayCpayCppayapplyRequestV1();
		request.setServiceUrl("http://IP:PORT/api/mybank/pay/cpay/cppayapply/V1");

		MybankPayCpayCppayapplyRequestV1.MybankPayCpayCppayapplyRequestV1Biz bizContent = new MybankPayCpayCppayapplyRequestV1.MybankPayCpayCppayapplyRequestV1Biz();
		MybankPayCpayCppayapplyRequestV1.BeanGoodsInfo beanGoodsInfo = new MybankPayCpayCppayapplyRequestV1.BeanGoodsInfo();
		MybankPayCpayCppayapplyRequestV1.BeanRecvMallInfo beanRecvMallInfo = new MybankPayCpayCppayapplyRequestV1.BeanRecvMallInfo();

		List<MybankPayCpayCppayapplyRequestV1.BeanGoodsInfo> beanGoodsInfoList = new ArrayList<MybankPayCpayCppayapplyRequestV1.BeanGoodsInfo>();
		List<MybankPayCpayCppayapplyRequestV1.BeanRecvMallInfo> beanRecvMallInfoList = new ArrayList<MybankPayCpayCppayapplyRequestV1.BeanRecvMallInfo>();

		beanGoodsInfo.setGoodsSubId("1");
		beanGoodsInfo.setGoodsName("1");
		beanGoodsInfo.setPayeeCompanyName("payeeCompanyName");

		beanGoodsInfo.setGoodsNumber("1");
		beanGoodsInfo.setGoodsAmt("100");
		beanGoodsInfo.setGoodsUnit("单位");

		beanGoodsInfoList.add(beanGoodsInfo);

		beanRecvMallInfo.setMallCode("1001");
		beanRecvMallInfo.setMallName("mallName");
		beanRecvMallInfo.setMccCode("1");
		beanRecvMallInfo.setMccName("1");
		beanRecvMallInfo.setBusinessLicense("1");
		beanRecvMallInfo.setBusinessLicenseType("0");
		beanRecvMallInfo.setPayeeCompanyName("recvname");
		beanRecvMallInfo.setPayeeSysflag("1");
		beanRecvMallInfo.setPayeeBankCode("1");
		beanRecvMallInfo.setPayeeAccno("1");
		beanRecvMallInfo.setPayAmount("1");
		beanRecvMallInfo.setPayeeBankCountry("1");
		beanRecvMallInfo.setRbankname("1");
		beanRecvMallInfo.setPayeeBankSign("1");
		beanRecvMallInfo.setPayeeCountry("1");
		beanRecvMallInfo.setPayeeAddress("1");
		beanRecvMallInfo.setRacaddress1("1");
		beanRecvMallInfo.setRacaddress2("1");
		beanRecvMallInfo.setRacaddress3("1");
		beanRecvMallInfo.setRacaddress4("1");
		beanRecvMallInfo.setRacpostcode("1");
		beanRecvMallInfo.setAgentbic("1");
		beanRecvMallInfoList.add(beanRecvMallInfo);

		bizContent.setPayMode("1");
		bizContent.setPayEntitys("1");
		bizContent.setPartnerSeq("TEST2019042200001");
		bizContent.setAsynFlag("1");
		bizContent.setReservDirect("1");
		bizContent.setPayChannel("1");
		bizContent.setAgreeCode("123");
		bizContent.setReturnUrl("1");
		bizContent.setCallbackUrl("1");
		bizContent.setPayerAccname("1");
		bizContent.setPayMemno("1");
		bizContent.setPayerAccno("0200062009213057712");
		bizContent.setPayerFeeAccno("1");
		bizContent.setPayerFeeAccName("1");
		bizContent.setPayerFeeCurr("1");
		bizContent.setOrderCode("1");
		bizContent.setOrderAmount("1");
		bizContent.setOrderCurr("1");
		bizContent.setSumPayamt("1");
		bizContent.setSubmitTime("20190422150000");
		bizContent.setRceiptRemark("1");
		bizContent.setInternationalFlag("1");
		bizContent.setPayeeList(beanRecvMallInfoList);
		bizContent.setGoodsList(beanGoodsInfoList);
		bizContent.setAgreementId("1");
		bizContent.setInvoiceId("1");
		bizContent.setManifestId("1");
		bizContent.setAgreementImageId("1");
		bizContent.setDicbusscode("");
		bizContent.setIsOnlyShowTPlus0Bank("1");
		bizContent.setIsHideScanPaymentFunction("1");
		bizContent.setReInterestFlag(0);
		bizContent.setReInterestType(0);


		request.setBizContent(bizContent);

		Random rand = new Random();
		String msgId = rand.nextInt(99999) + "msg";
		System.out.println(msgId);

		request.setBizContent(bizContent);

		MybankPayCpayCppayapplyResponseV1 response;
		try {
			response = client.execute(request,msgId);
			System.out.println(JSONObject.toJSONString(response));
			if (response.isSuccess()) {
				// 业务成功处理
				System.out.println(response.getReturnCode());
			} else {
				//失败
				System.out.println(response.getReturnCode());
				System.out.println(response.getReturnMsg());
			}
		} catch (IcbcApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
