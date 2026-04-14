package com.icbc.api;

import com.icbc.api.request.MybankPayGyjrB2bMemregisterRequestV1;
import com.icbc.api.response.MybankPayGyjrB2bMemregisterResponseV1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MybankPayGyjrB2bMemregisterTestV1 {
    protected static final String MY_PRIVATE_KEY = "XXXX";

    protected static final String APIGW_PUBLIC_KEY = "XXXXX";

    protected static final String APP_ID = "123";

    public static void main(String[] args) {

        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);

        MybankPayGyjrB2bMemregisterRequestV1 req = new MybankPayGyjrB2bMemregisterRequestV1();
        req.setServiceUrl("http://IP:PROT/api/mybank/pay/gyjr/b2b/memregister/V1");

        MybankPayGyjrB2bMemregisterRequestV1.RequestV1TransInfo transInfo = new MybankPayGyjrB2bMemregisterRequestV1.RequestV1TransInfo();
        MybankPayGyjrB2bMemregisterRequestV1.RequestV1Biz biz = new MybankPayGyjrB2bMemregisterRequestV1.RequestV1Biz();

        transInfo.setProtocolCode("1");
        transInfo.setBusinessNo("1");
        transInfo.setPlatVendorid("1");
        transInfo.setOrganizationCode("1");
        transInfo.setAgreeCode("AGREECODE20190215");
        transInfo.setMemberNo("001");
        transInfo.setMemberName("会员号1");
        transInfo.setMemberType("01");
        transInfo.setMemberRole("2");
        transInfo.setCertificateType("1");
        transInfo.setCertificateId("123123123");
        transInfo.setCorpRepreName("某某某");
        transInfo.setCorpRepreIdType("1");
        transInfo.setCorpRepreId("123123123123");
        transInfo.setCorpRepreSignDate("2020-02-22");
        transInfo.setDealName("某某某");
        transInfo.setDealTelphoneNo("18575637316");
        transInfo.setMallUrl("http://XXXX");
        transInfo.setIcpCode("123455");
        transInfo.setSingNoNoteAmtd("150000");
        transInfo.setEnterAmtType("0");
        transInfo.setAccBankFlag("1");
        transInfo.setAccno("123123123");
        transInfo.setAccBankFlag("还是某某某");
        transInfo.setAccno("12345");
        transInfo.setAccName("还是某某某");
        transInfo.setAccBankName("还是某某某");
        transInfo.setMerEname("还是某某某");
        transInfo.setMerShname("还是某某某");
        transInfo.setSaleDepName("珠海一部");
        transInfo.setShopAddr("珠海");
        transInfo.setPostCode("12345");
        transInfo.setLinkCode("12345");
        transInfo.seteMail("xx@.qq.com");
        transInfo.setRegAmt("还是某某某");
        transInfo.setCallbackUrl("www.baidu.com");
        transInfo.setOperType("1");
        transInfo.setBillPlaNo("123");
        transInfo.setCopyMemberNo("123");

        List<MybankPayGyjrB2bMemregisterRequestV1.BeanBillAccInfo> billAccInfoList = new ArrayList<MybankPayGyjrB2bMemregisterRequestV1.BeanBillAccInfo>();
        MybankPayGyjrB2bMemregisterRequestV1.BeanBillAccInfo billAccInfo = new MybankPayGyjrB2bMemregisterRequestV1.BeanBillAccInfo();
        billAccInfo.setBillAccBankFlag( "1");
        billAccInfo.setBillAccno("123");
        billAccInfo.setBillBankNo("123");
        billAccInfoList.add(billAccInfo);
        transInfo.setBillAccInfo(billAccInfoList);

        MybankPayGyjrB2bMemregisterRequestV1.BeanEnSurePayCardApply enSurePayCardApply = new MybankPayGyjrB2bMemregisterRequestV1.BeanEnSurePayCardApply();
        enSurePayCardApply.setDistriButorCode("000001");
        enSurePayCardApply.setPayControl("1000000000");
        enSurePayCardApply.setPayerAccno("12345");
        enSurePayCardApply.setPayerAccname("小家电公司");
        enSurePayCardApply.setPayerBankFlag("1");
        enSurePayCardApply.setPayerRepreIdType("1");
        enSurePayCardApply.setPayerBankCode("12345");
        enSurePayCardApply.setSummary("支付");
        enSurePayCardApply.setPurpose("用途");
        enSurePayCardApply.setRemarks("备注");
        enSurePayCardApply.setShare_type("1");

        List<MybankPayGyjrB2bMemregisterRequestV1.BeanPayeeList> payeeList = new ArrayList<MybankPayGyjrB2bMemregisterRequestV1.BeanPayeeList>();
        MybankPayGyjrB2bMemregisterRequestV1.BeanPayeeList payee = new MybankPayGyjrB2bMemregisterRequestV1.BeanPayeeList();
        payee.setCaiZhiKaAccno("0200242353566646141526");
        payee.setPayeeMainFlag("1");
        payee.setPayeeMemno("000001");
        payee.setPayeeAccno("12345");
        payee.setPayeeAccname("小家电公司");
        payee.setPayeeBankFlag("1");
        payee.setPayeeeBankCode("1111");
        payee.setPayeePayType("1");
        payee.setPayeeeShareRatio("80000000");
        payeeList.add(payee);

        enSurePayCardApply.setPayeeList(payeeList);
        transInfo.setEnSurePayCardApply(enSurePayCardApply);

        MybankPayGyjrB2bMemregisterRequestV1.BeanEnsureInfo ensureInfo = new MybankPayGyjrB2bMemregisterRequestV1.BeanEnsureInfo();
        ensureInfo.setPhone("13117512457");
        ensureInfo.setEnsurePayFlag("01");
        ensureInfo.setAccno("12345");
        ensureInfo.setAccname("小家电公司");
        ensureInfo.setAccBankFlag("1");
        ensureInfo.setAccBankName("小家电");
        ensureInfo.setAccBankNo("1231");
        transInfo.setEnsureInfo(ensureInfo);
        biz.setTransInfo(transInfo);
        req.setBizContent(biz);
        MybankPayGyjrB2bMemregisterResponseV1 response = new MybankPayGyjrB2bMemregisterResponseV1();

        Random rand = new Random();
        String msgId = rand.nextInt(99999) + "msg";
        //log.fine(msgId);

        try {
            response = client.execute(req, msgId);
            System.out.println("response:" + response.toString());
            if (response.isSuccess()) {

                System.out.println("success");//
            } else {

                System.out.println("error");//
            }
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }
}
