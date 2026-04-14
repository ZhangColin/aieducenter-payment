package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.MybankPayGyjrB2bPayapplyRequestV1;
import com.icbc.api.response.MybankPayGyjrB2bPayapplyResponseV1;

import java.util.*;
import java.util.logging.Logger;

public class MybankPayGyjrB2bPayapplyTestV1 {
    protected static final String MY_PRIVATE_KEY = "XXXXXXXX";
    protected static final String APIGW_PUBLIC_KEY = "XXXXXXXX";
    protected static final String APP_ID = "123";
    static Logger log = Logger.getLogger(MybankPayGyjrB2bPayapplyTestV1.class.getName());

    public static void main(String[] args) {

//		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        MybankPayGyjrB2bPayapplyRequestV1 request = new MybankPayGyjrB2bPayapplyRequestV1();
        request.setServiceUrl("https://IP:PORT/api/mybank/pay/gyjr/b2b/payapply/V1");

//		long timeMillis = System.currentTimeMillis();
//		String time=new SimpleDateFormat("yyyyMMddHHmmss").format(timeMillis) ;// 当前日期
        MybankPayGyjrB2bPayapplyRequestV1.MybankPayGyjrB2bPayapplyRequestV1Biz bizContent = new MybankPayGyjrB2bPayapplyRequestV1.MybankPayGyjrB2bPayapplyRequestV1Biz();
        MybankPayGyjrB2bPayapplyRequestV1.PayapplyTransInfoRequestV1Biz transInfo = new MybankPayGyjrB2bPayapplyRequestV1.PayapplyTransInfoRequestV1Biz();
        List<MybankPayGyjrB2bPayapplyRequestV1.BeanGoodsInfo> beanGoodsInfoList = new ArrayList<MybankPayGyjrB2bPayapplyRequestV1.BeanGoodsInfo>();
        MybankPayGyjrB2bPayapplyRequestV1.BeanGoodsInfo beanGoodsInfo = new MybankPayGyjrB2bPayapplyRequestV1.BeanGoodsInfo();
        beanGoodsInfo.setGoodsSubId("1");
        beanGoodsInfo.setGoodsName("倚天剑");
        beanGoodsInfo.setPayeeCompanyName("张三");
        beanGoodsInfo.setGoodsNumber("1");
        beanGoodsInfo.setGoodsAmt("10000");
        beanGoodsInfo.setGoodsUnit("单位");
        beanGoodsInfoList.add(beanGoodsInfo);

        List<MybankPayGyjrB2bPayapplyRequestV1.BeanRecvMallInfo> beanRecvMallInfoList = new ArrayList<MybankPayGyjrB2bPayapplyRequestV1.BeanRecvMallInfo>();
        MybankPayGyjrB2bPayapplyRequestV1.BeanRecvMallInfo beanRecvMallInfo = new MybankPayGyjrB2bPayapplyRequestV1.BeanRecvMallInfo();
        beanRecvMallInfo.setMallCode("0200EH0013035");
        beanRecvMallInfo.setMccCode("323451");
        beanRecvMallInfo.setMccName("1");
        beanRecvMallInfo.setBusinessLicense("1");
        beanRecvMallInfo.setBusinessLicenseType("0");
        beanRecvMallInfo.setMallName("mallName");
        beanRecvMallInfo.setPayeeCompanyName("test");
        beanRecvMallInfo.setPayeeSysflag("3");
        beanRecvMallInfo.setPayeeBankCode("");
        beanRecvMallInfo.setPayeeHeadBankCode("123456789120");
        beanRecvMallInfo.setPayeeAccno("aaa-111111");
        beanRecvMallInfo.setPayAmount("11223311");
        beanRecvMallInfo.setRbankname("afica bank");
        beanRecvMallInfo.setPayeeBankCountry("840");
        beanRecvMallInfo.setPayeeBankSign("CHASUS33XXX");
        beanRecvMallInfo.setPayeeCountry("840");
        beanRecvMallInfo.setPayeeAddress("urtyusastt");
        beanRecvMallInfo.setRacaddress1("urtyusastt");
        beanRecvMallInfo.setRacaddress2("urtyusastt");
        beanRecvMallInfo.setRacaddress3("urtyusastt");
        beanRecvMallInfo.setRacaddress4("urtyusastt");
        beanRecvMallInfo.setRacpostcode("");
        beanRecvMallInfo.setAgentbic("");
        beanRecvMallInfo.setPayeePhone("13117512457");
        beanRecvMallInfo.setPayeeOrgcode("1");
        beanRecvMallInfo.setReceivableAmount("");
        beanRecvMallInfo.setPayeeWalletId("1");
        beanRecvMallInfo.setPayeeWalletName("中国长城");
        beanRecvMallInfoList.add(beanRecvMallInfo);

        transInfo.setProtocolCode("1");
        transInfo.setBusinessNo("1");
        transInfo.setPlatVendorid("1");
        transInfo.setOrganizationCode("1");
        transInfo.setAgreeCode("1");
        transInfo.setPartnerSeq("030240098767001490600157339");
        transInfo.setPayChannel("2");
        transInfo.setInternationalFlag("2");
        transInfo.setPayMode("1");
        transInfo.setReservDirect("1");
        transInfo.setOperType("301");
        transInfo.setPayEntitys("1");
        transInfo.setAsynFlag("0");
        transInfo.setLogonId("");
        transInfo.setPayerAccno("");
        transInfo.setPayerAccname("");
        transInfo.setPayerFeeAccno("");
        transInfo.setPayerFeeAccName("");
        transInfo.setPayerFeeCurr("");
        transInfo.setPayMemno("0200EH0013035");
        transInfo.setOrgcode("10237605X");
        transInfo.setOrderCode("2019062730002");
        transInfo.setOrderAmount("11223311");
        transInfo.setOrderCurr("88");
        transInfo.setSumPayamt("11223311");
        transInfo.setOrderRemark("订单备注-直接支付-境内");
        transInfo.setRceiptRemark("回单补充信息备注");
        transInfo.setPurpose("");
        transInfo.setSummary("");
        transInfo.setSubmitTime("20240615162211");
        transInfo.setReturnUrl("");
        transInfo.setCallbackUrl("www.baidu.com");
        transInfo.setAgreementId("34567891");
        transInfo.setInvoiceId("");
        transInfo.setManifestId("");
        transInfo.setAgreementImageId("");
        transInfo.setEnterpriseName("");
        transInfo.setPayRemark("");
        transInfo.setBakReserve1("");
        transInfo.setBakReserve2("");
        transInfo.setBakReserve3("");
        transInfo.setPartnerSeqOrigin("");
        transInfo.setSumPayamtOrigin("");
        transInfo.setReporterName("");
        transInfo.setReporterContact("");
        transInfo.setIdentityMode("0");
        transInfo.setPayerPhone("");
        transInfo.setPayerWalletId("");
        transInfo.setPayerWalletName("");
        transInfo.setRepetCard("2");
        transInfo.setBatchNo("");

        transInfo.setIsPersonalPay("1");
        Map personalMsg = new HashMap<>();
        personalMsg.put("goods_name","goods_name");
        personalMsg.put("access_type","5");
        personalMsg.put("mer_custom_id","100001");
        personalMsg.put("mer_id","020053010625");
        personalMsg.put("scheme","scheme");
        personalMsg.put("biz_type","100001");
        personalMsg.put("goods_id","goods_id");
        personalMsg.put("remark","remark");
        personalMsg.put("order_apd_inf","order_apd_inf");
        personalMsg.put("body","body");
        personalMsg.put("mer_custom_phone","mer_custom_phone");
        personalMsg.put("extend_info","extend_info");
        personalMsg.put("mer_prtcl_no","0200530106250201");
        personalMsg.put("sub_open_id","openid");
        personalMsg.put("wxpay_detail","wxpay_detail");
        personalMsg.put("goods_address","goods_address");
        personalMsg.put("package_name","packagename");
//		personalMsg.put("goods_num","E支付PC页面上展示");
//		personalMsg.put("alipay_detail","支付宝商品详细描述，对于使用单品优惠的商户，该字段必");
        personalMsg.put("shop_appid","appid");

        transInfo.setPersonalMsg(personalMsg);

        transInfo.setPayeeList(beanRecvMallInfoList);
        transInfo.setGoodsList(beanGoodsInfoList);
        bizContent.setTransInfo(transInfo);
        request.setBizContent(bizContent);

        Random rand = new Random();
        String msgId = rand.nextInt(99999) + "msg";
        log.fine("msgId:"+msgId);

        request.setBizContent(bizContent);

        MybankPayGyjrB2bPayapplyResponseV1 response;
        try {
            response = client.execute(request,msgId);
            log.fine("JSONObject.toJSONString(response):"+ JSONObject.toJSONString(response));
            if (response.isSuccess()) {
                // 业务成功处理
                log.fine("response.getReturnCode():"+response.getReturnCode());
            } else {
                //失败
                log.fine("response.getReturnCode():"+response.getReturnCode());
                log.fine("response.getReturnMsg():"+response.getReturnMsg());
            }
        } catch (IcbcApiException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
