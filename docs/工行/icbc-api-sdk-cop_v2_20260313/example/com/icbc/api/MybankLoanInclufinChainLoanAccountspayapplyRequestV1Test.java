package com.icbc.api;

import java.util.ArrayList;
import java.util.List;

import com.icbc.api.request.MybankLoanInclufinChainLoanAccountspayapplyRequestV1;
import com.icbc.api.request.MybankLoanInclufinChainLoanAccountspayapplyRequestV1.MybankLoanInclufinChainLoanAccountspayapplyRequestV1Biz;
import com.icbc.api.request.MybankLoanInclufinChainLoanAccountspayapplyRequestV1.MybankLoanInclufinChainLoanAccountspayapplyRequestV1Biz.PFPATradeInfoDTO;
import com.icbc.api.response.MybankLoanInclufinChainLoanAccountspayapplyResponseV1;


public class MybankLoanInclufinChainLoanAccountspayapplyRequestV1Test {

	public static void main(String[] args) {
		try {
			 String APP_ID = "xxxxxxx";
			 String APIGW_PUBLIC_KEY = "xxxxx";
			 String PRI_KEY =   "xxx";               
			 String URL = "http://xxxxx/api/mybank/loan/inclufin/chainloan/accountspayapply/V1";
			
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, PRI_KEY, APIGW_PUBLIC_KEY);
        

        	MybankLoanInclufinChainLoanAccountspayapplyRequestV1Biz bizContent =
                    new MybankLoanInclufinChainLoanAccountspayapplyRequestV1Biz();
        	
        	
        	bizContent.setSerialNo("123");
        	bizContent.setAppNo("XX");
        	bizContent.setLanguage("zh_CN");
        	bizContent.setTransNo("IPayForPayableAccountCocoaService");
        	bizContent.setVer("2.0");
        	
        	bizContent.setPayBatchNo("xxxx");
        	bizContent.setSupplyChainNo("xxxx");
        	bizContent.setSupplierIdType("3");
        	bizContent.setSupplierIdNo("SupplierIdNo001");
        	bizContent.setSupplierCustName("供应商客户名");
        	bizContent.setPayerCustName("付款人客户名");
        	bizContent.setPayerIdType("3");
        	bizContent.setPayerIdNo("payeridno");
        	bizContent.setPayerAcctName("payeracctname");
        	bizContent.setPayerAcctNo("payeracctno");
        	bizContent.setAmount("99.00");
        	bizContent.setCurrency("001");

            PFPATradeInfoDTO dto = new PFPATradeInfoDTO();
            dto.setCredenceType("14");
            dto.setCredenceNo("200993");
            dto.setCredenceAmount("100.00");
            dto.setCredencePayAmount("99.00");

            List<PFPATradeInfoDTO> infoList = new ArrayList<PFPATradeInfoDTO>();
            infoList.add(dto);
        	bizContent.setInfoList(infoList);
        	
        	MybankLoanInclufinChainLoanAccountspayapplyRequestV1 request =
                    new MybankLoanInclufinChainLoanAccountspayapplyRequestV1();
            request.setServiceUrl(URL);
            request.setBizContent(bizContent);
            MybankLoanInclufinChainLoanAccountspayapplyResponseV1 response = client.execute(request);
          
            if (response.isSuccess()) {
            	//业务处理
                
            } else {
            	//业务处理
            }

        } catch (Exception e) {
        	//异常处理
        }
	}
}
