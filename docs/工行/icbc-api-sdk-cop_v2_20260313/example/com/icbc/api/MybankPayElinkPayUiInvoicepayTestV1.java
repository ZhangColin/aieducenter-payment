package com.icbc.api;

import java.util.ArrayList;
import java.util.List;

import com.icbc.api.request.MybankPayElinkPayUiInvoicepayRequestV1;
import com.icbc.api.request.MybankPayElinkPayUiInvoicepayRequestV1.MybankPayElinkPayUiInvoicepayRequestV1Biz;
import com.icbc.api.request.MybankPayElinkPayUiInvoicepayRequestV1.MybankPayElinkPayUiInvoicepayRequestV1Biz.Invoice;

/**
 * 发票支付申请
 *
 * @version 2019年10月版
 */
public class MybankPayElinkPayUiInvoicepayTestV1 {
    protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCkdpX4tyY3ROlwI5ilpLQ1bggzL+bLkj+8s+XlGl2ipBDBwDfAoLxiNU7V5nLPv8EYgeq7CV01IWYh9aWpoAJXF7SBW59JWwzExvu5hsBeO2RLBejO+qs/nMKCLX27lH5BbX+POD8Q5klyvxbL+BxFDos6jq4i2S9XY+9UfXTPZ7ZTVkicpxghxThClMGCcxFf4BknvKgm4g2NXikWtbe/q4wgva78VcRwiBd+ANhOx780xpZdHpD5GLSrHKj1loPUd6fF/SlVYHtkl+3/Gr8pXKIbreg0n2tZfpkS9aZ1gPqd9m/B/+Ty6KRCW0VCOwhrVhNAB9Mble8dkp/NuWDHAgMBAAECggEAUp1DPrn513+lZ/BpqKOw5roMInJolFA19jvEOy7gZGfYBwvgtcHIf+jZXjex6swfWolAeY39pUfOvzZfbnLzWf6NbQGyZS34nwx5vLuE/JEHE2y3gumGhqDfUHc7lrPELGet0Srm+z1DHgIBmJpc0Q+6PsMXlGfXESYhPib7O4fGeCw4r8O1DqBVsjHTnAeq8m/1QTuSDf5yIhPudp95/DHizvISNZQk0mTZuo6wcoehCwpLA5jj8qIEqXxTHKhfw1sKz/PMVml22DyLxwqKm9/UDZGZhqlxAktEJQeSfa4e94P++W7dFnNQo+5Y+bXYwyKnXnAgWXGON5H1UjdsgQKBgQDNPl+hxMYoW99bkMUfHQJdmizJHC7DhCtI+bapjjI9Answ7/gkzJtS9bW2Ir28Xwlb0tBsEG25aTN+5QG3L2e/X+2dpZr6NbvWpxy67Y6v/iP+J3dRmRdgJ9GHZaZkrXT5Vzrr1pgzDR3QtlayOlQ2yA/GsgOHbAaUHVoxVHiVhwKBgQDNInkfLBOBrtKpAIp7ckjLhRq3EAjjC3yH7xgR5HWx/8sAVbWg5Ui4nLiEBwD89lRc+EnONvr72XlUIs4qWpZnu+keJHgUXzMR+4BI9jQbAD8iVln6HhAVPjGOb5eYRigKeHgjedPHqSaBx0PHqt5gyDVR14N0HhdtCGpJGZOqwQKBgFTqqJB6pG3QTSJKTHRqAS+O+Vaf1W1JQstnnb6bOYfqfeOqdWVsThtN08euqgfE3k9icp8zUOJ0dzzaLRKIh4kzfFFMUkmRb80IySdB4FZKdMPTuldFWtmKv/MgCG8BITOEdrlayQ/eLmbK+iW1YmBj97sCrdkaxcIEPuvIAjgDAoGADj3kdtuShyBiE8Wr16I5l5eoISmF1G2Juc5Jt4RK/46ow18v+vmwMNEApnnuAaIAfWvng9+hUTbqC4NXGO2tH4qWRrd1dsSdQRRa9bXSLEW6HGIuYQURTMhCnIb22WWVhhSTErN0lpoRyFTTSdt9EO8VdJcn0zn7mmBJMMYL0IECgYEAkV6dKTWoFreY4cpHXRAgQxd65ZcoyYMDX2s9lRh+2FtuHCEjfH+liOsObxs7IVT0EzhxCY+9OfD2u3yZ0X89T3dVo2D/GtrHnJZscqJcz5/iHk9rnHbMvS9qVUtnBVEKxT0cSNhzjUlTmLaR/Mw8a6ahxiYR7sQE2eLRHrNwrnA=";
    protected static final String APP_ID = "10000000000000082009";

    protected static final String SERVICE_URL = "http://ip:port/mybank/pay/elink/pay/ui/invoicepay/V1";

    public static void main(String[] args) {
        String buildPostForm = "";
        UiIcbcClient client = new UiIcbcClient(APP_ID, MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8);
        MybankPayElinkPayUiInvoicepayRequestV1 request = new MybankPayElinkPayUiInvoicepayRequestV1();
        request.setServiceUrl(SERVICE_URL);
        MybankPayElinkPayUiInvoicepayRequestV1Biz bizContent = new MybankPayElinkPayUiInvoicepayRequestV1Biz();
        bizContent.setAgreeCode("11111111111111111");
        bizContent.setPartnerSeq("2019100809090909009021");
        bizContent.setPayType("1");

        List<Invoice> invoiceList = new ArrayList<Invoice>();
        Invoice invoice1 = new Invoice();
        Invoice invoice2 = new Invoice();
        invoice1.setInvoiceNo("123");
        invoice2.setInvoiceNo("4567");
        invoiceList.add(invoice1);
        invoiceList.add(invoice2);
        bizContent.setInvoiceList(invoiceList);

        bizContent.setPayerId("11113333333");
        bizContent.setOrderAmount("12321321");
        bizContent.setOrderCurr("001");
        bizContent.setOrderRemark("");
        bizContent.setRceiptRemark("");
        bizContent.setPurpose("");
        bizContent.setSummary("");
        bizContent.setTrxChannel("01");
        bizContent.setTradeTime("20190319172255");
        bizContent.setReporterContact("");
        bizContent.setReporterName("");
        bizContent.setPayeeId("11111144444");
        bizContent.setPayeeSysflag("1");
        bizContent.setPayeeBankCode("");
        bizContent.setPayeePhone("");
        request.setBizContent(bizContent);
        try {
            buildPostForm = client.buildPostForm(request);
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
        System.out.println(buildPostForm);
    }
}
