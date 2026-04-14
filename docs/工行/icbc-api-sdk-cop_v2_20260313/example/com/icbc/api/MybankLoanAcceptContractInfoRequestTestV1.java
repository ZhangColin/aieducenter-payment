package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.MybankLoanAcceptContractInfoRequestV1;
import com.icbc.api.request.MybankLoanAcceptContractInfoRequestV1Biz;
import com.icbc.api.response.MybankLoanAcceptContractInfoResponseV1;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Title :
 * @Description :
 * @Author : kfzx-zhengld
 * @Date : 2021/6/21 11:24
 * @Version : 1.0
 */

public class MybankLoanAcceptContractInfoRequestTestV1 {

    protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCRVgmyC7tV4L4dC5B/Hx/EIJFQwdFN6aB8Ia3AxhGgC/hAKfMohR7qDk0ZusNw1Af0ov7APl3tpz0U25+4aLGvy03Bu/eZHFK1feFxakZJtpGER6wm2EAd83cLoq15diIaIzOo8zgFuTbQu2TlIc4tDtr5ZffEe18eTD8asirZ8rtgqYmHq51cDmvBrHoWKHewkrU0fVjFdjiPglu0xg4PT4SHNYe29vWEkDnKg07YKF+t5u3EOyL3GrCQEFjJx87cWUVmGNehRtyT7qjNyyNiFXx4yngL7/WFc1ieMnPVzzOeHlLcRl99q22diO/Lv+i+E8oTzlzg+A233kV+jZtjAgMBAAECggEAT9xjP37K1gnE7djo8bF8lxz+PM4D4Tg7g3pOK9uq27M4Gqnyf5WGwz5Qh47kzh70sCzQp3qdynVXTxrPzVlaKisg8xACohO6LxiYxOWM8fxsQa/QnRe/Q7YA+FfP0ypi2tH1FSVw51Q7Fr+iVXYaARBkqx6o40KXpeJn2peEjHXM5SlZE0JKdeShfzvFzJVBClYLhGU7Zmtka8kFmBNCM3CdReH0lwyuSxcxfn4xq3wUqI6+0PU1qKrBsbRrYd3RIDvCrQ1kFQHiwwGgQ6I557KgIM1N+d6KvQQSDGlw36Q2JBqcbGJDGOUsIbfQaGKVqayttUXffLApcvnnjeAt4QKBgQDJ1ZEjJzr9DFjdqwVAvbRZFpxjEsONhUxQZFteJFGloLMqKPKDMzsQyruWBTPx+f0y/da0oBGCq1Uy6Fzbfdonq9WiZHoUy2mSMmNJdfNqN/fTM6uSAnP+FYy+EQ2hTIBEX4EY51cAQhcYuC4i3QL66QXcitKmBFiyTheK8SMzTwKBgQC4Vu38yxqgbH3pggO+ebqu/0EI5PR07K/t3IT1T3JCVqYEDOLrFio9cdKd32pf9JAeH9+3tRhuzkj1FCMWxbTut6GAT1UbQEhSR5SedbXKGRukwL7pK7dc5W5UmQ/MwaFa1xsE2O+aaXFvQ1UnwJ+npyTLQdBCoUC1166ZDr1hrQKBgQCcWzhOdUu7UN9fCefN2ahnA58vGRApCESwWq51Zclid/WUCZiljwCI56UeePVe1X0TDZZQ41ftVHFyOJQG7Z4c8X9UiGYSyeoE+cf01s01UhpIoVSejgvxoBwh+fAHLs6HKT1HX6AEcS6XVcdX9sMiQXbq/RhmyCM2Lk0Ks/fC/QKBgQCfvfUOE5NHYbzbFWfZL/OuolPhOUb96/2oymgNjIMfKadD60bZIaQJCxHQ7UJpVK1u7YAY2j4KB4kBmCmwGjPoigjR/+1oaBRD+PO5PZgDQi7KXp0RwmhrgAZUGS+v6DvDVJR9ZuB7ltRN7rNkd2/jANzJpoaqtsI00mrjB/rlIQKBgCp3oFsq4n6i91B0m5zhq6wBSQEoHq0MFh2p1F+UJgRdwUcmaesTOTJqIMHm/ASLIgHPsPzZldw4qNTOFxYFbp5ARqeIIgS5Bq4xn0vN+2cKssI2lmAZ3ksg6Aw/OPvnxKCobt9LsaiJY0Fz5mcoUsmZfPb8v5+2Mgkt8sFTDu8D";

    protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

    protected static final String APP_ID = "10000000000000005530";


