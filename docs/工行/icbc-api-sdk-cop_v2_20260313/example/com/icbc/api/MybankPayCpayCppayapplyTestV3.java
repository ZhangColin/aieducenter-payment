package com.icbc.api.test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcConstants;
import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.MybankPayCpayCppayapplyRequestV3;
import com.icbc.api.response.MybankPayCpayCppayapplyResponseV3;

public class MybankPayCpayCppayapplyTestV3 {
	
	protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----CdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALAWAcPiTMRU906PTdy0ozspX7XptZnkEw2C8R64RDB9BiRFXAj0cU4aTA1MyfmGIlceeVdgJf7OnmvpHnYxjQ7sGxMItPtodrGwA2y8j0AEbHc5pNWU8Hn0zoY9smHS5e+KjSbWv+VNbdnrRFTpDeiJ3+s2E/cKI2CDRbo7cAarAgMBAAECgYABiA933q4APyTvf/uTYdbRmuiEMoYr0nn/8hWayMt/CHdXNWs5gLbDkSL8MqDHFM2TqGYxxlpOPwnNsndbW874QIEKmtH/SSHuVUJSPyDW4B6MazA+/e6Hy0TZg2VAYwkB1IwGJox+OyfWzmbqpQGgs3FvuH9q25cDxkWntWbDcQJBAP2RDXlqx7UKsLfM17uu+ol9UvpdGoNEed+5cpScjFcsB0XzdVdCpp7JLlxR+UZNwr9Wf1V6FbD2kDflqZRBuV8CQQCxxpq7CJUaLHfm2kjmVtaQwDDw1ZKRb/Dm+5MZ67bQbvbXFHCRKkGI4qqNRlKwGhqIAUN8Ynp+9WhrEe0lnxo1AkEA0flSDR9tbPADUtDgPN0zPrN3CTgcAmOsAKXSylmwpWciRrzKiI366DZ0m6KOJ7ew8z0viJrmZ3pmBsO537llRQJAZLrRxZRRV6lGrwmUMN+XaCFeGbgJ+lphN5/oc9F5npShTLEKL1awF23HkZD9HUdNLS76HCp4miNXbQOVSbHi2QJAUw7KSaWENXbCl5c7M43ESo9paHHXHT+/5bmzebq2eoBofn+IFsyJB8Lz5L7WciDK7WvrGC2JEbqwpFhWwCOl/w==";
	protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
	protected static final String APP_ID = "0200EH0013035";
	
