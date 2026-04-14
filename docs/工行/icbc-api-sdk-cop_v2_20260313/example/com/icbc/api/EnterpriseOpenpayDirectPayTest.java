package com.icbc.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.request.EnterpriseOpenpayDirectPayRequestV1;
import com.icbc.api.request.EnterpriseOpenpayDirectPayRequestV1.EnterpriseOpenpayDirectPayGoodInfo;
import com.icbc.api.request.EnterpriseOpenpayDirectPayRequestV1.EnterpriseOpenpayDirectPayV1Biz;
import com.icbc.api.response.EnterpriseOpenpayDirectPayResponseV1;

public class EnterpriseOpenpayDirectPayTest {

	/**
	 * @param args
	 */

	protected static final String MY_PRIVATE_KEY = "XXXXXXX";

	protected static final String APIGW_PUBLIC_KEY = "XXXXX";

	protected static final String APP_ID = "123";

	protected static final String password = "12345678";

	protected static final String MY_PUB_KEY = "XXXXX";
	protected static final String encryptType = "xxxx";
	protected static final String encryptKey = "xxxx";
	public static void main(String[] args) {
		// TODO �Զ����ɵķ������

		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,
				MY_PRIVATE_KEY, APIGW_PUBLIC_KEY, MY_PUB_KEY, password);

		// �����������request
		EnterpriseOpenpayDirectPayRequestV1 request = new EnterpriseOpenpayDirectPayRequestV1();

		// ��������·��
		request.setServiceUrl("https://ip:port/api/enterprise/openpay/directpay/V1");

		EnterpriseOpenpayDirectPayV1Biz bizContent = new EnterpriseOpenpayDirectPayV1Biz();

		bizContent.setPay_name("��Ң��������һ�");
		bizContent.setPay_rem("payremark");
		bizContent.setGet_phone("13426351013");
		bizContent.setTrade_time("20211010162925");
		bizContent.setGet_address("get_address");
		bizContent.setFile_code("0200EH0011036");
		bizContent.setPay_acct_num("0200062009213084848");
		bizContent.setOrder_no("orderzydl0050");
		bizContent.setGet_county("Qianshan");
		bizContent.setPay_phoneno("010-82706722");
		bizContent.setLanguage("ZH_CN");
		bizContent.setFile_serialno("fsZHJlyd0049");
		bizContent.setTradeplat_code("2018070000000000035");
		bizContent.setRec_name("����������â��");
		bizContent.setOrderAmt("3000");
		bizContent.setPay_mode("0");
		bizContent.setRec_orgcode("asdfsa");
		bizContent.setAmout("250");
		bizContent.setAcct_orgcode("12345");
		bizContent.setRec_bnkclscode("");
		bizContent.setChannel_flag("0");
		bizContent.setRec_acct_num("3602009009000401956");
		bizContent.setGet_post("100101");
		bizContent.setOrder_rem("������עtest");
		bizContent.setCurrtype("001");
		bizContent.setFile_name("��������̶��״�����εϲΰ��ą̂");
		bizContent.setSign_flag("1");
		bizContent.setTradeplat_rem("tradeplat_rem");
		bizContent.setGet_email("www@139.com");
		bizContent.setGet_city("Anhui");
		bizContent.setTrade_orgcode("91350922MA34A6CP84");
		bizContent.setTradeplat_name("��Ң���Ż�����");
		bizContent.setGet_province("Anhui");
		bizContent.setSystem_flag("1");
		bizContent.setFile_id("0200EH0011036.e.0200");
		List<EnterpriseOpenpayDirectPayGoodInfo> goodlist = new ArrayList<EnterpriseOpenpayDirectPayGoodInfo>();
		
		EnterpriseOpenpayDirectPayGoodInfo goodinfo1 = new EnterpriseOpenpayDirectPayGoodInfo();
		goodinfo1.setGoods_name("ƻ��");
		goodinfo1.setGoods_num("11");
		goodinfo1.setGoods_seqno("SP001");
		goodinfo1.setPrice("850");
		goodinfo1.setSeller_phoneno("13811111111");
		goodinfo1.setSeller_name("appler_seller");
		goodinfo1.setUnits("KG");
		goodinfo1.setWeight("3");
		EnterpriseOpenpayDirectPayGoodInfo goodinfo2 = new EnterpriseOpenpayDirectPayGoodInfo();
		goodinfo2.setGoods_name("�㽶");
		goodinfo2.setGoods_num("11");
		goodinfo2.setGoods_seqno("SP002");
		goodinfo2.setPrice("850");
		goodinfo2.setSeller_phoneno("13811111111");
		goodinfo2.setSeller_name("appler_seller");
		goodinfo2.setUnits("KG");
		goodinfo2.setWeight("3");
		
		goodlist.add(goodinfo1);
		goodlist.add(goodinfo2);
		bizContent.setGoodlist(goodlist);
		
		request.setBizContent(bizContent);
		IcbcResponse response;

		Random rand = new Random();
		String msgId = rand.nextInt(99999) + "msg";
		System.out.println(msgId);

		try {
			response = client.execute(request, msgId);
			if (response.getReturnCode() == 0) {
				// ҵ��ɹ�����
				System.out.println("success");//
			} else {
				// ʧ��
				System.out.println("error");
			}
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}
	}
}
