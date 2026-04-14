package com.icbc.api;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcConstants;
import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import org.junit.Test;
import com.icbc.api.request.GyjrB2bFinanceDigitalCreditQuerycontractinfoRequestV1;
import com.icbc.api.response.GyjrB2bFinanceDigitalCreditQuerycontractinfoResponseV1;

import java.util.ArrayList;
import java.util.List;

/**
 * @Title :
 * @Description :
 * @Author : kfzx-zhengld
 * @Date : 2021/6/21 11:24
 * @Version : 1.0
 */

public class GyjrB2bFinanceDigitalCreditQuerycontractinfoRequestTestV1 {
    // APPID(变量值需替换)
    protected static final String APP_ID = "10000000000004097876";
    // 应用私钥(变量值需替换)
    protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCaQJXO4meBA/FtTExiJCFIIaoOcsvMgZnOn9g5KZ9J01LN9iDHjI0g5FoDvw+9D54nQigR+TepMXH7NCTPA8TTvWIQWX3kyDPNqOOxtuHBjeTODh2b9MTYvUuNcJo75zN9a6BVJQZ9pMDPfUmwQGK5QtpLShuqf/+Uv5XNUCM4/7UD9HLkiJrQlENlnpaD1A416X0WCEWJuMvjEN/QIxIP+ULcKSMt++vgUSo0FJ2+eDudYyh12aotUlrHZqtHB6qGiMC9stc/idZQzu6EfUl0DJTSx1E8QkZ7shRsW8UnH4ed8xMYX54LksYTlNWvYBRh+hM1WqvtnbQ9ZQSmLiRdAgMBAAECggEAPWcIO1qUqR7QtBlzLn4DYZtE/otj5PySJVYr1FVlOxYMGECpu8WPSFCeJG+h7DiTVD1Zhg4WwhsUrFKNdHqjl0YEC04Ud0XamTtpqAyMwWeidQPZdYNx+HUh5iO0wbO/I2X8ZOhgNROoTPAqrSZ0JhsemmndqXlaW/QHxbroyn8d23ZqKUCNEWv/CPgmTY4SgJVGFcbQRCdr2kkFYvjcwSpW8Ce4zo/KhB2B/pXAIKKq8rvGivqqoE+Yh/ihLGkLXaqeJYaWoIEpr41tplnVI57+fAGxDBwmJqJnzZbsk8fncf/1DRyD7XRrlTcYafU5nnwFwcTZea/lSJrEfztXAQKBgQD1zAZYTozbsld//Pr30FzdYyV+pka1EI4WJZLtR5cSrB4Pec1p6z89tmGjYaTBtYwuON1YyP9VhXIZJpMvsdJCEwEXKM9aNRh7ycLQ8dw3yWm/HighfWW0KYr5O+OGUT3PBebjRhnTrRYnfPc34M07Ax0YN850nVNNpz0TnMzQpQKBgQCgp8F1au/Um9UiWWred+q/UgWiywH02aaWotTaoQ6hUqxBLSVjyYWPbjvFqcZN9ZUxNz0avJ7FH0IQQPY/opxoJmF46HLLjZ/OZEdpwzpaJnldQG9UCSdI5I5uo31nyatxh5FWjWjfEx64FgApYDiI8lDjdgZqCpDoMOLO8k0/WQKBgQCKPmIjMKrQthG4jpSo5KwWaDkibmq+U71Wf3OPuQAjJGMT2THLLOC42Oc5eSWtPWrGZH05/nJ9qzxtIOEPpjfXqdCnMfDNj6dhmCzWT/HH/qRXNaLkkx6soeDkJObnGLJmya7+jOLhxyv6sbJp3yoO0sV+ZYTc2c+MkU8sxLf9cQKBgFjXrGJUesBmVN0j+beBNS4MG9XE0hYnbrqK3Y7x1fdckqmQX8bpIJUK7r8GfgWKs7B9zarFeD+mY5A9fHAAauIlp22HjkQG08L9LYjNT62e7SqmgWpFjZX/3sxMWQNWQRsNT1A/7yi9mBVRBVHcRidlaVoAYNevdCcTMbojwJgxAoGBAI3MbwoNpUiN8rvl4nosYpG98B9NFJLk6YcobfZWquS8JLeGNm1iTH87l9YlX47FBho6FPuYuzNSKjJwFtKqoaPL2UMiD/Xwx73xXEQHNhb5QW8tPVGXA57zKH1GJjZRyq8ksbuMLv1yIbtXfH5x122i+jUVScXgLMjqn3Wwlq0j";
    // API网关公钥(变量值需替换)
    protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";


