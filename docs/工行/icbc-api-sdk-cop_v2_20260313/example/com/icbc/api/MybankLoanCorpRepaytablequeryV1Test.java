package com.icbc.api;

import com.icbc.api.request.MybankLoanCorpRepaytablequeryRequestV1;
import com.icbc.api.request.MybankLoanCorpRepaytablequeryRequestV1.MybankLoanCorpRepaytablequeryRequestV1Biz;
import com.icbc.api.response.MybankLoanCorpRepaytablequeryResponseV1;

public class MybankLoanCorpRepaytablequeryV1Test {

	public static void main(String[] args) {
		try {
			String APP_ID = "[需替换]工行API APPID，请向工行联系获取";
			String APIGW_PUBLIC_KEY = "[需替换]工行API网关公钥，请向工行联系获取";
			String PRI_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";
			String URL = "http://[需替换]地址/api/mybank/loan/corp/repaytablequery/V1";

			DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, PRI_KEY,
					APIGW_PUBLIC_KEY);

			MybankLoanCorpRepaytablequeryRequestV1Biz bizContent = new MybankLoanCorpRepaytablequeryRequestV1Biz();

			MybankLoanCorpRepaytablequeryRequestV1.PublicInput publicInput = new MybankLoanCorpRepaytablequeryRequestV1.PublicInput();
			publicInput.setAppid("xxxxxx");
			publicInput.setAppseqno("xxxxxx");
			publicInput.setTimestamp("2020-12-25T19:04:08.942+0800");
			publicInput.setServicealias("LCB_ATSLoanRepayTable_queryRepayPlanByLoanOrPreqry_V1.0");
			bizContent.setPublicInput(publicInput);

			MybankLoanCorpRepaytablequeryRequestV1.ChanCommV10 chanCommV10 = new MybankLoanCorpRepaytablequeryRequestV1.ChanCommV10();
			chanCommV10.setAuthbrno("");
			chanCommV10.setAuthssi("");
			// 对应参数自行添加
			bizContent.setChanCommV10(chanCommV10);

			MybankLoanCorpRepaytablequeryRequestV1.InfoCommV10 infoCommV10 = new MybankLoanCorpRepaytablequeryRequestV1.InfoCommV10();
			infoCommV10.setActbrno("");
			infoCommV10.setApptype("");
			// 对应参数自行添加
			bizContent.setInfoCommV10(infoCommV10);

			MybankLoanCorpRepaytablequeryRequestV1.TranComm tranComm = new MybankLoanCorpRepaytablequeryRequestV1.TranComm();
			tranComm.setCheckTrxDateFlag(0);
			tranComm.setFlagFor24Hours(0);
			bizContent.setTranComm(new MybankLoanCorpRepaytablequeryRequestV1.TranComm());

			MybankLoanCorpRepaytablequeryRequestV1.PrivateInput privateInput = new MybankLoanCorpRepaytablequeryRequestV1.PrivateInput();
			privateInput.setAmount(0);
			privateInput.setBalance(0);
			// 对应参数自行添加
			bizContent.setPrivateInput(privateInput);

			MybankLoanCorpRepaytablequeryRequestV1 request = new MybankLoanCorpRepaytablequeryRequestV1();
			request.setServiceUrl(URL);
			request.setBizContent(bizContent);
			MybankLoanCorpRepaytablequeryResponseV1 response = client.execute(request);
			  
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
