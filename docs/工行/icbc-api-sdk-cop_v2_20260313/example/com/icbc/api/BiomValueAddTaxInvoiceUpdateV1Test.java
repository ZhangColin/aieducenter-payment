package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.BiomValueAddTaxInvoiceUpdateRequestV1;
import com.icbc.api.response.BiomValueAddTaxInvoiceUpdateResponseV1;

public class BiomValueAddTaxInvoiceUpdateV1Test {

    protected static final String MY_PRIVATE_KEY = "****";

    protected static final String APP_ID = "1234";

    protected static final String APIGW_PUBLIC_KEY = "****";

    public static void main(String[] args) {
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, "UTF-8", "json", APIGW_PUBLIC_KEY, (String) null, (String) null, (String) null, (String) null);

        BiomValueAddTaxInvoiceUpdateRequestV1 request = new BiomValueAddTaxInvoiceUpdateRequestV1();

        BiomValueAddTaxInvoiceUpdateRequestV1.BiomValueAddTaxInvoiceUpdateRequestV1Biz bizContent = new BiomValueAddTaxInvoiceUpdateRequestV1.BiomValueAddTaxInvoiceUpdateRequestV1Biz();

        BiomValueAddTaxInvoiceUpdateRequestV1.Approot approot = new BiomValueAddTaxInvoiceUpdateRequestV1.Approot();

        BiomValueAddTaxInvoiceUpdateRequestV1.PrivateReqBean privateReqBean = new BiomValueAddTaxInvoiceUpdateRequestV1.PrivateReqBean();

        BiomValueAddTaxInvoiceUpdateRequestV1.PublicReqBean publicReqBean = new BiomValueAddTaxInvoiceUpdateRequestV1.PublicReqBean();

        privateReqBean.setInnertaxerno("0020001242");
        privateReqBean.setKpnote("0020001242");
        privateReqBean.setApplnum("0020001242");
        privateReqBean.setOptellerno("0020001242");
        privateReqBean.setTaxer_no("0020001242");
        privateReqBean.setAppno("0020001242");
        privateReqBean.setOpertype("0020001242");

        publicReqBean.setChancode("0020001242");
        publicReqBean.setOapp("0020001242");

        approot.setPrivateReqBean(privateReqBean);
        approot.setPublicReqBean(publicReqBean);

        bizContent.setApproot(approot);
        request.setBizContent(bizContent);
        try {
            BiomValueAddTaxInvoiceUpdateResponseV1 response = client.execute(request);
            if (response.getReturnCode() == 0) {
                System.out.println("ReturnCode:" + response.getReturnCode());
                System.out.println("response:" + JSON.toJSONString(response));
            } else {
                System.out.println("response:" + JSON.toJSONString(response));
                System.out.println("ReturnCode:" + response.getReturnCode());
                System.out.println("ReturnMsg:" + response.getReturnMsg());
            }
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }
}
