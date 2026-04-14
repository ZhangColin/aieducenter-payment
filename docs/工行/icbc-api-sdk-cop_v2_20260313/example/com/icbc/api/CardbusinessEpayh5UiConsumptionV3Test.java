package com.icbc.api;

import static org.junit.Assert.assertEquals;
import java.util.logging.Logger;
import org.junit.Test;
import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.internal.util.internal.util.fastjson.JSONArray;
import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;

import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcConstants;
import com.icbc.api.UiIcbcClient;
import com.icbc.api.request.CardbusinessEpayh5UiConsumptionRequestV3;
import com.icbc.api.request.CardbusinessEpayh5UiConsumptionRequestV3.CardbusinessEpayh5UiConsumptionRequestV3Biz;
import com.icbc.api.utils.IcbcEncrypt;

public class CardbusinessEpayh5UiConsumptionV3Test {

    // 1、网关公钥
    protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
    // 2、密钥对认证方式，公钥在API平台登记，此处为私钥
    protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDfV8piJL/5Pc/ZdCMBcX1mVrLQo6rRBdqLOnYkU9xnVp1EdhMkx1wcP1BDWTo0SqupMFwZlsTH5t6ywdlD4sXvkyfCkuSSShg+ZGGLRKmDPoLOEP1KZ/SFwnIGb6hj30OXyhsmArVYfYr7qAlo0GqfQrDUAC2ZbasESLHlVvqMy48ssp2QcOPtZoWW/diQY1HJR+RMS9Kjg9oZr/dU5UloZQsDQVXmYBuAqU4RcBfEqV56dFu/suJkGrw0LenDPxI2QLJ5c51rkhx9lC7xBZigfWOnDLH6xoT1ChdcY9bP5xt/GjP5NYuI8xI2sGGTUPoUKxdrH6aiI9jp728+K1y5AgMBAAECggEAbJQsktwU7GHti2UXo5r+AOPDWQVIhQfYgHlyeCTA8Qg9usvAcM/u6tio96UIU+W9YKpfDB2tGxYVTEhLjOJRojAjU0fAkZIuCR8aAO/njSO1yeKekS7KxMCMWK6t6afgH4ok+qy0ZwnZqJC/ylIQk86DUv2nLYEQdCu3OKy5b/qZ1qA7yaCiG/D4HBThgiOifV2Yq1TCtvC2iv5mcuhH4iTXexeOQcbZepZlQnyXiVAlTYRAeo+ng8ub01NJZ4njPe9boKeqrpmMAOLN/gRTjh6yZ+90+hniXLgznOVPg+GxUbff8pVDd01POGVsid0f5Gr/TvEnixJV9nM70SCp0QKBgQD1C/oCC0mC9T8yZrrzkKZ5gsWpxElGYFvU/S1LdDsfGioRLLBJ8k4PvQcJN+pB1Ea2b8s01HQKWarXGYKQmu+dGsULbv9UpaVwH3Of+gt35Wo2+Fuh0bhcS58Ct41IGQh5leI5ckNq9iB9/x6zWQFeAEpUnXqIwFYkNCZIPbgmRQKBgQDpU3YAvKXDCesL9W4JhoWhyGBJ94frOq8hiH3vbr1xUpqDkJ9aovMDWy5f77E5Vuva/mEDxIpQrFTSA4clKj6T8E6CBiEMStP2DWLQsyC3AxDKv3g5lXab3IH4KtxjNCwadp+TRmRHWG09FLdt14AeS4El14xdhlGx6FsYncst5QKBgFBGHR9gTTOeXZaIOsQhZbe2lEQZ7hsk49BxI85tBBUbQB6iMhn3S4UyWkS10YLBJG0NUFc9JcpiN2oBjFkMuGQR6ezl7rTvErQZSYploi4jtFjPoUzwY+GwUCXWtWyh7rnN1O8WtGksudYspgUAqkb991uivwpfX5i6kLPnrBS1AoGALe8WXhLFd14ufc41eX6YND9kZWtrwK1u6OUcFdTxSqv+a0Q/evJ1cQW0XYKsmyM3j4dgxgMdT8B9elLjejeU1j8K1aIrQ2Y/0ELWX0vEdwMNfTywiHWaQhjpJVgaxxTwUc1koPPMrhcEem/npKI2QMCQjkifA5J75tBdjr0R0NkCgYEA1eUVZW1zEXB79xf2GREbPi1UeQVfIvTqOQK8fa3O0Xdrdd//BFHy44eqSrg5eG0t78wbFtkwYHUIbQZOd0L9qp6yPIk2bqldKoqUxiXPjGX4QR1XgenbWjc+cLr//EN2zRqTLrd3K2e0V/Hx+6cL14/0DB73Ma7oyZ6rMKR2JYU=";
    // 3、appid
    protected static final String APP_ID = "xxxxxxxxx";
    // ENCRYPT_TYPE_AES:通过工具生成
    protected static final String ENCRYPT_AES_KEY = "ULgn+LpGG+JMNy+aFbGPZg==";
    private Logger logger = Logger.getLogger(CardbusinessEpayh5UiConsumptionV3Test.class.getName());