	static Logger log = Logger.getLogger(MybankPayCpayCppayapplyTestV3.class.getName());
	public static void main(String[] args) {
		
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
		
		MybankPayCpayCppayapplyRequestV3 request = new MybankPayCpayCppayapplyRequestV3();
		request.setServiceUrl("http://IP:PORT/api/mybank/pay/cpay/cppayapply/V3");
		
//		long timeMillis = System.currentTimeMillis();
//		String time=new SimpleDateFormat("yyyyMMddHHmmss").format(timeMillis) ;// ��ǰ����
		MybankPayCpayCppayapplyRequestV3.MybankPayCpayCppayapplyRequestV3Biz bizContent = new MybankPayCpayCppayapplyRequestV3.MybankPayCpayCppayapplyRequestV3Biz();
		
		
		
		List<MybankPayCpayCppayapplyRequestV3.BeanGoodsInfo> beanGoodsInfoList = new ArrayList<MybankPayCpayCppayapplyRequestV3.BeanGoodsInfo>();
		MybankPayCpayCppayapplyRequestV3.BeanGoodsInfo beanGoodsInfo = new MybankPayCpayCppayapplyRequestV3.BeanGoodsInfo();
		beanGoodsInfo.setGoodsSubId("1");
		beanGoodsInfo.setGoodsName("���콣");
		beanGoodsInfo.setPayeeCompanyName("����");
		beanGoodsInfo.setGoodsNumber("1");
		beanGoodsInfo.setGoodsAmt("10000");
		beanGoodsInfo.setGoodsUnit("��λ");
		beanGoodsInfoList.add(beanGoodsInfo);
		
		List<MybankPayCpayCppayapplyRequestV3.BeanRecvMallInfo> beanRecvMallInfoList = new ArrayList<MybankPayCpayCppayapplyRequestV3.BeanRecvMallInfo>();
		MybankPayCpayCppayapplyRequestV3.BeanRecvMallInfo beanRecvMallInfo = new MybankPayCpayCppayapplyRequestV3.BeanRecvMallInfo();
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
		beanRecvMallInfo.setPayeeBrno("1");
		beanRecvMallInfo.setPayeeBrnoZoneno("1111");
		beanRecvMallInfo.setReceivableAmount("");
		beanRecvMallInfoList.add(beanRecvMallInfo);

		List<MybankPayCpayCppayapplyRequestV3.BeanFeeInfo> beanfeeInfoList = new ArrayList<MybankPayCpayCppayapplyRequestV3.BeanFeeInfo>();
		MybankPayCpayCppayapplyRequestV3.BeanFeeInfo beanFeeInfo = new MybankPayCpayCppayapplyRequestV3.BeanFeeInfo();
		beanFeeInfo.setFeeFlag("1");
		beanFeeInfo.setFeeBden("1");
		beanFeeInfo.setPayAccno("1222");
		beanFeeInfo.setPayCurr("1");
		beanFeeInfo.setPlFlag("1");
		beanFeeInfo.setAmtFlag("1");
		beanFeeInfo.setFeeAmt("111");
		beanFeeInfo.setFeeAccno("1222");
		beanFeeInfo.setHkFlag("1");
		beanFeeInfo.setProdCode("11");
		beanFeeInfo.setFeeGdtlFlag("1");
		beanFeeInfo.setFeeGdtlNote("1");
		beanFeeInfo.setFeeGroupId("1");
		beanFeeInfo.setFeeOnFlag("1");
		beanFeeInfo.setFeeMode("1");
		beanFeeInfo.setFeeDate("");
		beanFeeInfo.setFeeCycle("1");
		beanFeeInfo.setIsEbankOrBase("1");
		beanFeeInfo.setIsBatchFile("1");
		beanFeeInfo.setSummary("1000");
		beanFeeInfo.setNotes("");
		beanFeeInfo.setUseData("");
//		beanFeeInfo.setFeeMode("1");
		beanFeeInfo.setCatrFlag("1");
		beanFeeInfo.setAccno("1222");
		beanFeeInfo.setBusCurr("1");
		beanFeeInfo.setAmount("1111");
		beanFeeInfo.setCndCd("1");
		beanFeeInfo.setCalCurr("1");
		beanFeeInfo.setCinoFlag("1");
		beanFeeInfo.setCino("");
		beanFeeInfo.setAccName("2222");
		beanFeeInfo.setoCityFlag("1");
		beanFeeInfo.setMultiply("0");
		beanFeeInfo.setCcList("1");
		beanFeeInfo.setCcRang("1");
		beanFeeInfo.setCaFavFlag("1");
		beanFeeInfo.setIsFeeDevFlag("1");
		beanFeeInfo.setFeeDevRang("1");
		beanFeeInfo.setOtActNo("1222");
		beanFeeInfo.setOtAccName("333");
		beanFeeInfo.setTaxManType("1");
		beanFeeInfo.setTaxManValue("1");
		beanFeeInfo.setGdtlFlag("1");
		beanFeeInfo.setFeeGroupId("");
		beanFeeInfo.setVouhType("1");
		beanFeeInfo.setVouhNo("");
		beanFeeInfo.setVouhPwd("");
		beanFeeInfo.setVouhDate("");
		beanFeeInfo.setVouhQuota("");
		beanFeeInfo.setCardNo("");
		beanFeeInfo.setHsTrxcode("");
		beanFeeInfo.setSerinum("");
		beanFeeInfo.setCrCurr("");
		beanFeeInfo.setFeeSeqno("");
		beanFeeInfo.setOnlyId("");
		beanFeeInfo.setYdFlag("1");
		beanFeeInfo.setPersonNotes("");
		beanfeeInfoList.add(beanFeeInfo);

		MybankPayCpayCppayapplyRequestV3.BeanPubInfo cpayPubInput = new MybankPayCpayCppayapplyRequestV3.BeanPubInfo();
		cpayPubInput.setChannelType("1");
		cpayPubInput.setIpAddress("111");
		cpayPubInput.setMacAddress("11");
		cpayPubInput.setChannelDate("");
		cpayPubInput.setChannelTime("");
		cpayPubInput.setStartApp("F-CPAY");
		cpayPubInput.setServface("1");
		cpayPubInput.setZoneNo("200");
		cpayPubInput.setBrNo("62");
		cpayPubInput.setOperTeller("");
		cpayPubInput.setTrxCode("3507");
		cpayPubInput.setTrxTimestamp("");
		cpayPubInput.setGroupID("1");
		cpayPubInput.setLogonID("1");
		cpayPubInput.setEventNo("1111111222222234");

		bizContent.setAgreeCode("002000099814");
		bizContent.setPartnerSeq("MQOY2522");
		bizContent.setPayChannel("1");
		bizContent.setInternationalFlag("2");
		bizContent.setPayMode("2");
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
		bizContent.setOrderRemark("������ע-ֱ��֧��-����");
		bizContent.setRceiptRemark("�ص�������Ϣ��ע");
		bizContent.setPurpose("");
		bizContent.setSummary("");
		bizContent.setSubmitTime("20240615162211");
		bizContent.setReturnUrl("");
		bizContent.setCallbackUrl("www.baidu.com");
		bizContent.setAgreementId("34567891");
		bizContent.setInvoiceId("");
		bizContent.setManifestId("");
		bizContent.setAgreementImageId("");
		bizContent.setApplBrnoZoneno("");
		bizContent.setEnterpriseName("");
		bizContent.setPayRemark("");
		bizContent.setBakReserve1("");
		bizContent.setBakReserve2("");
		bizContent.setBakReserve3("");
		bizContent.setPartnerSeqOrigin("");
		bizContent.setSumPayamtOrigin("");
		bizContent.setReporterName("");
		bizContent.setReporterContact("");
		bizContent.setApplBrno("");
		bizContent.setIdentityMode("0");	
		bizContent.setPayerPhone("0");	
		
		bizContent.setPayeeList(beanRecvMallInfoList);
		bizContent.setGoodsList(beanGoodsInfoList);
		bizContent.setFeeList(beanfeeInfoList);
		bizContent.setCpayPubInput(cpayPubInput);
		request.setBizContent(bizContent);
		
		Random rand = new Random();
		String msgId = rand.nextInt(99999) + "msg";
		log.fine("msgId:"+msgId);
		
		request.setBizContent(bizContent);
		
		MybankPayCpayCppayapplyResponseV3 response;
		try {
			response = client.execute(request,msgId);
			log.fine("JSONObject.toJSONString(response):"+JSONObject.toJSONString(response));
			if (response.isSuccess()) {
				// ҵ��ɹ�����
				log.fine("response.getReturnCode():"+response.getReturnCode());
			} else {
				//ʧ��
				log.fine("response.getReturnCode():"+response.getReturnCode());
				log.fine("response.getReturnMsg():"+response.getReturnMsg());
			}
		} catch (IcbcApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
