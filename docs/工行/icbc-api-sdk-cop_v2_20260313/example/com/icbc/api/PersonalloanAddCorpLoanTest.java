package com.icbc.api;

import java.util.HashMap;

import com.icbc.api.internal.util.internal.util.fastjson.JSONArray;
import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.request.PersonalloanAddCorpLoanRequestV1;
import com.icbc.api.request.PersonalloanAddCorpLoanRequestV1.PersonalloanAddCorpLoanBizContent;
import com.icbc.api.response.PersonalloanAddCorpLoanResponseV1;

public class PersonalloanAddCorpLoanTest {
	protected static final String appId="10000000000000064502";
	protected static final String privateKey="-----请使用正确的APP私钥-----EvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCC50HsMql4FnV729+Tum3GW/" +
			"CmfGTpsK8iJtMxAjvR9rNDPnriAWie1L1tkalETxFayD0IJJOQX7uQ3QabcpxtiZro15ieLtBZ10BxlSZZyyPTHoTN+365Z95zNImQLqLwzt" +
			"fkh+KNdjK/Oh4FdXhgULWILUHo6ve+N7UVphxT6judE/ffC0rHug9gAuGVtIU0Uv2XQz0I0fymIX37OM8rO1WpzyVCTVBkq9gT63YRf3vOan3i" +
			"WogBHSGX8VIg2J0zWHxaW7RUoLmcZPU7WFXVL0nPt5gdRHH5UVG8gvhG2/IbPqJAYNPdYwjTyjH1/BDfwGRPdySdyrJBzFnHS4cRAgMBAAECgg" +
			"EAVzrz2woHlFajA16Jpk/dRwmNW+QY+wEaM4/bvBTY+BmFa5H/IQv/Q5hQgutF+JcX4X797KczDko9G1OuS3gcdYH1EKRat05K2RNvhkD1xXBw9" +
			"wanSPwGflaRDgQ8hzAMtJuhklBa0Bmrx3Ja/xhAly4xSgWeni+117QXn82JowDHi9wy4IzdKAGG0oBzvpgeqlujKRe0IE8jEKtpaBGER7BOWuQW" +
			"aQch0AVi+iYTXRk1RPQhyEmIayY/ADOZskni5AZCHCUE53q/rPuGRetr+UikpA7p0JyMPvehh9OBbbO6HVN5Gp4l77zgpjS+JW+TR/+011U1nhiy" +
			"T2O4Oi/m4QKBgQC9SxxrEqB8/Cs1HP6kzL+lRdHmMktStdqdWyup42LBZLN35VTcS95hVpJ3Vky8H04jA84IjRNW24rAcu+Gv7kB5AJRQ0ZVYe4cZ" +
			"wgq42gjc+5FlScQ8EhzdIU+dzbdooWJEekL7d/qiKuKN+7Ic5q/6kBegTZKpZ15hl3+f1w6UwKBgQCxCI3nIyyucNLndmrypkdJDkkc/nxo/M0Nml" +
			"DK7Z0NBKPHwUZFUlyszB9c5eWNbI/flBxlOOMdJFw//7XQONHTCJC0SnxMofJNcQqEdiD033flWCJuUvqbFKX+W9MDg/8jxC2kTuWjhEB5Ap6QU1uXQd" +
			"Pjt/dWgNayqKNBpEU0iwKBgQCC/ITPuvX/RugHOuci1uQO07+pkUf6FgkPNlWCm+xscuiVoMYWIxHhPWzg04EJ9Ie3Rxy9YhHt2sGq5l3FWsoE+uh3nx" +
			"v+XWPNl6ZZNz+KuOA6mjmMSRl5eznwvcHjoGDTNueS76owux4o6Qd9AjIld03Pl44gHXZY75f/UdZaLQKBgQCV7GWoQhyb2kZx6NInjnO+Rc/bMxWPH9BT3" +
			"cTgJhyjorNE/sQQ/vAnEnLZDIS7oR2yvU7S1jtYOau6M0b5ALWJX66UUKQ1jcySktHSXewJFJsH6fy2q6U54mG1KHRqfMV8t63jgUFumhizomlAVqexT/" +
			"ku12QqqM+p27DubTyCgwKBgDyRjMQhVJQkda6tBF2WIwnEQ/1XfcTBlmqxRIX5/h0HDRrlYoyhfkP3xYgMtC0yekwycFY+73crby3qtpZve1fHB50WGK7tc" +
			"G6n99AVX4aKQzdEqZR557/9sAi4g/bzqvehRrb6afDXQ59dcV50l4NtJIKz0H+wP5/mAv7AJrCy";
	protected static final String apigwPublicKey="-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

