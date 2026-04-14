/**
 * 
 */
package com.icbc.api;

import com.icbc.api.request.MybankLoanInclusivefinanceBillinfoqryRequestV1;
import com.icbc.api.response.MybankLoanInclusivefinanceBillinfoqryResponseV1;


/**
 * @author Administrator
 *
 */
public class MybankLoanInclusivefinanceBillinfoqryTestV1 {

	//APP_ID,APIGW_PUBLIC_KEY,PRI_KEY,URL  根据实际的应用场景进行修攄1�7
	protected static final String appId = "123";
    protected static final String privateKey = "xxxxxx";
    protected static final String apigwPublicKey = "xxxxxx";
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
	        DefaultIcbcClient client = new DefaultIcbcClient(appId, privateKey, apigwPublicKey);
	        MybankLoanInclusivefinanceBillinfoqryRequestV1 request = new MybankLoanInclusivefinanceBillinfoqryRequestV1();

	        request.setServiceUrl("http://ip:port/api/mybank/loan/inclusivefinance/billinfoqry/V1");
	        MybankLoanInclusivefinanceBillinfoqryRequestV1.MybankLoanInclusivefinanceBillinfoqryV1Biz param = new MybankLoanInclusivefinanceBillinfoqryRequestV1.MybankLoanInclusivefinanceBillinfoqryV1Biz();

			param.setSerialNo("123456789012345678901234567");
			param.setAppNo("F-APIP");
			param.setAreaCode("0020000072");
			param.setEmployeeCode("000393868");
			param.setLanguage("zh_CN");
			param.setTransNo("IQueryPerBillInfCocoaService");
			param.setVer("1.0");
			param.setTurnPageFlag("0");
			param.setBeginRow("");
			param.setRowCount("");


			param.setCooperUnit("ST2313014");
			param.setApply_no("02002000000000100876");
			param.setEbank_loan_apply_no("02002000000000100876");
			param.setCnltype("405");
			param.setTerminal_type("");
			param.setTerminal_ip("");
			param.setTerminal_mac("");

//	        System.out.println("-------个人经营贷款还款计划查询 入参--------" + param);
	        request.setBizContent(param);
	        MybankLoanInclusivefinanceBillinfoqryResponseV1 response = new MybankLoanInclusivefinanceBillinfoqryResponseV1();
	        try {
	            response = client.execute(request);
	        } catch (Exception e) {
//	            System.out.println(e.getMessage());
	        }
	        if (response.isSuccess()) {
//	            System.out.println("success!");
	        } else {
//	            System.out.println("fail" + response.getReturnMsg());
	        }
//	        System.out.println(response);
	}

}