    public void test_cop() throws Exception {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        GyjrB2bFinanceDigitalCreditQuerycontractinfoRequestV1 request = new GyjrB2bFinanceDigitalCreditQuerycontractinfoRequestV1();
        request.setServiceUrl("http://ip:port/api/gyjr/b2b/finance/digitalcredit/querycontractinfo/V1");
        GyjrB2bFinanceDigitalCreditQuerycontractinfoRequestV1.MybankLoanAcceptContractInfoRequestV1Biz bizContent = new GyjrB2bFinanceDigitalCreditQuerycontractinfoRequestV1.MybankLoanAcceptContractInfoRequestV1Biz();
        GyjrB2bFinanceDigitalCreditQuerycontractinfoRequestV1.MybankLoanAcceptContractInfoRequestV1Biz.TransInfo transInfo = new GyjrB2bFinanceDigitalCreditQuerycontractinfoRequestV1.MybankLoanAcceptContractInfoRequestV1Biz.TransInfo();
        transInfo.setCus_cis("PF0053");
        transInfo.setBusi_type("200");
        GyjrB2bFinanceDigitalCreditQuerycontractinfoRequestV1.MybankLoanAcceptContractInfoRequestV1Biz.TransInfo.InputElements inputElements = new GyjrB2bFinanceDigitalCreditQuerycontractinfoRequestV1.MybankLoanAcceptContractInfoRequestV1Biz.TransInfo.InputElements();
        inputElements.setCert_type("3");
        inputElements.setDraw_acc_name("");
        inputElements.setCert_code("1556313165131567516");
        inputElements.setEnterprise_no("020090001926823");
        inputElements.setCredit_voucher("01");
        inputElements.setPlatform_no("PF0053");
        inputElements.setApply_date("20251230");
        inputElements.setDraw_acc_name("10010009名称");
        inputElements.setU_keyid("4990002224");
        inputElements.setCurrency("001");
        inputElements.setConfirm_type("1");
        inputElements.setProtocol_code("10550000000002902000");
        inputElements.setCustomer_numb("020090000206423");
        inputElements.setGroup_name("委耽芒垮蹲氛栓殷切绥");
        inputElements.setDue_date("20290620");
        inputElements.setGroup_cis("");
        inputElements.setProduct_type("1");
        inputElements.setGroup_id("102054341AAAAAA");
        inputElements.setCustomer_name("北京印刷集团有限责任公司");
        inputElements.setScene_code("10550000000002905001");
        inputElements.setEnterprise_name("比亚迪");
        inputElements.setDraw_acc("0200041709213281068");
        inputElements.setApply_amount("4000000");
        inputElements.setCertiID("sunlele01.c.0200");

        GyjrB2bFinanceDigitalCreditQuerycontractinfoRequestV1.MybankLoanAcceptContractInfoRequestV1Biz.TransInfo.TradeInfo tradeInfo = new GyjrB2bFinanceDigitalCreditQuerycontractinfoRequestV1.MybankLoanAcceptContractInfoRequestV1Biz.TransInfo.TradeInfo();
        List<GyjrB2bFinanceDigitalCreditQuerycontractinfoRequestV1.MybankLoanAcceptContractInfoRequestV1Biz.TransInfo.TradeInfo> tradeInfoList = new ArrayList<GyjrB2bFinanceDigitalCreditQuerycontractinfoRequestV1.MybankLoanAcceptContractInfoRequestV1Biz.TransInfo.TradeInfo>();
        tradeInfo.setTrade_info_numb("dxptest202111100001e1");
        tradeInfo.setTrade_info_money("4000000");

        tradeInfoList.add(tradeInfo);

        GyjrB2bFinanceDigitalCreditQuerycontractinfoRequestV1.MybankLoanAcceptContractInfoRequestV1Biz.TransInfo.BillInfo billInfo = new GyjrB2bFinanceDigitalCreditQuerycontractinfoRequestV1.MybankLoanAcceptContractInfoRequestV1Biz.TransInfo.BillInfo();
        List<GyjrB2bFinanceDigitalCreditQuerycontractinfoRequestV1.MybankLoanAcceptContractInfoRequestV1Biz.TransInfo.BillInfo> billInfoList = new ArrayList<>();
        billInfo.setSerial_no("1");
        billInfo.setBill_numb("1");
        billInfo.setBill_currency("001");
        billInfo.setBill_date("20290620");
        billInfo.setContract_num("01");
        billInfo.setBill_code("111111");
        billInfo.setBill_checkcode6("01");
        billInfo.setBill_money("2000000");
        billInfo.setBill_type("01");
        billInfo.setBill_kind("01");
        billInfo.setTrade_info_numb("dxptest202111100001e1");
        billInfoList.add(billInfo);



        List<GyjrB2bFinanceDigitalCreditQuerycontractinfoRequestV1.MybankLoanAcceptContractInfoRequestV1Biz.TransInfo.Ecredit> ecreditList = new ArrayList<>();
        GyjrB2bFinanceDigitalCreditQuerycontractinfoRequestV1.MybankLoanAcceptContractInfoRequestV1Biz.TransInfo.Ecredit ecredit = new GyjrB2bFinanceDigitalCreditQuerycontractinfoRequestV1.MybankLoanAcceptContractInfoRequestV1Biz.TransInfo.Ecredit();
        ecredit.setSerial_no("1");
        ecredit.setTransf_date("20290620083036");
        ecredit.setEcredit_ent_name("燕燕网银数据1");
        ecredit.setCerti_numb("52515");
        ecredit.setEcredit_sign("XXXX");
        ecredit.setEcredit_numb("dxptest202111100001e");
        ecredit.setEcredit_ent_taxID("1561515");
        ecredit.setEcredit_ent_numb("020090001926823");
        ecredit.setPay_date("20290620");
        ecredit.setEcredit_money("4000000");
        ecredit.setEcredit_text("我的地方");
        ecredit.setCerti_type("ICBC");
        ecredit.setPrev_ecredit_numb("-1");
        ecredit.setEcredit_sign_date("20290620083036");
        ecredit.setTrade_info_numb("dxptest202111100001e1");
        ecredit.setNext_ecredit_ent_numb("1000000000000000000000011068751-3");
        ecredit.setFlow_levels("0");
        ecredit.setNext_ecredit_ent_name("委耽芒垮蹲氛栓殷切绥");
        ecreditList.add(ecredit);
        GyjrB2bFinanceDigitalCreditQuerycontractinfoRequestV1.MybankLoanAcceptContractInfoRequestV1Biz.TransInfo.Ecredit ecredit1 = new GyjrB2bFinanceDigitalCreditQuerycontractinfoRequestV1.MybankLoanAcceptContractInfoRequestV1Biz.TransInfo.Ecredit();
        ecredit1.setSerial_no("2");
        ecredit1.setTransf_date("20290620083036");
        ecredit1.setEcredit_ent_name("委耽芒垮蹲氛栓殷切绥");
        ecredit1.setCerti_numb("52515");
        ecredit1.setEcredit_sign("XXXX");
        ecredit1.setEcredit_numb("dxptest202111100001e1");
        ecredit1.setEcredit_ent_taxID("1561515");
        ecredit1.setEcredit_ent_numb("1000000000000000000000011068751-3");
        ecredit1.setPay_date("20290620");
        ecredit1.setEcredit_money("4000000");
        ecredit1.setEcredit_text("我的地方");
        ecredit1.setCerti_type("ICBC");
        ecredit1.setPrev_ecredit_numb("dxptest202111100001e");
        ecredit1.setEcredit_sign_date("20290620083036");
        ecredit1.setTrade_info_numb("dxptest202111100001e1");
        ecredit1.setNext_ecredit_ent_numb("020090001926823");
        ecredit1.setFlow_levels("1");
        ecredit1.setNext_ecredit_ent_name("xxx公司");
        ecreditList.add(ecredit1);

        inputElements.setTrade_info_list(tradeInfoList);
        inputElements.setBill_info_list(billInfoList);
        inputElements.setEcredit_list(ecreditList);
        transInfo.setInput_elements(inputElements);
        bizContent.setTransInfo(transInfo);
        request.setBizContent(bizContent);
        GyjrB2bFinanceDigitalCreditQuerycontractinfoResponseV1 response;
        try {
            response = client.execute(request);

            if (response.isSuccess()) {
                // 业务成功处理
                System.out.println(response.getReturnCode());
                String returnContent = response.getReturnContent();
                System.out.println(returnContent);
                JSONObject jsonObject = JSONObject.parseObject(returnContent);
                System.out.println(jsonObject.get("returnCode"));
                System.out.println(jsonObject.get("returnMsg"));
                String returnContent1 = (String) jsonObject.get("returnContent");
                System.out.println(returnContent1);
                JSONObject content = JSONObject.parseObject(returnContent1);
                System.out.println(content.get("adjustA_mounth"));
                System.out.println(content.get("argue_solve_place"));
                System.out.println(content.get("cert_code"));
                System.out.println(content.get("cert_type"));
                System.out.println(content.get("con_text_version"));
                System.out.println(content.get("confirm_type"));
                System.out.println(content.get("customer_numb"));
                System.out.println(content.get("e_mail"));
                System.out.println(content.get("enterprise_int_pay_method"));
                System.out.println(content.get("eredit_due_date"));
                System.out.println(content.get("error_code"));
                System.out.println(content.get("error_msg"));
                System.out.println(content.get("factor_account_B"));
                System.out.println(content.get("factor_account_agreement"));
                System.out.println(content.get("factoring_bank"));
                System.out.println(content.get("factoring_bank_address"));
                System.out.println(content.get("factoring_bank_phone"));
                System.out.println(content.get("factoring_fiance_bigger"));
                System.out.println(content.get("factoring_fiance_lower"));
                System.out.println(content.get("fax_no"));
                System.out.println(content.get("finance_body"));
                System.out.println(content.get("finance_body_address"));
                System.out.println(content.get("float_rate1"));
                System.out.println(content.get("float_rate1_type"));
                System.out.println(content.get("float_rate2"));
                System.out.println(content.get("float_rate2_type"));
                System.out.println(content.get("float_rate3"));
                System.out.println(content.get("float_rate3_type"));
                System.out.println(content.get("grant_lendCompany"));
                System.out.println(content.get("int_pay_method"));
                System.out.println(content.get("lastEredit_due_date"));
                System.out.println(content.get("legal_person"));
                System.out.println(content.get("linkman"));
                System.out.println(content.get("loan_insno"));
                System.out.println(content.get("methodA"));
                System.out.println(content.get("methodB"));
                System.out.println(content.get("methodC"));
                System.out.println(content.get("od_int"));
                System.out.println(content.get("od_int_float"));
                System.out.println(content.get("payee_inner_account"));
                System.out.println(content.get("phone_no"));
                System.out.println(content.get("platform_no"));
                System.out.println(content.get("post_code"));
                System.out.println(content.get("rate_adjust"));
                System.out.println(content.get("receive_register_regulation"));
                System.out.println(content.get("serial_number"));
                System.out.println(content.get("settle_int_byDay"));
                System.out.println(content.get("settle_int_method"));
                System.out.println(content.get("telephone_no"));
                System.out.println(content.get("two_items"));
            } else {
                // 失败
            }
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }catch (Exception e) {
            System.out.println(e);
        }
    }


}
