package com.icbc.api;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Test;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.request.EnterpriseOpenpayControlRequestV1;
import com.icbc.api.request.EnterpriseOpenpayControlRequestV1.EnterpriseOpenpayControlV1Biz;
import com.icbc.api.request.EnterpriseOpenpayControlRequestV1.EnterpriseOpenpayControlV1Biz.EnterPriseOpenPayPaymentGoodInfo;
import com.icbc.api.request.EnterpriseOpenpayControlRequestV1.EnterpriseOpenpayControlV1Biz.EnterPriseOpenPayPaymentRecInfo;
import com.icbc.api.response.EnterpriseOpenpayControlResponseV1;

public class EnterpriseOpenpayControlTest {

	protected static final String MY_PRIVATE_KEY = "";

	protected static final String APIGW_PUBLIC_KEY = "";

	protected static final String APP_ID = "";
	
	protected static final String password = "";
	
	protected static final  String MY_PUB_KEY = "";
	
	

	public void test_cop() { 

	// ����client����
	DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY,MY_PUB_KEY,password);

	
	// �����������request
	EnterpriseOpenpayControlRequestV1 request = new EnterpriseOpenpayControlRequestV1();
	
	// ��������·��
	request.setServiceUrl("https://ip:port/api/enterprise/openpay/control/V1");
	

	EnterpriseOpenpayControlV1Biz bizContent = new EnterpriseOpenpayControlV1Biz();

	bizContent.setTransCode("DDZFZJKZ"); 
	bizContent.setCis("100015525000000"); 
	bizContent.setBankCode("102"); 
	bizContent.setId("eb00.y.0200"); 
	bizContent.setTranDate("20180315"); 
	bizContent.setTranTime("103022333"); 
	bizContent.setfSeqno("pckg12345"); 
	bizContent.setSignTime("20180315103022334"); 
	bizContent.setFileCode("0200EH0005213"); 
	bizContent.setFileSerialno("DDZFZJKZ031611"); 
	bizContent.setTradePlatCode("0200TT0001001"); 
	bizContent.setTradePlatName("Ͷ�����޹�˾"); 
	bizContent.setOrderNo("Order001"); 
	bizContent.setBusType("2"); 
	bizContent.setAmout("100"); 
	bizContent.setCurrType("001"); 
	bizContent.setPay_acctNum("0200099809216989665"); 
	bizContent.setPayName("�ʽ�����˺�"); 
	bizContent.setTradeOrgcode("012345678912345678"); 
	bizContent.setAcctOrgcode("012345678912345678"); 
	bizContent.setPayPhoneno("010-8270XXXX"); 
	bizContent.setGetProvince("������"); 
	bizContent.setGetCounty("������"); 
	bizContent.setGetCity("������"); 
	bizContent.setGetEmail("xxx@139.com"); 
	bizContent.setGetPhone("132xxxxxxxx"); 
	bizContent.setGetAddress("�����к������йش����԰XX��XXX¥"); 
	bizContent.setGetPost("100101"); 
	bizContent.setTradePlatRem("����ƽ̨��ע"); 
	bizContent.setPayRem("���ע"); 
	bizContent.setOrderRem("������ע");   
	List<EnterPriseOpenPayPaymentRecInfo> reclist = (List)new ArrayList<EnterPriseOpenPayPaymentRecInfo>();
	List<EnterPriseOpenPayPaymentGoodInfo> goodlist = (List)new ArrayList<EnterPriseOpenPayPaymentGoodInfo>();
	
	EnterPriseOpenPayPaymentRecInfo recinfo1 = new EnterPriseOpenPayPaymentRecInfo();
	EnterPriseOpenPayPaymentRecInfo recinfo2 = new EnterPriseOpenPayPaymentRecInfo();
	EnterPriseOpenPayPaymentRecInfo recinfo3 = new EnterPriseOpenPayPaymentRecInfo();
	//�տ���1
	recinfo1.setRecSeqNo("1");
	recinfo1.setRecName("����");
	recinfo1.setRecAcctNum("6222020200066551234");
	recinfo1.setRecBnkclsCode("12");
	recinfo1.setRecOrgCode("1234567890");
	recinfo1.setRecSysioFlag("1");
	//�տ���2
	recinfo2.setRecSeqNo("2");
	recinfo2.setRecName("����");
	recinfo2.setRecAcctNum("6222020200066551234");
	recinfo2.setRecBnkclsCode("12");
	recinfo2.setRecOrgCode("1234567890");
	recinfo2.setRecSysioFlag("1");
	//�տ���3
	recinfo3.setRecSeqNo("3");
	recinfo3.setRecName("����");
	recinfo3.setRecAcctNum("6222020200066551234");
	recinfo3.setRecBnkclsCode("12");
	recinfo3.setRecOrgCode("1234567890");
	recinfo3.setRecSysioFlag("1");
	reclist.add(recinfo1);
	reclist.add(recinfo2);
	reclist.add(recinfo3);
	
	EnterPriseOpenPayPaymentGoodInfo goodinfo1 = new EnterPriseOpenPayPaymentGoodInfo();
	EnterPriseOpenPayPaymentGoodInfo goodinfo2 = new EnterPriseOpenPayPaymentGoodInfo();
	EnterPriseOpenPayPaymentGoodInfo goodinfo3 = new EnterPriseOpenPayPaymentGoodInfo();
	
	//��Ʒ��Ϣ1
	goodinfo1.setGoodsSeqno("1");
	goodinfo1.setGoodsName("��Ʒ1");
	goodinfo1.setGoodsNum("100");
	goodinfo1.setWeight("��");
	goodinfo1.setPrice("1000000");
	goodinfo1.setSeller_name("����");
	goodinfo1.setSellerAddress("13812345678");
	goodinfo1.setUnits("100");
	//��Ʒ��Ϣ2
	goodinfo2.setGoodsSeqno("2");
	goodinfo2.setGoodsName("��Ʒ2");
	goodinfo2.setGoodsNum("100");
	goodinfo2.setWeight("��");
	goodinfo2.setPrice("1000000");
	goodinfo2.setSeller_name("����");
	goodinfo2.setSellerAddress("13812345678");
	goodinfo2.setUnits("100");
	//��Ʒ��Ϣ3
	goodinfo3.setGoodsSeqno("3");
	goodinfo3.setGoodsName("��Ʒ3");
	goodinfo3.setGoodsNum("100");
	goodinfo3.setWeight("��");
	goodinfo3.setPrice("1000000");
	goodinfo3.setSeller_name("����");
	goodinfo3.setSellerAddress("13812345678");
	goodinfo3.setUnits("100");
	goodlist.add(goodinfo1);
	goodlist.add(goodinfo2);
	goodlist.add(goodinfo3);
	
	bizContent.setReclist(reclist);
	bizContent.setGoodlist(goodlist);

	request.setBizContent(bizContent);
	
	Random rand = new Random();
	String msgId = rand.nextInt(99999)+"msg";
	
	EnterpriseOpenpayControlResponseV1 response; 
	 try { 
	  response = (EnterpriseOpenpayControlResponseV1) client.execute(request, msgId); 
	  if (response.isSuccess() ) { 
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
