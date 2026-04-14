package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.MybankLoanApplyRequestV1;
import com.icbc.api.request.MybankLoanApplyRequestV1Biz;
import com.icbc.api.response.MybankLoanApplyResponseV1;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Title :
 * @Description :
 * @Author : kfzx-zhengld
 * @Date : 2021/6/21 16:50
 * @Version : 1.0
 */

public class MybankLoanApplyRequestTestV1 {
    protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCz013iKd5Aa+2cqMrC+UHdAVwuuFPbF2cNICTIP04Of7nyjHz11BEdxcFK1GtACYy8bwpa9aHWKDe5SfA5PGvMgKtX8Jxp7X2myCCgFYBp3UzVHPQbglX0HN+l9iDk9pER+bvooC4HXS3u/l/aAyLQUmf18goZ20LtZ3TIO1QGRMPkE0uFcY36IB+K+YTdwvx9XV95BS0Gib+DPCLlSRt5eS7S9WU+/AeUg3dDBS0n9nlvPwoDMD+L7i4aBPQ4M5d86N3tuCi51JpPslAgf+kFG+cHN4R0zAsfDBo7gmoKKN98Srhdcq7CJz+TLcF9/rOHEQeUY4+dUdfe0pknTvgFAgMBAAECggEAef4udrvIlxOhAuJPi63lTLUbgGZ0HPHnazQfP+oSXiuwTieYrnzLj9MUqlso1yyjWUJLWCHDRLHL+9t5KJsg1n+DvgjxU7tVvsuNtoxfrXIAKaOWxwLIpO7hmok7D5lmGTe+jrX8A+rmune/NvthpsoA1Qz6ZUgmQ0AoLZ5yBj2EW4ddX4pq7w/HgFcTjj2DEyBj25bxrr80rXQuZGd7mrkrwrBIqSPU4RL+5I07TiEKyyA2jV09iQmCNjHYmdPFqHW0jRVAEYvMz+RLK4BK/hpNtIA+9ntM59MmPzpF6xXEHdW+zQwGakalTknu8Kj8qzW1KUzmBqcIrQ8SiuJ/AQKBgQDnxrJpYmm7i79cRkdgw482XKZqJCZB3Qcu7ZPWPGIjkCy9guptm3VpiFPHv7ImCskY5B98BNWBPtDAS5fnnM0qvKnDL08aO7/ghOe4whf+JA/Kb6w5jmWIpUTeWhC/NHQ8Xlm0jTwrLC+dS2kvN1N0Y9XIhZ6SLyn/VYeTOaxftQKBgQDGnrRSp3ck5meicnfwnk6q7GOidnVPUX0EdFkpn/R89EHhM9/g2hvUZdPJ0WzHQAXHqA6d1JlICsnZsI2fk2IBcZ5/N1FTu8LhM5p1vkp9yTmOB/mMr8Fxe7CJF9HF47X6Rakss7THN6U6YL0l5VYc6Tkr2tITc4/ijf42xIpJEQKBgHW8ZmbIGiOt4oAyJxINZRNdTx+9yz1L1NlOz/lU5RlhpIiEFyekf6kjGkPuqcVPV0Xwo9mw7u/fg9Ua0UhTS5k5CxdeUdVmopqfiMI+vkdX9DmGLrtyDTuzkF9O6xDlyWHdH75qvDTGFk3Vv9G/TgJisHdTAPELSk8tGFh+RIS9AoGBALROQlh+WjSPTSk1TRiMPdIAgnprmxcEXL98d6zH3oTIoD22f9h5Z6YrQlYF3gALaLPVJl+1LekukgsisY5ZlzDEWiQWraeb3c/erquQscL6t1rudmZ27t78cEgj7XT9wKeQMYG5KbVoGICkbVFHniLkvdfH78lksQvz5wEE7pxhAoGAdayDAZt2KrkAoDe94H3AcIETlUC0jOCaTcp+J/V14G/LAM9AAp8Ach+FJ1/0lw2ULUw/+Aouqq4uHr+SW09pmvyDzd7XZcjNu6iCnuF/7oJzEVf4kb/xMb36HWL8kq+DzWYbpkPzGheFL5xpWdfyvZiBp6adg7gKMcEy+B42ph4=";

    protected static final String APIGW_PUBLIC_KEY =
            "-----请使用正确的APP私钥-----BIjANBgkqhkiG9w0BAQEFAAOCAQ8A-----请使用正确的APP私钥-----BCgKCAQEAs9Nd4ineQGvtnKjKwvlB3QFcLrhT2xdnDSAkyD9ODn+58ox89dQRHcXBStRrQAmMvG8KWvWh1ig3uUnwOTxrzICrV/Ccae19psggoBWAad1M1Rz0G4JV9BzfpfYg5PaREfm76KAuB10t7v5f2gMi0FJn9fIKGdtC7Wd0yDtUBkTD5BNLhXGN+iAfivmE3cL8fV1feQUtBom/gzwi5UkbeXku0vVlPvwHlIN3QwUtJ/Z5bz8KAzA/i+4uGgT0ODOXfOjd7bgoudSaT7JQIH/pBRvnBzeEdMwLHwwaO4JqCijffEq4XXKuwic/ky3Bff6zhxEHlGOPnVHX3tKZJ074BQIDAQAB";

    protected static final String APP_ID = "10000000000004097459";


