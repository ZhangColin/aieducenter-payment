package com.icbc.api;

import com.icbc.api.request.MybankLoanInclusivefinanceBusinessloanapplyRequestV1;
import com.icbc.api.request.MybankLoanInclusivefinanceBusinessloanapplyRequestV1.MybankLoanInclusivefinanceBusinessloanapplyV1Biz;
import com.icbc.api.response.MybankLoanInclusivefinanceBusinessloanapplyResponseV1;

import java.util.ArrayList;
import java.util.List;
public class MybankLoanInclusivefinanceBusinessloanapplyTestV1 {
	// appid,privateKey,apigwPublicKey 根据实际的应用场景进行修改
	protected static final String appId = "123";
	protected static final String privateKey = "-----请使用正确的APP私钥-----CdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJ42tqQomxbLvuZTVKO4bBLwGp8SzRMiJ7jX9JNlS4sqmZzs9Z4lw/SSxf4dF9JJUErpNReUjzLp0As2FiuV8BALh6GYmiHGTWx7+v3HuWH9LiZNI00Ia+1HfpkJ8Sao8Ny5h1Pu49LayVleeLy496+phRE81dqlqaSHH8cKWnK1AgMBAAECgYBxod5f3QI2xzNe/e7GgAivOWAFbF16JofdDM4Opyww0fHucYqfgYRSPrCRqJeZYyqWAxUs0HhhGulfhAM8Xr5BxY5//x4HDqE2rOF7mrc/xkWNL+S9X5U0T8cdA3fnF/rYAUnJjKmeliuD2gcf5xYJENcnb8p/GQbiwIjoHDqe6QJBANzcevl9ke4cx1fa3ZcxVS3A1eHVtp573M/lo+8pRJlrf96ghFB54CzumsC0F7YfojDgCAN2egTqCGqNOuPJWWcCQQC3YqT5xlMlGk6Agav5Y6E5blx/AKJB8DeZSbjXLvytpXOASzoNR/A40uQXanXVqNU68l96FSq415AmTHi9OdWDAkA0el7t8Rw/i68B/Qsx5ZLrsCoh4vnlZmDtNQ9iwFeAbL6RU2qdBJhzlK5Io4IO0C1ll5XP3NLZYBJn3u7jOPB5AkEAiM/qHoHsM8j9effD0kmW1V7VWNajNqg9AnoykS73yaCem78DrzbVK7+B9UoyYNUVR2Xc/xpdhgsj+r6gcSN9ewJANSmxYF34601mteAnZwhyGKCJomiuSMNTr52i2uTspdZi0zqaerI1EGD3dAENxRebyPmPFxhwCcCbzF2gNUr1fw==";
	protected static final String apigwPublicKey = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

	public static void main(String[] args) throws IcbcApiException {
		DefaultIcbcClient client = new DefaultIcbcClient(appId, privateKey, apigwPublicKey);
		MybankLoanInclusivefinanceBusinessloanapplyRequestV1 request = new MybankLoanInclusivefinanceBusinessloanapplyRequestV1();

		request.setServiceUrl("https://ip:port/api/mybank/loan/inclusivefinance/businessloanapply/V1");
		MybankLoanInclusivefinanceBusinessloanapplyV1Biz bizContent = new MybankLoanInclusivefinanceBusinessloanapplyV1Biz();
		MybankLoanInclusivefinanceBusinessloanapplyRequestV1.RequestParam param = new MybankLoanInclusivefinanceBusinessloanapplyRequestV1.RequestParam();
		MybankLoanInclusivefinanceBusinessloanapplyRequestV1.LoanInfo loanInfo = new MybankLoanInclusivefinanceBusinessloanapplyRequestV1.LoanInfo();
		MybankLoanInclusivefinanceBusinessloanapplyRequestV1.CisInfo cisInfo = new MybankLoanInclusivefinanceBusinessloanapplyRequestV1.CisInfo();
		MybankLoanInclusivefinanceBusinessloanapplyRequestV1.SupplyInfo supplyInfo = new MybankLoanInclusivefinanceBusinessloanapplyRequestV1.SupplyInfo();
		MybankLoanInclusivefinanceBusinessloanapplyRequestV1.LoanTrdInfo loanTrdInfo = new MybankLoanInclusivefinanceBusinessloanapplyRequestV1.LoanTrdInfo();
		List<MybankLoanInclusivefinanceBusinessloanapplyRequestV1.LoanTrdInfo> LoanTrdInfoList= new ArrayList<>();
		
		loanInfo.setApply_no("DY170710420200051");
		loanInfo.setProd_kind_code("030100004661");
		loanInfo.setLoan_amount("300");
		loanInfo.setLoan_time_limit("6");
		loanInfo.setApply_date("20220102");
		loanInfo.setPromise_pay_falg("030100004661");
		loanInfo.setPcrtnmod("1");
		loanInfo.setCooper_unit("DY1707104");
		loanInfo.setBalrtncl("0");
		loanInfo.setIntrtncl("0");
		loanInfo.setPay_mode("1");
		loanInfo.setReturn_account_type("6");
		loanInfo.setReturn_accno("6222021001064472456");
		loanInfo.setReturn_areacode1("02000059");
		loanInfo.setReturn_account_name1("九一二");
		loanInfo.setSend_account_type1("6");
		loanInfo.setSend_account1("6222021001064472456");
		loanInfo.setSend_areacode1("02000059");
		loanInfo.setSend_account_name1("九一二");
		loanInfo.setPromise_pay_days("1");
		param.setLoan_info(loanInfo);
		
		cisInfo.setCard_no("330100199508080906");
		cisInfo.setEducation_degree("2");
		cisInfo.setSex("2");
		cisInfo.setCommunicate_zone("");
		cisInfo.setCisname("九一二");
		cisInfo.setCard_type("0");
		cisInfo.setMarital_status("1");
		cisInfo.setCellphohone_no("15171009102");
		cisInfo.setCommunicate_adrress("文一西路80号");
		cisInfo.setCommunicate_zone("571");
		cisInfo.setMonitor_flag("00001");
		param.setCis_info(cisInfo);
		
		supplyInfo.setLoan_use("09");
		supplyInfo.setBusi_licence_type("");
		supplyInfo.setCooper_unit_name("");
		supplyInfo.setModel_type("ZH1458");	
		param.setSupply_info(supplyInfo);
		
		loanTrdInfo.setTrd_chain_code("DY1707104202009300001");
		loanTrdInfo.setTrd_trade_form_type("23");
		loanTrdInfo.setTrd_trade_form_money("300");
		LoanTrdInfoList.add(loanTrdInfo);
		param.setLoan_trd_info(LoanTrdInfoList);

		bizContent.setParam(param);
		request.setBizContent(bizContent);
		// System.out.println("-------param--------" + param);
		MybankLoanInclusivefinanceBusinessloanapplyResponseV1 response = new MybankLoanInclusivefinanceBusinessloanapplyResponseV1();
		try {
			response = client.execute(request);
		} catch (Exception e) {
			// System.out.println(e.getMessage());
		}
		if (response.isSuccess()) {
			// System.out.println("success!");
		} else {
			// System.out.println("fail" + response.getReturnMsg());
		}
	}
}
