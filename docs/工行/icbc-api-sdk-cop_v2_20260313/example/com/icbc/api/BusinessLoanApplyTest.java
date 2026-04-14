package com.icbc.api;
import java.util.HashMap;
import java.util.logging.Logger;

import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.BusinessLoanApplyRequestV1;
import com.icbc.api.request.BusinessLoanApplyRequestV1.BusinessLoanApplyBizContent;
import com.icbc.api.response.BusinessLoanApplyResponseV1;

public class BusinessLoanApplyTest {
    static Logger log = Logger.getLogger(BusinessLoanApplyTest.class.getName());

	//appid,privateKey,apigwPublicKey  根据实际的应用场景进行修改
//	protected static final String appId="10000000000004095243";
	protected static final String appId="1";
	protected static final String privateKey="-----请使用正确的APP私钥-----CdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALAWAcPiTMRU906PTdy0ozspX7XptZnkEw2C8R64RDB9BiRFXAj0cU4aTA1MyfmGIlceeVdgJf7OnmvpHnYxjQ7sGxMItPtodrGwA2y8j0AEbHc5pNWU8Hn0zoY9smHS5e+KjSbWv+VNbdnrRFTpDeiJ3+s2E/cKI2CDRbo7cAarAgMBAAECgYABiA933q4APyTvf/uTYdbRmuiEMoYr0nn/8hWayMt/CHdXNWs5gLbDkSL8MqDHFM2TqGYxxlpOPwnNsndbW874QIEKmtH/SSHuVUJSPyDW4B6MazA+/e6Hy0TZg2VAYwkB1IwGJox+OyfWzmbqpQGgs3FvuH9q25cDxkWntWbDcQJBAP2RDXlqx7UKsLfM17uu+ol9UvpdGoNEed+5cpScjFcsB0XzdVdCpp7JLlxR+UZNwr9Wf1V6FbD2kDflqZRBuV8CQQCxxpq7CJUaLHfm2kjmVtaQwDDw1ZKRb/Dm+5MZ67bQbvbXFHCRKkGI4qqNRlKwGhqIAUN8Ynp+9WhrEe0lnxo1AkEA0flSDR9tbPADUtDgPN0zPrN3CTgcAmOsAKXSylmwpWciRrzKiI366DZ0m6KOJ7ew8z0viJrmZ3pmBsO537llRQJAZLrRxZRRV6lGrwmUMN+XaCFeGbgJ+lphN5/oc9F5npShTLEKL1awF23HkZD9HUdNLS76HCp4miNXbQOVSbHi2QJAUw7KSaWENXbCl5c7M43ESo9paHHXHT+/5bmzebq2eoBofn+IFsyJB8Lz5L7WciDK7WvrGC2JEbqwpFhWwCOl/w==";
	protected static final String apigwPublicKey="-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

	public static void main(String[] args) throws IcbcApiException{
		DefaultIcbcClient client = new DefaultIcbcClient(appId, privateKey,apigwPublicKey);
		BusinessLoanApplyRequestV1 request = new BusinessLoanApplyRequestV1();
		
		request.setServiceUrl("http://ip/api/mybank/loan/inclusivefinance/businessloanapply/V1");
		//request.setServiceUrl("http://ip:port/api/mybank/loan/inclusivefinance/businessloanapply/V1");
		BusinessLoanApplyBizContent bizContent = new BusinessLoanApplyBizContent();
		HashMap<String,Object> param = new HashMap<String,Object>();
		//公共参数
		param.put("serialNo","1");
		param.put("msg_id","1");
		param.put("areaCode","");
		param.put("employeeCode","");
		param.put("language","zh_CN");
		param.put("ver","1.0");
		param.put("turnPageFlag","");
		param.put("beginRow","");
		param.put("rowCount","");
		//私有参数
        
        
        JSONObject cisInfo = new JSONObject();
        JSONObject transInfo = new JSONObject();
        JSONObject loanInfo = new  JSONObject();
        JSONObject supplyInfo = new JSONObject();
        
        cisInfo.put("card_no", "1");
        cisInfo.put("education_degree", "9");
        cisInfo.put("monitor_flag", "00001");
        cisInfo.put("communicate_adrress", "四川省成都市高新区环球中心E3-705");
        cisInfo.put("cino", "1");
        cisInfo.put("cisname", "吴宽");
        cisInfo.put("card_type", "0");
        //cisInfo.put("terminal_ip", "ip:port");
        cisInfo.put("terminal_ip", "1");
        cisInfo.put("terminal_mac", "00:01:6C:06:A6:29");
        cisInfo.put("terminal_type", "2");
        cisInfo.put("marital_status", "1");
        cisInfo.put("cellphohone_no", "13267125471");
        cisInfo.put("work_company", "1");
        
        transInfo.put("identify_file_stream", "");
        transInfo.put("identify_media", "123456789");
        transInfo.put("identify_timestamp", "1567738381899");
        transInfo.put("identify_mode", "1");
        
        loanInfo.put("send_areacode1", "02000059");
        loanInfo.put("return_account_name1", "吴宽");
        loanInfo.put("return_areacode1", "00260");
        loanInfo.put("return_accno", "1");
        loanInfo.put("return_account_type", "6");
        loanInfo.put("deal_area", "0010100000");
        loanInfo.put("intrtncl", "3");
        loanInfo.put("loan_amount", "1");
        loanInfo.put("balrtncl", "0");
        loanInfo.put("pcrtnmod", "5");
        loanInfo.put("cooper_unit", "ST2313014");
        loanInfo.put("apply_type", "17");
        loanInfo.put("apply_source", "4");
        loanInfo.put("currtype", "001");
        loanInfo.put("send_account_type1", "1");
        loanInfo.put("prod_kind_code", "030100004661");
        loanInfo.put("promise_pay_falg", "1");
        loanInfo.put("promise_pay_days", "12");
        loanInfo.put("apply_no", "1");
        loanInfo.put("selling_channel", "");
        loanInfo.put("selling_def_code", "");
        loanInfo.put("loan_time_limit", "6");
        loanInfo.put("pay_mode", "1");
        loanInfo.put("send_account1", "1");
        loanInfo.put("cnltype", "405");
        loanInfo.put("send_account_name1", "测试");
        loanInfo.put("selling_employee", "");
        loanInfo.put("natural_float_type", "3");
        loanInfo.put("natural_float_value", "5");
        loanInfo.put("apply_date", "20190905");
        
        supplyInfo.put("cooper_unit_name", "");
        supplyInfo.put("loan_use", "9");
        supplyInfo.put("busi_licence_type", "");
        
        param.put("cis_info", cisInfo);
        param.put("trans_info", transInfo);
        param.put("loan_info", loanInfo);
        param.put("supply_info", supplyInfo);
        
		bizContent.setParam(param);
		request.setBizContent(bizContent);
		
//		System.out.println("-------合作方个人经营类贷款申请入参--------"+param);
		
		BusinessLoanApplyResponseV1 response=new BusinessLoanApplyResponseV1();
		try{
			response = client.execute(request);
		}catch(Exception e){
			log.info(e.getMessage());
		}
		if(response.isSuccess()){
			log.info("success!");
		}else{
			log.info("fail"+response.getReturnMsg());
//			System.out.println("fail"+response.getReturnMsg());
		}
		}
	
}