	public static void main(String[] args) throws IcbcApiException{
		DefaultIcbcClient client = new DefaultIcbcClient(appId, privateKey,apigwPublicKey);
		PersonalloanAddCorpLoanRequestV1 request = new PersonalloanAddCorpLoanRequestV1();
		request.setServiceUrl("http://ip:port/api/personalloan/addCorpLoan/V1");
		PersonalloanAddCorpLoanBizContent bizContent = new PersonalloanAddCorpLoanBizContent();
		HashMap<String,Object> param = new HashMap<String,Object>();
		HashMap<String,Object> cisInfo = new HashMap<String,Object>();
		cisInfo.put("cisname", "����11");
		cisInfo.put("sex", "1");
		cisInfo.put("birth_day", "19880622");
		cisInfo.put("card_type", "0");
		cisInfo.put("card_no", "110190198806227776");
		//bizContent.setCisInfo(cisInfo);
		param.put("cis_info", cisInfo);
		
		JSONArray rela_cis_info = new JSONArray();
		HashMap<String,Object> relaCisInfoMap1 = new HashMap<String,Object>();
		relaCisInfoMap1.put("rel_cisname", "�ﳬϱ����");
		relaCisInfoMap1.put("rel_card_type", "1");
		relaCisInfoMap1.put("rel_card_no", "hz1101");
		relaCisInfoMap1.put("rel_relation_def_id", "0005");
		rela_cis_info.add(relaCisInfoMap1);
		HashMap<String,Object> relaCisInfoMap2 = new HashMap<String,Object>();
		relaCisInfoMap2.put("rel_cisname", "��ͬ������");
		relaCisInfoMap2.put("rel_card_type", "1");
		relaCisInfoMap2.put("rel_card_no", "hz1102");
		relaCisInfoMap2.put("rel_relation_def_id", "S6");
		rela_cis_info.add(relaCisInfoMap2);
		//bizContent.setRelaCisInfo(rela_cis_info);
		param.put("rela_cis_info", rela_cis_info);
        System.out.println("-------rela_cis_info--------"+rela_cis_info);
		
		HashMap<String,Object> loanInfo = new HashMap<String,Object>();
		loanInfo.put("apply_no","test0001");
		loanInfo.put("prod_kind_code", "140010000296");
		loanInfo.put("currtype", "001");
		loanInfo.put("loan_amount", "100000");
		loanInfo.put("loan_time_limit", "120");
		loanInfo.put("deal_area", "0020000067");
		loanInfo.put("assure_means", "3");
		loanInfo.put("cooper_unit", "TH0200015");
		//bizContent.setLoanInfo(loanInfo);
		param.put("loan_info", loanInfo);
		
		HashMap<String,Object> supplyInfo = new HashMap<String,Object>();
		supplyInfo.put("down_payment", "4180975.00");
		supplyInfo.put("total_amt", "8440975.00");
		supplyInfo.put("building_area", "128");
		supplyInfo.put("entrust_amount", "0.00");
		supplyInfo.put("building_adrress", "̩�̺���-һ��-10��-83��502");
		supplyInfo.put("house_count", "1");
		//bizContent.setSupplyInfo(supplyInfo);
		param.put("supply_info", supplyInfo);
		
		bizContent.setParam(param);
		request.setBizContent(bizContent);
		System.out.println("-------param--------"+param);
		PersonalloanAddCorpLoanResponseV1 response;
		response = client.execute(request);
		if(response.isSuccess()){
			System.out.println("success!");
		}else{
			System.out.println("fail"+response.getReturnMsg());
		}
		}
}