    public void test_cop() throws Exception {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID,"RSA", MY_PRIVATE_KEY,"UTF-8",
                "json",APIGW_PUBLIC_KEY,"","", "", "");
        MybankLoanApplyRequestV1 request = new MybankLoanApplyRequestV1();
        request.setServiceUrl("http://ip:port/api/gyjr/b2b/finance/digitalcredit/applyloan/V1");
        MybankLoanApplyRequestV1Biz bizContent = new MybankLoanApplyRequestV1Biz();
        bizContent.setPlatform_no("PF0053");
        bizContent.setProtocol_code("10550000000002902000");
        bizContent.setScene_code("10550000000002905001");
        bizContent.setProduct_type("01");
        bizContent.setCustomer_numb("020090000206423");
        bizContent.setCustomer_name("固特异轮胎橡胶制品有限公司");
        bizContent.setEnterprise_no("020090001926823");
        bizContent.setEnterprise_name("比亚迪股份有限公司");
        bizContent.setApply_money("10000");
        bizContent.setPlat_loan_insno("56991005699276280799");
        bizContent.setIs_secret_retated("0");
        bizContent.setCredit_voucher("01");
        bizContent.setConfirm_type("1");
        bizContent.setNo_limit_pay("1");
        bizContent.setBegin_date("20210618");
        bizContent.setEnd_date("20230805");
        bizContent.setCurrency("001");
        bizContent.setInterest_mode("01");
        bizContent.setRepayment("01");
        bizContent.setPlat_charge_rate("0.0003");
        bizContent.setPlat_charge("240000");
        bizContent.setDeal_term("36");
        bizContent.setDeal_vitality("C");
        bizContent.setDeal_money_rank_rate("0.005");
        bizContent.setDispute_rate("0.00001");
        bizContent.setEvaluation("C");
        bizContent.setLoan_insno("202103111008003");
        bizContent.setLoan_account("0200041709213281068");
        bizContent.setCert_type("3");
        bizContent.setCert_code("130527600144056");
        bizContent.setDraw_acc_name("固特异轮胎橡胶制品有限公司");
        bizContent.setGroup_cis("020090000206423");
        bizContent.setGroup_id("102054341AAAAAA");
        bizContent.setGroup_name("委耽芒垮蹲氛栓殷切绥");
        bizContent.setCertiID("102054341AAAAAA");
        bizContent.setU_keyid("4990002224");
        bizContent.setU_public_key("bfasdbabkaliugabgkjab-vcbewaefgbebgabwbevabeagabgva");
        bizContent.setFcredit_open_mode("2");
        bizContent.setFcredit_numb("123456789");
        bizContent.setBillImage_names("凭证影像文件名");
        bizContent.setPubkey("u盾公钥");
        bizContent.setSign("签名串");
        bizContent.setFilepath("4+2文件路径");

        MybankLoanApplyRequestV1Biz.TradeInfoListInfo tradeInfoListInfo = new MybankLoanApplyRequestV1Biz.TradeInfoListInfo();
        List<MybankLoanApplyRequestV1Biz.TradeInfoListInfo> tradeInfoListInfoList = new ArrayList<>();
        tradeInfoListInfo.setTrade_info_numb("55097668167526991295");
        tradeInfoListInfo.setTrade_info_money("200000000");
        tradeInfoListInfo.setContract_image_name("bydheguteyidediyigehetong.html");
        tradeInfoListInfoList.add(tradeInfoListInfo);
        bizContent.setTrade_info_list(tradeInfoListInfoList);

        MybankLoanApplyRequestV1Biz.FcreditOpenInfo fcreditOpenInfo = new MybankLoanApplyRequestV1Biz.FcreditOpenInfo();
        fcreditOpenInfo.setEffect_date("20210618");
        fcreditOpenInfo.setScene_type("01");
        fcreditOpenInfo.setProject_code("55099766816752691296");
        fcreditOpenInfo.setEcredit_num("55052699766816791295");
        fcreditOpenInfo.setEcredit_amt("800000000");
        fcreditOpenInfo.setCurrency("001");
        fcreditOpenInfo.setPay_date("20230618");
        fcreditOpenInfo.setPayer_bank_name("燕燕网银数据1");
        fcreditOpenInfo.setPayer_bank_account("0200000209213652642");
        fcreditOpenInfo.setCertificate_type("53");
        fcreditOpenInfo.setIs_secret("1");
        fcreditOpenInfo.setSecret_file_no("涉密情况说明影像文件编号");
        fcreditOpenInfo.setSecret_agreement_no("保密协议影像文件编号");
        fcreditOpenInfo.setHas_certificate("1");
        fcreditOpenInfo.setEcredit_ent_numb("62879972672100569927");
        fcreditOpenInfo.setEcredit_ent_name("固特异轮胎橡胶制品有限公司");
        fcreditOpenInfo.setNext_ecredit_ent_numb("62879972672100569927");
        fcreditOpenInfo.setNext_ecredit_ent_name("固特异轮胎橡胶制品有限公司");
        fcreditOpenInfo.setTransf_date("20210618");
        bizContent.setFcredit_open_info(fcreditOpenInfo);

        MybankLoanApplyRequestV1Biz.CountpartyListInfo countpartyListInfo = new MybankLoanApplyRequestV1Biz.CountpartyListInfo();
        countpartyListInfo.setCountparty_type("001");
        countpartyListInfo.setCountparty_name("固特异轮胎橡胶制品有限公司");
        countpartyListInfo.setFunds_use("买橡胶");
        countpartyListInfo.setCountparty_account("21007986907956959296");
        countpartyListInfo.setAeceive_amount("500000000");
        countpartyListInfo.setOpen_bank("广东省深圳市坪山支行");
        List<MybankLoanApplyRequestV1Biz.CountpartyListInfo> countpartyListInfoList = new ArrayList<>();
        countpartyListInfoList.add(countpartyListInfo);
        bizContent.setCountparty_list(countpartyListInfoList);


        request.setBizContent(bizContent);


        MybankLoanApplyResponseV1 response ;
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
