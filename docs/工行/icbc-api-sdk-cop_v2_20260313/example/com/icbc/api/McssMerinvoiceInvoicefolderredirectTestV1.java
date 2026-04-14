package com.icbc.api;

import com.alibaba.fastjson.JSONObject;
import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.request.McssMerinvoiceInvoicefolderredirectRequestV1;
import com.icbc.api.request.McssMerinvoiceInvoicefolderredirectRequestV1.McssMerinvoiceInvoicefolderredirectRequestBizV1;
import com.icbc.api.response.McssMerinvoiceInvoicefolderredirectResponseV1;

public class McssMerinvoiceInvoicefolderredirectTestV1 {

    protected static final String APIGW_PUBLIC_KEY = "xxxx";
    protected static final String MY_PRIVATE_KEY = "xxxx";
    protected static final String APP_ID = "xxxx";

    public static void main(String[] args) throws Exception {
        McssMerinvoiceInvoicefolderredirectTestV1 a = new McssMerinvoiceInvoicefolderredirectTestV1();
        a.test_cop();

    }

    public void test_cop() throws IcbcApiException {
        McssMerinvoiceInvoicefolderredirectRequestV1 request = new McssMerinvoiceInvoicefolderredirectRequestV1();
        request.setServiceUrl("http://ip:port/api/mcss/merinvoice/invoicefolderredirect/V1");
        McssMerinvoiceInvoicefolderredirectRequestBizV1 bizContent = new McssMerinvoiceInvoicefolderredirectRequestBizV1();
        //商户APPID
        bizContent.setPartner_appid(APP_ID);
        //商户编号
        bizContent.setPrti_id("020057018890");
        //工行订单号、外部协议编号+商户订单号必输其一
        bizContent.setOrder_id("C251107095511121121CPY");
        //开票企业编号
        bizContent.setOut_vendor_id("20251112KP002");

        request.setBizContent(bizContent);
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        McssMerinvoiceInvoicefolderredirectResponseV1 response = client.execute(request);

    }
}
