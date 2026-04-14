package com.icbc.api;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.request.JftApiBillDiscountOrderReceiveRequestV1;
import com.icbc.api.response.JftApiBillDiscountOrderReceiveResponseV1;

import java.util.ArrayList;
import java.util.List;

public class JftApiBillDiscountOrderReceiveTestV1 {
	protected static final String MY_PRIVATE_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";
	protected static final String APIGW_PUBLIC_KEY = "[需替换]工行API网关公钥，请向工行联系获取";
	protected static final String APP_ID = "10000000000000005452";
	String host = "[需替换]工行API网关地址";
	String apiUrl= host+"/api/jft/api/billdis/order/receive/V1";
	String encryptKey = "[需替换]应用方加密串，与工行API开放平台一致";

	public void test_cop() throws Exception {

		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,"RSA2", MY_PRIVATE_KEY,"UTF-8", "json",APIGW_PUBLIC_KEY,"AES",encryptKey, "", "");

		JftApiBillDiscountOrderReceiveRequestV1 request = new JftApiBillDiscountOrderReceiveRequestV1();
		request.setServiceUrl(apiUrl);

		//请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
		JftApiBillDiscountOrderReceiveRequestV1.JftApiBillDiscountOrderReceiveRequestV1Biz bizContent = new JftApiBillDiscountOrderReceiveRequestV1.JftApiBillDiscountOrderReceiveRequestV1Biz();
		bizContent.setAppId(APP_ID);
		bizContent.setOutVendorId("999900001");
		bizContent.setNotifyUrl("https://test.com");
		bizContent.setOrderType("1");
		bizContent.setOrderNo("516510515622200008987654");
		bizContent.setCreditCode("51651051542542000089");
		bizContent.setImageNo("5616131211315661A08");
		bizContent.setCustomerName("测试");
		bizContent.setContactName("测试人");
		bizContent.setContactPhone("13584962475");
		bizContent.setOrderDate("2022-08-30 00:00:00");
		bizContent.setDscntType("BC08");
		bizContent.setInterestType("01");
		bizContent.setDscntBkName("中国工商银行");
		bizContent.setDscntBkAcctSvcr("102100010022");
		bizContent.setIsPartAbandon("1");

		List<JftApiBillDiscountOrderReceiveRequestV1.JftApiBillDiscountOrderReceiveRequestV1Biz.BillBo> billBoList = new ArrayList<>();
		JftApiBillDiscountOrderReceiveRequestV1.JftApiBillDiscountOrderReceiveRequestV1Biz.BillBo bizBillBo = new JftApiBillDiscountOrderReceiveRequestV1.JftApiBillDiscountOrderReceiveRequestV1Biz.BillBo();
		bizBillBo.setPackNo("110000296020211111000003998843");
		bizBillBo.setRangeBgn("00000000001");
		bizBillBo.setRangeEnd("001000141359");
		bizBillBo.setAmount("100000");
		bizBillBo.setIssDate("22020901");
		bizBillBo.setDueDate("22020901");
		bizBillBo.setDscntDate("22020901");
		bizBillBo.setOrderRate("1.2");
		bizBillBo.setAccptrName("qwe");
		bizBillBo.setAccptrAcctsvcr("123");
		bizBillBo.setCdTp("AC01");
		bizBillBo.setCdMdm("ME01");
		billBoList.add(bizBillBo);

		List<JftApiBillDiscountOrderReceiveRequestV1.JftApiBillDiscountOrderReceiveRequestV1Biz.InvcBo> invcBoList = new ArrayList<>();
		JftApiBillDiscountOrderReceiveRequestV1.JftApiBillDiscountOrderReceiveRequestV1Biz.InvcBo bizInvcBo = new JftApiBillDiscountOrderReceiveRequestV1.JftApiBillDiscountOrderReceiveRequestV1Biz.InvcBo();
		bizInvcBo.setPackNo("110000296020211111000003998843");
		bizInvcBo.setRangeBgn("00000000001");
		bizInvcBo.setRangeEnd("001000141359");
		bizInvcBo.setInvcType("004");
		bizInvcBo.setInvcNo("202209070003");
		bizInvcBo.setInvcCode("202209070003");
		bizInvcBo.setInvcAmt("1000.00");
		bizInvcBo.setInvcDate("20220303");
		bizInvcBo.setInvcChkNo("004");
		bizInvcBo.setInvcImageNo("123");
		invcBoList.add(bizInvcBo);

		List<JftApiBillDiscountOrderReceiveRequestV1.JftApiBillDiscountOrderReceiveRequestV1Biz.ContractBo> contractBoList = new ArrayList<>();
		JftApiBillDiscountOrderReceiveRequestV1.JftApiBillDiscountOrderReceiveRequestV1Biz.ContractBo bizContractBo = new JftApiBillDiscountOrderReceiveRequestV1.JftApiBillDiscountOrderReceiveRequestV1Biz.ContractBo();
		bizContractBo.setPackNo("110000296020211111000003998843");
		bizContractBo.setRangeBgn("00000000001");
		bizContractBo.setRangeEnd("001000141359");
		bizContractBo.setContractNo("202209070003");
		bizContractBo.setContractAmt("1000.00");
		bizContractBo.setContractDate("20220303");
		bizContractBo.setDataImageNo("1234567890");
		contractBoList.add(bizContractBo);

		bizContent.setPackList(billBoList);
		bizContent.setTicketList(invcBoList);
		bizContent.setContractList(contractBoList);
		request.setBizContent(bizContent);

		JftApiBillDiscountOrderReceiveResponseV1 response;
		try {
			response = (JftApiBillDiscountOrderReceiveResponseV1) client.execute(request, System.currentTimeMillis()+"");
			if (response.isSuccess()) {
				//业务成功处理，请根据接口文档用response.getxxx()获取同步返回的业务数据
				System.out.println("returnCode:"+response.getReturnCode());
				System.out.println("response:" + response);
			} else {
				//失败
				System.out.println("returnCode:"+response.getReturnCode());
				System.out.println("returnMsg:"+response.getReturnMsg());
			}
		} catch (IcbcApiException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		new JftApiBillDiscountOrderReceiveTestV1().test_cop();
	}
}
