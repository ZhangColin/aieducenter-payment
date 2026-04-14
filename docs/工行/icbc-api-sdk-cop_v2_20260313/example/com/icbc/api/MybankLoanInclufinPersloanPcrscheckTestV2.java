package com.icbc.api;

import com.icbc.api.request.MybankLoanInclufinPersloanPcrscheckRequestV2;
import com.icbc.api.request.MybankLoanInclufinPersloanPcrscheckRequestV2.MybankLoanInclufinPersloanPcrscheckRequestV2Biz;
import com.icbc.api.response.MybankLoanInclufinPersloanPcrscheckResponseV2;

public class MybankLoanInclufinPersloanPcrscheckTestV2{
	//APP_ID, PRI_KEY, APIGW_PUBLIC_KEY  根据实际的应用场景进行修�?
	protected static final String APP_ID = "xxxxxxx";
	protected static final String APIGW_PUBLIC_KEY = "xxxxxxx";
	protected static final String PRI_KEY = "xxxxxxx";
	protected static final String URL = "http://xxxxxxx/api/mybank/loan/inclufin/persloan/pcrscheck/V2";
	
	public static void main(String[] args){
		MybankLoanInclufinPersloanPcrscheckRequestV2 request = new MybankLoanInclufinPersloanPcrscheckRequestV2();
		MybankLoanInclufinPersloanPcrscheckResponseV2 response=new MybankLoanInclufinPersloanPcrscheckResponseV2();
		MybankLoanInclufinPersloanPcrscheckRequestV2Biz bizContent = new MybankLoanInclufinPersloanPcrscheckRequestV2Biz();
		//公共参数
		bizContent.setSerialNo("123456789012345678901231237"); //请求序列号
		bizContent.setAppNo("F-APIP"); //调用应用名称
		bizContent.setAreaCode(""); //交易机构代码
		bizContent.setEmployeeCode(""); //柜员号
		bizContent.setLanguage("zh_CN"); //语言代码
		bizContent.setTransNo("ILoanSendMessageCocoaService"); //接口编号
		bizContent.setVer("1.0"); //版本号
		
		//私有参数
		bizContent.setName("张三");//姓名
		bizContent.setCredType("0");//证件类型
		bizContent.setCredNum("110220197001011234");//证件号码
		bizContent.setSceneType("ZH0300");//场景类别
		bizContent.setProdKindCode("030100004661");
		bizContent.setMobile("18522089346");
		bizContent.setVerifyCode("111");
		bizContent.setTerminalType("1");//终端类型
		bizContent.setTerminalIp("ip:port");//终端IP
		bizContent.setTerminalMac("868663034022919-25B8D05BE6C8FCCD");//终端MAC
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
