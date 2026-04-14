package com.icbc.api;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcConstants;
import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import org.junit.Test;
import com.icbc.api.request.GyjrB2bFinanceDigitalCreditApplyLoanRequestV1;
import com.icbc.api.response.GyjrB2bFinanceDigitalCreditApplyLoanResponseV1;

import java.util.ArrayList;
import java.util.List;

/**
 * @Title :
 * @Description :
 * @Author : kfzx-zhengld
 * @Date : 2021/6/21 11:24
 * @Version : 1.0
 */

public class GyjrB2bFinanceDigitalCreditApplyLoanRequestTestV1 {

    // APPID(变量值需替换)
    protected static final String APP_ID = "10000000000004097876";
    // 应用私钥(变量值需替换)
    protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCaQJXO4meBA/FtTExiJCFIIaoOcsvMgZnOn9g5KZ9J01LN9iDHjI0g5FoDvw+9D54nQigR+TepMXH7NCTPA8TTvWIQWX3kyDPNqOOxtuHBjeTODh2b9MTYvUuNcJo75zN9a6BVJQZ9pMDPfUmwQGK5QtpLShuqf/+Uv5XNUCM4/7UD9HLkiJrQlENlnpaD1A416X0WCEWJuMvjEN/QIxIP+ULcKSMt++vgUSo0FJ2+eDudYyh12aotUlrHZqtHB6qGiMC9stc/idZQzu6EfUl0DJTSx1E8QkZ7shRsW8UnH4ed8xMYX54LksYTlNWvYBRh+hM1WqvtnbQ9ZQSmLiRdAgMBAAECggEAPWcIO1qUqR7QtBlzLn4DYZtE/otj5PySJVYr1FVlOxYMGECpu8WPSFCeJG+h7DiTVD1Zhg4WwhsUrFKNdHqjl0YEC04Ud0XamTtpqAyMwWeidQPZdYNx+HUh5iO0wbO/I2X8ZOhgNROoTPAqrSZ0JhsemmndqXlaW/QHxbroyn8d23ZqKUCNEWv/CPgmTY4SgJVGFcbQRCdr2kkFYvjcwSpW8Ce4zo/KhB2B/pXAIKKq8rvGivqqoE+Yh/ihLGkLXaqeJYaWoIEpr41tplnVI57+fAGxDBwmJqJnzZbsk8fncf/1DRyD7XRrlTcYafU5nnwFwcTZea/lSJrEfztXAQKBgQD1zAZYTozbsld//Pr30FzdYyV+pka1EI4WJZLtR5cSrB4Pec1p6z89tmGjYaTBtYwuON1YyP9VhXIZJpMvsdJCEwEXKM9aNRh7ycLQ8dw3yWm/HighfWW0KYr5O+OGUT3PBebjRhnTrRYnfPc34M07Ax0YN850nVNNpz0TnMzQpQKBgQCgp8F1au/Um9UiWWred+q/UgWiywH02aaWotTaoQ6hUqxBLSVjyYWPbjvFqcZN9ZUxNz0avJ7FH0IQQPY/opxoJmF46HLLjZ/OZEdpwzpaJnldQG9UCSdI5I5uo31nyatxh5FWjWjfEx64FgApYDiI8lDjdgZqCpDoMOLO8k0/WQKBgQCKPmIjMKrQthG4jpSo5KwWaDkibmq+U71Wf3OPuQAjJGMT2THLLOC42Oc5eSWtPWrGZH05/nJ9qzxtIOEPpjfXqdCnMfDNj6dhmCzWT/HH/qRXNaLkkx6soeDkJObnGLJmya7+jOLhxyv6sbJp3yoO0sV+ZYTc2c+MkU8sxLf9cQKBgFjXrGJUesBmVN0j+beBNS4MG9XE0hYnbrqK3Y7x1fdckqmQX8bpIJUK7r8GfgWKs7B9zarFeD+mY5A9fHAAauIlp22HjkQG08L9LYjNT62e7SqmgWpFjZX/3sxMWQNWQRsNT1A/7yi9mBVRBVHcRidlaVoAYNevdCcTMbojwJgxAoGBAI3MbwoNpUiN8rvl4nosYpG98B9NFJLk6YcobfZWquS8JLeGNm1iTH87l9YlX47FBho6FPuYuzNSKjJwFtKqoaPL2UMiD/Xwx73xXEQHNhb5QW8tPVGXA57zKH1GJjZRyq8ksbuMLv1yIbtXfH5x122i+jUVScXgLMjqn3Wwlq0j";
    // API网关公钥(变量值需替换)
    protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

    
    public void test_cop() throws Exception {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        GyjrB2bFinanceDigitalCreditApplyLoanRequestV1 request = new GyjrB2bFinanceDigitalCreditApplyLoanRequestV1();
        request.setServiceUrl("http://ip:port/api/gyjr/b2b/finance/digitalcredit/applyloan/V1");
        GyjrB2bFinanceDigitalCreditApplyLoanRequestV1.GyjrB2bFinanceDigitalCreditApplyLoanRequestV1Biz bizContent = new GyjrB2bFinanceDigitalCreditApplyLoanRequestV1.GyjrB2bFinanceDigitalCreditApplyLoanRequestV1Biz();
        GyjrB2bFinanceDigitalCreditApplyLoanRequestV1.GyjrB2bFinanceDigitalCreditApplyLoanRequestV1Biz.TransInfo transInfo = new GyjrB2bFinanceDigitalCreditApplyLoanRequestV1.GyjrB2bFinanceDigitalCreditApplyLoanRequestV1Biz.TransInfo();
        transInfo.setAppId("10000000000000314022");
        transInfo.setApply_money("5200000");
        transInfo.setCon_start_page("1");
        transInfo.setCon_end_page("14");
        transInfo.setGrant_start_page("15");
        transInfo.setGrant_end_page("15");
        transInfo.setBegin_date("20220228");
        transInfo.setCert_code("72749873-5");
        transInfo.setCert_type("3");
        transInfo.setConfirm_type("1");
        transInfo.setContractFlag("1");
        transInfo.setCreateDate("2022-02-28");
        transInfo.setCreateTime("1646048472824");
        transInfo.setCredit_voucher("53");
        transInfo.setCurrency("001");
        transInfo.setCertiID("BYD0003.c.0200");
        transInfo.setCustCis("020090002983751");
        transInfo.setCustFlag("1");
        transInfo.setCustomer_name("乐慧咸敲睹看（必蚊）乓忘嘴易");
        transInfo.setCustomer_numb("BYD0003");
        transInfo.setDraw_acc_name("乐慧咸敲睹看（必蚊）乓忘嘴易");
        transInfo.setEnd_date("20221227");
        transInfo.setEnterprise_name("饮技豆猛号午乓忘嘴易");
        transInfo.setEnterprise_no("BYD0001");
        transInfo.setErrorCode("0");
        transInfo.setErrorMsg("success");

        GyjrB2bFinanceDigitalCreditApplyLoanRequestV1.GyjrB2bFinanceDigitalCreditApplyLoanRequestV1Biz.TransInfo.FcreditOpenInfo fcreditOpenInfo = new GyjrB2bFinanceDigitalCreditApplyLoanRequestV1.GyjrB2bFinanceDigitalCreditApplyLoanRequestV1Biz.TransInfo.FcreditOpenInfo();
        fcreditOpenInfo.setEffect_date("20200720");
        fcreditOpenInfo.setPay_date("20221227");
        fcreditOpenInfo.setEcredit_num("D202112231414-004");
        fcreditOpenInfo.setCurrency("001");
        fcreditOpenInfo.setProject_code("55099766816752691296");
        fcreditOpenInfo.setEcredit_amt("22499996");
        fcreditOpenInfo.setCertificate_type("53");
        fcreditOpenInfo.setEcredit_ent_name("乐慧咸敲睹看（必蚊）乓忘嘴易");
        fcreditOpenInfo.setEcredit_ent_numb("BYD0001");
        fcreditOpenInfo.setScene_type("01");
        fcreditOpenInfo.setPayer_bank_account("0200003409066088890");
        fcreditOpenInfo.setPayer_bank_name("饮技豆猛号午乓忘嘴易");
        transInfo.setFcredit_open_info(fcreditOpenInfo);

        List<GyjrB2bFinanceDigitalCreditApplyLoanRequestV1.GyjrB2bFinanceDigitalCreditApplyLoanRequestV1Biz.TransInfo.Ecredit> ecredit_list = new ArrayList<>();
        GyjrB2bFinanceDigitalCreditApplyLoanRequestV1.GyjrB2bFinanceDigitalCreditApplyLoanRequestV1Biz.TransInfo.Ecredit ecredit1 = new GyjrB2bFinanceDigitalCreditApplyLoanRequestV1.GyjrB2bFinanceDigitalCreditApplyLoanRequestV1Biz.TransInfo.Ecredit();
        ecredit1.setEcredit_ent_taxID("37890406-0");
        ecredit1.setPay_date("20211227");
        ecredit1.setEcredit_sign_date("20200721");
        ecredit1.setTransf_date("20200721");
        ecredit1.setCerti_type("");
        ecredit1.setEcredit_text("");
        ecredit1.setCerti_numb("");
        ecredit1.setTrade_info_numb("D202112231414-004");
        ecredit1.setEcredit_ent_numb("BYD0001");
        ecredit1.setNext_ecredit_ent_numb("");
        ecredit1.setEcredit_money("2249999600");
        ecredit1.setFlow_levels("0");
        ecredit1.setPrev_ecredit_numb("-1");
        ecredit1.setEcredit_sign("");
        ecredit1.setSerial_no("1");
        ecredit1.setEcredit_numb("D202112231414-004");
        ecredit1.setEcredit_ent_name("饮烈颂用（必蚊）歹颂褪辛乓忘嘴易");
        ecredit1.setNext_ecredit_ent_name("");
        GyjrB2bFinanceDigitalCreditApplyLoanRequestV1.GyjrB2bFinanceDigitalCreditApplyLoanRequestV1Biz.TransInfo.Ecredit ecredit2 = new GyjrB2bFinanceDigitalCreditApplyLoanRequestV1.GyjrB2bFinanceDigitalCreditApplyLoanRequestV1Biz.TransInfo.Ecredit();
        ecredit2.setEcredit_ent_taxID("37890406-0");
        ecredit2.setPay_date("20211227");
        ecredit2.setEcredit_sign_date("20200721");
        ecredit2.setTransf_date("20200721");
        ecredit2.setCerti_type("");
        ecredit2.setEcredit_text("");
        ecredit2.setCerti_numb("");
        ecredit2.setTrade_info_numb("D202112231414-004-1");
        ecredit2.setEcredit_ent_numb("BYD0003");
        ecredit2.setNext_ecredit_ent_numb("");
        ecredit2.setEcredit_money("5200000");
        ecredit2.setFlow_levels("0");
        ecredit2.setPrev_ecredit_numb("D202112231414-004");
        ecredit2.setEcredit_sign("");
        ecredit2.setSerial_no("2");
        ecredit2.setEcredit_numb("D202112231414-004-1");
        ecredit2.setEcredit_ent_name("饮烈颂用（必蚊）歹颂褪辛乓忘嘴易");
        ecredit2.setNext_ecredit_ent_name("");
        ecredit_list.add(ecredit1);
        ecredit_list.add(ecredit2);
        transInfo.setEcredit_list(ecredit_list);

        transInfo.setLoan_insno("IFSAAA16462057936244976");
        transInfo.setFcredit_open_mode("2");
        transInfo.setFilepath("usr/enterprise/byd");
        transInfo.setGroup_id("020090002983751");
        transInfo.setInterest_mode("01");
        transInfo.setIs_secret_retated("0");
        transInfo.setLastUpdateContainerId("ed3bbdd73af3d386bdb46959fdbc0b1b6d79c3e55c3cc5a401a3edaa6524f076");
        transInfo.setLastUpdateContainerIp("ip:port");
        transInfo.setLastUpdateOper("BYD0003");
        transInfo.setLoan_account("D202112231414-004");
        transInfo.setLoanProgress("01");
        transInfo.setLstUpDate("2022-02-28");
        transInfo.setLstUpTime("19:41:12");
        transInfo.setModKey("0");
        transInfo.setNo_limit_pay("1");
        transInfo.setNotifyFailNum("0");
        transInfo.setNotifyResult("0");
        transInfo.setNotifyUrl("1646048470627");
        transInfo.setOpenStatus("1");
        transInfo.setpMonth("0002");
        transInfo.setPlat_charge("0");
        transInfo.setPlat_charge_rate("0");
        transInfo.setPlat_loan_insno("DS-21121736363406675580003");
        transInfo.setPlatform_no("PF0053");
        transInfo.setProduct_type("01");
        transInfo.setProtocol_code("GYJR20211016001");
        transInfo.setRepayment("01");
        transInfo.setScene_code("01");
        transInfo.setThreadRetryTimes("0");

        List<GyjrB2bFinanceDigitalCreditApplyLoanRequestV1.GyjrB2bFinanceDigitalCreditApplyLoanRequestV1Biz.TransInfo.TradeInfoList> trade_info_list = new ArrayList<>();
        GyjrB2bFinanceDigitalCreditApplyLoanRequestV1.GyjrB2bFinanceDigitalCreditApplyLoanRequestV1Biz.TransInfo.TradeInfoList tradeInfoList = new GyjrB2bFinanceDigitalCreditApplyLoanRequestV1.GyjrB2bFinanceDigitalCreditApplyLoanRequestV1Biz.TransInfo.TradeInfoList();
        tradeInfoList.setTrade_info_money("5200000");
        tradeInfoList.setContract_image_name("");
        tradeInfoList.setTrade_info_numb("D202112231414-004-1");
        trade_info_list.add(tradeInfoList);
        transInfo.setTrade_info_list(trade_info_list);

        transInfo.setTxtNames("sdf");
        transInfo.setU_keyid("4900005366");
        transInfo.setNotify_url("sdfsdga");
        transInfo.setBillImage_names("sdfsdtert");
        transInfo.setPubkey("ewer");
        transInfo.setSign("serert");
        transInfo.setU_public_key("sdf");

        bizContent.setTransInfo(transInfo);
        request.setBizContent(bizContent);
        GyjrB2bFinanceDigitalCreditApplyLoanResponseV1 response;
        try {
            System.out.println(JSONObject.toJSONString(request));
            response = client.execute(request);
            System.out.println(JSONObject.toJSONString(response));
            String returnContent = response.getReturnContent();
            JSONObject jsonObject = JSONObject.parseObject(returnContent);
            System.out.println(jsonObject.get("loan_progress"));
            System.out.println(jsonObject.get("error_msg"));
            System.out.println(jsonObject.get("error_code"));
            System.out.println(jsonObject.get("open_status"));
            if (response.isSuccess()) {
                // 业务成功处理
                System.out.println(response.getReturnCode());
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