    public void test_cop() throws IcbcApiException {
        CardbusinessEpayh5UiConsumptionRequestV3 request = new CardbusinessEpayh5UiConsumptionRequestV3();

        // 4、需传入appid，私钥，字符集
        // 是否使用AES加密请求数据
        //request.setNeedEncrypt(false);
        UiIcbcClient client ;
        if (request.isNeedEncrypt()) {
            client = new UiIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8, IcbcConstants.ENCRYPT_TYPE_AES, ENCRYPT_AES_KEY);
        } else {
            client = new UiIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY,  IcbcConstants.CHARSET_UTF8);
        }
        // 5、根据测试环境和生产环境替换相应ip和端口
        request.setServiceUrl("https://ip:port/ui/cardbusiness/epayh5/ui/consumption/V3");
        CardbusinessEpayh5UiConsumptionRequestV3Biz bizContent = new CardbusinessEpayh5UiConsumptionRequestV3Biz();
        request.setBizContent(bizContent);
        // 6、请对照接口文档用bizContent.setxxx()方法对业务上送数据进行赋值
        bizContent.setIcbc_appid("10000000000004095503");
        bizContent.setOrder_date("20201001165613");
        bizContent.setOrder_id("NewPay20200528165639");
        bizContent.setSer_id("43125321543");
        bizContent.setIs_suborder("1");
        bizContent.setOrder_num("1");
        JSONObject orderSubmitActInput = JSON.parseObject("{\"rec_num\":\"1\",\"oper_flag\":\"0\",\"sub_order_id\":\"55555\",\"seq_no\":\"020001030604000521912200000001\",\"busi_type\":\"2\",\"sub_mer_id\":\"***\",\"sub_mer_prtcl_no\":\"***\",\"classify_amt\":\"1\",\"mer_sp_infor\":\"商户自定义信息\",\"term_id\":\"1\",\"ori_mer_id\":\"***\",\"ori_trx_date\":\"2020-01-02\",\"mer_acct\":\"***\"}");
        JSONArray orderSubmitActInputArray = new JSONArray();
        orderSubmitActInputArray.add(orderSubmitActInput);
        bizContent.setOrder_submit_act_input(orderSubmitActInputArray);
        bizContent.setAmount("1");
        bizContent.setInstallment_times("1");
        bizContent.setCur_type("001");
        bizContent.setExternal_app_id("0");
        bizContent.setMer_id("020053010625");
        bizContent.setMer_prtcl_no("0200530106250201");
        bizContent.setMerchant_info("");
        bizContent.setLanguage("zh_CN");
        bizContent.setGoods_id("");
        bizContent.setGoods_name("哇哈哈");
        bizContent.setGoods_num("");
        bizContent.setCarriage_amt("");
        bizContent.setMer_hint("");
        bizContent.setMerURL("http:\\/\\/acq2020kfacq.dccnet.com.cn\\/servlet\\/tool\\/email");
        bizContent.setMer_var("");
        bizContent.setReturn_url("http:\\/\\/acq2020kfacq.dccnet.com\\/apiTest.html");
        bizContent.setAuto_refer_sec("");
        bizContent.setWallet_flag("0");
        bizContent.setCust_id("0");
        bizContent.setMobile("13012341118");
        bizContent.setCredit_type("2");
        bizContent.setNotify_type("HS");
        bizContent.setResult_type("0");
        bizContent.setH5_flag("0");
        bizContent.setCheck_flag("0");
        bizContent.setCust_name("彭先生");
        bizContent.setCust_cert_type("0");
        bizContent.setCust_cert_no("110101198506029980");
        bizContent.setAllocate_cardno("");
        bizContent.setAccess_type("4");
        bizContent.setBackup1("");
        bizContent.setRemark1("");
        bizContent.setRemark2("");
        bizContent.setOrder_apd_inf("霍比特人3");
        bizContent.setOrder_interval("");
        bizContent.setBack_shop_url("");
        bizContent.setBack_shop_url_sdk("xxx");

        try {
            boolean testFlag = true;
            logger.info(client.buildPostForm(request));
            assertEquals(true, testFlag);
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }

}
