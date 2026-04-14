package com.icbc.api;

import com.icbc.api.request.MybankLoanInclufinPersloanQuickLoanverifycodesendRequestV1;
import com.icbc.api.request.MybankLoanInclufinPersloanQuickLoanverifycodesendRequestV1.MybankLoanInclufinPersloanQuickLoanverifycodesendRequestV1Biz;
import com.icbc.api.response.MybankLoanInclufinPersloanQuickLoanverifycodesendResponseV1;

public class MybankLoanInclufinPersloanQuickLoanverifycodesendTestV1{
	//APP_ID, PRI_KEY, APIGW_PUBLIC_KEY  根据实际的应用场景进行修改
	protected static final String APP_ID = "xxxxxxx";
	protected static final String APIGW_PUBLIC_KEY = "xxxxxxx";
	protected static final String PRI_KEY = "xxxxxxx";
	protected static final String URL = "http://xxxxxxx/api/mybank/loan/inclufin/persloan/quickloanverifycodesend/V1";
	
	public static void main(String[] args){
		MybankLoanInclufinPersloanQuickLoanverifycodesendRequestV1 request = new MybankLoanInclufinPersloanQuickLoanverifycodesendRequestV1();
		MybankLoanInclufinPersloanQuickLoanverifycodesendResponseV1 response=new MybankLoanInclufinPersloanQuickLoanverifycodesendResponseV1();
		MybankLoanInclufinPersloanQuickLoanverifycodesendRequestV1Biz bizContent = new MybankLoanInclufinPersloanQuickLoanverifycodesendRequestV1Biz();
		//公共参数
		bizContent.setSerialNo("123456789012345678901234567"); //请求序列号
		bizContent.setAppNo("F-APIP"); //调用应用名称
		bizContent.setAreaCode(""); //交易机构代码
		bizContent.setEmployeeCode(""); //柜员号
		bizContent.setLanguage("zh_CN"); //语言代码
		bizContent.setTransNo("ILoanSendMessageCocoaService"); //接口编号
		bizContent.setVer("1.0"); //版本号
		
		//私有参数
		bizContent.setTransDate("20200423"); //交易日期
		bizContent.setPhoneNo("11111111111"); //手机号码	
		bizContent.setType("01"); //申请类型
		bizContent.setOperFlag("02"); //动作标识
		bizContent.setBusiCode("010"); //业务场景
		bizContent.setCustID(""); //合作方客户ID
		bizContent.setModelID("ZH1107"); //模型标识
		bizContent.setCooperUnit("SJ12345"); //合作方请求编号
		bizContent.setIdentityStr(""); //身份识别串
		bizContent.setCustName("张四"); //客户姓名
		bizContent.setCertType("0"); //证件类型
		bizContent.setCertCode("123456199909091234"); //证件号码
		bizContent.setRemark1(""); //备用1
		bizContent.setRemark2(""); //备用2
		
		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", PRI_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, null, null, null, null);

		request.setServiceUrl(URL);
		request.setBizContent(bizContent);
		
		try {
			response = client.execute(request);
		} catch (IcbcApiException e) {
			// System.out.println(e.getMessage());
		}
		if (response.isSuccess()) {
			// System.out.println("success!");
		} else {
			// System.out.println("fail" + response.getReturnMsg());
		}
		// System.out.println(response);
	}
}