    public void test_cop() throws Exception {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,"RSA", MY_PRIVATE_KEY,"UTF-8",
                "json",APIGW_PUBLIC_KEY,"AES","G242nUlMmiT1tcToX29xkg==", "", "");
        MybankLoanAcceptContractInfoRequestV1 request = new MybankLoanAcceptContractInfoRequestV1();
        request.setServiceUrl("http://ip:port/api/gyjr/b2b/finance/digitalcredit/querycontractinfo/V1");
        MybankLoanAcceptContractInfoRequestV1Biz bizContent = new MybankLoanAcceptContractInfoRequestV1Biz();
        bizContent.setCus_cis("bizContent");
        bizContent.setBusi_type("200");
        MybankLoanAcceptContractInfoRequestV1Biz.InputElements inputElements = new MybankLoanAcceptContractInfoRequestV1Biz.InputElements();
        inputElements.setPlatform_no("PF0053");
        inputElements.setProtocol_code("10550000000002902000");
        inputElements.setScene_code("10550000000002905001");
        inputElements.setCustomer_numb("10550000000002902000");
        inputElements.setCustomer_name("委耽芒垮蹲氛栓殷切绥");
        inputElements.setEnterprise_no("020090001926823");
        inputElements.setEnterprise_name("1");
        inputElements.setCredit_voucher("53");
        inputElements.setConfirm_type("1");
        inputElements.setCurrency("001");
        inputElements.setApply_amount("10000");
        inputElements.setApply_date("20200621");
        inputElements.setDue_date("20200721");
        inputElements.setDraw_acc("0200041709213281068");
        inputElements.setDraw_acc_name("委耽芒垮蹲氛栓殷切绥");
        inputElements.setCustomer_name("似懂非懂");
        inputElements.setCert_type("3");
        inputElements.setCert_code("130527600144056");
        inputElements.setGroup_cis("020090000206423");
        inputElements.setGroup_id("102054341AAAAAA");
        inputElements.setGroup_name("委耽芒垮蹲氛栓殷切绥");
        inputElements.setCertiID("sunlele01.c.0200");
        inputElements.setU_keyid("4990002224");

        MybankLoanAcceptContractInfoRequestV1Biz.TradeInfo tradeInfo = new MybankLoanAcceptContractInfoRequestV1Biz.TradeInfo();
        List<MybankLoanAcceptContractInfoRequestV1Biz.TradeInfo> tradeInfoList = new ArrayList<MybankLoanAcceptContractInfoRequestV1Biz.TradeInfo>();
        tradeInfo.setTrade_info_numb("dxptest202106150001e");
        tradeInfo.setTrade_info_money("44500");
        tradeInfo.setSecret_file_no("12321142");
        tradeInfo.setIs_secret("0");
        tradeInfo.setSecret_agreement_no("水电费水电费");
        tradeInfoList.add(tradeInfo);

        MybankLoanAcceptContractInfoRequestV1Biz.BillInfo billInfo = new MybankLoanAcceptContractInfoRequestV1Biz.BillInfo();
        List<MybankLoanAcceptContractInfoRequestV1Biz.BillInfo> billInfoList = new ArrayList<>();
        billInfo.setTrade_info_numb("dxptest202106150001e");
        billInfo.setSerial_no("123123123");
        billInfo.setBill_code("1");
        billInfo.setBill_kind("1");
        billInfo.setBill_numb("1");
        billInfo.setBill_money("1");
        billInfo.setBill_date("1");
        billInfo.setBill_currency("1");
        billInfo.setBill_type("1");
        billInfo.setBill_checkcode6("1");
        billInfo.setContract_num("1");
        billInfoList.add(billInfo);



        MybankLoanAcceptContractInfoRequestV1Biz.Ecredit ecredit = new MybankLoanAcceptContractInfoRequestV1Biz.Ecredit();
        List<MybankLoanAcceptContractInfoRequestV1Biz.Ecredit> ecreditList = new ArrayList<>();
        ecredit.setTrade_info_numb("dxptest202106150001e");
        ecredit.setSerial_no("1");
        ecredit.setFlow_levels("0");
        ecredit.setPrev_ecredit_numb("-1");
        ecredit.setEcredit_numb("dxptest202106150001e1");
        ecredit.setEcredit_money("8");
        ecredit.setEcredit_ent_numb("020090001926823");
        ecredit.setEcredit_ent_name("燕燕网银数据1");
        ecredit.setNext_ecredit_ent_numb("020090001926823");
        ecredit.setNext_ecredit_ent_name("让他今天已经");
        ecredit.setEcredit_ent_taxID("20180520");
        ecredit.setPay_date("20230618");
        ecredit.setTransf_date("20220331083036");
        ecredit.setEcredit_text("dxpText001");
        ecredit.setEcredit_sign("dxpEcreditSign001");
        ecredit.setEcredit_sign_date("20220331083036");
        ecredit.setCerti_type("ICBC");
        ecredit.setCerti_numb("20180520232");
        ecreditList.add(ecredit);



        MybankLoanAcceptContractInfoRequestV1Biz.Contract contract = new MybankLoanAcceptContractInfoRequestV1Biz.Contract();
        List<MybankLoanAcceptContractInfoRequestV1Biz.Contract> contractList = new ArrayList<>();
        contract.setTrade_info_no("dxptest202106150001e");
        contract.setContract_num("1");
        contract.setContract_name("1");
        contract.setContract_amt("1");
        contract.setContract_cur("1");
        contract.setTrade_content("1");
        contract.setEffect_date("1");
        contract.setPay_date("1");
        contract.setPayee_name("1");
        contract.setPayer_name("1");
        contractList.add(contract);
        inputElements.setTrade_info_list(tradeInfoList);
        inputElements.setBill_info_list(billInfoList);
        inputElements.setEcredit_list(ecreditList);
        inputElements.setContract_list(contractList);
        bizContent.setInput_elements(inputElements);
        request.setBizContent(bizContent);
        MybankLoanAcceptContractInfoResponseV1 response;
        try {
            response = client.execute(request, System.currentTimeMillis()+"");
            System.out.println(JSONObject.toJSONString(response));
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
